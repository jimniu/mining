function weibo_column(){
    //渠道用户禁止修改
    if(USERTYPE==0){
        $("input").attr("readonly", "readonly");
        $("textarea").attr("readonly", "readonly");
        return;
    }

    //检查关键词总数是否超出限制
    var columnid = $("input[name=column\\.id]").val();
    if(columnid.length==0){
        $.get("../network/keyword.shtml?ord="+new Date().getTime(), function(data){
        	var total = $("[data-total]").attr("data-total");
        	if(parseInt(data)>parseInt(total)){
        		msgbox("帐户关键词总数为"+data+"个，超出规定的个数:"+total);
        		$("a.keep").hide();
        	}
        });
    }
    
	//任务添加窗口的事件
	$("input[data-value]").each(function(){
		var self =$(this);

		if(self.val()==""){
			self.val(self.attr("data-value"));
		}
	}).focus(function(){
		var self =$(this);
		self.addClass("click");
		
		if(self.attr("data-status")<0){
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
	
	//保存任务
	$("a.keep").click(function(){
		//是否输入了查询条件
		var used = 0;
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
				if(name.indexOf("column.name")==-1){
					used += 1;
				}
			}
		});

		
		if($("input[name=column\\.name]").val()==""){
			msgbox("请输入任务名称！");
			//恢复输入框的原始内容
			$("input[data-value]").each(function(){
				var origin = $(this).attr("data-value").trim();
				var value  = $(this).attr("value");
				if(value==""){
					$(this).attr("value", origin);
				}
			});			
			return;
		}
		
		if($("input[name=column\\.searchword]").val()!=""){
			//抓取任务类型
			var task = $("select[name=column\\.task]").val();
			
			var keyword = $("input[name=column\\.searchword]").val();
			
			var origin = keyword.replaceAll("，",",").split(",");
			var target = new Array();
			for(var i=0; i<origin.length; i++){
				var item = origin[i].trim();
				if(item.length!=0){
					//如果抓取的是博主，则对博主链接掐头去尾
					if(task==1){
						item = item.split("?")[0];
						item = item.replace("http://weibo.com","");
					}
					target.push(item);
				}
			}
			$("input[name=column\\.searchword]").val(target.join(","));
			
			keyword = $("input[name=column\\.searchword]").val();
			

			//如果是关键词搜索，超出设定的个数，则提醒
			if(keyword.split(",").length>$("[data-cap]").attr("data-cap")){
				msgbox("关键词不能超过"+$("[data-cap]").attr("data-cap")+"组");
				return;
			}
		}else{
			msgbox("请输入关键词");
			return;
		}
		
		if(used<1){
			msgbox("请输入至少一个查询条件！");
			//恢复输入框的原始内容
			$("input[data-value]").each(function(){
				var origin = $(this).attr("data-value").trim();
				var value  = $(this).attr("value");
				if(value==""){
					$(this).attr("value", origin);
				}
			});
		}else{
			$("#submitbtn").trigger("click");
		}
	});
	
	
	//将用户选择的内容回填到相应的位置
	$("select[data-value]").each(function(){
		$(this).val($(this).attr("data-value"));
	});

	
}

