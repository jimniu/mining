package com.isd.service.bbs;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.isd.entity.bbs.LtColumn;
import com.isd.entity.mining.Network;


public interface LtColumnService {
	public String findKeywordsByNetwork(Integer userid);
	//根据用户ID以及类型搜索微信关键词设置
	public List<LtColumn> listByNetwork(Integer userid, int type);
	public List<LtColumn> listByNetworkTypeRelation(Integer userid, Integer type, Integer relation);
	//根据用户以及内容类型提取栏目信息，存入哈希表
	public HashMap<Integer, LtColumn>hashByNetwork(Integer userid, Integer type);
	
	//根据ID返回column对象
	public LtColumn findById(Integer columnid);
	
	//保存微信设置
	public void save(HttpServletRequest request, Network network, String prefix);
	//根据搜索时间，提取搜索时间最早的设置
	public LtColumn findOneByFetchTime();
	//根据上次搜索的ID，提取ID最小的设置
	public LtColumn findOneByLastId();	
	//更新
	public void update(LtColumn setting);
	//搜索搜狗查询关键词
	public void refresh(String prefix, LtColumn column);		
}
