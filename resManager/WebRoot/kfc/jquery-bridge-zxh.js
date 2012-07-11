function checkedAll() {
	if (document.getElementById('all').checked == true) {
		$('input[name="articleid"]').attr("checked", true);
	} else {
		$('input[name="articleid"]').attr("checked", false);
	}
}

jQuery.fn.selectCity = function(targetId) {
	var _seft = this;
	var targetId = $(targetId);

	this.change(function() {
		var A_top = $(this).offset().top + $(this).outerHeight(true); // 1
			var A_left = $(this).offset().left;
			targetId.bgiframe();
			targetId.show().css( {
				"position" : "absolute",
				"top" : A_top + "px",
				"left" : A_left + "px"
			});
		});

	targetId.find("#selectItemClose").click(function() {
		targetId.hide();
	});

	targetId.find("#selectSub :checkbox").click(function() {
		targetId.find(":checkbox").attr("checked", false);
		$(this).attr("checked", true);
		_seft.val($(this).val());
		$("#CityName_zh").html($(this).val());
		targetId.hide();
	});

	$(document).click(function(event) {
		if (event.target.id != _seft.selector.substring(1)) {
			targetId.hide();
		}
	});

	targetId.click(function(e) {
		e.stopPropagation(); // 2
		});

	return this;
}

function showProducts(url) {
	$("#showProducts").show("slow").html(
			"<img src='/kfcios/images/loading.gif'/> ...").load(
			url + '&s=' + getS());
}

function showOrder1(url) {
	$("#order1").show("slow").html(
			"<img src='/kfcios/images/loading.gif'/> ...").load(
			url + '&s=' + getS());
}

function showMyOrder(url) {
	$("#navtie").show("slow").html("订单记录");
	$("#orderArea").show("slow").html(
			"<img src='/kfcios/images/loading.gif'/> ...").load(
			url + "&s=" + getS());
	$("#a1").attr('class', 'nav_center_y');
	$("#a2").attr('class', 'nav_center_n');
	$("#a3").attr('class', 'nav_center_n');
	$("#a4").attr('class', 'nav_center_n');
}

function showIcusomter(url) {
	$("#navtie").show("slow").html("个人信息");
	$("#orderArea").show("slow").html(
			"<img src='/kfcios/images/loading.gif'/> ...").load(
			url + "?s=" + getS());
	$("#a2").attr('class', 'nav_center_y');
	$("#a1").attr('class', 'nav_center_n');
	$("#a3").attr('class', 'nav_center_n');
	$("#a4").attr('class', 'nav_center_n');
}

function showOrderInfo(orderid) {// dingdan xiangqing

	actionCall(
			"/kfcios/OrderAction/getOrderStatus.action",
			{
				orderid : orderid
			},
			function(out) {
				var sta = out.sta;
				if (sta == 9) {
					$("#orderstatus" + orderid).find(":a").remove();
					$("#orderstatus" + orderid)
							.append(
									'<a href="javascript:;" onclick="showOrderInfo(' + orderid + ');" class="greenU_link">已接受</a>');
					$("#orderqwe" + orderid).hide();
				}
				if (sta == 8) {
					$("#orderstatus" + orderid).find(":a").remove();
					$("#orderstatus" + orderid)
							.append(
									'<a href="javascript:;" onclick="showOrderInfo(' + orderid + ');" class="greenU_link">已完成</a>');
					$("#orderqwe" + orderid).hide();
				}
				if (sta == 7) {
					$("#orderstatus" + orderid).find(":a").remove();
					$("#orderstatus" + orderid)
							.append(
									'<a href="javascript:;" onclick="showOrderInfo(' + orderid + ');" class="greenU_link">已派单</a>');
					$("#orderqwe" + orderid).hide();
				}
				if (sta == 0) {
					$("#orderstatus" + orderid).find(":a").remove();
					$("#orderstatus" + orderid)
							.append(
									'<a href="javascript:;"  class="greenU_link">已取消</a>');
					$("#orderqwe" + orderid).hide();
				}
				if (sta == 1 || sta == 12) {
					$("#orderstatus" + orderid).find(":a").remove();
					$("#orderstatus" + orderid)
							.append(
									'<a href="javascript:;"  class="greenU_link">已改单</a>');
					$("#orderqwe" + orderid).hide();
				}
				if (sta == 2) {
					$("#orderstatus" + orderid).find(":a").remove();
					$("#orderstatus" + orderid)
							.append(
									'<a href="javascript:;" onclick="showOrderInfo(' + orderid + ');" class="greenU_link">进单失败</a>');
					$("#orderqwe" + orderid).hide();
				}

			});

	$("#orderItem").show("slow").html("").load(
			'/kfcios/OrderAction/orderInfo.action?orderid=' + orderid + '&s='
					+ getS());
	$("#orderItem").show();
	$("#orderinfo1").show("slow").html("").load(
			'/kfcios/OrderAction/orderInfo1.action?orderid=' + orderid + '&s='
					+ getS());

}
function showOrderInfoB(orderid) {// dingdan xiangqing

	actionCall(
			"/kfcios/OrderAction/getOrderStatus.action",
			{
				orderid : orderid
			},
			function(out) {
				var sta = out.sta;
				if (sta == 9) {
					$("#orderstatus" + orderid).find(":a").remove();
					$("#orderstatus" + orderid)
							.append(
									'<a href="javascript:;" onclick="showOrderInfoB(' + orderid + ');" class="greenU_link">已接受</a>');
					$("#orderqwe" + orderid).hide();
				}
				if (sta == 8) {
					$("#orderstatus" + orderid).find(":a").remove();
					$("#orderstatus" + orderid)
							.append(
									'<a href="javascript:;" onclick="showOrderInfoB(' + orderid + ');" class="greenU_link">已完成</a>');
					$("#orderqwe" + orderid).hide();
				}
				if (sta == 7) {
					$("#orderstatus" + orderid).find(":a").remove();
					$("#orderstatus" + orderid)
							.append(
									'<a href="javascript:;" onclick="showOrderInfoB(' + orderid + ');" class="greenU_link">已派单</a>');
					$("#orderqwe" + orderid).hide();
				}
				if (sta == 0) {
					$("#orderstatus" + orderid).find(":a").remove();
					$("#orderstatus" + orderid)
							.append(
									'<a href="javascript:;"  class="greenU_link">已取消</a>');
					$("#orderqwe" + orderid).hide();
				}
				if (sta == 1 || sta == 12) {
					$("#orderstatus" + orderid).find(":a").remove();
					$("#orderstatus" + orderid)
							.append(
									'<a href="javascript:;"  class="greenU_link">已改单</a>');
					$("#orderqwe" + orderid).hide();
				}
				if (sta == 2) {
					$("#orderstatus" + orderid).find(":a").remove();
					$("#orderstatus" + orderid)
							.append(
									'<a href="javascript:;" onclick="showOrderInfoB(' + orderid + ');" class="greenU_link">进单失败</a>');
					$("#orderqwe" + orderid).hide();
				}

			});

	$("#orderItem").show("slow").html("").load(
			'/kfcios/OrderAction/orderInfo.action?orderid=' + orderid
					+ '&boo=true&s=' + getS());
	$("#orderItem").show();
	$("#orderinfo1").show("slow").html("").load(
			'/kfcios/OrderAction/orderInfo1.action?orderid=' + orderid
					+ '&boo=true&s=' + getS());

}
function closeOrderInfo(orderid, boo) {
	if (boo) {
		$("#orderstatus" + orderid).find(":a").remove();
		$("#orderstatus" + orderid)
				.append(
						'<a href="javascript:;" onclick="showOrderInfoB(' + orderid + ');" class="greenU_link left_10">详情/Detail</a>');
		$("#orderqwe" + orderid).show();

	} else {
		$("#orderstatus" + orderid).find(":a").remove();
		$("#orderstatus" + orderid)
				.append(
						'<a href="javascript:;" onclick="showOrderInfo(' + orderid + ');" class="greenU_link left_10">详情/Detail</a>');
		$("#orderqwe" + orderid).show();

	}

	$("#orderItem").hide();
}

