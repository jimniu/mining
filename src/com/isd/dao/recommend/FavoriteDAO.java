package com.isd.dao.recommend;

import java.util.List;

import com.isd.dao.GenericDAO;
import com.isd.entity.recommend.Favorite;

public interface FavoriteDAO extends GenericDAO<Favorite, Integer>{
    //判断是否已经收藏此文
    public boolean exists(Integer userid, String module, Integer pageid);

    //根据时间显示用户收藏的内容列表
    public List<Favorite> list(Integer userid, String module, Integer offset, Integer pagesize);

    //按照模块显示用户的收藏数量
    public Integer total(Integer userid, String module);

    //根据内容ID的列表返回内容
    public List<Favorite> findByIds(String idlist);
}
