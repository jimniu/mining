package com.isd.service.mark.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.isd.dao.mark.ZmtTagDAO;
import com.isd.entity.mark.ZmtTag;
import com.isd.service.mark.ZmtTagService;


public class ZmtTagServiceImpl implements ZmtTagService{

	@Autowired 
	private ZmtTagDAO tagDAO;

	//根据用户ID返回内容
	public List<ZmtTag> findByUser(Integer uid) {
		// TODO Auto-generated method stub
		return tagDAO.findByProperty("uid", uid);
	}
	
	public Integer Count(int id) {
		// TODO Auto-generated method stub
		return tagDAO.count(id);
	}


	public Integer save(ZmtTag tag) {
		// TODO Auto-generated method stub
		return tagDAO.save(tag);
	}

	public void delete(Integer id) {
		// TODO Auto-generated method stub
		tagDAO.delete(id);
	}

	public ZmtTag findById(Integer id) {
		// TODO Auto-generated method stub
		return tagDAO.findById(id);
	}

	public void update(ZmtTag tag) {
		// TODO Auto-generated method stub
		tagDAO.update(tag);
	}

	public List findByHql(int id, String tag) {
		// TODO Auto-generated method stub
		String hql ="from ZmtTag where uid = "+id+" and value = '"+tag+"'";
		return tagDAO.findByHql(hql);
	}

	public List findListByHql(String tags) {
		// TODO Auto-generated method stub
		if(tags.length()>1){
			tags = tags.substring(1,tags.length()-1);
		}
		String hql = "from ZmtTag where id in ("+tags+")";
		return tagDAO.findByHql(hql);
	}

}
