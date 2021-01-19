<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../layout/head.jsp"%>
	<title>微信通-我的资讯卡</title>
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
							资讯卡列表
							</div>
							<div class="clear"></div>
						</div>
						<div class="wx_list_one">
							<ul>
								<s:iterator value="list">
								<li>
									<a href="javascript:;" data-preview="<s:url action="recommend/detail"/>?recommend.id=<s:property value="id"/>" title="<s:property value="title"/>">
										&nbsp;&nbsp;&nbsp;&nbsp;<span ><s:property value="title"/></span>
	                                </a>
									<span class="right" style="width:440px;">
										<span>
											<a href="javascript:;" data-delete="<s:property value="id"/>"><img src="../images/sc_bt.jpg"/></a>
										</span>
										<span>
											<a href="javascript:;" data-edit="<s:property value="id"/>"><img src="../images/bj_bt.jpg"/></a>
										</span>
										<span>
											<a href="javascript:;" data-link="<s:property value="prefix"/>/mobile/recommend.shtml?recommend.id=<s:property value="id"/>"><img src="../images/wechat.png" height="12"/></a>
										</span>
										<span>
											<s:if test="type==1">
												私有
											</s:if>
											<s:else>
												公开
											</s:else>
										</span>
										<span>
											<s:if test="status==1">
												发布
											</s:if>
											<s:else>
												待审
											</s:else>
										</span>
										<span>
											<s:date name="ctime" format="yyyy-MM-dd" />
										</span>

										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<span>转:<s:property value="shareno"/></span>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<span>赞:<s:property value="praise"/></span>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<span>阅:<s:property value="readno"/></span>

									</span>
	                                <div class="clear"></div>
								</li>								
								</s:iterator>
								</ul>
							</div>
							<div class="pagination" data-pagesize="<s:property value="pagesize"/>" data-pageindex="<s:property value="pageindex"/>" data-total="<s:property value="total"/>" data-urlbase="../recommend/list.shtml">
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
