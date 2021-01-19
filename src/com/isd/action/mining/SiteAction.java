package com.isd.action.mining;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;

import com.isd.action.GenericAction;
import com.isd.entity.common.City;
import com.isd.entity.common.Province;
import com.isd.entity.event.SjColumn;
import com.isd.entity.negative.FmColumn;
import com.isd.entity.news.XwColumn;
import com.isd.entity.news.XwResult;
import com.isd.entity.wechat.WxColumn;
import com.isd.service.bbs.LtColumnService;
import com.isd.service.bbs.LtResultService;
import com.isd.service.common.CityService;
import com.isd.service.event.SjColumnService;
import com.isd.service.mining.DaystatService;
import com.isd.service.mining.GeostatService;
import com.isd.service.mining.ProblemService;
import com.isd.service.mining.SiteService;
import com.isd.service.negative.FmColumnService;
import com.isd.service.news.XwColumnService;
import com.isd.service.news.XwResultService;
import com.isd.service.wechat.WxColumnService;
import com.isd.service.wechat.WxResultService;
import com.isd.service.weibo.WbColumnService;
import com.isd.service.weibo.WbResultService;
import com.isd.util.C;
import com.isd.util.CompressScript;

import cn.shopex.prism.sdk.PrismClient;

public class SiteAction extends GenericAction{

	private static final long serialVersionUID = -5455149988773148284L;
	
	@Autowired
	private ProblemService problemService;
	@Autowired
	private WbColumnService wbColumnService;
	@Autowired
	private WxColumnService wxColumnService;
	@Autowired
	private XwColumnService xwColumnService;
	@Autowired
	private LtColumnService ltColumnService;
	@Autowired
	private WbResultService wbResultService;
	@Autowired
	private WxResultService wxResultService;
	@Autowired
	private XwResultService xwResultService;
	@Autowired
	private LtResultService ltResultService;
	@Autowired
	private SjColumnService sjColumnService;
	@Autowired
	private FmColumnService fmColumnService;	
	@Autowired
	private DaystatService daystatService;
	@Autowired
	private GeostatService geostatService;
	@Autowired
	private SiteService siteService;
	
	
	private Integer columnid;
	//用于统计数据的时间范围。比如30代表最近30天
	private Integer days = 7;
	private Integer relation = 0;
	private String 	module="xw";
	private Integer type = C.TYPE_TRACK;
	private Integer pageid;
	@Autowired
	private CityService cityService;
	private Province province;
	
	//根据省份的ID，获取城市列表
	public String citylist(){
		List<City> list = cityService.citylist(province.getId());
		ctx.put("list", list);
		return SUCCESS;
	}
	
	//前台首页
	public String index(){
		if(C.getCookie(C.COOKIE_TOKEN)==null){
			try{
				String username = C.getCookie(C.COOKIE_EMAIL);
				ctx.put("username", username);
			}catch(Exception e){
				e.printStackTrace();
			}
			return SUCCESS;
		}else{
			ctx.put("redirectaction", "site/overview");
			return REDIRECTACTION;
		}
	}

	public String test(){
		String url = "https://openapi.shopex.cn";
		String key = "c3xebate";
		String secret = "nqgzzqwokvkqbjvmilut";
		PrismClient prismClient = new PrismClient(url,key,secret);

		String methodPath = "/api/auth/auth.checktoken";
		Map<String,String> appParams = new HashMap<String, String>();
		appParams.put("shopexid","c3xebate");
		appParams.put("token","cxprjyvyokq7y263ubof");
		try {
			//发送GET请求
			String apiResult = prismClient.doPost(methodPath, appParams);
			System.out.println(apiResult);
		}catch(Exception e){
			System.out.println(e);
		}

		return MESSAGE;
	}

	
	public String datacheck(){
		String result = siteService.datacheck();
		ctx.put("message", result);
		return MESSAGE;
	}
	
