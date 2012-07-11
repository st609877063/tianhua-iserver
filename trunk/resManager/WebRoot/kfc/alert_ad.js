
var show_alert = function(url){
	$("body").prepend(
		'<div style=" display:none; width:559px;position:relative;" id="alertNewDiv">'
		+'	<img src="'+url+'" border="0" />'
		+'	<a href="javascript:closeNewDiv();" style="position:absolute; right:5px; top:5px;">'
		+'		<img src="/kfcios/imgnew/ad/close.png" border="0" />'
		+'	</a>'
		+'</div>'
	);
	//alert('show_alert');
	showid("alertNewDiv");
}

function showid(idname){
  var isIE = (document.all) ? true : false;
  var isIE6 = isIE && ([/MSIE (\d)\.0/i.exec(navigator.userAgent)][0][1] == 6);
  var newbox=document.getElementById(idname);
  newbox.style.zIndex="999";
  newbox.style.display="block"
  newbox.style.position = !isIE6 ? "absolute" : "absolute";
  newbox.style.top = "85px";
  newbox.style.left = "50%";
  newbox.style.marginLeft = "-251px";//- newbox.offsetHeight / 2 + "px"; //
  var layer=document.createElement("div");
  layer.id="layer_alert";
  layer.style.width=layer.style.height="100%";
  layer.style.position= !isIE6 ? "fixed" : "absolute";
  layer.style.top=layer.style.left=0;
  layer.style.backgroundColor="#000";
  layer.style.zIndex="998";
  layer.style.opacity="0.5";
  document.body.appendChild(layer);
  var sel=document.getElementsByTagName("select");
  for(var i=0;i<sel.length;i++){        
	sel[i].style.visibility="hidden";
  }

  function layer_iestyle(){      
	layer.style.width=Math.max(document.documentElement.scrollWidth, document.documentElement.clientWidth)+ "px";
	layer.style.height= Math.max(document.documentElement.scrollHeight, document.documentElement.clientHeight)+"px";
  }
  
  function newbox_iestyle(){
	newbox.style.marginLeft = document.documentElement.scrollLeft - newbox.offsetWidth / 2 + "px";
  }
  
  if(isIE){layer.style.filter ="alpha(opacity=50)";}
  
  if(isIE6){  
	layer_iestyle();
	newbox_iestyle();
	window.attachEvent("onscroll",function(){                              
	  newbox_iestyle();
	})
	window.attachEvent("onresize",layer_iestyle);       
  }  
  layer.onclick=function(){
	$(document).ready(function(){
		$('#layer_alert').remove();
		$('#alertNewDiv').hide();
	});
  };
}

function closeNewDiv(){
	$(document).ready(function(){
		$('#layer_alert').remove();
		$('#alertNewDiv').hide();
	});
}
