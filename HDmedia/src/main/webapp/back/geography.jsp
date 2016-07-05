<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<center id="georaphy">
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
<!-- 添加
<div id="diyu_add_Info" class="easyui-dialog" title="添加地域信息" data-options="fit:true,iconCls:'icon-add',resizable:true,modal:true,closed:true">
	<form action="" style="padding:20px;float:left;display:inline-block;">
		<label>作品编号:</label><input name="works_id" id="works_id" class="myinput"><br /><br />
		
		<label>地域名称:</label><input name="area_name" id="area_name" class="myinput" ><br /><br />
		
		
		<label> 标 题 :</label><input name="area_title" id="area_title" class="myinput" required/><br /><br />
		<label> 图 片 :</label><input type="file" name="area_pic" id="area_pic" onchange="previewMultipleImage(this,'pic_show')" required/><br /><br />
		<label> 状态 :</label><input name="area_status" id="area_status" class="myinput" required/><br /><br />
	
		<label>内 容 :</label>
		<div>
			<script id="editor11" type="text/plain" style="width:800px;height:200px;"></script>
		</div><br/><br/>
		<a href="javascript:adddiyuInfo()" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加</a>
	</form>
	<div style="float:right;width:380px;margin-right:20px;">
		<fieldset id="pic_show">
			<legend>图片预览</legend>
		
		</fieldset>
	</div>
</div>

 -->
<!--修改-->
<div id="diyu_update_Info" class="easyui-dialog" title="修改地域" data-options="fit:true,iconCls:'icon-edit',resizable:true,modal:true,closed:true">
	<form action="" style="padding:20px;float:left;display:inline-block;">
		<label>作品编号:</label><input name="works_id" id="update_works_id" class="myinput"><br /><br />
		
		<label>地域名称:</label><input name="area_name" id="update_area_name" class="myinput" required/><br /><br />
		<label>标题:</label><input name="area_title" id="update_area_title" class="myinput" required/><br /><br />
		<label>图片:</label><input type="file" name="area_pic" id="update_area_pic"  required/><br /><br />
		<label> 状态 :</label><input name="area_status" id="update_area_status" class="myinput" required/><br /><br />
		
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
		<label>作品名称:</label><input name="works_name" id="show_works_name" class="myinput"><br /><br />
		<label>地域名称:</label><input name="area_name" id="show_area_name" class="myinput"><br /><br />
		
		<label>标题:</label><input name="area_title" id="show_area_title" class="myinput" required/><br /><br />
		<label> 状态 :</label><input name="area_status" id="show_area_status" class="myinput" required/><br /><br />
		
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

<!-- 显示所有人物 -->
<div id="diyu_show_person_Info" class="easyui-dialog" title="人物查看" style="width:1000px;height:450px" data-options="iconCls:'icon-add',resizable:true,modal:true,closed:true">
	<form action="" style="padding:20px;float:left;display:inline-block;">
		<label>人物姓名:</label>
		<select id="show_person_ppname" name="ppname" style="width:150px;">
		</select>
		<label>人物状态 :</label><input name="ppstatus" id="show_person_status" class="myinput" readonly/><br /><br />
		
		<label>人物图片:</label>
		<div id="show_person_ppimg" >
			
		</div>
		<label>人物详解:</label>
		<div id="show_person_content">
			
		</div>
	</form>
</div>



<!-- 选择添加人物或者是景点 -->
<div id="win22" class="easyui-window" title="添加信息" style="width:200px;height:100px" data-options="iconCls:'icon-save',modal:true,closed:true">   
    <div style="margin:20px 5px;">
		<a  class="easyui-linkbutton" onclick="openAddPersonInfo()">添加人物</a>
		<a class="easyui-linkbutton" onclick="">添加景点</a>
	</div> 
</div> 

