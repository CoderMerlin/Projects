<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE html>
<html>
<head>
	<title>注册成功</title>
	<meta http-equiv="content-Type" content="text/html" charset="utf-8">
	<link rel="stylesheet" type="text/css" href="css/register.css">
	<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
	<script type="text/javascript" src="js/register.js"></script>
</head>
<body>
	<div class="content">
    	<br /><br />
    	<div class="register_logo">
        	<img src="images/hd_02.png"/>
        </div>
		<div class="success">
			<h2>恭喜你注册成功,赶快登陆吧！</h2>
			<p><a href="login.jsp"><input type="submit" class="success_login" value="登陆" style="width:200px; height:30px;"/></a></p>
		</div>	
	</div>
    
        <ul class="bRadius2 mail1">
            <li data-mail="@qq.com" class="item1 item2">@qq.com</li>
            <li data-mail="@sina.com" class="item1 item3">@sina.com</li>
            <li data-mail="@126.com" class="item1 item4">@126.com</li>
            <li data-mail="@163.com" class="item1 item5">@163.com</li>
            <li data-mail="@gmail.com" class="item1 item6">@gmail.com</li>
        </ul>
        
        <div id="mz_Float">
            <div class="mz_FloatBox">
                <div class="mz3AngleL">
                    <i class="i_icon"></i>
                </div>
                <div class="mzFloatTip bRadius2">长度为8-16个字符，区分大小写，至少包含两种类型</div>
            </div>
        </div>

        <div style="text-align:center;margin:50px 0; font:normal 14px/24px 'MicroSoft YaHei';">
        </div>
        <div class="footer"><p>弘道传媒版权所有-备案号：湘ICP备13003602号-2经营许可证编号：湘B2-20130198营业执照.ALL RIGHT BY YC_弘道传媒</p></div>
</body>
</html>