/**
* <p>标题: 使用Socket协议进行数据通讯的外部接口</p>
* <p>说明: </p>
* <p>项目: Rapid Application Development Framework</p>
* <p>版权: Copyright 2008 - 2017</p>
* <p>时间: 2005-11-2511:01:05</p>
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
     * 初始化参数
     * @param ip    访问的目标IP地址
     * @param port  访问的目标端口号
     */
    public SocketClient(String ip,int port){
        this.ip = ip;
        this.port = port;
        this.open = false;
    }
    /**
     * 发送数据
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
            throw new AppException("发送数据失败",e);
        } finally {
            try{
                out.close();
                out = null;
            }catch(Exception e){                
            }
        }
    }
    /**
     * 打开通讯端口，创建socket连接。
     * 如果已经创建则使用原有连接，如果目标机器无法访问或端口占用，则抛出错误
     */
    public void open()throws AppException{
        if(server==null||open==false){
            try{
                server = new Socket(ip,port);
                open = true;
            }catch(UnknownHostException hue ){
                throw new AppException("创建Scoket连接失败1：",hue);
            }catch(IOException io){
                throw new AppException("创建Scoket连接失败2：",io);
            }
        }
    }
    /**
     * 关闭Socket连接
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
