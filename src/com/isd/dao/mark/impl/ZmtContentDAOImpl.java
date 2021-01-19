package com.isd.dao.mark.impl;

import java.util.List;

import com.isd.dao.GenericDAOImpl;
import com.isd.dao.mark.ZmtContentDAO;
import com.isd.entity.mark.ZmtContent;
import com.isd.util.C;


public class ZmtContentDAOImpl extends GenericDAOImpl<ZmtContent, Integer> implements  ZmtContentDAO {
	
	public ZmtContentDAOImpl() {
		super(ZmtContent.class);
	}

	//列出匹配的结果列表
	public List<ZmtContent> list(Integer uid, String kw, String from, String to, Integer offset, Integer pagesize){
		String hql = "select c from ZmtContent c where c.uid="+uid+" ";
		
		String part = this.genSql(kw, from, to);
		if(part.length()>0){
			hql += part;
		}
		hql += " order by c.ctime desc";
		return this.findPageByHql(hql, offset, pagesize);		
	}
	
	public List<ZmtContent> list(Integer uid, Integer tagid, String kw, String from, String to, Integer offset, Integer pagesize){
		String hql = "select c from ZmtContent c, ZmtContenttag ct where ct.uid="+uid+" and ct.tagid="+tagid+" and ct.contentid=c.id ";
		
		String part = this.genSql(kw, from, to);
		if(part.length()>0){
			hql += part;
		}
		hql += " order by c.ctime desc";
		return this.findPageByHql(hql, offset, pagesize);		
	}
	
	public List<ZmtContent> list(Integer offset, Integer pagesize){
		String hql = "from ZmtContent c order by c.ctime desc ";
		return this.findPageByHql(hql, offset, pagesize);		
	}
	
	public List<ZmtContent> list(Integer uid){
		String hql = "select c from ZmtContent c where c.uid="+uid+" order by c.ctime desc";
		return this.findByHql(hql);		
	}
	
	
	//查询匹配到的用户数
	public Integer count(Integer uid, Integer tagid, String kw, String from, String to){
		String hql = "select count(c.id) from ZmtContent c, ZmtContenttag ct where ct.uid="+uid+" and ct.tagid="+tagid+" and ct.contentid=c.id ";
		String part = this.genSql(kw, from, to);
		if(part.length()>0){
			hql += part;
		}	
		return this.getCountByHql(hql);
	}
	
	public Integer count(Integer uid, String kw, String from, String to){
		String hql = "select count(id) from ZmtContent c where c.uid="+uid;
		String part = this.genSql(kw, from, to);
		if(part.length()>0){
			hql += part;
		}	
		return this.getCountByHql(hql);
	}
	
	public Integer count(){
		String hql = "select count(id) from ZmtContent";
		return this.getCountByHql(hql);
	}	
	
	private String genSql(String kw, String from, String to){
		String result = "";

		if(kw!=null&&kw.length()>0){
			result += "and c.title like '%"+kw+"%' ";
		}
		
		if(from!=null&&from.length()>0){
			result += "and c.ctime >='"+from+" 00:00:00' ";
		}

		if(to!=null&&to.length()>0){
			result += "and c.ctime <='"+to+" 23:59:59' ";
		}		
		return result;
	}
	
	//地址是否已经收藏
	public boolean exists(Integer uid, String url){
		String hql = "from ZmtContent where uid="+uid+" and address='"+url+"'";
		return this.findByHql(hql).size()>0;
	}
	
	//根据关键词统计数量
	public List countByKeyword(Integer uid){
		// TODO Auto-generated method stub
		String hql ="select d.value,c.total from (select * from zmt_tag where uid = "+uid+") d left join (select b.tagid id, count(b.id) total from (select * from zmt_contenttag where uid = "+uid+") b group by b.tagid)  c  on  d.id = c.id  ";
		return this.findBySql(hql);
	}
	
	//根据tagid以及时间返回
	public List countByTime(Integer tagid,Integer time){
		// TODO Auto-generated method stub
		String hql = "select DATE_FORMAT( ctime,  '%m月%d日' )  as date , COUNT(id)  as total  from  zmt_contenttag where 1=1 ";
		if(tagid != null && tagid > 0){
			hql = hql + "  and tagid="+tagid+" ";
		}
		if(time!=null){   
			hql = hql + " and ctime>='"+C.getDate(time) +"'";
		}
		hql = hql + " GROUP BY DATE_FORMAT( ctime, '%m月%d日')  order by date";
		return this.findBySql(hql);
	}

}
