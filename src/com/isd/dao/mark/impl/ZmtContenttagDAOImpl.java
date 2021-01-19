package com.isd.dao.mark.impl;

import java.util.List;

import com.isd.dao.GenericDAOImpl;
import com.isd.dao.mark.ZmtContenttagDAO;
import com.isd.entity.mark.ZmtContent;
import com.isd.entity.mark.ZmtContenttag;
import com.isd.entity.mark.ZmtTag;

public class ZmtContenttagDAOImpl extends GenericDAOImpl<ZmtContenttag, Integer> implements  ZmtContenttagDAO {
	
	public ZmtContenttagDAOImpl() {
		super(ZmtContenttag.class);
	}

	//根据内容ID返回关系列表
	public List<ZmtContenttag> findByContentid(Integer contentid){
		String hql = "from ZmtContenttag where contentid="+contentid;
		return this.findByHql(hql);
	}
	
	//根据文章ID，返回对应的标签
	public List<ZmtTag> findTags(Integer contentid){
		String hql = "select t from ZmtTag t, ZmtContenttag ct where ct.contentid="+contentid+" and ct.tagid=t.id";
		return this.findByHql(hql);
	}
	
	//根据文章ID，返回相关的文章
	public List<ZmtContent> findRelated(Integer contentid){
		String hql = "select distinct c from ZmtContent c, ZmtContenttag ct1, ZmtContenttag ct2 where ct1.contentid="+contentid+" and ct1.tagid=ct2.tagid and ct2.contentid=c.id order by c.ctime desc";
		return this.findPageByHql(hql, 0, 5);
	}
	
	//根据文章ID，删除与关键词的关系
	public void deleteByContentid(Integer contentid){
		String hql = "delete from ZmtContenttag where contentid="+contentid;
		this.executeByHql(hql);
	}

	public List findAllAndTotal(int id) {
		// TODO Auto-generated method stub
		String hql ="select d.id,d.value,c.total,d.ctime  from (select * from zmt_tag where uid = "+id+") d left join (select b.tagid id, count(b.id) total from (select * from zmt_contenttag where uid = "+id+") b group by b.tagid)  c  on  d.id = c.id  order by d.ctime desc";
		return this.findBySql(hql);
	}
}
