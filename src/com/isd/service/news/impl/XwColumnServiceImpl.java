package com.isd.service.news.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.isd.dao.mining.NetworkDAO;
import com.isd.dao.news.XwColumnDAO;
import com.isd.dao.news.XwResultDAO;
import com.isd.entity.mining.Network;
import com.isd.entity.mining.Setting;
import com.isd.entity.news.XwColumn;
import com.isd.service.news.XwColumnService;
import com.isd.util.C;
import com.isd.util.queue.Queue;
import com.isd.util.queue.Sender;

public class XwColumnServiceImpl implements XwColumnService{
	@Autowired
	private XwColumnDAO columnDAO;
	@Autowired
	private XwResultDAO resultDAO;
	@Autowired
	private NetworkDAO networkDAO;
	
	public String findKeywordsByNetwork(Integer networkid){
		return columnDAO.findKeywordsByUser(networkid);
	}
	
	public XwColumn findById(Integer id){
		return columnDAO.findById(id);
	}
	//根据搜索时间，提取搜索时间最早的设置
	public XwColumn findOneByFetchTime(){
		return columnDAO.findOneByFetchTime();
	}


	//根据用户ID搜索微信关键词设置
	public List<XwColumn> listByNetwork(Integer uid, Integer type){
		return columnDAO.listByUser(uid, type);
	}
	public List<XwColumn> listByNetworkTrackRelation(Integer uid, Integer type, Integer relation){
		return columnDAO.listByNetworkTrackRelation(uid, type, relation);
	}	
	
	//根据用户以及内容类型提取栏目信息，存入哈希表
	public HashMap<Integer, XwColumn> hashByNetwork(Integer networkid, Integer type){
		List<XwColumn> list = columnDAO.listByUser(networkid, type);
		HashMap<Integer, XwColumn> hash = new HashMap<Integer, XwColumn>();
		for(int i=0; i<list.size(); i++){
			XwColumn column = list.get(i);
			hash.put(column.getId(), column);
		}
		return hash;
	}
	
	//保存设置
	public void columnsave(HttpServletRequest request, Network network, String prefix){
		Setting setting = Setting.json2obj(network.getSetting());
		List<XwColumn> list = columnDAO.listByUser(network.getId(), 0);
		for(int i=0; i<6; i++){
			XwColumn column = new XwColumn();
			if(list.size()>i){
				column = list.get(i);
			}
			
			String title 		= request.getParameter("title"+i);
			String searchword 	= request.getParameter("searchword"+i);
			String fetchword 	= request.getParameter("fetchword"+i);
			String relation 	= request.getParameter("relation"+i);
			

			searchword  = searchword.replaceAll("，", ",").trim();
			
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
			
			if(fetchword==null||fetchword.trim().length()==0){
				fetchword = searchword;
			}else{
				fetchword = fetchword.replaceAll("，", ",").trim();
			}
			
			//如果搜索关键词有变化，则判断距离上次有效更改关键词的时间，24小时之内部级做次数
			if(column.getSearchword()==null){
				column.setModified(0);
				column.setCtime(new Date());
				column.setMtime(new Date());
				column.setTotal(0);
				column.setTotal(0);
				column.setSearchword(searchword);
			}else if(!column.getSearchword().equals(searchword)){
				column.setSearchword(searchword);
				//如果修改关键词后的24小时内继续修改，系统不算做一次修改
				/*if(column.getMtime()==null||(new Date().getTime()-column.getMtime().getTime())>24*3600*1000){
					//如果修改超过规定的次数，则不允许修改关键词
					if(column.getModified()<setting.getKeywordchange()){
						column.setMtime(new Date());
						column.setModified(column.getModified()+1);
						column.setSearchword(searchword);
					}
				}else{
					column.setSearchword(searchword);
				}*/
			}

			column.setFetchword(fetchword);						
			column.setUtime(new Date());
			column.setTitle(title);
			column.setRelation(Integer.parseInt(relation));
			column.setStatus(C.STATUS_INACTIVE);
			if(searchword.length()>0||fetchword.length()>0){
				column.setStatus(network.getStatus());
			}
			
			column.setSerno(i);
			column.setNetworkid(network.getId());
			column.setType(0);
	
			if(column.getId()==null){
				columnDAO.save(column);
			}else{
				columnDAO.update(column);
			}
			
			//刷新后台
			this.refresh(prefix, column);
		}

		//如果栏目数多于6个，删除多余的部分
		if(list.size()>6){
			for(int i=6; i<list.size(); i++){
				XwColumn column = list.get(i);
				columnDAO.delete(column.getId());
			}
		}
	}
	
	//第三方结果
	public void refresh(String prefix, XwColumn column){
		//刷新robot的关键词状态
		if(column!=null&&column.getSearchword()!=null&&column.getSearchword().trim().length()>0){
			//获取用户的信息,提取用户的优先级
			Network network = networkDAO.findById(column.getNetworkid());
			Setting setting = Setting.json2obj(network.getSetting());

			//构造消息对象
			Queue queue = new Queue();
			queue.setModule(C.MODULE_NEWS);
			queue.setColumnid(column.getId());
			queue.setFetchword(column.getFetchword());
			queue.setSearchword(column.getSearchword());
			queue.setNetworkid(column.getNetworkid());
			queue.setSerno(column.getSerno());
			//对于实时内容，关键词的优先级与账户优先级一致，对于事件和负面，则优先级为0
			if(column.getType()==0) {
				queue.setPriority(network.getPriority());
			}else{
				queue.setPriority(0);
			}
			queue.setRelation(column.getRelation());
			queue.setStatus(network.getStatus());
			queue.setType(column.getType());
			queue.setStartpoint(C.stringToDate(setting.getStartpoint()));
			queue.setScope(setting.getScope());

			Sender.send(queue);

			//更新栏目的统计数据
			Integer contenttotal = resultDAO.contentCountByColumnDate(column.getId(), C.TIME_ALL);
			Integer contenttoday = resultDAO.contentCountByColumnDate(column.getId(), C.TIME_TODAY);
			column.setTotal(contenttotal);
			column.setToday(contenttoday);
			//挖掘任务的状态与所属用户的状态一致,账户停止，其包含的挖掘任务也停止
			column.setStatus(network.getStatus());
			column.setFtime(new Date());
			columnDAO.update(column);
		}else if(column!=null){
			column.setFtime(new Date());
			column.setStatus(0);
			columnDAO.update(column);	
		}
	}

}
