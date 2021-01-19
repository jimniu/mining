package com.isd.entity.mark;

import java.util.Date;


/**
 * ZmtContent entity. @author MyEclipse Persistence Tools
 */

public class ZmtContent  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer uid;
     private String title;
     private String address;
     private String content;
     private String media;
     private String method;
     private String comment;
     private String tag;
     private Date ctime;
     private Date utime;


    // Constructors

    /** default constructor */
    public ZmtContent() {
    }

	/** minimal constructor */
    public ZmtContent(Integer uid) {
        this.uid = uid;
    }
    
    /** full constructor */
    public ZmtContent(Integer uid, String title, String address, String content, String media, String method, String comment, String tag, Date ctime, Date utime) {
        this.uid = uid;
        this.title = title;
        this.address = address;
        this.content = content;
        this.media = media;
        this.method = method;
        this.comment = comment;
        this.tag = tag;
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

    public Integer getUid() {
        return this.uid;
    }
    
    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }

    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }

    public String getMedia() {
        return this.media;
    }
    
    public void setMedia(String media) {
        this.media = media;
    }

    public String getMethod() {
        return this.method;
    }
    
    public void setMethod(String method) {
        this.method = method;
    }

    public String getComment() {
        return this.comment;
    }
    
    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTag() {
        return this.tag;
    }
    
    public void setTag(String tag) {
        this.tag = tag;
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