package com.isd.service.news;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.isd.entity.mining.Network;
import com.isd.entity.news.XwColumn;

public interface XwColumnService {
	public String findKeywordsByNetwork(Integer networkid);
	public XwColumn findById(Integer id);
	//根据用户ID搜索微信关键词设置
	public List<XwColumn> listByNetwork(Integer networkid, Integer type);
	public List<XwColumn> listByNetworkTrackRelation(Integer networkid, Integer type, Integer relation);
	//根据用户以及内容类型提取栏目信息，存入哈希表
	public HashMap<Integer, XwColumn>hashByNetwork(Integer networkid, Integer type);
	//根据搜索时间，提取搜索时间最早的设置
	public XwColumn findOneByFetchTime();
	//保存设置
	public void columnsave(HttpServletRequest request, Network network, String prefix);
	public void refresh(String prefix, XwColumn column);
}
