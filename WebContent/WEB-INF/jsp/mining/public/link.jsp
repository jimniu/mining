<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../layout/head.jsp"%>
    <title>公众号通</title>
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
                    <div class="wbwj_rwgl_title">
                            <div class="wbwj_rwgl_title_left">获取链接</div>
                     
							<div class="clear"></div>
                           
                    </div>
					<div class="sjzq_sjgl_list">
                            <ul>
                                <li>历史资讯链接</li>
                                <li class="text2"><input type="text" id="review" value="<s:property value="host"/><s:url action="mobile/review"/>?recommend.userid=<s:property value="#session.user.id"/>"/></li>
                                 <li>
                                     <div class="sjzq_link_right">
                                        <a href="javascript:;"  class="getlink" data-clipboard-action="copy" data-clipboard-target="#review">复制链接</a>
									 </div>
							    </li>
                            </ul>
							<div class="clear"></div>
                    </div>
					<div class="sjzq_sjgl_list">
                            <ul>
                                <li><s:property value="oem.title" />链接</li>
                                <li class="text2"><input type="text"  id="mobile" value="<s:property value="host"/><s:url action="mobile/index"/>"/></li>
                                <li>
                                    <div class="sjzq_link_right">
                                        <a href="javascript:;"  class="getlink" data-clipboard-action="copy" data-clipboard-target="#mobile">复制链接</a>
                                    </div>
							    </li>
                            </ul>
							<div class="clear"></div>
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
