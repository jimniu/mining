package com.isd.service.event.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.isd.dao.bbs.LtColumnDAO;
import com.isd.dao.event.SjColumnDAO;
import com.isd.dao.news.XwColumnDAO;
import com.isd.dao.news.XwResultDAO;
import com.isd.dao.wechat.WxColumnDAO;
import com.isd.dao.weibo.WbColumnDAO;
import com.isd.dao.weibo.WbResultDAO;
import com.isd.entity.bbs.LtColumn;
import com.isd.entity.event.SjColumn;
import com.isd.entity.news.XwColumn;
import com.isd.entity.wechat.WxColumn;
import com.isd.entity.weibo.WbColumn;
import com.isd.service.event.SjColumnService;
import com.isd.service.mining.DaystatService;
import com.isd.util.C;

public class SjColumnServiceImpl implements SjColumnService{
	@Autowired
	private SjColumnDAO columnDAO;
	@Autowired
	private XwColumnDAO xwColumnDAO;
	@Autowired
	private XwResultDAO xwResultDAO;
	@Autowired
	private WxColumnDAO wxColumnDAO;
	@Autowired
	private LtColumnDAO ltColumnDAO;
	@Autowired
	private WbColumnDAO wbColumnDAO;
	@Autowired
	private WbResultDAO wbResultDAO;
	@Autowired
	private DaystatService statService;	
	

