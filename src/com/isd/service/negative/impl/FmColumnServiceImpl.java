package com.isd.service.negative.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.isd.dao.bbs.LtColumnDAO;
import com.isd.dao.bbs.LtResultDAO;
import com.isd.dao.negative.FmColumnDAO;
import com.isd.dao.news.XwColumnDAO;
import com.isd.dao.news.XwResultDAO;
import com.isd.dao.wechat.WxColumnDAO;
import com.isd.dao.wechat.WxResultDAO;
import com.isd.dao.weibo.WbColumnDAO;
import com.isd.dao.weibo.WbResultDAO;
import com.isd.entity.bbs.LtColumn;
import com.isd.entity.negative.FmColumn;
import com.isd.entity.news.XwColumn;
import com.isd.entity.wechat.WxColumn;
import com.isd.entity.weibo.WbColumn;
import com.isd.service.negative.FmColumnService;
import com.isd.util.C;

public class FmColumnServiceImpl implements FmColumnService{
	@Autowired
	private FmColumnDAO columnDAO;
	@Autowired
	private XwColumnDAO xwColumnDAO;
	@Autowired
	private XwResultDAO xwResultDAO;
	@Autowired
	private WxColumnDAO wxColumnDAO;
	@Autowired
	private WxResultDAO wxResultDAO;	
	@Autowired
	private LtColumnDAO ltColumnDAO;
	@Autowired
	private LtResultDAO ltResultDAO;	
	@Autowired
	private WbColumnDAO wbColumnDAO;
	@Autowired
	private WbResultDAO wbResultDAO;
	

	//根据用户返回所有任务
	public List<FmColumn> findByNetwork(Integer networkid, Integer offset, Integer pagesize){
		return columnDAO.findByNetwork(networkid, offset, pagesize);
	}
	//根据用户返回指定状态的任务
	public List<FmColumn> findByNetwork(Integer networkid, Integer status, Integer offset, Integer pagesize){
		return columnDAO.findByNetwork(networkid, status, offset, pagesize);	
	}
	
	public String findKeywordsByNetwork(Integer networkid){
		return columnDAO.findKeywordsByUser(networkid);
	}
	
