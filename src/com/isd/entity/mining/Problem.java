package com.isd.entity.mining;

import java.util.Date;


/**
 * Problem entity. @author MyEclipse Persistence Tools
 */

public class Problem  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer aid;
     private String title;
     private String content;
     private Integer weight;
     private Integer status;
     private Date ctime;
     private Date utime;


    // Constructors

    /** default constructor */
    public Problem() {
    }

	/** minimal constructor */
    public Problem(Integer aid, String content, Integer weight, Integer status, Date ctime) {
        this.aid = aid;
        this.content = content;
        this.weight = weight;
        this.status = status;
        this.ctime = ctime;
    }
    
    /** full constructor */
    public Problem(Integer aid, String title, String content, Integer weight, Integer status, Date ctime, Date utime) {
        this.aid = aid;
        this.title = title;
        this.content = content;
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