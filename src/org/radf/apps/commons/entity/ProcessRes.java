package org.radf.apps.commons.entity;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.upload.FormFile;
import org.radf.plat.util.entity.EntitySupport;

public class ProcessRes extends EntitySupport{
	private String nemid;
	private String name;
	private List<FormFile> myFiles = new ArrayList<FormFile>();
	
	
	public FormFile getFile(int i)  // 索引属性  
    {  
        return myFiles.get(i);  
    }  
    public void setFile(int i, FormFile myFile)  // 索引属性  
    {  
        if (myFile.getFileSize() > 0)  // 只有上传文件的字节数大于0，才上传这个文件  
        {  
            myFiles.add(myFile);  
        }  
    }  
    public int getFileCount()  // 获得上传文件的个数  
    {  
        return myFiles.size();  
    }
	public String getNemid() {
		return nemid;
	}
	public void setNemid(String nemid) {
		this.nemid = nemid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}  
    
}
