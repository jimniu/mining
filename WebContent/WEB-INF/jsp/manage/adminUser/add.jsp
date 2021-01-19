<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>艾数达</title> 
		<%@ include file="../layout/head.jsp"%>
	</head>
	<body>
	<div class="box">
	<div class="header_tow">
		<div class="header_tow_samil">
		<div class="logo"><a href="javascript:;"><img src="../images/logo_.jpg" /></a></div>
		<div class="right"><s:if test="#session.adminUser.name!=null"><span><a href="<s:url action="auser/manage_userinfo"><s:param name='adminUser.id'  value="#session.adminUser.id"></s:param></s:url>">你好，<s:property value="#session.adminUser.name" /></a></span></s:if><a href="<s:url action="auser/manage_logout"/>"><img src="../images/header_tow_but_.jpg" /></a></div>
		</div>
	</div>
	<div class="cont cont_samil">
	<%@ include file="../layout/left.jsp"%>
		<div class="rwlb_div">
			<div class="zc_text_top">管理员添加<a href="<s:url action="auser/manage_index"/>">返回</a></div>
			<div class="zc_text">
				<form action="<s:url action="auser/manage_save"/>" method="post"  id="form">
					<p class="btl"><label>用户名：</label><input type="text"  name="adminUser.name"  id="name"/></p>
					<p class="btl"><label>密码：</label><input type="password"  name="adminUser.password"  id="pwd"/></p>
					<p class="btl"><label>确认密码：</label><input type="password"  id="pwd1"/></p>
					<p class="btl"><label>全名：</label><input type="text" name="adminUser.fullName"  id="fullName"/></p>
					<p class="btl"><label>手机：</label><input type="text" name="adminUser.phone"  id="phone"/></p>
					<p class="btl"><label>邮箱：</label><input type="text" name="adminUser.email"  id="email"/></p>
					<p class="btl"><img src="../images/zc_xian.jpg" /></p>
					<p class="inp_rad"><label class="fwlb_label">管理员类型：</label>
						<select  name="adminUser.type">
							<option value="0"  selected="selected">初级</option>
							<option value="1">中级</option>
							<option value="2">高级</option>
						</select>					
					</p>
					<p class="tk_p"><label>&nbsp;</label><input type="checkbox" checked="checked"  id="readme"/><label class="tk_label">我已阅读并同意相关服务条款和隐私政策</label><a href="javascript:;"><img src="../images/tk_jt.jpg" /></a></p>
					<p class="p_button"><label>&nbsp;</label><a href="javascript:;"  id="submit"><img src="../images/zc_tjbutton.jpg" /></a><a href="<s:url action="auser/manage_index"/>"><img src=../images/fh_fhbutton.jpg /></a></p>
				</form>
				<div class="zc_cgceng"  style="display:none">恭喜，此用户<br />已添加成功。</div>
			</div>
		</div>
	</div>
	<div class="footer">
		Copyright&nbsp;&copy;&nbsp;2010&nbsp;艾数达科技&nbsp;京ICP备09063988号
	</div>
	<%@ include file="../layout/footer.jsp"%>
</div>
</body>
</html>
