package com.isd.service.mark.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.isd.dao.mark.ZmtContenttagDAO;
import com.isd.dao.mark.ZmtTagDAO;
import com.isd.entity.mark.ZmtContent;
import com.isd.entity.mark.ZmtContenttag;
import com.isd.entity.mark.ZmtTag;
import com.isd.service.mark.ZmtContenttagService;


public class ZmtContenttagServiceImpl implements ZmtContenttagService{

	@Autowired 
	private ZmtContenttagDAO contenttagDAO;
	@Autowired
	private ZmtTagDAO tagDAO;

	//保存文章对应的标签
	public void save(Integer uid, Integer contentid, Date ctime, String tags){
		if(tags==null||tags.length()==0)
			return;
		
		//将文章原先绑定的标签列出，并存于hash中便于查找
		List<ZmtContenttag> list = contenttagDAO.findByContentid(contentid);
		HashMap<Integer, ZmtContenttag> hash = new HashMap<Integer, ZmtContenttag>();
		for(int i=0; i<list.size(); i++){
			ZmtContenttag ct = list.get(i);
			hash.put(ct.getTagid(), ct);
		}
		
		String[] array = tags.split(",");
		for(int i=0; i<array.length; i++){
			String tag = array[i].trim();
			if(tag.length()>0){
				Integer tagid = Integer.valueOf(tag);
				//如果标签ID没有存在于Hash中，需要入库
				if(hash.get(tagid)==null){
					ZmtContenttag ct = new ZmtContenttag();
					ct.setContentid(contentid);
					ct.setTagid(tagid);
					ct.setUid(uid);
					ct.setCtime(ctime);
					contenttagDAO.save(ct);				
				}else{ //如果tagid存在于hash中，则将其从list中去除
					ZmtContenttag ct = hash.get(tagid);
					list.remove(ct);
				}
			}
		}
		
		//list中剩余的对象属于文章不再关注的标签，删除
		for(int i=0; i<list.size(); i++){
			ZmtContenttag ct = list.get(i);
			contenttagDAO.delete(ct);		
		}
	}
	
	//根据文章ID，返回对应的标签
	public List<ZmtTag> findTags(Integer contentid){
		return contenttagDAO.findTags(contentid);
	}
	
	//根据文章ID，返回相关的文章
	public List<ZmtContent> findRelated(Integer contentid){
		return contenttagDAO.findRelated(contentid);
	}
	
	//根据文章ID，删除与关键词的关系
	public void deleteByContentid(Integer contentid){
		contenttagDAO.deleteByContentid(contentid);
	}	

	public List findAllAndTotal(int id) {
		// TODO Auto-generated method stub
		return contenttagDAO.findAllAndTotal(id);
	}

}
