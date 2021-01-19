<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"  %>
<%@ taglib prefix="s" uri="/struts-tags"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../layout/head.jsp"%>
    <title>邮件推广-客户资料管理</title>
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
                            <span>邮件客户资料管理</span>
                        </div>
                        <div class="mtyx_ysmc">
                        	<span>姓名</span>
                            <input type="text"/>
                        	<span>邮箱</span>
                            <input type="text"/>
                            <a href="#">搜索</a>
                            <a href="javascript:;"  id="add">增加</a>
                            <a href="#">导入</a>
                        </div>
                        <div class="wbwj_yhgl_list">
                            <table width="805" cellpadding="0" cellspacing="0" border="0">
                                <tr class="title">
                                    <td width="250">名称</td>
                                    <td width="230">邮件</td>
                                    <td width="195">分类</td>
                                    <td width="130">操作</td>
                                </tr>
                                <tr>
                                	<td>李经理</td>
                                	<td>123456@qq.com</td>
                                	<td>未分类</td>
                                	<td>
                                    	<a href="#"><img src="../images/bj_bt.jpg"/></a>
                                        <a href="#"><img src="../images/sc_bt.jpg"/></a>
                                        <div class="clear"></div>
                                    </td>
                                </tr>
                                <tr>
                                	<td>李经理</td>
                                	<td>123456@qq.com</td>
                                	<td>未分类</td>
                                	<td>
                                    	<a href="#"><img src="../images/bj_bt.jpg"/></a>
                                        <a href="#"><img src="../images/sc_bt.jpg"/></a>
                                        <div class="clear"></div>
                                    </td>
                                </tr>
                                <tr>
                                	<td>李经理</td>
                                	<td>123456@qq.com</td>
                                	<td>未分类</td>
                                	<td>
                                    	<a href="#"><img src="../images/bj_bt.jpg"/></a>
                                        <a href="#"><img src="../images/sc_bt.jpg"/></a>
                                        <div class="clear"></div>
                                    </td>
                                </tr>
                                <tr>
                                	<td>李经理</td>
                                	<td>123456@qq.com</td>
                                	<td>未分类</td>
                                	<td>
                                    	<a href="#"><img src="../images/bj_bt.jpg"/></a>
                                        <a href="#"><img src="../images/sc_bt.jpg"/></a>
                                        <div class="clear"></div>
                                    </td>
                                </tr>
                                <tr>
                                	<td>李经理</td>
                                	<td>123456@qq.com</td>
                                	<td>未分类</td>
                                	<td>
                                    	<a href="#"><img src="../images/bj_bt.jpg"/></a>
                                        <a href="#"><img src="../images/sc_bt.jpg"/></a>
                                        <div class="clear"></div>
                                    </td>
                                </tr>
                            </table>
                        </div>
                        <div class="dx_bomb"  style="display:none;">
                        	<div class="dx_bomb_top"></div>
                            <div class="dx_bomb_center">
                            	<a href="#" class="close"></a>
                                <h1>增加邮件客户资料</h1>
                                <div>
                                	<span>名称</span>
                                    <input type="text"/>
								</div>
                                <div>
                                	<span>分类</span>
                                    <input type="text"/>
								</div>
                                <div>
                                	<span>邮件</span>
                                    <input type="text"/>
								</div>
                                <a href="javascript:;"  class="dx_submit">提交</a>
                            </div>
                            <div class="dx_bomb_bottom"></div>
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
