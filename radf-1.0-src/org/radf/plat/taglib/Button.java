// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.plat.taglib;


public class Button
{

    private String name;
    private String url;
    private String mode;
    private String click;
    private String label;
    private String functionID;

    public Button(String name, String label, String functionID, String click)
    {
    	this.name = name;
    	this.label = label;
    	this.functionID = functionID;
    	this.click = click;
    }

    public Button(String label, String functionID, String click)
    {
    	this.label = label;
    	this.functionID = functionID;
    	this.click = click;
    }

    public String getFunctionid()
    {
        return functionID;
    }

    public void setFunctionid(String s)
    {
    	functionID = s;
    }

    public String getClick()
    {
        return click;
    }

    public void setClick(String s)
    {
    	click = s;
    }

    public String getLabel()
    {
        return label;
    }

    public void setLabel(String s)
    {
    	label = s;
    }

    public String getMode()
    {
        return mode;
    }

    public void setMode(String s)
    {
    	mode = s;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String s)
    {
    	name = s;
    }

    public String getUrl()
    {
        return url;
    }

    public void setUrl(String s)
    {
    	url = s;
    }
}
