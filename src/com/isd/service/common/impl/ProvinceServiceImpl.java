package com.isd.service.common.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.isd.dao.common.ProvinceDAO;
import com.isd.entity.common.Province;
import com.isd.service.common.ProvinceService;
import com.isd.util.C;


public class ProvinceServiceImpl implements ProvinceService{
	@Autowired
	private ProvinceDAO provinceDAO;
	
	//根据名称，返回对象
	public Province findByName(String name){
		HashMap<String, Province> hash = new HashMap<String, Province>();
		String key = "provname_hash";
		
		Object obj = C.getFromCache(key);
		if(obj!=null){
			hash = (HashMap<String, Province>)obj;
		}else{
			List<Province> provlist = provinceDAO.provinceList();
			
			for(int i=0; i<provlist.size(); i++){
				Province prov = provlist.get(i);
				hash.put(prov.getName(), prov);
			}
			C.saveToCache(key, hash);
		}
		return hash.get(name);
	}
	
	//根据ID返回对象
	public Province findById(Integer id){
		HashMap<Integer, Province> hash = this.provinceHash();
		return hash.get(id);
	}
	
	//省份列表
	public List<Province> provinceList(){
		List<Province> list;
		String key = "prov_list";
		
		Object obj = C.getFromCache(key);
		if(obj!=null){
			list = (List<Province>)obj;
		}else{
			list = provinceDAO.provinceList();
			C.saveToCache(key, list);
		}		
		return list;
	}
	
	//省份的哈希
	public HashMap<Integer, Province> provinceHash(){
		HashMap<Integer, Province> hash = new HashMap<Integer, Province>();
		String key = "provid_hash";
		
		Object obj = C.getFromCache(key);
		if(obj!=null){
			hash = (HashMap<Integer, Province>)obj;
		}else{
			List<Province> provlist = provinceDAO.provinceList();
			
			for(int i=0; i<provlist.size(); i++){
				Province prov = provlist.get(i);
				hash.put(prov.getId(), prov);
			}
			C.saveToCache(key, hash);
		}
		return hash;
	}
}
