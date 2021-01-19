﻿﻿﻿﻿<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<s:iterator value="list" status="st" id="lst"><b>[<s:property value="sitename"/>]</b><s:property value="title"/><s:if test='!#st.last'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</s:if></s:iterator>