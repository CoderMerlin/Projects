// JavaScript Document

	
	$(function() { 
		var sWidth = $("#focus").width(); //获取焦点图的宽度（显示面积） 
		var len = $("#focus ul li").length; //获取焦点图个数 
		var index = 0; 
		var picTimer; 
	
		//本例为左右滚动，即所有li元素都是在同一排向左浮动，所以这里需要计算出外围ul元素的宽度 
		$("#focus ul").css("width",sWidth * (len)); 
		
		//鼠标滑上焦点图时停止自动播放，滑出时开始自动播放 
		$("#focus").hover(function() { 
			clearInterval(picTimer); 
		},function() { 
			picTimer = setInterval(function() { 
			showPics(index); 
			index++; 
		if(index == len) {index = 0;} 
			},3000); //此处代表自动播放的间隔，单位：毫秒 
		}).trigger("mouseleave"); 
		
		//显示图片函数，根据接收的index值显示相应的内容 
		function showPics(index) { //普通切换 
			var nowLeft = -index*sWidth; //根据index值计算ul元素的left值 
		$("#focus ul").stop(true,false).animate({"left":nowLeft},500); //通过animate()调整ul元素滚动到计算出的position 
		//$("#focus .btn span").removeClass("on").eq(index).addClass("on"); //为当前的按钮切换到选中的效果 
		$("#focus .btn span").stop(true,false).animate({
			"opacity":"0.4"
			},500).eq(index).stop(true,false).animate({
				"opacity":"1"},500); //为当前的按钮切换到选中的效果 
		} 
		
		}); 
		
		
		
	//第二组图片	
	$(function() { 
		var sWidth = $("#focus1").width(); //获取焦点图的宽度（显示面积） 
		var len = $("#focus1 ul li").length; //获取焦点图个数 
		var index = 0; 
		var picTimer; 
	
		//本例为左右滚动，即所有li元素都是在同一排向左浮动，所以这里需要计算出外围ul元素的宽度 
		$("#focus1 ul").css("width",sWidth * (len)); 
		
		//鼠标滑上焦点图时停止自动播放，滑出时开始自动播放 
		$("#focus1").hover(function() { 
			clearInterval(picTimer); 
		},function() { 
			picTimer = setInterval(function() { 
			showPics(index); 
			index++; 
		if(index == len) {index = 0;} 
			},3000); //此处代表自动播放的间隔，单位：毫秒 
		}).trigger("mouseleave"); 
		
		//显示图片函数，根据接收的index值显示相应的内容 
		function showPics(index) { //普通切换 
			var nowLeft = -index*sWidth; //根据index值计算ul元素的left值 
		$("#focus1 ul").stop(true,false).animate({"left":nowLeft},500); //通过animate()调整ul元素滚动到计算出的position 
		//$("#focus .btn span").removeClass("on").eq(index).addClass("on"); //为当前的按钮切换到选中的效果 
		$("#focus1 .btn span").stop(true,false).animate({
			"opacity":"0.4"
			},500).eq(index).stop(true,false).animate({
				"opacity":"1"},500); //为当前的按钮切换到选中的效果 
		} 
		
		}); 
		
		
		//第三组图片	
	$(function() { 
		var sWidth = $("#focus2").width(); //获取焦点图的宽度（显示面积） 
		var len = $("#focus2 ul li").length; //获取焦点图个数 
		var index = 0; 
		var picTimer; 
	
		//本例为左右滚动，即所有li元素都是在同一排向左浮动，所以这里需要计算出外围ul元素的宽度 
		$("#focus2 ul").css("width",sWidth * (len)); 
		
		//鼠标滑上焦点图时停止自动播放，滑出时开始自动播放 
		$("#focus2").hover(function() { 
			clearInterval(picTimer); 
		},function() { 
			picTimer = setInterval(function() { 
			showPics(index); 
			index++; 
		if(index == len) {index = 0;} 
			},3000); //此处代表自动播放的间隔，单位：毫秒 
		}).trigger("mouseleave"); 
		
		//显示图片函数，根据接收的index值显示相应的内容 
		function showPics(index) { //普通切换 
			var nowLeft = -index*sWidth; //根据index值计算ul元素的left值 
		$("#focus2 ul").stop(true,false).animate({"left":nowLeft},500); //通过animate()调整ul元素滚动到计算出的position 
		//$("#focus .btn span").removeClass("on").eq(index).addClass("on"); //为当前的按钮切换到选中的效果 
		$("#focus2 .btn span").stop(true,false).animate({
			"opacity":"0.4"
			},500).eq(index).stop(true,false).animate({
				"opacity":"1"},500); //为当前的按钮切换到选中的效果 
		} 
		
		}); 