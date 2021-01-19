package com.isd.dao.mining;

import java.util.List;

import com.isd.dao.GenericDAO;
import com.isd.entity.mining.Geostat;

public interface GeostatDAO extends GenericDAO<Geostat, Integer>{
	//按用户统计
	public List findByUser(Integer userid, Integer type);

	//按栏目统计
	public List findByColumn(Integer columnid, Integer type);
	
	//返回记录
	public List<Geostat> findByColumnProvince(String date, Integer userid, Integer columnid, String module, Integer type, Integer province);
}