function showIcoupon(url) {
	$("#navtie").show("slow").html("我的优惠");
	$("#orderArea").show("slow").html(
			"<img src='/kfcios/images/loading.gif'/> ...").load(
			url + "?s=" + getS());
	$("#a3").attr('class', 'nav_center_y');
	$("#a2").attr('class', 'nav_center_n');
	$("#a1").attr('class', 'nav_center_n');
	$("#a4").attr('class', 'nav_center_n');
}

function showPayment(url) {

	$("#navtie").show("slow").html("网上支付记录");
	$("#orderArea").show("slow").html(
			"<img src='/kfcios/images/loading.gif'/> ...").load(
			url + "?s=" + getS());
	$("#a4").attr('class', 'nav_center_y');
	$("#a2").attr('class', 'nav_center_n');
	$("#a3").attr('class', 'nav_center_n');
	$("#a1").attr('class', 'nav_center_n');

}

function showOrder2(url) {
	$("#content").show("slow").html(
			"<img src='/kfcios/images/loading.gif'/> ...").load(
			url + '&s=' + getS());
}

function getS() {
	var d, s = "";
	d = new Date();
	s += d.getYear() + "-";
	s += (d.getMonth() + 1) + "-";
	s += d.getDate() + "-";
	s += d.getHours() + "-";
	s += d.getMinutes() + "-";
	s += d.getSeconds() + "-";
	s += d.getMilliseconds();
	return s;
}

function showOrdering(orderId) {
	location.href = '/kfcios/OrderingAction/ordering.action?orderId=' + orderId
			+ '&s=' + getS();
}
function getObject(objectId) {
	if (document.getElementById && document.getElementById(objectId)) {
		// W3C DOM
		return document.getElementById(objectId);
	} else if (document.all && document.all(objectId)) {
		// MSIE 4 DOM
		return document.all(objectId);
	} else if (document.layers && document.layers[objectId]) {
		// NN 4 DOM.. note: this won't find nested layers
		return document.layers[objectId];
	} else {
		return false;
	}
}

// CityString1重写 xpc 2012年1月4日
function queryPhoneZone() {
	var cityName =$("#cityNoName").val();
	var na= /^[0-9]*$/;
	if(na.test(cityName)){
		$("#selectItem").hide();
		$("#zoneNo").val(cityName);
		return;
	}
	var s = getS();
	var targetId = $("#selectItem3");
	// var cityName=document.forms[0].cityName.value;
	
	if (cityName != '') {
		//var A_top = $("#cityNoName").offset().top + $("#cityNoName").outerHeight(true); 
		//var A_left = $("#cityNoName").offset().left - 130;
		targetId.bgiframe();
		targetId.show().css( {
			"position" : "absolute",
			//"top" : A_top + "px",
			//"left" : A_left + "px"
			"top" : 22,
			"left" : 0
		});
	} else {
		targetId.hide();
		$("#zoneNo").html("");
	}
	if (cityName != '') {
		cityName = encodeURI(encodeURI(cityName));
		$("#queryCity").html("<img src='/kfcios/images/loading.gif'/> ...")
				.load("/kfcios/SubscrMealNewAction/queryPhoneZone.action?cityName1="+ cityName + "&boo=true&s=" + s);

		targetId.find("#selectItemClose").click(function() {
			targetId.hide();
		});

		targetId.click(function(e) {
			e.stopPropagation(); // 2
		});

		return this;
	}
}

