package com.isd.entity.mining;

import java.util.Date;


/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class TUser  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String name;
     private String company;
     private String mobile;
     private String tellphone;
     private String email;
     private Date ctime;
     private String ipaddr;   
     
     
	public TUser() {
		super();
	}
	
	public TUser(Integer id, String name, String company, String mobile, String tellphone,
			String email, Date ctime, String ipaddr) {
		super();
		this.id = id;
		this.name = name;
		this.company = company;
		this.mobile = mobile;
		this.tellphone = tellphone;
		this.email = email;
		this.ctime = ctime;
		this.ipaddr = ipaddr;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getCtime() {
		return ctime;
	}
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	public String getIpaddr() {
		return ipaddr;
	}
	public void setIpaddr(String ipaddr) {
		this.ipaddr = ipaddr;
	}

	public String getTellphone() {
		return tellphone;
	}

	public void setTellphone(String tellphone) {
		this.tellphone = tellphone;
	}
 
	
}