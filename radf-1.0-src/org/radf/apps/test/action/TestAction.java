/**
 * 测试应用
 * @author zqb
 * @version 1.0
 */
package org.radf.apps.test.action;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.radf.apps.test.entity.TestEntity;
import org.radf.apps.test.facade.TestFacade;

import org.radf.plat.commons.DBUtil;
import org.radf.plat.commons.FileUtil;
import org.radf.plat.commons.TypeCast;
import org.radf.plat.sieaf.envelop.RequestEnvelop;
import org.radf.plat.sieaf.envelop.ResponseEnvelop;
import org.radf.plat.sieaf.event.EventResponse;
import org.radf.plat.util.action.ActionLeafSupport;

public class TestAction extends ActionLeafSupport {
    public ActionForward login(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ActionForward forward = new ActionForward();
        RequestEnvelop requestEnvelop = new RequestEnvelop();
        HashMap mapRequest = new HashMap();
        // 页面参数传递，考虑是否用From，或者页面直接传递对象
        TestEntity dto = new TestEntity();
        dto.setLoginName(request.getParameter("loginName"));
//        try{
//            test();
//        }catch(Exception e){
//            e.printStackTrace();
//        }
        
        // 数据合法性校验，尽量放在页面js实现，不能页面处理的除非null等跳转判断，否则尽量IMP中处理
        if (dto.getLoginName() == null||dto.getLoginName().equalsIgnoreCase("")) {
            //拼装出错信息
            request.setAttribute(ActionLeafSupport.ERROR,ExceptionSupport("输入的用户名为空"));
            forward =  mapping.findForward(FAILURE);
        }else{
            // 传递页面参数到入口结构requestEnvelop中，如果用imp固定的增删改查传递必须是dto
            mapRequest.put("dto", dto);
            requestEnvelop.setBody(mapRequest);
            // 获取facade服务，调用接口
            TestFacade facade = (TestFacade) getService("TestFacade");
            ResponseEnvelop resEnv = facade.userLogin(requestEnvelop);
            // 返回参数处理
            EventResponse returnValue = processRevt(resEnv);
            // 根据返回结果是否成功，分别调用不同的页面处理机制
            if (returnValue.isSucessFlag()) {
                // 获取IMP返回的参数，并返回到页面中。如果IMP返回的是HashMap，则结果getBody即可获得，如果不是HashMap，则通过getBody().get("out")获得对应的对象
                HashMap ret = returnValue.getBody();// IMP返回的就是HashMap
                dto = (TestEntity)ret.get("dto");
                request.setAttribute("dto", dto);
                forward = mapping.findForward(SUCCESS);
            } else {
                //拼装出错信息
                request.setAttribute(ActionLeafSupport.ERROR,returnValue.getMsg());
                forward = mapping.findForward(FAILURE);
            }
        }
        return forward;
    }
    
    private void test(){
        try{
            save();
            read();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
        }
    }
    private void read(){
        String sql = "select id,clob1,blob1 from zqb order by id asc";
        Connection con = null;
        try{
            con = DBUtil.getConnection();
            Collection list = DBUtil.querySQL(con,sql);
            Iterator it = list.iterator();
            while (it.hasNext()) {
                HashMap map = (HashMap)it.next();
                Clob clob = (Clob)map.get("2");
                Blob blob = (Blob)map.get("3");
                //CLOB
                String clobString = TypeCast.clobToString(clob);
                System.out.println(clobString);
                //BLOB
                InputStream is = TypeCast.blobToInputStream(blob);
                FileUtil.saveFile("e:\\2.gif",is);
                byte[] bytes = TypeCast.blobToBytes(blob);
                FileUtil.saveFile("e:\\3.gif",bytes);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            DBUtil.closeConnection(con);
        }
    }
 
    
    private void save(){
        Connection con = null;
        PreparedStatement pstmt = null;
        
        int id = 1;
        String sql = "insert into zqb(id,CLOB1,BLOB1)values(?,EMPTY_CLOB(),EMPTY_BLOB())";
        try{
            con = DBUtil.getJDBCConnection();
            DBUtil.beginTrans(con);
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,id);
            pstmt.executeUpdate();
            pstmt = null;
            
            byte[] bytes = FileUtil.readFile("e:\\1.gif");
            
            DBUtil.saveClob(con,"zqb","clob1","id = "+id, id+"$" + buildClob());
            DBUtil.saveBlob(con,"zqb","blob1","id = "+id, bytes);
            DBUtil.commit(con);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            DBUtil.closeStatement(pstmt);
            DBUtil.rollback(con);
            DBUtil.closeConnection(con);
        }
    }
    private String buildClob(){
        StringBuffer sb = new StringBuffer(10000);
        for(int i=0;i<1000;i++){
            StringBuffer s = new StringBuffer(10);
            for(int j=0;j<10;j++){
                s.append(j);
            }
            sb.append(s);
        }
        return sb.toString();
    }
}