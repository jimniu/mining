<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<head>
    <title>未授权</title>
    <%@ include file="../layout/head.jsp"%>
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
                    <div class="mfty_pic">
                        <p class="text1">尊敬的用户： 您好！</p>
                        <p class="text2">您是免费体验用户，目前还没有权限使用本功能，如果您需要本功能服务,请您联系市场销售人员，我们的销售人员会为您提供全面的解答。</p>
                        <p class="text3">邮件：sales@ishowdata.com</p>
                        <p class="text4">电话：010-64473875</p>
                        <p class="text5">010-64473805</p>
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
