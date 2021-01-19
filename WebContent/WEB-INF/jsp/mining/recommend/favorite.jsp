<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../layout/head.jsp"%>
	<title>微信通--创建咨询卡</title>
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
								<s:if test='favorite.module=="wx"'>
									<a href="<s:url action="recommend/favorite"/>">所有</a>
									<a href="<s:url action="recommend/favorite"/>?favorite.module=xw">媒体</a>
									<a href="<s:url action="recommend/favorite"/>?favorite.module=wx" class="dt_title_left_vtd">微信</a>
									<a href="<s:url action="recommend/favorite"/>?favorite.module=wb">微博</a>
									<a href="<s:url action="recommend/favorite"/>?favorite.module=lt">论坛</a>
									<a href="<s:url action="recommend/favorite"/>?favorite.module=zmt">自媒体</a>
								</s:if>
								<s:elseif test='favorite.module=="xw"'>
									<a href="<s:url action="recommend/favorite"/>">所有</a>
									<a href="<s:url action="recommend/favorite"/>?favorite.module=xw" class="dt_title_left_vtd">媒体</a>
									<a href="<s:url action="recommend/favorite"/>?favorite.module=wx">微信</a>
									<a href="<s:url action="recommend/favorite"/>?favorite.module=wb">微博</a>
									<a href="<s:url action="recommend/favorite"/>?favorite.module=lt">论坛</a>
									<a href="<s:url action="recommend/favorite"/>?favorite.module=zmt">自媒体</a>
								</s:elseif>
								<s:elseif test='favorite.module=="wb"'>
									<a href="<s:url action="recommend/favorite"/>">所有</a>
									<a href="<s:url action="recommend/favorite"/>?favorite.module=xw">媒体</a>
									<a href="<s:url action="recommend/favorite"/>?favorite.module=wx">微信</a>
									<a href="<s:url action="recommend/favorite"/>?favorite.module=wb"  class="dt_title_left_vtd">微博</a>
									<a href="<s:url action="recommend/favorite"/>?favorite.module=lt">论坛</a>
									<a href="<s:url action="recommend/favorite"/>?favorite.module=zmt">自媒体</a>
								</s:elseif>
								<s:elseif test='favorite.module=="lt"'>
									<a href="<s:url action="recommend/favorite"/>">所有</a>
									<a href="<s:url action="recommend/favorite"/>?favorite.module=xw">媒体</a>
									<a href="<s:url action="recommend/favorite"/>?favorite.module=wx">微信</a>
									<a href="<s:url action="recommend/favorite"/>?favorite.module=wb">微博</a>
									<a href="<s:url action="recommend/favorite"/>?favorite.module=lt" class="dt_title_left_vtd">论坛</a>
									<a href="<s:url action="recommend/favorite"/>?favorite.module=zmt">自媒体</a>
								</s:elseif>								
								<s:elseif test='favorite.module=="zmt"'>
									<a href="<s:url action="recommend/favorite"/>">所有</a>
									<a href="<s:url action="recommend/favorite"/>?favorite.module=xw">媒体</a>
									<a href="<s:url action="recommend/favorite"/>?favorite.module=wx">微信</a>
									<a href="<s:url action="recommend/favorite"/>?favorite.module=wb">微博</a>
									<a href="<s:url action="recommend/favorite"/>?favorite.module=lt">论坛</a>
									<a href="<s:url action="recommend/favorite"/>?favorite.module=zmt" class="dt_title_left_vtd">自媒体</a>
								</s:elseif>
								<s:else>
									<a href="<s:url action="recommend/favorite"/>" class="dt_title_left_vtd">所有</a>
									<a href="<s:url action="recommend/favorite"/>?favorite.module=xw">媒体</a>
									<a href="<s:url action="recommend/favorite"/>?favorite.module=wx">微信</a>
									<a href="<s:url action="recommend/favorite"/>?favorite.module=wb">微博</a>
									<a href="<s:url action="recommend/favorite"/>?favorite.module=lt">论坛</a>
									<a href="<s:url action="recommend/favorite"/>?favorite.module=zmt">自媒体</a>
								</s:else>
								<div class="clear"></div>
							</div>
							<div class="dt_title_right">
								<span class="b_save"><a href="javascript:;" id="create_recommend" data-module="<s:property value="favorite.module"/>">创建资讯卡</a></span>
							</div>
							<div class="clear"></div>
						</div>
						<div class="wx_list_two">
							<ul>
								<s:iterator value="list">
								<li>
								    <span class="leftfrom">
									   <input type="checkbox" name="chosen" value="<s:property value="id"/>" data-module="<s:property value="module"/>">
									</span>
									<span class="left" style="width:530px;">
									<a href="<s:property value="url"/>" title="[<s:property value="sitename"/>] <s:property value="title"/>" target="_blank" data-type="title"><span>[<s:property value="sitename"/>]</span> <s:property value="title"/></a>
									</span>
									<span class="right" style="width:135px;">											
										<s:date name="ptime" format="yyyy-MM-dd" />
										<a href="javascript:;" data-comment="<s:property value="id"/>"><img src="../images/comment.png" height="15" title="<s:property value="comment"/>"></a>
										<!--<img src="../images/read.png" height="15" title="<s:property value="readno"/>次阅读"> -->
										<s:if test="chosen==1">
											<img src="../images/chosen.png" height="15" title="已设置为资讯卡内容">
										</s:if>
										<s:else>
											<img src="../images/nochosen.png" height="15" title="未被资讯卡选中">
										</s:else>
										<a href="javascript:;" data-delete="<s:property value="id"/>"><img src="../images/delete.png" height="15" title="删除本条推荐"/></a>
									</span>
	                                <div class="clear"></div>
								</li>								
								</s:iterator>
								</ul>
								<input type="hidden" name="idlist" value="<s:property value="#session.idlist"/>">
							</div>
							<div class="pagination" data-pagesize="<s:property value="pagesize"/>" data-pageindex="<s:property value="pageindex"/>" data-total="<s:property value="total"/>" data-urlbase="../recommend/favorite.shtml?favorite.module=<s:property value="favorite.module"/>">
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
