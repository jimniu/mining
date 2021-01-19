package com.isd.entity.recommend;

import java.util.Date;


/**
 * Favorite entity. @author MyEclipse Persistence Tools
 */

public class Favorite  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Integer networkid;
     private String module;
     private Integer siteid;
     private String sitename;
     private String siteimg;
     private String siteurl;
     private Integer pageid;
     private String title;
     private String url;
     private String comment;
     private Integer readno;
     private Integer chosen;
     private Date ptime;
     private Date ctime;


    // Constructors

    /** default constructor */
    public Favorite() {
    }

    
    /** full constructor */
    public Favorite(Integer networkid, String module, Integer siteid, String sitename, String siteurl, Integer pageid, String title, String url, Date ptime, Date ctime) {
        this.networkid = networkid;
        this.module = module;
        this.siteid = siteid;
        this.sitename = sitename;
        this.siteurl = siteurl;
        this.pageid = pageid;
        this.title = title;
        this.url = url;
        this.ptime = ptime;
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


	public String getModule() {
        return this.module;
    }
    
    public void setModule(String module) {
        this.module = module;
    }

    public Integer getSiteid() {
        return this.siteid;
    }
    
    public void setSiteid(Integer siteid) {
        this.siteid = siteid;
    }

    public String getSitename() {
        return this.sitename;
    }
    
    public void setSitename(String sitename) {
        this.sitename = sitename;
    }

    public String getSiteurl() {
        return this.siteurl;
    }
    
    public void setSiteurl(String siteurl) {
        this.siteurl = siteurl;
    }

    public Integer getPageid() {
        return this.pageid;
    }
    
    public void setPageid(Integer pageid) {
        this.pageid = pageid;
    }

    public String getTitle() {
        return this.title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }

    public Date getPtime() {
        return this.ptime;
    }
    
    public void setPtime(Date ptime) {
        this.ptime = ptime;
    }

    public Date getCtime() {
        return this.ctime;
    }
    
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public Integer getReadno() {
        return readno;
    }

    public void setReadno(Integer readno) {
        this.readno = readno;
    }


	public String getSiteimg() {
		return siteimg;
	}


	public void setSiteimg(String siteimg) {
		this.siteimg = siteimg;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}


	public Integer getChosen() {
		return chosen;
	}


	public void setChosen(Integer chosen) {
		this.chosen = chosen;
	}
}