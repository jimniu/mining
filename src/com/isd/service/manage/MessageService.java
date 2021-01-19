package com.isd.service.manage;

import java.util.List;

import com.isd.entity.manage.Message;

public interface MessageService {
	//列出匹配的结果列表
	public List<Message> list(Message message, Integer offset, Integer pagesize);
	//查询匹配到的用户数
	public Integer count(Message message);	
	//获取用户信息
	public Message getInfo(Integer id);	
	//删除用户
	public void delete(Integer id);
	//修改
	public void updateMessage(Message message);
	//添加
	public Integer save(Message message);
	
	public List<Message> findById(Integer id, Integer offset, Integer pagesize);
	
	public Integer countById(int id);
}
