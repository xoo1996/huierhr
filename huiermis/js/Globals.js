function LEMIS(){
    this.WEB_APP_NAME  = "huiermis";
}
var lemis = new LEMIS();

/**
 * @file globals.js
 * @desription
 */

// �õ����ϵ�����Ԫ�ص�ֵ
function getAlldata(obj){
    var row="";
    var flag=0;
    if(obj.length){
        for (i=0; i<obj.length; i++ ){
            if ( obj(i).type != "submit" && obj(i).type != "reset" && obj(i).type != "button"){
                if(obj(i).type =="radio" || obj(i).type =="checkbox"){
                    if (obj(i).checked){
                        flag=1;
                    }else{
                        flag=0;
                    }
                }else{
                    if(flag==1){
                        var name=obj(i).name;
                        var value=obj(i).value;
                        row=row+name+"="+value+"&";
                    }
                }
            }
        }
    }else{
      if ( obj.type != "submit" && obj.type != "reset" && obj.type != "button"){
                if(obj.type =="radio" || obj.type =="checkbox"){
                    if (obj.checked){
                        flag=1;
                    }else{
                        flag=0;
                    }
                }else{
                    if(flag==1){
                        var name=obj.name;
                        var value=obj.value;
                        row=row+name+"="+value+"&";
                    }
                }
            }
    }
    // alert(row);
    return row;
}

function getAlldatas(obj1){
    var row="";
    var flag=0;
/*    alert("obj1(0).type" + obj1(0).type);
    alert("obj1.length" + obj1.length);*/
    if( typeof obj1(0).type == "undefined" ){
    	for(j=0;j<obj1.length;j++){
    		var obj = obj1(j);
        	/*	alert("obj.type" + obj.type);
        		alert("obj.length" + obj.length);*/
        		 if(obj.length){
     		        for (i=0; i<obj.length; i++ ){
     		        	/*alert(obj(i).type);*/
     		            if ( obj(i).type != "submit" && obj(i).type != "reset" && obj(i).type != "button"){
     		                if(obj(i).type =="radio" || obj(i).type =="checkbox"){
     		                    if (obj(i).checked){
     		                        flag=1;
     		                    }else{
     		                        flag=0;
     		                    }
     		                }else{
     		                    if(flag==1){
     		                        var name=obj(i).name;
     		                        var value=obj(i).value;
     		                        row=row+name+"="+value+"&";
     		                    }
     		                }
     		            }
     		        }
     		   }else{
     		      if ( obj.type != "submit" && obj.type != "reset" && obj.type != "button"){
    	                if(obj.type =="radio" || obj.type =="checkbox"){
    	                    if (obj.checked){
    	                        flag=1;
    	                    }else{
    	                        flag=0;
    	                    }
    	                }else{
    	                    if(flag==1){
    	                        var name=obj.name;
    	                        var value=obj.value;
    	                        row=row+name+"="+value+"&";
    	                    }
    	                }
     		     }
     		 }
    	}
   
    }else{
    	
    }
    // alert(row);
    return row;
}

// ���ð�ť��js
function clearForm(obj,aryDef){
  if(obj.length){
    for(var i = 0; i < obj.length; i++){
        if ( obj(i).type != "submit" && obj(i).type != "reset" && obj(i).type != "button" ){
          if(obj(i).type == "hidden"){
            if(obj(i).name == "aaa020" || obj(i).name == "aca111" ||  obj(i).name == "cce001" ){
                obj(i).value='';
            }
          }else{
          	if(!isInAry(obj(i).name,aryDef))
	            obj(i).value='';
          }
        }
    }
  }else{
    if (obj.type != "submit" && obj.type != "reset" && obj.type != "button" ){
            if(obj.type == "hidden"){
                if(obj.name == "aaa020" || obj.name == "aca111"){
                    obj.value='';
                }
            }else{
             	if(!isInAry(obj(i).name,aryDef))
		            obj.value='';
                
            }
    }
  }
}

function isInAry(name,ary){
	for(var i = 0; i < ary.length; i++){
		if(name == ary[i]){
			return true;
			break;
		}
	}
	return false;
}
function resetForm(obj){
	clearForm(obj,new Array());
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
/**
 * У��form���ύ
 */
function checkValue(formObj){
    var obj;
    var form = formObj;
    for(i=0;i<form.elements.length; i++){

         obj=form[i];

            if(obj.isEAF=="true"){

            if(obj.type != "submit" && obj.type != "reset" && obj.type != "button" )
                if(!validate(obj)){
                    return false;
                 }
         }
    }
    return true;
}
/**
 * �ؼ�У��
 */
function validate(obj){
  if(!obj.validate()){
    try{
        obj.focus();
    }catch(e){
    }
    return false;
  }
  return true;
}
/**
 * �����ύ��ʱ�ڵ���CheckValue֮ǰ���ô˺���
 */
function preCheckForBatch(){
   var obj = document.all("tableform");
   var flag = 0;
    if(obj.length){
        for (i=0; i<obj.length; i++ ){
            if ( obj(i).type != "submit" && obj(i).type != "reset" && obj(i).type != "button"){
                if(obj(i).type =="radio" || obj(i).type =="checkbox"){
                    if (obj(i).checked){
                        flag=1;
                    }else{
                        flag=0;
                    }
                }else{
                    if(1 == flag && "true" == obj(i).isEAF){
                         if(!validate(obj(i))){
                           return false;
                         }
                    }
                }
            }
        }
    }else{
      if ( obj.type != "submit" && obj.type != "reset" && obj.type != "button"){
                if(obj.type =="radio" || obj.type =="checkbox"){
                    if (obj.checked){
                        flag=1;
                    }else{
                        flag=0;
                    }
                }else{
                    if(1 == flag && "true" == obj.isEAF){
                         if(!validate(obj)){
                           return false;
                         }
                    }
                }
            }
    }
    return true;
}

/**
 * ���������ύ
 */
function newBatch(){
   var obj = document.all("tableform");
    if(obj.length){
        for (i=0; i<obj.length; i++ ){
            if ( obj(i).type != "submit" && obj(i).type != "reset" && obj(i).type != "button"){
                if(obj(i).type =="radio" || obj(i).type =="checkbox"){
                    if (obj(i).checked){
                        obj(i).checked = false;
                    }
                }else{
                  if("hidden" != obj(i).type){
                    obj(i).value='';
                  }
                }
            }
        }
    }else{
      if ( obj.type != "submit" && obj.type != "reset" && obj.type != "button"){
                if(obj.type =="radio" || obj.type =="checkbox"){
                    if (obj.checked){
                        obj.checked = false;
                    }
                }else{
                   if("hidden" != obj.type){
                    obj.value='';
                  }
                }
      }
    }
}
/**
 * ҳ����Ի�
 */
function page_init()
{

}
function selectall(obj)
{
    if(null ==obj) return false;
 var num=obj.length;
  if(document.all("checkall").checked){
    if(num==null){obj.checked=true;}
    else{
       for (var i=0;i<num;i++){
         if(!obj[i].disabled){
            obj[i].checked=true;
         }
       }
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
// �ж��Ƿ�ȫѡ��������������checkboxȫ��ѡ�У����޸�checkall��״̬
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

// ֻ��ѡ��һ��
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
          alert("ֻ��ѡ��һ��ҵ�����ݣ�");
          return false;
        } else if (j == 0) {
          alert("��ѡ��ҵ�����ݣ�");
          return false;
        }
      } else {
        if (! checkObj.checked) {
          alert("��ѡ��ҵ������");
          return false;
        }
      }
    } else {
      alert("��ǰû�пɲ���ҵ�����ݣ�");
      return false;
    }

    return true;
  }


