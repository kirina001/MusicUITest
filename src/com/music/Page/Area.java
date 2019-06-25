package com.music.Page;

import com.music.UI.KeywordOfWeb;

public class Area {
	/**
	 * 初始化
	 * @param kw
	 */
	public static void init(KeywordOfWeb kw) {
		kw.click("//span[text()='播放区域']");
		kw.halt("3");
	}

	/**
	 * 新增客户
	 * @param kw
	 */
	public static void addArea(KeywordOfWeb kw,String userName,String areaName) {
		init(kw);
		kw.click("//span[text()='新增区域']");
		kw.halt("1");
//		kw.click("//input[@class='el-input__inner']");
		kw.selectElementInput("//input[@class='el-input__inner']", 0, userName);
//		kw.halt("1");
//		kw.click("//input[@placeholder='区域描述']");
//		
		kw.input("//input[@placeholder='区域名称']", areaName);
//		kw.halt("1");
//		kw.click("//span[text()='确认']");
	
//		kw.ss("//ul[@class='el-scrollbar__view el-select-dropdown__list']", 3);
		
		
//		document.getElementsByClassName('el-scrollbar__view el-select-dropdown__list')
//		取出客户信息随机选一个
//		kw.input("//input[@placeholder='客户名称']", "");
	}

}
