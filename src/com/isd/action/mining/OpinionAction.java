package com.isd.action.mining;

import org.springframework.beans.factory.annotation.Autowired;

import com.isd.action.GenericAction;
import com.isd.entity.mining.Opinion;
import com.isd.service.mining.OpinionService;

public class OpinionAction extends GenericAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1162061231546626190L;
	
	@Autowired
	private OpinionService opinionService;
	
	private Opinion opinion;

	public String add(){
		opinion.setNetworkid(mynetwork.getId());
		Integer id = opinionService.add(opinion);
		ctx.put("text", id);
		return TEXT;
	}
	
	public Opinion getOpinion() {
		return opinion;
	}

	public void setOpinion(Opinion opinion) {
		this.opinion = opinion;
	}	
}
