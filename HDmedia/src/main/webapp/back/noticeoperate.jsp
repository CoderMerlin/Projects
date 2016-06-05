<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<table id="gonggao_info"></table>
<script>
	var datagrid;


	$(function(){
		var editRow=undefined;
		var op;
		var flag;
		var gonggaostatusObj=[{gongaoid:0,gonggaostatus:'不可用'},{gonggaoid:1,gonggaostatus:'可用'}];
		datagrid=$('#gonggao_info').datagrid({   
		    url:'gongGao_getAllGongGao.action', 
		    queryParams:{op:"getAllGongGao"},
		    fitColumns:true,
		    striped:true,
		    loadMsg:"数据加载中...",
		    pagination:true,
		    rownumbers:true,
		    sortName:'gid',
		    sortOrder:'asc',
		    remoteSort:false,
		    columns:[[   
		        {field:'gids',title:'公告编号',width:100,align:'center',checkbox:true},   
		        {field:'gid',title:'公告编号',width:100,align:'center',sortable:true},
		        {field:'gtitle',title:'公告标题',width:100,align:'center',editor:{type:"text",options:{required:true}}},   
		        {field:'gtime',title:'公告创建时间',width:100,align:'center'},
		        {field:'gtext',title:'公告内容',width:100,align:'center',
		        	formatter:function(value,rowData,index){
		        		//console.info(JSON.stringify(rowDate));  //将对象转换成字符串
		        		//console.info(JSON.parse(rowData));  //将字符串转换成对象
		        		return '<a class="icon-padding2" href="javascript:showgonggaoDetail(\''+rowData.gid+'\')">公告内容</a>';
		        	}
		        },  
		        {field:'gstatus',title:'公告状态',width:100,align:'center',editor:{type:"combobox",options:{
		        	required:true,valueField:'gonggaoid',textField:'gonggaostatus',data:gonggaostatusObj}},
		        	formatter:function(value,row,index){
		        		for(var i=0;i<gonggaostatusObj.length;i++){
		        			if(gonggaostatusObj[i].gonggaoid==value){
		        				return gonggaostatusObj[i].gonggaostatus;
		        			}
		        		}
		        		return value;
		        	}
		        }
		    ]],
			 toolbar:[{   
		        text:"添加",   
		        iconCls:'icon-add',   
		        handler:function(){   
		          	$("#gonggao_add_Info").dialog("open");
		        }
		    },{
		    	text:"修改",
		    	iconCls:'icon-edit',
		    	handler:function(){
		    		//获取要修改的行
		    		var rows=datagrid.datagrid("getChecked")[0];
		    		if(rows==undefined){
		    			$.messager.show({title:'温馨提示',msg:'请选择您要修改的公告编号！',timeout:2000,showType:'slide'});
		    		}else{
		    			//显示要修改的公告内容
		    			$("#gonggao_update_Info").dialog("open");
		    			var gid=rows.gid; //获取要修改的公告id
		    			var ue1=UE.getEditor('editor1');  //打开编辑器
		    			$.post("gongGao_update.action",{gid:gid},function(data){
							var gonggao=data.rows;
							var rows=datagrid.datagrid("getChecked")[0];
							$("#g_title_update").val(gonggao.gtitle);  //公告标题
							$("#g_status_update").val(gonggao.gstatus);  //公告状态
							$("#g_pic_update").html(gonggao.ggyl1);  //公告图片
						
							ue1.setContent(gonggao.gtext);  //公告内容
							
							var str="";
							var pics=gonggao.ggyl1.split(",");
							for(var i=0; i<pics.length;i++){
								str+="<img src='../"+pics[i]+"' width='100px' height='100px' />&nbsp;";
							}
							$("#gonggao_pic_update").html($(str));
						},"json");
		    		}
		    	}
		    },{
		    	text:"删除",
		    	iconCls:'icon-remove',
		    	handler:function(){
		    		//获取所有被选中的行
		    		var rows=datagrid.datagrid("getChecked");
		    		if(rows.length<=0){ //说明没有选中任意一行
		    			$.messager.show({
    						title:'温馨提示',
    						msg:'请选择您要删除的公告编号！',
    						timeout:2000, //时间
    						showType:'slide'
    					});
		    		}else{
		    			$.messager.confirm('信息确认', '您确定要删除所选中的公告信息吗？', function(r){
		    				if (r){
		    					var gids="";
		    					for(var i=0;i<rows.length-1;i++){
		    						gids+=rows[i].gid+",";
		    					}
		    					gids+=rows[i].gid;
		    					//将要删除gid 发送到服务器
		    					$.post("../gongGaoServlet",{op:"delGongGaoInfo",gids:gids},function(data){
				    				if(data==1){ //删除成功
				    					$.messager.show({
				    						title:'删除提示',
				    						msg:'公告信息删除成功..',
				    						timeout:2000, //时间
				    						showType:'slide'
				    					});
				    					datagrid.datagrid("reload");  //重新加载数据一次
				    				}else{ //添加失败
				    					$.messager.alert('失败提示！','公告信息删除失败...','error');
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
						
						$.post("../guanliServlet",rows,function(data){
							data=parseInt( $.trim(data));
							if(data==1){ //添加成功
								$.messager.show({
									title:'成功提示',
									msg:'公告操作信息'+flag+'成功...',
									timeout:2000,
									showType:'slide'
								});
							}else{
								$.messager.alert('失败提示','管理员信息'+flag+'失败...','error');
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

<style>
	.myinput{
		width:200px;
		border:1px solid #F63;
	}
	
	label{
		padding-right:10px;
	}
</style>

<!-- 添加 -->
<div id="gonggao_add_Info" class="easyui-dialog" title="添加公告" data-options="fit:true,iconCls:'icon-add',resizable:true,modal:true,closed:true">
	<form action="" style="padding:20px;float:lect;display:inline-block;">
		<label>公告标题:</label><input type="text" name="gtitle" id="g_title"  class="myinput"/>
		<label>公告状态:</label>
			<select id="g_status" class="easyui-combobox" name="gstatus" style="width:200px;"> 
				<option value="1">可用</option>  
				<option value="0">不可用</option>
			</select><br /><br /> 
		<label>公告图片:</label><input type="file" name="ggyl1" id="g_pic" multiple="multiple" onchange="previewMultipleImage(this,'gonggao_pic_show')"/><br /><br />
		<label>公告内容:</label>
		<div>
			<script id="editor" type="text/javascript" style="width:800px;height:400px;"></script>
		</div><br /><br />
		<a href="javascript:addgonggaoInfo()" class="easyui-linkbutton" data-options="iconCls:'icon-add'">添加</a>
	</form>
	<div style="float:right;width:380px; margin-right:20px;">
		<fieldset id="gonggao_pic_show" >
			<legend>图片预览</legend>
			
		</fieldset>
	</div>
</div>

<!-- 修改 -->
<div id="gonggao_update_Info" class="easyui-dialog" title="修改公告" data-options="fit:true,iconCls:'icon-add',resizable:true,modal:true,closed:true">
	<form action="" style="padding:20px;float:lect;display:inline-block;">
		<label>公告标题:</label><input type="text" name="gtitle" id="g_title_update"  class="myinput"/>
		<label>公告状态:</label>
		<select id="g_status_update" class="easyui-combobox" name="gstatus" style="width:200px;"> 
				<option value="1">可用</option>  
				<option value="0">不可用</option>
			</select><br /><br /> 
		<label>公告图片:</label><input type="file" name="ggyl1" id="g_pic_update" multiple="multiple" onchange="previewMultipleImage(this,'gonggao_pic_update')"/><br /><br />
		<label>公告内容:</label>
		<div>
			<script id="editor1" type="text/javascript" name="gtext" style="width:800px;height:400px;"></script>
		</div><br /><br />
		<a href="javascript:updategonggaoInfo()" class="easyui-linkbutton" data-options="iconCls:'icon-add'">保存修改</a>
	</form>
	<div style="float:right;width:380px; margin-right:20px; margin-top:20px;">
		<fieldset id="gonggao_pic_update" >
		</fieldset>
	</div>
</div>

<script>
	//添加公告信息
	var ue=UE.getEditor('editor');
	function addgonggaoInfo(){
		var gtitle=$("#g_title").val();  //公告标题
		var gstatus=$("#g_status").combobox('getValue'); //公告状态
		var ggyl1=$("#g_pic").val();  //公告图片
		var gtext=ue.getContent();  //公告内容
		$.ajaxFileUpload({
			url:"gongGao_add.action",
			secureuri:false,
			fileElementId:"g_pic",
			dataType:"json",
			data:{gtitle:gtitle,gstatus:gstatus,gtext:gtext},
			success:function(data,status){
				if(parseInt($.trim(data))==1){//说明成功
					$.messager.show({title:'成功提示',msg:'公告信息添加成功！',timeout:2000,showType:'slide'});
					$("#gonggao_add_Info").dialog("close");
					$("#gonggao_info").datagrid("reload");
					$("#g_title").val("");
					$("#g_pic").val("");
					$("#gonggao_pic_show").html("");
					ue.getContent();
					
				}else{
					$.messager.alert("失败提示","公告信息添加失败！","error");
				}
			},
			error:function(data,status,e){
				$.messager.alert("错误提示！","公告信息添加有误！\n"+e,"error");
			}
		});
	}


	//修改公告信息
	var ue1=UE.getEditor('editor1');
	function updategonggaoInfo(){
		var rows=datagrid.datagrid("getChecked")[0];
		var gid=rows.gid;
		var gtitle=$("#g_title_update").val();  //公告标题
		var gstatus=$("#g_status_update").combobox('getValue'); //公告状态
		var gtext=ue1.getContent();  //公告内容
		console.info(gstatus);
		$.ajaxFileUpload({
			url:"../gongGaoServlet?op=updateGongGaoInfo",
			secureuri:false,
			fileElementId:"g_pic_update",
			dataType:"json",
			data:{gid:gid,gtitle:gtitle,gstatus:gstatus,gtext:gtext},
			success:function(data,status){
				if(parseInt($.trim(data))==1){//说明成功
					$.messager.show({title:'成功提示',msg:'新闻信息修改成功！',timeout:2000,showType:'slide'});
					$("#gonggao_update_Info").dialog("close");
					$("#gonggao_info").datagrid("reload");
				}else{
					$.messager.alert("失败提示","公告信息修改失败！","error");
				}
			},
			error:function(data,status,e){
				$.messager.alert("错误提示！","公告信息修改有误！\n"+e,"error");
			}
		});
	}
	
	
</script>