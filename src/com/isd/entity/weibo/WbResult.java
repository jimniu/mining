package com.isd.entity.weibo;

import java.util.Date;


/**
 * WbResult entity. @author MyEclipse Persistence Tools
 */

public class WbResult  implements java.io.Serializable {


    // Fields    

     private Long id;
     private Integer networkid;
     private Integer columnid;
     private Long wbid;
     private String content;
     private Long mid;
     private String url;
     private Integer forward;
     private Integer comment;
     private Integer praise;
     private Integer fansrange;
     private String client;
     private String gender;
     private Integer level;
     private Integer province;
     private Integer city;
     private String vtype;
     private Integer type;
     private Integer relation;
     private Date ptime;
     private Date ctime;


    // Constructors

    /** default constructor */
    public WbResult() {
    }

	/** minimal constructor */
    public WbResult(Long wbid) {
        this.wbid = wbid;
    }
    
    /** full constructor */
    public WbResult(Integer networkid, Integer columnid, Long wbid, String content, Long mid, String url, Integer forward, Integer comment, Integer praise, String client, String gender, Integer level, Integer province, Integer city, String vtype, Integer type, Integer relation, Date ptime, Date ctime) {
        this.networkid = networkid;
        this.columnid = columnid;
        this.wbid = wbid;
        this.content = content;
        this.mid = mid;
        this.url = url;
        this.forward = forward;
        this.comment = comment;
        this.praise = praise;
        this.client = client;
        this.gender = gender;
        this.level = level;
        this.province = province;
        this.city = city;
        this.vtype = vtype;
        this.type = type;
        this.relation = relation;
        this.ptime = ptime;
        this.ctime = ctime;
    }

   
    // Property accessors

    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }


    public Integer getNetworkid() {
		return networkid;
	}

	public void setNetworkid(Integer networkid) {
		this.networkid = networkid;
	}

	public Integer getColumnid() {
        return this.columnid;
    }
    
    public void setColumnid(Integer columnid) {
        this.columnid = columnid;
    }

    public Long getWbid() {
        return this.wbid;
    }
    
    public void setWbid(Long wbid) {
        this.wbid = wbid;
    }

    public String getContent() {
        return this.content;
    }
    
    public void setContent(String content) {
        this.content = content;
    }

    public Long getMid() {
        return this.mid;
    }
    
    public void setMid(Long mid) {
        this.mid = mid;
    }

    public String getUrl() {
        return this.url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getForward() {
        return this.forward;
    }
    
    public void setForward(Integer forward) {
        this.forward = forward;
    }

    public Integer getComment() {
        return this.comment;
    }
    
    public void setComment(Integer comment) {
        this.comment = comment;
    }

    public Integer getPraise() {
        return this.praise;
    }
    
    public void setPraise(Integer praise) {
        this.praise = praise;
    }

    public String getClient() {
        return this.client;
    }
    
    public void setClient(String client) {
        this.client = client;
    }

    public String getGender() {
        return this.gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getLevel() {
        return this.level;
    }
    
    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getProvince() {
        return this.province;
    }
    
    public void setProvince(Integer province) {
        this.province = province;
    }

    public Integer getCity() {
        return this.city;
    }
    
    public void setCity(Integer city) {
        this.city = city;
    }

    public String getVtype() {
        return this.vtype;
    }
    
    public void setVtype(String vtype) {
        this.vtype = vtype;
    }

    public Integer getType() {
        return this.type;
    }
    
    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getRelation() {
        return this.relation;
    }
    
    public void setRelation(Integer relation) {
        this.relation = relation;
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

    public Integer getFansrange() {
        return fansrange;
    }

    public void setFansrange(Integer fansrange) {
        this.fansrange = fansrange;
    }
}