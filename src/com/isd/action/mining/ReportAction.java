package com.isd.action.mining;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.isd.action.GenericAction;
import com.isd.entity.bbs.LtColumn;
import com.isd.entity.event.SjColumn;
import com.isd.entity.news.XwColumn;
import com.isd.entity.recommend.Favorite;
import com.isd.entity.recommend.Recommend;
import com.isd.entity.report.HtmlToPdf;
import com.isd.entity.wechat.WxColumn;
import com.isd.service.bbs.LtColumnService;
import com.isd.service.bbs.LtResultService;
import com.isd.service.event.SjColumnService;
import com.isd.service.mining.DaystatService;
import com.isd.service.news.XwColumnService;
import com.isd.service.news.XwResultService;
import com.isd.service.recommend.FavoriteService;
import com.isd.service.recommend.RecommendService;
import com.isd.service.wechat.WxColumnService;
import com.isd.service.wechat.WxResultService;
import com.isd.service.weibo.WbColumnService;
import com.isd.service.weibo.WbDictService;
import com.isd.service.weibo.WbResultService;
import com.isd.util.C;


public class ReportAction extends GenericAction{

	private static final long serialVersionUID = -1L;
	@Autowired
	private DaystatService daystatService;
	@Autowired
	private WbResultService wbResultService;
	@Autowired
	private WbColumnService wbColumnService;	
	@Autowired
	private WbDictService wbDictService;	
	@Autowired
	private WxResultService wxResultService;
	@Autowired
	private WxColumnService wxColumnService;
	@Autowired
	private XwResultService	xwResultService;
	@Autowired
	private XwColumnService	xwColumnService;
	@Autowired
	private LtResultService	ltResultService;
	@Autowired
	private LtColumnService	ltColumnService;	
	@Autowired
	private SjColumnService sjColumnService;
	@Autowired
	private RecommendService recommendService; 
	@Autowired
	private FavoriteService favoriteService;
	
	private String url;
	private int days = 7;
	private int type = 0;
	private int relation = 0;
	private int columnid = -1;
	private String[] module;
	private String from=C.getDateStart(new Date());
	private String to=C.getDateEnd(new Date());
	private int dd = 0;
	
	public String index(){
		return SUCCESS;
	}
	
	public String detail(){
		String range = "当天";
		String scope = "所有";
		if(days==7){
			range ="最近一周";
		}else if(days==30){
			range = "最近一个月";
		}else if(days==90){
			range = "最近三个月";
		}else if(days==183){
			range = "最近半年";			
		}else if(days==367){
			range = "最近一年";
		}
		
		if(relation==1) {
			scope = "自有";
		}else if(relation == 2) {
			scope = "竞品";
		}
		
		String today = C.dateToString(new Date());
		ctx.put("today", today);
		ctx.put("range", range);
		ctx.put("scope", scope);
		return SUCCESS;
	}
	
	//导出的选择页
	public String exp(){
		List<WxColumn>wxlist = wxColumnService.listByNetworkTypeRelation(mynetwork.getId(), C.TYPE_TRACK, C.RELATION_ALL);
		List<XwColumn>xwlist = xwColumnService.listByNetworkTrackRelation(mynetwork.getId(), C.TYPE_TRACK, C.STATUS_ACTIVE);

		ctx.put("wxlist", wxlist);
		ctx.put("xwlist", xwlist);
		return SUCCESS;
	}
	
