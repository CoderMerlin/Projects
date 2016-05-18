<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<table id="tiezi_info"  data-options="fit:true"></table>

<script>
		var op;
		var flag;
		var statusObj=[{sid:0,sname:'不可用'},{sid:1,sname:'可用'}];
		var tiezi;
	$(function(){
		var datagrid;
		var editRow=undefined;
		
		datagrid=$('#tiezi_info').datagrid({
			url:"../tieZiServlet",
			queryParams:{op:"getPageTieZiInfo"},
			fitColumns:true,
		    striped:true,
		    loadMsg:"数据加载中...",
		    pagination:true,
		    rownumbers:true,
		    sortName:'tid',
		    sortOrder:'asc',
		    remoteSort:false,
		    columns:[[
		    	{field:'tids',title:'帖子编号',width:100,align:'center',checkbox:true},   
		        {field:'tid',title:'帖子编号',width:100,align:'center',sortable:true},   
		        {field:'ltname',title:'论坛名称',width:100,align:'center',editor:{type:"text",options:{required:true}}},  
		        {field:'yhzsname',title:'用户名',width:100,align:'center',editor:{type:"text",options:{required:true}}},
		        {field:'tzname',title:'帖子标题',width:100,align:'center',editor:{type:"text",options:{required:true}}},   
		        {field:'tztime',title:'发表时间',width:100,align:'center',editor:{type:"text",options:{required:true}}},
		        {field:'status',title:'状态',width:100,align:'center',editor:{type:"combobox",options:{
	        	required:true,valueField:'sid',textField:'sname',data:statusObj}},
	        	formatter:function(value,row,index){
	        		for(var i=0;i<statusObj.length;i++){
	        			if(statusObj[i].sid==value){
	        				return statusObj[i].sname;
	        			}
	        		}
	        		return value;
	        	}	
	        } 
		               
		    ]],
		    toolbar:[{
		    	text:"添加",
		    	iconCls:'icon-add',
		    	handler:function(){
		    		$("#tiezi_add_tiezi").dialog("open");
		    	}
		    },{
		    	text:"修改",
		    	iconCls:'icon-edit',
		    	handler:function(){
		    		var rows=datagrid.datagrid("getChecked")[0];
		    		if(rows==undefined){
		    			$.messager.show({title:'温馨提示',msg:'您没有选中数据或选中多行数据,请重新选择...',timeout:2000,showType:'slide'});
		    		}else{
						tid=rows.tid;
						$.post("../tieZiServlet",{op:"findTieZiByTid",tid:tid},function(data){
							tiezi=data.updaterow;
							$("#tz_updateyhid").val(tiezi.yhid);
							$("#tz_updateltname").val(tiezi.ltname);
							$("#tz_updatetzname").val(tiezi.tzname);
							$("#tz_updatetzzy").val(tiezi.tzzy);
							$("#tz_updatetztime").datebox('setValue',tiezi.tztime);;
							$("#tz_updateweight").val(tiezi.weight);
							$("#tz_updatetzclick").val(tiezi.tzclick);
							var str="";
							var tzphoto=tiezi.tzphoto.split(",");
							for(var i=0;i<tzphoto.length;i++){
								str+="<img src='../"+tzphoto[i]+"' width='100px' height='100px'>&nbsp;";
							}
							$("#tiezi_img_updateshow").html($(str));
							updateUE.setContent(tiezi.tztext);
							$("#tiezi_update_tiezi").dialog("open");
						},"json");
		    		}
		    	}
		    },{
		    	text:"删除",
		    	iconCls:'icon-remove',
		    	handler:function(){
		    		var rows=datagrid.datagrid("getChecked");
		    		
		    		if(rows.length<=0){
		    			$.messager.show({title:'温馨提示',msg:'请选择您要删除的数据...',timeout:2000,showType:'slide'});
		    		}else{
		    			$.messager.confirm('信息确认','您确定要删除选中的数据吗？',function(r){
		    				if(r){
		    					var tids="";
		    					for(var i=0;i<rows.length-1;i++){
		    						tids+=rows[i].tid+",";
		    					}
		    					tids+=rows[i].tid;
		    					
		    					$.post("../tieZiServlet",{op:"delTieZiInfo",tids:tids},function(data){
		    						if(data>0){
		    							$.messager.show({title:'成功提示',msg:'新闻信息删除成功...',timeout:2000,showType:'slide'});
										datagrid.datagrid("reload");
		    						}else{
		    							$.messager.alert('失败提示','新闻信息删除失败...');
		    						}
		    					});
		    				}
		    			});
		    		}
		    	}
		    },{
		  		text:'撤销',
		  		iconCls:'icon-redo',
		    	handler:function(){
		    		datagrid.datagrid("rejectChanges");  //回滚自创建以来或上次调用AcceptChanges，所有的变化数据
		    		datagrid.datagrid("endEdit",editRow);
		    		editRow=undefined;
		    	}
		  	}]
		
		});
	});
	
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

