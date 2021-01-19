//今日动态页面
function track_content(){
	$("#searchBtn").click(function(){
		$("#submitBtn").trigger("click");
	});
	
	//处理标题超长的问题
	$("li span.left a").each(function(){
		var self = $(this);
		var max = 100;
		var html = self.html();
		var length = JHshStrLen(html);
		if(length>max){
			var str = substring(html, max);
			self.html(str);
		}
	});
	
	//好评内容
	$("a[data-positive]").click(function(){
		var self = $(this);
		var id = self.attr("data-positive");
		var time= self.attr("data-ptime");

		var url = "../opinion/add.shtml?opinion.module=xw&opinion.type=1&opinion.pageid="+id+"&opinion.ptime="+time+"&ord="+new Date().getTime();
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

		var url = "../opinion/add.shtml?opinion.module=xw&opinion.type=2&opinion.pageid="+id+"&opinion.ptime="+time+"&ord="+new Date().getTime();
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
	
	
	//删除内容
	$("a[data-delete]").click(function(){
		var self = $(this);
		var id = self.attr("data-delete");

		confirm("您确认要删除该条新闻吗？",function(){
			var url = "../track/delete.shtml?result.id="+id+"&ord="+new Date().getTime();
			$.get(url, function(data){
				if(data.indexOf("success")>-1){
					self.parents("li").hide();
				}
			});
		});
	});
	

	//收藏内容
	$("a[data-favorite]").click(function(){
		var self = $(this);
		var id = self.attr("data-favorite");

		var url = "../recommend/addfavorite.shtml?favorite.module=xw&favorite.pageid="+id+"&ord="+new Date().getTime();
		$.get(url, function(data){
			if(data=="true"){
				msgbox("成功加入收藏！");
			}else{
				msgbox("文章之前已经收藏！");
			}
		});
	});
}

function track_more(){
	$("#searchBtn").click(function(){
		$("#submitBtn").trigger("click");
	});
	
	//处理标题超长的问题
	$("li span.left a").each(function(){
		var self = $(this);
		var max = 100;
		var html = self.html();
		var length = JHshStrLen(html);
		if(length>max){
			var str = substring(html, max);
			self.html(str);
		}
	});
	
	//删除内容
	$("a[data-delete]").click(function(){
		var self = $(this);
		var id = self.attr("data-delete");

		confirm("您确认要删除该条新闻吗？",function() {
			var url = "../track/delete.shtml?result.id=" + id + "&ord=" + new Date().getTime();
			$.get(url, function (data) {
				if (data.indexOf("success") > -1) {
					self.parents("li").hide();
				}
			});
		});
	});

	//收藏内容
	$("a[data-favorite]").click(function(){
		var self = $(this);
		var id = self.attr("data-favorite");

		var url = "../recommend/addfavorite.shtml?favorite.module=xw&favorite.pageid="+id+"&ord="+new Date().getTime();
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
		var id = self.attr("data-positive");
		var time= self.attr("data-ptime");

		var url = "../opinion/add.shtml?opinion.module=xw&opinion.type=1&opinion.pageid="+id+"&opinion.ptime="+time+"&ord="+new Date().getTime();
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

		var url = "../opinion/add.shtml?opinion.module=xw&opinion.type=2&opinion.pageid="+id+"&opinion.ptime="+time+"&ord="+new Date().getTime();
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
	
	//分页
	pageselectCallback = function(page_index, jq){ 
		return true; 
	}; 
	
	var total = $(".pagination").attr("data-total");
	var pagesize = $(".pagination").attr("data-pagesize");
	var pageindex = $(".pagination").attr("data-pageindex");
	var url = $(".pagination").attr("data-urlbase");
	if(url.indexOf("?")>-1){
		url += "&pageindex=__id__";
	}else{
		url+= "?pageindex=__id__";
	}
	$(".pagination").pagination(total, { 
		items_per_page:pagesize,
		num_display_entries:10,
		current_page:pageindex,
		next_text:"下一页",
		prev_text:"上一页",
    	callback: pageselectCallback, 
    	link_to:url,
     	num_edge_entries: 1
    });	
}

//搜索结果页面
function track_filter(){
	$("input.datepicker").datepicker({ dateFormat: 'yy-mm-dd' });  
 	
	//搜索功能
	$("#searchBtn").click(function(){
		$("#submitBtn").trigger("click");
	});
	
	
	//处理标题超长的问题
	$("li span.left a").each(function(){
		var self = $(this);
		var max = 100;
		var html = self.html();
		var length = JHshStrLen(html);
		if(length>max){
			var str = substring(html, max);
			self.html(str);
		}
	});
	
	//删除内容
	$("a[data-delete]").click(function(){
		var self = $(this);
		var id = self.attr("data-delete");

		confirm("您确认要删除该条新闻吗？",function() {
			var url = "../track/delete.shtml?result.id=" + id + "&ord=" + new Date().getTime();
			$.get(url, function (data) {
				if (data.indexOf("success") > -1) {
					self.parents("li").hide();
				}
			});
		});
	});

    //收藏内容
    $("a[data-favorite]").click(function(){
        var self = $(this);
        var id = self.attr("data-favorite");

        var url = "../recommend/addfavorite.shtml?favorite.module=xw&favorite.pageid="+id+"&ord="+new Date().getTime();
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
		var id = self.attr("data-positive");
		var time= self.attr("data-ptime");

		var url = "../opinion/add.shtml?opinion.module=xw&opinion.type=1&opinion.pageid="+id+"&opinion.ptime="+time+"&ord="+new Date().getTime();
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

		var url = "../opinion/add.shtml?opinion.module=xw&opinion.type=2&opinion.pageid="+id+"&opinion.ptime="+time+"&ord="+new Date().getTime();
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

//关键词管理页面
function track_column(){
    //渠道用户禁止修改
	if(USERTYPE==0){
        $("input").attr("readonly", "readonly");
        $("textarea").attr("readonly", "readonly");
        return;
    }

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
	
	$("textarea[name*=word]").each(function(){
		var self =$(this);
		if(self.val()==""){
			self.val(self.attr("data-value"));
		}
		
		//如果修改次数超出限制，则不允许修改
		if(self.attr("data-status")<0){
			self.attr("readonly","readonly");
		}
	}).focus(function(){
		var self =$(this);
		if(self.attr("data-status")<0){
			msgbox("关键词的修改已经达到上限，无法继续修改。");
		}else if(self.val()==self.attr("data-value")){
			self.val("");
		}
	}).blur(function(){
		var self =$(this);
		if(self.val()==""){
			self.val(self.attr("data-value"));
		}
	});
	
	$("select[name*=relation]").each(function(){
		var self =$(this);
		self.val(self.attr("data-value"));
	});
	
	
	$("#okbtn").click(function(){
		var valid = true;
		$("textarea[name*=word]").each(function(){
			var self =$(this);
			var capping = self.attr("data-cap");
			
			if(self.val()==self.attr("data-value")){
				self.val("");
			}
			
			var origin = self.val().replaceAll("，",",").split(",");
			var target = new Array();
			for(var i=0; i<origin.length; i++){
				var item = origin[i].trim();
				if(item.length!=0){
					target.push(item);
				}
			}
			self.val(target.join(","));
			
			//如果超出规定数量的词，则提醒
			if(self.val().split(",").length>capping){
				msgbox("关键词不能超过"+capping+"组");
				valid = false;
			}
		});
		$("input[name^=title]").each(function(){
			var self =$(this);
			if(self.val()==self.attr("data-value")){
				self.val("");
			}
		});
		if(valid){
			$("#myform").submit();
		}
	});
	
	$("#resetbtn").click(function(){
		$("#myform")[0].reset();
	});
	
}

//数据分析页面
function track_result(){
	var columnchart = function(){
		var days = $("#columnreport select[name=days]").val();
		$.get("../track/columnchart.shtml?days="+days+"&ord="+new Date().getTime(),
			function(data){
			 	eval(data);
			}
		);
	};
	columnchart();
	$("#columnreport select").change(function(){
		columnchart();
	});
	
	var sitechart = function(){
		var days  	 = $("#sitereport select[name=days]").val();
		var count 	 = $("#sitereport select[name=count]").val();
		$.get("../track/sitechart.shtml?days="+days+"&count="+count+"&org="+new Date().getTime(),function(data){
			eval(data);
		});
	};
	sitechart();
	$("#sitereport select").change(function(){
		sitechart();
	});
	
	//存量图表	
	var stockchart = function(){
		var days = $("#stockreport select[name=days]").val();
		$.get("../track/stockchart.shtml?days="+days+"&ord="+new Date().getTime(),
			function(data){
			 	eval(data);
			}
		);
	};
	stockchart();
	$("#stockreport select").change(function(){
		stockchart();
	});
}

//内容来源分析
function track_origin(){
	//原发转发对照饼图
	var originchart = function(){
		var days   		= $("#originreport select[name=days]").val();
		var relation 	= $("#originreport select[name=relation]").val()
		$.get("../track/originchart.shtml?days="+days+"&relation="+relation+"&ord="+new Date().getTime(),
			function(data){
			 	eval(data);
			}
		);
	};
	originchart();
	$("#originreport select").change(function(){
		originchart();
	});

		
	//原发/转发的媒体分类图
	var origincategorychart = function(){
		var days   		= $("#origincategoryreport select[name=days]").val();
		var relation 	= $("#origincategoryreport select[name=relation]").val();
		var origin 		= $("#origincategoryreport select[name=origin]").val();
		
		$.get("../track/origincategorychart.shtml?days="+days+"&relation="+relation+"&origin="+origin+"&ord="+new Date().getTime(),
			function(data){
			 	eval(data);
			}
		);
	};
	origincategorychart();
	$("#origincategoryreport select").change(function(){
		origincategorychart();
	});
}

//媒体分析页面
function track_category(){
	//媒体分类饼图
	var categorychart = function(){
		var days   		= $("#categoryreport select[name=days]").val();
		var relation 	= $("#categoryreport input[type=radio][name=relation]:checked").val()
		$.get("../track/categorychart.shtml?days="+days+"&relation="+relation+"&ord="+new Date().getTime(),
			function(data){
			 	eval(data);
			}
		);
	};
	categorychart();
	$("#categoryreport select[name=days]").change(function(){
		categorychart();
	});
	$("#categoryreport input[type=radio][name=relation]").click(function(){
		categorychart();
	});
	
	//媒体分类柱状图
	var mediachart = function(){
		var days   		= $("#mediareport select[name=days]").val();
		var relation 	= $("#mediareport input[type=radio]:checked").val()
		$.get("../track/mediachart.shtml?days="+days+"&relation="+relation+"&ord="+new Date().getTime(),
			function(data){
			 	eval(data);
			}
		);
	};
	mediachart();
	$("#mediareport select[name=days]").change(function(){
		mediachart();
	});
	$("#mediareport input[type=radio]").click(function(){
		mediachart();
	});
}
