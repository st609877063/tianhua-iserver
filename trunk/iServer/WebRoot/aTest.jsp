<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>主页</title>
<link rel="stylesheet" type="text/css" href="static/css/style.css" />
<script type="text/javascript" src="static/js/jquery.min.js"></script>
<script type="text/javascript" src="static/js/ddaccordion.js"></script>
<script type="text/javascript">
ddaccordion.init({
	headerclass: "submenuheader", //Shared CSS class name of headers group
	contentclass: "submenu", //Shared CSS class name of contents group
	revealtype: "click", //Reveal content when user clicks or onmouseover the header? Valid value: "click", "clickgo", or "mouseover"
	mouseoverdelay: 200, //if revealtype="mouseover", set delay in milliseconds before header expands onMouseover
	collapseprev: true, //Collapse previous content (so only one open at any time)? true/false 
	defaultexpanded: [], //index of content(s) open by default [index1, index2, etc] [] denotes no content
	onemustopen: false, //Specify whether at least one header should be open always (so never all headers closed)
	animatedefault: false, //Should contents open by default be animated into view?
	persiststate: true, //persist state of opened contents within browser session?
	toggleclass: ["", ""], //Two CSS classes to be applied to the header when it's collapsed and expanded, respectively ["class1", "class2"]
	togglehtml: ["suffix", "<img src='static/images/plus.gif' class='statusicon' />", "<img src='static/images/minus.gif' class='statusicon' />"], //Additional HTML added to the header when it's collapsed and expanded, respectively  ["position", "html1", "html2"] (see docs)
	animatespeed: "fast", //speed of animation: integer in milliseconds (ie: 200), or keywords "fast", "normal", or "slow"
	oninit:function(headers, expandedindices){ //custom code to run when headers have initalized
		//do nothing
	},
	onopenclose:function(header, index, state, isuseractivated){ //custom code to run whenever a header is opened or closed
		//do nothing
	}
})
</script>
<script language="javascript" type="text/javascript" src="static/js/niceforms.js"></script>
<link rel="stylesheet" type="text/css" media="all" href="static/css/niceforms-default.css" />
</head>
<body>
<div id="main_container">

	<div class="header">
    <div class="logo"><a href="#"><img src="static/images/logo.gif" alt="" title="" border="0" /></a></div>
    
    <div class="right_header">欢迎 Admin<a href="#"></a> | <a href="#" class="messages">修改密码</a> | <a href="/iServer/user/logout?_method=get" class="logout">注销</a></div>
    <div class="jclock"></div>
    </div>
    
    <div class="main_content">
                    <div class="menu">
                    <ul>
                        <li><a class="current" href="#">后台首页</a></li>
	                    <li><a href="#">文章管理</a>
	                        <ul>
	                        <li><a href="" title="文章列表">文章列表</a></li>
	                        </ul>
	                    </li>
	                    <li><a href="#">用户管理</a>
	                        <ul>
	                        <li><a href="" title="用户列表">用户列表</a></li>
	                        </ul>
	                    </li>
	                    <li><a href="#">配置管理</a>
	                        <ul>
	                        <li><a href="" title="配置列表">配置列表</a></li>
	                        </ul>
	                    </li>
	                    <li><a href="#">其他管理</a>
	                    </li>	                   	                    	                    
                    </ul>
                    </div>  
    <div class="center_content">  
    <div class="left_content">
    
    		<div class="sidebar_search">
            <form>
            <input type="text" name="" class="search_input" value="search keyword" onclick="this.value=''" />
            <input type="image" class="search_submit" src="static/images/search.png" />
            </form>            
            </div>
    
            <div class="sidebarmenu">
            
                <a class="menuitem submenuheader" href="">文章管理</a>
                <div class="submenu">
                    <ul>
                    <li><a href="">文章列表</a></li>
                    </ul>
                </div>
                <a class="menuitem submenuheader" href="" >用户管理</a>
                <div class="submenu">
                    <ul>
                    <li><a href="">用户列表</a></li>
                    </ul>
                </div>
                <a class="menuitem submenuheader" href="">配置管理</a>
                <div class="submenu">
                    <ul>
                    <li><a href="">配置列表</a></li>
                    </ul>
                </div>
                <a class="menuitem" href="">其他管理</a>
                    
            </div>
            
            
            <div class="sidebar_box">
                <div class="sidebar_box_top"></div>
                <div class="sidebar_box_content">
                <h3>帮助</h3>
                <img src="static/images/info.png" alt="" title="" class="sidebar_icon_right" />
                <p>
					暂无
                </p>                
                </div>
                <div class="sidebar_box_bottom"></div>
            </div>
    </div>      
    <div class="right_content">                   
    <h2></h2> 
                                       
