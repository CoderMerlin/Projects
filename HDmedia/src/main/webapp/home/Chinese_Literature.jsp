<%@ page contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<base href="/HDmedia/">
<title>中华文化</title>
<meta name="viewport" content="width=device-width, initial-scale=1"/> <!--默认为屏幕的宽度-->
<link rel="short icon" href="Hd_index_img/hd_01.png"/>  <!--小图标，需要浏览器兼-->
<!--css-->
<link rel="stylesheet" href="home/css/footer.css" />
<link rel="stylesheet" href="home/css/Hd_nav.css" />
<link rel="stylesheet" href="home/css/hd_Ancient_poetry.css" /> <!--中华文化古诗词css-->
<link rel="stylesheet" href="home/css/hd_Ancient_poetry_nav.css"><!--中华诗词菜单栏-->
<link rel="stylesheet" href="home/css/all_classes_of_authors.css"> <!--诸子百家css-->
<link rel="stylesheet" href="home/css/style10.css" />
<!--Javascript-->
<script type="text/javascript"  src="js/jquery-1.11.3.min.js"></script>
<script type="text/javascript"  src="home/js/Chinese_Literature_js.js"></script>
</head>
<body>
	<%@ include file="header.jsp" %>
    <div id="hd_Ancient_poetry_title" class="hd_Ancient_poetry_title" name="hd_Ancient_poetry_title"> <!--中华古诗词标题-->
    	<p>中华文化 -> 古诗词</p>
        <img src="home/images/Chinese_Literature_underline.jpg" style=" padding:0; margin:0;width:100%; height:10%; float:left; display:inline;" />
    </div>
    <div id="hd_Ancient_poetry_nav" style=" margin-left:5%; padding:0px; width:16%; height:600px;float:left;"><!--中华古诗词导航栏-->
    <div id="hd_Ancient_poetry_list" class="hd_Ancient_poetry_list">
        <div id="hd_allBut" class="hd_allBut"> <!--所有的按钮-->
            <h2><a href="#"><strong>&nbsp;</strong>古诗词导航栏目<i>&nbsp;</i></a></h2>
            <ul style="width:125%" class="jspop box">
                <li class="hd_Tang1"><!--唐诗大全--> 
                    <div class=tx><a href="#"><i>&nbsp;</i>唐诗大全</a> </div>
                    <dl>
                        <dt>热门</dt>
                        <dd>
                            <a href="#">李白</a>
                            <a href="#">杜甫</a>
                            <a href="#">白居易</a>
                            <a href="#">王维</a> 
                        </dd>
                    </dl>
                    <dl>
                        <dt>热门诗词</dt>
                        <dd>
                            <a href="#">《春思》</a>
                            <a href="#">《长恨歌》</a>
                            <a href="#">《忆江南》</a>
                        </dd>
                    </dl>
                    <div class=pop>
                        <dl>
                            <dl>
                                <dt>李白</dt>
                                <dd>
                                    <a class="ui-link" href="#">《将进酒》</a>
                                    <a class="ui-link"  href="#">《春思》</a>
                                    <a class="ui-link" href="#">《望庐山瀑布》</a>
                                    <a class="ui-link"   href="#">《乌夜啼》</a>           
                                    <a class="ui-link" href="#">其他诗</a>
                                </dd>
                            </dl>
                            <dl>
                                <dt>杜甫</dt>
                                <dd>
                                    <a class="ui-link" href="">《登高》</a> 
                                    <a class="ui-link"  href="">《绝句》</a>
                                    <a class="ui-link" href="">《望月》</a>
                                    <a class="ui-link"  href="">《春望》</a> 
                                    <a class="ui-link" href="">《茅屋为秋风所破歌》</a>
                                    <a class="ui-link" href="">其他诗</a>
                                </dd>
                            </dl>
                            <dl>
                                <dt>白居易</dt>
                                <dd>
                                    <a class="ui-link" href="" >《钱塘湖春行》</a>
                                    <a class="ui-link"  href="">《长恨歌》</a>
                                    <a class="ui-link"  href="">《琵琶行》</a>
                                    <a class="ui-link"   href="">《忆江南》</a>
                                    <a class="ui-link" href="javascript:;">其他诗</a>
                                </dd>
                            </dl>
                            <dl>
                                <dt>王维</dt>
                                <dd>
                                    <a class="ui-link" href="">《山居秋暝》</a> 
                                    <a class="ui-link"  href="">《竹里馆》</a> 
                                    <a class="ui-link" href="">《九月九日忆山东兄弟》</a>
                                    <a class="ui-link"  href="">《鹿柴》</a>
                                    <a class="ui-link" href="">其他诗</a>
                                </dd>
                            </dl>
                        </dl>
                        <dl>
                            <dt>其他</dt>
                            <dd>
                                <a  href="">王昌龄</a>
                                <a  href="">柳宗元</a>
                                <a  href="">韩愈</a>
                                <a  href="">陈子昂</a>
                                <a  href="">高适</a>
                                <a  href="">岑参</a> 
                                <a  href="">刘禹锡</a> 
                            </dd>
                        </dl>
                    </div>
                </li>
                <li class="hd_Song1">
                    <div class=tx><a href=""><i>&nbsp;</i>宋词精选</a> </div>
                    <dl>
                        <dt>推荐</dt>
                        <dd>
                            <a href="">苏轼诗词</a> 
                            <a href="">辛弃疾诗词</a>
                            <a href="" >柳永诗词</a> 
                            <a href="">欧阳修诗词</a>
                            <a href="" >李清照诗词</a>
                        </dd>
                    </dl>
                    <div class=pop>
                        <h3><a href="">花草保健茶</a></h3>
                        <dl>
                            <dl>
                                <dt>苏轼</dt>
                                <dd>
                                    <a class="ui-link" href="" >《浣溪沙》</a>
                                    <a class="ui-link"    href="">《沁园春》</a> 
                                    <a  class="ui-link" href="">《减字花木兰》</a> 
                                    <a class="ui-link"   href="" >更多诗词</a>
                                </dd>
                            </dl>
                            <dl>
                                <dt>辛弃疾</dt>
                                <dd>
                                    <a class="ui-link" href="" >《菩萨蛮·书江西造口壁》</a>
                                    <a class="ui-link"  href="">《破阵子·为陈同普赋装次以寄之》</a> 
                                    <a class="un ui-link"   href="">更多诗词</a>
                                </dd>
                            </dl>
                            <dl>
                                <dt>柳永</dt>
                                <dd>
                                    <a class="ui-link" href="" >《雨霖铃》</a> 
                                    <a class="ui-link"    href=""  >《蝶恋花》</a>
                                    <a class="ui-link"   href="">《望海潮》</a>
                                    <a class="un ui-link"   href="">更多诗词</a>
                                </dd>
                            </dl>
                        </dl>
                        <dl>
                            <dt>欧阳修</dt>
                            <dd>
                                <a href="">《木兰花》</a>
                                <a href="">《蝶恋花》</a>
                                <a href="" >《采桑子》</a>
                                <a class="un ui-link"   href="">更多诗词</a>
                            </dd>
                        </dl>
                        <dl>
                            <dt>李清照</dt>
                            <dd>
                                <a href="" >《声声慢》</a>
                                <a   href="" >《如梦令》</a>
                                <a  href="">《一剪梅》</a>
                                <a class="un ui-link"   href="">更多诗词</a>
                            </dd>
                        </dl>
                        <dl>
                            <dt>周邦彦</dt>
                            <dd>
                                <a href="">《苏幕遮》</a> 
                                <a href="">《蝶恋花》</a>
                                <a href="">《浣溪沙》</a>
                                <a class="un ui-link"   href="">更多诗词</a>
                            </dd>
                        </dl>
                        <dl>
                            <dt>秦观诗词</dt>
                            <dd>
                                <a href="">《虞美人》</a>
                                <a href="">《满庭芳》</a>
                                <a href="">《鹊桥仙》</a>
                                <a class="un ui-link"   href="">更多诗词</a>
                            </dd>
                        </dl>
                    </div>
                </li>
                <li class="hd_Yuan1">
                    <div class=tx><a href=""><i>&nbsp;</i>元曲大全</a> </div>
                    <dl>
                        <dt>推荐</dt>
                        <dd>
                            <a href="" >关汉卿</a> 
                            <a href="">贯云石</a> 
                            <a href="">张养浩</a>
                            <a href="">张克久</a> 
                        </dd>
                    </dl>
                    <div class=pop>
                        <h3><a href="">精选茶具</a></h3>
                        <dl>
                            <dl>
                                <dt>关汉卿</dt>
                                <dd>
                                    <a class="ui-link" href="">《双调·碧玉箫》</a>
                                    <a class="ui-link"  href="">《双调·大德歌》</a> 
                                    <a class="ui-link" href="" >欣赏更多</a>
                                </dd>
                            </dl>
                        </dl>
                        <dl>
                            <dt>张可久</dt>
                            <dd>
                                <a  href="">《中吕·红绣鞋·西湖雨》</a>
                                <a  href="">《人月圆 子昂学士小景》</a>
                                <a   href="">《人月圆 春日湖上 北曲聊乐府前集今》</a> 
                                <a href="">欣赏更多</a>
                            </dd>
                        </dl>
                        <dl>
                            <dt>张养浩</dt>
                            <dd>
                                <a href="">《山坡羊·潼关怀古》</a>
                                <a  href="">《水仙子》</a>
                                <a  href="">《庆东原》</a>
                                <a  href="">欣赏更多</a>
                            </dd>
                        </dl>
                        <dl>
                            <dt>马致远</dt>
                            <dd>
                                <a href="">《天净沙 秋思》</a>
                                <a  href="">《南吕·四块玉》</a>
                                <a  href="">落梅风</a>
                                <a  href="">欣赏更多</a>
                            </dd>
                        </dl>
                    </div>
                </li>
                <li class="hd_More1">
                    <div class=tx><a href=""><i>&nbsp;</i>更多古诗词</a> </div>
                    <dl>
                        <dt>推荐</dt>
                        <dd>
                            <a href="">经典诗词名句</a>
                            <a   href="">近当代诗集</a>
                            <a  href="">毛泽东诗词</a> 
                        </dd>
                    </dl>
                    <div class=pop>
                        <h3><a href="">可口茶食</a></h3>
                        <dl>
                            <dl>
                                <dt>先秦诗集</dt>
                                <dd>
                                    <a class="ui-link" href="">诗经全集</a>
                                    <a class="ui-link"   href="">楚辞全集</a>
                                    <a  class="un ui-link" href="">楚辞章句</a>
                                    <a  class="un ui-link" href="">宋玉辞赋集</a>
                                    <a  class="un ui-link" href="">先秦诗七卷</a>
                                </dd>
                            </dl>
                            <dl>
                                <dt>明朝诗词</dt>
                                <dd>
                                    <a class="ui-link"  href="">吴兆《古别离》</a>
                                    <a class="ui-link"  href="">吴学礼《重过南浦》</a> 
                                    <a class="ui-link" href="javascript:;">程相《游少林寺》</a>
                                    <a class="ui-link" href="">更多诗词</a>
                                </dd>
                            </dl>
                        </dl>
                        <dl>
                            <dt>清朝诗集</dt>
                            <dd>
                                <a href="">龚自珍《已亥杂诗》</a>
                                <a  href="">纳兰性德《采桑子》</a>
                                <a  href="">乾隆《阅马》</a>
                                <a  href="">更多诗词</a>
                            </dd>
                        </dl>
                        <dl>
                            <dt>毛泽东诗词</dt>
                            <dd>
                                <a  href="">《沁园春·雪》</a>
                                <a   href="">《七律·长征》</a>
                                <a   href="">《仆算子·咏梅》</a>
                                <a   href="">《沁园春·长沙》</a>
                                <a   href="">更多诗词</a>
                            </dd>
                        </dl>
                    </div>
                </li>
            </ul>
        </div>
	</div>
    </div><!--写诗词目录-->
    <div id="poetry" class="poetry" name="poetry">  <!--诗词图片的整个块-->
    	<div class="container">
            <div class="main"> 
                <!-- TENTH EXAMPLE -->
                <div class="view view-tenth">
                    <img src="home/images/Hd_Chinese_Literature_img/Ancient_poetry/1_poetry_YJN.gif"/>
                    <div class="mask">
                        <h2>《忆江南》——&nbsp;白居易</h2>
                        <p>&nbsp;&nbsp;江南好，风景旧曾谙。日出江花红胜火，春来江水绿如蓝。能不忆江南？</p>
                        <a href="#" class="read_info">阅读更多</a>
                    </div>
                </div>
                <div class="view view-tenth">
                    <img src="home/images/Hd_Chinese_Literature_img/Ancient_poetry/2_poetry_ZH.gif"/>
                    <div class="mask">
                        <h2>《竹后》——&nbsp;&nbsp;郑燮</h2>
                        <p>咬定青山不放松，立根原在破岩中。千磨万击还坚劲，任尔东西南北风。</p>
                        <a href="#" class="read_info">阅读更多</a>
                    </div>
                </div>
                <div class="view view-tenth">
                    <img src="home/images/Hd_Chinese_Literature_img/Ancient_poetry/3_poetry_MN.gif"/>
                    <div class="mask">
                        <h2>《悯农》——&nbsp;&nbsp;李绅</h2>
                        <p>锄禾日当午，汗滴禾下土。谁知盘中餐，粒粒皆辛苦。</p>
                        <a href="#" class="read_info">阅读更多</a>
                    </div>
                </div>
                <div class="view view-tenth">
                    <img src="home/images/Hd_Chinese_Literature_img/Ancient_poetry/4_poetry_SX.gif"/>
                    <div class="mask">
                        <h2>《山行》——&nbsp;&nbsp;杜牧</h2>
                        <p>远上寒山石径斜，白云生处有人家。停车坐爱枫林晚，霜叶红于二月花。</p>
                        <a href="#" class="read_info">阅读更多</a>
                    </div>
                </div>
            </div>
        </div>
    </div><!--诗词的图片-->
    <div id="4" style=" margin: 0 5%; padding:0px; width:90%; height:50px; float:left;"><!--文言文层-->
    	<p style="padding:0.5% 1%;margin:0; width:50%; height:60%; float:left; display:inline; font-size:130%;">中华文化 -> 文言文</p><!--文言文标题-->
        <img src="home/images/Chinese_Literature_underline.jpg" style=" padding:0; margin:0;width:100%; height:10%; float:left; display:inline;" />
    </div> <!--文言文-->
    <div id="5" style=" margin: 0 5%; padding:0px; width:90%; height:520px; float:left;"><!--文言文的四个整块-->
    	<div id="a" style="margin:1% 1.9%; padding:0px; width:46%; height:230px; float:left; "><!--小标题-->
        	<div class="xiaoxWYW_1" style="margin:0; padding:0; width:100%; height:10%;"><!--正文-->
                <p class="xiaoxWYW_1_1" style=" font-size:24px; font-family:'方正舒体'; display:block;">小学文言文
                    <a class="xiaoxWYW_1_2" style="text-align:right; font-size:14px;" href="#">[更多]</a>
                </p>
            </div>
            <div style="padding:0; margin:0; width:100%; height:10%;">
            	<p style=" display:block; font-size:16px; font-family:'方正舒体';">陈元方候袁公原文及翻译，注释赏析及写作背景中心思想</p>
            </div>
            <div style="width:100%; height:20%;">
            	<p style="text-indent:2em; display:block; font-size:14px; font-family:'微软雅黑'; float:left;">陈元方候袁公 &nbsp;&nbsp;作者:&nbsp;&nbsp; 刘义庆&nbsp;&nbsp; 陈元方年十一岁时，候袁公。袁公问曰：贤家君在太丘，远近称之，何所履行？元方曰：老父在太丘，强者绥（suí）之以德，弱者抚之以仁... <a>【阅读全文】</a></p>
            </div>
            <div style="width:100%; height:60%;">
            	 <ul style="font-size:15px; padding:0 10%;">
                    <li>孟母三迁原文及翻译，注释赏析及写作背景中心思想</li>
                    <li>揠苗助长原文及翻译，注释赏析及写作背景中心思想</li>
                    <li>画蛇添足原文及翻译，注释赏析及写作背景中心思想</li>
                    <li>北人食菱原文及翻译，注释赏析及写作背景中心思想</li>
                    <li>愚人食盐原文及翻译，注释赏析及写作背景中心思想</li>
            	</ul>
            </div>
           
        </div>
         <img src="home/images/Chinese_Literature_underline.jpg" style="height:50%; width:0.2%;" />
        <div id="b" style="margin:1% 1.9%; padding:0px; width:46%; height:230px; float:left;"><!--初中文言文--> <!--小标题-->
       		<div class="chuzWYW_1" style="margin:0; padding:0; width:100%; height:10%; "><!--正文-->
                <p class="chuzWYW_1_1" style=" font-size:24px; font-family:'方正舒体'; display:block;">初中文言文
                    <a class="chuzWYW_1_2" style="text-align:right; font-size:14px;" href="#">[更多]</a>
                </p>
            </div>
             <div style="padding:0; margin:0; width:100%; height:10%;">
            	<p style=" display:block; font-size:16px; font-family:'方正舒体';">王安石待客_王安石待客阅读答案及翻译</p>
            </div>
            <div style="width:100%; height:20%;">
            	<p style="text-indent:2em; display:block; font-size:14px; font-family:'微软雅黑'; float:left;">王安石待客 王安石在相位，子妇之亲①萧氏子至京师②，因谒公，公约之饭。翌日，萧氏子盛服而往，意为公必盛馔。日过..<a>【阅读全文】</a></p>
            </div>
            <div style="width:100%; height:60%;">
            	 <ul style="font-size:15px; padding:0 10%;">
                    <li>乐羊子妻原文及翻译，注释赏析及写作背景中心思想</li>
                    <li>岳阳楼记原文及翻译，注释赏析及写作背景中心思想</li>
                    <li>宋定伯捉鬼原文及翻译，注释赏析及写作背景中心思想</li>
                    <li>木兰诗原文及翻译，注释赏析及写作背景中心思想</li>
                    <li>送董邵南游河北序原文及翻译，注释赏析及写作背景中心思</li>
            	</ul>
            </div>
        </div>
        <img src="home/images/Chinese_Literature_underline.jpg" style="width:100%; height:0.5%;" />
        <div id="c" style="margin:1% 1.9%; padding:0px; width:46%; height:230px;float:left;"><!--高中文言文--><!--小标题-->
        	<div class="gaozWYW_1" style="margin:0; padding:0; width:100%; height:10%; "><!--正文-->
                <p class="gaozWYW_1_1" style=" font-size:24px; font-family:'方正舒体'; display:block;">高中文言文
                    <a class="gaozWYW_1_2" style="text-align:right; font-size:14px;" href="#">[更多]</a>
                </p>
            </div>
             <div style="padding:0; margin:0; width:100%; height:10%;">
            	<p style=" display:block; font-size:16px; font-family:'方正舒体';">项羽之死：《项王军壁垓下》原文翻译及赏析</p>
            </div>
            <div style="width:100%; height:20%;">
            	<p style="text-indent:2em; display:block; font-size:14px; font-family:'微软雅黑'; float:left;">《项王军壁垓下》讲述项羽之死。项王军壁垓下，兵少食尽，汉军及诸侯兵围之数重。项王乃欲东渡乌江。项王笑曰：纵江东父兄怜而王我，我何面目见之？纵彼不言，籍独不愧于心乎？”乃自刎而死。<a>【阅读全文】</a></p>
            </div>
            <div style="width:100%; height:60%;">
            	 <ul style="font-size:15px; padding:0 10%;">
                    <li>王慎中《朱碧潭诗序》原文翻译及阅读答案</li>
                    <li>苏辙《为兄轼下狱上书》原文翻译及阅读答案</li>
                    <li>鸿门宴原文及翻译，注释赏析及写作背景中心思想</li>
                    <li>前赤壁赋原文及翻译，注释赏析及写作背景中心思想</li>
                    <li>"韩子高，会稽山阴人也"原文翻译及阅读答案</li>
            	</ul>
            </div>
        </div>
        <img src="home/images/Chinese_Literature_underline.jpg" style="height:50%; width:0.2%; float:left;" />
        <div id="d" style="margin:1% 1.9%; padding:0px; width:46%; height:230px; float:left;"><!--古文观止--><!--小标题-->
        	<div class="guwWYW_1" style="margin:0; padding:0; width:100%; height:10%; "><!--正文-->
                <p class="guwWYW_1_1" style=" font-size:24px; font-family:'方正舒体'; display:block;">古文观止
                    <a class="guwWYW_1_2" style="text-align:right; font-size:14px;" href="#">[更多]</a>
                </p>
            </div>
             <div style="padding:0; margin:0; width:100%; height:10%;">
            	<p style=" display:block; font-size:16px; font-family:'方正舒体';">信陵君救赵论</p>
            </div>
            <div style="width:100%; height:20%;">
            	<p style="text-indent:2em; display:block; font-size:14px; font-family:'微软雅黑'; float:left;">信陵君救赵论 作者： 唐顺之 论者以窃符为信陵君之罪，余以为此未足以罪信陵也。夫强秦之暴亟矣，今悉兵以临赵，赵必... <a>【阅读全文】</a></p>
            </div>
            <div style="width:100%; height:60%;">
            	 <ul style="font-size:15px; padding:0 10%;">
                    <li><a>《送天台陈庭学序》</a></li><br/><li><a>《阅江楼记》</a></li><br/>
                    <li><a>《司马季主论卜》</a></li><br/>
                    <li><a>《卖柑者言》</a></li><br/>
                    <li><a>《深虑论》</a></li><br/>
            	</ul>
            </div>
        </div>
    </div><!--分四块 1.小学文言文 2.初中文言文 3.高中文言文 4.古文观止-->
    <div id="all_classes_of_authors" class="all_classes_of_authors"><!--诸子百家-->
    	<p>中华文化 -> 诸子百家</p>
        <img src="home/images/Chinese_Literature_underline.jpg" />
    </div>
    <!--诸子百家图片-->
    <div id="all_classes_of_authors_img" class="all_classes_of_authors_img">
    	<div id="all_classes_of_authors_img_1" class="all_classes_of_authors_img_1">
        	<div id="focus"  class="focus">
            	<ul>
                	<li><img src="home/images/Hd_Chinese_Literature_img/all_classes_of_authors/rujia.jpg" /></li>
                    <li><img src="home/images/Hd_Chinese_Literature_img/all_classes_of_authors/fajia.jpg" /></li>
                </ul>         	
            </div>    	
        </div>
    	<div id="all_classes_of_authors_img_2"  class="all_classes_of_authors_img_1">
        	<div id="focus1" style="width:100%; height:100%; overflow:hidden; position:relative;">
            	<ul style="height:100%;position:absolute;">
                	<li><img src="home/images/Hd_Chinese_Literature_img/all_classes_of_authors/daojia.jpg" style="width:100%; height:100%;" /></li>
                    <li><img src="home/images/Hd_Chinese_Literature_img/all_classes_of_authors/mingjia.jpg" style="width:100%; height:100%;" /></li>
                </ul>         	
            </div>
        </div>
        <!--第三张图-->
        <div id="all_classes_of_authors_img_3"  class="all_classes_of_authors_img_1">
        	<div id="focus2" style="width:100%; height:100%; overflow:hidden; position:relative;">
            	<ul style="height:100%;position:absolute;">
                	<li><img src="home/images/Hd_Chinese_Literature_img/all_classes_of_authors/mojia.jpg" style="width:100%; height:100%;" /></li>
                    <li><img src="home/images/Hd_Chinese_Literature_img/all_classes_of_authors/yinyangjia.jpg" style="width:100%; height:100%;" /></li>
                </ul>         	
            </div>
        </div>
        
    </div><!--诸子百家 插入动态图片-->
	<%@ include file="footer.jsp" %>
</body>
</html>