function CityString1() {
	var s = getS();
	var targetId = $("#selectItem");
	// var cityName=document.forms[0].cityName.value;
	var cityName = getObject("cityName").value;
		//zhangj_login
	if (cityName != '') {
		//var A_top = $("#cityName").offset().top+ $("#cityName").outerHeight(true); // 1
		//var A_left = $("#cityName").offset().left - 130;
		targetId.bgiframe();
		targetId.show().css( {
			"position" : "absolute",
			//"top" : A_top + "px",
			//"left" : A_left + "px"
			"top" : "22px",
			"left" : 0
		});
	} else {
		targetId.hide();
		$("#cityid").val("");
		$("#CityName_zh").html("");
		$("#zoneNo").html("");
	}
	if (cityName != '') {
		cityName = encodeURI(encodeURI(cityName));
		$("#queryCity1").html("<img src='/kfcios/images/loading.gif'/> ...")
				.load(
						"/kfcios/SubscrMealNewAction/queryCity.action?cityName1="
								+ cityName + "&boo=true&s=" + s);

		targetId.find("#selectItemClose").click(function() {
			targetId.hide();
		});

		targetId.click(function(e) {
			e.stopPropagation(); // 2
		});

		return this;
	}
}

function CityString4() {
	var s = getS();
	var targetId = $("#selectItem");
	// var cityName=document.forms[0].cityName.value;
	var cityName = getObject("cityName").value;
	if (cityName != '') {
		var A_top = $("#cityName").offset().top
				+ $("#cityName").outerHeight(true); // 1
		var A_left = $("#cityName").offset().left;
		targetId.bgiframe();
		targetId.show().css( {
			"position" : "absolute",
			"top" : A_top + "px",
			"left" : A_left + "px"
		});
	} else {
		targetId.hide();
		$("#cityid").val("");
		$("#CityName_zh").html("");
		$("#zoneNo").html("");
	}
	if (cityName != '') {
		cityName = encodeURI(encodeURI(cityName));
		$("#queryCity1").html("<img src='/kfcios/images/loading.gif'/> ...")
				.load(
						"/kfcios/SubscrMealNewAction/queryCity.action?cityName1="
								+ cityName + "&boo=true&s=" + s);

		targetId.find("#selectItemClose").click(function() {
			targetId.hide();
		});

		targetId.click(function(e) {
			e.stopPropagation(); // 2
			});

		return this;
	}
}

function CityString0() {
	var s = getS();
	var targetId = $("#selectItem5");
	// var cityName=document.forms[0].cityName.value;
	var cityName = getObject("cityName").value;
	if (cityName != '') {
		var A_top = $("#cityName").offset().top
				+ $("#cityName").outerHeight(true); // 1
		var A_left = $("#cityName").offset().left - 130;
		targetId.bgiframe();
		targetId.show().css( {
			"position" : "absolute",
			"top" : A_top + "px",
			"left" : A_left + "px"
		});
	} else {
		targetId.hide();
		$("#cityid").val("");
		$("#CityName_zh").html("");
		$("#zoneNo").html("");
	}
	if (cityName != '') {
		cityName = encodeURI(encodeURI(cityName));
		$("#queryCity1").html("<img src='/kfcios/images/loading.gif'/> ...")
				.load(
						"/kfcios/SubscrMealNewAction/queryCity.action?cityName1="
								+ cityName + "&boo=true&s=" + s);

		targetId.find("#selectItemClose").click(function() {
			targetId.hide();
		});

		targetId.click(function(e) {
			e.stopPropagation(); // 2
			});

		return this;
	}
}

function CityString2(name) {
	$("#selectItem").hide();
	$("#queryCity").html("<img src='/kfcios/images/loading.gif'/> ...").load(
			"/kfcios/SubscrMealNewAction/queryCity.action?cityName1=" + name);

	var targetId = $("#selectItem3");
	var A_top = $("#cityName").offset().top + $("#cityName").outerHeight(true)
			+ 25; // 1
	var A_left = $("#cityName").offset().left;
	targetId.bgiframe();
	targetId.show().css( {
		"position" : "absolute",
		"top" : A_top + "px",
		"left" : A_left + "px"
	});
	targetId.find("#selectItemClose").click(function() {
		targetId.hide();
	});

	$(document).mouseup(function(event) {
		if (event.target.id != $("#cityName").selector.substring(2)) {
			targetId.hide();
		}
	});

	targetId.click(function(e) {
		e.stopPropagation(); // 2
		});

	return this;

}
function clearselectItem3() {
	$("#selectItem3").hover(function() {
		$("#selectItem3").show();
	}, function() {
		$("#selectItem3").hide();
	});

}

