<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE html>
<html>
<head>
	<title>弘道传媒注册</title>
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
		<form id="#mainForm1" class="mainForm mainForm1" method="post" action="user_registerBymail.action">
			<div class="number">
				<a href="##" class="linkABlue">邮箱号注册</a>
				<span></span>
				<a href="##" class="linkAGray number1">账户名注册</a>
			</div>
			<div class="normalInput">
				<input type="text" class="email1" name="yhemail" maxlength="11" placeholder="安全邮箱">	
			</div>
			<span class="error error1"></span>
			<span class="error error2"></span>
			<div class="normalInput">
				<input type="password" class="password" name="yhpwd" id="epassword" maxlength="16" autocomplete="off" placeholder="密码">
				<a id="pwdBtn" href="##" class="pwdBtnShow" isshow="false">
					<i class="i_icon"></i>
				</a>
			</div>
			<span class="error error3"></span>
			<div class="rememberField">
				<span class="checkboxPic check_chk" tabindex="-1" isshow="false">
					<i class="i_icon"></i>
				</span>
				<input type="checkbox" clstag="pageclick|keycount|20150112ABD|6" tabindex="3" class="jdcheckbox" name="chkRememberMe" id="autoLogin">
				<label class="pointer">我已阅读并接受</label>
				<a href="#" target="_blank" class="linkABlue">《弘道传媒服务协议条款》</a>
			</div>
			<span class="otherError">请确认已阅读并同意《弘道传媒服务协议条款》</span>
			<input type="submit" class="fullBtnBlue" value="注册"/>
		</form>        
                
        <!--这是账号注册-->
		<form id="#mainForm2" class="mainForm mainForm2" method="post" action="user_register.action">
			<div class="number">
				<a href="##" class="linkABlue2 number2">邮箱号注册</a>
				<span></span>
				<a href="" class="linkAGray2">账户名注册</a>
			</div>
			<div class="normalInput">
				<input type="text" class="username" name="yhname" id="yhname" maxlength="32" placeholder="账户名" autocomplete="off">	
				<span class="grayTip"></span>
			</div>
			<span class="error error1"></span>		
			<div class="normalInput">
				<input type="password" class="passwordN" name="yhpwd" id="yhpwd" maxlength="16" autocomplete="off" placeholder="密码">
				<a id="pwdBtnN" href="##" class="pwdBtnShowN" isshow="false">
					<i class="i_icon"></i>
				</a>
			</div>
			<span class="error error3"></span>         
            <div class="normalInput">
            	<input type="password" class="password2N" maxlength="16" autocomplete="off" placeholder="确认密码">
                <a id="pwdBtnN1" href="##" class="pwdBtnShowN1" isshow="false">
					<i class="i_icon"></i>
				</a>
            </div>
            <span class="error error4"></span>
            
			<div class="rememberField">
				<span class="checkboxPic check_chk" tabindex="-1" isshow="false">
					<i class="i_icon"></i>
				</span>
				<label class="pointer">我已阅读并接受</label>
				<a href="ServiceAgreement.jsp" target="_blank" class="linkABlue">《弘道传媒服务协议条款》</a>
			</div>
			<span class="otherError">请确认已阅读并同意《弘道传媒服务协议条款》</span>
			<input type="submit" class="fullBtnBlue" value="注册"/>
			
		</form>
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

