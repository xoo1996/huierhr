/**
* <p>����: ʹ��SocketЭ���������ͨѶ���ⲿ�ӿ�</p>
* <p>˵��: </p>
* <p>��Ŀ: Rapid Application Development Framework</p>
* <p>��Ȩ: Copyright 2008 - 2017</p>
* <p>ʱ��: 2005-11-2511:01:05</p>
*
* @author zqb
* @version 1.0
*/
package org.radf.plat.commons;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import org.radf.plat.util.exception.AppException;

public class SocketClient {
    private String ip;
    private int port;
    private boolean open;
    private Socket server = null;
    /**
     * ��ʼ������
     * @param ip    ���ʵ�Ŀ��IP��ַ
     * @param port  ���ʵ�Ŀ��˿ں�
     */
    public SocketClient(String ip,int port){
        this.ip = ip;
        this.port = port;
        this.open = false;
    }
    /**
     * ��������
     * @param sendstr
     */
    public void send(String sendstr) throws AppException{
        DataOutputStream out = null;
        if(server==null){
            open();
        }
        try{
            out = new DataOutputStream(new BufferedOutputStream (server.getOutputStream()));
            out.writeBytes(sendstr);
            out.flush();
        }catch(IOException e){
            throw new AppException("��������ʧ��",e);
        } finally {
            try{
                out.close();
                out = null;
            }catch(Exception e){                
            }
        }
    }
    /**
     * ��ͨѶ�˿ڣ�����socket���ӡ�
     * ����Ѿ�������ʹ��ԭ�����ӣ����Ŀ������޷����ʻ�˿�ռ�ã����׳�����
     */
    public void open()throws AppException{
        if(server==null||open==false){
            try{
                server = new Socket(ip,port);
                open = true;
            }catch(UnknownHostException hue ){
                throw new AppException("����Scoket����ʧ��1��",hue);
            }catch(IOException io){
                throw new AppException("����Scoket����ʧ��2��",io);
            }
        }
    }
    /**
     * �ر�Socket����
     */
    public void close(){
        if (server!=null){
            try{
                server.close();
            }catch(Exception e){
            }finally{
                server = null;
                open = false;
            }
        }
    }

}
