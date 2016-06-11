<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<center id="wzyhpinglun_info" data-options="fit:true"></center>
<script>
var datagrid;
var rows;

$(function(){
	var datagrid;
	var editRow=undefined;
	var op;
	var flag;
	
	datagrid=$('#wzyhpinglun_info').datagrid({   
	    url:'wenZhangPl_getPageWzyhpinglunInfo.action',
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
	        {field:'wzpltime',title:'评论时间',width:100,align:'center',sortable:true}, 
	        {field:'status',title:'评论状态',width:60,align:'center',sortable:true},
	        {field:'_operate',title:'操作',width:100,align:'center',
	        	formatter: function(value,rowData,index){
					return '<a class="icon-search1 icon-padding" href="javascript:showWzyhpinglunDetail(\''+rowData.wzplid+'\')">详细</a>';
				}
			}
	    ]],
	    toolbar:[{
	    	text:"添加",
			iconCls:'icon-add',
			handler:function(){
				$("#wzyhpinglun_add_Info").dialog("open");
				$.messager.show({
					title:'温馨提示',msg:'等待用户评论',timeout:2000,showType:'slide'
				});
			}
		},{
	    	text:"修改",
			iconCls:'icon-edit',
			handler:function(){
				var rows=datagrid.datagrid("getChecked")[0];
	    		if(rows==undefined){
	    			$.messager.show({title:'温馨提示',msg:'您没有选中数据或选中多行数据,请重新选择...',timeout:2000,showType:'slide'});
	    		}else{
				$("#update_wzplid").val(rows.wzplid);
				uee.setContent(rows.wzpltext);
				$("#wzyhpinglun_update_Info").dialog("open");
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
							$.post("wenZhangPl_delWzyhpinglunInfo",{op:"delWzyhpinglunInfo",wzplids:wzplids},function(data){
								if(data.total==1){ //添加成功
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

$.post("wenZhang_getAllWenzhangTitle",function(data){
	var obj=$("#wzid");
	var opt;
	$.each(data.rows,function(index,item){
		opt="<option value='"+item.wzid+"'>"+item.wztitle+"</option>";
		obj.append($(opt));
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
<!--添加-->
<div id="wzyhpinglun_add_Info" class="easyui-dialog" title="添加评论" data-options="fit:true,iconCls:'icon-add',resizable:true,modal:true,closed:true">
	<form action="" style="padding:20px;float:left;display:inline-block;">
		<label>用户编号:</label><input name="yhzcid" id="yhzcid" class="myinput"><br /><br />
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
	var ue = UE.getEditor('editor');
	var uee = UE.getEditor('editors');
	
	function showWzyhpinglunDetail(wzplid) {
		$("#wzyhpinglun_show_Info").dialog("open");
		$.post("wenZhangPl_findPingLunById",{wzplid : wzplid},function(data){
			var news = data.rows[0];
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
		$.post("wenZhangPl_updateWzyhpinglunInfo",{wzpltext:wzpltext,wzplid:wzplid},function(data){
			if (data.total == 1) {//说明是成功的
				$.messager.show({title : '成功提示',msg : '评论信息修改成功...',timeout : 2000,showType : 'slide'});
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
		var wzpltext = ue.getContent();
		$.post("wenZhangPl_addWzyhpinglunInfo",{yhzcid:yhzcid,wzid:wzid,wzpltime:wzpltime,wzpltext:wzpltext},function(data){
			if(data.total==1){
				$.messager.show({title : '成功提示',msg : '评论信息添加成功...',timeout : 2000,showType : 'slide'});
				$("#wzyhpinglun_add_Info").dialog("close");
				$("#wzyhpinglun_info").datagrid("reload");
				
				$("#yhzcid").val("");
				$("#wzid").val("");
				$("#wzpltime").datebox('setValue', '');
				ue.setContent("");
			} else {
				$.messager.alert("失败提示", "评论信息添加失败...", "error");
			}
		});
	}
		
</script>