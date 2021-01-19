<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>

    <script type="text/javascript" src="../js/jquery/jquery-1.8.3.min.js"></script>
    <title><s:property value="result.title"/></title>
</head>
<body>
<s:if test="result.url==null||result.url.length()==0">

	<div class="t_gai" style=" width:70%; margin:20px auto;overflow:hidden">
		
		<div class="t_center" style="width:100%;">
			<!--<div class="t_bt">
				<a href="javascript:;"><img src="../images/t_bt.png" /></a>
			</div>-->
			<h3 style="text-align:center;margin:0px auto;overflow:hidden;padding-top:20px"><s:property value="result.title"/></h3>
			<p style="text-align:center;margin:10px auto;overflow:hidden">
			<s:property value="result.wxname"/> 
			<s:date name="result.ptime" format="yyyy-MM-dd" />
			</p>
			<div class="t_content" style=" width:100%; margin:0px auto;">
			    加载中......
			</div>
		</div>
		
	</div>
	<script>
		function callback(data){
			$("div.t_content").html(data);

			$("div.t_content img").each(function(){
				var self = $(this);
				var src  = self.attr("data-src");
				self.attr("data-time", 1);
				self.attr("src", "../images/working.gif");

				self.error(function(){
					var mine = $(this);
					var source = mine.attr("data-src");
					var time = mine.attr("data-time");
					mine.attr("src", "../images/working.gif");

					if(time<=10) {
						setTimeout(function () {
							mine.attr("src", "http://www.dig88.cn/common/showimage.shtml?ord=" + new Date().getTime() + "&url=" + source);
						}, time * 1000);
					}else{
						mine.hide();
					}
					self.attr("data-time", time*2);
				});

				setTimeout(function () {
					self.attr("src", "http://www.dig88.cn/common/showimage.shtml?ord=" + new Date().getTime() + "&url=" + src);
				}, 300+500 * Math.random());
			});
		}
	</script>
	<script src="<s:property value="url"/>"></script>
</s:if>
<s:else>
    <script>
        var url = "<s:property value="result.url"/>";
        url = url.replace(new RegExp("&amp;","gmi"),"&");
		url = url.replace(new RegExp("\\x26amp;","gmi"),"&");
        window.location.href = url;
    </script>
</s:else>
</body>
</html>
