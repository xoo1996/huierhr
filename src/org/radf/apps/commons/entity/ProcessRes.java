package org.radf.apps.commons.entity;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.upload.FormFile;
import org.radf.plat.util.entity.EntitySupport;

public class ProcessRes extends EntitySupport{
	private String nemid;
	private String name;
	private List<FormFile> myFiles = new ArrayList<FormFile>();
	
	
	public FormFile getFile(int i)  // ��������  
    {  
        return myFiles.get(i);  
    }  
    public void setFile(int i, FormFile myFile)  // ��������  
    {  
        if (myFile.getFileSize() > 0)  // ֻ���ϴ��ļ����ֽ�������0�����ϴ�����ļ�  
        {  
            myFiles.add(myFile);  
        }  
    }  
    public int getFileCount()  // ����ϴ��ļ��ĸ���  
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
