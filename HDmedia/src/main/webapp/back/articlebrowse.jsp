<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<center id="wenzhang_info">
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
<div id="wenzhang_add_Info" class="easyui-dialog" title="添加文章" data-options="fit:true,iconCls:'icon-add',resizable:true,modal:true,closed:true">
	<form action="" style="padding:20px;float:left;display:inline-block;">
		<label>管理员编号:</label><input name="glid" id="glid" class="myinput"><br /><br />
		<label>文 章 类 型:</label>
		<select name="wztypeid" id="wztypeid" class="myinput">
			
		</select><br /><br />
		
		<label>文 章 标 题 :</label><input name="wztitle" id="wztitle" class="myinput" required/><br /><br />
		<label>文 章 图 片 :</label><input type="file" name="wzpicture" id="wzpicture" onchange="previewMultipleImage(this,'pic_show')" required/><br /><br />
		<label>文 章 权 重 :</label><input name="wzweight" id="wzweight" class="myinput" required/><br /><br />
		
		
		<label>文 章 内 容 :</label>
		<div>
			<script id="editor" type="text/plain" style="width:800px;height:200px;"></script>
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
	<form action="" style="padding:20px;float:left;display:inline-block;">
		<label>文章类型:</label>
		<select name="wzname" id="update_wzname" class="myinput">
			
		</select><br /><br />
		
		<label>文章标题:</label><input name="wztitle" id="update_wztitle" class="myinput" required/><br /><br />
		<label>文章图片:</label><input type="file" name="wzpicture" id="update_wzpicture"  required/><br /><br />
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
$.post("../wenzhangTypeServlet",{op:"getAllWenzhangWzname"},function(data){
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
	    url:'../wenzhangServlet',
	    queryParams:{op:"getPageWenzhangInfo"},
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
	        		//console.info(rowData);
	        		//console.info(JSON.stringify(rowData));
	        		//console.info( JSON.parse(rowData) );
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
				rows=datagrid.datagrid("getChecked");
				if(rows.length<=0){
					$.messager.show({
						title:'温馨提示',msg:'请选择您要修改的数据。。。',timeout:2000,showType:'slide'
					});
				}else{
					
					$("#wenzhang_update_Info").dialog("open");
					$("#update_wzname").val(rows[0].wztypeid);
					$("#update_wztitle").val(rows[0].wztitle);
					$("#update_wzweight").val(rows[0].wzweight);
					uee.setContent(rows[0].wztext);
					
					var str = "";

					var pics = rows[0].wzpicture.split(",");
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
							var wzids="";
							for(var i=0;i<rows.length-1;i++){
								wzids+=rows[i].wzid+",";
							}
							wzids+=rows[i].wzid;
							
							//将要删除wzid发送到服务器
							$.post("../wenzhangServlet",{op:"delWenzhangInfo",wzids:wzids},function(data){
								if(data==1){ //添加成功
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
					
					$.post("../wzyhpinglunServlet",rows,function(data){
						data=parseInt( $.trim(data));
						if(data==1){ //添加成功
							$.messager.show({
								title:'成功提示',
								msg:'文章信息'+flag+'成功...',
								timeout:2000,
								showType:'slide'
							});
						}else{
							$.messager.alert('失败提示','文章信息'+flag+'失败...','error');
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
		}]
	});  
});
</script>
<script>
var ue = UE.getEditor('editor');
var uee = UE.getEditor('editors');

function showwenzhangDetail(wzid) {
	$("#wenzhang_show_Info").dialog("open");
	$.post("../wenzhangServlet",{op : "findNewsByWzid",wzid : wzid},
		function(data) {
			var news = data.rows;
			$("#show_glid").val(news.glid);
			$("#show_wzname").val(news.wzname);
			$("#show_wztitle").val(news.wztitle);
			$("#show_wzweight").val(news.wzweight);
			$("#show_wzdianjinnum").val(news.wzdianjinnum);
			$("#content_show").html(news.wztext);

			var str = "";

			var pics = news.wzpicture.split(",");
			for ( var i = 0; i < pics.length; i++) {
				str += "<img src='../"+pics[i]+"' width='100px' height='100px'>&nbsp;";
			}
			$("#show_pic_show").html($(str));
		}, "json");
}

function updatewenzhangInfo(){
	var wzid = rows[0].wzid;
	var wztypeid = $("#update_wzname").val();
	var wztitle = $("#update_wztitle").val();
	var wzweight=$("#update_wzweight").val();
	var wztext = uee.getContent();
	
	$.ajaxFileUpload({
		url : "../wenzhangServlet?op=updateWenzhangInfo",
		secureuri : false,
		fileElementId : "update_wzpicture",
		dataType : "json",
		data : {wzid : wzid,
				wztypeid : wztypeid,
				wztitle : wztitle,
				wzweight : wzweight,
				wztext : wztext
			},
		success : function(data, status) {
			if (parseInt($.trim(data)) == 1) {//说明是成功的
				$.messager.show({
					title : '成功提示',
					msg : '文章信息修改成功...',
					timeout : 2000,
					showType : 'slide'
				});
				$("#wenzhang_update_Info").dialog("close");
				$("#wenzhang_info").datagrid("reload");
			} else {
				$.messager.alert("失败提示", "文章信息修改失败...", "error");
			}
		}
	});
}

function addwenzhangInfo(){
	var glid = $("#glid").val();
	var wztypeid = $("#wztypeid").val();
	var wztitle = $("#wztitle").val();
	var wzweight=$("#wzweight").val();
	
	var wztext = ue.getContent();
	
	
	$.ajaxFileUpload({
		url : "../wenzhangServlet?op=addWenzhangInfo",
		secureuri : false,
		fileElementId : "wzpicture",
		dataType : "json",
		data : {
			glid : glid,
			wztypeid :  wztypeid,
			wztitle : wztitle,
			wzweight : wzweight,
			wztext : wztext
			
		},
		success : function(data, status) {
			if (parseInt($.trim(data)) == 1) { //说明是成功的
				$.messager.show({
					title : '成功提示',
					msg : '文章信息添加成功...',
					timeout : 2000,
					showType : 'slide'
				});
				$("#wenzhang_add_Info").dialog("close");
				$("#wenzhang_info").datagrid("reload");
				$("#glid").val();
				$("#wztypeid").val("");
				$("#wztitle").val("");
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