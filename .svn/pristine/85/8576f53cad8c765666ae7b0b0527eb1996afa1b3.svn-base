document.writeln('<div id="progressBarLayer" style="position:absolute; width:200px; height:115px; z-index:999"></div>');

function hideProgressBar() {
	progressBarLayer.style.visibility="hidden";
}
function close_window() {
	progressBarLayer.style.visibility="hidden";
	//  ½áÊø±³¾°Ð§¹û
	window.endEffect();
}
function showProgressBar(title,para) {
	var left = 300;
	var top = 280;
	var width = 400;
	var height = 80;
	
	// ÏÔÊ¾±³¾°²ã
	startEffect();

	if(left <= 0)
		left = 200;
	if(top <= 0)
		top = 200;
	if(width <= 0)
		width = 400;
	if(height <= 0)
		height = 80;
		
	progressBarLayer.style.left=left;
	progressBarLayer.style.top=top;
	progressBarLayer.style.width=width;
	progressBarLayer.style.height=height;

	width=width;
	height=height;
	progressBarLayer.innerHTML="<table width=" + width+" height="+height
		+ " border=0 bgcolor=#0066be cellpadding=0px cellspacing=0><tr><td>"
		+ "<table border=0 cellpadding=0 cellspacing=0 height=15><tr><th height=15 "
		+ "onMouseDown=initializedragie(progressBarLayer) onMouseUp=stopns() style=cursor:hand width=100% bgcolor=#0066be "
		+ "onselectstart='return false'><layer width=100% onMouseOver=dragswitch=1;drag_dropns(progressBarLayer) "
		+ "onMouseOut=dragswitch=0 left=-6 top=2 onselectstart='return false'><div align='left'><font color=#FFFFFF size=2>&nbsp; "
		+ title+"</font></div></layer></th><td bgcolor=#0066be onselectstart='return false'> "
		+ "<img id=img_close src='/" + lemis.WEB_APP_NAME + "/images/progressBar/close.gif' style='border:0px outset;' onclick=close_window();>&nbsp;</td></tr>"
		+ "<tr><td colspan=2 style=padding:0px width=100% height="+height
		+ "><iframe name=subwindow width=100% height=100% noresize scrolling=no frameborder=0 marginheight=0 marginwidth=0"
		+ "src='/" + lemis.WEB_APP_NAME + "/common/progressBarStatus.jsp?progressBarName="
		+ para + "'></iframe></td></tr></table></td></tr></table>";
//	progressBarLayer.innerHTML="<table width="+width+" height="+height+" border=0 bgcolor=#0066be cellpadding=0px cellspacing=0><tr><td><table border=0 cellpadding=0 cellspacing=0 height=15><tr><th height=15 onMouseDown=initializedragie(progressBarLayer) onMouseUp=stopns() style=cursor:hand width=100%  background='/lemis/images/progressBar/title_bg.jpg' onselectstart='return false'><layer width=100% onMouseOver=dragswitch=1;drag_dropns(progressBarLayer) onMouseOut=dragswitch=0 left=-6 top=2 onselectstart='return false'><div align='left'><font color=#FFFFFF size=2>&nbsp;"+title+"</font></div></layer></th><td background='/lemis/images/progressBar/title_bg.jpg' onselectstart='return false'><img id=img_close src='/lemis/images/progressBar/close.gif' style='border:0px outset;' onclick=close_window();>&nbsp;</td></tr><tr><td colspan=2 style=padding:0px width=100% height="+height+"><iframe name=subwindow width=100% height=100% noresize scrolling=no frameborder=0 marginheight=0 marginwidth=0 src='/lemis/common/progressBarStatus.jsp?progressBarName="+para+"'></iframe></td></tr></table></td></tr></table>";
	progressBarLayer.style.visibility="visible";
}

var dragswitch=0
var nsx
var nsy
var nstemp
var whichIt = null;
var dragapproved=false

function drag_dropns(name){
	temp=eval(name)
	temp.captureEvents(Event.MOUSEDOWN | Event.MOUSEUP)
	temp.onmousedown=gons
	temp.onmousemove=dragns
	temp.onmouseup=stopns
}

function gons(e){
	temp.captureEvents(Event.MOUSEMOVE)
	nsx=e.x
	nsy=e.y
}
function dragns(e){
	if (dragswitch==1){
		temp.moveBy(e.x-nsx,e.y-nsy)
		return false
	}
}

function stopns(){
//	temp.releaseEvents(Event.MOUSEMOVE)
	dragapproved=false;
}

function drag_dropie(){
	if (dragapproved==true){
		temp.style.pixelLeft=tempx+event.clientX-iex
		temp.style.pixelTop=tempy+event.clientY-iey
		return false
	}
}

function initializedragie(name){
	whichIt=name
	temp=whichIt
	iex=event.clientX
	iey=event.clientY
	tempx=temp.style.pixelLeft
	tempy=temp.style.pixelTop
	dragapproved=true
	document.onmousemove=drag_dropie
}


