package com.isd.action.manage;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.isd.action.GenericAction;
import com.isd.entity.manage.AdminUser;
import com.isd.entity.manage.Message;
import com.isd.entity.mining.Network;
import com.isd.entity.mining.User;
import com.isd.service.manage.MessageService;
import com.isd.service.mining.NetworkService;
import com.isd.service.mining.UserService;
import com.isd.util.C;

public class MessageAction extends GenericAction{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private MessageService messageService;
	@Autowired
	private UserService userService;
	@Autowired
	private NetworkService networkService;
	private Message message;
	
	//公告管理首页
	public String index(){ 		
		List<Message> list = messageService.list(message, pageindex*12, 12);
		Integer total = messageService.count(message);
		ctx.put("list", list);
		ctx.put("total", total.toString());	
		if(message!=null){
			if(message.getTitle()!=null){
				ctx.put("temptitle",C.getURLChinese(message.getTitle()));
			}
			if(message.getContent()!=null){
				ctx.put("tempcontent",C.getURLChinese(message.getContent()));
			}	
		}
		return SUCCESS;
	}

	//添加页面
	public String add(){
 		List<User> list = userService.findAll();
 		ctx.put("list", list);
		return SUCCESS;
	}
	
	//保存
	public String save(){
		AdminUser au = (AdminUser)session.getAttribute(C.SESSION_ADMINUSER);
		message.setAid(au.getId());
		message.setCtime(new Date());
		messageService.save(message);
		return SUCCESS;
	}
	
	//删除
	public String delete(){
		if(message.getScope()!=null&&message.getScope().length()>0){
			String temp = message.getScope();
			String[] aa = temp.split(",");
			int total = aa.length;
		    for (int i = 0 ; i <aa.length ; i++ ) {		    
		      Integer id = Integer.parseInt(aa[i]);
		      messageService.delete(id);		   
		    } 
			ctx.put("text", "删除了"+total+"条");
		}
		return TEXT;
	}
	
	//获得信息
	public String getInfo(){ 
		Integer id = message.getId();		
		Message mess = messageService.getInfo(id);
		session.setAttribute("mess", mess);
		List<Network> list = networkService.list(0, 10000);
 		ctx.put("list", list);
		return SUCCESS;
	}

	//修改
	public String update(){
		Message ms = (Message)session.getAttribute("mess");
		ms.setTitle(message.getTitle());
		ms.setContent(message.getContent());
		ms.setStatus(message.getStatus());
		ms.setWeight(message.getWeight());
		ms.setScope(message.getScope());
		ms.setType(message.getType());
		ms.setUtime(new Date());
		messageService.updateMessage(ms);
		return SUCCESS;
	}
	
	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}
}
