package com.isd.entity.news;

import java.util.Date;


/**
 * XwPattern entity. @author MyEclipse Persistence Tools
 */

public class XwPattern  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String name;
     private Integer siteid;
     private Integer type;
     private String regex;
     private Integer serno;
     private Date ctime;


    // Constructors

    /** default constructor */
    public XwPattern() {
    }

    
    /** full constructor */
    public XwPattern(String name, Integer siteid, Integer type, String regex, Integer serno, Date ctime) {
        this.name = name;
        this.siteid = siteid;
        this.type = type;
        this.regex = regex;
        this.serno = serno;
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

    public Integer getSiteid() {
        return this.siteid;
    }
    
    public void setSiteid(Integer siteid) {
        this.siteid = siteid;
    }

    public Integer getType() {
        return this.type;
    }
    
    public void setType(Integer type) {
        this.type = type;
    }

    public String getRegex() {
        return this.regex;
    }
    
    public void setRegex(String regex) {
        this.regex = regex;
    }

    public Integer getSerno() {
        return this.serno;
    }
    
    public void setSerno(Integer serno) {
        this.serno = serno;
    }

    public Date getCtime() {
        return this.ctime;
    }
    
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
   








}