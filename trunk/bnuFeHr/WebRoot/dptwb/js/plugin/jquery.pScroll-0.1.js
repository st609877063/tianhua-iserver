//====================================================================================================
// [插件名称] jQuery picScroll
//----------------------------------------------------------------------------------------------------
// [描    述] 是一款基于jQuery类库，实现图片通过按钮上下、左右滚动效果的插件。enjoy~
//----------------------------------------------------------------------------------------------------
// [作者网名] Evan	
// [邮  	 箱] frienkyliu@hotmail.com	
// [作者博客] http://www.seeutopia.com
// [QQ 交流] 93129471
// [更新日期] 2011-11-28
// [版 本 号] 0.1
//====================================================================================================
(function($){
	$.fn.fullSize=function(z){
		var fk=$(this).outerWidth()+parseInt($(this).css("margin-left"))+parseInt($(this).css("margin-right"));
		var fg=$(this).outerHeight()+parseInt($(this).css("margin-top"))+parseInt($(this).css("margin-bottom"));
		return z=='w'? fk : fg;
	};
	$.fn.Scroll=function(options){
		var settings={
            btnlt:null,         //按钮left/top
            btnrb:null,			//按钮right/bottom
            elemchild:'li',		//子元素即循环的元素成员,默认子元素为li
            move:1,				//移动一格距离
            showunit:3,			//可视区域显示几格
			speed:1000,			//速度
			playswitch:true,	//自动滚动
			duration:3000,		//自动滚动间隔时间
			effectType:0		//效果 默认为0：左右滚动
		};
		var o = $.extend(settings,options);
		return this.each(function(){
			var d = $(this);
			var initXY=0, unit=6, direc=o.effectType==0 ? 'left' : 'top';
			
			o.move=o.move * d.children(o.elemchild + ":eq(0)").fullSize(direc=='left'?'w':'h');	//get Child Element width
			//initially set position			
			
			initXY= -o.move * o.showunit;
			d.css(direc, initXY + "px");
			unit = d.children(o.elemchild).length;
			var lis = d.children(o.elemchild);
			for(var i=o.showunit;i>=1;i--){
				lis.eq(lis.length-i).clone().insertBefore(lis.eq(0));
			};
			if(o.playswitch){
				if(o.effectType==0){
					var atpX = setInterval(autoplay, o.duration);
				}
				else{
					var atpY = setInterval(autoplay, o.duration);
				}
			}
			function autoplay(){
				if(!d.is(":animated")){
					initXY=initXY-o.move;
					doScroll("rb");
				}
			};
			function doScroll(dir){
            	if(direc=='left'){    //此处left表示水平
	            	d.animate({left:initXY},o.speed,function(){
	            		if(dir=="rb"){
							if(chk("rb")){
								initXY=0;
								d.css(direc, initXY + 'px');
							}
						}
						else if(dir=="lt"){
							if(chk("lt")){
	                			initXY = -o.move * unit;
	                			d.css(direc, initXY+'px');
							}
						}
	            	});
            	}
            	else if(direc=='top'){
	            	d.animate({top:initXY},o.speed,function(){
	            		if(dir=="rb"){
							if(chk("rb")){
								initXY=0;
								d.css(direc, initXY + 'px');
							}
						}
						else if(dir=="lt"){
							if(chk("lt")){
	                			initXY = -o.move * unit;
	                			d.css(direc, initXY+'px');
							}
						}
	            	});
            	}
            }
            $(o.btnlt).click(function(){
                if(!d.is(":animated")){
                	initXY=initXY+o.move;
					doScroll("lt");
                }
            });
            $(o.btnrb).click(function(){
				if(!d.is(":animated")){
					initXY=initXY-o.move;
					doScroll("rb");
				}
            });
            d.hover(function(){
            	atpX == undefined ? clearInterval(atpY) : clearInterval(atpX);
            },function(){
            	if(o.playswitch){
					if(o.effectType==0){
						var atpX = setInterval(autoplay, o.duration);
					}
					else{
						var atpY = setInterval(autoplay, o.duration);
					}
	            }
            });
			//check btn l/r position
            function chk(t){
                if(unit>o.showunit){
                    if(t=="lt"){
                        return parseInt(d.css(direc))==0? true: false;
                    }
                    else if(t=="rb"){
                        return Math.abs(initXY)==(o.move*unit)? true: false;
                    }
                }
                else{
                     return false;       
                }
            };
		});
	}
	
})(jQuery)
