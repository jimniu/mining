// index.jsp
function auser_manage_index(){
	
	$("#search").click(function(){
		$("#form").submit();
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
			var page = $('#page').val() - 1;
			var url = $(".fy_div").attr("data-urlbase");
			if(page<0){
				page = 1;
			}
			var gourl = url+"&pageindex="+page;
			
			self.location.href= gourl;
			}
	});
	
	$("#selectAll").click(function(){
		var checked = $(this).attr("checked");
		if(checked=="checked"){
		  $('input[name="auserid"]').each(function(){   			
				$(this).attr("checked","checked");
			  });    
		}else{
			$('input[name="auserid"]').each(function(){   			
				$(this).attr("checked",null);
			  });    
		}
	});
	
	$("#delete").click(function(){
		  var s='';      
		  var flag = true;
		  $('input[name="auserid"]:checked').each(function(){   
			  if($(this).val() == $("#delete").attr("pos")){
				  msgbox("不能删除自己");
				  flag = false;
			  }
			 s+=$(this).val()+',';
		  });    
		  if(s!=null && flag){
			  $.post("../auser/manage_delete.shtml", {
				  "adminUser.temp" : s,
				  "ord" : new Date().getTime()
			  }, function(data){
				  if(data.length>0){
					  msgbox(data);
					  setTimeout(function(){
						  window.location.href="../auser/manage_index.shtml";	
					  }, 2000);		 
				  }			
			  });
		  }
	});
}


// add.jsp
function auser_manage_add() {
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

	$("#phone").blur(function() {
		var cellphone = $("#phone").val();
		if (!(/^1[3|5][0-9]\d{4,8}$/.test(cellphone))) {
			msgbox("请输入合法的11位手机号");
		}
	});

	$("#name").blur(function() {
		var name = $("input[name$=name]").val();
		if(name.length > 0 ){
			$.post("../auser/manage_checkname.shtml", {
				"adminUser.name" : name,
				"ord" : new Date().getTime()
			}, function(data) {
				if (data > 0) {
					msgbox("此用户已注册");
					flag = false;
				} else {
					flag = true;
				}
			});
		}
	});

	$("#submit").click(function() {
		var name = $("input[name$=name]").val();
		var password = $("input[name$=password]").val();
		var password1 = $("#pwd1").val();
		var fullName = $("#fullName").val();
		var phone = $("input[name$=phone]").val();
		var email = $("input[name$=email]").val();

		if (name.length == 0) {
			msgbox("请输入名字");
		} else if (!flag) {
			msgbox("请更换用户名");
		} else if (password.length == 0) {
			msgbox("请输入密码");
		} else if (password1.length == 0) {
			msgbox("请输入确认密码");
		} else if (fullName.length == 0) {
			msgbox("请输入全名");
		} else if (phone.length == 0) {
			msgbox("请输入手机");
		} else if (email.length == 0) {
			msgbox("请输入邮箱");
		} else if (!(/^1[3|5][0-9]\d{4,8}$/.test(phone))) {
			msgbox("请输入合法的11位手机号");
		} else if (!(/^(\w)+(\.\w+)*@(\w)+((\.\w{2,3}){1,3})$/.test(email))) {
			msgbox("请输入合法的邮箱地址");
		} else if ($("#readme").attr("checked") != "checked") {
			msgbox("请同意条款");
		} else {
			$(".zc_cgceng").css("display", "block");
			setTimeout(function(){
				$("#form").submit();
			}, 2000);
		}
	});
}

// update.jsp
function auser_manage_userinfo(){
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

	$("#phone").blur(function() {
		var cellphone = $("#phone").val();
		if (!(/^1[3|5][0-9]\d{4,8}$/.test(cellphone))) {
			msgbox("请输入合法的11位手机号");
		}
	});

	$("#name").blur(function() {
		var name = $("input[name$=name]").val();
		if(name.length > 0 ){
		$.post("../auser/manage_checkname.shtml", {
				"adminUser.name" : name,
				"ord" : new Date().getTime()
			}, function(data) {
				if (data > 0) {
					msgbox("此用户已注册");
					flag = false;
				} else {
					flag = true;
				}
			});
		}
	});

	$("#submit").click(function() {
		var name = $("input[name$=name]").val();
		var password = $("input[name$=password]").val();
		var password1 = $("#pwd1").val();
		var fullName = $("#fullName").val();
		var phone = $("input[name$=phone]").val();
		var email = $("input[name$=email]").val();

		if (name.length == 0) {
			msgbox("请输入名字");
		} else if (!flag) {
			msgbox("请更换用户名");
		} else if (password.length == 0) {
			msgbox("请输入密码");
		} else if (password1.length == 0) {
			msgbox("请输入确认密码");
		} else if (fullName.length == 0) {
			msgbox("请输入全名");
		} else if (phone.length == 0) {
			msgbox("请输入手机");
		} else if (email.length == 0) {
			msgbox("请输入邮箱");
		} else if (!(/^1[3|5][0-9]\d{4,8}$/.test(phone))) {
			msgbox("请输入合法的11位手机号");
		} else if (!(/^(\w)+(\.\w+)*@(\w)+((\.\w{2,3}){1,3})$/.test(email))) {
			msgbox("请输入合法的邮箱地址");
		} else if ($("#readme").attr("checked") != "checked") {
			msgbox("请同意条款");
		} else {
			$(".zc_cgceng").css("display", "block");
			setTimeout(function(){
				$("#form").submit();
			}, 2000);
		}
	});
}
