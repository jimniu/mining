<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
 	<%@ include file="../layout/head.jsp"%>
    <title>微博挖掘-内容管理</title>
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
                        <div class="wbwj_title">内容管理</div>
                        <form action="<s:url action="weibo/content"/>" method="get">
                        <div class="wbwj_sjfx_top">
                            <ul>
                                <li>
                                    <span class="title">任务选择：</span>
                                    <span class="text">
                                        <select name="column.id" data-value="<s:property value="column.id"/>">
                                        	<option value="-1">所有</option>
                                        	<s:iterator value="columnlist" status="st">
                                        	<option value="<s:property value="id"/>"><s:property value="title"/></option>
                                        	</s:iterator>
                                        </select>
                                    </span>
                                    <div class="clear"></div>
                                </li>
                                <li>
                                    <span class="title">显示顺序：</span>
                                    <span class="text2">
                                        <select id="showorder" name="orderby" data-value="<s:property value="orderby"/>">
                                            <option value="ptime">发表顺序</option>
                                            <option value="ctime">挖掘顺序</option>                                            
                                            <option value="forward">转发数</option>
                                            <option value="comment">评论数</option>
                                            <option value="praise">点赞数</option>
                                        </select>
                                    </span>
                                    <div class="clear"></div>
                                </li>
                                <li>
                                    <span class="title">时间范围：</span>
                                    <span class="text2">
                                        <select id="showorder" name="days" data-value="<s:property value="days"/>">
                                            <option value="0">当天</option>
                                            <option value="1">最近24小时</option>                                            
                                            <option value="7">最近一周</option>
                                            <option value="30">最近一个月</option>
                                            <option value="90">最近三个月</option>
                                            <option value="183">最近半年</option>
                                            <option value="365">最近一年</option>
                                            <option value="3650">所有</option>
                                        </select>
                                    </span>
                                    <div class="clear"></div>
                                </li>                                
                                <li>
                                    <span class="title">显示条数：</span>
                                    <span class="text2"><input type="text" name="pagesize" value="<s:property value="pagesize"/>"/></span>
                                    <div class="clear"></div>
                                </li>   
                                <li>
                                    <span class="title">关键词：</span>
                                    <span class="text2"><input type="text" name="column.searchword" value="<s:property value="column.searchword"/>"/></span>
                                    <div class="clear"></div>
                                </li> 
                            </ul>
                        </div>
                        <input type="hidden" name="pageindex" value="<s:property value="pageindex"/>"/>
                        <input type="submit" style="display:none;" id="searchBtn"/>
                        </form>
                        <div class="wbwj_sjfx_link">
                            <a href="<s:url action="weibo/contentexport"/>?column.id=<s:property value="column.id"/>&column.searchword=<s:property value="column.searchword"/>&pagesize=<s:property value="pagesize"/>&days=<s:property value="days"/>">导 出</a>
                            <a href="javascript:;" id="filterBtn">筛 选</a>
                            <div class="clear"></div>
                        </div>
                        <s:iterator value="resultlist" status="st" id="lst">
						<div class="wbwj_sdj_one">
                            <div class="wbwj_sdj_one_top"><img src="../images/wbwj_dsj_text1.jpg"/></div>
                            <div class="wbwj_sdj_one_center">
                                <div class="wbwj_sdj_one_text">
                                	<a href="<s:url action="weibo/feed"/>?result.columnid=<s:property value="#lst[0].columnid"/>&result.wbid=<s:property value="#lst[0].wbid"/>" target="_blank">
                                		<span>[<s:property value="#lst[1].nickname"/>]</span>
                                	</a>
                                	<s:property value="#lst[0].content" escape="false"/>
                                </div>
                                <div class="wbwj_sdj_one_time">
                                	<div class="wbwj_sdj_one_time_left"> 
	                                	<s:date name="#lst[0].ptime" format="yyyy年MM月dd日 HH:mm" />
										<s:if test="%{#lst[0].client.length()>0}">
										來自
										<s:property value="#lst[0].client" />
										</s:if>
                                	</div>
                                    <div class="wbwj_sdj_one_time_right">
										<s:if test="#opinionhash[#lst[0].mid]==2">
										<span>
											<a href="javascript:;" data-negative="<s:property value="#lst[0].mid"/>" data-ptime="<s:date name="#lst[0].ptime" format="yyyy-MM-dd HH:mm:ss" />"><img src="../images/negatived.png" height="12" title="负面文章"/></a>
										</span>
										</s:if>
										<s:else>
										<span>
											<a href="javascript:;" data-negative="<s:property value="#lst[0].mid"/>" data-ptime="<s:date name="#lst[0].ptime" format="yyyy-MM-dd HH:mm:ss" />"><img src="../images/negative.png" height="12" title="将文章设为负面"/></a>
										</span>
										</s:else>
										<s:if test="#opinionhash[#lst[0].mid]==1">
										<span>
											<a href="javascript:;" data-positive="<s:property value="#lst[0].mid"/>" data-ptime="<s:date name="#lst[0].ptime" format="yyyy-MM-dd HH:mm:ss" />"><img src="../images/positived.png" height="12" title="正面文章"/></a>
										</span>
										</s:if>
										<s:else>
										<span>
											<a href="javascript:;" data-positive="<s:property value="#lst[0].mid"/>" data-ptime="<s:date name="#lst[0].ptime" format="yyyy-MM-dd HH:mm:ss" />"><img src="../images/positive.png" height="12" title="将文章设为正面"/></a>
										</span>
										</s:else>    
                                    	<span>
											<a href="javascript:;" data-favorite="<s:property value="#lst[0].id"/>"><img src="../images/recommend.jpg" title="收录到我的收藏"/></a>
										</span>
                                    	<span><a href="javascript:;" data-delete="<s:property value="#lst[0].id"/>"><img src="../images/x_bt.jpg" title="删除本文"/></a></span>
                                        <span>点赞：<s:if test="#lst[0].praise>0"><s:property value="#lst[0].praise"/></s:if></span>
                                        <span>评论：<s:if test="#lst[0].comment>0"><s:property value="#lst[0].comment"/></s:if></span>
                                        <span>转发：<s:if test="#lst[0].forward>0"><s:property value="#lst[0].forward"/></s:if></span>
                                        <s:if test="#lst[0].url!=null">
                                        <span><a href="http://weibo.com<s:property value="#lst[0].url"/>" target="_blank">原文</a></span>
                                        </s:if>
                                    </div>
                                    <div class="clear"></div>									
								</div>
                            </div>
                            <div class="wbwj_sdj_one_bottom"><img src="../images/wbwj_dsj_text3.jpg"/></div>
                            <div class="clear"></div>
                        </div>
                        </s:iterator>
	                    <div class="pagination">
							<s:if test="pageindex>0">
							<a href="../weibo/content.shtml?column.id=<s:property value="column.id"/>&column.keyword=<s:property value="column.keyword"/>&orderby=<s:property value="orderby"/>&pagesize=<s:property value="pagesize"/>&days=<s:property value="days"/>&pageindex=<s:property value="pageindex-1"/>" class="prev" rel="prev" style="">上一页</a>
							</s:if>
							<s:if test="#resultlist.size()>=pagesize">
							<a href="../weibo/content.shtml?column.id=<s:property value="column.id"/>&column.keyword=<s:property value="column.keyword"/>&orderby=<s:property value="orderby"/>&pagesize=<s:property value="pagesize"/>&days=<s:property value="days"/>&pageindex=<s:property value="pageindex+1"/>" class="next" rel="next">下一页</a>
							</s:if>
						</div>
                    </div>
                </div>
             <div class="clear"></div>
            </div>      
            <div class="clear"></div> 
        </div>
        <div class="list_top"><img src="../images/list_bottom.jpg"/></div>
    </div>
	<%@ include file="../layout/footer.jsp"%>
</div>
</body>
</html>