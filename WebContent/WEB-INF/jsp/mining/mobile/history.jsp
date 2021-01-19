 <%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%><%@taglib prefix="s" uri="/struts-tags"%>
 	
<s:iterator value="list" status="st" id="lst">
	<div class="list_one">
		<div  class="list_one_text">
			<a href="<s:url action="mobile/recommend"/>?recommend.id=<s:property value="id"/>"><s:property value="title"/></a>
		</div>
		<div class="list_one_ly">
			<div class="left"><s:date name="ctime" format="YYYY-MM-dd HH:mm"/></div>
			<div class="right"></div>
			<div class="clear"></div>
		</div>
		<div class="clear"/>
	</div>
</s:iterator>
	
     
    