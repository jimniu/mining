package com.isd.entity.mark;

import java.util.Date;


/**
 * ZmtTag entity. @author MyEclipse Persistence Tools
 */

public class ZmtTag  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer uid;
     private String value;
     private Date ctime;


    // Constructors

    /** default constructor */
    public ZmtTag() {
    }

    
    /** full constructor */
    public ZmtTag(Integer uid, String value, Date ctime) {
        this.uid = uid;
        this.value = value;
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

    public String getValue() {
        return this.value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }

    public Date getCtime() {
        return this.ctime;
    }
    
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
   








}