<!-- 添加人物 -->
<div id="add_person_Info" class="easyui-dialog" title="添加人物信息" style="width:1000px;height:600px"  data-options="iconCls:'icon-add',resizable:true,modal:true,closed:true">
	<form action="propersonBack_addPersonInfo.action" style="padding:20px;float:left;display:inline-block;" method="post" enctype="multipart/form-data">
		<label>地域名称:</label><select id="show_province_prname" name="prid" style="width:150px;">
		</select>
		<label>人物名称:</label><input name="ppname" id="person_Info_ppname" class="myinput" ><br /><br />
		<label>人物图片:</label><input type="file" name="upload" id="person_Info_ppimg" onchange="previewMultipleImage(this,'show_person_Info_ppimg')" required/><br /><br />
		<label> 状态 :</label>
		<select id="person_Info_ppstatus" name="ppstatus" style="width:150px;">
			<option value="1">存在</option>
			<option value="0">不存在</option>
		</select>
		<label>内 容 :</label>
		<div>
			<textarea rows="" cols="" name="ppcontent" style="resize:none; width:300px;height:250px">
				
			</textarea>
		</div><br/><br/>
		<input type="submit" class="easyui-linkbutton" data-options="iconCls:'icon-add'" value="添加"/> 
		<!-- <a href="javascript:addPersonInfo()" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加</a> -->
	</form>
	<div style="float:right;width:380px;margin-right:20px;">
		<fieldset id="show_person_Info_ppimg">
			<legend>图片预览</legend>
		</fieldset>
	</div>
</div>

 

<script>


