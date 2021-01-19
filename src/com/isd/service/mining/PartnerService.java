package com.isd.service.mining;

import java.util.HashMap;
import java.util.List;

import com.isd.entity.mining.Partner;


public interface PartnerService {	
	//根据访问的域名来提取合作伙伴
	public Partner findByUrl(String url);
	//根据ID来获取对象
	public Partner findById(Integer id);
	//保存设置
	public int save(Partner partner);
	//更新设置
	public void update(Partner partner);
	//列出合作伙伴
	public List<Partner> list(int offset, int pagesize);
	public HashMap<Integer, Partner> hash();
}
