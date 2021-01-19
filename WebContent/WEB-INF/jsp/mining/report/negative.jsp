<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<h2>
	负面管理报表
</h2>
<div class="bbzx_list_one_width">
	<div class="bbzx_list_fmgl">
		<h3>
			<span></span>微博负面统计
		</h3>
		<div class="bbzx_list_wx_num">
			<ul>
				<li class="width">
					负面用户人数：
					<span><s:property value="wbmediacount" /></span>人
				</li>
				<li class="width">
					负面博文总数：
					<span><s:property value="wbcontentcount" /></span>篇
				</li>
			</ul>
		</div>
		<div class="bbzx_list_wx_table">
			<table width="880" border="0" cellspacing="0" cellpadding="0">
				<tr class="title">
					<td width="40">
						序号
					</td>
					<td width="180" class="left">
						用户
					</td>
					<td class="left">
						内容
					</td>
					<td width="50">
						转发数
					</td>
					<td width="50">
						评论数
					</td>
					<td width="50">
						点赞数
					</td>
					<td width="80">
						发布日期
					</td>
				</tr>
				<s:iterator value="wblist" status="st" id="lst">
				<tr>
					<td>
						<s:property value="#st.index+1" />
					</td>
					<td class="left">
						<!--<span><img src="<s:property value="#lst[1].photo"/>" width="20"/>
						</span>-->
						<span><s:property value="#lst[1].nickname" /></span>
					</td>
					<td class="left">
						<s:property value="#lst[0].content" />
					</td>
					<td>
						<s:property value="#lst[0].forward" />
					</td>
					<td>
						<s:property value="#lst[0].comment" />
					</td>
					<td>
						<s:property value="#lst[0].praise" />
					</td>
					<td>
						<s:date name="#lst[0].ptime" format="yyyy-MM-dd" />
					</td>
				</tr>
				</s:iterator>
			</table>
		</div>
		<h3>
			<span></span>微信负面统计
		</h3>
		<div class="bbzx_list_wx_num">
			<ul>
				<li class="width">
					负面报道公众号总数：
					<span><s:property value="wxmediacount" /></span>个
				</li>
				<li class="width">
					负面微信文章总数：
					<span><s:property value="wxcontentcount" /></span>人
				</li>
			</ul>
		</div>
		<div class="bbzx_list_wx_table">
			<table width="880" border="0" cellspacing="0" cellpadding="0">
				<tr class="title">
					<td width="60">
						序号
					</td>
					<td width="150" class="left">
						公众号名称
					</td>
					<td class="left">
						负面文章标题
					</td>
					<td width="80">
						阅读数
					</td>
					<td width="80">
						点赞数
					</td>
					<td width="80">
						发布日期
					</td>
				</tr>
				<s:iterator value="wxlist" status="st" id="lst">
				<tr>
					<td>
						<s:property value="#st.index+1" />
					</td>
					<td class="left">
						<!--<span><img src="../common/showimage.shtml?url=<s:property value="logo"/>" width="20"/>
						</span>-->
						<span><s:property value="wxname"/></span>
					</td>
					<td class="left">
						<s:property value="title"/>
					</td>
					<td>
						<s:property value="readcount"/>
					</td>
					<td>
						<s:property value="likecount"/>
					</td>
					<td>
						<s:date name="ptime" format="yyyy-MM-dd" />
					</td>
				</tr>
				</s:iterator>
			</table>
		</div>
		<h3>
			<span></span>媒体负面统计
		</h3>
		<div class="bbzx_list_wx_num">
			<ul>
				<li class="width">
					负面报道媒体总数：
					<span><s:property value="xwmediacount" /></span>个
				</li>
				<li class="width">
					负面新闻标题总数：
					<span><s:property value="xwcontentcount" /></span>篇
				</li>
			</ul>
		</div>
		<div class="bbzx_list_wx_table">
			<table width="880" border="0" cellspacing="0" cellpadding="0">
				<tr class="title">
					<td width="60">
						序号
					</td>
					<td width="200" class="left">
						媒体名称
					</td>
					<td class="left">
						负面新闻标题
					</td>
					<td width="80">
						发布日期
					</td>
				</tr>
				<s:iterator value="xwlist" status="st" id="lst">
				<tr>
					<td>
						<s:property value="#st.index+1" />
					</td>
					<td class="left">
						<s:property value="sitename"/>
					</td>
					<td class="left">
						<s:property value="title"/>
					</td>
					<td>
						<s:date name="ptime" format="yyyy-MM-dd" />
					</td>
				</tr>
				</s:iterator>
			</table>
		</div>
		<h3>
			<span></span>社区论坛负面统计
		</h3>
		<div class="bbzx_list_wx_num">
			<ul>
				<li class="width">
					负面发帖论坛总数：
					<span><s:property value="ltmediacount" /></span>个
				</li>
				<li class="width">
					负面帖子总数：
					<span><s:property value="ltcontentcount" /></span>篇
				</li>
			</ul>
		</div>
		<div class="bbzx_list_wx_table">
			<table width="880" border="0" cellspacing="0" cellpadding="0">
				<tr class="title">
					<td width="60">
						序号
					</td>
					<td width="200" class="left">
						论坛名称
					</td>
					<td class="left">
						负面帖子标题
					</td>
					<td width="80">
						发布日期
					</td>
				</tr>
				<s:iterator value="ltlist" status="st" id="lst">
				<tr>
					<td>
						<s:property value="#st.index+1" />
					</td>
					<td class="left">
						<s:property value="sitename"/>
					</td>
					<td class="left">
						<s:property value="title"/>
					</td>
					<td>
						<s:date name="ptime" format="yyyy-MM-dd" />
					</td>
				</tr>
				</s:iterator>
			</table>
		</div>
	</div>
</div>