
eapObjsMgr = new EAPObjsMgr();
function EAPObjsMgr(){	
	this.getEAPObj = EAPOM_getEAPObj;
	this.onReady = EAPOM_onReady;          
}
function EAPOM_getEAPObj(editerObj){
	var objName = editerObj.JSObjName;  
	switch(objName){
		case "QuickSelect": var obj = new QuickSelectObj(editerObj); 
			break;	
		case "Select": var obj = new SelectObj(editerObj);
			break;
		default:		alert("配置文件中没有此种类型为'"+objName+"'的JS对象！");
		}

	return obj;
}



function EAPOM_onReady(formObj){
	var obj,eapObj;
	
   var form = findObj(formObj);
	for(i=0;i<form.elements.length; i++){
			obj=form[i]; 
			if( obj.JSObjName != null){
				eapObj = this.getEAPObj(obj);
				if(eapObj != null) {
					eapObj.onReady();      								
				}
			}
	}	
}


function findObj(n, d) { 
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=findObj(n,d.layers[i].document);
  if(!x && document.getElementById) x=document.getElementById(n); return x;
}