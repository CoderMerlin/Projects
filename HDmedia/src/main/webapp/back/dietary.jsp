<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<table id="dietary_info" data-options="fit:true"></table>

<script>

var dietary;

$(function(){
	var datagrid;
	var editRow=undefined;
	var statusObj=[{sid:0,sname:'不可用'},{sid:1,sname:'可用'}];
	var op;
	datagrid=$('#dietary_info').datagrid({   
	    url:'dietary_getPageDietaryInfo',
	    fitColumns:true,
	    striped:true,
	    loadMsg:"数据加载中...",
	    pagination:true,
	    rownumbers:true,
	    sortName:'dtid',
	    sortOrder:'asc',
	    remoteSort:false,
	    columns:[[ 
			{field:'dtids',title:'饮食编号',width:100,align:'center',checkbox:true}, 
	        {field:'dtid',title:'饮食编号',width:100,align:'center',sortable:true},   
	        {field:'dtname',title:'饮食名称',width:100,align:'center',editor:{type:"text",options:{required:true}}},
	        {field:'dttitle',title:'饮食标题',width:100,align:'center',editor:{type:"text",options:{required:true}}},
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
	        } ,
	        {field:'_operate',title:'操作',width:100,align:'center',
	        	formatter: function(value,rowData,index){
					return '<a class="icon-search1 icon-padding" href="javascript:showDietaryDetail(\''+rowData.dtid+'\')">详细</a>';
				}
			}
	    ]],
	    toolbar:[{
	    	text:"添加",
			iconCls:'icon-add',
			handler:function(){
				$("#dietary_add_dietary").dialog("open");
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
		    			dtid=rows.dtid;
		    			$.post("dietary_findDietaryById",{dtid:dtid},function(data){
		    				var dietary=data.rows[0];
		    				$("#update_dtid").val(dietary.dtid);
		    				$("#dt_updatedtname").val(dietary.dtname);
							$("#dt_updatedttitle").val(dietary.dttitle);
							$("#update_dtpid").val(dietary.dtpid);
							if(dietary.photo!=null){
								var str="";
								var dietary_photo=dietary.photo.split(",");
								for(var i=0;i<dietary_photo.length;i++){
									str+="<img src='upload/"+dietary_photo[i]+"' width='100px' height='100px'>&nbsp;";
								}
								$("#dietary_img_update").html($(str));
							}
							updateUE.setContent(dietary.dttext);
							$("#dietary_update_dietary").dialog("open");
		    			},"json");
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
							var dtids="";
							for(var i=0;i<rows.length-1;i++){
								dtids+=rows[i].dtid+",";
							}
							dtids+=rows[i].dtid;
							
							//将要删除glid发送到服务器
							$.post("dietary_delDietary",{dtids:dtids},function(data){
								if(data.total==1){ //添加成功
									$.messager.show({
										title:'删除提示',
										msg:'饮食信息删除成功...',
										timeout:2000,
										showType:'slide'
									});
									datagrid.datagrid("reload"); //重新加载数据一次
								}else{
									$.messager.alert('失败提示','饮食信息删除失败...','error');
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
				datagrid.datagrid("rejectChanges"); //回滚自创建以来或自上次调用AcceptChanges，所有的变化数据。
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

<div id="dietary_add_dietary" class="easyui-dialog" title="添加饮食信息"  data-options="fit:true,iconCls:'icon-add',resizable:true,modal:true,closed:true">
	<form id="add_dietary" method="post" style="padding:20px;float:left;display:inline-block;" enctype="multipart/form-data" >
		<input type="text" name="dttext" id="add_dttext" style="display:none"/>
		<label>饮食名称:</label><input type="text" name="dtname" id="dt_dtname" class="myinput"><br/><br/>
	    <label>饮食标题:</label><input type="text" name="dttitle" id="dt_dttitle" class=" myinput" /><br/><br/>
		<label>饮食类型:</label>
		<select name="dtpid" id="add_dtpid" class="myinput">
			
		</select><br /><br />
		<label>饮食图片:</label><input type="file" name="upload" id="dt_photo" multiple="multiple" onchange="previewMultipleImage(this,'dietary_img_show')"/><br /><br />
		<label>饮食简介</label>
		<div>
			<script type="text/javascript" id="editor" style="width:800px;height:400px;"></script>
		</div><br/><br/>
		<a href="javascript:addDietary()" class="easyui-linkbutton" data-option="iconCls:'icon-add'">添加</a>
	</form>
	
	<div style="float:right;width:380px; margin-right:20px;">
		<fieldset id="dietary_img_show">
			<legend>图片预览</legend>
		</fieldset>
	</div>
</div>

<div id="dietary_update_dietary" class="easyui-dialog" title="修改饮食信息"  data-options="fit:true,iconCls:'icon-add',resizable:true,modal:true,closed:true">
	<form id="update_dietary" method="post" style="padding:20px;float:left;display:inline-block;" enctype="multipart/form-data" >
		<input type="text" name="dtid" id="update_dtid" style="display:none"/>
		<input type="text" name="dttext" id="update_dttext" style="display:none"/>
		<label>饮食名称:</label><input type="text" name="dtname" id="dt_updatedtname" class="myinput"><br/><br/>
	    <label>饮食标题:</label><input type="text" name="dttitle" id="dt_updatedttitle" class=" myinput" /><br/><br/>
		<label>饮食类型:</label>
		<select name="dtpid" id="update_dtpid" class="myinput">
			
		</select><br /><br />
		<label>饮食图片:</label><input type="file" name="upload" id="dt_updatephoto" multiple="multiple" onchange="previewMultipleImage(this,'dietary_img_update')"/><br /><br />
		<label>饮食简介</label>
		<div>
			<script type="text/javascript" id="updateED" style="width:800px;height:400px;"></script>
		</div><br/><br/>
		<a href="javascript:updateDietary()" class="easyui-linkbutton" data-option="iconCls:'icon-add'">修改</a>
	</form>
	
	<div style="float:right;width:380px; margin-right:20px;">
		<fieldset id="dietary_img_update">
			<legend>图片预览</legend>
		</fieldset>
	</div>
</div>

<div id="dietary_show_dietary" class="easyui-dialog" title="显示饮食信息"  data-options="fit:true,iconCls:'icon-add',resizable:true,modal:true,closed:true">
	<form id="update_dietary" method="post" style="padding:20px;float:left;display:inline-block;" enctype="multipart/form-data" >
		<label>饮食名称:</label><input type="text" name="dtname" id="dt_showdtname" class="myinput" readonly="readonly"/><br/><br/>
	    <label>饮食标题:</label><input type="text" name="dttitle" id="dt_showdttitle" class=" myinput"  readonly="readonly"/><br/><br/>
		<label>饮食类型:</label>
		<select name="dtpid" id="show_dtpid" class="myinput" >
			
		</select><br /><br />
		<label>饮食图片:</label><input type="file" name="upload" id="dt_showphoto" multiple="multiple" onchange="previewMultipleImage(this,'dietary_img_show')"  /><br /><br />
		<label>饮食简介</label>
		<div id="show_dttext">
		
		</div><br/><br/>
	</form>
	<div style="float:right;width:380px; margin-right:20px;">
		<fieldset id="dietary_img_show">
			<legend>图片预览</legend>
		</fieldset>
	</div>
</div>
<script>

function showDietaryDetail(dtid){
	$.post("dietary_findDietaryById",{dtid:dtid},function(data){
		var dietary=data.rows[0];
		$("#dt_showdtname").val(dietary.dtname);
		$("#dt_showdttitle").val(dietary.dttitle);
		$("#show_dtpid").val(dietary.dtpid);
		if(dietary.photo!=null){
			var str="";
			var dietary_photo=dietary.photo.split(",");
			for(var i=0;i<dietary_photo.length;i++){
				str+="<img src='upload/"+dietary_photo[i]+"' width='100px' height='100px'>&nbsp;";
			}
			console.info(str);
			$("#dietary_img_show").html($(str));
			console.info($("#dietary_img_show").html());
		}
		$("#show_dttext").html(dietary.dttext);
		$("#dietary_show_dietary").dialog("open");
	},"json");
}

$.post("dietaryType_getAllType",function(data){
	var obj=$("#add_dtpid");
	var obj1=$("#update_dtpid");
	var obj2=$("#show_dtpid");
	var opt;
	$.each(data.rows,function(index,item){
		opt="<option value='"+item.dtpid+"'>"+item.dtpname+"</option>";
		obj.append($(opt));
		obj1.append($(opt));
		obj2.append($(opt));
	});
},"json");


	var ue=UE.getEditor("editor");
	var updateUE=UE.getEditor("updateED");
	
	function addDietary(){
		$("#add_dttext").val(ue.getContent());
		var formData=new FormData($("#add_dietary")[0]);
		$.ajax({
			type:"post",
			url:"dietary_addDietary",
			processData:false,
			contentType:false,
			async:false,
			cache:false,
			data:formData,
			success:function(data){
				if(data.total=="1"){
					$("#dietary_add_dietary").dialog("close");
					$("#dietary_info").datagrid("reload");
					$.messager.show({title:'成功提示',msg:'作品信息添加成功...',timeout:2000,showType:'slide'});
					$("#dt_dtname").val("");
					$("#dt_dttitle").val("");
					$("#dttype").val("");
					ue.setContent("");
				}else{
					$.messager.alert("失败提示","作品信息添加失败...","error");
				}
			}
		});
	}
	
	function updateDietary(){
		$("#update_dttext").val(updateUE.getContent());
		var formData=new FormData($("#update_dietary")[0]);
		$.ajax({
			type:"post",
			url:"dietary_updateDietary",
			processData:false,
			contentType:false,
			async:false,
			cache:false,
			data:formData,
			success:function(data){
				if(data.total=="1"){
					$("#dietary_update_dietary").dialog("close");
					$("#dietary_info").datagrid("reload");
					$.messager.show({title:'成功提示',msg:'作品信息修改成功...',timeout:2000,showType:'slide'});
				}else{
					$.messager.alert("失败提示","作品信息修改失败...","error");
				}
			}
		});
	}
</script>