	public String excel(){
		HashMap xwColumnHash = xwColumnService.hashByNetwork(mynetwork.getId(), type);
		List xwContentList = xwResultService.contentListByUserTypeTimeRange(mynetwork.getId(), type, dd, from, to);
		
		HashMap<Integer, WxColumn> wxColumnhash = wxColumnService.hashByNetwork(mynetwork.getId(), type);
		List wxContentList = wxResultService.contentListByUserTypeTimeRange(mynetwork.getId(), type, dd, from, to);	
		
		HashMap ltColumnHash = ltColumnService.hashByNetwork(mynetwork.getId(), type);
		List ltContentList = ltResultService.contentListByUserTypeTimeRange(mynetwork.getId(), type, dd, from, to);
		
		HashMap wbColumnHash = wbColumnService.hashByNetwork(mynetwork.getId(), type);
		List wbContentList = wbResultService.contentListByUserTypeTimeRange(mynetwork.getId(), type, from, to);

		ctx.put("xwcolumnhash", xwColumnHash);
		ctx.put("xwcontentlist", xwContentList);
		
		ctx.put("wxcolumnhash", wxColumnhash);
		ctx.put("wxcontentlist", wxContentList);
		
		ctx.put("ltcolumnhash", ltColumnHash);
		ctx.put("ltcontentlist", ltContentList);	
		
		ctx.put("wbcolumnhash", wbColumnHash);
		ctx.put("wbcontentlist", wbContentList);
		
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename="+C.getDate(0)+"_"+type+".xls");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type","application/vnd.ms-excel");
		return SUCCESS;
	}
	
	public String topexcel(){
		List list = null;
		HashMap columnHash=null;
	
		if(module[0].equals(C.MODULE_NEWS)){
			list  = xwResultService.topContentListByUserTypeRelationDate(mynetwork.getId(), columnid, type, relation, from, to, 0, pagesize);
			columnHash = xwColumnService.hashByNetwork(mynetwork.getId(), type);
		}else if(module[0].equals(C.MODULE_WECHAT)){
			list = wxResultService.topContentListByUserTypeRelationDate(mynetwork.getId(), columnid, type, relation, from, to, 0, pagesize);
			columnHash = wxColumnService.hashByNetwork(mynetwork.getId(), type);
		}else if(module[0].equals(C.MODULE_WEIBO)){
			list = wbResultService.topContentListByUserTypeRelationDate(mynetwork.getId(), type, relation, from, to, 0, pagesize);
			columnHash = wbColumnService.hashByNetwork(mynetwork.getId(), type);
		}else if(module[0].equals(C.MODULE_BBS)){
			list = ltResultService.topContentListByUserTypeRelationDate(mynetwork.getId(), type, relation, from, to, 0, pagesize);
			columnHash = ltColumnService.hashByNetwork(mynetwork.getId(), type);
		}else if(module[0].equals("wxr")){
			list = wxResultService.topReadListByUserTypeRelationDate(mynetwork.getId(), type, relation, from, to, 0, pagesize);
			columnHash = wxColumnService.hashByNetwork(mynetwork.getId(), type);
		}
		
		ctx.put("list", list);
		ctx.put("hash", columnHash);
		ctx.put("mod", module[0]);
		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=TopList_"+module[0]+"_"+type+"_"+relation+".xls");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type","application/vnd.ms-excel");
		return SUCCESS;		
	}
	
	//推荐内容导出
	public String expreco(){
		List<Recommend> list = recommendService.listByDateRange(mynetwork.getId(),from, to);
		String idlist = "";
		for(int i=0; i<list.size(); i++){
			idlist += list.get(i).getIdlist()+",";
		}
		if(idlist.endsWith(",")){
			idlist = idlist.substring(0, idlist.length()-1);
		}
		
		
		if(idlist.length()>0){
			List<Favorite> favorlist = favoriteService.findByIds(idlist);
			ctx.put("list", favorlist);
		}

		response.setContentType("application/vnd.ms-excel");
		response.setHeader("Content-Disposition", "attachment; filename=Recommend_list_"+from+"_"+to+".xls");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-Type","application/vnd.ms-excel");
		return SUCCESS;	
	}
	
	public String export(){
		url = request.getHeader("referer");
		String token = C.genToken(myself.getId());
		url += "&token="+token+"&ord="+this.random+"&";
		return SUCCESS;
	}
	
	public String generate(){
		String link = new HtmlToPdf().convert(url, mynetwork.getId()+".pdf");
		ctx.put("text", link);
		return TEXT;
	}
	
	public String overview(){
		int dates = days;
		if(days<7){
			dates = 7;
		}
		
		List wblist = wbResultService.mediaContentCountByUserTypeRelation(mynetwork.getId(), 0, relation, dates);
		List wxlist = wxResultService.mediaContentCountByUserTypeRelation(mynetwork.getId(), 0, relation, dates);
		List xwlist = xwResultService.mediaContentCountByUserTypeRelation(mynetwork.getId(), 0, relation, dates);
		List ltlist = ltResultService.mediaContentCountByUserTypeRelation(mynetwork.getId(), 0, relation, dates);
			
		ctx.put("wblist", wblist);
		ctx.put("wxlist", wxlist);
		ctx.put("xwlist", xwlist);
		ctx.put("ltlist", ltlist);

		
		//事件按照模块提取数据
		/*List sjwblist = daystatService.userTotal(mynetwork.getId(), C.MODULE_WEIBO, 1, days);
		List sjwxlist = daystatService.userTotal(mynetwork.getId(), C.MODULE_WECHAT, 1, days);
		List sjxwlist = daystatService.userTotal(mynetwork.getId(), C.MODULE_NEWS, 1, days);
		List sjltlist = daystatService.userTotal(mynetwork.getId(), C.MODULE_BBS, 1, days);			
		
		//负面按照模块提取数据
		Integer fmwb = wbResultService.contentCountByUserTypeRelationDate(mynetwork.getId(), C.TYPE_NEGATIVE, relation, days);
		Integer fmwx = wxResultService.contentCountByUserTypeRelationDate(mynetwork.getId(), C.TYPE_NEGATIVE, relation, days);
		Integer fmxw = xwResultService.contentCountByUserTypeRelationDate(mynetwork.getId(), C.TYPE_NEGATIVE, relation, days);
		Integer fmlt = ltResultService.contentCountByUserTypeRelationDate(mynetwork.getId(), C.TYPE_NEGATIVE, relation, days);
		
		ctx.put("sjwblist", sjwblist);
		ctx.put("sjwxlist", sjwxlist);
		ctx.put("sjxwlist", sjxwlist);
		ctx.put("sjltlist", sjltlist);
		
		ctx.put("fmwb", fmwb.toString());
		ctx.put("fmwx", fmwx.toString());
		ctx.put("fmxw", fmxw.toString());
		ctx.put("fmlt", fmlt.toString());	
		*/
		return SUCCESS;
	}

	public String weibo(){
		List weibosummary 	= wbResultService.mediaSumByNetworkTypeRelationDate(mynetwork.getId(), C.TYPE_TRACK, relation, days);
		List contentsummary = wbResultService.contentSumByNetworkTypeRelationDate(mynetwork.getId(), C.TYPE_TRACK, relation, days);
		List weibolist 		= wbResultService.mediaListByNetworkTypeRelationDate(mynetwork.getId(), C.TYPE_TRACK, relation, days, 0, 10);
		List contentlist 	= wbResultService.contentListByNetworkTypeRelationDate(mynetwork.getId(), C.TYPE_TRACK, relation, days, 0, 3);

		ctx.put("weibosummary", weibosummary);
		ctx.put("contentsummary", contentsummary);
		ctx.put("weibolist", weibolist);
		ctx.put("contentlist", contentlist);
		return SUCCESS;
	}
	
	public String wechat(){
		List<WxColumn> columnlist = wxColumnService.listByNetworkTypeRelation(mynetwork.getId(), C.TYPE_TRACK, relation);
		
		//微信数量
		int wxtotal = wxResultService.mediaCountByNetworkTypeRelationDate(mynetwork.getId(), C.TYPE_TRACK, relation, days);
		//根据用户、内容类型、时间，返回微信列表
		List wxlist = wxResultService.mediaListByNetworkTypeRelationDate(mynetwork.getId(), C.TYPE_TRACK, relation, days, 0, 10);

		//文章总数
		int contenttotal = wxResultService.contentCountByNetworkTypeRelationDate(mynetwork.getId(), C.TYPE_TRACK, relation, days);
		//按照用户、内容类型、时间范围、排序提取文章内容
		List contentlist = wxResultService.contentListByNetworkTypeRelationDate(mynetwork.getId(), C.TYPE_TRACK, relation, days, 0, 10);
		
		List columnreport = wxResultService.columnreport(mynetwork.getId(), C.TYPE_TRACK, relation, days);
		List stockreport  = wxResultService.stockreport(mynetwork.getId(), C.TYPE_TRACK, relation, days);
		
		List<List> sitereport = new ArrayList<List>();
		for(int i=0; i<columnlist.size(); i++) {
			WxColumn col = columnlist.get(i);
			List list = wxResultService.sitereport(mynetwork.getId(), col.getId(), days, 30);
			sitereport.add(list);
		}
		
		List<String> timelist = new ArrayList<String>();
		HashMap<String, String> hashmap = new HashMap<String, String>();
		for(int i=0; i<stockreport.size(); i++){
			Object[] item = (Object[])stockreport.get(i);
			String time = (String)item[0];
			Integer columnid = (Integer)item[1];
			String total = item[2].toString();
			hashmap.put(time+"_"+columnid, total);
			if(!timelist.contains(time)){
				timelist.add(time);
			}
		}
		
		ctx.put("wxcolumnlist", columnlist);
		
		ctx.put("wxtotal", String.valueOf(wxtotal));
		ctx.put("wxlist", wxlist);

		ctx.put("contenttotal", String.valueOf(contenttotal));
		ctx.put("contentlist", contentlist);
		
		ctx.put("columnreport", columnreport);
		ctx.put("sitereport", sitereport);
		ctx.put("timelist", timelist);
		ctx.put("hashmap", hashmap);

		return SUCCESS;
	}
	
	public String news(){
		List<XwColumn> xwcolumnlist = xwColumnService.listByNetworkTrackRelation(mynetwork.getId(), C.TYPE_TRACK, relation);
		
		//媒体数量
		int mediacount 		= xwResultService.mediaCountByNetworkTypeRelationDate(mynetwork.getId(), C.TYPE_TRACK, relation, days);
		//内容数量
		int contentcount 	= xwResultService.contentCountByNetworkTypeRelationDate(mynetwork.getId(), C.TYPE_TRACK, relation, days);
		
		
		List columnreport 	= xwResultService.columnreport(mynetwork.getId(), C.TYPE_TRACK, relation, days);
		List stockreport 	= xwResultService.stockreport(mynetwork.getId(), C.TYPE_TRACK, relation, days);
		
		List<List> sitereport	= new ArrayList<List>();
		for(int i=0; i<xwcolumnlist.size(); i++) {
			XwColumn col = xwcolumnlist.get(i);
			List list = xwResultService.sitereport(mynetwork.getId(), col.getId(), days, 30);
			sitereport.add(list);
		}
		
		
		List<String> timelist = new ArrayList<String>();
		HashMap<String, String> hashmap = new HashMap<String, String>();
		for(int i=0; i<stockreport.size(); i++){
			Object[] item = (Object[])stockreport.get(i);
			String time = (String)item[0];
			Integer columnid = (Integer)item[1];
			String total = item[2].toString();
			hashmap.put(time+"_"+columnid, total);
			if(!timelist.contains(time)){
				timelist.add(time);
			}
		}		
		

		
		//满足条件的内容列表
		List contentlist = xwResultService.contentListByNetworkTypeRelationDate(mynetwork.getId(), C.TYPE_TRACK, relation, days, 0, 10);
		//满足条件的站点列表
		List medialist	 = xwResultService.mediaListByNetworkTypeRelationDate(mynetwork.getId(), C.TYPE_TRACK, relation, days, 0, 10);

		ctx.put("mediacount", String.valueOf(mediacount));
		ctx.put("contentcount", String.valueOf(contentcount));
		
		ctx.put("sitereport", sitereport);
		ctx.put("columnreport", columnreport);
		ctx.put("timelist", timelist);
		ctx.put("hashmap", hashmap);
		
		ctx.put("xwcolumnlist", xwcolumnlist);
		ctx.put("contentlist", contentlist);
		ctx.put("medialist", medialist);
		
		return SUCCESS;
	}
	
	public String bbs(){
		List<LtColumn> ltcolumnlist = ltColumnService.listByNetworkTypeRelation(mynetwork.getId(), C.TYPE_TRACK, relation);
		
		//论坛数量
		int mediacount 		= ltResultService.mediaCountByNetworkTypeRelationDate(mynetwork.getId(), C.TYPE_TRACK, relation, days);
		//内容数量
		int contentcount 	= ltResultService.contentCountByNetworkTypeRelationDate(mynetwork.getId(), C.TYPE_TRACK, relation, days);
		
		List columnreport =	ltResultService.columnreport(mynetwork.getId(), C.TYPE_TRACK, relation, days);
		
		List sitereport = new ArrayList<List>();
		for(int i=0; i<ltcolumnlist.size(); i++) {
			LtColumn col = ltcolumnlist.get(i);
			List list = ltResultService.sitereport(mynetwork.getId(), col.getId(), days, 30);
			sitereport.add(list);
		}
		
		List stockreport =	ltResultService.stockreport(mynetwork.getId(), C.TYPE_TRACK, relation, days);

		List<String> timelist = new ArrayList<String>();
		HashMap<String, String> hashmap = new HashMap<String, String>();
		for(int i=0; i<stockreport.size(); i++){
			Object[] item = (Object[])stockreport.get(i);
			String time = (String)item[0];
			Integer columnid = (Integer)item[1];
			String total = item[2].toString();
			hashmap.put(time+"_"+columnid, total);
			if(!timelist.contains(time)){
				timelist.add(time);
			}
		}		
		
		
		
		List contentlist = ltResultService.contentListByNetworkTypeRelationDate(mynetwork.getId(), C.TYPE_TRACK, relation, days, 0, 10);
		List medialist	 = ltResultService.mediaListByNetworkTypeRelationDate(mynetwork.getId(), C.TYPE_TRACK, relation, days, 0, 10);
		
		ctx.put("mediacount", String.valueOf(mediacount));
		ctx.put("contentcount", String.valueOf(contentcount));
		
		ctx.put("columnreport", columnreport);
		ctx.put("sitereport", sitereport);
		ctx.put("timelist", timelist);
		ctx.put("hashmap", hashmap);
		ctx.put("ltcolumnlist", ltcolumnlist);

		ctx.put("contentlist", contentlist);
		ctx.put("medialist", medialist);
		return SUCCESS;
	}
	
	public String event(){
		List<SjColumn> columnlist = sjColumnService.findByNetwork(mynetwork.getId(), pageindex*pagesize, pagesize);
		
		//获取事件所对应的栏目的hash
		HashMap<Integer, Integer> wxhash = wxResultService.contentCountByUserTypeRelationDateHash(mynetwork.getId(), C.TYPE_EVENT, relation, days);
		HashMap<Integer, Integer> wbhash = wbResultService.contentCountByUserTypeDateHash(mynetwork.getId(), C.TYPE_EVENT, days);
		HashMap<Integer, Integer> xwhash = xwResultService.contentCountByNetworkTypeRelationDateHash(mynetwork.getId(), C.TYPE_EVENT, relation, days);
		HashMap<Integer, Integer> lthash = ltResultService.contentCountByNetworkTypeRelationDateHash(mynetwork.getId(), C.TYPE_EVENT, relation, days);
		
		ctx.put("wxhash", wxhash);
		ctx.put("xwhash", xwhash);
		ctx.put("lthash", lthash);
		ctx.put("wbhash", wbhash);
		ctx.put("columnlist", columnlist);	
		
		return SUCCESS;
	}
	
	public String negative(){
		List wblist = wbResultService.contentListByNetworkTypeRelationDate(mynetwork.getId(), C.TYPE_NEGATIVE, C.RELATION_ALL, days, 0, 10);
		List wxlist = wxResultService.contentListByNetworkTypeDateOrder(mynetwork.getId(), C.TYPE_NEGATIVE, "ptime", days, 0, 10);
		List xwlist = xwResultService.contentListByNetworkTypeRelationDate(mynetwork.getId(), C.TYPE_NEGATIVE, C.RELATION_ALL, days, 0, 10);
		List ltlist = ltResultService.contentListByNetworkTypeRelationDate(mynetwork.getId(), C.TYPE_NEGATIVE, C.RELATION_ALL, days, 0, 10);
		
		int wbmediacount 	= wbResultService.mediaCountByUserTypeRelationDate(mynetwork.getId(), C.TYPE_NEGATIVE, C.RELATION_ALL, days);
		int wbcontentcount	= wbResultService.contentCountByUserTypeRelationDate(mynetwork.getId(), C.TYPE_NEGATIVE, C.RELATION_ALL, days);
		
		int wxmediacount = wxResultService.mediaCountByNetworkTypeRelationDate(mynetwork.getId(), C.TYPE_NEGATIVE, C.RELATION_ALL, days);
		int wxcontentcount = wxResultService.contentCountByNetworkTypeRelationDate(mynetwork.getId(), C.TYPE_NEGATIVE, C.RELATION_ALL, days);

		int xwmediacount 	= xwResultService.mediaCountByNetworkTypeRelationDate(mynetwork.getId(), C.TYPE_NEGATIVE, C.RELATION_ALL, days);
		int xwcontentcount 	= xwResultService.contentCountByNetworkTypeRelationDate(mynetwork.getId(), C.TYPE_NEGATIVE, C.RELATION_ALL, days);

		int ltmediacount 	= ltResultService.mediaCountByNetworkTypeRelationDate(mynetwork.getId(), C.TYPE_NEGATIVE, C.RELATION_ALL, days);
		int ltcontentcount 	= ltResultService.contentCountByNetworkTypeRelationDate(mynetwork.getId(), C.TYPE_NEGATIVE, 0, days);

		
		ctx.put("wbmediacount", String.valueOf(wbmediacount));
		ctx.put("wbcontentcount", String.valueOf(wbcontentcount));
		
		ctx.put("wxmediacount", String.valueOf(wxmediacount));
		ctx.put("wxcontentcount", String.valueOf(wxcontentcount));
		
		ctx.put("xwmediacount", String.valueOf(xwmediacount));
		ctx.put("xwcontentcount", String.valueOf(xwcontentcount));
		
		ctx.put("ltmediacount", String.valueOf(ltmediacount));
		ctx.put("ltcontentcount", String.valueOf(ltcontentcount));
		
		ctx.put("wblist", wblist);
		ctx.put("wxlist", wxlist);
		ctx.put("xwlist", xwlist);
		ctx.put("ltlist", ltlist);
		return SUCCESS;
	}	
	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getRelation() {
		return relation;
	}

	public void setRelation(int relation) {
		this.relation = relation;
	}

	public String[] getModule() {
		return module;
	}

	public void setModule(String[] module) {
		this.module = module;
	}

	public int getColumnid() {
		return columnid;
	}

	public void setColumnid(int columnid) {
		this.columnid = columnid;
	}

	public int getDd() {
		return dd;
	}

	public void setDd(int dd) {
		this.dd = dd;
	}
}
