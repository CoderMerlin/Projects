<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script>
function shownew(d){
	if(d==1005){
		location.href="indexlcw.jsp";
	}else if(d==1007){
		location.href="ForumIndex.jsp";
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
 
	<div id="hd_nav" class="hd_nav"><!--弘道菜单导航-->
		<div id="hd_title" class="hd_title">
        	<img src="images/1.png"/>
        	<p>弘扬中华文化	传颂道德品行</p>
        </div>
        <div id="hd_nav_menu" class="hd_nav_menu">
        	<ul  id="hd_menu" class="hd_menu_name">	  	
                <li class="hd_home_page">
                    <a href="index.jsp">首页</a>
                </li>
                <span class="sep">|</span>	
                <c:forEach items="${navname}" var="item"> 
                	<c:if test="${item.nav1status eq '1'}">
                		<li><a href="javascript:shownew(${item.nav1id })">${item.nav1name}</a>
                			<ul id="xiala">
                				<c:forEach items="${nav2name}" var="item1">
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
        </div>
    </div>
    
    
    
    
    
    
    <div class="meizu-header-user" id="meizu-header-user">
                <div class="meizu-header-user-img">
                    <img id="meizu-header-user-img" src="a.png" alt="log">
                    <span class="meizu-arrow-background"></span>
                    <span class="meizu-arrow-frontground"></span>
                </div>
                <div style="display: none;" class="meizu-header-user-box" id="meizu-header-user-box"></div>
                <!-- 登录前 -->
                <div class="meizu-header-user-trigger" id="_unlogin" style="display: block;">
                    <ul style="display: none;" class="meizu-header-user-dropdown">
                        <li>
                            <a href="https://member.meizu.com/login/login.html?lang=&amp;service=www&amp;appuri=http%3A%2F%2Fwww.meizu.com%2Faccounts%2Flogin&amp;useruri=http%3A%2F%2Fwww.meizu.com%2F%3Frefcode%3Dbaidu_pinzuan%26utm_source%3Dbaidupz%26utm_medium%3Dcpc%26utm_term%3Dmeizu_baidupz_e22211b6c6091985_20160307" id="meizu-header-user-login" class="button button-primary center">立即登录</a>
                        </li>
                        <li class="meizu-header-signup-help">没有账号?<a href="https://member.meizu.com/register?lang=&amp;service=www&amp;appuri=http%3A%2F%2Fwww.meizu.com%2Faccounts%2Flogin&amp;useruri=http%3A%2F%2Fwww.meizu.com%2F%3Frefcode%3Dbaidu_pinzuan%26utm_source%3Dbaidupz%26utm_medium%3Dcpc%26utm_term%3Dmeizu_baidupz_e22211b6c6091985_20160307" id="meizu-header-user-register">立即注册</a></li>
                        <li class="meizu-header-user-sep"></li>
                        <li>
                            <a href="http://store.meizu.com/cart">我的购物车</a>
                        </li>
                        <li>
                            <a href="http://store.meizu.com/myorder/">我的订单</a>
                        </li>
                        <li><a href="http://store.meizu.com/mformy/index">M码通道</a></li>
                    </ul>
                </div>
                <!-- 登录后 -->
                <div class="meizu-header-user-trigger" id="_islogin" style="display: none;">
                    <ul style="display: none;" class="meizu-header-user-dropdown"> 
                        <li style="margin-top: 10px;">
                            <a href="https://member.meizu.com/uc/webjsp/member/detail">个人中心</a>
                        </li>
                        <li>
                            <a href="http://store.meizu.com/cart">我的购物车</a>
                        </li>
                        <li>
                            <a href="http://store.meizu.com/myorder/">我的订单</a>
                        </li>
                        <li><a href="http://store.meizu.com/mformy/index">M码通道</a></li>
                        <li>
                            <button onclick="window.location.href=_logout" class="button button-primary center">退出登录</button>
                        </li>
                    </ul>
                </div>
            </div>   
