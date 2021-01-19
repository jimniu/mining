// 前台index.jsp
function site_index() {
	//添加输入框的事件
	$("input[data-value]").each(function(){
		var origin = $(this).attr("data-value");
		var value  = $(this).attr("value");
		if(value==""){
			$(this).val(origin)
		}
	}).focus(function(){
		var origin = $(this).attr("data-value");
		var value  = $(this).attr("value");
		if(origin==value){
			$(this).val("")
		}else{
			$(this).select();
		}
	}).blur(function(){
		var value  = $(this).attr("value");
		if(value==""){
			var origin = $(this).attr("data-value");
			$(this).val(origin);
		}
	});
	
	//登录操作
	$("#loginbtn").click(function(){
		$("input[data-value]").each(function(){
			var origin = $(this).attr("data-value");
			var value  = $(this).attr("value");
			if(value==origin){
				$(this).val("");
			}
		});
		
		var name = $("input[name$=name]").val().trim();
		var pass = $("input[name$=password]").val().trim();
		var remember = $("input[name=remember]").is(':checked')?1:0;

		if (name.length == 0) {
			$("input[name$=name]").val($("input[name$=name]").attr("data-value"));
			msgbox("请输入用户名");
		} else if (pass.length == 0) {
			msgbox("请输入登录密码");
		} else {
			$.post("../user/login.shtml", {
				"user.name" : name,
				"user.password" : pass,
				"rememberme" : remember,
				"ord" : new Date().getTime()
			}, function(data) {
				if (data == "SUCCESS") {
					window.location.href = "../site/overview.shtml";
				} else {
					msgbox(data);
				}
			});
		}
	});
	
	$(".index_dl_bt  a").click(function(){
		$(".index_dl").css("display","none");
	});
	
	$(".index_dl_qd  a").click(function(){
		$(".index_dl").css("display","none");
	});

	//绑定回车事件
	enterEvent($("#loginbtn"));
	
	$("#tiyan").click(function(){
		$(".index_ljty").show();
	});
	
	$(".index_ljty_bt a").click(function(){
		$(".index_ljty").hide();
	});
	
	$(".index_ljty li a").click(function(){
		var name = $("#tusername").val().trim();
		var checkCode = $("#checkCode").val();
		var company = $("input[name$=company]").val().trim();
		var mobile = $("input[name$=mobile]").val().trim();
		var tellphone = $("input[name$=tellphone]").val().trim();
		var email = $("input[name$=email]").val().trim();
		
		if (name.length == 0) {
			msgbox("请输入姓名");
		} else if (company.length == 0) {
			msgbox("请输入公司名称");
		} else if (mobile.length == 0) {
			msgbox("请输入手机号");
		} else if (tellphone.length == 0) {
			msgbox("请输入固定电话");
		} else if (email.length == 0) {
			msgbox("请输入邮箱");
		} else if (!(/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/.test(mobile))) {
			msgbox("请输入合法的11位手机号");
		} else if(!/^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/.test(tellphone)){
			msgbox("请输入正确格式的固定电话");
		}else if (!(/^(\w)+(\.\w+)*@(\w)+((\.\w{2,3}){1,3})$/.test(email))) {
			msgbox("请输入合法的邮箱地址");
		} else if (checkCode.length == 0) {
			msgbox("请输入验证码");
		} else{
			$.post("../tuser/add.shtml", {
				"tuser.name" : name,
				"tuser.company" : company,
				"tuser.mobile" : mobile, 
				"tuser.tellphone" : tellphone, 
				"tuser.email" : email, 
				"verifyCode" : checkCode,
				"ord" : new Date().getTime()
			}, function(data) {
				if (data == "SUCCESS") {
					$(".index_ljty").hide();
					msgbox("您的信息已经提交成功，稍后我们会联系您！");
					setTimeout(function(){
						window.location.href = "../site/index.shtml";
					},3000);
				} else {
					msgbox(data);
				}
			});
		}
		
	});
}