// ������ѡ��
function delObj(name) {
    var checkObj = document.all(name);   // ��ѡ�����
    if (checkObj) {
      if (checkObj.length) {
        for (var i=0,j=0;i<checkObj.length;i++) {
          if (checkObj[i].checked) {
            j++;
            break;
          }
        }
        if (j == 0) {
          alert("��ѡ��ҵ�����ݣ�");
          return false;
        }
      } else {
        if (!checkObj.checked) {
          alert("��ѡ��ҵ�����ݣ�");
          return false;
        }
      }
    } else {
      alert("��ǰû�пɲ�����ҵ�����ݣ�");
      return false;
    }
     return true;
  }

/**
 * �õ���ǰ�е�����
 * 
 * @param obj
 *            object
 */
function getRowData(obj){
    // get the parent element(TD)
    var obj_col=obj.parentElement;
    if (obj_col==null)
    {
        return;
    }
    // get the parent element again(TR)
    var obj_row=obj_col.parentElement;
    if (obj_row==null)
    {
        return;
    }
    // keep the data of the current line
    var rowData=new Array();
    var iLength=obj_row.childNodes.length;
    var iNum=0;
    for (var i=0;i<iLength;i++)
    {
        // rowData[iNum]=obj_row.childNodes(i).childNodes(0).value;
        rowData[iNum]=obj_row.childNodes(i).innerText;
        iNum++;
    }
    return rowData;
}

// �õ�Ҫ�༭�����ݣ�1����name:checkbox��name
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


// �õ�ѡ�е����ݣ�1����name:ѡ������,ĳ���ֶε�����
function getSelectData(obj,inputname){
    var row="";
    var flag=0;
    if(obj.length){
        for (i=0; i<obj.length; i++ ){
            if ( obj(i).type != "submit" && obj(i).type != "reset" && obj(i).type != "button"){
                if(obj(i).type =="radio" || obj(i).type =="checkbox"){
                    if (obj(i).checked){
                        flag=1;
                    }else{
                        flag=0;
                    }
                }else{
                    if(flag==1){
                        var name=obj(i).name;
                        var value=obj(i).value;
                        if(name==inputname)
                        {
                        row=value;
                        break;
                        }
                    }
                }
            }
        }
    }else{
      if ( obj.type != "submit" && obj.type != "reset" && obj.type != "button"){
                if(obj.type =="radio" || obj.type =="checkbox"){
                    if (obj.checked){
                        flag=1;
                    }else{
                        flag=0;
                    }
                }else{
                    if(flag==1){
                        var name=obj.name;
                        var value=obj.value;
                        if(name==inputname)
                        {
                        row=value;
                       
                        }
                    }
                }
            }
    }
    // alert(row);
    return row;
}

/**
 * У��ͬ����radio�Ƿ��б�ѡ��
 * 
 * @param objRadio
 *            ͬ����radio
 * @return true/false/null �б�ѡ��/û�б�ѡ��/���󲻴���
 * @author zhouzm
 */
function checkRadio(objRadio){
    if(objRadio==undefined)
    return null;
    if(objRadio.length==undefined){
        return objRadio.checked;
    }else{
        for(var i=0;i<objRadio.length;i++){
         if(objRadio[i].checked)
            return true;
        }
        return false;
    }
}

/**
 * У��ͬ����radio�Ƿ��б�ѡ��
 * 
 * @param objRadio
 *            ͬ����radio
 * @return true/false/null �б�ѡ��/û�б�ѡ��/���󲻴���
 * @author zhouzm
 */
function checkRadio(objRadio){
    if(objRadio==undefined)
    return true;
    if(objRadio.length==undefined){
        return objRadio.checked;
    }else{
        for(var i=0;i<objRadio.length;i++){
         if(objRadio[i].checked)
            return true;
        }
        return false;
    }
}


/*
 * forDight(Dight,How):��ֵ��ʽ��������DightҪ ��ʽ���� ���֣�HowҪ������С��λ���� ��������
 */  
function forDight(Dight,How)  
{  
     Dight  =  Math.round(Dight*Math.pow(10,How))/Math.pow(10,How);  
     return  Dight;  
}

// ====================================����
// js====================================================
// ==================================================== �����趨����
// =======================================================
var bMoveable=true;     // ���������Ƿ�����϶�
var regionTree=false;
var workTypeTree=false;
var ssjdTree=false;   // ���������ֵ�������by���鳬 2004��4��26��
var cylbTree=false;   // �����ҵ��������by�ֿ�
var calendar=false;
var _VersionInfo="Version:2.0&#13;2.0����:walkingpoison&#13;1.0����: F.R.Huang(meizz)&#13;MAIL: meizz@hzcnc.com"    // �汾��Ϣ

