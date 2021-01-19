<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<h2>
	事件管理报表
</h2>
<div class="bbzx_list_one_width">
	<div class="bbzx_list_sjgl">
		<h3>
			<span></span>事件管理
		</h3>
		<div class="bbzx_list_wx_num">
			<ul>
				<li>
					事件管理总数：
					<span><s:property value="#columnlist.size()" /></span>个
				</li>
			</ul>
		</div>
		<div class="bbzx_list_wx_table">
			<table width="880" border="0" cellspacing="0" cellpadding="0">
				<tr class="title">
					<td width="60">
						序号
					</td>
					<td class="left">
						事件名称
					</td>
					<td width="80">
						建立日期
					</td>
					<td width="80">
						微博文章数
					</td>
					<td width="80">
						微信文章数
					</td>
					<td width="80">
						新闻文章数
					</td>
					<td width="80">
						社区帖子数
					</td>
					<td width="80">
						事件状态
					</td>
				</tr>
				<s:iterator value="columnlist" status="st" id="lst">
					<tr>
						<td>
							<s:property value="#st.index+1" />
						</td>
						<td>
							<s:property value="title" />
						</td>
						<td>
							<s:date name="ctime" format="yyyy-MM-dd" />
						</td>
						<td>
							<s:property value="#wbhash[wbid]" />
						</td>
						<td>
							<s:property value="#wxhash[wxid]" />
						</td>
						<td>
							<s:property value="#xwhash[xwid]" />
						</td>
						<td>
							<s:property value="#lthash[ltid]" />
						</td>
						<td class="color1">
							<s:if test="status==1">
						            黄色预警
							</s:if>
							<s:elseif test="status==2">
							橙色预警
						    </s:elseif>
							<s:elseif test="status==3">
							红色预警
							</s:elseif>
							<s:else>
							常规状态
							</s:else>
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
		<!--  
		<h3>
			<span></span>事件分析
		</h3>
		<div class="bbzx_list_wx_num">
			<ul>
				<li>
					总计数量：
					<span>165951</span>人
				</li>
				<li>
					阅读数：
					<span>165951</span>人
				</li>
				<li>
					点赞数：
					<span>165951</span>人
				</li>
			</ul>
		</div>
		<div class="bbzx_list_wx_table">
			<table width="880" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="60">
						序号
					</td>
					<td class="left">
						事件名称
					</td>
					<td width="80">
						数据总数
					</td>
					<td width="180">
						事件关键词
					</td>
					<td width="100">
						传播地区
					</td>
					<td width="100">
						事件扩散
					</td>
				</tr>
				<tr>
					<td>
						1
					</td>
					<td class="left">
						青岛大虾事件
					</td>
					<td>
						876543
					</td>
					<td>
						青岛：大虾：38：神消费
					</td>
					<td>
						青岛：山东
					</td>
					<td>
						青岛：山东
					</td>
				</tr>
				<tr>
					<td>
						10
					</td>
					<td class="left">
						青岛大虾事件
					</td>
					<td>
						876543
					</td>
					<td>
						青岛：大虾：38：神消费
					</td>
					<td>
						青岛：山东
					</td>
					<td>
						青岛：山东
					</td>
				</tr>
			</table>
		</div>
	</div>
	-->
</div>