<div id="tiezi_add_tiezi" class="easyui-dialog" title="添加帖子信息"  data-options="fit:true,iconCls:'icon-add',resizable:true,modal:true,closed:true">
	<form style="padding:20px;float:left;display:inline-block;">
		<label>用户id:</label><input type="text" name="yhid" id="tz_yhid" class="myinput"><br/><br/>
	    <label>论坛名字:</label><input type="text" name="ltname" id="tz_ltname" class=" myinput" /><br/><br/>
	    <label>帖子标题:</label><input type="text" name="tzname" id="tz_tzname" class="myinput"><br/><br/>
	    <label>帖子摘要:</label><input type="text" name="tzzy" id="tz_tzzy" class=" myinput" /><br/><br/>
	    <label>发表时间:</label><input name="tztime" id="tz_tztime" class="easyui-datetimebox myinput" required/><br /><br />
		<label>帖子权重:</label><input type="text" name="weight" id="tz_weight" class="myinput"/><br/><br/>
		<label>帖子点击量:</label><input type="text" name="tzclick" id="tz_tzclick" class="myinput" value="0"/><br/><br/>
		<label>帖子图片:</label><input type="file" name="tzphoto" id="tz_photo" multiple="multiple" onchange="previewMultipleImage(this,'tiezi_img_show')"/><br /><br />
		
		<label>帖子简介:</label>
		<div>
			<script type="text/javascript" id="editor" style="width:800px;height:400px;"></script>
		</div><br/><br/>
		<a href="javascript:addTieZi()" class="easyui-linkbutton" data-option="iconCls:'icon-add'">添加</a>
	</form>
	
	<div style="float:right;width:380px; margin-right:20px;">
		<fieldset id="tiezi_img_show">
			<legend>图片预览</legend>
		</fieldset>
	</div>
</div>

<div id="tiezi_update_tiezi" class="easyui-dialog" title="修改帖子信息"  data-options="fit:true,iconCls:'icon-add',resizable:true,modal:true,closed:true">
	<form style="padding:20px;float:left;display:inline-block;">
		<label>用户id:</label><input type="text" name="yhid" id="tz_updateyhid" class="myinput"><br/><br/>
	    <label>论坛名字:</label><input type="text" name="ltname" id="tz_updateltname" class=" myinput" /><br/><br/>
	    <label>帖子标题:</label><input type="text" name="tzname" id="tz_updatetzname" class="myinput"><br/><br/>
	    <label>帖子摘要:</label><input type="text" name="tzzy" id="tz_updatetzzy" class=" myinput" /><br/><br/>
	    <label>发表时间:</label><input name="tztime" id="tz_updatetztime" class="easyui-datetimebox myinput" required/><br /><br />
		<label>帖子权重:</label><input type="text" name="weight" id="tz_updateweight" class="myinput"/><br/><br/>
		<label>帖子点击量:</label><input type="text" name="tzclick" id="tz_updatetzclick" class="myinput" value="0"/><br/><br/>
		<label>帖子图片:</label><input type="file" name="tzphoto" id="tz_updatephoto" multiple="multiple" onchange="previewMultipleImage(this,'tiezi_img_updateshow')"/><br /><br />
		<label>帖子简介:</label>
		<div>
			<script type="text/javascript" id="updateUE" style="width:800px;height:400px;"></script>
		</div><br/><br/>
		<a href="javascript:updateTieZi()" class="easyui-linkbutton" data-option="iconCls:'icon-add'">修改</a>
	</form>
	
	<div style="float:right;width:380px; margin-right:20px;">
		<fieldset id="tiezi_img_updateshow">
			<legend>图片预览</legend>
		</fieldset>
	</div>
