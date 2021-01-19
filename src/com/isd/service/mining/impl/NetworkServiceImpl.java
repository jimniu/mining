package com.isd.service.mining.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.isd.dao.bbs.LtColumnDAO;
import com.isd.dao.event.SjColumnDAO;
import com.isd.dao.mining.DaystatDAO;
import com.isd.dao.mining.GeostatDAO;
import com.isd.dao.mining.NetworkDAO;
import com.isd.dao.mining.UserDAO;
import com.isd.dao.negative.FmColumnDAO;
import com.isd.dao.news.XwColumnDAO;
import com.isd.dao.recommend.FavoriteDAO;
import com.isd.dao.recommend.RecommendDAO;
import com.isd.dao.wechat.WxColumnDAO;
import com.isd.dao.weibo.WbColumnDAO;
import com.isd.entity.mining.Network;
import com.isd.entity.mining.Setting;
import com.isd.entity.mining.User;
import com.isd.service.mining.NetworkService;
import com.isd.util.C;
import com.isd.util.queue.Sender;

public class NetworkServiceImpl implements NetworkService{
	@Autowired 
	private NetworkDAO networkDAO;
	@Autowired
	private XwColumnDAO xwColumnDAO;
	@Autowired
	private WxColumnDAO wxColumnDAO;
	@Autowired
	private LtColumnDAO ltColumnDAO;
	@Autowired
	private WbColumnDAO wbColumnDAO;
	@Autowired
	private DaystatDAO daystatDAO;
	@Autowired
	private GeostatDAO geostatDAO;
	@Autowired
	private SjColumnDAO sjColumnDAO;
	@Autowired
	private FmColumnDAO fmColumnDAO;
	@Autowired
	private FavoriteDAO favoriteDAO;
	@Autowired
	private RecommendDAO recommendDAO;
	@Autowired
	private UserDAO userDAO;

	//保存设置
	public int save(Network network, Setting setting){
		setting.setStartpoint(C.getDate(14));
		network.setSetting(Setting.obj2json(setting));	
		
		network.setCtime(new Date());
		network.setUtime(new Date());
		int id = networkDAO.save(network);
		return id;
	}
	
	//更新设置
	public void update(Network network, Setting setting){
		//账户的状态是否改改变
		boolean statusChanged = false;

		Network net = networkDAO.findById(network.getId());
		if(network.getType()!=null){
			net.setType(network.getType());
		}
		if(network.getArea()!=null){
			net.setArea(network.getArea());
		}
		if(network.getCompany()!=null){
			net.setCompany(network.getCompany());
		}
		if(network.getContact()!=null){
			net.setContact(network.getContact());
		}
		if(network.getExpire()!=null) {
			net.setExpire(network.getExpire());
		}
		if(network.getName()!=null){
			net.setName(network.getName());
		}
		if(network.getPartnerid()!=null){
			net.setPartnerid(network.getPartnerid());
		}
		if(network.getPriority()!=null) {
			net.setPriority(network.getPriority());
		}
		if(network.getPhone()!=null){
			net.setPhone(network.getPhone());
		}
		if(network.getStatus()!=null){
			if(net.getStatus()!=network.getStatus()){
				statusChanged = true;
			}
			net.setStatus(network.getStatus());			
		}
		if(network.getUserid()!=null){
			net.setUserid(network.getUserid());
		}
		net.setUtime(new Date());
		
		//提取原先的设置信息
		Setting set = Setting.json2obj(net.getSetting());
		if(set==null){
			set = new Setting();
		}
		
		if(setting.getColumnkeyword()!=null){
			set.setColumnkeyword(setting.getColumnkeyword());
		}
		if(setting.getDailycard()!=null){
			set.setDailycard(setting.getDailycard());
		}
		if(setting.getHeadimage()!=null){
			set.setHeadimage(setting.getHeadimage());
		}
		if(setting.getKeywordchange()!=null){
			set.setKeywordchange(setting.getKeywordchange());
		}
		if(setting.getScope()!=null){
			set.setScope(setting.getScope());
		}
		if(setting.getStartpoint()!=null&&setting.getStartpoint().length()>0){
			set.setStartpoint(setting.getStartpoint());
		}
		if(set.getStartpoint()==null){
			set.setStartpoint(C.getDate(net.getCtime(), 14));
		}
		
		if(setting.getTotalkeyword()!=null){
			set.setTotalkeyword(setting.getTotalkeyword());
		}
		if(setting.getWxaccount()!=null){
			set.setWxaccount(setting.getWxaccount());
		}
		if(setting.getWxbarcode()!=null){
			set.setWxbarcode(setting.getWxbarcode());
		}
		
		net.setSetting(Setting.obj2json(set));
		
		networkDAO.update(net);

		//根据network的状态来更新栏目的状态
		if(statusChanged) {
			Integer status = 1;
			if(net.getStatus()==0){
				//如果账户的状态变为无效，则修改栏目的状态值为2，当栏目的信息刷新到抓取服务器时，将会设置抓取端的状态为无效
				status = 2;
			}
			wxColumnDAO.changeStatus(net.getId(), status);
			xwColumnDAO.changeStatus(net.getId(), status);
			ltColumnDAO.changeStatus(net.getId(), status);
			wbColumnDAO.changeStatus(net.getId(), status);
		}
	}

	public void delete(Integer networkid){
		xwColumnDAO.delete("userid", networkid);
		wxColumnDAO.delete("userid", networkid);
		wbColumnDAO.delete("userid", networkid);
		ltColumnDAO.delete("userid", networkid);
		geostatDAO.delete("userid", networkid);
		daystatDAO.delete("userid", networkid);
		sjColumnDAO.delete("userid", networkid);
		fmColumnDAO.delete("userid", networkid);
		favoriteDAO.delete("userid", networkid);
		recommendDAO.delete("userid", networkid);
		userDAO.delete("networkid", networkid);
		networkDAO.delete(networkid);

		//给抓取服务器发送账户删除的任务
		Sender.task("delete_network", networkid);
	}
	
	//列出合作伙伴
	public List<Network> list(int offset, int pagesize){
		return networkDAO.list(offset, pagesize);
	}
	
	public List<Network> list(Integer status, int offset, int pagesize){
		return networkDAO.list(status, offset, pagesize);
	}

	//账户数
	public Integer total(){
		return networkDAO.total();
	}
	public Integer total(Integer status){
		return networkDAO.total(status);
	}
	
	//根据ID来获取对象
	public Network findById(Integer id){
		return networkDAO.findById(id);
	}

	public HashMap<Integer, Network> hash(){
		HashMap<Integer, Network> hash = new HashMap<Integer, Network>();
		List<Network> list = networkDAO.list(0, 9999999);
		for(int i=0; i<list.size(); i++){
			Network network = list.get(i);
			hash.put(network.getId(), network);
		}
		return hash;
	}

	//提取登录用户的账户
	public Network getMyNetwork(){
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(true);
		if(session!=null && session.getAttribute(C.SESSION_NETWORK)!=null && session.getAttribute(C.SESSION_NETWORK) instanceof Network){
			return (Network) session.getAttribute(C.SESSION_NETWORK);
		}else if(session!=null && session.getAttribute(C.SESSION_USER)!=null && session.getAttribute(C.SESSION_USER) instanceof User){
			User user= (User)session.getAttribute(C.SESSION_USER);
			Network network = networkDAO.findById(user.getNetworkid());
			session.setAttribute(C.SESSION_NETWORK, network);
			return network;
		}else{
			return null;
		}
	}
}
