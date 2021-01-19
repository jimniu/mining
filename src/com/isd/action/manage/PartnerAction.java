package com.isd.action.manage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.isd.action.GenericAction;
import com.isd.entity.mining.Partner;
import com.isd.service.mining.PartnerService;


public class PartnerAction extends GenericAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private PartnerService partnerService;

	private Partner partner;

	public String index(){
		List<Partner> list = partnerService.list(0, pagesize);
		ctx.put("list", list);
		return SUCCESS;
	}
	
	//添加合作伙伴
	public String add(){
		return SUCCESS;
	}
	
	//保存合作伙伴
	public String save(){
		partnerService.save(partner);
		ctx.put("redirectaction", "partner/manage_index");
		return REDIRECTACTION;
	}
	
	//编辑
	public String edit(){
		partner = partnerService.findById(partner.getId());
		return SUCCESS;
	}

	//更新
	public String update(){
		partnerService.update(partner);
		ctx.put("redirectaction", "partner/manage_index");
		return REDIRECTACTION;		
	}
	public Partner getPartner() {
		return partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
	}
}
