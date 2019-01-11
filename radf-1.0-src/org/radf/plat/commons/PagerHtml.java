/**
* <p>标题: 通用查询组件</p>
* <p>说明: 可以通过配置文件以及传递参数等方式进行分页查询,可以返回分页结果集合，也可返回html页面</p>
* <p>项目: Rapid Application Development Framework</p>
* <p>版权: Copyright 2008 - 2017</p>
* <p>时间: 2006-1-12 14:58:42</p>
*
* @author zqb
* @version 1.0
*/
package org.radf.plat.commons;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;

import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.global.GlobalNames;

public class PagerHtml  extends Pager{

    // 新开窗口属性
    private String Winproperty;
    //  表格属性
    private String Tableproperty;
    //  边框属性
    private String border;
    //Table表头的属性
    private String title,titleAlign,titleBGColor,headBGColor,headFontColor;
    //关于页面的基本信息：总页数，当前页，页面大小，总记录数
    private int totalPage,currentPage,pageSize,recordCount;
    
    //标题栏中显示的中文名称
    private HashMap head;
     //列宽度设定
    private HashMap colwidths;
    //列上产生的链接url
    private HashMap colLinks;
    //列上需要传递的参数
    private HashMap colLinkparas;
    
    //在列位置上生成的动作链接，只有在列字段为空的时候才会判断是否存在独立的链接
    private HashMap actLink;
    //在列位置上生成的动作名称，只有actLink有效才有效
    private HashMap actLinkName;
    //在列位置上生成的动作链接的参数，只有actLink有效才有效
    private HashMap actLinkparas;
    
    
    // 图片的路径
    private String imgPath = "";

    private String pictureUp = "/image/up.gif"; // 排序的图片

    private String pictureDown = "/image/down.gif"; // 排序的图片

    // 排序方式，包括：order、asc
    private String order;

    // 选择作为排序列的编号，列字段名从hashmap cols中获取
    private String orderBy;
    
    //是否显示分页页脚
    private boolean buttom = true;
    
    private String pageLink;


    private boolean openWindow;

    private String target;

    public PagerHtml() {
        super();
        setWinProperty(null, null, null, null, null, null, null);
        setTableproperty(null, null, null, null, null, null, null, null, null,null);
        setTableHead(null,null,null,null,null);
        totalPage = 0;
        currentPage = 0;
        pageSize = GlobalNames.PAGE_SIZE==0?10:GlobalNames.PAGE_SIZE;
        recordCount = 0;
        head = new HashMap(10);
        colwidths = new HashMap(10);
        colLinks = new HashMap(2);
        colLinkparas = new HashMap(2);
        actLink = new HashMap(2);
        actLinkparas = new HashMap(2);
        actLinkName = new HashMap(2);
        
        orderBy = "";
        order = "desc";
        pageLink = "";

        openWindow = false;
        target = "_blank";
    }
    /**
     * 获取分页查询结果集合的页面html片断，执行本方法前，需要作出如下准备：
     * (1)通过setSQL()传递分页查询的SQL语句(或通过配置文件)，必须
     * (2)通过setOrder()设置获取数据的排序条件，可以不传递
     * (3)通过setPageCols设置显示列的属性，通过配置文件传时可以不传递，但将不显示标题列
     * @param page      要显示的页数
     * @param count     每页显示的行数(显示方式=1有效，=2时固定为1)
     * @param flag      显示方式：1-横排,2-一页显示一条记录
     * @return
     */
    public String getPage(int page,int count,String flag) throws AppException{
        if (flag==null||flag.equals("1")){
            return getPage(page,count);
        }else{
            return getPage(page,flag);
        }
    }
    /**
     * 获取分页查询结果集合的页面html片断，执行本方法前，需要作出如下准备：
     * (1)通过setSQL()传递分页查询的SQL语句(或通过配置文件)，必须
     * (2)通过setOrder()设置获取数据的排序条件，可以不传递
     * (3)通过setPageCols设置显示列的属性，通过配置文件传时可以不传递，但将不显示标题列
     * @param page      页码
     * @param count     每页显示的记录数
     */
    public String getPage(int page,int count)throws AppException{
        setPageSize(count);
        return getPage1(page,"1");
    }
    
