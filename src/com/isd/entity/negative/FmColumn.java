package com.isd.entity.negative;

import java.util.Date;


/**
 * FmColumn entity. @author MyEclipse Persistence Tools
 */

public class FmColumn  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer networkid;
     private String title;
     private String keyword;
     private Integer xwid;
     private Integer wbid;
     private Integer wxid;
     private Integer ltid;
     private Integer status;
     private Date ctime;
     private Date utime;
     private Date stime;


    // Constructors

    /** default constructor */
    public FmColumn() {
    }

    
    /** full constructor */
    public FmColumn(Integer networkid, String title, String keyword, Integer xwid, Integer wbid, Integer wxid, Integer ltid, Integer status, Date ctime, Date utime, Date stime) {
        this.networkid = networkid;
        this.title = title;
        this.keyword = keyword;
        this.xwid = xwid;
        this.wbid = wbid;
        this.wxid = wxid;
        this.ltid = ltid;
        this.status = status;
        this.ctime = ctime;
        this.utime = utime;
        this.stime = stime;
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


	public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public String getKeyword() {
        return this.keyword;
    }
    
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getXwid() {
        return this.xwid;
    }
    
    public void setXwid(Integer xwid) {
        this.xwid = xwid;
    }

    public Integer getWbid() {
        return this.wbid;
    }
    
    public void setWbid(Integer wbid) {
        this.wbid = wbid;
    }

    public Integer getWxid() {
        return this.wxid;
    }
    
    public void setWxid(Integer wxid) {
        this.wxid = wxid;
    }

    public Integer getLtid() {
        return this.ltid;
    }
    
    public void setLtid(Integer ltid) {
        this.ltid = ltid;
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

    public Date getStime() {
        return this.stime;
    }
    
    public void setStime(Date stime) {
        this.stime = stime;
    }
   








}