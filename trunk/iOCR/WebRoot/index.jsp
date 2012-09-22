<%@ page contentType="text/html; charset=UTF-8"  %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
 "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
  <%
	String protocol = request.getScheme();
	String serverIP = request.getServerName();
	int port = request.getServerPort();
	String _ctx = (request.getContextPath()).equalsIgnoreCase("/") ? "" : request.getContextPath();
	String url = protocol + "://"+ serverIP + ":" + port + _ctx+ "/myServlet";
 %>
<html>
<title>识别验证码</title>
<body>
	
	<div style="width:  600px; margin: auto; margin-top: 120px; font-size: 12px;"> 
	<div style="margin-top: 20px; margin-bottom: 20px;font-size: 20px; color: red;">
		<b >分析图片数字--演示</b>
	</div>
	<form name="actionForm" action="<%=url%>" method="post">
			<label for="j_imgurl">图片URL:<input type="text"  size="50" id='j_imgurl' name='j_imgurl' value="https://investorservice.cfmmc.com/veriCode.do"/> </label>
		    <input type="submit" id="submitBtn" name='submitBtn' value="获取"/>
	</form>
	</div>
</body>
</html>
