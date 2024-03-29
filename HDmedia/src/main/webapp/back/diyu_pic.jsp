<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<center id="diyu_pic_info">
</center>
<style>
	.myinput{
		width:200px;
		border:1px solid #F63;
	}
	
	label{
		padding-right:10px;
	}
</style>
<!--添加-->
<div id="diyu_add_Info" class="easyui-dialog" title="添加地域" data-options="fit:true,iconCls:'icon-add',resizable:true,modal:true,closed:true">
	<form action="" style="padding:20px;float:left;display:inline-block;">
		<label>地域编号:</label><input name="area_id" id="area_id" class="myinput"><br /><br />
		
		<label>图片名称:</label><input name="ap_name" id="ap_name" class="myinput"><br /><br />
		
		<label> 图 片 :</label><input type="file" name="area_pic" id="area_pic" onchange="previewMultipleImage(this,'pic_show')" required/><br /><br />
		
		<label> 状态 :</label><input name="ap_status" id="ap_status" class="myinput" required/><br /><br />
	
		<label>内 容 :</label>
		<div>
			<script id="editor" type="text/plain" style="width:800px;height:200px;"></script>
		</div><br/><br/>
		<a href="javascript:adddiyuInfo()" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加</a>
	</form>
	<div style="float:right;width:380px;margin-right:20px;">
		<fieldset id="pic_show">
			<legend>图片预览</legend>
		
		</fieldset>
	</div>
</div>


<!--修改-->
<div id="diyu_update_Info" class="easyui-dialog" title="修改地域" data-options="fit:true,iconCls:'icon-edit',resizable:true,modal:true,closed:true">
	<form action="" style="padding:20px;float:left;display:inline-block;">
		<label>地域编号:</label><input name="area_id" id="update_area_id" class="myinput"><br /><br />
		
		<label>图片名称:</label><input name="ap_name" id="update_ap_name" class="myinput" required/><br /><br />
		<label>图片:</label><input type="file" name="area_pic" id="update_area_pic"  required/><br /><br />
		
		<label> 状态 :</label><input name="ap_status" id="update_ap_status" class="myinput" required/><br /><br />
		
		<label>内容:</label>
		<div>
			<script id="editors" type="text/plain" style="width:800px;height:200px;"></script>
		</div><br/><br/>
		<a href="javascript:updatediyuInfo()" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改</a>
	</form>
	<div style="float:right;width:380px;margin-right:20px;">
		<fieldset id="update_pic_show">
			<legend>图片预览</legend>
		
		</fieldset>
	</div>
</div>

<!--详细-->
<div id="diyu_show_Info" class="easyui-dialog" title="详细地域" data-options="fit:true,iconCls:'icon-add',resizable:true,modal:true,closed:true">
	<form action="" style="padding:20px;float:left;display:inline-block;">
		<label>地域名称:</label><input name="area_name" id="show_area_name" class="myinput"><br /><br />
		<label>图片名称:</label><input name="ap_name" id="show_ap_name" class="myinput"><br /><br />
		<label> 状态 :</label><input name="ap_status" id="show_ap_status" class="myinput" required/><br /><br />
		
		<label>内容:</label>
		<div id="content_show">
			
		</div>
	</form>
	<div style="float:right;width:380px;margin-right:20px;">
		<fieldset id="show_pic_show">
			<legend>图片预览</legend>
		
		</fieldset>
	</div>
</div>
<script>
var datagrid;
var rows;
$.post("../diyu_picServlet",{op:"getAllDiyu_picType"},function(data){
	var obj=$("#area_id");
	var obj1=$("#update_ap_name");
	var opt;
	$.each(data.rows,function(index,item){
		opt="<option value='"+item.area_id+"'>"+item.ap_name+"</option>";
		obj.append($(opt));
		obj1.append($(opt));
	});
},"json");


