package com.isd.action.mining;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.isd.action.GenericAction;
import com.isd.entity.mark.ZmtContent;
import com.isd.entity.mark.ZmtTag;
import com.isd.entity.mining.User;
import com.isd.service.mark.ZmtContentService;
import com.isd.service.mark.ZmtContenttagService;
import com.isd.service.mark.ZmtTagService;
import com.isd.util.C;

public class MarkAction extends GenericAction{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ZmtContentService contentService;
	@Autowired
	private ZmtTagService tagService;
	@Autowired
	private ZmtContenttagService contenttagService;
	
	private String tagname;
	private ZmtTag tag;
	private String address;
	private ZmtContent content;
	private Integer id;	
	private Integer time = 7;
	private String type = "keyword";
	
	//搜索用处
	private String from;
	private String to;
	private String kw;
	
	public String index(){
		User user = (User)session.getAttribute(C.SESSION_USER);
		List list = contenttagService.findAllAndTotal(mynetwork.getId());
		Integer total = tagService.Count(user.getId());
		ctx.put("list", list);
		ctx.put("count", total.toString());	
		return SUCCESS;
	}
	
	//自媒体内容列表
	public String list(){
		pagesize = 20;
		
		if(kw!=null){
			kw = C.getURLChinese(kw);
		}
		
		//根据搜索条件查询
		if(content!=null&&content.getTag()!=null&&content.getTag().length()>0){
			Integer tagid = Integer.valueOf(content.getTag());
			List<ZmtContent> list = contentService.list(mynetwork.getId(),tagid, kw, from, to, pageindex*pagesize, pagesize);
			Integer total = contentService.count(mynetwork.getId(),tagid, kw, from, to);	
			ctx.put("marklist", list);
			ctx.put("total", total.toString());				
		}else{
			List<ZmtContent> list = contentService.list(mynetwork.getId(), kw, from, to, pageindex*pagesize, pagesize);
			Integer total = contentService.count(mynetwork.getId(), kw, from, to);		
			ctx.put("marklist", list);
			ctx.put("total", total.toString());				
		}

		List<ZmtTag> taglist = tagService.findByUser( mynetwork.getId());
		ctx.put("taglist", taglist);
		return SUCCESS;
	}

	//抓取内容
	public String detail(){
		//如果参数带ID，代表用户要对内容进行编辑，则从数据库提取数据
		List<ZmtTag> taglist = tagService.findByUser(mynetwork.getId());
		if( id!=null ){
			content = contentService.findById(id);
		}else{
			content = contentService.fetch(address);
		}

		ctx.put("taglist", taglist);	
		ctx.put("content", content);
		return SUCCESS;		
	}
		
	public String page(){
		if( id != null){
			content = contentService.findById(id);	
			if(!content.getUid().equals(mynetwork.getId())){
				ctx.put("redirect", "mark/list.shtml");
			}
			
			//文章包含的标签
			List<ZmtTag> taglist= contenttagService.findTags(id);
			//相关文章
			List<ZmtContent> relatelist = contenttagService.findRelated(id);
			ctx.put("relatelist", relatelist);
			ctx.put("taglist", taglist);
			ctx.put("content", content);
			return SUCCESS;					
		}else{
			ctx.put("redirect", "mark/list.shtml");
			return REDIRECT;		
		}
	}

	public String result(){
		content = contentService.findById(id);
		ctx.put("content", content);
		return SUCCESS;
	}
	
	public String tag(){
		return SUCCESS;
	}

	//发表内容
	public String post(){
		//如果参数带ID，代表用户要对内容进行编辑，则从数据库提取数据
		List<ZmtTag> taglist = tagService.findByUser(mynetwork.getId());
		if( id!=null ){
			content = contentService.findById(id);
			ctx.put("content", content);
		}

		ctx.put("taglist", taglist);

		return SUCCESS;
	}
	
