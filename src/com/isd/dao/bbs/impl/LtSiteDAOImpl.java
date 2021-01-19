package com.isd.dao.bbs.impl;

import java.util.List;

import com.isd.dao.GenericDAOImpl;
import com.isd.dao.bbs.LtSiteDAO;
import com.isd.entity.bbs.LtSite;
import com.isd.util.C;


public class LtSiteDAOImpl extends GenericDAOImpl<LtSite, Integer> implements LtSiteDAO {
	public LtSiteDAOImpl() {
		super(LtSite.class);
	}

	//根据链接地址返回站点对象
	public LtSite findByUrl(String url){
		String hql = "from LtSite where url='"+C.getDomainName(url)+"'";
		List<LtSite>list = this.findPageByHql(hql, 0, 1);
		if(list.size()>0)
		{
			return list.get(0);
		}else{
			return null;
		}
	}
	
	public List<LtSite> list(int offset, int pagesize){
		String hql = "from LtSite order by pagecount desc, ctime desc";
		return this.findPageByHql(hql, offset, pagesize);
	}
}
