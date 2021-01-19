package com.isd.dao.mark;

import java.util.List;

import com.isd.dao.GenericDAO;
import com.isd.entity.mark.ZmtContent;

public interface ZmtContentDAO extends GenericDAO<ZmtContent, Integer>{
	
	//列出匹配的结果列表
	public List<ZmtContent> list(Integer uid, Integer tagid, String kw, String from, String to, Integer offset, Integer pagesize);
	public List<ZmtContent> list(Integer uid, String kw, String from, String to, Integer offset, Integer pagesize);
	public List<ZmtContent> list(Integer offset, Integer pagesize);
	public List<ZmtContent> list(Integer uid);
	//查询匹配到的用户数
	public Integer count(Integer uid, Integer tagid, String kw, String from, String to);
	public Integer count(Integer uid, String kw, String from, String to);	
	public Integer count();
	
	//地址是否已经收藏
	public boolean exists(Integer uid, String url);
	//根据关键词统计数量
	public List countByKeyword(Integer uid);
	//根据tagid以及时间返回
	public List countByTime(Integer tagid,Integer time);
}
