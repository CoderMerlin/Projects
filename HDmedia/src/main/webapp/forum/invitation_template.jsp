<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!doctype html>
<html>
<head>
	<base href="/HDmedia/"/>
    <meta charset="utf-8">
    <title>帖子模板</title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/> <!--默认为屏幕的宽度-->
    <link rel="short icon" href="images/hd_01.png"/>  <!--小图标，需要浏览器兼-->
    <link rel="stylesheet" href="home/css/Hd_nav.css" />    <!--导航栏css-->
    <link rel="stylesheet" href="home/css/footer.css" />    <!--尾部-->
    <link rel="stylesheet" href="forum/css/invitation_template_main.css" /> <!--帖子的主要样式-->
    <script type="text/javascript" src="forum/js/invitationlook.js"></script>
</head>
<style>
	.myinput{
		width:150px;
		border:1px solid #f63;
	}
	label{
		padding-right:10px;
	}
	textarea{
		resize:none;
	}
</style>
<script type="text/javascript">
	function pageLinkClick(pageNo,ltid){
		document.forms[0].action ='http://localhost:8080/HDmedia/web/tieZis_findbyLtids.action?pageNo='+pageNo+'&ltid='+ltid;
		document.forms[0].submit();
	}
</script>
<body>
	<%@ include file="../home/header.jsp" %>
    <!--查看贴的模板-->
    <div class="invitation_template-mian" ><!--整个帖子-->
    	<div class="invitation-main-all"><!--整个中心-->
        	<div id="forum-adv" class="forum-adv"><!--论坛广告位-->
            	<div class="adv_1">广告位1</div>
                <div class="adv_2">广告位2</div>
            </div>
            <div  class="invitation-content"><!--左边发的帖子-->
            	<div class="invitation-content-a"><!--热门板块-->
                	<span class="hot-bankuai">热门板块:</span>
                    <ul class="hotplace-pic">
                    
                    	
                       <%--  <c:forEach items="${sessionScope.luntan}" var="item">
                             <c:if test="${item.rownum < 9}">
                             	<li class="hotlist">
                        			<a href="" class="hotlist-con">
                            			<img class="hotlist-pic" src="${item.ltyl2 }" />
                                		<span class="hotlist-text">${item.ltname }</span>
                            		</a>
                       			 </li>
                            </c:if>
                       </c:forEach> --%>
                        
                    </ul>
                </div>
                <div id="invitation-content-b" class="invitation-content-b"><!--帖子内容-->
                	<div class="invitation-content-b-con">  
                    	<div class="invitation-content-b-nav"> 
                        	<a href="" class="b-nav-list current">推荐</a>
                            <a href="" class="b-nav-list">最新主题</a>
                            <a href=" forum/publishedtopics.jsp <%-- ltid=${sessionScope.ltid } --%>" class="btn b-nav-btn">发表新主题</a>
						<!-- tieZis_publishTop.action?ltid=${item.ltid }&yhid=1001 -->
						</div>
                        <ul>
                        	<c:forEach items="${sessionScope.TieZiBean}" var="item"> 
								<c:if test="${item.status eq 1}">
						    		<li id="1001">
		                            	<div class="theme_list_img">
		                                	<a><img src="../upload/${item.yhphoto }"/></a>
		                                </div>
		                                <div class="theme_list_con">
		                                	<div class="title">
		                                    	<a target="_blank" class="title_name" style="color:#488eee" href="huities_findHuitie.action?tid=${item.tid }&ltid=${sessionScope.ltid}" onclick="showHuiTie(${item.tid })">${item.tzname }</a>
		                                        <img class="stamp" src="forum/images/Hd_Community_Forum_img/jin01.png"/>
		                                        <img class="stamp" src="forum/images/Hd_Community_Forum_img/tujian01.png"/>
		                                    </div>
		                                    <div class="auth_msg clearfix">
		                                    	<a href="" class="user_name">${item.yhzsname}</a><!--昵称-->
		                                        <i class="vip_icon"><img src="forum/images/Hd_Community_Forum_img/vip_jpg.jpg" /></i> <!--等级-->
		                                        <span class="time txt">${item.tztime }</span>
		                                        <span class="stick txt">置顶</span>
		                                        <span class="numb view"><!--观看量-->
		                                        	<i><img  src="forum/images/Hd_Community_Forum_img/view.png"/></i>
		                                            <span>${item.tzclick }</span>
		                                        </span>
		                                        <span class="numb msg"><!--回复量-->   
		                                        	<i><img src="forum/images/Hd_Community_Forum_img/msg.png" /></i>
		                                            <span>${item.htcount }</span>
		                                        </span>            
		                                    </div>
		                                </div>
		                            </li>
								</c:if>
							</c:forEach>
							<div>
							<form action="tieZis_findbyLtids.action?ltid=${sessionScope.ltid }" method="post" >
								<strong> 
									<span  style="margin-left: 20%; margin-top: 10px;"><a href="javascript:pageLinkClick(1,${sessionScope.ltid })">[首页]</a> </span>
									<span><a href="javascript:pageLinkClick(${sessionScope.pageNo-1 },${sessionScope.ltid })">[上一页]</a> </span>
									<c:forEach items="${sessionScope.list}" var="item">
										${item}
									</c:forEach>
									
									<span><a href="javascript:pageLinkClick(${sessionScope.pageNo+1 },${sessionScope.ltid })">[下一页]</a> </span>
									<span><a href="javascript:pageLinkClick(${sessionScope.pageSize },${sessionScope.ltid })">[尾页]</a> </span>
									<span style="margin-left: 70%">共${sessionScope.countbyids.count}条记录&nbsp;|&nbsp;${sessionScope.pageNo }/${sessionScope.pageSize }页&nbsp;|&nbsp;每页12条</span>
								</strong> 
							
							
							</form>
							</div>
						</ul>
						
                    </div> 
                </div>
            </div>
            <div id="invitation-choiceness-content" class="contain_left fr" ><!--右边精选内容-->
            	<div class="left_wrap"> <!--推荐的-->
               		<div class="sidebarplate">
                    	<div class="sidebarplate_tab">
                        	<div class="tab">
                            	<span class="on">板块推荐</span>
                                <span>百宝箱</span>	
                             </div>
                             <div>
                             	<ul>
                             		<c:forEach items="${sessionScope.luntan}" var="item">
                             			<c:if test="${item.rownum < 9}">
                             				<li><a href="tieZis_findbyLtids.action?ltid=${item.ltid }">${item.ltname}</a></li>
                             			</c:if>
                             		</c:forEach>
                                </ul>
                             </div>
                        </div>
                    </div>
                </div>
                <div class="mibbs_sign" id="mibbs_sign"><!--签到-->
                	<div>
                    	<div></div>
                    </div>
                </div>
                <div class="right-warp"><!--论坛公告-->
                	<div class="con">
                    	<div class="forum_notice"><!--公告-->
                        	<p>论坛公告</p>	
                        </div>
                        	<c:forEach items="${sessionScope.GongGaoBean}" var="item">
                        		<c:if test="${item.rownum<4 }">
                        			 <a><span class="forum_notice_text">${item.gtext }</span> </a>
                        			 <span class="text_tuijian">[${item.gtime}]</span>
                        		</c:if>
                        	</c:forEach>
                        <div>  
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    
    <%@ include file="../home/footer.jsp" %>
</body>
</html>