$(function(){
	var datagrid;
	var editRow=undefined;
	var op;
	var flag;
	
	datagrid=$('#diyu_pic_info').datagrid({   
	    url:'../diyu_picServlet',
	    queryParams:{op:"getPageDiyu_picInfo"},
	    fitColumns:true,
	    striped:true,
	    loadMsg:"数据加载中...",
	    pagination:true,
	    rownumbers:true,
	    sortName:'area_id',
	    sortOrder:'asc',
	    remoteSort:false,
	    columns:[[ 
			{field:'area_ids',title:'选择',width:100,align:'center',checkbox:true}, 
	        {field:'ap_id',title:'地域图片编号',width:100,align:'center',sortable:true},   
	        {field:'ap_name',title:'地域图片名称',width:100,align:'center',editor:{type:"text",options:{required:true}}},
	        {field:'ap_status',title:'状态',width:100,align:'center',sortable:true},
	        {field:'_operate',title:'操作',width:100,align:'center',
	        	formatter: function(value,rowData,index){
	        		//console.info(rowData);
	        		//console.info(JSON.stringify(rowData));
	        		//console.info( JSON.parse(rowData) );
					return '<a class="icon-search1 icon-padding" href="javascript:showdiyuDetail(\''+rowData.ap_id+'\')">详细</a>';
				}
			}
	    ]],
	    toolbar:[{
	    	text:"添加",
			iconCls:'icon-add',
			handler:function(){
				$("#diyu_add_Info").dialog("open");
			}
		},{
	    	text:"修改",
			iconCls:'icon-edit',
			handler:function(){
				rows=datagrid.datagrid("getChecked");
				if(rows.length<=0){
					$.messager.show({
						title:'温馨提示',msg:'请选择您要修改的数据。。。',timeout:2000,showType:'slide'
					});
				}else{
					
					$("#diyu_update_Info").dialog("open");
					$("#update_area_name").val(rows[0].area_name);
					$("#update_ap_name").val(rows[0].ap_name);
					$("#update_area_status").val(rows[0].area_status);

					uee.setContent(rows[0].ap_details);
					
					var str = "";

					var pics = rows[0].area_pic.split(",");
					for ( var i = 0; i < pics.length; i++) {
						str += "<img src='../"+pics[i]+"' width='100px' height='100px'>&nbsp;";
					}
					$("#update_pic_show").html($(str));
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
							var area_ids="";
							for(var i=0;i<rows.length-1;i++){
								area_ids+=rows[i].area_id+",";
							}
							area_ids+=rows[i].area_id;
							
							//将要删除area_id发送到服务器
							$.post("../diyu_picServlet",{op:"delDiyu_picInfo",ap_ids:ap_ids},function(data){
								if(data==1){ //添加成功
									$.messager.show({
										title:'删除提示',
										msg:'地域信息删除成功...',
										timeout:2000,
										showType:'slide'
									});
									datagrid.datagrid("reload"); //重新加载数据一次
								}else{
									$.messager.alert('失败提示','地域信息删除失败...','error');
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
<script>
var ue = UE.getEditor('editor');
var uee = UE.getEditor('editors');

function showdiyuDetail(ap_id) {
	$("#diyu_show_Info").dialog("open");
	$.post("../diyu_picServlet",{op : "findNewsByap_id",ap_id :ap_id},
		function(data) {
			var news = data.rows;
			$("#show_area_name").val(news.area_name);
			$("#show_ap_name").val(news.ap_name);
			$("#show_ap_status").val(news.ap_status);
			$("#content_show").html(news.ap_details);

			var str = "";

			var pics = news.area_pic.split(",");
			for ( var i = 0; i < pics.length; i++) {
				str += "<img src='../"+pics[i]+"' width='100px' height='100px'>&nbsp;";
			}
			$("#show_pic_show").html($(str));
		}, "json");
}

function updatediyuInfo(){
	var ap_id = rows[0].ap_id;
	var area_id= $("#update_area_id").val();
	var ap_name= $("#update_ap_name").val();
	var ap_status=$("#update_ap_status").val();
	var ap_details= uee.getContent();
	
	$.ajaxFileUpload({
		url : "../diyu_picServlet?op=updateDiyu_picInfo",
		secureuri : false,
		fileElementId : "update_area_pic",
		dataType : "json",
		data : {ap_id : ap_id,
			area_id : area_id,
			ap_name:ap_name,
			ap_status : ap_status,
			ap_details : ap_details
			},
		success : function(data, status) {
			if (parseInt($.trim(data)) == 1) {//说明是成功的
				$.messager.show({
					title : '成功提示',
					msg : '地域信息修改成功...',
					timeout : 2000,
					showType : 'slide'
				});
				$("#diyu_update_Info").dialog("close");
				$("#diyu_info").datagrid("reload");
			} else {
				$.messager.alert("失败提示", "地域信息修改失败...", "error");
			}
		}
	});
}

function adddiyuInfo(){
	var area_id = $("#area_id").val();
	var ap_name = $("#ap_name").val();
	var ap_status=$("#ap_status").val();
	var ap_details = ue.getContent();
	
	
	$.ajaxFileUpload({
		url : "../diyu_picServlet?op=addDiyu_picInfo",
		secureuri : false,
		fileElementId : "area_pic",
		dataType : "json",
		data : {
			area_id : area_id,
			ap_name:ap_name,
			ap_status : ap_status,
			ap_details : ap_details
			
		},
		success : function(data, status) {
			if (parseInt($.trim(data)) == 1) { //说明是成功的
				$.messager.show({
					title : '成功提示',
					msg : '地域信息添加成功...',
					timeout : 2000,
					showType : 'slide'
				});
				$("#diyu_add_Info").dialog("close");
				$("#diyu_info").datagrid("reload");
				$("#area_id").val();
				$("#ap_name").val("");
				$("#ap_status").val("");
				ue.setContent("");
			} else {
				$.messager.alert("失败提示", "地域信息添加失败...", "error");
			}
		},
		error : function(data, status, e) {
			$.messager.alert("错误提示", "地域信息添加有误...\n" + e, "error");
		}
	});
}
</script>