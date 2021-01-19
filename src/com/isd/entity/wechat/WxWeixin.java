package com.isd.entity.wechat;

import java.util.Date;


/**
 * WxWeixin entity. @author MyEclipse Persistence Tools
 */

public class WxWeixin  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String name;
     private String symbol;
     private String openid;
     private String url;
     private String intro;
     private String owner;
     private Integer verified;
     private String logo;
     private String barcode;
     private Integer categoryid;
     private Integer provinceid;
     private Date ctime;
     private Date utime;


    // Constructors

    /** default constructor */
    public WxWeixin() {
    }

	/** minimal constructor */
    public WxWeixin(String name, String openid) {
        this.name = name;
        this.openid = openid;
    }
    
    /** full constructor */
    public WxWeixin(String name, String symbol, String openid, String url, String intro, String owner, Integer verified, String logo, String barcode, Integer categoryid, Integer provinceid, Date ctime, Date utime) {
        this.name = name;
        this.symbol = symbol;
        this.openid = openid;
        this.url = url;
        this.intro = intro;
        this.owner = owner;
        this.verified = verified;
        this.logo = logo;
        this.barcode = barcode;
        this.categoryid = categoryid;
        this.provinceid = provinceid;
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

    public String getSymbol() {
        return this.symbol;
    }
    
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getOpenid() {
        return this.openid;
    }
    
    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }

    public String getIntro() {
        return this.intro;
    }
    
    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getOwner() {
        return this.owner;
    }
    
    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Integer getVerified() {
        return this.verified;
    }
    
    public void setVerified(Integer verified) {
        this.verified = verified;
    }

    public String getLogo() {
        return this.logo;
    }
    
    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getBarcode() {
        return this.barcode;
    }
    
    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public Integer getCategoryid() {
        return this.categoryid;
    }
    
    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public Integer getProvinceid() {
        return this.provinceid;
    }
    
    public void setProvinceid(Integer provinceid) {
        this.provinceid = provinceid;
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