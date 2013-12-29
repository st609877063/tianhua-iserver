var cut_div; //裁减图片外框div
	var cut_img; //裁减图片
	var imgdefw; //图片默认宽度
	var imgdefh; //图片默认高度
	var offsetx = 90; //图片位置位移x
	var offsety = -270; //图片位置位移y
	var divx = 360; //外框宽度
	var divy = 360; //外框高度
	var cutx = 180; //裁减宽度
	var cuty = 180; //裁减高度
	var zoom = 1; //缩放比例

	var zmin = 0.1; //最小比例
	var zmax = 10; //最大比例
	var grip_pos = 5; //拖动块位置0-最左 10 最右
	var img_grip; //拖动块
	var img_track; //拖动条
	var grip_y; //拖动块y值
	var grip_minx; //拖动块x最小值
	var grip_maxx; //拖动块x最大值

	//图片初始化
	function imageinit() {

		cut_div = document.getElementById('cut_div');
		cut_img = document.getElementById('cut_img');
		imgdefw = cut_img.width;
		imgdefh = cut_img.height;

		if (imgdefw > divx) {
			zoom = divx / imgdefw;
			cut_img.width = divx;

			cut_img.height = Math.round(imgdefh * zoom);
		}

		cut_img.style.left = Math.round((divx - cut_img.width) / 2);
		cut_img.style.top = Math.round((divy - cut_img.height) / 2) - divy;

		if (imgdefw > cutx) {
			zmin = cutx / imgdefw;
		} else {
			zmin = 1;
		}
		zmax = zmin > 0.25 ? 8.0 : 4.0 / Math.sqrt(zmin);
		if (imgdefw > cutx) {
			zmin = cutx / imgdefw;
			grip_pos = 5 * (Math.log(zoom * zmax) / Math.log(zmax));
		} else {
			zmin = 1;
			grip_pos = 5;
		}

		Drag.init(cut_div, cut_img);
		cut_img.onDrag = when_Drag;
	}

	//图片逐步缩放
	function imageresize(flag) {
		if (flag) {
			zoom = zoom * 1.5;
		} else {
			zoom = zoom / 1.5;
		}
		if (zoom < zmin)
			zoom = zmin;
		if (zoom > zmax)
			zoom = zmax;
		cut_img.width = Math.round(imgdefw * zoom);
		cut_img.height = Math.round(imgdefh * zoom);
		checkcutpos();
		grip_pos = 5 * (Math.log(zoom * zmax) / Math.log(zmax));
		img_grip.style.left = (grip_minx + (grip_pos / 10 * (grip_maxx - grip_minx)))
				+ "px";
	}

	//获得style里面定位
	function getStylepos(e) {
		return {
			x :parseInt(e.style.left),
			y :parseInt(e.style.top)
		};
	}

	//获得绝对定位
	function getPosition(e) {
		var t = e.offsetTop;
		var l = e.offsetLeft;
		while (e = e.offsetParent) {
			t += e.offsetTop;
			l += e.offsetLeft;
		}
		return {
			x :l,
			y :t
		};
	}

	//检查图片位置
	function checkcutpos() {
		var imgpos = getStylepos(cut_img);

		max_x = Math.max(offsetx, offsetx + cutx - cut_img.clientWidth);
		min_x = Math.min(offsetx + cutx - cut_img.clientWidth, offsetx);
		if (imgpos.x > max_x)
			cut_img.style.left = max_x + 'px';
		else if (imgpos.x < min_x)
			cut_img.style.left = min_x + 'px';

		max_y = Math.max(offsety, offsety + cuty - cut_img.clientHeight);
		min_y = Math.min(offsety + cuty - cut_img.clientHeight, offsety);

		if (imgpos.y > max_y)
			cut_img.style.top = max_y + 'px';
		else if (imgpos.y < min_y)
			cut_img.style.top = min_y + 'px';
	}

	//图片拖动时触发
	function when_Drag(clientX, clientY) {

		checkcutpos();
	}

	//获得图片裁减位置
	function getcutpos() {
		var imgpos = getStylepos(cut_img);
		var x = offsetx - imgpos.x;
		var y = offsety - imgpos.y;
		var cut_pos = document.getElementById('cut_pos');
		cut_pos.value = x + ',' + y + ',' + cut_img.width + ','+ cut_img.height;
		
		return true;
	}

	//缩放条初始化
	function gripinit() {
		img_grip = document.getElementById('img_grip');
		img_track = document.getElementById('img_track');
		track_pos = getPosition(img_track);

		grip_y = track_pos.y;
		grip_minx = track_pos.x + 4;
		grip_maxx = track_pos.x + img_track.clientWidth - img_grip.clientWidth
				- 5;

		img_grip.style.left = (grip_minx + (grip_pos / 10 * (grip_maxx - grip_minx)))
				+ "px";
		img_grip.style.top = grip_y + "px";

		Drag.init(img_grip, img_grip);
		img_grip.onDrag = grip_Drag;

	}

	//缩放条拖动时触发
	function grip_Drag(clientX, clientY) {

		var posx = clientX;
		img_grip.style.top = grip_y + "px";
		if (clientX < grip_minx) {
			img_grip.style.left = grip_minx + "px";
			posx = grip_minx;
		}
		if (clientX > grip_maxx) {
			img_grip.style.left = grip_maxx + "px";
			posx = grip_maxx;
		}

		grip_pos = (posx - grip_minx) * 10 / (grip_maxx - grip_minx);
		zoom = Math.pow(zmax, grip_pos / 5) / zmax;
		if (zoom < zmin)
			zoom = zmin;
		if (zoom > zmax)
			zoom = zmax;
		cut_img.width = Math.round(imgdefw * zoom);
		cut_img.height = Math.round(imgdefh * zoom);

		checkcutpos();
	}

	//页面载入初始化
	function avatarinit() {
		imageinit();
		gripinit();
	
	}
	
	/**
 * Base class of Drag
 * @example:
 * Drag.init( header_element, element );
 */
