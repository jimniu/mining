package com.isd.util.queue;

import java.util.Date;

/**
 * Message entity. @author MyEclipse Persistence Tools
 */

public class Queue implements java.io.Serializable {
	// Fields

	/**
	* 
	*/
	private static final long serialVersionUID = 3370424589042098356L;
	// Fields
 	private Integer networkid;
 	private Integer columnid;
 	private String module;
 	private String fetchword;
 	private String searchword;
 	private Integer type;
 	private Integer serno;
 	private Integer relation;
 	private Integer priority;
 	private Date startpoint;
 	private String scope;
 	private Integer status;
 	private Integer task;
	
	
	public Integer getSerno() {
		return serno;
	}
	public void setSerno(Integer serno) {
		this.serno = serno;
	}
	public Integer getNetworkid() {
		return networkid;
	}
	public void setNetworkid(Integer networkid) {
		this.networkid = networkid;
	}
	public Integer getColumnid() {
		return columnid;
	}
	public void setColumnid(Integer columnid) {
		this.columnid = columnid;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
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
	public Integer getPriority() {
		return priority;
	}
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	public Date getStartpoint() {
		return startpoint;
	}
	public void setStartpoint(Date startpoint) {
		this.startpoint = startpoint;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getTask() {
		return task;
	}
	public void setTask(Integer task) {
		this.task = task;
	}
	
}