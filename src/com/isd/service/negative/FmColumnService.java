package com.isd.service.negative;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.isd.entity.negative.FmColumn;

public interface FmColumnService {
	//根据用户返回所有任务
	public List<FmColumn> findByNetwork(Integer networkid, Integer offset, Integer pagesize);
	//根据用户返回指定状态的任务
	public List<FmColumn> findByNetwork(Integer networkid, Integer status, Integer offset, Integer pagesize);
	public String findKeywordsByNetwork(Integer networkid);
	//保存设置
	public void save(HttpServletRequest request, Integer uid);	
	//根据ID返回栏目
	public FmColumn findById(Integer id);
	//根据一组ID来进行状态的改变
	public void handle(String ids);
}
