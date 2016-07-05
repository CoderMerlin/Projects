<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
 
	<div id="hd_nav" class="hd_nav" style="height: 100px"><!--弘道菜单导航-->
		<div id="hd_title" class="hd_title">
        	<img src="images/hd_02.png"/>
        	<p>弘扬中华文化	传颂道德品行</p>
        </div>
         <div id="hd_nav_menu" class="hd_nav_menu">
        	<ul  id="hd_menu" class="hd_menu_name" style="padding-top: 20px;">	  	
                <li class="hd_home_page">
                    <a href="hDIndex_getIndexAllInfo.action">首页</a>
                </li>
                <span class="sep">|</span>	
                <li>
                    <a href="georaphyBack_properson.action">人文地理</a>
                    <ul>
                        <li><a href="georaphyBack_properson.action">名族</a></li>
                        <li><a href="georaphyBack_properson.action">地域</a></li> 
                    </ul>
                </li>
                <span class="sep">|</span>				
                <li >
                    <a href="">文化</a>
                     <ul>
                        <li><a href="">茶艺文化</a></li>
                        <li><a href="">酒水文化</a></li>
                        <li><a href="">美食文化</a></li>  
                    </ul>
                </li>
                <span class="sep">|</span>				
                <li >
                    <a href="works_getAllWorks.action">中华文学</a>
                    <ul>
                        <li><a href="home/Chinese_Literature.jsp" name="hd_Ancient_poetry_title">古诗词</a></li>
                        <li><a href="home/Chinese_Literature.jsp">文言文</a></li>
                        <li><a href="home/Chinese_Literature.jsp">古代典籍</a></li>
                        <li><a href="home/Chinese_Literature.jsp">历史故事</a></li>
                    </ul>
                </li>
                <span class="sep">|</span>				
                <li >
                    <a href="home/PaintingAndCalligraphy.jsp">书画风采</a>
                    <ul>
                         <li><a href="home/PaintingAndCalligraphy.jsp">国画</a></li>
                         <li><a href="home/PaintingAndCalligraphy.jsp">书法</a></li>
                         <li><a href="home/PaintingAndCalligraphy.jsp">古代书法</a></li>
                         <li><a href="home/PaintingAndCalligraphy.jsp">古代绘画</a></li>
                         <li><a href="home/PaintingAndCalligraphy.jsp">近代绘画</a></li>
                    </ul>
                </li>
                <span class="sep">|</span>				
                <li >
                    <a href="">鉴赏文宝</a>
                    <ul>
                      <li><a href="">玉器</a></li>
                      <li><a href="">书画</a></li>
                      <li><a href="">陶瓷</a></li>           
                    </ul>
                </li>
                <span class="sep">|</span>				
                <li >
                    <a href="">在线商城</a>
                    <ul>
                      <li><a href="">拍卖会场</a></li>
                    </ul>
                </li>
                <span class="sep">|</span>				
                <li >
                    <a href="lunTan_find.action">社区论坛</a>
                </li>
             </ul>
        </div>
    </div>

    <div class="meizu-header-user" id="meizu-header-user" style="display: none;">
                <div class="meizu-header-user-img">
                    <img id="meizu-header-user-img" src="a.png" ><!-- alt="log" -->
                    <span class="meizu-arrow-background"></span>
                    <span class="meizu-arrow-frontground"></span>
                </div>
                <div style="display: none;" class="meizu-header-user-box" id="meizu-header-user-box"></div>
                <!-- 登录前 -->
                <div class="meizu-header-user-trigger" id="_unlogin" style="display: block;">
                    <ul style="display: none;" class="meizu-header-user-dropdown">
                        <li>
                            <a href="#" id="meizu-header-user-login" class="button button-primary center">立即登录</a>
                        </li>
                        <li class="meizu-header-signup-help">没有账号?<a href="#" id="meizu-header-user-register">立即注册</a></li>
                        <li class="meizu-header-user-sep"></li>
                        <li>
                            <a href="#">我的购物车</a>
                        </li>
                        <li>
                            <a href="#">我的订单</a>
                        </li>
                        <li><a href="#">M码通道</a></li>
                    </ul>
                </div>
                <!-- 登录后 -->
                <div class="meizu-header-user-trigger" id="_islogin" style="display: none;">
                    <ul style="display: none;" class="meizu-header-user-dropdown"> 
                        <li style="margin-top: 10px;">
                            <a href="#">个人中心</a>
                        </li>
                        <li>
                            <a href="#">我的购物车</a>
                        </li>
                        <li>
                            <a href="#">我的订单</a>
                        </li>
                        <li><a href="#">M码通道</a></li>
                        <li>
                            <button onclick="window.location.href=_logout" class="button button-primary center">退出登录</button>
                        </li>
                    </ul>
                </div>
            </div>   
