package com.isd.service.manage.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.isd.dao.manage.ProblemDAO;
import com.isd.entity.mining.Problem;
import com.isd.service.manage.ProblemService;


public class ProblemServiceImpl implements ProblemService{
	@Autowired 
	private ProblemDAO problemDAO;
	
	//列出匹配的结果列表
	public List<Problem> list(Problem problem, Integer offset, Integer pagesize){
		return problemDAO.list(problem, offset, pagesize);
	}
	
	//查询匹配到的用户数
	public Integer count(Problem problem){
		return problemDAO.count(problem);
	}

	//删除用户
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		problemDAO.delete(id);
	}
	
	public Problem getInfo(Integer id) {
		// TODO Auto-generated method stub
		return problemDAO.get(id);
	}

	public void updateProblem(Problem problem) {
		// TODO Auto-generated method stub
		problemDAO.update(problem);
	}

	public Integer save(Problem problem) {
		// TODO Auto-generated method stub
		return problemDAO.save(problem);
	}

	public List<Problem> findById(Integer id, Integer offset, Integer pagesize) {
		// TODO Auto-generated method stub
		return problemDAO.findById(id, offset, pagesize);
	}

	public Integer countById(int id) {
		// TODO Auto-generated method stub
		return problemDAO.countById(id);
	}

}
