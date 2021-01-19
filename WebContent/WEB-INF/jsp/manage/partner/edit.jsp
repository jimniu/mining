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
    <div class="cont">
       <%@ include file="../layout/left.jsp"%>
        <div class="right">
            <div class="rwlb_div">
                <div class="zc_text_top">合作伙伴编辑<a href="<s:url action="partner/manage_index"/>">返回</a></div>
                <div class="zc_text">
                	<form action="<s:url action="partner/manage_update"/>" method="post"  id="form">
                        <p class="btl">
                        	<label>合作伙伴：</label>
                        	<input type="text" name="partner.name" value="<s:property value="partner.name"/>"/>
                       	</p>
						<p class="btl">
							<label>站点logo的地址：</label>
							<input type="text" name="partner.logo"  value="<s:property value="partner.logo"/>"/>
						</p>
						<p class="btl">
                        	<label>站点名称：</label>
                        	<input type="text" name="partner.title" value="<s:property value="partner.title"/>"/>
                       	</p>
						<p class="btl">
							<label>站点名称的图片链接：</label>
							<input type="text" name="partner.banner"  value="<s:property value="partner.banner"/>"/>
						</p>
						<p class="btl">
							<label>首页站点名称链接：</label>
							<input type="text" name="partner.homebanner"  value="<s:property value="partner.homebanner"/>"/>
						</p>
                        <p class="btl">
                        	<label>域名：</label>
                        	<input type="text" name="partner.domain"  value="<s:property value="partner.domain"/>"/>
                       	</p>
                        <p class="btl">
                        	<label>联系电话：</label>
                        	<input type="text" name="partner.phone"  value="<s:property value="partner.phone"/>"/>
                       	</p>                        	                      	
                        <p>
                        	<label>页脚代码：</label>
                        	<textarea name="partner.footer"><s:property value="partner.footer"/></textarea>
                       	</p>
                       	<p>
                       		<label>是否有效：</label>      
                       		<select name="partner.status" data-value="<s:property value="partner.status"/>">
                       			<option value="1">有效</option>
                       			<option value="0">无效</option>
                       		</select>                 		
                       	</p>
                        <p class="p_button">
                        	<label>&nbsp;</label>
                        	<a href="javascript:;"  id="submit"><img src="../images/zc_tjbutton.jpg"/></a>
                        	<a href="<s:url action="message/manage_index"/>"><img src="../images/zc_qxbutton_.jpg"/></a>
                        </p>
                        <input type="hidden" name="partner.id" value="<s:property value="partner.id"/>"/>
                    </form>
                </div>
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
