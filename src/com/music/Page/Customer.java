package com.music.Page;

import com.music.Tools.DateForMusic;
import com.music.UI.KeywordOfWeb;

public class Customer {
	/**
	 * 初始化
	 * @param kw
	 */
	public static void init(KeywordOfWeb kw) {
		kw.click("//span[text()='客户管理']");
		kw.halt("3");
	}

	/**
	 * 新增客户
	 * @param kw
	 */
	public static void addCustomer(KeywordOfWeb kw,String customerName) {
		init(kw);
		String startDate=DateForMusic.currentDay();
		String endDate=DateForMusic.nextYear();
		kw.click("//button[@class='el-button el-button--primary is-plain']");
		kw.halt("1");
		//客户信息待参数化
		kw.input("//input[@placeholder='客户名称']", customerName);
//		kw.selectElementClick("//i[@class='el-input__icon el-icon-date']", 0);
		kw.selectElementInput("//input[@placeholder='选择日期']", 0, startDate);
		kw.halt("1");
		kw.selectElementInput("//input[@placeholder='选择日期']", 1, endDate);
		kw.halt("1");
		kw.click("//input[@placeholder='地址']");
		kw.halt("1");
		kw.selectElementClick("//button[@class='el-button el-button--primary']", 1);
	}
	
	/**
	 *查询客户
	 * @param kw
	 */
	public static void selectCustomer(KeywordOfWeb kw,String customerName) {
		init(kw);
		//客户信息待参数化
		kw.input("//input[@placeholder='关键字搜索']", customerName);
		kw.click("//span[text()='搜索']");
		kw.halt("1");
		assertCustomerName(kw,customerName);
	}
	/**
	 * 校验新增客户信息是否存在
	 * @param kw
	 */
	public static void assertCustomerName(KeywordOfWeb kw,String customerName) {
		//其实定位到这个元素已算是测试通过
		String result=kw.getJs("document.getElementsByClassName('cell')[7].innerText");
//		System.out.println("输入信息："+customerName);
//		System.out.println("返回结果： "+result);
		if (result.equals(customerName)){
			System.out.println("校验信息成功");
		}
		else {
			System.out.println("校验信息失败");
		}
		
	}
	/**
	 * 编辑客户信息
	 * @param kw
	 */
	public static void editCustomer(KeywordOfWeb kw,String customerName) {
		init(kw);
		kw.selectElementClick("//span[@title='编辑']", 0);
		kw.halt("1");
		kw.click("//span[text()='编辑']");
//		kw.selectElementClick("//button[@class='el-button el-button--primary is-plain']", 0);
		kw.halt("1");
		kw.input("//input[@placeholder='客户名称']", customerName);
		kw.halt("1");
		kw.click("//span[text()='保存']");
//		kw.selectElementClick("//button[@class='el-button el-button--primary is-plain']", 0);
		assertCustomerName(kw, customerName);
	}
	/**
	 * 删除第二条数据
	 * @param kw
	 */
	public static void deleteCustomer(KeywordOfWeb kw) {
		init(kw);
		kw.selectElementClick("//span[@title='删除']", 1);
		kw.click("//button[@class='el-button el-button--default el-button--small el-button--primary ']");
	}
	/**
	 * 跳转到播放区域
	 */
	public static void customerArea(KeywordOfWeb kw,String customerName) {
		init(kw);
		kw.selectElementClick("//span[@title='播放区域']", 0);
		kw.halt("1");
		String result=kw.getJs("document.getElementsByClassName('cell')[9].innerText");
		if (result.equals(customerName)){
			System.out.println("校验信息成功");
		}
		else {
			System.out.println("校验信息失败");
		}
		
	}
	
	
	

}
