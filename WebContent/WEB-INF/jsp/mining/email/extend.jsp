<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../layout/head.jsp"%>
    <title>邮件推广-推广列表</title>
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
                    <div class="wbwj_dsj_box  yjlx_parent">
                        <div class="mtyx_title">
                            <span>邮件推广</span>
                        </div>
                        <div class="mtyx_ysmc">
                        	<span>名称</span>
                            <input type="text"/>
                            <a href="javascript:;">搜索</a>
                            <a href="<s:url action="email/addExtend"/>">增加</a>
                        </div>
                        <div class="wbwj_yhgl_list">
                            <table width="805" cellpadding="0" cellspacing="0" border="0">
                                <tr class="title">
                                    <td width="170">名称</td>
                                    <td width="175">发送时间</td>
                                    <td width="180">发送次数</td>
                                    <td width="150">状态</td>
                                    <td width="130">操作</td>
                                </tr>
                                <tr>
                                	<td>李经理</td>
                                	<td>
                                        <span>2015-12-12</span>
                                        <span>12：12</span>
                                    </td>
                                	<td>1</td>
                                	<td>待审核</td>
                                	<td>
                                    	<a href="#"><img src="../images/bj_bt.jpg"/></a>
                                        <a href="#"><img src="../images/sc_bt.jpg"/></a>
                                        <div class="clear"></div>
                                    </td>
                                </tr>
                                <tr>
                                	<td>李经理</td>
                                	<td>
                                        <span>2015-12-12</span>
                                        <span>12：12</span>
                                    </td>
                                	<td>1</td>
                                	<td>待审核</td>
                                	<td>
                                    	<a href="#"><img src="../images/bj_bt.jpg"/></a>
                                        <a href="#"><img src="../images/sc_bt.jpg"/></a>
                                        <div class="clear"></div>
                                    </td>
                                </tr>
                                <tr>
                                	<td>李经理</td>
                                	<td>
                                        <span>2015-12-12</span>
                                        <span>12：12</span>
                                    </td>
                                	<td>1</td>
                                	<td>待审核</td>
                                	<td>
                                    	<a href="#"><img src="../images/bj_bt.jpg"/></a>
                                        <a href="#"><img src="../images/sc_bt.jpg"/></a>
                                        <div class="clear"></div>
                                    </td>
                                </tr>
                                <tr>
                                	<td>李经理</td>
                                	<td>
                                        <span>2015-12-12</span>
                                        <span>12：12</span>
                                    </td>
                                	<td>1</td>
                                	<td>待审核</td>
                                	<td>
                                    	<a href="#"><img src="../images/bj_bt.jpg"/></a>
                                        <a href="#"><img src="../images/sc_bt.jpg"/></a>
                                        <div class="clear"></div>
                                    </td>
                                </tr>
                            </table>
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