function setCity(a, id) {
	a.className = "c";
	$("#" + id).html("<img src='/kfcios/images/loading.gif'/> ...").load(
			"/kfcios/SubscrMealNewAction/queryCity.action?cityName1=" + id);
}
function clearCity(a) {
	a.className = "";
}

// -------------------------------------------------------------------------------
function favoritesClose() {
	$("#tools-dropdown").remove();
}

function popEditPage(url) {
	var bH = $("body").height();
	var bW = $("body").width();
	$("body")
			.html(
					'<div id="maskDiv1" style="display:none;"></div>' + '<div id="dialogLayer" style="display:none;"></div>' + $(
							"body").html());
	$("#maskDiv1").css( {
		width : bW,
		height : bH,
		position : "absolute",
		top : "0px",
		left : "0px",
		"background-color" : "#000",
		"z-index" : "30",
		filter : "alpha(opacity=75)",
		opacity : "0.75"
	});
	$("#maskDiv1").show("slow");
	$("#dialogLayer").css( {
		position : "absolute",
		"z-index" : 40
	}).show("slow").html("<img src='/kfcios/images/loading.gif'/> ...").load(
			url);

}

function editPageClose() {
	$("#maskDiv1").remove();
	$("#dialogLayer").remove();
}

function showNewsList(url) {
	$("#newsList").show("slow").html(
			"<img src='/kfcios/images/loading.gif'/> ...").load(url);
}

function show() {
	$("#display").hide();
	$("#sorting").hide();
	$("#addFavorites").show("slow");
}
function hiden() {
	$("#addFavorites").hide();
	$("#display").hide();
	$("#sorting").hide();
}
function showDisplay() {
	$("#addFavorites").hide();
	$("#sorting").hide();
	$("#display").show("slow");
}
function hidenDisplay() {
	$("#display").hide();
}
function showSorting() {
	$("#addFavorites").hide();
	$("#display").hide();
	$("#sorting").show("slow");
}
function hidenSorting() {
	$("#sorting").hide();
}

function showCategoryFilter() {
	if (!$("#categoryFilter").is(":hidden")) {
		$("#categoryFilter").hide("slow");
	} else {
		$("#categoryFilter").show("slow");
		$("#locationFilter").hide("slow");
		$("#keywordsFilter").hide("slow");
	}
}

function showLocationFilter() {
	if (!$("#locationFilter").is(":hidden")) {
		$("#locationFilter").hide("slow");
	} else {
		$("#categoryFilter").hide("slow");
		$("#locationFilter").show("slow");
		$("#keywordsFilter").hide("slow");
	}
}

function showKeywordsFilter() {
	if (!$("#keywordsFilter").is(":hidden")) {
		$("#keywordsFilter").hide("slow");
	} else {
		$("#categoryFilter").hide("slow");
		$("#locationFilter").hide("slow");
		$("#keywordsFilter").show("slow");
	}
}

function changeSelectDay() {
	$("#mostR").show("slow")
			.html("<img src='/kfcios/images/loading.gif'/> ...").load(
					"/h_result/daily/articleTop10-date.html");
	// $("#todayRead").hide("slow");
}
function changeSelectWeek() {
	$("#mostR").show("slow")
			.html("<img src='/kfcios/images/loading.gif'/> ...").load(
					"/h_result/daily/articleTop10-week.html");
	// $("#todayRead").hide("slow");
}
function changeSelectMonth() {
	$("#mostR").show("slow")
			.html("<img src='/kfcios/images/loading.gif'/> ...").load(
					"/h_result/daily/articleTop10-month.html");
	// $("#todayRead").hide("slow");
}

function showOption(id, list) {
	var len = list.length;
	ld_clearList(document.getElementById(id));
	// ld_addOption(document.getElementById(id),null,"Please select")
	for ( var i = 0; i < len; i++) {
		ld_addOption(document.getElementById(id), list[i].CoolingID,
				list[i].StreetName)
	}
}

function showOption1(id, list) {
	var len = list.length;
	ld_clearList(document.getElementById(id));
	ld_addOption(document.getElementById(id), null, "Please select")
	for ( var i = 0; i < len; i++) {
		ld_addOption(document.getElementById(id), list[i].id, list[i].name)
	}
}
function queryaddres() {
	var address2 = getObject("address2").value;
	if (address2 == null ||address2.length == 0 || address2 == ''|| address2=='请输入地址关键字，按“查询”并从列表中点选') {
		jAlert("请填写送餐地址中的路名/小区名。");
		return ;
	}
	if (address2 == '请简体中文输入地址关键字') {
		jAlert("请填写送餐地址中的路名/小区名。");
		return ;
	}
	var cityID = getObject("cityid").value;
	var cityName = getObject("cityName").value;
	if (cityID == '') {
		jAlert("请先选择送餐城市。");
		return false;
	}
	// http://218.78.214.170/cgi-bin/webapi?index=KFC&cityID=10&jsoncallback=test&Keywords=zhongshan&pattern=1&page_size=100
	// $.getJSON("http://218.78.214.170/cgi-bin/webapi?index=KFC&cityID="+cityID+"&Keywords="+encodeURI(address2)+"&pattern=1&page_size=100&jsoncallback=?",
	$
			.getJSON("/kfcios/SearchAction/search.action?cityId=" + cityID
					+ "&streetName=" + encodeURI(encodeURI(address2)) + "&s="
					+ getS(), function(backdata) {

				// showOption('select2',backdata.Doc);
					showDiv(backdata.doc)
					var len = backdata.doc.length;

					if (len == 0) {
						// 跳到错误页面
					maskDialog("/kfcios/SubscrMealNewAction/errorinputTime1.action?address2="
							+ encodeURI(encodeURI(cityName))
							+ encodeURI(encodeURI(address2)));
					// location.href="/kfcios/SubscrMealNewAction/errorinputTime.action?address2="+encodeURI(encodeURI(address2))+"&
					// cityName="+encodeURI(encodeURI(cityName))+"&
					// cityid="+cityID;
					// actionCall("/kfcios/SubscrMealNewAction/errorinputTime.action",{address2:address2,cityName:cityName,cityid:cityID},function(out){

					// });

				} else {
					var targetId = $("#selectItem2");
					//var A_top = $("#address2").offset().top
					//		+ $("#address2").outerHeight(true); // 1
					//var A_left = $("#address2").offset().left - 130;
					targetId.bgiframe();
					targetId.show().css( {
					//	"position" : "relative"
						//"top" : A_top + "px",
						//"left" : A_left + "px"
						//"top" : "60px",
						//"left" :"183px"
					});

					targetId.find("#selectItemClose").click(function() {
						targetId.hide();
					});

					/*
					 * $(document).click(function(event){
					 * if(event.target.id!=$("#address2").selector.substring(1)){
					 * targetId.hide(); } });
					 */

					targetId.click(function(e) {
						e.stopPropagation(); // 2
					});

				}
				return this;

			});
}

