<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="../layout/head.jsp"%>
    <title>负面管理-负面分析</title>
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
                    <%@ include file="guide.jsp"%>
                    <div class="wbwj_dsj_box">
                        <div class="wbwj_title">负面分析</div>
                        <div class="fmgl_fmfx_bt">
                            <span class="text1">微信</span>
                            <span class="text2">微博</span>
                            <span class="text3">新闻</span>
                            <span class="text4">社区</span>
                            <div class="clear"></div>
                        </div>
                        <div class="sjzq_sjfx_pic">
                        	<canvas id="chart" width="805" height="490" style="margin-left:0px;" data-url="<s:url action="negative/chart"/>"></canvas>
                        </div>
                        <div class="fmgl_fmfx_link">
                        	<div class="fmgl_fmfx_left">
                                <div class="clear"></div>
                            </div>
                        	<div class="fmgl_fmfx_right">
                                <a href="javascript:;" id="handlebtn">处 理</a>
                                <div class="clear"></div>
                            </div>
                            <div class="clear"></div>
                        </div>
                        <div class="wbwj_yhgl_list" name="fmfx_link">
                        <table width="805" border="0" cellspacing="0" cellpadding="0">
                          <tr class="title">
                            <td><input type="checkbox" id="checkall"/></td>
                            <td>全部</td>
                            <td>时间</td>
                            <td>状态</td>
                          </tr>
                          <s:iterator value="list">
                          <tr>
                            <td width="20"><input type="checkbox" name="columnid" value="<s:property value="id"/>"/></td>
                            <td><a href="javascript:;" data-id="<s:property value="id"/>"><s:property value="title"/></a></td>
                            <td width="70"><s:date name="ctime" format="yyyy-MM-dd" /></td>
                            <s:if test="status==0">
                            <td width="40" class="color1">已处理</td>
                            </s:if>
                            <s:if test="status==1">
                            <td width="40" class="color2">未处理</td>
                            </s:if>                            
                          </tr>
                          </s:iterator>
                        </table>
                        </div>
                    </div>
                </div>
                <div class="clear"></div>
                </div>
            </div>
            <div class="list_top"><img src="../images/list_bottom.jpg"/></div>
        </div>
    </div>
    <%@ include file="../layout/footer.jsp"%>
</div>
</body>
</html>
