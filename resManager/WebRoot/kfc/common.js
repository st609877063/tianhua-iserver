
$(function() {
	$('#eventBtn_c').click(function() {
				$('#c_content').hide();
				$('#e_content').hide();
				$('#eventInner').show();
				index = 0;
				showImg(index);
				$('#helpArea').show();
				$('.content_box_right_3').hide();
			})
	$('#eventBtn_e').click(function() {
				$('#c_content').hide();
				$('#e_content').hide();
				$('#eventInner').show();
				index = 0;
				showImg(index);
				$('#helpArea').show();
				$('.content_box_right_3').hide();
			})
	$('#china li').click(function() {
		$('#c_content').show();
		$('#c_content .content_box_left_main_right_c ul').eq(0).show();
		$('#eventInner').hide();
		$(this).addClass('on').siblings().removeClass('on');
		index = $('#china li').index(this);
		$('#c_content .content_box_left_main_right_c ul').eq(index)
				.show().siblings().hide();
		$('#helpArea').show();
		$('.content_box_right_3').show();
	})
	$('#English li').click(function() {
		$('#c_content').show();
		$('#c_content .content_box_left_main_right_c ul').eq(0).show();
		$('#eventInner').hide();
		$(this).addClass('on').siblings().removeClass('on');
		index = $('#English li').index(this);
		$('#c_content .content_box_left_main_right_c ul').eq(index)
				.show().siblings().hide();
		$('#helpArea').show();
		$('.content_box_right_3').show();
	})
	$('#menuE').click(function() {
		$('#English').show();
		$('#china').hide();
		index = 0;
		showImg(index);
		$('#e_content .content_box_left_main_right_c ul').eq(index)
				.show().siblings().hide();
		$('#English li').removeClass('on');
		$('#china li').removeClass('on');
	})
	$('#menuC').click(function() {
		index = 0;
		showImg(index);
		$('#china').show();
		$('#English').hide();
		index = 0;
		showImg(index);
		$('#c_content .content_box_left_main_right_c ul').eq(index)
				.show().siblings().hide();
		$('#English li').removeClass('on');
		$('#china li').removeClass('on');
	})
	var len = $(".solid li").length;
	var index = 0;
	var adTimer;
	$(".solid li").mouseover(function() {
				index = $(".solid li").index(this);
				showImg(index);
			}).eq(0).mouseover();
	// 滑入 停止动画，滑出开始动画.
	$('.eventArea').hover(function() {
				clearInterval(adTimer);
			}, function() {
				adTimer = setInterval(function() {
							showImg(index)
							index++;
							if (index == len) {
								index = 0;
							}
						}, 3000);
			}).trigger("mouseleave");
	$('.content_box_right_3 li').click(function() {
				$('#c_content').hide();
				$('#e_content').hide();
				$('#eventInner').show();
				index = $(".content_box_right_3 li").index(this);
				showImg(index);
				$('.content_box_right_3').hide();
				$('#helpArea').show();
				$('#English li').removeClass('on');
				$('#china li').removeClass('on');
			});
})

// 通过控制top ，来显示不同的幻灯片
function showImg(index) {
	$('.popInner div').eq(index).fadeIn().siblings().fadeOut();
}
function setCNLeftMenu(BtnId, divId, index, count) {
	clearDetailInfo_Div();
	for (var i = 1; i <= count; i++) {
		if (i != index) {
			if ((i == 1) || (i == 11)) {
				getEId("cn_menu" + i).className = 'menu_link';
				getEId("cn_index_left_menu0" + i).className = 'Index_Left_Menu2';
			} else {
				getEId("cn_menu" + i).className = 'menu_link';
				getEId("cn_index_left_menu0" + i).className = 'Index_Left_Menu';
			}
		}
	}
	if (getEId(divId).className != "Index_Left_Menu_on") {
		getEId(divId).className = 'Index_Left_Menu_on';
		getEId(BtnId).className = 'menu_link_current';
	} else {
		if ((index == 1) || (index == 11)) {
			getEId(divId).className = 'Index_Left_Menu2';
		} else {
			getEId(divId).className = 'Index_Left_Menu';
		}
		getEId(BtnId).className = 'menu_link';
	}
}

