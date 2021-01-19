package com.isd.service.mining.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.isd.dao.mining.MoblocDAO;
import com.isd.entity.mining.Mobloc;
import com.isd.service.mining.MoblocService;
import com.isd.util.C;


public class MoblocServiceImpl implements MoblocService{
	@Autowired 
	private MoblocDAO moblocDAO;
	
	//根据手机号提取地域信息
	public Mobloc findByMobile(String mobile){
		if(mobile.length()!=11){
			return null;
		}
		String number = mobile.substring(0,7);
		List<Mobloc> list = moblocDAO.findByProperty("mobile", Integer.valueOf(number));
		
		//如果取不到已有结果，则从网络进行查询
		if(list==null||list.size()==0){
			String url = "http://wap.ip138.com/sim_search138.asp?mobile="+mobile;
			String result = C.getContentFromURL(url);
			
			int start = result.indexOf("归属地：");
			if(start==-1){
				return null;
			}
			result = result.substring(start+"归属地：".length()).trim();
			int end = result.indexOf("<br");
			result = result.substring(0, end).trim();
			
			String arr[] = result.split(" ");
			String province = arr[0];
			String city = province;
			if(arr.length>1){
				city = arr[1];
			}
			
			province = province.replaceAll("省", "").trim();
			city = city.replaceAll("市", "").trim();
			
			Mobloc mobloc = new Mobloc();
			mobloc.setMobile(Integer.valueOf(number));
			mobloc.setProvince(province);
			mobloc.setCity(city);
			mobloc.setCtime(new Date());
			Integer id = moblocDAO.save(mobloc);
			mobloc.setId(id);
			return mobloc;
		}else{
			return list.get(0);
		}
	}
}
