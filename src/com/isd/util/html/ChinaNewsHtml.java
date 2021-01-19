package com.isd.util.html;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.struts2.json.JSONUtil;

public class ChinaNewsHtml extends HtmlBase{

	public ChinaNewsHtml(){
		super();
		domain = "chinanews.com";
	}
	
	public ChinaNewsHtml(String url) {
		super(url);
		// TODO Auto-generated constructor stub
	}

	//提取图片
	public void findSlide(){
		//判断是否内嵌了幻灯
		Pattern pattern = Pattern.compile("var\\s*picsJson\\s*=\\s*(\\{[^;]*\\});",Pattern.CASE_INSENSITIVE);
		Matcher matched = pattern.matcher(html);

		while(matched.find()){
			String info = matched.group(1);

			//提取视频代码和图片
			try{
				HashMap<String,Object> obj = (HashMap<String,Object>)JSONUtil.deserialize(info);

				ArrayList  items = (ArrayList)obj.get("pics"); 
				for(int i=0; i<items.size(); i++){
					obj = (HashMap<String, Object>)items.get(i);
					
					String text = obj.get("articleTitle").toString();
					String link = "http://www.chinanews.com"+obj.get("bigPicUrl").toString().replace("200x145_", "");
					
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
