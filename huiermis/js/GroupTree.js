function CommonTree() {
    // 是否显示树节点左边的图片 true/false
    var bShowTreeImage = true;
    this.init = function(treeName,selectNodeId) {
        var xmlData = document.all(treeName).documentElement;
        var divTree = document.all("div_tree_" + treeName);
        //定义点击树节点的事件处理
        divTree.onNodeSelecting = function() {
             //点击的处理方法
            showContent(event.treenode);
        }
        //定义点击展开按钮的事件处理
        divTree.onNodeToggleClick = function() {
            //alert("in onNodeToggleClick");
            ToggleNode(divTree,xmlData,event.treenode);
        }
        //定义双击节点时的事件处理
        divTree.onNodeLabelDblClick = function() {
            ToggleNode(divTree,xmlData,event.treenode);
        }
        loadSubMenu(divTree, xmlData, divTree.getRootNode());
        //设置默认算中的节点
        var oNode=divTree.findNodeByID("01");
        if(selectNodeId == null){
            if(oNode!=null){
                ToggleNode(divTree,xmlData,oNode);
            }
        }else if(selectNodeId != null){
           /* if(selectNodeId != "01"){*/
                var totalLevel = selectNodeId.length/2;

                for(i=1;i<=totalLevel;i++){
                    var parentId = selectNodeId.substring(0,i*2);

                    oNode=divTree.findNodeByID(parentId);
                    if(oNode!=null){
                        ToggleNode(divTree,xmlData,oNode);
                        if(oNode.expanded==false){
                            divTree.toggleNode(oNode,true);
                        }
                        /*if(i == totalLevel){
                            divTree.highlightCurrentNode(oNode);
                        }*/
                    }
                }
            /*}else{
                ToggleNode(divTree,xmlData,oNode);
                if(oNode.expanded==false){
                    divTree.toggleNode(oNode,true);
                }
                divTree.selectNode(oNode);
            }*/
        }
    }
 	/**
    * 如果子节点没有导入折将数据岛中数据进行解析并导入
    * @return
    */
    function ToggleNode(divTree,xmlData,oParentNode) {
        if(oParentNode.isLeaf){
            return;
        }

        if(oParentNode.childNodes.length>0){
            return;
        }

        var treelist=xmlData.getElementsByTagName("Tree");
        var toggleelement;
        var xmlElement;
        for(i=0;i<treelist.length;i++){
            toggleelement=treelist.item(i);
            if(toggleelement.selectSingleNode("@id").text==oParentNode.ID){
                xmlElement=toggleelement;
            }
        }
        var oXMLNodes = xmlElement.childNodes;
        var strMenuID, strMenuLabel, strMenuTitle, strLinkName;
        for (var i=0; i<oXMLNodes.length; i++) {
            strMenuID = oXMLNodes[i].selectSingleNode("@id").text;            
            strMenuLabel = oXMLNodes[i].selectSingleNode("@label").text;
            strMenuTitle = oXMLNodes[i].selectSingleNode("@title").text;            
            var ks=strMenuTitle.substr(0,1);
            if (oXMLNodes[i].selectSingleNode("@link") != null)
                strLinkName = oXMLNodes[i].selectSingleNode("@link").text;
            else
                strLinkName = null;

            var oTreeNodeInitInfo = divTree.newNodeInitInfo(strMenuID, strMenuLabel, strMenuTitle, null, strLinkName);
            if (oXMLNodes[i].nodeName == "TreeNode") {
                oTreeNodeInitInfo.isLeaf = true;
                if( bShowTreeImage ){
                    if("G"==ks)
                    {
                      oTreeNodeInitInfo.imgSelect = "/" + lemis.WEB_APP_NAME + "/images/tree/tree_leaf_select_G.gif";
                      oTreeNodeInitInfo.imgUnselect = "/" + lemis.WEB_APP_NAME + "/images/tree/tree_leaf_unselect_G.gif";
                    }else if("K"==ks)
                    {
                      oTreeNodeInitInfo.imgSelect = "/" + lemis.WEB_APP_NAME + "/images/tree/tree_leaf_select_K.gif";
                      oTreeNodeInitInfo.imgUnselect = "/" + lemis.WEB_APP_NAME + "/images/tree/tree_leaf_unselect_K.gif";
                    }else
                    {
                    oTreeNodeInitInfo.imgSelect = "/" + lemis.WEB_APP_NAME + "/images/tree/tree_leaf_select.gif";
                    oTreeNodeInitInfo.imgUnselect = "/" + lemis.WEB_APP_NAME + "/images/tree/tree_leaf_unselect.gif";
                    }
                }
            } else {
                if( bShowTreeImage ){
                    oTreeNodeInitInfo.imgSelect ="/" + lemis.WEB_APP_NAME + "/images/tree/tree_branch_select.gif";
                    oTreeNodeInitInfo.imgUnselect = "/" + lemis.WEB_APP_NAME + "/images/tree/tree_branch_unselect.gif";
                }
            }

            var oTreeNode = divTree.createNode(oTreeNodeInitInfo);


            if(oTreeNodeInitInfo.data==null)

                oTreeNode.htmlContainer.all.tags("SPAN")[1].style.borderBottom = "none";
            divTree.insertNode(oParentNode, oTreeNode);

            if (oXMLNodes[i].nodeName == "Tree") {

                oTreeNode.htmlContainer.all.tags("SPAN")[1].style.borderBottom = "none";
            }
        }

    }
    
     //返回当前id对应节点的子节点title的集合
    function getSubMenuTitles(treeName,id){
      var SubTitles=new Array();
      var divTree = document.all("div_tree_" + treeName);
      var oNode;
      if(id==""){
        oNode=divTree.getRootNode();
      }else{
        oNode=divTree.findNodeByID(id);
      }
      if(oNode==null)
      {
        return SubTitles;
      }
      var oNodes=oNode.childNodes;
  	  for (var i=0; i<oNodes.length; i++) {
           SubTitles[i]=oNodes[i].label;
      }
      return  SubTitles;
    }

    //返回当前id对应节点的同级节点title的集合
    function getBrotherMenuTitles(treeName,id){
      var Titles=new Array();
      var divTree = document.all("div_tree_" + treeName);
      var oNode=divTree.findNodeByID(id);
      var parentNo = oNode.parentNode;
      if(parentNo!=null){
        Titles=getSubMenuTitles(treeName,parentNo.ID);
      }
      return Titles;
    }

    //载入子菜单
    function loadSubMenu(divTree, xmlData, oParentNode) {
        var oXMLNodes = xmlData.childNodes;

        if(oParentNode.childNodes.length>0){
            return;
        }

        var strMenuID, strMenuLabel, strMenuTitle, strLinkName;
        for (var i=0; i<oXMLNodes.length; i++) {

            strMenuID = oXMLNodes[i].selectSingleNode("@id").text;
            strMenuLabel = oXMLNodes[i].selectSingleNode("@label").text;
            strMenuTitle = oXMLNodes[i].selectSingleNode("@title").text;
            if (oXMLNodes[i].selectSingleNode("@link") != null)
                strLinkName = oXMLNodes[i].selectSingleNode("@link").text;
            else
                strLinkName = null;

            var oTreeNodeInitInfo = divTree.newNodeInitInfo(strMenuID, strMenuLabel, strMenuTitle, null, strLinkName);
            if (oXMLNodes[i].nodeName == "TreeNode") {
                oTreeNodeInitInfo.isLeaf = true;
                if( bShowTreeImage ){
                    oTreeNodeInitInfo.imgSelect ="/" + lemis.WEB_APP_NAME + "/images/tree/tree_leaf_select.gif";
                    oTreeNodeInitInfo.imgUnselect = "/" + lemis.WEB_APP_NAME + "/images/tree/tree_leaf_unselect.gif";
                }
            } else {
                if( bShowTreeImage ){
                    oTreeNodeInitInfo.imgSelect = "/" + lemis.WEB_APP_NAME + "/images/tree/tree_branch_select.gif";
                    oTreeNodeInitInfo.imgUnselect ="/" + lemis.WEB_APP_NAME + "/images/tree/tree_branch_unselect.gif";
                }
            }

            var oTreeNode = divTree.createNode(oTreeNodeInitInfo);

             //如果节点没有制定显示页面，没有链接，则不显示下滑线
            if(oTreeNodeInitInfo.data==null)
            //设置没有下划线
                oTreeNode.htmlContainer.all.tags("SPAN")[1].style.borderBottom = "none";

            divTree.insertNode(oParentNode, oTreeNode);

            if (oXMLNodes[i].nodeName == "Tree") {
                //设置没有下划线
                oTreeNode.htmlContainer.all.tags("SPAN")[1].style.borderBottom = "none";
                //递归
//				loadSubMenu(divTree, oXMLNodes[i], oTreeNode);
            }
            //默认展开所有节点  add by huangzg@2003-07-21
//            if(oTreeNode.expanded==false){
//                divTree.toggleNode(oTreeNode,true);
//            }
        }

    }


     //对于点击菜单项的处理
    function showContent(oTreeNode) {
        var form = parent.displayFrame.document.forms[0];
        var hasChildren = "true";
        if(oTreeNode.isLeaf == true)
        	hasChildren = "false"
        form.action = "/" + lemis.WEB_APP_NAME + "/sysmanager/deptAction.do?method=findByKey&groupid="
        	+ oTreeNode.title + "&treeid=" + oTreeNode.ID + "&hasChildren=" + hasChildren;
        form.submit();
    }

}

function selectDefaultNode(parentNodeId){
    new CommonTree().init('deptTree',parentNodeId);
}

/**
* 得到指定节点的子节点数
* 参数：parentNodeId （默认0）
*/
function getChildNodesLength(){
    var divTree = document.all("div_tree_deptTree");
    var parentNodeId = "01";
    var args = getChildNodesLength.arguments;
    if( args.length>0 ){
        parentNodeId = args[0];
    }
    var oNode = divTree.findNodeByID( parentNodeId );
    if( oNode!=null&&oNode.childNodes.length>0 ){
        return oNode.childNodes.length;
    }
    return 0;
}