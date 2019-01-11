// Decompiled by Jad v1.5.7g. Copyright 2000 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/SiliconValley/Bridge/8617/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi 

package org.radf.plat.taglib;

import javax.servlet.jsp.JspException;
import org.apache.struts.taglib.html.TextareaTag;

import org.radf.plat.commons.DateUtil;

// Referenced classes of package com.lbs.cp.taglib:
//            TagUtil

public class FormatInputTag extends TextareaTag {

	protected String mask;

	protected String required;

	protected String label;

	protected String disable;

	protected String format;

	protected String style;

	public FormatInputTag() {
		disable = "true";
		format = "false";
	}

	protected String renderTextareaElement() throws JspException {
		if ("true".equalsIgnoreCase(disable))
			return renderDisableProperties();
		else
			return renderProperties();
	}

	protected String renderDisableProperties() {
		StringBuffer stringbuffer = new StringBuffer();
		stringbuffer.append("<input type = 'hidden' name='");
		stringbuffer.append(property);
		stringbuffer.append("' value=\"");
		if (mask.equalsIgnoreCase("date") && format.equalsIgnoreCase("true")){
            if (!(getFormatInputValue() == null || ""
                    .equalsIgnoreCase(getFormatInputValue()))) {
                stringbuffer.append(DateUtil.converToString(
                        getFormatInputValue(), "yyyy-MM-dd"));
            } else {
                stringbuffer.append(getFormatInputValue());
            }
        }else
			stringbuffer.append(getFormatInputValue());
		stringbuffer.append("\" />");
		stringbuffer.append("<label>");
		if (mask.equalsIgnoreCase("date") && format.equalsIgnoreCase("true")) {
			if (!(getFormatInputValue() == null || ""
					.equalsIgnoreCase(getFormatInputValue()))) {
				stringbuffer.append(DateUtil.converToString(
						getFormatInputValue(), "yyyy-MM-dd"));
			} else {
				stringbuffer.append(getFormatInputValue());
			}
		} else {
			stringbuffer.append(getFormatInputValue());
		}
		stringbuffer.append("</label>");
		return stringbuffer.toString();
	}

	protected String renderProperties() throws JspException {
		StringBuffer stringbuffer = new StringBuffer(
				"<textarea style='width:100%' ");
//      添加 20070227 为身份证输入框添加ID属性 用来返回 lwd
		stringbuffer.append(" id='"+property+"' name=\"");
		if (indexed)
			prepareIndex(stringbuffer, name);
		stringbuffer.append(property);
		stringbuffer.append("\"");
		if (accesskey != null) {
			stringbuffer.append(" accesskey=\"");
			stringbuffer.append(accesskey);
			stringbuffer.append("\"");
		}
		if (mask != null) {
			stringbuffer.append(" mask=\"");
			stringbuffer.append(mask);
			stringbuffer.append("\"");
			if (mask.equalsIgnoreCase("date")) {
				stringbuffer.append(" onfocus=\"setday(this,document.all.");
				stringbuffer.append(property);
				stringbuffer.append(")\"");
			}
		}
		if (required != null) {
			stringbuffer.append("  required=\"");
			stringbuffer.append(required);
			stringbuffer.append("\"");
		}
		if (label != null) {
			stringbuffer.append(" label=\"");
			stringbuffer.append(label);
			stringbuffer.append("\"");
		}
		if (disable != null) {
			stringbuffer.append(" disable=\"");
			stringbuffer.append(disable);
			stringbuffer.append("\"");
		}
		if (tabindex != null) {
			stringbuffer.append(" tabindex=\"");
			stringbuffer.append(tabindex);
			stringbuffer.append("\"");
		}
		if (cols != null) {
			stringbuffer.append(" cols=\"");
			stringbuffer.append(cols);
			stringbuffer.append("\"");
		} else {
			stringbuffer.append(" cols=\"");
			stringbuffer.append(20);
			stringbuffer.append("\"");
		}
		if (style != null) {
			stringbuffer.append(" style=\"");
			stringbuffer.append("width:" + style + "px; ");
			stringbuffer.append("\"");
		} else {
			stringbuffer.append(" style=\"");
			stringbuffer.append("width:" + 120 + "px; ");
			stringbuffer.append("\"");
		}
		if (rows != null) {
			stringbuffer.append(" rows=\"");
			stringbuffer.append(rows);
			stringbuffer.append("\"");
		} else {
			stringbuffer.append(" rows=\"");
			stringbuffer.append(1);
			stringbuffer.append("\"");
		}
		stringbuffer.append(prepareEventHandlers());
		stringbuffer.append(prepareStyles());
		stringbuffer.append(" class=\"mask\"");
		stringbuffer.append(">");
		if (mask.equalsIgnoreCase("date") && format.equalsIgnoreCase("true")) {
			if (!(getFormatInputValue() == null || ""
					.equalsIgnoreCase(getFormatInputValue()))) {
				stringbuffer.append(DateUtil.converToString(
						getFormatInputValue(), "yyyy-MM-dd"));
			}
		} else
			stringbuffer.append(getFormatInputValue());
		stringbuffer.append("</textarea>");
		return stringbuffer.toString();
	}

	public String getMask() {
		return mask;
	}

	public void setMask(String s) {
		mask = s;
	}

	public String getRequired() {
		return required;
	}

	public String getDisable() {
		return disable;
	}

	public String getLabel() {
		return label;
	}

	public void setRequired(String s) {
		required = s;
	}

	public void setDisable(String s) {
		disable = s;
	}

	public void setLabel(String s) {
		label = s;
	}

	/**
	 * @return Returns the format.
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * @param format
	 *            The format to set.
	 */
	public void setFormat(String format) {
		this.format = format;
	}

	protected String getFormatInputValue() {
		if (value == null || "".equals(value.trim())
				|| "null".equals(value.trim()))
			return TagUtil.getPropertyValue(pageContext, property);
		if (value != null && value.startsWith("${") && value.endsWith("}"))
			return TagUtil.getELPropertyValue(pageContext, this, value);
		else
			return value;
	}
}
