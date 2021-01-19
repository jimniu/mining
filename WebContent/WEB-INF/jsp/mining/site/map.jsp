﻿﻿﻿﻿<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
data={<s:iterator value="list" status="st" id="lst">"<s:property value="#lst[0]"/>":{"value":"<s:property value="#lst[1]"/>","index":"<s:property value="#st.index"/>","stateInitColor":"0"}<s:if test='!#st.last'>,</s:if></s:iterator>}