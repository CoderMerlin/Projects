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
	    url:'../dietaryServlet',
	    queryParams:{op:"getPageDietaryInfo"},
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
					return '<a class="icon-search1 icon-padding" href="javascript:showDietaryDetail(\''+rowData.dtlid+'\')">详细</a>';
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
				console.info(rows);
				if(rows==undefined){
					$.messager.show({title:'温馨提示',msg:'请选择您要修改的的数据...',timeout:2000,showType:'slide'});	
				}else{
		    			dtid=rows.dtid;
		    			$.post("../dietaryServlet",{op:"findDietaryById",dtid:dtid},function(data){
		    				dietary=data.updaterows;
		    				$("#dt_updatedtname").val(dietary.dtname);
							$("#dt_updatedttitle").val(dietary.dttitle);
							$("#dt_updatedttype").val(dietary.dttype);
							
							var str="";
							var dietary_photo=dietary.photo.split(",");
							for(var i=0;i<dietary_photo.length;i++){
								str+="<img src='../"+dietary_photo[i]+"' width='100px' height='100px'>&nbsp;";
							}
							$("#dietary_img_update").html($(str));
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
							$.post("../dietaryServlet",{op:"delDietary",dtids:dtids},function(data){
								if(data==1){ //添加成功
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
	<form style="padding:20px;float:left;display:inline-block;">
		<label>饮食名称:</label><input type="text" name="dtname" id="dt_dtname" class="myinput"><br/><br/>
	    <label>饮食标题:</label><input type="text" name="dttitle" id="dt_dttitle" class=" myinput" /><br/><br/>
		<label>饮食类型:</label><input type="text" name="dttype" id="dt_dttype" class="myinput"/><br/><br/>
		<label>饮食图片:</label><input type="file" name=photo id="dt_photo" multiple="multiple" onchange="previewMultipleImage(this,'dietary_img_show')"/><br /><br />
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
	<form style="padding:20px;float:left;display:inline-block;">
		<label>饮食名称:</label><input type="text" name="dtname" id="dt_updatedtname" class="myinput"><br/><br/>
	    <label>饮食标题:</label><input type="text" name="dttitle" id="dt_updatedttitle" class=" myinput" /><br/><br/>
		<label>饮食类型:</label><input type="text" name="dttype" id="dt_updatedttype" class="myinput"/><br/><br/>
		<label>饮食图片:</label><input type="file" name=photo id="dt_updatephoto" multiple="multiple" onchange="previewMultipleImage(this,'dietary_img_update')"/><br /><br />
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

<script>
	var ue=UE.getEditor("editor");
	var updateUE=UE.getEditor("updateED");
	function addDietary(){
		var dtname=$("#dt_dtname").val();
		var dttitle=$("#dt_dttitle").val();
		var dttype=$("#dt_dttype").val();
		var dttext=ue.getContent();
		$.ajaxFileUpload({
			url:"../dietaryServlet?op=addDietary",
			secureuri:false,
			fileElementId:"dt_photo",
			dataType:"json",
			data:{dtname:dtname,dttitle:dttitle,dttype:dttype,dttext:dttext},
			success:function(data,status){
				if(parseInt($.trim(data))==1){
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
		var dtid=dietary.dtid;
		var dtname=$("#dt_updatedtname").val();
		var dttitle=$("#dt_updatedttitle").val();
		var dttype=$("#dt_updatedttype").val();
		var dttext=updateUE.getContent();
		$.ajaxFileUpload({
			url:"../dietaryServlet?op=updateDietary",
			secureuri:false,
			fileElementId:"dt_updatephoto",
			dataType:"json",
			data:{dtid:dtid,dtname:dtname,dttitle:dttitle,dttype:dttype,dttext:dttext},
			success:function(data,status){
				if(parseInt($.trim(data))==1){
					$("#dietary_update_dietary").dialog("close");
					$("#dietary_info").datagrid("reload");
					$.messager.show({title:'成功提示',msg:'作品信息添加成功...',timeout:2000,showType:'slide'});
				}else{
					$.messager.alert("失败提示","作品信息添加失败...","error");

				}
			}
		});
		
	}
	
</script>