function setCNLeftMenu(BtnId, divId, index, count) {
	clearDetailInfo_Div();
	for (var i = 1; i <= count; i++) {
		if (i != index) {
			if ((i == 1) || (i == 11)) {
				getEId("cn_menu" + i).className = 'menu_link';
				getEId("cn_index_left_menu0" + i).className = 'Index_Left_Menu2';
			} else {
				getEId("cn_menu" + i).className = 'menu_link';
				getEId("cn_index_left_menu0" + i).className = 'Index_Left_Menu';
			}
		}
	}
	if (getEId(divId).className != "Index_Left_Menu_on") {
		getEId(divId).className = 'Index_Left_Menu_on';
		getEId(BtnId).className = 'menu_link_current';
	} else {
		if ((index == 1) || (index == 11)) {
			getEId(divId).className = 'Index_Left_Menu2';
		} else {
			getEId(divId).className = 'Index_Left_Menu';
		}
		getEId(BtnId).className = 'menu_link';
	}
}

function setENLeftMenu(BtnId, divId, index, count) {
	clearDetailInfo_Div();
	for (var i = 1; i <= count; i++) {
		if (i != index) {
			if ((i == 1) || (i == 11)) {
				getEId("en_menu" + i).className = 'menu_link';
				getEId("en_index_left_menu0" + i).className = 'Index_Left_Menu2';
			} else {
				getEId("en_menu" + i).className = 'menu_link';
				getEId("en_index_left_menu0" + i).className = 'Index_Left_Menu';
			}
		}
	}
	if (getEId(divId).className != "Index_Left_Menu_on") {
		getEId(divId).className = 'Index_Left_Menu_on';
		getEId(BtnId).className = 'menu_link_current';
	} else {
		if ((index == 1) || (index == 11)) {
			getEId(divId).className = 'Index_Left_Menu2';
		} else {
			getEId(divId).className = 'Index_Left_Menu';
		}
		getEId(BtnId).className = 'menu_link_current';
	}
}

function getEId(id) {
	return document.getElementById(id);
}

// 首页左边菜单 显示层，根据传入的层名称
function showDiv(urlObj, e) {
	var urlObjH = getTop(urlObj) - document.documentElement.scrollTop;// 当前链接相对于屏幕高度
	var screenH = document.documentElement.clientHeight;// 当前屏幕高度
	var floatDiv = getEId(e);// 当前的弹出层
	var floatDivH = 230;// 当前弹出层的高度
	if ((screenH - urlObjH) > floatDivH) {
		floatDiv.style.top = getTop(urlObj) + "px";
		if (!document.all) {
			floatDiv.childNodes[1].style.paddingTop = "20px";
		} else {
			floatDiv.firstChild.style.paddingTop = "20px";
		}
	} else {
		floatDiv.style.top = getTop(urlObj) + (screenH - urlObjH - floatDivH)
				+ "px";
		if (!document.all) {
			floatDiv.childNodes[1].style.paddingTop = urlObjH
					- (screenH - floatDivH) + 20 + "px";
		} else {
			floatDiv.firstChild.style.paddingTop = urlObjH
					- (screenH - floatDivH) + 20 + "px";
		}
	}
	var divObj = document.getElementsByTagName("div");
	for (var i = 0; i < divObj.length; i++) {
		if (divObj[i].getAttribute("ref") == "DetailInfo_Div") {
			divObj[i].style.display = "none";
		}
	}
	getEId(e).style.display = "block";
}
// 获取元素在窗口中的绝对位置
function getTop(e) {
	var offset = e.offsetTop;
	if (e.offsetParent != null)
		offset += getTop(e.offsetParent);
	return offset;
}
// 首页左边菜单 关闭层，根据传入的层名称
function closeDiv(e) {
	getEId(e).style.display = "none";
}

// 设定当前选中的支付银行图片
function setPR(e) {
	/*for (var i = 1; i <= 12; i++) {
		getEId("PR_" + i).className = "";
	}*/
	//getEId("PR_" + e).className = "PR_Selected";
	ifDonation();
}

function showPR(e) {
	if (e == 0) {
		//getEId("label_0").className = "red";
		//getEId("label_1").className = "";
		//getEId("PaymentRadio_0").style.display = "block";
		//getEId("PaymentRadio_1").style.display = "none";
	} else {
		actionCall("/kfcios/OrderAction/Tipflag.action", {}, function(out) {
			var msg = out.msg;
			if (msg != '') {
				$.alerts.cancelButton = '&nbsp;取消&nbsp;';
				jConfirm(msg, "温馨提示", function(b) {
							if (!b) {
								//getEId("label_0").className = "";
								//getEId("label_1").className = "red";
								//getEId("PaymentRadio_0").style.display = "none";
								//getEId("PaymentRadio_1").style.display = "block";
							} else {
								var paymentRadio = document
										.getElementsByName("paymentRadio");
								paymentRadio[0].checked = "checked";

								//getEId("label_0").className = "red";
								//getEId("label_1").className = "";
								//getEId("PaymentRadio_0").style.display = "block";
								//getEId("PaymentRadio_1").style.display = "none";
							}
						});
			} else {
				//getEId("label_0").className = "";
				//getEId("label_1").className = "red";
				//getEId("PaymentRadio_0").style.display = "none";
				//getEId("PaymentRadio_1").style.display = "block";
			}
		});

	}
}

