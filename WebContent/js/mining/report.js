function report_index(){
	$("#generatebtn").click(function(){
		$("#submitbtn").trigger("click");
	});
}

function report_exp(){
	$(".keep").click(function(){
		$(this).closest("form").find("[type=submit]").trigger("click");
	});
	
	$("input[name=type]").click(function(){
		var self = $(this);
		var val = self.val();
		if(val!=0){
			$("input[name=relation][value=0]").attr("checked", "checked");
			$("#type1").hide("middle");
			$("#type2").hide("middle");
		}else{
			$("#type1").show("middle");
			$("#type2").show("middle");
		}
	});

	//选择微信和新闻模块时，显示栏目信息
	$("select[name=module]").change(function(){
		var self = $(this);
		var val = self.val();

		var option = $("#default").html();
		if(val=="xw"||val=="wx"){
			var option = $("#"+val+"list").html();
		}
		$("select[name=columnid]").html(option);
	});


}

function report_detail(){
	$("div[data-url]").each(function(){
		var self = $(this);
		var url = self.attr("data-url");
		
		$.get(url, function(data){
			self.html(data);
		});
	});
}

function report_export(){
	msgbox("生成集成报表需要点时间哦～<br/>请耐心等候，生成完毕后将自动在本窗口打开！",
			function(){
				$("#working").show();
				var url = $("#loading").attr("data-url");
				$.post("../report/generate.shtml",{
					"url":url,
					"ord":new Date().getTime()
				}, function(data){
					window.location.href=".."+data;
				});
			});
}