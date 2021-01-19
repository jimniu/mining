function wechat_content(){
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
	
	//阅读数
	$("a.yuedu").hover(function(){
		var id = $(this).attr("data-id");
		$("div.right_yd_piao").hide();
		$("div.right_yd_piao[data-id="+id+"]").show()
	},function(){
		$("div.right_yd_piao").hide();
	});
	
	//点赞数
	$("a.dianzan").hover(function(){
		var id = $(this).attr("data-id");
		$("div.right_dz_piao").hide();
		$("div.right_dz_piao[data-id="+id+"]").show()
	},function(){
		$("div.right_dz_piao").hide();
	});
	
	//好评内容
	$("a[data-positive]").click(function(){
		var self = $(this);
		var id = self.attr("data-positive");
		var time= self.attr("data-ptime");

		var url = "../opinion/add.shtml?opinion.module=wx&opinion.type=1&opinion.pageid="+id+"&opinion.ptime="+time+"&ord="+new Date().getTime();
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

		var url = "../opinion/add.shtml?opinion.module=wx&opinion.type=2&opinion.pageid="+id+"&opinion.ptime="+time+"&ord="+new Date().getTime();
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
		confirm("您确认要删除该条微信吗？",function() {
			var url = "../wechat/delete.shtml?result.id=" + id + "&ord=" + new Date().getTime();
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

		var url = "../recommend/addfavorite.shtml?favorite.module=wx&favorite.pageid="+id+"&ord="+new Date().getTime();
		$.get(url, function(data){
			if(data=="true"){
				msgbox("成功加入收藏！");
			}else{
				msgbox("文章之前已经收藏！");
			}
		});
	});
	
}

function wechat_filter(){
	$("input.datepicker").datepicker({ dateFormat: 'yy-mm-dd' });
	
	$("#searchBtn").click(function(){
		$("#submitBtn").trigger("click");
	});
	
	//处理标题超长的问题
	$("li span.left a").each(function(){
		var self = $(this);
		var max = 92;
		var html = self.html();
		var length = JHshStrLen(html);
		if(length>max){
			var str = substring(html, max);
			self.html(str);
		}
	});
	

    //收藏内容
    $("a[data-favorite]").click(function(){
        var self = $(this);
        var id = self.attr("data-favorite");

        var url = "../recommend/addfavorite.shtml?favorite.module=wx&favorite.pageid="+id+"&ord="+new Date().getTime();
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

		var url = "../opinion/add.shtml?opinion.module=wx&opinion.type=1&opinion.pageid="+id+"&opinion.ptime="+time+"&ord="+new Date().getTime();
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

		var url = "../opinion/add.shtml?opinion.module=wx&opinion.type=2&opinion.pageid="+id+"&opinion.ptime="+time+"&ord="+new Date().getTime();
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
		confirm("您确认要删除该条微信吗？",function() {
			var url = "../wechat/delete.shtml?result.id=" + id + "&ord=" + new Date().getTime();
			$.get(url, function (data) {
				if (data.indexOf("success") > -1) {
					self.parents("li").hide();
				}
			});
		});
	});    
}

function wechat_column(){
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
		if(valid==true){
			$("#myform").submit();
		}
	});
	
	$("select[name*=relation]").each(function(){
		var self =$(this);
		self.val(self.attr("data-value"));
	});
	
	$("#resetbtn").click(function(){
		$("#myform")[0].reset();
	});
	
}