	public String save(){
		if(content.getId()!=null){		
			ZmtContent con = contentService.findById(content.getId());
			con.setUtime(new Date());
			con.setTitle(content.getTitle());
			con.setAddress(content.getAddress());
			con.setContent(content.getContent());
			con.setComment(content.getComment());
			con.setMedia(content.getMedia());
			con.setTag(content.getTag());
			contentService.update(con);
			
			//保存文章对应的标签
			contenttagService.save(mynetwork.getId(), content.getId(), con.getCtime(), con.getTag());
		}else{
			if(!contentService.exists(mynetwork.getId(), content.getAddress())){
				content.setUid(mynetwork.getId());
				content.setCtime(new Date());
				Integer conid = contentService.save(content);
				
				//保存文章对应的标签
				contenttagService.save(mynetwork.getId(), content.getId(), content.getCtime(), content.getTag());
			}
		}		
		ctx.put("redirect", "mark/list.shtml");
		return REDIRECT;		
	}

	//删除
	public String delete(){
		if(kw!=null&&kw.length()>0){
			String[] array = kw.split(",");
			int total = 0;
		    for (int i = 0 ; i <array.length ; i++ ) {	
		    	String item = array[i].trim();
		    	if(item.length()>0){
			    	Integer id = Integer.parseInt(item);
			    	contentService.delete(id);
					contenttagService.deleteByContentid(id);	
					total ++;
		    	}
		    } 
			ctx.put("text", "删除了"+total+"条");
		}
		return TEXT;
	}
	
	//删除单条
	public String deleteID(){	
		contentService.delete(id);
		contenttagService.deleteByContentid(id);
		ctx.put("redirect", "mark/list.shtml");
		return REDIRECT;	
	}
	
	//导出
	public String export(){
        response.setContentType("application/vnd.ms-excel");         
        response.setHeader("Content-Disposition", "attachment;fileName="+"result.xls"); 
		List<ZmtContent> list = contentService.list(mynetwork.getId());
		ctx.put("list", list);		
		return SUCCESS;
	}
	
	//分析
	public String analyse(){
		List<ZmtTag> taglist = tagService.findByUser(mynetwork.getId());		
		if(type.equals("keyword")){
			List list = contentService.countByKeyword(mynetwork.getId());
	    	ctx.put("list", list);
		} else {
			List list = contentService.countByTime(id, time);
	    	ctx.put("list", list);
			ctx.put("from",C.getDate(0));
			ctx.put("to", C.getDate(time));
		}	
		ctx.put("taglist", taglist);
		return SUCCESS;
	}
	
	//列出所有内容，查看其body的获取方式
	public String listall(){
		List<ZmtContent> list = contentService.list(pageindex*pagesize, pagesize);
		Integer total = contentService.count();
		ctx.put("list", list);
		ctx.put("total", total.toString());
		return SUCCESS;
	}
	
	public String add(){
		List result = tagService.findByHql(mynetwork.getId(), tagname);
		if(result==null || result.size()<1){			
			ZmtTag tag = new ZmtTag();
			tag.setCtime(new Date());
			tag.setValue(tagname);
			tag.setUid(mynetwork.getId());
			Integer id = tagService.save(tag);
		}
		ctx.put("redirect", "mark/index.shtml");
		return REDIRECT;
	}
	
	public String deleteTag(){
		if(tagname!=null&&tagname.length()>0){
			String[] aa = tagname.split(",");
			int total = aa.length;
		    for (int i = 0 ; i <aa.length ; i++ ) {		    
		      Integer id = Integer.parseInt(aa[i]);
		      tagService.delete(id);
		    } 
			ctx.put("text", "删除了"+total+"条");
		}
		return TEXT;
	}
	
	public String deleteTagID(){
		if(tag.getId()!=null && tag.getId()>0){
			 tagService.delete(tag.getId());
		}
		ctx.put("text", SUCCESS);
		return TEXT;
	}
	
	public String update(){
		ZmtTag t = tagService.findById(tag.getId());
		t.setValue(tag.getValue());
		tagService.update(t);
		ctx.put("text", SUCCESS);
		return TEXT;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public ZmtContent getContent() {
		return content;
	}

	public void setContent(ZmtContent content) {
		this.content = content;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getKw() {
		return kw;
	}

	public void setKw(String kw) {
		this.kw = kw;
	}
	

	public String getTagname() {
		return tagname;
	}

	public void setTagname(String tagname) {
		this.tagname = tagname;
	}

	public ZmtTag getTag() {
		return tag;
	}

	public void setTag(ZmtTag tag) {
		this.tag = tag;
	}
		
}