	public void save(SjColumn column){
		String keyword = column.getKeyword().replaceAll("，", ",").trim();
		keyword = keyword.replaceAll("，", ",").trim();
		
		//如果超出5个，则删除后面的部分
		String[]array = keyword.split(",");
		List<String> kwlist = new ArrayList<String>();
		for(int j=0; j<array.length; j++){
			String kw = array[j].trim();
			if(kw.length()>0){
				kwlist.add(kw);
			}
		}
		keyword = "";
		for(int j=0; j<kwlist.size(); j++){
			if(j==0){
				keyword = kwlist.get(j).toString();
			}else if(j<5){
				keyword += ","+kwlist.get(j).toString();
			}
		}

		column.setKeyword(keyword);
		
		//根据情况，保存或更新
		if(column.getId()!=null){
			SjColumn col = column; 
			column = columnDAO.findById(col.getId());
			column.setProvince(col.getProvince());
			column.setCity(col.getCity());
			column.setIntro(col.getIntro());
			column.setKeyword(col.getKeyword());
			column.setModified(0);
			column.setOccurred(col.getOccurred());
			column.setRole(col.getRole());
			column.setSerno(col.getSerno());
			column.setTitle(col.getTitle());
			column.setUtime(new Date());
			column.setStatus("1");
		}else{
			column.setCtime(new Date());
			column.setStatus("1");
		}
		
		//保存至新闻的栏目,如果原先保存的栏目被删除，则重新保存至新的栏目
		if(column.getXwid()!=null){
			XwColumn xwcol = xwColumnDAO.findById(column.getXwid());
			if(xwcol!=null){
				xwcol.setTitle(column.getTitle());
				xwcol.setSearchword(keyword);
				xwcol.setFetchword(keyword);
				xwcol.setRelation(0);
				xwcol.setType(1);
				xwcol.setSerno(99);
				xwcol.setUtime(new Date());
				xwcol.setStatus(C.STATUS_INACTIVE);
				if(keyword.length()>0){
					xwcol.setStatus(C.STATUS_ACTIVE);
				}				
				xwColumnDAO.update(xwcol);				
			}else{
				column.setXwid(null);
			}
		}
		if(column.getXwid()==null){
			XwColumn xwcol = new XwColumn();
			xwcol.setType(1);
			xwcol.setCtime(new Date());
			xwcol.setSearchword(keyword);
			xwcol.setFetchword(keyword);
			xwcol.setRelation(0);
			xwcol.setSerno(99);
			xwcol.setTotal(0);
			xwcol.setToday(0);
			xwcol.setTitle(column.getTitle());
			xwcol.setNetworkid(column.getNetworkid());
			xwcol.setStatus(C.STATUS_INACTIVE);
			if(keyword.length()>0){
				xwcol.setStatus(C.STATUS_ACTIVE);
			}			
			Integer xwid = xwColumnDAO.save(xwcol);
			
			column.setXwid(xwid);
		}
		
		//保存至微信的栏目，如果原先保存的栏目被删除，则重新保存至新的栏目
		if(column.getWxid()!=null){
			WxColumn wxcol = wxColumnDAO.findById(column.getWxid());
			if(wxcol!=null){
				wxcol.setTitle(column.getTitle());
				wxcol.setFetchword(keyword);
				wxcol.setSearchword(keyword);
				wxcol.setType(1);
				wxcol.setRelation(0);
				wxcol.setSerno(99);
				wxcol.setUtime(new Date());
				wxcol.setStatus(C.STATUS_INACTIVE);
				if(keyword.length()>0){
					wxcol.setStatus(C.STATUS_ACTIVE);
				}				
				wxColumnDAO.update(wxcol);
			}else{
				column.setWxid(null);
			}
		}	
		if(column.getWxid()==null){
			WxColumn wxcol = new WxColumn();
			wxcol.setType(1);
			wxcol.setCtime(new Date());
			wxcol.setFetchword(keyword);
			wxcol.setSearchword(keyword);
			wxcol.setRelation(0);
			wxcol.setSerno(99);
			wxcol.setTotal(0);
			wxcol.setToday(0);			
			wxcol.setTitle(column.getTitle());
			wxcol.setNetworkid(column.getNetworkid());
			wxcol.setStatus(C.STATUS_INACTIVE);
			if(keyword.length()>0){
				wxcol.setStatus(C.STATUS_ACTIVE);
			}			
			Integer wxid = wxColumnDAO.save(wxcol);
			
			column.setWxid(wxid);
		}
		
		//保存至微博的栏目，如果原先保存的栏目被删除，则重新保存至新的栏目
		if(column.getWbid()!=null){
			WbColumn wbcol = wbColumnDAO.findById(column.getWbid());
			if(wbcol!=null){
				wbcol.setTitle(column.getTitle());
				//如果新的关键词没有包含旧的关键词，则清空旧的查询结果
				if(keyword.indexOf(wbcol.getSearchword())==-1){
					wbResultDAO.clearByColumn(column.getWbid());
				}
				wbcol.setType(1);
				wbcol.setRelation(0);
				wbcol.setSearchword(keyword);
				wbcol.setFetchword(keyword);
				wbcol.setUtime(new Date());
				wbcol.setStatus(C.STATUS_INACTIVE);
				if(keyword.length()>0){
					wbcol.setStatus(C.STATUS_ACTIVE);
				}				
				wbColumnDAO.update(wbcol);
			}else{
				column.setWbid(null);
			}
		}	
		if(column.getWbid()==null){
			WbColumn wbcol = new WbColumn();
			wbcol.setType(1);
			wbcol.setRelation(0);
			wbcol.setCtime(new Date());
			wbcol.setFetchword(keyword);
			wbcol.setSearchword(keyword);
			wbcol.setTitle(column.getTitle());
			wbcol.setNetworkid(column.getNetworkid());
			wbcol.setStatus(C.STATUS_INACTIVE);
			if(keyword.length()>0){
				wbcol.setStatus(C.STATUS_ACTIVE);
			}	
			Integer wbid = wbColumnDAO.save(wbcol);
			
			column.setWbid(wbid);
		}
		
		
		//保存至论坛的栏目,如果原先保存的栏目被删除，则重新保存至新的栏目
		if(column.getLtid()!=null){
			LtColumn ltcol = ltColumnDAO.findById(column.getLtid());
			if(ltcol!=null){
				ltcol.setTitle(column.getTitle());
				ltcol.setType(1);
				ltcol.setRelation(0);
				ltcol.setSerno(99);
				ltcol.setSearchword(keyword);
				ltcol.setFetchword(keyword);
				ltcol.setUtime(new Date());
				ltcol.setStatus(C.STATUS_INACTIVE);
				if(keyword.length()>0){
					ltcol.setStatus(C.STATUS_ACTIVE);
				}				
				ltColumnDAO.update(ltcol);
			}else{
				column.setLtid(null);
			}
		}				
		if(column.getLtid()==null){
			LtColumn ltcol = new LtColumn();
			ltcol.setType(1);
			ltcol.setCtime(new Date());
			ltcol.setSearchword(keyword);
			ltcol.setFetchword(keyword);
			ltcol.setRelation(0);
			ltcol.setSerno(99);
			ltcol.setTotal(0);
			ltcol.setToday(0);
			ltcol.setTitle(column.getTitle());
			ltcol.setNetworkid(column.getNetworkid());
			ltcol.setStatus(C.STATUS_INACTIVE);
			if(keyword.length()>0){
				ltcol.setStatus(C.STATUS_ACTIVE);
			}			
			Integer ltid = ltColumnDAO.save(ltcol);
			
			column.setLtid(ltid);
		}

		if(column.getId()!=null){
			columnDAO.update(column);
		}else{
			columnDAO.save(column);
		}
	}

	//根据用户返回任务
	public List<SjColumn> findByNetwork(Integer networkid, Integer offset, Integer pagesize){
		return columnDAO.findByNetwork(networkid, offset, pagesize);
	}
	
	//根据用户的搜索条件返回任务
	public List<SjColumn> findByNetwork(Integer networkid, SjColumn column, Integer offset, Integer pagesize){
		return columnDAO.findByNetwork(networkid, column, offset, pagesize);
	}
	
	public String findKeywordsByNetwork(Integer networkid){
		return columnDAO.findKeywordsByUser(networkid);
	}
	
	//根据ID返回栏目
	public SjColumn findById(Integer id){
		return columnDAO.findById(id);
	}
	
	public void delete(Integer id){
		SjColumn column = columnDAO.findById(id);
		xwColumnDAO.delete(column.getXwid());
		wxColumnDAO.delete(column.getWxid());
		wbColumnDAO.delete(column.getWbid());
		ltColumnDAO.delete(column.getLtid());
		columnDAO.delete(column);
	}
}
