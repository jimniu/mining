package com.isd.service.weibo.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.isd.dao.mining.NetworkDAO;
import com.isd.dao.weibo.WbColumnDAO;
import com.isd.dao.weibo.WbResultDAO;
import com.isd.dao.weibo.WbUserDAO;
import com.isd.entity.mining.Network;
import com.isd.entity.mining.Setting;
import com.isd.entity.weibo.WbColumn;
import com.isd.service.weibo.WbColumnService;
import com.isd.util.C;
import com.isd.util.queue.Queue;
import com.isd.util.queue.Sender;

public class WbColumnServiceImpl implements WbColumnService {
	@Autowired
	private WbColumnDAO columnDAO;
	@Autowired
	private WbResultDAO resultDAO;
	@Autowired
	private WbUserDAO weiboDAO;
	@Autowired
	private NetworkDAO networkDAO;

	// 列出用户的任务
	public List<WbColumn> listByNetwork(Integer networkid, Integer type) {
		return columnDAO.listByUser(networkid, type);
	}
	
	public String findKeywordsByNetwork(Integer networkid){
		return columnDAO.findKeywordsByUser(networkid);
	}
	
	//根据用户以及内容类型提取栏目信息，存入哈希表
	public HashMap<Integer, WbColumn>hashByNetwork(Integer networkid, Integer type){
		List<WbColumn> list = columnDAO.listByUser(networkid, type);
		HashMap<Integer, WbColumn> hash = new HashMap<Integer, WbColumn>();
		for(int i=0; i<list.size(); i++){
			WbColumn column = list.get(i);
			hash.put(column.getId(), column);
		}
		return hash;
	}
	

	// 根据任务ID返回任务
	public WbColumn findById(Integer id) {
		return columnDAO.findById(id);
	}

	// 根据迫切程度返回任务
	public WbColumn findByLastid() {
		return columnDAO.findByLastid();
	}
	
	public void save(WbColumn column, Network network){
		String searchword = column.getSearchword();
		Setting setting = Setting.json2obj(network.getSetting());		
		
		//如果超出规定的数量，则删除后面的部分
		String[]array = searchword.split(",");
		List<String> kwlist = new ArrayList<String>();
		for(int j=0; j<array.length; j++){
			String kw = array[j].trim();
			if(kw.length()>0){
				kwlist.add(kw);
			}
		}
		searchword = "";
		for(int j=0; j<kwlist.size(); j++){
			if(j==0){
				searchword = kwlist.get(j).toString();
			}else if(j<setting.getColumnkeyword()){
				searchword += ","+kwlist.get(j).toString();
			}
		}
		
		String fetchword = column.getFetchword();
		if(fetchword==null||fetchword.trim().length()==0){
			fetchword = searchword;
		}else{
			fetchword = fetchword.replaceAll("，", ",").trim();
		}
		
		if(column.getId()==null){
			column.setFetchword(fetchword);
			column.setSearchword(searchword);
			column.setFeedtoday(0);
			column.setFeedtotal(0);
			column.setUsertoday(0);
			column.setUsertotal(0);
			column.setFeedsum(0l);
			column.setFansum(0l);
			column.setHitsum(0);
			column.setStatus(network.getStatus());
			column.setType(0);
			column.setNetworkid(network.getId());
			column.setCtime(new Date());
			column.setModified(0);
			column.setMtime(new Date());
			column.setUtime(new Date());
			columnDAO.save(column);

			this.refresh(column);
		}else{
			WbColumn col = columnDAO.findById(column.getId());
			//防止人为修改链接中的id值来覆盖其他人的数据,用户ID对应不上时不做保存
			if(col.getNetworkid().equals(network.getId())){
				if(!col.getSearchword().equals(searchword)){
					column.setSearchword(searchword);
					
					//如果修改关键词后的24小时内继续修改，系统不算做一次修改
					/*if(col.getMtime()==null||(new Date().getTime()-col.getMtime().getTime())>24*3600*1000){
						//如果修改超过规定的次数，则不允许修改关键词
						if(col.getModified()<setting.getKeywordchange()){
							col.setMtime(new Date());
							col.setModified(col.getModified()+1);
							col.setSearchword(searchword);
						}
					}else{
						col.setSearchword(searchword);
					}*/						
				}
				col.setFetchword(fetchword);
				col.setRelation(column.getRelation());
				col.setTitle(column.getTitle().trim());
				col.setStatus(network.getStatus());
				col.setTask(column.getTask());
				col.setUtime(new Date());
				columnDAO.update(col);
				
				this.refresh(col);
			}
		}
	}

