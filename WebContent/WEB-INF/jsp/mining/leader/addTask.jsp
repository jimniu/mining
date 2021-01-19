<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>意见领袖-新建任务</title>
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
                        <div class="wbwj_top_title">媒体传播营销：</div>
                        <div class="wbwj_top_bt"><a href="javascript:;">展开</a></div>
                        <div class="clear"></div>
                    </div>
                    <div class="wbwj_pic"   style="display:none;"><img src="../images/sjyx_mtyx.jpg"/></div>
					<div class="mtyx_xjrw_list1">
                    	<p>意见领袖任务</p>
                        <h6>新建任务</h6>
                        <div class="myyx_rwm">
                        	<span>任务名称：</span>
                            <input type="text" value="任务名称"/>
                        </div>
                        
                        <div class="myyx_tfmt">
                        	<span>投放媒体：</span>
                            <a>文本</a>
                            <a>文件</a>
                        </div>
                        <div class="myyx_img">
                        	<img src="../images/mtyx_img1.jpg"/>
                        </div>
                    	<div class="myyx_scwj">
                            <input type="text" value="上传文件"/>
                            <a href="#">浏览...</a>
                        </div>                  
                        <div class="myyx_rwys">
                        	<span>预算任务：</span>
                            <select>
                            	<option>预算任务：</option>
                            	<option>预算任务：</option>
                            	<option>预算任务：</option>
                            	<option>预算任务：</option>
                            	<option>预算任务：</option>
                            </select>
                        </div>
                        <div class="myyx_bt">
                            <a href="<s:url action="leader/task"/>" class="myyx_bt1">提交</a>
                            <a href="<s:url action="leader/task"/>" class="myyx_bt2">返回</a>
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
