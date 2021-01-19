<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>数据抓取-数据管理_详情</title>
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
                        <div class="wbwj_top_bt"><a href="javascript:;">展开</a></div>
                        <div class="clear"></div>
                    </div>
                    <div class="wbwj_pic" style="display:none;"><img src="../images/sjzq_pic.jpg"/></div>
                    <div class="wbwj_dsj_box">
                        <div class="wbwj_rwgl_title">
                            <div class="wbwj_rwgl_title_left">数据管理</div>
							<div class="wbwj_rwgl_title_right1">                               
                                <a href="javascript:;" id="wantMark">数据抓取</a>
                            </div>
							 <div class="wbwj_rwgl_title_right1">
                                <a href="<s:url action="mark/post"/>">发表文章</a>
                            </div>                            
                            <div class="clear"></div>
                            <div class="sjzq_sjgl_piao"  style="display: none">
                                <div class="link">
                                	<form action="<s:url action="mark/detail"/>" method="post"  id="addform">
	                                    <div class="gb_bt"><a href="javascript:;"><img src="../images/x_bt.jpg"/></a><div class="clear"></div></div>
	                                    <div class="input"><input type="text" value="输入网址" name="address" /></div>
	                                    <div class="tj_bt"><a href="javascript:;">提交</a></div>
                                 	</form>
                                </div>
                            </div>
                        </div>
                        <div class="sjzq_sjgl_xq">
                            <div class="sjzq_sjgl_xq_title"><s:if test="content.media!=null"><span>[<s:property value="content.media" />]</span></s:if><s:property value="content.title" /></div>
                            <div class="sjzq_sjgl_xq_link">
                                <ul>
                                    <li>
                                        <span class="left">标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;签：</span>
                                        <span class="right">
                                        	<s:iterator value="taglist" status="st">
                                        		<span><s:property value="value" /></span>
                                        	</s:iterator>			
                                         </span>
                                        <div class="clear"></div>
                                    </li>
                                    <li>
                                        <span class="left">收藏时间：</span>
                                        <span class="right"><s:date name="content.ctime" format="yyyy-MM-dd" /></span>
                                        <div class="clear"></div>
                                    </li>
                                    <s:if test="content.address.length()>5">
                                    <li>
                                        <span class="left">原文链接：</span>
                                        <span class="right1"><input type="text" name="url" value="<s:property value="content.address" />"/></span>
                                        <div class="clear"></div>
                                    </li>
                                    </s:if>
                                </ul>
                            </div>
                            <div class="sjzq_sjgl_xq_list">
                            	<s:property value="content.content"  escape="false" />
                            </div>
                            <div class="sjzq_sjgl_xq_bq">
                                <h2>相同标签</h2>
                                <ul>
                                	<s:iterator value="relatelist" status="st">
	                                    <li>
	                                        <a href="<s:url action="mark/page"><s:param name='id'   value="id"></s:param></s:url>">
	                                            <span class="left"><s:property value="title"/></span>
	                                            <span class="right"><s:date name="ctime" format="yyyy-MM-dd" /></span>
	                                            <div class="clear"></div>
	                                        </a>
	                                    </li>
                                   </s:iterator>
                                </ul>
                            </div>
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
