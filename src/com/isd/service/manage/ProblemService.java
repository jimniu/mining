package com.isd.service.manage;

import java.util.List;

import com.isd.entity.mining.Problem;

public interface ProblemService {
	//列出匹配的结果列表
	public List<Problem> list(Problem problem, Integer offset, Integer pagesize);
	//查询匹配到的用户数
	public Integer count(Problem problem);	
	//获取用户信息
	public Problem getInfo(Integer id);	
	//删除用户
	public void delete(Integer id);
	//修改
	public void updateProblem(Problem problem);
	//添加
	public Integer save(Problem problem);
	
	public List<Problem> findById(Integer id, Integer offset, Integer pagesize);
	
	public Integer countById(int id);
}
