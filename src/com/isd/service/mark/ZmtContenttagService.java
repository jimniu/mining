package com.isd.service.mark;

import java.util.Date;
import java.util.List;

import com.isd.entity.mark.ZmtContent;
import com.isd.entity.mark.ZmtTag;




public interface ZmtContenttagService {
	//保存文章对应的标签
	public void save(Integer uid, Integer contentid, Date ctime, String tags);
	//根据文章ID，返回对应的标签
	public List<ZmtTag> findTags(Integer contentid);
	//根据文章ID，返回相关的文章
	public List<ZmtContent> findRelated(Integer contentid);
	//根据文章ID，删除与关键词的关系
	public void deleteByContentid(Integer contentid);
	//查全部加数量
	public List findAllAndTotal(int id);
}
