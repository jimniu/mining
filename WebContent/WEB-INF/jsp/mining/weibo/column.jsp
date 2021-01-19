<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
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
                        <div class="wbwj_rwgl_ck">
                            <h2>任务信息</h2>
                            <form action="<s:url action="weibo/savecolumn"/>" method="post">
                            <ul>
                                <li>
                                    <span class="text">任务名称：</span>
                                    <span class="text2" style="margin-left:0px;"><input type="text" name="column.title" value="<s:property value="column.title"/>" data-value="请输入任务的名称" /></span>
                                    <div class="clear"></div>
                                </li> 
                                <s:if test="myself.type==2">
                                <li>
                                    <span class="text">抓取关键词：</span>
                                    <span class="text2" style="margin-left:0px;"><input type="text" name="column.fetchword" value="<s:property value="column.fetchword"/>" data-value="多词用“,”，“+”或“-”隔开，分别代表“或”，“与”和“不包含”。" /></span>
                                    <div class="clear"></div>
                                </li> 
                                </s:if>
                                <li>
                                    <span class="text">关键词：</span>
                                    <span class="text2" style="margin-left:0px;"><input type="text" name="column.searchword" value="<s:property value="column.searchword"/>" data-value="多词用“,”“+”“-”隔开，分别代表“或”“与”“不包含”。最多<s:property value="mysetting.columnkeyword"/>组关键词，多余关键词将移除。" data-status="<s:property value="mysetting.keywordchange-column.modified"/>" data-cap="<s:property value="mysetting.columnkeyword"/>" data-total="<s:property value="mysetting.totalkeyword"/>"/></span>
                                    <div class="clear"></div>
                                </li>
                                <li>
                                    <span class="text">任务类型：</span>
                                    <span class="text1" style="margin-left:0px;">
                                        <select id="relation" name="column.task" data-value="<s:property value="column.task"/>">
                                            <option value="0">抓取关键词</option>
                                            <option value="1">抓取博主</option>
                                        </select>                                    
                                    </span>
                                    <div class="clear"></div>
                                </li> 
                                <li>
                                    <span class="text">任务属性：</span>
                                    <span class="text1" style="margin-left:0px;">
                                        <select id="relation" name="column.relation" data-value="<s:property value="column.relation"/>">
                                            <option value="1">自有品牌</option>
                                            <option value="2">竞品</option>
                                        </select>                                    
                                    </span>
                                    <div class="clear"></div>
                                </li>
                                <li>
                                    <span class="text">任务状态：</span>
                                    <span class="text1" style="margin-left:0px;">
                                        <select id="status" name="column.status" data-value="<s:property value="column.status"/>">
                                            <option value="1">有效</option>
                                            <option value="0">无效</option>
                                        </select>                                    
                                    </span>
                                    <div class="clear"></div>
                                </li>
                            </ul>
                            <div class="wbwj_rwgl_ck_bt">
                            	<input type="hidden" name="column.id" value="<s:property value="column.id"/>"/>
                                <a href="javascript:;" class="keep">保存</a>
                                
                                <a href="<s:url action="weibo/columnlist"/>" class="back">返回</a>
                                <div class="clear"></div>
                            </div>
                            <input type="submit" style="display:none;"id="submitbtn">
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
