<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
 	<%@ include file="../layout/head.jsp"%>
    <title>微博转发评论趋势分析</title>
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
                    <div class="right_title">转发评论数据分析</div>
                    <div class="gghx_box">
                        <div class="wbwj_sjfx_box" id="trenddiv">
                        	<h2>趋势分析：
								&nbsp;&nbsp;
                            	<select name="days">
                                    <option value="7">最近一周</option>
                                    <option value="30" selected>最近一个月</option>
                                    <option value="90">最近三个月</option>
                                    <option value="183">最近半年</option>
                                    <option value="365">最近一年</option>
								</select>
								&nbsp;&nbsp;|&nbsp;&nbsp;
								<select name="column">
                                    <s:iterator value="columnlist" status="st">
                                   	<option value="<s:property value="id"/>"><s:property value="title"/></option>
                                    </s:iterator>
                                </select>
                        	</h2>
                            <div class="pic">
                            	<div id="trendchart" style="width:805px; height:450px; margin-left:0px;"></div>
                            </div>
                        </div>                    
                        <div class="wbwj_sjfx_box" id="fansdiv">
                        	<h2>粉丝数量区间分析：
								&nbsp;&nbsp;
                            	<select name="days">
                                    <option value="7">最近一周</option>
                                    <option value="30" selected>最近一个月</option>
                                    <option value="90">最近三个月</option>
                                    <option value="183">最近半年</option>
                                    <option value="365">最近一年</option>
								</select>
								&nbsp;&nbsp;|&nbsp;&nbsp;
								<select name="column">
                                    <s:iterator value="columnlist" status="st">
                                   	<option value="<s:property value="id"/>"><s:property value="title"/></option>
                                    </s:iterator>
                                </select>
                        	</h2>
                            <div class="pic">
                            	<div id="fanschart" style="width:805px; height:450px; margin-left:0px;"></div>
                            </div>
                        </div>
                        <div class="wbwj_rwfx_box" id="devicediv">
                        	<h2>
                        		发布设备分布：
                        		&nbsp;&nbsp;
                            	<select name="days">
                                    <option value="7">最近一周</option>
                                    <option value="30" selected>最近一个月</option>
                                    <option value="90">最近三个月</option>
                                    <option value="183">最近半年</option>
                                    <option value="365">最近一年</option>
								</select>
                                &nbsp;&nbsp;|&nbsp;&nbsp;
                                <select name="column">
                                    <s:iterator value="columnlist" status="st">
                                        <option value="<s:property value="id"/>"><s:property value="title"/></option>
                                    </s:iterator>
                                </select>
                        	</h2>
                            <div class="pic">
                            	<div id="devicechart" style="width:805px; height:450px; margin-left:0px;"></div>
                            </div>
                        </div>
                    </div>
                </div>
             <div class="clear"></div>
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