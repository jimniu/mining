<%@page contentType="text/html; charset=utf-8"%><%@taglib prefix="s"
	uri="/struts-tags"%><div style="font-size: 35px;">
	<img src="../images/error.png" width="30">
	页面出错或内容不存在，
	  <script>   
var t=3
  function   testTime()   {   
 if(t == 0)  location  = "../home/index.dhtml";   
      $("#js").html(t+"秒后");  
      t--;   //   计数器递减   
  }  
setInterval("testTime()",1000);      
    
         </script>
	<span style="font-size: 35px; color: red;" id="js"></span>
	返回
	<a href="../" style="font-size: 35px;">首页</a>
</div>