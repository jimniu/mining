//analyse.jsp
function mark_analyse(){
	
	function GetQueryString(name) 
	{ 
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); 
	var r = window.location.search.substr(1).match(reg); 
	if (r!=null) return unescape(r[2]); return null; 
	}  
	
	$(document).ready(function(){
		var id = GetQueryString("id");
		$("select").find("option[value='"+id+"']").attr("selected",true);
		var time = GetQueryString("time");
		$(".hywj_one_right").find("a[pos='"+time+"']").addClass("hywj_one_right_vtd");
	});
	
	$("#choose").click(function(){
		var id = $("select").val();
		if(id==0){
			window.location.href="analyse.shtml";
		}else{
			window.location.href="analyse.shtml?type=date&time=7&id="+id;
		}
	});
	
}

// list.jsp
function mark_list(){
	
	var obj = $("select[data-value]");
	obj.val(obj.attr("data-value"));
	
	$("#datepicker").datepicker({ dateFormat: 'yy-mm-dd' });  
	$("#datepicker1").datepicker({ dateFormat: 'yy-mm-dd' });  		 
	
	$("#selectAll").click(function(){
		var checked = $(this).attr("checked");
		if(checked=="checked"){
		  $('input[name="contentid"]').each(function(){   			
				$(this).attr("checked","checked");
			  });    
		}else{
			$('input[name="contentid"]').each(function(){   			
				$(this).attr("checked",null);
			  }); 		
		}
	});
	
	$("#delete").click(function(){
		var flag = confirm("确定删除吗?", function(){
			var s='';
			$('input[name="contentid"]:checked').each(function(){
				s+=$(this).val()+',';
			});
			if(s!=null){
				$.post("../mark/delete.shtml", {
					"kw" : s,
					"ord" : new Date().getTime()
				}, function(data){
					if(data.length>0){
						msgbox(data);
						setTimeout(function(){
							window.location.href="../mark/list.shtml";
						}, 2000);
					}
				});
			}
		});
	});

	//收藏内容
	$("a[data-favorite]").click(function(){
		var self = $(this);
		var id = self.attr("data-favorite");

		var url = "../recommend/addfavorite.shtml?favorite.module=zmt&favorite.pageid="+id+"&ord="+new Date().getTime();
		$.get(url, function(data){
			if(data=="true"){
				msgbox("成功加入收藏！");
			}else{
				msgbox("文章之前已经收藏！");
			}
		});
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
	
	$("#search").click(function(){
		var key = $("#kw").val().trim();;
		 $("#kw").val(key);
		$("#searchform").submit();
	});
	
	var flag = true;
	$("#wantMark").click(function() {
		if(flag){
			flag = false;
			$(".sjzq_sjgl_piao").css("display","");
		}else{
			flag = true;
			$(".sjzq_sjgl_piao").css("display","none");
		}		
	});
	
	$(".gb_bt a").click(function() {
		flag = true;
		$(".sjzq_sjgl_piao").css("display","none");
	});

	$('input[name = address]').bind( {
		focus : function() {
			if (this.value == "输入网址") {
				this.value = "";
			}
		},
		blur : function() {
			if (this.value == "") {
				this.value ="输入网址";
			}
		}
	}); 
	
	$(".tj_bt a").click(function(){
		if($('input[name = address]').val() != "输入网址"){
			$("#addform").submit();
		}		
	});
	
	$("ul a[name=sc]").each(function(){
		$(this).click(function(){
			var pos = $(this).attr("pos");
			 confirm("确定删除吗?",function(){
				 $.post("../mark/deleteID.shtml", {
					 "id" : pos,
					 "ord" : new Date().getTime()
				 }, function(data){
					 window.location.href="../mark/list.shtml";
				 });
			 });
		});
	});
	
}

//userinfo.jsp
function mark_detail(){
	
	$("#contentarea").xheditor({tools:'mini'});
	
	var tag = $("#tag").val();
	if(tag.length>0){
		var scope = tag.split(",");
		for(var i= 1;i<scope.length;i++){
			$("#biaoqian").find("input[value='"+scope[i]+"']").attr("checked",true);
		}
	}
	
	$("#goback").click(function(){
		history.back(-1);
	});	
	
	$("#save").click(function(){
		var s=','; 
		$('input[name="tagid"]:checked').each(function(){   
			  s+=$(this).val()+',';	
		});
		
		if ($("#title").val().length>30){
			msgbox("标题最多30个汉字的长度");
		}else if(s.length<2){
			msgbox("请选择标签");
		}else if ($("#comment").val().length>120){
			msgbox("点评最多120个汉字的长度");
		}else{
			$("#tag").val(s);
			$("#saveform").submit();
		}	
	});

}

function mark_page(){
	var flag = true;
	$("#wantMark").click(function() {
		if(flag){
			flag = false;
			$(".sjzq_sjgl_piao").css("display","");
		}else{
			flag = true;
			$(".sjzq_sjgl_piao").css("display","none");
		}		
	});
	
	$(".gb_bt a").click(function() {
		flag = true;
		$(".sjzq_sjgl_piao").css("display","none");
	});

	$('input[name = address]').bind( {
		focus : function() {
			if (this.value == "输入网址") {
				this.value = "";
			}
		},
		blur : function() {
			if (this.value == "") {
				this.value ="输入网址";
			}
		}
	}); 
	
	$(".tj_bt a").click(function(){
		if($('input[name = address]').val() != "输入网址"){
			$("#addform").submit();
		}		
	});

	$("img").each(function(){
		var self = $(this);

		// 创建对象
        var img = new Image();

		// 改变图片的src
        img.src = self.attr("src");

		// 判断是否有缓存
        if(img.complete){
        	if(img.width>800){
                self.attr("width", "100%");
			}
        }else{
            // 加载完成执行
            img.onload = function(){
                if(img.width>800){
                    self.attr("width", "100%");
                }
            };
        }
		//
	});

}

//index.jsp
function mark_index(){
	 
	var flag = true;	
	$("#selectAll").click(function(){
		if(flag){
		  $('input[name="tagid"]').each(function(){   			
				$(this).attr("checked","checked");
			  });    
		  flag = false;
		}else{
			$('input[name="tagid"]').each(function(){   			
				$(this).attr("checked",null);
			  });  
		 flag = true;			
		}
	});
		
	$("ul a[name=sc]").each(function(){
		$(this).click(function(){
			var p = $(this).attr("p");
			var pos = $(this).attr("pos");
			 if(p<2){
				 confirm("确定删除吗?", function(){
					 $.post("../mark/deleteTagID.shtml", {
						 "tag.id" : pos,
						 "ord" : new Date().getTime()
					 }, function(data){
						 window.location.href="../mark/index.shtml";
					 });
				 });
			 } else {
				 msgbox("不能删除非空标签");
			 }
		});	
	});
	
	$("#delete").click(function(){
		confirm("确定删除吗?", function(){
			var  b = true;
			var s='';
			$('input[name="tagid"]:checked').each(function(){
				if($(this).attr("pos")>0){
					msgbox("不能删除非空标签");
					b = false;
				} else {
					s+=$(this).val()+',';
				}
			});
			if(s!=null&&b){
				$.post("../mark/deleteTag.shtml", {
					"tagname" : s,
					"ord" : new Date().getTime()
				}, function(data){
					if(data.length>0){
						msgbox(data);
						setTimeout(function(){
							window.location.href="../mark/index.shtml";
						}, 2000);
					}
				});
			}
		});
	});

	var total = $("#total").val();
	var flag1 = true;
	$("#addtag").click(function() {
		if(total<20){
			if(flag1){
				flag1 = false;
				$("#ceng1").css("display","");
			}else{
				flag1 = true;
				$("#ceng1").css("display","none");
			}		
		} else {
			msgbox("您只能创建最多20个标签");
		}
	});
	
	$("ul a[name=xg]").each(function(){
		$(this).click(function(){
			var pos = $(this).attr("pos");
			$("ul .sjzq_sjgl_piao_bt").css("display","none");
			$("ul .sjzq_sjgl_piao_bt[pos = "+pos+"]").css("display","block");		
		});	
	});

	$("ul .sjzq_sjgl_piao_bt .gb_bt a").click(function() {
		$("ul .sjzq_sjgl_piao_bt").css("display","none");
	});
	
	$("ul .sjzq_sjgl_piao_bt input").focus(function() {
		if (this.value == "修改名称") {
			this.value = "";
		}
	}).blur(function(){
		if (this.value == "") {
			this.value ="修改名称";
		}
	});
	

	$("ul .sjzq_sjgl_piao_bt .tj_bt a").each(function(){
			$(this).click(function(){				
				var pos = $(this).attr("pos");
				var name = $("ul .sjzq_sjgl_piao_bt input[pos="+pos+"]").val();
				if(name != "修改名称" && name.trim() != "" ){
					if(name.length>10){
						msgbox("标签限制在10字内");
					}else{
						  $.post("../mark/update.shtml", {
							  "tag.id" : pos,
							  "tag.value" : name,
							  "ord" : new Date().getTime()
						  }, function(data){
							  window.location.href="../mark/index.shtml";						
						  });
					}
				}			
			});		
	});
	
	$("#ceng1 .gb_bt a").click(function() {
		flag1 = true;
		$("#ceng1").css("display","none");
	});
	
	$('#ceng1 input').bind( {
		focus : function() {
			if (this.value == "输入名称") {
				this.value = "";
			}
		},
		blur : function() {
			if (this.value == "") {
				this.value ="输入名称";
			}
		}
	}); 
	
	$("#ceng1 .tj_bt a").click(function(){
		var name = $('#ceng1 input').val();
		if(name != "输入名称" && name.trim() != "" ){
			if(name.length>10){
				msgbox("标签限制在10字内");
			}else{
				$("#tagform").submit();
			}
		}	
	});
	
}

function mark_post(){

	$("#contentarea").xheditor({tools:'mini'});

	var tag = $("#tag").val();
	if(tag.length>0){
		var scope = tag.split(",");
		for(var i= 1;i<scope.length;i++){
			$("#biaoqian").find("input[value='"+scope[i]+"']").attr("checked",true);
		}
	}

	$("#goback").click(function(){
		history.back(-1);
	});

	$("#save").click(function(){
		var s=',';
		$('input[name="tagid"]:checked').each(function(){
			s+=$(this).val()+',';
		});

		if ($("#title").val().length>30){
			msgbox("标题最多30个汉字的长度");
		}else if(s.length<2){
			msgbox("请选择标签");
		}else{
			$("#tag").val(s);
			$("#saveform").submit();
		}
	});

}


