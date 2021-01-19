<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../layout/head.jsp"%>
<title>每日总揽</title>
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
                    <div class="mrzl_top">
                        <div class="wbwj_top_title">每日总揽：<span>(<s:date name="now" format="yyyy-MM-dd HH:mm:ss" />)</span></div>
                        <div class="wbwj_top_kssj"><a href="<s:url action="site/chartview"/>"  target="blank"><img src="../images/kssj_bt.jpg"/></a></div>
                        <div class="clear"></div>
                    </div>
                    <div class="mrzl_pic_list">
                        <div class="mrzl_pic_list_one">
                            <h2>当日数据：</h2>
                            <div class="mrzl_drsj">
                                <ul>
                                    <li class="first">
                                        <span class="left" data-rate="<s:property value="wxtodayrate"/>">
                                        	<a href="<s:url action="wechat/content"/>">
                                        	<canvas width="55" height="55" style="margin-left:0px;"></canvas>
                                        	</a>
                                        </span>
                                        <span class="right">
                                            <span class="title"><span>微信数量：</span><span class="text1">&uarr;</span></span>
                                            <span class="num"><s:property value="wxtoday"/></span>
                                        </span>
                                        <div class=" clear"></div>
                                    </li>
                                    <li>
                                        <span class="left" data-rate="<s:property value="wbtodayrate"/>">
	                                        <a href="<s:url action="weibo/columnlist"/>">
	                                        	<canvas width="55" height="55" style="margin-left:0px;"></canvas>
	                                        </a>
                                        </span>
                                        <span class="right">
                                            <span class="title"><span>微博数量：</span><span class="text2">&uarr;</span></span>
                                            <span class="num"><s:property value="wbtoday"/></span>
                                        </span>
                                        <div class=" clear"></div>
                                    </li>
                                    <li>
                                        <span class="left" data-rate="<s:property value="xwtodayrate"/>">
                                        	<a href="<s:url action="track/content"/>">
                                        		<canvas width="55" height="55" style="margin-left:0px;"></canvas>
                                        	</a>
                                        </span>
                                        <span class="right">
                                            <span class="title"><span>新闻数量：</span><span class="text3">&uarr;</span></span>
                                            <span class="num"><s:property value="xwtoday"/></span>
                                        </span>
                                        <div class=" clear"></div>
                                    </li>
                                    <li>
                                        <span class="left" data-rate="<s:property value="lttodayrate"/>">
                                        	<a href="<s:url action="bbs/content"/>">
                                        		<canvas width="55" height="55" style="margin-left:0px;"></canvas>
                                        	</a>
                                        </span>
                                        <span class="right">
                                            <span class="title"><span>社区数量：</span><span class="text4">&uarr;</span></span>
                                            <span class="num"><s:property value="lttoday"/></span>
                                        </span>
                                        <div class=" clear"></div>
                                    </li>
                                    <div class="clear"></div>
                                </ul>
                            </div>
                        </div>
                        <div class="mrzl_pic_list_one" id="columndiv">
                            <h2>细分数据：
                            	&nbsp;&nbsp;
                            	<select name="days" data-value="<s:property value="days"/>">
                                	<option value="0">当天</option>
                                    <option value="7" selected>最近一周</option>
                                    <option value="30">最近一个月</option>
                                    <option value="90">最近三个月</option>
                                    <option value="183">最近半年</option>
                                    <option value="365">最近一年</option>
								</select>
								&nbsp;&nbsp;&nbsp;&nbsp;|
                            	&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="module" value="wx" data-value="<s:property value="columnmodule"/>">微信
                            	&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="module" value="wb" data-value="<s:property value="columnmodule"/>">微博
                            	&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="module" value="xw" data-value="<s:property value="columnmodule"/>" checked>新闻
                            	&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="module" value="lt" data-value="<s:property value="columnmodule"/>">社区
                            </h2>
                            <div class="mrzl_lssj">
                               	<div id="columnchart" style="width:805px; height:450px;margin-left:-50px;"></div>
                                <div class="clear"></div>
                            </div>
                        </div>
                        <div class="mrzl_pic_list_one" id="historydiv">
                            <h2>历史数据：
                            	&nbsp;&nbsp;
                            	<select name="days" data-value="<s:property value="days"/>">
                                    <option value="7" selected>最近一周</option>
                                    <option value="30">最近一个月</option>
                                    <option value="90">最近三个月</option>
                                    <option value="183">最近半年</option>
                                    <option value="365">最近一年</option>
								</select>
								&nbsp;&nbsp;&nbsp;&nbsp;|
                            	&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="relation" value="0" data-value="<s:property value="relation"/>" checked>全部
                            	&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="relation" value="1" data-value="<s:property value="relation"/>">自有
                            	&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="relation" value="2" data-value="<s:property value="relation"/>">竞品								
                            </h2>
                            <div class="mrzl_lssj">
                               	<div id="historypie" style="width:805px; height:450px;margin-left:-50px;"></div>
                                <div class="clear"></div>
                            </div>
							<h2>
                            	&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="historymodule" value="wx">微信
                            	&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="historymodule" value="wb">微博
                            	&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="historymodule" value="xw" checked>新闻
                            	&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="historymodule" value="lt">社区
                                <div class="clear"></div>
                            </h2>
                            <div class="mrzl_pic">
                            	<div id="historybar" style="width:805px; height:400px;margin-left:-50px;"></div>
                            </div>
                        </div>                        
                    </div>
                    
                    <div class="right_title">每日更新</div>
                    <div class="mrzl_box">
                        <div class="mrzl_one">
                            <h2>微博挖掘：</h2>
                            <ul>
                            	<s:iterator value="wbsummary" status="st" id="lst">
                                <li>
                                    <span class="text">
                                    	<a href="<s:url action="weibo/more"/>?coumn.type=0&column.id=<s:property value="#lst[0]"/>">
											<s:if test="#lst[4]==1">
                                    		【自有】
                                    		</s:if>
                                    		<s:else>
                                    		【竞品】
                                    		</s:else>
                                    		<s:property value="#lst[1]"/>
                                    	</a> 
                                    </span>
                                    <span class="update">
                                    	更新
                                    	<span>
                                    		<s:property value="#lst[2]"/>
                                    	</span>
                                    	条
                                    </span>
                                    <span class="time"><s:property value="date"/></span>
                                    <div class="clear"></div>
                                </li>
                                </s:iterator>
                            </ul>
                        </div>
                        <div class="mrzl_one">
                            <h2>媒体挖掘：</h2>
                            <ul>
                            	<s:iterator value="xwsummary" status="st" id="lst">
                                <li>
                                    <span class="text">
                                    	<a href="<s:url action="track/more"/>?column.id=<s:property value="#lst[0]"/>">
                                    		<s:if test="#lst[4]==1">
                                    		【自有】
                                    		</s:if>
                                    		<s:else>
                                    		【竞品】
                                    		</s:else>
                                    		<s:property value="#lst[1]"/> 
                                    	</a> 
                                    </span>
                                    <span class="update">
                                    	更新
                                    	<span>
                                    		<s:property value="#lst[2]"/>
                                    	</span>
                                    	条
                                    </span>
                                    <span class="time"><s:property value="date"/></span>
                                    <div class="clear"></div>
                                </li>
                                </s:iterator>
                            </ul>
                        </div>
                        <div class="mrzl_one">
                            <h2>微信挖掘：</h2>
                            <ul>
                            	<s:iterator value="wxsummary" status="st" id="lst">
                                <li>
                                    <span class="text">
                                    	<a href="<s:url action="wechat/more"/>?column.id=<s:property value="#lst[0]"/>">
                                    		<s:if test="#lst[4]==1">
                                    		【自有】
                                    		</s:if>
                                    		<s:else>
                                    		【竞品】
                                    		</s:else>
                                    		<s:property value="#lst[1]"/> 
                                    	</a> 
                                    </span>
                                    <span class="update">
                                    	更新
                                    	<span>
                                    		<s:property value="#lst[2]"/>
                                    	</span>
                                    	条
                                    </span>
                                    <span class="time"><s:property value="date"/></span>
                                    <div class="clear"></div>
                                </li>
                                </s:iterator>                            
                            </ul>
                        </div>
                        <div class="mrzl_one">
                            <h2>论坛挖掘：</h2>
                            <ul>
                            	<s:iterator value="ltsummary" status="st" id="lst">
                                <li>
                                    <span class="text">
                                    	<a href="<s:url action="bbs/more"/>?column.id=<s:property value="#lst[0]"/>">
                                    		<s:if test="#lst[4]==1">
                                    		【自有】
                                    		</s:if>
                                    		<s:else>
                                    		【竞品】
                                    		</s:else>
                                    		<s:property value="#lst[1]"/> 
                                    	</a> 
                                    </span>
                                    <span class="update">
                                    	更新
                                    	<span>
                                    		<s:property value="#lst[2]"/>
                                    	</span>
                                    	条
                                    </span>
                                    <span class="time"><s:property value="date"/></span>
                                    <div class="clear"></div>
                                </li>
                                </s:iterator>                            
                            </ul>
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
