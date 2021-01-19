package com.isd.service.common.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.isd.dao.common.IndustryDAO;
import com.isd.entity.common.Industry;
import com.isd.service.common.IndustryService;
import com.isd.util.C;


public class IndustryServiceImpl implements IndustryService{
	@Autowired
	private IndustryDAO industryDAO;
	
	String[] group = {"industry"};
	
	//根据ID返回对象
	public Industry findById(Integer id){
		HashMap<Integer, Industry> hash = new HashMap<Integer, Industry>();
		String key = "indid_hash";
		
		Object obj = C.getFromCache(key);
		if(obj!=null){
			hash = (HashMap<Integer, Industry>)obj;
		}else{
			List<Industry> industrylist = industryDAO.findAll();
			
			for(int i=0; i<industrylist.size(); i++){
				Industry industry = industrylist.get(i);
				hash.put(industry.getId(), industry);
			}
			C.saveToCache(key, hash, group);
		}
		return hash.get(id);
	}
	
	//城市列表
	public List<Industry> industryList(Integer parentid){
		List<Industry> list;
		String key = "industry_list_"+parentid;
		
		Object obj = C.getFromCache(key);
		if(obj!=null){
			list = (List<Industry>)obj;
		}else{
			list = industryDAO.industryList(parentid);
			C.saveToCache(key, list, group);
		}		
		return list;		
	}
	
	public Integer findIdByName(String name, Integer parentid){
		Industry industry = industryDAO.findByName(name, parentid);
		if(industry==null){
			industry = new Industry();
			industry.setName(name);
			industry.setParentid(parentid);
			Integer id = industryDAO.save(industry);
			industry.setId(id);
			
			C.flushGroup("industry");
		}
		return industry.getId();
	}
}