// ==================================================== WEB ҳ����ʾ����
// =====================================================
var strFrame;       // ����������HTML����
document.writeln('<iframe id=meizzDateLayer Author=wayx frameborder=0 style="position: absolute; width: 144; height: 211; z-index: 9998; display: none"></iframe>');
strFrame='<style>';
strFrame+='INPUT.button{BORDER-RIGHT: #8FB1F3 1px solid;BORDER-TOP: #8FB1F3 1px solid;BORDER-LEFT: #8FB1F3 1px solid;';
strFrame+='BORDER-BOTTOM: #8FB1F3 1px solid;BACKGROUND-COLOR: #fff8ec;font-family:����;}';
strFrame+='TD{FONT-SIZE: 9pt;font-family:����;}';
strFrame+='</style>';
strFrame+='<scr' + 'ipt>';
strFrame+='var datelayerx,datelayery;   /*��������ؼ������λ��*/';
strFrame+='var bDrag;   /*����Ƿ�ʼ�϶�*/';
strFrame+='function document.onmousemove()  /*������ƶ��¼��У������ʼ�϶����������ƶ�����*/';
strFrame+='{if(bDrag && window.event.button==1)';
strFrame+=' {var DateLayer=parent.document.all.meizzDateLayer.style;';
strFrame+='     DateLayer.posLeft += window.event.clientX-datelayerx;/*����ÿ���ƶ��Ժ����λ�ö��ָ�Ϊ��ʼ��λ�ã����д����div�в�ͬ*/';
strFrame+='     DateLayer.posTop += window.event.clientY-datelayery;}}';
strFrame+='function DragStart()     /*��ʼ�����϶�*/';
strFrame+='{var DateLayer=parent.document.all.meizzDateLayer.style;';
strFrame+=' datelayerx=window.event.clientX;';
strFrame+=' datelayery=window.event.clientY;';
strFrame+=' bDrag=true;}';
strFrame+='function DragEnd(){      /*���������϶�*/';
strFrame+=' bDrag=false;}';
strFrame+='</scr' + 'ipt>';
strFrame+='<div style="z-index:9999;position: absolute; left:0; top:0;" onselectstart="return false"><span id=tmpSelectYearLayer Author=wayx style="z-index: 9999;position: absolute;top: 3; left: 19;display: none"></span>';
strFrame+='<span id=tmpSelectMonthLayer Author=wayx style="z-index: 9999;position: absolute;top: 3; left: 78;display: none"></span>';
strFrame+='<table border=1 cellspacing=0 cellpadding=0 width=142 height=160 bordercolor=#8FB1F3 bgcolor=#8FB1F3 Author="wayx">';
strFrame+='  <tr Author="wayx"><td width=142 height=23 Author="wayx" bgcolor=#FFFFFF><table border=0 cellspacing=1 cellpadding=0 width=140 Author="wayx" height=23>';
strFrame+='      <tr align=center Author="wayx"><td width=16 align=center bgcolor=#8FB1F3 style="font-size:12px;cursor: hand;color: #ffffff" ';
strFrame+='        onclick="parent.meizzPrevM()" title="��ǰ�� 1 ��" Author=meizz><b Author=meizz>&lt;</b>';
strFrame+='        </td><td width=60 align=center style="font-size:12px;cursor:default" Author=meizz ';
strFrame+='onmouseover="style.backgroundColor=\'#FFD700\'" onmouseout="style.backgroundColor=\'white\'" ';
strFrame+='onclick="parent.tmpSelectYearInnerHTML(this.innerText.substring(0,4))" title="�������ѡ�����"><span Author=meizz id=meizzYearHead></span></td>';
strFrame+='<td width=48 align=center style="font-size:12px;cursor:default" Author=meizz onmouseover="style.backgroundColor=\'#FFD700\'" ';
strFrame+=' onmouseout="style.backgroundColor=\'white\'" onclick="parent.tmpSelectMonthInnerHTML(this.innerText.length==3?this.innerText.substring(0,1):this.innerText.substring(0,2))"';
strFrame+='        title="�������ѡ���·�"><span id=meizzMonthHead Author=meizz></span></td>';
strFrame+='        <td width=16 bgcolor=#8FB1F3 align=center style="font-size:12px;cursor: hand;color: #ffffff" ';
strFrame+='         onclick="parent.meizzNextM()" title="��� 1 ��" Author=meizz><b Author=meizz>&gt;</b></td></tr>';
strFrame+='    </table></td></tr>';
strFrame+='  <tr Author="wayx"><td width=142 height=18 Author="wayx">';
strFrame+='<table border=1 cellspacing=0 cellpadding=0 bgcolor=#8FB1F3 ' + (bMoveable? 'onmousedown="DragStart()" onmouseup="DragEnd()"':'');
strFrame+=' BORDERCOLORLIGHT=#8FB1F3 BORDERCOLORDARK=#FFFFFF width=140 height=20 Author="wayx" style="cursor:' + (bMoveable ? 'move':'default') + '">';
strFrame+='<tr Author="wayx" align=center valign=bottom><td style="font-size:12px;color:#FFFFFF" Author=meizz>��</td>';
strFrame+='<td style="font-size:12px;color:#FFFFFF" Author=meizz>һ</td><td style="font-size:12px;color:#FFFFFF" Author=meizz>��</td>';
strFrame+='<td style="font-size:12px;color:#FFFFFF" Author=meizz>��</td><td style="font-size:12px;color:#FFFFFF" Author=meizz>��</td>';
strFrame+='<td style="font-size:12px;color:#FFFFFF" Author=meizz>��</td><td style="font-size:12px;color:#FFFFFF" Author=meizz>��</td></tr>';
strFrame+='</table></td></tr><!-- Author:F.R.Huang(meizz) http://www.meizz.com/ mail: meizz@hzcnc.com 2002-10-8 -->';
strFrame+='  <tr Author="wayx"><td width=142 height=120 Author="wayx">';
strFrame+='    <table border=1 cellspacing=2 cellpadding=0 BORDERCOLORLIGHT=#8FB1F3 BORDERCOLORDARK=#FFFFFF bgcolor=#fff8ec width=140 height=120 Author="wayx">';
var n=0; for (j=0;j<5;j++){ strFrame+= ' <tr align=center Author="wayx">'; for (i=0;i<7;i++){
strFrame+='<td width=20 height=20 id=meizzDay'+n+' style="font-size:12px" Author=meizz onclick=parent.meizzDayClick(this.innerText,0)></td>';n++;}
strFrame+='</tr>';}
strFrame+='      <tr align=center Author="wayx">';
for (i=35;i<39;i++)strFrame+='<td width=20 height=20 id=meizzDay'+i+' style="font-size:12px" Author=wayx onclick="parent.meizzDayClick(this.innerText,0)"></td>';
strFrame+='        <td colspan=3 align=right Author=meizz><span onclick=parent.closeLayer() style="font-size:12px;cursor: hand"';
strFrame+='         Author=meizz title="' + _VersionInfo + '"><u>�ر�</u></span>&nbsp;</td></tr>';
strFrame+='    </table></td></tr><tr Author="wayx"><td Author="wayx">';
strFrame+='        <table border=0 cellspacing=1 cellpadding=0 width=100% Author="wayx" bgcolor=#FFFFFF>';
strFrame+='          <tr Author="wayx"><td Author=meizz align=left><input Author=meizz type=button class=button value="<<" title="��ǰ�� 1 ��" onclick="parent.meizzPrevY()" ';
strFrame+='             onfocus="this.blur()" style="font-size: 12px; height: 20px"><input Author=meizz class=button title="��ǰ�� 1 ��" type=button ';
strFrame+='             value="< " onclick="parent.meizzPrevM()" onfocus="this.blur()" style="font-size: 12px; height: 20px"></td><td ';
strFrame+='             Author=meizz align=center><input Author=meizz type=button class=button value=���� onclick="parent.meizzToday()" ';
strFrame+='             onfocus="this.blur()" title="��ǰ����" style="font-size: 12px; height: 20px; cursor:hand"></td><td ';
strFrame+='             Author=meizz align=right><input Author=meizz type=button class=button value=" >" onclick="parent.meizzNextM()" ';
strFrame+='             onfocus="this.blur()" title="��� 1 ��" class=button style="font-size: 12px; height: 20px"><input ';
strFrame+='             Author=meizz type=button class=button value=">>" title="��� 1 ��" onclick="parent.meizzNextY()"';
strFrame+='             onfocus="this.blur()" style="font-size: 12px; height: 20px"></td>';
strFrame+='</tr></table></td></tr></table></div>';

window.frames.meizzDateLayer.document.writeln(strFrame);
window.frames.meizzDateLayer.document.close();      // ���ie������������������

// ==================================================== WEB ҳ����ʾ����

