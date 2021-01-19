package com.isd.entity.wechat;

import java.util.Date;

/**
 * WxResult entity. @author MyEclipse Persistence Tools
 */

public class WxResult implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 5717237058601055080L;
	
	private Integer id;
	private Integer networkid;
	private Integer wxid;
	private String wxname;
	private String logo;
	private Integer province;
	private Integer columnid;
	private Integer kwid;
	private Long contentid;
	private String title;
	private String url;
	private Integer serno;
	private Integer type;
	private Integer relation;
	private Integer readcount;
	private Integer likecount;
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

	public Integer getWxid() {
		return wxid;
	}

	public void setWxid(Integer wxid) {
		this.wxid = wxid;
	}

	public String getWxname() {
		return wxname;
	}

	public void setWxname(String wxname) {
		this.wxname = wxname;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public Integer getProvince() {
		return province;
	}

	public void setProvince(Integer province) {
		this.province = province;
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

	public Long getContentid() {
		return contentid;
	}

	public void setContentid(Long contentid) {
		this.contentid = contentid;
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

	public Integer getReadcount() {
		return readcount;
	}

	public void setReadcount(Integer readcount) {
		this.readcount = readcount;
	}

	public Integer getLikecount() {
		return likecount;
	}

	public void setLikecount(Integer likecount) {
		this.likecount = likecount;
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