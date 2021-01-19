
package com.isd.util;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.io.FileUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolException;
import org.apache.http.client.RedirectStrategy;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.json.JSONUtil;

import com.isd.entity.common.Province;
import com.opensymphony.oscache.base.NeedsRefreshException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class C {
	public final static String VERSION				= "20180426";

	//用户缺省的networkid
	public final static Integer DEFAULT_NETWORK		= 2;

	public final static String MODULE_WEIBO			= "wb";
	public final static String MODULE_NEWS			= "xw";
	public final static String MODULE_WECHAT		= "wx";
	public final static String MODULE_BBS			= "lt";
	public final static String MODULE_EVENT			= "sj";
	public final static String MODULE_NEGATIVE		= "fm";
	public final static String MODULE_SITE			= "qz";
	public final static String MODULE_RECOMMEND		= "reco";
	public final static String MODULE_MARK			= "zmt";
	
	public final static String CATEGORY_PROVINCE	= "prov";					/*内容的分类，包括按省份、按月份、总数、当天数据量等*/
	public final static String CATEGORY_MONTH		= "mon";
	public final static String CATEGORY_TOTAL		= "total";
	public final static String CATEGORY_TODAY		= "today";
	
	public final static String TYPE_CONTENT			= "content";				/*数据的类型，是内容还是站点*/
	public final static String TYPE_MEDIA			= "media";
	
	public final static Integer RELATION_ALL		= 0;
	public final static Integer RELATION_SELF		= 1;
	public final static Integer RELATION_COMP		= 2;
	
	//缓存的Key
	public final static String CACHE_USER			= "existeduser";			//已经入库的用户以及关系
	public final static String CACHE_FETCHUSERCOUNT	= "fetchusercount";			//每次抓取的用户数，根据抓取的速度将自动调整
	public final static String CACHE_FETCHFEEDCOUNT	= "fetchfeedcount";			//每次抓取的用户数，根据抓取的速度将自动调整
	public final static String CACHE_FETCHSTATUS	= "fetchstatus";			//抓取状态
	
	//用户登录后，session记录的数据
	public final static String SESSION_USER 		= "user";					//保存user对象
	public final static String SESSION_ADMINUSER 	= "adminUser";			 
	public final static String SESSION_URL 			= "url";					//保存跳转的目的地
	public final static String SESSION_AUTHCODE		= "authCode";				//验证码	
	public final static String SESSION_THREAD		= "thread";					//线程
	public final static String SESSION_TMPUSER		= "tmpuser";				//临时用户信息
	public final static String SESSION_NETWORK		= "network";				//账号信息

	
	//数据显示的时间选项
	public final static int TIME_TODAY 				= 0;
	public final static int TIME_24HOURS 			= 1;
	public final static int TIME_WEEK 				= 7;
	public final static int TIME_MONTH 				= 30;
	public final static int TIME_3MONTH 			= 90;
	public final static int TIME_HALFYEAR 			= 183;
	public final static int TIME_YEAR 				= 365;
	public final static int TIME_ALL 				= 3650;
	
	//数据类型
	public final static int TYPE_TRACK 				= 0;
	public final static int TYPE_NEGATIVE			= 2;
	public final static int TYPE_EVENT 				= 1;
	
	//任务抓取的类型
	public final static int TASK_KEYWORD			= 0;
	public final static int TASK_MEDIA				= 1;
	
	//标签中的关键词
	public final static String KEY_PROVINCE			= "pro";
	public final static String KEY_CITY				= "cty";
	public final static String KEY_COMPANY			= "com";
	public final static String KEY_SCHOOL			= "sch";
	public final static String KEY_TAG				= "tag";
	public final static String KEY_CLIENT			= "cli";	
	
	//微博字典
	public final static String DICT_INDUSTRY		= "ind";
	public final static String DICT_COMPANY			= "com";
	public final static String DICT_SCHOOL			= "sch";
	public final static String DICT_PROVINCE		= "pro";
	public final static String DICT_CITY			= "city";
	public final static String DICT_CLIENT			= "cli";
	public final static String DICT_TITLE			= "tit";

	public final static String APPID				= "wx09c588e6bae17cf4";
	public final static String APPSECRET			= "24119ba5db75b9f2511212677c753e10";
	
	//搜索步调
	public final static int SEARCH_PACE				= 10000;
	
	//站点的代号
	public final static Integer SITE_WEIBO			= 1;						//微博
	
	// MD5运算时的附加码，防止用户猜出原码
	public final static String MD5_SECRET 			= "isd.robot";
	
	//状态
	public final static Integer STATUS_NEW 			= 0;
	public final static Integer STATUS_FINISHED 	= 1;
	public final static Integer STATUS_RUNNING 		= 2;
	public final static Integer STATUS_PAUSE 		= 3;
	public final static Integer STATUS_ACTIVE 		= 1;
	public final static Integer STATUS_INACTIVE		= 0;
	
	// 邮箱信息
	public final static String MAIL_SERVER 			= "mail.dig24.cn";
	public final static String MAIL_ACCOUNT 		= "webjournal@dig24.cn";
	public final static String MAIL_PASSOWRD 		= "Journal&Web";
	public final static String MAIL_FROMNAME 		= "Dig24";//写中文是乱码的
	public final static String MAIL_FROM 				= "webjournal@dig24.cn";
	
	// Cookie
	public final static String COOKIE_TOKEN			= "bdp_token";
	public final static String COOKIE_EMAIL 			= "bdp_mail";
	public final static String COOKIE_CODE 			= "bdp_code";
	public final static String COOKIE_AUTO 			= "bdp_auto";
	public final static Integer COOKIE_LIFE 			= 360 * 24 * 3600;
	public final static int[] FANSRANGE 				= {0, 10, 50, 100, 200, 400, 500, 1000, 2000, 3000, 4000, 5000, 10000, 50000, 100000, 500000};


	public static String MD5(String str) {
		String ret = "";
		ret = DigestUtils.md5Hex(str);
		return ret;
	}
	
	//对于用户的userid进行加密，"o"分割用户的ID以及哈希值
	public static String genToken(Integer userid) {
		String uid = userid.toString();
		String code = C.MD5(uid+"_"+C.MD5_SECRET);
		String token = uid+"o"+code.substring(uid.length()+1);
		return token;
	}
	
	//从token中获得用户ID
	public static Integer getIDFromToken(String token){
		Integer pos = token.indexOf("o");
		if(pos > -1){
			String uid = token.substring(0,pos);
			return Integer.valueOf(uid);
		}else{
			return null;
		}
	}	
	
	//将文件读入内存中
	public static String readfile(String filename){
		BufferedReader br = null;
		StringBuffer buffer= new StringBuffer();;
		try {
			br=new BufferedReader(new InputStreamReader(new FileInputStream(filename),"UTF-8"));
			while( br.ready()){
				buffer.append(br.readLine()+"\r\n");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(br!=null)
					br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return buffer.toString();
	}
	
	//将内存中的内容写入文件
	public static void writeFile(String content, String filename){ 
        File filePath=new File(filename.substring(0,filename.lastIndexOf(File.separator)));  
        if(!filePath.exists()){  
            filePath.mkdirs();  
        }  
        
        OutputStreamWriter out =null;
        try {
			out = new OutputStreamWriter(new FileOutputStream(filename),"UTF-8"); 
			out.write(content,0,content.length());  
			out.flush(); 
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(out!=null)
					out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}                 
    }	
	
	//删除目录
    public static boolean deleteDir(File dir) {
    	if (dir.isDirectory()) {
    		String[] children = dir.list();
    		for (int i=0; i<children.length; i++) {
    			boolean success = deleteDir(new File(dir, children[i]));
    			if (!success) {
    				return false;
                }
    		}
    	}
    	// The directory is now empty so now it can be smoked
    	return dir.delete();
    }

	
	//本函数只用作发送注册相关的邮件，不能用来发送大量的邮件
	static public void sendEmail(String email, String subject, String body) {
		// sending email, if send fail, return default password
		SendMail sendmail = new SendMail(C.MAIL_SERVER);
		sendmail.sendMail(C.MAIL_FROM, C.MAIL_FROMNAME, email, "", "", subject, body, C.MAIL_ACCOUNT, C.MAIL_PASSOWRD, "");
	}
	
	//读取cookie
	static public String getCookie(String name) {
		HttpServletRequest request = ServletActionContext.getRequest();
		Cookie[] cookies = request.getCookies();
		try{
			if(cookies != null){
				for (int i = 0; i < cookies.length; i++) {
					Cookie c = cookies[i];
					if (c.getName().equalsIgnoreCase(name)) {
						return URLDecoder.decode(c.getValue(), "UTF-8");
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	//读取参数，用于拦截器中提取数据
	static public String getParameter(String name) {
		HttpServletRequest request = ServletActionContext.getRequest();
		String value = request.getParameter(name);
		return value;
	}

	//设置cookie，浏览器关闭时cookie会清除
	static public void setCookie(String name, String value) {
		HttpServletResponse response = ServletActionContext.getResponse();
		try{
			Cookie cookie = new Cookie(name, URLEncoder.encode(value, "UTF-8"));
			cookie.setMaxAge(-1);
			cookie.setPath("/");
			response.addCookie(cookie);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//设置cookie，cookie有效期是一年
	static public void setCookie(String name, String value, int life) {
		HttpServletResponse response = ServletActionContext.getResponse();
		try{
			Cookie cookie = new Cookie(name, URLEncoder.encode(value, "UTF-8"));
			cookie.setMaxAge(life);
			cookie.setPath("/");
			response.addCookie(cookie);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//删除cookie
	static public void removeCookie(String name) {
		HttpServletResponse response = ServletActionContext.getResponse();
		Cookie cookie = new Cookie(name, null);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);
	}	
	
	// 保存session
	public static void setSession(String key, Object value) {
		HttpSession session = ServletActionContext.getRequest().getSession(true);
		session.setAttribute(key, value);
	}
	
	// 提取session
	public static Object getSession(String key) {
		HttpSession session = ServletActionContext.getRequest().getSession(true);
		return session.getAttribute(key);
	}
	
	//获取天气预报
	public static String calendarToString(Calendar time, String pattern) {
		SimpleDateFormat formatter = new SimpleDateFormat(pattern, Locale.CHINA);
		String returnValue = formatter.format(time.getTime());
		return returnValue;
	}
	
	//整理关键词
	static public Vector<String> extractKeyword(String keyword){
		Vector<String> result = new Vector<String>();
		
		String[]array = keyword.split("-");
		
		//包含的关键词
		String include = array[0];
		//不能包含的关键词
		String exclude = "";
		
		//没有包含要排除的关键词
		if(array.length>1){
			for(int i=1; i<array.length; i++){
				String kw = array[i];
				
				int pos = kw.indexOf("+");
				if(pos>-1){
					exclude = exclude + "-" + kw.substring(0, pos);
					include = include + "+" + kw.substring(pos+1);
				}else{
					exclude = exclude + "-" + kw;
				}
			}
		}
		
		include = include.replaceAll("\\++", "+");
		exclude = exclude.replaceAll("-+", "-");
		
		while(exclude.startsWith("-")){
			exclude = exclude.substring(1);
		}
		
		while(include.startsWith("+")){
			include = include.substring(1);
		}
		
		while(include.endsWith("+")){
			include = include.substring(0, include.length()-1);
		}
		
		//vector第一个内容是要包含的关键词，第二个内容是要排除的关键词
		result.add(include);
		result.add(exclude);
		
		return result;
	}	
		

	static public String dateToString(Date time, String pattern) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(time);
		return calendarToString(calendar, pattern);
	}

	static public String dateToString(Date time) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(time);
		return calendarToString(calendar, "yyyy-MM-dd HH:mm:ss");
	}
	
	//此方法计算时间毫秒
	public static long fromDateStringToLong(String inVal) { 
		Date date = null;   //定义时间类型       
		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		try { 
			date = inputFormat.parse(inVal); //将字符型转换成日期型
		} catch (Exception e) { 
			e.printStackTrace(); 
		} 
		return date.getTime();   //返回毫秒数
	} 
	  
	public static Date stringToDate(String inVal) { 
		Date date = null;   //定义时间类型       
		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		try { 
			date = inputFormat.parse(inVal); //将字符型转换成日期型
		} catch (Exception e) { 
			e.printStackTrace(); 
		} 
		return date;   //返回日期
	} 	

	//返回指定天数之前的日期串
	public static String getLastYear() {
		String now = C.getDate(365);
		String []array = now.split("-");
		String lastyear = array[0]+"-"+array[1]+"-01";
		return lastyear;
	}
	
	//返回指定天数之前的日期串
	public static String getDate(int days) {
		//找到指定天数之前的起始时间和终止时间
		Date date = new Date(new Date().getTime()-days * 24 * 3600l * 1000);
		String time = C.dateToString(date, "yyyy-MM-dd 00:00:00");
		if(days==1){
			time = C.dateToString(date, "yyyy-MM-dd HH:mm:ss");
		}else if(days==0){
			time = C.dateToString(new Date(), "yyyy-MM-dd 00:00:00");
		}
		return time;
	}
	
	//返回指定日期的指定天数之前的日期串
	public static String getDate(Date date, int days) {
		//找到指定天数之前的起始时间和终止时间
		Date d = new Date(date.getTime()-days * 24 * 3600l * 1000);
		return C.dateToString(d, "yyyy-MM-dd 00:00:00");
	}	
	
	//返回指定天数之前的月份的第一天
	public static String getMonthStart(int days) {
		//找到指定天数之前的起始时间和终止时间
		Date date = new Date(new Date().getTime()-days * 24 * 3600l * 1000);
		return C.dateToString(date, "yyyy-MM-01");
	}
	
	//返回指定日期的开始时间
	public static String getDateStart(Date date) {
		return C.dateToString(date, "yyyy-MM-dd 00:00:00");
	}	

	//返回指定日期的结束时间
	public static String getDateEnd(Date date) {
		return C.dateToString(date, "yyyy-MM-dd 23:59:59");
	}	
	
	
	//判断字符串是否是数字
	public static boolean isNumeric(String str){
		Pattern pattern = Pattern.compile("[-0-9\\.]*");
		Matcher isNum = pattern.matcher(str);
		if( !isNum.matches() )
		{
			return false;
		}
		return true;
	} 
	

	//针对 从js传来的 中文字符和特殊字符进行转码，从js 传来的字符 默认是按照 iso-8859-1 进行编码 要将其 转为 utf-8
	public static String ISO2UTF(String str){
		String val = "";
		try {
			val = new String(str.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return val;
	}
	
	//提取URL中的汉字
	public static String getURLChinese(String str){
		/*String result = str;
		try{
			result = URLDecoder.decode(new String(str.getBytes("ISO8859-1"),"UTF-8"), "UTF-8");
		}catch(Exception e){}
		return result;
		*/
		return str;
	}
	
	// 按字节截取字符串
	public static String subStr(String str, int width) {
		String ellipsis = "...";
		if (str == null) {
			return "";
		}
		int d = 0; // byte length
		int n = 0; // char length
		int c = 0; // 带有省略符号时需要截取的字符个数
		while (n < str.length()) {
			d = (int) str.charAt(n) > 256 ? d + 2 : d + 1;
			if (d > width - ellipsis.length() && c == 0) {
				c = n;
			}
			if (d > width) {
				break;
			} else {
				n++;
			}
		}
		if (d > width) {
			str = str.substring(0, c);
			str += ellipsis;
		} else {
			str = str.substring(0, n);
		}
		return str;
	}	
	// 按字节截取字符串
	public static String subStr(String str, int width, String ellipsis) {
		if (str == null) {
			return "";
		}
		int d = 0; // byte length
		int n = 0; // char length
		int c = 0; // 带有省略符号时需要截取的字符个数
		while (n < str.length()) {
			d = (int) str.charAt(n) > 256 ? d + 2 : d + 1;
			if (d > width - ellipsis.length() && c == 0) {
				c = n;
			}
			if (d > width) {
				break;
			} else {
				n++;
			}
		}
		if (d > width) {
			str = str.substring(0, c);
			str += ellipsis;
		} else {
			str = str.substring(0, n);
		}
		return str;
	}
	
	//缓存处理
	static public void saveToCache(String key, Object object, String[]group){
		CacheManager cache = CacheManager.getInstance();
		// 保存到缓存中
		cache.putInCache(key, object, group);
		//System.out.println("Cache Save:"+key);
	}
	
	static public void saveToCache(String key, Object object){
		CacheManager cache = CacheManager.getInstance();
		// 保存到缓存中
		cache.putInCache(key, object);
		//System.out.println("Cache Save:"+key);
	}
	
	static public Object getFromCache(String key, Integer life){
		CacheManager cache = CacheManager.getInstance();
		Object object = null;
		try {
			//从缓存中提取数据
			object  = cache.getFromCache(key, life);
			//System.out.println("Cache Hit:"+key);
		}catch (NeedsRefreshException nre) {
			//缓存已失效
			object = null;
			//System.out.println("Cache Miss:"+key);
		}
		return object;
	}
	
	static public void saveToApplication(String key, Object object){
		Map app = ServletActionContext.getContext().getApplication();
		app.put(key, object);
		app.put(key+"_tstamp", new Date());
	}
	
	static public Object getFromApplication(String key){
		Map app = ServletActionContext.getContext().getApplication();
		return app.get(key);
	}

	static public Object getFromApplication(String key, int seconds){
		Map app = ServletActionContext.getContext().getApplication();
		Date time = (Date)app.get(key+"_tstamp");
		if(time==null||(new Date().getTime()-time.getTime())>seconds*1000){
			return null;
		}else {
			return app.get(key);
		}
	}
	
	static public Object getFromCache(String key){
		CacheManager cache = CacheManager.getInstance();
		Object object = null;
		try {
			//从缓存中提取数据
			object  = cache.getFromCache(key);
			//System.out.println("Cache Hit:"+key);
		}catch (NeedsRefreshException nre) {
			//缓存已失效
			object = null;
			//System.out.println("Cache Miss:"+key);
		}
		return object;
	}	
	
	//清理缓存
	static public void flushGroup(String group){
		CacheManager cache = CacheManager.getInstance();
		cache.flushGroup(group);
		//System.out.println("Group Cache Flush:"+group);
	}
	static public void flushEntry(String key){
		CacheManager cache = CacheManager.getInstance();
		cache.flushEntry(key);
		//System.out.println("Cache Flush:"+key);
	}

	public static String getContentFromURL(String inurl) {
		if(inurl.indexOf("https:")>-1){
			return C.getContentFromHttpsURL(inurl);
		}
		
		// 此方法只能用于HTTP协议
		String retVal = "";
		String line = "";
		InputStream inputStream = null;

		if(inurl!=null&&inurl.trim().indexOf("http://")==0){
			try {
				URL url = new URL(inurl);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				
				connection.setRequestMethod("GET"); // 设置请求的方法
				// 设置HTTP请求头信息
				connection.setConnectTimeout(300000);
				connection.setReadTimeout(300000);
				connection.setRequestProperty("accept", "*/*");
				connection.setRequestProperty("connection", "Keep-Alive");
				connection.setRequestProperty("user-agent",	"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
				connection.setDoInput(true);
				
				//判断返回代码
				if(connection.getResponseCode()!=200){
					return "error";
				}
				
				String encoding = connection.getContentEncoding();
				
				if (encoding != null && encoding.equalsIgnoreCase("gzip")) {
					  inputStream = new GZIPInputStream(connection.getInputStream());
				}
				else if (encoding != null && encoding.equalsIgnoreCase("deflate")) {
					  inputStream = new InflaterInputStream(connection.getInputStream(), new Inflater(true));
				}
				else {
					  inputStream = connection.getInputStream();
				}

				
				ByteArrayOutputStream baos = new ByteArrayOutputStream();  
		        byte[] buffer = new byte[1024];    
		        int len;    
		        try {  
		            while ((len = inputStream.read(buffer)) > -1 ) {    
		                baos.write(buffer, 0, len);    
		            }  
		            baos.flush();  
		        } catch (IOException e) {  
		            e.printStackTrace();  
		        } 
				
				BufferedReader in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(baos.toByteArray()), "UTF-8"));
				while ((line = in.readLine()) != null) {
					retVal += line + "\r\n";
				}
				in.close();
				
				// 检查实际的编码
				Pattern pattern = Pattern.compile("<\\s*meta.*charset\\s*=\\s*(.*)", Pattern.CASE_INSENSITIVE);
				Matcher matched = pattern.matcher(retVal);
				String charset = matched.find() ? matched.group(1) : "none";

				// 如果页面实际编码为gbk，则重新按照gbk抓取
				if (!charset.equals("none")&&!charset.toLowerCase().contains("utf")){
					retVal = "";
					in = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(baos.toByteArray()), "GBK"));
					while ((line = in.readLine()) != null) {
						retVal += line + "\r\n";
					}
					in.close();
				}				
			} catch (Exception e) {
				e.printStackTrace();
				return "error";
			}
		}else{
			return "error";
		}
		
		return retVal;
	}

	public static String getContentFromHttpsURL(String inurl){
		//建立连接
		URL url = null;
		String response = "";
		try {
			url = new URL(inurl);

			HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();
			// 创建SSLContext对象，并使用我们指定的信任管理器初始化
			TrustManager[] tm = {(TrustManager) new MyX509TrustManager()};
			SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");
			sslContext.init(null, tm, new java.security.SecureRandom());
			// 从上述SSLContext对象中得到SSLSocketFactory对象
			SSLSocketFactory ssf = sslContext.getSocketFactory();
			httpUrlConn.setSSLSocketFactory(ssf);
			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod("GET");
			// 取得输入流
			InputStream inputStream = httpUrlConn.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			//读取响应内容
			StringBuffer buffer = new StringBuffer();
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			httpUrlConn.disconnect();
			//输出返回结果
			//System.out.println(buffer);
			response = buffer.toString();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	public static String getContentFromURLWithoutRedirect(String inurl) {
        String retVal = "";
		BasicCookieStore cookieStore = new BasicCookieStore();
		
		//缓存保存cookie内容
		String key = "cookie";		
		Object cache = C.getFromCache(key, 3600*30*24);
		if(cache!=null){
			cookieStore = (BasicCookieStore)cache;
		}
		
        CloseableHttpClient httpclient = HttpClients.custom().setDefaultCookieStore(cookieStore).setRedirectStrategy(new RedirectStrategy() {	//设置重定向处理方式

			public boolean isRedirected(HttpRequest arg0, HttpResponse arg1, HttpContext arg2) throws ProtocolException {
				return false;
			}

			public HttpUriRequest getRedirect(HttpRequest arg0,	HttpResponse arg1, HttpContext arg2) throws ProtocolException {
				return null;
			}
		}).build();
        try {
            HttpGet httpget = new HttpGet(inurl);
            httpget.addHeader("Referer", inurl);
            httpget.addHeader("Connection", "keep-alive");
            httpget.addHeader("Cache-Control", "max-age=0");
            httpget.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            httpget.addHeader("User-Agent", "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.1; WOW64; Trident/4.0; SLCC2; .NET CLR 2.0.50727; .NET CLR 3.5.30729; .NET CLR 3.0.30729; Media Center PC 6.0; .NET4.0C)");
            httpget.addHeader("Accept-Encoding", "gzip, deflate, sdch");
            httpget.addHeader("Accept-Language","zh-CN,zh;q=0.8");
            
        	
            CloseableHttpResponse response = httpclient.execute(httpget);
            try {
            	int statusCode = response.getStatusLine().getStatusCode();
            	if(statusCode==HttpStatus.SC_OK){
	                HttpEntity entity = response.getEntity();
	                retVal = EntityUtils.toString(entity);
	
	                System.out.println(new Date()+":Get URL "+inurl);
            	}else if(statusCode == HttpStatus.SC_MOVED_TEMPORARILY || statusCode == HttpStatus.SC_MOVED_PERMANENTLY){
            		Header[] headers = response.getHeaders("Location");
    				if(headers!=null && headers.length>0){
    					String redirectUrl = headers[0].getValue();
    					if(!redirectUrl.startsWith("http://")){
    						String domain = inurl.substring(0, inurl.indexOf("/", 8));
    						redirectUrl = domain + redirectUrl;
    					}
    					
    					redirectUrl = C.getURLChinese(redirectUrl);
    					
    		            char[] ch = redirectUrl.toCharArray(); 
    		            String url = "";
    		            for (int i = 0; i < ch.length; i++) { 
    		                char c = ch[i];
    		                if(CharUtil.isChinese(c)){
    		                	url += URLEncoder.encode(String.valueOf(c), "utf-8");
    		                }else{
    		                	url += c;
    		                }
    		            } 
    					//System.out.println("重定向的URL:"+url);
    		            retVal = getContentFromURL(url);
    				}
            	}
                /*List<org.apache.http.cookie.Cookie> cookies = cookieStore.getCookies();
                if (cookies.isEmpty()) {
                    System.out.println("None");
                } else {
                    for (int i = 0; i < cookies.size(); i++) {
                        System.out.println("- " + cookies.get(i).toString());
                    }
                }*/
            }catch(Exception e){
            	e.printStackTrace();
            } finally {
                response.close();
            }
            
        }catch(Exception e){
        	e.printStackTrace();
        }finally {
        	try{
        		httpclient.close();
        	}catch(Exception e){
        		e.printStackTrace();
        	}
        }
        
        C.saveToCache(key, cookieStore);
        return retVal;
	}
	
	
	public static String postContentToURL(String inurl, HashMap para, String referer) {
        String retVal = "";
		BasicCookieStore cookieStore = new BasicCookieStore();
		
		//缓存保存cookie内容
		String cachekey = "cookie";		
		Object cache = C.getFromCache(cachekey, 3600*30*24);
		if(cache!=null){
			cookieStore = (BasicCookieStore)cache;
		}
		
        CloseableHttpClient httpclient = HttpClients.custom().setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.89 Safari/537.36").setDefaultCookieStore(cookieStore).build();

        try {
        	RequestBuilder builder = RequestBuilder.post().setUri(new URI(inurl));
        	Iterator iterator = para.keySet().iterator();
        	while(iterator.hasNext()) {
        		String key = iterator.next().toString();
        		String value = para.get(key).toString();
        		builder.addParameter(key, value);
        	}
        	

        	builder.addHeader("Referer", referer);
        	builder.addHeader("Connection", "keep-alive");
        	builder.addHeader("Cache-Control", "max-age=0");
        	builder.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
        	builder.addHeader("Accept-Encoding", "gzip, deflate, sdch");
        	builder.addHeader("Accept-Language","zh-CN,zh;q=0.8");
        	
        	HttpUriRequest login = builder.build();
        	CloseableHttpResponse response = httpclient.execute(login);
		    try {
		    	int statusCode = response.getStatusLine().getStatusCode();
	            
	 
	            if(statusCode==302){
	            	Header locationHeader = response.getFirstHeader("Location");
	            	if (locationHeader != null) {
	            		String location = locationHeader.getValue();
	            		C.getContentFromURL(location);
	            	}
	            }else if(statusCode==200){
	            	HttpEntity entity = response.getEntity();
	                retVal = EntityUtils.toString(entity);
	            }
		
		        System.out.println(new Date()+": Post url "+inurl);
		        
		        /*List<org.apache.http.cookie.Cookie> cookies = cookieStore.getCookies();
		        if (cookies.isEmpty()) {
		            System.out.println("None");
		        } else {
		            for (int i = 0; i < cookies.size(); i++) {
		                System.out.println("- " + cookies.get(i).toString());
		            }
		        }*/
		    }catch(Exception e){
		    	e.printStackTrace();
		    } finally {
		    	response.close();
		    }
		            
        }catch(Exception e){
        	e.printStackTrace();
        }finally {
        	try{
        		httpclient.close();
        	}catch(Exception e){
        		e.printStackTrace();
        	}
        }
        
        C.saveToCache(cachekey, cookieStore);
        return retVal;
	}	
	
	//去除html标签
	static public String filterHTML(String str){
		return str.replaceAll("</?[^<]+>", "");
	}
	
	// 提取主域名部分
	public static String httpsplit(String s) {
		String result = "";
		//末尾添加/，以处理类似于  http://www.dig24.cn 这样的url
		s = s + "/";
		Pattern pattern = Pattern.compile(
				"\\s*http://[^/]*\\.([^\\.]*)\\.(com|net|org|gov)\\.(\\w*)/",
				Pattern.CASE_INSENSITIVE);
		Matcher matched = pattern.matcher(s);
		if (matched.find()) {
			result = matched.group(1) + "." + matched.group(2) + "."
					+ matched.group(3);
		} else {
			pattern = Pattern
					.compile(
							"\\s*http://[^/]*\\.([^\\.]*)\\.(com|cn|net|gov|mobi|org|tv|biz|cc|name|info)/",
							Pattern.CASE_INSENSITIVE);
			matched = pattern.matcher(s);
			if (matched.find()) {
				result = matched.group(1) + "." + matched.group(2);
			}
		}
		
		if(result.length()<3){
			pattern = Pattern.compile("http://([^/]*)/",
					Pattern.CASE_INSENSITIVE);
			matched = pattern.matcher(s);
			if (matched.find()) {
				result = matched.group(1);
			}
		}		
		return result;
	}
	
	public static String getContentFromURL(String inurl, String charset) {
		// 此方法只能用于HTTP协议
		String retVal = "";
		String line = "";
		InputStream inputStream = null;

		if(inurl!=null&&inurl.trim().indexOf("http://")==0){
			try {
				URL url = new URL(inurl);
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				
				connection.setRequestMethod("GET"); // 设置请求的方法
				// 设置HTTP请求头信息
				connection.setRequestProperty("accept", "*/*");
				connection.setRequestProperty("connection", "Keep-Alive");
				connection.setRequestProperty("user-agent",	"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
				connection.setDoInput(true);
				
				inputStream = connection.getInputStream();
				
				String encoding = connection.getContentEncoding();
				if (encoding != null && encoding.equalsIgnoreCase("gzip")) {
					  inputStream = new GZIPInputStream(connection.getInputStream());
				}
				else if (encoding != null && encoding.equalsIgnoreCase("deflate")) {
					  inputStream = new InflaterInputStream(connection.getInputStream(), new Inflater(true));
				}
				else {
					  inputStream = connection.getInputStream();
				}
				BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, charset));
				while ((line = in.readLine()) != null) {
					retVal += line + "\r\n";
				}
				in.close();
				
			} catch (Exception e) {
				e.printStackTrace();
				return "error";
			}
		}else{
			return "error";
		}
		
		return retVal;
	}
	
	//处理上传图片，移到指定目录，产生对应的文件名，返回url
	static public String handleUpload(Integer userid, String module, String filename, File file){
		String imgurl = null;
		if (file != null) {
			
			// 目录按照月份分层，如2009/05/,以减少单个目录下文件的数量
			String path = "/upload/"+module+"/"+C.dateToString(new Date(), "yyyy") + "/" + C.dateToString(new Date(), "MM") + "/";

			// 得到目录的物理路径并创建目录
			String absolute = ServletActionContext.getServletContext().getRealPath(path);
			File absolutePath = new File(absolute);
			if (!absolutePath.isDirectory()) {
				try {
						absolutePath.mkdirs();
					} catch (SecurityException e) {
						e.printStackTrace();
					}
			}

			//上传后的文件名称为 用户id_随机数.扩展名，如1000_6688.jpg
			
			String rand = String.valueOf(new Date().getTime());
			rand = rand.substring(4, 10);
			
			//扩展名
			String ext = filename.substring(filename.lastIndexOf('.'));

			String newname = userid	+ "_" + rand + ext;

			// 原始文件
			File newFile = new File(absolutePath, newname);
			
			try
			{
				//将临时目录下的上传文件拷贝到指定位置
				FileUtils.copyFile(file, newFile);
			}catch(Exception e)
			{
				e.printStackTrace();
			}

			//得到图片的以根开始的相对路径			
			imgurl = ".." + path + newFile.getName();
		}
		return imgurl;
	}	
	
	// 提取主域名部分
	public static String getDomainName(String s) {
		String result = "";
		//末尾添加/，以处理类似于  http://www.dig24.cn 这样的url
		s = s + "/";
		Pattern pattern = Pattern.compile(
				"\\s*http://[^/]*\\.([^\\.]*)\\.(com|net|org|gov)\\.(\\w*)/",
				Pattern.CASE_INSENSITIVE);
		Matcher matched = pattern.matcher(s);
		if (matched.find()) {
			result = matched.group(1) + "." + matched.group(2) + "."
					+ matched.group(3);
		} else {
			pattern = Pattern
					.compile(
							"\\s*http://[^/]*\\.([^\\.]*)\\.(com|cn|net|gov|mobi|org|tv|biz|cc|name|info)/",
							Pattern.CASE_INSENSITIVE);
			matched = pattern.matcher(s);
			if (matched.find()) {
				result = matched.group(1) + "." + matched.group(2);
			}
		}
		
		if(result.length()<3){
			pattern = Pattern.compile("http://([^/]*)/",
					Pattern.CASE_INSENSITIVE);
			matched = pattern.matcher(s);
			if (matched.find()) {
				result = matched.group(1);
			}
		}		
		return result;
	}
	
	//将远端的图片保存在本地
	public static boolean saveImageURLAs(String imageurl, File filename){
		try {   
			URL url = new URL(imageurl);  
			Image src = javax.imageio.ImageIO.read(url); // 构造Image对象  
			int srcwidth = src.getWidth(null); // 得到源图宽  
			int srcheight = src.getHeight(null); // 得到源图长  
			
			BufferedImage tag = new BufferedImage(srcwidth, srcheight, BufferedImage.TYPE_INT_RGB);
			tag.getGraphics().drawImage(src,0,0,srcwidth, srcheight, 0, 0, srcwidth, srcheight, null);
			//构造文件名  
			FileOutputStream out = new FileOutputStream(filename);  
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
			JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(tag);
			param.setQuality(0.75f, false);// 质量
			encoder.setJPEGEncodeParam(param);
			encoder.encode(tag); // 近JPEG编码
			out.close();
		} catch (Exception e) {  
			//System.out.println("Error fetching image "+imageurl);
			return false;
		}
		return true;
	}
	
	static public String getEncodeImageURL(String url){
		if(url==null||url.length()<5)
			return "";
		String encoded = C.MD5(url);
		
		//增加多级目录，避免一个目录下文件太多
		String pathname = "/images/wechat/" + encoded.substring(0,2) + "/" + encoded.substring(2,4) + "/" + encoded.substring(4,6);

		//如果传进来的url扩展名不是图片扩展名，则使用jpg扩展名
		String theUrl = url.toLowerCase();
		String ext = ".jpg";
		if(theUrl.endsWith(".gif") || theUrl.endsWith(".jpg") || theUrl.endsWith(".png")){
			ext = url.substring(url.lastIndexOf("."));
		}
		
		String fullname = pathname + "/" + encoded + ext;
		return fullname;
	}
	
	//图片下载到本地
	public static String showimage(String url){
		String link = url;
		String encodedname = C.getEncodeImageURL(url);
		
		//增加二级目录，避免一个目录下文件太多
		String pathname = encodedname.substring(0,encodedname.lastIndexOf("/"));
	
		File path = new File(ServletActionContext.getServletContext().getRealPath(pathname));
		if (!path.isDirectory()) {
			try {
				path.mkdirs();
			} catch (SecurityException e) {
				e.printStackTrace();
			}
		}
	

		File file = new File(ServletActionContext.getServletContext().getRealPath(encodedname));
		if(!file.exists()){
		    //如果没有下载，则进行下载操作，避免多个进程同时下载同一个链接
		    if(C.getFromApplication(url)==null) {
                //下载图片
                C.saveImageURLAs(url, file);
                //将下载链接存入应用的对象中
                C.saveToApplication(url, 1);
            }
			link = encodedname+"?ord="+new Date().getTime();
		}else{
			link = encodedname;
		}
		return link;
	}	

	static public void pause(int mill){
		//暂停一定的时间
		try{
			Thread.sleep(mill);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//根据ID返回省份名称
	public static String id2province(Integer id){
		HashMap<Integer, Province> hash = new HashMap<Integer, Province>();
		String key = "provid_hash";
		
		Object obj = C.getFromCache(key);
		if(obj!=null){
			hash = (HashMap<Integer, Province>)obj;
			Province prov = hash.get(id);
			if(prov ==null){
				return null;
			}else{
				return prov.getName();
			}
		}else{
			return null;
		}
	}
	
	//返回按地区的网民数量比例
	public static HashMap<String, Double> userRatio(){
		String key = "user_ratio";
		HashMap<String, Double> hash = (HashMap<String, Double>)C.getFromApplication(key);
		if(hash==null){
			hash = new HashMap<String, Double>();
			hash.put("广东", 13.06);
			hash.put("江苏", 7.52);
			hash.put("浙江", 6.99);
			hash.put("山东", 6.89);
			hash.put("河南", 5.55);
			hash.put("河北", 4.82);
			hash.put("北京", 4.93);
			hash.put("福建", 3.57);
			hash.put("四川", 4.07);
			hash.put("上海", 3.5);
			hash.put("湖南", 3.39);
			hash.put("安徽", 3.1);
			hash.put("湖北", 3.33);
			hash.put("辽宁", 3.3);
			hash.put("广西", 2.54);
			hash.put("山西", 2.54);
			hash.put("江西", 2.25);
			hash.put("陕西", 2.23);
			hash.put("黑龙江", 2.14);
			hash.put("天津", 1.9);		
			hash.put("重庆", 1.77);
			hash.put("吉林", 1.58);
			hash.put("云南", 1.68);
			hash.put("内蒙古", 1.36);
			hash.put("贵州", 1.31);
			hash.put("新疆", 1.1);
			hash.put("台湾", 1.01);
			hash.put("甘肃", 0.95);
			hash.put("海南", 0.54);
			hash.put("香港", 0.41);	
			hash.put("宁夏", 0.33);			
			hash.put("青海", 0.22);			
			hash.put("西藏", 0.07);			
			hash.put("澳门", 0.04);
			C.saveToApplication(key, hash);
		}
		return hash;
	}
	
	//返回省份列表
	public static List<String> provincelist(){
		String key = "province_list";
		List<String> list = (List<String>)C.getFromApplication(key);
		if(list==null){
			list = new ArrayList<String>();
			list.add("广东");
			list.add("江苏");
			list.add("浙江");
			list.add("山东");
			list.add("河南");
			list.add("河北");
			list.add("北京");
			list.add("福建");
			list.add("四川");
			list.add("上海");
			list.add("湖南");
			list.add("安徽");
			list.add("湖北");
			list.add("辽宁");
			list.add("广西");
			list.add("山西");
			list.add("江西");
			list.add("陕西");
			list.add("黑龙江");
			list.add("天津");		
			list.add("重庆");
			list.add("吉林");
			list.add("云南");
			list.add("内蒙古");
			list.add("贵州");
			list.add("新疆");
			list.add("台湾");
			list.add("甘肃");
			list.add("海南");
			list.add("香港");	
			list.add("宁夏");			
			list.add("青海");			
			list.add("西藏");			
			list.add("澳门");
			C.saveToApplication(key, list);
		}
		return list;
	}

	public static final String wxAccessToken(){
		String link = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+C.APPID+"&secret="+C.APPSECRET;
		String result = C.getContentFromHttpsURL(link);
		String token = "";
		if(result.trim().length()>10) {
			try {
				HashMap<String, String> obj = (HashMap<String, String>) JSONUtil.deserialize(result);
				token = obj.get("access_token");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return token;
	}

	public static final String wxJsapiTicket(){
		Object ticket = C.getFromApplication("jsapiticket", 7200);
		if(ticket==null) {
			String token = C.wxAccessToken();
			String link = "https://api.weixin.qq.com/cgi-bin/ticket/getticket?type=jsapi&access_token=" + token;
			String result = C.getContentFromHttpsURL(link);
			if(result.trim().length()>10) {
				try {
					HashMap<String, String> obj = (HashMap<String, String>) JSONUtil.deserialize(result);
					ticket = obj.get("ticket");
					C.saveToApplication("jsapiticket", ticket);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		if(ticket!=null)
			return ticket.toString();
		else
			return null;
	}

	public static Map<String, String> sign(String jsapi_ticket, String url) {
		Map<String, String> ret = new HashMap<String, String>();
		String nonce_str = create_nonce_str();
		String timestamp = create_timestamp();
		String string1;
		String signature = "";

		//注意这里参数名必须全部小写，且必须有序
		string1 = "jsapi_ticket=" + jsapi_ticket +
				"&noncestr=" + nonce_str +
				"&timestamp=" + timestamp +
				"&url=" + url;
		//System.out.println(string1);

		try
		{
			MessageDigest crypt = MessageDigest.getInstance("SHA-1");
			crypt.reset();
			crypt.update(string1.getBytes("UTF-8"));
			signature = byteToHex(crypt.digest());
		}
		catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}

		ret.put("url", url);
		ret.put("jsapi_ticket", jsapi_ticket);
		ret.put("nonceStr", nonce_str);
		ret.put("timestamp", timestamp);
		ret.put("signature", signature);

		return ret;
	}

	private static String byteToHex(final byte[] hash) {
		Formatter formatter = new Formatter();
		for (byte b : hash)
		{
			formatter.format("%02x", b);
		}
		String result = formatter.toString();
		formatter.close();
		return result;
	}

	private static String create_nonce_str() {
		return UUID.randomUUID().toString();
	}

	private static String create_timestamp() {
		return Long.toString(System.currentTimeMillis() / 1000);
	}

	//根据粉丝数获取粉丝数的范围
	public static HashMap<Integer, String> getFansRangeHash(){
		HashMap<Integer, String> hash = new HashMap<Integer, String>();
		//判断粉丝的范围
		for(int i=0; i<C.FANSRANGE.length; i++){
			String range = C.FANSRANGE[i]+"+";
			if(i<C.FANSRANGE.length-1) {
				range = C.FANSRANGE[i] + "-" + C.FANSRANGE[i + 1];
			}
			hash.put(C.FANSRANGE[i], range);
		}
		return hash;
	}
}
