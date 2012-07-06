var $ = jQuery;
var thespeed = 5;
var navIE = document.all && navigator.userAgent.indexOf("Firefox")==-1;
var myspeed=0;
$(function(){
		
		//快捷菜单
		bindQuickMenu();
		
		//左侧菜单开关
		LeftMenuToggle();
		
		//全部功能开关
		AllMenuToggle();

		//取消菜单链接虚线
		$(".head").find("a").click(function(){$(this).blur();});
		$(".menu").find("a").click(function(){$(this).blur();});
		
		/*
		//载入滚动消息
		$.get('getdedesysmsg.php',function(data){
			if(data != ''){
				$(".scroll").html(data);
				$(".scroll").Scroll({line:1,speed:500,timer:3000});
			}
			else
			{
				$(".scroll").html("无法读取织梦官方消息");
			}
		});
		*/
		
		
	}).keydown(function(event){//快捷键
		if(event.keyCode ==116 ){
			//url = $("#main").attr("src");
			//main.location.href = url;
			//return false;	
		}
		if(event.keyCode ==27 ){
			$("#qucikmenu").slideToggle("fast");
		}
});
	
function bindQuickMenu(){//快捷菜单
		$("#ac_qucikmenu").bind("mouseenter",function(){
			$("#qucikmenu").slideDown("fast");
		}).dblclick(function(){
			$("#qucikmenu").slideToggle("fast");
		}).bind("mouseleave",function(){
			hidequcikmenu=setTimeout('$("#qucikmenu").slideUp("fast");',700);
			$(this).bind("mouseenter",function(){clearTimeout(hidequcikmenu);});
		});
		$("#qucikmenu").bind("mouseleave",function(){
			hidequcikmenu=setTimeout('$("#qucikmenu").slideUp("fast");',700);
			$(this).bind("mouseenter",function(){clearTimeout(hidequcikmenu);});
		}).find("a").click(function(){
			$(this).blur();
			$("#qucikmenu").slideUp("fast");
			//$("#ac_qucikmenu").text($(this).text());
		});
}
	
function LeftMenuToggle(){//左侧菜单开关
		$("#togglemenu").click(function(){
			if($("body").attr("class")=="showmenu"){
				$("body").attr("class","hidemenu");
				$(this).html("显示菜单");
			}else{
				$("body").attr("class","showmenu");
				$(this).html("隐藏菜单");
			}
		});
	}
	
	
function AllMenuToggle(){//全部功能开关
		mask = $(".pagemask,.iframemask,.allmenu");
		$("#allmenu").click(function(){
				mask.show();
		});
		//mask.mousedown(function(){alert("123");});
		mask.click(function(){mask.hide();});
}
	
function AC(act){	
		//alert(act);
		mlink = $("a[id='"+act+"']");	
		if(mlink.size()>0){
			box = mlink.parents("div[id^='menu_']");
			boxid = box.attr("id").substring(5,128);
			if($("body").attr("class")!="showmenu")$("#togglemenu").click();
			if(mlink.attr("_url")){
				$("#menu").find("div[id^=menu]").hide();
				box.show();
				mlink.addClass("thisclass").blur().parents("#menu").find("ul li a").not(mlink).removeClass("thisclass");
				if($("#mod_"+boxid).attr("class")==""){
					$("#nav").find("a").removeClass("thisclass");
					$("#nav").find("a[id='mod_"+boxid+"']").addClass("thisclass").blur();
				}
				main.location.href = mlink.attr("_url");
			}else if(mlink.attr("_open") && mlink.attr("_open")!=undefined){
				window.open(mlink.attr("_open"));
			}
		}
}

/*********************
 * 滚动按钮设置
*********************/

function scrollwindow()
{
	parent.frames['menu'].scrollBy(0,myspeed);
}

function initializeIT()
{
	if (myspeed!=0) {
		scrollwindow();
	}
}
	
