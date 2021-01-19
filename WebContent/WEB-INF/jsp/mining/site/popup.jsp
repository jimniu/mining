<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!--弹出窗口-->
<div class="t">
	<div class="t_top">
		<img src="../images/t_top.png" />
	</div>
	<div class="t_center">
		<div class="t_bt">
			<a href="javascript:;"><img src="../images/t_bt.png" /></a>
		</div>
		<div class="t_link">
			<a href="javascript:;" class="t_link_vtd" data-url="../site/focus.shtml?relation=0" data-type="0">今日焦点</a>
			<a href="javascript:;" data-url="../site/focus.shtml?relation=0" data-type="2">负面焦点</a>
		</div>
		<div class="t_fl">
			<span>TOP50</span>
			<span><a href="javascript:;" data-module="wb" data-value="<s:property value="module"/>">微博</a> </span>
			<span><a href="javascript:;" data-module="wx">微信</a> </span>
			<span><a href="javascript:;" class="fl_vtd" data-module="xw">媒体</a> </span>
			<span><a href="javascript:;" data-module="lt">论坛</a> </span>
			<span>
			<select name="days" data-value="<s:property value="days"/>">
				<option value="0"  selected>当天</option>
				<option value="1">最近24小时</option>
				<option value="7">最近一周</option>
				<option value="30">最近一个月</option>
				<option value="90">最近三个月</option>
			</select>
			<select name="columnid" id="columnlist">
				<option value="0">所有</option>
			</select>
			</span>
		</div>
		<div class="t_list">
		</div>
	</div>
	<div class="t_bottom">
		<img src="../images/t_bottom.png" />
	</div>
</div>

