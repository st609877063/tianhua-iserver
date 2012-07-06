<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title></title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="shortcut icon" href="images/favicon.gif" type="image/ico" />
<link rel="stylesheet" href="css/screen.css" type="text/css" media="screen" />
<link rel="stylesheet" href="css/lightbox.css" type="text/css" media="screen" />

<script src="js/jquery-1.7.2.min.js"></script>
<script src="js/jquery-ui-1.8.18.custom.min.js"></script>
<script src="js/jquery.smooth-scroll.min.js"></script>
<script src="js/lightbox.js"></script>

<script>
	jQuery(document).ready(function($) {
		$('a').smoothScroll({
			speed : 1000,
			easing : 'easeInOutCubic'
		});

		$('.showOlderChanges').on('click', function(e) {
			$('.changelog .old').slideDown('slow');
			$(this).fadeOut();
			e.preventDefault();
		});
	});
</script>
</head>

<body>
	<div id="content">
		<div class="section" id="example">
			<h2>Examples</h2>
			<h3>Single image</h3>
			<div class="imageRow">
				<div class="single">
					<a href="images/examples/image-2.jpg" rel="lightbox" title="Optional caption.">
						<img src="images/examples/thumb-2.jpg" alt="" />
					</a>
				</div>
			</div>

			<h3 style="clear: both;">Image set</h3>
			<div class="imageRow">
				<div class="set">
					<div class="single first">
						<a href="images/examples/image-3.jpg" rel="lightbox[plants]"
							title="礼品礼品礼品礼品礼品礼品礼品礼品礼品礼品礼品礼品">
							<img src="images/examples/thumb-3.jpg" alt="Plants: image 1 0f 4 thumb" />
						</a>
					</div>
					<div class="single">
						<a href="images/examples/image-4.jpg" rel="lightbox[plants]"
							title="Alternately you can press the right arrow key.">
							<img src="images/examples/thumb-4.jpg" alt="Plants: image 2 0f 4 thumb" />
						</a>
					</div>
					<div class="single">
						<a href="images/examples/image-5.jpg" rel="lightbox[plants]"
							title="The script preloads the next image in the set as you're viewing.">
							<img src="images/examples/thumb-5.jpg" alt="Plants: image 3 0f 4 thumb" />
						</a>
					</div>
					<div class="single last">
						<a href="images/examples/image-6.jpg" rel="lightbox[plants]"
							title="Click the X or anywhere outside the image to close">
							<img src="images/examples/thumb-6.jpg" alt="Plants: image 4 0f 4 thumb" />
						</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>