package com.isd.action.mining;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.isd.action.GenericAction;
import com.isd.entity.recommend.Favorite;
import com.isd.entity.recommend.Recommend;
import com.isd.service.recommend.FavoriteService;
import com.isd.service.recommend.RecommendService;
import com.isd.util.C;


public class RecommendAction extends GenericAction{
	private static final long serialVersionUID = -1L;
	@Autowired
	private FavoriteService favoriteService;
	@Autowired
	private RecommendService recommendService;

	private Favorite favorite;
	private Recommend recommend;

	private File image;
	private String imageContentType;
	private String imageFileName;

	//添加到收藏
	public String addfavorite(){
		favorite.setNetworkid(mynetwork.getId());
		boolean ret = favoriteService.add(favorite);
		ctx.put("text", String.valueOf(ret));
		return TEXT;
	}

	//删除收藏
	public String delfavorite(){
		favoriteService.delete(favorite.getId());
		ctx.put("text", "success");
		return TEXT;
	}

	//收藏新闻/微信
	public String favorite(){
		if(favorite==null) {
			favorite = new Favorite();
		}

		List<Favorite> list = favoriteService.list(mynetwork.getId(), favorite.getModule(), pageindex*pagesize, pagesize);

		Integer total = favoriteService.total(mynetwork.getId(), favorite.getModule());
		ctx.put("total", total.toString());
		ctx.put("list", list);
		return SUCCESS;
	}
	
	//保存推荐语
	public String savecomment(){
		Favorite favor = favoriteService.findById(favorite.getId());
		favor.setComment(favorite.getComment());
		favoriteService.update(favor);
		ctx.put("text", favor.getComment());
		return TEXT;
	}
	

	//创建推荐
	public String create(){
		recommend = new Recommend();
		//从session中提取id列表
		String idlist = session.getAttribute("idlist")==null?"":session.getAttribute("idlist").toString();
		List<Favorite> list = favoriteService.findByIds(idlist);

		idlist = "";
		recommend.setStatus(1);
		//如果内容来自于其他账户，则去掉。如果包含自媒体内容，则设置状态为0
		for(int i=0; i<list.size(); i++){
			if(list.get(i).getNetworkid().equals(mynetwork.getId())){
				idlist += list.get(i).getId()+",";
				/*if(list.get(i).getModule().equals(C.MODULE_MARK)){
					recommend.setStatus(0);
				}*/
			}else{
				list.remove(i);
			}
		}
		recommend.setIdlist(idlist.substring(0, idlist.length()-1));
		ctx.put("list", list);
		return SUCCESS;
	}

	//保存recommend
	public String save(){
		//优先选择上传的图片
		if(image!=null) {
			String imgurl = C.handleUpload(mynetwork.getId(), C.MODULE_RECOMMEND, imageFileName, image);
			recommend.setImgurl(imgurl);
		}

		List<Favorite> list = favoriteService.findByIds(recommend.getIdlist());

		recommend.setStatus(1);
		//如果内容包含自媒体，则需要审批
		/*for(int i=0; i<list.size(); i++){
			if(list.get(i).getNetworkid().equals(mynetwork.getId())){
				if(list.get(i).getModule().equals(C.MODULE_MARK)){
					recommend.setStatus(0);
				}
			}
		}*/

		recommend.setNetworkid(mynetwork.getId());
		Integer id = recommendService.save(recommend);
		//如果保存成功，则设置内容被选中
		if( id > 0){
			for(int i=0; i<list.size(); i++){
				Favorite favor = list.get(i);
				favor.setChosen(1);
				favoriteService.update(favor);
			}
		}
		
		//设置收藏被选中
		

		//清理缓存
		session.setAttribute("idlist", "");
		ctx.put("redirect", "recommend/list.shtml");
		return REDIRECT;
	}

	//是否可以创建资讯卡
	public String available(){
		boolean cando = recommendService.available(mynetwork);
		ctx.put("text", cando);
		return TEXT;
	}

