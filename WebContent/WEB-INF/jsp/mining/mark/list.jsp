<%@page contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>数据抓取-数据管理</title>
    <%@ include file="../layout/head.jsp"%>
	<link href="../css/jquery-ui.min.css" rel="stylesheet" type="text/css" />
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
                        <div class="wbwj_top_title" >数据管理：</div>
                        <div class="wbwj_top_bt"><a href="javascript:;"  id="shou">展开</a></div>
                        <div class="clear"></div>
                    </div>
                    <div class="wbwj_pic" id="tu" style="display:none;"><img src="../images/sjzq_pic.jpg"/></div>
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
                            <div class="sjzq_sjgl_piao"   style="display: none">
                                <div class="link">
	                                <form action="<s:url action="mark/detail"/>" method="post"  id="addform">
	                                    <div class="gb_bt"><a href="javascript:;"><img src="../images/x_bt.jpg"/></a><div class="clear"></div></div>
	                                    <div class="input"><input type="text" value="输入网址" name="address" /></div>
	                                    <div class="tj_bt"><a href="javascript:;">提交</a></div>
	                                 </form>
                                </div>
                            </div>
                        </div>
                         <div class="sjzq_sjgl_list">
                         	<form action="<s:url action="mark/list"/>" method="get"  id="searchform">
                            <ul>
                                <li>数据抓取时间从</li>
                                <li class="text3"><input type="text"  name="from"  id="datepicker"  value="<s:property value="from"/>"/></li>
                                <li>到</li>
                                <li class="text3"><input type="text"  name="to"   id="datepicker1"   value="<s:property value="to"/>"/></li>
                                <li>关键词</li>
                                <li class="text2"><input type="text"  name="kw"  id="kw" value="<s:property value="kw"/>"/></li>
                                <li>标签</li>
                                <li class="text1">
                                    <select name="content.tag"  data-value="<s:property value="content.tag"/>">
                                       	<option value="">请选择：</option>
										<s:iterator value="taglist" status="st">
											<option value="<s:property value="id"/>">
												<s:property value="value" />
											</option>
										</s:iterator>
                                    </select>
                                </li>
                                <div class="clear"></div>
                            </ul>
                            </form>
                        </div>
                        <div class="sjzq_link">
                         	<s:if test="total>0">
                            	<div class="sjzq_link_left"><input type="checkbox"   id="selectAll"/>全部</div>
                            </s:if>
                            <div class="sjzq_link_right">
                                <a href="javascript:;"  id="delete">删 除</a>
                                <a href="../mark/export.shtml?content.ctime=<s:property value="content.ctime"/>&content.utime=<s:property value="content.utime"/>&content.title=<s:property value="content.title"/>&content.tag=<s:property value="content.tag"/>">导 出</a>
                                <a href="javascript:;" id="search">搜 索</a>
                                <div class="clear"></div>
                            </div>
                            <div class="clear"></div>
                        </div>
                        <div class="sjzq_sjgl">
                            <ul>
                            	<s:iterator value="marklist" status="ml">
	                                <li>
	                                    <span class="bt"><input type="checkbox" name="contentid" value="<s:property value="id"/>" /></span>
	                                    <span class="text"><a href="<s:url action="mark/page"><s:param name='id'   value="id"></s:param></s:url>"><b>[<s:property value="media" />]</b> <s:property value="title" /> </a></span>
	                                    <span><s:date name="ctime" format="yyyy-MM-dd" /></span>
	                                    <span><a href="<s:url action="mark/detail"><s:param name='id'   value="id"></s:param></s:url>"><img src="../images/bj_bt.jpg"/></a></span>
	                                    <span><a href="javascript:;"  name="sc"  pos="<s:property value="id"/>"><img src="../images/sc_bt.jpg"/></a></span>
                                        <span>
											<a href="javascript:;" data-favorite="<s:property value="id"/>"><img src="../images/recommend.jpg"></a>
										</span>
	                                    <div class="clear"></div>
	                                </li>
                                </s:iterator>
                            </ul>
                        </div>
                        <div class="ym">
                            <div class="fy_div" data-pagesize="<s:property value="pagesize"/>"
							data-pageindex="<s:property value="pageindex"/>"
							data-total="<s:property value="total"/>"
							data-urlbase="../mark/list.shtml?kw=<s:property value="kw"/>&from=<s:property value="from"/>&to=<s:property value="to"/>&content.tag=<s:property value="content.tag"/>">
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
