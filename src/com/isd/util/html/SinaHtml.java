package com.isd.util.html;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.struts2.json.JSONUtil;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.isd.util.C;

public class SinaHtml extends HtmlBase{
	public SinaHtml() {
		super();
		// TODO Auto-generated constructor stub
		domain = "sina.com.cn";
	}
	
	public SinaHtml(String url) {
		super(url);
		// TODO Auto-generated constructor stub
	}
	
	protected void findTitle(){
		Elements nodes = doc.body().select("#artibodyTitle");
		for (Element node : nodes){
			title = node.html();
		}	
		if(title==null){
			super.findTitle();
		}			
	}
	
	public void findBody(){
		doc.body().select(".artical-player-wrap").remove();
		if(this.url.indexOf("tech.sina.com.cn/geo/pod")>-1){
			//处理国家地理杂志每日一图
			Elements nodes = doc.body().select("#artibody").select("img");
			for (Element node : nodes){
				node.removeAttr("width").removeAttr("height");
				body = node.outerHtml()+"<br/>";
			}
			
			nodes = doc.body().select(".content");
			for (Element node : nodes){
				node.removeAttr("class").removeAttr("style");
				body += node.outerHtml();
			}
			
		}else if(this.url.indexOf("blog.sina.com.cn")>-1){ //处理微博文章
			Elements nodes = doc.body().select(".articalContent, .feedInfo").select("img");
			for (Element node : nodes){
				if(node.attr("real_src").length()>0)
					node.attr("src", node.attr("real_src"));
				node.removeAttr("width").removeAttr("height").removeAttr("style").removeAttr("real_src").removeAttr("alt").removeAttr("title");
			}
			doc.body().select(".shareUp").remove();
			doc.body().select(".into_bloger").remove();
			body = doc.body().select(".articalContent, .feedInfo").removeAttr("class").removeAttr("style").outerHtml();
		}
		
		//
		if(body == null||body.length()<1){
			super.findBody();
		}		
	}
	
	protected void findMedia(){
		media = "新浪网";
	}
	
	//提取图片
	public void findSlide(){
		String address = url;
		
		if(url.indexOf("xiu.jiaju.sina.com.cn/tu")>-1){  //http://xiu.jiaju.sina.com.cn/tu/1298691/
			int from = html.indexOf("var CONFIG");
			from = html.indexOf("{", from);
			int to   = html.indexOf("</script>", from);
			to = html.lastIndexOf("}", to)+1;
			
			body = "";
			
			String json = html.substring(from, to);
			//提取图片
			try{
				HashMap<String,Object> obj = (HashMap<String,Object>)JSONUtil.deserialize(json);
				//groupimginfo
				ArrayList  items = (ArrayList)obj.get("listData"); 
				for(int i=0; i< items.size(); i++){
					HashMap<String,Object> item = (HashMap<String,Object>)items.get(i);
					String link = item.get("b").toString();
					
					body += "<div class=\"slide\">";
					body += "<center><img src='"+link+"'></center>";
					body += "</div>";
					
					
					image += link+";";						
				}
			}catch(Exception e){
				e.printStackTrace();
			}			
		}else if(url.indexOf("slide.collection.sina.com.cn/slide")>-1){
			String page = url.substring(url.lastIndexOf("/"), url.lastIndexOf(".html"));
			String[]array = page.split("_");
			String api = "http://slide.news.sina.com.cn/interface/slide_interface.php?ch="+array[1]+"&sid="+array[2]+"&id="+array[3]+"&range=&key=&size=208_138&type=1&dpc=1";

			String info = C.getContentFromURL(api, "UTF-8");
			body = "";
			
			//提取视频代码和图片
			try{
				info = info.substring(info.indexOf("{")-1);
				HashMap<String,Object> obj = (HashMap<String,Object>)JSONUtil.deserialize(info);
				
				//HashMap<String, Object>  data = (HashMap<String, Object>)obj.get("data");
				ArrayList items = (ArrayList)obj.get("images");
				for(int i=0; i<items.size(); i++){
					HashMap<String, String>item = (HashMap<String, String>)items.get(i); 
					String link = item.get("image_url");
					String text = item.get("intro");
					
					body += "<div class=\"slide\">";
					body += "<center><img src='"+link+"'></center>";
					body += "<p>&nbsp;&nbsp;&nbsp;&nbsp;"+text+"</p><br/><br/>";
					body += "</div>";


					image += link+";";
				}		
			}catch(Exception e){
				e.printStackTrace();
			}			
		}else{
			//判断是否内嵌了幻灯，比如：http://news.sina.com.cn/photo/kanjian/41.html#p=1
			Pattern pattern = Pattern.compile("slide_url\\s*[:|=]\\s*([\"|'])([^'|\"]*)\\1",Pattern.CASE_INSENSITIVE);
			Matcher matched = pattern.matcher(html);
			if(matched.find()){
				address = matched.group(2);
				super.findBody();
			}
			
			//判断新浪幻灯片的图片
			pattern = Pattern.compile("slide_\\d+_(\\d+)_(\\d+).html",Pattern.CASE_INSENSITIVE);
			matched = pattern.matcher(address);
			if(matched.find()){
				String sid = matched.group(1);
				String aid = matched.group(2);			

				String api = "http://platform.sina.com.cn/slide/image?app_key=2894297679&format=json&num=300&sid="+sid+"&album_id="+aid;
				String info = C.getContentFromURL(api, "UTF-8");
				
				//提取视频代码和图片
				try{
					HashMap<String,Object> obj = (HashMap<String,Object>)JSONUtil.deserialize(info);
					
					HashMap<String, Object>  data = (HashMap<String, Object>)obj.get("data");
					ArrayList items = (ArrayList)data.get("item");
					for(int i=0; i<items.size(); i++){
						HashMap<String, String>item = (HashMap<String, String>)items.get(i); 
						String link = item.get("img_url");
						String text = item.get("intro");
						
						body += "<div class=\"slide\">";
						body += "<center><img src='"+link+"'></center>";
						body += "<p>&nbsp;&nbsp;&nbsp;&nbsp;"+text+"</p><br/><br/>";
						body += "</div>";

						image += link+";";
					}		
				}catch(Exception e){
					e.printStackTrace();
				}			
			}else{ //判断是否内容中包含幻灯片的图片信息，如http://vi.travel.sina.com.cn/play/?a=20301&u=0&c=1&t=3
				Elements slideData = doc.select("#eData");
				if(slideData.size()>0){
					Elements nodes = slideData.get(0).select("dl");
					for (Element node : nodes){
						String text = node.child(5).text();
						String link = node.child(2).text();
						
						body += "<div class=\"slide\">";
						body += "<center><img src='"+link+"'></center>";
						body += "<p>&nbsp;&nbsp;&nbsp;&nbsp;"+text+"</p><br/><br/>";
						body += "</div>";
						
						image += link+";";
					}
				}
			}
			
		}
		
	}
}
