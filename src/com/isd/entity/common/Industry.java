package com.isd.entity.common;



/**
 * Industry entity. @author MyEclipse Persistence Tools
 */

public class Industry  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String name;
     private Integer parentid;


    // Constructors

    /** default constructor */
    public Industry() {
    }

	/** minimal constructor */
    public Industry(String name) {
        this.name = name;
    }
    
    /** full constructor */
    public Industry(String name, Integer parentid) {
        this.name = name;
        this.parentid = parentid;
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

    public Integer getParentid() {
        return this.parentid;
    }
    
    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }
   








}