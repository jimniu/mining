function event_add(){
	//提交按钮
	$("#btnSubmit").click(function(){
		$("input[data-value]").each(function(){
			var name = $(this).attr("name");
			var origin = $(this).attr("data-value");
			var value  = $(this).attr("value").trim();
			if(value==origin||value==""){
				$(this).val("");
			}else{
				//替换输入内容中的空格、中文逗号和中文空格
				value = value.replaceAll("\\s+", ",");
				value = value.replaceAll("，", ",");
				value = value.replaceAll("　", ",");
				$(this).attr("value", value);
			}
		});
		
		if($("input[name=column\\.keyword]").val()!=""){
			var keyword = $("input[name=column\\.keyword]").val();
			
			var origin = keyword.replaceAll("，",",").split(",");
			var target = new Array();
			for(var i=0; i<origin.length; i++){
				var item = origin[i].trim();
				if(item.length!=0){
					target.push(item);
				}
			}
			$("input[name=column\\.keyword]").val(target.join(","));
			
			keyword = $("input[name=column\\.keyword]").val();
			

			//如果是关键词搜索，超出设定的个数，则提醒
			if(keyword.split(",").length>$("[data-cap]").attr("data-cap")){
				msgbox("关键词不能超过"+$("[data-cap]").attr("data-cap")+"组");
				return;
			}
		}else{
			msgbox("请输入关键词");
			return;
		}
		
		$("#submit").trigger("click");
	});
	
	 //检查关键词总数是否超出限制
    $.get("../network/keyword.shtml?ord="+new Date().getTime(), function(data){
    	var total = $("[data-total]").attr("data-total");
    	if(parseInt(data)>parseInt(total)){
    		msgbox("帐户关键词总数为"+data+"个，超出规定的个数:"+total);
    		$("a.keep").hide();
    	}
    });
	
	$("input[data-value]").each(function(){
		var self =$(this);

		if(self.val()==""){
			self.val(self.attr("data-value"));
		}
	}).focus(function(){
		var self =$(this);
		self.addClass("click");
		
		if(self.attr("data-status")<=0){
			msgbox("关键词的修改已经达到上限，无法继续修改。");
		}else if(self.val()==self.attr("data-value")){
			self.val("");
		}
	}).blur(function(){
		var self =$(this);
		self.removeClass("click");
		if(self.val()==""){
			self.val(self.attr("data-value"));
		}
	});
	
	//事件时间选择
	$("input[name*=occurred]").datepicker({ dateFormat: 'yy-mm-dd' }); 
	
	//省市联动
	$("#province").empty();
	for(var i=0; i<PROVINCES.length; i++){
		$("#province").append("<option value='"+PROVINCES[i].value+"'>"+PROVINCES[i].name+"</option>");
	}
	
	$("#province").change(function(){
		var value = $(this).val()-1;
		$("#city").empty();
		$("#city").append("<option value=''>市/区</option>");
		if(value>=0){
			$("input[name=column\\.province]").val(PROVINCES[value+1].name);		
			for(var i=0; i<LOCATION[value].length; i++){
				$("#city").append("<option value='"+LOCATION[value][i]+"'>"+LOCATION[value][i]+"</option>");
			}
		}else{
			$("input[name=task\\.province]").val("");
		}		
	});
}

function event_edit(){
	//提交按钮
	$("#btnSubmit").click(function(){
		$("#submit").trigger("click");
	});
	
	//事件时间选择
	$("input[name*=occurred]").datepicker({ dateFormat: 'yy-mm-dd' }); 
	
	//省市联动
	$("#province").empty();
	for(var i=0; i<PROVINCES.length; i++){
		$("#province").append("<option value='"+PROVINCES[i].value+"'>"+PROVINCES[i].name+"</option>");
	}
	
	$("#province").change(function(){
		var value = $(this).val()-1;
		$("#city").empty();
		$("#city").append("<option value=''>市/区</option>");
		if(value>=0){
			$("input[name=column\\.province]").val(PROVINCES[value+1].name);		
			for(var i=0; i<LOCATION[value].length; i++){
				$("#city").append("<option value='"+LOCATION[value][i]+"'>"+LOCATION[value][i]+"</option>");
			}
		}else{
			$("input[name=task\\.province]").val("");
		}		
	});
	
	//回填省市选项
	var prov = $("#province").attr("data-value");
	for(var i=0; i<PROVINCES.length; i++){
		if(PROVINCES[i].name==prov){
			$("#province").val(PROVINCES[i].value);	
			$("#province").trigger("change");
		}
	}
	var city = $("#city").attr("data-value");
	$("#city").val(city);
}

function event_list(){
	//搜索月份
	$("#month").datepicker({ dateFormat: 'yy-mm' }); 
	
	//点击搜索按钮
	$("#searchbtn").click(function(){
		$("#submitbtn").trigger("click");
	});
}

function event_detail(){
	//传播区域图表
	var trendchart = function(){
		var type  = $("#trenddiv select").val();
		var columnid = $("#trenddiv select").attr("data-id");
		var days = $("#mapdiv select[name=days]").val();
		$.get("../event/trendchart.shtml?column.id="+columnid+"&column.title="+type+"&ord="+new Date().getTime(),function(data){
			eval(data);
		});
	};
	trendchart();
	$("#trenddiv select").change(function(){
		trendchart();
	});
}