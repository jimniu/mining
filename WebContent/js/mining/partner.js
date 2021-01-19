function partner_manage_add(){
	$("#submit").click(function() {	 
		var name = $("input[name$=name]").val();
		var title = $("input[name$=title]").val();	
		var logo = $("input[name$=logo]").val();
		var domain = $("input[name$=domain]").val();
		
		if (name.length == 0) {
			msgbox("请输入合作伙伴名称");
		}else if (domain.length==0) {
			msgbox("请输入合作伙伴的域名");
		} else if (logo.length==0) {
			msgbox("请输入合作伙伴的logo地址");
		}else {		
			$("#form").submit();
		}
	});
}

function partner_manage_edit(){
	$("#submit").click(function() {	 
		var name = $("input[name$=name]").val();
		var title = $("input[name$=title]").val();	
		var logo = $("input[name$=logo]").val();
		var domain = $("input[name$=domain]").val();
		
		if (name.length == 0) {
			msgbox("请输入合作伙伴名称");
		} else if (domain.length==0) {
			msgbox("请输入合作伙伴的域名");
		} else if (logo.length==0) {
			msgbox("请输入合作伙伴的logo地址");
		}else {		
			$("#form").submit();
		}
	});
}