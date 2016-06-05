<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<table id="yonghu_info"></table>
<script>
	var datagrid;
	$(function(){
		var editRow=undefined;
		var op;
		var flag;
		var yonghustatusObj=[{yonghuid:0,yonghuname:'不可用'},{yonghuid:1,yonghuname:'可用'}];
		datagrid=$('#yonghu_info').datagrid({   
		    url:'yongHuZC_getPageYongHuZCInfo.action', 
		    fitColumns:true,
		    striped:true,
		    loadMsg:"数据加载中...",
		    pagination:true,
		    rownumbers:true,
		    sortName:'yhzcid',
		    sortOrder:'asc',
		    remoteSort:false,
		    columns:[[   
		        {field:'yhzcids',title:'用户注册编号',width:100,align:'center',checkbox:true},   
		        {field:'yhzcid',title:'用户注册编号',width:100,align:'center',sortable:true},
		        {field:'yhname',title:'用户姓名',width:100,align:'center',editor:{type:"text",options:{required:true}}},   
		        {field:'yhemail',title:'用户邮箱',width:100,align:'center',editor:{type:"text",options:{required:true}}},  
		        {field:'yhpwd',title:'用户密码',width:100,align:'center',editor:{type:"text",options:{required:true}}},
		        {field:'yhzctime',title:'用户注册时间',width:100,align:'center'},
		        {field:'_operate',title:'操作',width:100,align:'center',
		        	formatter:function(value,rowData,index){
		        		//console.info(JSON.stringify(rowDate));  //将对象转换成字符串
		        		//console.info(JSON.parse(rowData));  //将字符串转换成对象
		        		return '<a class="icon-search1 icon-padding" href="javascript:showyonghuDetail(\''+rowData.yhzcid+'\')">详细</a>';
		        	}
		        },
		         {field:'yhzcstatus',title:'用户状态',width:100,align:'center',editor:{type:"combobox",options:{
		        	required:true,valueField:'yonghuid',textField:'yonghuname',data:yonghustatusObj}},
		        	formatter:function(value,row,index){
		        		for(var i=0;i<yonghustatusObj.length;i++){
		        			if(yonghustatusObj[i].yonghuid==value){
		        				return yonghustatusObj[i].yonghuname;
		        			}
		        		}
		        		return value;
		        	}
		        }
		    ]],
			 toolbar:[{   
		        text:"修改或填写详细信息",   
		        iconCls:'icon-edit',   
		        handler:function(){   
		        	var rows=datagrid.datagrid("getChecked")[0];  //获取要添加的行
		        	if(rows==undefined){
		    			$.messager.show({title:'温馨提示',msg:'请选择您要添加或修改详细信息的用户！',timeout:2000,showType:'slide'});
		    		}else{
		    			//获取当前选择行的索引
		    			var yhzcid=rows.yhzcid;  //获取要修改的用户id
		    			$.post("yongHu_findByYongHuZCById.action",{yhzcid:yhzcid},function(data){
		    				var yh=data.yh;
							if(yh==null){
								$("#yonghu_add_yonghusInfo").dialog("open");
								$("#yh_zcid_add").val(yhzcid);//用户注册编号
								$("#yh_zsname_add").val("");
								$("#yh_sex_add").val("");
								$("#yh_phone_add").val("");
								$("#yh_indentity_add").val("");
								$("#yh_qq_add").val("");
								$("#yh_birthday_add").datebox('setValue','');
								$("#yh_addr_add").val("");
								$("#yh_ftnum_add").val("0");
								$("#yh_jf_add").val("0");
								$("#yh_qd_add").val("0");
								$("#yh_jy_add").val("0");
								$("#yh_qianming_add").val("");
								$("#yonghu_photo_show").html(""); //用户头像
							}else{
								$("#yonghu_update_yonghusInfo").dialog("open");
								$("#yh_zcid_update").val(yhzcid);//用户注册编号
								$("#yh_id_update").val(yh.yhid);//用户编号
								$("#yh_zsname_update").val(yh.yhzsname);  //用户真实姓名 
								$("#yh_sex_update").val(yh.yhsex);   //用户性别
						 		$("#yh_age_update").val(yh.yhage);   //用户年龄
								$("#yh_phone_update").val(yh.phone);  //用户手机号
								$("#yh_indentity_update").val(yh.yhindentity); //用户身份证
								$("#yh_qq_update").val(yh.yhqq);  //用户qq号
								$("#yh_birthday_update").datebox('setValue',yh.yhbirthday);  //用户生日
								$("#yh_addr_update").val(yh.yhaddr);  //用户地址
								$("#yh_ftnum_update").val(yh.yhftnum);  //用户发帖次数
								$("#yh_jf_update").val(yh.yhjf);  //用户积分
								$("#yh_qd_update").val(yh.yhqd);  //用户签到
								$("#yh_jy_update").val(yh.yhjy);  //用户用户经验
								$("#yh_qianming_update").val(yh.yhqianming); //用户签名
								
								var str="";
								c
								$("#yonghu_photo_show").html($(str)); //用户头像
							}
							
						},"json");
		    			$("#yonghu_update_yonghusInfo").dialog("open");
		    		}
		        }
		    }]   
		});	
		
		
		
		
	});






	//显示用户详细信息
	function showyonghuDetail(yhzcid){
		$("#yonghu_show_yonghusInfo").dialog("open");
		$.post("../yongHuServlet",{op:"findYonghuByYHZCid",yhzcid:yhzcid},function(data){
			var yonghu=data.rows;
			if(yonghu==undefined){
				alert("该用户没有填写详细信息,请先填写详细信息,再查看！");
				$("#yh_zsname_show").val();  //用户真实姓名 
				$("#yh_sex_show").val();    //用户性别
				$("#yh_email_show").val(); //用户邮箱
				$("#yh_age_show").val();   //用户年龄
				$("#yh_phone_show").val();  //用户手机号
				$("#yh_indentity_show").val(); //用户身份证
				$("#yh_qq_show").val();  //用户qq号
				$("#yh_birthday_show").val();  //用户生日
				$("#yh_addr_show").val();  //用户地址
				$("#yh_ftnum_show").val();  //用户发帖次数
				$("#yh_jf_show").val();  //用户积分
				$("#yh_qd_show").val();  //用户签到
				$("#yh_jy_show").val();  //用户用户经验
				$("#yh_qianming_show").html("");  //用户签名
				$("#yh_photo_show_info").html(""); //用户头像
				$("#yonghu_show_yonghusInfo").dialog("close");   //关闭当前显示的窗口
			}else{
				$("#yh_zsname_show").val(yonghu.yhzsname);  //用户真实姓名 
				$("#yh_sex_show").val(yonghu.yhsex);  //用户性别
				$("#yh_age_show").val(yonghu.yhage);   //用户年龄
				$("#yh_phone_show").val(yonghu.yhphone);  //用户手机号
				$("#yh_indentity_show").val(yonghu.yhindentity); //用户身份证
				$("#yh_qq_show").val(yonghu.yhqq);  //用户qq号
				$("#yh_birthday_show").val(yonghu.yhbirthday);  //用户生日
				$("#yh_addr_show").val(yonghu.yhaddr);  //用户地址
				$("#yh_ftnum_show").val(yonghu.yhftnum);  //用户发帖次数
				$("#yh_jf_show").val(yonghu.yhjf);  //用户积分
				$("#yh_qd_show").val(yonghu.yhqd);  //用户签到
				$("#yh_jy_show").val(yonghu.yhjy);  //用户用户经验
				$("#yh_qianming_show").val(yonghu.yhqianming);  //用户签名
				var str="";
				str="<img src='../../uploadPic/"+yonghu.yhphoto+"' width='100px' height='100px' />&nbsp;";
				$("#yh_photo_show_info").html($(str)); //用户头像
			}		
		},"json");
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

<!-- 添加或修改用户-->
<div id="yonghu_add_yonghusInfo" class="easyui-dialog" title="添加用户" data-options="fit:true,iconCls:'icon-add',resizable:true,modal:true,closed:true">
	<form action="yongHu_addYongHuInfo" method="post" style="padding:20px;float:lect;display:inline-block;" enctype="multipart/form-data">
       	<input type="hidden" name="yhzcid" id="yh_zcid_add"/>
        <label>用户真实姓名:</label><input type="text" name="yhzsname" id="yh_zsname_add"  class="myinput"/>&nbsp;&nbsp;&nbsp;
		<label>用户性别:</label>
			<select id="yh_sex_add" class="easyui-combobox" name="yhsex" style="width:200px;"> 
				<option value="男">男</option>  
				<option value="女">女</option>
			</select> &nbsp;&nbsp;&nbsp;
		<label>用户年龄:</label><input type="text" name="yhage" id="yh_age_add"   class="easyui-numberspinner" style="width:80px;"  data-options="min:6,max:150,editable:false">  <br /><br />
		<label>用户手机号码:</label><input  class="easyui-numberbox myinput" name="yhphone" id="yh_phone_add" />&nbsp;&nbsp;&nbsp;
		<label>用户qq:&nbsp;&nbsp;&nbsp;</label><input class="easyui-numberbox myinput" name="yhqq" id="yh_qq_add" />&nbsp;&nbsp;&nbsp;&nbsp;
		<label>用户生日:</label><input type="text" name="yhbirthday" id="yh_birthday_add" class="easyui-datebox"/><br /><br />
		
		<label>用户身份证号:</label><input type="text" name="yhindentity" id="yh_indentity_add"  class="myinput"/>&nbsp;&nbsp;&nbsp;
		<label>用户地址:&nbsp;</label><input type="text" name="yhaddr" id="yh_addr_add"  class="myinput"/>&nbsp;&nbsp;&nbsp;&nbsp;
		<label>用户头像:</label><input type="file" name="upload" id="yh_photo_add" onchange="previewMultipleImage(this,'yonghu_photo_show')"/><br /><br />
		
		<label>用户发帖次数:</label><input class="easyui-numberbox myinput" name="yhftnum" id="yh_ftnum_add" /><br /><br />
		<label>用户签到次数:</label><input lass="easyui-numberbox myinput" name="yhqd" id="yh_qd_add"  /><br /><br />
		
		<label>用户积分:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</label><input lass="easyui-numberbox myinput" name="yhjf" id="yh_jfv"  /><br /><br />
		<label>用户经验:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</label><input lass="easyui-numberbox myinput" name="yhjy" id="yh_jy_add" /><br /><br />
		<label>用户签名:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</label><input type="textarea" name="yhqianming" id="yh_qianming_add"  style="maxlength=140;warp=soft;"/><br /><br />
		
 		<!-- <div id="add_yonghu" style="display:block;">
			<a href="javascript:addyonghuInfo()" class="easyui-linkbutton" data-options="iconCls:'icon-add'" >添加</a>
		</div><br/> -->
		<input type="submit" value="添加" />
	</form>
	
	<div style="float:right;width:380px; margin-right:20px;">
		<fieldset id="yonghu_photo_show" >
			<legend>头像预览</legend>
		</fieldset>
	</div>
</div>

<div id="yonghu_update_yonghusInfo" class="easyui-dialog" title="修改用户" data-options="fit:true,iconCls:'icon-add',resizable:true,modal:true,closed:true">
	<form action="yongHu_updateYongHuInfo" method="post" style="padding:20px;float:lect;display:inline-block;" enctype="multipart/form-data">
		<input type="hidden" name="yhzcid" id="yh_zcid_update"/>
		<label>用户真实姓名:</label><input type="text" name="yhzsname" id="yh_zsname_update"  class="myinput"/>&nbsp;&nbsp;&nbsp;
		<label>用户性别:</label>
			<select id="yh_sex_update" class="easyui-combobox" name="yhsex" style="width:200px;"> 
				<option value="男">男</option>  
				<option value="女">女</option>
			</select> &nbsp;&nbsp;&nbsp;
		<label>用户年龄:</label><input type="text" name="yhage" id="yh_age_update"   class="easyui-numberspinner" style="width:80px;"  required="required" data-options="min:6,max:150,editable:false">  <br /><br />
		<label>用户手机号码:</label><input  class="easyui-numberbox myinput" name="yhphone" id="yh_phone_update" />&nbsp;&nbsp;&nbsp;
		<label>用户qq:&nbsp;&nbsp;&nbsp;</label><input class="easyui-numberbox myinput" name="yhqq" id="yh_qq_update" />&nbsp;&nbsp;&nbsp;&nbsp;
		<label>用户生日:</label><input type="text" name="yhbirthday" id="yh_birthday_update" class="easyui-datebox" /><br /><br />
		
		<label>用户身份证号:</label><input type="text" name="yhindentity" id="yh_indentity_update"  class="myinput"/>&nbsp;&nbsp;&nbsp;
		<label>用户地址:&nbsp;</label><input type="text" name="yhaddr" id="yh_addr_update"  class="myinput"/>&nbsp;&nbsp;&nbsp;&nbsp;
		<label>用户头像:</label><input type="file" name="upload" id="yh_photo_update"  onchange="previewMultipleImage(this,'yonghu_photo_show_update')"/><br /><br />
		
		<label>用户发帖次数:</label><input class="easyui-numberbox myinput" name="yhftnum" id="yh_ftnum_update" /><br /><br />
		<label>用户签到次数:</label><input lass="easyui-numberbox myinput" name="yhqd" id="yh_qd_update"  /><br /><br />
		
		<label>用户积分:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</label><input lass="easyui-numberbox myinput" name="yhjf" id="yh_jf_update"  /><br /><br />
		<label>用户经验:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</label><input lass="easyui-numberbox myinput" name="yhjy" id="yh_jy_update" /><br /><br />
		<label>用户签名:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</label><input type="textarea" name="yhqianming" id="yh_qianming_update"  style="maxlength=140;warp=soft;"/><br /><br />
		
		<!-- <div id="update_yonghu" style="display:block;">
			<a href="javascript:updateyonghuInfo()" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">保存修改</a>
		</div> -->
		<input type="submit" value="修改" />
	</form>
	<div style="float:right;width:380px; margin-right:20px;">
		<fieldset id="yonghu_photo_show_update" >
			<legend>头像预览</legend>
		</fieldset>
	</div>
</div>


<!-- 详细 -->
<div id="yonghu_show_yonghusInfo" class="easyui-dialog" title="用户详细信息" data-options="fit:true,iconCls:'icon-add',resizable:true,modal:true,closed:true">
	<form action="" style="padding:20px;float:lect;display:inline-block;">
		<label>用户真实姓名:</label><input type="text" name="yhzsname" id="yh_zsname_show"  class="myinput" readonly="readonly" />&nbsp;&nbsp;&nbsp;
		<label>用户年龄:</label><input type="text" name="yhage" id="yh_age_show"  class="myinput" readonly="readonly"/>&nbsp;&nbsp;&nbsp;
		<label>用户积分:</label><input type="text" name="yhjf" id="yh_jf_show"  class="myinput" readonly="readonly"/><br /><br />
		
		<label>用户手机号码:</label><input class="easyui-numberbox myinput" name="yhphone" id="yh_phone_show" readonly="readonly"/>&nbsp;&nbsp;&nbsp;
		<label>用户性别:</label><input type="text" name="yhsex" id="yh_sex_show" class="myinput" readonly="readonly" />&nbsp;&nbsp;&nbsp;
		<label>用户签到:</label><input type="text" name="yhqd" id="yh_qd_show"  class="myinput" readonly="readonly"/><br /><br />
		
		<label>用户身份证号:</label><input type="text" name="yhindentity" id="yh_indentity_show"  class="myinput" readonly="readonly"/>&nbsp;&nbsp;&nbsp;
		<label>用户生日:</label><input type="text" name="yhbirthday" id="yh_birthday_show"  class="myinput" readonly="readonly"/>&nbsp;&nbsp;&nbsp;
		<label>用户经验:</label><input type="text" name="yhjy" id="yh_jy_show"  class="myinput" readonly="readonly"/><br /><br />
		
		
		<label>用户发帖次数:</label><input type="text" name="yhftnum" id="yh_ftnum_show"  class="myinput" readonly="readonly"/>&nbsp;&nbsp;&nbsp;
		<label>用户地址:</label><input type="text" name="yhaddr" id="yh_addr_show"  class="myinput" readonly="readonly"/>&nbsp;&nbsp;&nbsp;
		<label>用户qq:&nbsp;&nbsp;</label><input type="text" name="yhqq" id="yh_qq_show"  class="myinput" readonly="readonly"/><br /><br />
		
		<label>用户签名:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</label><input type="text" name="yhqianming" id="yh_qianming_show"  readonly="readonly"/><br /><br />
		
	</form>
	<div style="float:right;width:380px;margin-right:20px; margin-top:20px;" id="yh_photo_show_info">  
		
	</div>
</div>


