package com.isd.service.weibo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.isd.dao.weibo.WbUserDAO;
import com.isd.entity.weibo.WbUser;
import com.isd.service.common.CityService;
import com.isd.service.common.ProvinceService;
import com.isd.service.weibo.WbDictService;
import com.isd.service.weibo.WbUserService;


public class WbUserServiceImpl implements WbUserService{
	@Autowired
	private WbUserDAO weiboDAO;
	@Autowired
	private ProvinceService provinceService;
	@Autowired
	private CityService cityService;
	@Autowired
	private WbDictService dictService;
	
	//根据ID返回
	public WbUser findByWbid(Long uid){
		List<WbUser> list = weiboDAO.findByProperty("wbid", uid);
		return list.size()>0?list.get(0):new WbUser();
	}
	
	//整理用户的地域信息
	/*public WbUser modifyArea(WbUser user){
		Integer provid = user.getProvince();
		Integer cityid = user.getCity();
		
		if(provid!=null&&cityid!=null&&cityid<110101&&!cityid.toString().startsWith(provid.toString())){
			WbDict province = dictService.findById(provid);
			WbDict city = dictService.findById(cityid);
			
			Province newprov = provinceService.findByName(province.getName());
			if(newprov!=null){
				user.setProvince(newprov.getId());			
				City newcity = cityService.findByName(newprov.getId(), city.getName());

				if(newcity!=null){
					user.setCity(newcity.getId());
				}
				weiboDAO.update(user);
			}
		}else if((provid!=null||cityid!=null)&&user.getUtime().before(C.stringToDate("2016-01-18 12:00:00"))){	//如果
			user.setUtime(null);
			user.setCtime(new Date());
			user.setProvince(null);
			user.setCity(null);
			weiboDAO.update(user);
		}
		return user;		
	}*/
}
