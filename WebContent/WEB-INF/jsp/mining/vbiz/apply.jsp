<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
	 <%@ include file="../layout/head.jsp"%>
	<title>微众云商通</title>
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
                        <div class="wbwj_top_title">微众云商通</div>
                        <div class="wbwj_top_bt"><a href="javascript:;">收起</a></div>
                        <div class="clear"></div>
                    </div>
                    <div class="wbwj_pic"><img src="../images/wzyst_pic.jpg"></div>
                    <div class="wbwj_dsj_box">
                        <div class="mtyx_title wzyst_title">
                        	<a href="http://fans.weizoom.com/login/"  class="wzyst_title_a">申请微众云商通，</a>
                            <span>请致电：010-64473805 </span>
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
