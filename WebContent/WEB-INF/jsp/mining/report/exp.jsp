<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../layout/head.jsp"%>
    <title>报表中心-Excel导出</title>
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
                    <div class="wbwj_rwgl_box">
                        <div class="wbwj_rwgl_title">
                            <div class="wbwj_rwgl_title_left">数据列表导出</div>
                            <div class="clear"></div>
                        </div>
                        <div class="wbwj_rwgl_ck_one">
                            <form action="<s:url action="report/excel"/>" method="post">
                            <ul>
                                <li>
                                    <span class="text">起始时间：</span>
                                    <span class="text4" style="margin-left:0px;"><input type="text" name="from" value="<s:property value="from"/>"/></span>
                                    <div class="clear"></div>
                                </li>  
                                <li>
                                    <span class="text">结束时间：</span>
                                    <span class="text4" style="margin-left:0px;"><input type="text" name="to" value="<s:property value="to"/>"/></span>
                                    <div class="clear"></div>
                                </li>
                                <!--  
                                <li>
                                    <span class="text">品牌关系：</span>
                                    <span class="text1" style="margin-left:0px;">
                                        <select id="relation" name="relation" data-value="<s:property value="relation"/>">
                                        	<option value="0">所有</option>
                                            <option value="1">自有品牌</option>
                                            <option value="2">竞品</option>
                                        </select>                                    
                                    </span>
                                    <div class="clear"></div>
                                </li>
                                -->
                            </ul>
                            <ul>
                                <li>
                                    <span class="text">数据类型：</span>
                                    <span class="text1" style="margin-left:0px;">
                                        <select id="type" name="type" data-value="<s:property value="type"/>">
                                        	<option value="0">内容挖掘</option>
                                            <option value="1">事件监控</option>
                                            <option value="2">负面监控</option>
                                        </select>                                    
                                    </span>
                                    <div class="clear"></div>
                                </li>                               
                            </ul>
                            <ul>
								<li>
                                    <span class="text">结果去重：</span>
                                    <span class="text1" style="margin-left:0px;">
                                        <select id="dd" name="dd" data-value="<s:property value="dd"/>">
                                        	<option value="0">不去重</option>
                                            <option value="1">去重</option>
                                        </select>                                    
                                    </span>
                                    <div class="clear"></div>
                                </li>
                            </ul>
                            <div class="wbwj_rwgl_ck_one_bt">
                                <a href="javascript:;" class="keep">导出</a>
                                <div class="clear"></div>
                            </div>
                            <input type="submit" style="display:none;">
                            </form>
                        </div>
                        <div class="wbwj_rwgl_title">
                            <div class="wbwj_rwgl_title_left">数据分析TOP导出</div>
                            <div class="clear"></div>
                        </div>
                        <div class="wbwj_rwgl_ck_one">
                            <form action="<s:url action="report/topexcel"/>" method="post">
                            <ul>
                                <li>
                                    <span class="text">起始时间：</span>
                                    <span class="text4" style="margin-left:0px;"><input type="text" name="from" value="<s:property value="from"/>"/></span>
                                    <div class="clear"></div>
                                </li>  
                                <li>
                                    <span class="text">结束时间：</span>
                                    <span class="text4" style="margin-left:0px;"><input type="text" name="to" value="<s:property value="to"/>"/></span>
                                    <div class="clear"></div>
                                </li>
                            </ul>
                            <ul>
                                <li>
                                    <span class="text">TOP：</span>
                                    <span class="text4" style="margin-left:0px;"><input type="text" name="pagesize" value="50"/></span>
                                    <span class="text_gai">个</span>
                                    <div class="clear"></div>
                                </li>
                            </ul>
                            <ul>
                                <li>
                                    <span class="text">数据类型：</span>
                                    <span class="text1" style="margin-left:0px;">
                                        <select name="module">
                                            <option value="wb">微博转发数</option>
                                        	<option value="xw">媒体传播数</option>
                                            <option value="wx">微信传播数</option>
                                            <!--
											<option value="wxr">微信阅读数</option>
											-->
                                            <option value="lt">论坛传播数</option>
                                        </select>                                    
                                    </span>
                                    <div class="clear"></div>
                                </li>                               
                            </ul>
                            <ul>
                                <li>
                                    <span class="text">所属栏目：</span>
                                    <span class="text1" style="margin-left:0px;">
                                        <select name="columnid">
                                        	<option value="-1">所有栏目</option>
                                        </select>
                                    </span>
                                    <div id="xwlist" style="display: none;">
                                        <option value="-1">所有栏目</option>
                                        <s:iterator value="xwlist">
                                            <s:if test="title.length()>0">
                                            <option value="<s:property value="id"/>"><s:property value="title"/></option>
                                            </s:if>
                                        </s:iterator>
                                    </div>
                                    <div id="wxlist" style="display: none;">
                                        <option value="-1">所有栏目</option>
                                        <s:iterator value="wxlist">
                                            <s:if test="title.length()>0">
                                            <option value="<s:property value="id"/>"><s:property value="title"/></option>
                                            </s:if>
                                        </s:iterator>
                                    </div>
                                    <div id="default" style="display: none;">
                                        <option value="-1">所有栏目</option>
                                    </div>
                                    <div class="clear"></div>
                                </li>
                            </ul>
                           	<ul>
                            	<li>
                                <span class="text">数据类型:</span>
                                <span>
                                <input type="radio" name="type" value="0" checked="checked">监控&nbsp;&nbsp;&nbsp;&nbsp;
                                </span>
                                <span>
                                <input type="radio" name="type" value="1">事件&nbsp;&nbsp;&nbsp;&nbsp;
                                </span>
                                <span>
                                <input type="radio" name="type" value="2">负面
                                </span>
                                </li>
                            </ul>                            
                           	<ul>
                            	<li>
                                <span class="text">数据属性:</span>
                                <span>
                                <input type="radio" name="relation" value="0" checked="checked">全部&nbsp;&nbsp;&nbsp;&nbsp;
                                </span>
                                <span id="type1">
                                <input type="radio" name="relation" value="1">自有&nbsp;&nbsp;&nbsp;&nbsp;
                                </span>
                                <span id="type2">
                                <input type="radio" name="relation" value="2">竞品&nbsp;&nbsp;&nbsp;&nbsp;
                                </span>
                                </li>
                            </ul>
                            <div class="wbwj_rwgl_ck_one_bt">
                                <a href="javascript:;" class="keep">导出</a>
                                <div class="clear"></div>
                            </div>
                            <input type="submit" style="display:none;">
                            </form>
                        </div>                        
                        <div class="wbwj_rwgl_title">
                            <div class="wbwj_rwgl_title_left">微信通导出</div>
                            <div class="clear"></div>
                        </div>
                        <div class="wbwj_rwgl_ck_one">
                            <form action="<s:url action="report/expreco"/>" method="post">
                            <ul>
                                <li>
                                    <span class="text">起始时间：</span>
                                    <span class="text4" style="margin-left:0px;"><input type="text" name="from" value="<s:property value="from"/>"/></span>
                                    <div class="clear"></div>
                                </li>  
                                <li>
                                    <span class="text">结束时间：</span>
                                    <span class="text4" style="margin-left:0px;"><input type="text" name="to" value="<s:property value="to"/>"/></span>
                                    <div class="clear"></div>
                                </li>
                            </ul>
                            <div class="wbwj_rwgl_ck_one_bt">
                                <a href="javascript:;" class="keep">导出</a>
                                <div class="clear"></div>
                            </div>
                            <input type="submit" style="display:none;">
                            </form>
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
