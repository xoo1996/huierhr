/**
 * <p>标题: JMS消息队列监听</p>
 * <p>说明: 监听日志生成，并将日志记录到数据库中。不需要EJB</p>
 * <p>项目: Rapid Application Development Framework</p>
 * <p>版权: Copyright 2008 - 2017</p>
 * <p>时间: 2005-7-27 15:04:28</p>
 *
 * @author zqb
 * @version 1.0
 */
package org.radf.plat.webcontroller;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.naming.Context;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.radf.manage.logMessage.dao.LogMessageDAO;
import org.radf.manage.logMessage.dto.LogMessageDTO;
import org.radf.plat.log.LogUtil;
import org.radf.plat.util.global.GlobalNames;

public class LogMsgServlet extends HttpServlet implements Servlet,
        MessageListener {

    private Context context;
    private Queue queue;
    private QueueConnectionFactory factory;
    private QueueConnection connection;
    private QueueSession session;
    private QueueReceiver receiver;
    private LogMessageDAO dao = new LogMessageDAO();
    /**
     * 初始化
     */
    public void init() throws ServletException {
        System.out.println("JMS servlet is Init......");
        super.init();
        try {
            context = LogUtil.getContext();
            queue = LogUtil.getQueue();
            factory = LogUtil.getConnectionFactory();
            if(queue!=null&&factory!=null){
                connection = LogUtil.getConnection();
                session = LogUtil.getSession(connection);//connection.createQueueSession(false,Session.AUTO_ACKNOWLEDGE);
                receiver = LogUtil.getQueueReceiver(session, queue);//session.createReceiver(queue);
                receiver.setMessageListener(this);
                System.out.println("JMS servlet is Listening......");
            }else{
                System.out.println("JMS servlet isn't Listening......");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 释放资源
     */
    public void destroy() {
        try {
            connection.close();
        } catch (Exception e) {
        }
    }
    
    /**
     * invock the <code>LogMessageDAO</code> 's method to save the log info into storage
     * @param msg an instance of <code>LogMessage</code> which contain all logging info
     */
    public void onMessage(Message msg) {
        try{
            if (GlobalNames.DEBUG_PERFERMANCE_FLAG) {
                System.out.println("Message Servlet received the request from Client ...\n"+msg);
            }

            ObjectMessage myMsg = (ObjectMessage)msg;
            LogMessageDTO savingMsg = (LogMessageDTO)myMsg.getObject();
            dao.insertLogMessage(savingMsg);    //存储日志
        }catch(Exception ex){
            if (GlobalNames.DEBUG_PERFERMANCE_FLAG) {
                System.out.println("Logging service exception when handle the LogMessage in MSBean");
            }
            ex.printStackTrace();
        }
    }
}
