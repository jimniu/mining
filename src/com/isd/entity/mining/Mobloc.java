package com.isd.entity.mining;

import java.util.Date;


/**
 * Mobloc entity. @author MyEclipse Persistence Tools
 */

public class Mobloc  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer mobile;
     private String province;
     private String city;
     private Date ctime;


    // Constructors

    /** default constructor */
    public Mobloc() {
    }

    
    /** full constructor */
    public Mobloc(Integer mobile, String province, String city, Date ctime) {
        this.mobile = mobile;
        this.province = province;
        this.city = city;
        this.ctime = ctime;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMobile() {
        return this.mobile;
    }
    
    public void setMobile(Integer mobile) {
        this.mobile = mobile;
    }

    public String getProvince() {
        return this.province;
    }
    
    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return this.city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }

    public Date getCtime() {
        return this.ctime;
    }
    
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
   








}