function queryaddres0() {
	var address2 = getObject("address2").value;
	if (address2.length == 0 && adress2 == '') {
		return false;
	}
	if (address2 == '请简体中文输入地址关键字') {
		jAlert("请填写送餐地址中的路名/小区名。");
		return false;
	}
	var cityID = getObject("cityid").value;
	var cityName = getObject("cityName").value;
	if (cityID == '') {
		jAlert("请先选择送餐城市。");
		return false;
	}
	// http://218.78.214.170/cgi-bin/webapi?index=KFC&cityID=10&jsoncallback=test&Keywords=zhongshan&pattern=1&page_size=100
	// $.getJSON("http://218.78.214.170/cgi-bin/webapi?index=KFC&cityID="+cityID+"&Keywords="+encodeURI(address2)+"&pattern=1&page_size=100&jsoncallback=?",
	$
			.getJSON("/kfcios/SearchAction/search.action?cityId=" + cityID
					+ "&streetName=" + encodeURI(encodeURI(address2)) + "&s="
					+ getS(), function(backdata) {

				// showOption('select2',backdata.Doc);
					showDiv(backdata.doc)
					var len = backdata.doc.length;

					if (len == 0) {
						// 跳到错误页面
					maskDialog("/kfcios/SubscrMealNewAction/errorinputTime1.action?address2="
							+ encodeURI(encodeURI(cityName))
							+ encodeURI(encodeURI(address2)));
					// location.href="/kfcios/SubscrMealNewAction/errorinputTime.action?address2="+encodeURI(encodeURI(address2))+"&
					// cityName="+encodeURI(encodeURI(cityName))+"&
					// cityid="+cityID;
					// actionCall("/kfcios/SubscrMealNewAction/errorinputTime.action",{address2:address2,cityName:cityName,cityid:cityID},function(out){

					// });

				} else {
					var targetId = $("#selectItem2");
					var A_top = $("#address2").offset().top
							+ $("#address2").outerHeight(true); // 1
					var A_left = $("#address2").offset().left - 130;
					targetId.bgiframe();
					targetId.show().css( {
						"position" : "absolute",
						"top" : A_top + "px",
						"left" : A_left + "px"
					});

					targetId.find("#selectItemClose").click(function() {
						targetId.hide();
					});

					/*
					 * $(document).click(function(event){
					 * if(event.target.id!=$("#address2").selector.substring(1)){
					 * targetId.hide(); } });
					 */

					targetId.click(function(e) {
						e.stopPropagation(); // 2
						});

				}
				return this;

			});
}
function showDiv(list) {
	$("#selectSub2").find(":checkbox").remove();
	$("#selectSub2").find(":label").remove();
	var len = list.length;
	if (len == 0) {
		// $("#selectSub2").append("<label id='cr0m' for='cr07'
		// >查无结果</label><br>");
		// $("#inputAddress1").submit();
	} else {

		for ( var i = 0; i < len; i++) {

			var onmo1 = "onmouseover=chancolor('cr0m"
					+ encodeURI(list[i].coolingID) + "'); ";
			var onmo2 = "onmouseout=chancolor2('cr0m"
					+ encodeURI(list[i].coolingID) + "'); ";
			var o = list[i].endRange;
			if (o == 9999) {
				var va = list[i].streetName;
				var oncl = "onclick=showaddress2('" + encodeURI(va) + "','"
						+ list[i].id + "'); ";
				$("#selectSub2").append(
						"<label id='cr0m" + encodeURI(list[i].coolingID)
								+ "' for='cr07' " + oncl + onmo1 + onmo2 + ">"
								+ va.replace(/(^\s*)|(\s*$)/g, "")
								+ "</label><br>");
			} else {
				var va = list[i].streetName;
				var oncl = "onclick=showaddress2('" + encodeURI(va) + "','"
						+ list[i].id + "'); ";
				$("#selectSub2").append(
						"<label id='cr0m" + encodeURI(list[i].coolingID)
								+ "' for='cr07' " + oncl + onmo1 + onmo2 + ">"
								+ va.replace(/(^\s*)|(\s*$)/g, "")
								+ "</label><br>");
			}
		}

	}

}
function chancolor(id) {
	$("#" + id).attr("style", "color:#db030e;");
}
function chancolor2(id) {
	$("#" + id).attr("style", "color:#5d8602;");
}
function showaddress2(address2, id) {
	$("#address2").val(decodeURI(address2));
	$("#selectItem2").hide();
	$("#selectItem6").hide();
	$("#strRid").val(id);
}
function dianji(id, nameZh, zoneNo) {
	$("#cityid").val(id);
	$("#CityName_zh").html(nameZh);
	$('#CityName_zh').css('padding-right','5px');
	$("#cityName").val(nameZh);
	$("#selectItem").hide();
	$("#selectItem2").hide();
	$("#selectItem3").hide();
	if ($("#zoneNo1").length > 0) {
		$("#zoneNo1").val(zoneNo);
	}
}
function showAddrs(id, nameZh, zoneNo){
	$("#cityid").val(id);
	$("#CityName_zh").html(nameZh);
	$('#CityName_zh').css('padding-right','5px');
	$("#cityName").val(nameZh);
	$("#selectItem").hide();
	$("#selectItem2").hide();
	$("#selectItem3").hide();
	if ($("#zoneNo1").length > 0) {
		$("#zoneNo1").val(zoneNo);
	}
}
// dianji简化版本 xpc 2012年1月4日 适用于queryCity.jsp
function showZone(id, name_zh, zoneNo) {
	$("#cityNoName").val(zoneNo);

	$("#zoneNo").val(zoneNo);
	$("#selectItem").hide();
	$("#selectItem2").hide();
	$("#selectItem3").hide();
	if ($("#zoneNo").length > 0) {
		$("#zoneNo").val(zoneNo);
	}

}
function showOption3(id, list) {
	var len = list.length;
	ld_clearList(document.getElementById(id));
	for ( var i = 0; i < len; i++) {
		ld_addOption(document.getElementById(id), list[i].id, list[i].name)
	}
}
function showOption4(id, list) {
	var len = list.length;
	ld_clearList(document.getElementById(id));
	for ( var i = 0; i < len; i++) {
		ld_addOption(document.getElementById(id), list[i].name, list[i].name)
	}
}
function chanhour(val) {
	actionCall('/kfcios/SubscrMealNewAction/cascadeMinutes.action', {
		hour : val
	}, function(json) {
		showOption4('minute', json.minutes);
	});
}
function moveSelect2() {
	var strr = $("#select21 option:selected").val();
	if (strr == "null") {
		jAlert("Select a category!")
		return;
	}
	var str1;
	var str2;
	var str;
	str2 = $("#select2 option:selected").val();
	str1 = $("#select21 option:selected").text();
	if (str2 == "null" || str2 == "undefined" || str2 == null || str2 == " ") {
		str = str1;
	} else {
		str = str2;
	}
	var d, s = "";
	d = new Date();
	s += d.getYear() + "-";
	s += (d.getMonth() + 1) + "-";
	s += d.getDate() + "-";
	s += d.getHours() + "-";
	s += d.getMinutes() + "-";
	s += d.getSeconds() + "-";
	s += d.getMilliseconds();
	var ids = "'" + s + "'"

	$("#filtersList")
			.append(
					'<div id="'
							+ s
							+ '" style="margin-bottom:10px;"><a href="#" onclick="clearDiv('
							+ ids
							+ ');"><img src="/daily/images/button_filter_delete.gif" border="0" align="absmiddle" class="floatRight" /></a><img src="/daily/images/icon_category.gif" align="absmiddle" /> <input type="hidden" name="category" id="category" value="'
							+ str + '">' + str
							+ ' <div class="clear"></div></div>');
}
function moveSelect3() {
	var strr = $("#select31 option:selected").val();
	if (strr == "null") {
		jAlert("Select a country")
		return;
	}
	var str1;
	var str2;
	var str;
	var strr;
	str2 = $("#select3 option:selected").val();
	str1 = $("#select31 option:selected").text();
	if (str2 == "null") {
		str = "country" + "-" + str1;
		strr = str1;
	} else {
		str = "city" + "-" + str2;
		strr = str2;
	}
	var d, s = "";
	d = new Date();
	s += d.getYear() + "-";
	s += (d.getMonth() + 1) + "-";
	s += d.getDate() + "-";
	s += d.getHours() + "-";
	s += d.getMinutes() + "-";
	s += d.getSeconds() + "-";
	s += d.getMilliseconds();
	var ids = "'" + s + "'"
	$("#filtersList")
			.append(
					'<div id="'
							+ s
							+ '" style="margin-bottom:10px;"><a href="#" onclick="clearDiv('
							+ ids
							+ ');"><img src="/daily/images/button_filter_delete.gif" border="0" align="absmiddle" class="floatRight" /></a><img src="/daily/images/icon_location.gif" align="absmiddle" /> <input type="hidden" name="location" id="location" value="'
							+ str + '">' + strr
							+ ' <div class="clear"></div></div>');
}

