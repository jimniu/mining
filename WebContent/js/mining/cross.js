function cross_finance(){
	
	$("#jrkjsj_box_2 .jrkjsj01").hide();
	$("#jrkjsj_box_2 .jrkjsj01:eq(0)").show();
	$(".jrkjsj_baoguang a").click(function(){
		$(this).addClass("jrkjsj_baoguang_current");
		$(this).siblings().removeClass("jrkjsj_baoguang_current");
		var $jrkjsj = $( this).index();
		$("#jrkjsj_box_2 .jrkjsj01").eq($jrkjsj).show();
		$("#jrkjsj_box_2 .jrkjsj01").eq($jrkjsj).siblings().hide();
		
		})
}


