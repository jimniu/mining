<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
				<div class="col-sm-offset-5" style="margin-bottom:15px;"><h3>网站列表</h3></div>
				<table class="table table-hover table-striped">
					<thead>
						<tr>
							<th>标题</th>
							<th>地址</th>
							<th>方式</th>
							<th width="200">操作</th>
						</tr>
					</thead>	
					<s:iterator value="list">				
					<tr>
						<td><a href="<s:property value="address"/>" target="_blank"><s:property value="title"/></a></td>
						<td><s:property value="address"/></td>
						<td><s:property value="method"/></td>
						<td>
							<a href="../news/patternlist.shtml?site.url=<s:property value="address"/>">样式列表</a>
						</td>
					</tr>
					</s:iterator>
				</table>
				<div class="pagination" data-pagesize="<s:property value="pagesize"/>" data-pageindex="<s:property value="pageindex"/>" data-total="<s:property value="total"/>" data-urlbase="../mark/listall.shtml">
				</div>
