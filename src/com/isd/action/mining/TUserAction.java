package com.isd.action.mining;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.isd.action.GenericAction;
import com.isd.entity.mining.TUser;
import com.isd.service.mining.TUserService;
import com.isd.util.C;
import com.isd.util.SendMail;


public class TUserAction extends GenericAction{

	/**
	 * 
	 */
	private static final long serialVersionUID  = 1L;
	
	@Autowired
	private TUserService tuserService;
	
	private TUser tuser;
	private String verifyCode;
	
	public String add(){
		String checkCode = (String)session.getAttribute("verifyCode");
		if(!verifyCode.equals(checkCode)){
			ctx.put("text", "验证码不正确");			
		} else {
			if(tuser!=null){
				tuser.setCtime(new Date());
				tuser.setIpaddr(request.getRemoteAddr());
				tuserService.add(tuser);
				String content ="姓名："+tuser.getName()+"<br/>公司："+tuser.getCompany()+"<br/>手机："+tuser.getMobile()+"<br/>固定电话："+tuser.getTellphone()+"<br/>邮箱："+tuser.getEmail();
				SendMail sm = new SendMail("smtp.dig24.cn");	
				//sm.sendMail(C.MAIL_FROM,C.MAIL_FROMNAME, "yiming.jiang@ishowdata.com", null, null, "大数据在线申请", content, C.MAIL_ACCOUNT, C.MAIL_PASSOWRD, "");
				sm.sendMail(C.MAIL_FROM,C.MAIL_FROMNAME, "james.li@ishowdata.com", null, null, "大数据在线申请", content, C.MAIL_ACCOUNT, C.MAIL_PASSOWRD, "");
				sm.sendMail(C.MAIL_FROM,C.MAIL_FROMNAME, "jon.liu@ishowdata.com", null, null, "大数据在线申请", content, C.MAIL_ACCOUNT, C.MAIL_PASSOWRD, "");
			}
			ctx.put("text", "SUCCESS");		
		}	
		return TEXT;
	}

	public TUser getTuser() {
		return tuser;
	}

	public void setTuser(TUser tuser) {
		this.tuser = tuser;
	}

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}
	
}
