package com.isd.action.api;

import org.springframework.beans.factory.annotation.Autowired;

import com.isd.action.GenericAction;
import com.isd.service.mark.ZmtContentService;
import com.isd.service.mining.UserService;
import com.isd.service.news.XwResultService;
import com.isd.service.wechat.WxResultService;
import com.isd.service.weibo.WbResultService;

public class InterfaceAction extends GenericAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9139075860342489760L;

	@Autowired
	private XwResultService xwReportService;
	@Autowired
	private WxResultService wxReportService;
	@Autowired
	private ZmtContentService zmtContentService;
	@Autowired
	private WbResultService resultService;
	@Autowired
	private UserService userService;

	private String para; 
	private Integer uid;
	
	//根据用户的uid登录并且查看页面是否允许返回数据
	private boolean checkRight(){
		String referer = request.getHeader("referer");
		//如果没有传入uid，则返回空
		if(uid==null){
			return false;
		}else if(referer==null){
			if(thisurl.startsWith("http://localhost")||thisurl.startsWith("http://127.0.0.1/")){
				return true;
			}else{
				return false;
			}
		}else if(referer.startsWith("http://localhost/")||referer.startsWith("http://127.0.0.1/")){
			//如果是本地测试，则不检验登录状况
			return true;
		}
		
		//如果没有登录，则先自动登录
		if(myself==null){
			userService.login(uid);
			myself = userService.getMe();
		}
		
		//如果登录状态仍旧不对，说明传进来的uid参数存在问题
		/*if(myself==null||myself.getReferer()==null){
			return false;
		}else if(referer.indexOf(myself.getReferer())>-1){
			return true;
		}*/
		return false;
	}

	//获取新闻内容
	public String news(){
		/*
		if(this.checkRight()){
			List list = xwReportService.contentListByUserType(uid, 0, 0, pagesize);
			List<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
			for(int i=0; i<list.size(); i++){
				Object[] obj = (Object[])list.get(i);
				XwResult report = (XwResult)obj[0];
				XwSite site = (XwSite) obj[1];
				HashMap<String, String> hash = new HashMap<String, String>();
				hash.put("name", site.getName());
				hash.put("title", C.subStr(report.getTitle(), 40, "..."));
				hash.put("url", report.getUrl());
				hash.put("time", C.dateToString(report.getFtime(), "yyyy-MM-dd"));
				result.add(hash);
			}
			
			JSONArray json=JSONArray.fromObject(result);
			String text = json.toString();
			text = text.replaceAll("\"", "'");
			if(para!=null&&para.length()>0){
				text = "var "+para+"="+text+";";
			}		
			ctx.put("text", text);
		}
		*/
		return TEXT;
	}
	
	//获取微信内容
	public String wechat(){
		/*if(this.checkRight()){		
			List list = wxReportService.contentListByUserType(uid, 0, 0, pagesize);
			List<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
			for(int i=0; i<list.size(); i++){
				Object[] obj = (Object[])list.get(i);
				WxContent content = (WxContent)obj[0];
				WxWeixin  site = (WxWeixin) obj[1];
				
				HashMap<String, String> hash = new HashMap<String, String>();
				hash.put("name", site.getName());
				hash.put("title", C.subStr(content.getTitle(), 40, "..."));
				hash.put("url", content.getUrl());
				hash.put("time", C.dateToString(content.getFtime(), "yyyy-MM-dd"));
				result.add(hash);
			}
			
			JSONArray json=JSONArray.fromObject(result);
			String text = json.toString();
			text = text.replaceAll("\"", "'");
			if(para!=null&&para.length()>0){
				text = "var "+para+"="+text+";";
			}		
			ctx.put("text", text);
		}*/
		return TEXT;		
	}
	
	//获取收藏的文章
	public String mark(){
		/*if(this.checkRight()){
			List list = zmtContentService.list(uid, null, null, null, 0, pagesize);
			List<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
			for(int i=0; i<list.size(); i++){
				ZmtContent content = (ZmtContent)list.get(i);
				
				HashMap<String, String> hash = new HashMap<String, String>();
				hash.put("name", content.getMedia());
				hash.put("title", C.subStr(content.getTitle(), 40, "..."));
				hash.put("url", content.getAddress());
				hash.put("time", C.dateToString(content.getCtime(), "yyyy-MM-dd"));
				result.add(hash);
			}
			
			JSONArray json=JSONArray.fromObject(result);
			String text = json.toString();
			text = text.replaceAll("\"", "'");
			if(para!=null&&para.length()>0){
				text = "var "+para+"="+text+";";
			}		
			ctx.put("text", text);
		}*/
		return TEXT;		
	}
	
	//获取微博信息
	public String weibo(){
		/*
		if(this.checkRight()){
			List list = resultService.findByUid(uid, 0, pagesize);
			List<HashMap<String, String>> result = new ArrayList<HashMap<String, String>>();
			for(int i=0; i<list.size(); i++){
				Object[] obj = (Object[])list.get(i);
				WbMiddle feed = (WbMiddle)obj[0];
				WbUser weibo = (WbUser)obj[1];
			
				HashMap<String, String> hash = new HashMap<String, String>();
				hash.put("name", weibo.getNickname());
				hash.put("vtype", weibo.getVtype());
				hash.put("title", feed.getContent());
				hash.put("url", "http://weibo.com"+weibo.getHomepage());
				hash.put("time", C.dateToString(feed.getPtime(), "yyyy-MM-dd"));
				result.add(hash);
			}
			
			JSONArray json=JSONArray.fromObject(result);
			String text = json.toString();
			text = text.replaceAll("\"", "'");
			if(para!=null&&para.length()>0){
				text = "var "+para+"="+text+";";
			}	
			ctx.put("text", text);
		}*/
		return TEXT;
	}
	
	
	public String getPara() {
		return para;
	}

	public void setPara(String para) {
		this.para = para;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}
}
