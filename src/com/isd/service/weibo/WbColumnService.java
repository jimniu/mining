package com.isd.service.weibo;

import java.util.HashMap;
import java.util.List;

import com.isd.entity.mining.Network;
import com.isd.entity.weibo.WbColumn;



public interface WbColumnService {
	//列出用户的任务
	public List<WbColumn> listByNetwork(Integer networkid, Integer type);
	public String findKeywordsByNetwork(Integer networkid);
	
	//根据用户以及内容类型提取栏目信息，存入哈希表
	public HashMap<Integer, WbColumn> hashByNetwork(Integer networkid, Integer type);
	
	//根据任务ID返回任务
	public WbColumn findById(Integer id);
	//根据迫切程度返回任务
	public WbColumn findByLastid();	
	//保存任务
	public void save(WbColumn task, Network network);
	//删除任务
	public void delete(Integer taskid);
	//根据迫切程度返回任务
	public WbColumn findByFetchTime();	
	public void refresh(WbColumn task);
}
