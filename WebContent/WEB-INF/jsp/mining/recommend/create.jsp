<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="../layout/head.jsp" %>
    <title>推荐挖掘-创建推荐</title>
</head>
<body>
<div class="box">
    <%@ include file="../layout/top.jsp" %>
    <div class="width_box">
        <%@ include file="../layout/message.jsp" %>
        <div class="list">
            <div class="list_top"><img src="../images/list_top.jpg"/></div>
            <div class="list_center">
                <%@ include file="../layout/left.jsp" %>
                <div class="list_right">
                    <%@ include file="guide.jsp" %>
                    <div class="wbwj_rwgl_box">
                        <div class="wbwj_rwgl_title">
                            <div class="wbwj_rwgl_title_left">新建推荐</div>
                            <div class="wbwj_rwgl_title_right"></div>
                            <div class="clear"></div>
                        </div>
                        <div class="wbwj_rwgl_ck">
                            <form action="<s:url action="recommend/save"/>" method="post" enctype="multipart/form-data">
                                <ul>
                                    <li>
                                        <span class="text">推荐标题：</span>
                                        <span class="text2" style="margin-left:0px;"><input type="text" name="recommend.title" data-value="请输入标题"/></span>
                                        <div class="clear"></div>
                                    </li>
                                    <li>
                                        <span class="text">宣传图：</span>
                                        <span class="text2" style="margin-left:0px;width:350px;background-size:350px 28px;">
                                            <input type="text" name="recommend.imgurl" data-value="请输入图片地址或上传图片" style="margin-left:5px;width:340px;"/>
                                        </span>
                                        <input type="file" name="image" id="fileimage" style="margin-left:15px;"/>
                                        <div class="clear"></div>
                                    </li>
                                    <li>
                                        <span class="text">图片链接：</span>
                                        <span class="text2" style="margin-left:0px;"><input type="text" name="recommend.clkurl" data-value="请输入标题及图片的跳转地址"/></span>
                                        <div class="clear"></div>
                                    </li>
                                    <li>
                                        <span class="text">摘要：</span>
                                        <span class="text2" style="margin-left:0px;"><input type="text" name="recommend.summary" data-value="请输入摘要"/></span>
                                        <div class="clear"></div>
                                    </li>
                                    <li>
                                        <span class="text">推荐类型：</span>
                                        <span class="text1" style="margin-left:0px;">
                                        <select id="type" name="recommend.type">
                                            <option value="0">公开</option>
                                            <option value="1">私有</option>
                                        </select>
                                        </span>
                                        <div class="clear"></div>
                                    </li>
                                    <!--
                                    <li>
                                        <span class="text">打赏：</span>
                                        <span class="text1" style="margin-left:0px;">
                                        <select id="reward" name="recommend.reward">
                                            <option value="0">否</option>
                                            <option value="1">是</option>
                                        </select>
                                        </span>
                                        <div class="clear"></div>
                                    </li>
                                    -->
                                </ul>
                                <div class="wx_list_two" style="margin-top:15px;">
                                    <ul class="sortable">
                                        <s:iterator value="list">
                                            <li class="ui-state-default" id="<s:property value="id"/>">
                                                <span class="left">
                                                    <s:if test='module=="zmt"'>
                                                        <a href="javascript:;"  data-type="title">
                                                            <span style="color: red;">[<s:property value="sitename"/>] <s:property value="title"/></span>
                                                        </a>
                                                    </s:if>
                                                    <s:else>
                                                        <a href="javascript:;"  data-type="title">
                                                            [<s:property value="sitename"/>] <s:property value="title"/>
                                                        </a>
                                                    </s:else>
									            </span>
                                                <span class="right">
                                                    <s:date name="ptime" format="yyyy-MM-dd"/>
                                                    <a href="javascript:;" data-delete="<s:property value="id"/>"><img src="../images/sc_bt.jpg"/></a>
                                                </span>
                                                <div class="clear"></div>
                                            </li>
                                        </s:iterator>
                                    </ul>
                                </div>
                                <div class="wbwj_rwgl_ck_bt">
                                    <input type="hidden" name="recommend.idlist" value="<s:property value="recommend.idlist"/>">
                                    <input type="hidden" name="recommend.status" value="<s:property value="recommend.status"/>">
                                    <a href="javascript:;" class="keep">保存</a>
                                    <div class="clear"></div>
                                </div>
                                <input type="submit" style="display:none;" id="submitbtn">
                            </form>
                        </div>
                    </div>
                </div>
                <div class="clear"></div>
            </div>
            <div class="list_top"><img src="../images/list_bottom.jpg"/></div>
        </div>
    </div>
    <%@ include file="../layout/footer.jsp" %>
</div>
</body>
</html>