var outObject=null;
var outButton;      // ����İ�ť
var outDate="";     // ��Ŷ��������
var odatelayer=window.frames.meizzDateLayer.document.all;       // �����������
function setday(tt,obj) // ��������
{
    if (arguments.length >  2){alert("�Բ��𣡴��뱾�ؼ��Ĳ���̫�࣡");return;}
    if (arguments.length == 0){alert("�Բ�����û�д��ر��ؼ��κβ�����");return;}
   calendar=true;       // ��ʾ�Ѿ���������
   lemisTree=false;     // lemisTree
   toChangeObject=null;
// alert("lemisTree:"+lemisTree);
// alert("calendar:"+calendar);
    var dads  = document.all.meizzDateLayer.style;
    var th = tt;
    var ttop  = tt.offsetTop;     // TT�ؼ��Ķ�λ���
    var thei  = tt.clientHeight;  // TT�ؼ�����ĸ�
    var tleft = tt.offsetLeft;    // TT�ؼ��Ķ�λ���
    var ttyp  = tt.type;          // TT�ؼ�������
    while (tt = tt.offsetParent){ttop+=tt.offsetTop; tleft+=tt.offsetLeft;}
    dads.top  = (ttyp=="image")? ttop+thei : ttop+thei+6;
    dads.left = tleft;
    outObject = (arguments.length == 1) ? th : obj;
    outButton = (arguments.length == 1) ? null : th;    // �趨�ⲿ����İ�ť
    // ���ݵ�ǰ������������ʾ����������
    var reg = /^(\d+)-(\d{1,2})-(\d{1,2})$/;
    var r = outObject.value.match(reg);
    if(r!=null){
        r[2]=r[2]-1;
        var d= new Date(r[1], r[2],r[3]);
        if(d.getFullYear()==r[1] && d.getMonth()==r[2] && d.getDate()==r[3]){
            outDate=d;      // �����ⲿ���������
        }
        else outDate="";
            meizzSetDay(r[1],r[2]+1);
    }
    else{
        outDate="";
        meizzSetDay(new Date().getFullYear(), new Date().getMonth() + 1);
    }
    dads.display = '';

    event.returnValue=false;
    if(workTypeTree)
    	workTypeTree_close();
    if(ssjdTree)
    	ssjdTree_close();
    if(cylbTree)
    	cylbTree_close();
    if(regionTree)
    	regionTree_close();
	calendar=true;       // ��ʾ�Ѿ���������
	
}

// ��setday()������JS������������obj�������ڣ����Ӽ�diffdays�죬��obj2chg��ʾ�Ӽ��������
// by Tony, 2005-06
var toChangeObject=null;
var diff;
function setandchange(tt,obj,obj2chg,diffdays) // ��������
{
    if (arguments.length >  4){alert("�Բ��𣡴��뱾�ؼ��Ĳ���̫�࣡");return;}
    if (arguments.length == 0){alert("�Բ�����û�д��ر��ؼ��κβ�����");return;}
   calendar=true;       // ��ʾ�Ѿ���������
   lemisTree=false;     // lemisTree
// alert("lemisTree:"+lemisTree);
// alert("calendar:"+calendar);
    var dads  = document.all.meizzDateLayer.style;
    var th = tt;
    var ttop  = tt.offsetTop;     // TT�ؼ��Ķ�λ���
    var thei  = tt.clientHeight;  // TT�ؼ�����ĸ�
    var tleft = tt.offsetLeft;    // TT�ؼ��Ķ�λ���
    var ttyp  = tt.type;          // TT�ؼ�������
    while (tt = tt.offsetParent){ttop+=tt.offsetTop; tleft+=tt.offsetLeft;}
    dads.top  = (ttyp=="image")? ttop+thei : ttop+thei+6;
    dads.left = tleft;
    outObject = obj;
    toChangeObject = obj2chg;
    diff = diffdays;
    outButton = (arguments.length == 1) ? null : th;    // �趨�ⲿ����İ�ť
    // ���ݵ�ǰ������������ʾ����������
    var reg = /^(\d+)-(\d{1,2})-(\d{1,2})$/;
    var r = outObject.value.match(reg);
    if(r!=null){
        r[2]=r[2]-1;
        var d= new Date(r[1], r[2],r[3]);
        if(d.getFullYear()==r[1] && d.getMonth()==r[2] && d.getDate()==r[3]){
            outDate=d;      // �����ⲿ���������
        }
        else outDate="";
            meizzSetDay(r[1],r[2]+1);
    }
    else{
        outDate="";
        meizzSetDay(new Date().getFullYear(), new Date().getMonth() + 1);
    }
    dads.display = '';

    event.returnValue=false;
   
}

var MonHead = new Array(12);               // ����������ÿ���µ��������
    MonHead[0] = 31; MonHead[1] = 28; MonHead[2] = 31; MonHead[3] = 30; MonHead[4]  = 31; MonHead[5]  = 30;
    MonHead[6] = 31; MonHead[7] = 31; MonHead[8] = 30; MonHead[9] = 31; MonHead[10] = 30; MonHead[11] = 31;

var meizzTheYear=new Date().getFullYear(); // ������ı����ĳ�ʼֵ
var meizzTheMonth=new Date().getMonth()+1; // �����µı����ĳ�ʼֵ
var meizzWDay=new Array(39);               // ����д���ڵ�����

function document.onclick(){ // ������ʱ�رոÿؼ� //ie6�����������������л����㴦�����
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
      // ���ҳ�棬�ر������ֵ��� by���鳬 2004��4��26��
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
      if(cylbTree){
          if (srcElement.getAttribute("Author")!="zhansk" && srcElement != cylb_outObject1){
              regionTree=false;
              workTypeTree=false;
              ssjdTree=false;
              cylbTree=false;
              calendar=false;
              // cylbTree_close();
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

	function document.onkeyup()     // ��Esc���رգ��л�����ر�
  	{
    if (window.event.keyCode==27){
        if(outObject)outObject.blur();
        closeLayer();
    }
    else if(document.activeElement)
        if(document.activeElement.getAttribute("Author")==null && document.activeElement != outObject && document.activeElement != outButton)
       	{	
            closeLayer();
        }
  }

function meizzWriteHead(yy,mm)  // �� head ��д�뵱ǰ��������
  {
    odatelayer.meizzYearHead.innerText  = yy + " ��";
    odatelayer.meizzMonthHead.innerText = mm + " ��";
  }

function tmpSelectYearInnerHTML(strYear) // ��ݵ�������
{
  if (strYear.match(/\D/)!=null){alert("�����������������֣�");return;}
  var m = (strYear) ? strYear : new Date().getFullYear();
  if (m < 1000 || m > 9999) {alert("���ֵ���� 1000 �� 9999 ֮�䣡");return;}
  var n = m - 100;
  if (n < 1000) n = 1000;
  if (n + 26 > 9999) n = 9974;
  var s = "<select Author=meizz name=tmpSelectYear style='font-size: 12px' "
     s += "onblur='document.all.tmpSelectYearLayer.style.display=\"none\"' "
     s += "onchange='document.all.tmpSelectYearLayer.style.display=\"none\";"
     s += "parent.meizzTheYear = this.value; parent.meizzSetDay(parent.meizzTheYear,parent.meizzTheMonth)'>\r\n";
  var selectInnerHTML = s;
  for (var i = n; i < n + 150; i++)
  {
    if (i == m)
       {selectInnerHTML += "<option Author=wayx value='" + i + "' selected>" + i + "��" + "</option>\r\n";}
    else {selectInnerHTML += "<option Author=wayx value='" + i + "'>" + i + "��" + "</option>\r\n";}
  }
  selectInnerHTML += "</select>";
  odatelayer.tmpSelectYearLayer.style.display="";
  odatelayer.tmpSelectYearLayer.innerHTML = selectInnerHTML;
  odatelayer.tmpSelectYear.focus();
}

function tmpSelectMonthInnerHTML(strMonth) // �·ݵ�������
{
  if (strMonth.match(/\D/)!=null){alert("�·���������������֣�");return;}
  var m = (strMonth) ? strMonth : new Date().getMonth() + 1;
  var s = "<select Author=meizz name=tmpSelectMonth style='font-size: 12px' "
     s += "onblur='document.all.tmpSelectMonthLayer.style.display=\"none\"' "
     s += "onchange='document.all.tmpSelectMonthLayer.style.display=\"none\";"
     s += "parent.meizzTheMonth = this.value; parent.meizzSetDay(parent.meizzTheYear,parent.meizzTheMonth)'>\r\n";
  var selectInnerHTML = s;
  for (var i = 1; i < 13; i++)
  {
    if (i == m)
       {selectInnerHTML += "<option Author=wayx value='"+i+"' selected>"+i+"��"+"</option>\r\n";}
    else {selectInnerHTML += "<option Author=wayx value='"+i+"'>"+i+"��"+"</option>\r\n";}
  }
  selectInnerHTML += "</select>";
  odatelayer.tmpSelectMonthLayer.style.display="";
  odatelayer.tmpSelectMonthLayer.innerHTML = selectInnerHTML;
  odatelayer.tmpSelectMonth.focus();
}

function closeLayer()               // �����Ĺر�
{
    regionTree=false;
    workTypeTree=false;
    calendar=false;
    document.all.meizzDateLayer.style.display="none";
}

function IsPinYear(year)            // �ж��Ƿ���ƽ��
  {
    if (0==year%4&&((year%100!=0)||(year%400==0))) return true;else return false;
  }

function GetMonthCount(year,month)  // �������Ϊ29��
  {
    var c=MonHead[month-1];if((month==2)&&IsPinYear(year)) c++;return c;
  }

function GetDOW(day,month,year)     // ��ĳ������ڼ�
  {
    var dt=new Date(year,month-1,day).getDay()/7; return dt;
  }

function meizzPrevY()  // ��ǰ�� Year
  {
    if(meizzTheYear > 999 && meizzTheYear <10000){meizzTheYear--;}
    else{alert("��ݳ�����Χ��1000-9999����");}
    meizzSetDay(meizzTheYear,meizzTheMonth);
  }
function meizzNextY()  // ���� Year
  {
    if(meizzTheYear > 999 && meizzTheYear <10000){meizzTheYear++;}
    else{alert("��ݳ�����Χ��1000-9999����");}
    meizzSetDay(meizzTheYear,meizzTheMonth);
  }
function meizzToday()  // Today Button
  {
    var today;
    meizzTheYear = new Date().getFullYear();
    meizzTheMonth = new Date().getMonth()+1;
    today=new Date().getDate();
// if (meizzTheMonth < 10){meizzTheMonth = "0" + meizzTheMonth;}
// if (today < 10){today = "0" + today;}
// if(meizzTheMonth.length==1)meizzTheMonth="0"+meizzTheMonth;
// if(today.length==1)today="0"+today;
// parent.alert(meizzTheMonth);
// parent.alert(today);
    meizzSetDay(meizzTheYear,meizzTheMonth);
    if(outObject){
// if((meizzTheMonth < 10)and(!(today < 10))){
// outObject.value=meizzTheYear + "-0" + meizzTheMonth + "-" + today;
// }else if((!(meizzTheMonth < 10))and(today < 10)){
// outObject.value=meizzTheYear + "-" + meizzTheMonth + "-0" + today;
// }else if((meizzTheMonth < 10)and(today < 10)){
// outObject.value=meizzTheYear + "-0" + meizzTheMonth + "-0" + today;
// }else{
            if(meizzTheMonth < 10)
                meizzTheMonth = "0" + meizzTheMonth;
            if(today < 10)
                today = "0" + today;
            outObject.value=meizzTheYear + "-" + meizzTheMonth + "-" + today;
// }
    }
    closeLayer();
  }
function meizzPrevM()  // ��ǰ���·�
  {
    if(meizzTheMonth>1){meizzTheMonth--}else{meizzTheYear--;meizzTheMonth=12;}
    meizzSetDay(meizzTheYear,meizzTheMonth);
  }
function meizzNextM()  // �����·�
  {
    if(meizzTheMonth==12){meizzTheYear++;meizzTheMonth=1}else{meizzTheMonth++}
    meizzSetDay(meizzTheYear,meizzTheMonth);
  }


function meizzSetDay(yy,mm)   // ��Ҫ��д����**********
{
  meizzWriteHead(yy,mm);
  // ���õ�ǰ���µĹ�������Ϊ����ֵ
  meizzTheYear=yy;
  meizzTheMonth=mm;

  for (var i = 0; i < 39; i++){meizzWDay[i]=""};  // ����ʾ�������ȫ�����
  var day1 = 1,day2=1,firstday = new Date(yy,mm-1,1).getDay();  // ĳ�µ�һ������ڼ�
  for (i=0;i<firstday;i++)meizzWDay[i]=GetMonthCount(mm==1?yy-1:yy,mm==1?12:mm-1)-firstday+i+1  // �ϸ��µ������
  for (i = firstday; day1 < GetMonthCount(yy,mm)+1; i++){meizzWDay[i]=day1;day1++;}
  for (i=firstday+GetMonthCount(yy,mm);i<39;i++){meizzWDay[i]=day2;day2++}
  for (i = 0; i < 39; i++)
  { var da = eval("odatelayer.meizzDay"+i)     // ��д�µ�һ���µ�������������
    if (meizzWDay[i]!="")
      {
        // ��ʼ���߿�
        da.borderColorLight="#8FB1F3";
        da.borderColorDark="#FFFFFF";
        if(i<firstday)      // �ϸ��µĲ���
        {
            da.innerHTML="<b><font color=gray>" + meizzWDay[i] + "</font></b>";
            da.title=(mm==1?12:mm-1) +"��" + meizzWDay[i] + "��";
            da.onclick=Function("meizzDayClick(this.innerText,-1)");
            if(!outDate)
                da.style.backgroundColor = ((mm==1?yy-1:yy) == new Date().getFullYear() &&
                    (mm==1?12:mm-1) == new Date().getMonth()+1 && meizzWDay[i] == new Date().getDate()) ?
                     "#FFD700":"#e0e0e0";
            else
            {
                da.style.backgroundColor =((mm==1?yy-1:yy)==outDate.getFullYear() && (mm==1?12:mm-1)== outDate.getMonth() + 1 &&
                meizzWDay[i]==outDate.getDate())? "#00ffff" :
                (((mm==1?yy-1:yy) == new Date().getFullYear() && (mm==1?12:mm-1) == new Date().getMonth()+1 &&
                meizzWDay[i] == new Date().getDate()) ? "#FFD700":"#e0e0e0");
                // ��ѡ�е�������ʾΪ����ȥ
                if((mm==1?yy-1:yy)==outDate.getFullYear() && (mm==1?12:mm-1)== outDate.getMonth() + 1 &&
                meizzWDay[i]==outDate.getDate())
                {
                    da.borderColorLight="#FFFFFF";
                    da.borderColorDark="#8FB1F3";
                }
            }
        }
        else if (i>=firstday+GetMonthCount(yy,mm))      // �¸��µĲ���
        {
            da.innerHTML="<b><font color=gray>" + meizzWDay[i] + "</font></b>";
            da.title=(mm==12?1:mm+1) +"��" + meizzWDay[i] + "��";
            da.onclick=Function("meizzDayClick(this.innerText,1)");
            if(!outDate)
                da.style.backgroundColor = ((mm==12?yy+1:yy) == new Date().getFullYear() &&
                    (mm==12?1:mm+1) == new Date().getMonth()+1 && meizzWDay[i] == new Date().getDate()) ?
                     "#FFD700":"#e0e0e0";
            else
            {
                da.style.backgroundColor =((mm==12?yy+1:yy)==outDate.getFullYear() && (mm==12?1:mm+1)== outDate.getMonth() + 1 &&
                meizzWDay[i]==outDate.getDate())? "#00ffff" :
                (((mm==12?yy+1:yy) == new Date().getFullYear() && (mm==12?1:mm+1) == new Date().getMonth()+1 &&
                meizzWDay[i] == new Date().getDate()) ? "#FFD700":"#e0e0e0");
                // ��ѡ�е�������ʾΪ����ȥ
                if((mm==12?yy+1:yy)==outDate.getFullYear() && (mm==12?1:mm+1)== outDate.getMonth() + 1 &&
                meizzWDay[i]==outDate.getDate())
                {
                    da.borderColorLight="#FFFFFF";
                    da.borderColorDark="#8FB1F3";
                }
            }
        }
        else        // ���µĲ���
        {
            da.innerHTML="<b>" + meizzWDay[i] + "</b>";
            da.title=mm +"��" + meizzWDay[i] + "��";
            da.onclick=Function("meizzDayClick(this.innerText,0)");     // ��td����onclick�¼��Ĵ���
            // ����ǵ�ǰѡ������ڣ�����ʾ����ɫ�ı���������ǵ�ǰ���ڣ�����ʾ����ɫ����
            if(!outDate)
                da.style.backgroundColor = (yy == new Date().getFullYear() && mm == new Date().getMonth()+1 && meizzWDay[i] == new Date().getDate())?
                    "#FFD700":"#e0e0e0";
            else
            {
                da.style.backgroundColor =(yy==outDate.getFullYear() && mm== outDate.getMonth() + 1 && meizzWDay[i]==outDate.getDate())?
                    "#00ffff":((yy == new Date().getFullYear() && mm == new Date().getMonth()+1 && meizzWDay[i] == new Date().getDate())?
                    "#FFD700":"#e0e0e0");
                // ��ѡ�е�������ʾΪ����ȥ
                if(yy==outDate.getFullYear() && mm== outDate.getMonth() + 1 && meizzWDay[i]==outDate.getDate())
                {
                    da.borderColorLight="#FFFFFF";
                    da.borderColorDark="#8FB1F3";
                }
            }
        }
        da.style.cursor="hand"
      }
    else{da.innerHTML="";da.style.backgroundColor="";da.style.cursor="default"}
  }
}

// Modified by Tony Yu, 2005-06
// �Ӽ����ں���toChangeObject��������ʾ
function meizzDayClick(n,ex)  // �����ʾ��ѡȡ���ڣ������뺯��*************
{
  var yy=meizzTheYear;
  var mm = parseInt(meizzTheMonth)+ex;  // ex��ʾƫ����������ѡ���ϸ��·ݺ��¸��·ݵ�����
    // �ж��·ݣ������ж�Ӧ�Ĵ���
    if(mm<1){
        yy--;
        mm=12+mm;
    }
    else if(mm>12){
        yy++;
        mm=mm-12;
    }

  if (mm < 10){mm = "0" + mm;}
  if (outObject != null )
  {
    if (!n) {// outObject.value="";
      return;}
    if ( n < 10){n = "0" + n;}
    outObject.value= yy + "-" + mm + "-" + n ; // ע�����������������ĳ�����Ҫ�ĸ�ʽ

	if (toChangeObject != null)
		dayafter(outObject, toChangeObject, diff);

    closeLayer();
  }
  else {closeLayer(); alert("����Ҫ����Ŀؼ����󲢲����ڣ�");}
}

// Added by Tony Yu, 2005-06
// �Ӽ����ں���affectedObject��������ʾ
// dayObject:�༭�Ķ���
// affectedObject���������ں���ʾ�Ķ���
// diffBetweenDays���������ڵ�����
function dayafter(dayObject, affectedObject, diffBetweenDays){
  var str =  dayObject.value;;
  var reg1 = /^(\d{4,4})-(\d{2,2})-(\d{2,2})$/; 
  var r = str.match(reg1); 
  d= new Date(r[1], --r[2],r[3]*1+parseInt(diffBetweenDays)); 
  var iFullYear=d.getFullYear()
  var iMonth=parseInt(d.getMonth())+1;
  var iDate=parseInt(d.getDate());
  if(iMonth<10){
  	iMonth="0"+iMonth;
  }
  if(iDate<10){
  	iDate="0"+iDate;
  }
  var FullDate=iFullYear+"-"+iMonth+"-"+iDate;
  affectedObject.value = FullDate;
}

// ====================================punblic
// js====================================================
/**
 * �ýű��ļ��ṩһЩ���õĺ���
 * 
 * @copyright bjlbs 2004
 * @author zhouzm
 * @date 2004-3-10
 * 
 */


/**
 * ȥ���ַ����е����пո�
 * 
 * @param str
 *            Ҫȥ���ո���ַ���
 */
function replaceBlankAll(str){
    str=str.toString();
    if (str=="")
    {
        return;
    }
    var reg=/ /gi;
    return str.replace(reg, "");

}

/**
 * ȥ���ַ����е����пո�
 * 
 * @param str
 *            Ҫȥ���ո���ַ���
 */
function replaceBlank(str){
    str=str.toString();
    if (str=="")
    {
        return;
    }
    var reg=/(^\s*|\s*$)/g;
    return str.replace(reg, "");

}

/**
 * �Ƚ��������ڴ�С,2004-12-01 > 2004-11-08�᷵��true
 * 
 * @param max
 *            ���������д������
 * @param min
 *            ����������С������
 * @return true/false max����min/maxС��min
 */
function compareDate(max,min){
    var newMax = strToDate(max);
    var newMin = strToDate(min);
    if (newMax >= newMin){
        return true;
    }else{
        return false;
    }
}

/**
 * ���ڰ�һ������string ת����һ�� Date ���͵�ֵ
 * 
 * @param strDate
 *            Ҫת�����ַ��� ��20020303�� �� ��2002-3-3�� �� ��2002.3.3��
 * @return 8λ�ַ�������
 */
function strToDate(strDate) {
    var tempDate = strDate;
    var index1 = tempDate.indexOf(".");
    if(-1 == index1)
        index1 = tempDate.indexOf("-");
        
    var index2 = tempDate.lastIndexOf(".");
    if(-1 == index2)
        index2 = tempDate.lastIndexOf("-");

    // ��ʽ��2002-2-2,2002.2.3�Ľ���
    if ((-1 != index1) || (-1 != index2)) {
        var year = tempDate.substring(0, index1);
        
        var m = parseInt(tempDate.substring(index1 + 1, index2),10);
        var month = "" + m;
        if(m < 10)
            month = "0" + m;
            
        var d = parseInt(tempDate.substring(index2 + 1, tempDate.length),10);             
        var day = "" + d;
        if(d < 10)
            day = "0" + d;
            
        tempDate = year + month + day;
    } 
    return tempDate;
}

/**
 * �õ�һ���ַ����ĳ���(��Ӣ�����)
 * 
 * @param str
 *            �ַ��� return �ַ�������
 */
function getStrLength(str){
    var num = str.length;
    var arr = str.match(/[^\x00-\x80]/ig)
    if(arr!=null)num+=arr.length;
    return num
}


/**
 * ͨ�����֤����õ�����������
 * 
 * @param str
 *            ���֤���� return 20000101/19990101 ����������
 */
function getDateForCard(str){
    var inputStr=str.toString();
    var year;
    var month;
    var day;
    if (inputStr.length==18)
    {
        year=parseInt(inputStr.substring(6,10),10).toString();
        month=parseInt(inputStr.substring(10,12),10).toString();
        day=parseInt(inputStr.substring(12,14),10).toString();
    }else{
        year=parseInt(inputStr.substring(6,8),10).toString();
        year="19"+year;
        month=parseInt(inputStr.substring(8,10),10).toString();
        day=parseInt(inputStr.substring(10,12),10).toString();
    }
    if (month.length==1)
    {
        month="0"+month;
    }
    if (day.length==1)
    {
        day="0"+day;
    }

    return year+month+day;
}

/**
 * ͨ�����֤����õ��Ա�
 * 
 * @param str
 *            ���֤���� return 1/2 ��/Ů
 */
function getSexForCard(str){
    var inputStr=str.toString();
    var sex;
    if (inputStr.length==18)
    {
        sex=inputStr.charAt(16);
        if (sex%2==0)
        {
            return 2;
        }else{
            return 1;
        }
    }else{
        sex=inputStr.charAt(14);
        if (sex%2==0)
        {
            return 2;
        }else{
            return 1;
        }
    }

}

/**
 * �Ƚ�ǰ�Ĵ�С�����maxMoney > minMoney����true�����򷵻�false�� reurn true/false maxMoney >
 * minMoney / maxMoney < minMoney
 */
function compareMoney(maxMoney,minMoney){
  var max = 0;
  var min = 0;
  maxMoney = replaceBlank(maxMoney);
  minMoney = replaceBlank(minMoney);
  if(null != maxMoney && "" != maxMoney){
     max = parseInt(maxMoney.replace("��","").replace(".",""));
  }

  if(null != minMoney && "" != minMoney){
     min = parseInt(minMoney.replace("��","").replace(".",""));
  }
  if(max >= min){
    return true;
  }else{
    return false;
  }
}
/**
 * ��ѡ�е������ߵĶ�ѡoption���Ƶ��ұߵĶ�ѡoption��
 * 
 * @param form
 *            ��ѡoption�����ڵ�
 * @param leftOption
 *            ��ߵĶ�ѡoption��
 * @param rightOption
 *            �ұߵĶ�ѡoption��
 * @param �Ƿ�ȫѡ
 */
function moveRight(form,leftOption,rightOption,isAll) {
 // alert(isAll);
 var leftSelect  = document.all(form).all(leftOption);
 var rightSelect = document.all(form).all(rightOption);
 move(leftSelect,rightSelect,isAll);
}
/**
 * ��ѡ�е�����ұߵĶ�ѡoption���Ƶ���ߵĶ�ѡoption��
 * 
 * @param form
 *            ��ѡoption�����ڵ�
 * @param leftOption
 *            ��ߵĶ�ѡoption��
 * @param rightOption
 *            �ұߵĶ�ѡoption��
 * @param �Ƿ�ȫѡ
 */
function moveLeft(form,leftOption,rightOption,isAll) {
 var leftSelect  = document.all(form).all(leftOption);
 var rightSelect = document.all(form).all(rightOption);
 move(rightSelect,leftSelect,isAll);
}
/**
 * �����ݴ�fromSelect��ѡoption���е����ݣ��ƶ���toSelect��ѡoption����
 * 
 * @param fromSelect
 *            �ṩ���ݵ�select
 * @param toSelect
 *            �������ݵ�select
 * @param isAll
 *            �Ƿ�ȫ���ƶ���Ĭ��Ϊ�ƶ�ѡ�����ݣ�true���ƶ���������
 * @return void
 */
function move(fromSelect,toSelect,isAll)  {
 // alert(isAll);
 fromOptions = fromSelect.options;
 // alert("optionsLength="+fromOptions.length);
 var toSelectLength = 0;
 if(toSelect.options) {
    toSelectLength = toSelect.options.length;
 }

if(fromOptions.length){
 if(isAll) {
   for(i=0;i<fromOptions.length;) {
       // alert(fromOptions.length+"="+fromOptions[i].value+"=["+i+"]="+fromOptions[i].text);
       var newOption = new Option(fromOptions[i].text,fromOptions[i].value,toSelectLength++);
       toSelect.add(newOption);
       fromSelect.remove(fromOptions[i].index);
   }
 } else {
   for(i=0;i<fromOptions.length;i++) {
      if(fromOptions[i].selected) {
          // alert(fromOptions.length+"="+fromOptions[i].value+"=["+i+"]="+fromOptions[i].text);
          var newOption =new Option(fromOptions[i].text,fromOptions[i].value,++toSelectLength);
          toSelect.add(newOption);
          fromSelect.remove(fromOptions[i].index);
          i = 0;
      }
   }
 }
}
}
/**
 * �õ�ѡ�еļ�¼
 */
function getSelectedData(form,selectedOption) {
  var selected = document.all(form).all(selectedOption);
  var resultList = "";
  if(selected.length){
   for(i = 0;i < selected.length; i++) {
          resultList += selected[i].value + ";";
   }
  }
  return resultList;
}

/**
 * ����¼�����money��ȱʡ���� �����㷨���Լ���ҳ���жԴ˷���ʵ��
 */
function computeForBatchInput(objID){

}

/**
 * �������֤�Ż�ȡ������Ϣ��ȱʡ���� �����㷨���Լ���ҳ���жԴ˷���ʵ��
 */
function getInfoByCard(objID){

}

/**
 * ���㿪ʼ���º���ֹ����֮��
 */
function computeYearMonth(objID){

}

function selectChange(objID){

}
/**
 * �رմ���
 */
function closeWindow(formName){
   if(confirm("ȷ��Ҫ�رմ˴�����")){            
    var XMLHTTP = new ActiveXObject("Microsoft.XMLHTTP");
    XMLHTTP.open("POST", "/" + lemis.WEB_APP_NAME + "/cleanSessionAction.do?name=" + formName, false);
    XMLHTTP.send("");
    location.href = "/" + lemis.WEB_APP_NAME + "/Main.htm";
   }
}


// First things first, set up our array that we are going to use.
var keyStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + // all caps
"abcdefghijklmnopqrstuvwxyz" + // all lowercase
"0123456789+/="; // all numbers plus +/=

// Heres the encode function
function encode64(inp)
{
var out = ""; // This is the output
var chr1, chr2, chr3 = ""; // These are the 3 bytes to be encoded
var enc1, enc2, enc3, enc4 = ""; // These are the 4 encoded bytes
var i = 0; // Position counter

do { // Set up the loop here
chr1 = inp.charCodeAt(i++); // Grab the first byte
chr2 = inp.charCodeAt(i++); // Grab the second byte
chr3 = inp.charCodeAt(i++); // Grab the third byte

// Here is the actual base64 encode part.
// There really is only one way to do it.
enc1 = chr1 >> 2;
enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
enc4 = chr3 & 63;

if (isNaN(chr2)) {
enc3 = enc4 = 64;
} else if (isNaN(chr3)) {
enc4 = 64;
}

// Lets spit out the 4 encoded bytes
out = out + keyStr.charAt(enc1) + keyStr.charAt(enc2) + keyStr.charAt(enc3) +
keyStr.charAt(enc4);

// OK, now clean out the variables used.
chr1 = chr2 = chr3 = "";
enc1 = enc2 = enc3 = enc4 = "";

} while (i < inp.length); // And finish off the loop

// Now return the encoded values.
return out;
}

// Heres the decode function
function decode64(inp)
{
var out = ""; // This is the output
var chr1, chr2, chr3 = ""; // These are the 3 decoded bytes
var enc1, enc2, enc3, enc4 = ""; // These are the 4 bytes to be decoded
var i = 0; // Position counter

// remove all characters that are not A-Z, a-z, 0-9, +, /, or =
var base64test = /[^A-Za-z0-9+/=]/g;

if (base64test.exec(inp)) { // Do some error checking
alert("There were invalid base64 characters in the input text.n" +
"Valid base64 characters are A-Z, a-z, 0-9, ?+?, ?/?, and ?=?n" +
"Expect errors in decoding.");
}
inp = inp.replace(/[^A-Za-z0-9+/=]/g, "");

do { // Here?s the decode loop.

// Grab 4 bytes of encoded content.
enc1 = keyStr.indexOf(inp.charAt(i++));
enc2 = keyStr.indexOf(inp.charAt(i++));
enc3 = keyStr.indexOf(inp.charAt(i++));
enc4 = keyStr.indexOf(inp.charAt(i++));

// Heres the decode part. There?s really only one way to do it.
chr1 = (enc1 << 2) | (enc2 >> 4);
chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
chr3 = ((enc3 & 3) << 6) | enc4;

// Start to output decoded content
out = out + String.fromCharCode(chr1);

if (enc3 != 64) {
out = out + String.fromCharCode(chr2);
}
if (enc4 != 64) {
out = out + String.fromCharCode(chr3);
}

// now clean out the variables used
chr1 = chr2 = chr3 = "";
enc1 = enc2 = enc3 = enc4 = "";

} while (i < inp.length); // finish off the loop

// Now return the decoded values.
return out;
}

// ����ָ����ѡ���ѡ��״̬���޸ĸ�ѡ���б��еĸ�ѡ��״̬
function selectAllItem(flgChk, chkList)
{
    if(null==flgChk||null ==chkList) return false;
	var num=chkList.length;
	var state=false;
	if (flgChk.checked)
	{
		state=true;
	}
		for (var i=0;i<num ; i++)
		{
			if (!chkList[i].disabled) 
			{
				chkList[i].checked=state;
			}
		}
}
// �ж��Ƿ�ȫѡ��������������checkboxȫ��ѡ�У����޸�checkall��״̬
function checkSelectAll(flgChk,chkList)
{
    if(null==flgChk||null ==chkList) return false;
	var num=chkList.length;
		for (var i=0;i<num ; i++)
		{
			if (!chkList[i].checked) 
			{
				flgChk.checked=false;
				return;
			}
		}
		flgChk.checked=true;
}
/*
 * ��ʽ�������� value������ʽ���ĸ��������ַ��� pos��������С��λ��
 */
function format(value, pos) {
	var v = value.replace(/_/g,"");
	var p=v.lastIndexOf(".");
	var leng=0;
// alert("pos:"+pos+",length:"+v.length+",p:"+p);
	if (p<0) {
		leng=pos;
		v=v+".";
	}
	else if (v.length-p-1>pos) {
		v=v.substring(0, p+pos+1);
	}
	else if (p>v.length-pos-1) {
		leng=p-(v.length-pos-1);
	}
	if (leng>0) {
		for (var i=0;i<leng;i++) {
			v=v+"0";
		}
	}
	return v;
}

 
 // ���� select ԭֵ
var oldValue=""
var oldText="";
var searchtype="0";

// select�������onkeypress�¼�����λ�������ֵ
function catch_press(sel){

 if(sel.selectedIndex>=0){ 
  var s=String.fromCharCode(event.keyCode);
  oldValue=oldValue+s;
  oldText=oldText+s;
 
  event.returnValue = false;
  if (!event.returnValue && sel.onchange)
  sel.onchange(sel)
 }
 if(searchtype=="0")
  searchoptiontext(sel);
 else 
  searchoptionvalue(sel);
 selMouseOver();
}

// ͨ��text������λ
function searchoptionvalue(obj){ 
  if(obj){
   var invalue=oldValue;
   var selectlength=obj.options.length
   for(var i=0;i<selectlength;i++){
    var temp=obj.options[i].value
     if(temp==invalue){
     obj.selectedIndex=i
     obj.focus()
     break;
    }  
   }
  }
 }
 // ͨ��ֵvalue��λ
function searchoptiontext(obj){ 
  if(obj){
   var invalue=oldText;
   var selectlength=obj.options.length
   for(var i=0;i<selectlength;i++){
    var temp=obj.options[i].text
    // alert(temp.indexOf(invalue))
     if(temp.indexOf(invalue)!=-1){
     obj.selectedIndex=i
     obj.focus()
     break;
    }  
   }
  }
 }


// select�������onfocus�¼�,��ձ����ֵ
function catch_focus(sel) {
 oldText ="";
 oldValue ="";
}

// select�������onkeydown�¼����޸��������ֵ
function catch_keydown(sel)
{
 switch(event.keyCode)
 {
  case 13: // �س���
   catch_focus(sel)
   event.returnValue = false;
   break;
  case 27: // Esc��
   catch_focus(sel)
   event.returnValue = false;
   break;
  case 8:  // �ո�
   var s = "";
   if(searchtype=="0"){
     s=oldText;
     s = s.substr(0,s.length-1);
     oldText=s;
   }else{
     s=oldValue;
     s = s.substr(0,s.length-1);
     oldValue=s;
   } 
   selMouseOver(sel);
   event.returnValue = false;
   break;
 }
 if (!event.returnValue && sel.onchange)
  sel.onchange(sel)
}

function selMouseOver(obj)
{
 with (document.all.div_hint)
 {
  innerText ="��ʾ��Ϣ��\r\n �������ˣ�"+ oldText+"\r\n <����ѡ����б���ո�ֵ��>";
  // obj.options[obj.selectedIndex].text;
  if (innerText.length > 0)
  {
   innerText = " " + innerText + "  ";
   style.display = "block";
   style.left = event.clientX + 20;
   style.top = event.clientY+20;
  }
 }
}

// select ѡ�������ƿ�ʱ��ʧ
function selMouseOut(obj)
{
 with (document.all.div_hint)
 {
  style.display = "none"
 }
} 

function selMouseOver1(obj)
{
with (document.all.div_hint)
 {
  innerText ="��ʾ��Ϣ��"+obj+"";
  if (innerText.length > 0)
  {
   innerText = " " + innerText + "  ";
   style.display = "block";
   style.left = event.clientX + 20;
   style.top = event.clientY+20;
  }
 }
}

function trim(s) {
  var count = s.length;
  var st    = 0;       // start
  var end   = count-1; // end

  if (s == "") return s;
  while (st < count) {
    if (s.charAt(st) == " ")
      st ++;
    else
      break;
  }
  while (end > st) {
    if (s.charAt(end) == " ")
      end --;
    else
      break;
  }
  return s.substring(st,end + 1);
}
function setHeight(){
	var height = document.body.scrollHeight;
	parent.document.getElementById("mainFrame").height = height;
}
function go2Page(obj1,obj2){
 
          if(obj2==null)
             {
               obj2="1";
              }
          if(obj2=="")
             {
               obj2="1";
              }
		  window.location.href="/"+lemis.WEB_APP_NAME+"/"+obj1+"/backAction.do?method=go2Page&back="+obj2;

		  
	}
	function GetROUND_UP(obj1){
 
          return Math.ceil(obj1);
	}
	function GetROUND_DOWN(obj1){
 
          return Math.floor(obj1);
	}
	function GetROUND_HALF_EVEN(obj1){
 
          return Math.round(obj1);
	}

