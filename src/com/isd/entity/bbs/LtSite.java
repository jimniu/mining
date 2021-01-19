package com.isd.entity.bbs;

import java.util.Date;


/**
 * LtSite entity. @author MyEclipse Persistence Tools
 */

public class LtSite  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String name;
     private String url;
     private Date ctime;


    // Constructors

    /** default constructor */
    public LtSite() {
    }

    
    /** full constructor */
    public LtSite(String name, String url, Date ctime) {
        this.name = name;
        this.url = url;
        this.ctime = ctime;
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

    public Date getCtime() {
        return this.ctime;
    }
    
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
   








}