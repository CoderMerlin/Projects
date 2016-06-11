<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript" src="../js/jquery-1.11.3.min.js"></script>
<script>
 function shownew(d){
	if(d==1005){
		location.href="works_find.action";
	}else if(d==1007){
		location.href="lunTan_find.action";
	}else if(d==1003){
		location.href="new.jsp";
	}else if(d==1002){
		location.href="diyu.jsp";
	}else if(d==1004){
		location.href="new.jsp";
	}
}
function shownews(d){
	if(d==1050){
		location.href="goudetsf.jsp";
	}else if(d==1052){
		location.href="jxdsh.jsp";
	}else if(d==1053){
		location.href="xdsh.jsp";
	}else if(d==1054){
		location.href="yhds.jsp";
	}else if(d==1051){
		location.href="goudethh.jsp";
	}else if(d==1042){
		location.href="mingzu.jsp";
	}else if(d==1043){
		location.href="diyu.jsp";
	}
} 

</script>

<div id="hd_nav" class="hd_nav">
	<!--弘道菜单导航-->
	<div id="hd_title" class="hd_title">
		<img src="images/1.png" />
		<p>弘扬中华文化 传颂道德品行</p>
	</div>
	<div id="hd_nav_menu" class="hd_nav_menu">
		
		 <ul  id="hd_menu" class="hd_menu_name">	  	
                <li class="hd_home_page">
                    <a href="index.jsp">首页</a>
                </li>
                <span class="sep">|</span>
                <c:forEach items="${sessionScope.navname}" var="item"> 
                	
                	<c:if test="${item.nav1status eq '1'}">
                		<li><a href="javascript:shownew(${item.nav1id })">${item.nav1name}</a>
                			<ul id="xiala">
                				<c:forEach items="${navTow}" var="item1">
	                				<c:if test="${item.nav1name eq item1.nav1name}"> 				
			                        	<li><a href="javascript:shownews(${item1.nav2id })">${item1.nav2name}</a></li>
	                    			</c:if>
	                    		</c:forEach>
	                    	</ul>
                		</li>
                		<span class="sep">|</span>
                	</c:if>	
                </c:forEach>			
                <li>
                    <a href="../login.jsp">登录</a>
                </li>			
        </ul>
        <!-- <ul id="hd_menu" class="hd_menu_name">
			<li class="hd_home_page"><a href="index.jsp">首页</a></li> <span class="sep">|</span>
			<li><a href="javascript:shownew(1002)">人文地理</a><ul id="xiala"></ul></li> 
			<span class="sep">|</span>
			<li><a href="javascript:shownew(1003)">茶艺文化</a><ul id="xiala"></ul></li> 
			<span class="sep">|</span>
			<li><a href="javascript:shownew(1007)">社区论坛</a><ul id="xiala"></ul></li> 
			<span class="sep">|</span>
			<li><a href="javascript:shownew(1004)">古代文学</a><ul id="xiala"></ul></li> 
			<span class="sep">|</span>
			<li><a href="javascript:shownew(1005)">书画风采</a><ul id="xiala"> </ul></li>
			<span class="sep">|</span>
			<li><a href="javascript:shownew(1006)">珠宝鉴赏</a><ul id="xiala"></ul></li> 
			<span class="sep">|</span>
			<li><a href="../login.jsp">登录</a></li>
		</ul> -->
	</div>
</div>
