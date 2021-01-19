package com.isd.action.mining;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.isd.action.GenericAction;
import com.isd.entity.wechat.WxColumn;
import com.isd.entity.wechat.WxResult;
import com.isd.service.mining.DaystatService;
import com.isd.service.mining.OpinionService;
import com.isd.service.wechat.WxColumnService;
import com.isd.service.wechat.WxResultService;
import com.isd.util.C;

public class WechatAction extends GenericAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8320146062924042874L;
	@Autowired
	private WxColumnService columnService;
	@Autowired
	private WxResultService resultService;
	@Autowired
	private DaystatService statService;
	@Autowired
	private OpinionService opinionService;
	
	private WxColumn column;
	private WxResult result;
	
	private String type = "ptime";
	private Integer days;
	private Integer wxid;
	private Integer count = 5;
	
	//搜索用处
	private String from;
	private String to;

	//微信设置的界面
	public String column(){
		List<WxColumn> list = columnService.listByNetworkType(mynetwork.getId(), 0);
		ctx.put("list", list);
		return SUCCESS;
	}

	//保存微信的结果
	public String saveColumn(){
		//保存设置
		columnService.save(request, mynetwork, prefix);
		
		ctx.put("redirectaction", "wechat/content");
		return REDIRECTACTION;
	}
	
	//调用robot，更新栏目的统计，补全结果中的信息
	public String refresh(){
		column = columnService.findOneByFetchTime();

		columnService.refresh(prefix, column);
		ctx.put("message", "SUCCESS");
		return MESSAGE;
	}

	public String detail(){
		result = resultService.findById(result.getId());
		String url = "http://localhost:8080/robot/wechat/content.shtml?content.id="+result.getContentid();
		if(host.indexOf("localhost")==-1){
			url = "http://master.dig88.cn:8080/robot/wechat/content.shtml?content.id="+result.getContentid();
		}
		ctx.put("url", url);
		return SUCCESS;
	}
	
	//今日动态内容部分
	public String content(){
		List<WxColumn> columnlist = columnService.listByNetworkType(mynetwork.getId(), C.TYPE_TRACK);		
		if(columnlist.size()==0){
			ctx.put("redirectaction", "wechat/column");
			return REDIRECTACTION;
		}else{
			//提取内容的最早发布时间和最晚发布时间
			Date from = new Date();
			Date to   = new Date(0);
			
			List<List> resultlist = new ArrayList<List>();
			for(int i=0; i<columnlist.size(); i++){
				List<WxResult> list = resultService.contentListByColumnDateOrder(columnlist.get(i).getId(), "ptime", C.TIME_ALL, 0, 10);
				resultlist.add(list);
				
				if(list.size()>0){
					if(list.get(0).getPtime().after(to)){
						to = list.get(0).getPtime();
					}
					if(list.get(list.size()-1).getPtime().before(from)){
						from = list.get(list.size()-1).getPtime();
					}
				}				
			}
			//当日更新的数据量
			int updatecount = resultService.contentCountByNetworkTypeRelationDate(mynetwork.getId(), C.TYPE_TRACK, 0, C.TIME_TODAY);
			
			//正反面的信息
			HashMap<Long, Integer> opinionhash = opinionService.findByNetworkModulePtime(mynetwork.getId(), C.MODULE_WECHAT, from, to);
			
			ctx.put("updatecount", String.valueOf(updatecount));
			ctx.put("today", C.dateToString(new Date(), "yyyy-MM-dd"));
			ctx.put("columnlist", columnlist);
			ctx.put("resultlist", resultlist);
			ctx.put("opinionhash", opinionhash);
			return SUCCESS;
		}
	}
	
	//今日动态内容更多
	public String more(){
		//提取内容的最早发布时间和最晚发布时间
		Date from = new Date();
		Date to   = new Date(0);
		
		if(days==null){
			days = C.TIME_ALL;
		}
		if(column.getId()!=null){
			column = columnService.findById(column.getId());
			if(column.getNetworkid().equals(mynetwork.getId())){
				List<WxResult> list = resultService.contentListByColumnDateOrder(column.getId(), type, days, pageindex*pagesize, pagesize);
				//给定时间范围内，栏目收录的内容总量
				Integer total = resultService.contentCountByColumnDate(column.getId(), days);
				
				//当日更新的数据量
				Integer updatecount = resultService.contentCountByColumnDate(column.getId(), C.TIME_TODAY);;

				if(list.size()>0){
					if(list.get(0).getPtime().after(to)){
						to = list.get(0).getPtime();
					}
					if(list.get(list.size()-1).getPtime().before(from)){
						from = list.get(list.size()-1).getPtime();
					}
				}
				//正反面的信息
				HashMap<Long, Integer> opinionhash = opinionService.findByNetworkModulePtime(mynetwork.getId(), C.MODULE_WECHAT, from, to);

				ctx.put("opinionhash", opinionhash);
				ctx.put("updatecount", String.valueOf(updatecount));
				ctx.put("total", String.valueOf(total));
				ctx.put("column", column);
				ctx.put("list", list);	
			}			
		}else if(column.getType()!=null){
			List<WxResult> list = resultService.contentListByNetworkTypeDateOrder(mynetwork.getId(), column.getType(), type, days,  pageindex*pagesize, pagesize);
			//给定时间范围内，用户收录的内容总量
			int total = resultService.contentCountByNetworkTypeRelationDate(mynetwork.getId(), column.getType(), 0, days);
			
			//当日更新的数据量
			int updatecount = resultService.contentCountByNetworkTypeRelationDate(mynetwork.getId(), column.getType(), 0, C.TIME_TODAY);
			
			if(list.size()>0){
				if(list.get(0).getPtime().after(to)){
					to = list.get(0).getPtime();
				}
				if(list.get(list.size()-1).getPtime().before(from)){
					from = list.get(list.size()-1).getPtime();
				}
			}
			//正反面的信息
			HashMap<Long, Integer> opinionhash = opinionService.findByNetworkModulePtime(mynetwork.getId(), C.MODULE_WECHAT, from, to);
			
			ctx.put("opinionhash", opinionhash);
			ctx.put("updatecount", String.valueOf(updatecount));
			ctx.put("total", String.valueOf(total));
			ctx.put("list", list);				
		}
		ctx.put("today", C.dateToString(new Date(), "yyyy-MM-dd"));
		return SUCCESS;
	}
	
	public String openid(){
		pagesize = 10;
		List list = null;
		if(column==null||column.getRelation()==null){
			list = resultService.mediaListByNetworkTypeRelationDate(mynetwork.getId(), C.TYPE_TRACK, 0, C.TIME_ALL, pageindex*pagesize, pagesize);
			int total = resultService.mediaCountByNetworkTypeRelationDate(mynetwork.getId(), C.TYPE_TRACK, 0, C.TIME_ALL);
			ctx.put("list", list);
			ctx.put("total", String.valueOf(total));
		}else{
			list = resultService.mediaListByNetworkTypeRelationDate(mynetwork.getId(), C.TYPE_TRACK, column.getRelation(), C.TIME_ALL, pageindex*pagesize, pagesize);
			int total = resultService.mediaCountByNetworkTypeRelationDate(mynetwork.getId(), C.TYPE_TRACK, column.getRelation(), C.TIME_ALL);
			ctx.put("list", list);
			ctx.put("total", String.valueOf(total));
		}
		
		return SUCCESS;
	}	
	
	//获取公众号的最新文章
	public String latest(){
		List<WxResult> list = resultService.contentListByMedia(result.getWxid(), 0, 1);
		if(list.size()>0){
			ctx.put("text", list.get(0).getTitle());
		}
		return TEXT;
	}
	
	//数据结果页面
	public String result(){
		List<WxColumn> columnlist = columnService.listByNetworkTypeRelation(mynetwork.getId(), 0, C.RELATION_ALL);	
		ctx.put("list", columnlist);
		return SUCCESS;
	}

	//媒体关注度分析
	public String sitechart(){
		List<WxColumn> columnlist = columnService.listByNetworkTypeRelation(mynetwork.getId(), C.TYPE_TRACK, C.RELATION_ALL);
		List<List> datalist = new ArrayList<List>();
		for(int i=0; i<columnlist.size(); i++) {
			WxColumn col = columnlist.get(i);
			List list = resultService.sitereport(mynetwork.getId(), col.getId(), days, count);
			datalist.add(list);
		}
		ctx.put("columnlist", columnlist);
		ctx.put("datalist", datalist);
		return SUCCESS;
	}
	
	//栏目挖掘数据的分析
	public String columnchart(){
		List list =	resultService.columnreport(mynetwork.getId(), C.TYPE_TRACK, C.RELATION_ALL, days);

		ctx.put("list", list);
		return SUCCESS;		
	}
	
	//数据存量分析
	public String stockchart(){
		List list =	resultService.stockreport(mynetwork.getId(), C.TYPE_TRACK, C.RELATION_ALL, days);
		
		List<String> timelist = new ArrayList<String>();
		HashMap<String, String> hashmap = new HashMap<String, String>();
		for(int i=0; i<list.size(); i++){
			Object[] item = (Object[])list.get(i);
			String time = (String)item[0];
			Integer columnid = (Integer)item[1];
			String total = item[2].toString();
			hashmap.put(time+"_"+columnid, total);
			if(!timelist.contains(time)){
				timelist.add(time);
			}
		}
	
		List<WxColumn> columnlist = columnService.listByNetworkType(mynetwork.getId(), C.TYPE_TRACK);
		
		ctx.put("timelist", timelist);
		ctx.put("hashmap", hashmap);
		ctx.put("columnlist", columnlist);
		return SUCCESS;
	}
	
	//根据过滤条件显示结果
	public String filter(){
		if(result!=null&&result.getTitle()!=null&&result.getTitle().length()>0){
			result.setTitle(C.getURLChinese(result.getTitle()));
		}
		if(result!=null&&result.getWxname()!=null&&result.getWxname().length()>0){
			result.setWxname(C.getURLChinese(result.getWxname()));
		}
		
		List list = resultService.listByFilter(mynetwork.getId(), result, from, to, pageindex*pagesize, pagesize);
		
		//提取内容的最早发布时间和最晚发布时间
		Date datefrom = new Date();
		Date dateto   = new Date(0);
		if(list.size()>0){
			Date ptime = (Date)((Object[])list.get(0))[4];
			if(ptime.after(dateto)){
				dateto = ptime;
			}
			
			ptime = (Date)((Object[])list.get(list.size()-1))[4];
			if(ptime.before(datefrom)){
				datefrom = ptime;
			}
		}
		//正反面的信息
		HashMap<Long, Integer> opinionhash = opinionService.findByNetworkModulePtime(mynetwork.getId(), C.MODULE_WECHAT, datefrom, dateto);

		//int total = resultService.countByFilter(mynetwork.getId(), result, from, to);
		ctx.put("list", list);
		ctx.put("opinionhash", opinionhash);
		//ctx.put("total", String.valueOf(total));
		return SUCCESS;
	}
	
	//根据过滤条件导出结果
	public String export(){
		if(result!=null&&result.getTitle()!=null&&result.getTitle().length()>0){
			result.setTitle(C.getURLChinese(result.getTitle()));
		}
		if(result!=null&&result.getWxname()!=null&&result.getWxname().length()>0){
			result.setWxname(C.getURLChinese(result.getWxname()));
		}
		
		List list = resultService.listByFilter(mynetwork.getId(), result, from, to, pageindex*pagesize, pagesize);
		ctx.put("list", list);
		
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=Wechat_"+C.getDate(0)+"_search_result.xls");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type","application/vnd.ms-excel");
		return SUCCESS;
	}
	
	//总揽的按月统计数据
	public String monthly(){
		List list = statService.userMonthly(mynetwork.getId(), C.MODULE_WECHAT, 0, 365);
		ctx.put("list", list);
		return SUCCESS;
	}
	
	//删除微信文章
	public String delete(){
		boolean resp = resultService.delete(mynetwork.getId(), result.getId());
		if(resp){
			ctx.put("message", "success");
		}else{
			ctx.put("message", "failure");
		}
		return MESSAGE;
	}
	
	//自画像
	public String paint(){
		return SUCCESS;
	}
	
	//自画像列表
	public String paintlist(){
		return SUCCESS;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public Integer getWxid() {
		return wxid;
	}

	public void setWxid(Integer wxid) {
		this.wxid = wxid;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public WxColumn getColumn() {
		return column;
	}

	public void setColumn(WxColumn column) {
		this.column = column;
	}

	public WxResult getResult() {
		return result;
	}

	public void setResult(WxResult result) {
		this.result = result;
	}
}
