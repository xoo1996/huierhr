package org.radf.plat.control;

/**
 * @author sky
 * 进度栏实体类
 */
public class progressBar {
	/** 进度栏名称 */
	private	String	strProgressBarName;
	/** 总共完成的进度 */
	private int		nTotalProgress;
	/** 已完成的进度 */
	private int		nCompletedProgress;
	/** 完成百分比 */
	private int		nPercent;
	/** 是否已经完成 */
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