	//后台首页
	public String manage_index(){
		return SUCCESS;
	}
	

	
	public String overview(){
		if(mynetwork==null){
			ctx.put("message", "您访问的账户已不存在，请联系客服人员。");
			return MESSAGE;
		}
		//当日数据
		int wxtoday = wxResultService.contentCountByNetworkTypeRelationDate(mynetwork.getId(), C.TYPE_TRACK, 0, C.TIME_TODAY);
		int xwtoday = xwResultService.contentCountByNetworkTypeRelationDate(mynetwork.getId(), C.TYPE_TRACK, 0, C.TIME_TODAY);
		int wbtoday = wbResultService.contentCountByUserTypeRelationDate(mynetwork.getId(), C.TYPE_TRACK, 0, C.TIME_TODAY);
		int lttoday = ltResultService.contentCountByNetworkTypeRelationDate(mynetwork.getId(), C.TYPE_TRACK, 0, C.TIME_TODAY);
		
		List wbsummary = wbResultService.columnreport(mynetwork.getId(), C.TYPE_TRACK, 0);
		List wxsummary = wxResultService.columnreport(mynetwork.getId(), C.TYPE_TRACK, C.RELATION_ALL, 0);
		List xwsummary = xwResultService.columnreport(mynetwork.getId(), C.TYPE_TRACK, C.RELATION_ALL, 0);
		List ltsummary = ltResultService.columnreport(mynetwork.getId(), C.TYPE_TRACK, C.RELATION_ALL, 0);
		
		
		int wbtodayrate = (int)(wbtoday * 100. / (wxtoday+xwtoday+wbtoday+lttoday)+0.5);
		int wxtodayrate = (int)(wxtoday * 100. / (wxtoday+xwtoday+wbtoday+lttoday)+0.5);
		int xwtodayrate = (int)(xwtoday * 100. / (wxtoday+xwtoday+wbtoday+lttoday)+0.5);	
		int lttodayrate = (int)(lttoday * 100. / (wxtoday+xwtoday+wbtoday+lttoday)+0.5);
		
		ctx.put("wbsummary", wbsummary);
		ctx.put("wxsummary", wxsummary);
		ctx.put("xwsummary", xwsummary);
		ctx.put("ltsummary", ltsummary);
		
		ctx.put("wxtoday", String.valueOf(wxtoday));
		ctx.put("xwtoday", String.valueOf(xwtoday));
		ctx.put("wbtoday", String.valueOf(wbtoday));
		ctx.put("lttoday", String.valueOf(lttoday));
		
		ctx.put("wbtodayrate", String.valueOf(wbtodayrate));
		ctx.put("wxtodayrate", String.valueOf(wxtodayrate));
		ctx.put("xwtodayrate", String.valueOf(xwtodayrate));
		ctx.put("lttodayrate", String.valueOf(lttodayrate));
		
		ctx.put("date", C.getDate(0));
		ctx.put("now", new Date());
		return SUCCESS;
	}
	
	//弹出窗口的焦点内容
	public String popup(){
		return SUCCESS;
	}

	public String columnlist(){
		if(module.equals("wx")) {
			List<WxColumn> list = wxColumnService.listByNetworkType(mynetwork.getId(), type);
			ctx.put("list", list);
		}else if(module.equals("xw")){
			List<XwColumn> list = xwColumnService.listByNetwork(mynetwork.getId(), type);
			ctx.put("list", list);
		}else if(module.equals("wb")){
			//List<WbColumn> list = wbColumnService.listByUser(mynetwork.getId(), type);
			//ctx.put("list", list);
		}else if(module.equals("lt")){
			//List<LtColumn> list = ltColumnService.listByUser(mynetwork.getId(), type);
			//ctx.put("list", list);
		}
		return SUCCESS;
	}
	
