/**
 * Select校验组件
 * js组件：SelectObj.js
 * 应用例子:Select.html
 * 属性：isNullable：是否为空。 当为NO时不可以为空，需要非空校验；当为YES时可以为空，不需要非空校验。    
 * 说明：1、非空校验
 *       2、将Enter 键转换为 Tab 键。
 *             
 * @author  lixiangyu@neusoft.com,2003.04
 *          micy@neusoft.com	2003.07.21
 *				hugh@neusoft.com
 */
 function SelectObj(editerObj){
	
	//定义输入对象
	this.edtObj = editerObj;
    	
	
	//公共方法
	this.getParentObj = SEL_getParentObj;
	this.getBaseObj = SEL_getBaseObj;
	
	this.onvalidate = SEL_onvalidate;
	
	this.onReady = SEL_onDocumentReady;
 	this.eventBand = SEL_eventBand;
	
	
	//私有方法
	
	//私有对象
    var ParObj=null;
    var BasObj=null;
}

 	function SEL_getParentObj(){
    	if(this.ParObj==null){
    		this.ParObj = new BaseObj(this.edtObj);
    		}
    		return this.ParObj;	
   	}
   function SEL_getBaseObj(){
   	if(this.BasObj==null){
   		this.BasObj = new BaseObj(this.edtObj);
   	}
   	return this.BasObj;
   	
   	}

    
    // var fixLength=this.edtObj.fixLength;
     
    function SEL_onvalidate() {
    
     //调用Base.htc中的公用函数检查数据合法性
     if(!this.getBaseObj().commonCheck())	return false;   
     return true;
    }   

/**
 *名称：onDocumentReady
 *功能：在select中自动增加这项<option value="">请选择</option>
 *形参：
 *
 *返回：
 */
function SEL_onDocumentReady() {	 
	//只增加一个"请选择"
	try{
		if(this.edtObj.options(0).text != "请选择")
			this.edtObj.add(new Option("请选择",""),0); 	
    }catch(e){}  	
    
    var isNoSelected = this.edtObj.getAttribute("isNoSelected");
    if(isNoSelected != null && isNoSelected.toUpperCase() == "TRUE"){
     	this.edtObj.selectedIndex = 0;  
     	return;
    }

	var flag = false;
	var isNotInDW = this.edtObj.getAttribute("isNotInDW");
	isNotInDW = isNotInDW == null ? "false" : isNotInDW.toLowerCase();
	for(var i=1; i<this.edtObj.options.length; i++){
        if(this.edtObj.options(i).isSelected != null && this.edtObj.options(i).isSelected.toUpperCase() == "TRUE"){        	
            this.edtObj.selectedIndex = i;
            flag = true;
            break;
        }  
       if(isNotInDW != "true")
       		if(this.edtObj.options(i).selected) flag = true;      
    }	
    if(!flag) this.edtObj.selectedIndex = 0;  
    
    //调用父类的初始化方法   	 
    this.getParentObj().onReady()
}

function SEL_eventBand(){}


