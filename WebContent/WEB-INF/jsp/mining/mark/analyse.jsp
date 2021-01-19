<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>数据抓取-数据分析</title>
    <%@ include file="../layout/head.jsp"%>
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
                    <div class="wbwj_top">
                        <div class="wbwj_top_title">数据分析：</div>
                        <div class="wbwj_top_bt"><a href="javascript:;" id="shou">展开</a></div>
                        <div class="clear"></div>
                    </div>
                    <div class="wbwj_pic" id="tu" style="display:none;"><img src="../images/sjzq_pic.jpg"/></div>
                    <div class="wbwj_dsj_box">
                        <div class="wbwj_title">数据分析</div>
                        <div class="sjzq_sjfx_link">
                            <h2>选择标签：</h2>
                            <ul>
	                            <s:iterator  value="taglist"  status="st">
	                                <li><a  href="<s:url action="mark/analyse"/>?type=date&time=7&id=<s:property value="id"/>" ><s:property value="value"/></a></li>
	                             </s:iterator>
                                <div class="clear"></div>
                            </ul>
                        </div>
                        <s:if test='type=="date"'>
                        	 <div class="hywj_one_text">
                                <div class="hywj_one_left">数据挖掘分析时间：<s:property value="to"/> 至 <s:property value="from"/></div>
                                <div class="hywj_one_right">
                                    <a  href="<s:url action="mark/analyse"/>?type=date&time=3650&id=<s:property value="id"/>"   pos ="3650" >全部</a>
                                    <a  href="<s:url action="mark/analyse"/>?type=date&time=365&id=<s:property value="id"/>"   pos ="365" >最近1年</a>
                                    <a  href="<s:url action="mark/analyse"/>?type=date&time=183&id=<s:property value="id"/>"   pos ="183" >最近半年</a>
                                    <a  href="<s:url action="mark/analyse"/>?type=date&time=90&id=<s:property value="id"/>"   pos ="90" >最近90天</a>
                                    <a  href="<s:url action="mark/analyse"/>?type=date&time=30&id=<s:property value="id"/>"   pos ="30" >最近30天 </a>
                                    <a   href="<s:url action="mark/analyse"/>?type=date&time=7&id=<s:property value="id"/>"   pos ="7" >最近7天</a>
                                    <div class="clear"></div>
                                </div>
                                <div class="clear"></div>
                            </div>
                        </s:if>         
                        <div class="sjzq_sjfx_list">
                            <ul>
                                <li>标签：</li>
                                <li class="text">
                                    <select>
                                    	<option value ="0">全部</option>
                                    	<s:iterator  value="taglist"  status="st">
	                                        <option value ="<s:property value="id"/>" ><s:property value="value"/></option>	
                                        </s:iterator>
                                    </select>
                                </li>
                                <li class="bt"><a href="javascript:;"  id="choose">确定</a></li>
                                <div class="clear"></div>
                            </ul>
                        </div>
                        <div class="sjzq_sjfx_pic">
                        	<canvas id="myChart" width="805" height="403" style="margin-left:10px;"></canvas>
						</div>
                    </div>
                </div>
                <div class="clear"></div>
                </div>
            </div>
            <div class="list_top"><img src="../images/list_bottom.jpg"/></div>
        </div>
    </div>
   <%@ include file="../layout/footer.jsp"%>
   <script>
   	//历史挖掘数量分析
    var myChart = echarts.init(document.getElementById('myChart'));
	var option = {
	    tooltip : {
	        trigger: 'axis',
	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
	            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
	        }
	    },
	    grid: {
	    	top: '5%',
	        left: '3%',
	        right: '4%',
	        bottom: '3%',
	        containLabel: true
	    },
		color: chartcolor,		    
	    xAxis : [
	        {
	            type : 'category',
	            data : [<s:iterator value="list" status="st" id="lst">"<s:property value="#lst[0]"/>"<s:if test='!#st.last'>,</s:if></s:iterator>],
	            axisTick: {
	                alignWithLabel: true
	            }
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value'
	        }
	    ],
	   	barGap:'0%',
	    series : [
	        {
	            name:'文章数量',
	            type:'bar',
	            data: [<s:iterator value="list" status="st" id="lst">"<s:property value="#lst[1]"/>"<s:if test='!#st.last'>,</s:if></s:iterator>]
	        }
	    ]
	};
	// 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
   
 
				
</script>
</body>
</html>
