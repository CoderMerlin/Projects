<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<center id="wenzhang_info" data-options="fit:true"></center>
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
<div id="wenzhang_add_Info" class="easyui-dialog" title="添加文章" data-options="fit:true,iconCls:'icon-add',resizable:true,modal:true,closed:true">
	<form id="wz_add" style="padding:20px;float:left;display:inline-block;" enctype="multipart/form-data">
		<input  type="text" id="wz_addwztext" name="wztext" style="display:none;"/>
		<label>管理员编号:</label><input name="glid" id="glid" class="myinput"><br /><br />
		<label>文 章 类 型:</label>
		<select name="wztypeid" id="wztypeid" class="myinput">
			
		</select><br /><br />
		
		<label>文 章 标 题 :</label><input name="wztitle" id="wztitle" class="myinput" required/><br /><br />
		<label>文 章 作者 :</label><input name="wzyl1" id="wzyl1" class="myinput" required/><br /><br />
		<label>文 章 图 片 :</label><input type="file" name="upload" id="wzpicture" onchange="previewMultipleImage(this,'pic_show')" required/><br /><br />
		<label>文 章 权 重 :</label><input name="wzweight" id="wzweight" class="myinput" required/><br /><br />
		<label>文 章 内 容 :</label>
		<div>
			<script id="editorArtic" type="text/javascript" style="width:800px;height:200px;"></script>
		</div><br/><br/>
		<a href="javascript:addwenzhangInfo()" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加</a>
	</form>
	<div style="float:right;width:380px;margin-right:20px;">
		<fieldset id="pic_show">
			<legend>图片预览</legend>
		
		</fieldset>
	</div>
</div>


<!--修改-->
<div id="wenzhang_update_Info" class="easyui-dialog" title="修改文章" data-options="fit:true,iconCls:'icon-add',resizable:true,modal:true,closed:true">
	<form method="post" id="wz_upwz" action="" style="padding:20px;float:left;display:inline-block;" enctype="multipart/form-data">
		<input  type="text" id="wz_upwztext" name="wztext" style="display:none;"/>
		<input  type="text" id="wz_upwzid" name="wzid" style="display:none;"/>
		<label>文章类型:</label>
		<select name="wztypeid" id="update_wzname" class="myinput">
			
		</select><br /><br />
		
		<label>文章标题:</label><input name="wztitle" id="update_wztitle" class="myinput" required/><br /><br />
		<label>文 章 作者 :</label><input name="wzyl1" id="update_wzyl1" class="myinput" required/><br /><br />
		<label>文章图片:</label><input type="file" name="upload" id="update_wzpicture"  required/><br /><br />
		<label>文章权重:</label><input name="wzweight" id="update_wzweight" class="myinput" required/><br /><br />
		<label>文章内容:</label>
		<div>
			<script id="editors" type="text/plain" style="width:800px;height:200px;"></script>
		</div><br/><br/>
		<a href="javascript:updatewenzhangInfo()" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改</a>
	</form>
	<div style="float:right;width:380px;margin-right:20px;">
		<fieldset id="update_pic_show">
			<legend>图片预览</legend>
		
		</fieldset>
	</div>
</div>

<!--详细-->
<div id="wenzhang_show_Info" class="easyui-dialog" title="详细文章" data-options="fit:true,iconCls:'icon-add',resizable:true,modal:true,closed:true">
	<form action="" style="padding:20px;float:left;display:inline-block;">
		<label>管理员编号:</label><input name="glid" id="show_glid" class="myinput"><br /><br />
		<label>文章类型:</label><input name="wzname" id="show_wzname" class="myinput"><br /><br />
		<label>文章标题:</label><input name="wztitle" id="show_wztitle" class="myinput" required/><br /><br />
		<label>文 章 作者 :</label><input name="wzyl1" id="show_wzyl1" class="myinput" required/><br /><br />
		<label>文章权重:</label><input name="wzweight" id="show_wzweight" class="myinput" required/><br /><br />
		<label>文章点击数量:</label><input name="wzdianjinnum" id="show_wzdianjinnum" class="myinput" required/><br /><br />
		
		
		<label>文章内容:</label>
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
$.post("wenZhangType_getAllType",function(data){
	var obj=$("#wztypeid");
	var obj1=$("#update_wzname");
	var opt;
	$.each(data.rows,function(index,item){
		opt="<option value='"+item.wztypeid+"'>"+item.wzname+"</option>";
		obj.append($(opt));
		obj1.append($(opt));
	});
},"json");

