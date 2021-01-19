package com.isd.entity.manage;

import java.util.Date;


/**
 * AdminUser entity. @author MyEclipse Persistence Tools
 */

public class AdminUser  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String name;
     private String password;
     private String fullName;
     private String phone;
     private String email;
     private Integer type;
     private String scope;
     private String temp;
     private Integer status;
     private Date ctime;
     private String ipaddress;
     private Date utime;


    // Constructors

    /** default constructor */
    public AdminUser() {
    }

	/** minimal constructor */
    public AdminUser(String name, String password, String fullName, String phone, String email, Integer type, Date ctime) {
        this.name = name;
        this.password = password;
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.type = type;
        this.ctime = ctime;
    }
    
    /** full constructor */
    public AdminUser(String name, String password, String fullName, String phone, String email, Integer type, String scope, String temp, Integer status, Date ctime, String ipaddress, Date utime) {
        this.name = name;
        this.password = password;
        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.type = type;
        this.scope = scope;
        this.temp = temp;
        this.status = status;
        this.ctime = ctime;
        this.ipaddress = ipaddress;
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

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return this.fullName;
    }
    
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return this.phone;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getType() {
        return this.type;
    }
    
    public void setType(Integer type) {
        this.type = type;
    }

    public String getScope() {
        return this.scope;
    }
    
    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getTemp() {
        return this.temp;
    }
    
    public void setTemp(String temp) {
        this.temp = temp;
    }

    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCtime() {
        return this.ctime;
    }
    
    public void setCtime(Date ctime) {
        this.ctime = ctime;
    }

    public String getIpaddress() {
        return this.ipaddress;
    }
    
    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

    public Date getUtime() {
        return this.utime;
    }
    
    public void setUtime(Date utime) {
        this.utime = utime;
    }
   








}