package com.isd.dao.mining.impl;

import java.util.List;

import com.isd.dao.GenericDAOImpl;
import com.isd.dao.mining.MessageDAO;
import com.isd.entity.manage.Message;


public class MessageDAOImpl extends GenericDAOImpl<Message, Integer> implements MessageDAO {
	
	public MessageDAOImpl() {
		super(Message.class);
	}

	//显示消息
	public List<Message> findLatest(){
		String hql = "from Message where type = 0  order by weight desc  limit 1";
		return findByHql(hql);
	}
	//列出匹配的结果列表
	public List<Message> list(Message message, Integer offset, Integer pagesize){
		String hql = "from Message  where 1=1  ";
		String part = this.genSql(message);
		if(part!=null){
			hql += part;
		}
		return this.findPageByHql(hql, offset, pagesize);
	}
	
	//查询匹配到的用户数
	public Integer count(Message message){
		String hql = "select count(*)  from Message  where 1=1  ";
		String part = this.genSql(message);
		if(part!=null){
			hql += part;
		}
		return this.getCountByHql(hql);
	}
	
	private String genSql(Message message){
		String result = "";
		if(message==null){
			return null;
		}else{		
			
			if(message.getTitle()!=null&&message.getTitle().length()>0){
				result += " and title  like '%"+message.getTitle()+"%' ";
			}
			if(message.getContent()!=null&&message.getContent().length()>0){
				result += "  and content  like '%"+message.getContent()+"%' ";
			}			
		}
		
		return result;
	}

	public Integer countById(int id) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Message where scope like '%,"+ id +",%'   and  status=1";
		return this.getCountByHql(hql);
	}

	public List<Message> findById(Integer id, Integer offset, Integer pagesize) {
		String hql = "from  Message where scope like '%,"+id+",%'  and status=1 order by weight desc";
		return this.findPageByHql(hql, offset, pagesize);
	}
}
