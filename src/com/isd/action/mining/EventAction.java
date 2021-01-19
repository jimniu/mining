package com.isd.action.mining;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.isd.action.GenericAction;
import com.isd.entity.bbs.LtColumn;
import com.isd.entity.event.SjColumn;
import com.isd.entity.news.XwColumn;
import com.isd.entity.wechat.WxColumn;
import com.isd.entity.weibo.WbColumn;
import com.isd.service.bbs.LtColumnService;
import com.isd.service.bbs.LtResultService;
import com.isd.service.common.ProvinceService;
import com.isd.service.event.SjColumnService;
import com.isd.service.news.XwColumnService;
import com.isd.service.news.XwResultService;
import com.isd.service.wechat.WxColumnService;
import com.isd.service.wechat.WxResultService;
import com.isd.service.weibo.WbColumnService;
import com.isd.service.weibo.WbResultService;
import com.isd.util.C;

public class EventAction extends GenericAction{

	private static final long serialVersionUID = 1L;
	@Autowired
	private SjColumnService columnService;
	@Autowired
	private XwColumnService xwColumnService;
	@Autowired
	private LtColumnService ltColumnService;
	@Autowired
	private WbColumnService wbColumnService;
	@Autowired
	private WxColumnService wxColumnService;
	@Autowired
	private XwResultService xwResultService;
	@Autowired
	private LtResultService ltResultService;
	@Autowired
	private WbResultService wbResultService;
	@Autowired
	private WxResultService wxResultService;	
	@Autowired
	private ProvinceService provinceService;
	
	private SjColumn column = null;
	
	//事件列表
	public String list(){
		List<SjColumn> columnlist;
		if(column==null){
			columnlist = columnService.findByNetwork(mynetwork.getId(), pageindex*pagesize, pagesize);
		}else{
			if(column.getTitle().length()>0){
				String title = C.getURLChinese(column.getTitle());
				column.setTitle(title);
			}
			columnlist = columnService.findByNetwork(mynetwork.getId(), column, pageindex*pagesize, pagesize);
		}
		
		//获取事件所对应的栏目的hash
		HashMap<Integer, WxColumn> wxhash = wxColumnService.hashByNetwork(mynetwork.getId(), 1);
		HashMap<Integer, XwColumn> xwhash = xwColumnService.hashByNetwork(mynetwork.getId(), 1);
		HashMap<Integer, LtColumn> lthash = ltColumnService.hashByNetwork(mynetwork.getId(), 1);
		HashMap<Integer, WbColumn> wbhash = wbColumnService.hashByNetwork(mynetwork.getId(), 1);
		ctx.put("wxhash", wxhash);
		ctx.put("xwhash", xwhash);
		ctx.put("lthash", lthash);
		ctx.put("wbhash", wbhash);
		ctx.put("columnlist", columnlist);
		return SUCCESS;
	}
	
	public String result(){
		return SUCCESS;
	}
	
	public String detail(){
		column = columnService.findById(column.getId());
		int wbCount = wbResultService.contentCountByColumnDate(column.getWbid(), C.TIME_ALL);
		int wxCount = wxResultService.contentCountByColumnDate(column.getWxid(), C.TIME_ALL);
		int xwCount = wbResultService.contentCountByColumnDate(column.getXwid(), C.TIME_ALL);
		int ltCount = wbResultService.contentCountByColumnDate(column.getLtid(), C.TIME_ALL);
		
		ctx.put("wbcount", String.valueOf(wbCount));
		ctx.put("wxcount", String.valueOf(wxCount));
		ctx.put("xwcount", String.valueOf(xwCount));
		ctx.put("ltcount", String.valueOf(ltCount));
		return SUCCESS;
	}
	
	public String trendchart(){
		String type = column.getTitle();
		column = columnService.findById(column.getId());
		List list = null;
		if(type.equals("wb")){
			list = wbResultService.provinceContentCountByColumnDate(column.getWbid(), C.TIME_ALL);
		}else if(type.equals("wx")){
			list = wxResultService.provinceContentCountByColumnDate(column.getWxid(), C.TIME_ALL);
		}else if(type.equals("xw")){
			list = xwResultService.provinceContentCountByColumnDate(column.getXwid(), C.TIME_ALL);
		}
		for(int i=0; i<list.size(); i++){
			Object[] arr = (Object[])list.get(i);
			Integer id = (Integer)arr[0];
			if(id>90){
				list.remove(i);
			}
		}
		HashMap hash = provinceService.provinceHash();
		ctx.put("hash", hash);
		ctx.put("list", list);
		return SUCCESS;
	}
	
	public String add(){
		column = new SjColumn();
		return SUCCESS;
	}
	
	public String edit(){
		column = columnService.findById(column.getId());
		return SUCCESS;
	}
	
	public String delete(){
		columnService.delete(column.getId());
		ctx.put("redirectaction", "event/list");
		return REDIRECTACTION;
	}
	
	public String save(){
		column.setNetworkid(mynetwork.getId());
		columnService.save(column);
		ctx.put("redirectaction", "event/list");
		return REDIRECTACTION;
	}
	
	public SjColumn getColumn() {
		return column;
	}

	public void setColumn(SjColumn column) {
		this.column = column;
	}
}
