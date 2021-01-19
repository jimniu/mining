package com.isd.dao.manage.impl;

import java.util.List;

import com.isd.dao.GenericDAOImpl;
import com.isd.dao.manage.MessageDAO;
import com.isd.entity.manage.Message;
import com.isd.util.C;


public class MessageDAOImpl extends GenericDAOImpl<Message, Integer> implements MessageDAO {
	
	public MessageDAOImpl() {
		super(Message.class);
	}

	//列出匹配的结果列表
	public List<Message> list(Message message, Integer offset, Integer pagesize){
		String hql = "from Message  where 1=1  ";
		String part = this.genSql(message);
		if(part!=null){
			hql += part;
		}
		hql += " order by ctime desc";
		return this.findPageByHql(hql, offset, pagesize);
	}
	
	//查询匹配到的用户数
	public Integer count(Message message){
		String hql = "select count(id)  from Message  where 1=1  ";
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
				String title = C.getURLChinese(message.getTitle());
				result += " and title  like '%"+title+"%' ";
			}
			if(message.getContent()!=null&&message.getContent().length()>0){
				String content = C.getURLChinese(message.getContent());
				result += "  and content  like '%"+content+"%' ";
			}			
		}
		
		return result;
	}

	public Integer countById(int id) {
		// TODO Auto-generated method stub
		String hql = "select count(id) from Message where scope like '%,"+ id +",%'   and  status=1";
		return this.getCountByHql(hql);
	}

	public List<Message> findById(Integer id, Integer offset, Integer pagesize) {
		String hql = "from  Message where scope like '%,"+id+",%'  and status=1 order by weight desc";
		return this.findPageByHql(hql, offset, pagesize);
	}
}
