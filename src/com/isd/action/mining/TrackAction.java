package com.isd.action.mining;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;

import com.isd.action.GenericAction;
import com.isd.entity.common.Category;
import com.isd.entity.news.XwColumn;
import com.isd.entity.news.XwResult;
import com.isd.service.common.CategoryService;
import com.isd.service.mining.DaystatService;
import com.isd.service.mining.OpinionService;
import com.isd.service.news.XwColumnService;
import com.isd.service.news.XwResultService;
import com.isd.util.C;

public class TrackAction extends GenericAction{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1420747384834238984L;
	
	@Autowired
	private XwColumnService columnService;
	@Autowired
	private XwResultService resultService;
	@Autowired
	private DaystatService statService;
	@Autowired
	private CategoryService categoryService;	
	@Autowired
	private OpinionService opinionService;
	
	private XwColumn column;
	private XwResult result;

    private Integer days = 7;
    private Integer count = 5;
	private Integer relation = 0;
	private Integer origin = 0;
 
	//搜索用处
	private String from;
	private String to;
    
    //今日动态
	public String content(){
		List<XwColumn> columnlist = columnService.listByNetwork(mynetwork.getId(), 0);		
		if(columnlist.size()==0){
			ctx.put("redirectaction", "track/column");
			return REDIRECTACTION;
		}else{
			//提取内容的最早发布时间和最晚发布时间
			Date from = new Date();
			Date to   = new Date(0);
			List<List<XwResult>> resultlist = new ArrayList<List<XwResult>>();
			for(int i=0; i<columnlist.size(); i++){
				List<XwResult> list = resultService.contentListByColumnDate(mynetwork.getId(), columnlist.get(i).getId(), C.TIME_ALL, 0, 10);
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
			HashMap<Long, Integer> opinionhash = opinionService.findByNetworkModulePtime(mynetwork.getId(), C.MODULE_NEWS, from, to);
			
			ctx.put("updatecount", String.valueOf(updatecount));
			ctx.put("today", C.dateToString(new Date(), "yyyy-MM-dd"));
			ctx.put("columnlist", columnlist);
			ctx.put("resultlist", resultlist);
			ctx.put("opinionhash", opinionhash);
			return SUCCESS;
		}
	}
	
	//更多内容
	public String more(){
		//提取内容的最早发布时间和最晚发布时间
		Date from = new Date();
		Date to   = new Date(0);
		
		if(column.getId()!=null){
			column = columnService.findById(column.getId());
			List<XwResult> resultlist = resultService.contentListByColumnDate(mynetwork.getId(), column.getId(), C.TIME_ALL, pageindex*pagesize, pagesize);

			int total = resultService.contentCountByColumnDate(column.getId(), C.TIME_ALL);
			//当日更新的数据量
			int updatecount = resultService.contentCountByColumnDate(column.getId(), C.TIME_TODAY);
			

			if(resultlist.size()>0){
				if(resultlist.get(0).getPtime().after(to)){
					to = resultlist.get(0).getPtime();
				}
				if(resultlist.get(resultlist.size()-1).getPtime().before(from)){
					from = resultlist.get(resultlist.size()-1).getPtime();
				}
			}
			//正反面的信息
			HashMap<Long, Integer> opinionhash = opinionService.findByNetworkModulePtime(mynetwork.getId(), C.MODULE_NEWS, from, to);

			
			ctx.put("updatecount", String.valueOf(updatecount));
			ctx.put("total", String.valueOf(total));
			ctx.put("resultlist", resultlist);
			ctx.put("opinionhash", opinionhash);
			
		}else if(column.getType()!=null){
			List<XwResult> resultlist = resultService.contentListByNetworkTypeRelationDate(mynetwork.getId(), column.getType(), C.RELATION_ALL, C.TIME_ALL, pageindex*pagesize, pagesize);
			int total = resultService.contentCountByNetworkTypeRelationDate(mynetwork.getId(), column.getType(), 0, C.TIME_ALL);
			
			//当日更新的数据量
			int updatecount = resultService.contentCountByNetworkTypeRelationDate(mynetwork.getId(), column.getType(), 0, C.TIME_TODAY);
			
			if(resultlist.size()>0){
				if(resultlist.get(0).getPtime().after(to)){
					to = resultlist.get(0).getPtime();
				}
				if(resultlist.get(resultlist.size()-1).getPtime().before(from)){
					from = resultlist.get(resultlist.size()-1).getPtime();
				}
			}
			//正反面的信息
			HashMap<Long, Integer> opinionhash = opinionService.findByNetworkModulePtime(mynetwork.getId(), C.MODULE_NEWS, from, to);
			
			ctx.put("updatecount", String.valueOf(updatecount));
			ctx.put("total", String.valueOf(total));
			ctx.put("resultlist", resultlist);		
			ctx.put("opinionhash", opinionhash);
		}
		ctx.put("today", C.dateToString(new Date(), "yyyy-MM-dd"));
		return SUCCESS;
	}	
	
	//监控设置
	public String column(){
		List<XwColumn> list = columnService.listByNetwork(mynetwork.getId(), 0);
		
		ctx.put("list", list);
		return SUCCESS;
	}
	
	//保存监控设置
	public String columnsave(){
		//保存栏目设置
		columnService.columnsave(request, mynetwork, prefix);
		ctx.put("redirectaction", "track/content");
		return REDIRECTACTION;
	}

	//第三方搜索
	public String refresh(){
		column = columnService.findOneByFetchTime();
		columnService.refresh(prefix, column);
		ctx.put("message", "SUCCESS");
		return MESSAGE;
	}	
	
	
	//来源报表
	public String origin(){
		return SUCCESS;
	}

	//根据查询条件显示不同来源的比例
	public String originchart(){
		List list = resultService.originCountByUserTypeRelationDate(mynetwork.getId(), C.TYPE_TRACK, relation, days);
		ctx.put("list", list);
		return SUCCESS;
	}
	
	//按媒体来源统计文章数
	public String origincategorychart(){
		List catelist = resultService.categoryContentCountByUserTypeRelationOriginDate(mynetwork.getId(), C.TYPE_TRACK, relation, origin, days);
		HashMap<Integer, Category> catehash = categoryService.hash();
	
		//只提取前10位的分类
		for(int i=0;i<catelist.size(); i++){
			Object[] object = (Object[])catelist.get(i);
			if(object[0]==null||object[0].toString().equals("0")){
				catelist.remove(i);
			}
		}
		catelist = catelist.subList(0, catelist.size()>10?10:catelist.size());
		
		ctx.put("catelist", catelist);
		ctx.put("catehash", catehash);
		return SUCCESS;
	}	
	
	

	//分类报表输出
	public String category(){
		return SUCCESS;
	}	
	
	//按媒体来源统计文章数
	public String categorychart(){
		List catelist = resultService.categoryContentCountByNetworkTypeRelationDate(mynetwork.getId(), C.TYPE_TRACK, relation, days);
		List columnlist	  = resultService.columnContentCountByNetworkTypeRelationDate(mynetwork.getId(), C.TYPE_TRACK, relation, days);
		HashMap<Integer, Category> catehash = categoryService.hash();
		HashMap<Integer, XwColumn> columnhash = columnService.hashByNetwork(mynetwork.getId(), C.TYPE_TRACK);
		
		//只提取前10位的分类
		for(int i=0;i<catelist.size(); i++){
			Object[] object = (Object[])catelist.get(i);
			if(object[0]==null||object[0].toString().equals("0")){
				catelist.remove(i);
			}
		}
		catelist = catelist.subList(0, catelist.size()>10?10:catelist.size());
		
		ctx.put("catelist", catelist);
		ctx.put("columnlist", columnlist);
		ctx.put("catehash", catehash);
		ctx.put("columnhash", columnhash);
		return SUCCESS;
	}
	
	//按日期、媒体来源统计媒体数
	public String mediachart(){
		//提取内容最多的媒体分类
		List catelist = resultService.categoryContentCountByNetworkTypeRelationDate(mynetwork.getId(), C.TYPE_TRACK, relation, days);
		for(int i=0;i<catelist.size(); i++){
			Object[] object = (Object[])catelist.get(i);
			if(object[0]==null||object[0].toString().equals("0")){
				catelist.remove(i);
			}
		}
		catelist = catelist.subList(0, catelist.size()>=10?10:catelist.size());
		
		//数据存放在hash中，便于jsp中提取
		HashMap<String, BigInteger> datahash = new HashMap<String, BigInteger>();
		List list = resultService.categoryMediaCountByNetworkTypeRelationDate(mynetwork.getId(), C.TYPE_TRACK, relation, days);
		
		//日期列表
		Vector datelist = new Vector();
		
		for(int i=0; i<list.size(); i++){
			Object[] object = (Object[])list.get(i);
			String date = object[0].toString();
			Integer categoryid = (Integer)object[1];
			BigInteger count      = (BigInteger)object[2];
			if(!datelist.contains(date)){
				datelist.add(date);
			}
			datahash.put(date+categoryid, count);
		}
		
		//分类的hash，用于从ID提取名称
		HashMap<Integer, Category> catehash = categoryService.hash();

		
		ctx.put("catelist", catelist);
		ctx.put("datelist", datelist);
		ctx.put("catehash", catehash);
		ctx.put("datahash", datahash);
		return SUCCESS;
	}

	//报表输出
	public String result(){
		List<XwColumn> list = columnService.listByNetworkTrackRelation(mynetwork.getId(), 0, C.RELATION_ALL);		
		ctx.put("list", list);		
		return SUCCESS;
	}	
	
	
	//栏目挖掘数据的分析
	public String columnchart(){
		List list =	resultService.columnreport(mynetwork.getId(), C.TYPE_TRACK, C.RELATION_ALL, days);

		ctx.put("list", list);
		return SUCCESS;		
	}

	//媒体关注度分析
	public String sitechart(){
		List<XwColumn> columnlist = columnService.listByNetworkTrackRelation(mynetwork.getId(), C.TYPE_TRACK, C.RELATION_ALL);
		List<List> datalist = new ArrayList<List>();
		for(int i=0; i<columnlist.size(); i++) {
			XwColumn col = columnlist.get(i);
			List list = resultService.sitereport(mynetwork.getId(), col.getId(), days, count);
			datalist.add(list);
		}
		ctx.put("columnlist", columnlist);
		ctx.put("datalist", datalist);
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
		
		List<XwColumn> columnlist = columnService.listByNetwork(mynetwork.getId(), 0);
		
		ctx.put("timelist", timelist);
		ctx.put("hashmap", hashmap);
		ctx.put("columnlist", columnlist);
		return SUCCESS;
	}

	//搜索内容
	public String filter(){
		if(result!=null&&result.getTitle()!=null&&result.getTitle().length()>0){
			result.setTitle(C.getURLChinese(result.getTitle()));
		}
		if(result!=null&&result.getSitename()!=null&&result.getSitename().length()>0){
			result.setSitename(C.getURLChinese(result.getSitename()));
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
		HashMap<Long, Integer> opinionhash = opinionService.findByNetworkModulePtime(mynetwork.getId(), C.MODULE_NEWS, datefrom, dateto);

		
		//int total = resultService.countByFilter(mynetwork.getId(), result, from, to);
		ctx.put("list", list);
		ctx.put("opinionhash", opinionhash);
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
		response.setHeader("Content-Disposition", "attachment; filename=News_"+C.getDate(0)+"_search_result.xls");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type","application/vnd.ms-excel");		
		return SUCCESS;
	}	
	
	//总揽的按月统计数据
	public String monthly(){
		List list = statService.userMonthly(mynetwork.getId(), C.MODULE_NEWS, 0, 365);

		ctx.put("list", list);
		return SUCCESS;
	}
	
	//删除新闻文章
	public String delete(){
		boolean resp = resultService.delete(mynetwork.getId(), result.getId());
		if(resp){
			ctx.put("message", "success");
		}else{
			ctx.put("message", "failure");
		}
		return MESSAGE;
	}	
	
	public XwColumn getColumn() {
		return column;
	}

	public void setColumn(XwColumn column) {
		this.column = column;
	}

	public XwResult getResult() {
		return result;
	}

	public void setResult(XwResult result) {
		this.result = result;
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

	public Integer getRelation() {
		return relation;
	}

	public void setRelation(Integer relation) {
		this.relation = relation;
	}

	public Integer getOrigin() {
		return origin;
	}

	public void setOrigin(Integer origin) {
		this.origin = origin;
	}
}
