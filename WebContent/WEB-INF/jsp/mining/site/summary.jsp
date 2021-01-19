﻿<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<h2>
	<span><s:property value="myself.name"/>@<s:property value="mynetwork.name"/></span>
	当日数据
</h2>
<ul>
	<li class="text">
		<s:property value="today" />
	</li>
	<!--  
		            <li class="text1">新增关注用户数量（个）</li>
		            <li class="text2">888</li>
		            <li class="text1">新增媒体关注用户数量（篇）</li>
		            <li class="text3">45646</li>
		            -->
	<li class="text1">
		微博用户关注（个）
	</li>
	<li class="text4">
		<s:property value="wbtoday" />
	</li>
	<li class="text1">
		社区文章关注（个）
	</li>
	<li class="text4">
		<s:property value="lttoday" />
	</li>
	<li class="text1">
		微信公众号（个）
	</li>
	<li class="text4">
		<s:property value="openidtoday" />
	</li>
	<li class="text1">
		微信文章（篇）
	</li>
	<li class="text4">
		<s:property value="wxtoday" />
	</li>
	<li class="text1">
		新闻媒体（家）
	</li>
	<li class="text4">
		<s:property value="sitetoday" />
	</li>
	<li class="text1">
		新闻媒体拓展（篇）
	</li>
	<li class="text4">
		<s:property value="xwtoday" />
	</li>
	<li class="text5">
		<a href="javascript:;" id="changesummary">历史积累&gt;</a>
	</li>
</ul>