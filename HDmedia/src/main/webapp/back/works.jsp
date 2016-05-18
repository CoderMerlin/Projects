<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<table id="works_info" data-options="fit:true"></table>

<script>
	var works;
	
	$(function(){
		var datagrid;
		var editRow=undefined;
		var op;
		var flag;
	 datagrid=$('#works_info').datagrid({
			url:"../worksServlet",
			queryParams:{op:"getPageWorksInfo"},
			fitColumns:true,
		    striped:true,
		    loadMsg:"数据加载中...",
		    pagination:true,
		    rownumbers:true,
		    sortName:'works_id',
		    sortOrder:'asc',
		    remoteSort:false,
		    columns:[[
		    	{field:'works_ids',title:'作品编号',width:100,align:'center',checkbox:true},   
		        {field:'works_id',title:'作品编号',width:100,align:'center'},   
		        {field:'works_name',title:'作品名',width:100,align:'center',editor:{type:"text",options:{required:true}}},  
		        {field:'author_name',title:'作者姓名',width:100,align:'center',editor:{type:"text",options:{required:true}}},
		        {field:'works_time',title:'发表时间',width:100,align:'center',editor:{type:"text",options:{required:true}}},   
		        {field:'works_type',title:'作品类型',width:100,align:'center',editor:{type:"text",options:{required:true}}},
		        {field:'works_click',title:'作品点击量',width:100,align:'center',editor:{type:"text",options:{required:true}}},   
		        {field:'status',title:'状态',width:100,align:'center',editor:{type:"text",options:{required:true}}},
		        {field:'_operate',title:'操作',width:100,align:'center',
	        	formatter: function(value,rowData,index){
					return '<a class="icon-search1 icon-padding" href="javascript:showWorksDetails(\''+rowData.works_id+'\')">详细</a>';
				}
			}  		        
		    ]],
		    toolbar:[{
		    	text:"添加",
		    	iconCls:'icon-add',
		    	handler:function(){
		    	$("#works_add_worksInfo").dialog("open");
		    	}
		    },{
		    	text:"修改",
		    	iconCls:'icon-edit',
		    	handler:function(){
		    		var wrows=datagrid.datagrid("getChecked");
		    		if(wrows.length!=1){
		    			$.messager.show({title:'温馨提示',msg:'您已选中多行或未选择数据,请重选...',timeout:2000,showType:'slide'});
		    		}else{
		    			works=wrows[0];
		    			works_id=works.works_id;
		    			$.post("../worksServlet",{op:"findWorksInfoById",works_id:works_id},function(data){
		    				var workss=data.rows;
							$("#works_updatetype").val(workss.works_type);
							$("#works_updatename").val(workss.works_name);
							$("#author_updateauthorname").val(workss.author_name);
							$("#works_updatetime").datebox('setValue',workss.works_time);
							$("#works_updaclick").datebox('setValue',workss.works_click);
							$("#works_updateweight").val(workss.works_weight);
							
							var str="";
							var works_img=workss.works_img.split(",");
							for(var i=0;i<works_img.length;i++){
								str+="<img src='../"+works_img[i]+"' width='100px' height='100px'>&nbsp;";
							}
							$("#works_img_update").html($(str));
							
							upue.setContent(workss.works_details);
							$("#works_update_worksInfo").dialog("open");
			
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
		    					var works_ids="";
		    					for(var i=0;i<rows.length-1;i++){
		    						works_ids+=rows[i].works_id+",";
		    					}
		    					works_ids+=rows[i].works_id;
		    					
		    					$.post("../worksServlet",{op:"delWorksInfo",works_ids:works_ids},function(data){
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
	
	$.post("../worksServlet",{op:"getAllWorksType"},function(data){
		var obj=$("#works_selecttype");
		var obj1=$("#works_updateselecttype");
		var opt;
		$.each(data.zplx,function(index,item){
			opt="<option value=' "+item.works_type+" '>"+item.works_type+"</option>";
			obj.append($(opt));
			obj1.append($(opt));
		});
	},"json");	
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
<div id="works_add_worksInfo" class="easyui-dialog" title="添加作品" data-options="fit:true,iconCls:'icon-add',resizable:true,modal:true,closed:true">
	<form action="" style="padding:20px;float:left;display:inline-block;">
		<label>作品类型</label><input type="works_type" name="title" id="works_workstype" class="myinput"/>
		<select name="workstype" id="works_selecttype" class="myinput" onclick="getvalue()" >
		
		</select><br /><br />
		<label>作品名称:</label><input type="text" name="works_name" id="works_worksname" class="myinput"/><br /><br />
		<label>作者名称:</label><input type="text" name="author_name" id="author_authorname" class="myinput"/><br /><br />
		<label>发表时间:</label><input name="works_time" id="works_workstime" class="easyui-datebox myinput" required/><br /><br />
		<label>作品图片:</label><input type="file" name="works_img" id="works_worksimg" multiple="multiple" onchange="previewMultipleImage(this,'works_img_show')"/><br /><br />
		<label>作品权重:</label><input class="easyui-numberbox myinput" name="works_weight" id="works_worksweight" value="0"/><br /><br />
		<label>作品内容:</label>
		<div>
			<script id="editor11" type="text/plain" style="width:800px;height:400px;"></script>
		</div><br/><br/>
		<a href="javascript:addWorksInfo()" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加</a>
	</form>
	<div style="float:right;width:380px;margin-right:20px;">
		<fieldset id="works_img_show">
			<legend>图片预览</legend>
		</fieldset>
	</div>
</div>


<div id="works_show_worksInfo" class="easyui-dialog" title="查看作品" data-options="fit:true,iconCls:'icon-add',resizable:true,modal:true,closed:true">
	<form action="" style="padding:20px;float:left;display:inline-block;">
		<label>作品类型</label><input type="works_type" name="title" id="works_showtype" class="myinput" readonly="readonly"/>
		<label>作品名称:</label><input type="text" name="works_name" id="works_showname" class="myinput"  readonly="readonly"/><br /><br />
		<label>作者名称:</label><input type="text" name="author_name" id="author_showauthorname" class="myinput" readonly="readonly"/><br /><br />
		<label>发表时间:</label><input type="text" name="works_time" id="works_showtime" class=" myinput" readonly="readonly"/><br /><br />
		<label>作品点击量:</label><input type="text"  name="works_click" id="works_showclick" readonly="readonly"/><br /><br />
		<label>作品权重:</label><input type="text"  class=" myinput" name="works_weight" id="works_showweight" readonly="readonly"/><br /><br />
		<label>作品内容:</label>
		<div id="works_showdetails">
			
		</div><br/><br/>
	</form>
	<div id="works_img_show_info" style="float:right;width:380px;margin-right:20px;">
		
	</div>
</div>

<div id="works_update_worksInfo" class="easyui-dialog" title="修改作品" data-options="fit:true,iconCls:'icon-add',resizable:true,modal:true,closed:true">
	<form action="" style="padding:20px;float:left;display:inline-block;">
		<label>作品类型</label><input type="works_type" name="title" id="works_updatetype" class="myinput"/>
		<select name="workstype" id="works_updateselecttype" class="myinput" onclick="getvalue()" >
		
		</select><br /><br />
		<label>作品名称:</label><input type="text" name="works_name" id="works_updatename" class="myinput"/><br /><br />
		<label>作者名称:</label><input type="text" name="author_name" id="author_updateauthorname" class="myinput"/><br /><br />
		<label>发表时间:</label><input name="works_time" id="works_updatetime" class="easyui-datebox myinput" required/><br /><br />
		<label>作品图片:</label><input type="file" name="works_img" id="works_updateimg" multiple="multiple" onchange="previewMultipleImage(this,'works_img_update')"/><br /><br />
		<label>作品点击量:</label><input  class="easyui-numberbox myinput" name="works_click" id="works_updateclick"/><br /><br />
		<label>作品权重:</label><input class="easyui-numberbox myinput" name="works_weight" id="works_updateweight" value="0"/><br /><br />
		<label>作品内容:</label>
		<div>
			<script id="updateUE" type="text/plain" style="width:800px;height:400px;"></script>
		</div><br/><br/>
		<a href="javascript:updateWorksInfo()" class="easyui-linkbutton" data-options="iconCls:'icon-add'">修改</a>
	</form>
	<div style="float:right;width:380px;margin-right:20px;">
		<fieldset id="works_img_update">
			<legend>图片预览</legend>
		</fieldset>
	</div>
</div>
<script>
	var ue11=UE.getEditor('editor11');
	var upue=UE.getEditor('updateUE');		
	
	function updateWorksInfo(){
		var works_id=works.works_id;
		var works_type=$("#works_updatetype").val();
		var works_name=$("#works_updatename").val();
		var author_name=$("#author_updateauthorname").val();
		var works_time=$("#works_updatetime").datebox('getValue');
		var works_click=$("#works_updaclick").datebox('getValue');
		var works_weight=$("#works_updateweight").val();
		var works_details=upue.getContent();
		
		$.ajaxFileUpload({
			url:"../worksServlet?op=updateWorksInfo",
			secureuri:false,
			fileElementId:"works_updateimg",
			dataType:"json",
			data:{works_id:works_id,works_name:works_name,author_name:author_name,works_time:works_time,works_click:works_click,works_type:works_type,works_weight:works_weight,works_details:works_details},
			success:function(data,status){
				if(parseInt($.trim(data))==1){
					$.messager.show({title:'成功提示',msg:'作品信息添加成功...',timeout:2000,showType:'slide'});
					$("#works_update_worksInfo").dialog("close");
					$("#works_info").datagrid("reload");
				}else{
					$.messager.alert("失败提示","作品信息添加失败...","error");
				}
			}
		});
		
	}
	function showWorksDetails(works_id){
		$("#works_show_worksInfo").dialog("open");
		$.post("../worksServlet",{op:"findWorksInfoById",works_id:works_id},function(data){
			var workss=data.rows;
			$("#works_showtype").val(workss.works_type);
			$("#works_showname").val(workss.works_name);
			$("#author_showauthorname").val(workss.author_name);
			$("#works_showtime").val(workss.works_time);
			$("#works_showclick").val(workss.works_click);
			$("#works_showweight").val(workss.works_weight);
			$("#works_showdetails").html(workss.works_details);
			
			var str="";
			var works_img=workss.works_img.split(",");
			for(var i=0;i<works_img.length;i++){
				str+="<img src='../"+works_img[i]+"' width='100px' height='100px'>&nbsp;";
			}
			$("#works_img_show_info").html($(str));
			
		},"json");
	}
	
	
	function getvalue(){
	$("#works_workstype").val($("#works_selecttype").val());
	$("#works_updatetype").val($("#works_updateselecttype").val());
	}
	
	
	function addWorksInfo(){
		var works_name=$("#works_worksname").val();
		var author_name=$("#author_authorname").val();
		var works_time=$("#works_workstime").datebox('getValue');
		var works_type=$.trim( $("#works_workstype").val());
		console.info(works_type);
		var works_weight=$("#works_worksweight").val();
		var works_details=ue11.getContent();
		$.ajaxFileUpload({
			url:"../worksServlet?op=addWorksInfo",
			secureuri:false,
			fileElementId:"works_worksimg",
			dataType:"json",
			data:{works_name:works_name,author_name:author_name,works_time:works_time,works_type:works_type,works_weight:works_weight,works_details:works_details},
			success:function(data,status){
				if(parseInt($.trim(data))==1){
					$.messager.show({title:'成功提示',msg:'作品信息添加成功...',timeout:2000,showType:'slide'});
					$("#works_add_worksInfo").dialog("close");
					$("#works_info").datagrid("reload");
					$("#works_worksname").val("");
					$("#author_authorname").val("");
					$("#works_workstime").datebox('setValue',"");
					$("#works_workstype").val("");
					$("#works_worksweight").val("0");
					ue11.setContent("");
				}else{
					$.messager.alert("失败提示","作品信息添加失败...","error");
				}
			}
		});
	}
</script>

