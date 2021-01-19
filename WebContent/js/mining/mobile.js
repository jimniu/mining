//今日内容
function mobile_today() {
	
	//点击退出按钮
	$(".nav_right a").click(function(){
		var popup = $(".nav_list");
		if(popup.is(":hidden")){
			$(".nav_list").show();
		}else{
			$(".nav_list").hide();
		}
	});

	//加载内容
	var loadContent = function(url){
		$.get(url,function(data){
			$(".jzgd").remove();
			$("#main").append(data);
			$("#total").html($(".jzgd a").attr("data-total"));
			pageindex++;
			
			$("div.jzgd a").click(function(){
				var self = $(this);
				var url = baseurl+"?module="+module+"&relation="+relation+"&days="+days+"&pageindex="+pageindex+"&pagesize="+pagesize;
				
				loadContent(url);
			});
		});
	};
	
	//加载更多
	$("div.jzgd a").click(function(){
		var self = $(this);
		var url = baseurl+"?module="+module+"&relation="+relation+"&days="+days+"&pageindex="+pageindex+"&pagesize="+pagesize;
		loadContent(url);
	});	
	$("div.jzgd a").trigger("click");
	
	//点击按钮
	$("a[data-relation]").click(function(){
		var self = $(this);
		
		self.closest("div").find("a[data-relation]").removeClass("vtd");
		self.addClass("vtd");
		
		relation = self.attr("data-relation");
		pageindex = 0;
		
		$("#main").html("");
		var url = baseurl+"?module="+module+"&relation="+relation+"&days="+days+"&pageindex="+pageindex+"&pagesize="+pagesize;
		loadContent(url);
	});

	$("a[data-module]").click(function(){
		var self = $(this);
		
		self.closest("div").find("a[data-module]").removeClass("vtd");
		self.addClass("vtd");
		
		module = self.attr("data-module");
		pageindex = 0;
		
		$("#main").html("");
		var url = baseurl+"?module="+module+"&relaton="+relation+"&days="+days+"&pageindex="+pageindex+"&pagesize="+pagesize;
		loadContent(url);
	});
}

function mobile_index(){
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

		if (name.length == 0) {
			$("input[name$=name]").val($("input[name$=name]").attr("data-value"));
			alert("请输入用户名");
		} else if (pass.length == 0) {
			alert("请输入登录密码");
		} else {
			$.post("../user/login.shtml", {
				"user.name" : name,
				"user.password" : pass,
				"rememberme" : "1",
				"ord" : new Date().getTime()
			}, function(data) {
				if (data == "SUCCESS") {
					window.location.href = "../mobile/today.shtml";
				} else {
					alert(data);
				}
			});
		}
	});
}

function mobile_focus() {
	//点击退出按钮
	$(".nav_right a").click(function(){
		var popup = $(".nav_list");
		if(popup.is(":hidden")){
			$(".nav_list").show();
		}else{
			$(".nav_list").hide();
		}
	});
	
	//选中正在加载内容的按钮
	$("a[data-module]").removeClass("vtd");
	$("a[data-module="+module+"]").addClass("vtd");
	
	//弹窗显示相同标题的内容
	$("a[data-url]").click(function(){
		var self = $(this);
		var url = self.attr("data-url");
		$.get(url, function(data){
			$(".cb_list").remove();
			self.parent().parent().after(data);
		});
	});
}

function mobile_reg(){
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

    $("#loginbtn").click(function(){
        $("input[data-value]").each(function(){
            var self = $(this);
            var origin = self.attr("data-value");
            if(self.val().trim()==origin){
                self.val("");
            }
        });

        var name = $("input[name$=name]").val().trim();
        var checkCode = $("#checkCode").val();
        var company = $("input[name$=company]").val().trim();
        var mobile = $("input[name$=mobile]").val().trim();
        var tellphone = $("input[name$=tellphone]").val().trim();
        var email = $("input[name$=email]").val().trim();

        if (name.length == 0) {
            msgbox("请输入姓名", function(){$("input[data-value]").trigger("blur");});
        } else if (company.length == 0) {
            msgbox("请输入公司名称", function(){$("input[data-value]").trigger("blur");});
        } else if (mobile.length == 0) {
            msgbox("请输入手机号", function(){$("input[data-value]").trigger("blur");});
        } else if (tellphone.length == 0) {
            msgbox("请输入固定电话", function(){$("input[data-value]").trigger("blur");});
        } else if (email.length == 0) {
            msgbox("请输入邮箱", function(){$("input[data-value]").trigger("blur");});
        } else if (!(/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/.test(mobile))) {
            msgbox("请输入合法的11位手机号", function(){$("input[data-value]").trigger("blur");});
        } else if(!/^(0[0-9]{2,3}\-)?([2-9][0-9]{6,7})+(\-[0-9]{1,4})?$/.test(tellphone)){
            msgbox("请输入正确格式的固定电话", function(){$("input[data-value]").trigger("blur");});
        }else if (!(/^(\w)+(\.\w+)*@(\w)+((\.\w{2,3}){1,3})$/.test(email))) {
            msgbox("请输入合法的邮箱地址", function(){$("input[data-value]").trigger("blur");});
        } else if (checkCode.length == 0) {
            msgbox("请输入验证码", function(){$("input[data-value]").trigger("blur");});
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
                    $("#regdiv").hide();
                    $("#infodiv").show();
                } else {
                    msgbox(data);
                }
            });
        }

        if($("input[name=tuser\\.name]").val().trim().length<1){
            msgbox("请输入账号！", function(){$("input[data-value]").trigger("blur");});
        }else if($("input[name=tuser\\.company]").val().trim().length<1){
            msgbox("请输入公司名称！", function(){$("input[data-value]").trigger("blur");});
        }else if($("input[name=tuser\\.mobile]").val().trim().length<1){
            msgbox("请输入手机！", function(){$("input[data-value]").trigger("blur");});
        }else if($("input[name=tuser\\.tellphone]").val().trim().length<1){
            msgbox("请输入固话！", function(){$("input[data-value]").trigger("blur");});
        }else if($("input[name=tuser\\.email]").val().trim().length<1){
            msgbox("请输入邮箱！", function(){$("input[data-value]").trigger("blur");});
        }else {
            $("#submitbtn").trigger("click");
        }
    });
}


