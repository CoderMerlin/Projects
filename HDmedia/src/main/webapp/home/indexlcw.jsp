<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>书画风采</title>
<meta name="viewport" content="width=device-width, initial-scale=1"/> <!--默认为屏幕的宽度-->
<link rel="stylesheet" href="css/footer.css" />
<link rel="stylesheet" href="css/Hd_nav.css" />
<link rel="stylesheet" href="css/1.css"/>
<link rel="stylesheet" href="css/test.css"/>
<link rel="stylesheet" href="css/index.css"/>
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="js/ajaxfileupload.js"></script>
<script src="js/jquery-1.11.3.js"></script>
<script src="js/1.js" type="text/jscript"></script>
<script src="js/jq.js" type="text/jscript"></script>

</head>
<script>
	var count=1;
	var myTime;
	function show1(){
			for(var i=1;i<=document.getElementsByClassName('img_2').length;i++){
				if(count==i){
					document.getElementById(i).style.display='block';
					document.getElementById('kg'+i).style.background='red';
				}else{
					document.getElementById(i).style.display='none';
					document.getElementById('kg'+i).style.background='#999';	
				}
			}
			count++;
			if(count>10){
				count=1;
			}
			myTime=window.setTimeout('show1()',1000);
	}
	window.onload=function(){
	show1();
	var sp =document.getElementsByClassName('no');	
	for(var i=0;i<sp.length;i++){
		sp[i].onmouseover=function(){
			//清除定时器
			window.clearTimeout(myTime);
			var  count1=this.title; //特别注意，此时span中不能嵌套其他标
				for(var i=1;i<=document.getElementsByClassName('img_2').length;i++){
					if(count1==i){
							document.getElementById(i).style.display='block';
							document.getElementById('kg'+i).style.background='red';
						}else{
							document.getElementById(i).style.display='none';
							document.getElementById('kg'+i).style.background='#999';
						}
					}
					count1++;
					if(count1>10){
						count1=1;
					}
			}
			//给每个span标签添加鼠标移除事件
			sp[i].onmouseout=function(){
				myTime=window.setTimeout('show1()',2000);
			}
		}
	}
</script>

