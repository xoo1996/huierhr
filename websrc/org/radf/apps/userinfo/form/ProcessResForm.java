package org.radf.apps.userinfo.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

public class ProcessResForm extends ActionForm{
	private String nemid;
	private String name;
	private String id;
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
    
    
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
