<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<base href="/HDmedia/">
<meta charset="UTF-8">
<title>弘道传媒—后台管理</title>
<link rel="stylesheet" type="text/css" href="easyui/css/easyui.css">
<link rel="stylesheet" type="text/css" href="easyui/css/icon.css">
<link rel="stylesheet" type="text/css" href="easyui/css/demo.css">
<link rel="stylesheet" type="text/css" href="css/index.css">
<link rel="shortcut icon" type="image/x-icon" href="images/hd_01.png">

<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="easyui/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="easyui/js/index.js"></script>
<script type="text/javascript" src="easyui/js/easyui-lang-zh_CN.js"></script>

<script type="text/javascript" src="js/ajaxfileupload.js"></script>
<script type="text/javascript" src="js/showpic.js"></script>

<!--编译器 -->
<script type="text/javascript" src="ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="ueditor/ueditor.all.min.js"></script>
<script type="text/javascript" src="ueditor/lang/zh-cn/zh-cn.js"></script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false" id="newslogo">
		<div id="top_adminlogin_right">
			<c:if test="${not empty loginGuanLi }">
				<a href="#"><font color="#FF000" size="2">当前登录用户：${loginGuanLi.glname }</font></a>&nbsp;&nbsp;
					<a href="javascript:loginOut()"><font color="#FF0000" size="2">[注销]</font></a>
			</c:if>
			<c:if test="${empty loginGuanLi }">
				<a href="javascript:showlogin()"><font color="#FF0000" size="2">请登录</font></a>
				&nbsp;&nbsp;
				<a href="javascript:showzc()"><font color="#FF0000" size="2">免费注册</font></a>
			</c:if>
		</div>
	</div>
	<div data-options="region:'west',split:true,title:'导航'"
		style="width: 200px; padding: 10px;">
		<div id="aa" class="easyui-accordion"
			style="position: absolute; top: 27px; left: 0px; right: 0px; bottom: 0px;">
			<div title="人员菜单管理" iconcls="icon-save"
				style="overflow: auto; padding: 10px;">
				<ul class="easyui-tree" id="menu-tree">
					<li><span>人员管理</span>
						<ul>
							<li id="guanli_manager">管理员信息管理</li>
							<li data-options="state:'open'"><span>用户信息管理</span>
								<ul>
									<li id="yonghu_manager">用户注册信息管理</li>
									<li id="yonghu_more_manger">用户详细信息管理</li>
									<li id="search_yonghu">用户详细信息查询</li>
								</ul></li>
							<li id="change_pwd">修改密码</li>
						</ul>
					</li>
				</ul>
			</div>
			<div title="论坛管理" iconcls="icon-save"
				style="overflow: auto; padding: 10px;">
				<ul class="easyui-tree" id="notice-tree">
					<li><span>论坛管理</span>
						<ul>
							<li id="noticeclass_operate">论坛栏目操作</li>
							<li data-options="state:'open'"><span>帖子管理</span>
								<ul>
									<li id="invitation_browse">浏览帖子</li>
									<li id="invitation_operate">帖子操作</li>
								</ul></li>
						</ul></li>
				</ul>
			</div>
			<div title="作者及作品管理" iconcls="icon-save"
				style="overflow: auto; padding: 10px;">
				<ul class="easyui-tree" id="authorandwork-tree">
					<li data-options="state:'close'"><span>作者管理与作品操作</span>
						<ul>
							<li id="author_browse">作者管理</li>
							<li id="works_operate">作品操作</li>
						</ul></li>
				</ul>
			</div>
			<div title="文章管理" iconcls="icon-save"
				style="overflow: auto; padding: 10px;">
				<ul class="easyui-tree" id="article-tree">
					<li><span>文章</span>
						<ul>
							<li id="article_type">文章类型管理</li>
							<li id="article_browse">浏览文章管理</li>
							<li id="article_operate">文章评论管理</li>
							<li id="dt_dietary">饮食文化</li>
						</ul></li>
				</ul>
			</div>
			<div title="前台界面管理" iconcls="icon-save"
				style="overflow: auto; padding: 10px;">
				<ul class="easyui-tree" id="qtstage-tree">
					<li data-options="state:'open'"><span>导航栏管理</span>
						<ul>
							<li id="hd_nav_one">一级导航管理</li>
							<li id="hd_nav_two">二级导航管理</li>
						</ul>
					</li>
					<li data-options="state:'open'"><span>首页管理</span>
						<ul>
							<li id="index_image">首页图片管理</li>
							<li id="index_zixun">首页资讯管理</li>
						</ul>
					</li>
					<li data-options="state:'open'"><span>地域与名族</span>
						<ul>
							<li id="geography">地域</li>
							<li id="">名族</li>
						</ul>
					</li>
				</ul>
			</div>

			<div title="公告管理" iconcls="icon-reload" style="overflow: auto;"padding: 10px;">
				<li id="notice_browse">浏览公告</li>
				<li id="notice_operate">公告操作</li>
			</div>
			<div title="帖子管理" iconcls="icon-search" style="overflow: auto;"padding: 10px;">
			</div>
			<div title="资源管理"></div>
		</div>
	</div>
	<div data-options="region:'east',split:true,collapsed:true,title:'帮助'"
		style="width: 100px; padding: 0px; margin: 0px;">
		<img alt="图片加载失败" src="images/weixin.jpg" width="90px" height="90px">
	</div>
	<div class="footer" data-options="region:'south',border:false"
		id="copyright">
		<img src="images/hd_02.png" />
		<p>
			<a href="http://www.HDmedia.com">弘道传媒科技股份有限公司</a> &copy; 版权所有
			0730-100888666
		</p>
	</div>
	<div data-options="region:'center',title:'内容'">
		<div id="index_content" class="easyui-tabs" data-options="fit:true">
		</div>
	</div>
</body>
</html>