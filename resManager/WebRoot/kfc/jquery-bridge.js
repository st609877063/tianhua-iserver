function processParams(params){
     var rparams = "";
       if(params)
       for(var prop in params){
           var pvalue = params[prop];
           if( $.isArray(pvalue) ){
               for(var i=0;i<pvalue.length;i++){
                   var obj = pvalue[i];
                   if("object" == typeof obj){
                       for(var oprop in obj){
                           rparams += "&"+prop+"["+i+"]."+oprop+"="+ encodeURIComponent(obj[oprop]);
                       }
                   }else{
                       rparams += "&"+prop+"="+encodeURIComponent(obj)
                   }
               }
           }else{
               rparams += "&"+prop+"="+encodeURIComponent(pvalue);
           }
       }
   
       return rparams;
}

function injectParams(form1,params){
    if(params)
       for(var prop in params){
           var pvalue = params[prop];
           if( $.isArray(pvalue) ){
               for(var i=0;i<pvalue.length;i++){
                   var obj = pvalue[i];
                   if("object" == typeof obj){
                       for(var oprop in obj){
                           $("<input type=hidden name='"+prop+"["+i+"]."+oprop+"'>").val(obj[oprop]).appendTo($(form1));
                       }
                   }else{
                       $("<input type=hidden name='"+prop+"'>").val(obj).appendTo($(form1));
                   }
               }
           }else{
           $("<input type=hidden name='"+prop+"'>").val(pvalue).appendTo($(form1));
           }
       }

}

function actionCall(action,params,callback){
    params = processParams(params);

   jQuery.post(action,params,function(jsonobj){
	   if(jsonobj.exception){
		   alert("Error:"+jsonobj.msg);
		   return;
	   }
	   if(callback)
		  callback(jsonobj);

	},"json");

}

function formSend(form1,callback,params){
   if(params)
       injectParams(form1,params);
   
   if(form1.enctype != "multipart/form-data")
        jQuery.post(form1.action,$(form1).serialize(),function(jsonobj){
               if(jsonobj.exception){
                   alert("Error:"+jsonobj.msg);
                   return;
               }
               if(callback)
                  callback(jsonobj);
        },"json");
    else
        formSubmit(form1,callback);

}

function formSubmit(form1,callback){

  if(  $("iframe[name=resultframe]").size()==0 ){
      $("body").append('<iframe name="resultframe" width="0px" height="0px"></iframe>');
  }
  if(callback)
  iframeCallback = callback;

  form1.target = "resultframe";
  form1.submit();
    
}

var iframeCallback = function(){
    alert("iframeCallback is called!");
};


function pageGet(topart,url){
	$(topart).load(url);
}


function maskDialog(url){
var bH=$("body").height();
var bW=$("body").width();
//$("body").html( '<div id="maskDiv" style="display:none;"></div>'
//							    + '<div id="dialogLayer" style="display:none;"></div>' +$("body").html());
$("body").prepend('<div id="maskDiv" style="display:none;"> </div>'
							    + '<div id="dialogLayer" style="display:none;"></div>');
$("#maskDiv").css({width:bW,height:bH,position:"absolute",top:"0px",left:"0px"
					,"background-color":"#000","z-index":"30",filter:"alpha(opacity=75)",opacity:"0.0"});
$("#maskDiv").show("slow").html("正在提交请等待。。。。。。。。。。。。");
$("#dialogLayer").css({position:"absolute",top:"150px","z-index":999,left:"260px"})
         .show("slow").html("<img src='/kfcios/images/loading.gif'/> ...").load(url);
}

function maskDialogClose(){
  $("#maskDiv").remove();
  $("#dialogLayer").remove();
}

function aliMaskDialog(url){
var bH=document.body.scrollHeight;
var bW=$("body").width();

document.getElementById("hour0").disabled=true;
document.getElementById("minute0").disabled=true;
//$("body").html( '<div id="maskDiv" style="display:none;"></div>'
//							    + '<div id="dialogLayer" style="display:none;"></div>' +$("body").html());
$("body").prepend('<div id="maskDiv" style="display:none;"> </div>'
							    + '<div id="dialogLayer" style="display:none;"></div>');
$("#maskDiv").css({width:bW,height:bH,position:"absolute",top:"0px",left:"0px"
					,"background-color":"#000","z-index":"30",filter:"alpha(opacity=75)",opacity:"0.3"});
$("#maskDiv").show("slow");
$("#dialogLayer").css({position:"absolute",top:"270px","z-index":40,left:"260px"})
         .show("slow").html("<img src='/kfcios/images/loading.gif'/> ...").load(url);
}

function alimaskDialogClose(){
	document.getElementById("hour0").disabled=false;
	document.getElementById("minute0").disabled=false;
	//获取用户选择的支付方式
	var itranstype="1";
	var checkPayment=document.getElementsByName("checkPayment");
	for(n=0;n<checkPayment.length;n++){	
		if(checkPayment[n].checked){
			 if(checkPayment[n].value==1){//支付宝
			 	itranstype="2";
			 }else{//银行
			 	itranstype="1";
			 }
		}
	}
	actionCall("/kfcios/OrderingAction/orderItransType.action",{itransType:itranstype},function(out){
			//支付成功
			if(out.boo==1){
			     $("#dialogLayer").remove();
			     location.href = '/kfcios/OrderingAction/ordering.action';
			}else{
				 $("#maskDiv").remove();
				 $("#dialogLayer").remove();
				 jAlert(out.msg);
		    }
	});
}

function alimaskDialogClose2(){
document.getElementById("hour0").disabled=false;
document.getElementById("minute0").disabled=false;
 $("#maskDiv").remove();
 $("#dialogLayer").remove();

}

function hpmaskDialog(url){
//var bH=$("body").height()+600;
var bH=$("body").height();
var bW=$("body").width();
//$("body").html( '<div id="maskDiv" style="display:none;"></div>'
//							    + '<div id="dialogLayer" style="display:none;"></div>' +$("body").html());
$("body").prepend('<div id="maskDiv" style="display:none;"> </div>'
							    + '<div id="dialogLayer" style="display:none;"></div>');
$("#maskDiv").css({width:bW,height:bH,position:"absolute",top:"0px",left:"0px"
					,"background-color":"#000","z-index":"30",filter:"alpha(opacity=75)",opacity:"0.30"});
$("#maskDiv").show("slow").html("");
//$("#dialogLayer").css({position:"absolute",top:"0px","z-index":40,left:"180px"})
$("#dialogLayer").css({position:"absolute",top:"15%","z-index":40,left:"50%","margin-left":"-458px"})
         .show("slow").html("<img src='/kfcios/images/loading.gif'/> ...").load(url);
}

function hpmaskDialogClose(){
  $("#maskDiv").remove();
  $("#dialogLayer").remove();
}

function showDivDialog(url,top,left){
	var bH=$("body").height()+600;
	var bW=$("body").width();
	$("body").prepend('<div id="maskDiv" style="display:none;"></div>'
								    + '<div id="dialogLayer" style="display:none;"></div>');
	$("#maskDiv").css({width:bW,height:bH,position:"absolute",top:"0px",left:"0px"
						,"background-color":"#000","z-index":"30",filter:"alpha(opacity=75)",opacity:"0.30"});
	$("#maskDiv").show("slow").html("");
	$("#dialogLayer").css({position:"absolute",top:top,"z-index":40,left:left})
	         .show("slow").html("<img src='/kfcios/images/loading.gif'/> ...").load(url);
}

function closeDivDialog(){
	  $("#maskDiv").remove();
	  $("#dialogLayer").remove();
}
