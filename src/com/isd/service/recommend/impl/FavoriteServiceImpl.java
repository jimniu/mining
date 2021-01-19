package com.isd.service.recommend.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.isd.dao.bbs.LtResultDAO;
import com.isd.dao.mark.ZmtContentDAO;
import com.isd.dao.news.XwResultDAO;
import com.isd.dao.recommend.FavoriteDAO;
import com.isd.dao.wechat.WxResultDAO;
import com.isd.dao.weibo.WbResultDAO;
import com.isd.dao.weibo.WbUserDAO;
import com.isd.entity.bbs.LtResult;
import com.isd.entity.mark.ZmtContent;
import com.isd.entity.news.XwResult;
import com.isd.entity.recommend.Favorite;
import com.isd.entity.wechat.WxResult;
import com.isd.entity.weibo.WbResult;
import com.isd.entity.weibo.WbUser;
import com.isd.service.recommend.FavoriteService;
import com.isd.util.C;


public class FavoriteServiceImpl implements FavoriteService {
    @Autowired
    private FavoriteDAO favoriteDAO;
    @Autowired
    private XwResultDAO xwResultDAO;
    @Autowired
    private WxResultDAO wxResultDAO;
    @Autowired
    private WbResultDAO wbResultDAO;
    @Autowired
    private LtResultDAO ltResultDAO;
    @Autowired
    private WbUserDAO 	wbUserDAO;
    @Autowired
    private ZmtContentDAO zmtContentDAO;

    //添加收藏
    public boolean add(Favorite favorite){
        boolean ret = false;

        //判断是否已经收藏此文
        boolean existed = favoriteDAO.exists(favorite.getNetworkid(), favorite.getModule(), favorite.getPageid());
        if(!existed){
            Favorite favor = new Favorite();
            if(favorite.getModule().equals(C.MODULE_NEWS)){
                XwResult result = xwResultDAO.findById(favorite.getPageid());
                favor.setCtime(new Date());
                favor.setNetworkid(favorite.getNetworkid());
                favor.setModule(C.MODULE_NEWS);
                favor.setPageid(favorite.getPageid());
                favor.setPtime(result.getPtime());
                favor.setSiteid(result.getSiteid());
                favor.setSitename(result.getSitename());
                favor.setTitle(result.getTitle());
                favor.setUrl(result.getUrl());
                favor.setReadno(0);
                favor.setChosen(0);

                favoriteDAO.save(favor);
                ret = true;
            }else if(favorite.getModule().equals(C.MODULE_WECHAT)){
                WxResult result = wxResultDAO.findById(favorite.getPageid());

                favor.setCtime(new Date());
                favor.setNetworkid(favorite.getNetworkid());
                favor.setModule(C.MODULE_WECHAT);
                favor.setPageid(favorite.getPageid());
                favor.setPtime(result.getPtime());
                favor.setSiteid(result.getWxid());
                favor.setSitename(result.getWxname());
                favor.setTitle(result.getTitle());

                String url = result.getUrl();
                url = url.replaceAll("\\\\x26amp;", "&");
                if(url.length()<5){
                    favor.setUrl("../wechat/detail.shtml?result.id="+favorite.getPageid());
                }else {
                    favor.setUrl(url);
                }
                favor.setReadno(0);
                favor.setChosen(0);
                
                favoriteDAO.save(favor);
                ret = true;
            }else if(favorite.getModule().equals(C.MODULE_MARK)){
                ZmtContent result = zmtContentDAO.findById(favorite.getPageid());
                favor.setCtime(new Date());
                favor.setNetworkid(favorite.getNetworkid());
                favor.setModule(C.MODULE_MARK);
                favor.setPageid(favorite.getPageid());
                favor.setPtime(result.getCtime());
                favor.setSitename(result.getMedia());
                favor.setTitle(result.getTitle());
                if(result.getAddress()!=null&&result.getAddress().length()>0){
                	favor.setUrl(result.getAddress());
                }else{
                	favor.setUrl("../mark/result.shtml?id="+favorite.getPageid());
                }
                favor.setReadno(0);
                favor.setChosen(0);
                
                favoriteDAO.save(favor);
                ret = true;
            }else if(favorite.getModule().equals(C.MODULE_WEIBO)){
                WbResult result = wbResultDAO.findById(Long.valueOf(favorite.getPageid().toString()));
                WbUser wbuser = wbUserDAO.findByWbid(result.getWbid());                

                favor.setCtime(new Date());
                favor.setNetworkid(favorite.getNetworkid());
                favor.setModule(C.MODULE_WEIBO);
                favor.setPageid(favorite.getPageid());
                favor.setPtime(result.getPtime());
                favor.setSiteid(wbuser.getId());
                favor.setSitename(wbuser.getNickname());
                favor.setSiteurl("http://weibo.com"+wbuser.getHomepage());
                favor.setSiteimg(wbuser.getPhoto());
                favor.setTitle(C.subStr(result.getContent(), 200));
                favor.setUrl("http://weibo.com"+result.getUrl());
                favor.setReadno(0);
                favor.setChosen(0);
                
                favoriteDAO.save(favor);
                ret = true;
            }else if(favorite.getModule().equals(C.MODULE_BBS)){
                LtResult result = ltResultDAO.findById(favorite.getPageid());
                favor.setCtime(new Date());
                favor.setNetworkid(favorite.getNetworkid());
                favor.setModule(C.MODULE_BBS);
                favor.setPageid(favorite.getPageid());
                favor.setPtime(result.getPtime());
                favor.setSiteid(result.getSiteid());
                favor.setSitename(result.getSitename());
                favor.setTitle(result.getTitle());
                favor.setUrl(result.getUrl());
                favor.setReadno(0);
                favor.setChosen(0);

                favoriteDAO.save(favor);
                ret = true;
            }
        }

        return ret;
    }

    //删除收藏
    public void delete(Integer id){
        favoriteDAO.delete(id);
    }

    //根据时间显示用户收藏的内容列表
    public List<Favorite> list(Integer networkid, String module, Integer offset, Integer pagesize){
        return favoriteDAO.list(networkid, module, offset, pagesize);
    }

    //根据内容ID的列表返回内容
    public List<Favorite> findByIds(String idlist){
        return favoriteDAO.findByIds(idlist);
    }

    //按照模块显示用户的收藏数量
    public Integer total(Integer networkid, String module){
        return favoriteDAO.total(networkid, module);
    }

    public Favorite findById(Integer id){
        return favoriteDAO.findById(id);
    }

    public void update(Favorite favorite){
        favoriteDAO.update(favorite);
    }
}