	//弹出窗口的焦点内容
	public String focus(){
		List list = null;
		String from = C.getDate(days);
		String to   = C.dateToString(new Date());
		
		if(module.equals(C.MODULE_NEWS)){
			list  = xwResultService.topContentListByUserTypeRelationDate(mynetwork.getId(),columnid, type, relation, from, to, 0, 50);
		}else if(module.equals(C.MODULE_WECHAT)){
			list = wxResultService.topContentListByUserTypeRelationDate(mynetwork.getId(), columnid, type, relation, from, to, 0, 50);
		}else if(module.equals(C.MODULE_WEIBO)){
			list = wbResultService.topContentListByUserTypeRelationDate(mynetwork.getId(), type, relation, from, to, 0, 50);
		}else if(module.equals(C.MODULE_BBS)){
			list = ltResultService.topContentListByUserTypeRelationDate(mynetwork.getId(), type, relation, from, to, 0, 50);
		}
		ctx.put("list", list);
		return SUCCESS;	
	}
	
	public String chartview(){
		return SUCCESS;
	}
	
	//地图数据
	public String map(){
		List list = geostatService.findByUser(mynetwork.getId(), 0);
		ctx.put("list", list);
		return SUCCESS;
	}
	
	//可视数据中的当日统计
	public String summary(){
		int wxtoday = wxResultService.contentCountByNetworkTypeRelationDate(mynetwork.getId(), C.TYPE_TRACK, 0, C.TIME_TODAY);
		int openidtoday = wxResultService.mediaCountByNetworkTypeRelationDate(mynetwork.getId(), C.TYPE_TRACK, 0, C.TIME_TODAY);
		
		int xwtoday = xwResultService.contentCountByNetworkTypeRelationDate(mynetwork.getId(), 0, 0, 0);
		int sitetoday  = xwResultService.mediaCountByNetworkTypeRelationDate(mynetwork.getId(), C.TYPE_TRACK, 0, C.TIME_TODAY);
		int wbtoday = wbResultService.contentCountByUserTypeRelationDate(mynetwork.getId(),0, 0, 0);
		int lttoday = ltResultService.contentCountByNetworkTypeRelationDate(mynetwork.getId(),0, 0,0);

		ctx.put("wxtoday", String.valueOf(wxtoday));
		ctx.put("openidtoday", String.valueOf(openidtoday));
		ctx.put("xwtoday", String.valueOf(xwtoday));
		ctx.put("sitetoday", String.valueOf(sitetoday));
		ctx.put("wbtoday", String.valueOf(wbtoday));
		ctx.put("lttoday", String.valueOf(lttoday));
		
		ctx.put("today", C.dateToString(new Date(), "yyyy-MM-dd HH:mm (EEEE)"));
		
		return SUCCESS;
	}

	//可视数据中的总的统计
	public String summaryall(){
		int wbtotal = wbResultService.contentCountByUserTypeRelationDate(mynetwork.getId(),C.TYPE_TRACK, 0, C.TIME_ALL);
		int wxtotal = wxResultService.contentCountByNetworkTypeRelationDate(mynetwork.getId(),C.TYPE_TRACK, 0, C.TIME_ALL);
		int xwtotal = xwResultService.contentCountByNetworkTypeRelationDate(mynetwork.getId(),C.TYPE_TRACK, 0, C.TIME_ALL);
		int lttotal = ltResultService.contentCountByNetworkTypeRelationDate(mynetwork.getId(),C.TYPE_TRACK, 0, C.TIME_ALL);
		
		int openidtotal = wxResultService.mediaCountByNetworkTypeRelationDate(mynetwork.getId(), C.TYPE_TRACK, 0, C.TIME_ALL);
		int sitetotal   = xwResultService.mediaCountByNetworkTypeRelationDate(mynetwork.getId(), C.TYPE_TRACK, 0, C.TIME_ALL);

		ctx.put("wbtotal", String.valueOf(wbtotal));
		ctx.put("wxtotal", String.valueOf(wxtotal));
		ctx.put("openidtotal", String.valueOf(openidtotal));
		ctx.put("xwtotal", String.valueOf(xwtotal));
		ctx.put("sitetotal", String.valueOf(sitetotal));
		ctx.put("lttotal", String.valueOf(lttotal));
		
		ctx.put("today", C.dateToString(new Date(), "yyyy-MM-dd HH:mm (EEEE)"));
		
		return SUCCESS;
	}	
	