$(function(){
	var datagrid;
	var editRow=undefined;
	var op;
	var flag;	
	var statusObj=[{sid:0,sname:'不可用'},{sid:1,sname:'可用'}];
	datagrid=$('#georaphy').datagrid({   
	    url:'georaphyBack_getAllProvinces.action',
	    queryParams:{op:"getPageDiyuInfo"},
	    fitColumns:true,
	    striped:true,
	    loadMsg:"数据加载中...",
	    pagination:true,
	    rownumbers:true,
	    sortName:'prid',
	    sortOrder:'asc',
	    remoteSort:false,
	    columns:[[ 
			{field:'prids',title:'选择',width:100,align:'center',checkbox:true}, 
	        {field:'prid',title:'地域编号',width:100,align:'center',sortable:true},   
	        {field:'prname',title:'地域名称',width:100,align:'center',editor:{type:"text",options:{required:true}}},
	        {field:'prcontent',title:'介绍',width:100,align:'center',editor:{type:"text",options:{required:true}}},
	        {field:'prstatus',title:'状态',width:100,align:'center',editor:{type:"combobox",options:{
	        	required:true,valueField:'sid',textField:'sname',data:statusObj}},
	        	formatter:function(value,row,index){
	        		for(var i=0;i<statusObj.length;i++){
	        			if(statusObj[i].sid==value){
	        				return statusObj[i].sname;
	        			}
	        		}
	        		return value;
	        	}
	        },
	        {field:'_operate',title:'人物查看',width:100,align:'center',
	        	formatter: function(value,rowData,index){
					return '<a class="icon-search1 icon-padding" href="javascript:showdiyuperson(\''+rowData.prid+'\')">详细</a>';
				}
			},
	        {field:'_operate1',title:'风景查看',width:100,align:'center',
	        	formatter: function(value,rowData,index){
					return '<a class="icon-search1 icon-padding" href="javascript:showdiyuscenery(\''+rowData.prid+'\')">详细</a>';
				}
			}
	    ]],

	    toolbar:[{
	    	id:'#tb',
	    	text:"添加信息",
			iconCls:'icon-add',
			handler:function(){
				$("#win22").dialog("open");
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
					$("#update_works_name").val(rows[0].works_name);
					$("#update_area_name").val(rows[0].area_name);
					$("#update_area_title").val(rows[0].area_title);
					$("#update_area_pic").val(rows[0].area_pic);
					$("#update_area_status").val(rows[0].area_status);

					uee.setContent(rows[0].area_content);
					
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
							$.post("../diyuServlet",{op:"delDiyuInfo",area_ids:area_ids},function(data){
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
var geue1 = UE.getEditor('editors');
//查看不同的人的信息
function getPersonDetal(prpid){
	$.post("georaphyBack_getPersonByPrpid.action",{prpid:prpid},function(data){
		var person=data.rows;
		$("#show_person_status").val(person.ppsatus);
		$("#show_person_content").html(person.ppcontent);
		var pics=person.ppimg;
		
		var str="<img src='../upload/"+pics+"' width='100px' height='100px' alt='图片加载失败'>"
		$("#show_person_ppimg").html($(str)); 
	},"json");

} 

/* 显示省份中所有的人物 */
function showdiyuperson(prid) {
	$("#diyu_show_person_Info").dialog("open");
	$("#show_person_status").val();
	$("#show_person_content").html("");
	$("#show_person_ppimg").html("");
	$.post("georaphyBack_getAllPersons.action",{prid :prid},
		function(data) {
			var person = data.persons[0];
			/* $("#show_person_ppname").val(persons.ppname); */
			$("#show_person_status").val(person.ppsatus);
			$("#show_person_content").html(person.ppcontent);
			/* var str = "";
			var pics = persons.ppimg.split(",");
			for ( var i = 0; i < pics.length; i++) {
				str += "<img src='../"+pics[i]+"' width='100px' height='100px'>&nbsp;";
			}*/
			var pics=person.ppimg;
			
			var str="<img src='../upload/"+pics+"' width='100px' height='100px' alt='图片加载失败'>"
			console.info(str);
			$("#show_person_ppimg").html($(str)); 
		}, "json");

	$.post("georaphyBack_getAllPersonNamesByPrid.action",function(data){
		var obj=$("#show_person_ppname");
		obj.empty();
		var opt="";
		$.each(data.rows,function(index,item){
			opt="<option value='"+item.prpid+"' onclick='getPersonDetal("+item.prpid+")'>"+item.ppname+"</option>";
			obj.append($(opt));
		});
	},"json");
}

//打开添加人物信息
function openAddPersonInfo(){
	$("#win22").dialog("close");
	$("#add_person_Info").dialog("open");
	$.post("georaphyBack_getAllProvincePrnames.action",function(data){
		var obj=$("#show_province_prname");
		obj.empty();
		var opt="";
		$.each(data.rows,function(index,item){
			opt="<option value='"+item.prid+"'>"+item.prname+"</option>";
			obj.append($(opt));
		});
	},"json");
}


function updatediyuInfo(){
	var area_id = rows[0].area_id;
	var works_id= $("#update_works_id").val();
	var area_name = $("#update_area_name").val();
	var area_title = $("#update_area_title").val();
	var area_status=$("#update_area_status").val();
	var area_content = uee.getContent();
	
	$.ajaxFileUpload({
		url : "../diyuServlet?op=updateDiyuInfo",
		secureuri : false,
		fileElementId : "update_area_pic",
		dataType : "json",
		data : {area_id : area_id,
			works_id : works_id,
			area_name:area_name,
			area_title : area_title,
			area_status : area_status,
			area_content : area_content
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
				$("#georaphy").datagrid("reload");
			} else {
				$.messager.alert("失败提示", "地域信息修改失败...", "error");
			}
		}
	});
}

function adddiyuInfo(){
	var works_id = $("#works_id").val();
	var area_name = $("#area_name").val();
	var area_title = $("#area_title").val();
	var area_status=$("#area_status").val();
	var area_content = ue.getContent();
	
	
	$.ajaxFileUpload({
		url : "../diyuServlet?op=addDiyuInfo",
		secureuri : false,
		fileElementId : "area_pic",
		dataType : "json",
		data : {
			works_id : works_id,
			area_name:area_name,
			area_title : area_title,
			area_status : area_status,
			area_content : area_content
			
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
				$("#works_id").val();
				$("#area_name").val("");
				$("#area_title").val("");
				$("#area_status").val("");
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