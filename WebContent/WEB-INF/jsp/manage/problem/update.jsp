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
			<div class="zc_text_top">问题修改<a href="<s:url action="problem/manage_index"/>">返回</a></div>
			<div class="zc_text">
				<form action="<s:url action="problem/manage_update"/>" method="post"  id="form">
					<p class="btl"><label>标题：</label><input type="text"  name="problem.title"  id="title"  value="<s:property value="#session.problem.title" />"/></p>
					<p ><label >内容：</label><textarea  name="problem.content"  id="content" ><s:property value="#session.problem.content" /></textarea></p>
					<p class="btl"><label>权重：</label><input type="text" name="problem.weight"  id="weight"   value="<s:property value="#session.problem.weight" />"/></p>
					<p><img src="../images/zc_xian.jpg" /></p>
					<p class="inp_rad"><label class="fwlb_label">是否立即发布：</label>
						<select name="problem.status">
							<s:if test="#session.problem.status == 1">
           						<option value="1"  selected="selected">是</option>
								<option value="0">否</option>
					        </s:if>							
					        <s:else>
					            <option value="1" >是</option>
								<option value="0"  selected="selected">否</option>
					        </s:else>
						</select>					
					</p>
					<p class="p_button"><label>&nbsp;</label><a href="javascript:;"  id="submit"><img src="../images/zc_tjbutton.jpg" /></a><a href="<s:url action="problem/manage_index"/>"><img src=../images/zc_qxbutton_.jpg /></a></p>
				</form>
				<div class="zc_cgceng"  style="display:none">恭喜，问题信息<br />已修改成功。</div>
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
