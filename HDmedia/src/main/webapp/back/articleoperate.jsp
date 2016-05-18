<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<center id="wzyhpinglun_info">
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
<div id="wzyhpinglun_add_Info" class="easyui-dialog" title="添加评论" data-options="fit:true,iconCls:'icon-add',resizable:true,modal:true,closed:true">
	<form action="" style="padding:20px;float:left;display:inline-block;">
		<label>用户编号:</label>
		<select name="yhzcid" id="yhzcid" class="myinput">
			
		</select><br /><br />
		<label>文章标题:</label>
		<select name="wzid" id="wzid" class="myinput">
			
		</select><br /><br />
		<label>评论时间:</label><input name="ndate" id="wzpltime" class="easyui-datetimebox myinput" required/><br /><br />
		<label>评论内容:</label>
		<div>
			<script id="editor" type="text/plain" style="width:800px;height:400px;"></script>
		</div><br/><br/>
		<a href="javascript:addwzyhpinglunInfo()" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加</a>
	</form>
</div>

<!--修改-->
<div id="wzyhpinglun_update_Info" class="easyui-dialog" title="修改评论" data-options="fit:true,iconCls:'icon-add',resizable:true,modal:true,closed:true">
	<form action="" style="padding:20px;float:left;display:inline-block;">
		<label>文章评论编号:</label>
		<input name="wzplid" id="update_wzplid" class="myinput"><br/><br/>
		<label>评论内容:</label>
		
		<div>
			<script id="editors" type="text/plain" style="width:800px;height:400px;"></script>
		</div><br/><br/>
		<a href="javascript:updatewzyhpinglunInfo()" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">确认修改</a>
	</form>
</div>

<!--详细-->
<div id="wzyhpinglun_show_Info" class="easyui-dialog" title="详细评论" data-options="fit:true,iconCls:'icon-add',resizable:true,modal:true,closed:true">
	<form action="" style="padding:20px;float:left;display:inline-block;">
		<label>用户编号:</label><input type="text" id="show_yhzcid" class="myinput"/><br /><br />
		<label>文章标题:</label><input type="text" id="show_wztitle" class="myinput"/><br /><br />
		<label>评论时间:</label><input name="ndate" id="show_wzpltime" class="easyui-datetimebox myinput" required/><br /><br />
		<label>评论状态:</label><input type="text" id="show_status" class="myinput"/><br /><br />
		<label>评论内容:</label>
		<div id="wzyhpinglun_content_show">
			
		</div>
	</form>
</div>

<script>
var datagrid;
var rows;
$.post("../wenzhangServlet",{op:"getAllWenzhangTitle"},function(data){
	var obj=$("#wzid");
	var opt;
	$.each(data.rows,function(index,item){
		opt="<option value='"+item.wzid+"'>"+item.wztitle+"</option>";
		obj.append($(opt));
	});
},"json");

