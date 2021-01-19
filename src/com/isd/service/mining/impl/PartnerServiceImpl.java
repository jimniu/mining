package com.isd.service.mining.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.isd.dao.mining.PartnerDAO;
import com.isd.entity.mining.Partner;
import com.isd.service.mining.PartnerService;
import com.isd.util.C;


public class PartnerServiceImpl implements PartnerService{
	@Autowired 
	private PartnerDAO partnerDAO;
	private String[]group = {"partner"};
	
	//根据访问的域名来提取合作伙伴
	public Partner findByUrl(String domain){
		Partner partner;
		
		String key = "partner_"+domain;
		
		Object cache = C.getFromCache(key, 3600);
		if(cache!=null){
			partner = (Partner)cache;
		}else{
			//根据域名搜索个性化定义
			partner = partnerDAO.findByUrl(domain);
			//如果没有搜到合适的数据，则加载递客网的数据
			if(partner==null){
				partner = partnerDAO.findById(1);
			}			
			C.saveToCache(key, partner, group);
		}		

		return partner;
	}
	
	//保存设置
	public int save(Partner partner){
		String key = "partner_"+partner.getDomain();
		partner.setUtime(new Date());
		partner.setStatus(1);
		int id = partnerDAO.save(partner);
		C.flushGroup("partner");
		return id;
	}
	
	//更新设置
	public void update(Partner partner){
		Partner part = partnerDAO.findById(partner.getId());
		part.setLogo(partner.getLogo());
		part.setFooter(partner.getFooter());
		part.setPhone(partner.getPhone());
		part.setName(partner.getName());
		part.setTitle(partner.getTitle());
		part.setBanner(partner.getBanner());
		part.setHomebanner(partner.getHomebanner());
		part.setDomain(partner.getDomain());
		part.setUtime(new Date());
		part.setStatus(partner.getStatus());
		partnerDAO.update(part);
		
		String key = "partner_"+partner.getDomain();
		C.flushGroup("partner");		
	}
	
	//列出合作伙伴
	public List<Partner> list(int offset, int pagesize){
		return partnerDAO.list(offset, pagesize);		
	}
	
	//根据ID来获取对象
	public Partner findById(Integer id){
		return partnerDAO.findById(id);
	}

	public HashMap<Integer, Partner> hash(){
		String key = "partner_hashmap";
		HashMap<Integer, Partner> hash;

		Object cache = C.getFromCache(key, 3600);
		if(cache!=null){
			hash = (HashMap<Integer, Partner>)cache;
		}else {
			hash = new HashMap<Integer, Partner>();
			List<Partner> list = partnerDAO.list(0, 9999999);
			for (int i = 0; i < list.size(); i++) {
				Partner partner = list.get(i);
				hash.put(partner.getId(), partner);
			}
			C.saveToCache(key, hash, group);
		}
		return hash;
	}
}
