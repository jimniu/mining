// JavaScript Document
$(document).ready(function($){
	$(".myyx_tfmt a").eq(0).css("background-image","url(images/wj_hover.png)")
	$(".myyx_tfmt a").eq(0).click(function(){
		$(".myyx_img").show();
		$(".myyx_scwj").hide();
		$(this).css("background-image","url(images/wj_hover.png)");
		$(".myyx_tfmt a").eq(1).css("background-image","url(images/wj_link.png)");
		})
	$(".myyx_tfmt a").eq(1).click(function(){
		$(".myyx_scwj").show();
		$(".myyx_img").hide();
		$(this).css("background-image","url(images/wj_hover.png)");
		$(".myyx_tfmt a").eq(0).css("background-image","url(images/wj_link.png)");
		})
	
})