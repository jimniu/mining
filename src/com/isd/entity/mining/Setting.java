package com.isd.entity.mining;

import net.sf.json.JSONObject;

/**
 * Network entity. @author MyEclipse Persistence Tools
 */

public class Setting implements java.io.Serializable {

	// Fields

	/**
	* 
	*/
	private static final long serialVersionUID = 9091746569575909254L;
	
	private Integer dailycard; 		// 每日资讯卡数量
	private String 	wxaccount; 		// 微信公众号
	private String 	wxbarcode; 		// 微信二维码
	private String 	headimage; 		// 微信通历史的头图
	private String 	startpoint; 	// 内容抓取的起始时间,以字符串表示，如2017-01-01 12:00:00
	private String 	scope; 			// 抓取范围
	private Integer columnkeyword; 	// 每个栏目的最大关键词数量
	private Integer totalkeyword; 	// 总的关键词数量
	private Integer keywordchange; 	// 栏目的关键词修改次数，24小时之内的修改算一次

	// Constructors

	/** default constructor */
	public Setting() {
	}


	public static Setting json2obj(String json){
		JSONObject jsonobject = JSONObject.fromObject(json);
		return (Setting)JSONObject.toBean(jsonobject, Setting.class);
	}
	
	
	public static String obj2json(Setting setting){
		JSONObject jsonobject = JSONObject.fromObject(setting);
		return jsonobject.toString();
	}
	
	public Integer getDailycard() {
		return dailycard;
	}

	public void setDailycard(Integer dailycard) {
		this.dailycard = dailycard;
	}

	public String getWxaccount() {
		return wxaccount;
	}

	public void setWxaccount(String wxaccount) {
		this.wxaccount = wxaccount;
	}

	public String getWxbarcode() {
		return wxbarcode;
	}

	public void setWxbarcode(String wxbarcode) {
		this.wxbarcode = wxbarcode;
	}

	public String getHeadimage() {
		return headimage;
	}

	public void setHeadimage(String headimage) {
		this.headimage = headimage;
	}


	public String getScope() {
		return scope;
	}

	public void setScope(String scope) {
		this.scope = scope;
	}

	public Integer getColumnkeyword() {
		return columnkeyword;
	}

	public void setColumnkeyword(Integer columnkeyword) {
		this.columnkeyword = columnkeyword;
	}

	public Integer getTotalkeyword() {
		return totalkeyword;
	}

	public void setTotalkeyword(Integer totalkeyword) {
		this.totalkeyword = totalkeyword;
	}

	public Integer getKeywordchange() {
		return keywordchange;
	}

	public void setKeywordchange(Integer keywordchange) {
		this.keywordchange = keywordchange;
	}

	public String getStartpoint() {
		return startpoint;
	}

	public void setStartpoint(String startpoint) {
		this.startpoint = startpoint;
	}

}