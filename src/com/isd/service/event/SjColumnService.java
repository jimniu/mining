package com.isd.service.event;

import java.util.List;

import com.isd.entity.event.SjColumn;




public interface SjColumnService {
	public void save(SjColumn column);
	//根据用户返回任务
	public List<SjColumn> findByNetwork(Integer networkid, Integer offset, Integer pagesize);
	//根据用户的搜索条件返回任务
	public List<SjColumn> findByNetwork(Integer networkid, SjColumn column, Integer offset, Integer pagesize);
	public String findKeywordsByNetwork(Integer networkid);
	//根据ID返回栏目
	public SjColumn findById(Integer id);
	
	public void delete(Integer id);
}
