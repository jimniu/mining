<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="../layout/head.jsp"%>
    <title>微博挖掘-数据分析-详情</title>
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
                    <div class="wbwj_dsj_box">
                        <div class="wbwj_title">数据分析</div>
                        <div class="wbwj_dsj_top">
                            <div class="wbwj_dsj_top_left"><img src="<s:property value="weibo.photo"/>" width="93"/></div>
                            <div class="wbwj_dsj_top_right">
                                <ul>
                                    <li class="title">
                                        <span><s:property value="weibo.nickname"/></span>
                                        <span>
                                        	<s:if test='%{weibo.gender=="M"}'><img src="../images/ceng_male.jpg" /></s:if>
											<s:elseif test='%{weibo.gender=="F"}'><img src="../images/ceng_female.jpg" /></s:elseif>
                                        </span>
                                        <div class="clear"></div>
                                    </li>
                                    <s:if test="%{weibo.intro.length()>0}">
                                    <li class="text">
                                        <span class="left">简介：</span>
                                        <span class="right"><s:property value="weibo.intro"/></span>
                                        <div class="clear"></div>
                                    </li>
                                    </s:if>
									<s:if test="%{weibo.level.length()>0}">
                                    <li class="text">
										<span class="left">等级：</span>
										<span class="right"><s:property value="weibo.level"/></span>
                                        <div class="clear"></div>
                                    </li>
									</s:if>
									<s:if test="%{weibo.province.length()>0}">
                                    <li class="text">
										<span class="left">地域：</span>
										<span class="right"><s:property value="weibo.province"/> <s:property value="weibo.city"/></span>
                                        <div class="clear"></div>
                                    </li>
									</s:if>
									<s:if test="%{weibo.school.length()>0}">
                                    <li class="text">
										<span class="left">学校：</span>
										<span class="right"><s:property value="weibo.school"/></span>
                                        <div class="clear"></div>
                                    </li>
									</s:if>
									<s:if test="%{weibo.company.length()>0}">			
                                    <li class="text">
										<span class="left">公司：</span>
										<span class="right"><s:property value="weibo.company"/></span>
                                        <div class="clear"></div>
                                    </li>
									</s:if>
									<s:if test="%{weibo.tag.length()>0}">
                                    <li class="text">
										<span class="left">标签：</span>
										<span class="right"><s:property value="weibo.tag"/></span>
                                        <div class="clear"></div>
                                    </li>
									</s:if>									
                                    <li class="text">
                                        <span class="num">粉丝数：<s:property value="weibo.fans"/></span>
                                        <span class="num">关注数：<s:property value="weibo.follow"/></span>
                                        <span class="num">微博数：<s:property value="weibo.feed"/></span>
                                        <div class="clear"></div>
                                    </li>
                                </ul>
                            </div>
                            <div class="clear"></div>
                        </div>
                        <div rel-keyword="<s:property value="keyword"/>"></div>
                        <s:iterator value="list" status="st">
                        <div class="wbwj_sdj_one">
                            <div class="wbwj_sdj_one_top"><img src="../images/wbwj_dsj_text1.jpg"/></div>
                            <div class="wbwj_sdj_one_center">
                                <div class="wbwj_sdj_one_text"><s:property value="content" escape="false"/></div>
                                <div class="wbwj_sdj_one_time">
                                	<div class="wbwj_sdj_one_time_left"> 
	                                	<s:date name="ptime" format="yyyy年MM月dd日 HH:mm" />
										<s:if test="%{client.length()>0}">
										來自
										<s:property value="client" />
										</s:if>
                                	</div>
                                    <div class="wbwj_sdj_one_time_right">
                                    	<span><a href="javascript:;" data-delete="<s:property value="id"/>"><img src="../images/x_bt.jpg"/></a></span>
                                        <span>点赞数：<s:if test="praise>0"><s:property value="praise"/></s:if></span>
                                        <span>评论数：<s:if test="comment>0"><s:property value="comment"/></s:if></span>
                                        <span>转发数：<s:if test="forward>0"><s:property value="forward"/></s:if></span>
                                        <s:if test="url!=null">
                                        <span><a href="http://weibo.com<s:property value="url"/>" target="_blank">原文</a></span>
                                        </s:if>
                                    </div>
                                    <div class="clear"></div>									
								</div>
                            </div>
                            <div class="wbwj_sdj_one_bottom"><img src="../images/wbwj_dsj_text3.jpg"/></div>
                            <div class="clear"></div>
                        </div>
                        </s:iterator>
                        <div class="pagination" data-pagesize="<s:property value="pagesize"/>" data-pageindex="<s:property value="pageindex"/>" data-total="<s:property value="total"/>" data-urlbase="../weibo/feed.shtml?result.columnid=<s:property value="result.columnid"/>&result.wbid=<s:property value="result.wbid"/>">
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
