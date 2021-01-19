$(document).ready(function($){
	//自动加载模块中的js函数
	if(eval("typeof " +MODULE+"_"+PAGE) == "function"){
		eval(MODULE+"_"+PAGE+"()");
	}
	//显示出错信息
	if(ERRMSG!=null&&ERRMSG.length>0){
		msgbox(ERRMSG);
	}
	
	if($(".dsj_nav_bt").size()>0){
		$("div.dsj_nav_bt a").click(function(){
			$.get("../site/popup.shtml", function(data){
				$(".list_right").prepend(data);
				//隐藏按钮
				$("div.dsj_nav_bt").hide();
				//定义拖拽
				//$(".t").draggable();
				//定义关闭按钮事件
				$(".t_bt").click(function(){
					$(".t").remove();
					$("div.dsj_nav_bt").show();
				});

				//当前所在的内容类型
				var type = 0;
				
				//选择今日焦点或负面
				$("div.t a[data-url]").click(function(){
					var self = $(this);
					var module = $("div.t .fl_vtd").attr("data-module");
					var days = $("div.t select[name=days]").val();
					type = self.attr("data-type");

                    //加载栏目
                    $.get("../site/columnlist.shtml?module="+module+"&type="+type, function(data){
                        $("select#columnlist").html(data);
                    });

                    //加载内容
					var url = self.attr("data-url")+"&type="+type+"&module="+module+"&days="+days+"&ord="+(new Date()).getTime();
					$("div.t a[data-url]").removeClass("t_link_vtd");
					self.addClass("t_link_vtd");
					$("div.t .t_list").html("");
					$.get(url, function(data){
						$("div.t .t_list").html(data);
						showSimilar();
					});
				});

				//选择时间范围
                $("div.t select[name=days]").change(function(){
                    var self = $(this);
                    var module = $("div.t .fl_vtd").attr("data-module");
                    var days = $("div.t select[name=days]").val();
                    var columnid = $("div.t select[name=columnid]").val();
                    var url = $("div.t a.t_link_vtd").attr("data-url")+"&type="+type+"&columnid="+columnid+"&module="+module+"&days="+days+"&ord="+(new Date()).getTime();
                    $("div.t .t_list").html("");
                    $.get(url, function(data){
                        $("div.t .t_list").html(data);
                        showSimilar();
                    });
				});

                //选择栏目
                $("div.t select[name=columnid]").change(function(){
                    var self = $(this);
                    var module = $("div.t .fl_vtd").attr("data-module");
                    var days = $("div.t select[name=days]").val();
                    var columnid = $("div.t select[name=columnid]").val();
                    var url = $("div.t a.t_link_vtd").attr("data-url")+"&type="+type+"&columnid="+columnid+"&module="+module+"&days="+days+"&ord="+(new Date()).getTime();
                    $("div.t .t_list").html("");
                    $.get(url, function(data){
                        $("div.t .t_list").html(data);
                        showSimilar();
                    });
                });
				
				//选择模块
				$("div.t a[data-module]").click(function(){
					var self = $(this);
					var module = self.attr("data-module");
                    var days = $("div.t select[name=days]").val();

                    //加载栏目
                    $.get("../site/columnlist.shtml?module="+module+"&type="+type, function(data){
                        $("select#columnlist").html(data);
                    });

                    //加载内容
					var url = $("div.t a.t_link_vtd").attr("data-url")+"&type="+type+"&module="+module+"&days="+days+"&ord="+(new Date()).getTime();
					$("div.t a[data-module").removeClass("fl_vtd");
					self.addClass("fl_vtd");
					$("div.t .t_list").html("");
					$.get(url, function(data){
						$("div.t .t_list").html(data);
						showSimilar();
					});
				});

				//点击传播数时，列出相关内容
				var showSimilar = function(){
					$(".t_list_one a[data-url]").click(function(){
						var self = $(this);
						var url = self.attr("data-url");
						$.get(url, function(data){
							$(".cb_list").remove();
							self.parent().parent().after(data);
						});
					});
				}

                $("div.t a.t_link_vtd").trigger("click");
                $("div.t a.fl_vtd").trigger("click");
			});
		});
		
		//快捷方式定义拖拽支持
		/*$(".dsj_nav_bt").draggable({
			stop: function( event, ui ) {
				var top = ui.position.top;
				var left = ui.position.left;
				SetCookie("navbtn_pos", left+"x"+top);
				event.stopPropagation();
			}
		});*/
		//根据cookie的记录结果来确定是否打开快捷方式并且恢复其位置
		/*var navbtn_pos = GetCookie("navbtn_pos");
		if(navbtn_pos!=null&&navbtn_pos.indexOf("x">-1)){
			var arr = navbtn_pos.split("x");
			var left = arr[0];
			var top = arr[1];
			$(".dsj_nav_bt").css("left",left+"px");
			$(".dsj_nav_bt").css("top", top+"px");
		}*/
	}
	

	
	//收起/下拉按钮
	$("div.wbwj_top_bt a").click(function(){
		var self = $(this);
		if(self.text()=="展开"){
			$("div.wbwj_pic").show("slow");
			self.css("background", "url(../images/wbwj_up_bt.png) no-repeat left center");
			self.text("收起");
		} else if(self.text()=="收起"){
			$("div.wbwj_pic").hide("slow");
			self.css("background", "url(../images/wbwj_dow_bt.png) no-repeat left center");
			self.text("展开");
		}
	});
	
	//三级菜单的显示/关闭
	$("div.list h2").click(function(){
		var self = $(this);
		var menu = self.siblings("div.text");
		if(menu.is(":visible")){
			menu.hide("normal");
		}else{
			menu.show("normal");
		}
	});
	
	//打开页面时，显示选中的三级菜单
	$("div[data-module="+MODULE+"]").show();
	//选中当前的模块菜单
	$(".list_left a[href*="+MODULE+"\\/"+PAGE+"]").addClass("vtd");
	$("div.dsj_nav a[href*="+MODULE+"]").addClass("vtd");
	
	//设定日期选择器的语言
	$.datepicker.setDefaults( $.datepicker.regional[ "zh-CN" ] );
});
 
