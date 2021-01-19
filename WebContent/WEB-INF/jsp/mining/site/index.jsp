<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="../layout/head.jsp"%>
<title><s:property value="oem.title" /></title>
</head>
<body>
<div class="index_box">
    <div class="dl_pic">
        <div class="top">
            <div class="logo">
	            <span>
	            	<a href="<s:url action="site/index"/>">
	            		<img src="<s:property value="oem.logo" />" height="46"/>
	            	</a>
	            </span>
	            <span>
                    <s:if test='oem.homebanner!=""&&oem.homebanner!=null'>
                        <img src="<s:property value="oem.homebanner"/>" height="46" width="273"/>
                    </s:if>
                    <s:else>
                        <img src="../images/logo_text1.png" height="46"/>
                    </s:else>
	            </span>
            </div>
            <div class="tell">联系电话：<s:property value="oem.phone" /></div>
            <div class="clear"></div>
        </div>        
        <div class="width_box">
           <div class="hy">
           <form action="javascript:;"  id="loginform">
           <ul>
           	   <!--<li class="ljty_bt"><a href="javascript:;"  id="tiyan">立即体验</a></li>-->
               <li>
                   <span class="left"><a href="javascript:;">用户登录</a></span>
                   <span class="right"><a href="<s:url action="user/index"/>">注册</a></span>
                   <div class="clear"></div>
               </li>
               <li class="yhm"><input type="text" name="user.name" value="<s:property value="username"/>" data-value="帐号"/></li>
               <li class="ma"><input type="password" name="user.password"/></li>
               <li class="text">
                   <span class="left"><input type="checkbox" name="remember" value="1"/>记住我</span>
                   <span class="right_ma"><a href="<s:url action="user/getpwd"/>">忘记密码？</a></span>
                   <div class="clear"></div>
               </li>
               <li class="dl"><a href="javascript:;" id="loginbtn">登录</a></li>
           </ul>
           </form>
           <div class="clear"></div>
           </div>
        </div>
    </div>
    <div class="nav">
        <div class="width_box">
            <div class="div_link">
                <h2>我们的挖掘源</h2>
                <ul>
                    <li>
                        <a href="javascript:;">
                        <span><img src="../images/index_bt1.jpg"/></span>
                        <h6>微博数据挖掘</h6>
                        <p>通过<s:property value="oem.title" />进行社交媒体用户数据和用户行为数据进行大数据挖掘，并且通过用户数据进行粉丝沟通和数据营销活动</p>
                        </a>
                    </li>
                    <li class="two">
                        <a href="javascript:;">
                        <span><img src="../images/index_bt2.jpg"/></span>
                        <h6>媒体数据挖掘</h6>
                        <p>通过<s:property value="oem.title" />进行媒体新闻数据、信息和新闻门户进行数据挖掘，并且对挖掘数据进行全方位的数据分析和统计</p>
                        </a>
                    </li>
                    <li>
                        <a href="javascript:;">
                        <span><img src="../images/index_bt3.jpg"/></span>
                        <h6>自媒体</h6>
                        <p>企业自媒体功能是通过<s:property value="oem.title" />为基础，针对重要单一某个数据源进行全文抓取和收藏形成自有媒体数据库并对数据进行分析和统计</p>
                        </a>
                    </li>
                    <li class="four">
                        <a href="javascript:;">
                        <span><img src="../images/index_bt4.jpg"/></span>
                        <h6>电商数据挖掘</h6>
                        <p>通过<s:property value="oem.title" />进行社交媒体用户数据和用户行为数据进行大数据挖掘，并且通过用户数据进行粉丝沟通和数据营销活动</p>
                        </a>
                    </li>
                    <li>
                        <a href="javascript:;">
                        <span><img src="../images/index_bt5.jpg"/></span>
                        <h6>论坛数据挖掘</h6>
                        <p>通过<s:property value="oem.title" />进行社交媒体用户数据和用户行为数据进行大数据挖掘，并且通过用户数据进行粉丝沟通和数据营销活动</p>
                        </a>
                    </li>
                    <div class="clear"></div>
                </ul>
                <div class="clear"></div>
            </div>
        </div>
    </div>
    <div class="footer_index">
    	<div class="footer_second">
			<s:property value="oem.footer" escape="false"/>
		</div>
    </div>
</div>
<div class="index_dl"  style="display:none;">
    <div class="index_dl_bei">
        <div class="index_dl_bt"><a href="javascript:;"><img src="../images/index_dl_bt.png"/></a><div class="clear"></div></div>
        <div class="index_dl_text">
            <h2>登录失败</h2>
            <p>您的账号未生效或者</p>
            <p>账号密码错误！</p>
        </div>
        <div class="index_dl_qd"><a href="javascript:;">确定</a></div>
    </div>
</div>
<div class="index_ljty"  style="display:none;">
    <div class="ljty_list">
        <div class="index_ljty_bt"><a href="javascript:;"><img src="../images/index_dl_bt.png"/></a><div class="clear"></div></div>
        <h2>在线申请</h2>
        <form>
        <ul>
            <li>
                <span class="left">姓名：</span>
                <span class="right"><input type="text"  name="tuser.name"  id="tusername"/></span>
                <div class="clear"></div>
            </li>
            <li>
                <span class="left">公司名称：</span>
                <span class="right"><input type="text"   name="tuser.company"/></span>
                <div class="clear"></div>
            </li>
            <li>
                <span class="left">手机号：</span>
                <span class="right"><input type="text"    name="tuser.mobile"/></span>
                <div class="clear"></div>
            </li>
             <li>
                <span class="left">固定电话：</span>
                <span class="right1">
                    <span class="input"><input type="text"  name="tuser.tellphone"/></span>
                    <span class="text">例如：010-88888888-818</span>
                </span>
                <div class="clear"></div>
            </li>    
            <li>
                <span class="left">邮箱：</span>
                <span class="right"><input type="text"   name="tuser.email"/></span>
                <div class="clear"></div>
            </li>
             <li>
                <span class="left">验证码：</span>
                <span class="right2">
                     <input  type="text"  id="checkCode"/>
                     <span><img border=0  src="<s:url action="user/getcode"/>"/></span>
                 </span>
                <div class="clear"></div>
            </li>
            <li><a href="javascript:;" >提交</a></li>
        </ul>
        </form>
    </div>
</div>
<img style="display:none;" src="../site/compress.shtml"/>
<script type="text/javascript" src="../js/jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../js/layer/layer.js"></script>
<script type="text/javascript" src="../js/pagination/jquery.pagination.js"></script>
<script type="text/javascript" src="../js/xheditor/xheditor-zh-cn.js">	</script>
<script type="text/javascript" src="../js/base.js"></script>
<script type="text/javascript" src="../js/jquery/jquery-ui.min.js"></script>
<script type="text/javascript" src="../js/chart/Chart.min.js"></script>
<script type="text/javascript" src="../js/mining/<s:property value="modulename" />.js" charset="UTF-8"></script>
<!--
<script src="//assets.kf5.com/supportbox/main.js" id="kf5-provide-supportBox" kf5-domain="isd.kf5.com"></script>
-->
</body>
</html>
