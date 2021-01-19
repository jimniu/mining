package com.isd.entity.weibo;

import java.util.Date;

/**
 * WbColumn entity. @author MyEclipse Persistence Tools
 */

public class WbColumn implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2438760279505259043L;
	// Fields

	private Integer id;
	private String title;
	private Integer relation;
	private Integer networkid;
	private String fetchword;
	private String searchword;
	private Integer modified;
	private Integer feedtotal;
	private Integer feedtoday;
	private Integer usertotal;
	private Integer usertoday;
	private Long fansum;
	private Long feedsum;
	private Integer hitsum;
	private Integer task;
	private Integer type;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getRelation() {
		return relation;
	}
	public void setRelation(Integer relation) {
		this.relation = relation;
	}
	public Integer getNetworkid() {
		return networkid;
	}
	public void setNetworkid(Integer networkid) {
		this.networkid = networkid;
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
	public Integer getModified() {
		return modified;
	}
	public void setModified(Integer modified) {
		this.modified = modified;
	}
	public Integer getFeedtotal() {
		return feedtotal;
	}
	public void setFeedtotal(Integer feedtotal) {
		this.feedtotal = feedtotal;
	}
	public Integer getFeedtoday() {
		return feedtoday;
	}
	public void setFeedtoday(Integer feedtoday) {
		this.feedtoday = feedtoday;
	}
	public Integer getUsertotal() {
		return usertotal;
	}
	public void setUsertotal(Integer usertotal) {
		this.usertotal = usertotal;
	}
	public Integer getUsertoday() {
		return usertoday;
	}
	public void setUsertoday(Integer usertoday) {
		this.usertoday = usertoday;
	}
	public Long getFansum() {
		return fansum;
	}
	public void setFansum(Long fansum) {
		this.fansum = fansum;
	}
	public Long getFeedsum() {
		return feedsum;
	}
	public void setFeedsum(Long feedsum) {
		this.feedsum = feedsum;
	}
	public Integer getHitsum() {
		return hitsum;
	}
	public void setHitsum(Integer hitsum) {
		this.hitsum = hitsum;
	}
	public Integer getTask() {
		return task;
	}
	public void setTask(Integer task) {
		this.task = task;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
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
	public Date getFtime() {
		return ftime;
	}
	public void setFtime(Date ftime) {
		this.ftime = ftime;
	}
	public Date getMtime() {
		return mtime;
	}
	public void setMtime(Date mtime) {
		this.mtime = mtime;
	}


}