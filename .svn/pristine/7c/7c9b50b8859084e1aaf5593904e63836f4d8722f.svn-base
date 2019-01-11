package org.radf.plat.log;


/**
 * <p>Title: LogUtil</p>
 * <p>Description: this class is a static class to cache InitialContext, Queue, QueueConnectionFactory </p>
 * @author zqb
 * @version 0.1
 */
import java.util.Hashtable;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.radf.plat.util.global.GlobalNames;

public abstract class LogUtil {
    private static InitialContext context;
    private static javax.jms.Queue myQueue;
    private static javax.jms.QueueConnectionFactory myConnectionFactory;
    
    private LogUtil(){
        init();
    }
    
    /**
     * 消息队列的初始化
     */
    public static void init(){
        getContext();
        getQueue();
        getConnectionFactory();
    }
    /**
     * 接收
     * @return
     */
    public static javax.jms.Queue getQueue() {
        try{
            if(myQueue == null&&context!=null){
                myQueue = (Queue)context.lookup(GlobalNames.JMS_QUEUE_NAME);
                System.out.println("myQueue = " + myQueue);
            }
        }catch(NamingException ne){
            System.out.println("不能绑定Queue："+ne);
        //    ne.printStackTrace();
        }
        return myQueue;
    }
    /**
     * 接收
     * @return
     */
    public static javax.jms.QueueConnectionFactory getConnectionFactory() {
        try{
            if(myConnectionFactory == null&&context!=null){
                myConnectionFactory = (QueueConnectionFactory)context.lookup(GlobalNames.JMS_CONTECTION_FACTROY);
                System.out.println("myConnectionFactory = " + myConnectionFactory);
            }
        }catch(NamingException ne){
            System.out.println("不能绑定QueueConnectionFactory："+ne);
            //ne.printStackTrace();
        }

        return myConnectionFactory;
    }
    
    /**
     * @return Returns the context.
     */
    public static InitialContext getContext() {
        if (context == null) {
            try{
                Hashtable properties = new Hashtable();
                properties.put(Context.INITIAL_CONTEXT_FACTORY,
                        GlobalNames.NAME_INITIAL_FACTORY);
                properties.put(Context.PROVIDER_URL, GlobalNames.NAME_URL_PROVIDER);
                properties.put(Context.SECURITY_PRINCIPAL, GlobalNames.JMS_USER_NAME);
                properties.put(Context.SECURITY_CREDENTIALS, GlobalNames.JMS_PASSWORD);
                context = new InitialContext(properties);
            }catch(NamingException ne){
                System.out.println("不能绑定InitialContext："+ne);
                //ne.printStackTrace();
            }
        }
        return context;
    }
    
    
    public static QueueConnection getConnection() throws JMSException {
        if (myConnectionFactory != null) {
            QueueConnection connection = myConnectionFactory
                    .createQueueConnection();
            connection.start();
            return connection;
        } else {
            return null;
        }
    }

    public static QueueSession getSession(QueueConnection connection)
            throws JMSException {
        if (connection != null) {
            QueueSession session = connection.createQueueSession(false,
                    Session.AUTO_ACKNOWLEDGE);
            return session;
        } else {
            return null;
        }
    }

    public static QueueReceiver getQueueReceiver(QueueSession session,
            Queue queue) throws JMSException {
        if (session != null) {
            QueueReceiver receiver = session.createReceiver(queue);
            return receiver;
        } else {
            return null;
        }
    }
}
