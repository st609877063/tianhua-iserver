var displaymode = 0;
var StyleSheetPath, _BasePath, _adminPath, _adminName;
function setStyleSheetPath(path) { StyleSheetPath = path; }
function setBasePath(basepath, adminpath) { _BasePath = basepath; _adminPath = adminpath; }
function setAdminName(adminname) { _adminName = adminname; };
//修改IE下document.getElementById在id和name同名时的bug
if (/msie/i.test(navigator.userAgent)) //根据userAgent确定用户使用IE浏览器
{
    document.nativeGetElementById = document.getElementById;
    document.getElementById = function(id) {
        var elem = document.nativeGetElementById(id);
        if (elem) {              //修改后的确认能得到id属性方法 
            if (elem.attributes['id'].value == id) {
                return elem;
            } else {                  //如果没有ID相同的,那么就遍历所有元素的集合找到id相同的元素
                for (var i = 1; i < document.all[id].length; i++) {
                    if (document.all[id][i].attributes['id'].value == id) {
                        return document.all[id][i];
                    }
                }
            }
        }
        return null;
    };
}

function setCookie(name, value, expires, path, domain, secure) {
    var today = new Date();
    today.setTime(today.getTime());
    if (expires) {
        expires = expires * 1000 * 60 * 60 * 24;
    }
    var expires_date = new Date(today.getTime() + (expires)); document.cookie = name + '=' + escape(value) + ((expires) ? ';expires=' + expires_date.toGMTString() : '') + ((path) ? ';path=' + path : '') + ((domain) ? ';domain=' + domain : '') + ((secure) ? ';secure' : '');
}

function getCookie(name) {
    if (document.cookie.length > 0) {
        cookieStart = document.cookie.indexOf(name + "=")
        if (cookieStart != -1) {
            cookieStart = cookieStart + name.length + 1
            cookieEnd = document.cookie.indexOf(";", cookieStart)
            if (cookieEnd == -1) cookieEnd = document.cookie.length
            return unescape(document.cookie.substring(cookieStart, cookieEnd))
        }
    }
    return ""
}



function jumpto(inputurl) {
    if (document.getElementById && displaymode == 0)
        document.getElementById("main_right").src = inputurl
    else if (document.all && displaymode == 0)
        document.all.external.src = inputurl;
    else {
        if (!window.win2 || win2.closed)
            win2 = window.open(inputurl);
        else {
        }
    }
}

function ShowMain(FileName_Left, FileName_Right) {
    var temp;
    if (FileName_Left != "") {
        var checkLeftUrl = CheckCurrentLeftUrl(FileName_Left);
        if (checkLeftUrl == "false") {
            temp = document.getElementById("left");
            temp.src = FileName_Left + GetUrlParm(FileName_Left);
            temp.contentWindow.window.name = "left";
            frames["left"] = temp.contentWindow.window;
        }
    }
    if (FileName_Right != "") {
        temp = document.getElementById("main_right");
        temp.src = FileName_Right + GetUrlParm(FileName_Right);
        temp.contentWindow.window.name = "main_right";
        frames["main_right"] = temp.contentWindow.window;
    }
    //InitSideBarState();
}

function CheckCurrentLeftUrl(leftUrl) {
    var src = document.getElementById("left").src;
    var regex = /\s*[\?&]{1,1}t=[0-9]{1,}$/;
    var currentLeftUrl = src.replace(regex, '');
    if (currentLeftUrl.lastIndexOf(leftUrl) >= 0) {
        if (currentLeftUrl.lastIndexOf(leftUrl) == (currentLeftUrl.length - leftUrl.length)) {
            return "true";
        }
    }
    return "false";
}

function GetUrlParm(url) {
    var urlparm = "?";
    if (url.indexOf('?') >= 0) {
        urlparm = "&";
    }
    urlparm = urlparm + "t=" + GetRandomNum();
    return urlparm;
}

function GetRandomNum() {
    var Range = 1000;
    var Rand = Math.random();
    return (Math.round(Rand * Range));
}

