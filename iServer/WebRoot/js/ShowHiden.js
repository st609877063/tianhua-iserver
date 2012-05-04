function show(id){
	var obj;
	obj=document.getElementById('PopMenu_'+id);	obj.style.visibility="visible";
	}
function hide(id){
	var obj;
	obj=document.getElementById('PopMenu_'+id);	obj.style.visibility="hidden";
	}
function hideOthers(id){
	var divs;
	if(document.all)
	{
  	divs = document.all.tags('DIV');
	}	
	else
	{
 	 divs = document.getElementsByTagName("DIV");
	}

 	for(var i = 0 ;i < divs.length;i++)
	{
	if(divs[i].id != 'PopMenu_'+id && divs[i].id.indexOf('PopMenu_')>=0)
	{	    divs[i].style.visibility="hidden";
	}	
	}
	}