$(function(){
	var datagrid;
	var editRow=undefined;
	var op;
	var flag;
	
	datagrid=$('#wzyhpinglun_info').datagrid({   
	    url:'../wzyhpinglunServlet',
	    queryParams:{op:"getPageWzyhpinglunInfo"},
	    fitColumns:true,
	    striped:true,
	    loadMsg:"数据加载中...",
	    pagination:true,
	    rownumbers:true,
	    sortName:'wzplid',
	    sortOrder:'asc',
	    remoteSort:false,
	    columns:[[ 
			{field:'wzplids',title:'选择',width:100,align:'center',checkbox:true}, 
	        {field:'wzplid',title:'评论编号',width:100,align:'center',sortable:true},   
	        {field:'yhzcid',title:'用户编号',width:100,align:'center',editor:{type:"text",options:{required:true}}},
	        {field:'wztitle',title:'文章标题',width:100,align:'center',editor:{type:"text",options:{required:true}}},
	        {field:'wzpltext',title:'评论内容',width:100,align:'center',editor:{type:"text",options:{required:true}}}, 
	        {field:'wzpltime',title:'评论评论',width:100,align:'center',sortable:true}, 
	        {field:'status',title:'品论状态',width:60,align:'center',sortable:true},
	        {field:'_operate',title:'操作',width:100,align:'center',
	        	formatter: function(value,rowData,index){
	        		//console.info(rowData);
	        		//console.info(JSON.stringify(rowData));
	        		//console.info( JSON.parse(rowData) );
					return '<a class="icon-search1 icon-padding" href="javascript:showWzyhpinglunDetail(\''+rowData.wzplid+'\')">详细</a>';
				}
			}
	    ]],
	    toolbar:[{
	    	text:"添加",
			iconCls:'icon-add',
			handler:function(){
				$.messager.show({
					title:'温馨提示',msg:'等待用户评论',timeout:2000,showType:'slide'
				});
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
				$("#wzyhpinglun_update_Info").dialog("open");
				$("#update_wzplid").val(rows[0].wzplid);
				uee.setContent(rows[0].wzpltext);
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
							var wzplids="";
							for(var i=0;i<rows.length-1;i++){
								wzplids+=rows[i].wzplid+",";
							}
							wzplids+=rows[i].wzplid;
							
							//将要删除wzplid发送到服务器
							$.post("../wzyhpinglunServlet",{op:"delWzyhpinglunInfo",wzplids:wzplids},function(data){
								if(data==1){ //添加成功
									$.messager.show({
										title:'删除提示',
										msg:'评论信息删除成功...',
										timeout:2000,
										showType:'slide'
									});
									datagrid.datagrid("reload"); //重新加载数据一次
								}else{
									$.messager.alert('失败提示','评论信息删除失败...','error');
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
								msg:'评论信息'+flag+'成功...',
								timeout:2000,
								showType:'slide'
							});
						}else{
							$.messager.alert('失败提示','评论信息'+flag+'失败...','error');
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
	
	function showWzyhpinglunDetail(wzplid) {
		$("#wzyhpinglun_show_Info").dialog("open");
		$.post("../wzyhpinglunServlet",{op : "findNewsByNid",wzplid : wzplid},function(data){
			var news = data.rows;
			console.info(news);
			$("#show_yhzcid").val(news.yhzcid);
			$("#show_wztitle").val(news.wztitle);
			$("#show_wzpltime").datetimebox('setValue',news.wzpltime);
			$("#show_status").val(news.status);
			$("#wzyhpinglun_content_show").html(news.wzpltext);
		}, "json");
	}
	
	function updatewzyhpinglunInfo(){
		var wzpltext = uee.getContent();
		var wzplid =$("#update_wzplid").val();
		console.info(wzpltext);
		console.info(wzplid);
		$.post("../wzyhpinglunServlet",{op : "updateWzyhpinglunInfo",wzpltext : wzpltext,wzplid : wzplid},
		function(data){
			if (parseInt($.trim(data)) == 1) {//说明是成功的
				$.messager.show({
					title : '成功提示',
					msg : '评论信息修改成功...',
					timeout : 2000,
					showType : 'slide'
			});
			$("#wzyhpinglun_update_Info").dialog("close");
			$("#wzyhpinglun_info").datagrid("reload");
			$("#update_yhzcid").val("");
			uee.setContent("");
			} else {
				$.messager.alert("失败提示", "评论信息修改失败...", "error");
			}	
		});
	}
	
	function addwzyhpinglunInfo(){
		var yhzcid = $("#yhzcid").val();
		var wzid = $("#wzid").val();
		var wzpltime = $("#wzpltime").datebox('getValue');
		var content = ue.getContent();
		
		$.ajaxFileUpload({
			url : "../wzyhpinglunServlet?op=addWzyhpinglunInfo",
			secureuri : false,
			dataType : "json",
			data : {
				yhzcid : yhzcid,
				wzid :  wzid,
				wzpltime : wzpltime,
				content : content
			},
			success : function(data, status) {
				if (parseInt($.trim(data)) == 1) { //说明是成功的
					$.messager.show({
						title : '成功提示',
						msg : '评论信息添加成功...',
						timeout : 2000,
						showType : 'slide'
					});
					$("#wzyhpinglun_add_Info").dialog("close");
					$("#wzyhpinglun_info").datagrid("reload");
					
					$("#yhzcid").val("");
					$("#wzid").val("");
					$("#wzpltime").datebox('setValue', '');
					ue.setContent("");
				} else {
					$.messager.alert("失败提示", "评论信息添加失败...", "error");
				}
			},
			error : function(data, status, e) {
				$.messager.alert("错误提示", "评论信息添加有误...\n" + e, "error");
			}
		});
	}
</script>