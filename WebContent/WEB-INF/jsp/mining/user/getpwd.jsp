<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>忘记密码—验证</title>
    <%@ include file="../layout/head.jsp"%>
</head>
<body>
<div class="box">
    <div class="second">
        <div class="width_box">
            <div class="logo">
                <span>
                    <a href="<s:url action="site/index"/>">
                        <img src="<s:property value="oem.logo" />" height="46"/>
                    </a>
                </span>
                <span>
				<s:if test='oem.banner!=""&&oem.banner!=null'>
                    <img src="<s:property value="oem.banner"/>" height="46" width="273"/>
                </s:if>
				<s:else>
                    <img src="../images/logo_text2.png" height="46"/>
                </s:else>
                </span>
            </div>
            <div class="clear"></div>
        </div>
    </div>
    <div class="width_box">
        <div class="zc_list">
            <div class="zc_top"><img src="../images/zc_top.png"/></div>
            <div class="zc_center">
                <div class="zc_left">
                    <div class="wjma_left_box">
                        <h2>忘记密码</h2>
                        <form id = "form">
                        <ul>
                            <li>
                                <span class="left">用户名：</span>
                                <span class="right">
                                    <span><input type="text"  name="user.name"/></span>
                                    <p>请输入注册的用户名*</p>
                                </span>
                                <div class="clear"></div>
                            </li>

                            <li>
                                <span class="left">注册邮箱：</span>
                                <span class="right">
                                    <span><input type="text"   name="user.email"/></span>
                                    <p>注册时所填的电子邮箱*</p>
                                </span>
                                <div class="clear"></div>
                            </li>
                            <li>
                                <span class="left">验证码：</span>
                                <span class="right2">
                                    <span class="input"><input type="text"  id="checkCode"/></span>
                                    <span><a href=""><img border=0  src="<s:url action="user/getcode"/>"/></a></span>
                                    <div class="clear"></div>
                                </span>
                                <div class="clear"></div>
                            </li>

                            <li>
                                <span class="left">&nbsp;</span>
                                <span class="right1">
                                    <span class="tj"><a href="javascript:;"  id="submit">下一步</a></span>
                                    <div class="clear"></div>
                                </span>
                                <div class="clear"></div>
                            </li>
                        </ul>
                        </form>
                    </div>
                </div>
                <div class="zc_right">
                    <div class="wjma_pic"><img src="../images/wjma_bt1.png"/></div>
                </div>
                <div class="clear"></div>
            </div>
            <div class="zc_bottom"><img src="../images/zc_bottom.png"></div>
        </div>
    </div>
    <%@ include file="../layout/footer.jsp"%>
</div>
</body>
</html>
