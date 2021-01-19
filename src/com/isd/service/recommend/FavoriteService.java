package com.isd.service.recommend;

import java.util.List;

import com.isd.entity.recommend.Favorite;


public interface FavoriteService {
    //添加收藏
    public boolean add(Favorite favorite);
    //删除收藏
    public void delete(Integer id);

    //根据时间显示用户收藏的内容列表
    public List<Favorite> list(Integer userid, String module, Integer offset, Integer pagesize);

    //按照模块显示用户的收藏数量
    public Integer total(Integer userid, String module);

    //根据内容ID的列表返回内容
    public List<Favorite> findByIds(String idlist);

    public Favorite findById(Integer id);

    public void update(Favorite favorite);
}
