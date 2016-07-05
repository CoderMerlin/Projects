<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<base href="/HDmedia/"/>
<meta charset="utf-8">
<title>论坛</title>
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="short icon" href="images/hd_01.png"/>  <!--小图标，需要浏览器兼-->
<link rel="stylesheet" href="home/css/Hd_nav.css" />    <!--导航栏css-->
<link rel="stylesheet" href="home/css/footer.css" />    <!--尾部-->
<!--尾部-->
<link rel="stylesheet" href="forum/css/forumindex.css" />
<!--论坛中心-->
</head>

<body>
	<%@ include file="../home/header.jsp"%>
	<div class="forum-main">
		<!--整个论坛-->
		<div id="mian-all" class="main-all">
			<!--整个中心-->
			<div id="forum-adv" class="forum-adv">
				<!--论坛广告位-->
				<div class="adv_1">广告位1</div>
				<div class="adv_2">广告位2</div>
			</div>
			<div class="forum-all-content">
				<!--所有的内容-->
				<div class="invitation-and-user">
					<!--显示帖子和用户的数量-->
					<p style="">
						今日:<em>${sessionScope.TerdayCount.terdayCount } </em> <span class="pipe">|</span> 昨日:<em> ${sessionScope.YesterdayCount.yesterdayCount }</em> <span
							class="pipe">|</span> 帖子:<em>${sessionScope.count.count }</em> <span class="pipe">|</span>
						会员:<em>${loginUser.yhzcid }</em> <span class="pipe">|</span> 欢迎:<em> <span
							class="xi2">${loginUser.yhname }</span>
						</em>
					</p>
					<!-- <div class="check-invitation">
						查看帖子数量
						<a target="_blank" href="tieZi_findbyLtids.action">查看帖子</a>
					</div> -->
				</div>
				<div class="all-invitation">
					<!--所有的帖子-->
					<div class="invitation-1">
						<!--第一个帖子区-->
						<div class="communication-region"
							style="width: 100%; height: 60px; background-color: #FFF;">
							<!--交流区-->
							<h2>交流区</h2>
						</div>
						<div class="Newbie-communication"
							style="width: 100%; height: 130px; background-color: #FFF; margin-top: 1px;">
							<!--新手交流区-->
							<table class="communication-region-table">
								<tr>
									<c:forEach items="${sessionScope.luntanjlq}" var="item">
										<c:if test="${item.rownum < 4}">
											<div
												style="width: 30%; height: 80px; margin-left: 2.2%; margin-top: 15px; float: left">
												<div style="width: 80px; height: 80px; float: left;">
													<a><img src="../upload/${item.ltyl2 }"
														style="width: 25% px; height: 100%;" /></a>
												</div>
												<div>
											
													<dl style="margin-left: 100px;">
														<dt class="smoll-title"
															style="font-size: 18px; line-height: 24px; color: #F00;">
															<a href="tieZis_findbyLtids.action?ltid=${item.ltid }">${item.ltname }</a>
														</dt>
														<dd class="invitation-total"
															style="font-size: 14px; line-height: 24px; color: #333;">帖子数：${item.count }
														</dd>
														<dd class="invitation-total"
															style="font-size: 14px; line-height: 24px; color: #333;">今日：${item.dtcount}
															&nbsp;&nbsp; 最新：${item.tztime }</dd>
													</dl>
												</div>
											</div>
										</c:if>
									</c:forEach>
								</tr>
							</table>
						</div>
					</div>


					<div class="invitation-2"
						style="width: 100%; height: 450px; margin-top: 5px;">
						<!---第二个帖子区-->
						<div class="culture-invitation"
							style="width: 100%; height: 60px; background-color: #FFF;">
							<!--文化帖子社区-->
							<h2>文化交流社区</h2>
						</div>
						<div
							style="width: 100%; height: 390px; background-color: #FFF; margin-top: 1px;">
							<!--文化主区-->
							<c:forEach items="${sessionScope.luntanwhjlq}" var="item">
								<c:if test="${item.rownum < 13}">
									<div
										style="width: 30%; height: 80px; margin-left: 2.2%; margin-top: 15px; float: left">
										<div style="width: 80px; height: 80px; float: left;">
											<a><img src="../upload/${item.ltyl2 }" style="width: 25% px; height: 100%;" /></a>
										</div>
										<div>
											<dl style="margin-left: 100px;">
												<dt class="smoll-title"
													style="font-size: 18px; line-height: 24px; color: #F00;">
													<a href="tieZis_findbyLtids.action?ltid=${item.ltid }">${item.ltname }</a>
												</dt>
												<dd class="invitation-total"
													style="font-size: 14px; line-height: 24px; color: #333;">帖子数：${item.count }
												</dd>
												<dd class="invitation-total"
													style="font-size: 14px; line-height: 24px; color: #333;">今日：${item.dtcount}
													&nbsp;&nbsp; 最新：${item.tztime }</dd>
											</dl>
										</div>
									</div>
								</c:if>
							</c:forEach>
						</div>
					</div>
					<div class="invitation-3"
						style="width: 100%; height: 200px; padding-top: 5px;">
						<!--资源分享-->
						<div class="resource-sharing"
							style="width: 100%; height: 60px; background-color: #FFF;">
							<!--分享区-->
							<h2>资源分享</h2>
						</div>
						<div>
							<table
								style="width: 100%; height: 140px; background-color: #FFF; margin-top: 1px; padding: 10px 20px;">
								<tr>
									<c:forEach items="${sessionScope.luntanzyfx}" var="item">
										<c:if test="${item.rownum < 4}">
											<td style="width: 30%; height: 80px;">
												<div style="width: 80px; height: 80px; float: left;">
													<a><img src="../upload/${item.ltyl2 }"
														style="width: 100%; height: 100%;" /></a>
												</div>
												<div>
													<dl style="margin-left: 100px;">
														<dt class="smoll-title"
															style="font-size: 18px; line-height: 24px; color: #F00;">
															<a href="tieZis_findbyLtids.action?ltid=${item.ltid }">${item.ltname }</a>
														</dt>
														<dd class="invitation-total"
															style="font-size: 14px; line-height: 24px; color: #333;">帖子数：${item.count }
														</dd>
														<dd class="invitation-total"
															style="font-size: 14px; line-height: 24px; color: #333;">今日：${item.dtcount}
															&nbsp;&nbsp; 最新：${item.tztime }</dd>
													</dl>
												</div>
											</td>
										</c:if>
									</c:forEach>
								</tr>
							</table>
						</div>
					</div>
					<div class="invitation-4"
						style="width: 100%; height: 300px; padding-top: 5px;">
						<!--站务管理-->
						<div class="Web-manage"
							style="width: 100%; height: 60px; background-color: #FFF;">
							<!--管理区-->
							<h2>站务管理</h2>
						</div>
						<div>
							<table
								style="width: 100%; height: 100px; background-color: #FFF; margin-top: 1px; padding: 10px 20px;">
								<tr>
									<c:forEach items="${sessionScope.luntanzzhz}" var="item">
										<c:if test="${item.rownum < 4}">
											<td style="width: 30%; height: 80px;">
												<div style="width: 80px; height: 80px; float: left;">
													<a><img src="../upload/${item.ltyl2 }"
														style="width: 100%; height: 100%;" /></a>
												</div>
												<div>
													<dl style="margin-left: 100px;">
														<dt class="smoll-title"
															style="font-size: 18px; line-height: 24px; color: #F00;">
															<a href="tieZis_findbyLtids.action?ltid=${item.ltid }">${item.ltname }</a>
														</dt>
														<dd class="invitation-total"
															style="font-size: 14px; line-height: 24px; color: #333;">帖子数：${item.count }
														</dd>
														<dd class="invitation-total"
															style="font-size: 14px; line-height: 24px; color: #333;">今日：${item.dtcount}
															&nbsp;&nbsp; 最新：${item.tztime }</dd>
													</dl>
												</div>
											</td>
										</c:if>
									</c:forEach>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		
	</div>
	<%@ include file="../home/footer.jsp"%>
</body>
</html>