$(function(){
	var datagrid;
	var editRow=undefined;
	var op;
	var flag;
	
	datagrid=$('#wenzhang_info').datagrid({   
	    url:'wenZhang_getPageWenzhangInfo',
	    fitColumns:true,
	    striped:true,
	    loadMsg:"数据加载中...",
	    pagination:true,
	    rownumbers:true,
	    sortName:'wzid',
	    sortOrder:'asc',
	    remoteSort:false,
	    columns:[[ 
			{field:'wzids',title:'选择',width:100,align:'center',checkbox:true}, 
	        {field:'wzid',title:'文章编号',width:100,align:'center',sortable:true},   
	        {field:'wzname',title:'文章类型',width:100,align:'center',editor:{type:"text",options:{required:true}}},
	        {field:'wztitle',title:'文章标题',width:100,align:'center',editor:{type:"text",options:{required:true}}}, 
	        {field:'wzdianjinnum',title:'文章点击数',width:100,align:'center',sortable:true},
	        {field:'_operate',title:'操作',width:100,align:'center',
	        	formatter: function(value,rowData,index){
					return '<a class="icon-search1 icon-padding" href="javascript:showwenzhangDetail(\''+rowData.wzid+'\')">详细</a>';
				}
			}
	    ]],
	    toolbar:[{
	    	text:"添加",
			iconCls:'icon-add',
			handler:function(){
				$("#wenzhang_add_Info").dialog("open");
			}
		},{
	    	text:"修改",
			iconCls:'icon-edit',
			handler:function(){
				var rows=datagrid.datagrid("getChecked")[0];
	    		if(rows==undefined){
	    			$.messager.show({title:'温馨提示',msg:'您没有选中数据或选中多行数据,请重新选择...',timeout:2000,showType:'slide'});
	    		}else{
	    			var wzid=rows.wzid;
	    			$.post("wenZhang_findWenZhangById",{wzid:wzid},function(data){
	    				console.info(data);
	    				var wenzhang=data.rows[0];
	    				$("#update_wzname").val(wenzhang.wztypeid);
						$("#update_wztitle").val(wenzhang.wztitle);
						$("#update_wzweight").val(wenzhang.wzweight);
						$("#update_wzyl1").val(wenzhang.wzyl1);
						$("#wz_upwzid").val(wenzhang.wzid);
						uee.setContent(wenzhang.wztext);
						if(wenzhang.wzpicture!=null){
							var str = "";
							var pics = wenzhang.wzpicture.split(",");
							for ( var i = 0; i < pics.length; i++) {
								str += "<img src='../upload/"+pics[i]+"' width='100px' height='100px'>&nbsp;";
							}
							$("#update_pic_show").html($(str));
						}
						$("#wenzhang_update_Info").dialog("open");
	    			});
					
					
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
							var wzids="";
							for(var i=0;i<rows.length-1;i++){
								wzids+=rows[i].wzid+",";
							}
							wzids+=rows[i].wzid;
							//将要删除wzid发送到服务器
							$.post("wenZhang_delWenzhangInfo",{wzids:wzids},function(data){
								if(data.total>=1){ //添加成功
									$.messager.show({
										title:'删除提示',
										msg:'文章信息删除成功...',
										timeout:2000,
										showType:'slide'
									});
									datagrid.datagrid("reload"); //重新加载数据一次
								}else{
									$.messager.alert('失败提示','文章信息删除失败...','error');
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
<script>
var ue = UE.getEditor('editorArtic');
var uee = UE.getEditor('editors');

function showwenzhangDetail(wzid) {
	$.post("wenZhang_findWenZhangById",{wzid : wzid},
		function(data) {
			var news = data.rows[0];
			$("#show_glid").val(news.glid);
			$("#show_wzname").val(news.wzname);
			$("#show_wztitle").val(news.wztitle);
			$("#show_wzyl1").val(news.wzyl1);
			$("#show_wzweight").val(news.wzweight);
			$("#show_wzdianjinnum").val(news.wzdianjinnum);
			$("#content_show").html(news.wztext);
			if(news.wzpicture!=null){
				var str = "";
				var pics = news.wzpicture.split(",");
				for ( var i = 0; i < pics.length; i++) {
					str += "<img src='../upload/"+pics[i]+"' width='100px' height='100px'>&nbsp;";
				}
				$("#show_pic_show").html($(str));
			}
			
			$("#wenzhang_show_Info").dialog("open");
		}, "json");
}

function updatewenzhangInfo(){
	$("#wz_upwztext").val(uee.getContent());
	var formData=new FormData($("#wz_upwz")[0]);
	$.ajax({
		type:"post",
		url:"wenZhang_updateWenzhangInfo",
		processData:false,
		contentType:false,
		async:false,
		cache:false,
		data:formData,
		success:function(data){
			if (data.total =="1") {//说明是成功的
				$.messager.show({title : '成功提示',msg : '文章信息修改成功...',timeout : 2000,showType : 'slide'});
				$("#wenzhang_update_Info").dialog("close");
				$("#wenzhang_info").datagrid("reload");
			} else {
				$.messager.alert("失败提示", "文章信息修改失败...", "error");
			}
		}
	});
}


function addwenzhangInfo(){
	$("#wz_addwztext").val(ue.getContent());
	var formData=new FormData($("#wz_add")[0]);
	$.ajax({
		type:"post",
		url:"wenZhang_addWenzhangInfo",
		processData:false,
		contentType:false,
		async:false,
		cache:false,
		data:formData,
		success:function(data){
			if(data.total=="1"){
				$("#wenzhang_add_Info").dialog("close");
				$("#wenzhang_info").datagrid("reload");
				$.messager.show({title:'成功提示',msg:'文章信息添加成功...',timeout:2000,showType:'slide'});
				$("#glid").val();
				$("#wztypeid").val("");
				$("#wztitle").val("");
				$("#wzyl1").val("");
				$("#wzweight").val("");
				ue.setContent("");
			} else {
				$.messager.alert("失败提示", "文章信息添加失败...", "error");
			}
		},
		error : function(data, status, e) {
			$.messager.alert("错误提示", "文章信息添加有误...\n" + e, "error");
		}
	});
}

</script>