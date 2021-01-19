package com.isd.dao.manage.impl;

import java.util.List;

import com.isd.dao.GenericDAOImpl;
import com.isd.dao.manage.ProblemDAO;
import com.isd.entity.mining.Problem;
import com.isd.util.C;


public class ProblemDAOImpl extends GenericDAOImpl<Problem, Integer> implements ProblemDAO {
	
	public ProblemDAOImpl() {
		super(Problem.class);
	}

	//列出匹配的结果列表
	public List<Problem> list(Problem problem, Integer offset, Integer pagesize){
		String hql = "from Problem  where 1=1  ";
		String part = this.genSql(problem);
		if(part!=null){
			hql += part;
		}
		hql += " order by ctime desc";
		return this.findPageByHql(hql, offset, pagesize);
	}
	
	//查询匹配到的用户数
	public Integer count(Problem problem){
		String hql = "select count(id)  from Problem  where 1=1  ";
		String part = this.genSql(problem);
		if(part!=null){
			hql += part;
		}
		return this.getCountByHql(hql);
	}
	
	private String genSql(Problem problem){
		String result = "";
		if(problem==null){
			return null;
		}else{		
			if(problem.getTitle()!=null&&problem.getTitle().length()>0){
				String title = C.getURLChinese(problem.getTitle());
				result += " and title  like '%"+title+"%' ";
			}
			if(problem.getContent()!=null&&problem.getContent().length()>0){
				String content = C.getURLChinese(problem.getContent());
				result += "  and content  like '%"+content+"%' ";
			}			
		}
		
		return result;
	}

	public Integer countById(int id) {
		// TODO Auto-generated method stub
		String hql = "select count(id) from Problem where scope like '%,"+ id +",%'   and  status=1";
		return this.getCountByHql(hql);
	}

	public List<Problem> findById(Integer id, Integer offset, Integer pagesize) {
		String hql = "from  Problem where scope like '%,"+id+",%'  and status=1 order by weight desc";
		return this.findPageByHql(hql, offset, pagesize);
	}
}
