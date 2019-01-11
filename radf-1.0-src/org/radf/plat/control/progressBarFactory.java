package org.radf.plat.control;

import java.util.Vector;
import java.util.Enumeration;

/**
 * @author sky
 * ����������
 */
public class progressBarFactory {
	private static progressBarFactory m_progressBarFactory = null;
	/** ��������ʼ������ */
	private final static int INITBARS = 4;
	/** �������� */
	private Vector m_progressBars = null;
	
	/** ��ȡ��ǰ��������Ψһʵ����� */
	public static progressBarFactory getFactory() {
		if(null == m_progressBarFactory) {
			m_progressBarFactory = new progressBarFactory();
			m_progressBarFactory.initialize();
		}
		
		return m_progressBarFactory;
	}
	
	/** ��ʼ�� */
	private void initialize() {
		m_progressBars = new Vector(INITBARS);
	}
	
	/** ���ݽ���������ȡ���������� */
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
	 * ���� ״̬���� ��������Ӧ��״̬��,���û���������� 
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

		/** ����״̬�� */
		progressBar bar = new progressBar(strBarName);
		m_progressBars.add(bar);
		
		return bar;
	}
	
	/** ������������ ���������� */
	public synchronized void addProgressBar(String strBarName) {
		if(null == m_progressBars)
			return;
		if(null == strBarName || "".equals(strBarName.trim()))
			return;
		
		progressBar bar = new progressBar(strBarName);
		m_progressBars.add(bar);
	}
	
	/** ɾ�������� */
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