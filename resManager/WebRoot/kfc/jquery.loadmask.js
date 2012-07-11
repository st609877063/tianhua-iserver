;(function($){
	
	$.fn.mask = function(label, delay){
		var time=10000;
		$(this).each(function() {
			var element = $(this);
			if(delay !== undefined && delay > 0) {
		        element.data("_mask_timeout", setTimeout(function() { $.maskElement(element, label)}, delay));
		        setTimeout(function(){
		        	element.unmask();
				},time+delay);
			} else {
				$.maskElement($(this), label);
				 setTimeout(function(){
					 element.unmask();
				},time);
			}
		});
	};
	
	$.fn.unmask = function(){
		$(this).each(function() {
			$.unmaskElement($(this));
		});
	};
	
	$.fn.isMasked = function(){
		return this.hasClass("masked");
	};

	$.maskElement = function(element, label){
		if (element.data("_mask_timeout") !== undefined) {
			clearTimeout(element.data("_mask_timeout"));
			element.removeData("_mask_timeout");
		}
		if(element.isMasked()) {
			$.unmaskElement(element);
		}
		if(element.css("position") == "static") {
			element.addClass("masked-relative");
		}
		element.addClass("masked");
		var maskDiv = $('<div class="loadmask"></div>');
		if(navigator.userAgent.toLowerCase().indexOf("msie") > -1){
			maskDiv.height(element.height() + parseInt(element.css("padding-top")) + parseInt(element.css("padding-bottom")));
			maskDiv.width(element.width() + parseInt(element.css("padding-left")) + parseInt(element.css("padding-right")));
		}
		if(navigator.userAgent.toLowerCase().indexOf("msie 6") > -1){
			element.find("select").addClass("masked-hidden");
		}
		element.append(maskDiv);
		if(label !== undefined) {
			var maskMsgDiv = $('<div class="loadmask-msg" style="display:none;"></div>');
			maskMsgDiv.append('<div style="width:100px;padding-left:50px;">' + '请稍候...' + '</div>');
			element.append(maskMsgDiv);
			maskMsgDiv.css("top", Math.round(element.height() / 2 - (maskMsgDiv.height() - parseInt(maskMsgDiv.css("padding-top")) - parseInt(maskMsgDiv.css("padding-bottom"))) / 2)+"px");
			maskMsgDiv.css("left", Math.round(element.width() / 2 - (maskMsgDiv.width() - parseInt(maskMsgDiv.css("padding-left")) - parseInt(maskMsgDiv.css("padding-right"))) / 2)+"px");
			maskMsgDiv.show();
		}
		
	};
	
	$.unmaskElement = function(element){
		if (element.data("_mask_timeout") !== undefined) {
			clearTimeout(element.data("_mask_timeout"));
			element.removeData("_mask_timeout");
		}
		element.find(".loadmask-msg").remove();
		element.find(".loadmask").remove();
		element.removeClass("masked");
		element.removeClass("masked-relative");
		element.find("select").removeClass("masked-hidden");
	};
 
})(jQuery);