<table id="rounded-corner" summary="2007 Major IT Companies' Profit">
    <thead>
    	<tr>
        	<th scope="col" class="rounded-company"></th>
            <th scope="col" class="rounded">Product</th>
            <th scope="col" class="rounded">Details</th>
            <th scope="col" class="rounded">Price</th>
            <th scope="col" class="rounded">Date</th>
            <th scope="col" class="rounded">Edit</th>
            <th scope="col" class="rounded-q4">Delete</th>
        </tr>
    </thead>
        <tfoot>
    	<tr>
        	<td colspan="6" class="rounded-foot-left"><em>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut.</em></td>
        	<td class="rounded-foot-right">&nbsp;</td>

        </tr>
    </tfoot>
    <tbody>
    	<tr>
        	<td><input type="checkbox" name="" /></td>
            <td>Product name</td>
            <td>details</td>
            <td>150$</td>
            <td>12/05/2010</td>

            <td><a href="#"><img src="static/images/user_edit.png" alt="" title="" border="0" /></a></td>
            <td><a href="#" class="ask"><img src="static/images/trash.png" alt="" title="" border="0" /></a></td>
        </tr>
        
    	<tr>
        	<td><input type="checkbox" name="" /></td>
            <td>Product name</td>
            <td>details</td>
            <td>150$</td>
            <td>12/05/2010</td>

            <td><a href="#"><img src="static/images/user_edit.png" alt="" title="" border="0" /></a></td>
            <td><a href="#" class="ask"><img src="static/images/trash.png" alt="" title="" border="0" /></a></td>
        </tr> 
        
    	<tr>
        	<td><input type="checkbox" name="" /></td>
            <td>Product name</td>
            <td>details</td>
            <td>150$</td>
            <td>12/05/2010</td>

            <td><a href="#"><img src="static/images/user_edit.png" alt="" title="" border="0" /></a></td>
            <td><a href="#" class="ask"><img src="static/images/trash.png" alt="" title="" border="0" /></a></td>
        </tr>
        
    	<tr>
        	<td><input type="checkbox" name="" /></td>
            <td>Product name</td>
            <td>details</td>
            <td>150$</td>
            <td>12/05/2010</td>

            <td><a href="#"><img src="static/images/user_edit.png" alt="" title="" border="0" /></a></td>
            <td><a href="#" class="ask"><img src="static/images/trash.png" alt="" title="" border="0" /></a></td>
        </tr>  
    	<tr>
        	<td><input type="checkbox" name="" /></td>
            <td>Product name</td>
            <td>details</td>
            <td>150$</td>
            <td>12/05/2010</td>

            <td><a href="#"><img src="static/images/user_edit.png" alt="" title="" border="0" /></a></td>
            <td><a href="#" class="ask"><img src="static/images/trash.png" alt="" title="" border="0" /></a></td>
        </tr>
        
    	<tr>
        	<td><input type="checkbox" name="" /></td>
            <td>Product name</td>
            <td>details</td>
            <td>150$</td>
            <td>12/05/2010</td>

            <td><a href="#"><img src="static/images/user_edit.png" alt="" title="" border="0" /></a></td>
            <td><a href="#" class="ask"><img src="static/images/trash.png" alt="" title="" border="0" /></a></td>
        </tr>    
        
    </tbody>
</table>

	 <a href="index.jsp" class="bt_green"><span class="bt_green_lft"></span><strong>新增文章</strong><span class="bt_green_r"></span></a>
     <a href="#" class="bt_blue"><span class="bt_blue_lft"></span><strong>浏览文章</strong><span class="bt_blue_r"></span></a>
     <a href="#" class="bt_red"><span class="bt_red_lft"></span><strong>删除文章</strong><span class="bt_red_r"></span></a> 
     
     
     <div class="pagination">
     <span class="disabled"><< prev</span><span class="current">1</span><a href="">2</a><a href="">3</a><a href="">4</a><a href="">5</a>…<a href="">10</a><a href="">11</a><a href="">12</a>...<a href="">100</a><a href="">101</a><a href="">next >></a>
     </div> 
     
     <h2>消息提示</h2>
     <div class="warning_box">
        未完成。
     </div>
     <div class="valid_box">
        已完成。
     </div>    
     </div><!-- end of right content-->                    
  	 </div>   <!--end of center content -->               

    <div class="clear"></div>
    </div> <!--end of main content-->
    <div class="footer">
    	<div class="left_footer">iServer | Powered by <a href="#">iServer</a></div>
    	<div class="right_footer"><a href="#"><img src="static/images/indeziner_logo.gif" alt="" title="" border="0" /></a></div>
    </div>
</div>		
</body>
</html>