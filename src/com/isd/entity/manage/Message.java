package com.isd.entity.manage;

import java.util.Date;


/**
 * Message entity. @author MyEclipse Persistence Tools
 */

public class Message  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer aid;
     private String title;
     private String content;
     private Integer type;
     private String scope;
     private Integer weight;
     private Integer status;
     private Date ctime;
     private Date utime;


    // Constructors

    /** default constructor */
    public Message() {
    }

	/** minimal constructor */
    public Message(Integer aid, String content, Integer type, Integer weight, Integer status, Date ctime) {
        this.aid = aid;
        this.content = content;
        this.type = type;
        this.weight = weight;
        this.status = status;
        this.ctime = ctime;
    }
    
    /** full constructor */
    public Message(Integer aid, String title, String content, Integer type, String scope, Integer weight, Integer status, Date ctime, Date utime) {
        this.aid = aid;
        this.title = title;
        this.content = content;
        this.type = type;
        this.scope = scope;
        this.weight = weight;
        this.status = status;
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

    public Integer getAid() {
        return this.aid;
    }
    
    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }

    public Integer getType() {
        return this.type;
    }
    
    public void setType(Integer type) {
        this.type = type;
    }

    public String getScope() {
        return this.scope;
    }
    
    public void setScope(String scope) {
        this.scope = scope;
    }

    public Integer getWeight() {
        return this.weight;
    }
    
    public void setWeight(Integer weight) {
        this.weight = weight;
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
   








}