package com.isd.dao.event;

import java.util.List;

import com.isd.dao.GenericDAO;
import com.isd.entity.event.SjColumn;


public interface SjColumnDAO extends GenericDAO<SjColumn, Integer>{
	//根据用户返回任务
	public List<SjColumn> findByNetwork(Integer userid, Integer offset, Integer pagesize);
	//根据用户的搜索条件返回任务
	public List<SjColumn> findByNetwork(Integer userid, SjColumn column, Integer offset, Integer pagesize);
	public String findKeywordsByUser(Integer userid);
	//根据提取时间返回栏目
	public SjColumn findOneBySummarizeTime();	
	
	//根据模块及ID，返回事件对象
	public SjColumn findByModule(String module, Integer columnid);
}
