// JavaScript Document
/*$(function(){
	yhname=$.trim(yhname);
	if(yhname==null || yhname==""){
		$(".loginArea").mouseover(function(){  //用户为空
			$(".not-login-block-sub").css("display","block");
			$(".arrow").css("display","block");
		});
		
		$(".loginArea").mouseout(function(){
			$(".not-login-block-sub").css("display","none");
			$(".arrow").css("display","none");
		});
	}else{ //说明用户不为空
		$(".loginArea").mouseover(function(){
			$(".log-dropdown").css("display","block");
			$(".arrow").css("display","block");
		});

		$(".loginArea").mouseout(function(){
			$(".log-dropdown").css("display","none");
			$(".arrow").css("display","none");
		});
	}
	
});

*/

//重新获取验证码
function changeVilidateCode(obj){
	obj.src="../valiCodeImg.jsp?d="+new Date();
}


//检查验证码是否正确
function checkzccode(){
	var zccode=$.trim($("#seccodetocheck").val());
	if(zccode!=null || zccode!=""){
		$.post("../tieZiServlet?d="+new Date(),{op:"checkcodeimg",zccode:zccode},function(data){
			if(data==1){
				$(".seccodecheckwhat").html("验证码正确").css("color","#0F0");
			}else{
				$(".seccodecheckwhat").html("验证码错误").css("color","#F00");
			}
		});
	}else{
		$(".seccodecheckwhat").html("请输入验证码！").css("color","#F00");
		$("#seccodetocheck").val("");
	}
}

//发表主题
function submitQuestion(){
	var title=$.trim($('#subject').val());
	var content=ue.getContent();
	console.info(content);
	if(yhname=="" || yhname==null){
		alert("请先登录!!!");
	}else if(title =="" || title==null){
		alert("标题不能为空!!!");
	}else{
		$.post("../tieZiServlet",{op:"addTieZi",yhname:yhname,title:title,content:content},function(data){
			if(data==1){
				console.info("发表成功!");
			}else{
				console.info("发表失败！");
			}
		});
	}
}



	