<body>
	<%@ include file="header.jsp" %>
    <div style="margin: 0; padding:0px; height:10px; width:90%; margin-top:10px; margin-bottom:10px; margin-left:5%; float:left;">
    	<img  src="../images/9.jpg" style="margin: 0; padding:0px; width:100%; height:10%; float:left; display:inline;">
    </div><!--横线-->
    <div id="wz" style="margin: 0; padding:0px; height:40px; background:#CCC; width:90%; margin-top:-10px; margin-left:5%; float:left;">
    		<span style="line-height:40px;">当前位置:书画风采</span>
    </div>
    <div id="dh" style=" margin-left:5%; padding:0px; width:90%; height:40px; background-color:#CCC;float:left; position:relative; opacity:0.3">
        <ul>
            <li id="one_1">书画</li>
            <c:forEach items="${sessionScope.navname}" var="item"> 
            	<c:if test="${item.nav1name eq '书画风采'}">
            		<c:forEach items="${nav2name}" var="item1">
	                	<c:if test="${item.nav1name eq item1.nav1name}">
			                <li class="ys"><a href="#sh${item1.rownum}">${item1.nav2name}</a></li>
	                    </c:if>
	                </c:forEach>
            	</c:if>
            </c:forEach>
        </ul>		
    </div>
    <div id="top10" style=" margin-left:5%; padding:0px; width:90%; height:400px; float:left; margin-top:-40px;">
    <div class="imgs">
    	<c:forEach items="${ sessionScope.Work_lb }" var="item">
    		<c:if test="${ item.rownum <11}">
				<li id="${item.rownum}" class="img_2" style="display:none"><a target="_blank" href="../worksServlet?op=showWorksByid&worksid=${item.works_id }"><img src="../${item.works_img }" /></a>
					<p>
						<a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${item.author_name}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;${item.works_name}</a>
					</p>
				</li>
			</c:if>
    	</c:forEach>
    </div>
    </div><!--图片轮播-->
    
    <div id="hd">
	<ul>
		<li id="kg1" title="1" class="no">&nbsp;</li>
		<li id="kg2" title="2" class="no">&nbsp;</li>
		<li id="kg3" title="3" class="no">&nbsp;</li>
		<li id="kg4" title="4" class="no">&nbsp;</li>
		<li id="kg5" title="5" class="no">&nbsp;</li>
        <li id="kg6" title="6" class="no">&nbsp;</li>
        <li id="kg7" title="7" class="no">&nbsp;</li>
        <li id="kg8" title="8" class="no">&nbsp;</li>
        <li id="kg9" title="9" class="no">&nbsp;</li>
        <li id="kg10" title="10" class="no">&nbsp;</li>
	</ul>
 	</div>
 	
    <%@ include file="center.jsp" %>
    
  
    <div style="margin: 0; padding:0px; height:10px; width:90%; margin-top:50px; margin-left:5%; float:left;">
    	<img  src="../images/9.jpg" style="margin: 0; padding:0px; width:100%; height:10%; float:left; display:inline;">
    </div><!--横线-->

    <div id="elect_title" style=" margin: 0 5%; padding:0px; width:90%; height:50px; float:left; ">
    	<h2>热门作品</h2> 	
    </div>
    <div id="content" style=" margin: 0 5%; padding:0px; width:90%; height:650px; float:left;border:#999 solid 1px; margin-top:5px;">
		<c:forEach items="${sessionScope.works}" var="item">
			<c:if test="${item.rownum <=6}">
				<div class="content_1" onmouseover="this.style.border='1px solid red'" onmouseout="this.style.border='1px solid #CCC'">
					<div class="content_4">
						<p>
							<a title="${ item.works_name }" target="_blank" href="../worksServlet?op=showWorksByid&worksid=${item.works_id }"> 
							<img border="0" src="../${ item.works_img }"> </a>
						</p>
					</div>
					<div class="content_3">
						<h3>
							<a title="${ item.works_name }" target="_blank" href="../worksServlet?op=showWorksByid&worksid=${item.works_id }">${ item.works_name }</a>
						</h3>
						<p>
							${ item.works_details }
							<a target="_blank" href="../worksServlet?op=showWorksByid&worksid=${item.works_id }">[详情]</a>
						</p>
						<span>${item.works_time}</span>
					</div>
				</div>
			</c:if>
		</c:forEach>   
    </div><!--书画风采所分的内容-->
    <div style="margin: 0; padding:0px; height:10px; width:90%; margin-top:50px; margin-left:5%; float:left;">
    	<img  src="../images/9.jpg" style="margin: 0; padding:0px; width:100%; height:10%; float:left; display:inline;">
    </div><!--横线-->
	


	<div id="elect_title" style=" margin: 0 5%; padding:0px; width:90%; height:50px; float:left; ">
    	<h2 id="sh11">古代书法</h2> 	
    </div>
    <div id="gd_sf" style=" margin: 0 5%; padding:0px; width:90%; height:510px; float:left; border:#999 solid 1px; margin-top:5px;">

		<c:forEach items="${sessionScope.Work_gdsf}" var="item">
			 <c:if test="${item.rownum <=8}">
				<div class="sf_text_1">
					<div class="sf_2" id="a1">
						<a target="_blank" href="../worksServlet?op=showWorksByid&worksid=${item.works_id }"><img src="../${ item.works_img }" /></a>
					</div>
					<p><a target="_blank" href="../worksServlet?op=showWorksByid&worksid=${item.works_id }">${ item.works_name }</a></p>
				</div>
			 </c:if>
		</c:forEach>

    </div>
 
    <div style="margin: 0; padding:0px; height:10px; width:90%; margin-top:50px; margin-left:5%; float:left;">
    	<img  src="../images/9.jpg" style="margin: 0; padding:0px; width:100%; height:10%; float:left; display:inline;">
    </div><!--横线-->
    <div id="elect_title" style=" margin: 0 5%; padding:0px; width:90%; height:50px; float:left; margin-top:5px;">
    	<h2 id="sh3">古代绘画</h2> 	
    </div>
    <div id="gd_hh" style=" margin: 0 5%; padding:0px; width:90%; height:450px; float:left; border:#999 solid 1px; margin-top:5px;">  
        <div id="gd_hh_left" style="width:33%; height:450px; float:left">
			<c:forEach items="${sessionScope.Work_gdhh}" var="item">
				<c:if test="${item.rownum>1 and item.rownum<6}">
					<div class="gd_hh_text_1">
	        			<a target="_blank" href="../worksServlet?op=showWorksByid&worksid=${item.works_id }"><img src="../${ item.works_img }"/></a>
		                <div class="gd_1">
		                	<h4><a target="_blank" href="../worksServlet?op=showWorksByid&worksid=${item.works_id }">${ item.works_name}</a></h4>
		                </div>
            		</div>
				</c:if>
			</c:forEach>
        </div>
        <div id="gd_hh_centent" style="width:33%; height:450px; float:left">
			<c:forEach items="${sessionScope.Work_gdhh}" var="item">
				<c:if test="${item.rownum<2}">
					<div class="gd_hh_text_2">
	        			<a target="_blank" href="../worksServlet?op=showWorksByid&worksid=${item.works_id }"><img src="../${ item.works_img }"/></a>
		                <div class="gd_2">
		                	<h1><a target="_blank" href="../worksServlet?op=showWorksByid&worksid=${item.works_id }">${ item.works_name}</a></h1>
		                </div>
            		</div>
				</c:if>
			</c:forEach>
		</div>
        <div id="gd_hh_right" style="width:33%; height:450px; float:left">
        	<c:forEach items="${sessionScope.Work_gdhh}" var="item">
				<c:if test="${item.rownum>5 and item.rownum<10}">
					<div class="gd_hh_text_1">
	        			<a target="_blank" href="../worksServlet?op=showWorksByid&worksid=${item.works_id }"><img src="../${ item.works_img }"/></a>
		                <div class="gd_1">
		                	<h4><a target="_blank" href="../worksServlet?op=showWorksByid&worksid=${item.works_id }">${ item.works_name}</a></h4>
		                </div>
            		</div>
				</c:if>
			</c:forEach>
        </div>
    </div>
     <div style="margin: 0; padding:0px; height:10px; width:90%; margin-top:50px; margin-left:5%; float:left;">
    	<img  src="../images/9.jpg" style="margin: 0; padding:0px; width:100%; height:10%; float:left; display:inline;">
    </div><!--横线-->
    <div id="elect_title" style=" margin: 0 5%; padding:0px; width:90%; height:50px; float:left; margin-top:5px;">
    	<h2 id="sh13">近现代书画</h2> 	
    </div>
    
    <div id="jxd_sh" style=" margin: 0 5%; padding:0px; width:90%; height:450px; float:left; background:'#CCC'; border:#999 solid 1px; margin-top:5px;">
        <div id="jxd_sh_left" style="width:33%; height:450px; float:left">
        	<c:forEach items="${sessionScope.Work_jxdsh}" var="item">
				<c:if test="${item.rownum <5}">
					<div class="gd_hh_text_1">
	        			<a target="_blank" href="../worksServlet?op=showWorksByid&worksid=${item.works_id }"><img src="../${ item.works_img }"/></a>
		                <div class="gd_1">
		                	<h4><a target="_blank" href="../worksServlet?op=showWorksByid&worksid=${item.works_id }">${ item.works_name}</a></h4>
		                </div>
            		</div>
				</c:if>
			</c:forEach>
        </div>
        <div id="jxd_sh_centent" style="width:33%; height:450px; float:left">
        	<div class="jxd_sh_text_2">
				<c:forEach items="${authorjxdsh_left}" var="item">
					<c:if test="${item.status eq '1'}">
						<div class="jxd_1">
							<p>${ item.author_name }</p>
							<p>${ item.author_details }</p>
						</div>
					</c:if>
				</c:forEach>
            </div>
        </div>
        <div id="jxd_sh_right" style="width:33%; height:450px; float:left">
			<c:forEach items="${sessionScope.Work_jxdsh}" var="item">
				<c:if test="${item.rownum >4 and item.rownum<9}">
					<div class="gd_hh_text_1">
						<a target="_blank" href="../worksServlet?op=showWorksByid&worksid=${item.works_id }"><img src="../${ item.works_img }" /></a>
						<div class="gd_1">
							<h4><a target="_blank" href="../worksServlet?op=showWorksByid&worksid=${item.works_id }">${ item.works_name}</a></h4>
						</div>
					</div>
				</c:if>
			</c:forEach>
		</div>
    </div>
    
    
    
    
    
    <div style="margin: 0; padding:0px; height:10px; width:90%; margin-top:50px; margin-left:5%; float:left;">
    	<img  src="../images/9.jpg" style="margin: 0; padding:0px; width:100%; height:10%; float:left; display:inline;">
    </div><!--横线-->
    <div id="elect_title" style=" margin: 0 5%; padding:0px; width:90%; height:50px; float:left; margin-top:5px;">
    	<h2 id="sh4">现代书画</h2> 	
    </div>
    <div id="dd_hh" style=" margin: 0 5%; padding:0px; width:90%; height:450px; float:left; border:#999 solid 1px; margin-top:5px;">
        <div id="dd_hh_left" style="width:33%; height:450px; float:left">
        	<c:forEach items="${sessionScope.Work_xdsh}" var="item">
				<c:if test="${item.rownum <2}">
					<div class="dd_hh_text_2">
						<a target="_blank" href="../worksServlet?op=showWorksByid&worksid=${item.works_id }"><img src="../${ item.works_img }" /></a>
						<div class="gd_2">
							<h1><a target="_blank" href="../worksServlet?op=showWorksByid&worksid=${item.works_id }">${ item.works_name}</a></h1>
						</div>
					</div>
				</c:if>
			</c:forEach>
        </div>
        <div id="dd_hh_right" style="width:67%; height:450px; float:left">
        	<c:forEach items="${sessionScope.Work_xdsh}" var="item">
				<c:if test="${item.rownum >1 and item.rownum <10}">
					<div class="dd_hh_text_1">
						<a target="_blank" href="../worksServlet?op=showWorksByid&worksid=${item.works_id }"><img src="../${ item.works_img }" /></a>
						<div class="gd_1">
							<h4><a target="_blank" href="../worksServlet?op=showWorksByid&worksid=${item.works_id }">${ item.works_name}</a></h4>
						</div>
					</div>
				</c:if>
			</c:forEach>
         </div>
    </div>
    <div style="margin: 0; padding:0px; height:10px; width:90%; margin-top:50px; margin-left:5%; float:left;">
    	<img  src="../images/9.jpg" style="margin: 0; padding:0px; width:100%; height:10%; float:left; display:inline;">
    </div><!--横线-->
    <div id="elect_title" style=" margin: 0 5%; padding:0px; width:90%; height:50px; float:left; margin-top:5px;">
    	<h2 id="sh7">油画雕塑</h2> 	
    </div>
    <div id="dd_hh" style=" margin: 0 5%; padding:0px; width:90%; height:450px; float:left; border:#999 solid 1px; margin-top:5px;">
        <c:forEach items="${sessionScope.Work_yhds}" var="item">
			<c:if test="${item.rownum <11}">
				<div class="yh_hh_text_1">
					<a target="_blank" href="../worksServlet?op=showWorksByid&worksid=${item.works_id }"><img src="../${ item.works_img }" /></a>
					<div class="gd_1">
						<h4><a target="_blank" href="../worksServlet?op=showWorksByid&worksid=${item.works_id }">${ item.works_name}</a></h4>
					</div>
				</div>
			</c:if>
		</c:forEach>
    </div>
	<%@ include file="footer.jsp" %>
</body>
</html>

