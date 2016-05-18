<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<table id="luntan_info"></table>
<script>
var datagrid;
var ltid;
	$(function(){
		var editRow=undefined;
		var op;
		var flag;
		var luntanstatusObj=[{luntanid:0,luntanstatus:'不可用'},{luntanid:1,luntanstatus:'可用'}];
		datagrid=$('#luntan_info').datagrid({   
		    url:'../lunTanServlet', 
		    queryParams:{op:"getPageLunTanInfo"},
		    fitColumns:true,
		    striped:true,
		    loadMsg:"数据加载中...",
		    pagination:true,
		    rownumbers:true,
		    sortName:'ltid',
		    sortOrder:'asc',
		    remoteSort:false,
		    columns:[[   
		        {field:'ltids',title:'论坛编号',width:100,align:'center',checkbox:true},   
		        {field:'ltid',title:'论坛编号',width:100,align:'center',sortable:true},
		        {field:'ltname',title:'论坛名称',width:100,align:'center',editor:{type:"text",options:{required:true}}},   
		        {field:'ltyl1',title:'论坛创建类型',width:100,align:'center',editor:{type:"text",options:{required:true}}},
		        {field:'lttime',title:'论坛创建时间',width:100,align:'center'},
		        {field:'_operate',title:'论坛图片',width:100,align:'center',
		        	formatter:function(value,rowData,index){
	        			if(rowData.ltyl2==null || rowData.ltyl2==""){
	        				
			        		return '<a class="icon-search1 icon-padding" href="javascript:add_luntanpicture(\''+rowData.ltid+'\')">添加</a>';
	        			}else{
	        				return '<a class="icon-search1 icon-padding" href="javascript:look_luntanpicture(\''+rowData.ltid+'\')">查看</a>';
	        			}
		        	}
		        },  
		        {field:'ltstatus',title:'论坛栏目状态',width:100,align:'center',editor:{type:"combobox",options:{
		        	required:true,valueField:'luntanid',textField:'luntanstatus',data:luntanstatusObj}},
		        	formatter:function(value,row,index){
		        		for(var i=0;i<luntanstatusObj.length;i++){
		        			if(luntanstatusObj[i].luntanid==value){
		        				return luntanstatusObj[i].luntanstatus;
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
		           op="addLunTanInfo"; //当前的操作
		           flag="添加";
		           //先判断又没有正在编辑的行
		           if(editRow!=undefined){//说明有行正在被编辑，则还原当前的修改
		        		datagrid.datagrid("rejectChanges");  //回滚自创建以来或上次调用AcceptChanges，所有的变化数据
		        		datagrid.datagrid("endEdit",editRow); //关闭当前正在编辑的行
		        		editRow=undefined;
		           }else{  //在表格的最前面添加一行
		        	   datagrid.datagrid("insertRow",{index:0,row:{}});
		           	   //打开编辑器
		           	   datagrid.datagrid("beginEdit",0);
		           	   editRow=0;  //记录当前正在被编辑的行
		           }
		        }
		    },{
		    	text:"修改",
		    	iconCls:'icon-edit',
		    	handler:function(){
		    		//获取要修改的行
		    		var rows=datagrid.datagrid("getChecked")[0];
		    		luntan=rows;
		    		if(rows==undefined){
		    			$.messager.show({title:'温馨提示',msg:'请选择您要修改的论坛名称！',timeout:2000,showType:'slide'});
		    		}else{
		    			op="updateLunTanInfo";
		    			flag="修改";
		    			if(editRow!=undefined){ //说明有行正在被编辑，则还原当前的修改
		    				datagrid.datagrid("rejectChanges");  //回滚自创建以来或上次调用AcceptChanges，所有的变化数据
			        		datagrid.datagrid("endEdit",editRow); //关闭当前正在编辑的行
			        		editRow=undefined;
		    			}else{
		    				//获取当前选择行的索引
		    				var index=datagrid.datagrid("getRowIndex",rows);
		    				
		    				datagrid.datagrid("updateRow",{index:index,row:rows});
		    				
		    				//打开编辑器
		    				datagrid.datagrid("beginEdit",index);
		           	   		editRow=index;  //记录当前正在被编辑的行
		    			}
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
    						msg:'请选择您要删除的用户注册信息！',
    						timeout:2000, //时间
    						showType:'slide'
    					});
		    		}else{
		    			$.messager.confirm('信息确认', '您确定要删除所选中的用户注册信息吗？', function(r){
		    				if (r){
		    					var ltids="";
		    					for(var i=0;i<rows.length-1;i++){
		    						ltids+=rows[i].ltid+",";
		    					}
		    					ltids+=rows[i].ltid;
		    					//将要删除ltid 发送到服务器
		    					$.post("../lunTanServlet",{op:"delLunTanInfo",ltids:ltids},function(data){
				    				if(data==1){ //删除成功
				    					$.messager.show({
				    						title:'删除提示',
				    						msg:'论坛栏目信息删除成功..',
				    						timeout:2000, //时间
				    						showType:'slide'
				    					});
				    					datagrid.datagrid("reload");  //重新加载数据一次
				    				}else{ //添加失败
				    					$.messager.alert('失败提示！','论坛栏目信息删除失败...','error');
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
		    		if(rows==undefined){//说明当前没有被编辑
		    			datagrid.datagrid("rejectChanges");  //回滚自创建以来或上次调用AcceptChanges，所有的变化数据
		        		datagrid.datagrid("unselectAll"); //关闭当前正在编辑的行
		        		editRow=undefined;
		    		}else{
		    			rows["op"]=op;
		    			
		    			$.post("../lunTanServlet",rows,function(data){
		    				data=parseInt( $.trim(data) );
		    				if(data==1){ //添加成功
		    					$.messager.show({
		    						title:'成功提示',
		    						msg:'论坛栏目信息'+flag+'成功..',
		    						timeout:2000, //时间
		    						showType:'slide'
		    					});
		    				}else{ //添加失败
		    					$.messager.alert('失败提示！','论坛栏目信息'+flag+'失败...','error');
		    				}
		    				rows=null;
		    				datagrid.datagrid("reload");  //重新加载数据一次
		    				editRow=undefined;
		    				datagrid.datagrid("rejectChanges");  //回滚自创建以来或上次调用AcceptChanges，所有的变化数据
			        		datagrid.datagrid("unselectAll"); //关闭当前正在编辑的行
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
	
	
	//添加论坛图片
	function add_luntanpicture(ltid1){
		ltid=ltid1;
	  $("#luntanpicture_add").dialog("open");
	  
	}
	
	
	
	function addLtpic(){
		$.ajaxFileUpload({
		  	url:"../lunTanServlet?op=addLunTanPicture",
		  	secureuri:false,
		  	fileElementId:"lt_ltpic",
		  	dataType:"json",
		  	data:{ltid:ltid},
		  	success:function(data,status){
		  		if(data==1){
		  			$.messager.show({title:'成功提示',msg:'图片添加成功...',timeout:2000,showType:'slide'});
		  			$("#luntanpicture_add").dialog("close");
		  			datagrid.datagrid("reload");  //重新加载数据一次datagrid.datagrid("reload");  //重新加载数据一次
		  			$("#lt_ltpic").val("");
		  		}else{
		  			$.messager.alert("失败提示","图片添加失败...","error");
		  		}
		  	}
	  });
	}
	
	//查看
	function look_luntanpicture(ltid2){
		ltid=ltid2;		
		 $("#luntanpicture_look").dialog("open");
		 $.post("../lunTanServlet",{op:"findltpic",ltid:ltid2},function(data){
		 	var luntan=data.rows;
		 	var str="";
		 	str="<img src='../../uploadPic/"+luntan.ltyl2+"' width='100px' height='100px' />&nbsp;";
		 	$("#luntan_img_look").html($(str));  //论坛图片
		 },"json");
	}
	
	//修改
	function updateLtpic(){
		var pic=$("#lt_ltpic1").val();
		if(pic==null || pic==""){
			$.messager.alert("失败提示","未选定要修改的图片!","error");
		}else{
			$.ajaxFileUpload({
		  	url:"../lunTanServlet?op=updateLunTanPicture",
		  	secureuri:false,
		  	fileElementId:"lt_ltpic1",
		  	dataType:"json",
		  	data:{ltid:ltid},
		  	success:function(data,status){
		  		if(data==1){
		  			$.messager.show({title:'成功提示',msg:'图片添加成功...',timeout:2000,showType:'slide'});
		  			$("#luntanpicture_look").dialog("close");
		  			datagrid.datagrid("reload");  //重新加载数据一次datagrid.datagrid("reload");  //重新加载数据一次
		  			$("#lt_ltpic1").val("");
		  		}else{
		  			$.messager.alert("失败提示","图片添加失败...","error");
		  		}
		  	}
	  });
		
		}
	}
	
	
	//关闭
	function close(){
		$("#luntanpicture_look").dialog("close");
	}	
	
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
<div id="luntanpicture_add" class="easyui-dialog" title="添加论坛图片"  data-options="iconCls:'icon-add',resizable:true,modal:true,closed:true" style="width:500px; height:400px">
	<form style="padding:20px;float:left;display:inline-block;">
		<label>帖子图片:</label><input type="file" name="ltpic" id="lt_ltpic" multiple="multiple" onchange="previewMultipleImage(this,'luntan_img_show')"/><br /><br />
		<a href="javascript:addLtpic()" class="easyui-linkbutton" data-option="iconCls:'icon-add'">添加</a>
	</form>
	
	<div style="float:right;width:380px; margin-right:20px;">
		<fieldset id="luntan_img_show">
			<legend>图片预览</legend>
		</fieldset>
	</div>
</div>

<div id="luntanpicture_look" class="easyui-dialog" title="查看论坛图片" data-options="iconCls:'icon-add',resizable:true,modal:true,closed:true" style="width:350px; height:300px">
	<form style="padding-left:100px; padding-top:20px; padding-buttom:20px;float:left;display:inline-block;">
		<label>帖子图片:</label><input type="file" name="ltpic" id="lt_ltpic1" multiple="multiple" onchange="previewMultipleImage(this,'luntan_img_look')"/><br /><br />
		<a href="javascript:close()" class="easyui-linkbutton" data-option="iconCls:'icon-add'">关闭</a>
		<a href="javascript:updateLtpic()" class="easyui-linkbutton" data-option="iconCls:'icon-add'">修改</a>
	</form>
	
	<div style="float:left;width:100px; padding-left:100px;" >
		<fieldset id="luntan_img_look">
			<legend>图片预览</legend>
		</fieldset>
	</div>
</div>

