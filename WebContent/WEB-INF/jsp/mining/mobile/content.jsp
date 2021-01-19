<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
	<s:if test='module=="xw"'>
	<s:iterator value="list" status="st" id="lst"> 
        <div class="list_one">
        	<div class="list_one_text">
        		<a href="<s:property value="url"/>" target="_blank">
        			<s:property value="title"/>
        		</a>
        	</div>
            <div class="list_one_ly">
                <div class="list_one_name">[<s:property value="sitename"/>]</div>
                <div class="list_one_time"><s:date name="ptime" format="MM-dd HH:mm" /></div>
            	<div class="clear"></div>
            </div>
        </div>
	</s:iterator>
	</s:if>
	<s:if test='module=="wx"'>
	<s:iterator value="list" status="st" id="lst"> 
        <div class="list_one">
        	<div class="list_one_text">
        		<a href="../wechat/detail.shtml?result.id=<s:property value="id"/>" target="_blank">
        			<s:property value="title"/>
        		</a>
        	</div>
            <div class="list_one_ly">
                <div class="list_one_name">[<s:property value="wxname"/>]</div>
                <div class="list_one_time"><s:date name="ptime" format="MM-dd HH:mm" /></div>
            	<div class="clear"></div>
            </div>
        </div>
	</s:iterator>
	</s:if>	
	<s:if test='module=="wb"'>
	<s:iterator value="list" status="st" id="lst"> 
        <div class="list_one">
        	<div class="list_one_text">
        		<a href="http://weibo.com<s:property value="#lst[1].url"/>" target="_blank">
        			<s:property value="#lst[1].content"/></div>
        		</a>
            <div class="list_one_ly">
                <div class="list_one_name">[<s:property value="#lst[0].nickname"/>]</div>
                <div class="list_one_time"><s:date name="#lst[0].ptime" format="MM-dd HH:mm" /></div>
            	<div class="clear"></div>
            </div>
        </div>
	</s:iterator>
	</s:if>		
	<s:if test='module=="lt"'>
	<s:iterator value="list" status="st" id="lst"> 
        <div class="list_one">
        	<div class="list_one_text">
        		<a href="<s:property value="url"/>" target="_blank">
        			<s:property value="title"/>
        		</a>
        		</div>
            <div class="list_one_ly">
                <div class="list_one_name">[<s:property value="sitename"/>]</div>
                <div class="list_one_time"><s:date name="ptime" format="MM-dd HH:mm" /></div>
            	<div class="clesr"></div>
            </div>
        </div>
	</s:iterator>
	</s:if>	
	<s:if test="#list.size>0">
	<div class="jzgd"><a href="javascript:;" data-total="<s:property value="total"/>" data-link="<s:url action="mobile/content"/>?days=<s:property value="days"/>&relation=<s:property value="relation"/>&module=<s:property value="module"/>&pageindex=<s:property value="pageindex+1"/>&pagesize=<s:property value="pagesize"/>">点击加载更多...</a></div>
	</s:if>