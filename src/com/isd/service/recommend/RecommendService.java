package com.isd.service.recommend;

import java.util.List;

import com.isd.entity.mining.Network;
import com.isd.entity.recommend.Recommend;


public interface RecommendService {
    public Integer save(Recommend recommend);

    public void update(Recommend recommend);

    //分页显示推荐列表
    public List<Recommend> listByDateRange(Integer userid, String from, String to);
    public List<Recommend> listByUser(Integer userid, Integer offset, Integer pagesize);
    public List<Recommend> listByStatus(Integer status, Integer offset, Integer pagesize);

    //按照模块显示用户的收藏数量
    public Integer totalByUser(Integer userid);
    public Integer totalByStatus(Integer userid);

    //根据ID返回recommend的内容
    public Recommend findById(Integer id);

    //返回信息卡的历史消息
    public List<Recommend> findHistory(Integer userid, Integer recommendid, Integer offset, Integer pageesize);

    //删除推荐
    public void delete(Integer id);
    //增加分享次数
    public void share(Integer id);

    //当天是否超过资讯卡的上限
    public boolean available(Network network);
}