// 显示隐藏产品点餐页面-点餐分类
function showOrderList(table, orderListId) {
	if (getEId(orderListId).style.display != "block") {
		getEId(orderListId).style.display = "block";
		table.className = "OP_Clip_Title";
	} else {
		getEId(orderListId).style.display = "none";
		table.className = "OP_Clip_Title2";
	}
}

function show2() {
	var title = document.getElementById("title");
	var content = document.getElementById("content");
	if (content.style.display == "none") {
		content.style.display = "block"
		title.style.display = "none"
	} else {
		content.style.display == "none"
		title.style.display == "block"
	}
}

function show3() {
	var title = document.getElementById("title3");
	var content = document.getElementById("content3");
	if (content.style.display == "none") {
		content.style.display = "block"
		title.style.display = "none"
	} else {
		content.style.display == "none"
		title.style.display == "block"
	}
}

function show4() {
	var title = document.getElementById("title4");
	var content = document.getElementById("content4");
	if (content.style.display == "none") {
		content.style.display = "block"
		title.style.display = "none"
	} else {
		content.style.display == "none"
		title.style.display == "block"
	}
}

function show5() {
	var title = document.getElementById("title5");
	var content = document.getElementById("content5");
	if (content.style.display == "none") {
		content.style.display = "block"
		title.style.display = "none"
	} else {
		content.style.display == "none"
		title.style.display == "block"
	}
}

function show_content() {
	document.getElementById("content2").style.display = "block";
	document.getElementById("content2_en").style.display = "none";
}

function show_content2() {
	document.getElementById("content2").style.display = "none";
	document.getElementById("content2_en").style.display = "block";
}

function setEdit(contentDiv, btnDiv) {
	if (btnDiv == "btn1") {
		getEId(contentDiv).innerHTML = '<input name="textfield6" type="text" class="form_green" id="textfield4" style="width:140px;" value="13795335133" />';
		getEId(btnDiv).innerHTML = '<a href="javascript:;" class="greenU_link">保存/Save</a>';
	} else {
		getEId(contentDiv).innerHTML = '<input name="txt_pwd" type="password" class="form_green" id="txt_pwd" style="width:140px;" value="587084100" />';
		getEId(btnDiv).innerHTML = '<a href="javascript:;" onclick="updatepassword();" class="greenU_link">保存/Save</a>';
	}
}

function setEdit2(contentDiv, btnDiv) {
	if (btnDiv == "btn1") {
		getEId(contentDiv).innerHTML = '<input name="textfield6" type="text" class="form_green" id="textfield4" style="width:140px;" value="13795335133" />';
		getEId(btnDiv).innerHTML = ' ';
	} else {
		getEId(contentDiv).innerHTML = '<input name="txt_pwd" type="password" class="form_green" id="txt_pwd" style="width:140px;" value="587084100" />';
		getEId(btnDiv).innerHTML = ' ';
	}
}

function ShowList(titleid, contentid) {
	if (document.getElementById(contentid).style.display == 'block') {
		document.getElementById(contentid).style.display = "none";
		document.getElementById(titleid).className = 'help_green';
	} else {
		document.getElementById(contentid).style.display = "block";
		document.getElementById(titleid).className = 'help_red';
	}
}

function ShowList2(titleid, contentid) {
	if (document.getElementById(contentid).style.display == 'block') {
		document.getElementById(contentid).style.display = "none";
		document.getElementById(titleid).className = 'help_green02';
	} else {
		document.getElementById(contentid).style.display = "block";
		document.getElementById(titleid).className = 'help_red02';
	}
}

function setCity(a) {
	a.className = "c";
}
function clearCity(a) {
	a.className = "";
}

function SwitchLanguage(divid) {
	if (divid == 'english1') {
		$("#english1").show();
		$("#china1").hide();
	}
	if (divid == 'china1') {
		$("#english1").hide();
		$("#china1").show();
	}
}
function LoadDiv() {
	document.getElementById('china').style.display = 'none';
	document.getElementById('english').style.display = 'none';
}

function clearDetailInfo_Div() {
	var divObj = document.getElementsByTagName("div");
	for (var i = 0; i < divObj.length; i++) {
		if (divObj[i].getAttribute("ref") == "DetailInfo_Div") {
			divObj[i].style.display = "none";
		}
	}
}

