<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../layout/head.jsp"%>
    <title>用户信息</title>
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
                            <li><a href=" <s:url action="user/info"/>" class="yhxx_left_vtd">用户信息</a></li>
                            <li><a href=" <s:url action="network/info"/>">账户信息</a></li>
                            <li><a href=" <s:url action="user/message"/>">我的消息</a></li>
                            <li><a href="<s:url action="site/overview"/>">返回主页</a></li>
                        </ul>
                    </div>
                </div>
                <div class="list_right">
                    <div class="yhxx_right">
                        <h2>用户信息</h2>
                        <form action="<s:url action="user/update"/>" method="post" id="form">
                        <ul>
                            <li>
                                <span class="left">账号：</span>
                                <span class="right"><input type="text" value="<s:property value="#session.user.name" />"  disabled="disabled"/></span>
                                <div class="clear"></div>
                            </li>
                            <li>
                                <span class="left">密码：</span>
                                <span class="right2">
                                    <input type="text"  name="user.password"  id="pwd"   value="<s:property value="#session.user.password" />"  />
                                    <span><a href="javascript:;"><img src="../images/zc_bt.jpg"></a></span>
                                    <div class="clear"></div>
                                </span>
                                <div class="clear"></div>
                            </li>
                            <li>
                                <span class="left">确认密码：</span>
                                <span class="right2">
                                    <input type="text"   name="pwd1"  id="pwd1"  value="<s:property value="#session.user.password" />"/>
                                    <span><a href="javascript:;"><img src="../images/zc_bt.jpg"></a></span>
                                    <div class="clear"></div>
                                </span>
                                <div class="clear"></div>
                            </li>
                            <li>
                                <span class="left">电话：</span>
                                <span class="right"><input type="text"   name="user.mobile" value="<s:property value="myself.mobile" />"  /></span>
                                <div class="clear"></div>
                            </li>
                            <li>
                                <span class="left">邮箱：</span>
                                <span class="right"><input type="text"   name="user.email"  value="<s:property value="myself.email" />"   id="email" /></span>
                                <div class="clear"></div>
                            </li>
                            <li>
                                <span class="left">&nbsp;</span>
                                <span class="right1">
                                    <span class="tj"><a href="javascript:;"  id="submit" >提交</a></span>
                                    <input type="submit" id="submitbtn" style="display:none;">
                                    <div class="clear"></div>
                                </span>
                                <div class="clear"></div>
                            </li>
                        </ul>
                        </form>
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
