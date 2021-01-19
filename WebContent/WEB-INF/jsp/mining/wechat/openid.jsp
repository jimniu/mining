<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../layout/head.jsp"%>
<title>微信挖掘-微信公共号</title>
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
                            <a href="<s:url action="wechat/content"/>">微信文章</a>
                            <a href="<s:url action="wechat/openid"/>" class="dt_title_left_vtd">微信公共号</a>
                            <div class="clear"></div>
                        </div>
                        <div class="dt_title_right">
                        	<form action="<s:url action="wechat/filter"/>" method="get">
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
                            <a href="<s:url action="wechat/openid"/>">所有</a>
                            <a href="<s:url action="wechat/openid"/>?column.relation=1">自有</a>
                            <a href="<s:url action="wechat/openid"/>?column.relation=2">竞品</a>
                            <div class="clear"></div>
                        </div>
                        <s:iterator value="list" id="lst" status="st">
                        <div class="wxwj_wxgg_one">                                                        
	                             <div class="left">
	                             	<a href="javascript:;"   pos="<s:property value="#lst[0].id" />">
	                                 	<span class="pic"><img data-src="..<s:property value="@com.isd.util.C@getEncodeImageURL(#lst[0].logo)"/>" data-origin="<s:property value="#lst[0].logo"/>" width="90"/></span>
	                                	<s:if test="owner!=null">
	                                 		<span class="pic_piao"><img src="../images/wbwj_dsj_pic.png"/></span>
	                                 	</s:if>
	                                 </a>
	                             </div>
                                <div class="right">
                                    <ul>
                                        <li>
                                            <span class="title">
                                            	<span><s:property value="#lst[0].name" /></span> 
                                            	<span>                                           	
												</span>
                                           	</span>
                                            <span class="wxh">微信号： <s:property value="#lst[0].symbol" /></span>
                                            <div class="clear"></div>
                                        </li>
                                        <li>
                                            <span class="left">功能介绍：</span>
                                            <span class="right"><s:property value="#lst[0].intro" /></span>
                                            <div class="clear"></div>
                                        </li>
                                        <li>
                                            <span class="left">微信认证：</span>
                                            <span class="right"><s:property value="#lst[0].owner" /></span>
                                            <div class="clear"></div>
                                        </li>
                                        <li>
                                            <span class="left">最近文章：</span>
                                            <span class="right"  data-latest="<s:property value="#lst[0].id"/>">
                                           		<span class="l"></span>
                                           		<s:if test="mynetwork.type==2">
                                                	<span class="r"><a href="<s:url action="wechat/paint"/>">进入公众号画像</a></span>
                                                </s:if>
                                            </span>
                                            <div class="clear"></div>
                                        </li>
                                        <li>
                                            <span class="left"></span>
                                            <span class="right"><span></span><span></span></span>
                                            <div class="clear"></div>
                                        </li>
                                    </ul>
                                </div>
                                <div class="clear"></div>
                            <div class="wxwj_wxgg_one_piao"  pos="<s:property value="id" />"><img src="<s:url action="common/showimage"/>?url=<s:property value="barcode"/>" width="90"/></div>
                        </div>
                        </s:iterator>
                    </div>
					<div class="pagination" data-pagesize="<s:property value="pagesize"/>" data-pageindex="<s:property value="pageindex"/>" data-total="<s:property value="total"/>" data-urlbase="../wechat/openid.shtml?column.relation=<s:property value="column.relation"/>"></div>
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

