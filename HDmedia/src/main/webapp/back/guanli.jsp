<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<table id="GuanLi_info" data-options="fit:true"></table>
<script>

function updateAdminInfo(){
	var glid=$("#glid_update").val();
	var glname=$("#glname_update").val();
	var glsex=$("#glsex_update").val();
	var glpwd=$("#glpwd_update").val();
	var glemail=$("#glemail_update").val();
	var glphone=$("#glphone_update").val();
	var glstatus=$("#glstatus_update").val();
	
	$.post("guanLi_updateGuanLi.action",{glid:glid,glname:glname,glsex:glsex,glpwd:glpwd,glemail:glemail,glphone:glphone,glstatus:glstatus},function(data){
			var result=data.result;
			if(result==1){
				$.messager.show({title:'成功提示',msg:'管理员信息修改成功...',timeout:2000,showType:'slide'});
				$("#admin_update_adminInfo").dialog("close");
				$("#GuanLi_info").datagrid("reload");
				$("#glname_update").val("");
				$("#glsex_update").val("");
				$("#glpwd_update").val("");
				$("#glemail_update").val("");
				$("#glphone_update").val("");
				$("#glstatus_update").val("");
			}else{
				$.messager.alert("失败提示","管理员信息修改失败...","error");
			}	
	});
}


