<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../layout/head.jsp"%>
    <title>微信挖掘-数据分析</title>
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
                    <div class="right_title">数据分析</div>
                    <div class="hywj_box">
                        <div class="hywj_one" id="columnreport">
                            <h2><a name="columnreport"></a>微信数据声量分析对比分析</h2>
                            <div class="hywj_one_text">
                                <div class="hywj_one_left">
		                            <select name="days" data-value="<s:property value="days"/>">
		                            	<option value="0">当天</option>
		                            	<option value="1">最近24小时</option>
		                                <option value="7" selected>最近一周</option>
		                                <option value="30">最近一个月</option>
		                                <option value="90">最近三个月</option>
		                                <option value="183">最近半年</option>
		                                <option value="365">最近一年</option>
									</select>
                                </div>
                                <div class="hywj_one_right">
                                </div>
                                <div class="clear"></div>
                            </div>
                            <div class="hywj_one_pic">
                            	<div id="columnchart" style="width:805px; height:450px;margin-left:0px;"></div>
                            </div>
                        </div>
                        <div class="hywj_one" id="sitereport">
                            <div class="hywj_one_bgpm">
                                <h2><a name="sitereport"></a>媒体关注分析</h2>
                                <div class="clear"></div>
                            </div>
                            <div class="hywj_one_text">
                                <div class="hywj_one_left">
		                            <select name="days" data-value="<s:property value="days"/>">
		                            	<option value="0">当天</option>
		                            	<option value="1">最近24小时</option>
		                                <option value="7" selected>最近一周</option>
		                                <option value="30">最近一个月</option>
		                                <option value="90">最近三个月</option>
		                                <option value="183">最近半年</option>
		                                <option value="365">最近一年</option>
									</select>                                
									&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp;
                                    <span>曝光排名前</span>
                                    <span>
                                        <select name="count">
											<option value="5">5</option>
											<option value="10">10</option>
											<option value="15">15</option>
											<option value="20" selected="selected">20</option>
											<option value="30">30</option>
											<option value="40">40</option>
											<option value="50">50</option>											
											<option value="100">100</option>											
                                        </select>
                                    </span>
                                    <span>位媒体</span>	                                    										
								</div>
                                <div class="hywj_one_right">
                                </div>
                                <div class="clear"></div>
                            </div>
                            <div class="hywj_one_pic">
                            	<div id="sitechart" style="width:805px; height:450px;margin-left:0px;"></div>
                            </div>
                        </div>
                        <div class="hywj_one" id="stockreport">
                            <h2><a name="stockreport"></a>微信数据声浪趋势分析对比</h2>
                            <div class="hywj_one_text">
                                <div class="hywj_one_left">
		                            <select name="days" data-value="<s:property value="days"/>">
		                            	<option value="0">当天</option>
		                            	<option value="1">最近24小时</option>
		                                <option value="7" selected>最近一周</option>
		                                <option value="30">最近一个月</option>
		                                <option value="90">最近三个月</option>
		                                <option value="183">最近半年</option>
		                                <option value="365">最近一年</option>
									</select> 
                                </div>
                                <div class="hywj_one_right">
                                </div>
                                <div class="clear"></div>
                            </div>
                            <div class="hywj_one_pic">
                            	<div id="stockchart" style="width:805px; height:450px;margin-left:0px;"></div>
                            </div>
                        </div>
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
