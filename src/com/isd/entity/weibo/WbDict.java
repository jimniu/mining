package com.isd.entity.weibo;



/**
 * WbDict entity. @author MyEclipse Persistence Tools
 */

public class WbDict  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String type;
     private String name;


    // Constructors

    /** default constructor */
    public WbDict() {
    }

	/** minimal constructor */
    public WbDict(String name) {
        this.name = name;
    }
    
    /** full constructor */
    public WbDict(String type, String name) {
        this.type = type;
        this.name = name;
    }

   
    // Property accessors

    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return this.type;
    }
    
    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
   








}