function moveSelect4() {
	var strr = $("#textfield3").val();
	if (strr == "") {
		jAlert("Please enter a keyword!")
		return;
	}
	var str = $("#textfield3").val();
	var d, s = "";
	d = new Date();
	s += d.getYear() + "-";
	s += (d.getMonth() + 1) + "-";
	s += d.getDate() + "-";
	s += d.getHours() + "-";
	s += d.getMinutes() + "-";
	s += d.getSeconds() + "-";
	s += d.getMilliseconds();
	var ids = "'" + s + "'"
	$("#filtersList")
			.append(
					'<div id="'
							+ s
							+ '" style="margin-bottom:10px;"><a href="#" onclick="clearDiv('
							+ ids
							+ ');"><img src="/daily/images/button_filter_delete.gif" border="0" align="absmiddle" class="floatRight" /></a><img src="/daily/images/icon_keywords.gif" align="absmiddle" /> <input type="hidden" name="keywords" id="keywords" value="'
							+ str + '">' + str
							+ '<div class="clear"></div></div>');
}

function clearDiv(id) {
	$("#" + id).remove();
}

function mysubmit(form, lebelid, url) {
	$("#tempLabelid").attr('value', lebelid);
	form.action = url;
	form.submit();
}

function showAllNews() {
	if (!$("#allNews").is(":hidden")) {
		$("#allNews").hide("slow");
		$("#allNTu").attr('src',
				'/daily/images/arrow_white_transparency_down.gif');
	} else {
		$("#allNews").show("slow");
		$("#allNTu").attr('src',
				'/daily/images/arrow_white_transparency_up.gif');
		$("#myFavorites").hide("slow");
		$("#myFTu").attr('src',
				'/daily/images/arrow_white_transparency_down.gif');
	}
}

