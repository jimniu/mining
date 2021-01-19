<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="../layout/head.jsp"%>
    <title>公共号画像</title>
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
                    <div class="right_title">公共号画像</div>
                    <div class="gghx_box">
                     	<div class="gghx_kshx">
                        	<span><input type="text" value="请输入公共号昵称"/></span>
                            <span><a href="javascript:;">开始画像</a></span>
                        </div>
                     	<div class="gghx_search">
                        	<ul>
                            	<li>
                                	<span>综合搜索：</span>
                                	<span><input type="text" value="入公共号昵称"/></span>
                                </li>
                            	<li>
                                	<span>创建时间：</span>
                                	<span><input type="text" /></span>
                                	<span>至</span>
                                	<span><input type="text"/></span>
                                </li>
                            	<li><a href="">搜索</a></li>
                                <div class="clear"></div>
                            </ul>
                        </div>
                        <div class="gghx_search_list">
                        	<div class="gghx_search_list_left">
                            	<div class="gghx_search_list_left_l">
                                	<a href="<s:url action="wechat/paintlist"/>">
                                	<img src="../images/sjgl_pic2.jpg"/>
                                    <div class="piao"><img src="../images/wbwj_dsj_pic.png"></div>
                                    </a>
                                </div>
                            	<div class="gghx_search_list_left_r">
                                	<h2>开始画像</h2>
                                	<ul>
                                        <li>
                                        	<span>影响力:</span>
                                        	<span><img src="../images/gghx_hover.jpg"></span>
                                        	<span><img src="../images/gghx_hover.jpg"></span>
                                        	<span><img src="../images/gghx_hover.jpg"></span>
                                        	<span><img src="../images/gghx_link.jpg"></span>
                                        	<span><img src="../images/gghx_link.jpg"></span>
                                        </li>
                                        <li>
                                        	<span>活跃度:</span>
                                        	<span><img src="../images/gghx_hover.jpg"></span>
                                        	<span><img src="../images/gghx_hover.jpg"></span>
                                        	<span><img src="../images/gghx_link.jpg"></span>
                                        	<span><img src="../images/gghx_link.jpg"></span>
                                        	<span><img src="../images/gghx_link.jpg"></span>
                                        </li>
                                        <li>
                                        	<span>健康度:</span>
                                        	<span><img src="../images/gghx_link.jpg"></span>
                                        	<span><img src="../images/gghx_link.jpg"></span>
                                        	<span><img src="../images/gghx_link.jpg"></span>
                                        	<span><img src="../images/gghx_link.jpg"></span>
                                        	<span><img src="../images/gghx_link.jpg"></span>
                                        </li>
                                    </ul>
                                </div>
                                <div class="clear"></div>
                            </div>
                        	<div class="gghx_search_list_right">
                            	<div class="gghx_search_list_right_l">
                                	<ul>
                                    	<li>
                                        	<span>总发稿量：</span>
                                        	<span>44万</span>
                                        </li>
                                    	<li>
                                        	<span>总阅读量：</span>
                                        	<span>44万</span>
                                        </li>
                                    	<li>
                                        	<span>总点赞量：</span>
                                        	<span>44万</span>
                                        </li>
                                        <div class="clear"></div>
                                    </ul>
                                </div>
                            	<div class="gghx_search_list_right_r">
                                	<span><a href="javascript:;"><img src="../images/x_bt.jpg"/></a></span>
                                	<span>微信号：DD29898283</span>
                                </div>
                                <div class="clear"></div>
                            </div>
                            <div class="clear"></div>
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
