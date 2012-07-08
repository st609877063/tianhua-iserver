<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!-- 功能地图 -->
<div style="display: none;" class="allmenu">
	<div class="allmenu-box">
		<dl class="maptop">
			<dt class="bigitem">文档相关</dt>
			<dd>
				<dl class="mapitem">
					<dt>常用操作</dt>
					<dd>
						<ul class="item">
							<li><a
								href="http://v57.demo.dedecms.com/dede/catalog_main.php"
								target="main">网站栏目管理</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/content_list.php"
								target="main">所有档案列表</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/content_list.php?arcrank=-1"
								target="main">等审核的档案</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/content_list.php?mid=1"
								target="main">我发布的文档</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/feedback_main.php"
								target="main">评论管理</a>
							</li>
							<li><a href="http://v57.demo.dedecms.com/dede/recycling.php"
								target="main">内容回收站</a>
							</li>
						</ul>
					</dd>
				</dl>
				<dl class="mapitem">
					<dt>内容管理</dt>
					<dd>
						<ul class="item">
							<li><a
								href="http://v57.demo.dedecms.com/dede/content_sg_list.php?channelid=-8"
								target="main">分类信息</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/content_list.php?channelid=1"
								target="main">普通文章</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/content_i_list.php?channelid=2"
								target="main">图片集</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/content_i_list.php?channelid=3"
								target="main">软件</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/content_list.php?channelid=6"
								target="main">商品</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/content_s_list.php"
								target="main">专题管理</a>
							</li>
						</ul>
					</dd>
				</dl>
				<dl class="mapitem">
					<dt>频道模型</dt>
					<dd>
						<ul class="item">
							<li><a
								href="http://v57.demo.dedecms.com/dede/mychannel_main.php"
								target="main">内容模型管理</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/templets_one.php"
								target="main">单页文档管理</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/stepselect_main.php"
								target="main">联动类别管理</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/freelist_main.php"
								target="main">自由列表管理</a>
							</li>
							<li><a href="http://v57.demo.dedecms.com/dede/diy_main.php"
								target="main">自定义表单</a>
							</li>
						</ul>
					</dd>
				</dl>
			</dd>
		</dl>
		<dl class="maptop">
			<dt class="bigitem">系统设置</dt>
			<dd>
				<dl class="mapitem">
					<dt>系统设置</dt>
					<dd>
						<ul class="item">
							<li><a href="http://v57.demo.dedecms.com/dede/sys_info.php"
								target="main">系统基本参数</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/sys_admin_user.php"
								target="main">系统用户管理</a>
							</li>
							<li><a href="http://v57.demo.dedecms.com/dede/sys_group.php"
								target="main">用户组设定</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/sys_multiserv.php"
								target="main">服务器分布/远程</a>
							</li>
							<li><a href="http://v57.demo.dedecms.com/dede/log_list.php"
								target="main">系统日志管理</a>
							</li>
							<li><a href="http://v57.demo.dedecms.com/dede/sys_safe.php"
								target="main">验证安全设置</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/sys_info_mark.php"
								target="main">图片水印设置</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/content_att.php"
								target="main">自定义文档属性</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/soft_config.php"
								target="main">软件频道设置</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/article_string_mix.php"
								target="main">防采集串混淆</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/article_template_rand.php"
								target="main">随机模板设置</a>
							</li>
							<li><a href="http://v57.demo.dedecms.com/dede/sys_task.php"
								target="main">计划任务管理</a>
							</li>
							<li><a href="http://v57.demo.dedecms.com/dede/sys_data.php"
								target="main">数据库备份/还原</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/sys_sql_query.php"
								target="main">SQL命令行工具</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/sys_verifies.php"
								target="main">文件校验[S]</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/sys_safetest.php"
								target="main">病毒扫描[S]</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/sys_repair.php"
								target="main">系统错误修复[S]</a>
							</li>
						</ul>
					</dd>
				</dl>
				<dl class="mapitem">
					<dt>模板管理</dt>
					<dd>
						<ul class="item">
							<li><a
								href="http://v57.demo.dedecms.com/dede/templets_main.php"
								target="main">默认模板管理</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/templets_tagsource.php"
								target="main">标签源码管理</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/mytag_main.php"
								target="main">自定义宏标记</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/mytag_tag_guide.php"
								target="main">智能标记向导</a>
							</li>
							<li><a href="http://v57.demo.dedecms.com/dede/tag_test.php"
								target="main">全局标记测试</a>
							</li>
						</ul>
					</dd>
				</dl>
			</dd>
		</dl>
		<dl class="maptop">
			<dt class="bigitem">必须辅助功能</dt>
			<dd>
				<dl class="mapitem">
					<dt>采集管理</dt>
					<dd>
						<ul class="item">
							<li><a href="http://v57.demo.dedecms.com/dede/co_main.php"
								target="main">采集节点管理</a>
							</li>
							<li><a href="http://v57.demo.dedecms.com/dede/co_url.php"
								target="main">临时内容管理</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/co_get_corule.php"
								target="main">导入采集规则</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/co_gather_start.php"
								target="main">监控采集模式</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/co_do.php?dopost=coall"
								target="main">采集未下载内容</a>
							</li>
						</ul>
					</dd>
				</dl>
				<dl class="mapitem">
					<dt>批量维护</dt>
					<dd>
						<ul class="item">
							<li><a
								href="http://v57.demo.dedecms.com/dede/sys_cache_up.php"
								target="main">更新系统缓存</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/content_batch_up.php"
								target="main">文档批量维护</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/search_keywords_main.php"
								target="main">搜索关键词维护</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/article_keywords_main.php"
								target="main">文档关键词维护</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/article_description_main.php"
								target="main">自动摘要|分页</a>
							</li>
							<li><a href="http://v57.demo.dedecms.com/dede/tags_main.php"
								target="main">TAG标签管理</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/sys_data_replace.php"
								target="main">数据库内容替换</a>
							</li>
						</ul>
					</dd>
				</dl>
				<dl class="mapitem">
					<dt>附件管理</dt>
					<dd>
						<ul class="item">
							<li><a href="http://v57.demo.dedecms.com/dede/media_add.php"
								target="main">上传新文件</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/media_main.php"
								target="main">附件数据管理</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/media_main.php?dopost=filemanager"
								target="main">文件式管理器</a>
							</li>
						</ul>
					</dd>
				</dl>
			</dd>
		</dl>
		<dl class="maptop">
			<dt class="bigitem">网站更新操作</dt>
			<dd>
				<dl class="mapitem">
					<dt>自动任务</dt>
					<dd>
						<ul class="item">
							<li><a
								href="http://v57.demo.dedecms.com/dede/makehtml_all.php"
								target="main">一键更新网站</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/sys_cache_up.php"
								target="main">更新系统缓存</a>
							</li>
						</ul>
					</dd>
				</dl>
				<dl class="mapitem">
					<dt>HTML更新</dt>
					<dd>
						<ul class="item">
							<li><a
								href="http://v57.demo.dedecms.com/dede/makehtml_homepage.php"
								target="main">更新主页HTML</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/makehtml_list.php"
								target="main">更新栏目HTML</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/makehtml_archives.php"
								target="main">更新文档HTML</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/makehtml_map_guide.php"
								target="main">更新网站地图</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/makehtml_rss.php"
								target="main">更新RSS文件</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/makehtml_js.php"
								target="main">获取JS文件</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/makehtml_spec.php"
								target="main">更新专题HTML</a>
							</li>
						</ul>
					</dd>
				</dl>
			</dd>
		</dl>
		<dl class="maptop">
			<dt class="bigitem">会员相关</dt>
			<dd>
				<dl class="mapitem">
					<dt>会员管理</dt>
					<dd>
						<ul class="item">
							<li><a
								href="http://v57.demo.dedecms.com/dede/member_main.php"
								target="main">注册会员列表</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/member_rank.php"
								target="main">会员级别设置</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/member_scores.php"
								target="main">积分头衔设置</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/member_model_main.php"
								target="main">会员模型管理</a>
							</li>
							<li><a href="http://v57.demo.dedecms.com/dede/member_pm.php"
								target="main">会员短信管理</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/member_guestbook.php"
								target="main">会员留言管理</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/member_info_main.php?type=feed"
								target="main">会员动态管理</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/member_info_main.php?type=mood"
								target="main">会员心情管理</a>
							</li>
						</ul>
					</dd>
				</dl>
				<dl class="mapitem">
					<dt>支付工具</dt>
					<dd>
						<ul class="item">
							<li><a
								href="http://v57.demo.dedecms.com/dede/cards_type.php"
								target="main">点卡产品分类</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/cards_manage.php"
								target="main">点卡产品管理</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/member_type.php"
								target="main">会员产品分类</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/member_operations.php"
								target="main">会员消费记录</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/shops_operations.php"
								target="main">商店订单记录</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/sys_payment.php"
								target="main">支付接口设置</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/shops_delivery.php"
								target="main">配货方式设置</a>
							</li>
						</ul>
					</dd>
				</dl>
			</dd>
		</dl>
		<dl class="maptop">
			<dt class="bigitem">基本模块插件</dt>
			<dd>
				<dl class="mapitem">
					<dt>模块管理</dt>
					<dd>
						<ul class="item">
							<li><a
								href="http://v57.demo.dedecms.com/dede/module_main.php"
								target="main">模块管理</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/module_upload.php"
								target="main">上传新模块</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/module_make.php"
								target="main">模块生成向导</a>
							</li>
						</ul>
					</dd>
				</dl>
				<dl class="mapitem">
					<dt>辅助插件</dt>
					<dd>
						<ul class="item">
							<li><a href="http://v57.demo.dedecms.com/dede/plus_main.php"
								target="main">插件管理器</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/erraddsave.php"
								target="main">挑错管理</a>
							</li>
							<li><a href="http://v57.demo.dedecms.com/dede/baidunews.php"
								target="main">百度新闻</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/file_manage_main.php"
								target="main">文件管理器</a>
							</li>
							<li><a href="http://v57.demo.dedecms.com/dede/ad_main.php"
								target="main">广告管理</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/friendlink_main.php"
								target="main">友情链接</a>
							</li>
							<li><a href="http://v57.demo.dedecms.com/dede/vote_main.php"
								target="main">投票模块</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/plus_bshare.php"
								target="main">bShare分享插件</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/catalog_do.php?dopost=guestbook"
								target="main">留言簿模块</a>
							</li>
							<li><a
								href="http://v57.demo.dedecms.com/dede/mynews_main.php"
								target="main">站内新闻发布</a>
							</li>
							<li><a href="http://v57.demo.dedecms.com/dede/mail_send.php"
								target="main">邮件订阅</a>
							</li>
						</ul>
					</dd>
				</dl>
			</dd>
		</dl>
		<br style="clear:both">
	</div>
