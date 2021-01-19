<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
 	<%@ include file="../layout/head.jsp"%>
	<title>报表中心-集成报表</title>
</head>
<body>
<div class="box">
	<%@ include file="../layout/top.jsp"%>
    <div class="width_box">
        <%@ include file="../layout/message.jsp"%>
        <div class="list">
            <div class="list_top"><img src="../images/list_top.jpg"/></div>
            <div class="list_center">
				<%@ include file="../layout/left.jsp"%>
                <div class="list_right">
                    <div class="jcbb_title">集成报表</div>
                    <div class="jcbb_box">
                    	<form action="<s:url action="report/detail"/>" method="get">
                    	<div class="time">
                        	<span>时间：</span>
                            <span class="select">
                                  <select name="days">
                                  	  <option value="0">当天</option>
                                      <option value="7">最近一周</option>
                                      <option value="30">最近一个月</option>
                                      <option value="90">最近三个月</option>
                                      <option value="183">最近半年</option>
                                      <option value="365">最近一年</option>
                                  </select>
							</span>
                        	<span>范围：</span>
                            <span class="select">
                                  <select name="relation">
                                  	  <option value="0">所有内容</option>
                                      <option value="1">自有内容</option>
                                      <option value="2">竞品内容</option>
                                  </select>
							</span>							
                        </div>
                        <div class="jcbb_checkbox">
                        	<ul>
                            	<li>
                                	<span><input name="module" type="checkbox" value="overview" checked="checked"></span>
                                    <span>报表总揽</span>
                                </li>                        	
                            	<li>
                                	<span><input name="module" type="checkbox" value="weibo"></span>
                                    <span>微博挖掘报表</span>
                                </li>
                            	<li>
                                	<span><input name="module" type="checkbox" value="wechat"></span>
                                    <span>微信挖掘报表</span>
                                </li>                                
                            	<li>
                                	<span><input name="module" type="checkbox" value="news"></span>
                                    <span>媒体挖掘报表</span>
                                </li>

                            	<li>
                                	<span><input name="module" type="checkbox" value="bbs"></span>
                                    <span>论坛挖掘报表</span>
                                </li>
                                <!--  
                            	<li>
                                	<span><input name="module" type="checkbox" value="event"></span>
                                    <span>事件管理报表</span>
                                </li>
                            	<li>
                                	<span><input name="module" type="checkbox" value="negative"></span>
                                    <span>负面管理报表</span>
                                </li>
                                -->
                            </ul>
                        </div>
                        <div class="jcbb_bt">
                        	<a href="javascript:;" id="generatebtn">生成报表</a>
                        	<input type="submit" id="submitbtn" style="display:none;">
                        </div>
                        </form>
                    </div>
                    <div class="scbb_box" style="display:none;">
                    	<h2>已生成的报表</h2>
                        <ul>
                        	<li>
                            	<span class="left">
                                	<div class="title"><span class="bt"></span><span>月报表：</span>报表总揽；事件管理报表；负面管理报表</div>
                                    <div class="time">2016年2月12日</div>
                                </span>
                                <span class="right">正在生成中</span>
                            </li>
                        	<li>
                            	<span class="left">
                                	<div class="title"><span class="bt"></span><span>月报表：</span>报表总揽；事件管理报表；负面管理报表</div>
                                    <div class="time">2016年2月12日</div>
                                </span>
                                <span class="right"><a href="">下载</a></span>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="clear"></div>
            </div>
            <div class="list_top"><img src="../images/list_bottom.jpg"/></div>
        </div>
    </div>
	<%@ include file="../layout/footer.jsp"%>
</div>
<div class="yqts_box" style="display:none;">
    <div class="yqts_list">
        <div class="yqts_list_bt"><a href=""><img src="../images/index_dl_bt.png"/></a><div class="clear"></div></div>
        <h2>温馨提示</h2>
        <p>生成集成报表需要点时间哦～</p>
        <p>我们会在页面下方提供下载链接！</p>
        <div class="bt"><a href="">确定</a></div>
        
    </div>
</div>
</body>
</html>