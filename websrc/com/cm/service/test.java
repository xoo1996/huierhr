package com.cm.service;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String str="abc";
		System.out.println(str);
		change(str);
		System.out.println(str);
	}

	public static void change(String str){
		str="acb";
	}
}
