function negative_content() {
	
	/*$("li").each(function(){
		var self = $(this);
		self.find("span").find("a").click(function(){
			self.find("div.fmgl_piao").show();
		});
	});
	
	$(".fmgl_piao a").click(function(){
		$("div.fmgl_piao").hide();
	});*/
	
	
//	$(".fmgl_piao a.gb").click(function(){
//		$("div.fmgl_piao").hide();
//	});
}

function negative_column(){
	$("input[name^=title]").each(function(){
		var self =$(this);
		if(self.val()==""){
			self.val(self.attr("data-value"));
		}
	}).focus(function(){
		var self =$(this);
		if(self.val()==self.attr("data-value")){
			self.val("");
		}
	}).blur(function(){
		var self =$(this);
		if(self.val()==""){
			self.val(self.attr("data-value"));
		}
	});
	
	$("textarea[name^=keyword]").each(function(){
		var self =$(this);
		if(self.val()==""){
			self.val(self.attr("data-value"));
		}
	}).focus(function(){
		var self =$(this);
		if(self.val()==self.attr("data-value")){
			self.val("");
		}
	}).blur(function(){
		var self =$(this);
		if(self.val()==""){
			self.val(self.attr("data-value"));
		}
	});
	
	$("#okbtn").click(function(){
		var valid = true;
		$("textarea[name^=keyword]").each(function(){
			var self =$(this);
			if(self.val()==self.attr("data-value")){
				self.val("");
			}
			//如果超出5个词，则提醒
			if(self.val().split(",").length>5){
				msgbox("关键词不能超过5组");
				valid = false;
			}
		});
		$("input[name^=title]").each(function(){
			var self =$(this);
			if(self.val()==self.attr("data-value")){
				self.val("");
			}
		});
		if(valid==true){
			$("#myform").submit();
		}
	});
	
	$("#resetbtn").click(function(){
		$("#myform")[0].reset();
	});
	
}

function negative_result(){
	var chart;
	$("a[data-id]").click(function(){
		var self = $(this);
		var id = self.attr("data-id");
		var url = $("#chart").attr("data-url")+"?column.id="+id;
		$.get(url+"&ord="+new Date().getTime(), function(data){
			eval(data);
			var cavas = $("#chart");
			var ctx = cavas.get(0).getContext("2d");
			if(typeof chart!= 'undefined'){
				chart.destroy();
			}
				
			//图的宽度
			var chartwidth = cavas.attr("width")-30;
			var datatotal  = data.labels.length;
			var barwidth   = 30;
			var spacing = (chartwidth - datatotal * barwidth) / (datatotal*2);
			if(spacing<0){
				spacing = 5;
			}
			
			chart = new Chart(ctx).Line(data,{datasetFill:false, bezierCurve:false, pointDotRadius:2});	
		});	
	});
	$("a[data-id]:last").trigger("click");	
	
	//全选复选框
	$("#checkall").click(function(){
		var self = $(this);
		var checked = self.attr("checked");
		if(checked!=undefined){
			$("input[name=columnid]").attr("checked", checked);
		}else{
			$("input[name=columnid]").attr("checked", false);
		}
		
	});
	
	//选择批量处理按钮
	$("#handlebtn").click(function(){
		var arr = "";
		$("input[name=columnid]").each(function(){
			var self = $(this);
			if(self.is(":checked")){
				arr += self.val()+",";
			}
		});
		if(arr.length==0){
			msgbox("请选择一个或多个复选框后再进行操作");
			return false;
		}else{
			arr = arr.substring(0,arr.length-1);
		}
		
		$.post("../negative/handle.shtml",{
					"ids":arr,
					"ord":new Date().getTime()
				},function(data){
					if(data.trim()=="SUCCESS"){
						window.location.href=window.location.href;
					}else{
						msgbox("更新状态失败");
					}
				}
		);
	});
}