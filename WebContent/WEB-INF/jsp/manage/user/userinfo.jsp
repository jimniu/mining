<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>艾数达</title>
    <%@ include file="../layout/head.jsp" %>
</head>
<body>
<div class="box">
    <div class="header_tow">
        <div class="header_tow_samil">
            <div class="logo"><a href="javascript:;"><img src="../images/logo_.jpg"/></a></div>
            <div class="right">
                <s:if test="#session.adminUser.name!=null">
                    <span><a href="<s:url action="auser/manage_userinfo"><s:param name='adminUser.id'  value="#session.adminUser.id"></s:param></s:url>">
                        你好，<s:property value="#session.adminUser.name"/></a>
                    </span>
                </s:if>
                <a href="<s:url action="auser/manage_logout"/>"><img src="../images/header_tow_but_.jpg"/></a></div>
        </div>
    </div>
    <div class="cont cont_samil">
        <%@ include file="../layout/left.jsp" %>
        <div class="rwlb_div">
            <div class="zc_text_top">用户信息<a href="<s:url action="user/manage_index"/>">返回</a></div>
            <div class="zc_text zc_text_info">
                <p class="btl"><label>账号：</label>
                    <input type="text" value="<s:property value="user.name" />" disabled="disabled" class="dis"/>
                </p>
                <p class="btl"><label>密码：</label>
                    <input type="text" value="<s:property value="user.password" />" disabled="disabled" class="dis"/>
                </p>
                <p class="btl"><label>手机：</label>
                    <input type="text" name="user.mobile" value="<s:property value="user.mobile" />" id="mobile" disabled="disabled" class="dis"/>
                </p>
                <p class="btl"><label>邮箱：</label>
                    <input type="text" name="user.email" value="<s:property value="user.email" />" id="email" disabled="disabled" class="dis"/>
                </p>
                <p class="btl"><label>注册时间：</label><s:date name="user.ctime" format="yyyy-MM-dd HH:mm:ss"/></p>
                <p class="btl"><label>最后登录时间：</label><s:date name="user.utime" format="yyyy-MM-dd HH:mm:ss"/></p>
                <p><img src="../images/zc_xian.jpg"/></p>
                <p class="btl"><label>所属账户：</label>
                    <select name="user.networkid" data-value="<s:property value="user.networkid"/>">
                    <s:iterator value="list">
                        <option value="<s:property value="id"/>"><s:property value="name"/></option>
                    </s:iterator>
                    </select>
                </p>
                <p><img src="../images/zc_xian.jpg"/></p>
                <p class="btl"><label>账户过期时间：</label>
                    <input type="text" name="user.expire" value="<s:date name="user.expire" format="yyyy-MM-dd"/>" id="expire" class="dis"/>
                </p>
                <p class="inp_rad"><label class="fwlb_label">用户类型：</label>
                    <input type="radio" name="user.type" value="1" checked="checked"/><label>正式用户</label>
                    <input type="radio" name="user.type" value="0" data-type="<s:property value="user.type" />"/><label>游客</label>
                </p>
                <p class="inp_rad"><label class="fwlb_label">用户状态：</label>
                    <input type="radio" name="user.status" value="0" data-status="<s:property value="user.status" />"/><label>尚未审核</label>
                    <input type="radio" name="user.status" value="1" checked="checked"/><label>已经批准</label>
                    <input type="radio" name="user.status" value="2"/><label>已经禁用</label>
                </p>
                <input type="hidden" name="user.id" value="<s:property value="user.id" />"/>
                <p class="p_button"><label>&nbsp;</label>
                   <a href="javascript:;" id="checkbtn"><img src="../images/sh_button.jpg"/></a>
                   <a href="<s:url action="user/manage_index"/>"><img src="../images/fh_fhbutton.jpg"/></a></p>
            </div>
        </div>
    </div>
    <div class="footer">
        Copyright&nbsp;&copy;&nbsp;2010&nbsp;艾数达科技&nbsp;京ICP备09063988号
    </div>
    <%@ include file="../layout/footer.jsp" %>
</div>
</body>
</html>
