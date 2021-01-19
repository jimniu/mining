<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../layout/head.jsp"%>
    <title>微博挖掘-用户管理</title>
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
                        <div class="wbwj_title">用户管理</div>
                        <div class="wbwj_yhgl_top">
                        	<form action="<s:url action="weibo/customer"/>" method="get">
                            <ul>
                                <li>任务选择：</li>
                                <li class="text">
									<select name="column.id" data-value="<s:property value="column.id"/>">
                                    <s:iterator value="columnlist" status="st">
                                    	<option value="<s:property value="id"/>"><s:property value="title"/></option>
									</s:iterator>
									</select>
                                </li>
                                <li>收录时间从：</li>
                                <li class="text1">
                                	<input type="text" name="column.from" class="datepicker"  value="<s:property value="column.from"/>"/>
                                </li>
                                <li>到</li>
                                <li class="text2">
                                	<input type="text" name="column.to" class="datepicker"  value="<s:property value="column.to"/>"/>
                                </li>
                                <div class="clear"></div>
                            </ul>
                            <ul/>
                                <li>粉丝数量大于：</li>
                                <li class="text">
                                	<input type="text" name="column.fans" value="<s:property value="column.fans"/>"/>
                                </li>
                                <li>微博数量大于：</li>
                                <li class="text">
                                	<input type="text" name="column.feed" value="<s:property value="column.feed"/>"/>
                                </li>
                                <div class="clear"></div>
                            </ul>
							<input type="hidden" name="pageindex" value="<s:property value="pageindex"/>"/>
                        	<input type="submit" style="display:none;" id="searchBtn"/>
                        	</form>
                        </div>
                        <div class="wbwj_yhgl_link">
                            <a href="javascript:;" id="deluser">删 除</a>
                            <a href="<s:url action="weibo/customerexport"/>?column.id=<s:property value="column.id"/>&column.from=<s:property value="column.from"/>&column.to=<s:property value="column.to"/>&column.fans=<s:property value="column.fans"/>&column.feed=<s:property value="column.feed"/>">导 出</a>
                            <a href="javascript:;" id="filterBtn">搜 索</a>
                            <div class="clear"></div>
                        </div>
                        <div class="wbwj_yhgl_list">
                            <table width="805" cellpadding="0" cellspacing="0" border="0">
                                <tr class="title">
                                    <td width="20"><input type="checkbox"/></td>
                                    <td>微博名称</td>
                                    <td width="100">粉丝数量</td>
                                    <td width="100">微博数量</td>
                                    <td width="110">收录时间</td>
                                    <td width="100">所属任务</td>
                                    <td width="60">删除</td>
                                </tr>
								<s:iterator value="list" id="lst" status="st">
								<tr>
									<td><input type="checkbox" name="wbid" value="<s:property value="wbid"/>" /></td>
									<td><a href="http://weibo.com/u/<s:property value="wbid"/>" target="_blank"><s:property value="nickname"/></a></td>
									<td><s:property value="fans"/></td>
									<td><s:property value="feed"/></td>
									<td><s:date name="ctime" format="yyyy-MM-dd HH:mm" /></td>
									<td><s:property value="column.name"/></td>
									<td class="jgz_td">
										<a href="javascript:;" data-delete="<s:url action="weibo/customerdelete"/>?customer.columnid=<s:property value="column.id"/>&ids=<s:property value="wbid"/>"><img src="../images/sc_bt.jpg" /></a>
									</td>
								</tr>
								</s:iterator>                                
                            </table>
                        </div>
						<div class="pagination" data-pagesize="<s:property value="pagesize"/>" data-pageindex="<s:property value="pageindex"/>" data-total="<s:property value="total"/>" data-urlbase="../weibo/customer.shtml?column.id=<s:property value="column.id"/>&column.from=<s:property value="column.from"/>&column.to=<s:property value="column.to"/>&column.fans=<s:property value="column.fans"/>&column.feed=<s:property value="column.feed"/>"/>
                    </div>
                </div>
                <div class="clear"></div>
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

