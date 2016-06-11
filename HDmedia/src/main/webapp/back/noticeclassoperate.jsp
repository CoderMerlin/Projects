<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<table id="luntan_info"  data-options="fit:true"></table>
<script>
var datagrid;
var ltid;
/**
 * 论坛对方省份
 */
	$(function(){
		var editRow=undefined;
		var op;
		var flag;
		var luntanstatusObj=[{luntanid:0,luntanstatus:'不可用'},{luntanid:1,luntanstatus:'可用'}];
		datagrid=$('#luntan_info').datagrid({   
		    url:'lunTan_showAll.action', 
		    fitColumns:true,
		    striped:true,
		    loadMsg:"数据加载中...",
		    pagination:true,
		    rownumbers:true,
		    sortName:'ltid',
		    sortOrder:'asc',
		    remoteSort:false,
		    columns:[[   
		        {field:'ltids',title:'论坛编号',width:100,align:'center',checkbox:true},   
		        {field:'ltid',title:'论坛编号',width:100,align:'center',sortable:true},
		        {field:'ltname',title:'论坛名称',width:100,align:'center',editor:{type:"text",options:{required:true}}},   
		        {field:'ltyl1',title:'论坛创建类型',width:100,align:'center',editor:{type:"text",options:{required:true}}},
		        {field:'lttime',title:'论坛创建时间',width:100,align:'center',editor:{type:"text",options:{required:true}}},
		        {field:'ltstatus',title:'论坛栏目状态',width:100,align:'center',editor:{type:"combobox",options:{
		        	required:true,valueField:'luntanid',textField:'luntanstatus',data:luntanstatusObj}},
		        	formatter:function(value,row,index){
		        		for(var i=0;i<luntanstatusObj.length;i++){
		        			if(luntanstatusObj[i].luntanid==value){
		        				return luntanstatusObj[i].luntanstatus;
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
		        	$("#luntan_add_luntan").dialog("open");
		        }
		    },{
		    	text:"修改",
		    	iconCls:'icon-edit',
		    	handler:function(){
		    		//获取要修改的行
		    		var rows=datagrid.datagrid("getChecked")[0];
		    		
		    		if(rows==undefined){
		    			$.messager.show({title:'温馨提示',msg:'请选择您要修改的论坛名称！',timeout:2000,showType:'slide'});
		    		}else{
		    			var ltid=rows.ltid;
		    			$.post("lunTan_findLunTanById",{ltid:ltid},function(data){
		    				console.info(data);
		    				luntan=data.rows[0];
		    				$("#ltid").val(luntan.ltid);
		    				$("#lt_upltname").val(luntan.ltname);
		    				$("#lt_uplttime").datebox('setValue',luntan.lttime);
		    				$("#lt_upltyl1").val(luntan.ltyl1);
		    				$("#lt_upltyl2").val("")
		    				
		    				var str="";
 							
 							if(luntan.ltyl2!=null){
 								var ltyl2=luntan.ltyl2.split(",");
 								for(var i=0;i<ltyl2.length;i++){
 									str+="<img src='upload/"+ltyl2[i]+"' width='100px' height='100px'>&nbsp;";
 								}
 							}
 							$("#luntan_upimg_show").html($(str));
		    				$("#luntan_update_luntan").dialog("open");
		    			});
		    		}
		    	}
		    },{
		    	text:"删除",
		    	iconCls:'icon-remove',
		    	handler:function(){
		    		//获取所有被选中的行
		    		var rows=datagrid.datagrid("getChecked");
		    		if(rows.length<=0){ //说明没有选中任意一行
		    			$.messager.show({
    						title:'温馨提示',
    						msg:'请选择您要删除的用户注册信息！',
    						timeout:2000, //时间
    						showType:'slide'
    					});
		    		}else{
		    			$.messager.confirm('信息确认', '您确定要删除所选中的用户注册信息吗？', function(r){
		    				if (r){
		    					var ltids="";
		    					for(var i=0;i<rows.length-1;i++){
		    						ltids+=rows[i].ltid+",";
		    					}
		    					ltids+=rows[i].ltid;
		    					//将要删除ltid 发送到服务器
		    					$.post("lunTan_delLunTanInfo",{op:"delLunTanInfo",ltids:ltids},function(data){
				    				if(data.total>0){ //删除成功
				    					$.messager.show({
				    						title:'删除提示',
				    						msg:'论坛栏目信息删除成功..',
				    						timeout:2000, //时间
				    						showType:'slide'
				    					});
				    					datagrid.datagrid("reload");  //重新加载数据一次
				    				}else{ //添加失败
				    					$.messager.alert('失败提示！','论坛栏目信息删除失败...','error');
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
<div id="luntan_add_luntan" class="easyui-dialog" title="添加帖子信息"  data-options="fit:true,iconCls:'icon-add',resizable:true,modal:true,closed:true">
	<form id="add_luntan" method="post" action="luntan_addluntanInfo" style="padding:20px;float:left;display:inline-block;" enctype="multipart/form-data" >
	    <label>论坛名字:</label><input type="text" name="ltname" id="lt_ltname" class=" myinput" /><br/><br/>
	    <label>创建时间:</label><input name="lttime" id="lt_lttime" class="easyui-datetimebox myinput"/><br /><br />
		<label>论坛类型:</label><input type="text" name="ltyl1" id="lt_ltyl1" class="myinput"/><br/><br/>
		<label>论坛图片:</label ><input type="file" name="upload" id="ltyl2" multiple="multiple" onchange="previewMultipleImage(this,'luntan_img_show')"/><br /><br />
		<div>
		</div><br/><br/>
 		<a href="javascript:addluntan()" class="easyui-linkbutton" data-option="iconCls:'icon-add'">添加</a>
	</form>
	
	<div style="margin-left:30px;width:380px; ">
		<fieldset id="luntan_img_show">
			<legend>图片预览</legend>
		</fieldset>
	</div>
</div>


<div id="luntan_update_luntan" class="easyui-dialog" title="更新论坛信息"  data-options="fit:true,iconCls:'icon-add',resizable:true,modal:true,closed:true">
	<form id="update_luntan" method="post" action="luntan_updateluntanInfo" style="padding:20px;float:left;display:inline-block;" enctype="multipart/form-data" >
		<input type="text" name="ltid" id="ltid" style="display:none"/>
	    <label>论坛名字:</label><input type="text" name="ltname" id="lt_upltname" class=" myinput" /><br/><br/>
	    <label>创建时间:</label><input name="lttime" id="lt_uplttime" class="easyui-datetimebox myinput" required/><br /><br />
		<label>论坛类型:</label><input type="text" name="ltyl1" id="lt_upltyl1" class="myinput"/><br/><br/>
		<label>论坛图片:</label ><input type="file" name="upload" id="upltyl2" multiple="multiple" onchange="previewMultipleImage(this,'luntan_upimg_show')"/><br /><br />
		<div>
		</div><br/><br/>
 		<a href="javascript:updateluntan()" class="easyui-linkbutton" data-option="iconCls:'icon-add'">修改 </a>
	</form>
	
	<div style="margin-left:30px;width:380px; ">
		<fieldset id="luntan_upimg_show">
			<legend>图片预览</legend>
		</fieldset>
	</div>
	
	
</div>
<script>
function addluntan(){
	var formData=new FormData($("#add_luntan")[0]);
	$.ajax({
		type:"post",
		url:"lunTan_addLunTanInfo",
		processData:false,
		contentType:false,
		async:false,
		cache:false,
		data:formData,
		success:function(data){
			if(data.total=="1"){
				$("#luntan_add_luntan").dialog("close");
				$("#luntan_info").datagrid("reload");
				$.messager.show({title:'成功提示',msg:'论坛信息添加成功...',timeout:2000,showType:'slide'});
				$("#lt_ltname").val("");
				$("#lt_lttime").datebox('setValue','');
				$("#lt_ltyl1").val("");
				$("#lt_ltyl2").val("")
			}else{
				$.messager.alert("失败提示","论坛信息添加失败...","error");
			}
		}
	});
}

function updateluntan(){
	var formData=new FormData($("#update_luntan")[0]);
	$.ajax({
		type:"post",
		url:"lunTan_updateLunTanInfo",
		processData:false,
		contentType:false,
		async:false,
		cache:false,
		data:formData,
		success:function(data){
			if(data.total=="1"){
				$("#luntan_update_luntan").dialog("close");
				$("#luntan_info").datagrid("reload");
				$.messager.show({title:'成功提示',msg:'论坛信息修改成功...',timeout:2000,showType:'slide'});
			}else{
				$.messager.alert("失败提示","论坛信息修改失败...","error");
			}
		}
	});
}
</script>