function showMyFavorites() {
	if (!$("#myFavorites").is(":hidden")) {
		$("#myFavorites").hide("slow");
		$("#myFTu").attr('src',
				'/daily/images/arrow_white_transparency_down.gif');
	} else {
		$("#myFavorites").show("slow");
		$("#myFTu")
				.attr('src', '/daily/images/arrow_white_transparency_up.gif');
		$("#allNews").hide("slow");
		$("#allNTu").attr('src',
				'/daily/images/arrow_white_transparency_down.gif');
	}
}

function submitForm(form, url) {
	form.action = url;
	form.submit();
}

function getFileName1(form) {
	var filename;
	filename = form.uploadfile.value;
	form.filename.value = filename;
}
function chanageImage(form) {
	var imageIcon = form.uploadfile.value;
	form.Icon.src = imageIcon;
}
function chanageTextsize1() {
	$("#story").attr('style', 'font-size:10px;margin:0 2px;');
	$("#summary").attr('class', 'summary1');
	$("#authorcss").attr('class', 'auxiInfo');

}
function chanageTextsize2() {
	$("#story").attr('style', 'font-size:12px;margin:0 2px;');
	$("#summary").attr('class', 'summary2');
	$("#authorcss").attr('class', 'auxiInfo2');
}
function chanageTextsize3() {
	$("#story").attr('style', 'font-size:14px;margin:0 2px;');
	$("#summary").attr('class', 'summary3');
	$("#authorcss").attr('class', 'auxiInfo3');
}
function showTodayRead() {
	if (!$("#todayRead").is(":hidden")) {
		$("#todayRead").hide("slow");
	} else {
		$("#todayRead").show("slow").html(
				"<img src='/kfcios/images/loading.gif'/> ...").load(
				"/do/daily/MustReadToday/");
		// $("#mostR").hide("slow");
	}
}
function showMostR() {
	if (!$("#mostR").is(":hidden")) {
		$("#mostR").hide("slow");
	} else {
		$("#mostR").show("slow").html(
				"<img src='/kfcios/images/loading.gif'/> ...").load(
				"/h_result/daily/articleTop10-Date.html");
		// $("#todayRead").hide("slow");
		var str = $("#mostReadSelect").val();
		if (str == '1') {
			changeSelectDay();
		}
		if (str == '2') {
			changeSelectWeek();
		}
		if (str == '3') {
			changeSelectMonth();
		}
	}

}

function addFeedback(url) {

	if (!$("#feedback").is(":hidden")) {
		$("#feedback").hide("slow");
	} else {
		$("#feedback").show("slow").html(
				"<img src='/kfcios/images/loading.gif'/> ...").load(url);
	}
}

function displayno() {
	$("#fdsend").remove();
	$("#fdsendDiv").append(' <h1>Thank you for your feedback !</h1>');
}

function priceNew(url) {

	if (!$("#priceNew").is(":hidden")) {
		$("#priceNew").hide("slow");
	} else {
		$("#priceNew").show("slow").html(
				"<img src='/kfcios/images/loading.gif'/> ...").load(url);
	}
}
function priceEdit(url) {

	$("#priceNew").show("slow").html(
			"<img src='/kfcios/images/loading.gif'/> ...").load(url);
}
function priceNewcancel() {
	$("#priceNew").hide("slow");
	location.href = location.href;
}
function productTree(meval) {
	jAlert(meval);
	if (!$("#tr2").is(":hidden")) {
		$("#img2").attr('src', '/images/left_7.gif');
		$("#tr2").hide("slow")
	} else {
		$("#img2").attr('src', '/images/left_6.gif');
		$("#tr2").show("slow")
	}
}
function showDwmap(url) {
	$("#dwmap").show("slow")
			.html("<img src='/kfcios/images/loading.gif'/> ...").load(url);
}
function showNewTask(url) {
	$("#newtask").show("slow").html(
			"<img src='/kfcios/images/loading.gif'/> ...").load(url);
}
function showCityMap() {
	$('#city01').load(
			'/kfcios/SubscrMealNewAction/queryCityMap.action?s=' + getS());
}

