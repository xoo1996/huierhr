//==================================================== �����趨���� =======================================================
var _VersionInfo="Version:1.0&#13;2.0����:����� �Ŷ�ƽ ������"	//�汾��Ϣ modify by chenkl����src��ʼֵΪ�գ��ڵ��ʱ����src��
var bMoveable=true;		//�����Ƿ�����϶�
var regionTree=false;
var workTypeTree=false;
var ssjdTree=false;     //���������ֵ������� by���鳬 2004��4��26��
var calendar=false;
var cylbTree=false;     //�����ҵ��������� �ֿ� 2007��01��31��
//====================================================Iframe WEB ҳ����ʾ���� =====================================================
document.writeln('<iframe id=worktypeTreeLayer src=""  Author=zhangsk frameborder=0 style="position: absolute; width: 280; height: 220; z-index: 9998; display: none"></iframe>');
var workType_outObject1;
var workType_outObject2;

document.writeln('<iframe id=regionTreeLayer src=""  Author=zhangsk frameborder=0 style="position: absolute; width: 280; height: 400; z-index: 9998; display: none"></iframe>');
var region_outObject1;
var region_outObject2;

//�������ֵ���  by���鳬 2004��4��26��
document.writeln('<iframe id=ssjdTreeLayer src=""  Author=zhangsk frameborder=0 style="position: absolute; width: 250; height: 335; z-index: 9998; display: none"></iframe>');
var ssjd_outObject1;
var ssjd_outObject2;

//�򿪲�ҵ�����  by�ֿ� 2007��01��31��
document.writeln('<iframe id=cylbTreeLayer src=""  Author=zhangsk frameborder=0 style="position: absolute; width: 280; height: 180; z-index: 9998; display: none"></iframe>');
var cylb_outObject1;
var cylb_outObject2;

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
function  setWorkTypeTreequery(obj1,obj2,obj3)//Ϊ�˿��ٲ�ѯʹ��lwd20071122
     {  //alert(window.event.keyCode);
        if(window.event.keyCode==32) 
          { window.event.keyCode=0;
            setWorkTypeTree(obj1,obj2,obj3)  
          }
  }   
