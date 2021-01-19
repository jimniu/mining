package com.isd.action.manage;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.isd.action.GenericAction;
import com.isd.entity.manage.AdminUser;
import com.isd.entity.mining.Problem;
import com.isd.service.manage.ProblemService;
import com.isd.util.C;

public class ProblemAction extends GenericAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private ProblemService problemService;
	private Problem problem;
	private String scope;
	
	//公告管理首页
	public String index(){ 		
		List<Problem> list = problemService.list(problem, pageindex*12, 12);
		Integer total = problemService.count(problem);
		ctx.put("list", list);
		ctx.put("total", total.toString());	
		if(problem!=null){
			if(problem.getTitle()!=null){
				ctx.put("temptitle",C.getURLChinese(problem.getTitle()));
			}
			if(problem.getContent()!=null){
				ctx.put("tempcontent",C.getURLChinese(problem.getContent()));
			}	
		}		
		return SUCCESS;
	}

	//添加页面
	public String add(){
		return SUCCESS;
	}
	
	//保存
	public String save(){
		AdminUser au = (AdminUser)session.getAttribute(C.SESSION_ADMINUSER);
		problem.setAid(au.getId());
		problem.setCtime(new Date());
		problemService.save(problem);
		return SUCCESS;
	}
	
	//删除
	public String delete(){
		if(scope!=null&&scope.length()>0){
			String[] aa = scope.split(",");
			int total = aa.length;
		    for (int i = 0 ; i <aa.length ; i++ ) {		    
		      Integer id = Integer.parseInt(aa[i]);
		      problemService.delete(id);		   
		    } 
			ctx.put("text", "删除了"+total+"条");
		}
		return TEXT;
	}
	
	//获得信息
	public String getInfo(){ 
		Integer id = problem.getId();		
		Problem problem = problemService.getInfo(id);
		session.setAttribute("problem", problem);
		return SUCCESS;
	}

	//修改
	public String update(){
		Problem prob = (Problem)session.getAttribute("problem");
		prob.setTitle(problem.getTitle());
		prob.setContent(problem.getContent());
		prob.setStatus(problem.getStatus());
		prob.setWeight(problem.getWeight());
		prob.setUtime(new Date());
		problemService.updateProblem(prob);
		return SUCCESS;
	}

	public Problem getProblem() {
		return problem;
	}

	public void setProblem(Problem problem) {
		this.problem = problem;
	}

	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

}
