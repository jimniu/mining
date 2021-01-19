package com.isd.entity.news;

import java.util.Date;

/**
 * XwColumn entity. @author MyEclipse Persistence Tools
 */

public class XwColumn implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2738575080900902041L;
	// Fields

	private Integer id;
	private Integer networkid;
	private Integer serno;
	private Integer type;
	private Integer relation;
	private String title;
	private String fetchword;
	private String searchword;
	private Integer modified;
	private Integer total;
	private Integer today;
	private Integer status;
	private Date ctime;
	private Date utime;
	private Date mtime;
	private Date ftime;
	
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFetchword() {
		return fetchword;
	}
	public void setFetchword(String fetchword) {
		this.fetchword = fetchword;
	}
	public String getSearchword() {
		return searchword;
	}
	public void setSearchword(String searchword) {
		this.searchword = searchword;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	public Integer getToday() {
		return today;
	}
	public void setToday(Integer today) {
		this.today = today;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCtime() {
		return ctime;
	}
	public void setCtime(Date ctime) {
		this.ctime = ctime;
	}
	public Date getUtime() {
		return utime;
	}
	public void setUtime(Date utime) {
		this.utime = utime;
	}
	public Date getMtime() {
		return mtime;
	}
	public void setMtime(Date mtime) {
		this.mtime = mtime;
	}
	public Date getFtime() {
		return ftime;
	}
	public void setFtime(Date ftime) {
		this.ftime = ftime;
	}
	public Integer getModified() {
		return modified;
	}
	public void setModified(Integer modified) {
		this.modified = modified;
	}
}