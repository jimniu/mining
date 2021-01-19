package com.isd.util.html;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ChinaHtml extends HtmlBase{

	
	public ChinaHtml(){
		super();
		domain = "china.com";
	}
	
	public ChinaHtml(String url) {
		super(url);
		// TODO Auto-generated constructor stub
	}

	//提取图片
	public void findSlide(){
		Elements elements = doc.select("ul#galleryViewList li");
		if(elements.size()>0){
			for (Element element : elements){
				String link = element.child(0).attr("href");
				String text = element.child(0).child(0).attr("name");

				body += "<div class=\"slide\">";
				body += "<center><img src='"+link+"'></center>";
				body += "<p>&nbsp;&nbsp;&nbsp;&nbsp;"+text+"</p><br/><br/>";
				body += "</div>";
				
				image += link+";";					

			}
			return;
		}
		
		elements = doc.select("#chan_galleryHD");
		if(elements.size()>0){
			body = doc.select("#chan_newsDetail").text();
			elements = doc.select("ul#smallPicList li");
			
			for (Element element : elements){
				String text = element.child(0).attr("alt");
				String link = element.child(0).child(0).attr("src").replace("small_", "");

				body += "<div class=\"slide\">";
				body += "<center><img src='"+link+"'></center>";
				body += "<p>&nbsp;&nbsp;&nbsp;&nbsp;"+text+"</p><br/><br/>";
				body += "</div>";

				image += link+";";					
			}
			return;
		}
	}
}
