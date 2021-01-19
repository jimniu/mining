package com.isd.entity.common;



/**
 * City entity. @author MyEclipse Persistence Tools
 */

public class City  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String name;
     private Integer provid;


    // Constructors

    /** default constructor */
    public City() {
    }

	/** minimal constructor */
    public City(String name) {
        this.name = name;
    }
    
    /** full constructor */
    public City(String name, Integer provid) {
        this.name = name;
        this.provid = provid;
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

    public Integer getProvid() {
        return this.provid;
    }
    
    public void setProvid(Integer provid) {
        this.provid = provid;
    }
   








}