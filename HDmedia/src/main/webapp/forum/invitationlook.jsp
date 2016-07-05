<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<base href="/HDmedia/"/>
<meta charset="utf-8">
<title>查看帖子</title>
<meta name="viewport" content="width=device-width, initial-scale=1"/> <!--默认为屏幕的宽度-->
<link rel="short icon" href="images/hd_01.png"/>  <!--小图标，需要浏览器兼-->
<link rel="stylesheet" href="home/css/Hd_nav.css" />    <!--导航栏css-->
<link rel="stylesheet" href="home/css/footer.css" />    <!--尾部-->
<link rel="stylesheet" href="forum/css/invitation_look.css" /> <!--帖子的主要样式-->


 <!--编译器 -->
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="ueditor/ueditor.all.min.js"></script>
<script type="text/javascript" src="ueditor/lang/zh-cn/zh-cn.js"></script>
</head>
<script type="text/javascript">
	function pageLinkClick(pageNo,tid){
		document.forms[0].action ='http://localhost:8080/HDmedia/huities_findHuitie.action?pageNo='+pageNo+'&tid='+tid;
		
		document.forms[0].submit();
	}
	
	function show(){
		$('#text').css('display','block');
	}
	var uelcw1=UE.getEditor("editor");
	function dianzan(htid){
		$.post("huities_dianzan.action?htid="+htid, function(data) {
			/* alert(data.htdianzan);
			alert($(this).html()); */
			$("."+htid).html("点赞&nbsp;&nbsp;"+data.htdianzan);
			//$(this).html("点赞&nbsp;&nbsp;"+data.htdianzan);
		}, 'json');
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
            <div class="invitation_look-content"><!--左边发的帖子-->
            	<div class="invitation_look-content-a"><!--热门板块-->
                	<a href="" class="platename">名族文化</a>
                    <span class="attention">+关注</span> &nbsp; &nbsp;已有
                    <span class="attention_numb">1552人关注</span>
                    <a href="" class="btn sendtheme">+发表新主贴</a>
                </div>
                <div class="invitation_look-content-b"><!--帖子内容-->
                	<div class="filtrate invitation">  
                    	<div class="invitation_con"> <!--楼主提到的事情-->
                        	<div class="hot_invitation_img"> <!--如果是热帖显示图片-->
                            	<img src="forum/images/hot_invitation.png"/>
                            </div>
							<h1>
								<!--主题题目-->
								<span class="top_name">${sessionScope.TZ.tzname }</span>
							</h1>
							<p class="txt">
								<!--下方显示的文字-->
								<span class="marktxt">置顶</span> <span class="marktxt">精华</span>
								<span>发表时间:${sessionScope.TZ.tztime }</span> &nbsp;<span>发表作者:</span>
								<span class="comefrom_auth">${sessionScope.TZ.yhzsname }</span>
								<span class="numb view"> </span> <span class="numb msg">
									<!--回复量--> <i><img
										src="forum/images/Hd_Community_Forum_img/msg.png" /></i> <span>回复条数：${sessionScope.huitiecount.htcount}</span>
								</span>
							</p>
							<div class="invitation_content  ">

								<p style="text-align: center;">帖子标题:${sessionScope.TZ.tzzy }</p>

								<p class="p_img">
									<c:forEach items="${sessionScope.tzphoto}" var="item">
										<img src="../upload/${item}" />
									</c:forEach>
								</p>
								<br />

								<p>帖子内容:${sessionScope.TZ.tztext}</p>
							</div>
						</div>
                    </div>
                    <div class="reply"><!--回复-->
                    	<div class="reply_con"> <!--回复的内容-->
                        	<div class="reply_title">
                            	<span class="reply_nav">
                                	<a href="" class="currtent">最新评论</a>
                                    <span> | </span>
                                    <a href="">最热评论</a>
                                </span>
                                <div class="lookandcollect">
                                	<a href="">只看楼主</a>
                                    <span> | </span>
                                    <a href="">收藏</a>
                                </div>
                            </div>
                            
                            
                            
                            
                            <ul class="reply_list">
                            
                            <c:forEach items="${sessionScope.HuitieBean}" var="item">
                            	<li class="clearfix">
                                	<div class="reply_list_img">
                                    	<a><img src="../upload/${item.yhphoto}"/></a>	
                                    </div>
                                    <div class="reply_list_con">
                                    	<div class="auth_msg clearfix">
                                        	<a href="" class="auth_name">${item.yhzsname }</a>
                                            <i class="vip_icon 	vip_2"><img src="Hd_Community_Forum_img/vip_jpg.jpg" /></i>
                                            <span class="at span">发表于</span>
                                            <span class="time span">${item.httime }</span>
											<span class="reply_list_float shafa"><a id="dianzan" href="javascript:void(0)" class="${item.htid}" onclick="dianzan(${item.htid})">点赞&nbsp;&nbsp;${item.htdianzan }</a></span>
                                        </div>
                                        <div class="replay_txt"> <!--回复的话-->
                                        	${item.httext }
                                        </div>
                                        <p class="reply_bu">
                                        	<a class="reply_btn">回复</a>
                                            <span>举报</span>
                                       	</p>
                                    </div>
                                </li>
                            </c:forEach>
                            	
                            </ul>
                        </div>
                        <div class="base_widget_paging"><!--回复的页数-->
                        	<form action="huities_findHuitie.action?tid=${sessionScope.tid }" method="post">
	
								<strong> 
								<span style="margin-left: 20%; margin-top: 10px;"><a href="javascript:pageLinkClick(1,${sessionScope.tid })">[首页]</a> </span> 
								<span><a href="javascript:pageLinkClick(${sessionScope.pageNo-1 },${sessionScope.tid })">[上一页]</a></span> 
								<c:forEach items="${sessionScope.list}" var="item">
											${item}
								</c:forEach> 
								<span><a href="javascript:pageLinkClick(${sessionScope.pageNo+1 },${sessionScope.tid })">[下一页]</a></span> 
								<span><a href="javascript:pageLinkClick(${sessionScope.pageSize },${sessionScope.tid })">[尾页]</a></span> 
								<span style="margin-left: 70%">共${sessionScope.huitiecount.htcount}条记录&nbsp;|&nbsp;${sessionScope.pageNo }/${sessionScope.pageSize }页&nbsp;|&nbsp;每页8条</span>
								</strong>
							</form>
                        </div>
                        <div class="pl" style="margin-top: 1%; padding: 0px; width: 100%; float: left;">
							<form action="huities_add.action?tid=${sessionScope.tid }" method="post">
								<div id="text">
									<script id="editor" name="httext" type="text/plain"style="width: 90%; height: 200px;"></script>
									<input type="submit" style="float: right; height: 40px; width: 9%; margin-top: -40px;" value="评     论">
								</div>
							</form>
						</div>
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
