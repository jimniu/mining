<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<h2>
	微博挖掘报表
</h2>
<div class="bbzx_list_one_width">
	<div class="bbzx_list_wb">
		<h3>
			<span></span>微博用户报表
		</h3>
		<div class="bbzx_list_wx_num">
			<ul>
				<li>
					总计人数：
					<span><s:property value="#weibosummary[0][0]" />
					</span>人
				</li>
				<li>
					总粉丝数：
					<span><s:property value="#weibosummary[0][1]" />
					</span>人
				</li>
				<li>
					发布微博总数：
					<span><s:property value="#weibosummary[0][2]" />
					</span>篇
				</li>
			</ul>
		</div>
		<div class="bbzx_list_wx_table">
			<table width="880" border="0" cellspacing="0" cellpadding="0">
				<tr class="title">
					<td width="50">
						序号
					</td>
					<td class="left">
						用户
					</td>
					<td width="90">
						关注数
					</td>
					<td width="90">
						粉丝数
					</td>
					<td width="90">
						博文数
					</td>
					<td width="90">
						关键词次数
					</td>
				</tr>
				<s:iterator value="weibolist" status="st" id="lst">
					<tr id="<s:property value="#lst[0].id"/>">
						<td>
							<s:property value="#st.index+1" />
						</td>
						<td class="left">
							<!--  
							<span>
								<img src="<s:property value="#lst[0].photo"/>"	width="20" />
							</span>
							-->
							<span><s:property value="#lst[0].nickname" />
							</span>
						</td>
						<td>
							<s:property value="#lst[0].follow" />
						</td>
						<td>
							<s:property value="#lst[0].fans" />
						</td>
						<td>
							<s:property value="#lst[0].feed" />
						</td>
						<td>
							<s:property value="#lst[1]" />
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
		<h3>
			<span></span>微博博文报表
		</h3>
		<div class="bbzx_list_wx_num">
			<ul>
				<li>
					匹配博文总计：
					<span><s:property value="#contentsummary[0][0]" />
					</span>篇
				</li>
				<li>
					转发数总计：
					<span><s:property value="#contentsummary[0][1]" />
					</span>次
				</li>
				<li>
					评论数总计：
					<span><s:property value="#contentsummary[0][2]" />
					</span>条
				</li>
				<li>
					点赞数总计：
					<span><s:property value="#contentsummary[0][3]" />
					</span>次
				</li>
			</ul>
		</div>
		<div class="bbzx_list_wb_list">
			<s:iterator value="contentlist" status="st" id="lst">
				<div class="bbzx_list_wb_one">
					<div class="bbzx_list_wb_one_left">
						<div class="bbzx_list_wb_one_pic">
							<img width="93" src="<s:property value="#lst[1].photo"/>" />
						</div>
						<div class="bbzx_list_wb_one_piao">
							<img src="../images/wbwj_dsj_pic.png" />
						</div>
					</div>
					<div class="bbzx_list_wb_one_right">
						<div class="top">
							<div class="left">
								<span class="name"><s:property value="#lst[1].nickname" />
								</span>
								<span class="bt">
									<s:if test='gender=="M"'>
										<img src="../images/ceng_male.jpg" />
									</s:if>
									<s:if test='gender=="F"'>
										<img src="../images/ceng_female.jpg" />
									</s:if> 
								</span>
								<span class="text"><span>粉丝</span>
								<s:property value="#lst[1].fans" />
								</span>
								<span class="text"><span>关注</span>
								<s:property value="#lst[1].follow" />
								</span>
								<span class="text"><span>博文</span>
								<s:property value="#lst[1].feed" />
								</span>
							</div>
						</div>
						<div class="text">
							<s:property value="#lst[0].content" />
						</div>
						<div class="time">
							<span><s:date name="#lst[0].ptime" format="yyyy年MM月dd日 HH:mm" />
							</span><span>来自</span><span><s:property value="#lst[0].client" />
							</span>
						</div>
						<div class="link">
							<ul>
								<li>
									<span>转发</span>
									<s:property value="#lst[0].forward" />
								</li>
								<li>
									<span>评论</span>
									<s:property value="#lst[0].comment" />
								</li>
								<li>
									<span>赞</span>
									<s:property value="#lst[0].praise" />
								</li>
							</ul>
						</div>
					</div>
					<div class="clear"></div>
				</div>
			</s:iterator>
		</div>
		<h3>
			<span></span>数据分析(无)
		</h3>
	</div>
</div>