function switchSysBar() {
    var obj = document.getElementById("switchPoint");
    if (obj.alt == "关闭左栏") {
        obj.alt = "打开左栏";
        obj.src = "static/images/butOpen.gif";
        document.getElementById("frmTitle").style.display = "none";
        var width, height;
        width = document.body.clientWidth - 12;
        height = document.body.clientHeight - 70;
        document.getElementById("main_right").style.height = height;
        document.getElementById("main_right").style.width = width;
        document.getElementById("FrameTabs").style.width = width;
        if (CheckFramesScroll) { CheckFramesScroll(); }
    }
    else {
        obj.alt = "关闭左栏";
        obj.src = "static/images/butClose.gif";
        document.getElementById("frmTitle").style.display = "";
        onload();
    }
    //CreateSideBarCookie();
}

var tID = "ChannelMenu_MenuMyDeskTop";

function ShowHideLayer(ID) {
    if (ID != tID) {
        var triangle = document.getElementById("MenuMyDeskTopMore_Triangle");
        if (tID != "") {
            document.getElementById(tID).style.display = "none";
            document.getElementById("A" + tID).style.backgroundImage = "url(static/images/digital_left.gif)";
            if (document.getElementById("A" + tID).childNodes.length < 2) {
                document.getElementById("Span" + tID).style.backgroundImage = "url(static/images/digital_side.gif)";
            } else {
                document.getElementById("Span" + tID).style.backgroundImage = "none";
                if (triangle) { triangle.style.backgroundImage = "none"; }
            }
            document.getElementById("Span" + tID).className = "digitaltext";
        }        
        document.getElementById(ID).style.display = "";
        document.getElementById("A" + ID).style.backgroundImage = "url(static/images/seg_left.gif)";
        if (document.getElementById("A" + ID).childNodes.length < 2) {
            document.getElementById("Span" + ID).style.backgroundImage = "url(static/images/seg_side.gif)";
            if (tID != "" && document.getElementById("A" + tID).childNodes.length >= 2) {
                if (triangle) { triangle.style.backgroundImage = "url(static/images/digital_side.gif)"; }
            }
        } else {
            document.getElementById("Span" + ID).style.backgroundImage = "none";
            if (triangle) { triangle.style.backgroundImage = "url(static/images/seg_side.gif)"; }
        }
        document.getElementById("Span" + ID).className = "segtext";
        tID = ID;
    }
}

var message = 0, order = 0, time = 0, uncheckarticle = 0, remindItemCount = 0;
var unsigninarticle = 0, stockalarm = 0, noconsignment = 0, noconfirmcount = 0;
var mintime = 5000, addtime = 1000, maxtime = 50000;
var clock;
function timeOut() {
    if (time >= (maxtime - mintime) / addtime)
        time = (maxtime - mintime) / addtime;
    return mintime + addtime * time;
}
function OpenLink(FileName_Left, FileName_Right) {
    if (parent.document.getElementById("left").src != FileName_Left) {
        parent.document.getElementById("left").src = FileName_Left;
    }
    var mr = parent.document.getElementById("main_right");
    mr.src = FileName_Right;
    mr.contentWindow.window.name = "main_right";
    frames["main_right"] = mr.contentWindow.window;
}


//弹出帮助部份
var IsHelpShowCheck = false;

function HelpTipsCheck() {
        var HelpShow = $("HelpShow", s).text();
        if (HelpShow == "true") {
            lock();
        }
}
//
function lock() {
    $("#HelpTips").css("left", (document.body.clientWidth - 500) / 2 + document.body.scrollLeft);
    $("#HelpTips").css("top", (document.body.clientHeight - 300) / 2 + document.body.scrollTop);
    $("#HelpTips").css("display", "block");
    $("#ly").css("display", "block");
    $("#ly").css("width", document.body.clientWidth);
    $("#ly").css("height", document.body.clientHeight);
    GetHelpContent();
}

