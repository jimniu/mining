<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<option value="">请选择</option>
<s:iterator value="list">
<option value="<s:property value="id"/>"><s:property value="name"/></option>
</s:iterator>