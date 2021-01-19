package com.isd.entity.weibo;



/**
 * WbUsertag entity. @author MyEclipse Persistence Tools
 */

public class WbUsertag  implements java.io.Serializable {


    // Fields    

     private Long id;
     private Long wbid;
     private Integer tagid;


    // Constructors

    /** default constructor */
    public WbUsertag() {
    }

    
    /** full constructor */
    public WbUsertag(Long wbid, Integer tagid) {
        this.wbid = wbid;
        this.tagid = tagid;
    }

   
    // Property accessors

    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public Long getWbid() {
        return this.wbid;
    }
    
    public void setWbid(Long wbid) {
        this.wbid = wbid;
    }

    public Integer getTagid() {
        return this.tagid;
    }
    
    public void setTagid(Integer tagid) {
        this.tagid = tagid;
    }
   








}