
//<!--
function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}
//-->





//只弹出一个窗口
function displayWindow(theURL,winName,width,height) { //v3.1
    var window_width = width;
    var window_height = screen.height-100;
    var window_top = 0;
    var window_left =screen.width-window_width-30;
    newWindow=window.open(''+ theURL + '',''+ winName + '','width=' + window_width + ',height=' + window_height + ',top=' + window_top + ',left=' + window_left + ',scrollbars=yes');
    newWindow.focus();
}

var selectFlag=true;
function showLeftMenu(){
	if(parent.document.all.switchLeft.cols == "0,*"){
		parent.document.all.switchLeft.cols = "150,*";
		document.all.button.src = "/" + lemis.WEB_APP_NAME + "/images/arrow_left.gif";
        document.all.button.title="隐藏菜单";
        selectFlag=true;
	}
}
function changeLeft(){
	if(parent.document.all.switchLeft.cols == "0,*"){
        if(true==selectFlag){
            parent.document.all.switchLeft.cols = "150,*";
            document.all.button.src = "/" + lemis.WEB_APP_NAME + "/images/arrow_left.gif";
            document.all.button.title="隐藏菜单";
        }else{
            //alert("请先选择子系统");
        	return false;
        }
	}else{
		parent.document.all.switchLeft.cols = "0,*";
		document.all.button.src = "/" + lemis.WEB_APP_NAME + "/images/arrow_right.gif";
        document.all.button.title="显示菜单";
	}
}

//隐藏top
function hiddenTop(){
  parent.document.all.switchTop.rows = "0,*";
}

//显示top
function displayTop(){
   parent.document.all.switchTop.rows = "95,*";
}

//
function expandIt1(el) {
	obj = eval("sub" + el);
	if (obj.style.display == "none")
	{
		obj.style.display = "block";//显示子菜单
		parent.document.all.switchTop.rows = "95,*";
		//document.all.main1.innerHTML = "向上";
        document.all.main1.src = "/" + lemis.WEB_APP_NAME + "/images/arrow_up.gif";
        document.all.main1.title="隐藏LOGO";
	}else {
        obj.style.display = "none";
        parent.document.all.switchTop.rows = "30,*";
        //document.all.main1.innerHTML = "向下";
        document.all.main1.src = "/" + lemis.WEB_APP_NAME + "/images/arrow_down.gif";
        document.all.main1.title="显示LOGO";
	}
}
