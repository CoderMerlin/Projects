<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!-- <script type="text/javascript" src="../ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="../ueditor/ueditor.all.min.js"></script>
<script type="text/javascript" src="../ueditor/lang/zh-cn/zh-cn.js"></script> -->
<!doctype html>
<html>
<head>
    <meta charset="utf-8">
    <title>帖子模板</title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/> <!--默认为屏幕的宽度-->
    <link rel="short icon" href="Hd_index_img/hd_01.png"/>  <!--小图标，需要浏览器兼-->
    <link rel="stylesheet" href="css/Hd_nav.css" />    <!--导航栏css-->
    <link rel="stylesheet" href="css/footer.css" />    <!--尾部-->
    <link rel="stylesheet" href="css/invitation_template_main.css" /> <!--帖子的主要样式-->
    <script type="text/javascript" src="js/invitationlook.js"></script>
    <!--编译器 -->
	<script type="text/javascript" src="ueditor/ueditor.config.js"></script>
	<script type="text/javascript" src="ueditor/ueditor.all.min.js"></script>
	<script type="text/javascript" src="ueditor/lang/zh-cn/zh-cn.js"></script>
	<script type="text/javascript" src="../js/ajaxfileupload.js"></script>
	<script type="text/javascript" src="../js/showpic.js"></script>
</head>
<body>
<%@ include file="header.jsp" %>
	<div id="tiezi_add_tiezi" style="width:70%"  class="easyui-dialog" title="添加帖子信息">
		<form action="tieZis_add.action?ltid=${sessionScope.ltid }" method="post" style="padding:20px;float:left;" enctype="multipart/form-data">
		    <label>帖子标题:</label><input type="text" name="tzname" id="tzname" class="myinput">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		    <label>帖子摘要:</label><input type="text" name="tzzy" id="tzzy" class="myinput" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<label>帖子图片:</label><input type="file" name="upload" id="tzphoto" multiple="multiple" onchange="previewMultipleImage(this,'tiezi_img_show')"/><br /><br />
			<div style="float:right;width:100%;">
			<fieldset id="tiezi_img_show">
				<legend>图片预览</legend>
			</fieldset>
			</div>
			<label>帖子内容:</label>
			<div>
				<script id="editor" name="tztext" type="text/plain" style="width:800px;height:400px;"></script>
			</div><br/><br/>
			<input type="submit" value="确定发表">
			<!-- <a href="javascript:addTiezi()" class="easyui-linkbutton" data-option="iconCls:'icon-add'">发表</a> -->
			
		</form>
		
		
	</div>
<%@ include file="footer.jsp" %>

</body>
</html>

<script>
var uelcw=UE.getEditor("editor");
</script>