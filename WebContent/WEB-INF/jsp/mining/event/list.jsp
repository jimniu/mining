﻿<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
	<%@ include file="../layout/head.jsp"%>
    <title>事件管理-事件预警</title>
    
	<script type="text/javascript" src="../js/jquery/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="../js/layer/layer.js"></script>
	<script type="text/javascript" src="../js/base.js"></script>
	<script type="text/javascript" src="../js/mining/<s:property value="modulename" />.js" charset="UTF-8"></script>
    <script type="text/javascript" src="../js/map/lib/raphael-min.js"></script>
	<script type="text/javascript" src="../js/map/res/chinaMapConfig.js"></script>
	<script type="text/javascript" src="../js/map/map.js"></script>
	<script type="text/javascript" src="../js/chart/Chart.min.js"></script>
	<script src="//assets.kf5.com/supportbox/main.js" id="kf5-provide-supportBox" kf5-domain="isd.kf5.com"></script>
	
	<style type="text/css">
	/* 提示自定义 */
	.mapTip, #StateTip{display:none; position:absolute;background: url(../images/dt_bt3.png)no-repeat center center;width:68px;height:51px;  text-align:center; }
	.mapTip1, #StateTip1{display:none; position:absolute;background: url(../images/dt_bt2.png)no-repeat center center;width:68px;height:51px;  text-align:center;}
	.mapTip2, #StateTip3{display:none; position:absolute;background: url(../images/dt_bt1.png)no-repeat center center;width:68px;height:51px; text-align:center;}
	</style>
	
	<script type="text/javascript">
		$(function(){			
			var timer;
			var stateColorList = ['#FD1800','#FE7D00','#FEC601'];
			var data = {<s:iterator value="columnlist" status="st" id="lst">'<s:property value="province"/>':{'value':'<s:property value="#summaryhash['sj_'+id+'_total_xw']"/>','stateInitColor':<s:property value="#st.index"/>}<s:if test='!#st.last'>,</s:if></s:iterator>};	
			var mapRegion = $('#ChinaMap').SVGMap({
				external: true,
				mapName: 'china',
				mapWidth: 805,
				mapHeight: 500,
				mapTipWidth: 68,
                mapTipHeight: 51,
                showTip: !1,
                stateHoverColor: "#AAD5FF",
                hoverRegion: "",
				stateData: data,
		       	stateColorList : stateColorList,
	      	    mapTipHtml: function(stateData, obj){
			    	var name = obj.id;
			    	var color = stateData[name].stateInitColor;
			    	var value = stateData[name].value;
			    	$("#MapTip").removeClass();
		    		if(color== 0){
		    			$("#MapTip").addClass("mapTip");
		    			tipStr = '<div class="mapInfo">'+name+':'+value+'</div>';
		    		}else if (color == 1){
		    			$("#MapTip").addClass("mapTip1");
		    			tipStr = '<div class="mapInfo">'+name+':'+value+'</div>';
		    		}else if (color==2){
		    			$("#MapTip").addClass("mapTip2");
		    			tipStr = '<div class="mapInfo">'+name+':'+value+'</div>';
		    		}		
			        return tipStr;
			    }
			});
			
		$(document.body).append('<div id="MapTip1"></div>');
		
		autotip= function(){
			$("#MapTip1").removeClass();
			$('#MapTip1').html("");
			$('#MapTip1').hide();
			var sec = Math.floor(Math.random()*3+1);

			if(sec==1){
				$("#MapTip1").addClass("mapTip");
				setTimeout(function(){
				
				$('#MapTip1').html("北京:2.3万");
				$('#MapTip1').css({
					left: $(mapRegion.externalData['北京'].node).offset().left - 22,
					top: $(mapRegion.externalData['北京'].node).offset().top - 40
				}).show("slow");	
			}, 1000);		
			}else if (sec ==2){
				$("#MapTip1").addClass("mapTip1");
				setTimeout(function(){
				$('#MapTip1').html("山东:2.3万");
				$('#MapTip1').css({
					left: $(mapRegion.externalData['山东'].node).offset().left + 10,
					top: $(mapRegion.externalData['山东'].node).offset().top - 20
				}).show("slow");	
			}, 1000);		
			}else{
				$("#MapTip1").addClass("mapTip2");
				setTimeout(function(){
				$('#MapTip1').html("河南:2.3万");
				$('#MapTip1').css({
					left: $(mapRegion.externalData['河南'].node).offset().left,
					top: $(mapRegion.externalData['河南'].node).offset().top - 20
				}).show("slow");	
			}, 1000);		
			}
		};
		
		if(timer!=null){
			clearInterval(timer);
		}
			
		//timer = setInterval("autotip()", 3000);
	});
	</script>
