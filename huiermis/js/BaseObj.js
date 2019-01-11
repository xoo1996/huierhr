
 

function BaseObj(editerObj){
	this.edtObj = editerObj;
	
	this.enterToTab = BSO_enterToTab;
	this.commonCheck = BSO_commonCheck;
	this.checkEmpty = BSO_checkEmpty;
	this.checkMinLength = BSO_checkMinLength;
	this.checkFixLength = BSO_checkFixLength;
	this.getObjValue = BSO_getObjValue;
	this.isEmpty = BSO_isEmpty;
	this.onReady = BSO_onContentReady;
	this.eventBand = BSO_eventBand;
}

function BSO_enterToTab(){
        if(event.srcElement.type != 'button' && event.srcElement.type != 'textarea' && event.keyCode == 13){
                event.keyCode = 9;
        }

}

function BSO_commonCheck(){
	if( !this.checkEmpty() || !this.checkMinLength() || !this.checkFixLength())	return false;
	return true;
}

function BSO_checkMinLength(){
	var minLength = this.edtObj.getAttribute("minLength");
	if(minLength == null) return true;
	if(!isNaN(parseInt(minLength,10))){
		var value = this.getObjValue();
		var valueLength = bitLength(value);
		minLength = parseInt(minLength,10);
		if(minLength > valueLength){
			showAlert("长度最小为"+minLength+"位,请重新输入！\n注意：一个汉字占"+lemis.bitsOfOneChinese+"位",this.edtObj,true);
			return false;
		}
	}
	return true;
}

function BSO_checkFixLength(){
	var fixLength = this.edtObj.getAttribute("fixLength");
	if(fixLength == null) return true;
	if(!isNaN(parseInt(fixLength,10))){
		var value = this.getObjValue();
		var valueLength =bitLength(value);
		fixLength = parseInt(fixLength,10);
		if(fixLength != valueLength){
			showAlert("长度必须为"+fixLength+"位,请重新输入！\n注意：一个汉字占"+lemis.bitsOfOneChinese+"位",this.edtObj,true);
			return false;
		}
	}
	return true;
}

function BSO_checkEmpty(){

	if(lowerCase(this.edtObj.getAttribute("isNullable")) == "false" ){
		if(this.isEmpty()){
			var mes = "不能为空，请重新输入！";
			if(lowerCase(this.edtObj.tagName) == "select")	mes = "不能为空，请选择！";
			showAlert(mes,this.edtObj,true);
			return false;
		}
	}
	return true;
}

function BSO_isEmpty(){
	if(this.getObjValue() == null)	return true;
	return false;
}

function BSO_getObjValue(){
  var value = this.edtObj.value;
  return value == null || value == "" ? null : value;
}


function BSO_onContentReady(){
   this.edtObj.islemis=true;
   
   var fixLength = this.edtObj.getAttribute("fixLength");
   if( fixLength != null && fixLength != "undenfied" && fixLength != "")  this.edtObj.maxLength = fixLength;
   
   if(this.edtObj.getAttribute("prompt") == "undenfied"){
   		this.edtObj.prompt = this.edtObj.name;
   }
  var isReadOnly = this.edtObj.getAttribute("isreadonly");
  if(isReadOnly != null && isReadOnly.toUpperCase() == "TRUE"){
        this.edtObj.disabled = true;
       
  }
  if(this.edtObj.disabled) this.edtObj.className = "NEUDwReadOnly";

}
function BSO_eventBand(eventID,eventHandler){
	if("onkeydown" == eventID.toLowerCase()){
	 	var handler = "eapObjsMgr.getEAPObj(this).getBaseObj().enterToTab();eapObjsMgr.getEAPObj(this)."+eventHandler+";";
	 	this.edtObj.attachEvent("onkeydown",handler);
	 	return;
	}
	alert(eventID+","+eventHandler);
	alert(this.edtObj);
	
	this.edtObj.attachEvent(eventID,"alert(this);");
	alert(eventID+","+eventHandler);
}
	


function upperCase(arg){
	return makeCase(arg,"UPPER");
}

function lowerCase(arg){
	return makeCase(arg,"LOWER");
}

function showAlert(message,obj,isFocus){
	var mes = "";
	var flag = false;
	if(obj){
		if(obj.prompt)
			mes = "["+obj.prompt+"]";
		if(isFocus) flag = true;
	}
	alert(mes + message);
	if(flag)	try{obj.focus();}catch(e){}
}

function makeCase(arg,kind){
	if(arg == null)	return "";
	var str = ""+arg;
	if(kind == "UPPER")
		return str.toUpperCase();
	return str.toLowerCase();
}

function bitLength(str){
	if(str==null || str == "") return 0;
	var len = 0;
	for(var i=0; i < str.length; i++){
		
		if(str.substring(i,i+1).charCodeAt(0) < 0x4e00){
			len ++;
		 	continue;
		}
		
		len += lemis.bitsOfOneChinese;
	}
	return len;
}