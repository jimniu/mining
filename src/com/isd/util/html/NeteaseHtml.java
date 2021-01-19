package com.isd.util.html;

import java.util.HashMap;
import java.util.List;

import org.apache.struts2.json.JSONUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class NeteaseHtml extends HtmlBase{

	public NeteaseHtml() {
		super();
		// TODO Auto-generated constructor stub
		domain = "163.com";
	}
	
	public NeteaseHtml(String url) {
		super(url);
		// TODO Auto-generated constructor stub
	}

	//提取图片
	public void findSlide(){
		//判断是否是网易幻灯片
		int pos = html.indexOf("photoList");
		if(pos > -1){ //http://edu.163.com/photoview/3Q0N0029/103.html?from=ph_rank#p=6BGAOPO83Q0N0029
			String html = doc.select("#photoList").html();
			//提取textarea中的内容时，jsoup会做转码，所以需要替换回来
			html = html.replaceAll("&lt;", "<");
			html = html.replaceAll("&gt;", ">");
			html = html.replaceAll("&quot;", "\"");
			html = html.replaceAll("</h>", "</h2");
			html = html.replaceAll("\r\n", ">\r\n");
			
			Element element = Jsoup.parseBodyFragment(html);
			Elements nodes = element.select("li");
			body = "";
			for(Element node: nodes){
				String text = node.select("h2").text();
				String link = node.select("i[title=img]").text();
				body += "<div class=\"slide\">";
				body += "<center><img src='"+link+"'></center>";
				body += "<p>&nbsp;&nbsp;&nbsp;&nbsp;"+text+"</p><br/><br/>";
				body += "</div>";

				image += link+";";
			}
			
		}else{ //http://news.163.com/photoview/00AP0001/33604.html#p=8S7ABUDR00AP0001
			Elements data = doc.select("textarea[name=gallery-data]");
			if(data.size()>0){
				String content = data.get(0).text();
				try{
					HashMap<String,List> obj = (HashMap<String,List>)JSONUtil.deserialize(content);
					body = ((HashMap)obj.get("info")).get("prevue").toString()+"<br/><br/>";
					List<List> list = obj.get("list");
					for(int i=0; i<list.size(); i++){
						HashMap info = (HashMap)list.get(i);
						String text = info.get("note").toString();
						String link = info.get("img").toString();
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
}
