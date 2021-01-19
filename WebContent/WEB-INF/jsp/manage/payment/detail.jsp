<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>艾数达</title> 
		<%@ include file="../layout/head.jsp"%>
		<link href="../css/style.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
	<div class="box">
	<div class="header_tow">
		<div class="header_tow_samil">
		<div class="logo"><a href="javascript:;"><img src="../images/logo_.jpg" /></a></div>
		<div class="right"><s:if test="#session.adminUser.name!=null"><span><a href="<s:url action="auser/manage_userinfo"><s:param name='adminUser.id'  value="#session.adminUser.id"></s:param></s:url>">你好，<s:property value="#session.adminUser.name" /></a></span></s:if><a href="<s:url action="auser/manage_logout"/>"><img src="../images/header_tow_but_.jpg" /></a></div>
		</div>
	</div>
	<div class="cont cont_samil" >
	<%@ include file="../layout/left.jsp"%>
		<div class="rwlb_div">
			<div class="zc_text_top">订单信息<a href="<s:url action="payment/manage_index"/>">返回</a></div>
			<div class="zc_text zc_text_info">
					<p class="btl"><label>订单ID：</label><input type="text"  value="<s:property value="#session.p.id" />"  disabled="disabled"  class="dis"/></p>
					<p class="btl"><label>用户名：</label><input type="text"  value="<s:property value="#session.u.name" />"   disabled="disabled"  class="dis"/></p>
					<p class="btl"><label>申请时间：</label><input type="text"  value="<s:date name="#session.p.ctime"  format="yyyy-MM-dd    HH:mm:ss" />"   disabled="disabled"  class="dis"/></p>
					<p><label>行业：</label>
					   <select  class="hy_select"  disabled="disabled">
						  <option value="<s:property value="#session.u.area" />"><s:property value="#session.u.area" /></option>						
						</select>
					</p>
					<p class="btl"><label>联系人/运营负责人：</label><input type="text"  name="user.contact"  value="<s:property value="#session.u.contact" />"  disabled="disabled"   class="dis"/></p>
					<p class="btl"><label>公司电话：</label><input type="text"  name="user.mobile" value="<s:property value="#session.u.mobile" />"   disabled="disabled"   class="dis"/></p>
					<p class="btl"><label>负责人手机验证：</label><input type="text"  name="user.cellphone"  value="<s:property value="#session.u.cellphone" />"    id="cellphone"  disabled="disabled"   class="dis"/></p>
					<p class="btl"><label>邮箱：</label><input type="text"  name="user.email"  value="<s:property value="#session.u.email" />"   id="email"  disabled="disabled"   class="dis"/></p>
					<p><img src="../images/zc_xian.jpg" /></p>
					<p class="btl"><label>绑定微博：</label><input type="text"   value="<s:property value="#session.u.weibo" />"    disabled="disabled"  class="dis"/></p>
					<p class="btl"><label>绑定微信公众号：</label><input type="text"   value="<s:property value="#session.u.weixin" />"   disabled="disabled"   class="dis"/></p>
					<p><img src="../images/zc_xian.jpg" /></p>
					<p class="inp_rad"><label class="fwlb_label">服务时限：</label>					
						<s:if test="#session.u..serviceTime ==3">
         					<input type="radio"   value="3"  checked="checked"  disabled="disabled"/><label>三个月</label>
							<input type="radio"    value = "6"   disabled="disabled"/><label>六个月</label>
							<input type="radio"   value = "12"  disabled="disabled"/><label>一年</label>
				        </s:if>
				        <s:elseif test="#session.u.serviceTime ==6">
				            <input type="radio" value="3"  disabled="disabled"/><label>三个月</label>
							<input type="radio" value = "6"    checked="checked"   disabled="disabled"/><label>六个月</label>
							<input type="radio"   value = "12"  disabled="disabled"/><label>一年</label>
				        </s:elseif>
				        <s:else>
				            <input type="radio"   value="3"   disabled="disabled"/><label>三个月</label>
							<input type="radio"   value = "6"   disabled="disabled"/><label>六个月</label>
							<input type="radio"   value = "12"   checked="checked"  disabled="disabled"/><label>一年</label>
				        </s:else>
					</p>	
					<p class="p_button"><label>&nbsp;</label>
					<s:if test="#session.p.status==0">
						<a href="<s:url action="payment/manage_ensure"></s:url>"><img src="../images/sh_button.jpg" /></a>
					</s:if>					
					<a href="<s:url action="payment/manage_index"/>"><img src="../images/fh_fhbutton.jpg" /></a></p>						
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
