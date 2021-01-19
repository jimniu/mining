package com.isd.entity.mining;

import java.util.Date;


/**
 * User entity. @author MyEclipse Persistence Tools
 */

public class User  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String name;
     private String password;
     private Integer networkid;
     private String email;
     private String mobile;
     private String ipaddr;
     private Date expire;
     private Integer type;
     private Integer status;
     private Date ctime;
     private Date utime;


    // Constructors

    /** default constructor */
    public User() {
    }

	/** minimal constructor */
    public User(String password, Integer networkid) {
        this.password = password;
        this.networkid = networkid;
    }
    
    /** full constructor */
    public User(String name, String password, Integer networkid, String email, String mobile, String ipaddr, Date expire, Integer type, Integer status, Date ctime, Date utime) {
        this.name = name;
        this.password = password;
        this.networkid = networkid;
        this.email = email;
        this.mobile = mobile;
        this.ipaddr = ipaddr;
        this.expire = expire;
        this.type = type;
        this.status = status;
        this.ctime = ctime;
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

    public Integer getNetworkid() {
        return this.networkid;
    }
    
    public void setNetworkid(Integer networkid) {
        this.networkid = networkid;
    }

    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return this.mobile;
    }
    
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getIpaddr() {
        return this.ipaddr;
    }
    
    public void setIpaddr(String ipaddr) {
        this.ipaddr = ipaddr;
    }

    public Date getExpire() {
        return this.expire;
    }
    
    public void setExpire(Date expire) {
        this.expire = expire;
    }

    public Integer getType() {
        return this.type;
    }
    
    public void setType(Integer type) {
        this.type = type;
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

    public Date getUtime() {
        return this.utime;
    }
    
    public void setUtime(Date utime) {
        this.utime = utime;
    }
   








}