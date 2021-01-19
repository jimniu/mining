<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>注册</title>
     <%@ include file="../layout/head.jsp"%>
</head>
<body>
<div class="box">
	<%@ include file="../layout/top.jsp"%>
    <div class="width_box">
        <div class="zc_list">
            <div class="zc_top"><img src="../images/zc_top.png"></div>
            <div class="zc_center">
                <div class="zc_left">
                    <div class="zc_left_box">
                        <h2>用户注册</h2>
                        <form>
                        <ul>
                            <li>
                                <span class="left">账号：</span>
                                <span class="right"><input type="text"   name="user.name"  id="name"/></span>
                                <div class="clear"></div>
                            </li>
                            <li>
                                <span class="left">密码：</span>
                                <span class="right">
                                    <input type="password"   name="user.password"  id="pwd"/>
                                    <div class="clear"></div>
                                </span>
                                <div class="clear"></div>
                            </li>
                            <li>
                                <span class="left">确认密码：</span>
                                <span class="right">
                                    <input type="password"  name="pwd1" id="pwd1"  />
                                    <div class="clear"></div>
                                </span>
                                <div class="clear"></div>
                            </li>
                            <li>
                                <span class="left">手机：</span>
                                <span class="right"><input type="text"   name="user.mobile"   id="mobile"/></span>
                                <div class="clear"></div>
                            </li>
                            <li>
                                <span class="left">邮箱：</span>
                                <span class="right"><input type="text" name="user.email"   id="email"/></span>
                                <div class="clear"></div>
                            </li>
                             <li>
				                <span class="left">验证码：</span>
				                <span class="right3">
				                     <input type="text"  id="checkCode"/>
				                     <span><img border=0  src="<s:url action="user/getcode"/>"/></span>
				                 </span>
				                <div class="clear"></div>
				            </li>
                            <li>
                                <span class="left">&nbsp;</span>
                                <span class="right1">
                                    <span class="tj"><a  href="javascript:;"  id="submit">提交</a></span>
                                    <span class="qx"><a href="<s:url action="site/index"/>">取消</a></span>
                                    <div class="clear"></div>
                                </span>
                                <div class="clear"></div>
                            </li>
                        </ul>
                        </form>
                    </div>
                </div>
                <div class="zc_right">
                    <h2>用户注册流程：</h2>
                    <div class="pic"><img src="../images/zc_pic.png"/></div>
                </div>
                <div class="clear"></div>
            </div>
            <div class="zc_bottom"><img src="../images/zc_bottom.png"></div>
        </div>
    </div>
</div>
<div class="index_dl"  style="display:none;">
    <div class="index_dl_bei">
        <div class="index_dl_bt"><a href="javascript:;"><img src="../images/index_dl_bt.png"/></a><div class="clear"></div></div>
        <div class="index_dl_text">
            <h2>注册成功</h2>
            <p>您的账号还需要审核，</p>
            <p>请耐心等待！</p>
        </div>
        <div class="index_dl_qd"><a href="javascript:;">确定</a></div>
    </div>
</div>
<%@ include file="../layout/footer.jsp"%>
</body>
</html>
