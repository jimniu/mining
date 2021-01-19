$(document).ready(function($){
	$("#menubtn").click(function(){
		var menu = $(".list_left");
		if(menu.is(":hidden")){
			menu.show();
		}else{
			menu.hide();
		}
	});
});