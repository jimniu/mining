package com.isd.dao.mining.impl;

import java.util.List;

import com.isd.dao.GenericDAOImpl;
import com.isd.dao.mining.ProblemDAO;
import com.isd.entity.mining.Problem;


public class ProblemDAOImpl extends GenericDAOImpl<Problem, Integer> implements ProblemDAO {
	
	public ProblemDAOImpl() {
		super(Problem.class);
	}

	public List<Problem> list(Integer offset, Integer pagesize){
		String hql = "from Problem where status = 1 order by ctime desc";
		return this.findPageByHql(hql, offset, pagesize);
	}

	public Integer count(){
		String hql = "select count(*)  from Problem  where status = 1";
		return this.getCountByHql(hql);
	}
}
