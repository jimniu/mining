package com.isd.service.mining;

import java.util.List;

import com.isd.entity.mining.Problem;

public interface ProblemService {
	
	public List<Problem> list( Integer offset, Integer pagesize);
	
	public Integer count();	
}
