<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<!doctype html>
<html>
<head>
<title>弘道传媒</title>
<base href="/HDmedia/">
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1"/> <!--默认为屏幕的宽度-->
<link rel="shortcut icon" type="image/x-icon" href="../images/hd_01.png"> <!--小图标，需要浏览器兼容-->

<link rel="stylesheet" href="home/css/footer.css" />
<link rel="stylesheet" href="home/css/Hd_nav.css" />
<link rel="stylesheet" href="css/mainPic.css" />

<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="home/js/mainPic.js"></script>
</head>

<body>
	<%@ include file="header.jsp" %>
       <div class="box1">
		<div class="banana_rotate"><!--放图片的块-->
			<div class="roll_img" id="roll_img_cc"> <!--主要滚动的图片-->
				<div id="portal_block_500" class="inrotate_img block move-span">
					<div id="portal_block_500_content" class="dxb_bc">
						<div id="div_roll_img_cc" class="roll_img_cc" style="width: 90%; position: absolute; left: 0px;">
							<ul>
							</ul>
						</div>
					</div>
				</div>
				<div class="leftbtn_picturn"></div>
				<div class="rightbtn_picturn"></div>
				<div class="cr"></div>
			</div>
			<div class="roll_opacity" style="display: none;"></div>
			<div class="roll" style="display: none;">
				<div class="roll_title">
					<a href="#"></a>
				</div>
				<div class="roll_small_signimg_div">
				</div>
			</div>
		</div>
	  </div>
    </div>
   <div class="hm_tail" style="padding:8px;"><!--尾部-->
   <div class="hd_culture_info"> <!-- 文化资讯-->
    	<div class="culture_info_container">
        	<p>文化资讯</p>
            <div class="figure-info">
            	<ul>
            		<c:forEach items="${zixun }" var="item" varStatus="i">
            		<li class="cul_info_four">
           				<div>
           					<img src="../${item.works_img }" alt="图片加载失败" class="info-img"/>
           				</div>
           				<div class="info_introduce"> <!--信息介绍-->
           					<h4>${item.author_name}</h4>
           					<c:choose>
           						<c:when test="${fn:length(item.works_details) > 80}">
           							<p>${fn:substring(item.works_details,0,150) }...</p>
           						</c:when>
           						<c:otherwise>
           							<p>${item.works_details }</p>
           						</c:otherwise>
           					</c:choose>
           					<button type="button" class="btn re-clr1"><a>详细介绍</a></button>
           				</div>
            		</c:forEach>
            		</li>
                </ul>
            </div>
        </div>
     </div> 
    <%@ include file="footer.jsp" %>
    
</body>

</html>