package com.isd.entity.mining;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Network entity. @author MyEclipse Persistence Tools
 */

public class Network implements java.io.Serializable {

	// Fields

	/**
	* 
	*/
	private static final long serialVersionUID = 1514814067370560543L;
	
	private Integer id;
	private String name;
	private Integer userid;
	private Integer partnerid;
	private String company;
	private String area;
	private String contact;
	private String phone;
	private Integer priority;
	private String setting;
	private Timestamp expire;
	private Integer type;
	private Integer status;
	private Date ctime;
	private Date utime;

	// Constructors

	/** default constructor */
	public Network() {
	}

	/** minimal constructor */
	public Network(Integer type) {
		this.type = type;
	}

	/** full constructor */
	public Network(String name, Integer userid, Integer partnerid, String company, String area, String contact,
			String phone, Integer priority, String setting, Timestamp expire, Integer type, Integer status, Date ctime, Date utime) {
		this.name = name;
		this.userid = userid;
		this.partnerid = partnerid;
		this.company = company;
		this.area = area;
		this.contact = contact;
		this.phone = phone;
		this.priority = priority;
		this.setting = setting;
		this.expire = expire;
		this.type = type;
		this.status = status;
		this.ctime = ctime;
		this.utime = utime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getPartnerid() {
		return this.partnerid;
	}

	public void setPartnerid(Integer partnerid) {
		this.partnerid = partnerid;
	}

	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getArea() {
		return this.area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getPriority() {
		return this.priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Timestamp getExpire() {
		return this.expire;
	}

	public void setExpire(Timestamp expire) {
		this.expire = expire;
	}

	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCtime() {
		return this.ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}

	public Date getUtime() {
		return this.utime;
	}

	public void setUtime(Date utime) {
		this.utime = utime;
	}

	public String getSetting() {
		return setting;
	}

	public void setSetting(String setting) {
		this.setting = setting;
	}
}