//绑定回车事件
function enterEvent(obj){
	$(document).unbind("keydown");
	$(document).bind("keydown", function(e){ 
		if(e.keyCode==13) {
			obj.focus();
	        obj.trigger("click");
	    }
	});
}

//提示窗口
function msgbox(msg, func) {
	var index = layer.alert(msg, {
        title: '提示',
        anim: 5,
		closeBtn: 1
	}, function(){
		if(typeof func === 'function'){
			func();
		}
		layer.close(index);
	});
}

function confirm(msg,func){
	var index = layer.confirm(msg, {
		title: '确认',
		btn: ['继续','放弃'] //按钮
	}, function(){
		func();
		layer.close(index);
	}, function(){
		layer.close(index);
	});
}

//将用户的地理信息保存到cookie
function getLocation(){
	if(typeof(remote_ip_info)!="undefined"){
		var city = remote_ip_info.city;
		var prov = remote_ip_info.province;
		SetCookie("isd_city", city);
		SetCookie("isd_prov", prov);
	}
}

//根据日期和时间，得到Date对象
function getDate(date, time) {
	if(time.length == 5){
		time = time+":00";
	}
	else if(time == null || time.length==0)
	{
		time = "00:00:00";
	}
	time = date + " " + time;
	var reg = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})/;
	var r = time.match(reg);
	var result = new Date();
	if(r!=null){
		result = new Date(r[1], r[3]-1,r[4],r[5],r[6],r[7]);
	}
	return result;
}

//判断字符串的长度，汉字作为两个字节处理
function JHshStrLen(sString) {
	var sStr, iCount, i, strTemp;
	iCount = 0;
	sStr = sString.split("");
	for (i = 0; i < sStr.length; i++) {
		strTemp = escape(sStr[i]);
		if (strTemp.indexOf("%u", 0) == -1) {
			iCount = iCount + 1;
		} else {
			iCount = iCount + 2;
		}
	}
	return iCount;
}

function substring(str,n){
  	var r=/[^\x00-\xff]/g;
  	if(str.replace(r,"mm").length<=n){
  		return str;
  	}
 	var m=Math.floor(n/2);
  	for(var i=m;i<str.length;i++){
		if(str.substr(0,i).replace(r,"mm").length>=n-3){
			return str.substr(0,i)+"...";
      	}
  	}
  	return str;
}

