package org.radf.plat.log;

import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

import org.radf.manage.logMessage.dto.LogMessageDTO;
import org.radf.plat.util.global.GlobalNames;

/**
 * JmsLogAppender
 * 
 * @author zqb
 * @version 1.0
 */

public class JmsLogAppender extends AppenderSkeleton {

    private Queue messageQueue;

    private QueueConnectionFactory qConFactory;

    private QueueSender sender;

    private ObjectMessage msg;

    private QueueConnection qCon;

    private QueueSession session;

    /**
     * initialized the JmsLogAppender instance, and get JMS Connection from JNDI
     * tree.
     */
    public JmsLogAppender() {
        try {
            qConFactory = LogUtil.getConnectionFactory();
            messageQueue = LogUtil.getQueue();
            if (qConFactory != null) {
                qCon = qConFactory.createQueueConnection();
                session = qCon.createQueueSession(false,
                        Session.AUTO_ACKNOWLEDGE);
                sender = session.createSender(messageQueue);
                msg = session.createObjectMessage();
            }
        } catch (JMSException jmsex) {
            if (GlobalNames.DEBUG_OUTPUT_FLAG) {
                System.out
                        .println("Catching JMSException when trying to send Logging info to server");
            }
            jmsex.printStackTrace();
        }
    }

    protected void append(LoggingEvent event) {
        try {
            if (sender != null) {
                LogMessageDTO myMsg = event == null ? null
                        : (LogMessageDTO) event.getMessage();
                if (msg != null) {
                    msg.setObject(myMsg);
                    // ===================================
                    sender.send(msg, DeliveryMode.NON_PERSISTENT, 5, 10000);
                }
            }
        } catch (Exception ex) {
            if (GlobalNames.DEBUG_OUTPUT_FLAG) {
                System.out.println("Loggin Service Exception: ");
                ex.printStackTrace();
            }
        }
    }

    public boolean requiresLayout() {
        return true;
    }

    /**
     * The synchronized modifier avoids concurrent append and close operations
     */
    public synchronized void close() {
        if (this.closed)
            return;
        this.closed = true;
        try {
            if (session != null)
                session.close();
            if (qCon != null)
                qCon.close();
        } catch (Exception e) {
            if (GlobalNames.DEBUG_OUTPUT_FLAG) {
                System.out.println("Error while closing JMSAppender"
                        + e.getMessage());
                e.printStackTrace();
            }
        }
        // Help garbage collection
        session = null;
        qCon = null;
    }
}
