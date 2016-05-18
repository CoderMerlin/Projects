<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!doctype html>
<html>
<head>
<base href="/HDmedia/">
<meta charset="utf-8">
<title>用户登录</title>
<link type="text/css" rel="stylesheet" href="css/login.css" />
<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script type="text/javascript" src="home/js/login.js"></script>
</head>
<body>
	<center>
		<div id="register">
			<br />
			<br />
			<div id="register_logo">
				<img src="images/hd_02.png" style="width: 100%" ; height="100%" ;"/>
			</div>
			<div id="register_title" style="display: block;">帐号登录</div>
			<div id="pwd_reset" style="display: none;">重置密码</div>
			<form id="mianform" action="loginServlet?op=yonghuLogin"
				method="post" role="form">
				<p id="p1" style="display: none;">请输入注册的邮箱地址、手机号码或弘道账号：</p>
				<p id="p2" style="display: none;">请重设您的帐号密码：</p>
				<input type="text" name="user" id="user" placeholder="邮箱/手机号码/弘道账号"
					style="width: 350px; height: 40px; display: block;" /> <label
					id="usert" style="color: #EB5B0E; display: none;"> <img
					src="images/tishi.png" style="margin-left: 5px; margin-right: 5px;" />请输入您的账号
				</label> <input type="password" name="pwd" id="pwd" placeholder="请输入你的密码"
					style="display: block;" /> <input type="password" name="pwd"
					id="pwd1" placeholder="请输入你的新密码" style="display: none;" /> <label
					id="pwdt1" style="color: #EB5B0E; display: none;"> <img
					src="images/tishi.png" style="margin-left: 5px; margin-right: 5px;" />请输入你的新密码
				</label> <input type="password" name="pwd" id="pwd2" placeholder="请确认你的密码"
					style="display: none;" />

				<p id="p3" style="display: none; font-weight: 300;">密码长度8~16位，数字、字母、字符至少包含两种</p>
				<label id="pwdt2" style="color: #EB5B0E; display: none;"> <img
					src="images/tishi.png" style="margin-left: 5px; margin-right: 5px;" />密码长度8~16位，数字、字母、字符至少包含两种
				</label> <input type="text" name="yzm" id="yzm" placeholder="图片验证码"
					style="display: none;" /> <input type="text" name="yzmnum"
					id="yzmnum" placeholder="验证码图片"
					style="margin-left: 9%; display: none; font-family: 'Candara'; font-size: 30px; font-weight: bolder; text-align: center; color: #333;"
					onclick="change()" /> <label id="yzmt"
					style="color: #EB5B0E; display: none;"> <img
					src="images/tishi.png" style="margin-left: 5px; margin-right: 5px;" />验证码错误或已过期
				</label> <input type="submit" name="load" id="load" value="立即登录"
					style="display: block;" /> <input type="button" name="next"
					id="next" value="下一步" style="display: none; margin-top: 12%;"
					onclick="nextstep()" /> <input type="button" name="submit"
					id="submit" value="提交" style="display: none;" onclick="submitpwd()" />
			</form>
			<div id="line" style="display: block">
				<li
					style="border-height: 0; border-width: 20%; border: 0.5px solid #ccc;"></li>
				<li style="line-height: 30%;">其他方式登录</li>
				<li
					style="border-height: 0; border-width: 20%; border: 0.5px solid #ccc;"></li>
			</div>
			<div id="picpart" style="display: block;">
				<li><a href=""><img src="images/qq.png"
						style="width: 100%; height: 100%;" /></a></li>
				<li><a href=""><img src="images/wb.png"
						style="width: 100%; height: 100%;" /></a></li>
				<li><a href=""><img src="images/wx.png"
						style="width: 100%; height: 100%;" /></a></li>
			</div>
			<div id="help" style="display: block">
				<li style="border-right: 1px solid #ccc;"><a
					href="register.jsp">注册弘道账号</a></li>
				<li><a href="javascript:forgetpwd()">忘记密码？</a></li>
			</div>
		</div>
		<div id="footer">
			<p>弘道传媒版权所有-备案号：湘ICP备13003602号-2经营许可证编号：湘B2-20130198营业执照.ALL
				RIGHT BY YC_弘道传媒</p>
		</div>
	</center>
</body>
</html>
