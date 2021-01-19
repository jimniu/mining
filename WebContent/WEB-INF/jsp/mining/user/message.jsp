<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
     <%@ include file="../layout/head.jsp"%>
    <title>我的消息</title>
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
                            <li><a href="<s:url action="user/info"/>">用户信息</a></li>
                            <li><a href="<s:url action="user/message"/>"  class="yhxx_left_vtd">我的消息</a></li>
                            <li><a href="<s:url action="site/overview"/>">返回主页</a></li>
                        </ul>
                    </div>
                </div>
                <div class="list_right">
                    <div class="yhxx_right">
                        <h2>我的消息</h2>
                        <div class="yhxx_list"  id="list" >
                        <s:iterator  value="list" status="st">
                            <div class="wdxx_one">
                                <div class="wdxx_title">
                                    <div class="wdxx_left"><span style="font-size: 16px; color:#333; font-weight: 600;" pp="0"  pos="<s:property value="id"/>"><s:property value="title"/></span></div>
                                    <div class="wdxx_right">
                                        <span><s:date name="ctime"  format="yyyy-MM-dd" /></span>
                                        <div class="clear"></div>
                                    </div>
                                    <div class="clear"></div>
                                </div>
                                <div class="wdxx_text"  style="display:none" pos="<s:property value="id"/>"><p><s:property value="content"/></p></div>
                            </div>  
                        </s:iterator>                     
                        </div>
                        <div class="ym">
                            <div class="fy_div"   data-pagesize="5"  data-pageindex="<s:property value="pageindex"/>"  data-total="<s:property value="count"/>"  data-urlbase="../user/message.shtml?"></div>			
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
