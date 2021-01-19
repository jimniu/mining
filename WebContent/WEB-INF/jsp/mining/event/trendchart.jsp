<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
$.get('../js/echarts/china.json', function (chinaJson) {
    echarts.registerMap('china', chinaJson);
    var myChart = echarts.init(document.getElementById('trendchart'));
	var geoCoordMap = {
	    '上海': [121.4648,31.2891],
	    '北京': [116.4551,40.2539],
	    '新疆': [87.9236,43.5883],
	    '内蒙古': [111.4124,40.4901],	 
	    '江苏': [118.8062,31.9208],
	    '广西': [108.479,23.1152],
	    '江西': [116.0046,28.6633],
	    '安徽': [117.29,32.0581],
	    '甘肃': [103.5901,36.3043],	    
	    '黑龙江': [127.9688,45.368],
	    '天津': [117.4219,39.4189],
	    '山西': [112.3352,37.9413],	    
		'广东': [113.5107,23.2196],
		'四川': [103.9526,30.7617],
		'西藏': [91.1865,30.1465],
		'云南': [102.9199,25.4663],
	    '浙江': [119.5313,29.8773],
		'辽宁': [123.1238,42.1216],
		'山东': [117.1582,36.8701],
		'海南': [110.3893,19.8516],
		'河北': [114.4995,38.1006],
	    '福建': [119.4543,25.9222],
		'江苏': [120.6519,31.3989],
		'贵州': [106.6992,26.7682],
		'陕西': [109.1162,34.2004],
		'河南': [113.4668,34.6234],
		'重庆': [107.7539,30.1904],
		'宁夏': [106.3586,38.1775],
		'吉林': [125.8154,44.2584],
		'湖北': [114.3896,30.6628],
		'湖南': [113.0823,28.2568],
		'青海': [101.4038,36.8207],
		'香港': [114.1707,22.2849],
		'澳门': [113.5380,22.1899],
		'台湾': [121.5641,25.0337]
	};
	
	var eventData = [
		<s:iterator value="list" status="st" id="lst">
		[{name:'<s:property value="column.province"/>'}, {name:'<s:property value="#hash[#lst[0]].name"/>',value:<s:property value="#lst[1]"/>}]
		<s:if test='!#st.last'>,</s:if></s:iterator>
	];
	
	
	var planePath = 'path://M1705.06,1318.313v-89.254l-319.9-221.799l0.073-208.063c0.521-84.662-26.629-121.796-63.961-121.491c-37.332-0.305-64.482,36.829-63.961,121.491l0.073,208.063l-319.9,221.799v89.254l330.343-157.288l12.238,241.308l-134.449,92.931l0.531,42.034l175.125-42.917l175.125,42.917l0.531-42.034l-134.449-92.931l12.238-241.308L1705.06,1318.313z';
	
	var convertData = function (data) {
	    var res = [];
	    for (var i = 0; i < data.length; i++) {
	        var dataItem = data[i];
	        var fromCoord = geoCoordMap[dataItem[0].name];
	        var toCoord = geoCoordMap[dataItem[1].name];
	        if (fromCoord && toCoord) {
	            res.push({
	                fromName: dataItem[0].name,
	                toName: dataItem[1].name,
	                coords: [fromCoord, toCoord]
	            });
	        }
	    }
	    return res;
	};
	
	var color = ['#a6c84c', '#ffa022', '#46bee9'];
	var series = [];
	[['事件', eventData]].forEach(function (item, i) {
	    series.push({
	        name: item[0] + ' Top10',
	        type: 'lines',
	        zlevel: 1,
	        effect: {
	            show: true,
	            period: 6,
	            trailLength: 0.7,
	            color: '#fff',
	            symbolSize: 3
	        },
	        lineStyle: {
	            normal: {
	                color: color[i],
	                width: 0,
	                curveness: 0.2
	            }
	        },
	        data: convertData(item[1])
	    },
	    {
	        name: item[0] + ' Top10',
	        type: 'lines',
	        zlevel: 2,
	        effect: {
	            show: true,
	            period: 6,
	            trailLength: 0,
	            symbol: planePath,
	            symbolSize: 2
	        },
	        lineStyle: {
	            normal: {
	                color: color[i],
	                width: 1,
	                opacity: 0.4,
	                curveness: 0.2
	            }
	        },
	        data: convertData(item[1])
	    },
	    {
	        name: item[0] + ' Top10',
	        type: 'effectScatter',
	        coordinateSystem: 'geo',
	        zlevel: 2,
	        rippleEffect: {
	            brushType: 'stroke'
	        },
	        label: {
	            normal: {
	                show: true,
	                position: 'right',
	                formatter: '{b}'
	            }
	        },
	        symbolSize: function (val) {
	            return 3;//val[2] / 8;
	        },
	        itemStyle: {
	            normal: {
	                color: color[i]
	            }
	        },
	        data: item[1].map(function (dataItem) {
	            return {
	                name: dataItem[1].name,
	                value: geoCoordMap[dataItem[1].name].concat([dataItem[1].value])
	            };
	        })
	    });
	});
	
	option = {
	    backgroundColor: '#404a59',
	    title : {
	        text: '事件传播图示',
	        subtext: '以省为单位',
	        left: 'center',
	        textStyle : {
	            color: '#fff'
	        }
	    },
	    tooltip : {
	        trigger: 'item'
	    },
	    legend: {
	        orient: 'vertical',
	        top: 'bottom',
	        left: 'right',
	        data:['事件 Top10'],
	        textStyle: {
	            color: '#fff'
	        },
	        selectedMode: 'single'
	    },
	    geo: {
	        map: 'china',
	        label: {
	            emphasis: {
	                show: false
	            }
	        },
	        roam: true,
	        itemStyle: {
	            normal: {
	                areaColor: '#323c48',
	                borderColor: '#404a59'
	            },
	            emphasis: {
	                areaColor: '#2a333d'
	            }
	        }
	    },
	    series: series
	};

	// 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
});