</div>
<!-- 功能地图 -->

<div class="head">
	<div class="top">
		<!-- logo -->
		<div class="top_logo">
			<img src="images/admin_top_logo.gif" alt="" title="" id="topdedelogo"
				height="37" width="200">
		</div>
		<!-- logo -->

		<!-- 头部内容 -->
		<div class="top_link">
			<ul>
				<li class="welcome">您好：admin ，欢迎使用xxx！</li>
				<li><a href="#" target="menu">主菜单</a>
				</li>
				<li><a href="#"
					onclick="JumpFrame('catalog_menu.php','public_guide.php');">内容发布</a>
				</li>
				<li><a href="#"
					onclick="JumpFrame('index_menu.php','content_list.php');">内容维护</a>
				</li>
				<li><a href="#"
					onclick="JumpFrame('index_menu.php','index_body.php');">系统主页</a>
				</li>
				<li><a href="http://v57.demo.dedecms.com/index.php?upcache=1"
					target="_blank">网站主页</a>
				</li>
				<li><a href="http://v57.demo.dedecms.com/member"
					target="_blank">会员中心</a>
				</li>
				<li><a href="http://v57.demo.dedecms.com/dede/exit.php"
					target="_top">注销</a>
				</li>
			</ul>
			<div class="quick">
				<a href="#" class="ac_qucikmenu" id="ac_qucikmenu">快捷方式</a> <a
					href="#" class="ac_qucikadd" id="ac_qucikadd"> <!--ADD--> </a>
			</div>
		</div>
		<!-- 头部内容 -->
	</div>

	<!-- 隐藏菜单,功能地图 -->
	<div class="topnav">
		<div class="menuact">
			<a href="#" id="togglemenu">隐藏菜单</a> <a href="#" id="allmenu">功能地图</a>
		</div>
		<div class="nav" id="nav"></div>
	</div>
	<!-- 隐藏菜单,功能地图 -->
</div>

<!-- 快捷方式 -->
<div style="display: none;" class="qucikmenu" id="qucikmenu">
	<ul>
		<li><a href="http://v57.demo.dedecms.com/dede/content_list.php"
			target="main">文档列表</a>
		</li>
		<li><a href="http://v57.demo.dedecms.com/dede/feedback_main.php"
			target="main">评论管理</a>
		</li>
		<li><a href="http://v57.demo.dedecms.com/dede/public_guide.php"
			target="main">内容发布</a>
		</li>
		<li><a href="http://v57.demo.dedecms.com/dede/catalog_main.php"
			target="main">栏目管理</a>
		</li>
		<li><a href="http://v57.demo.dedecms.com/dede/sys_info.php"
			target="main">修改参数</a>
		</li>
		<li><a href="http://v57.demo.dedecms.com/dede/www.baidu.com"
			target="main">考研视频</a>
		</li>
		<li><a href="http://v57.demo.dedecms.com/dede/www.hao123.com"
			target="main">我的链接</a>
		</li>
		<li><a href="http://item.taobao.com/item.htm?id=16198856748"
			target="main">第五代充值软件</a>
		</li>
	</ul>
</div>
<!-- 快捷方式 -->