	//走马灯
	public String ticker(){
		List<XwResult> list = xwResultService.contentListByUserTypeRelationDate(mynetwork.getId(), C.TYPE_TRACK, 0, 0,  0, 5);
		ctx.put("list", list);
		return SUCCESS;
	}
	
	
	//微博报表
	public String wbchart(){
		List list = daystatService.userMonthly(mynetwork.getId(), C.MODULE_WEIBO, 0, 365);
		ctx.put("list", list);
		return SUCCESS;
	}
	
	//微信报表
	public String wxchart(){
		List list = daystatService.userMonthly(mynetwork.getId(), C.MODULE_WECHAT, 0, 365);
		ctx.put("list", list);
		return SUCCESS;
	}	
	
	//新闻报表
	public String xwchart(){
		//统计新闻挖掘的数量
		List list = daystatService.userMonthly(mynetwork.getId(), C.MODULE_NEWS, 0, 365);
		
		ctx.put("list", list);
		return SUCCESS;
	}
	
	//论坛报表
	public String ltchart(){
		List list = daystatService.userMonthly(mynetwork.getId(), C.MODULE_BBS, 0, 365);
		ctx.put("list", list);
		return SUCCESS;
	}	
	
	public String home(){
		return SUCCESS;
	}
	
	public String about(){
		return SUCCESS;
	}

	public String problem(){
		pagesize = 15;
		List list = problemService.list(pageindex*pagesize, pagesize);
		Integer total = problemService.count();
		ctx.put("list", list);
		ctx.put("total", total.toString());
		return SUCCESS;
	}
	
	public String joinus(){
		return SUCCESS;
	}
	
	//事件可视数据
	public String event(){
		return SUCCESS;
	}

	//事件可视地图
	public String eventmap(){
		List<SjColumn> sjlist = sjColumnService.findByNetwork(mynetwork.getId(), 0, 3);
		int idx = offset % sjlist.size();
		SjColumn column = sjlist.get(idx);
		
		List list = geostatService.findByColumn(column.getId(), 1);
		ctx.put("list", list);
		return SUCCESS;
	}
	
	//事件的统计
	public String eventsummary(){
		List<SjColumn> sjlist = sjColumnService.findByNetwork(mynetwork.getId(), 0, 3);
		int idx = offset % sjlist.size();
		SjColumn column = sjlist.get(idx);

		Integer wbcount = wbResultService.contentCountByUserTypeRelationDate(mynetwork.getId(), C.TYPE_EVENT, 0, C.TIME_ALL);
		Integer wxcount = wxResultService.contentCountByNetworkTypeRelationDate(mynetwork.getId(), C.TYPE_EVENT, 0, C.TIME_ALL);
		Integer ltcount = ltResultService.contentCountByNetworkTypeRelationDate(mynetwork.getId(), C.TYPE_EVENT, 0, C.TIME_ALL);
		Integer xwcount = xwResultService.contentCountByNetworkTypeRelationDate(mynetwork.getId(), C.TYPE_EVENT, 0, C.TIME_ALL);
		Integer total = wbcount + wxcount + xwcount + ltcount;
		
		ctx.put("wxcount", String.valueOf(wxcount));
		ctx.put("wbcount", String.valueOf(wbcount));
		ctx.put("xwcount", String.valueOf(xwcount));
		ctx.put("ltcount", String.valueOf(ltcount));
		ctx.put("total", String.valueOf(total));
		ctx.put("column", column);
		ctx.put("today", C.dateToString(new Date(), "yyyy-MM-dd HH:mm (EEEE)"));	
		return SUCCESS;
	}
	
