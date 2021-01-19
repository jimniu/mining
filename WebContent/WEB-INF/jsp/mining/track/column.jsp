<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../layout/head.jsp"%>
	<title>关键词</title>
</head>
<body>
	<div class="box">
		<%@ include file="../layout/top.jsp"%>
		<div class="width_box">
			<%@ include file="../layout/message.jsp"%>
			<div class="list">
				<div class="list_top">
					<img src="../images/list_top.jpg" />
				</div>
				<div class="list_center">
					<%@ include file="../layout/left.jsp"%>
					<div class="list_right">
						<%@ include file="guide.jsp"%>
						<div class="right_title">
							关键词
						</div>
						<div class="wjgjc_box">
						<form action="<s:url action="track/columnsave"/>" method="post" id="myform">
							<ul>
								<li>
									<span class="left">栏目一：</span>
									<span class="right text"> 
										<input type="text" name="title0" value="<s:property value="#list[0].title"/>" data-value="请输入栏目标题"/>
										<textarea name="searchword0" data-value="多词用“,”，“+”或“-”隔开，分别代表“或”，“与”和“不包含”。最多<s:property value="mysetting.columnkeyword"/>组关键词，多余关键词将移除" data-status="<s:property value="mysetting.keywordchange-#list[0].modified"/>" data-cap="<s:property value="mysetting.columnkeyword"/>"><s:property value="#list[0].searchword"/></textarea>
										<s:if test="myself.type==2">
										内容抓取关键词如下：
										<textarea name="fetchword0" data-value="多词用“,”或“+”隔开，分别代表“或”和“与”。"><s:property value="#list[0].fetchword"/></textarea>
										</s:if>																				
										<select id="relation0" name="relation0" data-value="<s:property value="#list[0].relation"/>">
	                                    	<option value="1">自有品牌</option>
	                                        <option value="2">竞品</option>
										</select> 
									</span>
									<div class="clear"></div>
								</li>
								<li>
									<span class="left">栏目二：</span>
									<span class="right text"> 
										<input type="text" name="title1" value="<s:property value="#list[1].title"/>" data-value="请输入栏目标题"/> 
										<textarea name="searchword1" data-value="多词用“,”，“+”或“-”隔开，分别代表“或”，“与”和“不包含”。最多<s:property value="mysetting.columnkeyword"/>组关键词，多余关键词将移除" data-status="<s:property value="mysetting.keywordchange-#list[1].modified"/>" data-cap="<s:property value="mysetting.columnkeyword"/>"><s:property value="#list[1].searchword"/></textarea>
										<s:if test="myself.type==2">
										内容抓取关键词如下：
										<textarea name="fetchword1" data-value="多词用“,”或“+”隔开，分别代表“或”和“与”。"><s:property value="#list[1].fetchword"/></textarea>
										</s:if>																				
										<select id="relation1" name="relation1" data-value="<s:property value="#list[1].relation"/>">
	                                    	<option value="1">自有品牌</option>
	                                        <option value="2">竞品</option>
										</select> 
									</span>
									<div class="clear"></div>
								</li>
								<li>
									<span class="left">栏目三：</span>
									<span class="right text"> 
										<input type="text" name="title2" value="<s:property value="#list[2].title"/>" data-value="请输入栏目标题"/> 
										<textarea name="searchword2" data-value="多词用“,”，“+”或“-”隔开，分别代表“或”，“与”和“不包含”。最多<s:property value="mysetting.columnkeyword"/>组关键词，多余关键词将移除" data-status="<s:property value="mysetting.keywordchange-#list[2].modified"/>"  data-cap="<s:property value="mysetting.columnkeyword"/>"><s:property value="#list[2].searchword"/></textarea>
										<s:if test="myself.type==2">
										内容抓取关键词如下：
										<textarea name="fetchword2" data-value="多词用“,”或“+”隔开，分别代表“或”和“与”。"><s:property value="#list[2].fetchword"/></textarea>
										</s:if>											
										<select id="relation2" name="relation2" data-value="<s:property value="#list[2].relation"/>">
	                                    	<option value="1">自有品牌</option>
	                                        <option value="2">竞品</option>
										</select> 
									</span>
									<div class="clear"></div>
								</li>
								<li>
									<span class="left">栏目四：</span>
									<span class="right text"> 
										<input type="text" name="title3" value="<s:property value="#list[3].title"/>" data-value="请输入栏目标题"/> 
										<textarea name="searchword3" data-value="多词用“,”，“+”或“-”隔开，分别代表“或”，“与”和“不包含”。最多<s:property value="mysetting.columnkeyword"/>组关键词，多余关键词将移除" data-status="<s:property value="mysetting.keywordchange-#list[3].modified"/>" data-cap="<s:property value="mysetting.columnkeyword"/>"><s:property value="#list[3].searchword"/></textarea>
										<s:if test="myself.type==2">
										内容抓取关键词如下：
										<textarea name="fetchword3" data-value="多词用“,”或“+”隔开，分别代表“或”和“与”。"><s:property value="#list[3].fetchword"/></textarea>
										</s:if>											
										<select id="relation3" name="relation3" data-value="<s:property value="#list[3].relation"/>">
	                                    	<option value="1">自有品牌</option>
	                                        <option value="2" selected>竞品</option>
										</select> 
									</span>
									<div class="clear"></div>
								</li>
								<li>
									<span class="left">栏目五：</span>
									<span class="right text"> 
										<input type="text" name="title4" value="<s:property value="#list[4].title"/>" data-value="请输入栏目标题"/> 
										<textarea name="searchword4" data-value="多词用“,”，“+”或“-”隔开，分别代表“或”，“与”和“不包含”。最多<s:property value="mysetting.columnkeyword"/>组关键词，多余关键词将移除" data-status="<s:property value="mysetting.keywordchange-#list[4].modified"/>" data-cap="<s:property value="mysetting.columnkeyword"/>"><s:property value="#list[4].searchword"/></textarea>
										<s:if test="myself.type==2">
										内容抓取关键词如下：
										<textarea name="fetchword4" data-value="多词用“,”或“+”隔开，分别代表“或”和“与”。"><s:property value="#list[4].fetchword"/></textarea>
										</s:if>											
										<select id="relation4" name="relation4" data-value="<s:property value="#list[4].relation"/>">
	                                    	<option value="1">自有品牌</option>
	                                        <option value="2" selected>竞品</option>
										</select> 
									</span>
									<div class="clear"></div>
								</li>
								<li>
									<span class="left">栏目六：</span>
									<span class="right text"> 
										<input type="text" name="title5" value="<s:property value="#list[5].title"/>" data-value="请输入栏目标题"/> 
										<textarea name="searchword5" data-value="多词用“,”，“+”或“-”隔开，分别代表“或”，“与”和“不包含”。最多<s:property value="mysetting.columnkeyword"/>组关键词，多余关键词将移除" data-status="<s:property value="mysetting.keywordchange-#list[5].modified"/>" data-cap="<s:property value="mysetting.columnkeyword"/>"><s:property value="#list[5].searchword"/></textarea>
										<s:if test="myself.type==2">
										内容抓取关键词如下：
										<textarea name="fetchword5" data-value="多词用“,”或“+”隔开，分别代表“或”和“与”。"><s:property value="#list[5].fetchword"/></textarea>
										</s:if>											
										<select id="relation5" name="relation5" data-value="<s:property value="#list[5].relation"/>">
	                                    	<option value="1">自有品牌</option>
	                                        <option value="2" selected>竞品</option>
										</select> 
									</span>
									<div class="clear"></div>
								</li>
								<li>
									<span class="left"></span>
									<span class="right"> 
										<span class="tj">
											<a href="javascript:;" id="okbtn">提交</a>
										</span> 
										<span class="cz">
											<a href="javascript:;" id="resetbtn">重置</a>
										</span>
										<div class="clear"></div> 
									</span>
								</li>
							</ul>
						</form>
						</div>
					</div>
					<div class="clear"></div>
				</div>
				<div class="list_top">
					<img src="../images/list_bottom.jpg" />
				</div>
			</div>
		</div>
		<%@ include file="../layout/footer.jsp"%>
	</body>
</html>

