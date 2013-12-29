var cut_div; //�ü�ͼƬ���div
	var cut_img; //�ü�ͼƬ
	var imgdefw; //ͼƬĬ�Ͽ��
	var imgdefh; //ͼƬĬ�ϸ߶�
	var offsetx = 90; //ͼƬλ��λ��x
	var offsety = -270; //ͼƬλ��λ��y
	var divx = 360; //�����
	var divy = 360; //���߶�
	var cutx = 180; //�ü����
	var cuty = 180; //�ü��߶�
	var zoom = 1; //���ű���

	var zmin = 0.1; //��С����
	var zmax = 10; //������
	var grip_pos = 5; //�϶���λ��0-���� 10 ����
	var img_grip; //�϶���
	var img_track; //�϶���
	var grip_y; //�϶���yֵ
	var grip_minx; //�϶���x��Сֵ
	var grip_maxx; //�϶���x���ֵ

	//ͼƬ��ʼ��
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

	//ͼƬ������
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

	//���style���涨λ
	function getStylepos(e) {
		return {
			x :parseInt(e.style.left),
			y :parseInt(e.style.top)
		};
	}

	//��þ��Զ�λ
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

	//���ͼƬλ��
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

	//ͼƬ�϶�ʱ����
	function when_Drag(clientX, clientY) {

		checkcutpos();
	}

	//���ͼƬ�ü�λ��
	function getcutpos() {
		var imgpos = getStylepos(cut_img);
		var x = offsetx - imgpos.x;
		var y = offsety - imgpos.y;
		var cut_pos = document.getElementById('cut_pos');
		cut_pos.value = x + ',' + y + ',' + cut_img.width + ','+ cut_img.height;
		
		return true;
	}

	//��������ʼ��
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

	//�������϶�ʱ����
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

	//ҳ�������ʼ��
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
	// �����element�����ã�һ��ֻ����קһ��Element
	obj: null , 
	/**
	 * @param: elementHeader	used to drag..
	 * @param: element			used to follow..
	 */
	init: function(elementHeader, element) {
		// �� start �󶨵� onmousedown �¼���������괥�� start
		elementHeader.onmousedown = Drag.start;
		// �� element �浽 header �� obj ���棬���� header ��ק��ʱ������
		elementHeader.obj = element;
		// ��ʼ�����Ե����꣬��Ϊ���� position = absolute ���Բ�����ʲô���ã����Ƿ�ֹ���� onDrag ��ʱ�� parse ������
		if(isNaN(parseInt(element.style.left))) {
			element.style.left = "0px";
		}
		if(isNaN(parseInt(element.style.top))) {
			element.style.top = "0px";
		}
		// ���Ͽ� Function����ʼ���⼸����Ա���� Drag.init �����ú�Űﶨ��ʵ�ʵĺ���
		element.onDragStart = new Function();
		element.onDragEnd = new Function();
		element.onDrag = new Function();
	},
	// ��ʼ��ק�İ󶨣��󶨵������ƶ��� event ��
	start: function(event) {
		var element = Drag.obj = this.obj;
		// �����ͬ������� event ģ�Ͳ�ͬ������
		event = Drag.fixE(event);
		// �����ǲ���������
		if(event.which != 1){
			// �����������������
			return true ;
		}
		// ������������Ľ��ͣ����Ͽ�ʼ��ק�Ĺ���
		element.onDragStart();
		// ��¼�������
		element.lastMouseX = event.clientX;
		element.lastMouseY = event.clientY;
		// ���¼�
		document.onmouseup = Drag.end;
		document.onmousemove = Drag.drag;
		return false ;
	}, 
	// Element���ڱ��϶��ĺ���
	drag: function(event) {
		event = Drag.fixE(event);
		if(event.which == 0 ) {
		 	return Drag.end();
		}
		// ���ڱ��϶���Element
		var element = Drag.obj;
		// �������
		var _clientX = event.clientY;
		var _clientY = event.clientX;
		// ������û����ʲô������
		if(element.lastMouseX == _clientY && element.lastMouseY == _clientX) {
			return	false ;
		}
		// �ղ� Element ������
		var _lastX = parseInt(element.style.top);
		var _lastY = parseInt(element.style.left);
		// �µ�����
		var newX, newY;
		// �����µ����꣺ԭ�ȵ�����+����ƶ���ֵ��
		newX = _lastY + _clientY - element.lastMouseX;
		newY = _lastX + _clientX - element.lastMouseY;
		// �޸� element ����ʾ����
		element.style.left = newX + "px";
		element.style.top = newY + "px";
		// ��¼ element ���ڵ����깩��һ���ƶ�ʹ��
		element.lastMouseX = _clientY;
		element.lastMouseY = _clientX;
		// ������������Ľ��ͣ��ҽ��� Drag ʱ�Ĺ���
		element.onDrag(newX, newY);
		return false;
	},
	// Element ���ڱ��ͷŵĺ�����ֹͣ��ק
	end: function(event) {
		event = Drag.fixE(event);
		// ����¼���
		document.onmousemove = null;
		document.onmouseup = null;
		// �ȼ�¼�� onDragEnd �Ĺ��ӣ����Ƴ� obj
		var _onDragEndFuc = Drag.obj.onDragEnd();
		// ��ק��ϣ�obj ���
		Drag.obj = null ;
		return _onDragEndFuc;
	},
	// �����ͬ������� event ģ�Ͳ�ͬ������
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