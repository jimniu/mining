<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../layout/head.jsp"%>
<title>微信挖掘-每日动态</title>
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
                        <div class="dt_title_left">每日动态</div>
                        <div class="dt_title_right">
							<form action="<s:url action="wechat/filter"/>" method="get">
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
                        <div class="clear"></div>
                    </div>
                    <div class="wx_list_one">
                        <div class="list_one_title">
                            <div class="title">
                            	<span>
                            	<s:if test="column.relation==1">自有</s:if>
                            	<s:elseif test="column.relation==2">竞品</s:elseif>
                            	<s:elseif test="column.type==1">事件</s:elseif>
                            	<s:elseif test="column.type==2">负面</s:elseif>
                            	</span>
                            	<span>
                            		<img src="../images/title_line.png"/>
                            	</span>
                            	<span>
                            		<s:property value="column.title"/>
                            	</span>
								<span>
                            		<img src="../images/title_line.png"/>
                            	</span>
                            	<form action="<s:url action="wechat/more"/>" method="get" id="">
                            	<span>
	                            	显示顺序:
	                            	<select name="type" data-value="<s:property value="type"/>">
	                            		<option value="ptime">发布时间</option>
	                            		<option value="readcount">阅读数</option>
	                            		<option value="likecount">点赞数</option>
	                            	</select>
	                            	时间范围:
	                            	<select name="days" data-value="<s:property value="days"/>">
	                            		<option value="0">当日</option>
	                            		<option value="1">过去24小时</option>
	                            		<option value="7">过去一周</option>
	                            		<option value="30">过去一个月</option>
	                            		<option value="90">过去三个月</option>
	                            		<option value="183">过去半年</option>
	                            		<option value="365">过去一年</option>
	                            		<option value="3650">所有</option>
	                            	</select>
	                            </span>
	                            <input type="hidden" name="column.id" value="<s:property value="column.id"/>"/>
								<input type="submit" style="display:none;">
	                            </form>
                            </div>
                        </div>
                        <ul>
							<s:iterator value="list" status="st" id="lst">                        	
                            <li data-id="<s:property value="id"/>" data-wxid="<s:property value="wxid"/>">
                           		<span class="left"><a href="<s:url action="wechat/detail"/>?result.id=<s:property value="id"/>" target="_blank"><span>[<s:property value="wxname"/>]</span><s:property value="title"/></a></span>
                                <span class="right">
									<s:if test="#opinionhash[contentid]==2">
									<span>
										<a href="javascript:;" data-negative="<s:property value="contentid"/>" data-ptime="<s:date name="ptime" format="yyyy-MM-dd HH:mm:ss" />"><img src="../images/negatived.png" height="12" title="负面文章"/></a>
									</span>
									</s:if>
									<s:else>
									<span>
										<a href="javascript:;" data-negative="<s:property value="contentid"/>" data-ptime="<s:date name="ptime" format="yyyy-MM-dd HH:mm:ss" />"><img src="../images/negative.png" height="12" title="将文章设为负面"/></a>
									</span>
									</s:else>
									<s:if test="#opinionhash[contentid]==1">
									<span>
										<a href="javascript:;" data-positive="<s:property value="contentid"/>" data-ptime="<s:date name="ptime" format="yyyy-MM-dd HH:mm:ss" />"><img src="../images/positived.png" height="12" title="正面文章"/></a>
									</span>
									</s:if>
									<s:else>
									<span>
										<a href="javascript:;" data-positive="<s:property value="contentid"/>" data-ptime="<s:date name="ptime" format="yyyy-MM-dd HH:mm:ss" />"><img src="../images/positive.png" height="12" title="将文章设为正面"/></a>
									</span>
									</s:else>
									<span>
										<a href="javascript:;" data-favorite="<s:property value="id"/>"><img src="../images/recommend.jpg"></a>
									</span>
                               		<span>
                               			<a href="javascript:;" data-delete="<s:property value="id"/>"><img src="../images/sc_bt.jpg"/></a>
                               		</span>
                                 	<span><s:date name="ptime" format="yyyy-MM-dd" /></span>
                                 	<div class="clear"></div>
                                 	<div class="right_yd_piao"  data-id="<s:property value="id"/>">阅读：<s:property value="readcount"/></div>
                                 	<div class="right_dz_piao"  data-id="<s:property value="id"/>">赞：<s:property value="likecount"/></div>
                                </span>
                                <div class="clear"></div>                                   
                            </li>
                            </s:iterator>
                        </ul>
                    </div>
                    <div class="pagination" data-pagesize="<s:property value="pagesize"/>" data-pageindex="<s:property value="pageindex"/>" data-total="<s:property value="total"/>" data-urlbase="../wechat/more.shtml?column.id=<s:property value="column.id"/>&column.type=<s:property value="column.type"/>&type=<s:property value="type"/>&days=<s:property value="days"/>"></div>
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

