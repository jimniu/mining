package com.isd.entity.tianya;

import java.util.Date;


/**
 * TyUser entity. @author MyEclipse Persistence Tools
 */

public class TyUser  implements java.io.Serializable {


    // Fields    

     private Long id;
     private Long tyid;
     private String nickname;
     private String photo;
     private String gender;
     private Integer score;
     private String location;
     private String school;
     private String company;
     private String tag;
     private String homepage;
     private Integer verified;
     private Integer follow;
     private Integer fans;
     private Integer topic;
     private Integer reply;
     private String intro;
     private Integer lcount;
     private Date ltime;
     private Date rtime;
     private Date ctime;
     private Date utime;


    // Constructors

    /** default constructor */
    public TyUser() {
    }

	/** minimal constructor */
    public TyUser(Long tyid, String homepage) {
        this.tyid = tyid;
        this.homepage = homepage;
    }
    
    /** full constructor */
    public TyUser(Long tyid, String nickname, String photo, String gender, Integer score, String location, String school, String company, String tag, String homepage, Integer verified, Integer follow, Integer fans, Integer topic, Integer reply, String intro, Integer lcount, Date ltime, Date rtime, Date ctime, Date utime) {
        this.tyid = tyid;
        this.nickname = nickname;
        this.photo = photo;
        this.gender = gender;
        this.score = score;
        this.location = location;
        this.school = school;
        this.company = company;
        this.tag = tag;
        this.homepage = homepage;
        this.verified = verified;
        this.follow = follow;
        this.fans = fans;
        this.topic = topic;
        this.reply = reply;
        this.intro = intro;
        this.lcount = lcount;
        this.ltime = ltime;
        this.rtime = rtime;
        this.ctime = ctime;
        this.utime = utime;
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

    public Integer getScore() {
        return this.score;
    }
    
    public void setScore(Integer score) {
        this.score = score;
    }

    public String getLocation() {
        return this.location;
    }
    
    public void setLocation(String location) {
        this.location = location;
    }

    public String getSchool() {
        return this.school;
    }
    
    public void setSchool(String school) {
        this.school = school;
    }

    public String getCompany() {
        return this.company;
    }
    
    public void setCompany(String company) {
        this.company = company;
    }

    public String getTag() {
        return this.tag;
    }
    
    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getHomepage() {
        return this.homepage;
    }
    
    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public Integer getVerified() {
        return this.verified;
    }
    
    public void setVerified(Integer verified) {
        this.verified = verified;
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

    public Integer getTopic() {
        return this.topic;
    }
    
    public void setTopic(Integer topic) {
        this.topic = topic;
    }

    public Integer getReply() {
        return this.reply;
    }
    
    public void setReply(Integer reply) {
        this.reply = reply;
    }

    public String getIntro() {
        return this.intro;
    }
    
    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Integer getLcount() {
        return this.lcount;
    }
    
    public void setLcount(Integer lcount) {
        this.lcount = lcount;
    }

    public Date getLtime() {
        return this.ltime;
    }
    
    public void setLtime(Date ltime) {
        this.ltime = ltime;
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