function addAdminInfo(){
	var glname=$("#glname_add").val();
	var glsex=$("#glsex_add").val();
	var glpwd=$("#glpwd_add").val();
	var glemail=$("#glemail_add").val();
	var glphone=$("#glphone_add").val();
	var glzsname=$("#glzsname_add").val();
	var glindentity=$("#glindentity_add").val();
	var glstatus=$("#glstatus_add").val();
	
	$.post("guanLi_addGuanLi.action",{glname:glname,glsex:glsex,glpwd:glpwd,glemail:glemail,glphone:glphone,glzsname:glzsname,glindentity:glindentity,glstatus:glstatus},function(data){
			var result=data.result;
			if(result==1){
				$.messager.show({title:'成功提示',msg:'管理员信息添加成功...',timeout:2000,showType:'slide'});
				$("#admin_add_adminInfo").dialog("close");
				$("#GuanLi_info").datagrid("reload");
				$("#glname_add").val("");
				$("#glsex_add").val("");
				$("#glpwd_add").val("");
				$("#glemail_add").val("");
				$("#glphone_add").val("");
				$("#glzsname_add").val("");
				$("#glindentity_add").val("");
				$("#glstatus_add").val("");
			}else{
				$.messager.alert("失败提示","管理员信息添加失败...","error");
			}	
	});
}
$(function(){
	var datagrid;
	var editRow=undefined;
	var op;
	var flag;
	var statusObj=[{sid:0,sname:'不可用'},{sid:1,sname:'可用'}];
	
	datagrid=$('#GuanLi_info').datagrid({   
	    url:'guanLi_getAllGuanLi.action',
	    queryParams:{op:"getAllGuanLi"},
	    fitColumns:true,
	    striped:true,
	    loadMsg:"数据加载中...",
	    pagination:true,
	    rownumbers:true,
	    sortName:'glid',
	    sortOrder:'asc',
	    remoteSort:false,
	    columns:[[ 
			{field:'glids',title:'管理员编号',width:100,align:'center',checkbox:true}, 
	        {field:'glid',title:'管理员编号',width:100,align:'center',sortable:true},   
	        {field:'glname',title:'管理员姓名',width:100,align:'center',editor:{type:"text",options:{required:true}}},
	        {field:'glpwd',title:'管理员密码',width:100,align:'center',editor:{type:"text",options:{required:true}}},
	        {field:'glsex',title:'性别',width:100,align:'center',editor:{type:"text",options:{required:true}}},
	        {field:'glemail',title:'邮箱',width:100,align:'center',editor:{type:"text",options:{required:true}}},
	        {field:'glphone',title:'电话',width:100,align:'center',editor:{type:"text",options:{required:true}}},
	        {field:'glzsname',title:'真实姓名',width:100,align:'center',editor:{type:"text",options:{required:true}}},
	        {field:'glindentity',title:'身份证',width:100,align:'center',editor:{type:"text",options:{required:true}}},
	        {field:'glzhtime',title:'最后登陆时间',width:100,align:'center'},
	        {field:'glstatus',title:'状态',width:100,align:'center',editor:{type:"combobox",options:{
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
				$("#admin_add_adminInfo").dialog("open");
			}
		},{
	    	text:"修改",
			iconCls:'icon-edit',
			handler:function(){
				//获取要修改的行
				var rows=datagrid.datagrid("getChecked")[0];
				if(rows==undefined){
					$.messager.show({title:'温馨提示',msg:'请选择您要修改的的数据...',timeout:2000,showType:'slide'});	
				}else{
					op="updateGuanLiInfo";
					flag="修改";
					if(editRow!=undefined){  //说明有行正在被编辑，则还原当前的修改
						datagrid.datagrid("rejectChanges"); //回滚自创建以来或自上次调用AcceptChanges，所有的变化数据。
						datagrid.datagrid("endEdit",editRow); //关闭当前正在编辑的行
						editRow=undefined;
					}else{ 
						//获取当前选择行的索引
						var row=datagrid.datagrid("getChecked");
	    				var glid=row[0].glid;
	    				$.post("guanLi_getGuanLiById.action",{op:"getGuanLiById",glid:glid},function(data){
	    					var gl=data.guanLi;
	    					$("#glid_update").val(gl.glid);
	    					$("#glname_update").val(gl.glname);
	    					$("#glpwd_update").val(gl.glpwd);
	    					$("#glsex_update").val(gl.glsex);
	    					$("#glemail_update").val(gl.glemail);
	    					$("#glphone_update").val(gl.glphone);
	    					$("#glzsname_update").val(gl.glzsname);
	    					$("#glindentity_update").val(gl.glindentity);
	    					$("#glzhtime_update").val(gl.glzhtime);
	    					$("#glstatus_update").val(gl.glstatus);
	    				},'json');
						$("#admin_update_adminInfo").dialog("open");
					}
				}
			}
		},{
	    	text:"删除",
			iconCls:'icon-remove',
			handler:function(){
				//获取所有被选中的行
				var rows=datagrid.datagrid("getChecked");
				if(rows.length<=0){ //没有选择任何一行
					$.messager.show({
						title:'温馨提示',
						msg:'请选择您要删除的数据...',
						timeout:2000,
						showType:'slide'
					});
				}else{
					$.messager.confirm('信息确认', '您确定更要删除选中的数据吗?', function(r){
						if (r){
							var glids="";
							for(var i=0;i<rows.length-1;i++){
								glids+=rows[i].glid+",";
							}
							glids+=rows[i].glid;
							//将要删除glid发送到服务器
							$.post("guanLi_deleteGuanLiById.action",{glids:glids},function(data){
								var del=data.delId;
								if(del>0){ //删除成功
									$.messager.show({
										title:'删除提示',
										msg:'管理员信息删除成功...',
										timeout:2000,
										showType:'slide'
									});
									datagrid.datagrid("reload"); //重新加载数据一次
								}else{
									$.messager.alert('失败提示','管理员信息删除失败...','error');
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
				if(rows==undefined){
					datagrid.datagrid("rejectChanges"); //回滚自创建以来或自上次调用AcceptChanges，所有的变化数据。
					datagrid.datagrid("unselectAll");
					editRow=undefined;
				}else{
					rows["op"]=op;
					
					$.post("../guanliServlet",rows,function(data){
						data=parseInt( $.trim(data));
						if(data==1){ //添加成功
							$.messager.show({
								title:'成功提示',
								msg:'管理员信息'+flag+'成功...',
								timeout:2000,
								showType:'slide'
							});
						}else{
							$.messager.alert('失败提示','管理员信息'+flag+'失败...','error');
						}
						rows=null;
						datagrid.datagrid("reload"); //重新加载数据一次
						editRow=undefined;
						datagrid.datagrid("rejectChanges"); //回滚自创建以来或自上次调用AcceptChanges，所有的变化数据。
						datagrid.datagrid("unselectAll");
					});
				}
			}
		},{
			text:'撤销',
			iconCls:'icon-redo',
			handler:function(){
				datagrid.datagrid("rejectChanges"); //回滚自创建以来或自上次调用AcceptChanges，所有的变化数据。
				datagrid.datagrid("endEdit",editRow);
				editRow=undefined;
			}
		},{
			text:'导出',
			iconCls:'icon-redo',
			handler:function(){
				//获取要导出的行
				var rows=datagrid.datagrid("getChecked");
				if(rows==undefined){
					$.messager.show({title:'温馨提示',msg:'请选择您要导出的数据...',timeout:2000,showType:'slide'});	
				}else{
					$.messager.confirm('信息确认', '您确定导出选中的数据吗?', function(r){
						if (r){
							var glids="";
							for(var i=0;i<rows.length-1;i++){
								glids+=rows[i].glid+",";
							}
							glids+=rows[i].glid;
							var numb=rows.length-1;
							//获取当前选择的行索引
							$.post("../guanliServlet",{op:"oracleGlInfoToExcel",glids:glids,numb:numb},function(data){
								if(data==1){ //添加成功
									$.messager.show({
										title:'导出提示',
										msg:'管理员信息导出成功...',
										timeout:2000,
										showType:'slide'
									});
								}else{
									$.messager.alert('失败提示','管理员信息导出失败...','error');
								}
							});
						}
					});
				}
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
	textarea{
		resize:none;
	}
</style>

<div id="admin_add_adminInfo" class="easyui-dialog" title="添加管理员信息" data-options="fit:true,iconCls:'icon-add',resizable:true,modal:true,closed:true">
	<form action="" style="padding:20px;float:left;display:inline-block;">
		<input type="hidden" name="glid" id="glid_add"/> 
		<label>管理员姓名：</label><input type="text" id="glname_add" name="glname" class="myinput"/>&nbsp;&nbsp;&nbsp;
		<label>性别：</label><input type="text"  id="glsex_add" name="glsex" class="myinput"/>&nbsp;&nbsp;&nbsp;
		<label>真实姓名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</label><input type="text" id="glzsname_add"  name="glzsname" class="myinput" /><br/><br/>
		
		<label>管理员密码：</label><input type="text" id="glpwd_add" name="glpwd" class="myinput" />&nbsp;&nbsp;&nbsp;
		<label>邮箱：</label><input type="text"  id="glemail_add" name="glemail" class="easyui-validatebox" data-options="required:true,validType:'email'"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<label>身份证码：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</label><input type="text"  id="glindentity_add" name="glindentity" class="myinput" /><br/><br/>
		
		<label>状态：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</label><input type="combobox" id="glstatus_add" name="glstatus" class="myinput"/>&nbsp;&nbsp;&nbsp;
		<label>电话：</label><input type="text" id="glphone_add" name="glphone" class="myinput" ><br/><br/>
		<input type="hidden" name="glzhtime" id="glzhtime_update"/>
		<a href="javascript:addAdminInfo()" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加</a>	
	</form>
</div>

<div id="admin_update_adminInfo" class="easyui-dialog" title="修改管理员信息" data-options="fit:true,iconCls:'icon-add',resizable:true,modal:true,closed:true">
	<form action="" style="padding:20px;float:left;display:inline-block;">
		<label>管理员编号：</label><input type="text"  id="glid_update" name="glid" class="myinput" readonly="readonly">&nbsp;&nbsp;&nbsp;
		<label>性别：</label><input type="text"  id="glsex_update" name="glsex" class="myinput"/>&nbsp;&nbsp;&nbsp;
		<label>真实姓名：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</label><input type="text" id="glzsname_update"  name="glzsname" class="myinput" readonly="readonly"/><br/><br/>
		
		<label>管理员姓名：</label><input type="text" id="glname_update" name="glname" class="myinput"/>&nbsp;&nbsp;&nbsp;
		<label>邮箱：</label><input type="text"  id="glemail_update" name="glemail" class="easyui-validatebox" data-options="required:true,validType:'email'"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		<label>身份证码：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</label><input type="text"  id="glindentity_update" name="glindentity" class="myinput" readonly="readonly"/><br/><br/>
		
		<label>管理员密码：</label><input type="text" id="glpwd_update" name="glpwd" class="myinput" />&nbsp;&nbsp;&nbsp;
		<label>电话：</label><input type="text" id="glphone_update" name="glphone" class="myinput" >&nbsp;&nbsp;&nbsp;&nbsp;
		<label>状态：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</label><input type="text" id="glstatus_update" name="glstatus" class="myinput"/><br/><br/><br/>
		<a href="javascript:updateAdminInfo()" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改</a>	
	</form>
</div>

