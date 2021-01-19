package com.isd.entity.recommend;

import java.util.Date;


/**
 * Recommend entity. @author MyEclipse Persistence Tools
 */

public class Recommend  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer networkid;
     private String title;
     private String imgurl;
     private String clkurl;
     private String summary;
     private String idlist;
     private Integer reward;
     private Integer readno;
     private Integer praise;
     private Integer shareno;
     private Integer type;
     private Integer status;
     private Date ctime;
     private Date utime;


    // Constructors

    /** default constructor */
    public Recommend() {
    }

    
    /** full constructor */
    public Recommend(Integer networkid, String title, String imgurl, String clkurl, String summary, String idlist, Integer reward, Integer readno, Integer praise, Integer shareno, Integer type, Integer status, Date ctime, Date utime) {
        this.networkid = networkid;
        this.title = title;
        this.imgurl = imgurl;
        this.clkurl = clkurl;
        this.summary = summary;
        this.idlist = idlist;
        this.reward = reward;
        this.readno = readno;
        this.praise = praise;
        this.shareno = shareno;
        this.type = type;
        this.status = status;
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


    public Integer getNetworkid() {
		return networkid;
	}


	public void setNetworkid(Integer networkid) {
		this.networkid = networkid;
	}


	public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgurl() {
        return this.imgurl;
    }
    
    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getClkurl() {
        return this.clkurl;
    }
    
    public void setClkurl(String clkurl) {
        this.clkurl = clkurl;
    }

    public String getSummary() {
        return this.summary;
    }
    
    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getIdlist() {
        return this.idlist;
    }
    
    public void setIdlist(String idlist) {
        this.idlist = idlist;
    }

    public Integer getReward() {
        return this.reward;
    }
    
    public void setReward(Integer reward) {
        this.reward = reward;
    }

    public Integer getReadno() {
        return this.readno;
    }
    
    public void setReadno(Integer readno) {
        this.readno = readno;
    }

    public Integer getPraise() {
        return this.praise;
    }
    
    public void setPraise(Integer praise) {
        this.praise = praise;
    }

    public Integer getShareno() {
        return this.shareno;
    }
    
    public void setShareno(Integer shareno) {
        this.shareno = shareno;
    }

    public Integer getType() {
        return this.type;
    }
    
    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
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