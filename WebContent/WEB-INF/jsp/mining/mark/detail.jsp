<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>数据抓取-数据管理-数据抓取</title>
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
                        <div class="wbwj_top_title">数据管理：</div>
                        <div class="wbwj_top_bt"><a href="javascript:;"  id="shou">展开</a></div>
                        <div class="clear"></div>
                    </div>
                    <div class="wbwj_pic" id="tu" style="display:none;"><img src="../images/sjzq_pic.jpg"/></div>
                    <div class="wbwj_dsj_box">
                        <div class="wbwj_title">数据管理</div>
                        <div class="sjzq_sjgl_zq">
                        <form  action="<s:url action="mark/save"/>" method="post"   id="saveform" >
                            <ul>
                                <li>
                                    <span class="left">网址URL：</span>
                                    <span class="right"><input type="text"  value="<s:property value="content.address" />"  name="content.address"/></span>
                                    <div class="clear"></div>
                                </li>
                                <li>
                                    <span class="left">标题：</span>
                                    <span class="right4">
                                         <span class="right"><input type="text"  value="<s:property value="content.title" />"   name="content.title"  id="title"/></span>
                                        <div class="text"><span>*</span> 注意：标题最多30个汉字的长度！</div>
                                    </span>
                                    <div class="clear"></div>
                                </li>
                                <li>
                                    <span class="left">网站：</span>
                                    <span class="right4">
                                         <span class="right"><input type="text"  value="<s:property value="content.media" />"   name="content.media"  id="media"/></span>
                                        <div class="text"></div>
                                    </span>
                                    <div class="clear"></div>
                                </li>                                
                                <li>
                                    <span class="left">内容：</span>
                                    <span class="right3"><textarea style="width:600px;height:439px;" id="contentarea"  cols="" rows=""  name="content.content"><s:property value="content.content"  escape="false"/></textarea> </span>
                                    <div class="clear"></div>
                                </li>
                                
                                <li>
                                    <span class="left">标签：</span>
                                    <span class="right2"   id="biaoqian">
	                                    <s:iterator  value="taglist"  status="st">
	                                        <span><input type="checkbox"  name="tagid"  value="<s:property value="id"/>"/><s:property value="value"/></span>
	                                    </s:iterator>
                                    </span>
                                    <div class="clear"></div>
                                </li>
                                <li>
                                    <span class="left">点评：</span>
                                    <span class="right1"><textarea name="content.comment"   id="comment"><s:property value="content.comment" /></textarea></span>
                                    <div class="clear"></div>
                                </li>
                                <li>
                                    <span class="left">&nbsp;</span>
                                    <span class="right_bt">
                                        <span class="bc"><a href="javascript:;"   id="save">立即保存</a></span>
                                        <span class="qx"><a href="javascript:;"   id="goback" >取消</a></span>
                                        <div class="clear"></div>
                                    </span>
                                    <div class="clear"></div>
                                </li>
                            </ul>
                            <input type="hidden"  id="tag"   name="content.tag"  value="<s:property value="content.tag" />"/>
							<input type="hidden"  name="content.id"  value="<s:property value="content.id" />"/>
							<input type="hidden"  name="content.method"  value="<s:property value="content.method" />"/>
                            </form>
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
