function getElementPosition(elementId){//获得相对位置
    var rd   =   {x:0,y:0};
    var el = $("#"+elementId).position();
    rd.x   +=   el.left;
    rd.y   +=   el.top;
    return   rd  ;
}
/**
 * element jquery 对象
 * */
function getElementOffset(element){//获得相对位置
    var rd   =   {x:0,y:0};
    var el = element.offset();
    rd.x   +=   el.left;
    rd.y   +=   el.top;
    return   rd  ;
}
//弹出对话框
function showmsgcommon(htmlv,widthv,heightv,callback){
	var dlg = new J.ui.dialog({ id: 'msginfo', title:'<h4>提示</h4>', html:htmlv, width:widthv, height:heightv, cover:true, resize:false,rang:true,drag:false});
    dlg.ShowDialog();
    dlg.addBtn( 'confirm', '确定', function(){
    	callback(dlg);
    });
}

function showmsgcfn(type,msg,widthv,heightv,callback){
	var html = '<div align="center" style="padding:30px;font-weight:bold;color:#666;font-size:16px;z-index:99999" ><img src="images/'+type+'.gif" align="absbottom" />&nbsp;&nbsp;'+msg+'</div>';
	showmsgcommon(html,widthv,heightv,callback);
}

function showmsg(type,msg,widthv,heightv){
    showmsgcfn(type,msg,widthv,heightv,function(dlg){
    	dlg.cancel();
    });
}

function showmsgwithcallback(type,msg,widthv,heightv,callback){
    showmsgcfn(type,msg,widthv,heightv,function(dlg){
    	dlg.cancel();
    	callback();
    });
}