function setWorkTypeTree(tt,disObjName,valObjName) //��������
{
	if (arguments.length >  3){alert("�Բ��𣡴��뱾�ؼ��Ĳ���̫�࣡");return;}
	if (arguments.length == 0){alert("�Բ�����û�д��ر��ؼ��κβ�����");return;}
    var srcFrame = document.all.worktypeTreeLayer.src;
    
    //if("" == srcFrame)//Ҷ���޸ģ����ݵ�ǰ�����ѡ���������ķ�ʽ
    {
    
    		if(trim(tt.value)=="")
        	document.all.worktypeTreeLayer.src = "/" + lemis.WEB_APP_NAME+"/common/workType.jsp";
        else
        	document.all.worktypeTreeLayer.src = "/" + lemis.WEB_APP_NAME+"/common/workTypeQuery.jsp?condition="+tt.value;
    }
	var dads  = document.all.worktypeTreeLayer.style;
	var th = tt;
	var ttop  = tt.offsetTop;     //TT�ؼ��Ķ�λ���
	var thei  = tt.clientHeight;  //TT�ؼ�����ĸ�
	var tleft = tt.offsetLeft;    //TT�ؼ��Ķ�λ���
	var ttyp  = tt.type;          //TT�ؼ�������
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
function workerType_setValue(dispaly_value,code_value){//Ϊtag�ṩ���á���ʾ���͡������򡱵�ֵ
    parent.workType_outObject1.value=dispaly_value;
    parent.workType_outObject2.value=code_value;
    parent.workTypeTree_close()
}
function workerType_clearvalue_tree(){//��iframe�е������ա���ť���ֵͬʱ�ر�Iframe
    parent.workType_outObject1.value="";
    parent.workType_outObject2.value="";
    parent.workTypeTree_close()
}

function close_WorkType_Frame(){ //��iframe�е�����رա���ť�ر�iframe
	parent.workTypeTree_close()
}

function workTypeTree_close(){ //Ϊ���������ṩ�ر�Iframe�ķ���
	regionTree=false;
	workTypeTree=false;
	ssjdTree=false;
	cylbTree=false;
	calendar=false;
	document.all.worktypeTreeLayer.style.display="none";
}
//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
function clearValue(obj1,obj2){   //�ṩҳ��ĵ������ֵ�ķ�����������Iframe�е���գ�
    obj1.value="";
    obj2.value="";
}
function document.onclick(){ //������ʱ�رոÿؼ�	//ie6�����������������л����㴦�����
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
      //�����ǰҳʱ���ر� 
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
            //�����ǰҳʱ���ر� 
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

function setRegionTree(tt,disObjName,valObjName) //��������
{
	if (arguments.length >  3){alert("�Բ��𣡴��뱾�ؼ��Ĳ���̫�࣡");return;}
	if (arguments.length == 0){alert("�Բ�����û�д��ر��ؼ��κβ�����");return;}
    var srcFrame = document.all.regionTreeLayer.src;
    if("" == srcFrame){
        document.all.regionTreeLayer.src = "/" + lemis.WEB_APP_NAME+"/common/region.jsp";
    }
	var dads  = document.all.regionTreeLayer.style;
	var th = tt;
	var ttop  = tt.offsetTop;     //TT�ؼ��Ķ�λ���
	var thei  = tt.clientHeight;  //TT�ؼ�����ĸ�
	var tleft = tt.offsetLeft;    //TT�ؼ��Ķ�λ���
	var ttyp  = tt.type;          //TT�ؼ�������
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

function region_setValue(dispaly_value,code_value){//Ϊtag�ṩ���á���ʾ���͡������򡱵�ֵ
    parent.region_outObject1.value=dispaly_value;
    parent.region_outObject2.value=code_value;
    parent.regionTree_close()
}
function region_clearvalue_tree(){//��iframe�е������ա���ť���ֵͬʱ�ر�Iframe
    parent.region_outObject1.value="";
    parent.region_outObject2.value="";
    parent.regionTree_close()
}

function close_region_Frame(){
	parent.regionTree_close()
}

function regionTree_close(){               //��iframe�е�����رա���ť�ر�iframe
	regionTree=false;
	workTypeTree=false;
	ssjdTree=false;
	cylbTree=false;
	calendar=false;
	document.all.regionTreeLayer.style.display="none";
}

//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
function  setSSJDTreequery(obj1,obj2,obj3)//Ϊ�˿��ٲ�ѯʹ��cxp20081118
     {  //alert(window.event.keyCode);
        if(window.event.keyCode==32) 
          { window.event.keyCode=0;
            setSSJDTree(obj1,obj2,obj3)  
          }
 } 
 
function setSSJDTree(tt,disObjName,valObjName) //�������� ������ʾ�����ڽֵ����� by���鳬 2006��4��26��
{
	if (arguments.length >  3){alert("�Բ��𣡴��뱾�ؼ��Ĳ���̫�࣡");return;}
	if (arguments.length == 0){alert("�Բ�����û�д��ر��ؼ��κβ�����");return;}
    var srcFrame = document.all.ssjdTreeLayer.src;
    if("" == srcFrame){
        document.all.ssjdTreeLayer.src = "/" + lemis.WEB_APP_NAME+"/common/ssjd.jsp";
    }
    //{//���ݲ�ͬ���ѡ��ͬ����
    
   // 	if(trim(tt.value)=="")
   //     	document.all.ssjdTreeLayer.src = "/" + lemis.WEB_APP_NAME+"/common/ssjd.jsp";
  //      else
  //      	document.all.ssjdTreeLayer.src = "/" + lemis.WEB_APP_NAME+"/common/ssjdQuery.jsp?condition="+tt.value;
  //  }
    
	var dads  = document.all.ssjdTreeLayer.style;
	var th = tt;
	var ttop  = tt.offsetTop;     //TT�ؼ��Ķ�λ���
	var thei  = tt.clientHeight;  //TT�ؼ�����ĸ�
	var tleft = tt.offsetLeft;    //TT�ؼ��Ķ�λ���
	var ttyp  = tt.type;          //TT�ؼ�������
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

function ssjd_setValue(dispaly_value,code_value){//Ϊtag�ṩ���á���ʾ���͡������򡱵�ֵ
    parent.ssjd_outObject1.value=dispaly_value;
    parent.ssjd_outObject2.value=code_value;
    parent.ssjdTree_close()
}
function ssjd_clearvalue_tree(){//��iframe�е������ա���ť���ֵͬʱ�ر�Iframe
    parent.ssjd_outObject1.value="";
    parent.ssjd_outObject2.value="";
    parent.ssjdTree_close()
}

function close_ssjd_Frame(){
	parent.ssjdTree_close()
}

function ssjdTree_close(){               //��iframe�е�����رա���ť�ر�iframe
	regionTree=false;
	workTypeTree=false;
	ssjdTree=false;
	cylbTree=false;
	calendar=false;
	document.all.ssjdTreeLayer.style.display="none";
}

//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
function setCYLBTree(tt,disObjName,valObjName) //�������� ������ʾ��ҵ������ by�ֿ� 2007��01��31��
{
	
	if (arguments.length >  3){alert("�Բ��𣡴��뱾�ؼ��Ĳ���̫�࣡");return;}
	if (arguments.length == 0){alert("�Բ�����û�д��ر��ؼ��κβ�����");return;}
    var srcFrame = document.all.cylbTreeLayer.src;
    if("" == srcFrame){
        document.all.cylbTreeLayer.src = "/" + lemis.WEB_APP_NAME+"/common/cylb.jsp";
    }
	var dads  = document.all.cylbTreeLayer.style;
	var th = tt;
	var ttop  = tt.offsetTop;     //TT�ؼ��Ķ�λ���
	var thei  = tt.clientHeight;  //TT�ؼ�����ĸ�
	var tleft = tt.offsetLeft;    //TT�ؼ��Ķ�λ���
	var ttyp  = tt.type;          //TT�ؼ�������
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
    //Ϊtag�ṩ���á���ʾ���͡������򡱵�ֵ
    parent.cylb_outObject1.value=dispaly_value;
    parent.cylb_outObject2.value=code_value;
    parent.cylbTree_close()
}
function cylb_clearvalue_tree(){//��iframe�е������ա���ť���ֵͬʱ�ر�Ifram
    parent.cylb_outObject1.value="";
    parent.cylb_outObject2.value="";
    parent.cylbTree_close()
}

function close_cylb_Frame(){
	parent.cylbTree_close()
}

function cylbTree_close(){               //��iframe�е�����رա���ť�ر�iframe
	regionTree=false;
	workTypeTree=false;
	ssjdTree=false;
	cylbTree=false;
	calendar=false;
	document.all.cylbTreeLayer.style.display="none";
}
