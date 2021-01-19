<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div class="second">
	<div class="width_box">
        <div class="logo">
            <span>
				<a href="<s:url action="site/index"/>">
					<img src="<s:property value="oem.logo" />" height="46"/>
				</a>
            </span>
            <span>
				<s:if test='oem.banner!=""&&oem.banner!=null'>
					<img src="<s:property value="oem.banner"/>" height="46" width="273"/>
				</s:if>
				<s:else>
					<img src="../images/logo_text2.png" height="46"/>
				</s:else>
            </span>
         </div>		
		<s:if test="myself.name!=null">
			<div class="user">
				<span>
					<a href="<s:url action="user/logout"/>">
						<img src="../images/tc_bt.png" />
					</a>
				</span>
				<span>欢迎您：
					<a href="<s:url action="user/info"/>">
						<s:property value="myself.name" />
					</a>
					@
					<a href="<s:url action="network/info"/>">
						<s:property value="mynetwork.name" />
					</a>
				</span>
				<span>
					<a href="<s:url action="user/message"/>">
						<img src="../images/xf_bt.png" />
					</a>
				</span>		
				<div class="clear"></div>
			</div>
		</s:if>
		<div class="clear"></div>
	</div>
</div>
