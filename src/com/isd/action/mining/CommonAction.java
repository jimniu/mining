package com.isd.action.mining;

import org.springframework.beans.factory.annotation.Autowired;

import com.isd.action.GenericAction;
import com.isd.entity.common.Province;
import com.isd.entity.mining.Mobloc;
import com.isd.service.common.CityService;
import com.isd.service.mining.MoblocService;
import com.isd.util.C;


public class CommonAction extends GenericAction{


	/**
	 * 
	 */
	private static final long serialVersionUID = -8224102441545921873L;
	@Autowired
	private MoblocService moblocService;
	@Autowired
	private CityService cityService;
	
	private Province province;
	private String mobile;
	private String url;
	
	public String showimage(){
		String link = "";
		if(url!=null&&url.length()>0&&url.startsWith("http")){
			link = C.showimage(url);
			ctx.put("redirect", link);
		}
		return REDIRECT;
	}
	
	public String location(){
		Mobloc mobloc = moblocService.findByMobile(mobile);
		if(mobloc!=null){
			String message = mobloc.getProvince()+" "+mobloc.getCity();
			ctx.put("message", message);
		}else{
			ctx.put("message", "手机号无效");
		}
		return MESSAGE;
	}
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}
	
}
