/**
 * <p>标题: 关于文件的基本操作类</p>
 * <p>说明: </p>
 * <p>项目: Rapid Application Development Framework</p>
 * <p>版权: Copyright 2008 - 2017</p>
 * <p>时间: 2005-10-2610:07:24</p>
 *
 * @author zqb
 * @version 1.0
 */
package org.radf.plat.commons;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

import javax.servlet.ServletContext;

import org.apache.struts.upload.FormFile;

import org.radf.plat.util.exception.AppException;
import org.radf.plat.util.global.GlobalErrorCode;
import org.radf.plat.util.global.GlobalErrorMsg;
import org.radf.plat.util.global.GlobalNames;

public class FileUtil {

    protected ServletContext m_application;

    private boolean m_denyPhysicalPath;

    public String getPhysicalPath(String filePathName, int option)
            throws IOException {
        String path = new String();
        String fileName = new String();
        String fileSeparator = new String();
        boolean isPhysical = false;
        fileSeparator = System.getProperty("file.separator");
        if (filePathName == null)
            throw new IllegalArgumentException(
                    "There is no specified destination file (1140).");
        if (filePathName.equals(""))
            throw new IllegalArgumentException(
                    "There is no specified destination file (1140).");
        if (filePathName.lastIndexOf("\\") >= 0) {
            path = filePathName.substring(0, filePathName.lastIndexOf("\\"));
            fileName = filePathName
                    .substring(filePathName.lastIndexOf("\\") + 1);
        }
        if (filePathName.lastIndexOf("/") >= 0) {
            path = filePathName.substring(0, filePathName.lastIndexOf("/"));
            fileName = filePathName
                    .substring(filePathName.lastIndexOf("/") + 1);
        }
        path = path.length() != 0 ? path : "/";
        java.io.File physicalPath = new java.io.File(path);
        if (physicalPath.exists())
            isPhysical = true;
        if (option == 0) {
            if (isVirtual(path)) {
                path = m_application.getRealPath(path);
                if (path.endsWith(fileSeparator))
                    path = path + fileName;
                else
                    path = String.valueOf((new StringBuffer(String
                            .valueOf(path))).append(fileSeparator).append(
                            fileName));
                return path;
            }
            if (isPhysical) {
                if (m_denyPhysicalPath)
                    throw new IllegalArgumentException(
                            "Physical path is denied (1125).");
                else
                    return filePathName;
            } else {
                throw new IllegalArgumentException(
                        "This path does not exist (1135).");
            }
        }
        if (option == 1) {
            if (isVirtual(path)) {
                path = m_application.getRealPath(path);
                if (path.endsWith(fileSeparator))
                    path = path + fileName;
                else
                    path = String.valueOf((new StringBuffer(String
                            .valueOf(path))).append(fileSeparator).append(
                            fileName));
                return path;
            }
            if (isPhysical)
                throw new IllegalArgumentException(
                        "The path is not a virtual path.");
            else
                throw new IllegalArgumentException(
                        "This path does not exist (1135).");
        }
        if (option == 2) {
            if (isPhysical)
                if (m_denyPhysicalPath)
                    throw new IllegalArgumentException(
                            "Physical path is denied (1125).");
                else
                    return filePathName;
            if (isVirtual(path))
                throw new IllegalArgumentException(
                        "The path is not a physical path.");
            else
                throw new IllegalArgumentException(
                        "This path does not exist (1135).");
        }

        else {
            return null;
        }

    }
    /**
     * 判断是否是虚拟路径
     * @param pathName
     * @return
     */
    private boolean isVirtual(String pathName) {
        if (m_application.getRealPath(pathName) != null) {
            java.io.File virtualFile = new java.io.File(m_application
                    .getRealPath(pathName));
            return virtualFile.exists();
        }

        else {
            return false;
        }
    }
    /**
     * 判断文件是否存在
     * 
     * @param fileName
     * @return
     */
    public static boolean exists(String fileName){
        File file = new File(fileName);
        if (file.exists()) {
            return true;
        }else{
            return false;
        }
    }
    /**
     * 取当前路径
     * @return
     */
    public static String getCurrentPath() {
        File directory = new File(".");
        String nowPath = "";
        try {
            nowPath = directory.getCanonicalFile().toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return nowPath;
    }
    /**
     * 获取文件扩展名
     * @param fileName
     * @return
     * */
    public static String getFileExtendName(String fileName){
        if (fileName==null){
            return "";
        }else{
            return fileName.substring(fileName.lastIndexOf(".")+1,fileName.length());
        }
    }
    

    /**
     * 创建一个新文件，如果存在则报错
     * 
     * @param filePath
     * @param fileName
     * @return
     */
    public static void createFile(String filePath, String fileName)
            throws AppException {
        String file = null;
        if(filePath==null){
            file = fileName;
        }else{
            file = filePath+File.separator+fileName;
        }
        createFile(file);
    }
    /**
     * 创建一个新文件(含路径)，如果存在则报错
     * 
     * @param fileName  含有路径的文件名
     * @return
     */
    public static void createFile(String fileName)
            throws AppException {
        File f = new File(fileName);
        if (f.exists()) {
            throw new AppException(GlobalErrorCode.FILEEXCEPTIONCODE + 1,
                    GlobalErrorMsg.FILE_EXIST_ERROR);
        } else {
            try {
            	File fileFolder = f.getParentFile();
				if(!fileFolder.exists())
            		fileFolder.mkdirs();	
                f.createNewFile();
            } catch (IOException ie) {
                System.out.println("文件" + fileName + "创建失败："
                        + ie.getMessage());
                throw new AppException(GlobalErrorCode.FILEEXCEPTIONCODE + 2,
                        GlobalErrorMsg.FILE_CREATE_ERROR, ie);
            }
        }
    }   

    /**
     * 创建新文件，如果原来已经存在则删除并替换原来的文件
     * 
     * @param filePath
     * @param fileName
     * @return
     */
    public static void replaceFile(String filePath, String fileName)
            throws AppException {
        File f = new File(filePath, fileName);
        if (f.exists()) {
            if (!f.delete()) {
                throw new AppException(GlobalErrorCode.FILEEXCEPTIONCODE + 3,
                        "无法删除原来已经存在的文件");
            }
        }
        try {
            f.createNewFile();
        } catch (IOException ie) {
            System.out.println("文件" + filePath + fileName + "创建失败："
                    + ie.getMessage());
            throw new AppException(GlobalErrorCode.FILEEXCEPTIONCODE + 4,
                    GlobalErrorMsg.FILE_IO_ERROR, ie);
        }
    }

    /**
     * 创建目录，如果存在则不创建
     * 
     * @param path
     * @return 返回结果null则创建成功，否则返回的是错误信息
     * @return
     */
    public static String createDir(String path) {
        String msg=null;
        File dir = new File(path);
        
        if (dir == null) {
            msg = "不能创建空目录";
            return msg;
        }
        if (dir.isFile()) {
            msg = "已有同名文件存在";
            return msg;
        }
        if (!dir.exists()&&!dir.mkdir()) {
            msg = "目录创建失败，原因不明";
        }
        return msg;
    }
    /**
     * 删除指定目录或文件。
     * 如果要删除是目录，同时删除子目录下所有的文件
     * @file:File 目录
     * @author xbyan
     * */
    public static void deltree(String fileName){
        if (!exists(fileName))
            return;
        File file = new File(fileName);
        if (file.isFile()) {
            file.delete();
        } else {
            File[] sub = file.listFiles();
            if (sub == null || sub.length <= 0) {
                file.delete();
            } else {
                for (int i = 0; i < sub.length; i++) {
                    deltree(sub[i]);
                }
                file.delete();
            }
        }
    }
    /**
     * 删除指定目录或文件。
     * 如果要删除是目录，同时删除子目录下所有的文件
     * @file:File 目录
     * @author xbyan
     * */
    public static void deltree(File file){
        if (!file.exists())
            return;
        if (file.isFile()) {
            file.delete();
        } else {
            File[] sub = file.listFiles();
            if (sub == null || sub.length <= 0) {
                file.delete();
            } else {
                for (int i = 0; i < sub.length; i++) {
                    deltree(sub[i]);
                }
                file.delete();
            }
        }
    }
    /**
     * 从Properties格式配置文件中获取所有参数并保存到HashMap中。
     * 配置中的key值即map表中的key值，如果配置文件保存时用的中文，则返回结果也会转成中文。
     * @param file
     * @return
     * @throws IOException
     */
    public static HashMap readPropertyFile(String file) throws IOException{
        HashMap map = new HashMap();
        InputStream is = FileUtil.class.getResourceAsStream(file);
        Properties properties = new Properties();
        properties.load(is);
        Enumeration en = properties.propertyNames();
        while( en.hasMoreElements()){
            String key=(String)en.nextElement();
            String code = new String(properties.getProperty(key).getBytes("ISO-8859-1"),"gbk");
            map.put(key,code);
            if (GlobalNames.DEBUG_PERFERMANCE_FLAG) {
                System.out.println(file+"["+key+"]："+code);
            }
        }
        return map;
    }
    /**
     * 从文件读取数据，并将结果保存在二进制数组
     * @param filename
     * @return
     * @throws IOException
     */
    public static byte[] readFile(String filename) throws IOException {
        if (filename == null || filename.equals("")) {
            throw new IOException("无效的文件路径");
        }
        File file = new File(filename);
        long len = file.length();
        byte[] bytes = new byte[(int) len];
        BufferedInputStream bufferedInputStream = new BufferedInputStream(
                new FileInputStream(file));
        int r = bufferedInputStream.read(bytes);
        if (r != len)
            throw new IOException("读取文件不正确");
        bufferedInputStream.close();
        return bytes;
    }

//    /**
//     * 读取文本文件
//     * @param string
//     * @return String
//     * @throws IOException
//     */
//    public static String readTextFile1(String fileName) // 从文本文件中读取内容
//    {
//        String readStr = "";
//        try {
//            File file = new File(fileName);
//            String read = "";
//            FileReader fileread = new FileReader(file);
//            BufferedReader bufread = new BufferedReader(fileread);
//            while ((read = bufread.readLine()) != null) {
//                readStr = readStr + read +"\n";
//            }
//        } catch (Exception d) {
//            System.out.println(d.getMessage());
//        }
//
//        return readStr; //返回从文本文件中读取内容
//    }
    /**
     * 读文本文件，未完
     * 
     * @param filePath
     * @param fileName
     */
    public static String readTextFile(String file) throws AppException {
        BufferedReader br = null;
        try {
            FileInputStream fis = new FileInputStream(file);
            InputStreamReader in = new InputStreamReader(fis);
            br = new BufferedReader(in);

            String data = null;
            StringBuffer sb = new StringBuffer();
            while ((data = br.readLine()) != null) {
                sb.append(data);
                sb.append("\n");
            }
            br.close();
            return sb.toString();
        } catch (FileNotFoundException fe) {
            throw new AppException(GlobalErrorCode.FILEEXCEPTIONCODE + 7,
                    GlobalErrorMsg.FILE_IO_ERROR, fe);
        } catch (IOException ie) {
            throw new AppException(GlobalErrorCode.FILEEXCEPTIONCODE + 8,
                    GlobalErrorMsg.FILE_IO_ERROR, ie);
        }
    }

    /**
     * 将数据写入文件
     * @param fileName
     * @param inputstream
     * @throws AppException
     */
    public static void saveFile(String fileName, InputStream inputstream)
            throws AppException {
        if(inputstream==null&&GlobalNames.DEBUG_OUTPUT_FLAG){
            System.out.println("FileUtil.saveFile：InputStream is null");
            return;
        }
        try{
            createFile(fileName);
        }catch(AppException ae){
            //创建的文件存在
        }
        try {
            FileOutputStream fileoutputstream = new FileOutputStream(fileName);
            int i;
            while ((i = inputstream.read()) != -1)
                fileoutputstream.write(i);
            fileoutputstream.close();
        } catch (FileNotFoundException fe) {
            throw new AppException(GlobalErrorCode.FILEEXCEPTIONCODE + 5,
                    GlobalErrorMsg.FILE_IO_ERROR, fe);
        } catch (IOException ie) {
            throw new AppException(GlobalErrorCode.FILEEXCEPTIONCODE + 6,
                    GlobalErrorMsg.FILE_IO_ERROR, ie);
        }
    }

    /**
     * 将数据写入文件
     * @param data byte[]
     * @throws IOException
     */
    public static void saveFile(String filename,byte[] data)
            throws IOException {
        if(data==null){
            if(GlobalNames.DEBUG_OUTPUT_FLAG){
                System.out.println("FileUtil.saveFile：byte[] is null");
            }
            return;
        }

        File file = new File(filename);
        file.getParentFile().mkdirs();
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
                new FileOutputStream(file));
        bufferedOutputStream.write(data);
        bufferedOutputStream.close();
    }
    /**
     * 写入文本文件，未完
     * 
     * @param file
     * @param s
     * @throws AppException
     */
    public static void saveFile(String file, String s) throws AppException {
        OutputStreamWriter osw = null;
        FileOutputStream os = null;
         try {
             os = new FileOutputStream(file);
             osw = new OutputStreamWriter(os);
             osw.write(s, 0, s.length());
             osw.flush();
        } catch (IOException ie) {
            throw new AppException(GlobalErrorCode.FILEEXCEPTIONCODE + 9,
                    GlobalErrorMsg.FILE_IO_ERROR, ie);
         }finally{
             try{
                 if(os!=null)
                     os.close();
             }catch(Exception e){
                 
             }
             try{
                 if(osw!=null)
                     osw.close();
             }catch(Exception e){
             }
         }
    }
    
    /**
     * struts上传组件输入流代码的封装
     * @param file
     * @param filePath
     * @param filename
     * @return
     * @throws Exception
     * @author lf
     */
    public  String fileUpload(FormFile file,String filePath,String filename) throws Exception{
        if (filename!=null){
            String fileName = filename;//附件名
            if (file.getFileSize() < 122880) {//上传文件不能超过120k
            	InputStream stream = file.getInputStream();
				OutputStream bos = new FileOutputStream(filePath+File.separator+fileName);
				int bytesRead = 0;
				byte[] buffer = new byte[8192];
				while ( (bytesRead = stream.read(buffer, 0, 8192)) != -1) {
					bos.write(buffer, 0, bytesRead);
				}
				bos.close();
				stream.close();
				return("1");
			}else{
				return("0");
			}
        }else{
        	return("0");
        }
    }
    public static void main(String[] args) {
    	try{
            String file = "E:/公司资料/lic.txt";
            String code1 = FileUtil.readTextFile(file);
            System.out.println(code1);
            FileUtil.saveFile("e:/a.txt",code1);
//            String code2 = FileUtil.readTextFile2(file);
//            System.out.println(code2);
//            FileUtil.saveFile("e:\\b.txt",code2);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}