// 首页左边菜单 显示层，根据传入的层名称
function showDivL(urlObj, e, l) {
	var urlObjW = getLeft(urlObj) - document.documentElement.scrollLeft;// 当前链接相对于屏幕高度
	var urlObjH = getTop(urlObj) - document.documentElement.scrollTop;// 当前链接相对于屏幕高度
	var screenW = document.documentElement.clientWidth;// 当前屏幕高度
	var screenH = document.documentElement.clientWidth;// 当前屏幕高度
	var floatDiv = getEId(e);// 当前的弹出层
	var floatDivW = 388;// 当前弹出层的高度
	var floatDivH = 208;// 当前弹出层的高度
	if ((screenH - urlObjH) > floatDivH) {
		floatDiv.style.top = getTop(urlObj) + "px";
		if (!document.all) {
			floatDiv.childNodes[1].style.paddingTop = "20px";
		} else {
			floatDiv.firstChild.style.paddingTop = "20px";
		}
	} else {
		floatDiv.style.top = getTop(urlObj) + (screenH - urlObjH - floatDivH)
				+ "px";
		if (!document.all) {
			floatDiv.childNodes[1].style.paddingTop = urlObjH
					- (screenH - floatDivH) + 20 + "px";
		} else {
			floatDiv.firstChild.style.paddingTop = urlObjH
					- (screenH - floatDivH) + 20 + "px";
		}
	}


	if (l == 0) {
		if ((screenW - urlObjW) > floatDivW) {
			floatDiv.style.left = getLeft(urlObj) - 70 + "px";
			if (!document.all) {
				floatDiv.childNodes[1].style.paddingLeft = "0px";
			} else {
				floatDiv.firstChild.style.paddingLeft = "0px";
			}
		} else {
			floatDiv.style.left = getLeft(urlObj)
					+ (screenW - urlObjW - floatDivW) + "px";
			if (!document.all) {
				floatDiv.childNodes[1].style.paddingLeft = urlObjW
						- (screenW - floatDivW) + 20 + "px";
			} else {
				floatDiv.firstChild.style.paddingLeft = urlObjW
						- (screenW - floatDivW) + 20 + "px";
			}
		}
	}

	var divObj = document.getElementsByTagName("div");
	for (var i = 0; i < divObj.length; i++) {
		if (divObj[i].getAttribute("ref") == "DetailInfo_Div") {
			divObj[i].style.display = "none";
		}
	}
	getEId(e).style.display = "block";
}

// 点击超链接显示层
function showDivByA(urlObj, e, l,pic_height) {
	var explorer = window.navigator.userAgent ;
	var browser = navigator.appName;
	var b_version=navigator.appVersion;
	var version = b_version.split(";");
	var trim_version = version[1].replace(/[ ]/g,"");
	if(browser=="Microsoft Internet Explorer" && trim_version=="MSIE6.0")
	{
		pic_height=155;
	}else if(browser=="Microsoft Internet Explorer" && trim_version=="MSIE7.0")
	{
		pic_height=155;
	}else{
		pic_height=0;
	}
	var urlObjW = getLeft(urlObj) - document.documentElement.scrollLeft;// 当前链接相对于屏幕高度
	var urlObjH = getTop(urlObj) - document.documentElement.scrollTop;// 当前链接相对于屏幕高度
	var screenW = document.documentElement.clientWidth;// 当前屏幕高度
	var screenH = document.documentElement.clientWidth;// 当前屏幕高度
	var floatDiv = getEId(e);// 当前的弹出层
	var floatDivW = 388;// 当前弹出层的高度
	var floatDivH = 208;// 当前弹出层的高度
	if ((screenH - urlObjH) > floatDivH) {
		floatDiv.style.top = getTop(urlObj)-pic_height + "px";
		if (!document.all) {
			floatDiv.childNodes[1].style.paddingTop = "20px";
		} else {
			floatDiv.firstChild.style.paddingTop = "20px";
		}
	} else {
		floatDiv.style.top = getTop(urlObj) + (screenH - urlObjH - floatDivH)-pic_height
				+ "px";
		if (!document.all) {
			floatDiv.childNodes[1].style.paddingTop = urlObjH
					- (screenH - floatDivH) + 20 + "px";
		} else {
			floatDiv.firstChild.style.paddingTop = urlObjH
					- (screenH - floatDivH) + 20 + "px";
		}
	}


	if (l == 0) {
		if ((screenW - urlObjW) > floatDivW) {
			floatDiv.style.left = getLeft(urlObj) - 70 + "px";
			if (!document.all) {
				floatDiv.childNodes[1].style.paddingLeft = "0px";
			} else {
				floatDiv.firstChild.style.paddingLeft = "0px";
			}
		} else {
			floatDiv.style.left = getLeft(urlObj)
					+ (screenW - urlObjW - floatDivW) + "px";
			if (!document.all) {
				floatDiv.childNodes[1].style.paddingLeft = urlObjW
						- (screenW - floatDivW) + 20 + "px";
			} else {
				floatDiv.firstChild.style.paddingLeft = urlObjW
						- (screenW - floatDivW) + 20 + "px";
			}
		}
	}

	var divObj = document.getElementsByTagName("div");
	for (var i = 0; i < divObj.length; i++) {
		if (divObj[i].getAttribute("ref") == "DetailInfo_Div") {
			divObj[i].style.display = "none";
		}
	}
	getEId(e).style.display = "block";
}

