package com.isd.service.wechat;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.isd.entity.mining.Network;
import com.isd.entity.wechat.WxColumn;



public interface WxColumnService {
	public String findKeywordsByNetwork(Integer networkid);
	//根据用户ID搜索微信关键词设置
	public List<WxColumn> listByNetworkType(Integer networkid, Integer type);
	public List<WxColumn> listByNetworkTypeRelation(Integer networkid, Integer type, Integer relation);
	//根据用户以及内容类型提取栏目信息，存入哈希表
	public HashMap<Integer, WxColumn> hashByNetwork(Integer networkid, Integer type);
	
	//根据ID返回column对象
	public WxColumn findById(Integer columnid);	
	//保存微信设置
	public void save(HttpServletRequest request, Network network, String prefix);
	//根据搜索时间，提取搜索时间最早的设置
	public WxColumn findOneByFetchTime();
	//根据上次搜索的ID，提取ID最小的设置
	public WxColumn findOneByLastId();	
	//更新
	public void update(WxColumn setting);
	//搜索搜狗查询关键词
	public void refresh(String prefix, WxColumn column);	
}
