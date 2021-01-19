package com.isd.action.mining;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.isd.action.GenericAction;
import com.isd.entity.bbs.LtColumn;
import com.isd.entity.bbs.LtResult;
import com.isd.service.bbs.LtColumnService;
import com.isd.service.bbs.LtResultService;
import com.isd.service.mining.DaystatService;
import com.isd.service.mining.OpinionService;
import com.isd.util.C;

public class BbsAction extends GenericAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8320146062924042874L;
	@Autowired
	private LtColumnService columnService;
	@Autowired
	private LtResultService resultService;
	@Autowired
	private DaystatService statService;
	@Autowired
	private OpinionService opinionService;
	
	private LtColumn column;
	private LtResult result;
	
	private String type = "keyword";
	private Integer days = 7;
	private Integer count = 5;
	
	//搜索用处
	private String from;
	private String to;

	//栏目设置的界面
	public String column(){
		List<LtColumn> list = columnService.listByNetwork(mynetwork.getId(), 0);
		ctx.put("list", list);
		return SUCCESS;
	}
	
	//保存栏目的结果
	public String saveColumn(){
		//保存设置
		columnService.save(request, mynetwork, prefix);
		
		ctx.put("redirectaction", "bbs/content");
		return REDIRECTACTION;
	}
	
	//调用robot，搜索文章
	public String refresh(){
		column = columnService.findOneByFetchTime();
		columnService.refresh(prefix, column);
		ctx.put("message", "SUCCESS");
		return MESSAGE;
	}
	
	//今日动态内容部分
	public String content(){
		//提取内容的最早发布时间和最晚发布时间
		Date from = new Date();
		Date to   = new Date(0);
		
		List<LtColumn> columnlist = columnService.listByNetwork(mynetwork.getId(), 0);		
		if(columnlist.size()==0){
			ctx.put("redirectaction", "bbs/column");
			return REDIRECTACTION;
		}else{
			List<List> resultlist = new ArrayList<List>();
			for(int i=0; i<columnlist.size(); i++){
				List<LtResult> list = resultService.contentListByColumn(columnlist.get(i).getId(), 0, 10);
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
			int updatecount = resultService.contentCountByNetworkTypeRelationDate(mynetwork.getId(), 0, 0, 0);
			
			//正反面的信息
			HashMap<Long, Integer> opinionhash = opinionService.findByNetworkModulePtime(mynetwork.getId(), C.MODULE_BBS, from, to);
			
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
		
		if(column.getId()!=null){
			column = columnService.findById(column.getId());
			if(column.getNetworkid().equals(mynetwork.getId())){
				List<LtResult> list = resultService.contentListByColumn(column.getId(), pageindex*pagesize, pagesize);
				//当日更新的数据量
				int updatecount = column.getToday();
				//栏目收录的内容总量
				int total = resultService.contentCountByColumn(column.getId());
				
				if(list.size()>0){
					if(list.get(0).getPtime().after(to)){
						to = list.get(0).getPtime();
					}
					if(list.get(list.size()-1).getPtime().before(from)){
						from = list.get(list.size()-1).getPtime();
					}
				}
				//正反面的信息
				HashMap<Long, Integer> opinionhash = opinionService.findByNetworkModulePtime(mynetwork.getId(), C.MODULE_BBS, from, to);
				
				
				ctx.put("updatecount", String.valueOf(updatecount));
				ctx.put("total", String.valueOf(total));
				ctx.put("column", column);
				ctx.put("list", list);		
				ctx.put("opinionhash", opinionhash);
			}			
		}else if(column.getType()!=null){
			List<LtResult> list = resultService.contentListByUserType(mynetwork.getId(), column.getType(), pageindex*pagesize, pagesize);
			//当日更新的数据量
			int updatecount = resultService.contentCountByNetworkTypeRelationDate(mynetwork.getId(), column.getType(), 0, C.TIME_TODAY);
			//栏目收录的内容总量
			int total = resultService.contentCountByNetworkTypeRelationDate(mynetwork.getId(), column.getType(), 0, C.TIME_ALL );
			
			if(list.size()>0){
				if(list.get(0).getPtime().after(to)){
					to = list.get(0).getPtime();
				}
				if(list.get(list.size()-1).getPtime().before(from)){
					from = list.get(list.size()-1).getPtime();
				}
			}
			//正反面的信息
			HashMap<Long, Integer> opinionhash = opinionService.findByNetworkModulePtime(mynetwork.getId(), C.MODULE_BBS, from, to);
			
			ctx.put("updatecount", String.valueOf(updatecount));
			ctx.put("total", String.valueOf(total));
			ctx.put("list", list);		
			ctx.put("opinionhash", opinionhash);
		}
		ctx.put("today", C.dateToString(new Date(), "yyyy-MM-dd"));
		return SUCCESS;
	}
	
	public String openid(){
		pagesize = 20;
		if(column==null||column.getRelation()==null){
			List list = resultService.openidListByUserType(mynetwork.getId(),0,  pageindex*pagesize, pagesize);
			int total = resultService.openidCountByUserType(mynetwork.getId(), 0);
			ctx.put("list", list);
			ctx.put("total", String.valueOf(total));
		}else{
			List list = resultService.openidListByUserTypeRelation(mynetwork.getId(), 0, column.getRelation(), pageindex*pagesize, pagesize);
			int total = resultService.openidCountByUserTypeRelation(mynetwork.getId(), 0, column.getRelation());
			ctx.put("list", list);
			ctx.put("total", String.valueOf(total));
		}
		return SUCCESS;
	}	
	
	
	//数据结果页面
	public String result(){
		List<LtColumn> columnlist = columnService.listByNetworkTypeRelation(mynetwork.getId(), 0,  C.RELATION_ALL);	
		ctx.put("list", columnlist);
		return SUCCESS;
	}

	//媒体关注度分析
	public String sitechart(){
		List<LtColumn> columnlist = columnService.listByNetworkTypeRelation(mynetwork.getId(), 0, C.RELATION_ALL);
		List<List> datalist = new ArrayList<List>();
		for(int i=0; i<columnlist.size(); i++) {
			LtColumn col = columnlist.get(i);
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
		List<LtColumn> columnlist = columnService.listByNetwork(mynetwork.getId(), 0);
		
		List<String> timelist = new ArrayList<String>();
		HashMap<String, String> hashmap = new HashMap<String, String>();
		for(int i=0; i<list.size(); i++){
			Object[] item = (Object[])list.get(i);
			String time = (String)item[0];
			Integer serno = (Integer)item[1];
			String total = item[2].toString();
			hashmap.put(time+"_"+serno, total);
			if(!timelist.contains(time)){
				timelist.add(time);
			}
		}

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
		if(result!=null&&result.getSitename()!=null&&result.getSitename().length()>0){
			result.setSitename(C.getURLChinese(result.getSitename()));
		}
		
		List list = resultService.listByFilter(mynetwork.getId(), result, from, to, pageindex*pagesize, pagesize);
		//int total = resultService.countByFilter(mynetwork.getId(), result, from, to);
		ctx.put("list", list);
		//ctx.put("total", String.valueOf(total));
		return SUCCESS;
	}
	
	public String export(){
		if(result!=null&&result.getTitle()!=null&&result.getTitle().length()>0){
			result.setTitle(C.getURLChinese(result.getTitle()));
		}
		if(result!=null&&result.getSitename()!=null&&result.getSitename().length()>0){
			result.setSitename(C.getURLChinese(result.getSitename()));
		}
		
		List list = resultService.listByFilter(mynetwork.getId(), result, from, to, pageindex*pagesize, pagesize);
		ctx.put("list", list);
		
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=bbs_"+C.getDate(0)+"_search_result.xls");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type","application/vnd.ms-excel");		
		return SUCCESS;
	}
	
	//总揽的按月统计数据
	public String monthly(){
		List list = statService.userMonthly(mynetwork.getId(), C.MODULE_BBS, 0, 365);
		ctx.put("list", list);
		return SUCCESS;
	}
	
	//删除帖子
	public String delete(){
		boolean resp = resultService.delete(mynetwork.getId(), result.getId());
		if(resp){
			ctx.put("message", "success");
		}else{
			ctx.put("message", "failure");
		}
		return MESSAGE;
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

	public LtColumn getColumn() {
		return column;
	}

	public void setColumn(LtColumn column) {
		this.column = column;
	}

	public LtResult getResult() {
		return result;
	}

	public void setResult(LtResult result) {
		this.result = result;
	}

}
