package com.isd.entity.mining;

import java.util.Date;

/**
 * Favorite entity. @author MyEclipse Persistence Tools
 */

public class Opinion implements java.io.Serializable {

	// Fields

	private Integer id;
	private Integer networkid;
	private String module;
	private Long pageid;
	private Integer type;
	private Date ptime;
	private Date ctime;

	// Constructors

	/** default constructor */
	public Opinion() {
	}

	/** full constructor */
	public Opinion(Integer networkid, String module, Long pageid, Integer type, Date ptime, Date ctime) {
		this.networkid = networkid;
		this.module = module;
		this.pageid = pageid;
		this.type = type;
		this.ptime = ptime;
		this.ctime = ctime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}



	public Integer getNetworkid() {
		return networkid;
	}

	public void setNetworkid(Integer networkid) {
		this.networkid = networkid;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public Long getPageid() {
		return pageid;
	}

	public void setPageid(Long pageid) {
		this.pageid = pageid;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Date getPtime() {
		return ptime;
	}

	public void setPtime(Date ptime) {
		this.ptime = ptime;
	}

	public Date getCtime() {
		return ctime;
	}

	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
}