    /**
     * 获取分页查询结果集合的页面html片断，执行本方法前，需要作出如下准备：
     * (1)通过setSQL()传递分页查询的SQL语句(或通过配置文件)，必须
     * (2)通过setOrder()设置获取数据的排序条件，可以不传递
     * (3)通过setPageCols设置显示列的属性，通过配置文件传时可以不传递，但将不显示标题列
     * @param page      页码
     * @param flag      显示方式：1-横排(原来模式),2-一页显示一条记录，3-横排(记录数小于pageSize时显示空行)
     * @return
     */
    public String getPage(int page,String flag)throws AppException{
    	if(pageSize<=0)
    		pageSize=GlobalNames.PAGE_SIZE<=0?10:GlobalNames.PAGE_SIZE;
    	if(flag==null)
    		flag = "1";
        if(flag.equalsIgnoreCase("1")||flag.equalsIgnoreCase("3")){
            return getPage1(page,flag);
        }else if(flag.equalsIgnoreCase("2")){
            return getPage2(page);
        }else{
            throw new AppException("未定义的页面显示模式");
        }
    }
    /**
     * 获取分页查询结果集合的页面html片断，按照一行一条纪录方式显示。
     * 本方法生成的html结构以tr开始和结束，外面需要套table控制大小
     * 执行本方法前，需要作出如下准备：
     * (1)通过setSQL()传递分页查询的SQL语句(或通过配置文件)，必须
     * (2)通过setOrder()设置获取数据的排序条件，可以不传递
     * (3)通过setPageSize()设置页面大小，可以不传递
     * (4)通过setPageCols设置显示列的属性，通过配置文件传时可以不传递，但将不显示标题列
     * @param page  要显示的页码
     * @param flag  显示方式：1-横排(原来模式),2-一页显示一条记录，3-横排(记录数小于pageSize时显示空行)
     * @return
     */
    private String getPage1(int page,String flag) throws AppException{
        if (page == currentPage){
            if (order.equals("desc"))
                order = "asc";
            else if (order.equals("asc"))
                order = "desc";
        }
        if (order.equals("desc"))
            imgPath += pictureDown;
        if (order.equals("asc"))
            imgPath += pictureUp;
        //如果页面指定按某列排序，忽略后台的排序
        if(orderBy!=null&&!orderBy.equals("")){
            if(getCols(orderBy)!=null){
                setOrder((String)(getCols(orderBy)),order);
            }
        }
        StringBuffer sb = new StringBuffer(1024);
        sb.append("<!--Tableproperty-->\n");
        sb.append(Tableproperty);		//<tr><td><table>
        sb.append("\n<!--TableHead-->\n");
        sb.append(buildTableHead(page));//生成标题栏目,<tr></tr>

        sb.append("\n<!--data-->\n");	//生成数据<tr></tr>
        Connection con = null;
        ResultSet rst = null;
        try {
            con = DBUtil.getConnection();
            rst = getPageData(con,page,pageSize);
            int nowPageCount = 0; 
            for (sb.append("\n"); rst.next();sb.append("\n")) {
                nowPageCount++;
                if(border!=null&&border.equals("0")){
                    sb.append("<tr>");
                }else{
                    if(nowPageCount%2==1){
                        sb.append("<tr bgcolor='#fafafa'>");
                    }else{
                        sb.append("<tr bgcolor='#f6f6f6'>");
                    }
                }
                int colsCount = head.size();
                if(colsCount==0) colsCount = getColsSize();
                for (int j = 1; j <= colsCount; j++) {
                	//获取当前字段内容
                	String colName = (String) getCols(j + "");
					String colText = colName== null || colName.equals("")?null:rst.getString(colName);
					colText= colText==null?"&nbsp;":DataDictConvert(getColsDict(j), colText);
					//判断是否有超连结，组装html语句
                    if (colLinks.size() > 0&&colLinks.containsKey("" + j + "")) {
                    	//当前列上有超链接
                    	sb.append(getColLink(j,colText,rst));
                    }else{
                    	//当前列上有独立链接
                        if(actLink.size()>0&&actLink.containsKey("" + j + "")){
                        	sb.append(getActLink(j,rst));
                        }else{
                            sb.append("<td>" + colText + "</td>");
                        }
                    }
                }

                sb.append("</tr>");
            }
            //如果无论数据多少条都要显示pageSize行记录
            if(flag.equalsIgnoreCase("3")){
                while(pageSize-nowPageCount>0){
                    nowPageCount++;
                    if(border.equals("0")){
                        sb.append("<tr>");
                    }else{
                        if(nowPageCount%2==1){
                            sb.append("<tr bgcolor='#fafafa'>");
                        }else{
                            sb.append("<tr bgcolor='#f6f6f6'>");
                        }
                    }
                    int colsCount = head.size();
                    if(colsCount==0) colsCount = getColsSize();
                    for (int j = 1; j <= colsCount; j++) {
                        sb.append("<td>&nbsp;</td>");
                    }
                    sb.append("</tr>");                    
                }
            }
            
            
            sb.append("</table>");
            sb.append("</td>");
            sb.append("</tr>");
            sb.append("\n<!--PageSelect-->\n");
            sb.append(getPageSelect(page));	//<tr></tr>
            sb.append("\n");
            sb.append("\n<!--end page-->\n");
        } catch (Exception e) {
            System.out.println("[com.zjtz.Pager:Pager Bean]Error Found:"
                    + e.toString());
            sb.append("</table>");
            sb.append("</td>");
            sb.append("</tr>");
            sb.append("\n");
        }finally{
            DBUtil.closeResult(rst);
            DBUtil.closeConnection(con);
        }
        return sb.toString();
    }
    /**
     * 获取带有超连结的指定列编号上html内容
     * @param colId		列编号
     * @param colText	列内容
     * @param rs		当前列数据结果
     * @return
     * @throws SQLException 
     */
    private String getActLink(int colId,ResultSet rs) throws SQLException{
        String linkStr = (String) actLink.get("" + colId + "");
        String colText = (String) actLinkName.get("" + colId);
        if(StringUtil.getSubtringCount(linkStr,"?")==0){
            linkStr = linkStr + "?";
        }
        String parasStr = "";
        Iterator parameters = this.actLinkparas.keySet().iterator();
        do {
            if (!parameters.hasNext())
                break;
            String tempkey = parameters.next().toString();
            if (tempkey.indexOf(colId + "$") != -1) {
                String tempname = tempkey.substring(tempkey
                        .indexOf("$") + 1);
                String temppara = rs
                        .getString((String) actLinkparas
                                .get(tempkey));
                if (temppara == null)
                    temppara = "";
                parasStr = parasStr
                        + (tempname + "=" + temppara + "&");
            }
        } while (true);
        linkStr = linkStr + parasStr;
        StringBuffer sb = new StringBuffer(256);
        if (openWindow){
            sb.append("<td><a href=\"javascript:void(0)\" onclick=\"javascript:window.open('");
            sb.append(linkStr);
            sb.append("','detail','");
            sb.append(Winproperty + "')\">" + colText + "</a></td>");
        }else{
            sb.append("<td><a href=\"");
            sb.append(linkStr);
            sb.append("\" target=\"");
            sb.append(target);
            sb.append("\">");
            sb.append(colText);
            sb.append("</a></td>");
        }
        return sb.toString();
    }
    /**
     * 获取带有超连结的指定列编号上html内容
     * @param colId		列编号
     * @param colText	列内容
     * @param rs		当前列数据结果
     * @return
     * @throws SQLException 
     */
    private String getColLink(int colId,String colText,ResultSet rs) throws SQLException{
        String linkStr = (String) colLinks.get("" + colId + "");
        if(StringUtil.getSubtringCount(linkStr,"?")==0){
            linkStr = linkStr + "?";
        }
        String parasStr = "";
        Iterator parameters = colLinkparas.keySet().iterator();
        do {
            if (!parameters.hasNext())
                break;
            String tempkey = parameters.next().toString();
            if (tempkey.indexOf(colId + "$") != -1) {
                String tempname = tempkey.substring(tempkey
                        .indexOf("$") + 1);
                String temppara = rs
                        .getString((String) colLinkparas
                                .get(tempkey));
                if (temppara == null)
                    temppara = "";
                parasStr = parasStr
                        + (tempname + "=" + temppara + "&");
            }
        } while (true);
        linkStr = linkStr + parasStr;
        StringBuffer sb = new StringBuffer(256);
        if (openWindow){
            sb.append("<td><a href=\"javascript:void(0)\" onclick=\"javascript:window.open('");
            sb.append(linkStr);
            sb.append("','detail','");
            sb.append(Winproperty + "')\">" + colText + "</a></td>");
        }else{
            sb.append("<td><a href=\"");
            sb.append(linkStr);
            sb.append("\" target=\"");
            sb.append(target);
            sb.append("\">");
            sb.append(colText);
            sb.append("</a></td>");
        }
        return sb.toString();
    }
    /**
     * 获取分页查询结果集合的页面html片断，按照一页一条纪录方式显示。
     * 执行本方法前，需要作出如下准备：
     * (1)通过setSQL()传递分页查询的SQL语句(或通过配置文件)，必须
     * (2)通过setCountSql()传递获取数据记录总数的sql语句(或通过配置文件)，必须
     * (3)通过setOrder()设置获取数据的排序条件，可以不传递
     * (4)通过setPageCols设置显示列的属性，通过配置文件传时可以不传递，但将不显示标题列
     * @param page  要显示的页码
     * @return
     */
    private String getPage2(int page)throws AppException{
//        //获取总页数信息
//        recordCount = getRowCount();
//        totalPage = recordCount % pageSize != 0 ? recordCount / pageSize
//                + 1 : recordCount / pageSize;
//
        if (page == currentPage){
            if (order.equals("desc"))
                order = "asc";
            else if (order.equals("asc"))
                order = "desc";
        }
        if (order.equals("desc"))
            imgPath += pictureDown;
        if (order.equals("asc"))
            imgPath += pictureUp;
        //如果页面指定按某列排序，忽略后台的排序
        if(orderBy!=null&&!orderBy.equals("")){
            if(getCols(orderBy)!=null){
                setOrder((String)(getCols(orderBy)),order);
            }
        }   
        String result = "";
        result = Tableproperty;
        Connection con = null;
        ResultSet rst = null;        
        try {
            con = DBUtil.getConnection();
            rst = getPageData(con,page,pageSize);   
            StringBuffer sb = new StringBuffer();
            if (title.trim().length() > 0) {
               sb.append("<tr align=\"");
               sb.append(titleAlign==null?"":titleAlign);
               sb.append("\" bgcolor=\"");
               sb.append(titleBGColor==null?"":titleBGColor);
               sb.append("\"><th colspan=\"");
               sb.append(4);
               sb.append("\">");
               sb.append(title==null?"":title);
               sb.append("</th></tr>");
               sb.append("\n");
               sb.append("<tr bgcolor=\"");
               sb.append(headBGColor==null?"":headBGColor);
               sb.append("\" >");
               sb.append("\n");
            }
            result=result+sb.toString();
            if (rst.next()){
                result +="<tr>";
                for (int j = 1; j <= getColsSize(); j++) {
                   String s;
                   if (colLinks.size() > 0) {
                       if (colLinks.containsKey("" + j + "")) {
                           String linkStr = (String) colLinks.get("" + j + "");
                           if (!linkStr.endsWith("?"))
                               linkStr = linkStr + "?";
                           String parasStr = "";
                           Iterator parameters = colLinkparas.keySet().iterator();
                           do {
                               if (!parameters.hasNext())
                                   break;
                               String tempkey = parameters.next().toString();
                               if (tempkey.indexOf(j + "$") != -1) {
                                   String tempname = tempkey.substring(tempkey
                                           .indexOf("$") + 1);
                                   String temppara = rst
                                           .getString((String) colLinkparas
                                                   .get(tempkey));
                                   if (temppara == null)
                                       temppara = "";
                                   parasStr = parasStr
                                           + (tempname + "=" + temppara + "&");
                               }
                           } while (true);
                           linkStr = linkStr + parasStr;
                           String val = rst.getString((String) getCols(j));
                           if (val == null){
                               val = "&nbsp;";
                           }else{
                               val = DataDictConvert(getColsDict(j),val);
                           }
                           if (openWindow)
                               result = result
                                       + ("<td><a href=\"javascript:void(0)\" onclick=\"javascript:window.open('"
                                               + linkStr
                                               + "','detail','"
                                               + Winproperty + "')\">" + val + "</a></td>");
                           else
                               result = result
                                       + ("<td><a href=\"" + linkStr
                                               + "\" target=\"" + target
                                               + "\">" + val + "</a></td>");
                           continue;
                       }                            
                       s = "<td width='"+colwidths.get("" + j + "")+"'>"+head.get("" + j + "").toString()+"</td><td>"+rst.getString((String) getCols( j + ""))+"</td>";
                       if (j%2==0){
                            s =s+"</tr><tr>";
                        }
                       result = result +  s ;
                       continue;
                   }
                   s = "<td width='"+colwidths.get("" + j + "")+"'>"+head.get("" + j + "").toString()+"</td><td>"+rst.getString((String) getCols( j + ""))+"</td>";
                   if (j%2==0){
                    s =s+"</tr><tr>";
                   }
                   result = result +  s ;
               }
            }             
            result = result + "</table></td></tr>";
            result = result + "\n";
            result = result + getPageSelect(page);           
       } catch (Exception e) {
           System.out.println("[com.zjtz.Pager:Pager Bean]Error Found:"
                   + e.toString());
       }finally{
           DBUtil.closeResult(rst);
           DBUtil.closeConnection(con);
       }
       return result;
   }
    /**
     * @see org.radf.plat.commons.Pager#setParaByFileKey
     */
    public void setParaByFileKey(String key)throws AppException{
        super.setParaByFileKey(key);
        pageSize = getPageSize();
    }
    /**
     * 设置需要返回显示的表单列中列元素基本信息(包括标题名称、数据库字段、占据宽度等)，
     * 所有返回数据全部以字符串保存。
     * 当通过配置文件传递参数时，可以不传递本信息，但页面将不显示文字描述。
     * @param index         列显示的顺序编号
     * @param headName      列的标题文字描述
     * @param colsName      列的数据库字段名
     * @param colWidth      列的宽度，可以用象素也可以用比例
     */
    public void setPageCols(String index,String headName,String colsName,String colWidth){
        setPageCols(index,headName,colsName,colWidth,PagerUtil.TYPE_ST_);
    }
    /**
     * 设置需要返回显示的表单列基本信息。
     * 当通过配置文件传递参数时，可以不传递本信息，但页面将不显示标题行
     * @param index         列显示的顺序编号
     * @param headName      列的标题文字描述，任何一个为null则整个标题不显示(""显示)
     * @param colsName      列的数据库字段名
     * @param colwidth      列的宽度，可以用象素也可以用比例
     * @param type          列的数据类型
     */
    public void setPageCols(String index,String headName,String colsName,String colwidth,int type){
        head.put(index, headName);
        colwidths.put(index, colwidth);
//        if(colsName!=null){
            addColsValue(index,colsName);
            if(type==0){
                addColsType(index,PagerUtil.TYPE_ST_);
            }else{
                addColsType(index,type);
            }
//        }else{
//        	addColsValue(index,null);
//        }
    }
    public void setColLinks(String index, String value) {
        colLinks.put(index, value);
    }
    /**
     * 表单任意编号字段上，设置超链接所需要的参数名称参数值
     * @param index 格式:字段顺序编号$参数名称
     * @param value     参数值
     */
    public void setColLinkparas(String index, String value) {
        colLinkparas.put(index, value);
    }
    
    
    
