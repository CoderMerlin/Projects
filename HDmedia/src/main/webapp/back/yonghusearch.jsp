<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<table id="search_yonghu_info" ></table>
<div id="search_yonghu_info_find" style="width:100px, text-align:left">
	<form action="" style="padding:20px;float:lect;display:inline-block;">
	<span>用户昵称：</span><input type="text" name="yhname" id="search_yh_name"/>
	<span>用户邮箱：</span><input id="search_yh_email" class="easyui-validatebox" data-options="required:true,validType:'email'" />
	<span>用户手机号：</span><input  class="easyui-numberbox myinput" name="yhphone" id="search_yh_phone" />
	<span>用户注册时间：</span><input type="text" name="yhzctime" id="search_yh_zctime" class="easyui-datebox myinput" required/>
	<a href="javascript:findYongHuInfoByInfo()" class="easyui-linkbutton" data-options="iconCls:'icon-add'">查询</a>
	</form>
</div>

<script>
   		$(function(){
 			var mydatagrid; 
			mydatagrid=$('#search_yonghu_info').datagrid({   
			    url:'yongHu_findPageYongHuInfo', 
			    fitColumns:true,
			    striped:true,
			    loadMsg:"数据加载中...",
			    pagination:true,
			    rownumbers:true,
			    sortName:'yhzcid',
			    sortOrder:'asc',
			    remoteSort:false,
			    columns:[[ 
			        {field:'yhzcids',title:'用户注册编号',width:100,align:'center',checkbox:true},   
			        {field:'yhzcid',title:'用户注册编号',width:100,align:'center',sortable:true},
			        {field:'yhname',title:'用户昵称',width:100,align:'center',editor:{type:"text",options:{required:true}}},   
			        {field:'yhphone',title:'用户手机号码',width:100,align:'center',editor:{type:"text",options:{required:true}}},  
			        {field:'yhemail',title:'用户邮箱',width:100,align:'center',editor:{type:"text",options:{required:true}}},
			        {field:'yhzctime',title:'用户注册时间',width:100,align:'center'},
			        {field:'_operate',title:'操作',width:100,align:'center',
			        	formatter:function(value,rowData,index){
			        		//console.info(JSON.stringify(rowDate));  //将对象转换成字符串
			        		//console.info(JSON.parse(rowData));  //将字符串转换成对象
			        		return '<a class="icon-search1 icon-padding" href="javascript:showyonghuDetail(\''+rowData.yhzcid+'\')">详细</a>';
			        	}
			        }
			    ]],
			    toolbar:"#search_yonghu_info_find"
			});	
		}); 

	

	//多条件组合查询
	function findYongHuInfoByInfo(){
		var yhname=$.trim( $("#search_yh_name").val() );  //查询用户名
		var yhemail=$.trim( $("#search_yh_email").val() ); //查询用户邮箱
		var yhphone=$.trim($("#search_yh_phone").val() );  //查询用户手机
		var yhzctime=$.trim( $("#search_yh_zctime").datebox('getValue') );  //注册时间
		
		$('#search_yonghu_info').datagrid({
			url:'yHBean_findYongHuByInfo.action',
			queryParams:{yhname:yhname,yhemail:yhemail,yhphone:yhphone,yhzctime:yhzctime}
		});
		
	}
	//显示用户详细信息
	function showyonghuDetail(yhzcid){
		$("#yonghu_show_yonghusInfo").dialog("open");
		$.post("yongHu_findByYongHuZCById.action",{yhzcid:yhzcid},function(data){
			var yonghu=data.yh;
			if(yonghu==undefined){
				alert("该用户没有填写详细信息,请先填写详细信息,再查看！");
				$("#yh_zsname_show").val();  //用户真实姓名 
				$("#yh_sex_show").val();    //用户性别
				$("#yh_email_show").val(); //用户邮箱
				$("#yh_age_show").val();   //用户年龄
				$("#yh_phone_show").val();  //用户手机号
				$("#yh_indentity_show").val(); //用户身份证
				$("#yh_qq_show").val();  //用户qq号
				$("#yh_birthday_show").val();  //用户生日
				$("#yh_addr_show").val();  //用户地址
				$("#yh_ftnum_show").val();  //用户发帖次数
				$("#yh_jf_show").val();  //用户积分
				$("#yh_qd_show").val();  //用户签到
				$("#yh_jy_show").val();  //用户用户经验
				$("#yh_qianming_show").html("");  //用户签名
				$("#yh_photo_show_info").html(""); //用户头像
				$("#yonghu_show_yonghusInfo").dialog("close");   //关闭当前显示的窗口
			}else{
				$("#yh_zsname_show").val(yonghu.yhzsname);  //用户真实姓名 
				$("#yh_sex_show").val(yonghu.yhsex);  //用户性别
				$("#yh_age_show").val(yonghu.yhage);   //用户年龄
				$("#yh_phone_show").val(yonghu.yhphone);  //用户手机号
				$("#yh_indentity_show").val(yonghu.yhindentity); //用户身份证
				$("#yh_qq_show").val(yonghu.yhqq);  //用户qq号
				$("#yh_birthday_show").val(yonghu.yhbirthday);  //用户生日
				$("#yh_addr_show").val(yonghu.yhaddr);  //用户地址
				$("#yh_ftnum_show").val(yonghu.yhftnum);  //用户发帖次数
				$("#yh_jf_show").val(yonghu.yhjf);  //用户积分
				$("#yh_qd_show").val(yonghu.yhqd);  //用户签到
				$("#yh_jy_show").val(yonghu.yhjy);  //用户用户经验
				$("#yh_qianming_show").val(yonghu.yhqianming);  //用户签名
				var str="";
				str="<img src='../../uploadPic/"+yonghu.yhphoto+"' width='100px' height='100px' />&nbsp;";
				$("#yh_photo_show_info").html($(str)); //用户头像
			}		
		},"json");
	}
	
