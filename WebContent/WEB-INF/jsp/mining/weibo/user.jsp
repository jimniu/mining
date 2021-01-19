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
                        <form action="<s:url action="weibo/user"/>" method="get">
                        <div class="wbwj_sjfx_top">
                            <ul>
                                <li>
                                    <span class="title">任务选择：</span>
                                    <span class="text">
                                        <select name="column.id" data-value="<s:property value="column.id"/>">
                                        	<s:iterator value="columnlist" status="st">
                                        	<option value="<s:property value="id"/>"><s:property value="title"/></option>
                                        	</s:iterator>
                                        </select>
                                    </span>
                                    <div class="clear"></div>
                                </li>
                                <li>
                                    <span class="title">用户属性：</span>
                                    <span class="text1"><input type="radio" name="weibo.vtype" value="C" data-value="<s:property value="weibo.vtype"/>">企业认证</span>
                                    <span class="text1"><input type="radio" name="weibo.vtype" value="P">个人认证</span>
                                    <span class="text1"><input type="radio" name="weibo.vtype" value="V">V认证用户</span>
                                    <span class="text1"><input type="radio" name="weibo.vtype" value="A" checked="checked">所有用户</span>
                                    <div class="clear"></div>
                                </li>
                                <li>
                                    <span class="title"> 性别：</span>
                                    <span class="text1"><input type="radio" name="weibo.gender" value="M" data-value="<s:property value="weibo.gender"/>"/>男</span>
                                    <span class="text1"><input type="radio" name="weibo.gender" value="F"/>女</span>
                                    <span class="text1"><input type="radio" name="weibo.gender" value="A" checked="checked"/>所有</span>
                                    <div class="clear"></div>
                                </li>
                                <li>
                                    <span class="title"> 地区：</span>
                                    <span class="text2">
                                        <select id="province" name="weibo.province" data-value="<s:property value="weibo.province"/>">
                                            <option value="">请选择</option>
                                            <s:iterator value="provlist">
                                            <option value="<s:property value="id"/>"><s:property value="name"/></option>
                                            </s:iterator>
                                        </select>
                                    </span>
                                    <span class="text2">
                                        <select id="city" name="weibo.city" data-value="<s:property value="weibo.city"/>">
                                            <option value="">请选择</option>
                                            <s:iterator value="citylist">
                                            <option value="<s:property value="id"/>"><s:property value="name"/></option>
                                            </s:iterator>
                                        </select>
                                    </span>                                    
                                    <div class="clear"></div>
                                </li>
                                <li>
                                    <span class="title">会员级别：</span>
                                    <span class="text2"><input type="text" name="weibo.level" value="0" data-value="<s:property value="weibo.level"/>"/></span>
                                    <span>级以上</span>
                                    <div class="clear"></div>
                                </li>
                                <li>
                                    <span class="title">显示顺序：</span>
                                    <span class="text2">
                                        <select id="showorder" name="column.keyword" data-value="<s:property value="column.keyword"/>">
                                            <option value="ptime">发表顺序</option>
                                            <option value="ctime">挖掘顺序</option>
                                            <option value="total">闪现次数</option>
                                            <option value="fans">粉丝数</option>
                                            <option value="feed">微博数</option>
                                        </select>
                                    </span>
                                    <div class="clear"></div>
                                </li>
                                <li>
                                    <span class="title">时间范围：</span>
                                    <span class="text2">
                                        <select id="showorder" name="days" data-value="<s:property value="days"/>">
                                            <option value="0">当天</option>
                                            <option value="1">最近24小时</option>                                            
                                            <option value="7">最近一周</option>
                                            <option value="30">最近一个月</option>
                                            <option value="90">最近三个月</option>
                                            <option value="183">最近半年</option>
                                            <option value="365">最近一年</option>
                                        </select>
                                    </span>
                                    <div class="clear"></div>
                                </li>   
                                <li>
                                    <span class="title">显示条数：</span>
                                    <span class="text2"><input type="text" name="pagesize" value="<s:property value="pagesize"/>"/></span>
                                    <div class="clear"></div>
                                </li> 
                            </ul>
                        </div>
                        <input type="hidden" name="pageindex" value="<s:property value="pageindex"/>"/>
                        <input type="submit" style="display:none;" id="searchBtn"/>
                        </form>
                        <div class="wbwj_sjfx_link">
                            <a href="javascript:;" id="deluser">删 除</a>
                            <a href="<s:url action="weibo/userexport"/>?column.id=<s:property value="column.id"/>&weibo.vtype=<s:property value="weibo.vtype"/>&weibo.gender=<s:property value="weibo.gender"/>&weibo.province=<s:property value="weibo.province"/>&weibo.level=<s:property value="weibo.level"/>&column.keyword=<s:property value="column.keyword"/>&days=<s:property value="days"/>&&pagesize=<s:property value="pagesize"/>">导 出</a>
                            <a href="javascript:;" id="filterBtn">筛 选</a>
                            <div class="clear"></div>
                        </div>
                        <div class="wbwj_sjfx_list">
                            <table width="805" cellspacing="0" spellcheck="0" border="0">
                                <tr class="title">
                                    <td width="30"><input type="checkbox" id="checkall"/></td>
                                    <td>微博名称</td>
                                    <td width="80">粉丝数量</td>
                                    <td width="80">微博数量</td>
                                    <td width="80">闪现次数</td>
                                    <td width="100">区域</td>
                                    <td width="100">操作</td>
                                </tr>
                                <s:iterator value="resultlist" status="st" id="lst">
                                <tr>
                                    <td><input type="checkbox" name="wbid" value="<s:property value="#lst[1]"/>" /></td>
                                    <td class="link" data-wbid="<s:property value="#lst[1]"/>" nowrap>
										<a href="http://weibo.com/u/<s:property value="#lst[1]"/>" target="_blank"><s:property value="#lst[2]"/></a>   
										<div class="wbwj_title_position"  pos="<s:property value="#lst[1]"/>" ></div>                                 
									</td>
                                    <td><s:property value="#lst[5]"/></td>
                                    <td><s:property value="#lst[6]"/></td>
                                    <td><s:property value="#lst[8]"/></td>
                                    <td><s:property value="#provhash[#lst[3]].name"/> <s:property value="#cityhash[#lst[4]].name"/></td>
                                    <td>
                                    	<a href="../weibo/feed.shtml?result.columnid=<s:property value="#lst[0]"/>&result.wbid=<s:property value="#lst[1]"/>" target="_blank"><img src="../images/ck_bt.jpg"/></a>
                                    	<a href="javascript:;" data-delete="<s:url action="weibo/userdelete"/>?column.id=<s:property value="column.id"/>&ids=<s:property value="#lst[1]"/>"><img src="../images/sc_bt.jpg"/></a>
                                    </td>
                                </tr>
                                </s:iterator>
                            </table>
                            <div class="pagination" data-pagesize="<s:property value="pagesize"/>" data-pageindex="<s:property value="pageindex"/>" data-total="<s:property value="total"/>" data-urlbase="../weibo/user.shtml?column.id=<s:property value="column.id"/>&column.vtype=<s:property value="column.vtype"/>&column.gender=<s:property value="column.gender"/>&column.province=<s:property value="column.province"/>&column.level=<s:property value="column.level"/>&column.keyword=<s:property value="column.keyword"/>&pagesize=<s:property value="pagesize"/>&days=<s:property value="days"/>"/>
                        </div>

                    </div>
                </div>
             <div class="clear"></div>
            </div>      
            <div class="clear"></div> 
        </div>
        <div class="list_top"><img src="../images/list_bottom.jpg"/></div>
    </div>
	<%@ include file="../layout/footer.jsp"%>
</div>
</body>
</html>