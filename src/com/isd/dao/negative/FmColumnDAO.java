package com.isd.dao.negative;

import java.util.List;

import com.isd.dao.GenericDAO;
import com.isd.entity.negative.FmColumn;


public interface FmColumnDAO extends GenericDAO<FmColumn, Integer>{
	//根据用户返回所有任务
	public List<FmColumn> findByNetwork(Integer userid, Integer offset, Integer pagesize);
	//根据用户返回指定状态的任务
	public List<FmColumn> findByNetwork(Integer userid, Integer status, Integer offset, Integer pagesize);
	public String findKeywordsByUser(Integer userid);
	//根据提取时间返回栏目
	public FmColumn findOneBySummarizeTime();	
	//根据模块及ID，返回事件对象
	public FmColumn findByModule(String module, Integer columnid);	
}
