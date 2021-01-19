package com.isd.action.mining;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.isd.action.GenericAction;
import com.isd.entity.mining.Network;
import com.isd.entity.mining.Setting;
import com.isd.entity.recommend.Favorite;
import com.isd.entity.recommend.Recommend;
import com.isd.service.bbs.LtResultService;
import com.isd.service.mining.NetworkService;
import com.isd.service.mining.UserService;
import com.isd.service.news.XwResultService;
import com.isd.service.recommend.FavoriteService;
import com.isd.service.recommend.RecommendService;
import com.isd.service.wechat.WxResultService;
import com.isd.service.weibo.WbResultService;
import com.isd.util.C;

public class MobileAction extends GenericAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -608906749444758362L;
	@Autowired
	private WbResultService wbResultService;
	@Autowired
	private WxResultService wxResultService;
	@Autowired
	private XwResultService xwResultService;
	@Autowired
	private LtResultService ltResultService;
	@Autowired
	private UserService userService;
	@Autowired
	private RecommendService recommendService;
	@Autowired
	private FavoriteService favoriteService;
	@Autowired
	private NetworkService networkService;
	
	
	//用于统计数据的时间范围
	private Integer days = 0;
	private Integer relation = 0;
	private Integer type = 0;
	private String module="xw";
	private Integer pageid;
	private Recommend recommend;
	
	public String index(){
		String token = C.getCookie(C.COOKIE_TOKEN);
		if(token==null){
			try{
				String username = C.getCookie(C.COOKIE_EMAIL);
				ctx.put("username", username);
			}catch(Exception e){
				e.printStackTrace();
			}
			return SUCCESS;
		}else{
			if(mynetwork==null){
				userService.login(token);
			}
			ctx.put("redirectaction", "mobile/today");
			return REDIRECTACTION;
		}
	}
	
	//今日更新
	public String today(){
		if(mynetwork==null){
			ctx.put("redirectaction", "mobile/index");
			return REDIRECTACTION;
		}else{
			return SUCCESS;
		}
	}
	//加载今日更新数据
	public String content(){
		if(mynetwork==null){
			ctx.put("redirectaction", "mobile/index");
			return REDIRECTACTION;
		}
		List list = null;
		Integer total = 0;
		if(module.equals(C.MODULE_NEWS)){
			list  = xwResultService.contentListByUserTypeRelationDate(mynetwork.getId(), C.TYPE_TRACK, relation, days, pageindex*pagesize, pagesize);
			total = xwResultService.contentCountByNetworkTypeRelationDate(mynetwork.getId(), C.TYPE_TRACK, relation, days);
		}else if(module.equals(C.MODULE_WECHAT)){
			list = wxResultService.contentListByNetworkTypeRelationDate(mynetwork.getId(), C.TYPE_TRACK, relation, days, pageindex*pagesize, pagesize);
			total = wxResultService.contentCountByNetworkTypeRelationDate(mynetwork.getId(), C.TYPE_TRACK, relation, days);
		}else if(module.equals(C.MODULE_WEIBO)){
			list = wbResultService.contentListByNetworkTypeRelationDate(mynetwork.getId(), C.TYPE_TRACK, relation, days, pageindex*pagesize, pagesize);
			total = wbResultService.contentCountByUserTypeRelationDate(mynetwork.getId(), C.TYPE_TRACK, relation, days);
		}else if(module.equals(C.MODULE_BBS)){
			list = ltResultService.contentListByUserTypeRelationDate(mynetwork.getId(), C.TYPE_TRACK, relation, days, pageindex*pagesize, pagesize);
			total = ltResultService.contentCountByNetworkTypeRelationDate(mynetwork.getId(), C.TYPE_TRACK, relation, days);
		}
		ctx.put("list", list);
		ctx.put("total", total.toString());
		return SUCCESS;
	}
	
	//每日焦点
	public String focus(){
		if(mynetwork==null){
			ctx.put("redirectaction", "mobile/index");
			return REDIRECTACTION;
		}
		List list = null;
		String from = C.getDate(0);
		String to   = C.dateToString(new Date());
		
		if(days==2){
			from = C.getDateStart(new Date(new Date().getTime()-24*3600l*1000));
			to 	 = C.getDateEnd(new Date(new Date().getTime()-24*3600l*1000));
		}
		
		if(module.equals(C.MODULE_NEWS)){
			list  = xwResultService.topContentListByUserTypeRelationDate(mynetwork.getId(), -1, C.TYPE_TRACK, relation, from, to, 0, 50);
		}else if(module.equals(C.MODULE_WECHAT)){
			list = wxResultService.topContentListByUserTypeRelationDate(mynetwork.getId(), -1,  C.TYPE_TRACK, relation, from, to, 0, 50);
		}else if(module.equals(C.MODULE_WEIBO)){
			list = wbResultService.topContentListByUserTypeRelationDate(mynetwork.getId(), C.TYPE_TRACK, relation, from, to, 0, 50);
		}else if(module.equals(C.MODULE_BBS)){
			list = ltResultService.topContentListByUserTypeRelationDate(mynetwork.getId(), C.TYPE_TRACK, relation, from, to, 0, 50);
		}
		ctx.put("list", list);
		return SUCCESS;		
	}
	
	//显示相同标题
	public String similar(){
		if(mynetwork==null){
			ctx.put("redirectaction", "mobile/index");
			return REDIRECTACTION;
		}		
		List list = null;
		String from = C.getDate(0);
		String to   = C.dateToString(new Date());
		
		if(days==2){
			from = C.getDateStart(new Date(new Date().getTime()-24*3600l*1000));
			to 	 = C.getDateEnd(new Date(new Date().getTime()-24*3600l*1000));
		}
		if(module.equals(C.MODULE_NEWS)){
			list  = xwResultService.sameTitleListByUserTypeRelationDate(mynetwork.getId(), type, relation, from, to, pageid);
		}else if(module.equals(C.MODULE_WECHAT)){
			list = wxResultService.sameTitleListByUserTypeRelationDate(mynetwork.getId(), type, relation, from, to, pageid);
		}else if(module.equals(C.MODULE_BBS)){
			list = ltResultService.sameTitleListByUserTypeRelationDate(mynetwork.getId(), type, relation, from, to, pageid);
		}
		ctx.put("list", list);		
		return SUCCESS;
	}

	public String logout(){
		session.removeAttribute(C.SESSION_USER);
		session.removeAttribute(C.SESSION_NETWORK);
		C.removeCookie(C.COOKIE_TOKEN);
		ctx.put("redirectaction", "mobile/index");
		return REDIRECTACTION;		
	}

	//recommend内容
	public String recommend(){
		recommend = recommendService.findById(recommend.getId());
		if(recommend !=null) {
			Network network = networkService.findById(recommend.getNetworkid());
			Setting setting = Setting.json2obj(network.getSetting());
			
			ctx.put("network", network);
			ctx.put("setting", setting);

			if(recommend.getStatus()==1){
				//阅读数加1
				recommend.setReadno(recommend.getReadno()+1);
				recommendService.update(recommend);


				List<Favorite> list = favoriteService.findByIds(recommend.getIdlist());

				//返回微信的签名信息
				StringBuffer url  = request.getRequestURL();
				if (request.getQueryString() != null) {
					url.append('?');
					url.append(request.getQueryString());
				}
				String ticket = C.wxJsapiTicket();
				Map<String, String> sign = C.sign(ticket, url.toString().split("#")[0]);
				ctx.put("sign", sign);
				ctx.put("list", list);
			}
		}
		return SUCCESS;
	}

	//跳转到相应页面
	public String readup(){
		Favorite favorite = favoriteService.findById(pageid);
		if(favorite!=null) {
			Integer readno = favorite.getReadno() == null ? 1 : favorite.getReadno() + 1;
			favorite.setReadno(readno);
			favoriteService.update(favorite);
			ctx.put("text", "success");
		}
		return TEXT;
	}

	//recommend增加分享
	public String shareup(){
		recommendService.share(recommend.getId());
		ctx.put("text", "success");
		return TEXT;
	}

	public String review(){
		Network network = networkService.findById(recommend.getNetworkid());
		Setting setting = Setting.json2obj(network.getSetting());
		
		ctx.put("network", network);
		ctx.put("setting", setting);
		return SUCCESS;
	}

	public String history(){
		pagesize = 20;
		List<Recommend> list = recommendService.findHistory(recommend.getNetworkid(), recommend.getId(), pageindex*pagesize, pagesize);
		ctx.put("list", list);
		return SUCCESS;
	}

	public String praise(){
        recommend = recommendService.findById(recommend.getId());
        if(recommend !=null) {
            recommend.setPraise(recommend.getPraise()+1);
            recommendService.update(recommend);
            ctx.put("text", recommend.getPraise().toString());
        }
        return TEXT;
    }

    public String reg(){
    	return SUCCESS;
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

	public Integer getPageid() {
		return pageid;
	}

	public void setPageid(Integer pageid) {
		this.pageid = pageid;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Recommend getRecommend() {
		return recommend;
	}

	public void setRecommend(Recommend recommend) {
		this.recommend = recommend;
	}
}