	//根据栏目的饼图
	public String columnchart(){
		List list = null;
		if(module.equals(C.MODULE_WEIBO)){
			list = wbResultService.columnreport(mynetwork.getId(), C.TYPE_TRACK, days);
		}else if(module.equals(C.MODULE_WECHAT)){
			list = wxResultService.columnreport(mynetwork.getId(), C.TYPE_TRACK, C.RELATION_ALL, days);
		}else if(module.equals(C.MODULE_NEWS)){
			list = xwResultService.columnreport(mynetwork.getId(), C.TYPE_TRACK, C.RELATION_ALL, days);
		}else if(module.equals(C.MODULE_BBS)){
			list = ltResultService.columnreport(mynetwork.getId(), C.TYPE_TRACK, C.RELATION_ALL, days);
		}
		
		ctx.put("list", list);
		return SUCCESS;
	}
	
	//历史数据的图
	public String historypie(){
		//按照时间提取历史数据
		int wxhistory = wxResultService.contentCountByNetworkTypeRelationDate(mynetwork.getId(), C.TYPE_TRACK, relation, days);
		int xwhistory = xwResultService.contentCountByNetworkTypeRelationDate(mynetwork.getId(), C.TYPE_TRACK, relation, days);
		int wbhistory = wbResultService.contentCountByUserTypeRelationDate(mynetwork.getId(), C.TYPE_TRACK, relation, days);
		int lthistory = ltResultService.contentCountByNetworkTypeRelationDate(mynetwork.getId(), C.TYPE_TRACK, relation, days);
		
		ctx.put("wbhistory", String.valueOf(wbhistory));
		ctx.put("wxhistory", String.valueOf(wxhistory));
		ctx.put("xwhistory", String.valueOf(xwhistory));
		ctx.put("lthistory", String.valueOf(lthistory));
		
		return SUCCESS;
	}
	//历史数据的柱状图
	public String historybar(){
		List list = null;
		if(module.equals(C.MODULE_WEIBO)){
			list = wbResultService.dailyContentCountByUserTypeRelationDate(mynetwork.getId(), C.TYPE_TRACK, relation, days);
		}else if(module.equals(C.MODULE_WECHAT)){
			list = wxResultService.dailyContentCountByUserTypeRelationDate(mynetwork.getId(), C.TYPE_TRACK, relation, days);
		}else if(module.equals(C.MODULE_NEWS)){
			list = xwResultService.dailyContentCountByUserTypeRelationDate(mynetwork.getId(), C.TYPE_TRACK, relation, days);
		}else if(module.equals("lt")){
			list = ltResultService.dailyContentCountByUserTypeRelationDate(mynetwork.getId(), C.TYPE_TRACK, relation, days);
		}
		ctx.put("list", list);
		return SUCCESS;
	}
	
	
	//事件试图中的曲线图
	public String eventchart(){
		List<SjColumn> sjlist = sjColumnService.findByNetwork(mynetwork.getId(), 0, 3);
		int idx = offset % sjlist.size();
		SjColumn column = sjlist.get(idx);
		
		//获取事件所涉及的统计结果
		List list = daystatService.columnMonthly(column.getWbid(), C.MODULE_WEIBO, 365);
		list.addAll(daystatService.columnMonthly(column.getWxid(), C.MODULE_WECHAT, 365));
		list.addAll(daystatService.columnMonthly(column.getXwid(), C.MODULE_NEWS, 365));
		list.addAll(daystatService.columnMonthly(column.getLtid(), C.MODULE_BBS, 365));
		
		//合并统计结果
		HashMap<String, Long> hash = new HashMap<String, Long>();
		for(int i=0; i<list.size(); i++){
			Object[] object = (Object[])list.get(i);
			String date = (String)object[0];
			Long count = (Long)object[2];
			
			Long total = hash.get(date);
			if(total==null){
				hash.put(date, count);
			}else{
				hash.put(date, total+count);
			}
		}
		
		List result = new ArrayList();
		
		//对结果排序
		Iterator iter = hash.entrySet().iterator();
		while (iter.hasNext()) {
			Entry entry = (Entry) iter.next();
			String key = entry.getKey().toString();
			Long value = (Long)entry.getValue();

			int i = 0;
			for(i=0; i<result.size(); i++){
				Object[] object = (Object[])result.get(i);
				String val = object[0].toString();
				if(key.compareTo(val)<0){
					break;
				}
			}
			Object[] obj = new Object[2];
			obj[0]=key;
			obj[1]=value;
			result.add(i, obj);			
		}	
		
		ctx.put("list", result);
		return SUCCESS;		
	}
	