var Drag = {
	// 对这个element的引用，一次只能拖拽一个Element
	obj: null , 
	/**
	 * @param: elementHeader	used to drag..
	 * @param: element			used to follow..
	 */
	init: function(elementHeader, element) {
		// 将 start 绑定到 onmousedown 事件，按下鼠标触发 start
		elementHeader.onmousedown = Drag.start;
		// 将 element 存到 header 的 obj 里面，方便 header 拖拽的时候引用
		elementHeader.obj = element;
		// 初始化绝对的坐标，因为不是 position = absolute 所以不会起什么作用，但是防止后面 onDrag 的时候 parse 出错了
		if(isNaN(parseInt(element.style.left))) {
			element.style.left = "0px";
		}
		if(isNaN(parseInt(element.style.top))) {
			element.style.top = "0px";
		}
		// 挂上空 Function，初始化这几个成员，在 Drag.init 被调用后才帮定到实际的函数
		element.onDragStart = new Function();
		element.onDragEnd = new Function();
		element.onDrag = new Function();
	},
	// 开始拖拽的绑定，绑定到鼠标的移动的 event 上
	start: function(event) {
		var element = Drag.obj = this.obj;
		// 解决不同浏览器的 event 模型不同的问题
		event = Drag.fixE(event);
		// 看看是不是左键点击
		if(event.which != 1){
			// 除了左键都不起作用
			return true ;
		}
		// 参照这个函数的解释，挂上开始拖拽的钩子
		element.onDragStart();
		// 记录鼠标坐标
		element.lastMouseX = event.clientX;
		element.lastMouseY = event.clientY;
		// 绑定事件
		document.onmouseup = Drag.end;
		document.onmousemove = Drag.drag;
		return false ;
	}, 
	// Element正在被拖动的函数
	drag: function(event) {
		event = Drag.fixE(event);
		if(event.which == 0 ) {
		 	return Drag.end();
		}
		// 正在被拖动的Element
		var element = Drag.obj;
		// 鼠标坐标
		var _clientX = event.clientY;
		var _clientY = event.clientX;
		// 如果鼠标没动就什么都不作
		if(element.lastMouseX == _clientY && element.lastMouseY == _clientX) {
			return	false ;
		}
		// 刚才 Element 的坐标
		var _lastX = parseInt(element.style.top);
		var _lastY = parseInt(element.style.left);
		// 新的坐标
		var newX, newY;
		// 计算新的坐标：原先的坐标+鼠标移动的值差
		newX = _lastY + _clientY - element.lastMouseX;
		newY = _lastX + _clientX - element.lastMouseY;
		// 修改 element 的显示坐标
		element.style.left = newX + "px";
		element.style.top = newY + "px";
		// 记录 element 现在的坐标供下一次移动使用
		element.lastMouseX = _clientY;
		element.lastMouseY = _clientX;
		// 参照这个函数的解释，挂接上 Drag 时的钩子
		element.onDrag(newX, newY);
		return false;
	},
	// Element 正在被释放的函数，停止拖拽
	end: function(event) {
		event = Drag.fixE(event);
		// 解除事件绑定
		document.onmousemove = null;
		document.onmouseup = null;
		// 先记录下 onDragEnd 的钩子，好移除 obj
		var _onDragEndFuc = Drag.obj.onDragEnd();
		// 拖拽完毕，obj 清空
		Drag.obj = null ;
		return _onDragEndFuc;
	},
	// 解决不同浏览器的 event 模型不同的问题
	fixE: function(ig_) {
		if( typeof ig_ == "undefined" ) {
			ig_ = window.event;
		}
		if( typeof ig_.layerX == "undefined" ) {
			ig_.layerX = ig_.offsetX;
		}
		if( typeof ig_.layerY == "undefined" ) {
			ig_.layerY = ig_.offsetY;
		}
		if( typeof ig_.which == "undefined" ) {
			ig_.which = ig_.button;
		}
		return ig_;
	}
};

	if (document.all) {
		
		window.attachEvent('onload', avatarinit);
	} else {
		
		window.addEventListener('load', avatarinit, false);
	}