
function selectall(obj)
{
	if(null ==obj) return false;
 var num=obj.length;
  if(document.all("checkall").checked){
    if(num==null){obj.checked=true;}
	else{
       for (var i=0;i<num;i++)
	     obj[i].checked=true;
	}
	}
  else
  {
   if(num==null){obj.checked=false;}
   else{
     for (var i=0;i<num;i++)
      obj[i].checked=false;
  }
  }
}

//�ж��Ƿ�ȫѡ��������������checkboxȫ��ѡ�У����޸�checkall��״̬
function checkItem(checkListName, controlCheck)
{
 var aryChecked = document.all(checkListName);
 if (aryChecked != null)
 {
  if (aryChecked.length != null && aryChecked.length > 0)
  {
   var nCount = 0;
   for (var i = 0; i < aryChecked.length; i ++)
   {
    if (aryChecked[i].checked)
        nCount ++;
   }
   if (nCount == aryChecked.length) {


       controlCheck.checked = true;
	}  else{
       controlCheck.checked = false;
 	}
  }
  else
  {
   controlCheck.checked = aryChecked.checked;
  }
 }
}
//ֻ��ѡ��һ��
function editObj(name) {
    var checkObj = document.all(name);
    if (checkObj) {
      if (checkObj.length) {
        for (var i=0,j=0;i<checkObj.length && j<=2;i++) {
          if (checkObj[i].checked) {
            j++;
          }
        }
        if (j > 1) {
          alert("ֻ��ѡ��һ������");
          return false;
        } else if (j == 0) {
          alert("��ѡ�����");
          return false;
        }
      } else {
        if (! checkObj.checked) {
          alert("��ѡ�����");
          return false;
        }
      }
    } else {
      alert("��ǰû��ѡ�еĶ���");
      return false;
    }

	return true;
  }

//������ѡ��
function delObj(name) {
    var checkObj = document.all(name);   //��ѡ�����
    if (checkObj) {
      if (checkObj.length) {
        for (var i=0,j=0;i<checkObj.length;i++) {
          if (checkObj[i].checked) {
            j++;
            break;
          }
        }
        if (j == 0) {
          alert("��ѡ��Ҫɾ���Ķ���");
          return false;
        }
      } else {
        if (!checkObj.checked) {
          alert("��ѡ��Ҫɾ���Ķ���");
          return false;
        }
      }
    } else {
      alert("��ǰû�п�ɾ���Ķ���");
      return false;
    }
     return true;
  }
//�س�--�ύ
function aaa() {
    if (event.keyCode == 13) {
        gotoPage();
		event.keyCode = 9;
    }
}
//����ҳ��Ԫ�ص���ʽ
function setClass(eleName, clsName) {
    document.all(eleName).className = clsName;
}


//�õ����ϵ�����Ԫ�ص�ֵ
function getAlldata(obj){
    var data = "";
    for (i=0; i<obj.length; i++ ){
        if ( obj(i).type != "submit" && obj(i).type != "reset" && obj(i).type != "button"){
            if( obj(i).type == "select-multiple"){
                for(j=0; j<obj(i).length;j++){
                    if (obj(i).options[j].selected ){
                        data = data+obj(i).id+"="+replaceStr(obj(i).options[j].value)+"&";
                    }
                }
            }else if(obj(i).type =="radio" || obj(i).type =="checkbox"){
                if (obj(i).checked){
                    data=data+obj(i).id+"="+replaceStr(obj(i).value)+"&";
                }
            }else{
                data=data+obj(i).id+"="+replaceStr(obj(i).value)+"&";
            }
        }
    }
    return data;
}

function replaceStr(str)
{
	str = str.replace(/%/g,"%25");
	str = str.replace(/&/g,"%26");
    str = str.replace(/\\/g,"&#92;");
    str = str.replace(/</g,"&#60;");
    str = str.replace(/>/g,"&#62;");
    str = str.replace(/\"/g,"&#34;");
    str = str.replace(/ /g,"&nbsp;");

    return str;
}

/**�õ���ǰ�е�����
*@param obj object
*/
function getRowData(obj){
	//get the parent element(TD)
	var obj_col=obj.parentElement;
	if (obj_col==null)
	{
		return;
	}
	//get the parent element again(TR)
	var obj_row=obj_col.parentElement;
	if (obj_row==null)
	{
		return;
	}
	//keep the data of the current line
	var rowData=new Array();
	var iLength=obj_row.childNodes.length;
	var iNum=0;
	for (var i=0;i<iLength;i++)
	{
		//rowData[iNum]=obj_row.childNodes(i).childNodes(0).value;
		rowData[iNum]=obj_row.childNodes(i).innerText;
		iNum++;
	}
	return rowData;
}

//�õ�Ҫ�༭�����ݣ�1����name:checkbox��name
function getEditData(name){
  var obj=document.all.tags("input");
  var rowdata = null;
	var str="";
	for(var i=0;i<obj.length;i++)
	{
      if(obj[i].type == "checkbox"){

        if( obj[i].name == name && obj[i].checked){
      		rowdata = getRowData(obj[i]);
              break;
        }
      }
	}
  return rowdata;
}


//����ģʽ�Ի������̨���� add by chenkl
function MM_openModalWindow(requestURL,winName,width,height) { //v2.0
  newwin = window.showModalDialog(requestURL,winName,'dialogWidth:'+width+'px;dialogHeight:'+height+'px;help:no;status:no');
  //newwin.moveTo((screen.width-width)/2,(screen.height-height)/2);

}

function executeReqAction(requestAction){
if((null != requestAction) && ("" != requestAction )){
 		var reqAction = "/" + unieap.WEB_APP_NAME + requestAction
  		var XMLReq = new ActiveXObject("Microsoft.XMLHTTP");
  		XMLReq.open("POST", reqAction, false);
  		XMLReq.send("");
  	}
}

function openModalWindow(requestAction,requestURL,winName,width,height) { //v2.0

	executeReqAction(requestAction);
  MM_openModalWindow(requestURL,winName,width,height);
  //newwin.moveTo((screen.width-width)/2,(screen.height-height)/2);

}

//ȥ���ַ�����ǰ��ո�
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
//�õ�������ֵ����ƴװ��name=value&name1=value1����ʽ
function getInputData(){
	 var obj=document.all.tags("input");
	var str="";
	for(var i=0;i<obj.length;i++)
	{
      if(obj[i].type == "text"){
		str = str + "&" + replaceStr(obj[i].name) + "=" + replaceStr(obj[i].value);
      }
	}
	return str;
}