    /**
     * 设定按照指定列进行排序，只能对一个列设置
     * 此排序级别高于setOrder排序方法，设置后将导致setOrder方法失效
     * @param orderBy   排序的字段编号
     * @param type      排序方式，asc或者desc
     */
    public void setOrderBy(String orderBy,String type) {
        try {
            Integer.parseInt(orderBy);
            this.orderBy = orderBy;
            this.order = type;
        } catch (Exception exception) {
        }
    }
    /**
     * 设置每页显示的记录条数
     * @param pageSize
     */
    public void setPageSize(int pageSize) {
        if (pageSize > 0)
            this.pageSize = pageSize;
    }

    public void setCurrentPage(int currentPage) {
        if (currentPage > 0)
            this.currentPage = currentPage;
    }

    public void setPageLink(String pageLink) {
        this.pageLink = pageLink;
    }
    /**
     * 设置排序图片所在路径，如果不设置此参数则需要每个图片中自身包含路径
     * @param imgPath
     */
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
    /**
     * 设置升序排序图片相对于setImgPath路径下的文件名，若没有设置setImgPath则需要含路径设置
     * @param pictureUp
     */
    public void setPictureUp(String pictureUp) {
        this.pictureUp = pictureUp;
    }
    /**
     * 设置降序排序图片相对于setImgPath路径下的文件名，若没有设置setImgPath则需要含路径设置
     * @param pictureDown
     */
    public void setPictureDown(String pictureDown) {
        this.pictureDown = pictureDown;
    }
    /**
     * 表单上超链接创建的表单是否通过新窗口方式打开
     * @param openWindow
     */
    public void setOpenWindow(boolean openWindow) {
        this.openWindow = openWindow;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    /**
     * 设置弹出窗口的页面属性，变量名即html页面中的属性
     * @param height
     * @param width
     * @param menubar
     * @param scrollbars
     * @param status
     * @param titlebar
     * @param resizable
     * @return 生成的窗口页面属性字符串
     */
    public String setWinProperty(String height,String width,String menubar,String scrollbars,String status,String titlebar,String resizable ){
        StringBuffer sb = new StringBuffer(256);
        sb.append("height=");
        sb.append((height==null||height.equals(""))?"480":height);
        sb.append(",width=");
        sb.append((width==null||width.equals(""))?"640":width);
        sb.append(",menubar=");
        sb.append((menubar==null||width.equals(""))?"no":menubar);
        sb.append(",scrollbars=");
        sb.append((scrollbars==null||scrollbars.equals(""))?"yes":scrollbars);
        sb.append(",status=");
        sb.append((status==null||status.equals(""))?"no":status);
        sb.append(",titlebar=");
        sb.append((titlebar==null||titlebar.equals(""))?"no":titlebar);
        sb.append(",resizable=");
        sb.append((resizable==null||resizable.equals(""))?"no":resizable);
        this.Winproperty = sb.toString();
        sb = null;
        return this.Winproperty;
    }
    /**
     * 设置html页面中table表单属性，变量名即html页面中的属性
     * @param bordercolorlight
     * @param bordercolordark
     * @param align
     * @param id
     * @param class0            html中属性为class
     * @param bgcolor         table中的bgcolor属性
     * @param cellspacing
     * @param cellpadding
     * @param border
     * @param width
     * @return 生成的table部分字符串
     */
    public String setTableproperty(String bordercolorlight,String bordercolordark,String align,String id,
            String class0,String bgcolor,String cellspacing,String cellpadding,String border,String width){
        StringBuffer sb = new StringBuffer(256);
        sb.append("<tr><td>");
        sb.append("<table");
        this.border = border==null?"0":border;
        if(class0!=null){
            sb.append(" class=\"");
            sb.append(class0);
            sb.append("\"");
        }else{
            if(bgcolor!=null){
                sb.append(" bgcolor=\"");
                sb.append(bgcolor);
                sb.append("\"");
            }
            if(bordercolorlight!=null){
                sb.append(" bordercolorlight=\"");
                sb.append(bordercolorlight);
                sb.append("\"");
            }
            if(bordercolordark!=null){
                sb.append(" bordercolordark=\"");
                sb.append(bordercolordark);
                sb.append("\"");
            }
            if(align!=null){
                sb.append(" align=\"");
                sb.append(align);
                sb.append("\"");
            }
            if(width!=null){
                sb.append(" width=\"");
                sb.append(width);
                sb.append("\"");
            }
            sb.append(" border=\"");
            sb.append(this.border);
            sb.append("\"");
        }
        if(id!=null){
            sb.append(" id=\"");
            sb.append(id);
            sb.append("\"");
        }
        sb.append(" cellspacing=\"");
        sb.append(cellspacing==null?"0":cellspacing);
        sb.append("\"");
        sb.append(" cellpadding=\"");
        sb.append(cellpadding==null?"0":cellpadding);
        sb.append("\"");
        sb.append(">");
        sb.append("\n");
        this.Tableproperty = sb.toString();
        sb = null;
        return this.Tableproperty;
    }
    /**
     * 设定分页表单标题栏参数，各参数含义和html脚本表单下对应字段含义相同
     * @param titleAlign
     * @param titleBGColor
     * @param title
     * @param headBGColor
     * @param headFontColor
     */
    public void setTableHead(String titleAlign, String titleBGColor,
            String title, String headBGColor,String headFontColor){
        if (titleAlign == null)
            this.titleAlign = "center";
        else
            this.titleAlign = titleAlign;
        
        if (title == null)
            this.title = "";
        else
            this.title = title;
        
        if (titleBGColor == null)
            this.titleBGColor = "";
        else
            this.titleBGColor = titleBGColor;
            
        if (headBGColor == null)
            headBGColor = "";
        else
            this.headBGColor = headBGColor;
        
        if(headFontColor == null)
            this.headFontColor = "";
        else
            this.headFontColor = headFontColor;
    }
    
    /**
     * 生成表单头信息(标题栏)，数据来源于setPageCols(String,String,String,String)。
     * 如果来源数据中任何一列的title为null则不显示表单头(为""照常显示)
     * @param page
     * @return
     */
    private String buildTableHead(int page) {
        StringBuffer sb1 = new StringBuffer(256);
        if (title!=null&&title.trim().length() > 0) {
            sb1.append("<tr align=\"");
            sb1.append(titleAlign==null?"":titleAlign);
            sb1.append("\" bgcolor=\"");
            sb1.append(titleBGColor==null?"":titleBGColor);
            sb1.append("\"><th colspan=\"");
            sb1.append(head.size());
            sb1.append("\">");
            sb1.append(title==null?"":title);
            sb1.append("</th></tr>");
            sb1.append("\n");
            sb1.append("<tr bgcolor=\"");
            sb1.append(headBGColor==null?"":headBGColor);
            sb1.append("\" >");
        }else{
        	sb1.append("<tr>");
        }
        boolean isView = true;
        for (int i = 1; i <= head.size(); i++) {
            String s = (String)head.get("" + i);
            if(s==null){
                isView = false;
            }
        }
        StringBuffer sb2 = new StringBuffer(256);
        for (int i = 1; i <= head.size(); i++) {
            //<tr>
            //.......
            //<th width=colwidths><font color=headFontColor><a href=pageLink>head</a></font></th>
            //......
            //</tr>
            sb2.append("<th width=\"");
            sb2.append(colwidths.get("" + i + ""));
            sb2.append("\"><font color=\"");
            sb2.append(headFontColor==null?"":headFontColor);
            sb2.append("\">");
            if(isView){
            	if(orderBy!=null&&!orderBy.equals("")){
                    sb2.append("<a href=\"");
                    if(pageLink.indexOf("?")<0){
                        sb2.append(pageLink + "?pnum=" + page);
                    }else{
                        sb2.append(pageLink + "&pnum=" + page);
                    }
                    sb2.append("&orderby=" + i);
                    sb2.append("&order=" + order);
                    sb2.append("&currentPage=" + page + "\">");
                    
                    if (orderBy.trim().length() > 0 && Integer.parseInt(orderBy) == i)
                        sb2.append("<img border=\"0\" src=\"" + imgPath + "\">");
            	}else{
            		sb2.append("<a >");
            	}
                sb2.append(head.get("" + i + "").toString() + "</a></font>");
            }
            sb2.append("</th>");
        }
        sb1.append(sb2);
        sb1.append("</tr>\n");
        return sb1.toString();
    }
    
    /**
     * 设置页脚显示的分页信息描述栏
     * 
     * @param page
     *            当前所在页数
     * @return
     */
    private String getPageSelect(int page)throws AppException {
        StringBuffer sb = new StringBuffer(256);
        if(buttom){
            //获取总页数信息
            recordCount = getRowCount();
            totalPage = recordCount % pageSize != 0 ? recordCount / pageSize
                    + 1 : recordCount / pageSize;

            
        	sb.append("<tr><td><table width='95%'><tr align=center>");
            if (totalPage != 0) {
            	sb.append("\n<script language=javascript>");
            	sb.append("function goto_page() {");
            	sb.append("  if (PagerHtml_page.value!=\"\"){");
            	sb.append("    for(i=0;i<PagerHtml_page.value.length;i++){");
            	sb.append("      if  ((PagerHtml_page.value.charAt(i)<\"0\")||(PagerHtml_page.value.charAt(i)>\"9\"))");
            	sb.append("        {alert(\"页数必须为合法数字\");return false;}}}else{");
            	sb.append("  ");
            	sb.append("PagerHtml_page.value = 1;}");
            	sb.append("window.location.href='");
                if(pageLink.indexOf("?")<0){
                    sb.append(pageLink +"?pnum='+PagerHtml_page.value+'&orderby=");
                }else{
                    sb.append(pageLink +"&pnum='+PagerHtml_page.value+'&orderby=");
                }
                sb.append(orderBy);
                sb.append("&order=");
                sb.append(order);
                sb.append("&currentPage=");
                sb.append(page);
            	sb.append("';}</script>");
            	
                sb.append("<td width='35%' nowrap>共" + totalPage + "页");
                sb.append(recordCount + "条记录，跳转到第");
                sb.append("<INPUT id=CP size=2 name=PagerHtml_page value="+page+">页&nbsp;");
                sb.append("<INPUT onclick=goto_page() type=button value=go>");
                sb.append("</td>");
                if (page <= 1)
                    sb.append("<td width='35%' nowrap>[首页]&nbsp;[上页]");
                else {
                    sb.append("<td width='35%' nowrap><a href=\"");
                    if(pageLink.indexOf("?")<0){
                        sb.append(pageLink +"?pnum=1&orderby=");
                    }else{
                        sb.append(pageLink +"&pnum=1&orderby=");
                    }
                    sb.append(orderBy);
                    sb.append("&order=");
                    sb.append(order);
                    sb.append("&currentPage=");
                    sb.append(page);
                    sb.append("\">[首页]</a>&nbsp;");
                    sb.append("<a href=\"");
                    if(pageLink.indexOf("?")<0){
                        sb.append(pageLink + "?pnum=");
                    }else{
                        sb.append(pageLink + "&pnum=");
                    }
                    sb.append(page - 1);
                    sb.append("&orderby=" + orderBy + "&order=" + order);
                    sb.append("&currentPage=" + page + "\">[上页]</a>");
                }
                //sb.append("<select name=\"pager\" onchange=\"javascript:window.location.href='");
//                if(pageLink.indexOf("?")<0){
//                    sb.append(pageLink + "?pnum='+this.value+'&orderby=");
//                }else{
//                    sb.append(pageLink + "&pnum='+this.value+'&orderby=");
//                }
//                sb.append(orderBy + "&order=" + order + "&currentPage=");
//                sb.append(page + "'\">");
//                for (int k = 1; k <= totalPage; k++)
//                    if (page == k)
//                        sb.append("\n<option selected value=\"" + k + "\">" + k);
//                    else
//                        sb.append("\n<option value=\"" + k + "\">" + k);
//                sb.append("</select>页 \n");
                
                if (page >= totalPage)
                    sb.append("[下页]&nbsp;[末页]&nbsp;</td>");
                else{
                    if(pageLink.indexOf("?")<0){
                        sb.append("<a href=\"" + pageLink + "?pnum=" + (page + 1));
                    }else{
                        sb.append("<a href=\"" + pageLink + "&pnum=" + (page + 1));
                    }
                    sb.append("&orderby=" + orderBy + "&order=" + order);
                    sb.append("&currentPage=" + page + "\">[下页]&nbsp;</a>");
                    if(pageLink.indexOf("?")<0){
                        sb.append("<a href=\"" + pageLink + "?pnum=" + totalPage);
                    }else{
                        sb.append("<a href=\"" + pageLink + "&pnum=" + totalPage);
                    }
                    sb.append("&orderby=" + orderBy + "&order=" + order);
                    sb.append("&currentPage=" + page + "\">[末页]&nbsp;</a></td>");
                }
            } else {
                sb.append("<p align=center>对不起，没有找到满足条件的数据，请重新查询！</p>");
            }
        	sb.append("</tr></table></td></tr>");
        }
        return sb.toString();
    }
    /**
     * 设置操作性链接参数，只有在setPageCols大于实际数据库取出列数之后才有效
     * @param key       列编号
     * @param name      显示的名称
     * @param url       链接
     */
    public void setActLink(String key,String name,String url) {
        actLink.put(key,url);
        actLinkName.put(key,name);
    }
    /**
     * 设置操作性链接参数，只有在setPageCols大于实际数据库取出列数之后才有效
     * @param key       参数标志，格式："字段编号$参数名称"
     * @param value     参数值来源(列名)
     */
    public void setActLinkparas(String key,String value) {
        actLinkparas.put(key,value);
    }
    /**
     * 设置是否显示页脚(分页的上页、下页选择部分)
     * @param buttom The buttom to set.
     */
    public void setButtom(boolean buttom) {
        this.buttom = buttom;
    }
}