//总揽
function site_overview(){
	var columnchart = function(){
		var module = $("#columndiv input[type=radio]:checked").val();
		var days   = $("#columndiv select").val();
		$.get("../site/columnchart.shtml?module="+module+"&days="+days+"&ord="+new Date().getTime(),
			function(data){
			 	eval(data);
			}
		);
	};
	columnchart();
	
	//渠道细分的饼图加载事件
	$("#columndiv [data-value]").change(function(){
		columnchart();
	});
	

	//历史数据的饼图
	var historypie = function(){
		var days   		= $("#historydiv select[name=days]").val();
		var relation 	= $("#historydiv input[type=radio][name=relation]:checked").val()
		$.get("../site/historypie.shtml?days="+days+"&relation="+relation+"&ord="+new Date().getTime(),
			function(data){
			 	eval(data);
			}
		);
	};
	//历史数据的柱状图
	var historybar = function(){
		var module 		= $("#historydiv input[type=radio][name=historymodule]:checked").val();
		var days   		= $("#historydiv select").val();
		var relation 	= $("#historydiv input[type=radio][name=relation]:checked").val()
		$.get("../site/historybar.shtml?module="+module+"&days="+days+"&relation="+relation+"&ord="+new Date().getTime(),
			function(data){
			 	eval(data);
			}
		);
	};	
	historypie();
	historybar();
	
	//历史数据的日期范围的修改事件
	$("#historydiv select[name=days]").change(function(){
		historypie();
		historybar();
	});
	
	//历史数据的关系关系修改事件
	$("#historydiv input[type=radio][name=relation]").change(function(){
		historypie();
		historybar();
	});
	
	//历史数据曲线图的内容修改事件
	$("#historydiv input[type=radio][name=historymodule]").change(function(){
		historybar();
	});
	
	
	//柱状图
	$("div.mrzl_lssj_link a[data-url]").click(function(){
		var self = $(this);
		
		$("div.mrzl_lssj_link a.text1_vtd").removeClass("text1_vtd");
		self.addClass("text1_vtd");
		
		var url = self.attr("data-url");
		$.get(url,function(data){
			eval(data);
			var cavas = $("#monthlychart");
			var ctx = cavas.get(0).getContext("2d");
			if(typeof siteChart!= 'undefined'){
				siteChart.destroy();
			}
			
			//图的宽度
			var chartwidth = cavas.attr("width")-30;
			var datatotal  = data.labels.length;
			var barwidth   = 70;
			var spacing = (chartwidth - datatotal * barwidth) / (datatotal*2);
			if(spacing<0){
				spacing = 5;
			}
			siteChart = new Chart(ctx).Bar(data,{datasetFill:false, barValueSpacing:spacing, pointDotRadius:2});	
		});
	});
	
	$("div.mrzl_lssj_link a[data-url*=wechat]").trigger("click");
	
	//页面开始处的小饼图
	var index = 0;
	$("span[data-rate]").each(function(){
		var self = $(this);
		var rate = self.attr("data-rate");
		var color 	  = ["rgba(89,171,157,1)","rgba(255,66,0,1)","rgba(68,137,202,1)","rgba(174,93,160,1)"];
		var highlight = ["rgba(89,171,157,0.7)","rgba(255,66,0,0.7)","rgba(68,137,202,0.7)","rgba(174,93,160,0.7)"];
		var data = [
			{
				value: rate,
				color:color[index],
			    highlight: highlight[index]
			},
			{
				value: (100-rate),
				color:"#f2f2f2",
			    highlight: "#f2f2f2"
			}
		];
		
		var cavas = self.find("canvas");
				
		var ctx = cavas.get(0).getContext("2d");
		var datatotalchart = new Chart(ctx).Doughnut(data,{animateRotate:false, percentageInnerCutout:80});
		index ++;
	});
}

