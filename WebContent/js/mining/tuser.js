//index.jsp
function tuser_manage_index(){
 
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
		link_to:url+"?pageindex=__id__",
		num_edge_entries: 0
	}); 
	
	$("#goto").click(function(){
		if(!isNaN($('#page').val())){
		var page = $('#page').val()-1;
		var url = $(".fy_div").attr("data-urlbase");
		if(page<0){
			page = 1;
		}
		var gourl = url+"?pageindex="+page;
		
		self.location.href= gourl;
		}
	});
	
	$("#search").click(function(){
		$("#form").submit();
	});
	
	$("#selectAll").click(function(){
		var checked = $(this).attr("checked");
		if(checked=="checked"){
		  $('input[name="tuserid"]').each(function(){   			
				$(this).attr("checked","checked");
			  });    
		}else{
			$('input[name="tuserid"]').each(function(){   			
				$(this).attr("checked",null);
			  });    
		}
	});
	
	$("#delete").click(function(){
		  var s='';      
		  $('input[name="tuserid"]:checked').each(function(){   			
			 s+=$(this).val()+',';
		  });    
		  if(s!=null){
			  $.post("../tuser/manage_delete.shtml", {
				  "temp" : s,
				  "ord" : new Date().getTime()
			  }, function(data){
				  if(data.length>0){
					  msgbox(data);
					  setTimeout(function(){
						  window.location.href="../tuser/manage_index.shtml";	
					  }, 2000);		 
				  }			
			  });
		  }
	});
}
