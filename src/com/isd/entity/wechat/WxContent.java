package com.isd.entity.wechat;

import java.sql.Timestamp;
import java.util.Date;


/**
 * WxContent entity. @author MyEclipse Persistence Tools
 */

public class WxContent  implements java.io.Serializable {


    // Fields    

     private Long id;
     private Integer wxid;
     private Integer kwid;
     private String title;
     private String url;
     private String link;
     private String content;
     private Integer readcount;
     private Integer likecount;
     private Date ptime;
     private Date ftime;
     private Timestamp utime;


    // Constructors

    /** default constructor */
    public WxContent() {
    }

	/** minimal constructor */
    public WxContent(Integer wxid, String title, String url) {
        this.wxid = wxid;
        this.title = title;
        this.url = url;
    }
    
    /** full constructor */
    public WxContent(Integer wxid, Integer kwid, String title, String url, String link, String content, Integer readcount, Integer likecount, Date ptime, Date ftime, Timestamp utime) {
        this.wxid = wxid;
        this.kwid = kwid;
        this.title = title;
        this.url = url;
        this.link = link;
        this.content = content;
        this.readcount = readcount;
        this.likecount = likecount;
        this.ptime = ptime;
        this.ftime = ftime;
        this.utime = utime;
    }

   
    // Property accessors

    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public Integer getWxid() {
        return this.wxid;
    }
    
    public void setWxid(Integer wxid) {
        this.wxid = wxid;
    }

    public Integer getKwid() {
        return this.kwid;
    }
    
    public void setKwid(Integer kwid) {
        this.kwid = kwid;
    }

    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }

    public String getLink() {
        return this.link;
    }
    
    public void setLink(String link) {
        this.link = link;
    }

    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }

    public Integer getReadcount() {
        return this.readcount;
    }
    
    public void setReadcount(Integer readcount) {
        this.readcount = readcount;
    }

    public Integer getLikecount() {
        return this.likecount;
    }
    
    public void setLikecount(Integer likecount) {
        this.likecount = likecount;
    }

    public Date getPtime() {
        return this.ptime;
    }
    
    public void setPtime(Date ptime) {
        this.ptime = ptime;
    }

    public Date getFtime() {
        return this.ftime;
    }
    
    public void setFtime(Date ftime) {
        this.ftime = ftime;
    }

    public Timestamp getUtime() {
        return this.utime;
    }
    
    public void setUtime(Timestamp utime) {
        this.utime = utime;
    }
   








}