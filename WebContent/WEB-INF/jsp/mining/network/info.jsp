<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <%@ include file="../layout/head.jsp"%>
    <title>用户信息</title>
</head>
<body>
<div class="box">
    <%@ include file="../layout/top.jsp"%>
    <div class="width_box">
        <%@ include file="../layout/message.jsp"%>
        <div class="list">
            <div class="list_top"><img src="../images/list_top.jpg"/></div>
            <div class="list_center">
                <div class="list_left">
                    <div class="yhxx_left">
                        <ul>
                            <li><a href=" <s:url action="user/info"/>">用户信息</a></li>
                            <li><a href=" <s:url action="network/info"/>" class="yhxx_left_vtd">账户信息</a></li>
                            <li><a href=" <s:url action="user/message"/>">我的消息</a></li>
                            <li><a href="<s:url action="site/overview"/>">返回主页</a></li>
                        </ul>
                    </div>
                </div>
                <div class="list_right">
                    <div class="yhxx_right">
                        <h2>账户信息</h2>
                        <form action="<s:url action="network/update"/>" method="post" enctype="multipart/form-data" id="form">
                        <ul>
                            <li>
                                <span class="left">账号：</span>
                                <span class="right"><input type="text" name="network.name" value="<s:property value="mynetwork.name" />"/></span>
                                <div class="clear"></div>
                            </li>
                            <li>
                                <span class="left">企业名称：</span>
                                <span class="right"><input type="text"   name="network.company" value="<s:property value="mynetwork.company" />" /></span>
                                <div class="clear"></div>
                            </li>
                            <li>
                                <span class="left">行业：</span>
                                <span class="right">
                                    <select name="network.area" data-value="<s:property value="mynetwork.area" />">
                                        <option value="0">选择行业</option>
                                        <option value="高新科技">高新科技</option>
                                        <option value="互联网">&nbsp;&nbsp;&nbsp;互联网</option>
                                        <option value="电子商务">&nbsp;&nbsp;&nbsp;电子商务</option>
                                        <option value="电子游戏">&nbsp;&nbsp;&nbsp;电子游戏</option>
                                        <option value="计算机软件">&nbsp;&nbsp;&nbsp;计算机软件</option>
                                        <option value="计算机硬件">&nbsp;&nbsp;&nbsp;计算机硬件</option>
                                        <option value="信息传媒">信息传媒</option>
                                        <option value="出版业">&nbsp;&nbsp;&nbsp;出版业</option>
                                        <option value="电影录音">&nbsp;&nbsp;&nbsp;电影录音</option>
                                        <option value="广播电视">&nbsp;&nbsp;&nbsp;广播电视</option>
                                        <option value="通信">&nbsp;&nbsp;&nbsp;通信</option>
                                        <option value="金融">金融</option>
                                        <option value="银行">&nbsp;&nbsp;&nbsp;银行</option>
                                        <option value="资本投资">&nbsp;&nbsp;&nbsp;资本投资</option>
                                        <option value="证券投资">&nbsp;&nbsp;&nbsp;证券投资</option>
                                        <option value="保险">&nbsp;&nbsp;&nbsp;保险</option>
                                        <option value="信贷">&nbsp;&nbsp;&nbsp;信贷</option>
                                        <option value="财务">&nbsp;&nbsp;&nbsp;财务</option>
                                        <option value="审计">&nbsp;&nbsp;&nbsp;审计</option>
                                        <option value="服务业">服务业</option>
                                        <option value="法律">&nbsp;&nbsp;&nbsp;法律</option>
                                        <option value="餐饮">&nbsp;&nbsp;&nbsp;餐饮</option>
                                        <option value="酒店">&nbsp;&nbsp;&nbsp;酒店</option>
                                        <option value="旅游">&nbsp;&nbsp;&nbsp;旅游</option>
                                        <option value="广告">&nbsp;&nbsp;&nbsp;广告</option>
                                        <option value="公关">&nbsp;&nbsp;&nbsp;公关</option>
                                        <option value="景观">&nbsp;&nbsp;&nbsp;景观</option>
                                        <option value="咨询分析">&nbsp;&nbsp;&nbsp;咨询分析</option>
                                        <option value="市场推广">&nbsp;&nbsp;&nbsp;市场推广</option>
                                        <option value="人力资源">&nbsp;&nbsp;&nbsp;人力资源</option>
                                        <option value="社工服务">&nbsp;&nbsp;&nbsp;社工服务</option>
                                        <option value="养老服务">&nbsp;&nbsp;&nbsp;养老服务</option>
                                        <option value="教育">教育</option>
                                        <option value="高等教育">&nbsp;&nbsp;&nbsp;高等教育</option>
                                        <option value="基础教育">&nbsp;&nbsp;&nbsp;基础教育</option>
                                        <option value="职业教育">&nbsp;&nbsp;&nbsp;职业教育</option>
                                        <option value="幼儿教育">&nbsp;&nbsp;&nbsp;幼儿教育</option>
                                        <option value="特殊教育">&nbsp;&nbsp;&nbsp;特殊教育</option>
                                        <option value="培训">&nbsp;&nbsp;&nbsp;培训</option>
                                        <option value="医疗服务">医疗服务</option>
                                        <option value="临床医疗">&nbsp;&nbsp;&nbsp;临床医疗</option>
                                        <option value="制药">&nbsp;&nbsp;&nbsp;制药</option>
                                        <option value="保健">&nbsp;&nbsp;&nbsp;保健</option>
                                        <option value="美容">&nbsp;&nbsp;&nbsp;美容</option>
                                        <option value="医疗器材">&nbsp;&nbsp;&nbsp;医疗器材</option>
                                        <option value="生物工程">&nbsp;&nbsp;&nbsp;生物工程</option>
                                        <option value="疗养服务">&nbsp;&nbsp;&nbsp;疗养服务</option>
                                        <option value="护理服务">&nbsp;&nbsp;&nbsp;护理服务</option>
                                        <option value="艺术娱乐">艺术娱乐</option>
                                        <option value="创意艺术">&nbsp;&nbsp;&nbsp;创意艺术</option>
                                        <option value="体育健身">&nbsp;&nbsp;&nbsp;体育健身</option>
                                        <option value="娱乐休闲">&nbsp;&nbsp;&nbsp;娱乐休闲</option>
                                        <option value="图书馆">&nbsp;&nbsp;&nbsp;图书馆</option>
                                        <option value="博物馆">&nbsp;&nbsp;&nbsp;博物馆</option>
                                        <option value="策展">&nbsp;&nbsp;&nbsp;策展</option>
                                        <option value="博彩">&nbsp;&nbsp;&nbsp;博彩</option>
                                        <option value="制造加工">制造加工</option>
                                        <option value="食品饮料业">&nbsp;&nbsp;&nbsp;食品饮料业</option>
                                        <option value="纺织皮革业">&nbsp;&nbsp;&nbsp;纺织皮革业</option>
                                        <option value="服装业">&nbsp;&nbsp;&nbsp;服装业</option>
                                        <option value="烟草业">&nbsp;&nbsp;&nbsp;烟草业</option>
                                        <option value="造纸业">&nbsp;&nbsp;&nbsp;造纸业</option>
                                        <option value="印刷业">&nbsp;&nbsp;&nbsp;印刷业</option>
                                        <option value="化工业">&nbsp;&nbsp;&nbsp;化工业</option>
                                        <option value="汽车">&nbsp;&nbsp;&nbsp;汽车</option>
                                        <option value="家具">&nbsp;&nbsp;&nbsp;家具</option>
                                        <option value="电子电器">&nbsp;&nbsp;&nbsp;电子电器</option>
                                        <option value="机械设备">&nbsp;&nbsp;&nbsp;机械设备</option>
                                        <option value="塑料工业">&nbsp;&nbsp;&nbsp;塑料工业</option>
                                        <option value="金属加工">&nbsp;&nbsp;&nbsp;金属加工</option>
                                        <option value="军火">&nbsp;&nbsp;&nbsp;军火</option>
                                        <option value="地产建筑">地产建筑</option>
                                        <option value="房地产">&nbsp;&nbsp;&nbsp;房地产</option>
                                        <option value="装饰装潢">&nbsp;&nbsp;&nbsp;装饰装潢</option>
                                        <option value="物业服务">&nbsp;&nbsp;&nbsp;物业服务</option>
                                        <option value="特殊建造">&nbsp;&nbsp;&nbsp;特殊建造</option>
                                        <option value="建筑设备">&nbsp;&nbsp;&nbsp;建筑设备</option>
                                        <option value="贸易零售">贸易零售</option>
                                        <option value="零售">&nbsp;&nbsp;&nbsp;零售</option>
                                        <option value="大宗交易">&nbsp;&nbsp;&nbsp;大宗交易</option>
                                        <option value="进出口贸易">&nbsp;&nbsp;&nbsp;进出口贸易</option>
                                        <option value="公共服务">公共服务</option>
                                        <option value="政府">&nbsp;&nbsp;&nbsp;政府</option>
                                        <option value="国防军事">&nbsp;&nbsp;&nbsp;国防军事</option>
                                        <option value="航天">&nbsp;&nbsp;&nbsp;航天</option>
                                        <option value="科研">&nbsp;&nbsp;&nbsp;科研</option>
                                        <option value="给排水">&nbsp;&nbsp;&nbsp;给排水</option>
                                        <option value="水利能源">&nbsp;&nbsp;&nbsp;水利能源</option>
                                        <option value="电力电网">&nbsp;&nbsp;&nbsp;电力电网</option>
                                        <option value="公共管理">&nbsp;&nbsp;&nbsp;公共管理</option>
                                        <option value="环境保护">&nbsp;&nbsp;&nbsp;环境保护</option>
                                        <option value="非盈利组织">&nbsp;&nbsp;&nbsp;非盈利组织</option>
                                        <option value="开采冶金">开采冶金</option>
                                        <option value="煤炭工业">&nbsp;&nbsp;&nbsp;煤炭工业</option>
                                        <option value="石油工业">&nbsp;&nbsp;&nbsp;石油工业</option>
                                        <option value="黑色金属">&nbsp;&nbsp;&nbsp;黑色金属</option>
                                        <option value="有色金属">&nbsp;&nbsp;&nbsp;有色金属</option>
                                        <option value="土砂石开采">&nbsp;&nbsp;&nbsp;土砂石开采</option>
                                        <option value="地热开采">&nbsp;&nbsp;&nbsp;地热开采</option>
                                        <option value="交通仓储">交通仓储</option>
                                        <option value="邮政">&nbsp;&nbsp;&nbsp;邮政</option>
                                        <option value="物流递送">&nbsp;&nbsp;&nbsp;物流递送</option>
                                        <option value="地面运输">&nbsp;&nbsp;&nbsp;地面运输</option>
                                        <option value="铁路运输">&nbsp;&nbsp;&nbsp;铁路运输</option>
                                        <option value="管线运输">&nbsp;&nbsp;&nbsp;管线运输</option>
                                        <option value="航运业">&nbsp;&nbsp;&nbsp;航运业</option>
                                        <option value="民用航空业">&nbsp;&nbsp;&nbsp;民用航空业</option>
                                        <option value="农林牧渔">农林牧渔</option>
                                        <option value="种植业">&nbsp;&nbsp;&nbsp;种植业</option>
                                        <option value="畜牧养殖业">&nbsp;&nbsp;&nbsp;畜牧养殖业</option>
                                        <option value="林业">&nbsp;&nbsp;&nbsp;林业</option>
                                        <option value="渔业">&nbsp;&nbsp;&nbsp;渔业</option>
                                    </select>
                                </span>
                                <div class="clear"></div>
                            </li>
                            <li>
                                <span class="left">联系人：</span>
                                <span class="right"><input type="text"   name="network.contact"  value="<s:property value="mynetwork.contact" />"/></span>
                                <div class="clear"></div>
                            </li>
                            <li>
                                <span class="left">公司电话：</span>
                                <span class="right"><input type="text"   name="network.phone" value="<s:property value="mynetwork.phone" />"  /></span>
                                <div class="clear"></div>
                            </li>
                            <li>
                                <span class="left">微信公众号：</span>
                                <span class="right"><input type="text"   name="setting.wxaccount"  value="<s:property value="setting.wxaccount" />"   id="weixin" /></span>
                                <div class="clear"></div>
                            </li>
                            <li>
                                <span class="left">微信二维码：</span>
                                <span class="right"><input type="file" name="image" id="fileimage"/></span>
                                <div class="clear"></div>
                            </li>
                            <li>
                                <span class="left"></span>
                                <span style="margin-left: 120px;"><img src="<s:property value="setting.wxbarcode" />" width="110"></span>
                                <input type="hidden" name="setting.wxbarcode" value="<s:property value="setting.wxbarcode" />"/>
                                <div class="clear"></div>
                            </li>
                            <li>
                                <span class="left">微信通历史头图：</span>
                                <span class="right"><input type="file" name="headimage" id="headimage"/></span>
                                <div class="clear"></div>
                            </li>
                            <li>
                                <span class="left"></span>
                                <span style="margin-left: 120px;"><img src="<s:property value="setting.headimage" />" width="200"></span>
                                <input type="hidden" name="setting.headimage" value="<s:property value="setting.headimage" />"/>
                                <div class="clear"></div>
                            </li>
                            <li>
                                <span class="left">&nbsp;</span>
                                <span class="right1">
                                    <span class="tj"><a href="javascript:;"  id="submit" >提交</a></span>
                                    <input type="submit" id="submitbtn" style="display:none;">
                                    <div class="clear"></div>
                                </span>
                                <div class="clear"></div>
                            </li>
                        </ul>
                        </form>
                    </div>
                </div>
                <div class="clear"></div>
            </div>
            <div class="list_top"><img src="../images/list_bottom.jpg"/></div>
        </div>
    </div>
    <%@ include file="../layout/footer.jsp"%>
</div>
</body>
</html>
