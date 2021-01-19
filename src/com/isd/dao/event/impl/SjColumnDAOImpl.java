package com.isd.dao.event.impl;

import java.util.List;

import com.isd.dao.GenericDAOImpl;
import com.isd.dao.event.SjColumnDAO;
import com.isd.entity.event.SjColumn;


public class SjColumnDAOImpl extends GenericDAOImpl<SjColumn, Integer> implements SjColumnDAO {
	public SjColumnDAOImpl() {
		super(SjColumn.class);
	}
	
	//根据用户返回任务
	public List<SjColumn> findByNetwork(Integer networkid, Integer offset, Integer pagesize){
		String hql = "from SjColumn where networkid="+networkid+" order by serno asc, ctime desc";
		return this.findPageByHql(hql, offset, pagesize);
	}
	
	public String findKeywordsByUser(Integer networkid){
		String sql = "select group_concat(keyword) from mining.sj_column where networkid="+networkid+" and status=1";
		List result = this.findBySql(sql);
		if(result.size()>0&&result.get(0)!=null){
			return result.get(0).toString();
		}else{
			return "";
		}		
	}
	
	//根据用户的搜索条件返回任务
	public List<SjColumn> findByNetwork(Integer networkid, SjColumn column, Integer offset, Integer pagesize){
		String hql = "from SjColumn where networkid="+networkid;
		if(column.getTitle().length()>0){
			hql += " and title like '%"+column.getTitle()+"%'";
		}
		if(column.getOccurred().length()>0){
			hql += " and occurred like '"+column.getOccurred()+"%'";
		}
		hql += " order by serno asc, ctime desc";
		return this.findPageByHql(hql, offset, pagesize);
	}
	

	//根据提取时间返回栏目
	public SjColumn findOneBySummarizeTime(){
		String hql = "from SjColumn order by stime asc";
		List<SjColumn> list = this.findPageByHql(hql, 0, 1);
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	//根据模块及ID，返回事件对象
	public SjColumn findByModule(String module, Integer columnid){
		String hql = "from SjColumn where "+module+"id="+columnid;
		List<SjColumn> list = this.findByHql(hql);
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
}
