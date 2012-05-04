<%@page import="com.platform.database.GlobalVariables" %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>iServer后台登陆</title>
		<link rel="stylesheet" type="text/css" href="static/css/style.css" />
		<script type="text/javascript" src="static/js/jquery.js"></script>
		<script type="text/javascript"
			src="static/js/jconfirmaction.jquery.js"></script>
		<script type="text/javascript">
			$(document).ready(function() {
				$('#submit').click(function(){
				var name = $("#name").val();
				var password = $("#password").val();
				$.ajax({
							method:'GET',
							url:'/'+'<%=GlobalVariables.serverName%>'+'user/login?name='+name+'&password='+password,
							dataType:'json',
							success:function(json)
							{
								if(json.success)
		 							window.location.href="index.jsp";
		 						else
		 							alert(json.msg);
		  					},
							error:function(json)
							{
								alert("警告:服务器繁忙请稍候再试!");
							}
					});
				});
			});
		</script>
		<script language="javascript" type="text/javascript"
			src="static/js/niceforms.js"></script>
		<link rel="stylesheet" type="text/css" media="all"
			href="static/css/niceforms-default.css" />
	</head>
	<body>
		<div id="main_container">
			<div class="header_login" >
			<!-- 
				<div class="logo">
					<a href="#"><img src="#" alt="" title="" border="0" />
					</a>
				</div>
			 -->
			</div>
			<div class="login_form">
				<h3>
					Magazine Server
				</h3>
				<!-- 
        		 <a href="#" class="forgot_pass">忘记密码</a> 
         		 -->
				<form action="" method="post" class="niceform">
					<fieldset>
						<dl>
							<dt>
								<label for="name">
									用户名:
								</label>
							</dt>
							<dd>
								<input type="text" name="name" id="name" size="54" />
							</dd>
						</dl>
						<dl>
							<dt>
								<label for="password">
									密码:
								</label>
							</dt>
							<dd>
								<input type="password" name="password" id="password" size="54" />
							</dd>
						</dl>
						<!-- 
						<dl>
							<dt>
								<label></label>
							</dt>
							<dd>
								<input type="checkbox" name="interests[]" id="" value="" />
								<label class="check_label">
									记住我?
								</label>
							</dd>
						</dl>
						 -->
						<dl class="submit">
							<dd>
							<input type="button" name="submit" id="submit" value="登陆" />
							</dd>
						</dl>
					</fieldset>
				</form>
			</div>
			<!--  
			<div class="footer_login">
				<div class="left_footer_login">
					ISERVER | Powered by
					<a href="#">JIMMY</a>
				</div>
			</div>
			-->
		</div>
	</body>
</html>