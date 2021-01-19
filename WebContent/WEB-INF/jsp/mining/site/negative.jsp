<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head lang="en">
    <link href="../css/style_chartview.css"   rel="stylesheet"   type="text/css"/>
    <title>可视数据_负面管理</title>
</head>
<body>
<div class="kssj_box">
    <div class="kssj"></div>
    <div class="kssj_list"></div>
    <div class="logo"><a href="<s:url action="site/overview"/>"><img src="../images/logo.png"></a></div>
    <div class="kssj_link">
        <ul>
            <li class="bt1"><a href="<s:url action="site/chartview"/>">数据挖掘</a></li>
            <li class="bt2"><a href="<s:url action="site/event"/>">事件管理</a></li>
            <li class="bt3"><a href="javascript:;" class="bt3_vtd">负面管理</a></li>
        </ul>
    </div>
    <div class="kssj_fmgl">
        <div class="kssj_fmgl_title">
            <ul>
                <li class="one">
                    <span>
                        <h2><s:if test="#columnlist[0].title!=''"><s:property value="#columnlist[0].title"/></s:if><s:else>负面一</s:else>：</h2>
                        <p><s:property value="#columnlist[0].keyword"/></p>
                    </span>
                </li>
                <li class="two">
                    <span>
                        <h2><s:if test="#columnlist[1].title!=''"><s:property value="#columnlist[1].title"/></s:if><s:else>负面二</s:else>：</h2>
                        <p><s:property value="#columnlist[1].keyword"/></p>
                    </span>
                </li>
                <li class="three">
                    <span>
                        <h2><s:if test="#columnlist[2].title!=''"><s:property value="#columnlist[2].title"/></s:if><s:else>负面三</s:else>：</h2>
                        <p><s:property value="#columnlist[2].keyword"/></p>
                    </span>
                </li>
                <li class="four">
                    <span>
                        <h2><s:if test="#columnlist[3].title!=''"><s:property value="#columnlist[3].title"/></s:if><s:else>负面四</s:else>：</h2>
                        <p><s:property value="#columnlist[3].keyword"/></p>
                    </span>
                </li>
                <li class="five">
                    <span>
                        <h2><s:if test="#columnlist[4].title!=''"><s:property value="#columnlist[4].title"/></s:if><s:else>负面五</s:else>：</h2>
                        <p><s:property value="#columnlist[4].keyword"/></p>
                    </span>
                </li>
                <div class=" clear"></div>
            </ul>
        </div>
        <div class="kssj_fmgl_pic">
            <ul>
                <li>
                    <div class="kssj_fmgl_pic_one">
                        <div class="kssj_fmgl_pic_one_title">2015事件数据分析</div>
                        <div class="kssj_fmgl_pic_one_pic"><img src="../images/kssj_pic5.jpg"/></div>
                    </div>
                </li>
                <li>
                    <div class="kssj_fmgl_pic_one">
                        <div class="kssj_fmgl_pic_one_title">2015事件数据分析</div>
                        <div class="kssj_fmgl_pic_one_pic"><img src="../images/kssj_pic6.jpg"/></div>
                    </div>
                </li>
                <li>
                    <div class="kssj_fmgl_pic_one">
                        <div class="kssj_fmgl_pic_one_title">2015事件数据分析</div>
                        <div class="kssj_fmgl_pic_one_link">
                            <span class="text1">用户</span>
                            <span class="text2">评论</span>
                            <span class="text3">行业</span>
                            <span class="text4">论坛</span>
                            <div class="clear"></div>
                        </div>
                        <div class="kssj_fmgl_pic_one_pic"><img src="../images/kssj_pic7.jpg"/></div>
                    </div>
                </li>
                <li>
                    <div class="kssj_fmgl_pic_one">
                        <div class="kssj_fmgl_pic_one_title">2015事件数据分析</div>
                        <div class="kssj_fmgl_pic_one_link">
                            <span class="text1">用户</span>
                            <span class="text2">评论</span>
                            <span class="text3">行业</span>
                            <span class="text4">论坛</span>
                            <div class="clear"></div>
                        </div>
                        <div class="kssj_fmgl_pic_one_pic"><img src="../images/kssj_pic8.jpg"/></div>
                    </div>
                </li>
                <div class="clear"></div>
            </ul>
            <div class="clear"></div>
        </div>
        <div class="kssj_fmgl_list">
            <ul>
                <li class="one">
                    <div class="title">
                        <span class="left">微博</span>
                        <span class="right"><s:property value="wbcount"/>条</span>
                        <div class="clear"></div>
                    </div>
                    <div class="text">
                    	<s:iterator value="wblist" status="st" id="lst">
                    	<a href="<s:url action="weibo/feed"/>?result.columnid=<s:property value="#lst[0].columnid"/>&result.wbid=<s:property value="#lst[0].wbid"/>" target="_blank">
                    		<s:property value="@com.isd.util.C@subStr(#lst[1].content,60-#lst[0].nickname.length())"/>
						</a>
                    	</s:iterator>
                    </div>
                </li>
                <li class="two">
                    <div class="title">
                        <span class="left">媒体</span>
                        <span class="right"><s:property value="xwcount"/>条</span>
                        <div class="clear"></div>
                    </div>
                    <div class="text">
                    	<s:iterator value="xwlist">
						<a href="<s:property value="url"/>" target="_blank">
                        	<s:property value="@com.isd.util.C@subStr(title,60)"/>
						</a>                    	
                    	</s:iterator>
                    </div>
                </li>
                <li class="three">
                    <div class="title">
                        <span class="left">微信</span>
                        <span class="right"><s:property value="wxcount"/>条</span>
                        <div class="clear"></div>
                    </div>
                    <div class="text">
                    	<s:iterator value="wxlist" status="st" id="lst">
                        <a href="<s:property value="#lst[0].url"/>" target="_blank">
                        	<s:property value="@com.isd.util.C@subStr(#lst[0].title,60)"/>
						</a>                    	
                    	</s:iterator>
                    </div>
                </li>
                <li class="four">
                    <div class="title">
                        <span class="left">社区</span>
                        <span class="right"><s:property value="ltcount"/>条</span>
                        <div class="clear"></div>
                    </div>
                    <div class="text">
	                    <s:iterator value="ltlist">
						<a href="<s:property value="url"/>" target="_blank">
                        	<s:property value="@com.isd.util.C@subStr(title,60)"/>
                        </a>	                    
	                    </s:iterator>
                    </div>
                </li>
                <div class="clear"></div>
            </ul>
        </div>
        <div class="clear"></div>
    </div>
</div>
</body>
</html>
