package com.isd.dao.manage;

import java.util.List;

import com.isd.dao.GenericDAO;
import com.isd.entity.manage.Message;

public interface MessageDAO extends GenericDAO<Message, Integer>{
	
	//列出匹配的结果列表
	public List<Message> list(Message message, Integer offset, Integer pagesize);
	//查询匹配到的用户数
	public Integer count(Message message);
	
	public List<Message> findById(Integer id, Integer offset, Integer pagesize);
	
	public Integer countById(int id);
}
