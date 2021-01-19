package com.isd.entity.event;

import java.util.Date;


/**
 * SjColumn entity. @author MyEclipse Persistence Tools
 */

public class SjColumn  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer networkid;
     private String title;
     private String occurred;
     private String province;
     private String city;
     private String role;
     private String intro;
     private String keyword;
     private Integer modified;
     private Integer serno;
     private Integer xwid;
     private Integer wbid;
     private Integer wxid;
     private Integer ltid;
     private String status;
     private Date ctime;
     private Date utime;
     private Date stime;


    // Constructors

    /** default constructor */
    public SjColumn() {
    }

    
    /** full constructor */
    public SjColumn(Integer networkid, String title, String occurred, String province, String city, String role, String intro, String keyword, Integer serno, Integer xwid, Integer wbid, Integer wxid, Integer ltid, String status, Date ctime, Date utime, Date stime) {
        this.networkid = networkid;
        this.title = title;
        this.occurred = occurred;
        this.province = province;
        this.city = city;
        this.role = role;
        this.intro = intro;
        this.keyword = keyword;
        this.serno = serno;
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

    public String getOccurred() {
        return this.occurred;
    }
    
    public void setOccurred(String occurred) {
        this.occurred = occurred;
    }

    public String getProvince() {
        return this.province;
    }
    
    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return this.city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }

    public String getRole() {
        return this.role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }

    public String getIntro() {
        return this.intro;
    }
    
    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getKeyword() {
        return this.keyword;
    }
    
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getSerno() {
        return this.serno;
    }
    
    public void setSerno(Integer serno) {
        this.serno = serno;
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

    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
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


	public Integer getModified() {
		return modified;
	}


	public void setModified(Integer modified) {
		this.modified = modified;
	}
   








}