package com.isd.entity.mining;

import java.util.Date;


/**
 * Geostat entity. @author MyEclipse Persistence Tools
 */

public class Geostat  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer networkid;
     private String module;
     private Integer columnid;
     private Integer type;
     private String date;
     private Integer province;
     private Integer total;
     private Date utime;


    // Constructors

    /** default constructor */
    public Geostat() {
    }

    
    /** full constructor */
    public Geostat(Integer networkid, String module, Integer columnid, Integer type, String date, Integer province, Integer total, Date utime) {
        this.networkid = networkid;
        this.module = module;
        this.columnid = columnid;
        this.type = type;
        this.date = date;
        this.province = province;
        this.total = total;
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

    public Integer getProvince() {
        return this.province;
    }
    
    public void setProvince(Integer province) {
        this.province = province;
    }

    public Integer getTotal() {
        return this.total;
    }
    
    public void setTotal(Integer total) {
        this.total = total;
    }

    public Date getUtime() {
        return this.utime;
    }
    
    public void setUtime(Date utime) {
        this.utime = utime;
    }
   








}