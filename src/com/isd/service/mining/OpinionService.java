package com.isd.service.mining;

import java.util.Date;
import java.util.HashMap;

import com.isd.entity.mining.Opinion;

public interface OpinionService {	
	public Integer add(Opinion opinion);
	public HashMap<Long, Integer> findByNetworkModulePtime(Integer userid, String module, Date from, Date to);
	
}