//可视数据
function site_chartview(){
	var wbchart;
	var wxchart;
	var xwchart;
	var ltchart;
	//自动显示tip的定时器
	var timer;
	//上次自动显示tip的位置
	var lastone = 0;
	//图表类型
	var typeid = 0;
	
	//图表的样式数组
	var chartarray = ["new Chart(ctx).Bar(data,{datasetFill:false, barValueSpacing:spacing, pointDotRadius:2})", 
					  "new Chart(ctx).Line(data,{datasetFill:false, bezierCurve:false, pointDotRadius:2})",
					  "new Chart(ctx).Line(data,{datasetFill:false, bezierCurve:true, pointDotRadius:2})",
					  "new Chart(ctx).Bar(data,{datasetFill:false, barValueSpacing:spacing, pointDotRadius:2})"];
	
	var getType = function(){
		typeid = (typeid) % (chartarray.length);
		var type = chartarray[typeid];
		typeid ++;
		return type;
	};
	
	var ticker = function(){
		var url = $("marquee").attr("data-url");
		$.get(url+"?ord="+new Date().getTime(), function(info){
			$("marquee").html(info);
		});
	};
	
	var map = function(){
		//在正式地图显示之前，先显示空白地图
		$('#map').SVGMap({
			external: true,
			mapName: 'china',
			mapWidth: 559,
			mapHeight: 390,
			strokeWidth: 2,
			strokeColor: '#24b8c6',
			stateInitColor: '#000000',
		});
			
		//获取数据
		var url = $("#map").attr("data-url");
		$.get(url+"?ord="+new Date().getTime(), function(info){
			var data = {};
			eval(info);

			$('div.excellent ul').hide().html("");
			$('div.inferior ul').hide().html("");
			
			var i = 1;
			for(k in data){
				//没有省份的数据忽略
				if(chinaMapConfig.shapes[k]==null){
					continue;
				}
				if(i <= 5){
					data[k].stateInitColor=i;
					$('div.excellent ul').append('<li name="'+k+'" class="text'+i+'">Top'+i+' '+k+'</li>');
				}else{
					$('div.inferior ul').prepend('<li name="'+k+'">'+k+'</li>');
					//如果超过5个，则隐藏最后的
					if($('div.inferior li').size()>5){
						$('div.inferior li:eq(5)').hide();
					}
				}
				i++;
			}
				
			//设置后几名的样式
			i = 1;
			$('div.inferior ul li:lt(5)').each(function(){
			  	var self = $(this);
			  	var name = self.attr("name");
			  	data[name].stateInitColor=i+5;
				self.text("Top"+i+" "+self.text())
			  	self.addClass("text"+i);
			  	i++;
			});
	
			var stateColorList = ['#000000','#ff0000', '#d01d57', '#fc5c0d', '#f18449', '#f5ba54', '#0097f0', '#6f94f8', '#3981af', '#5f52a0', '#556fb5','#ffffff'];
				
			$("#map").html("");
			var mapRegion = $('#map').SVGMap({
				external: true,
				mapName: 'china',
				mapWidth: 559,
				mapHeight: 390,
				stateData: data,
				
				stateColorList : stateColorList,
				strokeWidth: 2,
				strokeColor: '#24b8c6',
				stateInitColor: '#000000',
				
				stateTipWidth: 28,
				stateTipHeight: 15,
				// stateTipX: 2,
				// stateTipY: 0,
			    mapTipHtml: function(stateData, obj){
			    	var name = obj.id;
					var tipStr = '<div class="mapInfo"><b>'+name+': 0</b></div>';
					if(typeof stateData[name]!='undefined'){
						var _value = stateData[name].value;
						tipStr = '<div class="mapInfo"><b>'+name+': ' + _value + '</b></div>';
					}		
			        return tipStr;
			    }
			});

			$('div.excellent ul').show("slow");
			$('div.inferior ul').show("slow");
			
			$('.MapControl li').hover(function () {
				var thisName = $(this).attr('name');
					
				var thisHtml = '<b>' + thisName + ': 0</b>';;
				if(typeof data[thisName]!='undefined'){
					var _value = data[thisName].value;
					thisHtml = '<b>' + thisName + ': ' + _value + '</b>';
				}					
			
				$(document.body).append('<div id="MapTip1" class="mapTip"><div class="con"></div></div');
				$('#MapTip1 .con').html(thisHtml);
				$('#MapTip1').css({
					left: $(mapRegion.externalData[thisName].node).offset().left,
					top: $(mapRegion.externalData[thisName].node).offset().top - 5
				}).show();
				mapRegion.externalData[thisName].attr({
					"fill": "#feb41c"
				});
			}, function () {
				var thisName = $(this).attr('name');
		
				$('#MapTip1').remove();
				mapRegion.externalData[thisName].attr({
					fill: stateColorList[data[thisName].stateInitColor]
				});
			});	
			
			//定期显示某个省份的提示
			autotip = function(){
				var size = $('.MapControl li').size();
				var rand = Math.floor(size * Math.random());
				$('.MapControl li:eq('+lastone+')').trigger("mouseout");
				$('.MapControl li:eq('+rand+')').trigger("mouseover");
				lastone = rand;
			};
			
			if(timer!=null){
				clearInterval(timer);
			}
			timer = setInterval("autotip()", 3000);
		});
	}; 	
	
	//统计数据
	var summary = function(){
		$("#summary").hide();
		var url = $("#summary").attr("data-url");
		$.get(url+"?ord="+new Date().getTime(), function(data){
			$("#summary").html(data).show("slow");
			
			//点击替换当前显示
			$("#changesummary").click(function(){
				summary();
			});
		});	
		
		if(url.indexOf("summaryall")>-1){
			url = url.replace("summaryall", "summary");
		}else{
			url = url.replace("summary", "summaryall");
		}
		$("#summary").attr("data-url", url);
	};
	
	var weibo = function(){
		var url = $("#wbchart").attr("data-url");
		$.get(url+"?ord="+new Date().getTime(), function(data){
			eval(data);
			var cavas = $("#wbchart");
			var ctx = cavas.get(0).getContext("2d");
			if(typeof wbchart!= 'undefined'){
				wbchart.destroy();
			}
					
			//图的宽度
			var chartwidth = cavas.attr("width")-30;
			var datatotal  = data.labels.length;
			var barwidth   = 30;
			var spacing = (chartwidth - datatotal * barwidth) / (datatotal*2);
			if(spacing<0){
				spacing = 5;
			}
			wbchart = eval(getType());	
		});	
	};

	var wechat = function(){
		var url = $("#wxchart").attr("data-url");
		$.get(url+"?ord="+new Date().getTime(), function(data){
			eval(data);
			var cavas = $("#wxchart");
			var ctx = cavas.get(0).getContext("2d");
			if(typeof wxchart!= 'undefined'){
				wxchart.destroy();
			}

			//图的宽度
			var chartwidth = cavas.attr("width")-30;
			var datatotal  = data.labels.length;
			var barwidth   = 30;
			var spacing = (chartwidth - datatotal * barwidth) / (datatotal*2);
			if(spacing<0){
				spacing = 5;
			}
			
			wxchart = eval(getType());	

		});	
	};
	
	var news = function(){
		var url = $("#xwchart").attr("data-url");
		$.get(url+"?ord="+new Date().getTime(), function(data){
			eval(data);
			var cavas = $("#xwchart");
			var ctx = cavas.get(0).getContext("2d");
			if(typeof xwchart!= 'undefined'){
				xwchart.destroy();
			}
			
			//图的宽度
			var chartwidth = cavas.attr("width")-30;
			var datatotal  = data.labels.length;
			var barwidth   = 30;
			var spacing = (chartwidth - datatotal * barwidth) / (datatotal*2);
			if(spacing<0){
				spacing = 5;
			}
			
			xwchart = eval(getType());	
		});	
	};
	
	var bbs = function(){
		var url = $("#ltchart").attr("data-url");
		$.get(url+"?ord="+new Date().getTime(), function(data){
			eval(data);
			var cavas = $("#ltchart");
			var ctx = cavas.get(0).getContext("2d");
			if(typeof ltchart!= 'undefined'){
				ltchart.destroy();
			}
				
			//图的宽度
			var chartwidth = cavas.attr("width")-30;
			var datatotal  = data.labels.length;
			var barwidth   = 30;
			var spacing = (chartwidth - datatotal * barwidth) / (datatotal*2);
			if(spacing<0){
				spacing = 5;
			}
			
			ltchart = eval(getType());	
		});	
	};
	
	var counter = 0;
	
	//重新加载视图
	reload = function(){
		switch(counter){
			case 0:
				map();
				break;
			case 1:
				summary();
				break;
			case 2:
				weibo();
				break;
			case 3:
				wechat();
				break;
			case 4:
				news();
				break;
			case 5:
				bbs();
				break;
			case 6:
				ticker();
				break;
		}
		counter ++;
		counter = counter % 7;
	};
	ticker();
	map();
	summary();
	weibo();
	wechat();
	news();
	bbs();
	setInterval("reload()", 30000);
}

