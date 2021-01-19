package com.isd.entity.news;

import java.util.Date;


/**
 * XwSite entity. @author MyEclipse Persistence Tools
 */

public class XwSite  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String name;
     private String url;
     private Integer categoryid;
     private Integer provinceid;
     private Date ctime;
     private Date utime;


    // Constructors

    /** default constructor */
    public XwSite() {
    }

    
    /** full constructor */
    public XwSite(String name, String url, Integer categoryid, Integer provinceid, Date ctime, Date utime) {
        this.name = name;
        this.url = url;
        this.categoryid = categoryid;
        this.provinceid = provinceid;
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

    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getCategoryid() {
        return this.categoryid;
    }
    
    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public Integer getProvinceid() {
        return this.provinceid;
    }
    
    public void setProvinceid(Integer provinceid) {
        this.provinceid = provinceid;
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
   








}