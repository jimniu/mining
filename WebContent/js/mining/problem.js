//index.jsp
function problem_manage_index(){	
	
	$("#selectAll").click(function(){
		var checked = $(this).attr("checked");
		if(checked=="checked"){
		  $('input[name="problemid"]').each(function(){   			
				$(this).attr("checked","checked");
			  });    
		}else{
			$('input[name="problemid"]').each(function(){   			
				$(this).attr("checked",null);
			  });    
		}
	});
 
	//分页
	pageselectCallback = function(page_index, jq){ 
		return true; 
	}; 
	
	var total = $(".fy_div").attr("data-total");
	var pagesize = $(".fy_div").attr("data-pagesize");
	var pageindex = $(".fy_div").attr("data-pageindex");
	var url = $(".fy_div").attr("data-urlbase");
	$(".fy_div").pagination(total, { 
		items_per_page:pagesize,
		num_display_entries:6,
		current_page:pageindex,
		prev_show_always:false,
		next_text:"下一页",
		prev_text:"上一页",
		callback: pageselectCallback, 
		link_to:url+"&pageindex=__id__",
		num_edge_entries: 0
	}); 
	
	$("#goto").click(function(){
		if(!isNaN($('#page').val())){
		var page = $('#page').val()-1;
		var url = $(".fy_div").attr("data-urlbase");
		if(page<0){
			page = 1;
		}
		var gourl = url+"&pageindex="+page;
		
		self.location.href= gourl;
		}
	});
	
	$("#search").click(function(){
		$("#form").submit();
	});
	
	$("#delete").click(function(){
		  var s='';      
		  $('input[name="problemid"]:checked').each(function(){   			
			 s+=$(this).val()+',';
		  });    
		  if(s!=null){
			  $.post("../problem/manage_delete.shtml", {
				  "scope" : s,
				  "ord" : new Date().getTime()
			  }, function(data){
				  if(data.length>0){
					  msgbox(data);
					  setTimeout(function(){
						  window.location.href="../problem/manage_index.shtml";	
					  }, 2000);		 
				  }			
			  });
		  }
	});
}


//add.jsp
function problem_manage_add() {
	
	$("#submit").click(function() {	 

		var title = $("input[name$=title]").val();
		var content = $("#content").val();	
		var weight = $("input[name$=weight]").val();	
		
		if (title.length == 0) {
			msgbox("请输入标题");
		} else if (content.length == 0) {
			msgbox("请输入内容");
		} else if (isNaN(weight)) {
			msgbox("权重必须为数字");
		} else {		
			$(".zc_cgceng").css("display", "block");
			setTimeout(function(){
				$("#form").submit();
			}, 2000);
		}
	});
}


//update.jsp
function problem_manage_info() {

	$("#submit").click(function() {	
		var title = $("input[name$=title]").val();
		var content = $("#content").val();	
		var weight = $("input[name$=weight]").val();	
		
		if (title.length == 0) {
			msgbox("请输入标题");
		} else if (content.length == 0) {
			msgbox("请输入内容");
		} else if (isNaN(weight)) {
			msgbox("权重必须为数字");
		} else {
			$(".zc_cgceng").css("display", "block");
			setTimeout(function(){
				$("#form").submit();
			}, 2000);
		}
	});
}