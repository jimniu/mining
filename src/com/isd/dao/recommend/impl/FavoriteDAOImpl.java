package com.isd.dao.recommend.impl;


import java.util.List;

import com.isd.dao.GenericDAOImpl;
import com.isd.dao.recommend.FavoriteDAO;
import com.isd.entity.recommend.Favorite;


public class FavoriteDAOImpl extends GenericDAOImpl<Favorite, Integer> implements FavoriteDAO {

	public FavoriteDAOImpl() {
		super(Favorite.class);
	}
	//判断是否已经收藏此文
	public boolean exists(Integer networkid, String module,  Integer pageid){
		String hql = "from Favorite where networkid="+networkid+" and module='"+module+"' and pageid="+pageid;
		return this.findByHql(hql).size()>0?true:false;
	}

	//根据时间显示用户收藏的内容列表
	public List<Favorite> list(Integer networkid, String module, Integer offset, Integer pagesize){
		String hql = "from Favorite where networkid="+networkid+" order by ctime desc";
		if(module!=null&&module.length()>1) {
			hql = "from Favorite where networkid=" + networkid + " and module='" + module + "' order by ctime desc";
		}
		return this.findPageByHql(hql, offset, pagesize);
	}

	//按照模块显示用户的收藏数量
	public Integer total(Integer networkid, String module){
		String hql = "select count(id) from Favorite where networkid="+networkid;
		if(module!=null&&module.length()>1) {
			hql = "select count(id) from Favorite where networkid=" + networkid + " and module='" + module + "'";
		}
		return this.getCountByHql(hql);
	}

	//根据内容ID的列表返回内容
	public List<Favorite> findByIds(String idlist){
		String hql = "from Favorite where id in("+idlist+") order by INSTR(',"+idlist+",', ','+id+',')";
		return this.findByHql(hql);
	}
}
