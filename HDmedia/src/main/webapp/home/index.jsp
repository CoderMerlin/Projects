<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<!doctype html>
<html>
<head>
<base href="/HDmedia/">
<title>弘道传媒</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1"/> <!--默认为屏幕的宽度-->
<link rel="shortcut icon" type="image/x-icon" href="images/hd_01.png"> <!--小图标，需要浏览器兼容-->


<link rel="stylesheet" href="home/css/footer.css" />
<link rel="stylesheet" href="home/css/Hd_nav.css" />
<link rel="stylesheet" href="home/css/mainPic.css" />

 
 
 
<script type="text/javascript" src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="home/js/mainPic.js"></script>
</head>

<body>
	<%@ include file="header.jsp" %>
       <div class="box1">
		<div class="banana_rotate"><!--放图片的块-->
			<div class="roll_img" id="roll_img_cc"> <!--主要滚动的图片-->
				<div id="portal_block_500" class="inrotate_img block move-span">
					<div id="portal_block_500_content" class="dxb_bc">
						<div id="div_roll_img_cc" class="roll_img_cc" style="width: 90%; position: absolute; left: 0px;">
							<ul>
							</ul>
						</div>
					</div>
				</div>
				<div class="leftbtn_picturn"></div>
				<div class="rightbtn_picturn"></div>
				<div class="cr"></div>
			</div>
			<div class="roll_opacity" style="display: none;"></div>
			<div class="roll" style="display: none;">
				<div class="roll_title">
					<a href="#"></a>
				</div>
				<div class="roll_small_signimg_div">
				</div>
			</div>
		</div>
	  </div>
   
   <div class="hm_tail" style="padding:8px;"><!--尾部-->
  <div class="hd_culture_info"> <!-- 文化资讯-->
    	<div class="culture_info_container">
        	<p>文化资讯</p>
            <div class="figure-info">
            	<ul>
<<<<<<< HEAD
            		<%-- <c:forEach items="${zixun }" var="item" varStatus="i">
            		 <li class="cul_info_four">
           				<div>
           					<img src="../${item.works_img }" alt="图片加载失败" class="info-img"/>
           				</div>
           				<div class="info_introduce"> <!--信息介绍-->
           					<h4>${item.author_name}</h4>
           					<c:choose>
           						<c:when test="${fn:length(item.works_details) > 80}">
           							<p>${fn:substring(item.works_details,0,150) }...</p>
           						</c:when>
           						<c:otherwise>
           							<p>${item.works_details }</p>
           						</c:otherwise>
           					</c:choose>
           					<button type="button" class="btn re-clr1"><a>详细介绍</a></button>
           				</div>
           			</li> 
            		</c:forEach> --%>
=======
                	<li class="cul_info_four">
                            <div>
                            	<img src="home/images/LYteacher.jpg" alt="图片加载失败" class="info-img"/>
                            </div>
                            <div class="info_introduce"> <!--信息介绍-->
                                <h4>李越</h4>
                                <p>李越礼仪课程资深培训师。曾任一所艺术学校的董事长，多年来从事传统文化的教学工作，并多次为政府机关、大专院校、部队、武警官兵、企业等进行礼仪培训....</p>
                                <button type="button" class="btn re-clr1"><a>详细介绍</a></button>
                            </div>
                    </li>
                    <li class="cul_info_four">
                    		<div>
                            	<img src="home/images/XJteacher.jpg" alt="图片加载失败" class="info-img" />
                            </div>
                            <div class="info_introduce"><!--信息介绍-->
                                <h4>徐洁</h4>
                                <p>徐洁来自四川，国际注册会计师，在怀孕期间，为了自己的虚荣心，高强度地学习，完全不懂胎教，导致孩子得了严重的自闭症。今时今日，在中华传统文化的教育下....</p>
                                <button type="button" class="btn btn-default re-clr1"><a>详细介绍</a></button>
                            </div>
                    </li>
                	<li class="cul_info_four">
                    		<div>
                           		<img src="home/images/CLXteacher.jpg" alt="图片加载失败" class="info-img"/>
                            </div>
                            <div class="info_introduce">
                                <h4>蔡礼旭</h4>
                                <p>蔡礼旭老师，1973年出生于台湾，现任马来西亚中华文化教育中心主讲教师、国际儒学联合会会员、海南省监狱劳教系统特聘德育讲师、云南省道德大讲堂顾问....</p>
                                <button type="button" class="btn btn-default re-clr1"><a>详细介绍</a></button>
                            </div>
                    </li>
                    <li class="cul_info_four">
                    		<div>
                            	<img src="home/images/WSLteacher.jpg" alt="图片加载失败" class="info-img"/>
                            </div>
                            <div class="info_introduce">
                                <h4>王双利</h4>
                                <p>王双利河北唐山企业家，山西锵兴能源发展集团有限公司董事长。身价百万，却感受不到幸福，在公司打骂员工，在家不孝父母。他以亲身经历告诉大家什么是真正的孝....</p>
                                <button type="button" class="btn btn-default re-clr1"><a>详细介绍</a></button>
                            </div>
                    </li>
                </ul>
            </div>
        </div>

     </div>
  </div>
    <%@ include file="footer.jsp" %>
    
</body>

</html>
