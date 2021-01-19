<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../layout/head.jsp"%>
    <title>意见领袖-新建推广预算</title>
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
                                    <img src="../images/xjys_bt3_hover.png"/>
                                </a>
                                <a>
                                    <img src="../images/xjys_bt4_link.png"/>
                                </a>
                            </div>
                            <h1>
                            	<span>选择意见领袖</span><span>筛选意见领袖</span><span>媒体投放</span><span>生成预算任务</span>
                            </h1>
                        </div>
                        <div class="mtys_xjtgys_content3">
                        	<ul>
								<li><p>微博红人：胡夏</p></li>
    							<li>
                                	<span>投放方式：</span>
                                    <select>
                                    	<option>原文发布</option>
                                    	<option>原文发布</option>
                                    	<option>原文发布</option>
                                    </select>
                                </li>
                                <li>
                                	<span>投放时间：</span>
                                    <a href="#" class="janpan"><img src="../images/mtyx_bt2.png"/></a>
                                    <input type="text" value="2015-10-10"/>
                                </li>
    							<li>
                                	<span>投放时限：</span>
                                    <select>
                                    	<option>一个月</option>
                                    	<option>一个月</option>
                                    	<option>一个月</option>
                                    </select>
                                </li>
                                <li>
                                	<span>转发次数：</span>
                                    <input type="text" class="cishu" value="10">
                                </li>
                            </ul>
                        	<ul>
								<li><p>微博红人：胡夏</p></li>
    							<li>
                                	<span>投放方式：</span>
                                    <select>
                                    	<option>原文发布</option>
                                    	<option>原文发布</option>
                                    	<option>原文发布</option>
                                    </select>
                                </li>
                                <li>
                                	<span>投放时间：</span>
                                    <a href="#" class="janpan"><img src="../images/mtyx_bt2.png"/></a>
                                    <input type="text" value="2015-10-10"/>
                                </li>
    							<li>
                                	<span>投放时限：</span>
                                    <select>
                                    	<option>一个月</option>
                                    	<option>一个月</option>
                                    	<option>一个月</option>
                                    </select>
                                </li>
                                <li>
                                	<span>转发次数：</span>
                                    <input type="text" class="cishu" value="10">
                                </li>
                            </ul>
                        	<ul class="no_border">
								<li><p>微博红人：胡夏</p></li>
    							<li>
                                	<span>投放方式：</span>
                                    <select>
                                    	<option>原文发布</option>
                                    	<option>原文发布</option>
                                    	<option>原文发布</option>
                                    </select>
                                </li>
                                <li>
                                	<span>投放时间：</span>
                                    <a href="#" class="janpan"><img src="../images/mtyx_bt2.png"/></a>
                                    <input type="text" value="2015-10-10"/>
                                </li>
    							<li>
                                	<span>投放时限：</span>
                                    <select>
                                    	<option>一个月</option>
                                    	<option>一个月</option>
                                    	<option>一个月</option>
                                    </select>
                                </li>
                                <li>
                                	<span>转发次数：</span>
                                    <input type="text" class="cishu" value="10">
                                </li>
                            </ul>
                        	
                        	
                        </div>
                        <div class="mtys_and">
                        	<h4>预计评估费用</h4>
                            <p>
                            	<span>3,000.00元</span>
                            	<span>（人民币叁仟元整）</span>
                            </p>
                        </div>
                        <div class="mtys_xjtgys_bt">
                        	<a href="<s:url action="leader/budget"/>" class="back2">返回</a>
                        	<a href="<s:url action="leader/stepTwo"/>" class="next2">上一步</a>
                        	<a href="<s:url action="leader/stepFour"/>" class="next2">下一步</a>
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
