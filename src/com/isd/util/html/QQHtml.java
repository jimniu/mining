package com.isd.util.html;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.struts2.json.JSONUtil;
import org.jsoup.select.Elements;

import com.isd.util.C;

public class QQHtml extends HtmlBase{
	public QQHtml() {
		super();
		// TODO Auto-generated constructor stub
		domain = "qq.com";
	}
	
	public QQHtml(String url) {
		super(url);
		// TODO Auto-generated constructor stub
	}
	
	//提取图片
	public void findSlide(){
		String address = url;
		Elements elements = doc.select("iframe[src~=qq.com/a/\\d*/\\d*\\.htm]");
		if(elements.size()>0){
			address = elements.get(0).attr("src");
		}
		
		//判断是否属于幻灯片，比如：http://astro.lady.qq.com/a/20121127/000040.htm
		Pattern pattern = Pattern.compile("(^.*qq.com/a/\\d*/\\d*)\\.htm",Pattern.CASE_INSENSITIVE);
		Matcher matched = pattern.matcher(address);
		if(matched.find()){
			address = matched.group(1)+".hdBigPic.js";
			String info = C.getContentFromURL(address, "GBK");
			if(info.equals("error")){
				address = matched.group(1)+".hdPic.js";
				info = C.getContentFromURL(address, "GBK");
				super.findBody();
			}
			
			//提取视频代码和图片
			try{
				HashMap<String,Object> obj = (HashMap<String,Object>)JSONUtil.deserialize(info);
				//groupimginfo
				ArrayList  items = (ArrayList)obj.get("Children"); 
				obj = (HashMap<String,Object>)this.getByName(obj, "groupimg");
				
				items = (ArrayList)obj.get("Children");
				for(int i=0; i<items.size(); i++){
					obj = (HashMap<String, Object>)items.get(i);
					ArrayList list = (ArrayList)getByName(obj, "bigimgurl").get("Children");
					String link = ((HashMap<String, Object>)list.get(0)).get("Content").toString();
					list = (ArrayList)getByName(obj, "cnt_article").get("Children");
					String text = ((HashMap<String, Object>)list.get(0)).get("Content").toString();	
					
					body += "<div class=\"slide\">";
					body += "<center><img src='"+link+"'></center>";
					body += "<p>&nbsp;&nbsp;&nbsp;&nbsp;"+text+"</p><br/><br/>";
					body += "</div>";

					image += link+";";					
				}
			}catch(Exception e){
				e.printStackTrace();
			}			
			
		}
	}
	
	private HashMap<String, Object> getByName(HashMap<String, Object> object, String name){
		HashMap<String, Object> obj = object;
		ArrayList items = (ArrayList)object.get("Children");
		for(int i=0; i<items.size(); i++){
			obj = (HashMap<String, Object>)items.get(i);

			if(obj.get("Name").toString().equals(name)){
				break;
			}
			if(obj.get("Children").toString().length()<3){
				continue;
			}else{
				obj = getByName(obj, name);
			}
			if(obj.get("Name").toString().equals(name)){
				break;
			}
		}			
		return obj;
	}
}
