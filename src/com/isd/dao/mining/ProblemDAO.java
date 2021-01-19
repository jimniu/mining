package com.isd.dao.mining;

import java.util.List;

import com.isd.dao.GenericDAO;
import com.isd.entity.mining.Problem;

public interface ProblemDAO extends GenericDAO<Problem, Integer>{
	
	public List<Problem> list(Integer offset, Integer pagesize);
	
	public Integer count();
}
