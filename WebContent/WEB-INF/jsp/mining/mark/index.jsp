<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>数据抓取-标签管理</title>
    <%@ include file="../layout/head.jsp"%>
</head>
<body>
<div class="box">
     <%@ include file="../layout/top.jsp"%>
    <div class="width_box">
        <%@ include file="../layout/message.jsp"%>
        <div class="list">
            <div class="list_top"><img src="../images/list_top.jpg"/></div>
            <div class="list_center">
                 <%@ include file="../layout/left.jsp"%>
                <div class="list_right">
                    <div class="wbwj_top">
                        <div class="wbwj_top_title">标签管理：</div>
                        <div class="wbwj_top_bt"><a href="javascript:;" id="shou">展开</a></div>
                        <div class="clear"></div>
                    </div>
                    <div class="wbwj_pic" id="tu" style="display:none;"><img src="../images/sjzq_pic.jpg"/></div>
                    <div class="wbwj_dsj_box">
                        <div class="wbwj_title">标签管理</div>
                         <div class="sjzq_link_one">
                         	<s:if test="count>0">
	                            	<div class="sjzq_link_left"><input type="checkbox"    id="selectAll"/>全部</div>
                           </s:if>
                            <div class="sjzq_link_right">
                                <a href="javascript:;"  id="delete">删 除</a>
                                <a href="javascript:;"  id="addtag">新建标签</a>
                                <div class="clear"></div>
                            </div>
                            <div class="clear"></div>
                           <div class="sjzq_link_piao"  style="display:none"  id="ceng1">
                            	<form  action="<s:url action="mark/add"/>" method="post"  id="tagform">
                                <div class="link">
                                    <div class="gb_bt"><a href="javascript:;"><img src="../images/x_bt.jpg"/></a><div class="clear"></div></div>
                                    <div class="input"><input type="text" value="输入名称"  name="tagname"/></div>
                                    <div class="tj_bt"><a href="javascript:;">提交</a></div>
                                </div>
                               </form>
                            </div>
                        </div>
                        <div class="sjzq_sjgl">
                            <ul>
                            	<s:iterator value="list"  id="lst" status="st">
	                                <li>
	                                    <span class="bt"><input type="checkbox"  name="tagid"  value="<s:property value="#lst[0]"/>" <s:if test="#lst[2]==null">pos="0"</s:if><s:else>pos="<s:property value="#lst[2]"/>"</s:else> /></span>
	                                    <span class="text"><a href="javascript:;"><s:property value="#lst[1]"/>（<s:if test="#lst[2]==null">0</s:if><s:else><s:property value="#lst[2]"/></s:else>个）</a></span>
	                                    <span><s:date name="#lst[3]" format="yyyy-MM-dd" /></span>
	                                    <span><a  href="javascript:;"  name="xg"   pos="<s:property value="#lst[0]"/>"><img src="../images/bj_bt.jpg"/></a></span>
	                                    <span><s:if test="#lst[2]==null"><a href="javascript:;"  name="sc"  p="0"  pos="<s:property value="#lst[0]"/>"><img src="../images/sc_bt.jpg"/></a></s:if><s:elseif test="#lst[2]==0"><a href="javascript:;"  name="sc"   p="1"   pos="<s:property value="#lst[0]"/>"><img src="../images/sc_bt.jpg"/></a></s:elseif><s:else><a href="javascript:;"   name="sc"  p="2" ><img src="../images/sc_bt.jpg"/></a></s:else></span>
	                                    <div class="clear"></div>
	                                    <div class="sjzq_sjgl_piao_bt"  style="display:none"   pos="<s:property value="#lst[0]"/>">
	                                    	<form>
		                                        <div class="link">
		                                            <div class="gb_bt"><a href="javascript:;"><img src="../images/x_bt.jpg"/></a><div class="clear"></div></div>
		                                            <div class="input"><input type="text" value="修改名称"  pos="<s:property value="#lst[0]"/>"/></div>
		                                            <div class="tj_bt"><a href="javascript:;"  pos="<s:property value="#lst[0]"/>">提交</a></div>
		                                        </div>
	                                        </form>
	                                    </div>
	                                </li> 
                                </s:iterator>
                            </ul>
                            <input  type="hidden"  id="total"  value="<s:property value="count"/>"/>
                        </div>
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
