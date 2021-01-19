package com.isd.service.mining.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;

import com.isd.dao.event.SjColumnDAO;
import com.isd.dao.mining.GeostatDAO;
import com.isd.dao.negative.FmColumnDAO;
import com.isd.dao.news.XwResultDAO;
import com.isd.dao.wechat.WxResultDAO;
import com.isd.dao.weibo.WbResultDAO;
import com.isd.entity.common.Province;
import com.isd.entity.event.SjColumn;
import com.isd.entity.mining.Geostat;
import com.isd.entity.negative.FmColumn;
import com.isd.service.common.ProvinceService;
import com.isd.service.mining.GeostatService;
import com.isd.util.C;


public class GeostatServiceImpl implements GeostatService{
	@Autowired 
	private GeostatDAO geostatDAO;
	@Autowired
	private WbResultDAO wbResultDAO;
	@Autowired
	private WxResultDAO wxResultDAO;
	@Autowired
	private SjColumnDAO sjColumnDAO;
	@Autowired
	private FmColumnDAO fmColumnDAO;
	@Autowired
	private XwResultDAO xwResultDAO;	
	@Autowired
	private ProvinceService provinceService;
	
	//按用户统计
	public List findByUser(Integer networkid, Integer type){
		return this.coverage(geostatDAO.findByUser(networkid, type));
	}

	//按栏目统计
	public List findByColumn(Integer columnid, Integer type){
		return this.coverage(geostatDAO.findByColumn(columnid, type));
	}
	
	//统计汇总
	public void summarize(int days){
		//统计微博
		long start = new Date().getTime();
		List wblist = wbResultDAO.provincial(days);
		this.save(wblist);
		System.out.println("Wb Provincial summarize Stop:"+new Date() +", Time used:"+(new Date().getTime()-start)/1000);
		
		
		//统计微信
		start = new Date().getTime();
		List wxlist = wxResultDAO.provincial(days);
		this.save(wxlist);		
		System.out.println("Wx Provincial summarize Stop:"+new Date() +", Time used:"+(new Date().getTime()-start)/1000);
		
		//统计新闻
		start = new Date().getTime();
		List xwlist = xwResultDAO.provincial(days);
		this.save(xwlist);
		System.out.println("Xw Provincial summarize Start:"+new Date() +", Time used:"+(new Date().getTime()-start)/1000);
	}
	
	//保存数据
	private void save(List list){
		for(int i=0; i<list.size(); i++){
			Object[] array = (Object[])list.get(i);
			
			String date			= (String)array[0];
			Integer networkid 		= (Integer)array[1];
			String module 		= (String)array[2];
			Integer type		= (Integer)array[3];
			Integer columnid 	= Integer.valueOf(array[4].toString());
			Integer province	= (Integer)array[5];
			Integer total 		= Integer.valueOf(array[6].toString());
			if(province==null||province.equals("null")||province>=100){
				continue;
			}
			
			//对于事件和负面，将栏目ID换算成相应事件或负面的栏目id
			if(type==1){
				SjColumn column = sjColumnDAO.findByModule(module, columnid);
				if(column!=null){
					columnid = column.getId();
				}
			}else if(type==2){
				FmColumn column = fmColumnDAO.findByModule(module, columnid);
				if(column!=null){
					columnid = column.getId();
				}
			}
	
			List<Geostat> existlist = geostatDAO.findByColumnProvince(date, networkid, columnid, module, type, province);
			if(existlist.size()>0){
				Geostat stat = existlist.get(0);
				stat.setTotal(total);
				stat.setUtime(new Date());
				geostatDAO.update(stat);
			}else{
				Geostat stat = new Geostat();
				stat.setDate(date);
				stat.setModule(module);
				stat.setColumnid(columnid);
				stat.setTotal(total);
				stat.setType(type);		
				stat.setProvince(province);
				stat.setNetworkid(networkid);
				stat.setUtime(new Date());
				geostatDAO.save(stat);
			}
		}		
	}
	
	//根据网络用户的比例，计算覆盖范围
	private List coverage(List list){
		HashMap<String, Integer> summary = new HashMap<String, Integer>();
		
		Integer national = 0;

		//先取出新闻的全国的数据
		for(int i=0; i<list.size(); i++){
			Object[] obj = (Object[])list.get(i);
			Integer prov = (Integer)obj[0];
			Integer count = ((Long)obj[1]).intValue();
			Province province = provinceService.findById(prov);
			if(province!=null){
				if(province.getName().equals("全国")){
					national = count;
				}else{
					summary.put(province.getName(), count);
				}
			}
		}
		
		if(national>0){
			//全国的省份列表
			List<String> provincelist = C.provincelist();
			
			//根据互联网用户比例，计算分布
			HashMap<String, Double> userratio = C.userRatio();
			
			for(int i=0; i<list.size(); i++){
				Object[] obj = (Object[])list.get(i);
				Integer prov = (Integer)obj[0];
				Integer count = ((Long)obj[1]).intValue();
				
				Province province = provinceService.findById(prov);
				if(province!=null&&userratio.get(province.getName())!=null){
					Double ratio = userratio.get(province.getName());
					count += (int)(national * ratio / 100+0.5);
					provincelist.remove(province.getName());
					summary.put(province.getName(), count);
				}
			}
			
			//本地没有内容发布的省份计算被全国媒体波及的数量
			for(int i=0; i<provincelist.size(); i++){
				String province = provincelist.get(i);
				Double ratio = userratio.get(province);
				Integer count = (int)(national * ratio / 100+0.5);
				if(count>0){
					summary.put(province, count);
				}
			}
		}
		
		List result = new ArrayList();
		
		//对结果排序
		Iterator iter = summary.entrySet().iterator();
		while (iter.hasNext()) {
			Entry entry = (Entry) iter.next();
			String key = entry.getKey().toString();
			int value = Integer.parseInt(entry.getValue().toString());

			int i = 0;
			for(i=0; i<result.size(); i++){
				Object[] object = (Object[])result.get(i);
				int val = (Integer)object[1];
				if(value>val){
					break;
				}
			}
			Object[] obj = new Object[2];
			obj[0]=key;
			obj[1]=value;
			result.add(i, obj);			
		}		
		
		return result;		
	}
	
}
