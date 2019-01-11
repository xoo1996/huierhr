package org.radf.apps.commons.entity;

import org.radf.plat.util.entity.EntitySupport;

public class Report extends EntitySupport {
	
	private String pname; //��Ʒ����
	private Integer number; //����
	private Double price; //����
	private Double sales; //���۶�
	private Double discount; //�ɱ�����
	private Double pmoney; //��Ʒ���
	private String fname; //֧������
    private Double outfee; //֧�����
    
    public Report(){
    	
    }
    
    public Report (String pname,
    		Integer number, Double price,
    		Double sales, Double discount,
    		Double pmoney, String fname,
    		Double outfee) {
    	this.discount = discount;
    	this.fname = fname;
    	this.number = number;
    	this.outfee = outfee;
    	this.pmoney = pmoney;
    	this.pname = pname;
    	this.price = price;
    	this.sales = sales;
    }
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getSales() {
		return sales;
	}
	public void setSales(Double sales) {
		this.sales = sales;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public Double getPmoney() {
		return pmoney;
	}
	public void setPmoney(Double pmoney) {
		this.pmoney = pmoney;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public Double getOutfee() {
		return outfee;
	}
	public void setOutfee(Double outfee) {
		this.outfee = outfee;
	}
    
    

}
