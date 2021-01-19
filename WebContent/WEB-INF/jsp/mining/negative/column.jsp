<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="../layout/head.jsp"%>
    <title>负面管理-关键词</title>
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
                    <div class="right_title">关键词</div>
                    <div class="wjgjc_box">
                    	<form action="<s:url action="negative/columnsave"/>" method="post" id="myform">
                        <ul>
                            <h2>负面关键词设置：</h2>
                            <li>
                                <span class="left">负面一：</span>
                                <span class="right text">
                                	<input type="text" name="title0" value="<s:property value="#list[0].title"/>" data-value="请输入负面标题"/> 
									<textarea name="keyword0" data-value="多词用“,”或“+”隔开，分别代表“或”和“与”。最多五组关键词，多余关键词将被移除"><s:property value="#list[0].keyword"/></textarea>
									<input type="hidden" name="id0" value="<s:property value="#list[0].id"/>"/>
                                </span>
                                <div class="clear"></div>
                            </li>
                            <li>
                                <span class="left">负面二：</span>
                                <span class="right text">
                                <span class="right text">
                                	<input type="text" name="title1" value="<s:property value="#list[1].title"/>" data-value="请输入负面标题"/> 
									<textarea name="keyword1" data-value="多词用“,”或“+”隔开，分别代表“或”和“与”。最多五组关键词，多余关键词将被移除"><s:property value="#list[1].keyword"/></textarea>
									<input type="hidden" name="id1" value="<s:property value="#list[1].id"/>"/>
                                </span>
                                </span>
                                <div class="clear"></div>
                            </li>
                            <li>
                                <span class="left">负面三：</span>
                                <span class="right text">
                                <span class="right text">
                                	<input type="text" name="title2" value="<s:property value="#list[2].title"/>" data-value="请输入负面标题"/> 
									<textarea name="keyword2" data-value="多词用“,”或“+”隔开，分别代表“或”和“与”。最多五组关键词，多余关键词将被移除"><s:property value="#list[2].keyword"/></textarea>
									<input type="hidden" name="id2" value="<s:property value="#list[2].id"/>"/>
                                </span>
                                </span>
                                <div class="clear"></div>
                            </li>
                            <li>
                                <span class="left">负面四：</span>
                                <span class="right text">
                                <span class="right text">
                                	<input type="text" name="title3" value="<s:property value="#list[3].title"/>" data-value="请输入负面标题"/> 
									<textarea name="keyword3" data-value="多词用“,”或“+”隔开，分别代表“或”和“与”。最多五组关键词，多余关键词将被移除"><s:property value="#list[3].keyword"/></textarea>
									<input type="hidden" name="id3" value="<s:property value="#list[3].id"/>"/>
                                </span>
                                </span>
                                <div class="clear"></div>
                            </li>
                            <li>
                                <span class="left">负面五：</span>
                                <span class="right text">
                                <span class="right text">
                                	<input type="text" name="title4" value="<s:property value="#list[4].title"/>" data-value="请输入负面标题"/> 
									<textarea name="keyword4" data-value="多词用“,”或“+”隔开，分别代表“或”和“与”。最多五组关键词，多余关键词将被移除"><s:property value="#list[4].keyword"/></textarea>
									<input type="hidden" name="id4" value="<s:property value="#list[4].id"/>"/>
                                </span>
                                </span>
                                <div class="clear"></div>
                            </li>                                                        
                            <li>
                                <span class="left"></span>
                                <span class="right">
									<span class="tj">
										<a href="javascript:;" id="okbtn">提交</a>
									</span> 
									<span class="cz">
										<a href="javascript:;" id="resetbtn">重置</a>
									</span>                                    
                                   <div class="clear"></div>
                                </span>
                            </li>
                        </ul>
                        </form>
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
