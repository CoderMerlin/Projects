<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<table id="search_yonghu_info" data-options="fit:true"></table>
<div id="search_yonghu_info_find" style="width:100px, text-align:left">
	<span>用户昵称：</span><input type="text" name="yhname" id="search_yh_name"/>
	<span>用户邮箱：</span><input id="search_yh_email" class="easyui-validatebox" data-options="required:true,validType:'email'" />
	<span>用户手机号：</span><input  class="easyui-numberbox myinput" name="yhphone" id="search_yh_phone" />
	<span>用户注册时间：</span><input type="text" name="yhzctime" id="search_yh_zctime" class="easyui-datebox myinput" required/>
	<a href="javascript:findYongHuInfoByInfo()" class="easyui-linkbutton" data-options="iconCls:'icon-add'">查询</a>
</div>

<script>
		$(function(){
			$('#search_yonghu_info').datagrid({   
			    url:'../yongHuServlet', 
			    queryParams:{op:"getPageYongHuInfo"},
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
			url:'yHSearchBean_findYongHuByInfo',
			queryParams:{yhname:yhname,yhemail:yhemail,yhphone:yhphone,yhzctime:yhzctime}
		});
		
	}



</script>