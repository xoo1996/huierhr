//==================================================== 参数设定部分 =======================================================
var _VersionInfo="Version:1.0&#13;2.0作者:李红涛 张定平 张守锴"	//版本信息 modify by chenkl（将src初始值为空，在点击时设置src）
var bMoveable=true;		//设置是否可以拖动
var regionTree=false;
var workTypeTree=false;
var ssjdTree=false;     //定义所属街道树变量 by李灵超 2004年4月26日
var calendar=false;
var cylbTree=false;     //定义产业类别树变量 林科 2007年01月31日
//====================================================Iframe WEB 页面显示部分 =====================================================
document.writeln('<iframe id=worktypeTreeLayer src=""  Author=zhangsk frameborder=0 style="position: absolute; width: 280; height: 220; z-index: 9998; display: none"></iframe>');
var workType_outObject1;
var workType_outObject2;

document.writeln('<iframe id=regionTreeLayer src=""  Author=zhangsk frameborder=0 style="position: absolute; width: 280; height: 400; z-index: 9998; display: none"></iframe>');
var region_outObject1;
var region_outObject2;

//打开所属街道树  by李灵超 2004年4月26日
document.writeln('<iframe id=ssjdTreeLayer src=""  Author=zhangsk frameborder=0 style="position: absolute; width: 250; height: 335; z-index: 9998; display: none"></iframe>');
var ssjd_outObject1;
var ssjd_outObject2;

//打开产业类别树  by林科 2007年01月31日
document.writeln('<iframe id=cylbTreeLayer src=""  Author=zhangsk frameborder=0 style="position: absolute; width: 280; height: 180; z-index: 9998; display: none"></iframe>');
var cylb_outObject1;
var cylb_outObject2;

//去掉字符串的前后空格

function trim(str){

	var temp = str;

	var exit = false;

	while(!exit){

		if(" " == temp.charAt(0)){

			temp = temp.substring(1,temp.length);

		}else{

			exit = true;

		}

	}

	exit = false;

	while(!exit){

		if(" " == temp.charAt(temp.length - 1 )){

			temp = temp.substring(0,temp.length - 1);

		}else{

			exit = true;

		}

	}

	return temp;

}
function  setWorkTypeTreequery(obj1,obj2,obj3)//为了快速查询使用lwd20071122
     {  //alert(window.event.keyCode);
        if(window.event.keyCode==32) 
          { window.event.keyCode=0;
            setWorkTypeTree(obj1,obj2,obj3)  
          }
  }   
function setWorkTypeTree(tt,disObjName,valObjName) //主调函数
{
	if (arguments.length >  3){alert("对不起！传入本控件的参数太多！");return;}
	if (arguments.length == 0){alert("对不起！您没有传回本控件任何参数！");return;}
    var srcFrame = document.all.worktypeTreeLayer.src;
    
    //if("" == srcFrame)//叶鹏修改，根据当前情况，选择那种树的方式
    {
    
    		if(trim(tt.value)=="")
        	document.all.worktypeTreeLayer.src = "/" + lemis.WEB_APP_NAME+"/common/workType.jsp";
        else
        	document.all.worktypeTreeLayer.src = "/" + lemis.WEB_APP_NAME+"/common/workTypeQuery.jsp?condition="+tt.value;
    }
	var dads  = document.all.worktypeTreeLayer.style;
	var th = tt;
	var ttop  = tt.offsetTop;     //TT控件的定位点高
	var thei  = tt.clientHeight;  //TT控件本身的高
	var tleft = tt.offsetLeft;    //TT控件的定位点宽
	var ttyp  = tt.type;          //TT控件的类型
	while (tt = tt.offsetParent){ttop+=tt.offsetTop; tleft+=tt.offsetLeft;}
	dads.top  = (ttyp=="image")? ttop+thei : ttop+thei+6;
	dads.left = tleft;
    dads.display = '';
    workType_outObject1 = arguments[1];
    workType_outObject2 = arguments[2];
    ssjdTree_close();	
    cylbTree_close();
	closeLayer();
	regionTree_close();
    workTypeTree=true;

}
function workerType_setValue(dispaly_value,code_value){//为tag提供设置“显示”和“代码域”的值
    parent.workType_outObject1.value=dispaly_value;
    parent.workType_outObject2.value=code_value;
    parent.workTypeTree_close()
}
function workerType_clearvalue_tree(){//在iframe中点击“清空”按钮清空值同时关闭Iframe
    parent.workType_outObject1.value="";
    parent.workType_outObject2.value="";
    parent.workTypeTree_close()
}

function close_WorkType_Frame(){ //在iframe中点击“关闭”按钮关闭iframe
	parent.workTypeTree_close()
}

