<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div><img src="../images/sjfx_text1.png"/></div>
<div class="center">
   	<div class="wbwj_title_position_pic">
   		<img src="<s:property value="weibo.photo"/>" width="93"/>
   	</div>
	<div class="wbwj_title_position_title">
       	<s:property value="weibo.nickname"/>
    	<span>
			<s:if test='%{weibo.gender=="M"}'><img src="../images/ceng_male.jpg" /></s:if>
			<s:elseif test='%{weibo.gender=="F"}'><img src="../images/ceng_female.jpg" /></s:elseif>        	
		</span>
	</div>
    <div class="wbwj_title_position_line">
    	<img src="../images/sjfx_text4.png"/>
    </div>
    <div class="wbwj_title_position_line">
    	<img src="../images/sjfx_text4.png"/>
    </div>
    <ul>
		<s:if test="%{weibo.intro.length()>0}">
            <li>
            <span class="left">简介：</span>
            <span class="right"><s:property value="weibo.intro"/></span>
        	<div class="clear"></div>
        </li>
        </s:if>
		<s:if test="%{weibo.level.length()>0}">
            <li>
			<span class="left">等级：</span>
			<span class="right"><s:property value="weibo.level"/></span>
        	<div class="clear"></div>
        </li>
		</s:if>
		<s:if test="%{weibo.province.length()>0}">
            <li>
			<span class="left">地域：</span>
			<span class="right"><s:property value="weibo.province"/> <s:property value="weibo.city"/></span>
        	<div class="clear"></div>
        </li>
		</s:if>
		<s:if test="%{weibo.school.length()>0}">
		<li>
			<span class="left">学校：</span>
			<span class="right"><s:property value="weibo.school"/></span>
        	<div class="clear"></div>
        </li>
		</s:if>
		<s:if test="%{weibo.company.length()>0}">			
		<li>
			<span class="left">公司：</span>
			<span class="right"><s:property value="weibo.company"/></span>
        	<div class="clear"></div>
        </li>
		</s:if>
		<s:if test="%{weibo.tag.length()>0}">
		<li>
			<span class="left">标签：</span>
			<span class="right"><s:property value="weibo.tag"/></span>
        	<div class="clear"></div>
		</li>
		</s:if>									
	</ul>
</div>
<div><img src="../images/sjfx_text3.png"/></div>