function site_event(){
	var xwchart;
	//上次自动显示tip的位置
	var lastone = 0;
	var index = 0;
	var timer;
	var summary = function(){
		var url = $("#summary").attr("data-url");
		$.get(url+"?offset="+index, function(info){
			$("#summary").hide().html(info).show("slow");
		});
	};
	
	var chart = function(){
		var url = $("#chart").attr("data-url");
		$.get(url+"?offset="+index, function(data){
			eval(data);
			var cavas = $("#chart");
			var ctx = cavas.get(0).getContext("2d");
			if(typeof eventchart!= 'undefined'){
				eventchart.destroy();
			}
			
			//图的宽度
			var chartwidth = cavas.attr("width")-30;
			var datatotal  = data.labels.length;
			var barwidth   = 30;
			var spacing = (chartwidth - datatotal * barwidth) / (datatotal*2);
			if(spacing<0){
				spacing = 5;
			}
			
			eventchart = new Chart(ctx).Line(data,{datasetFill:true, bezierCurve:true, pointDotRadius:2});	
		});	
	};
	
	var map = function(){
		var width = 730;
		var height = 560;
		
		//在正式地图显示之前，先显示空白地图
		$('#map').SVGMap({
			external: true,
			mapName: 'china',
			mapWidth: width,
			mapHeight: height,
			strokeWidth: 2,
			strokeColor: '#24b8c6',
			stateInitColor: '#000000',
		});

		//获取数据
		var url = $("#map").attr("data-url");
		$.get(url+"?offset="+index, function(info){
			var data = {};
			eval(info);
			
			var i = 1;
			//加重显示的省份
			var highlight = new Array();
			var top5 = "";
			
			for(k in data){
				//没有省份的数据忽略
				if(chinaMapConfig.shapes[k]==null){
					continue;
				}
				if(i <= 5){
					data[k].stateInitColor=i;
					top5 += '<li class="text'+i+'">TOP'+i+' '+k+'</li>';					
				}
				highlight.push(k);
				i++;
			}
			$("#top5").html(top5);
			
		
			var stateColorList = ['#556fb5','#ff0000', '#d01d57', '#fc5c0d', '#f18449', '#f5ba54', '#0097f0', '#6f94f8', '#3981af', '#5f52a0', '#556fb5','#ffffff'];
				
			var mapRegion = $('#map').SVGMap({
				external: true,
				mapName: 'china',
				mapWidth: width,
				mapHeight: height,
				stateData: data,
				
				stateColorList : stateColorList,
				strokeWidth: 2,
				strokeColor: '#24b8c6',
				stateInitColor: '#000000',
				
				stateTipWidth: 28,
				stateTipHeight: 15,
				// stateTipX: 2,
				// stateTipY: 0,
			    mapTipHtml: function(stateData, obj){
			    	var name = obj.id;
					var tipStr = '<div class="mapInfo"><b>'+name+': 0</b></div>';
					if(typeof stateData[name]!='undefined'){
						var _value = stateData[name].value;
						tipStr = '<div class="mapInfo"><b>'+name+': ' + _value + '</b></div>';
					}		
			        return tipStr;
			    }
			});

			var showTip = function (thisName) {
				var thisHtml = '<b>' + thisName + ': 0</b>';;
				if(typeof data[thisName]!='undefined'){
					var _value = data[thisName].value;
					thisHtml = '<b>' + thisName + ': ' + _value + '</b>';
				}					
			
				$(document.body).append('<div id="MapTip1" class="mapTip"><div class="con"></div></div');
				$('#MapTip1 .con').html(thisHtml);
				$('#MapTip1').css({
					left: $(mapRegion.externalData[thisName].node).offset().left,
					top: $(mapRegion.externalData[thisName].node).offset().top - 5
				}).show();
				mapRegion.externalData[thisName].attr({
					"fill": "#feb41c"
				});
			};
			var hideTip = function (thisName) {
				$('#MapTip1').remove();
				mapRegion.externalData[thisName].attr({
					fill: stateColorList[data[thisName].stateInitColor]
				});
			};	
			
			//定期显示某个省份的提示
			autotip = function(){
				hideTip(highlight[lastone]);
				var rand = Math.floor(highlight.length * Math.random());
				showTip(highlight[rand]);
				lastone = rand;
			};
			
			if(timer!=null){
				clearInterval(timer);
			}
			timer = setInterval("autotip()", 3000);
		});
	}; 	
		//重新加载视图
	reload = function(){
		summary();
		chart();
		map();
		index ++;
	};
	setInterval("reload()", 10000);	
	reload();
}