</script>
<style>
	.myinput{
		width:200px;
		border:1px solid #F63;
	}
	
	label{
		padding-right:10px;
	}
</style>

<!-- 详细 -->
<div id="yonghu_show_yonghusInfo" class="easyui-dialog" title="用户详细信息" data-options="fit:true,iconCls:'icon-add',resizable:true,modal:true,closed:true">
	<form action="" style="padding:20px;float:lect;display:inline-block;">
		<label>用户真实姓名:</label><input type="text" name="yhzsname" id="yh_zsname_show"  class="myinput" readonly="readonly" />&nbsp;&nbsp;&nbsp;
		<label>用户年龄:</label><input type="text" name="yhage" id="yh_age_show"  class="myinput" readonly="readonly"/>&nbsp;&nbsp;&nbsp;
		<label>用户积分:</label><input type="text" name="yhjf" id="yh_jf_show"  class="myinput" readonly="readonly"/><br /><br />
		
		<label>用户手机号码:</label><input class="easyui-numberbox myinput" name="yhphone" id="yh_phone_show" readonly="readonly"/>&nbsp;&nbsp;&nbsp;
		<label>用户性别:</label><input type="text" name="yhsex" id="yh_sex_show" class="myinput" readonly="readonly" />&nbsp;&nbsp;&nbsp;
		<label>用户签到:</label><input type="text" name="yhqd" id="yh_qd_show"  class="myinput" readonly="readonly"/><br /><br />
		
		<label>用户身份证号:</label><input type="text" name="yhindentity" id="yh_indentity_show"  class="myinput" readonly="readonly"/>&nbsp;&nbsp;&nbsp;
		<label>用户生日:</label><input type="text" name="yhbirthday" id="yh_birthday_show"  class="myinput" readonly="readonly"/>&nbsp;&nbsp;&nbsp;
		<label>用户经验:</label><input type="text" name="yhjy" id="yh_jy_show"  class="myinput" readonly="readonly"/><br /><br />
		
		
		<label>用户发帖次数:</label><input type="text" name="yhftnum" id="yh_ftnum_show"  class="myinput" readonly="readonly"/>&nbsp;&nbsp;&nbsp;
		<label>用户地址:</label><input type="text" name="yhaddr" id="yh_addr_show"  class="myinput" readonly="readonly"/>&nbsp;&nbsp;&nbsp;
		<label>用户qq:&nbsp;&nbsp;</label><input type="text" name="yhqq" id="yh_qq_show"  class="myinput" readonly="readonly"/><br /><br />
		
		<label>用户签名:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</label><input type="text" name="yhqianming" id="yh_qianming_show"  readonly="readonly"/><br /><br />
		
	</form>
	<div style="float:right;width:380px;margin-right:20px; margin-top:20px;" id="yh_photo_show_info">  
		
	</div>
</div>