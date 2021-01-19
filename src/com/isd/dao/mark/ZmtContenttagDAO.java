package com.isd.dao.mark;

import java.util.List;

import com.isd.dao.GenericDAO;
import com.isd.entity.mark.ZmtContent;
import com.isd.entity.mark.ZmtContenttag;
import com.isd.entity.mark.ZmtTag;

public interface ZmtContenttagDAO extends GenericDAO<ZmtContenttag, Integer>{
	//根据内容ID返回关系列表
	public List<ZmtContenttag> findByContentid(Integer contentid);
	//根据文章ID，返回对应的标签
	public List<ZmtTag> findTags(Integer contentid);
	//根据文章ID，返回相关的文章
	public List<ZmtContent> findRelated(Integer contentid);	
	//根据文章ID，删除与关键词的关系
	public void deleteByContentid(Integer contentid);

	public List findAllAndTotal(int id);
}