String.prototype.startWith=function(str){     
  var reg=new RegExp("^"+str);     
  return reg.test(this);        
}  

String.prototype.endWith=function(str){     
  var reg=new RegExp(str+"$");     
  return reg.test(this);        
} 

String.prototype.trim = function () {
	return this.replace(/(^\s*)|(\s*$)/g, "");
};

String.prototype.replaceAll = function(s1,s2){
	return this.replace(new RegExp(s1,"gmi"),s2);
}

//设置Cookie
function SetCookie(inName, inValue) {
	var expDate = new Date();
	expDate.setDate(expDate.getDate() + 3650);
	document.cookie = inName + "=" + encodeURI(inValue) + "; expires=" + expDate.toGMTString() + "; path=/";
} 

// 读取cookie
function GetCookie(inName) {
  // cookies are separated by semicolons
	var m_cookie = document.cookie.split("; ");
	for (var i = 0; i < m_cookie.length; i++) {
    // a name/value pair (a crumb) is separated by an equal sign
		var m_crumb = m_cookie[i].split("=");
		if (inName == m_crumb[0]) {
			var val = unescape(m_crumb[1]);
			val = val.replace(/^"(.*)"$/g, "$1");
			return val;
		}
	}

  // a cookie with the requested name does not exist
	return null;
}

//动态加载script
function loadScript(url){
	var js = document.createElement("script");
	js.type = "text/javascript";
	js.async = 1;
	js.src = url;
	document.getElementsByTagName("head")[0].appendChild(js);
}

chartcolor = ["#ff7f50","#87cefa","#da70d6","#32cd32","#6495ed",
		 	  "#ff69b4","#ba55d3","#cd5c5c","#ffa500","#40e0d0",
		      "#1e90ff","#ff6347","#7b68ee","#00fa9a","#ffd700",
		      "#6699FF","#ff6666","#3cb371","#b8860b","#30e0e0"];

PROVINCES = [
	{name: "省份", value: -1},
	{name: "北京", value: 1},
	{name: "上海", value: 2},
	{name: "天津", value: 3},
	{name: "重庆", value: 4},
	{name: "河北", value: 5},
	{name: "山西", value: 6},
	{name: "内蒙古", value: 7},
	{name: "辽宁", value: 8},
	{name: "吉林", value: 9},
	{name: "黑龙江", value: 10},
	{name: "江苏", value: 11},
	{name: "浙江", value: 12},
	{name: "安徽", value: 13},
	{name: "福建", value: 14},
	{name: "江西", value: 15},
	{name: "山东", value: 16},
	{name: "河南", value: 17},
	{name: "湖北", value: 18},
	{name: "湖南", value: 19},
	{name: "广东", value: 20},
	{name: "广西", value: 21},
	{name: "海南", value: 22},
	{name: "四川", value: 23},
	{name: "贵州", value: 24},
	{name: "云南", value: 25},
	{name: "西藏", value: 26},
	{name: "陕西", value: 27},
	{name: "甘肃", value: 28},
	{name: "宁夏", value: 29},
	{name: "青海", value: 30},
	{name: "新疆", value: 31},
	{name: "香港", value: 32},
	{name: "澳门", value: 33},
	{name: "台湾", value: 34},
	{name: "海外", value: 35},
	{name: "其他", value: 0}
];

