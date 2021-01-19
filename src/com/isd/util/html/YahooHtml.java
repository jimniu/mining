package com.isd.util.html;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.struts2.json.JSONUtil;

public class YahooHtml extends HtmlBase{
	public YahooHtml() {
		super();
		// TODO Auto-generated constructor stub
		domain = "yahoo.com";
	}
	
	public YahooHtml(String url) {
		super(url);
		// TODO Auto-generated constructor stub
	}
	
	//提取图片
	public void findSlide(){
		Pattern pattern = Pattern.compile("data=([^;]*)",Pattern.CASE_INSENSITIVE);
		Matcher matched = pattern.matcher(html);
		String code = matched.find() ? matched.group(1) : "";
		if(code.length()>0){
			try{
				List<HashMap<String,HashMap>> obj = (List<HashMap<String,HashMap>>)JSONUtil.deserialize(code);
				body = "";
				for(int i = 0; i < obj.size(); i++){
					String link = obj.get(i).get("orgl").get("src").toString();
					String text = obj.get(i).get("desc").toString();
					
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
	
}
