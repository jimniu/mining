package com.isd.dao.news.impl;

import java.util.List;

import com.isd.dao.GenericDAOImpl;
import com.isd.dao.news.XwSiteDAO;
import com.isd.entity.news.XwSite;
import com.isd.util.C;


public class XwSiteDAOImpl extends GenericDAOImpl<XwSite, Integer> implements XwSiteDAO {
	public XwSiteDAOImpl() {
		super(XwSite.class);
	}
	
	//根据链接地址返回站点对象
	public XwSite findByUrl(String url){
		String hql = "from XwSite where url='"+C.getDomainName(url)+"'";
		List<XwSite>list = this.findPageByHql(hql, 0, 1);
		if(list.size()>0)
		{
			return list.get(0);
		}else{
			return null;
		}
	}

}