</head>
<body>
<div class="box">
    <%@ include file="../layout/top.jsp"%>
    <div class="width_box">
        <%@ include file="../layout/message.jsp"%>
        <div class="list">
            <div class="list_top"><img src="../images/list_top.jpg"/></div>
            <div class="list_center">
               <%@ include file="../layout/left.jsp"%>
                <div class="list_right">
                    <%@ include file="guide.jsp"%>
                    <div class="wbwj_dsj_box">
                        <div class="wbwj_title">事件预警</div>
                        <div class="sjzq_sjgl_pic"  id="ChinaMap"></div>
                        <form action="<s:url action="event/list"/>" method="get">            
                        <div class="sjzq_sjgl_list">
                            <ul>
                                <li>事件名称：</li>
                                <li class="text2"><input type="text" name="column.title" value="<s:property value="column.title"/>"/></li>
                                <li>事件月份：</li>
                                <li class="text3"><input type="text" name="column.occurred" id="month"  value="<s:property value="column.occurred"/>"/></li>
                                <li class="right"><a href="<s:url action="event/add"/>">新建事件</a></li>
                                <li class="right"><a href="javascript:;" id="searchbtn">搜 索</a></li>
                                <div class="clear"></div>
                            </ul>
                        </div>
                        <input type="submit" id="submitbtn" style="display:none;">
                        </form>
                        <div class="sjgl_sjyj">
                        <table width="805" border="0" cellspacing="0" cellpadding="0">
                          <tr class="title">
                            <td width="150">事件名称</td>
                            <td width="70">微博</td>
                            <td width="70">微信</td>
                            <td width="70">新闻</td>
                            <td width="70">论坛</td>
                            <td width="70">总数</td>
                            <td width="70">状态</td>
                            <td>操作</td>
                          </tr>
                          <s:iterator value="columnlist">
                          <tr>
                            <td><s:property value="title"/></td>
                            <td><s:property value="#wbhash[wbid].feedtotal"/></td>
                            <td><a href="<s:url action="wechat/more"/>?column.id=<s:property value="wxid"/>"><s:property value="#wxhash[wxid].total"/></a></td>
                            <td><a href="<s:url action="track/more"/>?column.id=<s:property value="xwid"/>"><s:property value="#xwhash[xwid].total"/></a></td>
                            <td><a href="<s:url action="bbs/more"/>?column.id=<s:property value="ltid"/>"><s:property value="#lthash[ltid].total"/></a></td>
                            <td><s:property value="#wbhash[wbid].feedtotal+#wxhash[wxid].total+#xwhash[xwid].total+#lthash[ltid].total"/></td>
                            <td class="color1">            	
                            	<s:if test="status==1">
				            	黄色预警
				            	</s:if>
				            	<s:elseif test="status==2">
				            	橙色预警
				            	</s:elseif>
				            	<s:elseif test="status==3">
				            	红色预警
				            	</s:elseif>
				            	<s:else>
				            	常规状态
				            	</s:else>
            				</td>
                            <td class="position">
                            	<a href="<s:url action="event/edit"/>?column.id=<s:property value="id"/>"><img src="../images/bj_bt.jpg"/></a>
                            	<a href="<s:url action="event/detail"/>?column.id=<s:property value="id"/>"><img src="../images/fx_bt.jpg"/></a>
                            	<a href="<s:url action="event/delete"/>?column.id=<s:property value="id"/>"><img src="../images/sc_bt.jpg"/></a>
                            </td>
                          </tr>
                          </s:iterator>
                        </table>
                        </div>
                    </div>
                </div>
                <div class="clear"></div>
            </div>
            <div class="list_top"><img src="../images/list_bottom.jpg"/></div>
        </div>
    </div> 
	<%@ include file="../layout/footer.jsp"%>
</body>
</html>
