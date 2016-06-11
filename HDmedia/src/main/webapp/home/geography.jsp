<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<base href="/HDmedia/">
<meta charset="utf-8"/>

<title>人文地理</title>
<!--css-->
<link rel="stylesheet" href="home/css/footer.css" />
<link rel="stylesheet" href="home/css/Hd_nav.css" />
<link rel="stylesheet" href="home/css/geography.css" />

<!--Javascript-->
<script type="text/javascript"  src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript"  src="js/myfocus-2.0.1.min.js"></script>
<script type="text/javascript" src="home/js/geography.js"></script>


<script type="text/javascript">
	//设置
	myFocus.set({
		id:'myFocus',//ID
		pattern:'mF_rapoo',//风格应用的名称
		time:3,//切换时间间隔(秒)
		trigger:'click',//触发切换模式:'click'(点击)/'mouseover'(悬停)
		width:1500,//设置图片区域宽度(像素)
		height:450,//设置图片区域高度(像素)
		txtHeight:'default'//文字层高度设置(像素),'default'为默认高度，0为隐藏
			
	});

</script>
</head>

<body>
        <div id="hd_nav" class="hd_nav"><!--弘道菜单导航-->
		<div id="hd_title" class="hd_title">
        	<img src="images/hd_02.png"/>
            <p>弘扬中华文化	传颂道德品行</p>
        </div>
        <div id="hd_nav_menu" class="hd_nav_menu">
        	<ul  id="hd_menu" class="hd_menu_name">	  	
                <li class="hd_home_page">
                    <a href="">首页</a>
                </li>
                <span class="sep">|</span>	
                <li>
                    <a href="">人文地理</a>
                    <ul>
                        <li><a href="">名族</a></li>
                        <li><a href="">地域</a></li> 
                    </ul>
                </li>
                <span class="sep">|</span>				
                <li >
                    <a href="">文化</a>
                     <ul>
                        <li><a href="">茶艺文化</a></li>
                        <li><a href="">酒水文化</a></li>
                        <li><a href="">美食文化</a></li>  
                    </ul>
                </li>
                <span class="sep">|</span>				
                <li >
                    <a href="">中华文学</a>
                    <ul>
                        <li><a href="#hd_Ancient_poetry_title" name="hd_Ancient_poetry_title">古诗词</a></li>
                        <li><a href="">文言文</a></li>
                        <li><a href="">古代典籍</a></li>
                        <li><a href="">历史故事</a></li>
                    </ul>
                </li>
                <span class="sep">|</span>				
                <li >
                    <a href="">书画风采</a>
                    <ul>
                         <li><a href="">国画</a></li>
                         <li><a href="">书法</a></li>
                         <li><a href="">古代书法</a></li>
                         <li><a href="">古代绘画</a></li>
                         <li><a href="">近代绘画</a></li>
                    </ul>
                </li>
                <span class="sep">|</span>				
                <li >
                    <a href="">鉴赏文宝</a>
                    <ul>
                      <li><a href="">玉器</a></li>
                      <li><a href="">书画</a></li>
                      <li><a href="">陶瓷</a></li>           
                    </ul>
                </li>
                <span class="sep">|</span>				
                <li >
                    <a href="">在线商城</a>
                    <ul>
                      <li><a href="">拍卖会场</a></li>
                    </ul>
                </li>
                <span class="sep">|</span>				
                <li >
                    <a href="">社区论坛</a>
                </li>
                <span class="sep">|</span>				
                <li >
                    <a href="">登录</a>
                </li>			
             </ul>
        </div>
    </div>
    <div id="hd_Ancient_poetry_title" class="hd_Ancient_poetry_title" name="hd_Ancient_poetry_title"> <!---->
    	<p>中华文化 -> 地理</p>
        <img src="home/images/Chinese_Literature_underline.jpg" 
        style=" padding:0; margin:0;width:100%; height:10%; float:left; display:inline;" />
    </div>
    <div id="local">
    	<img style="position:absolute; left:0px;top:0px;width:90%;height:85%;z-Index:-1; padding: 0 5%;margin: 0 auto;" src="home/images/local.png" />
    	<ul class="local-list">
        	<li><a href="">北京</a></li>
            <li><a href="">天津</a></li>
            <li><a href="">上海</a></li>
            <li><a href="">重庆</a></li>
            <li><a href="">河北</a></li>
            <li><a href="">河南</a></li>
            <li><a href="">湖南</a></li>
            <li><a href="">湖北</a></li>
            <li><a href="">山东</a></li>
            <li><a href="">山西</a></li>
            <li><a href="">陕西</a></li>
            <li><a href="">贵州</a></li>
            <li><a href="">吉林</a></li>
            <li><a href="">辽宁</a></li>
            <li><a href="">广东</a></li>
            <li><a href="">广西</a></li>
            <li><a href="">西藏</a></li>
            <li class="list-special"><a href="">云南</a></li>
            <li class="list-special"><a href="">新疆</a></li>
            <li class="list-special"><a href="">宁夏</a></li>
            <li class="list-special"><a href="">江苏</a></li>
            <li class="list-special"><a href="">福建</a></li>
            <li class="list-special"><a href="">澳门</a></li>
            <li class="list-special"><a href="">香港</a></li>
            <li class="list-special"><a href="">台湾</a></li>
            <li class="list-special"><a href="">海南</a></li>
            <li class="list-special"><a href="">安徽</a></li>
            <li class="list-special"><a href="">青海</a></li>
            <li class="list-special"><a href="">甘肃</a></li>
            <li class="list-special"><a href="">江西</a></li>
            <li class="list-special"><a href="">浙江</a></li>
            <li class="list-special"><a href="">四川</a></li>
            <li class="list-special"><a href="">黑龙江</a></li>
            <li class="list-special"><a href="">内蒙古</a></li>
        </ul>
    </div>
     <div id="local-detail">
    	<div class="detail-title">
        <img style="" src="home/images/bg_sheng.jpg"/>
        <p class="detail-province">北京</p>
        </div>
        <div class="detail-conter">
        北京，中华人民共和国首都、直辖市和国家中心城市，中国的政治、文化、科教和国际交往中心，中国经济、金融的决策和管理中心，中	
        华人民共和国中央人民政府和全国人民代表大会所在地，也是世界上最大的城市之一。
        </div>
    </div>
   <div id="myFocus"><!--焦点图盒子-->
  	<div class="loading"><img src="home/img/loading.gif" alt="请稍候..." /></div><!--载入画面(可删除)-->
      <div class="pic"><!--图片列表-->
        <ul class="every-pic">
            <li><a href="#1"><img src="home/img/1a.jpg" thumb="" alt="长城" text="图片1更详细的描述文字" /></a></li>
            <li><a href="#2"><img src="home/img/2a.jpg" thumb="" alt="丁字裤" text="图片2更详细的描述文字" /></a></li>
            <li><a href="#3"><img src="home/img/3a.jpg" thumb="" alt="你好" text="图片3更详细的描述文字"/></a></li>
            <li><a href="#4"><img src="home/img/4a.jpg" thumb="" alt="于网络" text="图片4更详细的描述文字" /></a></li>
            <li><a href="#5"><img src="home/img/5a.jpg" thumb="" alt="版权属于作者" text="图片5更详细的描述文字" /></a></li>
        </ul>
      </div>
    </div>
   
    <div class="c6" style="line-height:20px;">
    	<p style="padding: 5px">北京著名人物</p>
     <img src="home/images/Chinese_Literature_underline.jpg" 
        style=" padding:0; margin:0;width:100%; height:10%; float:left; display:inline;" />
    </div>
        <div id="c7">
            <div class="c72">
            	<ul>
                	<li class="c721"><img width="150" height="185" src="home/images/1388126103_thumb - 副本.jpg"><p><a href="">王实甫</a></p><p class="p1"> &nbsp;&nbsp;&nbsp;&nbsp;王实甫，名德信，定兴(今定兴县)人。著有杂剧十四种，现存《西厢记》、《丽春堂》、《破窑记》三种。</p></li>
                  
                    <li class="c722"><img width="150" height="185" src="home/images/1385709937_thumb.jpg"><p><a href="">程砚秋</a></p><p class="p2">&nbsp;&nbsp;&nbsp;&nbsp;程砚秋(1904-1958)，男，北京人，京剧旦角，四大名旦之一，程派艺术的创始人。</p></li>
                </ul>
            </div>
            <div class="c73">
            	<ul>
            	<li class="c731"><img width="150" height="185" src="images/res11_attpic_brief.jpg"><p><a href="">老舍</a></p><p class="p3">&nbsp;&nbsp;&nbsp;&nbsp;老舍，本名舒庆春，字舍予，笔名老舍。北京满族正红旗人，中国现代著名小说家、文学家、戏剧家。</p></li>
                    <li class="c732"><img width="150" height="185" src="images/1385707246_thumb.jpg"><p><a href="">马致远</a></p><p class="p4">&nbsp;&nbsp;&nbsp;&nbsp;马致远(约1250-1321至1324)，字千里，号东篱，(一说字致远，晚号“东篱”)。</p></li>
                 </ul>   
            </div>
        </div>
        <div class="c9" style="line-height:20px;">
        <p style="padding: 5px">北京著名景点</p>
         <img src="home/images/Chinese_Literature_underline.jpg" 
        style=" padding:0; margin:0;width:100%; height:10%; float:left; display:inline;" />
        </div>
        <div class="c10">
        	<div class="c101">
            	<ul>
                	<li class="c101-1"></li>
                    <li class="c101-2"></li>
                </ul>
            </div>
            <div class="c102">
            	<ul>
                	<li class="c102-1"><a href=""><img width:"180px"
	height:"130px" src="home/images/1384421243_thumb.jpg" data-bd-imgshare-binded="1"/></a><p><a href="">中国帝王的象征——故宫</a></p></li>
                    <li class="c102-2"><a href=""><img width:"180px"
	height:"130px" src="home/images/1385713932_thumb.jpg" data-bd-imgshare-binded="1"/></a><p><a href="">八达岭长城</a></p></li>
                    <li class="c102-3"><a href=""><img width:"180px"
	height:"130px" src="home/images/1385712439_thumb.jpg" data-bd-imgshare-binded="1"/></a><p><a href="">万园之园--圆明园</a></p></li>
                    <li class="c102-4"><a href=""><img width:"180px"
	height:"130px" src="home/images/1385711007_thumb.jpg" data-bd-imgshare-binded="1"/></a><p><a href="">皇家园林博物馆--颐和园</a></p></li>
                </ul>
            </div>
            <div class="c103">
            	<ul>
                	<li><a href=""><img height:"65px" width:"85px" src="home/images/1384421243_thumb.jpg"/></a><p><a href="">故宫</a></p></li>
                    <li><a href=""><img height:"65px" width:"85px" src="home/images/1388124671_thumb.jpg"/></a><p><a href="">香山公园</a></p></li>
                    <li><a href=""><img height:"65px" width:"85px" src="home/images/1388123591_thumb.jpg"/></a><p><a href="">王府井大街</a></p></li>
                    <li><a href=""><img height:"65px" width:"85px" src="home/images/1388116661_thumb.jpg"/></a><p><a href="">天安门</a></p></li>
                    <li><a href=""><img height:"65px" width:"85px" src="home/images/1388115141_thumb.jpg"/></a><p><a href="">国家大剧院</a></p></li>
                    <li><a href=""><img height:"65px" width:"85px" src="home/images/1385713932_thumb.jpg"/></a><p><a href="">八达岭长城</a></p></li>
                    <li><a href=""><img height:"65px" width:"85px" src="home/images/1385712439_thumb.jpg"/></a><p><a href="">圆明园</a></p></li>
                    <li><a href=""><img height:"65px" width:"85px" src="home/images/1385711007_thumb.jpg"/></a><p><a href="">颐和园</a></p></li>
                </ul>
            </div>
      </div>
      
      <!--尾部-->
       <div class="hd_footer_menu_all"><!--下放所有的菜单信息-->
    	<div class="hd_footer_menu_content">
        	<div class="menu_content">
            	<div class="footer_menu_con">
                	<dl>
                    	<dd><a href="">新人报道</a></dd>
                        <dd><a href="">论坛社区</a></dd>
                    </dl>
                    <dl>
                    	<dd><a href="">资源分享</a></dd>
                        <dd><a href="">加入我们</a></dd>
                    </dl>
                    <dl>
                    	<dd><a href="">法律声明</a></dd>
                        <dd><a href="">关于弘道</a></dd>
                    </dl>
                    <dl>
                    	<dd><a href="">联系客服</a></dd>
                        <dd><a href="">新浪微博</a></dd>
                    </dl>
                </div>
            </div>
        </div>
        <div class="hd_footer_down">
            <div  class="hd_footer_last menu_content">
                    <a class="hd_img"><img src="Hd_index_img/hd_02.png" /></a>
                    <span>COPYRIGHT&copy;WZGS.NET,2015,.</span>
                    <a href="#">备案号：湘ICP备13003602号-2</a>
                    <a href="#">经营许可证编号：湘B2-20130198</a>
                    <a href="#">营业执照</a>
                    <span>.ALL RIGHT BY YC_弘道传媒</span>
                </ul>
            </div>
        </div>         
    </div>
</body>
</html>