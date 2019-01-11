package org.radf.plat.control;

import java.util.Vector;
import java.util.Enumeration;

/**
 * @author sky
 * 进度栏工厂
 */
public class progressBarFactory {
	private static progressBarFactory m_progressBarFactory = null;
	/** 进度栏初始化个数 */
	private final static int INITBARS = 4;
	/** 进度栏区 */
	private Vector m_progressBars = null;
	
	/** 获取当前进度栏的唯一实体对象 */
	public static progressBarFactory getFactory() {
		if(null == m_progressBarFactory) {
			m_progressBarFactory = new progressBarFactory();
			m_progressBarFactory.initialize();
		}
		
		return m_progressBarFactory;
	}
	
	/** 初始化 */
	private void initialize() {
		m_progressBars = new Vector(INITBARS);
	}
	
	/** 根据进度栏名获取进度栏对象 */
	public synchronized progressBar getProgressBar(String strBarName) {
		if(null == m_progressBars)
			return null;
		if(null == strBarName || "".equals(strBarName.trim()))
			return null;
		
		Enumeration enums = m_progressBars.elements();
		while(enums.hasMoreElements()) {
			progressBar bar = (progressBar) enums.nextElement();
			if(strBarName.equals(bar.getStrProgressBarName()))
				return bar;
		}
		
		return null;
	}
	
	/**
	 * 根据 状态栏名 返回所对应的状态栏,如果没有则新增加 
	 */
	public synchronized progressBar getProgressBarEx(String strBarName) {
		if(null == m_progressBars)
			return null;
		if(null == strBarName || "".equals(strBarName.trim()))
			return null;
		
		Enumeration enums = m_progressBars.elements();
		while(enums.hasMoreElements()) {
			progressBar bar = (progressBar) enums.nextElement();
			if(strBarName.equals(bar.getStrProgressBarName()))
				return bar;
		}

		/** 新增状态栏 */
		progressBar bar = new progressBar(strBarName);
		m_progressBars.add(bar);
		
		return bar;
	}
	
	/** 新增进度栏到 进度栏工厂 */
	public synchronized void addProgressBar(String strBarName) {
		if(null == m_progressBars)
			return;
		if(null == strBarName || "".equals(strBarName.trim()))
			return;
		
		progressBar bar = new progressBar(strBarName);
		m_progressBars.add(bar);
	}
	
	/** 删除进度栏 */
	public synchronized void delProgressBar(String strBarName) {
		if(null == m_progressBars)
			return;
		if(null == strBarName || "".equals(strBarName.trim()))
			return;
		
		Enumeration enums = m_progressBars.elements();
		while(enums.hasMoreElements()) {
			progressBar bar = (progressBar) enums.nextElement();
			if(strBarName.equals(bar.getStrProgressBarName()))
				m_progressBars.remove(bar);
		}
	}
}