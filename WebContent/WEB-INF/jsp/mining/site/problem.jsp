<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../layout/head.jsp"%>
    <title>常见问题</title>
</head>
<body>
<div class="box">
    <%@ include file="../layout/top.jsp"%>
    <div class="width_box">
        <%@ include file="../layout/message.jsp"%>
        <div class="list">
            <div class="list_top"><img src="../images/list_top.jpg"/></div>
            <div class="list_center">
                <div class="list_left">
                    <div class="yhxx_left">
                        <ul>
                            <li><a href="<s:url action="site/about"/>">关于我们</a></li>
                            <li><a href="<s:url action="site/problem"/>" class="yhxx_left_vtd">常见问题</a></li>
                            <li><a href="<s:url action="site/joinus"/>">加入我们</a></li>
                        </ul>
                    </div>
                </div>
                <div class="list_right">
                    <div class="list_right_title">常见问题</div>
                    <div class="cjwt_list">
                    	<s:iterator value="list" status="st">
	                        <div class="cjwt_list_one">
	                            <div class="cjwt_list_one_title"><a href="javascript:;" class="cjwt_list_vtd"  pos="<s:property value="id"/>"   pp="0"><s:property value="title"/></a></div>
	                            <div class="cjwt_list_one_text" style=" display: none;"  pos="<s:property value="id"/>">
	                                <p><s:property value="content"/></p>          
	                            </div>
	                        </div>
                        </s:iterator>
                    </div>                    
                    <div class="ym">
                        <div class="fy_div" data-pagesize="<s:property value="pagesize"/>"  data-pageindex="<s:property value="pageindex"/>" data-total="<s:property value="total"/>" data-urlbase="../site/problem.shtml"></div>
                	</div>               
               	</div>
                <div class="clear"></div>
            </div>
            <div class="list_top"><img src="../images/list_bottom.jpg"/></div>
        </div>
    </div>
     <%@ include file="../layout/footer.jsp"%>
</div>
</body>
</html>
