(function($) {
	$.fn.marquee = function(speed, step, direction, pause) {
		var o;
		if(speed==null || step==null || direction==null || pause==null){
			o = $.extend({
					speed : 30, // �����ٶ�
					step :  1, // ��������=li���
					direction :  'up', // ��������
					pause : 1000 // ͣ��ʱ��
				}, o || {});
		}else{
					o = $.extend({
					speed : speed, // �����ٶ�
					step : step, // ��������=li���
					direction : direction, // ��������
					pause : pause // ͣ��ʱ��
				}, o || {});
		}
		// ��ȡ���������ڸ�Ԫ�������Ϣ
		
		var dIndex = jQuery.inArray(o.direction, ['right', 'down']);
		if (dIndex > -1) {
			o.direction = ['left', 'up'][dIndex];
			o.step = -o.step;
		}
		var mid;
		var div = $(this); // ��������
		var divWidth = div.innerWidth(); // ������
		var divHeight = div.innerHeight(); // ������
		var ul = $("ul", div);
		var li = $("li", ul);
		var liSize = li.size(); // ��ʼԪ�ظ���
		
		var liWidth = li.width(); // Ԫ�ؿ�
		var liHeight = li.height(); // Ԫ�ظ�
		var width = liWidth * liSize;
		var height = liHeight * liSize;
		if ((o.direction == 'left' && width > divWidth)
				|| (o.direction == 'up' && height > divHeight)) {
			// Ԫ�س�������ʾ��Χ�Ź���
			if (o.direction == 'left') {
				ul.width(2 * liSize * liWidth);
				if (o.step < 0)
					div.scrollLeft(width);
			} else {
				ul.height(2 * liSize * liHeight);
				if (o.step < 0)
					div.scrollTop(height);
			}
			ul.append(li.clone()); // ����Ԫ��
			mid = setInterval(_marquee, o.speed);
			div.hover(function() {
						clearInterval(mid);
					}, function() {
						mid = setInterval(_marquee, o.speed);
					});
		}
		function _marquee() {
			// ����
			if (o.direction == 'left') {
				var l = div.scrollLeft();
				if (o.step < 0) {
					div.scrollLeft((l <= 0 ? width : l) + o.step);
				} else {
					div.scrollLeft((l >= width ? 0 : l) + o.step);
				}
				if (l % liWidth == 0)
					_pause();
			} else {
				var t = div.scrollTop();
				if (o.step < 0) {
					div.scrollTop((t <= 0 ? height : t) + o.step);
				} else {
					div.scrollTop((t >= height ? 0 : t) + o.step);
				}
				if (t % liHeight == 0)
					_pause();
			}
		}
		function _pause() {
			// ͣ��
			if (o.pause > 0) {
				var tempStep = o.step;
				o.step = 0;
				setTimeout(function() {
							o.step = tempStep;
						}, o.pause);
			}
		}
	};
})(jQuery);