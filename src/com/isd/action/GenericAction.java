package com.isd.action;

import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.isd.entity.mining.Network;
import com.isd.entity.mining.Partner;
import com.isd.entity.mining.Setting;
import com.isd.entity.mining.User;
import com.isd.service.mining.NetworkService;
import com.isd.service.mining.PartnerService;
import com.isd.service.mining.UserService;
import com.isd.util.C;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

public class GenericAction extends ActionSupport implements Preparable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8059505581934382778L;
	/**
	 * 
	 */
	@Autowired
	private UserService 	userService;
	@Autowired
	private NetworkService networkService;
	@Autowired
	private PartnerService partnerService;

	public static final java.lang.String JSON 			= "json";
	public static final java.lang.String MESSAGE 		= "message";
	public static final java.lang.String TEXT 			= "text";
	public static final java.lang.String REDIRECT 		= "redirect";
	public static final java.lang.String REDIRECTACTION	= "redirectaction";
	public static final java.lang.String HOME 			= "home";
	public static final java.lang.String SUCCESS 		= "success";

	//页面所属模块
	protected String modulename 	= "home";
	//页面名称
	protected String pagename 		= "index";
    //域名
    protected String host;
	//页面输出格式
	protected String output 		= "web";
	//页面标题
	protected String pagetitle;
	//本次请求的URL
	protected String thisurl;
	//js调用的回调函数
	protected String callback;
	//url的前缀
	protected String prefix;
	//页号，从0开始
	protected int pageindex 		= 0;
	//页号偏移量，对于索引页，页面滚动时会自动追加部分内容，也就是在页号基础上增加偏移量来提取数据，从数据库取的时候的偏移量为(pageindex*sections+offset)*pagesize
	protected int offset 			= 0;
	//每页可以偏移的次数
	protected int sections 		= 4;
	//每页记录数
	protected int pagesize 		= 50;
	//随机数，用于标识自动加载页面的id
	protected long random;
	//合作伙伴
	protected Partner oem;
	//js的版本号，避免缓存
	protected String version = C.VERSION;

	//当前用户
	protected User myself 			= null;
	//当前管理的network
	protected Network mynetwork;
	//当前管理的network的设置
	protected Setting mysetting;
	protected ActionContext ctx;
	protected HttpServletRequest request;
	protected Map application;
	protected HttpSession session;
	protected HttpServletResponse response;

	public void prepare() {
		// 获得当前的上下文对象以及request和response对象
		ctx = ServletActionContext.getContext();
		application = ctx.getApplication();
		request = ServletActionContext.getRequest();
		session = request.getSession(true);
		response = ServletActionContext.getResponse();
		random = new Date().getTime();
		
	
		// 根据当前的URL确定模块的名称
		thisurl = request.getRequestURL().toString();
		Pattern pattern = Pattern.compile(
				"\\/([^\\/]*)\\/([^\\/]*)\\.[^\\/|\\.]*$",
				Pattern.CASE_INSENSITIVE);
		Matcher matched = pattern.matcher(thisurl);
		if (matched.find()) {
			modulename = matched.group(1);
			pagename = matched.group(2);
		}

		//提取url的前缀
		String url = request.getRequestURL().toString();
		prefix = url.substring(0, url.indexOf(request.getServletPath()));
        host = url.substring(0, url.indexOf("/", 8));
		

		
		// 从session中提取user对象
		myself = userService.getMe();

		//从session中提取network
		mynetwork = networkService.getMyNetwork();

		if(mynetwork!=null){
			mysetting = Setting.json2obj(mynetwork.getSetting()); 

			//提取账号设定的合作伙伴，如果提取不到，则按照域名查找
			if(mynetwork.getPartnerid()!=null){
				oem = partnerService.hash().get(mynetwork.getPartnerid());
			}
		}
		if(oem==null) {
			oem = partnerService.findByUrl(request.getServerName());
		}
	}

	public String getModulename() {
		return modulename;
	}

	public void setModulename(String modulename) {
		this.modulename = modulename;
	}

	public String getPagename() {
		return pagename;
	}

	public void setPagename(String pagename) {
		this.pagename = pagename;
	}

	public String getPagetitle() {
		return pagetitle;
	}

	public void setPagetitle(String pagetitle) {
		this.pagetitle = pagetitle;
	}

	public int getPageindex() {
		return pageindex;
	}

	public void setPageindex(int pageindex) {
		this.pageindex = pageindex;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public String getThisurl() {
		return thisurl;
	}

	public void setThisurl(String thisurl) {
		this.thisurl = thisurl;
	}

	public User getMyself() {
		return myself;
	}

	public void setMyself(User myself) {
		this.myself = myself;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	public int getSections() {
		return sections;
	}

	public void setSections(int sections) {
		this.sections = sections;
	}

	public String getCallback() {
		return callback;
	}

	public void setCallback(String callback) {
		this.callback = callback;
	}

	public long getRandom() {
		return random;
	}

	public void setRandom(long random) {
		this.random = random;
	}

	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	public Partner getOem() {
		return oem;
	}

	public void setOem(Partner oem) {
		this.oem = oem;
	}

	public String getHost(){
	    return host;
    }

	public String getVersion() {
		return version;
	}

	public Network getMynetwork() {
		return mynetwork;
	}

	public void setMynetwork(Network mynetwork) {
		this.mynetwork = mynetwork;
	}

	public Setting getMysetting() {
		return mysetting;
	}

	public void setMysetting(Setting mysetting) {
		this.mysetting = mysetting;
	}
}
