<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<table id="yonghuzc_info"></table>
<script>
	function addYongHuZCInfo(){
		var yhzctime;
		var yhname=$("#yhname_add").val();
		var yhemail=$("#yhemail_add").val();
		var yhpwd=$("#yhpwd_add").val();
		var yhzcstatus=$("#yhzcstatus_add").val();
		
		$.post("yongHuZC_addYongHuZCInfo.action",{yhzctime:yhzctime,yhname:yhname,yhemail:yhemail,yhpwd:yhpwd,yhzcstatus:yhzcstatus},function(data){
			var result=data.result;
			if(result==1){
				$.messager.show({title:'成功提示',msg:'用户注册信息添加成功...',timeout:2000,showType:'slide'});
				$("#yonghuzc_add_yonghuzcInfo").dialog("close");
				$("#yonghuzc_info").datagrid("reload");
				$("#yhname_add").val("");
				$("#yhemail_add").val("");
				$("#yhpwd_add").val("");
				$("#yhzcstatus_add").val("");
			}else{
				$.messager.alert("失败提示","用户注册信息添加失败...","error");
			}	
		});
	}
	
	function updateYongHuZCInfo(){
		var yhzcid=$("#yhzcid_update").val();
		var yhname=$("#yhname_update").val();
		var yhemail=$("#yhemail_update").val();
		var yhpwd=$("#yhpwd_update").val();
		var yhzcstatus=$("#yhzcstatus_update").val();
		$.post("yongHuZC_updateYongHuZCInfo.action",{yhzcid:yhzcid,yhname:yhname,yhemail:yhemail,yhpwd:yhpwd,yhzcstatus:yhzcstatus},function(data){
			var result=data.result;
			if(result==1){
				$.messager.show({title:'成功提示',msg:'用户注册信息修改成功...',timeout:2000,showType:'slide'});
				$("#yonghuzc_update_yonghuzcInfo").dialog("close");
				$("#yonghuzc_info").datagrid("reload");
				$("#yhname_update").val("");
				$("#yhname_update").val("");
				$("#yhemail_update").val("");
				$("#yhpwd_update").val("");
			}else{
				$.messager.alert("失败提示","用户注册信息修改失败...","error");
			}
		});
	}

	$(function(){
		var datagrid;
		var editRow=undefined;
		var op;
		var flag;
		var yonghuzcstatusObj=[{yonghuid:0,yonghuname:'不可用'},{yonghuid:1,yonghuname:'可用'}];
		datagrid=$('#yonghuzc_info').datagrid({   
		    url:'yongHuZC_getPageYongHuZCInfo.action', 
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
		        {field:'yhname',title:'用户姓名',width:100,align:'center',editor:{type:"text",options:{required:true}}},   
		        {field:'yhemail',title:'用户邮箱',width:100,align:'center',editor:{type:"text",options:{required:true}}},  
		        {field:'yhpwd',title:'用户密码',width:100,align:'center',editor:{type:"text",options:{required:true}}},
		        {field:'yhzctime',title:'用户注册时间',width:100,align:'center'},
		        {field:'yhzcstatus',title:'用户状态',width:100,align:'center',editor:{type:"combobox",options:{
		        	required:true,valueField:'yonghuid',textField:'yonghuname',data:yonghuzcstatusObj}},
		        	formatter:function(value,row,index){
		        		for(var i=0;i<yonghuzcstatusObj.length;i++){
		        			if(yonghuzcstatusObj[i].yonghuid==value){
		        				return yonghuzcstatusObj[i].yonghuname;
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
		        	$("#yonghuzc_add_yonghuzcInfo").dialog("open");
		        }
		    },{
		    	text:"修改",
		    	iconCls:'icon-edit',
		    	handler:function(){
		    		//获取要修改的行
		    		var rows=datagrid.datagrid("getChecked")[0];
		    		if(rows==undefined){
		    			$.messager.show({title:'温馨提示',msg:'请选择您要修改的用户的注册信息！',timeout:2000,showType:'slide'});
		    		}else{
		    			op="updateYongHuZCInfo";
		    			flag="修改";
		    			if(editRow!=undefined){ //说明有行正在被编辑，则还原当前的修改
		    				datagrid.datagrid("rejectChanges");  //回滚自创建以来或上次调用AcceptChanges，所有的变化数据
			        		datagrid.datagrid("endEdit",editRow); //关闭当前正在编辑的行
			        		editRow=undefined;
		    			}else{
		    				//获取当前选择行的索引
							var row=datagrid.datagrid("getChecked");
		    				var yhzcid=row[0].yhzcid;
		    				$.post("yongHuZC_getYongHuZCById.action",{yhzcid:yhzcid},function(data){
		    					var yh=data.yongHuZC;
		    					$("#yhzcid_update").val(yh.yhzcid);
		    					$("#yhname_update").val(yh.yhname);
		    					$("#yhpwd_update").val(yh.yhpwd);
		    					$("#yhemail_update").val(yh.yhemail);
		    					$("#yhzcstatus_update").val(yh.yhzcstatus);
		    				},'json');
							$("#yonghuzc_update_yonghuzcInfo").dialog("open");
		    			}
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
		    					var yhzcids="";
		    					for(var i=0;i<rows.length-1;i++){
		    						yhzcids+=rows[i].yhzcid+",";
		    					}
		    					yhzcids+=rows[i].yhzcid;
		    					//将要删除yhzcid 发送到服务器
		    					$.post("yongHuZC_delYongHuZCById.action",{yhzcids:yhzcids},function(data){
				    				var result=data.delId;
		    						if(result>0){ //删除成功
				    					$.messager.show({
				    						title:'删除提示',
				    						msg:'用户注册信息删除成功..',
				    						timeout:2000, //时间
				    						showType:'slide'
				    					});
				    					datagrid.datagrid("reload");  //重新加载数据一次
				    				}else{ //添加失败
				    					$.messager.alert('失败提示！','用户注册信息删除失败...','error');
				    				}
				    			});
		    				}
		    			});


		    		}
		    	}
		    },{
		    	text:"保存",
		    	iconCls:'icon-save',
		    	handler:function(){
		    		datagrid.datagrid("endEdit",editRow); //关闭当前正在编辑的行
		    		
		    		//获取当前被修改的数据
		    		var rows=datagrid.datagrid("getChanges")[0];
		    		if(rows==undefined){//说明当前没有被编辑
		    			datagrid.datagrid("rejectChanges");  //回滚自创建以来或上次调用AcceptChanges，所有的变化数据
		        		datagrid.datagrid("unselectAll"); //关闭当前正在编辑的行
		        		editRow=undefined;
		    		}else{
		    			rows["op"]=op;
		    			
		    			$.post("../yongHuServlet",rows,function(data){
		    				data=parseInt( $.trim(data) );
		    				if(data==1){ //添加成功
		    					$.messager.show({
		    						title:'成功提示',
		    						msg:'用户注册信息'+flag+'成功..',
		    						timeout:2000, //时间
		    						showType:'slide'
		    					});
		    				}else{ //添加失败
		    					$.messager.alert('失败提示！','用户注册信息'+flag+'失败...','error');
		    				}
		    				rows=null;
		    				datagrid.datagrid("reload");  //重新加载数据一次
		    				editRow=undefined;
		    				datagrid.datagrid("rejectChanges");  //回滚自创建以来或上次调用AcceptChanges，所有的变化数据
			        		datagrid.datagrid("unselectAll"); //关闭当前正在编辑的行
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
		width:150px;
		border:1px solid #f63;
	}
	label{
		padding-right:10px;
	}
</style>


<div id="yonghuzc_add_yonghuzcInfo" class="easyui-dialog" title="添加用户注册信息" data-options="fit:true,iconCls:'icon-add',resizable:true,modal:true,closed:true">
	<form action="" style="padding:20px;float:left;display:inline-block;">
		<input type="hidden" name="yhzctime" id="yhzctime_add"/> 
		<label>用户姓名：</label><input type="text" id="yhname_add" name="yhname" class="myinput"/><br/><br/>
		<label>用户邮箱：</label><input type="text" id="yhemail_add" name="yhemail" class="easyui-validatebox" data-options="required:true,validType:'email'"/><br/><br/>
		<label>用户密码：</label><input type="text" id="yhpwd_add" name="yhpwd" class="myinput" /><br/><br/>
		<label>状态：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</label><input type="text" id="yhzcstatus_add" name="yhzcstatus" class="myinput" ><br/><br/>
		<a href="javascript:addYongHuZCInfo()" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加</a>	
	</form>
</div>

<div id="yonghuzc_update_yonghuzcInfo" class="easyui-dialog" title="修改用户注册信息" data-options="fit:true,iconCls:'icon-add',resizable:true,modal:true,closed:true">
	<form action="" style="padding:20px;float:left;display:inline-block;">
		<label>用户注册编号:
		</label><input type="text" id="yhzcid_update" name="yhzcid" class="myinput" readonly="readonly"/><br/><br/>
		<label>用户姓名：&nbsp;&nbsp;&nbsp;&nbsp;
		</label><input type="text" id="yhname_update" name="yhname" class="myinput"/><br/><br/>
		<label>用户邮箱：&nbsp;&nbsp;&nbsp;&nbsp;
		</label><input type="text" id="yhemail_update" name="yhemail" class="easyui-validatebox" data-options="required:true,validType:'email'"/><br/><br/>
		<label>用户密码：&nbsp;&nbsp;&nbsp;&nbsp;
		</label><input type="text" id="yhpwd_update" name="yhpwd" class="myinput" /><br/><br/>
		<label>状态：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</label><input type="text" id="yhzcstatus_update" name="yhzcstatus" class="myinput" ><br/><br/>
		<a href="javascript:updateYongHuZCInfo()" class="easyui-linkbutton" data-options="iconCls:'icon-add'">修改</a>	
	</form>
</div>