function showDivBY1(urlObj, e, l) {
	var urlObjW = getLeft(urlObj) - document.documentElement.scrollLeft + 300;// 当前链接相对于屏幕高度
	var urlObjH = getTop(urlObj) - document.documentElement.scrollTop;// 当前链接相对于屏幕高度
	var screenW = document.documentElement.clientWidth;// 当前屏幕高度
	var screenH = document.documentElement.clientWidth;// 当前屏幕高度
	var floatDiv = getEId(e);// 当前的弹出层
	var floatDivW = 388;// 当前弹出层的高度
	var floatDivH = 208;// 当前弹出层的高度
	if ((screenH - urlObjH) > floatDivH) {
		floatDiv.style.top = getTop(urlObj) + 30 + "px";
		if (!document.all) {
			floatDiv.childNodes[1].style.paddingTop = "20px";
		} else {
			floatDiv.firstChild.style.paddingTop = "20px";
		}
	} else {
		floatDiv.style.top = getTop(urlObj) + (screenH - urlObjH - floatDivH)
				+ "px";
		if (!document.all) {
			floatDiv.childNodes[1].style.paddingTop = urlObjH
					- (screenH - floatDivH) + 20 + "px";
		} else {
			floatDiv.firstChild.style.paddingTop = urlObjH
					- (screenH - floatDivH) + 20 + "px";
		}
	}

	if (l == 0) {
		if ((screenW - urlObjW) > floatDivW) {
			floatDiv.style.left = getLeft(urlObj) + 30 + "px";
			if (!document.all) {
				floatDiv.childNodes[1].style.paddingLeft = "0px";
			} else {
				floatDiv.firstChild.style.paddingLeft = "0px";
			}
		} else {
			floatDiv.style.left = getLeft(urlObj) - 100
					+ (screenW - urlObjW - floatDivW) + "px";
			if (!document.all) {
				floatDiv.childNodes[1].style.paddingLeft = urlObjW
						- (screenW - floatDivW) + 20 + "px";
			} else {
				floatDiv.firstChild.style.paddingLeft = urlObjW
						- (screenW - floatDivW) + 20 + "px";
			}
		}
	}

	var divObj = document.getElementsByTagName("div");
	for (var i = 0; i < divObj.length; i++) {
		if (divObj[i].getAttribute("ref") == "DetailInfo_Div") {
			divObj[i].style.display = "none";
		}
	}
	getEId(e).style.display = "block";
}
// 获取元素在窗口中的绝对位置
function getLeft(e) {
	var offset = e.offsetLeft;
	if (e.offsetParent != null)
		offset += getLeft(e.offsetParent);
	return offset - 5;
}

function getTop(e) {
	var offset = e.offsetTop;
	if (e.offsetParent != null)
		offset += getTop(e.offsetParent);
	return offset;
}

// 首页左边菜单 关闭层，根据传入的层名称
function closeDiv(e) {
	getEId(e).style.display = "none";
}

function setEdit3(contentDiv, btnDiv) {
	if (btnDiv == "btn1") {
		getEId(contentDiv).innerHTML = '<select style="width:155px;"><option>13795335133</option><option>13795335133</option><option>13795335133</option></select>';
		getEId(btnDiv).innerHTML = '<a href="javascript:;" class="greenU_link">保存/Save</a>';
	} else {
		getEId(contentDiv).innerHTML = '<select style="width:155px;"><option>13795335133</option><option>13795335133</option><option>13795335133</option></select>';
		getEId(btnDiv).innerHTML = '<a href="javascript:;" class="greenU_link">保存/Save</a>';
	}
}

