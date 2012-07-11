//通用联动

//select
function ld_selectFill(subNode,fld2){

	var nodeList = subNode.childNodes;
	var ctrl = fld2;
	var Opt;
	if (document.all)	{
		for (i=0; i<nodeList.length; i++)		{
			Opt = ctrl.document.createElement("OPTION");
			Opt.text = nodeList[i].getAttribute("name");
			Opt.value = nodeList[i].getAttribute("code");
			ctrl.options.add(Opt);
		}
	}else{
		for (i=0; i<nodeList.length; i++){
			Opt = new Option(nodeList[i].getAttribute("name"),nodeList[i].getAttribute("code"),false,false);
			ctrl.options[ctrl.options.length] = Opt;
		}
	}
}

function ld_resetSelect1(treeDom,fld1){
	var ctrl = fld1;
	ld_clearList(fld1);
	if (document.all)	{
		    var Opt = ctrl.document.createElement("OPTION");
			Opt.text = "请选择";
			Opt.value = "";
			ctrl.options.add(Opt);
	}else{
		    var Opt = new Option("请选择","",false,false);
			ctrl.options[ctrl.options.length] = Opt;
	}
	var rootNode = treeDom.documentElement;	
	ld_selectFill(rootNode,fld1);
}

function ld_addOption(fld1,value1,text1){
	if (document.all)	{
		    var Opt = fld1.document.createElement("OPTION");
			Opt.text = text1;
			Opt.value = value1;
			fld1.options.add(Opt);
	}else{
		    var Opt = new Option(text1,value1,false,false);
			fld1.options[fld1.options.length] = Opt;
	}
}


//清空select的选项
function ld_clearList(ctrl){
	if (document.all)	{
		for(;ctrl.options.length>0;)
			ctrl.options.remove(ctrl.options.length-1);
	}	else	{
		for(;ctrl.options.length>0;)
			ctrl.options[ctrl.options.length-1] = null;
	}
}
//下拉框选中
function ld_select(fld1,value1){
	var opsArr = fld1.options;
	for(var i=0;i<opsArr.length;i++){
		if(opsArr[i].value == value1){
			opsArr[i].selected = true;
			return;
		}
	}
}

//第一个列表,treeDom是xmlDom树
//第一个下拉框
function ld_fillFirst(treeDom,fld1){
	
	var rootNode = treeDom.documentElement;	
	//alert(rootNode.nodeName);
	//填充选项
	ld_fill(rootNode,fld1);
}
//fill first且联动之后的其他框(三级)(用户动作触发)
function ld_fillFirst_3(treeDom,fld1,fld2,fld3){
	
	ld_fillFirst(treeDom,fld1);
	ld_chg1_3(treeDom,fld1,fld2,fld3);
}
function ld_fillFirst_2(treeDom,fld1,fld2){
	
	ld_fillFirst(treeDom,fld1);
	ld_chg1(treeDom,fld1,fld2);
}
//填充list,选中(对3级联动)
function ld_fillAllValue_3(treeDom,fld1,value1,fld2,value2,fld3,value3){
	ld_clearList(fld1);
	ld_fillFirst(treeDom,fld1);
	ld_select(fld1,value1);
	ld_chg1(treeDom,fld1,fld2);
	ld_select(fld2,value2);
	ld_chg2_3(treeDom,fld1,fld2,fld3);
	ld_select(fld3,value3);
}
//填充list,选中(对2级联动)
function ld_fillAllValue_2(treeDom,fld1,value1,fld2,value2){
	
	ld_fillFirst(treeDom,fld1);
	ld_select(fld1,value1);
	ld_chg1(treeDom,fld1,fld2);
	ld_select(fld2,value2);
	
}

//第一个chang后，fld2的列表也要刷新(onchange 对2级联动)
function ld_chg1(treeDom,srcElement,fld2){
	ld_clearList(fld2);
	
	var t = srcElement.selectedIndex;
	if (t==-1) return;

	var srcValue = srcElement.options[srcElement.selectedIndex].value;
	ld_chg1ByValue(treeDom,srcValue,fld2);
}
//第一个chang后，fld2,fld3的列表都要刷新(onchange 对3级联动)
function ld_chg1_3(treeDom,srcElement,fld2,fld3){
	ld_chg1(treeDom,srcElement,fld2);
	ld_chg2_3(treeDom,srcElement,fld2,fld3);
}
//第二个chang后，fld3的列表也要刷新(onchange 对3级联动),建议更名ld_chg2_3
function ld_chg2_3(treeDom,srcElement1,srcElement2,fld3){
	
	//ld_clearList(fld2);
	ld_clearList(fld3);
	
	var t = srcElement1.selectedIndex;
	if (t==-1) return;
	t = srcElement2.selectedIndex;
	if (t==-1) return;

	var srcValue1 = srcElement1.options[srcElement1.selectedIndex].value;
	var srcValue2 = srcElement2.options[srcElement2.selectedIndex].value;

    ld_chg2ByValue(treeDom,srcValue1,srcValue2,fld3);
}

function ld_chg1ByValue(treeDom,value1,fld2){
	
	var rootNode = treeDom.documentElement;	
	var srcValue = value1;
	var subNode = xml_firstChildNodeCode(rootNode,srcValue);

	//填充选项
	ld_fill(subNode,fld2);
}



//第二个chang后，fld3的列表也要刷新,treeDom是xmlDom树
function ld_chg2ByValue(treeDom,srcValue1,srcValue2,fld3){
    var rootNode = treeDom.documentElement;	
	var subNode1 = xml_firstChildNodeCode(rootNode,srcValue1);
	var subNode2 = xml_firstChildNodeCode(subNode1,srcValue2);

	//填充选项
	ld_fill(subNode2,fld3);
}


//srcElement chang后，fld2的列表也要刷新,treeDom是xmlDom树
function ld_fill(subNode,fld2){
	//alert("ld_fill");
	var nodeList = subNode.childNodes;
	//alert("ld_fill1");
	//don't clear
	//ld_clearList(fld2);
	var ctrl = fld2;
	var Opt;
	if (document.all)	{
		for (i=0; i<nodeList.length; i++)		{
			Opt = ctrl.document.createElement("OPTION");
			Opt.text = nodeList[i].getAttribute("name");
			Opt.value = nodeList[i].getAttribute("code");
			ctrl.options.add(Opt);
		}
	}else{
		for (i=0; i<nodeList.length; i++){
			Opt = new Option(nodeList[i].getAttribute("name"),nodeList[i].getAttribute("code"),false,false);
			ctrl.options[ctrl.options.length] = Opt;
		}
	}
}

//need for page
var addressDom = null;