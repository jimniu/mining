package com.isd.service.recommend.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.isd.dao.recommend.RecommendDAO;
import com.isd.entity.mining.Network;
import com.isd.entity.mining.Setting;
import com.isd.entity.recommend.Recommend;
import com.isd.service.recommend.RecommendService;


public class RecommendServiceImpl implements RecommendService {
    @Autowired
    private RecommendDAO recommendDAO;

    public Integer save(Recommend recommend){
        recommend.setReadno(0);
        recommend.setPraise(0);
        recommend.setShareno(0);
        recommend.setCtime(new Date());
        return recommendDAO.save(recommend);
    }

    public void update(Recommend recommend){
        Recommend recomm = recommendDAO.findById(recommend.getId());

        recomm.setTitle(recommend.getTitle());
        recomm.setImgurl(recommend.getImgurl());
        recomm.setSummary(recommend.getSummary());
        recomm.setIdlist(recommend.getIdlist());
        recomm.setReward(recommend.getReward());
        recomm.setClkurl(recommend.getClkurl());
        recomm.setStatus(recommend.getStatus());
        recomm.setType(recommend.getType());
        recomm.setUtime(new Date());
        recommendDAO.update(recomm);
    }

    //分页显示推荐列表
    public List<Recommend> listByUser(Integer networkid, Integer offset, Integer pagesize){
        return recommendDAO.listByUser(networkid, offset, pagesize);
    }
    public List<Recommend> listByStatus(Integer status, Integer offset, Integer pagesize){
        return recommendDAO.listByStatus(status, offset, pagesize);
    }
    
    public List<Recommend> listByDateRange(Integer networkid, String from, String to){
    	return recommendDAO.listByDateRange(networkid, from, to);
    }

    //按照模块显示用户的收藏数量
    public Integer totalByUser(Integer networkid){
        return recommendDAO.totalByUser(networkid);
    }
    public Integer totalByStatus(Integer status){
        return recommendDAO.totalByStatus(status);
    }

    //根据ID返回recommend的内容
    public Recommend findById(Integer id){
        return recommendDAO.findById(id);
    }

    //返回信息卡的历史消息
    public List<Recommend> findHistory(Integer networkid, Integer recommendid, Integer offset, Integer pageesize){
        return recommendDAO.findHistory(networkid, recommendid, offset, pageesize);
    }

    //删除推荐
    public void delete(Integer id){
        recommendDAO.delete(id);
    }

    //增加分享次数
    public void share(Integer id){
        Recommend recomm = recommendDAO.findById(id);
        Integer shareno = recomm.getShareno()==null?1:recomm.getShareno()+1;
        recomm.setShareno(shareno);
        recommendDAO.update(recomm);
    }

    //当天是否超过资讯卡的上限
    public boolean available(Network network){
        Integer totalToday = recommendDAO.available(network.getId());
        Setting setting = Setting.json2obj(network.getSetting());
        if(totalToday<setting.getDailycard()||setting.getDailycard()==-1){
            return true;
        }else{
            return false;
        }
    }
}