function setEdit4(contentDiv, btnDiv) {
	if (btnDiv == "btn1") {
		getEId(contentDiv).innerHTML = '<select style="width:155px;"><option>13795335133</option><option>13795335133</option><option>13795335133</option></select>';
		getEId(btnDiv).innerHTML = '<a href="javascript:;" class="greenU_link">保存/Save</a>';
	} else {
		getEId(contentDiv).innerHTML = '<select style="width:155px;"><option>13795335133</option><option>13795335133</option><option>13795335133</option></select>';
		getEId(btnDiv).innerHTML = '<a href="javascript:;" class="greenU_link">保存/Save</a>';
	}
}

function show6() {
	var title = document.getElementById("title6");
	var content = document.getElementById("content6");
	if (content.style.display == "none") {
		content.style.display = "block"
		title.style.display = "none"
	} else {
		content.style.display == "none"
		title.style.display == "block"
	}
}

function show7() {
	var title = document.getElementById("title7");
	var content = document.getElementById("content7");
	if (content.style.display == "none") {
		content.style.display = "block"
		title.style.display = "none"
	} else {
		content.style.display == "none"
		title.style.display == "block"
	}
}

// 左侧菜单显示隐藏
function show_menu_obj(lang, obj, id) {
	if ($("#menu_" + id).css("display") == "block") {
		$("#menu_" + id).hide();
		$("#" + obj).attr("src", "/kfcios/images2/e2.gif");
	} else {
		if (lang == 'china') {
			$(".nav_left_ul_cn").hide();
			$(".img_obj_cn").attr("src", "/kfcios/images2/e2.gif");
		} else {
			$(".nav_left_ul_en").hide();
			$(".img_obj_en").attr("src", "/kfcios/images2/e2.gif");
		}
		$("#menu_" + id).show();
		$("#" + obj).attr("src", "/kfcios/images2/e1.gif");
	}

}

function order_info() {
	$("#order_info_obj").show();
}

function mc(divnum, obj, divname, linkName) {
	document.getElementById('div_ws').style.display = "none";
	document.getElementById('div_rm').style.display = "none";
	clearDetailInfo_Div();
	// 关于层切换的js
	// 定义显示层 数组，
	var target = new Array(divnum);
	var d = new Array(divnum);
	for (var i = 0; i < divnum; i++) {
		target[i] = divname + eval(i + 1);
	}
	// 循环操作div
	for (var i = 0; i < divnum; i++) {
		if (i == obj) {
			var v = document.getElementById(target[i]);
			var bg = document.getElementById(d[i]);
			v.style.display = "block";
			// window.location.hash="topMenu";
		} else {
			var bg = document.getElementById(d[i]);
			var v = document.getElementById(target[i]);
			v.style.display = "none";

		}
	}
}

function Enmc(divnum, obj, divname, linkName) {
	clearDetailInfo_Div();
	// 关于层切换的js
	// 定义显示层 数组，
	var target = new Array(divnum);
	var d = new Array(divnum);
	for (var i = 0; i < divnum; i++) {
		target[i] = divname + eval(i + 1);
	}
	// 循环操作div
	for (var i = 0; i < divnum; i++) {
		if (i == obj) {
			var v = document.getElementById(target[i]);
			var bg = document.getElementById(d[i]);
			v.style.display = "block";
			// window.location.hash="topMenu";
		} else {
			var bg = document.getElementById(d[i]);
			var v = document.getElementById(target[i]);
			v.style.display = "none";

		}
	}
}

function clearDetailInfo_Div() {
	var divObj = document.getElementsByTagName("div");
	for (var i = 0; i < divObj.length; i++) {
		if (divObj[i].getAttribute("ref") == "DetailInfo_Div") {
			divObj[i].style.display = "none";
		}
	}
}

function show_tip(str) {
	if (str == 'en') {
		$("#tip_c").hide();
		$("#tip_e").show();
	} else {
		$("#tip_c").show();
		$("#tip_e").hide();
	}
}
function show21() {
	var title = document.getElementById("title");
	var content = document.getElementById("content");
	if (title.style.display == "none") {
		title.style.display = "block"
		content.style.display = "none"
	} else {
		title.style.display == "none"
		content.style.display == "block"
	}
}
// ------------------------------*新增缺少的js----------------------begin
// xulei add 2010-10-14

// 判断浏览器类型
function showBrowser() {

	if (navigator.userAgent.indexOf("Opera") >= 0) {
		return "opera";
	} else {

		if (navigator.userAgent.indexOf("360SE") >= 0) {
			return "360";
		} else {

			if (navigator.userAgent.indexOf("SE") >= 0) {
				return "sougou";
			}

			else {

				if (navigator.userAgent.indexOf("Firefox") >= 0) {
					return "firefox";
				} else {

					if (navigator.userAgent.indexOf("Chrome") >= 0) {
						return "chrome";
					} else {
						if (maxthonVersion() != "false") {
							return "maxthon";
						}

						else {

							if (navigator.userAgent.indexOf("TencentTraveler") >= 0) {
								return "txtt";
							}

							else {

								if (navigator.userAgent.indexOf("Safari") >= 0) {
									return "safari";
								} else {

									if (navigator.userAgent.indexOf('Netscape') >= 0) {
										return "netscape";
									} else {

										return "ie";
									}
								}
							}
						}
					}
				}
			}

		}
	}
}

