package com.music.Page;

import com.music.UI.KeywordOfWeb;

public class Login {
	/**
	 * 登录方法
	 * @param kw
	 */
	public static void login(KeywordOfWeb kw) {
		
		kw.input("//input[@name='username']", "admin");
		kw.input("//input[@name='password']", "123456");
		kw.halt("2");
//		kw.click("//button[@class='el-button el-button--primary']");
//		kw.click("//button[@class='el-form-item']");
//		kw.hover("//button/span");
//		kw.halt("1");
//		kw.click("//button/span");
		kw.runJs("document.getElementsByClassName('el-button el-button--primary')[0].click()");
		kw.halt("4");
		kw.refresh();
		
		
	}

}
