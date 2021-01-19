<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
     <%@ include file="../layout/head.jsp"%>
    <title>加入我们</title>
</head>
<body>
<div class="box">
    <%@ include file="../layout/top.jsp"%>
    <div class="width_box">
        <%@ include file="../layout/message.jsp"%>
        <div class="list">
            <div class="list_top"><img src="../images/list_top.jpg"/></div>
            <div class="list_center">
                <div class="list_left">
                    <div class="yhxx_left">
                        <ul>
                            <li><a href="<s:url action="site/about"/>">关于我们</a></li>
                            <li><a href="<s:url action="site/problem"/>">常见问题</a></li>
                            <li><a href="<s:url action="site/joinus"/>" class="yhxx_left_vtd">加入我们</a></li>
                        </ul>
                    </div>
                </div>
                <div class="list_right">
                    <div class="list_right_title">加入我们</div>
                    <div class="jrwm_list">
                        <h2>北京艾数达科技有限公司</h2>
                        <ul>
                            <li>地址：北京朝阳区西坝河北里168号恒川公寓D01栋别墅</li>
                            <li>网址：Http://www.ishowdata.com</li>
                            <li>电话:：(010)64473805/64473935/64473785</li>
                            <li>邮箱：HR@ishowdata.com</li>
                            <li><img src="../images/jrwm_pic.jpg"/></li>
                        </ul>
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