</div>
<script>
	var uezxl1=UE.getEditor("editor");
	var updateUE=UE.getEditor("updateUE");
	
	function addTieZi(){
		var yhid=$("#tz_yhid").val();
		var ltname=$("#tz_ltname").val();
		var tzname=$("#tz_tzname").val();
		var tzzy=$("#tz_tzzy").val();
		var tztime=$("#tz_tztime").datebox('getValue');
		var weight=$("#tz_weight").val();
		var tzclick=$("#tz_tzclick").val();
		var tztext=uezxl1.getContent();
		
		$.ajaxFileUpload({
			url:"../tieZiServlet?op=addTieZiInfo",
			secureuri:false,
			fileElementId:"tz_photo",
			dataType:"json",
			data:{yhid:yhid,ltname:ltname,tzname:tzname,tzzy:tzzy,tztime:tztime,weight:weight,tzclick:tzclick,tztext:tztext},
			success:function(data,status){
				if(parseInt($.trim(data))==1){
					$("#tiezi_add_tiezi").dialog("close");
					$("#tiezi_info").datagrid("reload");
					$.messager.show({title:'成功提示',msg:'作品信息添加成功...',timeout:2000,showType:'slide'});
					$("#tz_yhid").val("");
					$("#tz_ltname").val("");
					$("#tz_tzname").val("");
					$("#tz_tzzy").val("");
					$("#tz_tztime").datebox('setValue','');;
					$("#tz_weight").val("");
					$("#tz_tzclick").val("");
					uezxl1.setContent("");
				}else{
					$.messager.alert("失败提示","作品信息添加失败...","error");

				}
			}
		});
	}
	
	function updateTieZi(){
		var tid=tiezi.tid;
		 var yhid=$("#tz_updateyhid").val();
		var ltname=$("#tz_updateltname").val();
		var tzname=$("#tz_updatetzname").val();
		var tzzy=$("#tz_updatetzzy").val();
		var tztime=$("#tz_updatetztime").datebox('getValue');;
		var weight=$("#tz_updateweight").val();
		var tzclick=$("#tz_updatetzclick").val();
		var tztext=updateUE.getContent();
		console.info($("#tz_updatetzphoto").val());
		$.ajaxFileUpload({
			url:"../tieZiServlet?op=updateTieZiInfo",
			secureuri:false,
			fileElementId:"tz_updatephoto",
			dataType:"json",
			data:{tid:tid,yhid:yhid,ltname:ltname,tzname:tzname,tzzy:tzzy,tztime:tztime,weight:weight,tzclick:tzclick,tztext:tztext},
			success:function(data,status){
				if(parseInt($.trim(data))==1){
					$("#tiezi_update_tiezi").dialog("close");
					$("#tiezi_info").datagrid("reload");
					$.messager.show({title:'成功提示',msg:'作品信息添加成功...',timeout:2000,showType:'slide'});
				}else{
					$.messager.alert("失败提示","作品信息添加失败...","error");

				}
			}
		});
	}
	
</script>