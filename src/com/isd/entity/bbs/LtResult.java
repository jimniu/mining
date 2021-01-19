package com.isd.entity.bbs;

import java.util.Date;

/**
 * LtResult entity. @author MyEclipse Persistence Tools
 */

public class LtResult implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -4762816712825678869L;
	
	private Integer id;
	private Integer networkid;
	private Integer siteid;
	private String sitename;
	private String siteurl;
	private Integer columnid;
	private Integer kwid;
	private Long pageid;
	private String title;
	private String url;
	private Integer serno;
	private Integer type;
	private Integer relation;
	private Date ptime;
	private Date ctime;

	public Integer getId() {
		return id;
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

	public Integer getSiteid() {
		return siteid;
	}

	public void setSiteid(Integer siteid) {
		this.siteid = siteid;
	}

	public String getSitename() {
		return sitename;
	}

	public void setSitename(String sitename) {
		this.sitename = sitename;
	}

	public String getSiteurl() {
		return siteurl;
	}

	public void setSiteurl(String siteurl) {
		this.siteurl = siteurl;
	}

	public Integer getColumnid() {
		return columnid;
	}

	public void setColumnid(Integer columnid) {
		this.columnid = columnid;
	}

	public Integer getKwid() {
		return kwid;
	}

	public void setKwid(Integer kwid) {
		this.kwid = kwid;
	}

	public Long getPageid() {
		return pageid;
	}

	public void setPageid(Long pageid) {
		this.pageid = pageid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getSerno() {
		return serno;
	}

	public void setSerno(Integer serno) {
		this.serno = serno;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getRelation() {
		return relation;
	}

	public void setRelation(Integer relation) {
		this.relation = relation;
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