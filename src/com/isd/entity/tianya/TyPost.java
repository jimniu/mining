package com.isd.entity.tianya;

import java.util.Date;


/**
 * TyPost entity. @author MyEclipse Persistence Tools
 */

public class TyPost  implements java.io.Serializable {


    // Fields    

     private Long id;
     private Long tyid;
     private Long topicid;
     private String content;
     private Integer type;
     private String client;
     private Date ctime;
     private Date utime;


    // Constructors

    /** default constructor */
    public TyPost() {
    }

	/** minimal constructor */
    public TyPost(Long tyid, Long topicid) {
        this.tyid = tyid;
        this.topicid = topicid;
    }
    
    /** full constructor */
    public TyPost(Long tyid, Long topicid, String content, Integer type, String client, Date ctime, Date utime) {
        this.tyid = tyid;
        this.topicid = topicid;
        this.content = content;
        this.type = type;
        this.client = client;
        this.ctime = ctime;
        this.utime = utime;
    }

   
    // Property accessors

    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public Long getTyid() {
        return this.tyid;
    }
    
    public void setTyid(Long tyid) {
        this.tyid = tyid;
    }

    public Long getTopicid() {
        return this.topicid;
    }
    
    public void setTopicid(Long topicid) {
        this.topicid = topicid;
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

    public String getClient() {
        return this.client;
    }
    
    public void setClient(String client) {
        this.client = client;
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