<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <%@ include file="../layout/head.jsp"%>
    <title>微博挖掘-任务管理</title>
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
                    <div class="wbwj_rwgl_box">
                        <div class="wbwj_rwgl_title">
                            <div class="wbwj_rwgl_title_left">任务管理</div>
                            <div class="wbwj_rwgl_title_right"><a href="<s:url action="weibo/column"/>">新建任务</a></div>
                            <div class="clear"></div>
                        </div>
                        <div class="wbwj_rwgl_list">
                            <table width="805" border="0" cellspacing="0" cellpadding="0">
                                <tr class="title">
                                    <td>名称</td>
                                    <td width="50">任务类型</td>
                                    <td width="50">用户总数</td>
                                    <td width="60">粉丝总数</td>
                                    <td width="60">微博总数</td>
                                    <td width="50">闪现总数</td>
                                    <td width="80">修改时间</td>
                                    <td width="80">操作</td>
                                </tr>
                                <s:iterator value="columnlist" status="st">
                                <tr>
                                    <td><a href="<s:url action="weibo/result"/>?column.id=<s:property value="id"/>"><s:property value="title"/></a></td>
                                    <td><s:if test="relation==1">自有</s:if><s:elseif test="relation==2">竞品</s:elseif><s:else>未定义</s:else></td>
                                    <td><a href="<s:url action="weibo/result"/>?column.id=<s:property value="id"/>"><s:property value="usertotal"/></a></td>
                                    <td><s:property value="fansum"/></td>
                                    <td><s:property value="feedsum"/></td>
                                    <td><s:property value="hitsum"/></td>
                                    <td><s:date name="utime" format="MM-dd HH:mm" /></td>
                                    <td>
                                        <a href="<s:url action="weibo/column"/>?column.id=<s:property value="id"/>"><img src="../images/ck_bt.jpg"/></a>
                                        <a href="javascript:;" data-delete="<s:property value="id"/>"><img src="../images/sc_bt.jpg"/></a>
                                    </td>
                                </tr>
                                </s:iterator>
                            </table>
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
