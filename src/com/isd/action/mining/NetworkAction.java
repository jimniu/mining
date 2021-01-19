package com.isd.action.mining;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.isd.action.GenericAction;
import com.isd.entity.mining.Network;
import com.isd.entity.mining.Partner;
import com.isd.entity.mining.Setting;
import com.isd.entity.mining.User;
import com.isd.service.bbs.LtColumnService;
import com.isd.service.event.SjColumnService;
import com.isd.service.mining.NetworkService;
import com.isd.service.mining.PartnerService;
import com.isd.service.mining.UserService;
import com.isd.service.negative.FmColumnService;
import com.isd.service.news.XwColumnService;
import com.isd.service.wechat.WxColumnService;
import com.isd.service.weibo.WbColumnService;
import com.isd.util.C;


public class NetworkAction extends GenericAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Autowired
	private NetworkService networkService;
	@Autowired
	private PartnerService partnerService;
	@Autowired
	private UserService userService;
	@Autowired
	private WbColumnService wbColumnService;
	@Autowired
	private WxColumnService wxColumnService;
	@Autowired
	private XwColumnService xwColumnService;
	@Autowired
	private LtColumnService ltColumnService;
	@Autowired
	private FmColumnService fmColumnService;
	@Autowired
	private SjColumnService sjColumnService;	

	private Network network;
	private Setting setting;

	private File image;
	private String imageContentType;
	private String imageFileName;

	private File headimage;
	private String headimageContentType;
	private String headimageFileName;

	public String index(){
		List<Network> list = networkService.list(pageindex*pagesize, pagesize);
		ctx.put("list", list);
		return SUCCESS;
	}
	//编辑
	public String info(){
		Network net = (Network) session.getAttribute(C.SESSION_NETWORK);
		//提取原先的设置信息
		setting = Setting.json2obj(net.getSetting());
		
		return SUCCESS;
	}

	//修改
	public String update(){
		String imgurl = C.handleUpload(mynetwork.getId(), "network", imageFileName, image);
		if(imgurl!=null){
			setting.setWxbarcode(imgurl);
		}

		String headimgurl = C.handleUpload(mynetwork.getId(), "headimg", headimageFileName, headimage);
		if(headimgurl!=null){
			setting.setHeadimage(headimgurl);
		}
		
		network.setId(mynetwork.getId());
		networkService.update(network, setting);
		session.setAttribute(C.SESSION_NETWORK, networkService.findById(network.getId()));
		
		ctx.put("redirectaction", "network/info");
		return REDIRECTACTION;
	}
	
	public String keyword(){
		String wbkeyword = wbColumnService.findKeywordsByNetwork(mynetwork.getId());
		String wxkeyword = wxColumnService.findKeywordsByNetwork(mynetwork.getId());
		String xwkeyword = xwColumnService.findKeywordsByNetwork(mynetwork.getId());
		String ltkeyword = ltColumnService.findKeywordsByNetwork(mynetwork.getId());
		String fmkeyword = fmColumnService.findKeywordsByNetwork(mynetwork.getId());
		String sjkeyword = sjColumnService.findKeywordsByNetwork(mynetwork.getId());		
		
		Integer wbcount = wbkeyword.split(",").length-(wbkeyword.length()==0?1:0);
		Integer wxcount = wxkeyword.split(",").length-(wxkeyword.length()==0?1:0);
		Integer xwcount = xwkeyword.split(",").length-(xwkeyword.length()==0?1:0);
		Integer ltcount = ltkeyword.split(",").length-(ltkeyword.length()==0?1:0);
		Integer fmcount = fmkeyword.split(",").length-(fmkeyword.length()==0?1:0);
		Integer sjcount = sjkeyword.split(",").length-(sjkeyword.length()==0?1:0);		
		
		ctx.put("text", ltcount+wbcount+wxcount+xwcount+fmcount+sjcount);

		return TEXT;
	}

	public String manage_index(){
		Integer status = network==null?null:network.getStatus();
		
		List<Network> list = networkService.list(status, pageindex*pagesize, pagesize);
		HashMap<Integer, Partner> hash = partnerService.hash();
		Integer total = networkService.total(status);
		ctx.put("list", list);
		ctx.put("hash", hash);
		ctx.put("total", total.toString());
		return SUCCESS;
	}

	public String manage_add(){
		List<Partner> list = partnerService.list(0, 999999);
		ctx.put("list", list);
		return SUCCESS;
	}

	//保存
	public String manage_save(){
		networkService.save(network, setting);
		ctx.put("redirectaction", "network/manage_index");
		return REDIRECTACTION;
	}

	public String manage_info(){
		network = networkService.findById(network.getId());
		//提取原先的设置信息
		setting = Setting.json2obj(network.getSetting());
		
		List<Partner> list = partnerService.list(0, 999999);
		ctx.put("list", list);
		return SUCCESS;
	}
	
	public String manage_keyword(){
		String wbkeyword = wbColumnService.findKeywordsByNetwork(network.getId());
		String wxkeyword = wxColumnService.findKeywordsByNetwork(network.getId());
		String xwkeyword = xwColumnService.findKeywordsByNetwork(network.getId());
		String ltkeyword = ltColumnService.findKeywordsByNetwork(network.getId());
		String fmkeyword = fmColumnService.findKeywordsByNetwork(network.getId());
		String sjkeyword = sjColumnService.findKeywordsByNetwork(network.getId());		
		
		Integer wbcount = wbkeyword.split(",").length-(wbkeyword.length()==0?1:0);
		Integer wxcount = wxkeyword.split(",").length-(wxkeyword.length()==0?1:0);
		Integer xwcount = xwkeyword.split(",").length-(xwkeyword.length()==0?1:0);
		Integer ltcount = ltkeyword.split(",").length-(ltkeyword.length()==0?1:0);
		Integer fmcount = fmkeyword.split(",").length-(fmkeyword.length()==0?1:0);
		Integer sjcount = sjkeyword.split(",").length-(sjkeyword.length()==0?1:0);		
		
		ctx.put("wbkeyword", wbkeyword);
		ctx.put("wxkeyword", wxkeyword);
		ctx.put("xwkeyword", xwkeyword);
		ctx.put("ltkeyword", ltkeyword);
		ctx.put("fmkeyword", fmkeyword);
		ctx.put("sjkeyword", sjkeyword);
		
		ctx.put("wbcount", wbcount);
		ctx.put("wxcount", wxcount);
		ctx.put("xwcount", xwcount);
		ctx.put("ltcount", ltcount);
		ctx.put("fmcount", fmcount);
		ctx.put("sjcount", sjcount);

		ctx.put("total", ltcount+wbcount+wxcount+xwcount+fmcount+sjcount);

		return SUCCESS;
	}	

	public String manage_update(){
		networkService.update(network, setting);
		ctx.put("redirectaction", "network/manage_index");
		return REDIRECTACTION;
	}

	public String manage_delete(){
		networkService.delete(network.getId());
		ctx.put("text", "success");
		return TEXT;
	}

	public String manage_visit(){
		network = networkService.findById(network.getId());
		User user = userService.findById(0);
		user.setNetworkid(network.getId());
		//设置账号为管理员
		user.setType(2);
		//将查看的networkid存入admin账户的设置，避免引起认证时获取的networkid错误
		userService.updateUser(user);
		session.setAttribute(C.SESSION_USER, user);
		session.setAttribute(C.SESSION_NETWORK, network);
		ctx.put("redirectaction", "site/overview");
		return REDIRECTACTION;
	}

	public Network getNetwork() {
		return network;
	}

	public void setNetwork(Network network) {
		this.network = network;
	}

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public String getImageContentType() {
		return imageContentType;
	}

	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public File getHeadimage() {
		return headimage;
	}

	public void setHeadimage(File headimage) {
		this.headimage = headimage;
	}

	public String getHeadimageContentType() {
		return headimageContentType;
	}

	public void setHeadimageContentType(String headimageContentType) {
		this.headimageContentType = headimageContentType;
	}

	public String getHeadimageFileName() {
		return headimageFileName;
	}

	public void setHeadimageFileName(String headimageFileName) {
		this.headimageFileName = headimageFileName;
	}
	public Setting getSetting() {
		return setting;
	}
	public void setSetting(Setting setting) {
		this.setting = setting;
	}
}
