package com.isd.service.mark.impl;

import java.net.URLDecoder;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import com.isd.dao.mark.ZmtContentDAO;
import com.isd.dao.news.XwPatternDAO;
import com.isd.dao.news.XwSiteDAO;
import com.isd.entity.mark.ZmtContent;
import com.isd.entity.news.XwPattern;
import com.isd.entity.news.XwSite;
import com.isd.service.mark.ZmtContentService;
import com.isd.util.html.HtmlBase;


public class ZmtContentServiceImpl implements ZmtContentService{
	@Autowired
	private ZmtContentDAO contentDAO;
	@Autowired
	private XwSiteDAO siteDAO;
	@Autowired
	private XwPatternDAO patternDAO;
	
	//列出匹配的结果列表
	public List<ZmtContent> list(Integer uid, Integer tagid, String kw, String from, String to, Integer offset, Integer pagesize){
		return contentDAO.list(uid, tagid, kw, from, to, offset, pagesize);
	}
	public List<ZmtContent> list(Integer uid, String kw, String from, String to, Integer offset, Integer pagesize){
		return contentDAO.list(uid, kw, from, to, offset, pagesize);
	}	
	public List<ZmtContent> list(Integer offset, Integer pagesize){
		return contentDAO.list(offset, pagesize);
	}
	
	public List<ZmtContent> list(Integer uid){
		return contentDAO.list(uid);
	}
	
	//查询匹配到的用户数
	public Integer count(Integer uid, Integer tagid, String kw, String from, String to){
		return contentDAO.count(uid, tagid, kw, from, to);
	}
	
	public Integer count(Integer uid, String kw, String from, String to){
		return contentDAO.count(uid, kw, from, to);
	}
	
	public Integer count(){
		return contentDAO.count();
	}
	
	//ID查询
	public ZmtContent findById(Integer id) {
		// TODO Auto-generated method stub
		return contentDAO.findById(id);
	}

	//添加
	public Integer save(ZmtContent content) {
		// TODO Auto-generated method stub
		return contentDAO.save(content);
	}

	//修改
	public void update(ZmtContent content) {
		// TODO Auto-generated method stub
		contentDAO.update(content);
	}

	//地址是否已经收藏
	public boolean exists(Integer uid, String url){
		return contentDAO.exists(uid, url);
	}
	
	
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		contentDAO.delete(id);
	}

	//根据tagid以及时间返回
	public List countByTime(Integer tagid,Integer time){
		return contentDAO.countByTime(tagid, time);
	}

	public List countByKeyword(Integer uid) {
		return contentDAO.countByKeyword(uid);
	}
	
	
	//根据传入的地址，提取内容
	public ZmtContent fetch(String address){
		ZmtContent content = new ZmtContent();
		
		try{
			address = URLDecoder.decode(address, "ISO-8859-1");				
		}catch(Exception e){
			e.printStackTrace();
		}		
		
		HtmlBase html = HtmlBase.findParser(address);
		html.parseURL();
		
		//将抓取到的标题和媒体名称存入对象
		content.setAddress(address.trim());			
		content.setTitle(html.getTitle());
		content.setMedia(html.getMedia());
		
		//优先按照幻灯的形式去读取
		html.findSlide(); 
		
		//判断是否抓取到内容，如果抓到，则提取内容
		if(html.getBody().length()>10){
			content.setContent(html.getBody());
			content.setMethod("slide");
		}else{ //如果没抓到，则试图用站点中预设的内容样式来匹配
			Document doc = html.getDoc();
			XwSite site = siteDAO.findByUrl(address);
			if(site!=null){
				List<XwPattern> patlist = patternDAO.list(site.getId());
	    		for(int i=0; i<patlist.size(); i++){
	            	Elements elems = doc.select(patlist.get(i).getRegex());
	            	if(elems.size()>0){
	            		content.setContent(elems.html());
	            		content.setMethod("pattern");
	            		content.setMedia(site.getName());
	            		break;
	            	}   
	    		}				
				
			}
		}
		
		//如果根据slide和样式都没有得到正文内容，则直接分析页面结构来获得
		if(content.getContent()==null||content.getContent().length()<10){
			html.findBody();
			content.setContent(html.getBody());
			content.setMethod("analysis");
		}
		
		
		return content;
	}
}
