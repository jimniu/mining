package com.isd.util.html;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.struts2.json.JSONUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.isd.util.C;

public class IfengHtml extends HtmlBase{
	public IfengHtml() {
		super();
		// TODO Auto-generated constructor stub
		domain = "ifeng.com";
	}
	
	public IfengHtml(String url) {
		super(url);
		// TODO Auto-generated constructor stub
	}

	//提取图片
	public void findSlide(){
		if(body.length()==0){
			this.slide2();	
		}
		if(body.length()==0){
			this.slide3();	
		}
		if(body.length()==0){
			this.slide1();	
		}
	
	}
	
	//提取幻灯片
	private boolean slide1(){
		//判断是否内嵌了幻灯，比如：http://sports.ifeng.com/photo/saishi/detail_2012_11/25/19507275_0.shtml#p=1
		Pattern pattern = Pattern.compile("_listdata\\[\\d+\\]\\s*=\\s*(\\{[^\\}]*\\})",Pattern.CASE_INSENSITIVE);
		Matcher matched = pattern.matcher(html);
		String slidebody = "";
		while(matched.find()){
			String info = matched.group(1);

			pattern = Pattern.compile("title\\s*:\\s*'([^']*)',",Pattern.CASE_INSENSITIVE);
			Matcher match = pattern.matcher(info);
			String text = match.find()?match.group(1):"";
			
			pattern = Pattern.compile(",\\s*timg\\s*:\\s*'([^']*)',",Pattern.CASE_INSENSITIVE);
			match = pattern.matcher(info);
			String link = match.find()?match.group(1):"";
			
			//有的页面没有包含timg，只有listimg和img，这时需要针对每一个页面分别去加载并且取图片，
			//http://news.ifeng.com/history/vp/detail_2013_01/28/21677805_0.shtml
			if(link.length()<1){
				//取出页面地址
				pattern = Pattern.compile("pageurl\\s*:\\s*'([^']*)'",Pattern.CASE_INSENSITIVE);
				match = pattern.matcher(info);
				String pageurl = match.find()?match.group(1):"";
				
				String content = C.getContentFromURL(pageurl, "UTF-8");
				Document document = Jsoup.parse(content, pageurl);
				link = document.select("img[id=photo]").attr("src");				
			}
	
			slidebody += "<div class=\"slide\">";
			slidebody += "<center><img src='"+link+"'></center>";
			slidebody += "<p>&nbsp;&nbsp;&nbsp;&nbsp;"+text+"</p><br/><br/>";
			slidebody += "</div>";

			image += link+";";
		}
		
		if(slidebody.length()>0){
			body = slidebody;
			return true;
		}
		return false;
	}
	
	private boolean slide2(){
		//判断是否内嵌了幻灯，比如http://ent.ifeng.com/a/20150830/42483664_0.shtml#p=1
		String slidebody = "";
		Elements elems = doc.select("script");
		for (Element elem : elems) {
			String script = elem.html();
			if(script.indexOf("G_listdata")>-1){
				//提取JSON数据
				script = script.substring(script.indexOf("=")+1).trim();
				script = script.substring(0, script.length()-1);
				script =script.replaceAll("title:", "'title':");
				script =script.replaceAll("big_img:", "'big_img':");
				script =script.replaceAll("originalimg:", "'originalimg':");
				script =script.replaceAll("img:", "'img':");
				script =script.replaceAll("picwidth:", "'picwidth':");
				script =script.replaceAll("picheight:", "'picheight':");
				script =script.replaceAll("morelink:", "'morelink':");
				script =script.replaceAll("\r\n", "");
				
				//提取视频代码和图片
				try{
					ArrayList items = (ArrayList)JSONUtil.deserialize(script);

					for(int i=0; i<items.size(); i++){
						HashMap<String,Object> obj = (HashMap<String, Object>)items.get(i);
						
						String text = obj.get("title").toString();
						String link = obj.get("big_img").toString();
						
						slidebody += "<div class=\"slide\">";
						slidebody += "<center><img src='"+link+"'></center>";
						slidebody += "<p>&nbsp;&nbsp;&nbsp;&nbsp;"+text+"</p><br/><br/>";
						slidebody += "</div>";
						
						image += link+";";							
					}
				}catch(Exception e){
					e.printStackTrace();
				}				
			}
		}
		
		if(slidebody.length()>0){
			body = slidebody;
			return true;
		}		
		return false;
	}
	
	
	//在人间频道
	private boolean slide3(){
		//判断是否内嵌了幻灯，比如http://news.ifeng.com/a/20150821/44483456_0.shtml
		String slidebody = "";
		Elements elems = doc.select("script");
		for (Element elem : elems) {
			String script = elem.html();
			if(script.indexOf("scrollImgs")>-1){
				Pattern pattern = Pattern.compile("scrollImgs\\[\\d+\\]\\s*=\\s*(\\{[^\\}]*\\})",Pattern.CASE_INSENSITIVE);
				Matcher matched = pattern.matcher(script);
				while(matched.find()){
					String info = matched.group(1);

					pattern = Pattern.compile("describe\\s*:\\s*''\\+'([^']*)',",Pattern.CASE_INSENSITIVE);
					Matcher match = pattern.matcher(info);
					String text = match.find()?match.group(1):"";
					
					pattern = Pattern.compile("src\\s*:\\s*'([^']*)',",Pattern.CASE_INSENSITIVE);
					match = pattern.matcher(info);
					String link = match.find()?match.group(1):"";
			
					slidebody += "<div class=\"slide\">";
					slidebody += "<center><img src='"+link+"'></center>";
					slidebody += "<p>&nbsp;&nbsp;&nbsp;&nbsp;"+text+"</p><br/><br/>";
					slidebody += "</div>";

					image += link+";";
				}
				
			}
		}
		
		if(slidebody.length()>0){
			body = slidebody;
			return true;
		}		
		return false;
	}
		
	
}