//确定对话框
function showcofirmmsgcommon(htmlv,widthv, heightv, callback){
	var html = htmlv;
	var dlg = new J.ui.dialog({ id: 'confirmbox', 
								title:'<h4>提示</h4>', 
								html:html, 
								width: widthv, 
								height: heightv, 
								cover:true, 
								resize:false,
								rang:true,
								drag:false
							});
    dlg.ShowDialog();   
    dlg.addBtn( 'ok', '确定', function(){
    	callback(dlg);
    });
    dlg.addBtn( 'cancel', '取消', function(){
   		 dlg.cancel();
    });
}
function showcofirmmsg(msg,widthv, heightv, callback){
	var html = '<div align="center" style="padding:40px;font-weight:bold;color:#666;font-size:16px;"><img src="images/confirm.gif" align="absmiddle">&nbsp;&nbsp;'+msg+'</div>';
	showcofirmmsgcommon(html,widthv, heightv, callback);
}
//eg:var num = showcofirmmsg(2,10);介于2和10之间（包括2和10）的一个数值
function selectFromRandom(lowerValue,upperValue){
	var choices = upperValue-lowerValue+1;
	return Math.floor(Math.random()*choices+lowerValue);
}
/*******************************一下为JS String扩展************************************/
//统计字数：汉字算两个，英文算一个
String.prototype.fucCheckLength =function(){
    var  i,sum;  
    sum=0;  
    for(i=0;i<this.length;i++){ 
        if  ((this.charCodeAt(i)>=0)  &&  (this.charCodeAt(i)<=255)) 
           sum=sum+1;  
        else  
           sum=sum+2;  
    }  
    return  sum;  
}
//添加替换所有方法
String.prototype.replaceAll = function(s1,s2) { 
    return this.replace(new RegExp(s1,"gm"),s2); 
}
//标签处理
String.prototype.HTMLEnCode = function(){
	    var    s    =    "";  
        if    (this.length    ==    0)    return    "";  
        s    =    this.replace(/&/g,    "&amp;");  
        s    =    s.replace(/</g,        "&lt;");  
        s    =    s.replace(/>/g,        "&gt;");  
        s    =    s.replace(/\"/g,      "&quot;");  
        return    s;  
}
String.prototype.HTMLDeCode = function(){
        var    s    =    "";  
        if    (this.length    ==    0)    return    "";  
        s    =    s.replace(/&lt;/g,        " <");  
        s    =    s.replace(/&gt;/g,        ">");    
        s    =    s.replace(/&quot;/g,      "\"");   
        s    =    this.replace(/&amp;/g,    "&");  
        return    s;
}
//提取查找字符串前面的所有字符
String.prototype.getFront = function(searchStr){
	foundOffset=this.indexOf(searchStr);
    if(foundOffset==-1){
       return null;
    }
    return this.substring(0,foundOffset);
}
//提取查找字符串后面的所有字符
String.prototype.getEnd =function(searchStr){
    foundOffset=this.indexOf(searchStr);
    if(foundOffset==-1){
       return null;
    }
    return this.substring(foundOffset+searchStr.length,this.length);
}
//在字符串 searchStr 前面插入字符串 insertStr 
String.prototype.insertString=function(searchStr,insertStr){
    var front=this.getFront(searchStr);
    var end=this.getEnd(searchStr);
    if(front!=null && end!=null){
       return front+insertStr+searchStr+end;
    }
    return null;
}
//删除字符串 deleteStr
String.prototype.deleteString=function(deleteStr){
    return this.replaceString(deleteStr,"");
}
//将字符串 searchStr 修改为 replaceStr
String.prototype.replaceString=function(searchStr,replaceStr){
    var front=this.getFront(searchStr);
    var end=this.getEnd(searchStr);
    if(front!=null && end!=null){
       return front+replaceStr+end;
    }
    return null;
}
String.prototype.toObject=function(str){
	return eval(str); 
}
/**
 * 过滤IDS昵称非法字符
 * */
String.prototype.stripscript=function(){
	var pattern = new RegExp("[#%&*+\\/:;'\".?<> \r\n]") ;
	var rs = ""; 
	for (var i = 0; i < this.length; i++) { 
	rs = rs+this.substr(i, 1).replace(pattern, ''); 
	} 
	return rs; 
}
/////////////////////////////////////////////////////////////////////
//   字符串中中文与非中文字符的数量
//
// 引数 s   传入的字符串
//
// 返回值  characterNum = {cc:0,ec:0}
/////////////////////////////////////////////////////////////////////
String.prototype.characterCheck=function(){
	var characterNum = {cc:0,ec:0};
	for (var i=0; i<this.length; i++) {
	   var c = this.charCodeAt(i);
	   //单字节
	   if ((c >= 0x0001 && c <= 0x007e) || (0xff60<=c && c<=0xff9f)) {
	    characterNum.ec++;
	   }
	   //双字节
	   else {
	    characterNum.cc++;
	   }
	}
return characterNum;
}
//返回整数
String.prototype.wbCharNum=function(){
	var num = 0.0;
	for (var i=0; i<this.length; i++) {
	   var c = this.charCodeAt(i);
	   //单字节
	   if ((c >= 0x0001 && c <= 0x007e) || (0xff60<=c && c<=0xff9f)) {
	    num+=0.5;
	   }
	   //双字节
	   else {
	    num+=1;
	   }
	}
return Math.floor(num);
}
//返回没取舍的值
String.prototype.wbLength=function(){
	var num = 0.0;
	for (var i=0; i<this.length; i++) {
	   var c = this.charCodeAt(i);
	   //单字节
	   if ((c >= 0x0001 && c <= 0x007e) || (0xff60<=c && c<=0xff9f)) {
	    num+=0.5;
	   }
	   //双字节
	   else {
	    num+=1;
	   }
	}
	return Math.floor(num-URL.processUrl(this));
}


//隐藏dwr的弹出错误窗口
$(document).ready(function(){
	if(typeof(dwr) != "undefined") {
		dwr.engine.setErrorHandler(function(data) {});
	}
});


//改变背景层的高度
var browser=navigator.appName;
var b_version=navigator.appVersion;
var oldheight;

$(window).load(function(){
	oldheight = $("#box_new").height();
	changeBgHeight();
});

function changeBgHeight() {
	if(browser=="Microsoft Internet Explorer") {
		var showHeight = $("#box_new").height() - 200;
		var showHeight2 = $("#box_new").height() - 100;
		var version=b_version.split(";");
		var trim_Version=version[1].replace(/[ ]/g,"");
		if(trim_Version=="MSIE6.0") {
			$(".zzz").css("height",showHeight2);
			$(".zzz_1").css("height",showHeight2);
			$(".tools").css("height",showHeight2);
		}else{
			$(".zzz").css("height",showHeight);
			$(".zzz_1").css("height",showHeight);
			$(".tools").css("height",showHeight);
		}
	}else{
		var showHeight = $("#box_new").height() - 200;
		$(".zzz").css("height",showHeight);
		$(".zzz_1").css("height",showHeight-50);
		$(".tools").css("height",showHeight);

		//$(".zzz").css("height",document.body.scrollHeight-240);
		//$(".zzz_1").css("height",document.body.scrollHeight-240);
		//$(".tools").css("height",document.body.scrollHeight-240);

		$(".list").css("margin-top","-1px");
	}
}
function resetBgHeight() {
	if(browser=="Microsoft Internet Explorer") {
		var showHeight = oldheight - 200;
		var showHeight2 = oldheight - 100;
		var version=b_version.split(";");
		var trim_Version=version[1].replace(/[ ]/g,"");
		if(trim_Version=="MSIE6.0") {
			$(".zzz").css("height",showHeight2);
			$(".zzz_1").css("height",showHeight2);
			$(".tools").css("height",showHeight2);
		}else{
			$(".zzz").css("height",showHeight);
			$(".zzz_1").css("height",showHeight);
			$(".tools").css("height",showHeight);
		}
	}else{
		var showHeight = oldheight - 200;
		$(".zzz").css("height",showHeight);
		$(".zzz_1").css("height",showHeight-50);
		$(".tools").css("height",showHeight);
		$(".list").css("margin-top","-1px");
	}
	//再增加changeBgHeight方法，是为了解决页面背景层突然消失问题
	changeBgHeight();
}

function changeBgHeightForPlugin() {
	var showHeight = $("#box_new").height() - 300;
	var showHeight2 = $("#box_new").height() - 100;
	if(showHeight < 1750) {
		if(browser=="Microsoft Internet Explorer") {
			var version=b_version.split(";");
			var trim_Version=version[1].replace(/[ ]/g,"");
			if(trim_Version=="MSIE6.0") {
				$(".zzz").css("height", showHeight2);
				$(".tools").css("height", showHeight2);
			}else{
				$(".zzz").css("height", showHeight + $(".plugin").height());
				$(".tools").css("height", showHeight + $(".plugin").height());
			}
		}else{
			$(".zzz").css("height", showHeight + $(".plugin").height());
			$(".tools").css("height", showHeight + $(".plugin").height());

			//$(".zzz").css("height",document.body.scrollHeight-150);
			//$(".tools").css("height",document.body.scrollHeight-150);
		}
	}
}
//处理URL
	var URL = function(){
		return{
			checkUrl:function(str){
				var pattern = /http+:\/\/[^\s|^(\u4e00-\u9fa5)]*/i ; 
				var testStr = str;
				var flag = true;
				var ret = new Array();
				while(flag){
					(function(){
						var mts = pattern.exec(testStr); 
						if (mts != null) 
						{
							ret.push(mts[0]);
							testStr = RegExp.rightContext;
						}else{
							flag =false;
						}
					})();
				}
				return ret;
			},
		urlLengthDifference:function (arr){
			var str =arr.join("");
			return (str.length - arr.length*18)/2;
		},
		processUrl:function(mess){
			var arr = URL.checkUrl(mess);
			var num = URL.urlLengthDifference(arr);
			return num;
		}
		}
	}();
