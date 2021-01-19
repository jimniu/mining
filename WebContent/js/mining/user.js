function user_message(){
	
	$("#list").each(function(){
		$(this).find("span").click(function(){	
			var pp = $(this).attr("pp");
			var pos = $(this).attr("pos")
			if(	pp == 0 ){
				$(".wdxx_text[pos="+pos+"]").show();
				$(this).attr("pp", 1);
			} else {
				$(".wdxx_text[pos="+pos+"]").hide();
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
		link_to:url+"pageindex=__id__",
		num_edge_entries: 0
	}); 
}

//  index.jsp
function user_index() {
	var flag = true;

	$("#pwd1").blur(function() {
		var pwd = $("#pwd").val();
		var pwd1 = $("#pwd1").val();
		if (pwd != pwd1) {
			msgbox("请保证两次输入的密码一致");
		}
	});

	$("#email").blur(function() {
		var email = $("#email").val();
		var reg = /^(\w)+(\.\w+)*@(\w)+((\.\w{2,3}){1,3})$/;
		if (!reg.test(email)) {
			msgbox("请输入合法的邮箱地址");
		}
	});

	$("#mobile").blur(function() {
		var mobile = $("#mobile").val();
		if (!(/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/.test(mobile))) {
			msgbox("请输入合法的11位手机号");
		}
	});

	$("#name").blur(function() {
		var name = $(this).val().trim();
		$.post("../user/checkname.shtml", {
			"user.name" : name,
			"ord" : new Date().getTime()
		}, function(data) {
			if (data > 0) {
				msgbox("此用户名已存在，请更换用户名");
				flag = false;
			} else {
				flag = true;
			}
		});
	});

	$("#submit").click(function() {
		var name = $("input[name$=name]").val().trim();
		var checkCode = $("#checkCode").val();
		var password = $("input[name$=password]").val().trim();
		var password1 = $("input[name =pwd1]").val().trim();
		var mobile = $("input[name$=mobile]").val();
		var email = $("input[name$=email]").val();

		if (name.length == 0) {
			msgbox("请输入登录用户名");
		} else if (!flag) {
			msgbox("该登录用户名已存在，请更换用户名");
		} else if (password.length == 0) {
			msgbox("请输入密码");
		} else if (password1.length == 0) {
			msgbox("请输入确认密码");
		} else if (mobile.length == 0) {
			msgbox("请输入手机");
		} else if (email.length == 0) {
			msgbox("请输入邮箱");
		} else if (!(/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/.test(mobile))) {
			msgbox("请输入合法的11位手机号");
		} else if (!(/^(\w)+(\.\w+)*@(\w)+((\.\w{2,3}){1,3})$/.test(email))) {
			msgbox("请输入合法的邮箱地址");
		} else if (checkCode.length == 0) {
			msgbox("请输入验证码");
		} else {
			$.post("../user/add.shtml", {
				"user.name" : name,
				"user.password" : password,
				"user.mobile" : mobile,
				"user.email" : email,
				"verifyCode" : checkCode,
				"ord" : new Date().getTime()
			}, function(data) {
				if (data == "SUCCESS") {
					$(".index_dl").css("display","block");
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
		window.location.href = "../site/index.shtml";
	});
	
}

// update.jsp
function user_info() {
	$("#pwd1").blur(function() {
		var pwd = $("#pwd").val().trim();
		var pwd1 = $("#pwd1").val().trim();
		if (pwd != pwd1) {
			msgbox("请保证两次输入的密码一致");
		}
	});

	$("#email").blur(function() {
		var email = $("#email").val();
		var reg = /^(\w)+(\.\w+)*@(\w)+((\.\w{2,3}){1,3})$/;
		if (!reg.test(email)) {
			msgbox("请输入合法的邮箱地址");
		}
	});

	$("#mobile").blur(function() {
		var mobile = $("#mobile").val();
		if (!(/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/.test(mobile))) {
			msgbox("请输入合法的11位手机号");
		}
	});

	$("#submit").click(function() {
		var password = $("input[name$=password]").val().trim();
		var password1 = $("input[name =pwd1]").val().trim();
		var mobile = $("input[name$=mobile]").val();
		var email = $("input[name$=email]").val();

		if (password.length == 0) {
			msgbox("请输入密码");
		} else if (password1.length == 0) {
			msgbox("请输入确认密码");
		} else if (mobile.length == 0) {
			msgbox("请输入公司电话");
		} else if (email.length == 0) {
			msgbox("请输入邮箱");
		} else if (!(/^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/.test(mobile))) {
			msgbox("请输入合法的11位手机号");
		} else if (!(/^(\w)+(\.\w+)*@(\w)+((\.\w{2,3}){1,3})$/.test(email))) {
			msgbox("请输入合法的邮箱地址");
		} else {
			  $("#submitbtn").trigger("click");
		}
	});
}

// getpwd.jsp
function user_getpwd() {
	$("#submit").click(function() {
		var name = $("input[name$=name]").val();
		var email = $("input[name$=email]").val();
		var checkCode = $("#checkCode").val();

		if (name.length == 0) {
			msgbox("请输入用户名");
		} else if (email.length == 0) {
			msgbox("请输入邮箱");
		} else if (!(/^(\w)+(\.\w+)*@(\w)+((\.\w{2,3}){1,3})$/.test(email))) {
			msgbox("请输入合法的邮箱地址");
		} else if (checkCode == "请输入验证码") {
			msgbox("请输入验证码");
		} else {
			$.post("../user/checkuser.shtml", {
				"user.name" : name,
				"user.email" : email,
				"verifyCode" : checkCode,
				"ord" : new Date().getTime()
			}, function(data) {
				if (data == "SUCCESS") {
					window.location.href = "../user/resetpwd.shtml";
				} else {
					msgbox(data);
				}
			});
		}
	});

	$("#checkCode").focus(function() {
		var checkCode = $("#checkCode").val();
		if (checkCode == "请输入验证码") {
			$("#checkCode").attr("value", "");
		}
	});

	$("#checkCode").blur(function() {
		var checkCode = $("#checkCode").val().trim();
		if (checkCode == "") {
			$("#checkCode").attr("value", "请输入验证码");
		}
	});
}

//  resetpwd.jsp
function user_resetpwd() {
	$("#pwd1").blur(function() {
		var pwd = $("#pwd").val();
		var pwd1 = $("#pwd1").val();
		if (pwd != pwd1) {
			msgbox("请保证两次输入的密码一致");
		}
	});

	$("#submit").click(function() {
		var password = $("input[name$=password]").val();
		var password1 = $("input[name =pwd1]").val();

		if (password.length == 0) {
			msgbox("请输入密码");
		} else if (password1.length == 0) {
			msgbox("请输入确认密码");
		} else if (password != password1) {
			msgbox("请保证两次输入的密码一致");
		} else {
			$.post("../user/updatepwd.shtml", {
				"user.password" : password,
				"ord" : new Date().getTime()
			}, function(data) {
				if (data == "SUCCESS") {
					window.location.href = "../user/waiting.shtml";
				} else {
					msgbox(data);
				}
			});
		}
	});
}

//index.jsp
function user_manage_index(){
 	//下拉框回填
	$("select[data-value]").each(function(){
		var self = $(this);
		self.val(self.attr("data-value"));
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
	
	$("#goto").click(function(){
		if(!isNaN($('#page').val())){
		var page = $('#page').val()-1;
		var url = $(".fy_div").attr("data-urlbase");
		if(page<0){
			page = 1;
		}
		var gourl = url+"&pageindex="+page;
		
		self.location.href= gourl;
		}
	});
	
	$("#search").click(function(){
		$("#form").submit();
	});
	
	$("#selectAll").click(function(){
		var checked = $(this).attr("checked");
		if(checked=="checked"){
		  $('input[name="userid"]').each(function(){   			
				$(this).attr("checked","checked");
			  });    
		}else{
			$('input[name="userid"]').each(function(){   			
				$(this).attr("checked",null);
			  });    
		}
	});

    $("a[data-delete]").click(function(){
        var self = $(this);
        var id = self.attr("data-delete");

        confirm("您确认要删除该账户吗？",function() {
            var url = "../user/manage_delete.shtml?user.id=" + id + "&ord=" + new Date().getTime();
            $.get(url, function (data) {
                if (data.indexOf("success") > -1) {
                    self.parents("tr").hide();
                }
            });
        });
    });
}

function user_manage_userinfo(){
	var type = $("input[data-type]").attr("data-type");
	$("input[name$=type][value="+type+"]").attr("checked","checked");

    var status = $("input[data-status]").attr("data-status");
    $("input[name$=status][value="+status+"]").attr("checked","checked");

	var networkid = $("select[name$=networkid]").attr("data-value");
    $("select[name$=networkid]").val(networkid);

	$("#checkbtn").click(function(){
		var id  = $("input[name$=id]").val();
		var expire = $("input[name$=expire]").val();
        var type = $("input[name$=type]:checked").val();
        var status = $("input[name$=status]:checked").val();
        var networkid = $("select[name$=networkid]").val();

        if(expire.trim().length==0){
        	msgbox("请输入账户失效时间");
        	return;
		}
		$.post("../user/manage_review.shtml", {
			  "user.id" : id,
			  "user.type" : type,
			  "user.networkid" : networkid,
			  "user.expire" :expire,
			  "user.status" : status,
			  "ord" : new Date().getTime()
		}, function(data){
			  if(data.length>0){
				  window.location.href="../user/manage_index.shtml";	
			  }			
		});
	});
}
