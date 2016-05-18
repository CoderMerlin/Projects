<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<base href="/HDmedia/">
<script>
	//产生随机验证码
    function showMyCodeImg(obj){
    	obj.src="valiCodeImg.jsp?d="+new Date();
   	}
</script>
<center>
		<img alt="图片加载失败" src="images/hd_02.png">
</center>
<style>
	.myinput{
		width:200px;
		border:1px solid #F63;
	}
	
	.mymanagerdiv{
		margin-top:100px;
	}
	.mymanagerdiv img{
		width:100px;
		height:100px;
	}
	
	label{
		padding-right:10px;
	}	
	.input-group-btn a img{
		width:50px;
		height:20px;
		padding-top:10px;
	}
	
</style>