function dosomething() {
	    $.post("<?xml version='1.0' encoding='utf-8'?><root><type>SetHelpShow</type><helpset>" + IsHelpShowCheck + "</helpset></root>");
    $("#ly").css("display", "none");
    $("#HelpTips").css("display", "none");
}

function HelpShowCheck() {
    if (IsHelpShowCheck)
    { IsHelpShowCheck = false; }
    else {
        IsHelpShowCheck = true;
    }
}

function HelpShow() {
    $.post("common/HelpLinks.xml", function(s) {
        var HelpShow = $("HelpShow", s).text();
        if (HelpShow == "false") {
            IsHelpShowCheck = true;
            $('input[name=HelpShowFalse]').attr('checked', true);
        }
    });
    lock();
}
//
function GetHelpContent() {
    $.ajax({
        url:'common/HelpLinks.xml',
        type: 'post',
        dataType: 'xml',
            error: function(xml) {
                   $("#HelpInfo").html("Error loading XML,检查XML文件是否存在!");
                },
           
        	success: function(xml) {
            var HtmlStr = "";
            $(xml).find("Link").each(function(i) {
                var title = $(this).children("title").text();
                var url = $(this).children("url").text();
                HtmlStr = HtmlStr + "<li> <a href=\"" + url + "\" target=\"_blank\">" + title + "</a></li>";
            });
            HtmlStr = HtmlStr + "<li><a href=\"http://www.baidu.com/\" target=\"_blank\">更多......</a></li>";
            HtmlStr = "<ul style=\"line-height: 20px;\">" + HtmlStr + "</ul>";
            $("#HelpInfo").html(HtmlStr);
        }
    });
}

/*快捷导航开始*/
function ShowQuickNavigation() {
    window.isQuicknavigationShow = false;
    jQuery("#MenuMyDeskTopMore_Triangle").click(function(e) {
        e.stopPropagation();
        if (!window.isQuicknavigationShow) {
            var offset = jQuery(this).offset();
            offset.top += 20;
            var qnDiv = $("#quicknavigation").css({ top: offset.top, left: offset.left });
            if (qnDiv.length > 0) {
                qnDiv.show();
            } else {
                var template = '<div id="quicknavigation"><dl class="indexContent"><dt class="Border Title"><ul class="UpDown">';
                template += '<li><a title="向上滚动"  class="up" href="javascript:"  onmouseover="this.className=\'up_hover\'"  onmouseout="this.className=\'up_out\'"></a></li><li><a title="向下滚动"  class="dn" href="#"  onmouseover="this.className=\'dn_hover\'"  onmouseout="this.className=\'dn_out\'"></a></li>';
                template += '</ul>快捷导航(<a class="refreshQN" href="javascript:">刷新</a>)</dt><dd class="Border List"><img src="static/images/loading.gif" /><br/>数据加载中...</dd></dl></div>';
                jQuery(template).appendTo("body");
                qnDiv = jQuery("#quicknavigation").css({ top: offset.top, left: offset.left })
                    .show()
					.bind("mouseleave", function(event) {
					    jQuery(this).hide();
					    window.isQuicknavigationShow = false;
					}).find(".Title .refreshQN").click(function() {
					    GetQuickNavigation();
					});
                GetQuickNavigation(qnDiv, offset);
            }
            window.isQuicknavigationShow = true;
        } else {
            $("#quicknavigation").hide();
            window.isQuicknavigationShow = false;
        }
    });
}

function ScrollList(list, event, y) {
    if (!y) {
        if (event.wheelDelta) {
            y = event.wheelDelta / 3;
        } else if (event.detail) {
            y = -event.detail * 10;
        }
    }
    var jList = jQuery(list);
    var mt = jList.css("margin-top");
    mt = Number(mt.toLowerCase().replace("px", ""));
    if ((mt < 0 && y > 0) || (mt - jList.parent().height() > -jList.height()) && y < 0) {
        mt = mt + y;
        if (mt > 0) { mt = 0; }
        if (mt - jList.parent().height() < -jList.height()) { mt = jList.parent().height() - jList.height(); }
        jList.css("margin-top", mt);
    }
}
/*快捷导航结束*/