function weibo_user(){
	//点击筛选按钮
	$("#filterBtn").click(function(){
		$("#searchBtn").trigger("click");
	});
	
	//鼠标mouseover时显示详细信息
	$("td[data-wbid]").hover(function(){
		var self = $(this);
		var wbid = self.attr("data-wbid");
		$.post("../weibo/detail.shtml",
			{
				"weibo.wbid" : wbid,
				"ord" : new Date().getTime()
			},function(data){
				$(".wbwj_title_position").hide();
				$(".wbwj_title_position[pos="+wbid+"]").html(data);
				$(".wbwj_title_position[pos="+wbid+"]").show();
			});
	},function(){
		$(".wbwj_title_position").hide();
	});
	
	//点击任意地方，隐藏用户介绍
	$("body").click(function(){
		$(".wbwj_title_position").hide();
	});
	
	//全选复选框
	$("#checkall").click(function(){
		var self = $(this);
		var checked = self.attr("checked");
		if(checked!=undefined){
			$("input[name=wbid]").attr("checked", checked);
		}else{
			$("input[name=wbid]").attr("checked", false);
		}
		
	});
	
	//选择批量删除按钮
	$("#deluser").click(function(){
		var arr = "";
		$("input[name=wbid]").each(function(){
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
		
		var columnid = $("select[name=column\\.id]").val();
		$.post("../weibo/userdelete.shtml",{
					"column.id" : columnid,
					"ids":arr,
					"ord":new Date().getTime()
				},function(data){
					if(data.trim()=="SUCCESS"){
						window.location.href=window.location.href;
					}else{
						msgbox("删除结果失败");
					}
				}
		);
	});
	
	//选择删除图标
	$("a[data-delete]").click(function(){
		var self = $(this);
		var url = self.attr("data-delete");

		confirm("您要删除该用户吗？", function(){
			$.post(url,{
					"ord":new Date().getTime()
				},function(data){
					if(data.trim()=="SUCCESS"){
						self.parents("tr").hide();
					}else{
						msgbox("删除结果失败");
					}
				}
			);
		});
	});
	
	//分页
	pageselectCallback = function(page_index, jq){ 
		return true; 
	}; 
	
	var total = $(".pagination").attr("data-total");
	var pagesize = $(".pagination").attr("data-pagesize");
	var pageindex = $(".pagination").attr("data-pageindex");
	var url = $(".pagination").attr("data-urlbase");
	$(".pagination").pagination(total, { 
		items_per_page:pagesize,
		num_display_entries:10,
		current_page:pageindex,
		next_text:"下一页",
		prev_text:"上一页",
    	callback: pageselectCallback, 
    	link_to:url+"&pageindex=__id__",
     	num_edge_entries: 1
    }); 
	
	//回填选定的条件
	$("input[data-value]").each(function(){
		var self = $(this);
		var type = self.attr("type");
		var value = self.attr("data-value");
		var name = self.attr("name");
		if(value==""){
			return;
		}
		if(type=="radio"){
			$("input[name='"+name+"'][value='"+value+"']").attr("checked","true");
		}else if(type="text"){
			$("input[name='"+name+"']").val(value);
		}
	});
	
	//省份选择的事件
	$("#province").change(function(){
		var self = $(this);
		var provid = self.val();
		var url = "../site/citylist.shtml?province.id="+provid;
		$.get(url, function(data){
			$("#city").html(data);
			
			var city = $("#city").attr("data-value"); 
			$("#city").val(city);
		});
	});
	
	//回填省市选项
	$("select[data-value]").each(function(){
		var self = $(this);
		var value = self.attr("data-value");
		self.val(value);
		self.trigger("change");
	});
	
	
	//切换任务
	$("select[name$=column\\.id]").change(function(){
		//$("#searchBtn").trigger("click");
	});
}

//博文管理
function weibo_content(){
	//点击筛选按钮
	$("#filterBtn").click(function(){
		$("#searchBtn").trigger("click");
	});
	
	$("select[data-value]").each(function(){
		var self = $(this);
		var value = self.attr("data-value");
		self.val(value);
	});
	
	//切换任务
	$("select[name$=column\\.id]").change(function(){
		$("#searchBtn").trigger("click");
	});
	
	//删除按钮
	$("a[data-delete]").click(function(){
		var self = $(this);
		var id = self.attr("data-delete");
		
		confirm("确认要删除此微博？", function(){
			var url = "../weibo/feeddelete.shtml?result.id="+id;
			$.get(url, function(data){
				if(data.indexOf("success")>-1){
					self.parents("div.wbwj_sdj_one").hide();
				}
			});
		});
	});
	
	//收藏内容
	$("a[data-favorite]").click(function(){
		var self = $(this);
		var id = self.attr("data-favorite");

		var url = "../recommend/addfavorite.shtml?favorite.module=wb&favorite.pageid="+id+"&ord="+new Date().getTime();
		$.get(url, function(data){
			if(data=="true"){
				msgbox("成功加入收藏！");
			}else{
				msgbox("文章之前已经收藏！");
			}
		});
	});	
	
	//好评内容
	$("a[data-positive]").click(function(){
		var self = $(this);
		var id 	 = self.attr("data-positive");
		var time = self.attr("data-ptime");

		var url = "../opinion/add.shtml?opinion.module=wb&opinion.type=1&opinion.pageid="+id+"&opinion.ptime="+time+"&ord="+new Date().getTime();
		$.get(url, function(data){
			if(data.length>0){
				var img = self.find("img");
				var url = img.attr("src");
				if(data==0){
					url = url.replace("positived.png", "positive.png");
				}else{
					url = url.replace("positive.png", "positived.png");
				}	
				img.attr("src", url);
				
				img = self.parent().parent().find("img[src*=negative]");
				url = img.attr("src");
				url = url.replace("negatived.png", "negative.png");
				img.attr("src", url);
			}
		});
	});
	
	//好评内容
	$("a[data-negative]").click(function(){
		var self = $(this);
		var id = self.attr("data-negative");
		var time= self.attr("data-ptime");

		var url = "../opinion/add.shtml?opinion.module=wb&opinion.type=2&opinion.pageid="+id+"&opinion.ptime="+time+"&ord="+new Date().getTime();
		$.get(url, function(data){
			if(data.length>0){
				var img = self.find("img");
				var url = img.attr("src");
				if(data==0){
					url = url.replace("negatived.png", "negative.png");
				}else{
					url = url.replace("negative.png", "negatived.png");
				}
				img.attr("src", url);
				
				img = self.parent().parent().find("img[src*=positive]");
				url = img.attr("src");
				url = url.replace("positived.png", "positive.png");
				img.attr("src", url);				
			}
		});
	});	
}
function weibo_feed(){
	var keyword = $("div[rel-keyword]").attr("rel-keyword");
	keyword = keyword.replaceAll("\\+", ",");
	var array = keyword.split(",");
	
	$("div.wbwj_sdj_one_text").each(function(){
		var self = $(this);
		var content = self.html();
		for(var i=0; i<array.length; i++){
			if(array[i].length>0){
				content = content.replaceAll(array[i], "<font color='red'><strong>"+array[i]+"</strong></font>");
			}
		}
		self.html(content);
	});
	
	//删除按钮
	$("a[data-delete]").click(function(){
		var self = $(this);
		var id = self.attr("data-delete");
		
		var url = "../weibo/feeddelete.shtml?result.id="+id;
		$.get(url, function(data){
			if(data.indexOf("success")>-1){
				self.parents("div.wbwj_sdj_one").hide();
			}
		});
	});
	
	//分页
	pageselectCallback = function(page_index, jq){ 
		return true; 
	}; 
	
	var total = $(".pagination").attr("data-total");
	var pagesize = $(".pagination").attr("data-pagesize");
	var pageindex = $(".pagination").attr("data-pageindex");
	var url = $(".pagination").attr("data-urlbase");
	$(".pagination").pagination(total, { 
		items_per_page:pagesize,
		num_display_entries:10,
		current_page:pageindex,
		next_text:"下一页",
		prev_text:"上一页",
    	callback: pageselectCallback, 
    	link_to:url+"&pageindex=__id__",
     	num_edge_entries: 1
    }); 
}

function weibo_more(){
	//删除按钮
	$("a[data-delete]").click(function(){
		var self = $(this);
		var id = self.attr("data-delete");
		
		var url = "../weibo/feeddelete.shtml?result.id="+id;
		$.get(url, function(data){
			if(data.indexOf("success")>-1){
				self.parents("li").hide();
			}
		});
	});
	
	//分页
	pageselectCallback = function(page_index, jq){ 
		return true; 
	}; 
	
	var total = $(".pagination").attr("data-total");
	var pagesize = $(".pagination").attr("data-pagesize");
	var pageindex = $(".pagination").attr("data-pageindex");
	var url = $(".pagination").attr("data-urlbase");
	$(".pagination").pagination(total, { 
		items_per_page:pagesize,
		num_display_entries:10,
		current_page:pageindex,
		next_text:"下一页",
		prev_text:"上一页",
    	callback: pageselectCallback, 
    	link_to:url+"&pageindex=__id__",
     	num_edge_entries: 1
    }); 
}

function weibo_customer(){
	//点击搜索按钮
	$("#filterBtn").click(function(){
		$("#searchBtn").trigger("click");
	});
	
	//搜索时间范围
	$(".datepicker").datepicker({ dateFormat: 'yy-mm-dd' }); 
	
	
	//选择批量删除按钮
	$("#deluser").click(function(){
		var arr = "";
		$("input[name=wbid]").each(function(){
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
		
		var columnid = $("select[name=column\\.id]").val();
		$.post("../weibo/customerdelete.shtml",{
					"customer.columnid" : columnid,
					"ids":arr,
					"ord":new Date().getTime()
				},function(data){
					if(data.trim()=="SUCCESS"){
						window.location.href=window.location.href;
					}else{
						msgbox("删除结果失败");
					}
				}
		);
	});
	
	//选择删除图标
	$("a[data-delete]").click(function(){
		var self = $(this);
		var url = self.attr("data-delete");
		
		$.post(url,{
					"ord":new Date().getTime()
				},function(data){
					if(data.trim()=="SUCCESS"){
						self.parents("tr").hide();
					}else{
						msgbox("删除结果失败");
					}
				}
		);
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
		num_display_entries:10,
		current_page:pageindex,
		next_text:"下一页",
		prev_text:"上一页",
    	callback: pageselectCallback, 
    	link_to:url+"&pageindex=__id__",
     	num_edge_entries: 1
    }); 

	//回填选定的条件
	$("[data-value]").each(function(){
		var self = $(this);
		var value = self.attr("data-value");
		self.val(value);
	});
	
	//切换任务
	$("select[name$=column\\.id]").change(function(){
		$("#searchBtn").trigger("click");
	});
}

function weibo_result(){
	//区域图表
	var mapchart = function(){
		var columnid = $("#mapdiv select[name=column]").val();
		var days = $("#mapdiv select[name=days]").val();
		$.get("../weibo/mapchart.shtml?column.id="+columnid+"&days="+days+"&ord="+new Date().getTime(),function(data){
			eval(data);
		});
	};
	//mapchart();
	$("#mapdiv select").change(function(){
		//mapchart();
	});
	
	
	//挖掘图表
	var columnchart = function(){
		var columnid = $("#columndiv select[name=column]").val();
		var days = $("#columndiv select[name=days]").val();
		$.get("../weibo/columnchart.shtml?column.id="+columnid+"&days="+days+"&ord="+new Date().getTime(),function(data){
			eval(data);
		});
	};
	columnchart();
	$("#columndiv select").change(function(){
		columnchart();
	});
	
	
	//存量图表	
	var stockchart = function(){
		var days = $("#stockdiv select[name=days]").val();
		$.get("../weibo/stockchart.shtml?days="+days+"&ord="+new Date().getTime(),function(data){
			 	eval(data);
			}
		);
	};
	stockchart();
	$("#stockdiv select").change(function(){
		stockchart();
	});

}

function weibo_reach(){
    //趋势图表
    var trendchart = function(){
        var columnid = $("#trenddiv select[name=column]").val();
        var days = $("#trenddiv select[name=days]").val();
        $.get("../weibo/trendchart.shtml?column.id="+columnid+"&days="+days+"&ord="+new Date().getTime(),function(data){
            eval(data);
        });
    };
    trendchart();
    $("#trenddiv select").change(function(){
        trendchart();
    });

    //粉丝图表
    var fanschart = function(){
        var columnid = $("#fansdiv select[name=column]").val();
        var days = $("#fansdiv select[name=days]").val();
        $.get("../weibo/fanschart.shtml?column.id="+columnid+"&days="+days+"&ord="+new Date().getTime(),function(data){
            eval(data);
        });
    };
    fanschart();
    $("#fansdiv select").change(function(){
        fanschart();
    });

    //设备图表
    var devicechart = function(){
        var columnid = $("#devicediv select[name=column]").val();
        var days = $("#devicediv select[name=days]").val();
        $.get("../weibo/devicechart.shtml?column.id="+columnid+"&days="+days+"&ord="+new Date().getTime(),function(data){
            eval(data);
        });
    };
    devicechart();
    $("#devicediv select").change(function(){
        devicechart();
    });
}

function weibo_columnlist(){
	//删除内容
	$("a[data-delete]").click(function(){
		var self = $(this);
		var id = self.attr("data-delete");

		confirm("您确定要删除该挖掘任务吗？", function(){
			var url = "../weibo/columndelete.shtml?column.id="+id+"&ord="+new Date().getTime();
			$.get(url, function(data){
				if(data.indexOf("success")>-1){
					self.parents("tr").hide();
				}
			});
		})
	});
}