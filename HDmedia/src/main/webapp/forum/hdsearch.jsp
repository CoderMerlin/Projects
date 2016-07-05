<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<base href="/HDmedia/">
<meta charset="utf-8">
<meta name="MSSmartTagsPreventParsing" content="True">
<meta http-equiv="MSThemeCompatible" content="Yes">
<link rel="stylesheet" type="text/css"href="forum/css/style_2_common.css">
<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="forum/js/common_002.js" type="text/javascript"></script>
<script src="forum/js/common.js" type="text/javascript"></script>
<script src="forum/js/jquery-1.js" type="text/javascript"></script>
<meta content="width=1080" name="viewport">
<link href="forum/css/search.css" rel="stylesheet">
<title>弘道搜索</title>
</head>
<body>
	<div id="hd">
		<div class="wp">
			<div id="header_cont">
				<a href="#" class="turnbackhome">返回首页</a>
				<div id="accountOpt">
					<a href="#" target="_blank" title="五雨" alt="五雨" id="user_name">五雨</a>
					<a href="#" target="_blank"title="访问我的空间" id="user_avatar">
					<img src="forum/images/w300h300.jpg"></a>
				</div>
				<div style="left: 1049.2px; top: 48px; display: none;" id="accountPanel"> <!-- 人物信息 -->
					<div class="arrow"></div>
					<div class="user_avatar">
						<img src="forum/images/w300h300.jpg">
						<p class="userRole">
							<a href="#" target="_blank">梦想起航</a>
						</p>
					</div>
					<ul class="archives">
						<li><a href="#" target="_blank">1</a>
							<p>主题</p></li>
						<li><a href="#" target="_blank">12</a>
							<p>社区积分</p></li>
						<li style="border-right: none"><a href="#" target="_blank">1</a>
							<p>帖子</p></li>
					</ul>
					<ul class="accountInfo">
						<li><span class="label_coin">积&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;分</span><a
							title="169分">169</a></li>
						<li><span class="label_signed">总签到数</span><a title="1天"
							id="signcons">1</a></li>
					</ul>
					<div class="opts">
						<div class="optsWrap">
							<a href="#" target="_blank" class="opt"><i
								class="icon icon_msg"></i>
								<p>消息</p></a> <a href="#" target="_blank" class="opt">
								<i class="icon icon_friends"></i>
								<p>好友</p></a> <a href="#" target="_blank" class="opt"><i
								class="icon icon_medal"></i>
								<p>勋章</p></a> <a href="#" target="_blank" class="opt"><i
								class="icon icon_task"></i>
								<p>任务</p></a> <a href="#" target="_blank" class="opt"><i
								class="icon icon_setting"></i>
								<p>设置</p></a> <a href="#" target="_blank" class="opt"><i
								class="icon icon_mark"></i>
								<p>收藏</p></a>
						</div>
					</div>
					<a href="#" class="logout" target="_self">退出</a>
				</div>
			</div>
		</div>
		<div class="wp">
			<a href="" id="logo" class="iconfont">
			</a>
			<form class="searchform" method="post" autocomplete="off" action="hDSearch_getTeiZiByContent.action">
				<input name="formhash" value="057f0564" type="hidden">
				<div id="searchNav">
					<a href="#"><span data-type="forum" class="searchNav pull-left active">帖子</span></a> 
					<a href="#"><span data-type="user"
						class="searchNav pull-left ">作者</span></a> <a href="#"><span
						data-type="forumbug" class="searchNav pull-left ">BUG反馈</span></a> <a
						href="#"><span data-type="forumproposal"
						class="searchNav pull-left ">建议反馈</span></a> <span
						class="searchNav pull-right"> <a href="#">高级</a></span> 
						<span	class="searchNav pull-right" id="quickSearch"> 快速<i class="icon icon_arrow"></i>
					</span>
				</div>
				<input id="searchType" name="searchType" value="forum" type="hidden">
				<div class="searchBtn">
					<input name="srchtxt" class="searchTxt" placeholder="请输入搜索内容" value="" maxlength="50" type="text"> 
					<input name="searchsubmit" value="yes" type="hidden">
					<button id="scform_submit" class="searchSubmit" type="submit">
						<i class="icon icon_search"></i> <!-- 搜索的图片 -->
					</button>
				</div>
				<div class="tag copyr23">  <!-- 显示主要搜索内容 -->
					<span><a href="#" target="_blank">华 夏</a></span> <span><a
						href="#" target="_blank"> 美食文化</a></span> <span><a href="#"
						target="_blank">新手学堂</a></span> <span><a href="#" target="_blank">道玩</a></span>
					<span><a href="#" target="_blank">公摄</a></span> <span><a
						href="#" target="_blank">辩道</a></span> <span><a href="#"
						target="_blank">大嘴巴</a></span> <span><a href="#" target="_blank">因你不同</a></span>
				</div>
				<div style="left: 967.6px; top: 243.6px; display: none;"
					id="adPanel">
					<a href="#">1 小时以内的新帖</a> <a href="#">4 小时以内的新帖</a> <a href="#">8
						小时以内的新帖</a> <a href="#">24 小时以内的新帖</a> <a href="#">1 周内帖子</a> <a
						href="#">1 月内帖子</a> <a href="#">6 月内帖子</a> <a href="#">1 年内帖子</a>
				</div>
			</form>
			<div class="wp copyr2">
				<div class="result">
					<p class="resultTips">
						结果: <em>找到 “<span class="emfont">${sessionScope.searchs[0].keyword}</span>” 相关内容
						</em>
					</p>
					<div class="rlt_table">
						<div class="rlt_thead">
							<div class="rlt_tr">
								<div class="rlt_th rlt_title">标题</div>
								<div class="rlt_th rlt_module">板块</div>
								<div class="rlt_th rlt_author">作者</div>
								<div class="rlt_th rlt_complex">
									<a href="#" class="reply ">回复</a>/ <a href="#" class="check ">查看</a>
								</div>
								<div class="rlt_th rlt_lastUpdate">
									<a class="active" href="#">发布时间</a>
								</div>
							</div>
						</div>
						<div class="rlt_tbody">
						<c:forEach items="${sessionScope.searchs}" var="searchs">
							<div class="rlt_tr">
								<div class="rlt_th rlt_title">
									<a href="#"
									target="_blank" title="">${searchs.tzname}</a>
								</div>
								<div class="rlt_th rlt_module">
									<a href="#" title="${searchs.ltname}">${searchs.ltname}</a>
								</div>
								<div class="rlt_th rlt_author">
									<a href="#"
										title="${searchs.yhname }">${searchs.yhname }</a>
								</div>
								<div class="rlt_th rlt_complex">
									<a href="javascript:void(0);" class="reply">8</a>/<a
										href="javascript:void(0);" class="check">2580</a>
								</div>
								<div class="rlt_th rlt_lastUpdate" title="${searchs.tztime}">${searchs.tztime}</div>
								<div class="rlt_th rlt_content">
									...${searchs.keyWordBefore}<span
										class="keyword">${searchs.keyword}</span>${searchs.keyWordAfter}
									...
								</div>
							</div>
						</c:forEach>
						<!-- 尾部 -->
						<%-- <div class="rlt_tfoot">
							<div class="pgs cl mbm">
								<div class="pg">
									<strong>1</strong><a
										href="#">2</a>
										<a href="#" class="nxt" title="下一页">&gt;</a><label><span
										style="float: left; margin-left: 15px; color: #7f7f7f">
											跳转至 </span><input id="pageInput" name="custompage" class="px"
										size="2" title="输入页码，按回车快速跳转" value="1"
										onkeydown="if(event.keyCode==13) {window.location='search.php?mod=forum&amp;kw=aaa&amp;orderby=dateline&amp;ascdesc=desc&amp;special=&amp;srchfilter=all&amp;searchsubmit=yes&amp;srchtype=title&amp;page='+this.value;; doane(event);}"
										type="text"></label>
								</div>
							</div>
						</div> --%>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	$(function() {
	    $(window).scroll(function() {
	    	console.info("滚动函数");
	        var scrollHeight = $(document).scrollTop();
	        if (scrollHeight > 80) {
	            $('#scrollTop').css("display","block");
	        } else {
	            $('#scrollTop').css("display","none");
	        }
	    });
	})
	</script>
	<script src="forum/js/search.js" type="text/javascript"></script>
	<div id="ft" class="wp cl"></div>
	<div style="right: 182.5px; display: none;" id="scrollTop">
		<!--右边导航-->
		<a class="sclTop_share sclTop_shareWX" href="javascript:void(0);">
			<i class="icon icon_weixin"></i>
		</a> <a class="sclTop_share sclTop_shareWB"
			href="#" target="_blank"> <i
			class="icon icon_weibo"></i>
		</a> <a class="sclTop_share sclTop_goTop" href="javascript:void(0);">
			<i class="icon icon_goTop"></i>
		</a>
		<div id="wechatPic"></div>
	</div>
	
	
	<!-- end goTop -->
	<script type="text/javascript">
		function goto_mobile_href() {
			var f = window.location.href;
			var isfeedback = parseInt('');
			if (isfeedback == 1) {
				window.location.href = 'index.php?mobile=yes';
			} else if (f.indexOf('mobile=no') !== -1) {
				window.location.href = f.replace(/mobile=no/, 'mobile=yes');
			} else if (f.indexOf('mobile=yes') > 0) {
				location.reload();
			} else if (f === 'http://bbs.flyme.cn'
					|| f === 'http://bbs.flyme.cn/'
					|| f.indexOf('http://bbs.flyme.cn/index.html') > -1) {
				window.location.href = 'http://bbs.flyme.cn/index.php?mobile=yes';
			} else if (f.substr(-1) == '/' || f.substr(-4) == 'html'
					|| f.substr(-3) == 'php') {
				window.location.href = f + '?mobile=yes';
			} else {
				window.location.href = f + '&mobile=yes';
			}
		}
		
		
		
	</script>
	<div id="flymeFooter" class="footerWrap">
		<div class="footerInner">
			<div class="footer-layer1">
				<div class="footer-innerLink" id="footer-innerLink">
					<a href="http://www.meizu.com/about.html" target="_blank"
						title="关于魅族">关于魅族</a> <img class="foot-line"
						src="forum/images/space.gif"> <a href="http://hr.meizu.com/"
						target="_blank" title="工作机会">工作机会</a> <img class="foot-line"
						src="forum/images/space.gif"> <a
						href="http://www.meizu.com/contact.html" target="_blank"
						title="联系我们">联系我们</a> <img class="foot-line"
						src="forum/images/space.gif"> <a
						href="http://www.meizu.com/legalStatement.html" target="_blank"
						title="法律声明">法律声明</a>
				</div>
				<div class="footer-service">
					<span class="service-label">客服热线</span> <span class="service-num">400-788-3333</span>
					<a id="service-online" class="service-online"
						href="javascript:void(0);" title="在线客服">在线客服</a>
				</div>
				<div class="footer-outerLink">
					<a class="footer-sinaMblog" href="http://weibo.com/meizuflyme"
						target="_blank"><i class="i_icon"></i></a> <a
						class="footer-tencentMblog" href="http://t.qq.com/mx_flyme"
						target="_blank"><i class="i_icon"></i></a> <a id="footer-weChat"
						class="footer-weChat" href="javascript:void(0);" target="_blank"><i
						class="i_icon"></i></a> <a class="footer-qzone" style="display: none;"
						href="#" target="_blank"><i class="i_icon"></i></a>
				</div>
			</div>
			<div class="clear"></div>
			<div id="flymeCopyright" class="copyrightWrap">
				<div class="copyrightInner">
					<span>©2016 HongDao Telecom Equipment Co., Ltd. All rights
						reserved.</span> <a href="#" target="_blank">备案号：粤ICP备13003602号-4</a> <a
						target="_blank" href="#" hidefocus="true">经营许可证编号：粤B2-20130198</a>
					<a target="_blank" href="#" hidefocus="true">营业执照</a>
				</div>
			</div>
		</div>
	</div>
	<script src="forum/js/commonLib.js" type="text/javascript"></script>
	<div style="display: none">
		&nbsp;&nbsp;<span id="tcss"></span>
		<script type="text/javascript" src="forum/js/flow.js"></script>
	</div>
</body>
</html>