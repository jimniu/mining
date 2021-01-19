<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<div class="left">
	<span>后台管理系统</span>
	<div class="list" id="leftmenu">
		<a href="<s:url action="auser/manage_index"/>" class="a1">管理员</a>
		<a href="<s:url action="network/manage_index"/>" class="a2">账号管理</a>
		<a href="<s:url action="user/manage_index"/>" class="a2">用户管理</a>
		<a href="<s:url action="recommend/manage_index"/>" class="a3">推荐管理</a>
		<a href="<s:url action="message/manage_index"/>" class="a1">公告管理</a>
		<a href="<s:url action="problem/manage_index"/>" class="a3_hover">问题管理</a>
		<a href="<s:url action="tuser/manage_index"/>" class="a2">体验用户</a>
		<a href="<s:url action="partner/manage_index"/>" class="a2">合作伙伴</a>
	</div>
</div>

<script type="text/javascript">
	//清除所有的选中选项，同时设置当前的菜单为选中状态
	$("#leftmenu a").each(function(){
		var self = $(this);
		var css = self.attr("class");
		var link = self.attr("href");
		var arr = css.split("_");
		if(link.indexOf("\/"+MODULE+"\/")>-1){
			css = arr[0]+"_hover";
			self.attr("class", css);
		}else if(arr.length>1){
			self.attr("class", arr[0]);
		}
	});	
</script>