	//编辑推荐
    public String edit(){
        recommend = recommendService.findById(recommend.getId());
		List<Favorite> list = favoriteService.findByIds(recommend.getIdlist());
		String idlist = "";

		recommend.setStatus(1);
		//如果内容来自于其他账户，则去掉
		for(int i=0; i<list.size(); i++){
			if(list.get(i).getNetworkid().equals(mynetwork.getId())){
				idlist += list.get(i).getId()+",";
				/*if(list.get(i).getModule().equals(C.MODULE_MARK)){
					recommend.setStatus(0);
				}*/
			}else{
				list.remove(i);
			}
		}
		if(idlist.endsWith(",")) {
			recommend.setIdlist(idlist.substring(0, idlist.length() - 1));
		}

		ctx.put("list", list);
        return SUCCESS;
    }

    //保存修改
    public String update(){
		if(image!=null){
			String imgurl = C.handleUpload(mynetwork.getId(), C.MODULE_RECOMMEND, imageFileName, image);
			recommend.setImgurl(imgurl);
		}
        recommendService.update(recommend);
        ctx.put("redirect", "recommend/list.shtml");
        return REDIRECT;
    }

	//recommend
	public String list(){
		List<Recommend> list = recommendService.listByUser(mynetwork.getId(), pageindex*pagesize, pagesize);

		Integer total = recommendService.totalByUser(mynetwork.getId());
		ctx.put("total", total.toString());
		ctx.put("list", list);
		return SUCCESS;
	}

	public String putToList(){
		String idlist = "";
		Object obj = session.getAttribute("idlist");
		if(obj!=null){
			idlist = obj.toString();
			//重复的id不保存
			if((","+idlist+",").indexOf(","+favorite.getPageid()+",")==-1) {
				idlist += "," + favorite.getPageid();
			}
		}else{
			idlist = favorite.getPageid().toString();
		}

		if(idlist.startsWith(",")){
			idlist = idlist.substring(1);
		}
		if(idlist.endsWith(",")){
			idlist = idlist.substring(0, idlist.length()-1);
		}

		session.setAttribute("idlist", idlist);
		ctx.put("text", idlist);
		return TEXT;
	}

	public String removeFromList(){
		String idlist = "";
		Object obj = session.getAttribute("idlist");
		if(obj!=null){
			idlist = ","+obj.toString()+",";
		}
		idlist = idlist.replaceAll(","+favorite.getPageid()+",", ",");

		if(idlist.startsWith(",")){
			idlist = idlist.substring(1);
		}
		if(idlist.endsWith(",")){
			idlist = idlist.substring(0, idlist.length()-1);
		}

		session.setAttribute("idlist", idlist);
		ctx.put("text", idlist);
		return TEXT;
	}

	//删除推荐内容
	public String delete(){
        recommendService.delete(recommend.getId());
        ctx.put("text", "success");
        return TEXT;
    }

    //分享细节
    public String detail(){
		recommend = recommendService.findById(recommend.getId());
		if(recommend !=null) {
			List<Favorite> list = favoriteService.findByIds(recommend.getIdlist());

			ctx.put("list", list);
		}

		return SUCCESS;
	}

	//recommend
	public String manage_index(){
		if(recommend==null){
			recommend = new Recommend();
			recommend.setStatus(0);
		}
		List<Recommend> list = recommendService.listByStatus(recommend.getStatus(), pageindex*pagesize, pagesize);

		Integer total = recommendService.totalByStatus(recommend.getStatus());
		ctx.put("total", total.toString());
		ctx.put("list", list);
		return SUCCESS;
	}

	public String manage_verify(){
		Recommend recomm = recommendService.findById(recommend.getId());
		recomm.setStatus(recommend.getStatus());
		recommendService.update(recomm);
		ctx.put("text", "success");
		return TEXT;
	}

	public Favorite getFavorite() {
		return favorite;
	}

	public void setFavorite(Favorite favorite) {
		this.favorite = favorite;
	}

	public Recommend getRecommend() {
		return recommend;
	}

	public void setRecommend(Recommend recommend) {
		this.recommend = recommend;
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
}
