package com.isd.action.mining;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.isd.action.GenericAction;
import com.isd.entity.negative.FmColumn;
import com.isd.service.bbs.LtResultService;
import com.isd.service.mining.DaystatService;
import com.isd.service.negative.FmColumnService;
import com.isd.service.news.XwResultService;
import com.isd.service.wechat.WxResultService;
import com.isd.service.weibo.WbResultService;
import com.isd.util.C;

public class NegativeAction extends GenericAction{

	private static final long serialVersionUID = 1L;
	@Autowired 
	private FmColumnService columnService;
	@Autowired
	private XwResultService xwResultService;
	@Autowired
	private WxResultService wxResultService;
	@Autowired
	private LtResultService ltResultService;
	@Autowired
	private WbResultService wbResultService;
	@Autowired
	private DaystatService statService;
	
	private FmColumn column;
	private String ids;

	
	public String content(){
		List xwlist = xwResultService.contentListByNetworkTypeRelationDate(mynetwork.getId(), C.TYPE_NEGATIVE, C.RELATION_ALL, C.TIME_ALL,  0, 9);
		List wxlist = wxResultService.contentListByNetworkTypeDateOrder(mynetwork.getId(), 2, "ptime", 365, 0, 9);
		List ltlist = ltResultService.contentListByUserType(mynetwork.getId(), 2, 0, 9);
		List wblist = wbResultService.contentListByNetworkTypeRelationDate(mynetwork.getId(), C.TYPE_NEGATIVE, 0, C.TIME_ALL, 0, 9);
		
		ctx.put("xwlist", xwlist);
		ctx.put("wxlist", wxlist);
		ctx.put("ltlist", ltlist);
		ctx.put("wblist", wblist);
		return SUCCESS;
	}
	
	public String column(){
		List<FmColumn> list = columnService.findByNetwork(mynetwork.getId(), 0, 5);
		ctx.put("list", list);
		return SUCCESS;
	}
	
	public String columnsave(){
		//保存设置
		columnService.save(request, mynetwork.getId());
		
		ctx.put("redirectaction", "negative/content");
		return REDIRECTACTION;
	}	
	
	public String result(){
		List list = columnService.findByNetwork(mynetwork.getId(), 1, pageindex*pagesize, pagesize);
		ctx.put("list", list);
		return SUCCESS;
	}
	
	public String chart(){
		FmColumn col = columnService.findById(column.getId());
		List wxlist = statService.columnMonthly(col.getWxid(), C.MODULE_WECHAT, 365);
		List wblist = statService.columnMonthly(col.getWbid(), C.MODULE_WEIBO, 365);
		List xwlist = statService.columnMonthly(col.getXwid(), C.MODULE_NEWS, 365); 
		List ltlist = statService.columnMonthly(col.getLtid(), C.MODULE_BBS, 365);
		
		for(int i=0; i<xwlist.size(); i++){
			Object[] array = (Object[])xwlist.get(i);
			String month = array[0].toString();
			array[1] = 0;
			
			if(wblist.size()<i+1){
				wblist.add(i, array);
			}else{
				Object[] arr = (Object[])wblist.get(i);
				if(!arr[0].toString().equals(month)){
					wblist.add(i, array);
				}
			}
			
			if(wxlist.size()<i+1){
				wxlist.add(i, array);
			}else{
				Object[] arr = (Object[])wxlist.get(i);
				if(!arr[0].toString().equals(month)){
					wxlist.add(i, array);
				}
			}
			
			if(ltlist.size()<i+1){
				ltlist.add(i, array);
			}else{
				Object[] arr = (Object[])ltlist.get(i);
				if(!arr[0].toString().equals(month)){
					ltlist.add(i, array);
				}
			}
		}
		
		ctx.put("wxlist", wxlist);
		ctx.put("wblist", wblist);
		ctx.put("xwlist", xwlist);
		ctx.put("ltlist", ltlist);
		return SUCCESS;
	}

	public String handle(){
		columnService.handle(ids);
		ctx.put("text", "SUCCESS");
		return TEXT;
	}
	
	public FmColumn getColumn() {
		return column;
	}

	public void setColumn(FmColumn column) {
		this.column = column;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}
}
