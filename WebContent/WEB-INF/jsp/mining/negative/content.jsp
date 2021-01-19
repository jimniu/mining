<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
	 <%@ include file="../layout/head.jsp"%>
	<title>负面管理-每日动态</title>
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
                    <%@ include file="guide.jsp"%>
                    <!--  
                    <div class="dt_title">
                        <div class="dt_title_left">每日动态</div>
                        <div class="dt_title_right">
                            <span class="search"><input type="text"/><a href="javascript:;"><img src="../images/ss_bt.png"/></a><div class="clear"></div></span>
                            <span class="up_text">3000条更新</span>
                            <span class="time">2015-09-09</span>
                            <div class="clear"></div>
                        </div>
                        <div class="clear"></div>
                    </div>
                    -->
                    <div class="wx_list_one">
                        <div class="wx_list_one_title">
                            <div class="title"><span>微博</span></div>
                            <div class="more"><a href="<s:url action="weibo/more"/>?column.type=2" target="_blank">更多&nbsp;&gt;&gt;</a></div>
                        </div>
                        <ul>
                        	<s:iterator value="wblist" status="st" id="lst">
                            <li>
                                <span class="left">
                                	<a href="<s:url action="weibo/feed"/>?result.columnid=<s:property value="#lst[0].columnid"/>&result.wbid=<s:property value="#lst[0].wbid"/>" target="_blank">
                                		<span>[<s:property value="#lst[1].nickname"/>]</span>
                                		<s:property value="@com.isd.util.C@subStr(#lst[0].content,65-#lst[1].nickname.length())"/>
                                	</a>
                                </span>
                                <span class="right">
                                    <span>
                                    	<a href="javascript:;">
                                    		<img src="../images/gd_bt.jpg"/>
                                    	</a>
                                    </span>
                                    <span><s:date name="#lst[0].ptime" format="yyyy-MM-dd" /></span>
                                    <div class="clear"></div>
                                    </span>
                                    <div class="fmgl_piao">
                                	<div class="piao_text">
                                    	<div class="piao_text_bt"><a href="javascript:;" class="gb"><img src="../images/x_bt.jpg"/></a>  <div class="clear"></div></div>
                                        <div class="piao_text_list">
                                        	<ul>
                                            	<li>标签：</li>
                                                <li class="text">
                                                    <select>
                                                        <option>1</option>
                                                        <option>1</option>
                                                        <option>1</option>
                                                        <option>1</option>
                                                    </select>
                                                </li>
                                            	<li>级别：</li>
                                                <li class="text1">
                                                    <select>
                                                        <option>1</option>
                                                        <option>1</option>
                                                        <option>1</option>
                                                        <option>1</option>
                                                    </select>
                                                </li>
                                                <div class="clear"></div>
                                            </ul>
                                        </div>
                                        <div class="piao_text_tj"><a href="javascript:;">提交</a></div>                                       
                                    </div>
                                </div>
                                <div class="clear"></div>
                            </li>
							</s:iterator>
                        </ul>
                    </div>
                    <div class="wx_list_one">
                        <div class="wx_list_one_title">
                            <div class="title"><span>媒体</span></div>
                            <div class="more"><a href="<s:url action="track/more"/>?column.type=2" target="_blank">更多&nbsp;&gt;&gt;</a></div>
                        </div>
                        <ul>
                        	<s:iterator value="xwlist">
                            <li>
                                <span class="left">
                                	<a href="<s:property value="url"/>" target="_blank">
                                		<span>[<s:property value="sitename"/>]</span>
                                		<s:property value="title"/>
                                	</a>
                                </span>
                                <span class="right">
                                    <span>
                                    	<a href="javascript:;">
                                    		<img src="../images/gd_bt.jpg"/>
                                    	</a>
                                    </span>
                                    <span><s:date name="ptime" format="yyyy-MM-dd" /></span>
                                    <div class="clear"></div>
                                    </span>
                                    <div class="fmgl_piao">
                                	<div class="piao_text">
                                    	<div class="piao_text_bt"><a href="javascript:;" class="gb"><img src="../images/x_bt.jpg"/></a>  <div class="clear"></div></div>
                                        <div class="piao_text_list">
                                        	<ul>
                                            	<li>标签：</li>
                                                <li class="text">
                                                    <select>
                                                        <option>1</option>
                                                        <option>1</option>
                                                        <option>1</option>
                                                        <option>1</option>
                                                    </select>
                                                </li>
                                            	<li>级别：</li>
                                                <li class="text1">
                                                    <select>
                                                        <option>1</option>
                                                        <option>1</option>
                                                        <option>1</option>
                                                        <option>1</option>
                                                    </select>
                                                </li>
                                                <div class="clear"></div>
                                            </ul>
                                        </div>
                                        <div class="piao_text_tj"><a href="javascript:;">提交</a></div>                                       
                                    </div>
                                </div>
                                <div class="clear"></div>
                            </li>
                        	</s:iterator>
                        </ul>
                    </div>
                    <div class="wx_list_one">
                        <div class="wx_list_one_title">
                            <div class="title"><span>微信</span></div>
                            <div class="more"><a href="<s:url action="wechat/more"/>?column.type=2" target="_blank">更多&nbsp;&gt;&gt;</a></div>
                        </div>
                        <ul>
                        	<s:iterator value="wxlist" status="st" id="lst">
                            <li>
                                <span class="left">
                                	<a href="<s:url action="wechat/detail"/>?result.id=<s:property value="id"/>" target="_blank">
                                		<span>[<s:property value="wxname"/>]</span>
                                		<s:property value="title"/>
                                	</a>
                                </span>
                                <span class="right">
                                    <span>
                                    	<a href="javascript:;">
                                    		<img src="../images/gd_bt.jpg"/>
                                    	</a>
                                    </span>
                                    <span><s:date name="ptime" format="yyyy-MM-dd" /></span>
                                    <div class="clear"></div>
                                    </span>
                                    <div class="fmgl_piao">
                                	<div class="piao_text">
                                    	<div class="piao_text_bt"><a href="javascript:;" class="gb"><img src="../images/x_bt.jpg"/></a>  <div class="clear"></div></div>
                                        <div class="piao_text_list">
                                        	<ul>
                                            	<li>标签：</li>
                                                <li class="text">
                                                    <select>
                                                        <option>1</option>
                                                        <option>1</option>
                                                        <option>1</option>
                                                        <option>1</option>
                                                    </select>
                                                </li>
                                            	<li>级别：</li>
                                                <li class="text1">
                                                    <select>
                                                        <option>1</option>
                                                        <option>1</option>
                                                        <option>1</option>
                                                        <option>1</option>
                                                    </select>
                                                </li>
                                                <div class="clear"></div>
                                            </ul>
                                        </div>
                                        <div class="piao_text_tj"><a href="javascript:;">提交</a></div>                                       
                                    </div>
                                </div>
                                <div class="clear"></div>
                            </li>
                        	</s:iterator>
                        </ul>
                    </div>
                    <div class="wx_list_one">
                        <div class="wx_list_one_title">
                            <div class="title"><span>论坛</span></div>
                            <div class="more"><a href="<s:url action="bbs/more"/>?column.type=2" target="_blank">更多&nbsp;&gt;&gt;</a></div>
                        </div>
                        <ul>
                        	<s:iterator value="ltlist">
                            <li>
                                <span class="left">
                                	<a href="<s:property value="url"/>" target="_blank">
                                		<span>[<s:property value="sitename"/>]</span>
                                		<s:property value="title"/>
                                	</a>
                                </span>
                                <span class="right">
                                    <span>
                                    	<a href="javascript:;">
                                    		<img src="../images/gd_bt.jpg"/>
                                    	</a>
                                    </span>
                                    <span><s:date name="ptime" format="yyyy-MM-dd" /></span>
                                    <div class="clear"></div>
                                    </span>
                                    <div class="fmgl_piao">
                                	<div class="piao_text">
                                    	<div class="piao_text_bt"><a href="javascript:;" class="gb"><img src="../images/x_bt.jpg"/></a>  <div class="clear"></div></div>
                                        <div class="piao_text_list">
                                        	<ul>
                                            	<li>标签：</li>
                                                <li class="text">
                                                    <select>
                                                        <option>1</option>
                                                        <option>1</option>
                                                        <option>1</option>
                                                        <option>1</option>
                                                    </select>
                                                </li>
                                            	<li>级别：</li>
                                                <li class="text1">
                                                    <select>
                                                        <option>1</option>
                                                        <option>1</option>
                                                        <option>1</option>
                                                        <option>1</option>
                                                    </select>
                                                </li>
                                                <div class="clear"></div>
                                            </ul>
                                        </div>
                                        <div class="piao_text_tj"><a href="javascript:;">提交</a></div>                                       
                                    </div>
                                </div>
                                <div class="clear"></div>
                            </li>
                        	</s:iterator>
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
</body>
</html>
