package com.isd.action.mining;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.isd.action.GenericAction;
import com.isd.entity.common.City;
import com.isd.entity.common.Province;
import com.isd.entity.weibo.WbColumn;
import com.isd.entity.weibo.WbResult;
import com.isd.entity.weibo.WbUser;
import com.isd.service.common.CityService;
import com.isd.service.common.ProvinceService;
import com.isd.service.mining.DaystatService;
import com.isd.service.mining.OpinionService;
import com.isd.service.weibo.WbColumnService;
import com.isd.service.weibo.WbDictService;
import com.isd.service.weibo.WbResultService;
import com.isd.service.weibo.WbUserService;
import com.isd.util.C;


public class WeiboAction extends GenericAction{


	/**
	 *
	 */
	private static final long serialVersionUID = -8322939315329400841L;

	@Autowired
	private WbUserService userService;
	@Autowired
	private WbColumnService columnService;
	@Autowired
	private WbResultService resultService;
	@Autowired
	private ProvinceService provinceService;
	@Autowired
	private CityService cityService;
	@Autowired
	private DaystatService statService;
	@Autowired
	private WbDictService dictService;
	@Autowired
	private OpinionService opinionService;

	private WbResult result;
	private WbUser weibo;
	private WbColumn column;
	private String orderby;
	private String ids;
	private int days=30;


	//微博用户信息
	public String detail(){
		weibo = userService.findByWbid(weibo.getWbid());
		return SUCCESS;
	}

	//任务列表
	public String columnlist(){
		List<WbColumn> list = columnService.listByNetwork(mynetwork.getId(), 0);
		///////////////////////////Integer maxid = middleService.getMaxId();
		ctx.put("columnlist", list);
		/////////////////////ctx.put("maxid", maxid);
		return SUCCESS;
	}

	//新建或编辑任务
	public String column(){
		if(column!=null&&column.getId()!=null){
			column = columnService.findById(column.getId());
			if(!column.getNetworkid().equals(mynetwork.getId())){
				ctx.put("errmsg", "请查看您自己定义的任务");
				column = null;
			}
		}else{
			column = new WbColumn();
		}
		return SUCCESS;
	}

	//保存任务
	public String savecolumn(){
		columnService.save(column, mynetwork);

		ctx.put("redirectaction", "weibo/columnlist");
		return REDIRECTACTION;
	}

	//删除任务
	public String columndelete(){
		WbColumn tsk = columnService.findById(column.getId());
		if(tsk.getNetworkid().equals(mynetwork.getId())){
			columnService.delete(column.getId());
		}
		ctx.put("text", "success");
		return TEXT;
	}

	public String refresh(){
		column = columnService.findByFetchTime();
		columnService.refresh(column);
		ctx.put("message", "SUCCESS");
		return MESSAGE;
	}

	//用户管理
	public String user(){
		List<WbColumn> columnlist = columnService.listByNetwork(mynetwork.getId(),0);
		//如果客户没有任务列表，则引导到创建任务页面
		if(columnlist.size()==0){
			ctx.put("redirectaction", "weibo/column");
			return REDIRECTACTION;
		}

		//如果没有选择任务，则缺省使用第一个任务
		if(column==null){
			column = new WbColumn();
			column.setId(columnlist.get(columnlist.size()-1).getId());
			column.setNetworkid(mynetwork.getId());
		}else if(column.getId()==null){
			column.setId(columnlist.get(columnlist.size()-1).getId());
			column.setNetworkid(mynetwork.getId());
		}else{ //如果传进来任务ID，则加载以备后面确认属于当前用户
			WbColumn tsk = columnService.findById(column.getId());
			column.setNetworkid(tsk.getNetworkid());
		}

		if(column.getNetworkid().equals(mynetwork.getId())){
			List resultlist = resultService.userListBySearch(column, weibo, days, pageindex*pagesize, pagesize);
			Integer total = resultService.userCountBySearch(column, weibo, days);

			//提取省份的列表，用作下拉框的内容
			List<Province> provlist = provinceService.provinceList();

			//获取省市的hash内容，用于显示省市名称
			HashMap<Integer, Province> provhash = provinceService.provinceHash();
			HashMap<Integer, City> cityhash = cityService.cityHash();

			ctx.put("provlist", provlist);
			ctx.put("cityhash", cityhash);
			ctx.put("provhash", provhash);
			ctx.put("columnlist", columnlist);
			ctx.put("resultlist", resultlist);
			ctx.put("total", total.toString());
		}
		return SUCCESS;
	}

