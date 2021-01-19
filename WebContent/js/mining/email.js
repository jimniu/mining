function email_data(){

	$("#add").click(function(){
		$(".dx_bomb").show();
	});
	
	$(".dx_submit").click(function(){
		$(".dx_bomb").hide();
	});
	
	$("a.close").click(function(){
		$(".dx_bomb").hide();
	});
	
}
