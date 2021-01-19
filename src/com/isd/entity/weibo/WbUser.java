package com.isd.entity.weibo;

import java.util.Date;


/**
 * WbUser entity. @author MyEclipse Persistence Tools
 */

public class WbUser  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private Long wbid;
     private String nickname;
     private String photo;
     private String gender;
     private Integer level;
     private Integer province;
     private Integer city;
     private Integer school;
     private Integer company;
     private Integer industry;
     private Integer subindustry;
     private Integer title;
     private String email;
     private String homepage;
     private String tag;
     private String vtype;
     private Integer follow;
     private Integer fans;
     private Integer feed;
     private String intro;
     private Integer active;
     private Date rtime;
     private Date ctime;
     private Date utime;


    // Constructors

    /** default constructor */
    public WbUser() {
    }

	/** minimal constructor */
    public WbUser(String homepage) {
        this.homepage = homepage;
    }
    
    /** full constructor */
    public WbUser(Long wbid, String nickname, String photo, String gender, Integer level, Integer province, Integer city, Integer school, Integer company, Integer industry, Integer subindustry, Integer title, String email, String homepage, String tag, String vtype, Integer follow, Integer fans, Integer feed, String intro, Integer active, Date rtime, Date ctime, Date utime) {
        this.wbid = wbid;
        this.nickname = nickname;
        this.photo = photo;
        this.gender = gender;
        this.level = level;
        this.province = province;
        this.city = city;
        this.school = school;
        this.company = company;
        this.industry = industry;
        this.subindustry = subindustry;
        this.title = title;
        this.email = email;
        this.homepage = homepage;
        this.tag = tag;
        this.vtype = vtype;
        this.follow = follow;
        this.fans = fans;
        this.feed = feed;
        this.intro = intro;
        this.active = active;
        this.rtime = rtime;
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

    public Long getWbid() {
        return this.wbid;
    }
    
    public void setWbid(Long wbid) {
        this.wbid = wbid;
    }

    public String getNickname() {
        return this.nickname;
    }
    
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhoto() {
        return this.photo;
    }
    
    public void setPhoto(String photo) {
        this.photo = photo;
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

    public Integer getSchool() {
        return this.school;
    }
    
    public void setSchool(Integer school) {
        this.school = school;
    }

    public Integer getCompany() {
        return this.company;
    }
    
    public void setCompany(Integer company) {
        this.company = company;
    }

    public Integer getIndustry() {
        return this.industry;
    }
    
    public void setIndustry(Integer industry) {
        this.industry = industry;
    }

    public Integer getSubindustry() {
        return this.subindustry;
    }
    
    public void setSubindustry(Integer subindustry) {
        this.subindustry = subindustry;
    }

    public Integer getTitle() {
        return this.title;
    }
    
    public void setTitle(Integer title) {
        this.title = title;
    }

    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public String getHomepage() {
        return this.homepage;
    }
    
    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public String getTag() {
        return this.tag;
    }
    
    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getVtype() {
        return this.vtype;
    }
    
    public void setVtype(String vtype) {
        this.vtype = vtype;
    }

    public Integer getFollow() {
        return this.follow;
    }
    
    public void setFollow(Integer follow) {
        this.follow = follow;
    }

    public Integer getFans() {
        return this.fans;
    }
    
    public void setFans(Integer fans) {
        this.fans = fans;
    }

    public Integer getFeed() {
        return this.feed;
    }
    
    public void setFeed(Integer feed) {
        this.feed = feed;
    }

    public String getIntro() {
        return this.intro;
    }
    
    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Integer getActive() {
        return this.active;
    }
    
    public void setActive(Integer active) {
        this.active = active;
    }

    public Date getRtime() {
        return this.rtime;
    }
    
    public void setRtime(Date rtime) {
        this.rtime = rtime;
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