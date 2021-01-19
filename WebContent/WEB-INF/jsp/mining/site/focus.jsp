<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
		<s:if test='module=="xw"'>
		<s:iterator value="list" status="st" id="lst"> 
        <div class="t_list_box">
        <div class="t_list_one">
        	<div class="t_text">
        		<a href="<s:property value="#lst[1]"/>" target="_blank">
        			<s:property value="#lst[0]"/>
        		</a>
        	</div>
            <div class="t_box">
            	<div class="t_name">
	            	[<s:property value="#lst[4]"/>]
	            	<span><s:date name="#lst[2]" format="MM-dd HH:mm" /></span>
            	</div>
				<div class="t_num">
					<a href="javascript:;" data-recommend="favorite.module=xw&favorite.pageid=<s:property value="#lst[8]"/>">推荐</a>
					传播数：<a href="javascript:;" data-url="<s:url action="mobile/similar"/>?days=<s:property value="days"/>&module=xw&pageid=<s:property value="#lst[5]"/>&type=<s:property value="type"/>"><s:property value="#lst[3]"/></a>
				</div>
			</div>  
        </div>
        </div> 
        </s:iterator>
        </s:if>
		<s:if test='module=="wx"'>
		<s:iterator value="list" status="st" id="lst"> 
        <div class="t_list_box">
        <div class="t_list_one">
        	<div class="t_text">
        		<a href="<s:url action="wechat/detail"/>?result.id=<s:property value="#lst[8]"/>" target="_blank">
        			<s:property value="#lst[0]"/>
        		</a>
        	</div>
            <div class="t_box">
            	<div class="t_name">
	            	[<s:property value="#lst[4]"/>]
	            	<span><s:date name="#lst[2]" format="MM-dd HH:mm" /></span>
            	</div>
				<div class="t_num">
					<a href="javascript:;" data-recommend="favorite.module=wx&favorite.pageid=<s:property value="#lst[8]"/>">推荐</a>
					传播数：<a href="javascript:;" data-url="<s:url action="mobile/similar"/>?days=<s:property value="days"/>&module=wx&pageid=<s:property value="#lst[5]"/>&type=<s:property value="type"/>"><s:property value="#lst[3]"/></a>
				</div>
			</div>          	
        </div>
        </div> 
        </s:iterator>
        </s:if>    
		<s:if test='module=="wb"'>
		<s:iterator value="list" status="st" id="lst"> 
        <div class="t_list_one_wb">
        	<div class="t_text">
        		<a href="<s:property value="#lst[1]"/>" target="_blank">
        			<s:property value="#lst[0]"/>
        		</a>
        	</div>
            <div class="t_box">
            	<div class="t_name">
	            	[<s:property value="#lst[4]"/>]
	            	<span><s:date name="#lst[2]" format="MM-dd HH:mm" /></span>
            	</div>
				<div class="t_num">
					传播数：<s:property value="#lst[3]"/>
				</div>
			</div>          	
        </div>
        </s:iterator>
        </s:if>   
		<s:if test='module=="lt"'>
		<s:iterator value="list" status="st" id="lst"> 
        <div class="t_list_box">
        <div class="t_list_one">
        	<div class="t_text">
        		<a href="<s:property value="#lst[1]"/>" target="_blank">
        			<s:property value="#lst[0]"/>
        		</a>
        	</div>
            <div class="t_box">
            	<div class="t_name">
	            	[<s:property value="#lst[4]"/>]
	            	<span><s:date name="#lst[2]" format="MM-dd HH:mm" /></span>
            	</div>
				<div class="t_num">
					传播数：<a href="javascript:;" data-url="<s:url action="mobile/similar"/>?days=<s:property value="days"/>&module=lt&pageid=<s:property value="#lst[5]"/>&type=<s:property value="type"/>"><s:property value="#lst[3]"/></a>
				</div>
			</div>           
        </div>
        </div> 
        </s:iterator>
        </s:if>
<script type="text/javascript" src="../js/mining/site.js?ord=<s:property value="version" />" charset="UTF-8"></script>
<script type="text/javascript">
	site_focus();
</script>