	//保存设置
	public void save(HttpServletRequest request, Integer uid){
		String fullkw = "";
		List<FmColumn> list = new ArrayList<FmColumn>();
		
		for(int i=0; i<5; i++){
			FmColumn column = new FmColumn();
			
			String title = request.getParameter("title"+i).trim();
			String keyword = request.getParameter("keyword"+i);
			String id = request.getParameter("id"+i);
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
			
			//加载原栏目
			if(id.length()>0){
				Integer colid = Integer.parseInt(id);
				column = columnDAO.findById(colid);
			}			
			//如果标题、标签、ID均为0，则忽略
			if(id.length()==0&&(title.length()==0||keyword.length()==0)){
				continue;
			}	
			column.setTitle(title);
			column.setKeyword(keyword);
			
			if(keyword.length()>0){
				column.setStatus(C.STATUS_ACTIVE);
			}else{
				column.setStatus(C.STATUS_INACTIVE);
			}
			column.setNetworkid(uid);	
			
			if(column.getId()==null){
				column.setCtime(new Date());
				columnDAO.save(column);
			}else{
				column.setUtime(new Date());
				columnDAO.update(column);
			}	
			
			//将栏目加入到list，待后续写入wxid等
			list.add(column);
			if(keyword.length()>0){
				//复制到完整的关键词
				fullkw += "," + keyword;
			}
		}
		
		if(list.size()==0) {
			return;
		}
		FmColumn column = list.get(0);
		if(fullkw.startsWith(",")){
			fullkw = fullkw.substring(1, fullkw.length());
		}
			
		//保存至新闻的栏目,如果原先保存的栏目被删除，则重新保存至新的栏目
		if(column.getXwid()!=null){
			XwColumn xwcol = xwColumnDAO.findById(column.getXwid());
			if(xwcol!=null){
				xwcol.setTitle(column.getTitle());
				xwcol.setFetchword(fullkw);
				xwcol.setSearchword(fullkw);
				xwcol.setType(2);
				xwcol.setRelation(0);
				xwcol.setSerno(98);
				xwcol.setUtime(new Date());
				xwcol.setStatus(C.STATUS_INACTIVE);
				if(fullkw.length()>0){
					xwcol.setStatus(C.STATUS_ACTIVE);
				}				
				xwColumnDAO.update(xwcol);				
			}else{
				column.setXwid(null);
			}
		}
		if(column.getXwid()==null){
			XwColumn xwcol = new XwColumn();
			xwcol.setType(2);
			xwcol.setCtime(new Date());
			xwcol.setFetchword(fullkw);
			xwcol.setSearchword(fullkw);
			xwcol.setRelation(0);
			xwcol.setSerno(98);
			xwcol.setTitle(column.getTitle());
			xwcol.setNetworkid(column.getNetworkid());
			xwcol.setStatus(C.STATUS_INACTIVE);
			if(fullkw.length()>0){
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
				wxcol.setType(2);
				wxcol.setRelation(0);
				wxcol.setSerno(98);
				wxcol.setFetchword(fullkw);
				wxcol.setSearchword(fullkw);
				wxcol.setUtime(new Date());
				wxcol.setStatus(C.STATUS_INACTIVE);
				if(fullkw.length()>0){
					wxcol.setStatus(C.STATUS_ACTIVE);
				}				
				wxColumnDAO.update(wxcol);
			}else{
				column.setWxid(null);
			}
		}	
		if(column.getWxid()==null){
			WxColumn wxcol = new WxColumn();
			wxcol.setType(2);
			wxcol.setCtime(new Date());
			wxcol.setFetchword(fullkw);
			wxcol.setSearchword(fullkw);
			wxcol.setRelation(0);
			wxcol.setSerno(98);
			wxcol.setTitle(column.getTitle());
			wxcol.setNetworkid(column.getNetworkid());
			wxcol.setStatus(C.STATUS_INACTIVE);
			if(fullkw.length()>0){
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
				if(fullkw.indexOf(wbcol.getSearchword())==-1){
					wbResultDAO.clearByColumn(column.getWbid());
				}
				wbcol.setType(2);
				wbcol.setRelation(0);
				wbcol.setFetchword(fullkw);
				wbcol.setSearchword(fullkw);
				wbcol.setUtime(new Date());
				wbcol.setStatus(C.STATUS_INACTIVE);
				if(fullkw.length()>0){
					wbcol.setStatus(C.STATUS_ACTIVE);
				}				
				wbColumnDAO.update(wbcol);
			}else{
				column.setWxid(null);
			}
		}	
		if(column.getWbid()==null){
			WbColumn wbcol = new WbColumn();
			wbcol.setType(2);
			wbcol.setRelation(0);
			wbcol.setCtime(new Date());
			wbcol.setFetchword(fullkw);
			wbcol.setSearchword(fullkw);
			wbcol.setTitle(column.getTitle());
			wbcol.setNetworkid(column.getNetworkid());
			wbcol.setStatus(C.STATUS_INACTIVE);
			if(fullkw.length()>0){
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
				ltcol.setSearchword(fullkw);
				ltcol.setFetchword(fullkw);
				ltcol.setUtime(new Date());
				ltcol.setType(2);
				ltcol.setRelation(0);
				ltcol.setSerno(98);
				ltcol.setStatus(C.STATUS_INACTIVE);
				if(fullkw.length()>0){
					ltcol.setStatus(C.STATUS_ACTIVE);
				}				
				ltColumnDAO.update(ltcol);
			}else{
				column.setLtid(null);
			}
		}				
		if(column.getLtid()==null){
			LtColumn ltcol = new LtColumn();
			ltcol.setType(2);
			ltcol.setCtime(new Date());
			ltcol.setSearchword(fullkw);
			ltcol.setFetchword(fullkw);
			ltcol.setRelation(0);
			ltcol.setSerno(98);
			ltcol.setTitle(column.getTitle());
			ltcol.setNetworkid(column.getNetworkid());
			ltcol.setStatus(C.STATUS_INACTIVE);
			if(fullkw.length()>0){
				ltcol.setStatus(C.STATUS_ACTIVE);
			}
			Integer ltid = ltColumnDAO.save(ltcol);
			
			column.setLtid(ltid);
		}
		
		columnDAO.update(column);
			
		for(int i=1; i<list.size();i++){
			FmColumn col = list.get(i);
			col.setWxid(column.getWxid());
			col.setWbid(column.getWbid());
			col.setXwid(column.getXwid());
			col.setLtid(column.getLtid());
			
			columnDAO.update(col);
		}
	}
	
	//根据ID返回栏目
	public FmColumn findById(Integer id){
		return columnDAO.findById(id);
	}

	//根据一组ID来进行状态的改变
	public void handle(String ids){
		if(ids.length()==0){
			return;
		}
		
		String[] array = ids.split(",");
		for(int i=0; i<array.length; i++){
			Integer id = Integer.parseInt(array[i]);
			FmColumn column = columnDAO.findById(id);
			
			Integer status = Math.abs(column.getStatus()-1);
			
			//更新新闻栏目的状态
			if(column.getXwid()!=null){
				XwColumn xwcol = xwColumnDAO.findById(column.getXwid());
				if(xwcol!=null){
					xwcol.setStatus(status);
					xwColumnDAO.update(xwcol);
				}
			}
			
			//更新微信栏目
			if(column.getWxid()!=null){
				WxColumn wxcol = wxColumnDAO.findById(column.getWxid());
				if(wxcol!=null){
					wxcol.setStatus(status);
					wxColumnDAO.update(wxcol);
				}
			}	
			
			//更新微博的状态
			if(column.getWbid()!=null){
				WbColumn wbcol = wbColumnDAO.findById(column.getWbid());
				if(wbcol!=null){
					wbcol.setStatus(status);
					wbColumnDAO.update(wbcol);
				}
			}	
			
			//更新论坛的状态
			if(column.getLtid()!=null){
				LtColumn ltcol = ltColumnDAO.findById(column.getLtid());
				if(ltcol!=null){
					ltcol.setStatus(status);
					ltColumnDAO.update(ltcol);
				}
			}				
			
			//更新负面栏目的状态
			column.setStatus(status);
			columnDAO.update(column);			
		}
	}
}
