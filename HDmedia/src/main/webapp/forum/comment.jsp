<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<base href="/HDmedia/"/>
<meta charset="utf-8">
<title>游客评论</title>
<meta name="viewport" content="width=device-width, initial-scale=1"/> <!--默认为屏幕的宽度-->
<link rel="short icon" href="images/hd_01.png"/>  <!--小图标，需要浏览器兼-->
<link rel="stylesheet" href="forum/css/Hd_nav.css" />    <!--导航栏css-->
<link rel="stylesheet" href="forum/css/footer.css" />    <!--尾部-->
<link rel="stylesheet" type="text/css" href="forum/css/invitation_look.css"/>
 <!--编译器 -->
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="forum/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="forum/ueditor/ueditor.all.min.js"></script>
<script type="text/javascript" src="forum/ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript">
	function pageLinkClick(pageNo,tid){
		document.forms[0].action ='http://localhost:8080/HDmedia/huities_findHuitie.action?pageNo='+pageNo+'&tid='+tid;
		
		document.forms[0].submit();
	}
	
	function show(){
		$('#text').css('display','block');
	}
</script>
</head>


<body>
	<%@ include file="../home/header.jsp" %>
    <div id="path" style=" margin-left:5%; padding:0px; width:90%; height:50px; background-color:#CCC;float:left;">  
    	<ul>
            <li>评论列表（网友评论仅供网友表达个人看法，并不表明本站同意其观点或证实其描述）</li>	
        </ul>
    </div>
    <div style="margin: 0; padding:0px; height:10px; width:90%; margin-top:10px; margin-left:5%; float:left;">
    	<img  src="forum/images/9.jpg" style="margin: 0; padding:0px; width:100%; height:10%; float:left; display:inline;">
    </div><!--横线-->

	<div id="comment" style="margin-left: 5%; padding: 0px; width: 90%; float: left;">
		<div class="filtrate invitation">
			<div class="invitation_con">
				<!--楼主提到的事情-->
				<div class="hot_invitation_img">
					<!--如果是热帖显示图片-->
					<img src="forum/images/Hd_Community_Forum_img/hot_invitation.png" />
				</div>

				<h1>
					<!--主题题目-->
					<span class="top_name">${item.tzname}</span>
				</h1>
				<p class="txt">
					<!--下方显示的文字-->
					<span class="marktxt">置顶</span> <span class="marktxt">精华</span> <span>发表时间:${sessionScope.TZ.tztime }</span>
					&nbsp;<span>发表作者:</span> <span class="comefrom_auth">${sessionScope.TZ.yhzsname }</span> <span class="numb view"> </span>
					<span class="numb msg"> <!--回复量--> <i><img
							src="forum/images/Hd_Community_Forum_img/msg.png" /></i> <span>回复条数：${sessionScope.huitiecount.htcount}</span>
					</span>
				</p>

				<div class="invitation_content  ">
					
					<p style="text-align: center;">帖子标题:${sessionScope.TZ.tzname }</p>
					 
					<p class="p_img">
						<c:forEach items="${sessionScope.tzphoto}" var="item">
							<img src="upload/${item}" />
						</c:forEach>
					</p>
					<br /> 
					
					<p>帖子内容:${sessionScope.TZ.tztext}</p>
				</div>
				
			</div>
		</div>
		<div class="pl" style="margin-top: 1%; padding: 0px; width: 100%; float: left;">
			<form action="" method="post">
				<h3 onclick="show()" style="width: 70%;">点击回复<img src="forum/images/Hd_Community_Forum_img/msg.png" /></h3>
				<div id="text" style="display: none;">
					<script id="editor" type="text/plain"style="width: 90%; height: 200px;"></script>
					<input type="submit" style="float: right; height: 40px; width: 100px; margin-top: -40px;" value="评     论">
				</div>
			</form>
		</div>

		<c:forEach items="${sessionScope.HuitieBean}" var="item">
			<div class="text" id="title" style="margin-left: 5%; padding: 0px; width: 90%; float: left; max-height: 1000px;">
				<div style="margin-top: 1%;">
				<h3 style="width:88%; float: left;">${item.yhzsname }说：<a>${item.httext }</a></h3>
				<span id="goodfb126" style="width:12%; float: left;"> <a onclick="postBadGood('goodfb',126)" href="#">推荐</a>(${item.htdianzan }) &nbsp;&nbsp;<a onclick="postBadGood('goodfb',126)" href="#">回复<img src="forum/images/Hd_Community_Forum_img/msg.png" /></a></span>
				</div>
				<h5 style="width:100%; float: left;">${item.httime }</h5>
			</div>
		</c:forEach>
		
		<div class="fy" style="margin-left: 5%; margin-top:1%; padding: 0px; width: 90%; float: left;">
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
		
	</div>
	
	
	<div style="margin: 0; padding:0px; height:10px; width:90%; margin-left:5%; float:left;">
    	<img  src="forum/images/9.jpg" style="margin: 0; padding:0px; width:100%; height:10%; float:left; display:inline;">
    </div><!--横线-->
    
	<%@ include file="../home/footer.jsp" %>
</body>
<script type="text/javascript">
var uelcw1=UE.getEditor("editor");
</script>
</html>

