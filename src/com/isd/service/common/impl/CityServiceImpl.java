package com.isd.service.common.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.isd.dao.common.CityDAO;
import com.isd.entity.common.City;
import com.isd.service.common.CityService;
import com.isd.util.C;


public class CityServiceImpl implements CityService{
	@Autowired
	private CityDAO cityDAO;
	
	String[] group = {"city"};
	
	//根据名称，返回对象
	public City findByName(Integer provid, String name){
		HashMap<String, City> hash = new HashMap<String, City>();
		String key = "cityname_hash";
		Object obj = C.getFromCache(key);
		if(obj!=null){
			hash = (HashMap<String, City>)obj;
		}else{
			List<City> citylist = cityDAO.cityList();
			
			for(int i=0; i<citylist.size(); i++){
				City city = citylist.get(i);
				hash.put(city.getProvid()+"_"+city.getName(), city);
			}
			C.saveToCache(key, hash, group);
		}
		
		City city = hash.get(provid+"_"+name);
		
		//如果数据库的名称与传进来的不完全一致，则取部分进行匹配
		if(city==null){
			//取前两个字符或前一个字符去匹配
			String shortname = name.substring(0,2);
			if(name.length()>3){
				shortname = name.substring(0,3);
			}else if(name.length()==2){
				shortname = name.substring(0,1);
			}
			
			List<City> citylist = this.citylist(provid);
			for(int i=0; i<citylist.size(); i++){
				City object = citylist.get(i);
				String cityname = object.getName();
				if(cityname.indexOf(name)>-1||name.indexOf(cityname)>-1||cityname.indexOf(shortname)>-1){
					city = object;
					break;
				}
			}
		}
		
		return city;
	}
	
	//根据ID返回对象
	public City findById(Integer id){
		HashMap<Integer, City> hash = this.cityHash();
		return hash.get(id);
	}
	
	//城市列表
	public List<City> citylist(Integer provid){
		List<City> list;
		String key = "city_list_"+provid;
		
		Object obj = C.getFromCache(key);
		if(obj!=null){
			list = (List<City>)obj;
		}else{
			list = cityDAO.cityList(provid);
			C.saveToCache(key, list, group);
		}		
		return list;		
	}
	
	public HashMap<Integer, City> cityHash(){
		HashMap<Integer, City> hash = new HashMap<Integer, City>();
		String key = "cityid_hash";
		
		Object obj = C.getFromCache(key);
		if(obj!=null){
			hash = (HashMap<Integer, City>)obj;
		}else{
			List<City> provlist = cityDAO.cityList();
			
			for(int i=0; i<provlist.size(); i++){
				City prov = provlist.get(i);
				hash.put(prov.getId(), prov);
			}
			C.saveToCache(key, hash, group);
		}
		return hash;
	}
}
