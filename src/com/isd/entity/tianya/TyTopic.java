package com.isd.entity.tianya;

import java.util.Date;


/**
 * TyTopic entity. @author MyEclipse Persistence Tools
 */

public class TyTopic  implements java.io.Serializable {


    // Fields    

     private Long id;
     private String title;
     private Long tyid;
     private Long topicid;
     private String symbol;
     private Integer pageno;
     private Date rtime;
     private Date ctime;
     private Date utime;


    // Constructors

    /** default constructor */
    public TyTopic() {
    }

	/** minimal constructor */
    public TyTopic(Long topicid) {
        this.topicid = topicid;
    }
    
    /** full constructor */
    public TyTopic(String title, Long tyid, Long topicid, String symbol, Integer pageno, Date rtime, Date ctime, Date utime) {
        this.title = title;
        this.tyid = tyid;
        this.topicid = topicid;
        this.symbol = symbol;
        this.pageno = pageno;
        this.rtime = rtime;
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

    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
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

    public String getSymbol() {
        return this.symbol;
    }
    
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Integer getPageno() {
        return this.pageno;
    }
    
    public void setPageno(Integer pageno) {
        this.pageno = pageno;
    }

    public Date getRtime() {
        return this.rtime;
    }
    
    public void setRtime(Date rtime) {
        this.rtime = rtime;
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