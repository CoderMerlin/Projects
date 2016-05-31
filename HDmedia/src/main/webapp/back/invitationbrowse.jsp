<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<table id="huitie_info"  data-options="fit:true"></table>
<script>
/**
 * 发个
 */
		var op;
		var flag;
		var statusObj=[{sid:0,sname:'不可用'},{sid:1,sname:'可用'}];
		var ht;
	$(function(){
		var mydatagrid;
		var editRow=undefined;
		mydatagrid=$('#huitie_info').datagrid({
			url:"huiTie_getPageHuiTieInfo",
			fitColumns:true,
		    striped:true,
		    loadMsg:"数据加载中...",
		    pagination:true,
		    rownumbers:true,
		    sortName:'htid',
		    sortOrder:'asc',
		    remoteSort:false,
		    columns:[[
		    	{field:'htids',title:'回帖编号',width:100,align:'center',checkbox:true},   
		        {field:'htid',title:'回帖编号',width:100,align:'center',sortable:true},   
		        {field:'tzname',title:'帖子名称',width:100,align:'center',editor:{type:"text",options:{required:true}}},  
		        {field:'yhzsname',title:'用户名',width:100,align:'center',editor:{type:"text",options:{required:true}}},
		        {field:'httime',title:'回帖时间',width:100,align:'center',editor:{type:"text",options:{required:true}}},
		        {field:'htstatus',title:'状态',width:100,align:'center',editor:{type:"combobox",options:{
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
		    		$("#huitie_add_huitie").dialog("open");
		    	}
		    },{
		    	text:"修改",
		    	iconCls:'icon-edit',
		    	handler:function(){
		    		var rows=mydatagrid.datagrid("getChecked")[0];
		    		if(rows==undefined){
		    			$.messager.show({title:'温馨提示',msg:'您没有选中数据或选中多行数据,请重新选择...',timeout:2000,showType:'slide'});
		    		}else{
						htid=rows.htid;
						$.post("huiTie_findHuiTieByHtid",{op:"findHuiTieByHtid",htid:htid},function(data){
							ht=data.rows[0];
							console.info(data.rows);
							$("#ht_updateyhid").val(ht.yhid);
							$("#ht_updatetzname").val(ht.tzname);
							$("#ht_updatehttime").datebox('setValue',ht.httime);
							$("#ht_updatehtdianzan").val(ht.htdianzan);
							updateUE.setContent(ht.httext);
							
							$("#huitie_update_huitie").dialog("open");
						},"json");
		    		}
		    	}
		    },{
		    	text:"删除",
		    	iconCls:'icon-remove',
		    	handler:function(){
		    		var rows=mydatagrid.datagrid("getChecked");
		    		
		    		if(rows.length<=0){
		    			$.messager.show({title:'温馨提示',msg:'请选择您要删除的数据...',timeout:2000,showType:'slide'});
		    		}else{
		    			$.messager.confirm('信息确认','您确定要删除选中的数据吗？',function(r){
		    				if(r){
		    					var htids="";
		    					for(var i=0;i<rows.length-1;i++){
		    						htids+=rows[i].htid+",";
		    					}
		    					htids+=rows[i].htid;
		    					
		    					$.post("huiTie_delHuiTieInfo",{op:"delHuiTieInfo",htids:htids},function(data){
		    						if(data.total>0){
		    							$.messager.show({title:'成功提示',msg:'回帖信息删除成功...',timeout:2000,showType:'slide'});
		    							mydatagrid.datagrid("reload");
		    						}else{
		    							$.messager.alert('失败提示','回帖信息删除失败...');
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
		    		mydatagrid.datagrid("rejectChanges");  //回滚自创建以来或上次调用AcceptChanges，所有的变化数据
		    		mydatagrid.datagrid("endEdit",editRow);
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

<div id="huitie_add_huitie" class="easyui-dialog" title="添加回帖信息"  data-options="fit:true,iconCls:'icon-add',resizable:true,modal:true,closed:true">
	<form style="padding:20px;float:left;display:inline-block;">
		<label>用户id:</label><input type="text" name="yhid" id="ht_yhid" class="myinput"><br/><br/>
	    <label>帖子标题:</label><input type="text" name="tzname" id="ht_tzname" class="myinput"><br/><br/>
	    <label>发表时间:</label><input name="tztime" id="ht_httime" class="easyui-datetimebox myinput" required/><br /><br />
		<label>回帖点赞:</label><input type="text" name="htdianzan" id="ht_htdianzan" class="myinput" value="0"/><br/><br/>
		<label>回帖内容:</label>
		<div>
			<script type="text/javascript" id="editor" style="width:800px;height:400px;"></script>
		</div><br/><br/>
		<a href="javascript:addHuiTie()" class="easyui-linkbutton" data-option="iconCls:'icon-add'">添加</a>
	</form>
</div>

<div id="huitie_update_huitie" class="easyui-dialog" title="修改回帖信息"  data-options="fit:true,iconCls:'icon-add',resizable:true,modal:true,closed:true">
	<form style="padding:20px;float:left;display:inline-block;">
		<label>用户id:</label><input type="text" name="yhid" id="ht_updateyhid" class="myinput"><br/><br/>
	    <label>帖子标题:</label><input type="text" name="tzname" id="ht_updatetzname" class="myinput"><br/><br/>
	    <label>发表时间:</label><input name="tztime" id="ht_updatehttime" class="easyui-datetimebox myinput" required/><br /><br />
		<label>回帖点赞:</label><input type="text" name="htdianzan" id="ht_updatehtdianzan" class="myinput" value="0"/><br/><br/>
		<label>回帖内容:</label>
		<div>
			<script type="text/javascript" id="updateeditor" style="width:800px;height:400px;"></script>
		</div><br/><br/>
		<a href="javascript:updateHuiTie()" class="easyui-linkbutton" data-option="iconCls:'icon-add'">修改</a>
	</form>
</div>

<script>
	var ue=UE.getEditor("editor");
	var updateUE=UE.getEditor("updateeditor");
	
	function addHuiTie(){
		var yhid=$("#ht_yhid").val();
		var tzname=$("#ht_tzname").val();
		var httime=$("#ht_httime").datebox('getValue');
		var htdianzan=$("#ht_htdianzan").val();
		var httext=ue.getContent();
		$.ajaxFileUpload({
			url:"huiTie_addHuiTieInfo",
			secureuri:false,
			dataType:"json",
			data:{yhid:yhid,tzname:tzname,httime:httime,htdianzan:htdianzan,httext:httext},
			success:function(data,status){
				if(data.total==1){
					$("#huitie_add_huitie").dialog("close");
					$("#huitie_info").datagrid("reload");
					$.messager.show({title:'成功提示',msg:'作品信息添加成功...',timeout:2000,showType:'slide'});
					$("#ht_yhid").val("");
					$("#ht_tzname").val("");
					$("#ht_htdianzan").val("");
					$("#ht_httime").datebox('setValue','');;
					ue.setContent("");
				}else{
					$.messager.alert("失败提示","作品信息添加失败...","error");

				}
			}
		});
	}
	
	function updateHuiTie(){
		var htid=ht.htid;
		var yhid=$("#ht_updateyhid").val();
		var tzname=$("#ht_updatetzname").val();
		var httime=$("#ht_updatehttime").datebox('getValue');;
		var htdianzan=$("#ht_updatehtdianzan").val();
		var httext=updateUE.getContent();
		$.ajaxFileUpload({
			url:"huiTie_updateHuiTieInfo",
			secureuri:false,
			dataType:"json",
			data:{htid:htid,yhid:yhid,tzname:tzname,httime:httime,htdianzan:htdianzan,httext:httext},
			success:function(data,status){
				if(data.total==1){
					$("#huitie_update_huitie").dialog("close");
					$("#huitie_info").datagrid("reload");
					$.messager.show({title:'成功提示',msg:'作品信息添加成功...',timeout:2000,showType:'slide'});
				}else{
					$.messager.alert("失败提示","作品信息添加失败...","error");

				}
			}
		});
	}
</script>