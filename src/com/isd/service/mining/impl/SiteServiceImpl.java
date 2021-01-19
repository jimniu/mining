package com.isd.service.mining.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.isd.dao.news.XwResultDAO;
import com.isd.dao.wechat.WxResultDAO;
import com.isd.dao.weibo.WbResultDAO;
import com.isd.service.mining.SiteService;
import com.isd.util.C;

public class SiteServiceImpl implements SiteService{
	
	@Autowired
	private WbResultDAO wbResultDAO;
	@Autowired
	private WxResultDAO wxResultDAO;
	@Autowired
	private XwResultDAO xwResultDAO;
	
	//检查当天是否有数据保存到结果表
	public String datacheck(){
		int wb = wbResultDAO.contentCountByDate(0);
		int wx = wxResultDAO.contentCountByDate(0);
		int xw = xwResultDAO.contentCountByDate(0);
		String result = "WB:"+wb+"条;WX:"+wx+"条;XW:"+xw+"条！";
		
		if(wb*wx*xw==0){
			C.sendEmail("13801353496@qq.com,18510209918@qq.com,18610087387@qq.com", result, result);
			//C.sendEmail("18610087387@qq.com", result, "数据出现异常");
		}
		return result;
	}
}
