package com.isd.action.manage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.isd.action.GenericAction;
import com.isd.entity.mining.TUser;
import com.isd.service.manage.TUserService;

public class TUserAction extends GenericAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1;
	
	@Autowired
	private TUserService tuserService;
	private TUser tuser;
	private String temp;
	
	public String index(){ 
		List<TUser> list = tuserService.list(tuser, pageindex*12, 12);
		Integer total = tuserService.count(tuser);
		ctx.put("list", list);
		ctx.put("total", total.toString());				
		return SUCCESS;
	}
	
	public String delete(){ 
		int total = 0 ;
		if( temp!=null&&temp.length()>0){
			String[] aa = temp.split(",");
			total = aa.length;
		    for (int i = 0 ; i <aa.length ; i++ ) {		    
		      Integer id = Integer.parseInt(aa[i]);
		      tuserService.delete(id);		   
		    } 
			ctx.put("text", "删除了"+total+"条");
		}
		if(total<2){
			return HOME;
		}else{
			return TEXT;
		}
		
	}

	public TUser getTuser() {
		return tuser;
	}

	public void setTuser(TUser tuser) {
		this.tuser = tuser;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

}