function isMaxthon() {
	try {
		window.external.max_invoke("GetHotKey");
		return true;
	} catch (ex) {
		return false;
	}
}

function maxthonVersion() {
	if (isMaxthon()) {
		if (window.external && window.external.max_version) {
			return window.external.max_version.substr(0, 1);
		}
		return "false";
	} else {
		return "false";
	}

}
// ------------------------------*新增缺少的js----------------------end
// 2011-03-21 zxh
/**
 * 参数说明 temp 调用方法判断 guestCount 订餐数量
 */
function pageInfo(temp,guestCount,netSale){
	var path="http://222.73.95.229/YumDataTracking/parse_data.action";
	if(temp=="topview"){
		goFirst();
	}else if(temp=="tcsuccess"){
		orderSuccess(guestCount,netSale);
	}else if(temp=="delivery"){
		browser=showBrowser();
		cookieId = "";
		code = "";
		var cookieLinkId="";
	
		cookieId = getCookie("cookieServerId");
		cookieLinkId = getCookie("cookieServerLinkId");
		temp = getCookie("keyStepName");
		code = getCookie("codeServer");
		
		if(code==null||code.length<10){
			code="utm_campaign=kfc&utm_source=4008823823&utm_medium=4008823823&utm_content=4008823823";
		}
		
		if(temp!=null&&temp!="null"){
			$.ajax({   
				type : "GET",   
				url : path+"?keyStepName="+temp+"&browser="+browser+"&"+code+"&cookieId="+cookieId+"&cookieLinkId="+cookieLinkId+"&callback=?",   
				dataType : "jsonp",   
				jsonp: 'callback',   
				success : function(json)   
				{   
				   
				}});
		}
		$.ajax({   
		type : "GET",   
		url : path+"?keyStepName=delivery&browser="+browser+"&"+code+"&cookieId="+cookieId+"&cookieLinkId="+cookieLinkId+"&callback=?",   
		dataType : "jsonp",   
		jsonp: 'callback',   
		success : function(json)   
		{   
		   
		}   
	});
	}
	else{
		
		browser=showBrowser();
		cookieId = "";
		code = "";
		var cookieLinkId="";
	
		cookieId = getCookie("cookieServerId");
		cookieLinkId = getCookie("cookieServerLinkId");
		temp = getCookie("keyStepName");
		code = getCookie("codeServer");
		
		if(code==null||code.length<10){
			code="utm_campaign=kfc&utm_source=4008823823&utm_medium=4008823823&utm_content=4008823823";
		}
		$.ajax({   
		type : "GET",   
		url : path+"?keyStepName="+temp+"&browser="+browser+"&"+code+"&cookieId="+cookieId+"&cookieLinkId="+cookieLinkId+"&callback=?",   
		dataType : "jsonp",   
		jsonp: 'callback',   
		success : function(json)   
		{   
		   
		}   
		}); 
	}
}

