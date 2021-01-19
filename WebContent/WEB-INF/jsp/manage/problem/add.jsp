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
                <div class="zc_text_top">问题添加<a href="<s:url action="problem/manage_index"/>">返回</a></div>
                <div class="zc_text">
                	<form action="<s:url action="problem/manage_save"/>" method="post"  id="form">
                        <p class="btl">
                        	<label>标题：</label>
                        	<input type="text" name="problem.title" id="title"/>
                       	</p>
                        <p>
                        	<label>内容：</label>
                        	<textarea name="problem.content"  id="content"></textarea>
                       	</p>
                        <p class="btl">
                        	<label>权重：</label>
                        	<input type="text" name="problem.weight"  id="weight"  value="0"/>
                        </p>
                        <p><img src="../images/zc_xian.jpg"/></p>
                        <p class="inp_rad"><label class="fwlb_label">是否立即发布：</label>
                            <select name="problem.status">
                                <option value="1" selected="selected">是</option>
                                <option value="0">否</option>
                            </select>
                        </p>
                        <p class="p_button">
                        	<label>&nbsp;</label>
                        	<a href="javascript:;"  id="submit"><img src="../images/zc_tjbutton.jpg"/></a>
                        	<a href="<s:url action="message/manage_index"/>"><img src="../images/zc_qxbutton_.jpg"/></a>
                        </p>
                    	<div class="zc_cgceng" style="display:none">恭喜，此问题<br/>已添加成功。</div>
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
