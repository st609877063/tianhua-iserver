   //移动窗口部份
      
      
        var posX;
        var posY;
        fdiv = document.getElementById("HelpTips");

        document.onmouseup = function() {
            document.onmousemove = null;
        }

        function mousemove(ev) {
            if (ev == null) ev = window.event; //如果是IE
            fdiv.style.left = (ev.clientX - posX) + "px";
            fdiv.style.top = (ev.clientY - posY) + "px";
        }

        function CreateSideBarCookie() {
            var SideBarKey = document.getElementById("left").src.substring(document.getElementById("left").src.lastIndexOf('/') + 1, document.getElementById("left").src.lastIndexOf('.'));
            var SideBarValue;
            if (document.getElementById("frmTitle")) {
                if (SideBarValue = document.getElementById("frmTitle").style.display == "") {
                    SideBarValue = "block";
                }
                else {
                    SideBarValue = document.getElementById("frmTitle").style.display;
                }
            }
            var existentSideBarCookie = getCookie("SideBarCookie");
            if (SideBarKey.length != 0 && SideBarValue.length != 0) {
                var temp = "";
                var SideBarKV = existentSideBarCookie;
                if (existentSideBarCookie.length != 0) {
                    if (SideBarKV.indexOf(SideBarKey) != -1) {
                        var arrKV = existentSideBarCookie.split("&");
                        for (var v in arrKV) {
                            if (arrKV[v].indexOf(SideBarKey) != -1) {
                                temp = existentSideBarCookie.replace(arrKV[v], SideBarKey + "=" + SideBarValue);
                            }
                        }
                    }
                    else {
                        temp = SideBarKey + "=" + SideBarValue + "&" + existentSideBarCookie;
                    }
                }
                else {
                    temp = SideBarKey + "=" + SideBarValue;
                }
                setCookie("SideBarCookie", temp, 300, "/", "", false);
            }
            else {
                return "";
            }
        }

        function InitSideBarState() {
            var existentSideBarCookie = getCookie("SideBarCookie");
            var SideBarKey = document.getElementById("left").src.substring(document.getElementById("left").src.lastIndexOf('/') + 1, document.getElementById("left").src.lastIndexOf('.'));
            if (existentSideBarCookie.length != 0 && SideBarKey.length != 0 && existentSideBarCookie.indexOf(SideBarKey) != -1) {
                var arrKV = existentSideBarCookie.split("&");
                for (var v in arrKV) {
                    if (arrKV[v].indexOf(SideBarKey) != -1) {
                        var currentValue = arrKV[v].split("=");
                        ChangeSideBarState(currentValue[1]);
                    }
                }
            }
            else {
                var obj = document.getElementById("switchPoint");
                obj.alt = "关闭左栏";
                obj.src = "static/images/butClose.gif";
                document.getElementById("frmTitle").style.display = "block";
                onload();
            }
           
        }

        function ChangeSideBarState(temp) {
            var obj = document.getElementById("switchPoint");
            if (temp == "none") {
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
                document.getElementById("frmTitle").style.display = "block";
                onload();
            }
        }