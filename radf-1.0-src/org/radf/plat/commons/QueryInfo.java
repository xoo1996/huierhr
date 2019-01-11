/**
 * <p>标题: 储存分页信息</p>
 * <p>说明: </p>
 * <p>项目: Rapid Application Development Framework</p>
 * <p>版权: Copyright 2008 - 2017</p>
 * <p>时间: 2005-12-11</p>
 *
 * @author 沈志钢
 * @version 1.0
 */
package org.radf.plat.commons;

import net.sf.hibernate.type.Type;

import org.apache.struts.util.ResponseUtils;

public class QueryInfo extends PageQueryInfo{

	 private static final long _fldchar = 0x3138333134363839L;
	    public static String HQL_KEY = "hqlKey";
	    public static String FORWARD = "forward";
	    public static String TOTAL_NUM = "totalNum";
	    public static String TOTAL_PAGE_NO = "totalPageNo";
	    public static String CUR_PAGE_NO = "curPageNo";
	    public static String TO_PAGE_STATE = "toPageState";
	    public static String OTHER_INFO = "otherInfo";
	    public static String TO_PAGE = "toPage";
	    public static String PAGE_SIZE = "pageSize";
	    private String _fldvoid;
	    private String _fldgoto;
	    private Object _fldnull[];
	    private Type _fldlong[];
	    private String _fldelse;

	    public int getTotalNum()
	    {
	        return getRowCount();
	    }

	    public void setTotalNum(int i)
	    {
	        setRowCount(i);
	    }

	    public int getTotalPageNo()
	    {
	        return getPageCount();
	    }

	    public void setTotalPageNo(int i)
	    {
	        setPageCount(i);
	    }

	    public QueryInfo()
	    {
	    }

	    public String getForward()
	    {
	        return _fldgoto;
	    }

	    public void setForward(String s)
	    {
	        _fldgoto = s;
	    }

	    public String getHqlKey()
	    {
	        return _fldvoid;
	    }

	    public void setHqlKey(String s)
	    {
	        _fldvoid = s;
	    }

	    public String getOtherInfo()
	    {
	        return _fldelse;
	    }

	    public void setOtherInfo(String s)
	    {
	        _fldelse = s;
	    }

	    public StringBuffer getSubmitData()
	    {
	        StringBuffer stringbuffer = new StringBuffer();
	        stringbuffer.append("&hqlKey=");
	        stringbuffer.append(_fldvoid);
	        stringbuffer.append("&forward=");
	        stringbuffer.append(_fldgoto);
	        stringbuffer.append("&totalNum=");
	        stringbuffer.append(getRowCount());
	        stringbuffer.append("&totalPageNo=");
	        stringbuffer.append(getPageCount());
	        stringbuffer.append("&curPageNo=");
	        stringbuffer.append(getCurPageNo());
	        stringbuffer.append("&pageSize=");
	        stringbuffer.append(getPageSize());
	        stringbuffer.append("&otherInfo=");
	        stringbuffer.append(ResponseUtils.filter(_fldelse));
	        return stringbuffer;
	    }

	    public String getFirstSubmitData()
	    {
	        return getSubmitData().append("&toPageState=first").toString();
	    }

	    public String getPreviousSubmitData()
	    {
	        return getSubmitData().append("&toPageState=previous").toString();
	    }

	    public String getNextSubmitData()
	    {
	        return getSubmitData().append("&toPageState=next").toString();
	    }

	    public String getLastSubmitData()
	    {
	        return getSubmitData().append("&toPageState=last").toString();
	    }

	    public String getOrderSubmitData()
	    {
	        return getSubmitData().append("&toPageState=order").toString();
	    }

	    public String getToPageSubmitData()
	    {
	        return getSubmitData().append("&toPageState=toPage").toString();
	    }

	    public String getExcelAllSubmitData()
	    {
	        return getSubmitData().append("&toPageState=expExcelAll").toString();
	    }

	    public String getExpExcelSubmitData()
	    {
	        return getSubmitData().append("&toPageState=expExcel").toString();
	    }

	    public String getStringData()
	    {
	        StringBuffer stringbuffer = new StringBuffer();
	        stringbuffer.append(_fldvoid);
	        stringbuffer.append(";");
	        stringbuffer.append(_fldgoto);
	        stringbuffer.append(";");
	        stringbuffer.append(getRowCount());
	        stringbuffer.append(";");
	        stringbuffer.append(getPageCount());
	        stringbuffer.append(";");
	        stringbuffer.append(getCurPageNo() + 1);
	        stringbuffer.append(";");
	        stringbuffer.append(getPageSize());
	        stringbuffer.append(";");
	        stringbuffer.append(ResponseUtils.filter(_fldelse));
	        return stringbuffer.toString();
	    }

	    public Object[] getHqlParams()
	    {
	        return _fldnull;
	    }

	    public void setHqlParams(Object aobj[])
	    {
	        _fldnull = aobj;
	    }

	    public Type[] getHqlParamTypes()
	    {
	        return _fldlong;
	    }

	    public void setHqlParamTypes(Type atype[])
	    {
	        _fldlong = atype;
	    }

	    public StringBuffer getStateUrl()
	    {
	        StringBuffer stringbuffer = super.getStateUrl();
	        stringbuffer.append("&hqlKey=");
	        stringbuffer.append(_fldvoid);
	        stringbuffer.append("&forward=");
	        stringbuffer.append(_fldgoto);
	        stringbuffer.append("&otherInfo=");
	        stringbuffer.append(ResponseUtils.filter(_fldelse));
	        return stringbuffer;
	    }
}
