<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>忘记密码—重置</title>
    <%@ include file="../layout/head.jsp"%>
</head>
<body>
<div class="box">
    <div class="second">
        <div class="width_box">
            <div class="logo"><a  href="<s:url action="site/index"/>"><img src="../images/logo2.png"/></a></div>
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
                                <span class="left">密码：</span>
                                <span class="right">
                                    <span><input type="password"  name="user.password"  id="pwd"/></span>
                                    <p>请输入密码*</p>
                                </span>
                                <div class="clear"></div>
                            </li>

                            <li>
                                <span class="left">确认密码：</span>
                                <span class="right">
                                    <span><input type="password"  name="pwd1"  id="pwd1"/></span>
                                    <p>请输入确认密码*</p>
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
                    <div class="wjma_pic"><img src="../images/wjma_bt2.png"/></div>
                </div>
                <div class="clear"></div>
            </div>
            <div class="zc_bottom"><img src="../images/zc_bottom.png"/></div>
        </div>
    </div>
    <%@ include file="../layout/footer.jsp"%>
</div>
</body>
</html>
