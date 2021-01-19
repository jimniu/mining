package com.isd.service.mark;

import java.util.List;

import com.isd.entity.mark.ZmtTag;



public interface ZmtTagService {
	//根据用户ID返回内容
	public List<ZmtTag> findByUser(Integer uid);
	
	
	public Integer Count(int id);
	

	
	public Integer save(ZmtTag tag);
	
	public void delete(Integer id);
	
	public ZmtTag findById(Integer id);
	
	public void update(ZmtTag tag);
	
	public List findByHql(int id, String tag);
	
	public List findListByHql(String tags);
}
