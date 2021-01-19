package com.isd.dao.manage;

import java.util.List;

import com.isd.dao.GenericDAO;
import com.isd.entity.mining.Problem;

public interface ProblemDAO extends GenericDAO<Problem, Integer>{
	
	//列出匹配的结果列表
	public List<Problem> list(Problem problem, Integer offset, Integer pagesize);
	//查询匹配到的用户数
	public Integer count(Problem problem);
	
	public List<Problem> findById(Integer id, Integer offset, Integer pagesize);
	
	public Integer countById(int id);
}
