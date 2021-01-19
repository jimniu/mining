<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="../layout/head.jsp"%>
    <title>负面管理-标签管理</title>
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
                    <div class="wbwj_dsj_box">
                        <div class="wbwj_title">标签管理</div>
                        <div class="sjzq_link_one">
                            <div class="sjzq_link_left"><input type="checkbox"/>全部</div>
                            <div class="sjzq_link_right">
                                <a href="">删 除</a>
                                <a href="">新建标签</a>
                                <div class="clear"></div>
                            </div>
                            <div class="clear"></div>
                            <div class="sjzq_link_piao" style="display:none;"> 
                                <div class="link">
                                    <div class="gb_bt"><a href=""><img src="../images/x_bt.jpg"/></a><div class="clear"></div></div>
                                    <div class="input"><input type="text" value="输入网址"/></div>
                                    <div class="tj_bt"><a href="">提交</a></div>
                                </div>
                            </div>
                        </div>
                        <div class="sjzq_sjgl">
                            <ul>
                                <li>
                                    <span class="bt"><input type="checkbox"/></span>
                                    <span class="text"><a href="">上学的烦恼 九龙仓·雍景山为你支烦恼 九龙仓·雍景山为你支招</a></span>
                                    <span>2015-09-09</span>
                                    <span><a href=""><img src="../images/bj_bt.jpg"/></a></span>
                                    <span><a href=""><img src="../images/sc_bt.jpg"/></a></span>
                                    <div class="clear"></div>
                                    <div class="sjzq_sjgl_piao_bt" style="display:none;">
                                        <div class="link">
                                            <div class="gb_bt"><a href=""><img src="../images/x_bt.jpg"/></a><div class="clear"></div></div>
                                            <div class="input"><input type="text" value="输入网址"/></div>
                                            <div class="tj_bt"><a href="">提交</a></div>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <span class="bt"><input type="checkbox"/></span>
                                    <span class="text"><a href="">上学的烦恼 九龙仓·雍景山为你支烦恼 九龙仓·雍景山为你支招</a></span>
                                    <span>2015-09-09</span>
                                    <span><a href=""><img src="../images/bj_bt.jpg"/></a></span>
                                    <span><a href=""><img src="../images/sc_bt.jpg"/></a></span>
                                    <div class="clear"></div>
                                    <div class="sjzq_sjgl_piao_bt" style="display:none;">
                                        <div class="link">
                                            <div class="gb_bt"><a href=""><img src="../images/x_bt.jpg"/></a><div class="clear"></div></div>
                                            <div class="input"><input type="text" value="输入网址"/></div>
                                            <div class="tj_bt"><a href="">提交</a></div>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                        <div class="ym">
                            <span><a href="">下 1 页</a></span>
                            <span>|</span>
                            <span><a href="" class="ym_vtd">第 1 页</a></span>
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