function showkoyoz(k) {
	var url = '';
	if (k == 1) {
		url = 'http://kfc.mytianhui.com/1'
	}
	if (k == 2) {
		url = 'http://kfc.mytianhui.com/2'
	}
	if (k == 3) {
		url = 'http://kfc.mytianhui.com/3'
	}
	if (k == 4) {
		url = 'http://kfc.mytianhui.com/4'
	}
	if (k == 5) {
		url = 'http://kfc.mytianhui.com/5'
	}
	if (k == 6) {
		url = 'http://kfc.mytianhui.com/6'
	}

	if (k == 6) {
		if(confirm("您好！\r\n感谢您使用肯德基宅急送网上订餐服务.\r\n为了持续完善我们的网上订餐服务，从而为顾客提供更好、更贴心的服务，\r\n我们想请您花3-5分钟填写一份满意度调查问卷，谢谢！")){
		   getObject("koyoz").launchURL(url);
		 }
	} else {
		if (confirm("您好！\r\n我们很遗憾您没有完成本次订餐.\r\n为了帮助我们了解原因从而完善今后的网上订餐服务，\r\n我们想请您花2分钟填写一份调查问卷，也衷心的希望您今后继续光顾与支持肯德基宅急送网上订餐！")) {
			getObject("koyoz").launchURL(url);
		}
	}
}

// 2010-03-07增加新版页面时的JS 张夏晖
function showProductsNew(source, dayTime, iclassId,callback) {
	// 2012-01-15 weij 处理选中不同菜单同时高亮问题 del
	// $('.nav_left_y').removeClass().addClass('nav_left_n');
	// $(source).attr("class","nav_left_y");
	// $("#tab_obj").attr("style","");
	// 2012-01-15 weij 处理选中不同菜单同时高亮问题 del end
	// 2012-01-15 weij 处理选中不同菜单同时高亮问题 add 
	
	$(".nav_left_ul li").each(function() {
		if($(this).hasClass("s")){
			$(this).removeClass();
			$(this).addClass("s");
		}else{
			$(this).removeClass();
		}
		
	});
	if($(source).parent().hasClass("s")){
		$(source).parent().addClass("s on");
	}else{
		$(source).parent().addClass("on");
	}
	
	// 2012-01-15 weij 处理选中不同菜单同时高亮问题 add end

	$("#showProductsNew").show("slow").html(
			"<img src='/kfcios/images/loading.gif'/> ...").load(
			"/kfcios/HomeAction/showProductsNew.action?dayTime=" + dayTime
					+ "&iclassId=" + iclassId + "&s=" + getS(),callback);
}
function showProductsNewHtml(source, dayTime, iclassId) {
	if(iclassId!=0){
		$('.nav_left_y').removeClass().addClass('nav_left_n');
		$(source).attr("class", "nav_left_y");
		$("#tab_obj").attr("style", "");
	}else{
		//$("#eventInner").attr("style", "display:none");
		//$("#c_content").attr("style", "display:block");
	    $('#c_content').show();$('#eventInner').hide();
	}
	$("#showProductsNew").show("slow").html(
			"<img src='/kfcios/images/loading.gif'/> ...").load(
			"/kfcios/html/kfcios_" + dayTime + "_" + iclassId + ".html?s="
					+ getS());
}

function showProductsNewHtml_E(source, dayTime, iclassId) {
	if(iclassId!=0){
		$('.nav_left_y').removeClass().addClass('nav_left_n');
		$(source).attr("class", "nav_left_y");
		$("#tab_obj").attr("style", "");
	}else{
		//$("#eventInner").attr("style", "display:none");
		//$("#c_content").attr("style", "display:block");
	    $('#c_content').show();$('#eventInner').hide();
	}
	$("#showProductsNew").show("slow").html(
			"<img src='/kfcios/images/loading.gif'/> ...").load(
			"/kfcios/html/kfcios_" + dayTime + "_" + iclassId + "_E.html?s="
					+ getS());
}


function showProductsNew11(dayTime, iclassId) {
	$("#showProductsNew").show("slow").html(
			"<img src='/kfcios/images/loading.gif'/> ...").load(
			"/kfcios/HomeAction/showProductsNew.action?dayTime=" + dayTime
					+ "&iclassId=" + iclassId + "&s=" + getS());
}

// 中英切换
function showR(obj) {
	if (obj == 'c') {
		$("#showP_c").show();
		$("#showP_e").hide();
		$("#showO_c").show();
		$("#showO_e").hide();
		$("#showD_c").show();
		$("#showD_e").hide();
	} else {
		$("#showP_e").show();
		$("#showP_c").hide();
		$("#showO_e").show();
		$("#showO_c").hide();
		$("#showD_e").show();
		$("#showD_c").hide();
	}
}
function show_div(obj) {
	if (obj == 'china') {
		// $("#china").show();
		// $("#English").hide();
		document.getElementById("china").style.display = "block";
		document.getElementById("English").style.display = "none";
		actionCall("/kfcios/OrderingAction/chinEnglish.action", {
			boolCE : true
		}, function(out) {
			// alert(out.msg);
			});
	} else {
		// $("#English").show();
		// $("#china").hide();
		document.getElementById("china").style.display = "none";
		document.getElementById("English").style.display = "block";
		actionCall("/kfcios/OrderingAction/chinEnglish.action", {
			boolCE : false
		}, function(out) {
			// alert(out.msg);
			});

	}
}
