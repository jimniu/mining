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
			<div class="zc_text_top">公告修改<a href="<s:url action="message/manage_index"/>">返回</a></div>
			<div class="zc_text">
				<form action="<s:url action="message/manage_update"/>" method="post"  id="form">
					<p class="btl"><label>标题：</label><input type="text"  name="message.title"  id="title"  value="<s:property value="#session.mess.title" />"/></p>
					<p ><label >内容：</label><textarea  name="message.content"  id="content" ><s:property value="#session.mess.content" /></textarea></p>
					<p class="btl"><label>权重：</label><input type="text" name="message.weight"  id="weight"   value="<s:property value="#session.mess.weight" />"/></p>
					<p><img src="../images/zc_xian.jpg" /></p>
					<p class="inp_rad"><label class="fwlb_label">是否立即发布：</label>
						<select name="message.status">
							<s:if test="#session.mess.status == 1">
           						<option value="1"  selected="selected">是</option>
								<option value="0">否</option>
					        </s:if>							
					        <s:else>
					            <option value="1" >是</option>
								<option value="0"  selected="selected">否</option>
					        </s:else>
						</select>					
					</p>
					<p><img src="../images/zc_xian.jpg"/></p>
                    <p class="inp_rad">
                      	<label class="fwlb_label">收件人：</label>
	                      	<s:if test="#session.mess.type == 0">
                      		 	<input type="radio"  value="1"  name="message.type"  /><label>用户</label>
								<input type="radio"  value="0"  name="message.type"  checked="checked"/><label>全部</label>
	                      	</s:if>
			           		<s:else>
			           			<input type="radio"  value="1"  name="message.type"   checked="checked"/><label>用户</label>
								<input type="radio"  value="0"  name="message.type"/><label>全部</label>
			           		</s:else>
	      			</p>
                     <p>
                     	<label></label>
                     	<textarea id="scopetemp"></textarea>
                     </p>
					<input type="hidden"  name="message.scope"  id="scope"  value="<s:property value="#session.mess.scope" />"/>
					<p class="p_button"><label>&nbsp;</label><a href="javascript:;"  id="submit"><img src="../images/zc_tjbutton.jpg" /></a><a href="<s:url action="message/manage_index"/>"><img src=../images/zc_qxbutton_.jpg /></a></p>
				</form>
				<div class="zc_cgceng"  style="display:none">恭喜，公告信息<br />已修改成功。</div>
			</div>
			 <div class="gonggao_user"  id="userceng">
                    <p class="top_dq">当前用户</p>
                    <div class="dqyh_list">
	                    <s:iterator  value="list" status="st">
	                        <span class="nd"><input type="checkbox"  name="userid"  value="<s:property value="id"/>"  data-value="<s:property value="name"/>"/><s:property value="name"/></span>
	                    </s:iterator>
                    </div>
                    <div class="dqyh_bt">
                        <a href="javascript:;" class="submit_bt">提交</a>
                        <a href="javascript:;" class="close_bt">关闭</a>
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
