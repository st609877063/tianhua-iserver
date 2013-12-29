/**
 * @author 田宇
 */
(function($){
	/**
	 * @deprecated
	 * @param value 设置位置值
	 * @param 操作对象的ID
	 * */
	$.fn.cp=function( value ){
		var elem = $(this).get(0);
			if (elem&&(elem.tagName=="TEXTAREA"||elem.type.toLowerCase()=="text")) {
			   if($.browser.msie){
					   var rng;
					   if(elem.tagName == "TEXTAREA"){
						    rng = event.srcElement.createTextRange();
						    rng.moveToPoint(event.x,event.y);
					   }else{
					    	rng = document.selection.createRange();
					   }
					   if( value === undefined || value==0){
					   	 rng.moveStart("character",-event.srcElement.value.length);
					     return  rng.text.length;
					   }else if(typeof value === "number" ){
					   	 var index=this.cp();
						 index>value?( rng.moveEnd("character",value-index)):(rng.moveStart("character",value-index))
						 rng.select();
					   }
				}else{
					if( value === undefined || value==0 ){
					   	 return elem.selectionStart;
					   }else if(typeof value === "number" ){
					   	 elem.selectionEnd = value;
       			         elem.selectionStart = value;
					   }
				}
			}else{
				if( value === undefined )
				   return undefined;
			}
	},   
	/**
	 * 获取光标位置
	 * */
	$.fn.getCurPos= function(){  
       var e=$(this).get(0);   
       e.focus();   
       if(e.selectionStart){    //FF
            return e.selectionStart;   
        }   
       if(document.selection){    //IE   
           var r = document.selection.createRange();   
           if (r == null) {
                return e.value.length;   
            }   
            var re = e.createTextRange();
            if(re.duplicate){
	           	var rc = re.duplicate();
	            re.moveToBookmark(r.getBookmark());   
	            rc.setEndPoint('EndToStart', re); 
            }else{//其他IE版本
            	re.moveToPoint(event.x,event.y);
            	re.moveStart("character",-event.srcElement.value.length);
            	return re.text.length;
            }
            return rc.text.length;   
        }
        return e.value.length;   
   },
   /**
    * 设置光标位置
    * */
    $.fn.setCurPos= function(pos) {
        var e=$(this).get(0);   
        e.focus();   
        if (e.setSelectionRange) {   
            e.setSelectionRange(pos, pos);   
        } else if (e.createTextRange) {   
           var range = e.createTextRange();   
            range.collapse(true);   
            range.moveEnd('character', pos);   
            range.moveStart('character', pos);   
            range.select();   
        }   
   },
   $.fn.insertText= function(str) {
   		var obj=$(this).get(0);
   		if (document.selection) {
	        var sel = document.selection.createRange();
	        sel.text = str;
    	} else if (typeof obj.selectionStart === 'number' && typeof obj.selectionEnd === 'number') {
	        var startPos = obj.selectionStart,endPos = obj.selectionEnd,cursorPos = startPos,tmpStr = obj.value;
	        obj.value = tmpStr.substring(0, startPos) + str + tmpStr.substring(endPos, tmpStr.length);
	        cursorPos += str.length;
	        obj.selectionStart = obj.selectionEnd = cursorPos;
   		} else {
        	obj.value += str;
    	}
   },
   $.fn.TARowNum=function(){
		var obj=$(this).get(0);
        var rng=obj.createTextRange();
        var h=rng.boundingHeight;
        rng.move('character',1)
        var mh=rng.boundingHeight;
        return (h/mh);
   }
})(jQuery);