package com.isd.entity.news;

import java.util.Date;


/**
 * XwBlacklist entity. @author MyEclipse Persistence Tools
 */

public class XwBlacklist  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer networkid;
     private Integer siteid;
     private Date ctime;


    // Constructors

    /** default constructor */
    public XwBlacklist() {
    }

    
    /** full constructor */
    public XwBlacklist(Integer networkid, Integer siteid, Date ctime) {
        this.networkid = networkid;
        this.siteid = siteid;
        this.ctime = ctime;
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


	public Integer getSiteid() {
        return this.siteid;
    }
    
    public void setSiteid(Integer siteid) {
        this.siteid = siteid;
    }

    public Date getCtime() {
        return this.ctime;
    }
    
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }
   








}