	//导出用户
	public String userexport(){
        response.setContentType("application/vnd.ms-excel");
        //2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)
        response.setHeader("Content-Disposition", "attachment;fileName="+"user_result.xls");
		WbColumn tsk = columnService.findById(column.getId());
		if(tsk.getNetworkid().equals(mynetwork.getId())){
			List<WbResult> resultlist;
			resultlist = resultService.userListBySearch(column, weibo, days, pageindex*pagesize, pagesize);

			//获取省市的hash内容，用于显示省市名称
			HashMap<Integer, Province> provhash = provinceService.provinceHash();
			HashMap<Integer, City> cityhash = cityService.cityHash();

			ctx.put("cityhash", cityhash);
			ctx.put("provhash", provhash);

			ctx.put("resultlist", resultlist);
		}
		return SUCCESS;
	}

	//内容管理
	public String content(){
		//提取内容的最早发布时间和最晚发布时间
		Date from = new Date();
		Date to   = new Date(0);
		
		List<WbColumn> columnlist = columnService.listByNetwork(mynetwork.getId(),0);
		//如果客户没有任务列表，则引导到创建任务页面
		if(columnlist.size()==0){
			ctx.put("redirectaction", "weibo/column");
			return REDIRECTACTION;
		}

		//如果没有选择任务，则缺省使用第一个任务
		if(column==null||column.getId()==null){
			column = new WbColumn();
			column.setId(-1);
		}
		
		if(orderby==null||orderby.length()==0) {
			orderby = "ptime";
		}

		//将GET参数中的汉字做处理
		column.setSearchword(column.getSearchword());
		
		List resultlist = resultService.contentListByColumnDateOrder(mynetwork.getId(), C.TYPE_TRACK, column.getId(), days, column.getSearchword(), orderby,  pageindex*pagesize, pagesize);

		if(resultlist.size()>0){
			WbResult result =(WbResult)((Object[])(resultlist.get(0)))[0];
			if(result.getPtime().after(to)){
				to = result.getPtime();
			}
			result =(WbResult)((Object[])(resultlist.get(resultlist.size()-1)))[0];
			if(result.getPtime().before(from)){
				from = result.getPtime();
			}
		}
		

		//正反面的信息
		HashMap<Long, Integer> opinionhash = opinionService.findByNetworkModulePtime(mynetwork.getId(), C.MODULE_WEIBO, from, to);

		
		ctx.put("resultlist", resultlist);
		ctx.put("columnlist", columnlist);
		ctx.put("columnlist", columnlist);
		ctx.put("opinionhash", opinionhash);
		return SUCCESS;
	}

	//更多内容
	public String more(){
		//如果没有选择任务，则缺省使用第一个任务
		if(column==null){
			column = new WbColumn();
			column.setId(-1);
		}else if(column.getId()==null){
			column.setId(-1);
		}
		if(column.getType()==null){
			column.setType(0);
		}
		column.setNetworkid(mynetwork.getId());
		column.setSearchword("ptime");

		List resultlist = resultService.contentListByColumnDateOrder(mynetwork.getId(), column.getType(), column.getId(), days, column.getSearchword(), orderby, pageindex*pagesize, pagesize);
		Integer total = resultService.contentCountByColumnDate(column.getId(), days);

		ctx.put("resultlist", resultlist);
		ctx.put("total", total.toString());
		return SUCCESS;
	}

	//导出用户
	public String contentexport(){
        response.setContentType("application/vnd.ms-excel");
        //2.设置文件头：最后一个参数是设置下载文件名(假如我们叫a.pdf)
        response.setHeader("Content-Disposition", "attachment;fileName="+"content_result.xls");

		//如果没有选择任务，则缺省使用第一个任务
		if(column==null||column.getId()==null){
			column = new WbColumn();
			column.setId(-1);
			column.setSearchword("ptime");
		}
		column.setNetworkid(mynetwork.getId());

		List resultlist = resultService.contentListByColumnDateOrder(mynetwork.getId(), C.TYPE_TRACK, column.getId(), days, column.getSearchword(), orderby, pageindex*pagesize, pagesize);
		ctx.put("resultlist", resultlist);

		return SUCCESS;
	}

