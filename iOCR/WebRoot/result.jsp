<%@ page contentType="text/html; charset=UTF-8"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
 "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
 <%
	String protocol = request.getScheme();
	String serverIP = request.getServerName();
	int port = request.getServerPort();
	
	String _ctx = (request.getContextPath()).equalsIgnoreCase("/") ? "" : request.getContextPath();
	String url = protocol + "://"+ serverIP + ":" + port + _ctx+ "/myImageServlet?uuid=" + (String)request.getAttribute("uuid");
	String identifyingCode = (String)request.getAttribute("identifyingCode");
 %>
<html>
<title>识别验证码</title>
<body>
	
	<div style="width:  600px; margin: auto; margin-top: 120px; font-size: 20px;"> 
		<div style="margin-top: 20px; margin-bottom: 20px;font-size: 20px; color: red;">
			<b >分析图片数字--演示</b>
		</div>
		<label for="j_imgurl">   验证码：<input type="text" size="6" value="<%=identifyingCode%>" /> <img src="<%=url%>" align="bottom"id="j_imgurl" /></label>
		<br>
		<br>
		<label for="j_sub" style="margin-left: 160px;"> <input type="button" value="刷 新" id="j_sub" onclick="window.location.reload()" /></label>
	</div>
</body>
</html>