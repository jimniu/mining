<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
    <option value="0">所有</option>
<s:iterator value="list">
    <s:if test="status==1">
    <option value="<s:property value="id"/>"><s:property value="title"/></option>
    </s:if>
</s:iterator>
