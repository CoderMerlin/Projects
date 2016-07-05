<%@ page contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<base href="/HDmedia/">
<title>中华文化详情</title>
<meta name="viewport" content="width=device-width, initial-scale=1"/> <!--默认为屏幕的宽度-->
<link rel="short icon" href="Hd_index_img/hd_01.png"/>  <!--小图标，需要浏览器兼-->
<!--css-->
<link rel="stylesheet" href="home/css/footer.css" />
<link rel="stylesheet" href="home/css/Hd_nav.css" />
<link rel="stylesheet" href="home/css/hd_Ancient_poetry.css" /> <!--中华文化古诗词css-->
<link rel="stylesheet" href="home/css/style10.css" />
<link rel="stylesheet" href="home/css/literdetail.css" />
<!--Javascript-->
<script type="text/javascript"  src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript"  src="home/js/Chinese_Literature_js.js"></script>
</head>
<body>
	<%@ include file="header.jsp" %>
    <div id="hd_Ancient_poetry_title" class="hd_Ancient_poetry_title" name="hd_Ancient_poetry_title"> <!--中华古诗词标题-->
    	<p>中华文化 -> 文化详情</p>
        <img src="home/images/Chinese_Literature_underline.jpg" style=" padding:0; margin:0;width:100%; height:10%; float:left; display:inline;" />
    </div>
    <div id="liter_content">
    	<div id="liter_all_detail">
    		<div id="yuanwen_content">
    			<space id="ss">${sessionScope.work.works_name }</space>
    			<p>&nbsp;&nbsp; &nbsp;&nbsp;  ${sessionScope.work.works_details }</p>
    			<div id="work_detail">
	    			<ul>
	    				<li><space>作品名称</space>&nbsp;&nbsp;&nbsp;&nbsp;    ${sessionScope.work.works_name }</li>
	    				<li><space>作者 </space>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;          ${sessionScope.work.author_name }</li>
    					<li><space>文学载体</space>&nbsp;&nbsp;&nbsp;&nbsp;   文言文</li>
	    				<li><space>发生时间</space>&nbsp;&nbsp;&nbsp;&nbsp;   ${sessionScope.work.works_time }</li>
	    			</ul>
    			</div>
    		</div>
    		<div id="pic_detail">
    			<c:if test="${not empty sessionScope.work.works_img}">
    				<img src="../upload/${sessionScope.work.works_img }">
    			</c:if>
    			<c:if test="${ empty sessionScope.work.works_img}">
    				<img src="images/zw.jpg">
    			</c:if>
    		</div>
    		
    	</div>
    </div>
	<%@ include file="footer.jsp" %>
</body>
</html>