	//数据分析
	public String result(){
		List<WbColumn> columnlist = columnService.listByNetwork(mynetwork.getId(),0);

		ctx.put("columnlist", columnlist);
		return SUCCESS;
	}

	//微博用户区域分析
	public String mapchart(){
		List list = resultService.provinceMediaCountByColumnDate(column.getId(), days);
		HashMap hash = provinceService.provinceHash();
		ctx.put("list", list);
		ctx.put("hash", hash);
		return SUCCESS;
	}

	//数据挖掘分析图
	public String columnchart(){
		List list 	= resultService.dailyContentCountByColumnDate(column.getId(), days);
		ctx.put("list", list);
		return SUCCESS;
	}

	//挖掘任务对比图
	public String stockchart(){
		List list = resultService.columnreport(mynetwork.getId(), C.TYPE_TRACK, days);

		ctx.put("list", list);
		return SUCCESS;
	}

	//列出符合查询条件的微博
	public String feed(){
		pagesize = 10;
		weibo = userService.findByWbid(result.getWbid());
		List<WbResult> list = resultService.contentListByColumnWbid(result.getColumnid(), result.getWbid(), pageindex*pagesize, pagesize);
		WbColumn column = columnService.findById(result.getColumnid());
		Integer total = resultService.feedCountByColumnWbid(result.getColumnid(), result.getWbid());
		ctx.put("total", String.valueOf(total));
		ctx.put("list", list);
		if(column!=null){
			//有时会出现微博栏目被删的情况
			ctx.put("keyword", column.getSearchword());
		}
		return SUCCESS;
	}

	//微博内容列表
	public String feedlist(){
		weibo = userService.findByWbid(weibo.getWbid());
		/////////////////////////////List<WbMiddle> list = middleService.findByWbid(weibo.getWbid());

		ctx.put("weibo", weibo);
		////////////////////////////ctx.put("list", list);
		return SUCCESS;
	}

	//删除微博内容
	public String feeddelete(){
		resultService.feeddelete(result.getId());
		ctx.put("message", "success");
		return MESSAGE;
	}

	//从查询结果中删除用户
	public String userdelete(){
		if(ids!=null&&ids.length()>0){
			if(column!=null&&column.getId()!=null){
				column = columnService.findById(column.getId());
				if(column.getNetworkid().equals(mynetwork.getId())){
					resultService.deleteByColumnAndWbids(column.getId(), ids);
				}
			}
			ctx.put("text", "SUCCESS");
		}else{
			ctx.put("text", "NO IDS");
		}
		return TEXT;
	}

	//总揽的按月统计数据
	public String monthly(){
		List list = statService.userMonthly(mynetwork.getId(), C.MODULE_WEIBO, 0, 365);
		ctx.put("list", list);
		return SUCCESS;
	}

	//转发评论趋势分析的页面
	public String reach(){
		List<WbColumn> columnlist = columnService.listByNetwork(mynetwork.getId(),0);

		ctx.put("columnlist", columnlist);
		return SUCCESS;
	}

	//评论转发趋势分析报表
	public String trendchart(){
		List list = resultService.dailyCommentForwardSumByColumnDate(column.getId(), days);
		HashMap<String, Integer> hash = resultService.dailyVipByColumnDate(column.getId(), days);
		ctx.put("datalist", list);
		ctx.put("viphash", hash);
		return SUCCESS;
	}

	//评论转发与粉丝的关系报表
	public String fanschart(){
		List list = resultService.commentForwardSumByColumnFansrange(column.getId(), days);

		HashMap<Integer, String> rangehash = C.getFansRangeHash();
		ctx.put("datalist", list);
		ctx.put("rangehash", rangehash);
		return SUCCESS;
	}

	//设备分布图
	public String devicechart(){
		List list = resultService.contentCountByColumnDevice(column.getId(), days);
		if(list.size()>15) {
			list = list.subList(0, 15);
		}
		ctx.put("datalist", list);
		return SUCCESS;
	}


	public WbResult getResult() {
		return result;
	}

	public void setResult(WbResult result) {
		this.result = result;
	}

	public WbUser getWeibo() {
		return weibo;
	}

	public void setWeibo(WbUser weibo) {
		this.weibo = weibo;
	}


	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public WbColumn getColumn() {
		return column;
	}

	public void setColumn(WbColumn column) {
		this.column = column;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public String getOrderby() {
		return orderby;
	}

	public void setOrderby(String orderby) {
		this.orderby = orderby;
	}
}
