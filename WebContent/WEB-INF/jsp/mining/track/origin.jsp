<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../layout/head.jsp"%>
	<title>媒体原发/转发数据分析</title>
</head>
<body>
	<div class="box">
		<%@ include file="../layout/top.jsp"%>
		<div class="width_box">
			<%@ include file="../layout/message.jsp"%>
			<div class="list">
				<div class="list_top">
					<img src="../images/list_top.jpg" />
				</div>
				<div class="list_center">
					<%@ include file="../layout/left.jsp"%>
					<div class="list_right">
						<%@ include file="guide.jsp"%>
	                    <div class="right_title">媒体原发/转发数据分析</div>
	                    <div class="hywj_box">
	                        <div class="hywj_one" id="originreport">
	                            <h2>原发和转发总和比例对比图</h2>
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
	                            	<select name="relation">
	                                    <option value="0" selected>全部</option>
	                                    <option value="1">自有</option>
	                                    <option value="2">竞品</option>
									</select>
	                                <div class="clear"></div>
	                                </div>
	                            </div>
	                            <div class="hywj_one_pic">
	                            	<div id="originchart" style="width:805px; height:450px;margin-left:0px;"></div>
	                            </div>
	                        </div>	  
	                        <div class="hywj_one" id="origincategoryreport">
	                            <h2>原发/转发数据分析</h2>
	                            <div class="hywj_one_text">
	                            	<div class="hywj_one_left">
	                            	<select name="days">
	                                    <option value="7" selected>最近一周</option>
	                                    <option value="30">最近一个月</option>
	                                    <option value="90">最近三个月</option>
	                                    <option value="183">最近半年</option>
	                                    <option value="365">最近一年</option>
									</select>
									&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp
	                            	<select name="relation">
	                                    <option value="0" selected>全部</option>
	                                    <option value="1">自有</option>
	                                    <option value="2">竞品</option>
									</select>
	                            	&nbsp;&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;&nbsp
	                            	<select name="origin">
	                                    <option value="0" selected>全部</option>
	                                    <option value="1">原发</option>
	                                    <option value="2">转发</option>
									</select>
	                                <div class="clear"></div>
	                                </div>
	                            </div>
	                            <div class="hywj_one_pic">
	                            	<div id="origincategorychart" style="width:805px; height:450px;margin-left:0px;"></div>
	                            </div>
	                        </div>	                                          
	                    </div>
					</div>
					<div class="clear"></div> 
				</div>
				<div class="list_top">
						<img src="../images/list_bottom.jpg" />
					</div>
			</div>			
		</div>
		<%@ include file="../layout/footer.jsp"%>
	</div>
</body>
</html>
