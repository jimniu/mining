package com.isd.entity.mining;

import java.util.Date;


/**
 * Daystat entity. @author MyEclipse Persistence Tools
 */

public class Daystat  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer networkid;
     private String module;
     private Integer columnid;
     private Integer category;
     private Integer type;
     private String date;
     private Integer mediatotal;
     private Integer pagetotal;
     private Date utime;


    // Constructors

    /** default constructor */
    public Daystat() {
    }

    
    /** full constructor */
    public Daystat(Integer networkid, String module, Integer columnid, Integer category, Integer type, String date, Integer mediatotal, Integer pagetotal, Date utime) {
        this.networkid = networkid;
        this.module = module;
        this.columnid = columnid;
        this.category = category;
        this.type = type;
        this.date = date;
        this.mediatotal = mediatotal;
        this.pagetotal = pagetotal;
        this.utime = utime;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }



    public Integer getNetworkid() {
		return networkid;
	}


	public void setNetworkid(Integer networkid) {
		this.networkid = networkid;
	}


	public String getModule() {
        return this.module;
    }
    
    public void setModule(String module) {
        this.module = module;
    }

    public Integer getColumnid() {
        return this.columnid;
    }
    
    public void setColumnid(Integer columnid) {
        this.columnid = columnid;
    }

    public Integer getCategory() {
        return this.category;
    }
    
    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getType() {
        return this.type;
    }
    
    public void setType(Integer type) {
        this.type = type;
    }

    public String getDate() {
        return this.date;
    }
    
    public void setDate(String date) {
        this.date = date;
    }

    public Integer getMediatotal() {
        return this.mediatotal;
    }
    
    public void setMediatotal(Integer mediatotal) {
        this.mediatotal = mediatotal;
    }

    public Integer getPagetotal() {
        return this.pagetotal;
    }
    
    public void setPagetotal(Integer pagetotal) {
        this.pagetotal = pagetotal;
    }

    public Date getUtime() {
        return this.utime;
    }
    
    public void setUtime(Date utime) {
        this.utime = utime;
    }
   








}