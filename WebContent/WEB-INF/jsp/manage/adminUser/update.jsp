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
	<%@ include file="../layout/top.jsp"%>
	<div class="cont cont_samil">
	<%@ include file="../layout/left.jsp"%>
		<div class="rwlb_div">
			<div class="zc_text_top">管理员修改<a href="<s:url action="auser/manage_index"/>">返回</a></div>
			<div class="zc_text">
				<form action="<s:url action="auser/manage_update"/>" method="post"  id="form">
					<p class="btl"><label>用户名：</label><input type="text"  name="adminUser.name"  id="name"  value="<s:property value="#session.au.name" />"/></p>
					<p class="btl"><label>密码：</label><input type="password"  name="adminUser.password"  id="pwd"  value="<s:property value="#session.au.password" />"/></p>
					<p class="btl"><label>确认密码：</label><input type="password"  id="pwd1"  value="<s:property value="#session.au.password" />"/></p>
					<p  class="btl"><label>全名：</label><input type="text" name="adminUser.fullName"  id="fullName"  value="<s:property value="#session.au.fullName" />"/></p>
					<p class="btl"><label>手机：</label><input type="text" name="adminUser.phone"  id="phone"   value="<s:property value="#session.au.phone" />"/></p>
					<p class="btl"><label>邮箱：</label><input type="text" name="adminUser.email"  id="email"   value="<s:property value="#session.au.email" />"/></p>
					<p class="btl"><img src="../images/zc_xian.jpg" /></p>
					<p class="inp_rad"><label class="fwlb_label">管理员类型：</label>
						<select  disabled="disabled">
							<option  value = "<s:property value="#session.au.type" />" >
	       							<s:if test="#session.au.type == 0">
	           							初级
							        </s:if>
							        <s:elseif test="#session.au.type == 1">
							            中级
							        </s:elseif>
							        <s:else>
							            高级
							        </s:else>
						       </option>
						</select>					
					</p>
					<p class="tk_p"><label>&nbsp;</label><input type="checkbox" checked="checked"  id="readme"/><label class="tk_label">我已阅读并同意相关服务条款和隐私政策</label><a href=""><img src="../images/tk_jt.jpg" /></a></p>
					<p class="p_button"><label>&nbsp;</label><a href="javascript:;"  id="submit"><img src="../images/zc_tjbutton.jpg" /></a><a href="<s:url action="auser/manage_index"/>"><img src=../images/fh_fhbutton.jpg /></a></p>
				</form>
				<div class="zc_cgceng"  style="display:none">恭喜，用户信息<br />已修改成功。</div>
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
