<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../layout/head.jsp"%>
<title>论坛挖掘-论坛名称</title>
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
                            <a href="<s:url action="bbs/content"/>">论坛帖子</a>
                            <a href="<s:url action="bbs/openid"/>" class="dt_title_left_vtd">论坛</a>
                            <div class="clear"></div>
                        </div>
                        <div class="dt_title_right">
                        	<form action="<s:url action="bbs/filter"/>" method="get">
							<span class="search">
								<input type="text" name="result.title"/>
								<a href="javascript:;" id="searchBtn">
									<img src="../images/ss_bt.png" />
								</a>
								<div class="clear"></div>
							</span>
							<input type="submit" id="submitBtn" style="display:none;">
							</form>                        
                            <div class="clear"></div>
                        </div>
                        <div class="clear"></div>
                    </div>
                    <div class="wxwj_wxgg">
                        <div class="wxwj_wxgg_link">
                            <a href="<s:url action="bbs/openid"/>">相关</a>
                            <a href="<s:url action="bbs/openid"/>?column.relation=1">自有</a>
                            <a href="<s:url action="bbs/openid"/>?column.relation=2">竞品</a>
                            <div class="clear"></div>
                        </div>
                        <s:iterator value="list" id="lst" status="st">
                        <div class="wxwj_wxgg_one">
                            <a href="javascript:;">
                            <!--
                                <div class="left">
                                    <span class="pic"><img src="<s:property value='#lst[0].logo'/>" width="90"/></span>
                                   	<s:if test="#lst[0].owner!=null">
                                    	<span class="pic_piao"><img src="../images/wbwj_dsj_pic.png"/></span>
                                    </s:if>
                                </div>
                                -->
                                <div class=right_lt>
                                    <ul>
                                        <li>
                                            <span class="title">
                                            	<span><s:property value="#lst[0].name" /></span> 
                                            	<span>                                           	
                                            	<s:if test="#lst[2].relation==1">
                                            		<img src="../images/zy_bt.png"/>
                                            	</s:if>
												</span>
                                           	</span>
                                            <div class="clear"></div>
                                        </li>
                                        <li>
                                            <span class="left">最近文章：</span>
                                            <span class="right"><s:property value="#lst[1].title"/></span>
                                            <div class="clear"></div>
                                        </li>
                                    </ul>
                                </div>
                                <div class="clear"></div>
                            </a>
                            <div class="wxwj_wxgg_one_piao"><img src="<s:property value='#lst[0].barcode'/>" width="90"/></div>
                        </div>
                        </s:iterator>
                    </div>
					<div class="pagination" data-pagesize="<s:property value="pagesize"/>" data-pageindex="<s:property value="pageindex"/>" data-total="<s:property value="total"/>" data-urlbase="../bbs/openid.shtml?column.relation=<s:property value="column.relation"/>"></div>
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

