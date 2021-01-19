package com.isd.service.mining;

import java.util.HashMap;
import java.util.List;

import com.isd.entity.mining.Network;
import com.isd.entity.mining.Setting;


public interface NetworkService {
	//根据ID来获取对象
	public Network findById(Integer id);
	//保存设置
	public int save(Network Network, Setting setting);
	//更新设置
	public void update(Network Network, Setting setting);
	//删除
	public void delete(Integer networkid);
	//列出账户
	public List<Network> list(int offset, int pagesize);
	public List<Network> list(Integer status, int offset, int pagesize);
	
	public HashMap<Integer, Network> hash();
	//账户数
	public Integer total();
	public Integer total(Integer status);
	//提取登录用户的账户
	public Network getMyNetwork();
}
