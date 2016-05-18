<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<table id="author_info"  data-options="fit:true"></table>

<script>
		var op;
		var flag;
		var author;
	$(function(){
		var datagrid;
		var editRow=undefined;
		
		
		datagrid=$('#author_info').datagrid({
			url:"../authorServlet",
			queryParams:{op:"getPageAuthorInfo"},
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
		    			$.post("../authorServlet",{op:"findAuthorById",author_id:author_id},function(data){
		    				author=data.rows;
		    				$("#author_updatename").val(author.author_name);
		    				if(author.author_sex=="男"){
		    					$("#author_updatesex1").checked=true;
		    					console.info("男");
		    				}else{
		    					$("#author_updatesex2").checked=true;
		    					console.info("女");
		    				}
							
							$("#author_updatetime").val(author.author_time);
							$("#author_updatearea").val(author.author_area);
							$("#authhor_updatetype").val(author.author_type);
							$("#author_updateweight").val(author.author_weight);
							var str="";
				
							var author_photo=author.author_photo.split(",");
							for(var i=0;i<author_photo.length;i++){
								str+="<img src='../"+author_photo[i]+"' width='100px' height='100px'>&nbsp;";
							}
							$("#author_img_update").html($(str));
							
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
		    					
		    					$.post("../authorServlet",{op:"delAuthorInfo",author_ids:author_ids},function(data){
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

<div id="author_add_authorinfo" class="easyui-dialog" title="添加作者信息"  data-options="fit:true,iconCls:'icon-add',resizable:true,modal:true,closed:true">
	<form style="padding:20px;float:left;display:inline-block;">
		<label>作者姓名:</label><input type="text" name="author_name" id="author_authorname" class="myinput"><br/><br/>
		<label>作者性别:</label>&nbsp;&nbsp;<input type="radio" name="author_sex" id="author_authorsex1" value="男" checked="true"><label>男</label>
							<input type="radio" name="author_sex" id="author_authorsex2" value="女" checked="false"><label>女</label><br/><br/>
		<label>作者年代:</label><input type="text" name="author_time" id="author_authortime" class=" myinput" /><br/><br/>
		<label>籍&nbsp;&nbsp;&nbsp;&nbsp;贯:</label><input type="text" name="author_area" id="author_authorarea" class="myinput"/><br/><br/>
		<label>照&nbsp;&nbsp;&nbsp;&nbsp;片:</label><input type="file" name="author_photo" id="author_authorimg" multiple="multiple" onchange="previewMultipleImage(this,'author_img_show')"/><br /><br />
		<label>作者类型:</label><input type="text" name="author_type" id="authhor_authortype" class="myinput"/><br/><br/>
		<label>权&nbsp;&nbsp;&nbsp;&nbsp;重</label><input class="easyui-numberbox myinput" name="author_weight" id="author_authorweight" value="0"/><br/><br/>
		<label>作者简介</label>
		<div>
			<script type="text/javascript" id="editor" style="width:800px;height:400px;"></script>
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
	<form style="padding:20px;float:left;display:inline-block;">
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
	<form style="padding:20px;float:left;display:inline-block;">
		<label>作者姓名:</label><input type="text" name="author_name" id="author_updatename" class="myinput"/><br/><br/>
		<label>作者性别:</label>&nbsp;&nbsp;<input type="radio" name="author_sex" id="author_updatesex1" value="男" checked><label>男</label>
							<input type="radio" name="author_sex" id="author_updatesex2" value="女"><label>女</label><br/><br/>
		<label>作者年代:</label><input type="text" name="author_time" id="author_updatetime" class=" myinput"/><br/><br/>
		<label>籍&nbsp;&nbsp;&nbsp;&nbsp;贯:</label><input type="text" name="author_area" id="author_updatearea" class="myinput" /><br/><br/>
		<label>照&nbsp;&nbsp;&nbsp;&nbsp;片:</label><input type="file" name="author_photo" id="author_updateimg" multiple="multiple" onchange="previewMultipleImage(this,'author_img_update')"/><br /><br />
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
	var ue=UE.getEditor("editor");
	var updateUE=UE.getEditor("updateeditor");
	function returnAuthorInfo(){
		$("#author_show_authorinfo").dialog("close");
		$("#author_info").datagrid("reload");
	}
	function showAuthorInfo(author_id){
		$("#author_show_authorinfo").dialog("open");
		$.post("../authorServlet",{op:"findAuthorById",author_id:author_id},function (data){
			var author=data.rows;
			$("#author_showname").val(author.author_name);
			$("#author_showsex").val(author.author_sex);
			$("#author_showtime").val(author.author_time);
			$("#author_showarea").val(author.author_area);
			$("#authhor_showtype").val(author.author_type);
			$("#author_showweight").val(author.author_weight);
			$("#author_showdetais").html(author.author_details);
			
			var str="";
			
			var author_photo=author.author_photo.split(",");
			for(var i=0;i<author_photo.length;i++){
				str+="<img src='../"+author_photo[i]+"' width='100px' height='100px'>&nbsp;";
			}
			$("#author_img_show_info").html($(str));
		},"json");
	}
	
	
	function addAuthorInfo(){
		var author_name=$("#author_authorname").val();
		var author_sex;
		if($("#author_authorsex1").checked){
			author_sex=$("#author_authorsex1").val();
		}else{
			author_sex=$("#author_authorsex1").val();
		}
		var author_time=$("#author_authortime").val();
		var author_area=$("#author_authorarea").val();
		var author_type=$("#authhor_authortype").val();
		var author_weight=$("#author_authorweight").numberbox("getValue");
		var author_details=ue.getContent();
		$.ajaxFileUpload({
			url:"../authorServlet?op=addAuthorInfo",
			secureuri:false,
			fileElementId:"author_authorimg",
			dataType:"json",
			data:{author_name:author_name,author_sex:author_sex,author_time:author_time,author_area:author_area,author_type:author_type,author_weight:author_weight,author_details:author_details},
			success:function(data,status){
				if(parseInt($.trim(data))==1){
					$("#author_add_authorinfo").dialog("close");
					$("#author_info").datagrid("reload");
					$.messager.show({title:'成功提示',msg:'作品信息添加成功...',timeout:2000,showType:'slide'});
					$("#author_authorname").val("");
					$("#author_authorsex1").checked;
					$("#author_authortime").val("");
					$("#author_authorarea").val("");
					$("#author_authorweight").numberbox("setValue",0);
					ue.setContent("");
				}else{
					$.messager.alert("失败提示","作品信息添加失败...","error");

				}
			}
		});
	}

	function updateAuthorInfo(){
		var author_id=author.author_id;
		var author_name=$("#author_updatename").val();
		var author_sex;
		if($("#author_updatesex1").checked){
			author_sex=$("#author_updatesex1").val();
		}else{
			author_sex=$("#author_updatesex2").val();
		}
		var author_time=$("#author_updatetime").val();
		var author_area=$("#author_updatearea").val();
		var author_type=$("#authhor_updatetype").val();
		var author_weight=$("#author_updateweight").val();
		var author_details=updateUE.getContent();
		console.info(author_weight);
		$.ajaxFileUpload({
			url:"../authorServlet?op=updateAuthorInfo",
			secureuri:false,
			fileElementId:"author_updateimg",
			dataType:"json",
			data:{author_id:author_id,author_name:author_name,author_sex:author_sex,author_time:author_time,author_area:author_area,author_type:author_type,author_weight:author_weight,author_details:author_details},
			success:function(data,status){
				if(parseInt($.trim(data))==1){
					$("#author_update_authorinfo").dialog("close");
					$("#author_info").datagrid("reload");
					$.messager.show({title:'成功提示',msg:'作品信息添加成功...',timeout:2000,showType:'slide'});
				}else{
					$.messager.alert("失败提示","作品信息添加失败...","error");

				}
			}
		});
	}

</script>