function site_problem(){
	$(".cjwt_list").each(function(){
		$(this).find("a").click(function(){
			var pp = $(this).attr("pp");
			var pos = $(this).attr("pos")
			if(	pp == 0 ){
				$(".cjwt_list_one_text[pos="+pos+"]").show();
				$(this).attr("pp", 1);
			} else {
				$(".cjwt_list_one_text[pos="+pos+"]").hide();
				$(this).attr("pp", 0);
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
		link_to:url+"?pageindex=__id__",
		num_edge_entries: 0
	}); 
}

// 后台index.jsp
function site_manage_index(){
    enterEvent($("#submit"));

	//登录操作
	$("#submit").click(function(){
		var name = $("#name").val();
		var pass  = $("#password").val();

		if(name.length==0){
			msgbox("请输入用户名");
		}else if(pass.length==0){
			msgbox("请输入登录密码");
		}else{
			$.post("../auser/manage_login.shtml", {
				"adminUser.name" : name,
				"adminUser.password" : pass,
				"ord" : new Date().getTime()
			}, function(data){
				if(data=="SUCCESS"){
					window.location.href="../auser/manage_index.shtml";					
				} else {
					msgbox(data);
				}
			});
		}
	});	
}

function site_focus(){
	$("a[data-recommend]").click(function(){
		var self = $(this);
		var para = self.attr("data-recommend");
		$.get("../recommend/addfavorite.shtml?"+para+"&ord="+new Date().getTime(), function(data){
			if(data=="true"){
				msgbox("成功加入收藏！")
			}else if(data=="false"){
				msgbox("文章之前已经收藏！")
			}
		});
	});
}