package com.isd.entity.common;

import java.util.Date;


/**
 * Category entity. @author MyEclipse Persistence Tools
 */

public class Category  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String name;
     private Date ctime;
     private Date utime;


    // Constructors

    /** default constructor */
    public Category() {
    }

	/** minimal constructor */
    public Category(String name) {
        this.name = name;
    }
    
    /** full constructor */
    public Category(String name, Date ctime, Date utime) {
        this.name = name;
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

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
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