package com.isd.util.html;

import java.io.File;
import java.util.Hashtable;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.isd.util.C;

public class HtmlBase {
	protected Document doc;
	protected String html;
	protected String domain;
	protected String url;
	protected String title;
	protected String body = "";
	protected String media;
	protected String image;

	public HtmlBase(){
		
	}
	
	public HtmlBase(String url){
		this.url = url;
	}
	
	//根据传进来的地址，寻找合适的解析器
	static public HtmlBase findParser(String url){
		HtmlBase parser = null;
		Hashtable<String, String> parsers;
		String key = "html_parser_list";

		try {
			Object cache = C.getFromCache(key);
			if(cache!=null){
				parsers = (Hashtable<String, String>)cache;
			}else{
				parsers = new Hashtable<String, String>();
				//列出类所在目录下的所有文件
				String path = HtmlBase.class.getResource("HtmlBase.class").getPath();
				path = path.substring(0, path.lastIndexOf("/"));
				File dir = new File(path);
				File[] files = dir.listFiles();
				for(int i=0; i<files.length; i++){
					String name = files[i].getName();
					name = "com.isd.util.html."+name.substring(0,name.lastIndexOf("."));
					HtmlBase obj = (HtmlBase)Class.forName(name).newInstance();
					String link = obj.getDomain();
					if(link!=null){
						parsers.put(link, name);
					}
				}
				C.saveToCache(key, parsers);
			}		
			
			//得到传进来url的域名,返回域名代表的对象
			String link = C.getDomainName(url);
			String classname = parsers.get(link);
			if(classname==null){
				parser = (HtmlBase)Class.forName("com.isd.util.html.HtmlBase").newInstance();
			}else{
				parser = (HtmlBase)Class.forName(classname).newInstance();
			}
			parser.setUrl(url);			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return parser;
	}
	
	//根据页面地址提取正文
	public void parseURL(){
		html = C.getContentFromURL(url);

		html = html.replaceAll("&nbsp;&nbsp;", "　");
		//处理转码过的标签
		html = html.replaceAll("&lt;", "<");
		html = html.replaceAll("&gt;", ">");
		html = html.replaceAll("<!--((?!-->).)*-->", "");
		doc = Jsoup.parse(html, url);
		this.findTitle();
		this.findMedia();		
	}
	
	//分析标题
	protected void findTitle(){
		// 提取title
		title = doc.getElementsByTag("title").text();
		
		//对标题进行处理，按照分割符拆分，假定第一部分是标题
		title = title.replaceAll("-", "_");
		title = title.replaceAll("—", "_");
		title = title.replaceAll("——", "_");
		title = title.replaceAll("\\|", "_");
		String[] contents = title.split("_");
		title = contents[0];
	}
	
	//提取媒体名称
	protected void findMedia(){
		// 提取title
		media = doc.getElementsByTag("title").text();
		
		//对标题进行处理，按照分割符拆分，假定第一部分是标题
		media = media.replaceAll("-", "_");
		media = media.replaceAll("—", "_");
		media = media.replaceAll("——", "_");
		media = media.replaceAll("\\|", "_");
		String[] contents = media.split("_");
		//网站标题的最后一部分，往往是网站的名称，提取出来保存
		media = contents[contents.length-1];
	}

	//查找幻灯片
	public void findSlide(){
		
	}	
	
	//获取正文
	public void findBody(){
		Element element = doc.body().clone();	

		//清除无用的Tag
		this.removeTag(element, "script,noscript,style,iframe,class,meta,form,link,input,div[class~=(?i)comment],div[id~=(?i)comment]");
		
		//找出html中内容最多的最小节点
		while(true){
			Element node = this.findMainNode(element);
			if(node==null||size(node)/size(element)<0.5)
				break;
			else
				element = node;
		}

		//从找到的内容节点中去除链接占多数的节点
		while(this.removeLinkNode(element)){
			//System.out.println(element.toString());
		}
		
		clean(element);
		body = element.children().toString();
	}
	

	//寻找本节点的主要div子节点
	private Element findMainNode(Element element){
		Element main = null;
		double fullsize = size(element);
		
		Elements children = element.children();
		for (Element child : children) {
			if (blockTag(child)){ // 该元素子元素为div元素，则递归调用函数
				double childsize = size(child);
				if(childsize/fullsize>0.6){
					main = child;
					break;
				}
			}
		}
		return main;
	}
	
	//删除内容主要为链接的节点
	private boolean removeLinkNode(Element element){
		boolean result = false;
		Elements children = element.children().select("div:not(:has(div))");
		for (Element child : children) {
			//if(linkTag(child)&&child.select("img").size()==0){
			if(linkTag(child)){
				child.remove();
				result = true;
			}
		}
		return result;
	}

	//获得节点的大小
	private float size(Element element){
		Element node = element.clone();
		this.removeTag(node, "a");
		if(node!=null)
			return node.text().length();
		else 
			return 0;
	}

	//判断节点中的内容是否连接为主
	private boolean linkTag(Element element){
		//小于20个汉字的略去
		//if(element.text().length()<40)
		//	return true;
		Elements links = element.select("a[href]"); 
		String linktext = links.text().replaceAll(" ", "").replaceAll("<p[^>]*>", "");
		String alltext  = element.text().replaceAll(" ", "").replaceAll("<p[^>]*>", "");
		double linkrate = (double)links.size()/(double)alltext.length();
		double textrate = (double)linktext.length()/(double)alltext.length();
		if((linkrate>0.03||textrate>0.9)&&element.children().select("div").size()==0&&element.children().select("img").size()==0){
			return true;
		}else{
			return false;
		}
	}
	
	private boolean blockTag(Element element) // 判断元素对应标签是不是分块标签
	{
		if (element.select("div").size()>0)
			return true;
		else
			return false;
	}


	private void removeTag(Element doc, String tag) {
		Elements elements = doc.select(tag);
		for (Element element : elements) {
			element.remove();
		}
	}
	
	//清理内容中的链接、样式
	protected void clean(Element element){
		Elements nodes = element.select("[class],[style],[id]");
		for (Element node : nodes){
			node.removeAttr("class");
			node.removeAttr("style");
			node.removeAttr("id");
		}

		nodes = element.select("img");
		for (Element node : nodes){
			node.removeAttr("alt");
			node.removeAttr("title");
			node.removeAttr("name");
			
			String imgurl = node.absUrl("src");
			node.attr("src", imgurl);
			node.wrap("<center></center>");
		}
		//System.out.println(nodes.toString());
		
		nodes = element.select("a");
		for (Element node : nodes){
			String html = node.html();
			node.empty().after(html);
			node.remove();
		}
		//将dl,dt,dd中的内容取出，放在原先位置，然后去掉标签
		nodes = element.select("dl");
		for (Element node : nodes){
			String html = node.html();
			node.empty().after(html);
			node.remove();
		}		
		nodes = element.select("dt,dd");
		for (Element node : nodes){
			String html = node.html();
			node.empty().after(html);
			node.remove();
		}	
	}

	public Document getDoc() {
		return doc;
	}

	public void setDoc(Document doc) {
		this.doc = doc;
	}

	public String getHtml() {
		return html;
	}

	public void setHtml(String html) {
		this.html = html;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getMedia() {
		return media;
	}

	public void setMedia(String media) {
		this.media = media;
	}
	
	public static void main(String[] args) {
		HtmlBase base = new HtmlBase("aaa");
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
}