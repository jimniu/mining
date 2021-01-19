package com.isd.dao.negative.impl;

import java.util.List;

import com.isd.dao.GenericDAOImpl;
import com.isd.dao.negative.FmColumnDAO;
import com.isd.entity.negative.FmColumn;


public class FmColumnDAOImpl extends GenericDAOImpl<FmColumn, Integer> implements FmColumnDAO {
	public FmColumnDAOImpl() {
		super(FmColumn.class);
	}
	
	//根据用户返回任务
	public List<FmColumn> findByNetwork(Integer networkid, Integer offset, Integer pagesize){
		String hql = "from FmColumn where networkid="+networkid+" order by ctime desc";
		return this.findPageByHql(hql, offset, pagesize);
	}
	//根据用户返回指定状态的任务
	public List<FmColumn> findByNetwork(Integer networkid, Integer status, Integer offset, Integer pagesize){
		String hql = "from FmColumn where networkid="+networkid+" and status="+status+" order by ctime asc";
		return this.findPageByHql(hql, offset, pagesize);		
	}
	
	public String findKeywordsByUser(Integer networkid){
		String sql = "select group_concat(keyword) from mining.fm_column where networkid="+networkid+" and status=1";
		List result = this.findBySql(sql);
		if(result.size()>0&&result.get(0)!=null){
			return result.get(0).toString();
		}else{
			return "";
		}		
	}
	
	//根据提取时间返回栏目
	public FmColumn findOneBySummarizeTime(){
		String hql = "from FmColumn order by stime asc";
		List<FmColumn> list = this.findPageByHql(hql, 0, 1);
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	//根据模块及ID，返回事件对象
	public FmColumn findByModule(String module, Integer columnid){
		String hql = "from FmColumn where "+module+"id="+columnid;
		List<FmColumn> list = this.findByHql(hql);
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}	
}
