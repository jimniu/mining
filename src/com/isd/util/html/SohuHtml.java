package com.isd.util.html;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.struts2.json.JSONUtil;

import com.isd.util.C;

public class SohuHtml extends HtmlBase{
	public SohuHtml() {
		super();
		// TODO Auto-generated constructor stub
		domain = "sohu.com";
	}
	public SohuHtml(String url) {
		super(url);
		// TODO Auto-generated constructor stub
	}

	//提取图片
	public void findSlide(){
		//提取内容
		Pattern pattern = Pattern.compile("(http.*\\/group-\\d+)\\.shtml",Pattern.CASE_INSENSITIVE);
		Matcher matched = pattern.matcher(url);
		
		if(matched.find()){
			String address = matched.group(1)+".js";
			
			String info = C.getContentFromURL(address, "GBK");
			if(!info.equals("error")){
				info = info.substring(info.indexOf("curGroupData")+14);
				info = info.substring(0, info.indexOf("};")+1);
				//提取图片
				try{
					HashMap<String,Object> obj = (HashMap<String,Object>)JSONUtil.deserialize(info);
					//groupimginfo
					ArrayList  items = (ArrayList)obj.get("items"); 
					for(int i=0; i< items.size(); i++){
						HashMap<String,Object> item = (HashMap<String,Object>)items.get(i);
						String text = item.get("desc").toString();
						String link = "http://m1.biz.itc.cn/pic/new/"+item.get("pic3").toString();
						
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
