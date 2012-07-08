<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<table style="text-align: left;" align="left" border="0" cellpadding="0" cellspacing="0" width="180">
	<tbody>
		<tr>
			<td style="padding-top: 10px;" valign="top" width="20">
				<a id="link1" class="mmac"><div onclick="ShowMainMenu(1)">礼品</div></a>
				<a id="link2" class="mm"><div onclick="ShowMainMenu(2)">数据</div></a>
				<a id="link3" class="mm"><div onclick="ShowMainMenu(3)">发布</div></a>
				<a id="link4" class="mm"><div onclick="ShowMainMenu(4)">系统</div></a>
				<div class="mmf"></div>
			</td>
			<td id="mainct" valign="top" width="160">
				<div id="ct1" style="display: block;">
					<!-- Item 1 Strat -->
					<dl class="bitem" id="sunitems1_1">
						<dt onclick='showHide("items1_1")'>
							<b>礼品管理</b>
						</dt>
						<dd style="display:block;" class="sitem" id="items1_1">
							<ul class="sitemu">
								<li><a href="gift_items_list.action" >查询礼品</a></li>
								<li><a href="gift_items_saveP.action" >添加礼品</a></li>
							</ul>
						</dd>
					</dl>
					<!-- Item 1 End -->
					<!-- Item 2 Strat -->
					<dl class="bitem" id="sunitems2_1">
						<dt onclick='showHide("items2_1")'>
							<b>受赠礼人管理</b>
						</dt>
						<dd style="display:block" class="sitem" id="items2_1">
							<ul class="sitemu">

								<li><a href="gift_peoples_list.action?gift_peoples.p_flag=1" target="main">查询受礼人</a></li>
								<li><a href="gift_peoples_save.action?gift_peoples.p_flag=1" target="main">添加受礼人</a></li>
								<li><a href="gift_peoples_list.action?gift_peoples.p_flag=0" target="main">查询赠礼人</a></li>
								<li><a href="gift_peoples_save.action?gift_peoples.p_flag=0" target="main">添加赠礼人</a></li>

							</ul>
						</dd>
					</dl>
					<!-- Item 2 End -->
					<!-- Item 3 Strat -->
					<dl class="bitem" id="sunitems4_1">
						<dt onclick='showHide("items4_1")'>
							<b>仓库管理</b>
						</dt>
						<dd style="display:block" class="sitem" id="items4_1">
							<ul class="sitemu">
								<li><a href="gift_cangku_list.action">仓库一览</a></li>
							</ul>
						</dd>
					</dl>
					<!-- Item 3 End -->
					
				</div>
				<div id="ct2" style="display: none;">
					<!-- Item 1 Strat -->
					<dl class="bitem" id="sunitems1_2">
						<dt onclick='showHide("items1_2")'>
							<b>数据管理</b>
						</dt>
						<dd style="display:block;" class="sitem" id="items1_2">
							<ul class="sitemu">
								<li><a href="gift_data_page.action" target="main">数据导出</a></li>
								<li><a href="gift_data_page.action" target="main">数据导入</a></li>
							</ul>
						</dd>
					</dl>
					<!-- Item 1 End -->
				</div>
				<div id="ct3" style="display: none;">
					<!-- Item 1 Strat -->
					<dl class="bitem" id="sunitems1_3">
						<dt onclick='showHide("items1_3")'>
							<b>画册</b>
						</dt>
						<dd style="display:block;" class="sitem" id="items1_3">
							<ul class="sitemu">
								<li><a href="gift_lightbox_selectP.action">生成画册</a></li>
							</ul>
						</dd>
					</dl>
					<!-- Item 1 End -->
				</div>
				<div id="ct4" style="display: none;">
					
					<!-- Item 1 Strat -->
					<dl class="bitem" id="sunitems4_1">
						<dt onclick='showHide("items4_1")'>
							<b>用户组管理</b>
						</dt>
						<dd style="display:block" class="sitem" id="items4_1">
							<ul class="sitemu">
								<li><a href="gift_group_list.action">分组一览</a></li>
								<li><a href="gift_group_saveP.action">添加分组</a></li>
							</ul>
						</dd>
					</dl>
					<!-- Item 1 End -->
					<!-- Item 2 Strat -->
					<dl class="bitem" id="sunitems4_1">
						<dt onclick='showHide("items4_1")'>
							<b>用户管理</b>
						</dt>
						<dd style="display:block" class="sitem" id="items4_1">
							<ul class="sitemu">
								<li><a href="gift_user_list.action">用户一览</a></li>
								<li><a href="gift_user_saveP.action">添加用户</a></li>
							</ul>
						</dd>
					</dl>
					<!-- Item 2 End -->
				</div>
			</td>
		</tr>
		<tr>
			<td width="26"></td>
			<td valign="top" width="160">
				<img src="images/idnbgfoot.gif">
			</td>
		</tr>
	</tbody>
</table>
<script language="javascript">
	function myAlert() {
		alert('dede');
	}
</script>