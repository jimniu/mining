<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div class="list_left">
	<div class="left_title">
		<a href="<s:url action="site/overview"/>">每日总揽</a>
	</div>
	<div class="sjwj">
		<div class="title">
			数据挖掘
		</div>
		<div class="list">
			<h2 style="cursor:pointer;">
				微博挖掘
			</h2>
			<div class="text" style="display:none;" data-module="weibo">
				<dl>
					<dd>
						<a href="<s:url action="weibo/columnlist"/>">任务管理</a>
					</dd>
					<dd>
						<a href="<s:url action="weibo/user"/>">用户管理</a>
					</dd>	
					<dd>
						<a href="<s:url action="weibo/content"/>">内容管理</a>
					</dd>	
					<dd>
						<a href="<s:url action="weibo/result"/>">数据分析</a>
					</dd>
					<dd>
						<a href="<s:url action="weibo/reach"/>">覆盖分析</a>
					</dd>
				</dl>
			</div>
		</div>
		<div class="list">
			<h2 style="cursor:pointer;">
				媒体挖掘
			</h2>
			<div class="text" style="display:none;" data-module="track">
				<dl>
					<dd>
						<a href="<s:url action="track/content"/>">每日动态</a>
					</dd>
					<dd>
						<a href="<s:url action="track/column"/>">关键词</a>
					</dd>
					<dd>
						<a href="<s:url action="track/category"/>">分类分析</a>
					</dd>						
					<dd>
						<a href="<s:url action="track/origin"/>">来源分析</a>
					</dd>					
					<dd>
						<a href="<s:url action="track/result"/>">数据分析</a>
					</dd>
				</dl>
			</div>
		</div>
		<div class="list">
			<h2 style="cursor:pointer;">
				微信挖掘
			</h2>
			<div class="text" style="display:none;" data-module="wechat">
				<dl>
					<dd>
						<a href="<s:url action="wechat/content"/>">每日动态</a>
					</dd>
					<dd>
						<a href="<s:url action="wechat/column"/>">关键词</a>
					</dd>
					<dd>
						<a href="<s:url action="wechat/result"/>">数据分析</a>
					</dd>
					<s:if test="mynetwork.type==2">
						<dd>
							<a href="<s:url action="wechat/paint"/>">公众号画像</a>
						</dd>
					</s:if>
				</dl>
			</div>
		</div>
		<div class="list">
			<h2 style="cursor:pointer;">
				论坛挖掘
			</h2>
			<div class="text" style="display:none;" data-module="bbs">
				<dl>
					<dd>
						<a href="<s:url action="bbs/content"/>">每日动态</a>
					</dd>
					<dd>
						<a href="<s:url action="bbs/column"/>">关键词</a>
					</dd>
					<dd>
						<a href="<s:url action="bbs/result"/>">数据分析</a>
					</dd>
				</dl>
			</div>
		</div>
		<div class="list">
			<h2 style="cursor:pointer;">
				微信通
			</h2>
			<div class="text" style="display:none;" data-module="recommend">
				<dl>
					<dd>
						<a href="<s:url action="recommend/favorite"/>">创建资讯卡</a>
					</dd>
					<dd>
						<a href="<s:url action="recommend/list"/>">我的资讯卡</a>
					</dd>
				</dl>
			</div>
		</div>
		<div class="list">
			<h2 style="cursor:pointer;">
				公众号通
			</h2>
			<div class="text" style="display:none;" data-module="public">
				<dl>
					<dd>
						<a href="<s:url action="public/link"/>">链接管理</a>
					</dd>
				</dl>
			</div>
		</div>
		<div class="list">
			<h2 style="cursor:pointer;">
				自媒体
			</h2>
			<div class="text text_last" style="display:none;" data-module="mark">
				<dl>
					<dd>
						<a href="<s:url action="mark/list"/>">数据管理</a>
					</dd>
					<dd>
						<a href="<s:url action="mark/index"/>">标签管理</a>
					</dd>
					<dd>
						<a href="<s:url action="mark/analyse"/>">数据分析</a>
					</dd>
				</dl>
			</div>
		</div>
		<div class="sjgl">
			<div class="title">
				事件管理
			</div>
			<div class="list">
				<h2>
					<a href="<s:url action="event/list"/>">事件预警</a>
				</h2>
				<s:if test="mynetwork.type==2">
					<h2>
						<a href="<s:url action="event/result"/>">事件处理</a>
					</h2>
				</s:if>
			</div>
		</div>
		<div class="fmgl">
			<div class="title">
				负面管理
			</div>
			<div class="list">
				<h2>
					<a href="<s:url action="negative/content"/>">每日动态</a>
				</h2>
				<h2>
					<a href="<s:url action="negative/column"/>">关键词</a>
				</h2>
				<s:if test="mynetwork.type==2">
					<h2>
						<a href="<s:url action="negative/result"/>">数据分析</a>
					</h2>
				</s:if>
			</div>
		</div>
		<div class="bbzx">
        	<div class="title">报表中心</div>
			<div class="list">
				<h2><a href="<s:url action="report/index"/>">集成报表</a></h2>
            </div>
		</div>	
	</div>
</div>
<div id="shortcut">
	<div class="dsj_nav_bt"><a href="javascript:;"><img src="../images/important.gif"/></a></div>
</div>
	