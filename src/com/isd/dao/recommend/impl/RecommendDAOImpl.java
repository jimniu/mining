package com.isd.dao.recommend.impl;


import java.util.Date;
import java.util.List;

import com.isd.dao.GenericDAOImpl;
import com.isd.dao.recommend.RecommendDAO;
import com.isd.entity.recommend.Recommend;
import com.isd.util.C;


public class RecommendDAOImpl extends GenericDAOImpl<Recommend, Integer> implements RecommendDAO {

	public RecommendDAOImpl() {
		super(Recommend.class);
	}

	//分页显示推荐列表
	public List<Recommend> listByUser(Integer networkid, Integer offset, Integer pagesize){
		String hql = "from Recommend where networkid="+networkid+" order by ctime desc";
		return this.findPageByHql(hql, offset, pagesize);
	}
	public List<Recommend> listByStatus(Integer status, Integer offset, Integer pagesize){
		String hql = "from Recommend where status="+status+" order by ctime desc";
		return this.findPageByHql(hql, offset, pagesize);
	}
	public List<Recommend> listByDateRange(Integer networkid, String from, String to){
		String hql = "from Recommend where networkid="+networkid+ "and ctime>='"+from+"' and ctime<='"+to+"' order by ctime desc";
		return this.findByHql(hql);
	}

	//按照模块显示用户的收藏数量
	public Integer totalByUser(Integer networkid){
		String hql = "select count(id) from Recommend where networkid="+networkid;
		return this.getCountByHql(hql);
	}
	public Integer totalByStatus(Integer status){
		String hql = "select count(id) from Recommend where status="+status;
		return this.getCountByHql(hql);
	}

	//返回信息卡的历史消息
	public List<Recommend> findHistory(Integer networkid, Integer recommendid, Integer offset, Integer pagesize){
		String hql = "from Recommend where networkid="+networkid;
		if(recommendid!=null){
			hql += " and id<"+recommendid;
		}
		hql += " and type=0 order by id desc";
		return this.findPageByHql(hql, offset, pagesize);
	}

	//当天的资讯卡数量
	public Integer available(Integer networkid){
		String hql = "select count(*) from Recommend where networkid="+networkid+" and ctime>='"+ C.getDateStart(new Date())+"'";
		return this.getCountByHql(hql);
	}
}
