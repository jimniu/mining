<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../layout/head.jsp"%>
    <title>媒体传播营销-新建推广预算</title>
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
                    <div class="wbwj_pic"  style="display:none;"><img src="../images/sjyx_mtyx.jpg"/></div>
                    <div class="wbwj_dsj_box">
                        <div class="mtyx_title">
                            <span>新建推广预算</span>
                        </div>
                        <div class="mtys_xjtgys">
                        	<p>新建推广预算</p>
                            <div>
                                <a>
                                    <img src="../images/xjys_bt1_link.png"/>
                                </a>
                                <a>
                                    <img src="../images/xjys_bt2_link.png"/>
                                </a>
                                <a>
                                    <img src="../images/xjys_bt3_link.png"/>
                                </a>
                                <a>
                                    <img src="../images/xjys_bt4_hover.png"/>
                                </a>
                            </div>
                            <h1>
                            	<span>选择媒体</span><span>筛选媒体列表</span><span>媒体投放</span><span>生成预算任务</span>
                            </h1>
                        </div>
                        <div class="wbwj_yhgl_list">
                            <table width="805" cellpadding="0" cellspacing="0" border="0">
                                <tr class="title">
                                    <td width="160">名称</td>
                                    <td width="130">投放行业</td>
                                    <td width="130">投放位置</td>
                                    <td width="130">投放时间</td>
                                    <td width="130">投放时限</td>
                                    <td width="125">费用</td>
                                </tr>
                                <tr>
                                	<td>新浪网</td>
                                	<td>全国</td>
                                	<td>频道首页</td>
                                	<td>2015-12-30</td>
                                	<td>一个月</td>
                                	<td>3，000.00元</td>
                                </tr>
                                <tr>
                                	<td>新浪网</td>
                                	<td>全国</td>
                                	<td>频道首页</td>
                                	<td>2015-12-30</td>
                                	<td>一个月</td>
                                	<td>3，000.00元</td>
                                </tr>
                            </table>
                        </div>
                        <div class="mtys_and">
                        	<h4>总计费用</h4>
                            <p>
                            	<span>3,000.00元</span>
                            	<span>（人民币叁仟元整）</span>
                            </p>
                        </div>
                        <div class="mtys_zf">
                        	<h4>付款方式</h4>
                            <a href="#">网上支付</a><br/>
                            <a href="#">支票</a>
                        </div>
                        <div class="mtys_xjtgys_bt">
                        	<a href="<s:url action="media/budget"/>" class="back2">返回</a>
                        	<a href="<s:url action="media/stepThree"/>" class="next2">上一步</a>
                        	<a href="<s:url action="media/budget"/>" class="next2">确定</a>
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