function workTypeTree_close(){ //为其他函数提供关闭Iframe的方法
	regionTree=false;
	workTypeTree=false;
	ssjdTree=false;
	cylbTree=false;
	calendar=false;
	document.all.worktypeTreeLayer.style.display="none";
}
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
function clearValue(obj1,obj2){   //提供页面的调用清空值的方法（不是在Iframe中的清空）
    obj1.value="";
    obj2.value="";
}
function document.onclick(){ //任意点击时关闭该控件	//ie6的情况可以由下面的切换焦点处理代替
  with(window.event)
  {
      if(workTypeTree){
          if (srcElement.getAttribute("Author")!="zhansk" && srcElement != workType_outObject1){
              regionTree=false;
              workTypeTree=false;
              ssjdTree=false;
              cylbTree=false;
              calendar=false;
              workTypeTree_close();
        	}
      }
      if(regionTree){
          if (srcElement.getAttribute("Author")!="zhansk" && srcElement != region_outObject1){
              regionTree=false;
              workTypeTree=false;
              ssjdTree=false;
              cylbTree=false;
              calendar=false;
              regionTree_close();
        	}
      }
      //点击当前页时，关闭 
      if(ssjdTree){
       
          if (srcElement.getAttribute("Author")!="zhansk" && srcElement != ssjd_outObject1){
              regionTree=false;
              workTypeTree=false;
              ssjdTree=false;
              cylbTree=false;
              calendar=false;
              ssjdTree_close();
        	}
      }
            //点击当前页时，关闭 
      if(cylbTree){
       
          if (srcElement.getAttribute("Author")!="zhansk" && srcElement != cylb_outObject1){
              regionTree=false;
              workTypeTree=false;
              ssjdTree=false;
              cylbTree=false;
              calendar=false;
              cylbTree_close();
        	}
      }
      if(calendar){
          if (srcElement.getAttribute("Author")!="wayx" && srcElement != outObject && srcElement != outButton)
          {
              regionTree=false;
              workTypeTree=false;
              ssjdTree=false;
              cylbTree=false;
              calendar=false;
              closeLayer();
              	
          }
      }
  }
}
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

function setRegionTree(tt,disObjName,valObjName) //主调函数
{
	if (arguments.length >  3){alert("对不起！传入本控件的参数太多！");return;}
	if (arguments.length == 0){alert("对不起！您没有传回本控件任何参数！");return;}
    var srcFrame = document.all.regionTreeLayer.src;
    if("" == srcFrame){
        document.all.regionTreeLayer.src = "/" + lemis.WEB_APP_NAME+"/common/region.jsp";
    }
	var dads  = document.all.regionTreeLayer.style;
	var th = tt;
	var ttop  = tt.offsetTop;     //TT控件的定位点高
	var thei  = tt.clientHeight;  //TT控件本身的高
	var tleft = tt.offsetLeft;    //TT控件的定位点宽
	var ttyp  = tt.type;          //TT控件的类型
	while (tt = tt.offsetParent){ttop+=tt.offsetTop; tleft+=tt.offsetLeft;}
	dads.top  = (ttyp=="image")? ttop+thei : ttop+thei+6;
	dads.left = tleft;
    dads.display = '';
    region_outObject1 = arguments[1];
    region_outObject2 = arguments[2];
    workTypeTree=false;
	ssjdTree_close();
	cylbTree_close();
	workTypeTree_close();
	closeLayer();
    regionTree=true;    
	
}

function region_setValue(dispaly_value,code_value){//为tag提供设置“显示”和“代码域”的值
    parent.region_outObject1.value=dispaly_value;
    parent.region_outObject2.value=code_value;
    parent.regionTree_close()
}
function region_clearvalue_tree(){//在iframe中点击“清空”按钮清空值同时关闭Iframe
    parent.region_outObject1.value="";
    parent.region_outObject2.value="";
    parent.regionTree_close()
}

function close_region_Frame(){
	parent.regionTree_close()
}

function regionTree_close(){               //在iframe中点击“关闭”按钮关闭iframe
	regionTree=false;
	workTypeTree=false;
	ssjdTree=false;
	cylbTree=false;
	calendar=false;
	document.all.regionTreeLayer.style.display="none";
}

//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
function  setSSJDTreequery(obj1,obj2,obj3)//为了快速查询使用cxp20081118
     {  //alert(window.event.keyCode);
        if(window.event.keyCode==32) 
          { window.event.keyCode=0;
            setSSJDTree(obj1,obj2,obj3)  
          }
 } 
 
