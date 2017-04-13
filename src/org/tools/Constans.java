package org.tools;

import java.io.File;

import org.apache.struts2.ServletActionContext;

public class Constans {
	/**
	 * 适应任何系统的文件分隔符
	 */
	public static final String dot = File.separator;
	public static final String pdfDir = ServletActionContext.getRequest()
			.getRealPath("/") + "pdf"+dot;
	public static final String pdfUrl = "http://"+ServletActionContext.getRequest()
			.getLocalAddr()
			+ ":"
			+ ServletActionContext.getRequest().getLocalPort()
			+ ServletActionContext.getRequest().getContextPath()
			+ "/"+"pdf/";
	public static final String watermark =ServletActionContext.getRequest()
			.getRealPath("/") + "watermark.jpg";
}
