package com.isd.dao.recommend;

import java.util.List;

import com.isd.dao.GenericDAO;
import com.isd.entity.recommend.Recommend;

public interface RecommendDAO extends GenericDAO<Recommend, Integer>{
    //分页显示推荐列表
    public List<Recommend> listByUser(Integer userid, Integer offset, Integer pagesize);
    public List<Recommend> listByStatus(Integer status, Integer offset, Integer pagesize);
    public List<Recommend> listByDateRange(Integer userid, String from, String to);

    //按照模块显示用户的收藏数量
    public Integer totalByUser(Integer userid);
    public Integer totalByStatus(Integer status);

    //返回信息卡的历史消息
    public List<Recommend> findHistory(Integer userid, Integer recommendid, Integer offset, Integer pageesize);

    //当天的资讯卡数量
    public Integer available(Integer networkid);
}
