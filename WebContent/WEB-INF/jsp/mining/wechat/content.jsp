﻿<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
                        <div class="dt_title_left">
                            <a href="<s:url action="wechat/content"/>" class="dt_title_left_vtd">微信文章</a>
                            <a href="<s:url action="wechat/openid"/>">微信公共号</a>
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
                            <span class="up_text"><s:property value="updatecount"/>条更新</span>
                            <span class="time"><s:property value="today"/></span>
                            <div class="clear"></div>
                        </div>
                        <div class="clear"></div>
                    </div>
                    <s:if test="#columnlist[0].searchword!=''">
                    <div class="wx_list_one">
                        <div class="wx_list_one_title">
                            <div class="title">
                            	<span>	                            		
                            		<s:if test="#columnlist[0].relation==1">自有</s:if>
	                            	<s:else>竞品	</s:else>
	                            </span>
	                            <span><img src="../images/title_line.png"/></span><span><s:property value="#columnlist[0].title"/></span>
							</div>
                            <div class="more"><a href="<s:url action="wechat/more"/>?column.id=<s:property value="#columnlist[0].id"/>" target="_blank">更多&nbsp;&gt;&gt;</a></div>
                        </div>
                        <ul class="lanmu">
							<s:if test="#resultlist[0].size()==0">
                                <!--<span class="left"><h4 style="color:#F00;text-align:center;line-height:180px;">全力数据挖掘中...，建议更换热门关键词试一试</h4></span>-->
							</s:if>
							<s:iterator value="#resultlist[0]" status="st" id="lst">                        	
                            <li>
                                  <span class="left"><a href="<s:url action="wechat/detail"/>?result.id=<s:property value="id"/>" target="_blank"><span data-wxid="<s:property value="wxid"/>">[<s:property value="wxname"/>]</span><s:property value="title"/></a></span>
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
                                        	<a href="javascript:;" data-favorite="<s:property value="id"/>"><img src="../images/recommend.jpg" title="收录到我的收藏"></a>
                                        </span>
	                                  	<span>
	                                  		<a href="javascript:;" data-delete="<s:property value="id"/>"><img src="../images/sc_bt.jpg" title="删除本文"/></a>
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
                    </s:if>
                    <s:if test="#columnlist[1].searchword!=''">
                    <div class="wx_list_one">
                        <div class="wx_list_one_title">
                            <div class="title">
								<span>	                            		
                            		<s:if test="#columnlist[1].relation==1">自有</s:if>
	                            	<s:else>竞品	</s:else>
	                            </span>
                            	<span><img src="../images/title_line.png"/></span><span><s:property value="#columnlist[1].title"/></span>
                            </div>
                            <div class="more"><a href="<s:url action="wechat/more"/>?column.id=<s:property value="#columnlist[1].id"/>" target="_blank">更多&nbsp;&gt;&gt;</a></div>
                        </div>
                        <ul class="lanmu">
							<s:if test="#resultlist[1].size()==0">
                                <!--<span class="left"><h4 style="color:#F00;text-align:center;line-height:180px;">全力数据挖掘中...，建议更换热门关键词试一试</h4></span>-->
							</s:if>
							<s:iterator value="#resultlist[1]" status="st" id="lst">                        	
                            <li>
                                  <span class="left"><a href="<s:url action="wechat/detail"/>?result.id=<s:property value="id"/>" target="_blank"><span data-wxid="<s:property value="wxid"/>">[<s:property value="wxname"/>]</span><s:property value="title"/>  </a></span>
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
                                        	<a href="javascript:;" data-favorite="<s:property value="id"/>"><img src="../images/recommend.jpg" title="收录到我的收藏"></a>
                                        </span>
	                                  	<span>
	                                  		<a href="javascript:;" data-delete="<s:property value="id"/>"><img src="../images/sc_bt.jpg" title="删除本文"/></a>
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
                    </s:if>
					<s:if test="#columnlist[2].searchword!=''">
                    <div class="wx_list_one">
                        <div class="wx_list_one_title">
                            <div class="title">
                            	<span>	                            		
                            		<s:if test="#columnlist[2].relation==1">自有</s:if>
	                            	<s:else>竞品	</s:else>
	                            </span>
                            	<span><img src="../images/title_line.png"/></span><span><s:property value="#columnlist[2].title"/></span>
                            </div>
                            <div class="more"><a href="<s:url action="wechat/more"/>?column.id=<s:property value="#columnlist[2].id"/>" target="_blank">更多&nbsp;&gt;&gt;</a></div>
                        </div>
                        <ul class="lanmu">
							<s:if test="#resultlist[2].size()==0">
                                <!--<span class="left"><h4 style="color:#F00;text-align:center;line-height:180px;">全力数据挖掘中...，建议更换热门关键词试一试</h4></span>-->
							</s:if>
							<s:iterator value="#resultlist[2]" status="st" id="lst">                        	
                            <li>
                                  <span class="left"><a href="<s:url action="wechat/detail"/>?result.id=<s:property value="id"/>" target="_blank"><span data-wxid="<s:property value="wxid"/>">[<s:property value="wxname"/>]</span><s:property value="title"/>  </a></span>
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
                                        	<a href="javascript:;" data-favorite="<s:property value="id"/>"><img src="../images/recommend.jpg" title="收录到我的收藏"></a>
                                        </span>
	                                  	<span>
	                                  		<a href="javascript:;" data-delete="<s:property value="id"/>"><img src="../images/sc_bt.jpg" title="删除本文"/></a>
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
                    </s:if>
                    <s:if test="#columnlist[3].searchword!=''">
                    <div class="wx_list_one">
                        <div class="wx_list_one_title">
                            <div class="title">
								<span>	                            		
                            		<s:if test="#columnlist[3].relation==1">自有</s:if>
	                            	<s:else>竞品	</s:else>
	                            </span>
                            	<span><img src="../images/title_line.png"/></span><span><s:property value="#columnlist[3].title"/></span>
                            </div>
                            <div class="more"><a href="<s:url action="wechat/more"/>?column.id=<s:property value="#columnlist[3].id"/>" target="_blank">更多&nbsp;&gt;&gt;</a></div>
                        </div>
                        <ul>
							<s:if test="#resultlist[3].size()==0">
                                <!--<span class="left"><h4 style="color:#F00;text-align:center;line-height:180px;">全力数据挖掘中...，建议更换热门关键词试一试</h4></span>-->
							</s:if>
							<s:iterator value="#resultlist[3]" status="st" id="lst">                        	
                            <li>
                                  <span class="left"><a href="<s:url action="wechat/detail"/>?result.id=<s:property value="id"/>" target="_blank"><span data-wxid="<s:property value="wxid"/>">[<s:property value="wxname"/>]</span><s:property value="title"/>  </a></span>
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
                                        	<a href="javascript:;" data-favorite="<s:property value="id"/>"><img src="../images/recommend.jpg" title="收录到我的收藏"></a>
                                        </span>
	                                  	<span>
	                                  		<a href="javascript:;" data-delete="<s:property value="id"/>"><img src="../images/sc_bt.jpg" title="删除本文"/></a>
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
                    </s:if>
                    <s:if test="#columnlist[4].searchword!=''">
                    <div class="wx_list_one">
                        <div class="wx_list_one_title">
                            <div class="title">
                            	<span>	                            		
                            		<s:if test="#columnlist[4].relation==1">自有</s:if>
	                            	<s:else>竞品	</s:else>
	                            </span>
                            	<span><img src="../images/title_line.png"/></span><span><s:property value="#columnlist[4].title"/></span>
                         	</div>
                            <div class="more"><a href="<s:url action="wechat/more"/>?column.id=<s:property value="#columnlist[4].id"/>" target="_blank">更多&nbsp;&gt;&gt;</a></div>
                        </div>
                        <ul>
							<s:if test="#resultlist[4].size()==0">
                                <!--<span class="left"><h4 style="color:#F00;text-align:center;line-height:180px;">全力数据挖掘中...，建议更换热门关键词试一试</h4></span>-->
							</s:if>
							<s:iterator value="#resultlist[4]" status="st" id="lst">                        	
                            <li>
                                  <span class="left"><a href="<s:url action="wechat/detail"/>?result.id=<s:property value="id"/>" target="_blank"><span data-wxid="<s:property value="wxid"/>">[<s:property value="wxname"/>]</span><s:property value="title"/>  </a></span>
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
                                        	<a href="javascript:;" data-favorite="<s:property value="id"/>"><img src="../images/recommend.jpg" title="收录到我的收藏"></a>
                                        </span>
	                                  	<span>
	                                  		<a href="javascript:;" data-delete="<s:property value="id"/>"><img src="../images/sc_bt.jpg" title="删除本文"/></a>
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
                    </s:if>
                    <s:if test="#columnlist[5].searchword!=''">
                    <div class="wx_list_one">
                        <div class="wx_list_one_title">
                            <div class="title">
                            	<span>	                            		
                            		<s:if test="#columnlist[5].relation==1">自有</s:if>
	                            	<s:else>竞品	</s:else>
	                            </span>
                            	<span><img src="../images/title_line.png"/></span><span><s:property value="#columnlist[5].title"/></span>
                            </div>
                            <div class="more"><a href="<s:url action="wechat/more"/>?column.id=<s:property value="#columnlist[5].id"/>" target="_blank">更多&nbsp;&gt;&gt;</a></div>
                        </div>
                        <ul>
							<s:if test="#resultlist[5].size()==0">
                                <!--<span class="left"><h4 style="color:#F00;text-align:center;line-height:180px;">全力数据挖掘中...，建议更换热门关键词试一试</h4></span>-->
							</s:if>
							<s:iterator value="#resultlist[5]" status="st" id="lst">                        	
                            <li>
                                  <span class="left"><a href="<s:url action="wechat/detail"/>?result.id=<s:property value="id"/>" target="_blank"><span data-wxid="<s:property value="wxid"/>">[<s:property value="wxname"/>]</span><s:property value="title"/>  </a></span>
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
                                        	<a href="javascript:;" data-favorite="<s:property value="id"/>"><img src="../images/recommend.jpg" title="收录到我的收藏"></a>
                                        </span>
	                                  	<span>
	                                  		<a href="javascript:;" data-delete="<s:property value="id"/>"><img src="../images/sc_bt.jpg" title="删除本文"/></a>
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
                    </s:if>                    
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

