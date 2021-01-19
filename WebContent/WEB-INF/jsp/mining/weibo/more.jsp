<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../layout/head.jsp"%>
	<title>微博挖掘-每日动态-更多</title>
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
						<div class="dt_title">
							<div class="dt_title_left">
								每日动态
							</div>
							<!--  
							<div class="dt_title_right">
								<form action="<s:url action="track/filter"/>" method="get">
								<span class="search">
									<input type="text" name="result.title"/>
									<a href="javascript:;" id="searchBtn">
										<img src="../images/ss_bt.png" />
									</a>
									<div class="clear"></div>
								</span>
								<input type="hidden" name="result.columnid" value="<s:property value="column.id"/>"/>
								<input type="submit" id="submitBtn" style="display:none;">
								</form>
								<span class="up_text"><s:property value="updatecount"/>条更新</span>
								<span class="time"><s:property value="today"/></span>
								<div class="clear"></div>
							</div>
							-->
							<div class="clear"></div>
						</div>
						<div class="list_one">
							<div class="list_one_title">
								<div class="title">
									<span>
										<s:if test="column.relation==1">
										自有
										</s:if>
										<s:elseif test="column.relation==2" >
										竞品
										</s:elseif>
										<s:elseif test="column.type==1" >
										事件
										</s:elseif>						
										<s:elseif test="column.type==2" >
										负面
										</s:elseif>						
									</span>
									<span>
										<img src="../images/title_line.png" />
									</span>
									<span><s:property value="column.title"/></span>
								</div>
							</div>
							<ul>
								<s:iterator value="resultlist" status="st" id="lst">
								<li>
									<span class="left">
								    <a href="<s:url action="weibo/feed"/>?result.columnid=<s:property value="#lst[0].columnid"/>&result.wbid=<s:property value="#lst[1].wbid"/>" target="_blank">
                                		<span>[<s:property value="#lst[1].nickname"/>]</span>
                                		<s:property value="@com.isd.util.C@subStr(#lst[0].content,80-#lst[1].nickname.length())"/>
                                	</a>
                                	</span>
									<span class="right">
										<span><a href="javascript:;" data-delete="<s:property value="#lst[0].id"/>"><img src="../images/sc_bt.jpg"/></a></span>
										<s:date name="#lst[0].ptime" format="yyyy-MM-dd" />
									</span>
	                                <div class="clear"></div>
								</li>								
								</s:iterator>
								</ul>
							</div>
							<div class="pagination" data-pagesize="<s:property value="pagesize"/>" data-pageindex="<s:property value="pageindex"/>" data-total="<s:property value="total"/>" data-urlbase="../weibo/more.shtml?column.id=<s:property value="column.id"/>&column.type=<s:property value="column.type"/>&pagesize=<s:property value="pagesize"/>">
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