function setSSJDTree(tt,disObjName,valObjName) //主调函数 用来显示所属于街道的树 by李灵超 2006年4月26日
{
	if (arguments.length >  3){alert("对不起！传入本控件的参数太多！");return;}
	if (arguments.length == 0){alert("对不起！您没有传回本控件任何参数！");return;}
    var srcFrame = document.all.ssjdTreeLayer.src;
    if("" == srcFrame){
        document.all.ssjdTreeLayer.src = "/" + lemis.WEB_APP_NAME+"/common/ssjd.jsp";
    }
    //{//根据不同情况选择不同的树
    
   // 	if(trim(tt.value)=="")
   //     	document.all.ssjdTreeLayer.src = "/" + lemis.WEB_APP_NAME+"/common/ssjd.jsp";
  //      else
  //      	document.all.ssjdTreeLayer.src = "/" + lemis.WEB_APP_NAME+"/common/ssjdQuery.jsp?condition="+tt.value;
  //  }
    
	var dads  = document.all.ssjdTreeLayer.style;
	var th = tt;
	var ttop  = tt.offsetTop;     //TT控件的定位点高
	var thei  = tt.clientHeight;  //TT控件本身的高
	var tleft = tt.offsetLeft;    //TT控件的定位点宽
	var ttyp  = tt.type;          //TT控件的类型
	while (tt = tt.offsetParent){ttop+=tt.offsetTop; tleft+=tt.offsetLeft;}
	dads.top  = (ttyp=="image")? ttop+thei : ttop+thei+6;
	dads.left = tleft;
    dads.display = '';
    ssjd_outObject1 = arguments[1];
    ssjd_outObject2 = arguments[2];
	workTypeTree_close();
	cylbTree_close();
	closeLayer();
	regionTree_close();
    ssjdTree=true;
    //tt.focus();
}

function ssjd_setValue(dispaly_value,code_value){//为tag提供设置“显示”和“代码域”的值
    parent.ssjd_outObject1.value=dispaly_value;
    parent.ssjd_outObject2.value=code_value;
    parent.ssjdTree_close()
}
function ssjd_clearvalue_tree(){//在iframe中点击“清空”按钮清空值同时关闭Iframe
    parent.ssjd_outObject1.value="";
    parent.ssjd_outObject2.value="";
    parent.ssjdTree_close()
}

function close_ssjd_Frame(){
	parent.ssjdTree_close()
}

function ssjdTree_close(){               //在iframe中点击“关闭”按钮关闭iframe
	regionTree=false;
	workTypeTree=false;
	ssjdTree=false;
	cylbTree=false;
	calendar=false;
	document.all.ssjdTreeLayer.style.display="none";
}

//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
function setCYLBTree(tt,disObjName,valObjName) //主调函数 用来显示产业类别的树 by林科 2007年01月31日
{
	
	if (arguments.length >  3){alert("对不起！传入本控件的参数太多！");return;}
	if (arguments.length == 0){alert("对不起！您没有传回本控件任何参数！");return;}
    var srcFrame = document.all.cylbTreeLayer.src;
    if("" == srcFrame){
        document.all.cylbTreeLayer.src = "/" + lemis.WEB_APP_NAME+"/common/cylb.jsp";
    }
	var dads  = document.all.cylbTreeLayer.style;
	var th = tt;
	var ttop  = tt.offsetTop;     //TT控件的定位点高
	var thei  = tt.clientHeight;  //TT控件本身的高
	var tleft = tt.offsetLeft;    //TT控件的定位点宽
	var ttyp  = tt.type;          //TT控件的类型
	while (tt = tt.offsetParent){ttop+=tt.offsetTop; tleft+=tt.offsetLeft;}
	dads.top  = (ttyp=="image")? ttop+thei : ttop+thei+6;
	dads.left = tleft;
    dads.display = '';
    cylb_outObject1 = arguments[1];
    cylb_outObject2 = arguments[2];
	workTypeTree_close();
	ssjdTree_close();
	closeLayer();
	regionTree_close();
    cylbTree=true;
	

}

function cylb_setValue(dispaly_value,code_value){
    //为tag提供设置“显示”和“代码域”的值
    parent.cylb_outObject1.value=dispaly_value;
    parent.cylb_outObject2.value=code_value;
    parent.cylbTree_close()
}
function cylb_clearvalue_tree(){//在iframe中点击“清空”按钮清空值同时关闭Ifram
    parent.cylb_outObject1.value="";
    parent.cylb_outObject2.value="";
    parent.cylbTree_close()
}

function close_cylb_Frame(){
	parent.cylbTree_close()
}

function cylbTree_close(){               //在iframe中点击“关闭”按钮关闭iframe
	regionTree=false;
	workTypeTree=false;
	ssjdTree=false;
	cylbTree=false;
	calendar=false;
	document.all.cylbTreeLayer.style.display="none";
}