	//压缩js和css
	public String compress(){
		CompressScript cs = new CompressScript();
		if(this.prefix.indexOf("localhost")==-1){
			cs.compressFile("/js/base.js");
			cs.compressPath("/js/mining/");
			//cs.compressPath("/css/");
		}
		ctx.put("redirect", "http://www.dig88.cn/images/logo.png");
		return REDIRECT;
	}

	public Integer getColumnid() {
		return columnid;
	}

	public void setColumnid(Integer columnid) {
		this.columnid = columnid;
	}
	
	public String negative(){
		List<FmColumn> columnlist = fmColumnService.findByNetwork(mynetwork.getId(), C.STATUS_ACTIVE, 0, 5);
		List wxlist = wxResultService.contentListByNetworkTypeDateOrder(mynetwork.getId(), C.TYPE_NEGATIVE, "ptime", C.TIME_ALL, 0, 3);
		List wblist = wbResultService.contentListByNetworkTypeRelationDate(mynetwork.getId(), C.TYPE_NEGATIVE, C.RELATION_ALL, C.TIME_ALL, 0, 3);
		List xwlist = xwResultService.contentListByNetworkTypeRelationDate(mynetwork.getId(), C.TYPE_NEGATIVE, C.RELATION_ALL, C.TIME_ALL, 0, 3);
		List ltlist = ltResultService.contentListByUserType(mynetwork.getId(), C.TYPE_NEGATIVE, 0, 3);
		
		Integer wxcount = wxResultService.contentCountByNetworkTypeRelationDate(mynetwork.getId(), C.TYPE_NEGATIVE, C.RELATION_ALL, C.TIME_ALL);
		Integer wbcount = wbResultService.contentCountByUserTypeRelationDate(mynetwork.getId(), C.TYPE_NEGATIVE, C.RELATION_ALL, C.TIME_ALL);
		Integer xwcount = xwResultService.contentCountByNetworkTypeRelationDate(mynetwork.getId(), C.TYPE_NEGATIVE, C.RELATION_ALL, C.TIME_ALL);
		Integer ltcount = ltResultService.contentCountByNetworkTypeRelationDate(mynetwork.getId(), C.TYPE_NEGATIVE, C.RELATION_ALL, C.TIME_ALL);
		
		ctx.put("columnlist", columnlist);
		ctx.put("wxlist", wxlist);
		ctx.put("wblist", wblist);
		ctx.put("xwlist", xwlist);
		ctx.put("ltlist", ltlist);
		
		ctx.put("wxcount", String.valueOf(wxcount));
		ctx.put("wbcount", String.valueOf(wbcount));
		ctx.put("xwcount", String.valueOf(xwcount));
		ctx.put("ltcount", String.valueOf(ltcount));		
		return SUCCESS;
	}
	
	//统计更新
	public String summarize(){
		daystatService.summarize(days);
		geostatService.summarize(days);
		ctx.put("message", "SUCCESS");
		return MESSAGE;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public Integer getRelation() {
		return relation;
	}

	public void setRelation(Integer relation) {
		this.relation = relation;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getPageid() {
		return pageid;
	}

	public void setPageid(Integer pageid) {
		this.pageid = pageid;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}
	
	
}