	// 删除任务
	public void delete(Integer columnid) {
		WbColumn column = columnDAO.findById(columnid);
		column.setStatus(C.STATUS_INACTIVE);
		this.refresh(column);
		columnDAO.delete("id", columnid);
	}
	
	//根据迫切程度返回任务
	public WbColumn findByFetchTime(){
		return columnDAO.findByFetchTime();
	}
	
	public void refresh(WbColumn column){
		String result = "success";

		//刷新robot的关键词状态
		if(column!=null&&column.getSearchword()!=null&&column.getSearchword().trim().length()>0){
			//获取用户的信息,提取用户的优先级
			Network network = networkDAO.findById(column.getNetworkid());
			Setting setting = Setting.json2obj(network.getSetting());
			
			//构造消息对象
			Queue queue = new Queue();
			queue.setModule(C.MODULE_WEIBO);
			queue.setColumnid(column.getId());
			queue.setFetchword(column.getFetchword());
			queue.setSearchword(column.getSearchword());
			queue.setNetworkid(column.getNetworkid());
			//对于实时内容，关键词的优先级与账户优先级一致，对于事件和负面，则优先级为0
			if(column.getType()==0) {
				queue.setPriority(network.getPriority());
			}else{
				queue.setPriority(0);
			}
			queue.setRelation(column.getRelation());
			queue.setTask(column.getTask());
			if(network.getStatus()==C.STATUS_ACTIVE &&column.getStatus()==C.STATUS_ACTIVE) {
				queue.setStatus(C.STATUS_ACTIVE);
			}else{
				queue.setStatus(C.STATUS_INACTIVE);
			}
			queue.setType(column.getType());
			queue.setStartpoint(C.stringToDate(setting.getStartpoint()));
			queue.setScope(setting.getScope());
			
			Sender.send(queue);

			//获取已经匹配到的用户数量
			Integer feedtotal = resultDAO.contentCountByColumnDate(column.getId(), C.TIME_ALL);
			Integer feedtoday = resultDAO.contentCountByColumnDate(column.getId(), C.TIME_TODAY);
			Integer usertotal = resultDAO.mediaCountByColumnDate(column.getId(), C.TIME_ALL);
			Integer usertoday = resultDAO.mediaCountByColumnDate(column.getId(), C.TIME_TODAY);

			Long fansum = resultDAO.fansCountByColumn(column.getId());
			Long feedsum = resultDAO.feedCountByColumn(column.getId());
			Integer hitsum = resultDAO.hitCountByColumn(column.getId());

			column.setFeedtotal(feedtotal);
			column.setFeedtoday(feedtoday);
			column.setUsertotal(usertotal);
			column.setUsertoday(usertoday);
			column.setFansum(fansum);
			column.setFeedsum(feedsum);
			column.setHitsum(hitsum);
			column.setFtime(new Date());

			//挖掘任务的状态与所属用户的状态一致,账户停止，其包含的挖掘任务也停止
			column.setStatus(network.getStatus());
			columnDAO.update(column);
		}else if(column!=null){
			column.setFtime(new Date());
			//如果关键词为空，则状态为无效
			column.setStatus(0);
			columnDAO.update(column);	
		}
	}	
}
