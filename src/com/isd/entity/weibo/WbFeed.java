package com.isd.entity.weibo;

import java.sql.Timestamp;
import java.util.Date;


/**
 * WbFeed entity. @author MyEclipse Persistence Tools
 */

public class WbFeed  implements java.io.Serializable {


    // Fields    

     private Long id;
     private Long wbid;
     private String content;
     private Long mid;
     private Long rid;
     private String url;
     private Integer forward;
     private Integer comment;
     private Integer praise;
     private Integer client;
     private Timestamp ptime;
     private Date ctime;


    // Constructors

    /** default constructor */
    public WbFeed() {
    }

	/** minimal constructor */
    public WbFeed(Long wbid) {
        this.wbid = wbid;
    }
    
    /** full constructor */
    public WbFeed(Long wbid, String content, Long mid, Long rid, String url, Integer forward, Integer comment, Integer praise, Integer client, Timestamp ptime, Date ctime) {
        this.wbid = wbid;
        this.content = content;
        this.mid = mid;
        this.rid = rid;
        this.url = url;
        this.forward = forward;
        this.comment = comment;
        this.praise = praise;
        this.client = client;
        this.ptime = ptime;
        this.ctime = ctime;
    }

   
    // Property accessors

    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public Long getWbid() {
        return this.wbid;
    }
    
    public void setWbid(Long wbid) {
        this.wbid = wbid;
    }

    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }

    public Long getMid() {
        return this.mid;
    }
    
    public void setMid(Long mid) {
        this.mid = mid;
    }

    public Long getRid() {
        return this.rid;
    }
    
    public void setRid(Long rid) {
        this.rid = rid;
    }

    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getForward() {
        return this.forward;
    }
    
    public void setForward(Integer forward) {
        this.forward = forward;
    }

    public Integer getComment() {
        return this.comment;
    }
    
    public void setComment(Integer comment) {
        this.comment = comment;
    }

    public Integer getPraise() {
        return this.praise;
    }
    
    public void setPraise(Integer praise) {
        this.praise = praise;
    }

    public Integer getClient() {
        return this.client;
    }
    
    public void setClient(Integer client) {
        this.client = client;
    }

    public Timestamp getPtime() {
        return this.ptime;
    }
    
    public void setPtime(Timestamp ptime) {
        this.ptime = ptime;
    }

    public Date getCtime() {
        return this.ctime;
    }
    
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
   








}