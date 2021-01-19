function recommend_favorite(){
	//删除内容
	$("a[data-delete]").click(function(){
		var self = $(this);
		var id = self.attr("data-delete");

		confirm("您确定要删除该内容吗？", function(){
			var url = "../recommend/delfavorite.shtml?favorite.id="+id+"&ord="+new Date().getTime();
			$.get(url, function(data){
				if(data.indexOf("success")>-1){
					self.parents("li").hide();
				}
			});
		})
	});
	
	var tips = "点击输入推荐语";
	//处理推荐语按钮的提示
	$("a[data-comment]").each(function(){
		var self = $(this);
		var comm = self.find("img").attr("title");
		
		if(comm.length>0){
			self.parents("li").find("a[data-type=title]").css("color", "red");
		}else{
			self.find("img").attr("title", tips);
		}
	});
	
	//点击发表推荐语
	$("a[data-comment]").click(function(){
		var self = $(this);
		var id = self.attr("data-comment");
		var comm = self.find("img").attr("title").trim();
		
		if(comm==tips){
			comm = "";
		}
		
		layer.prompt({
				formType: 2,
				value: comm,
				title: '请输入推荐语，保存空格可删除推荐语'
			}, function(value, index, elem){
				value = value.trim();
				if(value.length>120){
					msgbox("推荐语不多于120个字");
					return;
				}
				//保存推荐语
				$.post("../recommend/savecomment.shtml",{
					"favorite.id":id,
					"favorite.comment":value
				},function(data){
					if(data.length>0){
						self.parents("li").find("a[data-type=title]").css("color", "red");
						self.find("img").attr("title", value);
					}else{
						self.parents("li").find("a[data-type=title]").css("color", "black");
						self.find("img").attr("title", tips);
					}
					
					layer.close(index);	
				});
			});
	});
	
	//回填已经选择的内容
	var idlist= ","+$("input[name=idlist]").val()+",";
    $("input[type=checkbox]").each(function(){
		var self = $(this);
		var pageid = self.val();
		if(idlist.indexOf(","+pageid+",")>-1){
			self.attr("checked", "checked");
		}
	});


	//复选框选择或取消选择
    $("input[type=checkbox]").change(function(){
		var self = $(this);
		var checked = self.is(':checked');
		var pageid  = self.val();
		var url = "../recommend/putToList.shtml?favorite.pageid="+pageid;
		if(checked==false){
            url = "../recommend/removeFromList.shtml?favorite.pageid="+pageid;
		}

		$.get(url+"&ord="+new Date().getTime(), function(data){
			$("input[name=idlist]").val(data);
		});

	});

	//创建推荐
	$("#create_recommend").click(function(){
		//获取选择的列表
		var idlist= $("input[name=idlist]").val();
		if(idlist.length==0){
            msgbox("请选择希望推荐的文章。");
		}else{
            window.location.href="../recommend/create.shtml";
		}
	});

	//将标题截断，避免多行
	$("a[data-type=title]").each(function(){
		var self = $(this);

		var title = self.html().trim();
		title = substring(title, 85);
		self.html(title);
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

function recommend_list(){
	//删除内容
	$("a[data-delete]").click(function(){
		var self = $(this);
		var id = self.attr("data-delete");

		confirm("您确认要删除该咨询卡吗?", function(){
			var url = "../recommend/delete.shtml?recommend.id="+id+"&ord="+new Date().getTime();
			$.get(url, function(data){
				if(data.indexOf("success")>-1){
					self.parents("li").hide();
				}
			});
		})
	});

	//编辑内容
	$("a[data-edit]").click(function(){
		//所属模块
		var id = $(this).attr("data-edit");

		window.location.href="../recommend/edit.shtml?recommend.id="+id+"&ord="+new Date().getTime();
	});

	//拷贝链接
	$("a[data-link]").click(function(){
		var link = $(this).attr("data-link")+"&ord="+new Date().getTime();
		var text = "<a href='"+link+"' target='_blank'>"+link+"</a>";
        layer.alert("<div id='qrcode' style='margin:0 auto;width:180px;'></div><br/>"+text, {area: '400px'} );
		
        //微信通的链接全部为原始域名
        var wechat = "http://www.dig88.cn/"+link.substring(link.indexOf("mobile"));
        //生成二维码
		var qrcode = new QRCode(document.getElementById("qrcode"), {
			text: wechat,
			width: 180,
			height: 180,
			colorDark : "#000000",
			colorLight : "#ffffff",
			correctLevel : QRCode.CorrectLevel.H
		});
	});

	$("a[data-preview]").click(function(){
		var url = $(this).attr("data-preview");
		layer.open({
			type: 2,
			title: '手机预览',
			shadeClose: true,
			shade: 0.8,
			area: ['480px', '90%'],
			content: url
		});
	});

	//截断标题
	//处理标题超长的问题
	$("a[data-preview]").each(function(){
		var self = $(this);
		var max = 55;
		var text = self.text();
		var html = self.html();
		var length = JHshStrLen(text);
		if(length>max){
			var str = substring(html, JHshStrLen(html)-(length-max));
			self.html(str);
		}
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

function recommend_create(){
	$("a.keep").click(function(){
        $.get("../recommend/available.shtml", function(data){
            if(data!="true"){
                msgbox("今天创建的资讯卡已经达到上限！", function(){$("input[data-value]").trigger("blur");});
                return;
            }
        });

		$("input[data-value]").each(function(){
			var self = $(this);
			var origin = self.attr("data-value");
			if(self.val().trim()==origin){
				self.val("");
			}
		});

		if($("input[name$=title]").val().trim().length<1){
			msgbox("请输入标题！", function(){$("input[data-value]").trigger("blur");});
		}else if($("input[name$=summary]").val().trim().length<1){
			msgbox("请输入摘要！", function(){$("input[data-value]").trigger("blur");});
		}else if($("input[name$=idlist]").val().trim().length<1){
            msgbox("选择的内容已为空！", function(){$("input[data-value]").trigger("blur");});
        }else {
			$("#submitbtn").trigger("click");
		}
	});

	$("input[data-value]").each(function(){
		var self = $(this);
		var origin = self.attr("data-value");
		self.focus(function(){
			if(self.val().trim()==origin){
				self.val("");
			}
		});

		self.blur(function(){
			if(self.val().trim()==""){
				self.val(origin);
			}
		});
	}).trigger("blur");

	//拖拽排序
	$( ".sortable" ).sortable({
		cursor: "move",
		items :"li", //只是li可以拖动
		opacity: 0.6, //拖动时，透明度为0.6
		revert: true, //释放时，增加动画
		update : function(event, ui){ //更新排序之后
			var idlist = $(this).sortable("toArray");
			//将最新的顺序更新到隐藏字段
			$("input[name=recommend\\.idlist]").val(idlist);
		}
	});

	//删除备选项
	$("a[data-delete]").click(function(){
		var self = $(this);
		var id = self.attr("data-delete");
		var idlist = $("input[name$=idlist]").val();
		//前后追加逗号，匹配时id前后都带有逗号，避免查到的是某个id的子串
		idlist = ","+idlist+",";
		//删除指定的id
		idlist = idlist.replaceAll(","+id+",", ",");
		//去除开始和结尾处的逗号
		if(idlist.startWith(",")){
			idlist = idlist.substring(1);
		}
		if(idlist.endWith(",")){
			idlist = idlist.substring(0, idlist.length-1);
		}

        if(idlist.trim().length==0){
            msgbox("内容列表不能清空！");
        }else {
            $("input[name$=idlist]").val(idlist);
            self.parents("li").hide();
        }
	});

    //提示
    var status = $("input[name$=status]").val();
    if(status==0){
        msgbox("选择的内容中包含自媒体，需要管理员审核后才能发布出去。");
    }

	//将标题截断，避免多行
	$("a[data-type=title]").each(function(){
		var self = $(this);

		var title = self.html().trim();
		title = substring(title, 75);
		self.html(title);
	});
}

function recommend_edit(){
	var reward = $("#reward").attr("data-value");
	$("#reward").val(reward);

	$("a.keep").click(function(){
		$("input[data-value]").each(function(){
			var self = $(this);
			var origin = self.attr("data-value");
			if(self.val().trim()==origin){
				self.val("");
			}
		});

		if($("input[name$=title]").val().trim().length<1){
			msgbox("请输入标题！", function(){$("input[data-value]").trigger("blur");});
		}else if($("input[name$=summary]").val().trim().length<1){
			msgbox("请输入摘要！", function(){$("input[data-value]").trigger("blur");});
		}else if($("input[name$=idlist]").val().trim().length<1){
            msgbox("选择的内容已为空！", function(){$("input[data-value]").trigger("blur");});
        }else {
			$("#submitbtn").trigger("click");
		}
	});

	$("input[data-value]").each(function(){
		var self = $(this);
		var origin = self.attr("data-value");
		self.focus(function(){
			if(self.val().trim()==origin){
				self.val("");
			}
		});

		self.blur(function(){
			if(self.val().trim()==""){
				self.val(origin);
			}
		});
	}).trigger("blur");

	$("select[data-value]").each(function(){
		var self = $(this);
		var value = self.attr("data-value");
		self.val(value);
	});

	$( ".sortable" ).sortable({
		cursor: "move",
		items :"li", //只是li可以拖动
		opacity: 0.6, //拖动时，透明度为0.6
		revert: true, //释放时，增加动画
		update : function(event, ui){ //更新排序之后
			var idlist = $(this).sortable("toArray");
			//将最新的顺序更新到隐藏字段
			$("input[name=recommend\\.idlist]").val(idlist);
		}
	});

    //删除备选项
    $("a[data-delete]").click(function(){
        var self = $(this);
        var id = self.attr("data-delete");
        var idlist = $("input[name$=idlist]").val();
        //前后追加逗号，匹配时id前后都带有逗号，避免查到的是某个id的子串
        idlist = ","+idlist+",";
        //删除指定的id
        idlist = idlist.replaceAll(","+id+",", ",");
        //去除开始和结尾处的逗号
        if(idlist.startWith(",")){
            idlist = idlist.substring(1);
        }
        if(idlist.endWith(",")){
            idlist = idlist.substring(0, idlist.length-1);
        }

        if(idlist.trim().length==0){
        	msgbox("内容列表不能清空！");
		}else{
            $("input[name$=idlist]").val(idlist);
            self.parents("li").hide();
		}
    });

	//将标题截断，避免多行
	$("a[data-type=title]").each(function(){
		var self = $(this);

		var title = self.html().trim();
		title = substring(title, 75);
		self.html(title);
	});
}

function recommend_manage_index() {
	$("a[data-preview]").click(function(){
		var url = $(this).attr("data-preview");
		layer.open({
			type: 2,
			title: '手机预览',
			shadeClose: true,
			shade: 0.8,
			area: ['480px', '90%'],
			content: url
		});
	});

	$("a[data-verify]").click(function(){
		var self = $(this);
		var id = self.attr("data-verify");
		var status = self.attr("data-status")==0?1:0;

		$.get("../recommend/manage_verify.shtml?recommend.id="+id+"&recommend.status="+status+"&ord="+new Date().getTime(), function(data){
			self.parents("tr").hide();
		});
	});

	$("select[name=recommend\\.status]").change(function(){
		var self = $(this);
		var status = self.val();
		window.location.href = "../recommend/manage_index.shtml?recommend.status="+status;
	});

	//初始化下拉框的值
	var status = $("select[name=recommend\\.status]").attr("data-value");
	$("select[name=recommend\\.status]").val(status);

	//分页
	pageselectCallback = function(page_index, jq){
		return true;
	};

	var total = $(".fy_div").attr("data-total");
	var pagesize = $(".fy_div").attr("data-pagesize");
	var pageindex = $(".fy_div").attr("data-pageindex");
	var url = $(".fy_div").attr("data-urlbase");
	if(url.indexOf("?")>-1){
		url += "&pageindex=__id__";
	}else{
		url+= "?pageindex=__id__";
	}
	$(".fy_div").pagination(total, {
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
