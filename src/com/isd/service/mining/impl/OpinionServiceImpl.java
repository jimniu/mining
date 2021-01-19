package com.isd.service.mining.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.isd.dao.mining.OpinionDAO;
import com.isd.entity.mining.Opinion;
import com.isd.service.mining.OpinionService;


public class OpinionServiceImpl implements OpinionService{
	@Autowired 
	private OpinionDAO opinionDAO;
	private String[]group = {"opinion"};
	
	public Integer add(Opinion opinion){
		opinion.setCtime(new Date());
		Opinion op = opinionDAO.findByUserModulePageid(opinion.getNetworkid(), opinion.getModule(), opinion.getPageid());
		
		Integer result = opinion.getType();
		if(op==null){
			opinionDAO.save(opinion);
		}else{
			if(op.getType()==opinion.getType()){
				result = 0;
				opinionDAO.delete(op.getId());
			}else{
				op.setType(opinion.getType());
				op.setPtime(opinion.getPtime());
				op.setCtime(opinion.getCtime());
				opinionDAO.update(op);
			}
		}
		return result;
	}
	
	public HashMap<Long, Integer> findByNetworkModulePtime(Integer networkid, String module, Date from, Date to){
		HashMap<Long, Integer> hash = new HashMap<Long, Integer>();
		
		List<Opinion> list = opinionDAO.findByNetworkModulePtime(networkid, module, from, to);
		for(int i=0; i<list.size(); i++){
			Opinion opinion = list.get(i);
			hash.put(opinion.getPageid(), opinion.getType());
		}
		
		return hash;
	}
}
