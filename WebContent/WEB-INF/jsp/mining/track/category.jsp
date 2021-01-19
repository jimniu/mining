<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../layout/head.jsp"%>
	<title>媒体分析</title>
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
	                    <div class="right_title">媒体类型分析</div>
	                    <div class="hywj_box">
	                        <div class="hywj_one" id="categoryreport">
	                            <h2>按媒体类型统计内容总数</h2>
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
									&nbsp;&nbsp;&nbsp;&nbsp;|
	                            	&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="relation" value="0" data-value="<s:property value="relation"/>" checked>全部
	                            	&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="relation" value="1" data-value="<s:property value="relation"/>">自有
	                            	&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="relation" value="2" data-value="<s:property value="relation"/>">竞品	
	                                <div class="clear"></div>
	                                </div>
	                            </div>
	                            <div class="hywj_one_pic">
	                            	<div id="categorychart" style="width:805px; height:500px;margin-left:0px;"></div>
	                            </div>
	                        </div>	  
	                        <div class="hywj_one" id="mediareport">
	                            <h2>按媒体类型统计媒体个数</h2>
	                            <div class="hywj_one_text">
	                            	<div class="hywj_one_left">
	                            	<select name="days" data-value="<s:property value="days"/>">
	                                    <option value="7" selected>最近一周</option>
	                                    <option value="30">最近一个月</option>
	                                    <option value="90">最近三个月</option>
	                                    <option value="183">最近半年</option>
	                                    <option value="365">最近一年</option>
									</select>
									&nbsp;&nbsp;&nbsp;&nbsp;|
	                            	&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="mrelation" value="0" data-value="<s:property value="relation"/>" checked>全部
	                            	&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="mrelation" value="1" data-value="<s:property value="relation"/>">自有
	                            	&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="mrelation" value="2" data-value="<s:property value="relation"/>">竞品	
	                                <div class="clear"></div>
	                                </div>
	                            </div>
	                            <div class="hywj_one_pic">
	                            	<div id="mediachart" style="width:805px; height:500px;margin-left:0px;"></div>
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
