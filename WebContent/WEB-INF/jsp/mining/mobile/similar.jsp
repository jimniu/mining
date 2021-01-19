<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%><%@taglib prefix="s" uri="/struts-tags"%>
      
<div class="cb_list">
	<ul>
		<s:iterator value="list" status="st" id="lst">
		<li>
    		<span class="left"><a href="<s:property value="#lst[1]"/>" target="_blank"><s:property value="#lst[3]"/></a></span>
        	<span class="right"><s:date name="#lst[2]" format="MM-dd HH:mm" />&nbsp;&nbsp;<!--<s:if test="#lst[5]==1">原</s:if><s:elseif test="#lst[5]==2">转</s:elseif>--></span>
        </li>
		</s:iterator>
	</ul>
</div> 
