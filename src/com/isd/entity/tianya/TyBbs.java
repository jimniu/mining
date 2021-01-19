package com.isd.entity.tianya;

import java.util.Date;


/**
 * TyBbs entity. @author MyEclipse Persistence Tools
 */

public class TyBbs  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String name;
     private String symbol;
     private Date utime;


    // Constructors

    /** default constructor */
    public TyBbs() {
    }

    
    /** full constructor */
    public TyBbs(String name, String symbol, Date utime) {
        this.name = name;
        this.symbol = symbol;
        this.utime = utime;
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

    public String getSymbol() {
        return this.symbol;
    }
    
    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Date getUtime() {
        return this.utime;
    }
    
    public void setUtime(Date utime) {
        this.utime = utime;
    }
   








}