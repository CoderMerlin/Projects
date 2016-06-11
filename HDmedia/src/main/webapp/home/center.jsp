<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div style="margin: 0; padding:0px; height:10px; width:90%; margin-top:50px; margin-left:5%; float:left;">
    	<img  src="../images/9.jpg" style="margin: 0; padding:0px; width:100%; height:10%; float:left; display:inline;">
    </div><!--横线-->
     <div id="elect_title" style=" margin: 0 5%; padding:0px; width:90%; height:50px; float:left; ">
    	<h2>作品评选</h2> 	
    </div>
<div id="elect"
	style=" margin: 0 5%; padding:0px; width:90%; height:500px; margin-top:10px; float:left; ">
	<div class="text">
		<c:forEach items="${sessionScope.Work_px}" var="item">
			<c:if test="${item.rownum <3}">
				<div class="one" onmouseover="this.style.border='1px solid red'"onmouseout="this.style.border='1px solid #CCC'">
					<div class="title">
						<h1>${ item.works_name}</h1>
					</div>
					<div class="img">
						<div class="img_1">
							<a class="tp" title="${ item.works_name}" target="_blank" href="../worksServlet?op=showWorksByid&worksid=${item.works_id }"> <img
								src="../${ item.works_img }"> </a>
						</div>
					</div>
				</div>
			</c:if>
		</c:forEach>
	</div>
	<div class="px">
		<div class="title">
			<h1>期待您的选择</h1>
			<div
				style="margin: 0; padding:0px; height:10px; width:90%; margin-left:5%; float:left;">
				<img src="images/9.jpg"
					style="margin: 0; padding:0px; width:100%; height:10%; float:left; displainline;">
			</div>
			<!--横线-->
		</div>
		<div class="px_content">
			<form>
				<td>
					<h3>您更欣赏哪一副作品:</h3>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<c:forEach items="${Work_px}" var="item">
					<c:if test="${item.rownum <3}">
						<tr>
							<input type="radio" name="px_1" id="raid_1" onClick="radio_xz_1()"/>${ item.works_name}
						</tr>
					</c:if>
				</c:forEach>
				</td>
				<td>
					<h3>说明您欣赏该作品的优点：</h3>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<td><input type="radio" name="px_2" id="raid_3"
					onClick="radio_xz_3()" />工笔结构严谨,设色典雅秀逸</td>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<td><input type="radio" name="px_3" id="raid_4"
					onClick="radio_xz_4()" />写意气旺神畅,笔墨华滋,浑然天成</td>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<td><input type="radio" name="px_4" id="raid_5"
					onClick="radio_xz_5()" />厚实灵动,相映成趣</td>
				</td>
				<td>
					<h3>您对本网站的意见：</h3>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
					type="text" name="px_5" /></td> <br /> <br />
				<td>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input
					type="button" name="px_4" value="提交">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" name="px_5" value="重置"></td>
			</form>
		</div>
	</div>
</div>
<!--评选功能。。最热书籍评选-->
