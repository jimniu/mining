package com.isd.service.manage.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.isd.dao.manage.MessageDAO;
import com.isd.entity.manage.Message;
import com.isd.service.manage.MessageService;


public class MessageServiceImpl implements MessageService{
	@Autowired 
	private MessageDAO messageDAO;
	
	//列出匹配的结果列表
	public List<Message> list(Message message, Integer offset, Integer pagesize){
		return messageDAO.list(message, offset, pagesize);
	}
	
	//查询匹配到的用户数
	public Integer count(Message message){
		return messageDAO.count(message);
	}

	//删除用户
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		messageDAO.delete(id);
	}
	
	public Message getInfo(Integer id) {
		// TODO Auto-generated method stub
		return messageDAO.get(id);
	}

	public void updateMessage(Message message) {
		// TODO Auto-generated method stub
		messageDAO.update(message);
	}

	public Integer save(Message message) {
		// TODO Auto-generated method stub
		return messageDAO.save(message);
	}

	public List<Message> findById(Integer id, Integer offset, Integer pagesize) {
		// TODO Auto-generated method stub
		return messageDAO.findById(id, offset, pagesize);
	}

	public Integer countById(int id) {
		// TODO Auto-generated method stub
		return messageDAO.countById(id);
	}
}
