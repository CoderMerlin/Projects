<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
<base href="/HDmedia/">
<meta charset="utf-8">
<title>发表帖子 - 综合讨论  弘道社区 </title>
<link rel="short icon" href="images/hd_01.png"/>  <!--小图标，需要浏览器兼-->
<link rel="stylesheet" type="text/css" href="forum/css/topicsheaderandfooterstyle.css" /><!--头部大部分样式-->
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="forum/js/topicsuser.js"></script>

<script type="text/javascript" src="ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="ueditor/ueditor.all.min.js"></script>
<script type="text/javascript" src="ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" src="forum/js/editor.js" ></script>


<script type="text/javascript">
	var yhname="${loginYongHu.yhname}";
</script>

</head>
<body>
        <div>
        	<div id="w_header">
       		<div id="nav" class="nav">
				<div class="meizu-header">
                	<div class="meizu-header-wrap">
                    	<div class="meizu-header-logo">
                        	<a href="" class="meizu-header-logo-img"></a>
                        </div>
                    <div class="meizu-header-content">
                        <ul class="meizu-header-link">
                        	<li><a href="">弘道首页</a></li>
                        	<li id="meizu-header-link-product-pro" data-subnav="pro" class="meizu-header-link-product">
                            	<a href="" class="meizu-header-link-product-a" onclick="">文化</a>
                            </li>
							<li id="meizu-header-link-product-meilan" data-subnav="meilan" class="meizu-header-link-product">
                                <a href="" class="meizu-header-link-product-a" onclick="">地理</a>
                            </li>
                            <li id="meizu-header-link-product-router" data-subnav="router" class="meizu-header-link-product">
                                <a href="" class="meizu-header-link-product-a" onclick="">中华文学</a>
                            </li>
							<li id="meizu-header-link-product-accesory" data-subnav="accesory" class="meizu-header-link-product">
                                <a href="" class="meizu-header-link-product-a" onclick="">珠宝商城</a>
                            </li>
							<li><a href="">服务</a></li>
							<li><a href="">专卖店</a></li>
                            <li><a href="" target="_blank">HongDao</a></li>
                            <li><a href="" target="_blank">论坛</a></li>
                         </ul>
                     <div class="login-box" id="mzCust" attribute="1">
                         <div class="loginArea">
                             <a id="newsNum" class="empty_newsnum" onmouseover="">0</a>
                             <div style="position: relative;" class="log-img-box">
                                 <img src="images/w50h50.jpg" alt="log" class="user-avatar">
                                 <img style="display:none; margin-left:10px;" class="arrow" src="forum/images/log-arrow.png">
                                    <ul class="log-dropdown" style="display:none;">
                                        <li><a class="" href="">我的账户</a></li>
                                        <li><a href="">我的动态</a></li>
                                        <li><a href="">我的帖子</a></li>
                                        <li><a href="">我的好友</a></li>
                                        <li><a href="" id="pm_ntc">我的消息</a></li>
                                        <li><a href="">个人设置</a></li>   
                                        <li><a class="logout_mzcust" id="mzLogout" href="">退出账户</a></li>
                                    </ul>
                              </div>
                              <div class="not-login-block-sub" style="padding-bottom: 20px; right: 200px; top: 80px; border-right-width: 1px; border-bottom-width: 1px; border-left-width: 1px; border-right-color: rgb(229, 229, 229); border-bottom-color: rgb(229, 229, 229); border-left-color: rgb(229, 229, 229); border-style: none solid solid; display: none;">
                                   <p><a id="mzLogin" class="u-btn" href="../login.jsp">立即登录</a></p>
                                   <p>没有账号？<a id="mzRegister" class="s-mzblue" href="register.jsp">立即注册</a></p>
                              </div>

                              <!--如果有新消息或提醒-->
                              <div id="newsNum_menu" class="p_pop" style="width:400px;display:none;">
                                 <div id="firstLevelNews"></div>
                                 <div id="secLevelNews"></div>
                              </div>
                          </div>
                     </div>
					</div>
             		</div>
        		</div>
			</div>
			</div>
			<div class="wp wp1">
				<div id="nv">
				<ul>                                                            	
                    <li id="mn_Nb934"><a href="" hidefocus="true">首页</a></li>
                    <li class="separator"> </li>
                    <li id="mn_forum"><a href="" hidefocus="true">版块</a></li>
                    <li class="separator"> </li>
                    <li id="mn_N2dde"><a href="" hidefocus="true">道友家</a></li>
                    <li class="separator"> </li>
                    <li id="mn_N4d1f"><a href="" hidefocus="true">道玩帮</a></li>                 
				</ul>
		</div>
		<div id="mu" class="cl" style="display:none;"></div>
        <div id="scbar" class="cl">
			<form id="scbar_form" method="post" autocomplete="off" onsubmit="" action="hDSearch_getTeiZiByContent.action" target="_blank">
            <div class="scbar_wrap">
                <input placeholder="搜索话题和用户" class=" xg1" name="srchtxt" id="scbar_txt" autocomplete="off" type="text">
                <button type="submit" name="searchsubmit" id="scbar_btn" sc="1" value="true"></button>
            </div>
			</form>
		</div>
    	</div>
        
       	
    </div>
    
    <!--发表评论-->
    <div id="wp" class="wp">
        <div class="newthread_post"> 
        	<div id="pt" class="bm cl">
            	<div class="z">
                	<a href="">弘道论坛</a>
                    <em> > </em>
                    <a href="">新手水吧</a>
                    <em> > </em>
                    		        发表帖子
                </div>
            </div>
            <form  action="tieZis_add.action?ltid=${sessionScope.ltid }"  method="post"  enctype="multipart/form-data">
            	<div id="ct" class="wp ">
                	<div>
                    	<div class="editorbox_post" id="editorbox">
                        	<ul class="tb tab_editorbox">
                            <li class="a">
                            	<a href="">发表帖子</a>
                           	</li>
                            </ul>
                            <div id="postbox">
                            	<div class="pbt cl">
									<div class="z">
										<span><input type="text" name="tzname" id="subject" class="input_style3" value="" onblur=""></span>
        								<span id="subjectchk">输入 <strong id="checklen">20</strong> 个字符以内的标题</span>
									</div>
								</div>
                                <div class="edt" id="e_body">
                                    <textarea style="line-height: 0px;height:330px;" id="content" name="tztext" >
  									
  									</textarea>
                                </div>
                                <div class="mtm identifying_code">
                                	<input  name="sechash" type="hidden"/>验证码
                                    <span id="seccode1">
                                    	<input  name="seccodeverify" type="text" autocomplete="off" style="ime-mode:disabled;width:100px" class="txt px vm" id="seccodetocheck" onblur="checkzccode()"/>
                                    	<span id="seccode2">
                                        	<img src="valiCodeImg.jsp" onClick="changeVilidateCode(this)" id="imgseccod" title="点击可刷新验证码"/>
                                        </span>
                                        <p class="seccodecheckwhat"></p>
                                    </span>
                                </div>
                                <div class="mtm pnpost" style="margin-top:10px;">
                                	<a class="normalbtn bluebtn" style="width:110px;">
                                    	<button  id="postsubmit" style="width:110px; margin:0" value="true" name="topicsubmit"><!--  onclick="submitQuestion() -->
                                        	<span>发表帖子</span>
                                        </button>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>           
	
</body>
</html>