//index.jsp
function message_manage_index(){	
	
	$("#selectAll").click(function(){
		var checked = $(this).attr("checked");
		if(checked=="checked"){
		  $('input[name="messageid"]').each(function(){   			
				$(this).attr("checked","checked");
			  });    
		}else{
			$('input[name="messageid"]').each(function(){   			
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
		  $('input[name="messageid"]:checked').each(function(){   			
			 s+=$(this).val()+',';
		  });    
		  if(s!=null){
			  $.post("../message/manage_delete.shtml", {
				  "message.scope" : s,
				  "ord" : new Date().getTime()
			  }, function(data){
				  if(data.length>0){
					  msgbox(data);
					  setTimeout(function(){
						  window.location.href="../message/manage_index.shtml";	
					  }, 2000);		 
				  }			
			  });
		  }
	});
}


//add.jsp
function message_manage_add() {

	$(".inp_rad input").click(function(){
		var pos = $(this).val();
		if(pos == 1){
			$("#userceng").show();
		} else {
			$('#userceng input[name="userid"]').each(function(){   			
				$(this).attr("checked","checked");
			  });    
			total();
		}
	});
	
	$("#userceng .close_bt").click(function(){
		$("#userceng").hide();
	});
	
	function total(){
		var s='';    
		var sc=','; 
		$('#userceng input[name="userid"]:checked').each(function(){   			
			s+=$(this).attr("data-value")+',';
			sc+=$(this).val()+',';
		});    
		if(s.length>0){
			s = s.substring(0,s.length-1);
		}
		$("#scope").val(sc);
		$("#scopetemp").val(s);
		$("#userceng").hide();
	}
	
	$("#userceng .submit_bt").click(function(){   
		total();
	});
	
	$("#submit").click(function() {	 
		
		var scope =  $("#scope").val();
		var title = $("input[name$=title]").val();
		var content = $("#content").val();	
		var weight = $("input[name$=weight]").val();	
		
		if (title.length == 0) {
			msgbox("请输入标题");
		} else if (content.length == 0) {
			msgbox("请输入内容");
		} else if (isNaN(weight)) {
			msgbox("权重必须为数字");
		} else if(scope.length<2){
			msgbox("请选择对象");
		} else {		
			$(".zc_cgceng").css("display", "block");
			setTimeout(function(){
				$("#form").submit();
			}, 2000);
		}
	});
}


//update.jsp
function message_manage_info() {

	var temp = $("#scope").val();
	var scope = temp.split(",");
	for(var i=1;i<scope.length;i++){
		$("#userceng input[value='"+scope[i]+"']").attr("checked",true);
	}
	total();
	 
	$(".inp_rad input").click(function(){
		var pos = $(this).val();
		if(pos == 1){
			$("#userceng").show();
		} else {
			$('#userceng input[name="userid"]').each(function(){   			
				$(this).attr("checked","checked");
			  });    
			total();
		}
	});
	
	$("#userceng .close_bt").click(function(){
		$("#userceng").hide();
	});
	
	function total(){
		var s='';    
		var sc=','; 
		$('#userceng input[name="userid"]:checked').each(function(){   			
			s+=$(this).attr("data-value")+',';
			sc+=$(this).val()+',';
		});    
		if(s.length>0){
			s = s.substring(0,s.length-1);
		}
		$("#scope").val(sc);
		$("#scopetemp").val(s);
		$("#userceng").hide();
	}
	
	$("#userceng .submit_bt").click(function(){   
		total();
	});
	
	$("#submit").click(function() {	
		
		var scope =  $("#scope").val();
		var title = $("input[name$=title]").val();
		var content = $("#content").val();	
		var weight = $("input[name$=weight]").val();	
		
		if (title.length == 0) {
			msgbox("请输入标题");
		} else if (content.length == 0) {
			msgbox("请输入内容");
		} else if (isNaN(weight)) {
			msgbox("权重必须为数字");
		}  else if(scope.length<2){
			msgbox("请选择对象");
		} else {
			$(".zc_cgceng").css("display", "block");
			setTimeout(function(){
				$("#form").submit();
			}, 2000);
		}
	});
}


function message_data(){

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

function message_extend(){

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