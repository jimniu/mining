package com.isd.entity.weibo;



/**
 * WbRelation entity. @author MyEclipse Persistence Tools
 */

public class WbRelation  implements java.io.Serializable {


    // Fields    

     private Long id;
     private Long wbid;
     private Long fansid;


    // Constructors

    /** default constructor */
    public WbRelation() {
    }

    
    /** full constructor */
    public WbRelation(Long wbid, Long fansid) {
        this.wbid = wbid;
        this.fansid = fansid;
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

    public Long getFansid() {
        return this.fansid;
    }
    
    public void setFansid(Long fansid) {
        this.fansid = fansid;
    }
   








}