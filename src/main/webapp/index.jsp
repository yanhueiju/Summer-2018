<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	//通过JSP的 代码，在浏览器端来获取 java后台访问的根URL
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML>
<html>

<head>
<!--  在head标签中，添加一个 当前界面访问工程的根 路径  -->
<base href="<%=basePath%>">
		<meta charset="utf-8">
		<meta name="renderer" content="webkit|ie-comp|ie-stand">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
		<meta http-equiv="Cache-Control" content="no-siteapp" />
		<link rel="Bookmark" href="/favicon.ico">
		<link rel="Shortcut Icon" href="/favicon.ico" />
		<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
		<link rel="stylesheet" type="text/css" href="static/h-ui/css/H-ui.min.css" />
		<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/H-ui.admin.css" />
		<link rel="stylesheet" type="text/css" href="lib/Hui-iconfont/1.0.8/iconfont.css" />
		<link rel="stylesheet" type="text/css" href="static/h-ui.admin/skin/default/skin.css" id="skin" />
		<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/style.css" />
		<!--[if IE 6]>
<script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
<script>DD_belatedPNG.fix('*');</script>
<![endif]-->
		<title>国税后台管理系统</title>
	</head>

	<body>
		<header class="navbar-wrapper">
			<div class="navbar navbar-fixed-top">
				<div class="container-fluid cl">
					<a class="logo navbar-logo f-l mr-10 hidden-xs" href="/aboutHui.shtml">国税后台管理系统</a>
					<a class="logo navbar-logo-m f-l mr-10 visible-xs" href="/aboutHui.shtml">国税后台管理系统</a>
					<span class="logo navbar-slogan f-l mr-10 hidden-xs">v3.1</span>
					<a aria-hidden="false" class="nav-toggle Hui-iconfont visible-xs" href="javascript:;">&#xe667;</a>
					<nav class="nav navbar-nav">

					</nav>
					<nav id="Hui-userbar" class="nav navbar-nav navbar-userbar hidden-xs">
						<ul class="cl">
							<li>${user.username}</li>
							<li class="dropDown dropDown_hover">
								<a href="javascript:;" class="dropDown_A">${user.type} <i class="Hui-iconfont">&#xe6d5;</i></a>
								<ul class="dropDown-menu menu radius box-shadow">
									<li>
										<a href="javascript:;" onClick="myselfinfo()">个人信息</a>
									</li>
									<li>
										<a href="login.jsp">切换账户</a>
									</li>
									<li>
										<a href="login/logout.action">退出</a>
									</li>
								</ul>
							</li>
						</ul>
					</nav>
				</div>
			</div>
		</header>
		<aside class="Hui-aside">
			<div class="menu_dropdown bk_2">
				<dl id="menu-article">
					<dt><i class="Hui-iconfont">&#xe616;</i> 信息管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
					<dd>
						<ul>
							<li>
								<!-- <a data-href="pages/info_manager/article-list.jsp" data-title="信息发布管理" href="javascript:void(0)">信息发布管理</a> -->
								<a data-href="info/all.action" data-title="信息发布管理" href="javascript:void(0)">信息发布管理</a>
							</li>
							
							<li>
								<!-- <a data-href="pages/info_manager/product-list.jsp" data-title="纳税咨询管理" href="javascript:void(0)">纳税咨询管理</a> -->
								<a data-href="tax/all.action" data-title="纳税咨询管理" href="javascript:void(0)">纳税咨询管理</a>
							</li>
						</ul>
					</dd>
				</dl>
				<!-- <dl id="menu-picture">
					<dt><i class="Hui-iconfont">&#xe613;</i> 服务管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
					<dd>
						<ul>
							<li>
								<a data-href="#" data-title="服务调查管理" href="javascript:void(0)">服务调查管理</a>
							</li>
							<li>
								<a data-href="#" data-title="易告知管理" href="javascript:void(0)">易告知管理</a>
							</li>
						</ul>
					</dd>
				</dl> -->
				<dl id="menu-admin">
					<dt><i class="Hui-iconfont">&#xe62d;</i> 管理员管理<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
					<dd>
						<ul>
							<li>
								<!-- <a data-href="pages/admin_manager/admin-role.jsp" data-title="角色管理" href="javascript:void(0)">角色管理</a> -->
								<a data-href="role/all.action" data-title="角色管理" href="javascript:void(0)">角色管理</a>
							</li>
							<li>
								<!-- <a data-href="pages/admin_manager/admin-list.jsp" data-title="管理员列表" href="javascript:void(0)">管理员列表</a> -->
								<a data-href="admin/all.action" data-title="管理员列表" href="javascript:void(0)">管理员列表</a>
							</li>
						</ul>
					</dd>
				</dl>
				<dl id="menu-tongji">
					<dt><i class="Hui-iconfont">&#xe61a;</i> 系统统计<i class="Hui-iconfont menu_dropdown-arrow">&#xe6d5;</i></dt>
					<dd>
						<ul>
							<li>
								<a data-href="pages/report_manager/charts-1.html" data-title="折线图" href="javascript:void(0)">折线图</a>
							</li>
							<li>
								<a data-href="pages/report_manager/charts-2.html" data-title="时间轴折线图" href="javascript:void(0)">时间轴折线图</a>
							</li>
							<li>
								<a data-href="pages/report_manager/charts-3.html" data-title="区域图" href="javascript:void(0)">区域图</a>
							</li>
							<li>
								<a data-href="pages/report_manager/charts-4.html" data-title="柱状图" href="javascript:void(0)">柱状图</a>
							</li>
							<li>
								<a data-href="pages/report_manager/charts-5.html" data-title="饼状图" href="javascript:void(0)">饼状图</a>
							</li>
							<li>
								<a data-href="pages/report_manager/charts-6.html" data-title="3D柱状图" href="javascript:void(0)">3D柱状图</a>
							</li>
							<li>
								<a data-href="pages/report_manager/charts-7.html" data-title="3D饼状图" href="javascript:void(0)">3D饼状图</a>
							</li>
						</ul>
					</dd>
				</dl>
			</div>
		</aside>
		<div class="dislpayArrow hidden-xs">
			<a class="pngfix" href="javascript:void(0);" onClick="displaynavbar(this)"></a>
		</div>
		<section class="Hui-article-box">
			<div id="Hui-tabNav" class="Hui-tabNav hidden-xs">
				<div class="Hui-tabNav-wp">
					<ul id="min_title_list" class="acrossTab cl">
						<li class="active">
							<span title="我的桌面" data-href="welcome.html">我的桌面</span>
							<em></em></li>
					</ul>
				</div>
				<div class="Hui-tabNav-more btn-group">
					<a id="js-tabNav-prev" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d4;</i></a>
					<a id="js-tabNav-next" class="btn radius btn-default size-S" href="javascript:;"><i class="Hui-iconfont">&#xe6d7;</i></a>
				</div>
			</div>
			<div id="iframe_box" class="Hui-article">
				<div class="show_iframe">
					<div style="display:none" class="loading"></div>
					<iframe scrolling="yes" frameborder="0" src="welcome.html"></iframe>
				</div>
			</div>
		</section>

		<div class="contextMenu" id="Huiadminmenu">
			<ul>
				<li id="closethis">关闭当前 </li>
				<li id="closeall">关闭全部 </li>
			</ul>
		</div>
		<!--_footer 作为公共模版分离出去-->
		<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
		<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
		<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script>
		<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script>
		<!--/_footer 作为公共模版分离出去-->

		<!--请在下方写此页面业务相关的脚本-->
		<script type="text/javascript" src="lib/jquery.contextmenu/jquery.contextmenu.r2.js"></script>
		<script type="text/javascript">
			$(function() {
				$("#min_title_list li").contextMenu('Huiadminmenu', {
					bindings: {
						'closethis': function(t) {
							console.log(t);
							if(t.find("i")){
								t.find("i").trigger("click");
							}		
						},
						'closeall': function(t) {
							alert('Trigger was '+t.id+'\nAction was Email');
						},
					}
				});
			});
			/*个人信息*/
			function myselfinfo() {
				layer.open({
					type: 1,
					area: ['500px', '350px'],
					fix: false, //不固定
					maxmin: true,
					shade: 0.4,
					title: '查看信息',
					content: "<div>&nbsp;&nbsp;&nbsp;&nbsp;<b>用户名：</b>${user.username}</br></div><div>&nbsp;&nbsp;&nbsp;&nbsp;<b>用户类型：</b>${user.type}</br></div>"
				});
			}

			/*资讯-添加*/
			function article_add(title, url) {
				var index = layer.open({
					type: 2,
					title: title,
					content: url
				});
				layer.full(index);
			}
			/*图片-添加*/
			function picture_add(title, url) {
				var index = layer.open({
					type: 2,
					title: title,
					content: url
				});
				layer.full(index);
			}
			/*产品-添加*/
			function product_add(title, url) {
				var index = layer.open({
					type: 2,
					title: title,
					content: url
				});
				layer.full(index);
			}
			/*用户-添加*/
			function member_add(title, url, w, h) {
				layer_show(title, url, w, h);
			}
		</script>

		<!--此乃百度统计代码，请自行删除-->
		<script>
			var _hmt = _hmt || [];
			(function() {
				var hm = document.createElement("script");
				hm.src = "https://hm.baidu.com/hm.js?080836300300be57b7f34f4b3e97d911";
				var s = document.getElementsByTagName("script")[0];
				s.parentNode.insertBefore(hm, s);
			})();
		</script>
		<!--/此乃百度统计代码，请自行删除-->
	</body>

</html>