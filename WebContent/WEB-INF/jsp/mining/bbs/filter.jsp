<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../layout/head.jsp"%>
<title>论坛挖掘-每日动态-搜索</title>
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
					<div class="dt_title">
						<div class="dt_title_left">
							搜索结果
						</div>
					</div>
					<div class="clear"></div>					
                    <div class="list_one_search">
                        <div class="list_one_search_box">
	                        <form action="<s:url action="bbs/filter"/>" method="get">
	                        <ul>
	                        	<li>
	                            	<span>标题：</span>
	                                <span class="right">
	                                	<input type="text" name="result.title" value="<s:property value="result.title"/>"/>
									</span>
	                                <div class="clear"></div>
								</li>
	                            <li class="text">
	                            	<span>论坛名称：</span>
	                                <span class="right">
	                                	<input type="text" name="result.sitename" value="<s:property value="result.sitename"/>"/>
	                                </span>
	                                <div class="clear"></div>
								</li>
	                            <li>
	                            	<span>收录时间从：</span>
	                                <span class="right1">
	                                	<input type="text" name="from" class="datepicker" value="<s:property value="from"/>"/>
	                                </span>
	                                <span class="text">到</span>
	                                <span class="right1">
	                                	<input type="text" name="to" class="datepicker" value="<s:property value="to"/>"/>
	                            	</span>
	                            	<div class="clear"></div>
	                            </li>
	                            <li class="text2">
	                            	<a href="javascript:;" id="searchBtn">搜索</a>
	                            	<div class="clear"></div>
	                            </li>
	                        	<div class="clear"></div>
	                        </ul>
	                        <input type="submit" id="submitBtn" style="display:none;">
	                    	<input type="hidden" name="result.columnid" value="<s:property value="result.columnid"/>"/>
	                    </form>                        
                        </div>
                        <ul>
							<s:iterator value="list" status="st" id="lst">
	                        <li>	
								<span class="left">	                            	
		                            <a href="<s:property value="#lst[3]"/>" target="_blank"><span>[<s:property value="#lst[2]"/>]</span><s:property value="#lst[1]"/></a>
								</span>
	                            <span class="right">
	                            	<s:date name="#lst[4]" format="yyyy-MM-dd" />
									<s:if test="#opinionhash[#lst[0]]==2">
									<span>
										<a href="javascript:;" data-negative="<s:property value="#lst[0]"/>" data-ptime="<s:date name="#lst[4]" format="yyyy-MM-dd HH:mm:ss" />"><img src="../images/negatived.png" height="12" title="负面文章"/></a>
									</span>
									</s:if>
									<s:else>
									<span>
										<a href="javascript:;" data-negative="<s:property value="#lst[0]"/>" data-ptime="<s:date name="#lst[4]" format="yyyy-MM-dd HH:mm:ss" />"><img src="../images/negative.png" height="12" title="将文章设为负面"/></a>
									</span>
									</s:else>
									<s:if test="#opinionhash[contentid]==1">
									<span>
										<a href="javascript:;" data-positive="<s:property value="#lst[0]"/>" data-ptime="<s:date name="#lst[4]" format="yyyy-MM-dd HH:mm:ss" />"><img src="../images/positived.png" height="12" title="正面文章"/></a>
									</span>
									</s:if>
									<s:else>
									<span>
										<a href="javascript:;" data-positive="<s:property value="#lst[0]"/>" data-ptime="<s:date name="#lst[4]" format="yyyy-MM-dd HH:mm:ss" />"><img src="../images/positive.png" height="12" title="将文章设为正面"/></a>
									</span>
									</s:else>
	                            	<span>
	                            		<a href="javascript:;" data-favorite="<s:property value="#lst[5]"/>"><img src="../images/recommend.jpg" title="收录到我的收藏"></a>
	                                </span>
		                            <span>
		                            	<a href="javascript:;" data-delete="<s:property value="#lst[5]"/>"><img src="../images/sc_bt.jpg" title="删除本文"/></a>
		                            </span>	                                	
								</span>
								<div class="clear"></div>
							</li>
	                        </s:iterator>                            
                        </ul>
                    </div>
                    <div class="pagination">
                    	<s:if test="pageindex>0">
						<a href="../bbs/filter.shtml?result.columnid=<s:property value="result.columnid"/>&result.title=<s:property value="result.title"/>&result.sitename=<s:property value="result.sitename"/>&from=<s:property value="from"/>&to=<s:property value="to"/>&pageindex=<s:property value="pageindex-1"/>" class="prev" rel="prev">上一页</a>
						</s:if>
						<s:if test="#list.size()>=pagesize">
						<a href="../bbs/filter.shtml?result.columnid=<s:property value="result.columnid"/>&result.title=<s:property value="result.title"/>&result.sitename=<s:property value="result.sitename"/>&from=<s:property value="from"/>&to=<s:property value="to"/>&pageindex=<s:property value="pageindex+1"/>" class="next" rel="next">下一页</a>
						</s:if>                    
	                    <s:if test="#list.size()>0">
	                    <a href="../bbs/export.shtml?result.columnid=<s:property value="result.columnid"/>&result.title=<s:property value="result.title"/>&result.sitename=<s:property value="result.sitename"/>&from=<s:property value="from"/>&to=<s:property value="to"/>&pageindex=<s:property value="pageindex"/>" download>导出本页</a>
	                    </s:if>
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

