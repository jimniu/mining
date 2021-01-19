// update.jsp
function network_info() {
    $("select[data-value]").each(function(){
        var self = $(this);
        var value = self.attr("data-value");
        self.val(value);
    });

	$("#submit").click(function() {
	  $("#submitbtn").trigger("click");
	});
}

function network_manage_add(){
    $("#checkbtn").click(function(){
        var name  = $("input[name$=name]").val();
        var company = $("input[name$=company]").val();
        var area = $("select[name$=area]").val();
        var contact = $("input[name$=contact]").val();
        var phone = $("input[name$=phone]").val();
        var priority = $("input[name$=priority]").val();
        var dailycard = $("input[name$=dailycard]").val();
        var columnkeyword = $("input[name$=columnkeyword]").val();
        var totalkeyword = $("input[name$=totalkeyword]").val();
        var keywordchange = $("input[name$=keywordchange]").val();
        var status = $("input[name$=status]:checked").val();
        var type = $("input[name$=type]:checked").val();
        var partnerid = $("select[name$=partnerid]").val();   
        
        var scope = ",";
        $("input[type=checkbox][name=scope]").each(function(){
        	var self = $(this);
        	var value = self.attr("value");
        	var checked = self.attr("checked");
        	if(checked=="checked"){
        		scope = scope+value+",";
        	}
        });  
        
        $.post("../network/manage_save.shtml", {
            "network.name" : name,
            "network.company" : company,
            "network.area" : area,
            "network.contact" :contact,
            "network.phone" :phone,
            "network.priority" :priority,
            "setting.dailycard" :dailycard,
            "setting.columnkeyword" : columnkeyword,
            "setting.totalkeyword" : totalkeyword,
            "setting.keywordchange" : keywordchange,
            "setting.scope" : scope, 
            "network.partnerid" : partnerid,
            "network.status" : status,
            "network.type" : type,
            "ord" : new Date().getTime()
        }, function(data){
            if(data.length>0){
                window.location.href="../network/manage_index.shtml";
            }
        });
    });
}

function network_manage_info(){
    $("select[data-value]").each(function(){
        var self = $(this);
        var value = self.attr("data-value");
        self.val(value);
    });

    $( "#startpoint" ).datepicker( {dateFormat: "yy-mm-dd 00:00:00"} );

    var value = $("[name$=type]").attr("data-value");
    $("[name$=type][value="+value+"]").attr("checked","checked");

    var value = $("[name$=status]").attr("data-value");
    $("[name$=status][value="+value+"]").attr("checked","checked");

    //判断抓取范围
    var scope = $("input[name=fetchscope]").val();
    $("input[type=checkbox][name=scope]").each(function(){
    	var self = $(this);
    	var value = self.attr("value");
    	if(scope.indexOf(","+value+",")>-1){
    		self.attr("checked", "checked");
    	}
    });
    
    $("#checkbtn").click(function(){
        var name  = $("input[name$=name]").val();
        var company = $("input[name$=company]").val();
        var area = $("select[name$=area]").val();
        var contact = $("input[name$=contact]").val();
        var phone = $("input[name$=phone]").val();
        var priority = $("input[name$=priority]").val();
        var dailycard = $("input[name$=dailycard]").val();
        var columnkeyword = $("input[name$=columnkeyword]").val();
        var totalkeyword = $("input[name$=totalkeyword]").val();
        var keywordchange = $("input[name$=keywordchange]").val();
        var partnerid = $("select[name$=partnerid]").val();
        var startpoint = $("input[name$=startpoint]").val();
        var status = $("input[name$=status]:checked").val();
        var type = $("input[name$=type]:checked").val();
        var id = $("input[name=networkid]").val();

        var scope = ",";
        $("input[type=checkbox][name=scope]").each(function(){
        	var self = $(this);
        	var value = self.attr("value");
        	var checked = self.attr("checked");
        	if(checked=="checked"){
        		scope = scope+value+",";
        	}
        });     
        
        $.post("../network/manage_update.shtml", {
            "network.name" : name,
            "network.company" : company,
            "network.area" : area,
            "network.contact" :contact,
            "network.phone" :phone,
            "network.priority" :priority,
            "setting.dailycard" :dailycard,
            "setting.columnkeyword" : columnkeyword,
            "setting.totalkeyword" : totalkeyword,
            "setting.keywordchange" : keywordchange,
            "setting.scope" : scope, 
            "setting.startpoint" : startpoint,
            "network.partnerid" : partnerid,
            "network.status" : status,
            "network.type" : type,
            "network.id" : id,
            "ord" : new Date().getTime()
        }, function(data){
            if(data.length>0){
                window.location.href="../network/manage_index.shtml";
            }
        });
    });
}

function network_manage_index(){

    $('tr:even').css('background','#EEEEEE');


    $("a[data-delete]").click(function(){
        var self = $(this);
        var id = self.attr("data-delete");

        confirm("您确认要删除该账户吗？",function() {
            var url = "../network/manage_delete.shtml?network.id=" + id + "&ord=" + new Date().getTime();
            $.get(url, function (data) {
                if (data.indexOf("success") > -1) {
                    self.parents("tr").hide();
                }
            });
        });
    });
    
    //选择账户状态的事件
    $("[data-status]").change(function(){
    	var self = $(this);
    	var status = self.val();
    	
    	window.location.href="../network/manage_index.shtml?network.status="+status;    
    });
    
    var status = $("[data-status]").attr("data-status");
    $("[data-status]").val(status);

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

