package com.isd.service.mining.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.isd.dao.bbs.LtResultDAO;
import com.isd.dao.mining.DaystatDAO;
import com.isd.dao.news.XwResultDAO;
import com.isd.dao.wechat.WxResultDAO;
import com.isd.dao.weibo.WbResultDAO;
import com.isd.entity.mining.Daystat;
import com.isd.service.mining.DaystatService;
import com.isd.util.C;


public class DaystatServiceImpl implements DaystatService{
	@Autowired 
	private DaystatDAO daystatDAO;
	@Autowired
	private WbResultDAO wbResultDAO;
	@Autowired
	private WxResultDAO wxResultDAO;
	@Autowired
	private XwResultDAO xwResultDAO;
	@Autowired
	private LtResultDAO ltResultDAO;	
	
	//按用户和模块显示按月的统计
	public List userMonthly(Integer networkid, String module, Integer type, int days){
		String date = C.getDate(days);
		return daystatDAO.userMonthly(networkid, module, type, date);
	}

	//按栏目和模块显示按月的统计
	public List columnMonthly(Integer columnid, String module, int days){
		String date = C.getDate(days);
		return daystatDAO.columnMonthly(columnid, module, date);
	}
	
	//按用户和模块显示按日的统计
	public List userDaily(Integer networkid, String module, Integer type, int days){
		String date = C.getDate(days);
		return daystatDAO.userDaily(networkid, module, type, date);
	}

	//按栏目和模块显示按日的统计
	public List columnDaily(Integer columnid, String module, int days){
		String date = C.getDate(days);
		return daystatDAO.columnDaily(columnid, module, date);
	}
	
	//按照用户和模块统计总数
	public List userTotal(Integer networkid, String module, Integer type, int days){
		String date = C.getDate(days);

		return daystatDAO.userTotal(networkid, module, type, date);
	}
	
	//按栏目和模块统计总数
	public List columnTotal(Integer columnid, String module, int days){
		String date = C.getDate(days);

		return daystatDAO.columnTotal(columnid, module, date);		
	}
	
	//统计汇总
	public void summarize(Integer days){
		//微博统计
		long start = new Date().getTime();
		List list = wbResultDAO.monthly(days);
		this.save(list);		
		list = wbResultDAO.daily(days);
		this.save(list);
		System.out.println("Wb Daily summarize Stop:"+new Date() +", Time used:"+(new Date().getTime()-start)/1000);
		
		//微信统计
		start = new Date().getTime();
		list = wxResultDAO.monthly(days);
		this.save(list);		
		list = wxResultDAO.daily(days);
		this.save(list);
		System.out.println("Wx Daily summarize Stop:"+new Date() +", Time used:"+(new Date().getTime()-start)/1000);
		
		//新闻统计
		start = new Date().getTime();
		list = xwResultDAO.monthly(days);
		this.save(list);		
		list = xwResultDAO.daily(days);
		this.save(list);	
		System.out.println("Xw Daily summarize Stop:"+new Date() +", Time used:"+(new Date().getTime()-start)/1000);
		
		//论坛统计
		start = new Date().getTime();
		list = ltResultDAO.monthly(days);
		this.save(list);		
		list = ltResultDAO.daily(days);
		this.save(list);		
		System.out.println("Lt Daily summarize Stop:"+new Date() +", Time used:"+(new Date().getTime()-start)/1000);
	}
	
	//保存数据
	private void save(List list){
		for(int i=0; i<list.size(); i++){
			Object[] array = (Object[])list.get(i);
			
			String date = (String)array[0];
			String module 		= (String)array[1];
			Integer networkid 		= (Integer)array[2];
			Integer columnid 	= (Integer)array[3];
			Integer category 	= Integer.valueOf(array[4].toString());
			Integer type		= (Integer)array[5];
			Integer mediatotal 	= Integer.valueOf(array[6].toString());
			Integer pagetotal 	= Integer.valueOf(array[7].toString());
	
			List<Daystat> existlist = daystatDAO.findByDate(columnid, module, category, date);
			if(existlist.size()>0){
				Daystat stat = existlist.get(0);
				stat.setMediatotal(mediatotal);
				stat.setPagetotal(pagetotal);
				stat.setUtime(new Date());
				daystatDAO.update(stat);
			}else{
				Daystat stat = new Daystat();
				stat.setDate(date);
				stat.setModule(module);
				stat.setColumnid(columnid);
				stat.setMediatotal(mediatotal);
				stat.setPagetotal(pagetotal);
				stat.setCategory(category);
				stat.setType(type);				
				stat.setNetworkid(networkid);
				stat.setUtime(new Date());
				daystatDAO.save(stat);
			}
		}
	}
	
}
