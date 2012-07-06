<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title></title>
<link href="../css/menu_base.css" rel="stylesheet" type="text/css">
<link href="../css/frame.css" rel="stylesheet" type="text/css">
<link href="../css/main.css" rel="stylesheet" type="text/css">
<script src="../js/jquery.js" language="javascript" type="text/javascript"></script>
<script src="../js/dedeajax2.js" language="javascript" type="text/javascript"></script>
<script src="../js/leftmenu.js" language="javascript" type="text/javascript"></script>
<script src="../js/frame.js" language="javascript" type="text/javascript"></script>
</head>

<body class="showmenu">

	<div style="display: none;" class="pagemask"></div>

	<%@ include file="include_top.jsp"%>

	<div class="left">
		<div class="menu" id="menu">
			<%@ include file="include_menu.jsp"%>
		</div>
	</div>

	<div class="right">
		<div class="main" width="100%" align="center">
			<table align="center" border="0" cellpadding="0" cellspacing="0"
				width="98%">
				<tbody>
					<tr>
						<td align="center" valign="top">
							<table bgcolor="#D6D6D6" border="0" cellpadding="0"
								cellspacing="1" width="100%">
								<tbody>
									<tr>
										<td background="../images/newlinebg3.gif" height="26">
											<table border="0" cellpadding="0" cellspacing="0" width="98%">
												<tbody>
													<tr>
														<td align="center"><input class="coolbg np"
															onclick="location='';" value="添加文档" type="button">
															<input class="coolbg np" onclick="location='';"
															value="我的文档" type="button"> <input
															class="coolbg np" onclick="location='';" value="栏目管理"
															type="button"> <input class="coolbg np"
															onclick="location='';" value="更新列表" type="button">
															<input class="coolbg np" onclick="location='';"
															value="更新文档" type="button"> <input
															class="coolbg np" onclick="location='';" value="稿件审核"
															type="button"></td>
													</tr>
												</tbody>
											</table></td>
									</tr>
								</tbody>
							</table>
							<table border="0" cellpadding="0" cellspacing="0" width="100%">
								<tbody>
									<tr bgcolor="#FFFFFF">
										<td height="4"></td>
									</tr>
								</tbody>
							</table>
							<table bgcolor="#cfcfcf" border="0" cellpadding="2"
								cellspacing="1" width="100%">
								<tbody>
									<tr bgcolor="#E7E7E7">
										<td colspan="9" style="padding-left: 10px;"
											background="../images/tbg.gif" height="24">分类信息 &gt;文档列表</td>
									</tr>
									<form name="form2"></form>
									<tr align="center" bgcolor="#FBFCE2" height="25">
										<td width="6%">ID</td>
										<td width="6%">选择</td>
										<td width="26%">文章标题</td>
										<td width="11%">更新时间</td>
										<td width="11%">类目</td>
										<td width="10%">权限</td>
										<td width="8%">点击</td>
										<td width="10%">发布人</td>
										<td width="10%">操作</td>
									</tr>
									<tr onmousemove="javascript:this.bgColor='#FCFDEE';"
										onmouseout="javascript:this.bgColor='#FFFFFF';" align="center"
										bgcolor="#FFFFFF" height="22">
										<td>105</td>
										<td><input name="arcID" id="arcID" value="105" class="np"
											type="checkbox"></td>
										<td align="left">佰邦达科技（北京）有限公司招聘PHP开发工程师</td>
										<td>2010-04-07</td>
										<td>分类信息</td>
										<td>开放浏览</td>
										<td>0</td>
										<td>admin</td>
										<td><a href="javascript:editArc(105)">编辑</a> | <a
											href="javascript:viewArc(105)">预览</a></td>
									</tr>
									<tr onmousemove="javascript:this.bgColor='#FCFDEE';"
										onmouseout="javascript:this.bgColor='#FFFFFF';" align="center"
										bgcolor="#FFFFFF" height="22">
										<td>104</td>
										<td><input name="arcID" id="arcID" value="104" class="np"
											type="checkbox">
										</td>
										<td align="left"><a
											href="http://v57.demo.dedecms.com/dede/archives_do.php?aid=104&amp;dopost=editArchives"
											oncontextmenu="ShowMenu(event,this,104,'招聘PHP程序员3名底薪3000起 +奖金 （包吃住）')">
												<u>招聘PHP程序员3名底薪3000起 +奖金 （包吃住）</u> </a></td>
										<td>2010-04-07</td>
										<td>分类信息</td>
										<td>开放浏览</td>
										<td>0</td>
										<td>admin</td>
										<td><a href="javascript:editArc(104)">编辑</a> | <a
											href="javascript:viewArc(104)">预览</a></td>
									</tr>
									<tr onmousemove="javascript:this.bgColor='#FCFDEE';"
										onmouseout="javascript:this.bgColor='#FFFFFF';" align="center"
										bgcolor="#FFFFFF" height="22">
										<td>103</td>
										<td><input name="arcID" id="arcID" value="103" class="np"
											type="checkbox">
										</td>
										<td align="left"><a
											href="http://v57.demo.dedecms.com/dede/archives_do.php?aid=103&amp;dopost=editArchives"
											oncontextmenu="ShowMenu(event,this,103,'《每日商报》招聘PHP程序员、网页设计师')">
												<u>《每日商报》招聘PHP程序员、网页设计师</u> </a></td>
										<td>2010-04-07</td>
										<td>分类信息</td>
										<td>开放浏览</td>
										<td>0</td>
										<td>admin</td>
										<td><a href="javascript:editArc(103)">编辑</a> | <a
											href="javascript:viewArc(103)">预览</a></td>
									</tr>
									<tr onmousemove="javascript:this.bgColor='#FCFDEE';"
										onmouseout="javascript:this.bgColor='#FFFFFF';" align="center"
										bgcolor="#FFFFFF" height="22">
										<td>102</td>
										<td><input name="arcID" id="arcID" value="102" class="np"
											type="checkbox">
										</td>
										<td align="left"><a
											href="http://v57.demo.dedecms.com/dede/archives_do.php?aid=102&amp;dopost=editArchives"
											oncontextmenu="ShowMenu(event,this,102,'尊米网诚聘全职PHP程序员（广州）')">
												<u>尊米网诚聘全职PHP程序员（广州）</u> </a></td>
										<td>2010-04-07</td>
										<td>分类信息</td>
										<td>开放浏览</td>
										<td>0</td>
										<td>admin</td>
										<td><a href="javascript:editArc(102)">编辑</a> | <a
											href="javascript:viewArc(102)">预览</a></td>
									</tr>
									<tr onmousemove="javascript:this.bgColor='#FCFDEE';"
										onmouseout="javascript:this.bgColor='#FFFFFF';" align="center"
										bgcolor="#FFFFFF" height="22">
										<td>101</td>
										<td><input name="arcID" id="arcID" value="101" class="np"
											type="checkbox">
										</td>
										<td align="left"><a
											href="http://v57.demo.dedecms.com/dede/archives_do.php?aid=101&amp;dopost=editArchives"
											oncontextmenu="ShowMenu(event,this,101,'4000每月 招聘dede二次开发程序一名')">
												<u>4000每月 招聘dede二次开发程序一名</u> </a></td>
										<td>2010-04-07</td>
										<td>分类信息</td>
										<td>开放浏览</td>
										<td>0</td>
										<td>admin</td>
										<td><a href="javascript:editArc(101)">编辑</a> | <a
											href="javascript:viewArc(101)">预览</a></td>
									</tr>
									<tr onmousemove="javascript:this.bgColor='#FCFDEE';"
										onmouseout="javascript:this.bgColor='#FFFFFF';" align="center"
										bgcolor="#FFFFFF" height="22">
										<td>100</td>
										<td><input name="arcID" id="arcID" value="100" class="np"
											type="checkbox">
										</td>
										<td align="left"><a
											href="http://v57.demo.dedecms.com/dede/archives_do.php?aid=100&amp;dopost=editArchives"
											oncontextmenu="ShowMenu(event,this,100,'吉林地区 礼聘 PHP程序员  网页设计师 系统运维工程师  薪酬面议')">
												<u>吉林地区 礼聘 PHP程序员 网页设计师 系统运维工程师 薪酬面议</u> </a></td>
										<td>2010-04-07</td>
										<td>分类信息</td>
										<td>开放浏览</td>
										<td>1</td>
										<td>admin</td>
										<td><a href="javascript:editArc(100)">编辑</a> | <a
											href="javascript:viewArc(100)">预览</a></td>
									</tr>
									<tr onmousemove="javascript:this.bgColor='#FCFDEE';"
										onmouseout="javascript:this.bgColor='#FFFFFF';" align="center"
										bgcolor="#FFFFFF" height="22">
										<td>99</td>
										<td><input name="arcID" id="arcID" value="99" class="np"
											type="checkbox">
										</td>
										<td align="left"><a
											href="http://v57.demo.dedecms.com/dede/archives_do.php?aid=99&amp;dopost=editArchives"
											oncontextmenu="ShowMenu(event,this,99,'武汉地区招聘dedecms网页美工')">
												<u>武汉地区招聘dedecms网页美工</u> </a></td>
										<td>2010-04-07</td>
										<td>分类信息</td>
										<td>开放浏览</td>
										<td>0</td>
										<td>admin</td>
										<td><a href="javascript:editArc(99)">编辑</a> | <a
											href="javascript:viewArc(99)">预览</a></td>
									</tr>
									<tr bgcolor="#ffffff">
										<td colspan="9" height="24">&nbsp; <a
											href="javascript:selAll()" class="coolbg">全选</a> <a
											href="javascript:noSelAll()" class="coolbg">取消</a> <a
											href="javascript:updateArc(0)" class="coolbg">&nbsp;更新&nbsp;</a>
											<a href="javascript:checkArc(0)" class="coolbg">&nbsp;审核&nbsp;</a>
											<a href="javascript:adArc(0)" class="coolbg">&nbsp;推荐&nbsp;</a>
											<a href="javascript:;" onclick="moveArc(event,this,0)"
											class="coolbg">&nbsp;移动&nbsp;</a> <a
											href="javascript:delArc(0)" class="coolbg">&nbsp;删除&nbsp;</a>
										</td>
									</tr>

									<tr align="right" bgcolor="#F9FCEF">
										<td colspan="9" align="center" height="20"><span>共
												1页/7条记录 首页 1 2 3 4 5 6 7 下页 末页</span></td>
									</tr>
								</tbody>
							</table>
							<table border="0" cellpadding="0" cellspacing="0" width="100%">
								<tbody>
									<tr>
										<td height="4"></td>
									</tr>
									<tr bgcolor="#FFFFFF">
										<td height="26">
											<table bgcolor="#cfcfcf" border="0" cellpadding="1"
												cellspacing="1" width="100%">
												<tbody>
													<tr bgcolor="#EEF4EA">
														<form name="form3" action="content_sg_list.php"
															method="get"></form>
														<input name="dopost" value="listArchives" type="hidden">
														<td background="index_body_data/wbg.gif">
															<table border="0" cellpadding="0" cellspacing="0"
																width="600">
																<tbody>
																	<tr>
																		<td width="70">关键字：</td>
																		<td width="160"><input name="keyword" style=""
																			type="text"></td>
																		<td><input name="imageField"
																			src="../images/button_search.gif" class="np" border="0"
																			height="22" type="image" width="60">
																		</td>
																	</tr>
																</tbody>
															</table></td>

													</tr>
												</tbody>
											</table></td>
									</tr>
									<tr>
										<td colspan="2" height="4"></td>
									</tr>
								</tbody>
							</table></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>

</body>
</html>