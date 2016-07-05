<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<table id="author_info"  data-options="fit:true"></table>

<script>
		
		var author;
		
	$(function(){
		var datagrid;
		var editRow=undefined;
		var op;
		var flag;
		datagrid=$('#author_info').datagrid({
			url:"author_getPageAuthorInfo",
			fitColumns:true,
		    striped:true,
		    loadMsg:"数据加载中...",
		    pagination:true,
		    rownumbers:true,
		    sortName:'author_id',
		    sortOrder:'asc',
		    remoteSort:false,
		    columns:[[
		    	{field:'author_ids',title:'作者编号',width:100,align:'center',checkbox:true},   
		        {field:'author_id',title:'作者编号',width:100,align:'center',sortable:true},   
		        {field:'author_name',title:'作者姓名',width:100,align:'center',editor:{type:"text",options:{required:true}}},  
		        {field:'author_sex',title:'作者性别',width:100,align:'center',editor:{type:"text",options:{required:true}}},
		        {field:'author_time',title:'作者年代',width:100,align:'center',editor:{type:"text",options:{required:true}}},   
		        {field:'author_area',title:'作者籍贯',width:100,align:'center',editor:{type:"text",options:{required:true}}},
		        {field:'author_type',title:'作者类型',width:100,align:'center',editor:{type:"text",options:{required:true}}},   
		        {field:'status',title:'状态',width:100,align:'center',editor:{type:"text",options:{required:true}}},
		        {field:'_operate',title:'操作',width:100,align:'center',
	        		formatter: function(value,rowData,index){
						return '<a class="icon-search1 icon-padding" href="javascript:showAuthorInfo(\''+rowData.author_id+'\')">详细</a>';
					} 
				} 		        
		    ]],
		    toolbar:[{
		    	text:"添加",
		    	iconCls:'icon-add',
		    	handler:function(){
		    		$("#author_add_authorinfo").dialog("open");
		    	}
		    },{
		    	text:"修改",
		    	iconCls:'icon-edit',
		    	handler:function(){
		    		var rows=datagrid.datagrid("getChecked");
		    		if(rows.length!=1){
		    			$.messager.show({title:'温馨提示',msg:'您没有选中数据或选中多行数据,请重新选择...',timeout:2000,showType:'slide'});
		    		}else{
		    			author=rows[0];
		    			var author_id=author.author_id;
		    			$.post("author_findAuthorById",{author_id:author_id},function(data){
		    				var author=data.rows[0];
		    				$("#author_upid").val(author.author_id);
		    				$("#author_updatename").val(author.author_name);
		    				if(author.author_sex=="女"){
		    					$("#author_updatesex1").checked=false;
		    				}else{
		    					$("#author_updatesex2").checked=false;
		    				}
							
							$("#author_updatetime").val(author.author_time);
							$("#author_updatearea").val(author.author_area);
							$("#authhor_updatetype").val(author.author_type);
							$("#author_updateweight").val(author.author_weight);
							if(author.author_photo!=null){
								var str="";
								var author_photo=author.author_photo.split(",");
								for(var i=0;i<author_photo.length;i++){
									str+="<img src='../upload/"+author_photo[i]+"' width='100px' height='100px'>&nbsp;";
								}
								$("#author_img_update").html($(str));
							}
							updateUE.setContent(author.author_details);
							$("#author_update_authorinfo").dialog("open");
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
		    					var author_ids="";
		    					for(var i=0;i<rows.length-1;i++){
		    						author_ids+=rows[i].author_id+",";
		    					}
		    					author_ids+=rows[i].author_id;
		    					
		    					$.post("author_delAuthorInfo",{author_ids:author_ids},function(data){
		    						if(data.total>0){
		    							$.messager.show({title:'成功提示',msg:'作者信息删除成功...',timeout:2000,showType:'slide'});
										datagrid.datagrid("reload");
		    						}else{
		    							$.messager.alert('失败提示','作者信息删除失败...');
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

<div id="author_add_authorinfo" class="easyui-dialog" title="添加作者信息"  data-options="fit:true,iconCls:'icon-add',resizable:true,modal:true,closed:true">
	<form  id="add_author"  method="post"  style="padding:20px;float:left;display:inline-block;"  enctype="multipart/form-data">
		<input type="text" name="author_details" id="author_details" style="display:none"/>
		<label>作者姓名:</label><input type="text" name="author_name" id="author_authorname" class="myinput"><br/><br/>
		<label>作者性别:</label>&nbsp;&nbsp;<input type="radio" name="author_sex" id="author_authorsex1" value="男" checked="false"><label>男</label>
							<input type="radio" name="author_sex" id="author_authorsex2" value="女" checked="false"><label>女</label><br/><br/>
		<label>作者年代:</label><input type="text" name="author_time" id="author_authortime" class=" myinput" /><br/><br/>
		<label>籍&nbsp;&nbsp;&nbsp;&nbsp;贯:</label><input type="text" name="author_area" id="author_authorarea" class="myinput"/><br/><br/>
		<label>照&nbsp;&nbsp;&nbsp;&nbsp;片:</label><input type="file" name="upload" id="author_authorimg" multiple="multiple" onchange="previewMultipleImage(this,'author_img_show')"/><br /><br />
		<label>作者类型:</label><input type="text" name="author_type" id="authhor_authortype" class="myinput"/><br/><br/>
		<label>权&nbsp;&nbsp;&nbsp;&nbsp;重</label><input class="easyui-numberbox myinput" name="author_weight" id="author_authorweight" value="0"/><br/><br/>
		<label>作者简介</label>
		<div>
			<script type="text/javascript" id="editorauthor" style="width:800px;height:400px;"></script>
		</div><br/><br/>
		<a href="javascript:addAuthorInfo()" class="easyui-linkbutton" data-option="iconCls:'icon-add'">添加</a>
	</form>
	
	<div style="float:right;width:380px; margin-right:20px;">
		<fieldset id="author_img_show">
			<legend>图片预览</legend>
		</fieldset>
	</div>
</div>

<div id="author_show_authorinfo" class="easyui-dialog" title="显示作者信息"  data-options="fit:true,iconCls:'icon-add',resizable:true,modal:true,closed:true">
	<form   style="padding:20px;float:left;display:inline-block;">
		<label>作者姓名:</label><input type="text" name="author_name" id="author_showname" class="myinput" readonly="readonly"/><br/><br/>
		<label>作者性别:</label><input type="text" name="author_sexs" id="author_showsex" class="myinput" readonly="readonly"/><br/><br/>
		<label>作者年代:</label><input type="text" name="author_time" id="author_showtime" class=" myinput"  readonly="readonly"/><br/><br/>
		<label>籍&nbsp;&nbsp;&nbsp;&nbsp;贯:</label><input type="text" name="author_area" id="author_showarea" class="myinput" readonly="readonly"/><br/><br/>
		<label>作者类型:</label><input type="text" name="author_type" id="authhor_showtype" class="myinput" readonly="readonly"/><br/><br/>
		<label>权&nbsp;&nbsp;&nbsp;&nbsp;重</label><input class="easyui-numberbox myinput" name="author_weight" id="author_showweight"  readonly="readonly"/><br/><br/>
		<label>作者简介</label>
		<div  id="author_showdetais">
			
		</div><br/><br/>
		<a href="javascript:returnAuthorInfo()" class="easyui-linkbutton" data-option="iconCls:'icon-add'">返回</a>
	</form>
	
	<div style="float:right;width:380px; margin-right:20px;" id="author_img_show_info">
		
	</div>
</div>

<div id="author_update_authorinfo" class="easyui-dialog" title="修改作者信息"  data-options="fit:true,iconCls:'icon-add',resizable:true,modal:true,closed:true">
	<form id="update_author"   method="post" style="padding:20px;float:left;display:inline-block;" enctype="multipart/form-data">
		<input type="text" name="author_id" id="author_upid" style="display:none"/>
		<input type="text" name="author_details" id="author_updetails" style="display:none"/>
		<label>作者姓名:</label><input type="text" name="author_name" id="author_updatename" class="myinput"/><br/><br/>
		<label>作者性别:</label>&nbsp;&nbsp;<input type="radio" name="author_sex" id="author_updatesex1" value="男" checked><label>男</label>
							<input type="radio" name="author_sex" id="author_updatesex2" value="女"><label>女</label><br/><br/>
		<label>作者年代:</label><input type="text" name="author_time" id="author_updatetime" class=" myinput"/><br/><br/>
		<label>籍&nbsp;&nbsp;&nbsp;&nbsp;贯:</label><input type="text" name="author_area" id="author_updatearea" class="myinput" /><br/><br/>
		<label>照&nbsp;&nbsp;&nbsp;&nbsp;片:</label><input type="file" name="upload" id="author_updateimg" multiple="multiple" onchange="previewMultipleImage(this,'author_img_update')"/><br /><br />
		<label>作者类型:</label><input type="text" name="author_type" id="authhor_updatetype" class="myinput"/><br/><br/>
		<label>权&nbsp;&nbsp;&nbsp;&nbsp;重</label><input class="easyui-numberbox myinput" name="author_weight" id="author_updateweight"/><br/><br/>
		<label>作者简介</label>
		<div>
			<script type="text/javascript" id="updateeditor" style="width:800px;height:400px;"></script>
		</div><br/><br/>
		<a href="javascript:updateAuthorInfo()" class="easyui-linkbutton" data-option="iconCls:'icon-add'">确定</a>
	</form>
	
	<div style="float:right;width:380px; margin-right:20px;">
		<fieldset id="author_img_update">
			<legend>图片预览</legend>
		</fieldset>
	</div>
</div>

<script>
	var ue22=UE.getEditor("editorauthor");
	var updateUE=UE.getEditor("updateeditor");
	function returnAuthorInfo(){
		$("#author_show_authorinfo").dialog("close");
		$("#author_info").datagrid("reload");
	}
	
	function updateAuthorInfo(){
		$("#author_updetails").val(updateUE.getContent());
		var formData=new FormData($("#update_author")[0]);
		console.info("要修改的数据"+formData);
		$.ajax({
			type:"post",
			url:"author_updateAuthorInfo",
			processData:false,
			contentType:false,
			async:false,
			cache:false,
			data:formData,
			success:function(data){
				if(data.total=="1"){
					$.messager.show({title:'成功提示',msg:'作者信息修改成功...',timeout:2000,showType:'slide'});
					$("#author_update_authorinfo").dialog("close");
					$("#author_info").datagrid("reload");
				}else{
					$.messager.alert("失败提示","作者信息修改失败...","error");

				}
			}
		});
	}
	
	
	
	function showAuthorInfo(author_id){
		$("#author_show_authorinfo").dialog("open");
		$.post("author_findAuthorById",{author_id:author_id},function (data){
			var author=data.rows[0];
			$("#author_showname").val(author.author_name);
			$("#author_showsex").val(author.author_sex);
			$("#author_showtime").val(author.author_time);
			$("#author_showarea").val(author.author_area);
			$("#authhor_showtype").val(author.author_type);
			$("#author_showweight").val(author.author_weight);
			$("#author_showdetais").html(author.author_details);
			if(author.author_photo!=null){
				var str="";
				var author_photo=author.author_photo.split(",");
				for(var i=0;i<author_photo.length;i++){
					str+="<img src='../upload/"+author_photo[i]+"' width='100px' height='100px'>&nbsp;";
				}
				$("#author_img_show_info").html($(str));
			}
		},"json");
	}
	
	function addAuthorInfo(){
		$("#author_details").val(ue22.getContent());
		var formData=new FormData($("#add_author")[0]);
		console.info(formData);
		$.ajax({
			type:"post",
			url:"author_addAuthorInfo",
			processData:false,
			contentType:false,
			async:false,
			cache:false,
			data:formData,
			success:function(data){
				if(data.total=="1"){
					$("#author_add_authorinfo").dialog("close");
					$("#author_info").datagrid("reload");
					$.messager.show({title:'成功提示',msg:'作者信息添加成功...',timeout:2000,showType:'slide'});
					$("#author_authorname").val("");
					$("#author_authorsex1").checked;
					$("#author_authortime").val("");
					$("#author_authorarea").val("");
					$("#author_authorweight").val("0");
					ue22.setContent("");
				}else{
					$.messager.alert("失败提示","作者信息添加失败...","error");
				}
			}
		});
	}
	
	
	
</script>
