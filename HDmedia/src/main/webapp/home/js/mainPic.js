// JavaScript Document
	var MOVE = 686, CURRENT = 0;
	var IMGS = ["a1.jpg", "a2.jpg", 'a3.jpg'];  /*图片*/
	var TITLES = ["清末 山水画 12399元", "魅力山水 ————", "心有灵犀一点通..."];  /*图片下放的文字*/
	
	jQuery (function ($){
	var dric = $ ('div#div_roll_img_cc'), um = dric.children ('ul'); //um 为ul
	var smalldiv = $ ('div.roll_small_signimg_div');
	var ra = $('div.roll_title a:first');
	$ (IMGS).each (function (i, dom)
	{
		um.append('<li><a href="#"><div class="roll_img_kk" style="background: url(home/images/'+dom+'); width: 1450px; height: 650px;"></div> </a></li>');//给ul添加标签
		smalldiv.append('<img class="roll_small_signimg"  src="home/images/'+dom+'">');
	});
	var sc = smalldiv.children ('img');
	sc.eq(CURRENT).addClass('current_con'); //获取sc中的第一个img
	ra.text(TITLES[CURRENT]);
	
	var ro = $ ('div.roll_opacity'), r = $ ('div.roll'), btn = $('div[class*="btn_picturn"]');
	$ ('div.box1').mouseenter (function ()
	{
		stop ();
		ro.show ();
		r.show ();
		btn.show();
	}).mouseleave (function ()
	{
		ro.hide ();
		r.hide ();
		btn.hide();
		run (btn.eq(1));
	});
	
	var uc = um.children ('li'), isRunning = false;
	var len = uc.length;
	uc.each (function (i, dom)
	{
		$ (this).attr ('index', i);
	});
	sc.click (function ()
	{
		if (isRunning)
		{
			return false;
		}
		$ (this).addClass ('current_con').siblings ('img[class*="current_con"]').removeClass ('current_con');
		var index = $ (this).index ();
		if (index == CURRENT)
		{
			return false;
		}
		// left
		else if (index < CURRENT)
		{
			for ( var i = index; i < len; i++)
			{
				uc.filter ('li[index=' + i + ']').appendTo (um);
			}
			for ( var i = 0; i < index; i++)
			{
				uc.filter ('li[index=' + i + ']').appendTo (um);
			}
			dric.css ('left', '-' + (MOVE * (CURRENT - index)) + 'px');
			dric.animate (
			{
				left : "0px"
			}, "slow", function ()
			{
				isRunning = false;
			});
		}
		// rigth
		else if (index > CURRENT)
		{
			dric.animate (
			{
				left : "-" + (MOVE * (index - CURRENT)) + "px"
			}, "slow", function ()
			{
				for ( var i = index; i < len; i++)
				{
					uc.filter ('li[index=' + i + ']').appendTo (um);
				}
				for ( var i = 0; i < index; i++)
				{
					uc.filter ('li[index=' + i + ']').appendTo (um);
				}
				dric.css ('left', '0px');
				isRunning = false;
			});
		}
		CURRENT = index;
		ra.text(TITLES[CURRENT]);
		isRunning = true;
	});
	
	var changeImg = function (isRight)
	{
		smalldiv.children ('img:eq(' + CURRENT + ')').removeClass ('current_con');
		if (!isRight)
        {
			CURRENT--;
			CURRENT = CURRENT < 0 ? len - 1 : CURRENT;
        }
		else 
		{
			CURRENT++;
			CURRENT = CURRENT > len - 1 ? 0 : CURRENT;
		}
		ra.text(TITLES[CURRENT]);
		smalldiv.children ('img:eq(' + CURRENT + ')').addClass ('current_con');
	};
	
	btn.eq(0).click (function ()
	{
		if (isRunning)
		{
			return false;
		}
		changeImg(false);
		isRunning = true;
		dric.find ('li:last').insertBefore (dric.find ('li:first'));
		dric.css ('left', '-' + MOVE + 'px');
		dric.animate (
		{
			left : '0px'
		}, 'slow', function ()
		{
			isRunning = false;
		});
	});
	
	btn.eq(1).click (function ()
	{
		if (isRunning)
		{
			return false;
		}
		changeImg(true);
		isRunning = true;
		dric.animate (
		{
			left : '-' + MOVE + 'px'
		}, 'slow', function ()
		{
			dric.find ('li:first').insertAfter (dric.find ('li:last'));
			dric.css ('left', '0px');
			isRunning = false;
		});
	});
	run (btn.eq(1));
});

	var INTERVAL = null, SLEEP = 2000;
	var run = function (btn)
	{
		if (!INTERVAL)
		{
			INTERVAL = setInterval (function ()
			{
				btn.triggerHandler ('click');
			}, SLEEP);
		}
	};
	
var stop = function ()
{
	if (!!INTERVAL)
    {
	    clearInterval (INTERVAL);
	    INTERVAL = null;
    }
};
