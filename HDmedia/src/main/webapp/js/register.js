$(function(){

	//页面切换初始化
	$(".number2").click(function(){
		$(".mainForm1").show();
		$(".mainForm2").hide();
		$(".error").hide();
		$(".normalInput").removeClass("errorC");
		$(".normalInput").removeClass("checkedN");
		$(".mainForm input").val("");
	});
	$(".number1").click(function(){
		$(".mainForm2").show();
		$(".mainForm1").hide();
		$(".error").hide();
		$(".normalInput").removeClass("errorC");
		$(".normalInput").removeClass("checkedN");
		$(".mainForm input").val("");
	});
	
	
	//文本框失去焦点
	$(".mainForm input").blur(function(){
		$("#mz_Float").css("top","");
	});

	//协议条款
	$(".checkboxPic").click(function(){
		if($(this).attr("isshow")=="false")
		{
			$(this).parent().css("margin-bottom","10px");
			$(".checkboxPic i").css({"background-position":" -0px -127px"});
			$(".otherError").css("display","block");
			$(this).attr("isshow","true");
		}
		else
		{
			$(this).parent().css("margin-bottom","");
			$(".checkboxPic i").css({"background-position":"-31px -127px"});
			$(".otherError").hide();
			$(this).attr("isshow","false");
		}
		
	}); 


	//mainform1
	//密码是否可见(mainform1)
	$(".pwdBtnShow").click(function(){
		if($(".pwdBtnShow").attr("isshow")=="false")
		{
			$(".pwdBtnShow i").css("background-position","-30px -93px");
			$("#epassword").attr("type","text");
			$(".pwdBtnShow").attr("isshow","true");
		}
		else
		{
			$(".pwdBtnShow i").css("background-position","-60px -93px");
			$("#epassword").attr("type","password");
			$(".pwdBtnShow").attr("isshow","false");
		}
		
	});


	//邮箱栏键盘操作
	$(".email1").keyup(function(){//键盘监听keyup,keydown,keypress
		var emailVal = $(".email1").val();
		emailValN = emailVal.replace(/\s/g,'');//去空
		emailValN = emailValN.replace(/[\u4e00-\u9fa5]/g,'');//屏蔽中文
		if(emailValN!=emailVal)
		{
			$(".email1").val(emailValN);
		}
		
		var mailVal = emailValN.split("@");
		var mailHtml=mailVal[0];
		if(mailHtml.length>15)
		{
			mailHtml=mailHtml.slice(0,15)+"...";//字数超加省略
		}
		
		for(var i=1;i<6;i++)
		{
			var M = $(".item1"+i).attr("data-mail");
			$(".item1"+i).html(mailHtml+M);
		}
	});
		
	
	//邮箱提示
	$(".item1").click(function(){
		var a= $(".email1").val();
		var b= $(this).attr("data-mail");
		$(".email1").val(a+b);
		$(".email1").trigger("focus");//setTimeout($(".email").("focus") );效果同，时间设多少无所谓
	});


	$(".email1").click(function(){
		if($(".error2").css("display")=="block")
		{
			$(".mail1").css("top","350px");
		}
		$(".mail1").show(); 
		return false;
	});
	$(document).click(function(){
		$(".mail1").hide();
	});
	
	
	
	//邮箱栏获得焦点
	$(".email1").focus(function(){
		$(".email1").parent().removeClass("errorC");
		$(".email1").parent().removeClass("checkedN");
		$(".error1").hide();
		$("#mz_Float").css("top","290px");
		$("#mz_Float").find(".bRadius2").html("输入邮箱号码，可用于登录和找回密码");
	});
	
	
	
	//邮箱栏失去焦点
	$(".email1").blur(function(){
		reg=/^\w+[@]\w+((.com)|(.net)|(.cn)|(.org)|(.gmail))$$/;
		if( $(".email1").val()=="")
		{
			$(".email1").parent().addClass("errorC");
			$(".error1").html("邮箱不能为空!");
			$(".error1").css("display","block");
		}
        else if(!reg.test($(".email1").val()))
        {
        	$(".email1").parent().addClass("errorC");
            $(".error1").html("请确认邮箱是否正确!");
            $(".error1").css("display","block");
        }
        else 
        {
        	$(".email1").parent().addClass("checkedN");
        }
	});
	

	//验证码栏获得焦点
	$(".kapkey").focus(function(){
		$(".kapkey").parent().removeClass("errorC");
		$(".kapkey").parent().removeClass("checkedN");
		$(".error2").hide();
		if($(".error1").css("display")=="block")
		{
			$("#mz_Float").css("top","405px");
		}
		else
		{
			$("#mz_Float").css("top","375px");
		}
		
		$("#mz_Float").find(".bRadius2").html("请输入邮箱收到的验证码！");
	});
	
	
	
	

	//验证码栏失去焦点
	$(".kapkey").blur(function(){
		reg=/^.*[\u4e00-\u9fa5]+.*$/;
		if( $(".kapkey").val()=="")
		{
			$(".kapkey").parent().addClass("errorC");
			$(".error2").html("请填写验证码");
			$(".error2").css("display","block");
		}
        else if($(".kapkey").val().length<3)
        {   
        	$(".kapkey").parent().addClass("errorC");
            $(".error2").html("验证码长度有误！");
            $(".error2").css("display","block");
        }
        else if(reg.test($(".kapkey").val()))
        {
        	$(".kapkey").parent().addClass("errorC");
            $(".error2").html("验证码里无中文！");
            $(".error2").css("display","block");
        }
        else 
        {
        	$(".kapkey").parent().addClass("checkedN");
        }
	});

	//密码栏失去焦点(mainform1)
	$(".password,.password1").blur(function(){
		reg1=/^.*[\d]+.*$/;
		reg2=/^.*[A-Za-z]+.*$/;
		reg3=/^.*[_@#%&^+-/*\/\\]+.*$/;//验证密码
		if($(".pwdBtnShow").attr("isshow")=="false")
		{
			var Pval = $(".password").val();
		}

		if( Pval =="")
		{
			$(".password").parent().addClass("errorC");
			$(".error3").html("请填写密码");
			$(".error3").css("display","block");
		}
        else if(Pval.length>16 || Pval.length<8)
        {   
        	$(".password").parent().addClass("errorC");
            $(".error3").html("密码应为8-16个字符，区分大小写");
            $(".error3").css("display","block");
        }
        else if(!((reg1.test(Pval) && reg2.test(Pval)) || (reg1.test(Pval) && reg3.test(Pval)) || (reg2.test(Pval) && reg3.test(Pval)) ))
        {
        	$(".password").parent().addClass("errorC");
            $(".error3").html("需至少包含数字、字母和符号中的两种类型");
            $(".error3").css("display","block");
        }
        else
        {
        	$(".password").parent().addClass("checkedN");
        }
	});

	//密码栏获得焦点(mainform1)
	$(".password").focus(function(){
		$(".password").parent().removeClass("errorC");
		$(this).parent().removeClass("checkedN");
		$(".error3").hide();
		if($(".error1").css("display")=="block" && $(".error2").css("display")=="block")
		{
			$("#mz_Float").css("top","500px");
		}
		else if($(".error1").css("display")=="block" || $(".error2").css("display")=="block")
		{
			$("#mz_Float").css("top","470px");
		}
		else
		{
			$("#mz_Float").css("top","440px");
		}
		
		$("#mz_Float").find(".bRadius2").html("长度为8-16个字符，区分大小写，至少包含两种类型");
	});


	//mainform1end



	//mainForm2
	
	//账户名栏获得焦点
	$(".username").focus(function(){
		$(".username").parent().removeClass("errorC");
		$(".username").parent().removeClass("checkedN");
		$(".error1").hide();
		$("#mz_Float").css("top","290px");
		$("#mz_Float").find(".bRadius2").html("长度为4-32个字符支持数字、字母、下划线，字母开头，字母或数字结尾");
	});
	
	
	//账户名栏失去焦点
	$(".username").blur(function(){
		reg=/^[a-zA-Z][0-9a-zA-Z_]{2,30}[0-9a-zA-Z]$/;//验证手机正则(输入前7位至11位)

		if( $(".username").val()=="")
		{ 
			$(".username").parent().addClass("errorC");
			$(".error1").html("请输入账户名");
			$(".error1").css("display","block");
		}
		else if($(".username").val().length>32 || $(".username").val().length<4)
        {   
        	$(".username").parent().addClass("errorC");
            $(".error1").html("账户名长度有误！");
            $(".error1").css("display","block");
        }
        else if(!reg.test($(".username").val()))
        {   
        	$(".username").parent().addClass("errorC");
            $(".error1").html("账户名格式有误!!");
            $(".error1").css("display","block");
        }
        else
        {
        	$(".username").parent().addClass("checkedN");
        }
	});
	
	
	//密码是否可见(mainform2)
	$(".pwdBtnShowN").click(function(){
		if($(".pwdBtnShowN").attr("isshow")=="false")
		{
			$(".pwdBtnShowN i").css("background-position","-30px -93px");
			$(".passwordN").attr("type","text");
			$(".pwdBtnShowN").attr("isshow","true");
		}
		else
		{
			$(".pwdBtnShowN i").css("background-position","-60px -93px");
			$(".passwordN").attr("type","password");
			$(".pwdBtnShowN").attr("isshow","false");
		}	
	});




	
	
	//密码栏获得焦点(mainform2)
	$(".passwordN,.password1N").focus(function(){
		$(".passwordN").parent().removeClass("errorC");
		$(this).parent().removeClass("checkedN");
		$(".error3").hide();
		if($(".error1").css("display")=="block")
		{
			$("#mz_Float").css("top","380px");
		}
		else
		{
			$("#mz_Float").css("top","380px");
		}
		
		$("#mz_Float").find(".bRadius2").html("长度为8-16个字符，区分大小写，至少包含两种类型!");
	});
	
	
	//密码栏失去焦点(mainform2)
	$(".passwordN,.password1N").blur(function(){
		reg1=/^.*[\d]+.*$/;
		reg2=/^.*[A-Za-z]+.*$/;
		reg3=/^.*[_@#%&^+-/*\/\\]+.*$/;//验证密码
		if($(".pwdBtnShow").attr("isshow")=="false")
		{
			var Pval = $(".passwordN").val();
		}
		
		if( Pval =="")
		{
			$(".passwordN").parent().addClass("errorC");
			$(".error3").html("请填写密码");
			$(".error3").css("display","block");
		}
        else if(Pval.length>16 || Pval.length<8)
        {   
        	$(".passwordN").parent().addClass("errorC");
            $(".error3").html("密码应为8-16个字符，区分大小写");
            $(".error3").css("display","block");
        }
        else if(!((reg1.test(Pval) && reg2.test(Pval)) || (reg1.test(Pval) && reg3.test(Pval)) || (reg2.test(Pval) && reg3.test(Pval)) ))
        {
        	$(".passwordN").parent().addClass("errorC");
            $(".error3").html("需至少包含数字、字母和符号中的两种类型");
            $(".error3").css("display","block");
        }
        else
        {
        	$(".passwordN").parent().addClass("checkedN");
        }
	});
	
	
	
		//确认密码是否可见
	$(".pwdBtnShowN1").click(function(){
		if($(".pwdBtnShowN1").attr("isshow")=="false")
		{
			$(".pwdBtnShowN1 i").css("background-position","-30px -93px");
			$(".password2N").attr("type","text");
			$(".pwdBtnShowN1").attr("isshow","true");
		}
		else
		{
			$(".pwdBtnShowN1 i").css("background-position","-60px -93px");
			$(".password2N").attr("type","password");
			$(".pwdBtnShowN1").attr("isshow","false");
		}
		
	});
	
	//确认密码获取焦点
	$(".passwordN,.password2N").focus(function(){
		$(".passwordN").parent().removeClass("errorC");
		$(this).parent().removeClass("checkedN");
		$(".error3").hide();
		if($(".error1").css("display")=="block")
		{
			$("#mz_Float").css("top","464px");
		}
		else
		{
			$("#mz_Float").css("top","434px");
		}
		
		$("#mz_Float").find(".bRadius2").html("请确认密码一致!");
	});	
	
	
	
	//确认密码失去焦点(mainform2)
	$(".password2N,.password3N").blur(function(){
		var password1=$.trim($(".passwordN").val());  
		var password2=$.trim($(".password2N").val()); 
		
		
		if(password1!="" && password1==password2){
			$(".password2N").parent().addClass("checkedN");
		}else{
			$(".password2N").parent().addClass("errorC");
            $(".error4").html("两次密码不一致!请确认两次密码一致!");
            $(".error4").css("display","block");
		}
	});

});

