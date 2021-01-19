package com.isd.entity.mining;

import java.util.Date;


/**
 * Partner entity. @author MyEclipse Persistence Tools
 */

public class Partner  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String name;
     private String domain;
     private String logo;
     private String phone;
     private String title;
     private String banner;
     private String homebanner;
     private String footer;
     private Integer status;
     private Date utime;


    // Constructors

    /** default constructor */
    public Partner() {
    }

    
    /** full constructor */
    public Partner(String name, String domain, String logo, String phone, String title, String footer, Integer status, Date utime) {
        this.name = name;
        this.domain = domain;
        this.logo = logo;
        this.phone = phone;
        this.title = title;
        this.footer = footer;
        this.status = status;
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

    public String getDomain() {
        return this.domain;
    }
    
    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getLogo() {
        return this.logo;
    }
    
    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public String getFooter() {
        return this.footer;
    }
    
    public void setFooter(String footer) {
        this.footer = footer;
    }

    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getUtime() {
        return this.utime;
    }
    
    public void setUtime(Date utime) {
        this.utime = utime;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getHomebanner() {
        return homebanner;
    }

    public void setHomebanner(String homebanner) {
        this.homebanner = homebanner;
    }
}