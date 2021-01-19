package com.isd.service.mining.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.isd.dao.mining.ProblemDAO;
import com.isd.entity.mining.Problem;
import com.isd.service.mining.ProblemService;


public class ProblemServiceImpl implements ProblemService{
	@Autowired 
	private ProblemDAO problemDAO;
	
	public List<Problem> list(Integer offset, Integer pagesize){
		return problemDAO.list(offset, pagesize);
	}
	
	public Integer count(){
		return problemDAO.count();
	}
}