function wechat_more(){
	$("#searchBtn").click(function(){
		$("#submitBtn").trigger("click");
	});
	
	//处理标题超长的问题
	$("li span.left a").each(function(){
		var self = $(this);
		var max = 92;
		var html = self.html();
		var length = JHshStrLen(html);
		if(length>max){
			var str = substring(html, max);
			self.html(str);
		}
	});
	
	//阅读数
	$("ul a.yuedu").hover(function(){
		var id = $(this).attr("data-id");
		$("div.right_yd_piao").hide();
		$("ul div.right_yd_piao[data-id="+id+"]").show()
	},function(){
		$("div.right_yd_piao").hide();
	});
	
	//点赞数
	$("ul a.dianzan").hover(function(){
		var id = $(this).attr("data-id");
		$("div.right_dz_piao").hide();
		$("ul div.right_dz_piao[data-id="+id+"]").show()
	},function(){
		$("div.right_dz_piao").hide();
	});
	
	//删除内容
	$("a[data-delete]").click(function(){
		var self = $(this);
		var id = self.attr("data-delete");

		confirm("您确认要删除该条微信吗？",function() {
			var url = "../wechat/delete.shtml?result.id=" + id + "&ord=" + new Date().getTime();
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

		var url = "../recommend/addfavorite.shtml?favorite.module=wx&favorite.pageid="+id+"&ord="+new Date().getTime();
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

		var url = "../opinion/add.shtml?opinion.module=wx&opinion.type=1&opinion.pageid="+id+"&opinion.ptime="+time+"&ord="+new Date().getTime();
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

		var url = "../opinion/add.shtml?opinion.module=wx&opinion.type=2&opinion.pageid="+id+"&opinion.ptime="+time+"&ord="+new Date().getTime();
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
	
	//回显选择项
	$("select[data-value]").each(function(){
		$(this).val($(this).attr("data-value"));
	}).change(function(){
		var self = $(this);
		self.parents("form").submit();
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

function wechat_openid(){
	//选中选项
	$("div.wxwj_wxgg_link").each(function(){
		$(this).removeClass("wxwj_wxgg_link_vtd");
	});
	var url = document.location.href;
	if(url.indexOf("column.relation=1")>-1){
		$("div.wxwj_wxgg_link a[href$=1]").addClass("wxwj_wxgg_link_vtd");
	}else if(url.indexOf("column.relation=2")>-1){
		$("div.wxwj_wxgg_link a[href$=2]").addClass("wxwj_wxgg_link_vtd");
	}else{
		$("div.wxwj_wxgg_link a[href$=shtml]").addClass("wxwj_wxgg_link_vtd");
	}
	
	//鼠标mouseover时显示详细信息
	$(".wxwj_wxgg_one a").hover(function(){
		var self = $(this);
		var pos = self.attr("pos");
		$(".wxwj_wxgg_one_piao").hide();
		$(".wxwj_wxgg_one_piao[pos="+pos+"]").show();
	},function(){
		$(".wxwj_wxgg_one_piao").hide();
	});
	
	//点击公众号的关系设置
	$("span[data-url]").click(function(){
		var self = $(this);
		var url = self.attr("data-url");
		$.get(url, function(data){
			document.location.href=document.location.href;
		});
	});
	
	//显示每个公众号的最新文章
	$("[data-latest]").each(function(){
		var self = $(this);
		var id = self.attr("data-latest");
		
		$.get("../wechat/latest.shtml?result.wxid="+id, function(data){
			self.find("span.l").html(data);
		});
	});
	
	$("img[data-src]").each(function(){
		var self = $(this);
		var src = self.attr("data-src");
		var origin = self.attr("data-origin");
		
		//图片加载出错时，从远程下载
		self.on("error",function(){
			var image = new Image();
			image.onload = function(){ 
				//如果能从远程加载图片成功，则显示远程图片
	    		self.attr("src", image.src);
    		};
    		image.onerror=function(){
    			self.attr("src", "../images/wbwj_dsj.jpg");
    		};
    		
			remote = "../common/showimage.shtml?url="+origin;
			image.src=remote;
		});
		
		self.attr("src", src);
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

function wechat_result(){
	//挖掘图表
	var columnchart = function(){
		var days = $("#columnreport select[name=days]").val();
		$.get("../wechat/columnchart.shtml?days="+days+"&org="+new Date().getTime(),function(data){
			eval(data);
		});
	};
	columnchart();
	$("#columnreport select").change(function(){
		columnchart();
	});
	
	
	var sitechart = function(){
		var days  	 = $("#sitereport select[name=days]").val();
		var count 	 = $("#sitereport select[name=count]").val();
		$.get("../wechat/sitechart.shtml?days="+days+"&count="+count+"&org="+new Date().getTime(),function(data){
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
		$.get("../wechat/stockchart.shtml?days="+days+"&ord="+new Date().getTime(),
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
