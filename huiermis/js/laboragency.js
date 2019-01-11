//??????????????????
function getCurYM()
{
	var thisDate = new Date();
	var thisMonth=("0"+(thisDate.getMonth()+1));
	
	thisMonth=thisMonth.substring(thisMonth.length-2,thisMonth.length);
	var thisYM = thisDate.getFullYear() +""+  thisMonth;
	return thisYM;
}

//??????????????????????????????????
function getEndYM(startym)
{
	var endym='';
	
	if(startym.length==6&&!isNaN(parseInt(startym)))//??????6????????????
	{
		
		var starty=startym.substring(0,4);
		var startm=startym.substring(4,6);
		var endy;
		var endm;
		
		if(parseInt(startm)<7)
		{
			endy=''+starty;
			endm='06';
			
		}
		else
		{
			endy=''+(parseInt(starty)+1);
			endm='06'
		}
		endym=endy+endm;
		
		
	}
	return endym;
}

//????????????????????
function getCurYD(startym)
{
	var yd;
	if(startym.length==6&&!isNaN(parseInt(startym)))//??????6????????????
	{
		var starty=startym.substring(0,4);
		var startm=startym.substring(4,6);
		
		if(parseInt(startm)<7)
		{
			yd=''+(parseInt(starty)-1);
		}
		else
		{
			yd=starty;
		}
	}
	return yd;	
}

//????????????????????????????
function getDifMonth(startym,endym)
{
	var mcount;
	if(startym.length==6&&!isNaN(parseInt(startym))&&endym.length==6&&!isNaN(parseInt(endym)))//??????6????????????
	{
		var starty=parseInt(startym.substring(0,4));
		var startm1=parseInt(startym.substring(4,5));
		var startm=parseInt(startym.substring(5,6));
		var endy=parseInt(endym.substring(0,4));
		var endm1=parseInt(endym.substring(4,5));
		var endm=parseInt(endym.substring(5,6));
		
		mcount=endy*12+endm1*10+endm-starty*12-startm1*10-startm+1;
	}
	return mcount;	
}

//????????????
function comFee(startym,endym,base,prop,feetype)
{
	var fee=0;
	if(feetype=='1')//????????
	{
		//alert(base+":"+prop+":"+getDifMonth(startym,endym));
		fee=(base)*(prop)*getDifMonth(startym,endym);
	}
	else//????????
	{
		fee=(base)*(prop)
	}
	
	return moneyformat(fee);
}

function moneyformat(money)
{
	if(!isNaN(parseFloat(money)))
	{
			if(money==0)
				return '0.00';
			//alert(money);
			if(money*100>=100)
			money=''+parseInt(money*100);
			else if(money*100>=10)
				money='0'+parseInt(money*100);
			else
				money='00'+parseInt(money*100);
			//alert(money);
			//alert((money+'').substring(0,(money+'').length-2));
			//alert((money+'').substring((money+'').length-2,(money+'').length));
			money=''+(money+'').substring(0,(money+'').length-2)+'.'+(money+'').substring((money+'').length-2,(money+'').length);
			//alert(money);
			return money;
	}
	else
		{
			return money;
		}
}
//??????
 function onlynum()
{
	//alert(event.keyCode);
	if((event.keyCode>=48&&event.keyCode<=57)||(event.keyCode>=96&&event.keyCode<=105)||event.keyCode==8||event.keyCode==37||event.keyCode==39||event.keyCode==46)
		{
			return true;
		}
		else
			{
				return false;
			}
}
