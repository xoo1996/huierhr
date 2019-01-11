package org.radf.plat.control;

/**
 * @author sky
 * ������ʵ����
 */
public class progressBar {
	/** ���������� */
	private	String	strProgressBarName;
	/** �ܹ���ɵĽ��� */
	private int		nTotalProgress;
	/** ����ɵĽ��� */
	private int		nCompletedProgress;
	/** ��ɰٷֱ� */
	private int		nPercent;
	/** �Ƿ��Ѿ���� */
	private boolean	bCompleted;
	
	public progressBar(String strBarName) {
		this.setStrProgressBarName(strBarName);
		this.setNCompletedProgress(0);
		this.setNTotalProgress(100);
		this.setBCompleted(false);
	}
	
	public boolean isBCompleted() {
		return bCompleted;
	}
	public void setBCompleted(boolean completed) {
		bCompleted = completed;
	}
	public int getNCompletedProgress() {
		return nCompletedProgress;
	}
	public void setNCompletedProgress(int completedProgress) {
		if(completedProgress < nTotalProgress)
			nCompletedProgress = completedProgress;
		else {
			nCompletedProgress = nTotalProgress;
			bCompleted = true;
		}
	}
	public int getNPercent() {
		nPercent = (nCompletedProgress * 100) / nTotalProgress;
		return nPercent;
	}
	public void setNPercent(int percent) {
		nPercent = percent;
	}
	public int getNTotalProgress() {
		return nTotalProgress;
	}
	public void setNTotalProgress(int totalProgress) {
		nTotalProgress = totalProgress;
	}
	public String getStrProgressBarName() {
		return strProgressBarName;
	}
	public void setStrProgressBarName(String strProgressBarName) {
		this.strProgressBarName = strProgressBarName;
	}
}
