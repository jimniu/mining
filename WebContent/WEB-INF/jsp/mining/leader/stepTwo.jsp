<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../layout/head.jsp"%>
    <title>新建意见领袖-筛选意见领袖</title>
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
                    <div class="wbwj_top">
                        <div class="wbwj_top_title">媒体传播营销</div>
                        <div class="wbwj_top_bt"><a href="javascript:;">展开</a></div>
                        <div class="clear"></div>
                    </div>
                    <div class="wbwj_pic"   style="display:none;"><img src="../images/sjyx_mtyx.jpg"/></div>
                    <div class="wbwj_dsj_box">
                        <div class="mtyx_title">
                            <span>意见领袖预算</span>
                        </div>
                        <div class="mtys_xjtgys">
                        	<p>新建意见领袖预算</p>
                            <div>
                                <a>
                                    <img src="../images/xjys_bt1_link.png"/>
                                </a>
                                <a>
                                    <img src="../images/xjys_bt2_hover.png"/>
                                </a>
                                <a>
                                    <img src="../images/xjys_bt3_link.png"/>
                                </a>
                                <a>
                                    <img src="../images/xjys_bt4_link.png"/>
                                </a>
                            </div>
                            <h1>
                            	<span>选择意见领袖</span><span>筛选意见领袖</span><span>媒体投放</span><span>生成预算任务</span>
                            </h1>
                        </div>
                        <div class="yjlx_hyd">
                        	<a>活跃度分析</a>
                        </div>
                        <div class="wbwj_yhgl_list ">
                            <table width="805" cellpadding="0" cellspacing="0" border="0" class="yjlx_parent">
                                <tr class="title">
                                    <td width="50">全选</td>
                                    <td width="410">意见领袖名称</td>
                                    <td width="210">粉丝数量</td>
                                    <td width="135">关注度</td>
                                </tr>
                                <tr>
                                	<td><input type="checkbox"/></td>
                                	<td>郭敬明</td>
                                	<td>1000w以上</td>
                                	<td>高</td>
                                </tr>
                                <tr>
                                	<td><input type="checkbox"/></td>
                                	<td>郭敬明</td>
                                	<td>1000w以上</td>
                                	<td>高</td>
                                </tr>
                                <tr>
                                	<td><input type="checkbox"/></td>
                                	<td>郭敬明</td>
                                	<td>1000w以上</td>
                                	<td>高</td>
                                </tr>
                                <tr>
                                	<td><input type="checkbox"/></td>
                                	<td>郭敬明</td>
                                	<td>1000w以上</td>
                                	<td>高</td>
                                </tr>
                                <tr>
                                	<td><input type="checkbox"/></td>
                                	<td>郭敬明</td>
                                	<td>1000w以上</td>
                                	<td>高</td>
                                </tr>
                                <tr>
                                	<td><input type="checkbox"/></td>
                                	<td>郭敬明</td>
                                	<td>1000w以上</td>
                                	<td>高</td>
                                </tr>
                                <div class="yjlx_bomb">
                                    <span></span>
                                    <p>至少选择5位意见领袖！</p>
                                </div>
                            </table>
                        </div>
                        <div class="mtys_xjtgys_bt">
                        	<a href="<s:url action="leader/budget"/>" class="back2">返回</a>
                        	<a href="<s:url action="leader/stepOne"/>" class="next2">上一步</a>
                        	<a href="<s:url action="leader/stepThree"/>" class="next2">下一步</a>
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
