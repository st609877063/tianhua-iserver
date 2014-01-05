//====================================================================================================
// [�������] jQuery picScroll
//----------------------------------------------------------------------------------------------------
// [��    ��] ��һ�����jQuery��⣬ʵ��ͼƬͨ����ť���¡����ҹ���Ч���Ĳ����enjoy~
//----------------------------------------------------------------------------------------------------
// [��������] Evan	
// [��  	 ��] frienkyliu@hotmail.com	
// [���߲���] http://www.seeutopia.com
// [QQ ����] 93129471
// [��������] 2011-11-28
// [�� �� ��] 0.1
//====================================================================================================
(function($){
	$.fn.fullSize=function(z){
		var fk=$(this).outerWidth()+parseInt($(this).css("margin-left"))+parseInt($(this).css("margin-right"));
		var fg=$(this).outerHeight()+parseInt($(this).css("margin-top"))+parseInt($(this).css("margin-bottom"));
		return z=='w'? fk : fg;
	};
	$.fn.Scroll=function(options){
		var settings={
            btnlt:null,         //��ťleft/top
            btnrb:null,			//��ťright/bottom
            elemchild:'li',		//��Ԫ�ؼ�ѭ����Ԫ�س�Ա,Ĭ����Ԫ��Ϊli
            move:1,				//�ƶ�һ�����
            showunit:3,			//����������ʾ����
			speed:1000,			//�ٶ�
			playswitch:true,	//�Զ�����
			duration:3000,		//�Զ��������ʱ��
			effectType:0		//Ч�� Ĭ��Ϊ0�����ҹ���
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
            	if(direc=='left'){    //�˴�left��ʾˮƽ
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
