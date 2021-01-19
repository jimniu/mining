package com.isd.entity.mark;

import java.util.Date;


/**
 * ZmtContenttag entity. @author MyEclipse Persistence Tools
 */

public class ZmtContenttag  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer uid;
     private Integer contentid;
     private Integer tagid;
     private Date ctime;


    // Constructors

    /** default constructor */
    public ZmtContenttag() {
    }

    
    /** full constructor */
    public ZmtContenttag(Integer uid, Integer contentid, Integer tagid, Date ctime) {
        this.uid = uid;
        this.contentid = contentid;
        this.tagid = tagid;
        this.ctime = ctime;
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

    public Integer getContentid() {
        return this.contentid;
    }
    
    public void setContentid(Integer contentid) {
        this.contentid = contentid;
    }

    public Integer getTagid() {
        return this.tagid;
    }
    
    public void setTagid(Integer tagid) {
        this.tagid = tagid;
    }

    public Date getCtime() {
        return this.ctime;
    }
    
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
   








}