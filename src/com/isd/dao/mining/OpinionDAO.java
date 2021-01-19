package com.isd.dao.mining;

import java.util.Date;
import java.util.List;

import com.isd.dao.GenericDAO;
import com.isd.entity.mining.Opinion;

public interface OpinionDAO extends GenericDAO<Opinion, Integer>{
	public Opinion findByUserModulePageid(Integer userid, String module, Long pageid);
	public List<Opinion> findByNetworkModulePtime(Integer userid, String module, Date from, Date to);
}