LOCATION = Array();
	LOCATION[0] = Array();
		LOCATION[0][0] = "东城区";
		LOCATION[0][1] = "西城区";
		LOCATION[0][2] = "崇文区";
		LOCATION[0][3] = "宣武区";
		LOCATION[0][4] = "朝阳区";
		LOCATION[0][5] = "丰台区";
		LOCATION[0][6] = "石景山区";
		LOCATION[0][7] = "海淀区";
		LOCATION[0][8] = "门头沟区";
		LOCATION[0][9] = "房山区";
		LOCATION[0][10] = "通州区";
		LOCATION[0][11] = "顺义区";
		LOCATION[0][12] = "昌平区";
		LOCATION[0][13] = "大兴区";
		LOCATION[0][14] = "怀柔区";
		LOCATION[0][15] = "平谷区";
		LOCATION[0][16] = "密云县";
		LOCATION[0][17] = "延庆县";
	LOCATION[1] = Array();
		LOCATION[1][0] = "黄浦区";
		LOCATION[1][1] = "卢湾区";
		LOCATION[1][2] = "徐汇区";
		LOCATION[1][3] = "长宁区";
		LOCATION[1][4] = "静安区";
		LOCATION[1][5] = "普陀区";
		LOCATION[1][6] = "闸北区";
		LOCATION[1][7] = "虹口区";
		LOCATION[1][8] = "杨浦区";
		LOCATION[1][9] = "闵行区";
		LOCATION[1][10] = "宝山区";
		LOCATION[1][11] = "嘉定区";
		LOCATION[1][12] = "浦东新区";
		LOCATION[1][13] = "金山区";
		LOCATION[1][14] = "松江区";
		LOCATION[1][15] = "青浦区";
		LOCATION[1][16] = "南汇区";
		LOCATION[1][17] = "奉贤区";
		LOCATION[1][18] = "崇明县";
	LOCATION[2] = Array();
		LOCATION[2][0] = "和平区";
		LOCATION[2][1] = "河东区";
		LOCATION[2][2] = "河西区";
		LOCATION[2][3] = "南开区";
		LOCATION[2][4] = "河北区";
		LOCATION[2][5] = "红桥区";
		LOCATION[2][6] = "塘沽区";
		LOCATION[2][7] = "汉沽区";
		LOCATION[2][8] = "大港区";
		LOCATION[2][9] = "东丽区";
		LOCATION[2][10] = "西青区";
		LOCATION[2][11] = "津南区";
		LOCATION[2][12] = "北辰区";
		LOCATION[2][13] = "武清区";
		LOCATION[2][14] = "宝坻区";
		LOCATION[2][15] = "宁河县";
		LOCATION[2][16] = "静海县";
		LOCATION[2][17] = "蓟县";
	LOCATION[3] = Array();
		LOCATION[3][0] = "万州区";
		LOCATION[3][1] = "涪陵区";
		LOCATION[3][2] = "渝中区";
		LOCATION[3][3] = "大渡口区";
		LOCATION[3][4] = "江北区";
		LOCATION[3][5] = "沙坪坝区";
		LOCATION[3][6] = "九龙坡区";
		LOCATION[3][7] = "南岸区";
		LOCATION[3][8] = "北碚区";
		LOCATION[3][9] = "万盛区";
		LOCATION[3][10] = "双桥区";
		LOCATION[3][11] = "渝北区";
		LOCATION[3][12] = "巴南区";
		LOCATION[3][13] = "黔江区";
		LOCATION[3][14] = "长寿区";
		LOCATION[3][15] = "綦江县";
		LOCATION[3][16] = "潼南县";
		LOCATION[3][17] = "铜梁县";
		LOCATION[3][18] = "大足县";
		LOCATION[3][19] = "荣昌县";
		LOCATION[3][20] = "璧山县";
		LOCATION[3][21] = "梁平县";
		LOCATION[3][22] = "城口县";
		LOCATION[3][23] = "丰都县";
		LOCATION[3][24] = "垫江县";
		LOCATION[3][25] = "武隆县";
		LOCATION[3][26] = "忠县";
		LOCATION[3][27] = "开县";
		LOCATION[3][28] = "云阳县";
		LOCATION[3][29] = "奉节县";
		LOCATION[3][30] = "巫山县";
		LOCATION[3][31] = "巫溪县";
		LOCATION[3][32] = "石柱土家族自治县";
		LOCATION[3][33] = "秀山土家族苗族自治县";
		LOCATION[3][34] = "酉阳土家族苗族自治县";
		LOCATION[3][35] = "彭水苗族土家族自治县";
		LOCATION[3][36] = "江津市";
		LOCATION[3][37] = "合川市";
		LOCATION[3][38] = "永川市";
		LOCATION[3][39] = "南川市";
	LOCATION[4] = Array();
		LOCATION[4][0] = "石家庄";
		LOCATION[4][1] = "邯郸";
		LOCATION[4][2] = "邢台";
		LOCATION[4][3] = "保定";
		LOCATION[4][4] = "张家口";
		LOCATION[4][5] = "承德";
		LOCATION[4][6] = "廊坊";
		LOCATION[4][7] = "唐山";
		LOCATION[4][8] = "秦皇岛";
		LOCATION[4][9] = "沧州";
		LOCATION[4][10] = "衡水";
	LOCATION[5] = Array();
		LOCATION[5][0] = "太原";
		LOCATION[5][1] = "大同";
		LOCATION[5][2] = "阳泉";
		LOCATION[5][3] = "长治";
		LOCATION[5][4] = "晋城";
		LOCATION[5][5] = "朔州";
		LOCATION[5][6] = "吕梁";
		LOCATION[5][7] = "忻州";
		LOCATION[5][8] = "晋中";
		LOCATION[5][9] = "临汾";
		LOCATION[5][10] = "运城";
	LOCATION[6] = Array();
		LOCATION[6][0] = "呼和浩特";
		LOCATION[6][1] = "包头";
		LOCATION[6][2] = "乌海";
		LOCATION[6][3] = "赤峰";
		LOCATION[6][4] = "呼伦贝尔";
		LOCATION[6][5] = "阿拉善盟";
		LOCATION[6][6] = "通辽";
		LOCATION[6][7] = "兴安盟";
		LOCATION[6][8] = "乌兰察布盟";
		LOCATION[6][9] = "锡林郭勒盟";
		LOCATION[6][10] = "巴彦淖尔盟";
		LOCATION[6][11] = "鄂尔多斯";
	LOCATION[7] = Array();
		LOCATION[7][0] = "沈阳";
		LOCATION[7][1] = "大连";
		LOCATION[7][2] = "鞍山";
		LOCATION[7][3] = "抚顺";
		LOCATION[7][4] = "本溪";
		LOCATION[7][5] = "丹东";
		LOCATION[7][6] = "锦州";
		LOCATION[7][7] = "营口";
		LOCATION[7][8] = "阜新";
		LOCATION[7][9] = "辽阳";
		LOCATION[7][10] = "盘锦";
		LOCATION[7][11] = "铁岭";
		LOCATION[7][12] = "朝阳";
		LOCATION[7][13] = "葫芦岛";
	LOCATION[8] = Array();
		LOCATION[8][0] = "长春";
		LOCATION[8][1] = "吉林";
		LOCATION[8][2] = "四平";
		LOCATION[8][3] = "辽源";
		LOCATION[8][4] = "通化";
		LOCATION[8][5] = "白山";
		LOCATION[8][6] = "松原";
		LOCATION[8][7] = "白城";
		LOCATION[8][8] = "延边";
	LOCATION[9] = Array();
		LOCATION[9][0] = "哈尔滨";
		LOCATION[9][1] = "齐齐哈尔";
		LOCATION[9][2] = "牡丹江";
		LOCATION[9][3] = "佳木斯";
		LOCATION[9][4] = "大庆";
		LOCATION[9][5] = "绥化";
		LOCATION[9][6] = "鹤岗";
		LOCATION[9][7] = "鸡西";
		LOCATION[9][8] = "黑河";
		LOCATION[9][9] = "双鸭山";
		LOCATION[9][10] = "伊春";
		LOCATION[9][11] = "七台河";
		LOCATION[9][12] = "大兴安岭";
	LOCATION[10] = Array();
		LOCATION[10][0] = "南京";
		LOCATION[10][1] = "镇江";
		LOCATION[10][2] = "苏州";
		LOCATION[10][3] = "南通";
		LOCATION[10][4] = "扬州";
		LOCATION[10][5] = "盐城";
		LOCATION[10][6] = "徐州";
		LOCATION[10][7] = "连云港";
		LOCATION[10][8] = "常州";
		LOCATION[10][9] = "无锡";
		LOCATION[10][10] = "宿迁";
		LOCATION[10][11] = "泰州";
		LOCATION[10][12] = "淮安";
	LOCATION[11] = Array();
		LOCATION[11][0] = "杭州";
		LOCATION[11][1] = "宁波";
		LOCATION[11][2] = "温州";
		LOCATION[11][3] = "嘉兴";
		LOCATION[11][4] = "湖州";
		LOCATION[11][5] = "绍兴";
		LOCATION[11][6] = "金华";
		LOCATION[11][7] = "衢州";
		LOCATION[11][8] = "舟山";
		LOCATION[11][9] = "台州";
		LOCATION[11][10] = "丽水";
	LOCATION[12] = Array();
		LOCATION[12][0] = "合肥";
		LOCATION[12][1] = "芜湖";
		LOCATION[12][2] = "蚌埠";
		LOCATION[12][3] = "马鞍山";
		LOCATION[12][4] = "淮北";
		LOCATION[12][5] = "铜陵";
		LOCATION[12][6] = "安庆";
		LOCATION[12][7] = "黄山";
		LOCATION[12][8] = "滁州";
		LOCATION[12][9] = "宿州";
		LOCATION[12][10] = "池州";
		LOCATION[12][11] = "淮南";
		LOCATION[12][12] = "巢湖";
		LOCATION[12][13] = "阜阳";
		LOCATION[12][14] = "六安";
		LOCATION[12][15] = "宣城";
		LOCATION[12][16] = "亳州";
	LOCATION[13] = Array();
		LOCATION[13][0] = "福州";
		LOCATION[13][1] = "厦门";
		LOCATION[13][2] = "莆田";
		LOCATION[13][3] = "三明";
		LOCATION[13][4] = "泉州";
		LOCATION[13][5] = "漳州";
		LOCATION[13][6] = "南平";
		LOCATION[13][7] = "龙岩";
		LOCATION[13][8] = "宁德";
	LOCATION[14] = Array();
		LOCATION[14][0] = "南昌";
		LOCATION[14][1] = "景德镇";
		LOCATION[14][2] = "九江";
		LOCATION[14][3] = "鹰潭";
		LOCATION[14][4] = "萍乡";
		LOCATION[14][5] = "新馀";
		LOCATION[14][6] = "赣州";
		LOCATION[14][7] = "吉安";
		LOCATION[14][8] = "宜春";
		LOCATION[14][9] = "抚州";
		LOCATION[14][10] = "上饶";
	LOCATION[15] = Array();
		LOCATION[15][0] = "济南";
		LOCATION[15][1] = "青岛";
		LOCATION[15][2] = "淄博";
		LOCATION[15][3] = "枣庄";
		LOCATION[15][4] = "东营";
		LOCATION[15][5] = "烟台";
		LOCATION[15][6] = "潍坊";
		LOCATION[15][7] = "济宁";
		LOCATION[15][8] = "泰安";
		LOCATION[15][9] = "威海";
		LOCATION[15][10] = "日照";
		LOCATION[15][11] = "莱芜";
		LOCATION[15][12] = "临沂";
		LOCATION[15][13] = "德州";
		LOCATION[15][14] = "聊城";
		LOCATION[15][15] = "滨州";
		LOCATION[15][16] = "菏泽";
	LOCATION[16] = Array();
		LOCATION[16][0] = "郑州";
		LOCATION[16][1] = "开封";
		LOCATION[16][2] = "洛阳";
		LOCATION[16][3] = "平顶山";
		LOCATION[16][4] = "安阳";
		LOCATION[16][5] = "鹤壁";
		LOCATION[16][6] = "新乡";
		LOCATION[16][7] = "焦作";
		LOCATION[16][8] = "濮阳";
		LOCATION[16][9] = "许昌";
		LOCATION[16][10] = "漯河";
		LOCATION[16][11] = "三门峡";
		LOCATION[16][12] = "南阳";
		LOCATION[16][13] = "商丘";
		LOCATION[16][14] = "信阳";
		LOCATION[16][15] = "周口";
		LOCATION[16][16] = "驻马店";
		LOCATION[16][17] = "济源";
	LOCATION[17] = Array();
		LOCATION[17][0] = "武汉";
		LOCATION[17][1] = "鄂州";
		LOCATION[17][2] = "宜昌";
		LOCATION[17][3] = "荆州";
		LOCATION[17][4] = "襄樊";
		LOCATION[17][5] = "黄石";
		LOCATION[17][6] = "荆门";
		LOCATION[17][7] = "黄冈";
		LOCATION[17][8] = "十堰";
		LOCATION[17][9] = "恩施";
		LOCATION[17][10] = "潜江";
		LOCATION[17][11] = "天门";
		LOCATION[17][12] = "仙桃";
		LOCATION[17][13] = "随州";
		LOCATION[17][14] = "咸宁";
		LOCATION[17][15] = "孝感";
	LOCATION[18] = Array();
		LOCATION[18][0] = "长沙";
		LOCATION[18][1] = "常德";
		LOCATION[18][2] = "株洲";
		LOCATION[18][3] = "湘潭";
		LOCATION[18][4] = "衡阳";
		LOCATION[18][5] = "岳阳";
		LOCATION[18][6] = "邵阳";
		LOCATION[18][7] = "益阳";
		LOCATION[18][8] = "娄底";
		LOCATION[18][9] = "怀化";
		LOCATION[18][10] = "郴州";
		LOCATION[18][11] = "永州";
		LOCATION[18][12] = "湘西";
		LOCATION[18][13] = "张家界";
	LOCATION[19] = Array();
		LOCATION[19][0] = "广州";
		LOCATION[19][1] = "深圳";
		LOCATION[19][2] = "珠海";
		LOCATION[19][3] = "汕头";
		LOCATION[19][4] = "东莞";
		LOCATION[19][5] = "中山";
		LOCATION[19][6] = "佛山";
		LOCATION[19][7] = "韶关";
		LOCATION[19][8] = "江门";
		LOCATION[19][9] = "湛江";
		LOCATION[19][10] = "茂名";
		LOCATION[19][11] = "肇庆";
		LOCATION[19][12] = "惠州";
		LOCATION[19][13] = "梅州";
		LOCATION[19][14] = "汕尾";
		LOCATION[19][15] = "河源";
		LOCATION[19][16] = "阳江";
		LOCATION[19][17] = "清远";
		LOCATION[19][18] = "潮州";
		LOCATION[19][19] = "揭阳";
		LOCATION[19][20] = "云浮";
	LOCATION[20] = Array();
		LOCATION[20][0] = "南宁";
		LOCATION[20][1] = "柳州";
		LOCATION[20][2] = "桂林";
		LOCATION[20][3] = "梧州";
		LOCATION[20][4] = "北海";
		LOCATION[20][5] = "防城港";
		LOCATION[20][6] = "钦州";
		LOCATION[20][7] = "贵港";
		LOCATION[20][8] = "玉林";
		LOCATION[20][9] = "南宁地区";
		LOCATION[20][10] = "柳州地区";
		LOCATION[20][11] = "贺州";
		LOCATION[20][12] = "百色";
		LOCATION[20][13] = "河池";
	LOCATION[21] = Array();
		LOCATION[21][0] = "海口";
		LOCATION[21][1] = "三亚";
		LOCATION[21][2] = "其他";
	LOCATION[22] = Array();
		LOCATION[22][0] = "成都";
		LOCATION[22][1] = "绵阳";
		LOCATION[22][2] = "德阳";
		LOCATION[22][3] = "自贡";
		LOCATION[22][4] = "攀枝花";
		LOCATION[22][5] = "广元";
		LOCATION[22][6] = "内江";
		LOCATION[22][7] = "乐山";
		LOCATION[22][8] = "南充";
		LOCATION[22][9] = "宜宾";
		LOCATION[22][10] = "广安";
		LOCATION[22][11] = "达州";
		LOCATION[22][12] = "雅安";
		LOCATION[22][13] = "眉山";
		LOCATION[22][14] = "甘孜";
		LOCATION[22][15] = "凉山";
		LOCATION[22][16] = "泸州";
		LOCATION[22][17] = "遂宁"
		LOCATION[22][18] = "巴中"
		LOCATION[22][19] = "资阳"
		LOCATION[22][20] = "阿坝"
	LOCATION[23] = Array();
		LOCATION[23][0] = "贵阳";
		LOCATION[23][1] = "六盘水";
		LOCATION[23][2] = "遵义";
		LOCATION[23][3] = "安顺";
		LOCATION[23][4] = "铜仁";
		LOCATION[23][5] = "黔西南";
		LOCATION[23][6] = "毕节";
		LOCATION[23][7] = "黔东南";
		LOCATION[23][8] = "黔南";
	LOCATION[24] = Array();
		LOCATION[24][0] = "昆明";
		LOCATION[24][1] = "大理";
		LOCATION[24][2] = "曲靖";
		LOCATION[24][3] = "玉溪";
		LOCATION[24][4] = "昭通";
		LOCATION[24][5] = "楚雄";
		LOCATION[24][6] = "红河";
		LOCATION[24][7] = "文山";
		LOCATION[24][8] = "思茅";
		LOCATION[24][9] = "西双版纳";
		LOCATION[24][10] = "保山";
		LOCATION[24][11] = "德宏";
		LOCATION[24][12] = "丽江";
		LOCATION[24][13] = "怒江";
		LOCATION[24][14] = "迪庆";
		LOCATION[24][15] = "临沧";
	LOCATION[25] = Array();
		LOCATION[25][0] = "拉萨";
		LOCATION[25][1] = "日喀则";
		LOCATION[25][2] = "山南";
		LOCATION[25][3] = "林芝";
		LOCATION[25][4] = "昌都";
		LOCATION[25][5] = "阿里";
		LOCATION[25][6] = "那曲";
	LOCATION[26] = Array();
		LOCATION[26][0] = "西安";
		LOCATION[26][1] = "宝鸡";
		LOCATION[26][2] = "咸阳";
		LOCATION[26][3] = "铜川";
		LOCATION[26][4] = "渭南";
		LOCATION[26][5] = "延安";
		LOCATION[26][6] = "榆林";
		LOCATION[26][7] = "汉中";
		LOCATION[26][8] = "安康";
		LOCATION[26][9] = "商洛";
	LOCATION[27] = Array();
		LOCATION[27][0] = "兰州";
		LOCATION[27][1] = "嘉峪关";
		LOCATION[27][2] = "金昌";
		LOCATION[27][3] = "白银";
		LOCATION[27][4] = "天水";
		LOCATION[27][5] = "酒泉";
		LOCATION[27][6] = "张掖";
		LOCATION[27][7] = "武威";
		LOCATION[27][8] = "定西";
		LOCATION[27][9] = "陇南";
		LOCATION[27][10] = "平凉";
		LOCATION[27][11] = "庆阳";
		LOCATION[27][12] = "临夏";
		LOCATION[27][13] = "甘南";
	LOCATION[28] = Array();
		LOCATION[28][0] = "银川";
		LOCATION[28][1] = "石嘴山";
		LOCATION[28][2] = "吴忠";
		LOCATION[28][3] = "固原";
	LOCATION[29] = Array();
		LOCATION[29][0] = "西宁";
		LOCATION[29][1] = "海东";
		LOCATION[29][2] = "海南";
		LOCATION[29][3] = "海北";
		LOCATION[29][4] = "黄南";
		LOCATION[29][5] = "玉树";
		LOCATION[29][6] = "果洛";
		LOCATION[29][7] = "海西";
	LOCATION[30] = Array();
		LOCATION[30][0] = "乌鲁木齐";
		LOCATION[30][1] = "阿勒泰";
		LOCATION[30][2] = "克拉玛依";
		LOCATION[30][3] = "伊犁";
		LOCATION[30][4] = "巴音郭楞";
		LOCATION[30][5] = "昌吉";
		LOCATION[30][6] = "克孜勒苏";
		LOCATION[30][7] = "博尔塔拉";
		LOCATION[30][8] = "吐鲁番";
		LOCATION[30][9] = "哈密";
		LOCATION[30][10] = "喀什";
		LOCATION[30][11] = "和田";
		LOCATION[30][12] = "阿克苏";
		LOCATION[30][13] = "塔城"
	LOCATION[31] = Array();
		LOCATION[31][0] = "香港";
	LOCATION[32] = Array();
		LOCATION[32][0] = "澳门";
	LOCATION[33] = Array();
		LOCATION[33][0] = "台北";
		LOCATION[33][1] = "高雄";
		LOCATION[33][2] = "其他";
	LOCATION[34] = Array();
		LOCATION[34][0] = "美国";
		LOCATION[34][1] = "英国";
		LOCATION[34][2] = "法国";
		LOCATION[34][3] = "俄罗斯";
		LOCATION[34][4] = "加拿大";
		LOCATION[34][5] = "巴西";
		LOCATION[34][6] = "澳大利亚";
		LOCATION[34][7] = "印尼";
		LOCATION[34][8] = "泰国";
		LOCATION[34][9] = "马来西亚";
		LOCATION[34][10] = "新加坡";
		LOCATION[34][11] = "菲律宾";
		LOCATION[34][12] = "越南";
		LOCATION[34][13] = "印度";
		LOCATION[34][14] = "日本";
		LOCATION[34][15] = "其他";
		LOCATION[35] = Array();
		LOCATION[35][0] = "";