//进入首页传参
function goFirst(){
	var path="http://222.73.95.229/YumDataTracking/parse_data.action";
	var temp = "topview";
	var cookieLinkId=newGuid();
	var cookieId="";
	var browser = showBrowser();
	
	var code = getSpecify();
	
	actionCall("/kfcios/CookieAction/saveCookie.action",{'name':"codeServer",'value':code,'time':7200000},function(out){ });
	actionCall("/kfcios/CookieAction/saveCookie.action",{'name':"cookieServerLinkId",'value':cookieLinkId,'time':7200000},function(out){ });










	
		actionCall("/kfcios/CookieAction/getCookie.action",{'name':"cookieServerId"},function(out){


				cookieId = out.msg;
			if(cookieId!=null&&cookieId.length>30){

					$.ajax({   
						type : "get",   
						url : path+"?keyStepName="+temp+"&browser="+browser+"&"+code+"&cookieId="+cookieId+"&cookieLinkId="+cookieLinkId+"&callback=?",   




						dataType : "jsonp",   
						jsonp: 'callback',   
						success : function(json)   
						{   
						   
						}   
					});  

			}else{

					

				cookieId=newGuid();
		
				actionCall("/kfcios/CookieAction/saveCookie.action",{'name':"cookieServerId",'value':cookieId,'time':129600000},function(out2){
				




					$.ajax({   
						type : "GET",   
						url : path+"?keyStepName="+temp+"&browser="+browser+"&"+code+"&cookieId="+cookieId+"&cookieLinkId="+cookieLinkId+"&callback=?",   






						dataType : "jsonp",   
						jsonp: 'callback',   
						success : function(json)   
						{   
						   
						}   
					});   
				});
			}
	    });
		
	
}
//下单成功 参数 订餐人数  金额
function orderSuccess(guestCount, netSale) {
var path="http://222.73.95.229/YumDataTracking/parse_data.action";
	var code = "";
	var cookieId =""; 
	var cookieLinkeId = "";
	var temp = "tcsuccess";
	var browser = showBrowser();
	
	actionCall("/kfcios/CookieAction/getCookie.action",{'name':"cookieServerId"},function(out){


			cookieId = out.msg;
			if(cookieId==null||cookieId.length<30){
				return;
			}else{
			actionCall("/kfcios/CookieAction/getCookie.action",{'name':"codeServer"},function(out){


				code = out.msg;
				actionCall("/kfcios/CookieAction/getCookie.action",{'name':"cookieServerLinkId"},function(out){




					cookieLinkId = out.msg;
					$.ajax({   
						type : "GET",   
						url : path+"?keyStepName="+temp+"&browser="+browser+"&"+code+"&cookieId="+cookieId+"&cookieLinkId="+cookieLinkId+"&guestCount="+guestCount+"&netsale="+netSale+"&callback=?",   















						dataType : "jsonp",   
						jsonp: 'callback',   
						success : function(json)   
						{   
						   
						}   
					});
				});
			});
			}
	});
	
}

// 获取指定值
function getSpecify() {
	var uri = location.search;
	if (uri == null || "" == uri || uri.length < 20) {
		return "utm_campaign=kfc&utm_source=4008823823&utm_medium=4008823823&utm_content=4008823823";
	} else {

		if (uri.indexOf("utm_campaign") != -1) {
			uri = decodeURIComponent(uri.substring(1).replace(/\+/g, " "));
			var url = encodeURI(uri);

			return url;
		} else {

			return "utm_campaign=kfc&utm_source=4008823823&utm_medium=4008823823&utm_content=4008823823";
		}
	}
}

// 生成GUID
function newGuid() {
	var guid = "";
	for (var i = 1; i <= 32; i++) {
		var n = Math.floor(Math.random() * 16.0).toString(16);
		guid += n;
		if ((i == 8) || (i == 12) || (i == 16) || (i == 20))
			guid += "-";
	}
	return guid;
}
// 添加cookie
function setCookie(name, value, expire) {
	var exp = new Date();
	exp.setTime(exp.getTime() + expire * 3600 * 1000);
	document.cookie = name + "=" + escape(value) + ";expires="
			+ exp.toGMTString();
}

// 添加单次cookie
function addCookie(c_name, c_value, c_day) {

	var str = c_name + "=" + escape(c_value);
	if (c_day == 0) {// 为0时不设定过期时间
		var date = new Date();
		var ms = 1;
		date.setTime(date.getTime() + ms);
		str += "; expires=" + date.toGMTString();
	}
	document.cookie = str;
}

function getCookie(name) {
	var arr = document.cookie
			.match(new RegExp("(^| )" + name + "=([^;]*)(;|$)"));
	if (arr != null) {
		return unescape(arr[2]);
	} else {
		return null;
	}
}
// -----------------------------
// 2011-03-21 zxh
function err(divid) {
	if (divid == 'en1') {
		$("#en1").show();
		$("#ch1").hide();
	}
	if (divid == 'ch1') {
		$("#en1").hide();
		$("#ch1").show();
	}

}
function showDivBY(btnId, DivId, num) {
	clearDetailInfo_Div();
	var floatDiv = getEId(DivId);// 当前的弹出层
	if (num % 2 == 0) {
		floatDiv.style.top = "-100px";
	} else {
		floatDiv.style.top = "-5px";
	}
	getEId(DivId).style.display = "block";
	document.getElementById('select').style.display = "none";
}

function closeDiv2(DivId) {
	getEId(DivId).style.display = "none";
	document.getElementById('select').style.display = "block";
}
// 2011-11-29 add
function showDiv2(btnId, DivId, num) {
	clearDetailInfo_Div();
	var floatDiv = getEId(DivId);// 当前的弹出层
	if (num % 2 == 1) {
		floatDiv.style.top = "-100px";
	} else {
		floatDiv.style.top = "-35px";
	}
	getEId(DivId).style.display = "block";
	document.getElementById('select').style.display = "none";
}
