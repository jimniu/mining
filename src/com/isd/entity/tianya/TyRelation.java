package com.isd.entity.tianya;



/**
 * TyRelation entity. @author MyEclipse Persistence Tools
 */

public class TyRelation  implements java.io.Serializable {


    // Fields    

     private Long id;
     private Long tyid;
     private Long fansid;


    // Constructors

    /** default constructor */
    public TyRelation() {
    }

    
    /** full constructor */
    public TyRelation(Long tyid, Long fansid) {
        this.tyid = tyid;
        this.fansid = fansid;
    }

   
    // Property accessors

    public Long getId() {
        return this.id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }

    public Long getTyid() {
        return this.tyid;
    }
    
    public void setTyid(Long tyid) {
        this.tyid = tyid;
    }

    public Long getFansid() {
        return this.fansid;
    }
    
    public void setFansid(Long fansid) {
        this.fansid = fansid;
    }
   








}