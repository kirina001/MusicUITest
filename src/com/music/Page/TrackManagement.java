package com.music.Page;

import com.music.UI.KeywordOfWeb;

public class TrackManagement {

	/**
	 * 初始化
	 * @param kw
	 */
	public static void init(KeywordOfWeb kw) {
		kw.click("//span[text()='曲目管理']");
	}

	/**
	 * 新增曲目
	 * 
	 * @param kw
	 */
	public static void addMusic(KeywordOfWeb kw) {
		init(kw);
		kw.halt("3");
		kw.click("//button[@class='el-button el-button--primary is-plain'][2]");
		kw.halt("1");
		// 选择曲目类型
		kw.runJs("document.getElementsByClassName('el-input el-input--suffix')[0].click()");
		kw.click("//li[text()='中国音乐']");
		// 选择曲速
		kw.halt("1");
		kw.runJs("document.getElementsByClassName('el-input el-input--suffix')[1].click()");
		kw.runJs("document.getElementsByClassName('el-select-dropdown__item')[5].click()");
		// 选择上传文件
		kw.halt("1");
		kw.input("//input[@name='file']", "E:\\musicData\\10\\长安夜.mp3");
		kw.halt("2");
		// 关闭弹窗
		kw.runJs("document.getElementsByClassName('el-dialog__close el-icon el-icon-close')[2].click()");

	}

	/**
	 * 编辑曲目
	 * 
	 * @param kw
	 */
	public static void editMusic(KeywordOfWeb kw) {
		init(kw);
		kw.halt("3");
		kw.runJs("document.getElementsByClassName('el-button el-button--error el-button--small')[1].click()");
		kw.halt("2");
		kw.runJs("document.getElementsByClassName('el-input__inner')[0].value='修改名称2'");
		kw.halt("1");
		// 取消按钮
		kw.runJs("document.getElementsByClassName('el-button el-button--primary')[0].click()");

	}

	/**
	 * 删除曲目
	 * 
	 * @param kw
	 */
	public static void deleteMusic(KeywordOfWeb kw) {
		init(kw);
		kw.halt("1");
		// 选择第一条数据
		kw.runJs("document.getElementsByClassName('el-button el-button--error el-button--small')[2].click()");
		kw.halt("1");
		// 弹窗确认删除
		kw.runJs(
				"document.getElementsByClassName('el-button el-button--default el-button--small el-button--primary ')[0].click()");
	}

	/**
	 * 下载曲目
	 * 
	 * @param kw
	 */
	public static void downloadMusic(KeywordOfWeb kw) {
		init(kw);
		// 勾选第一首曲目
		kw.halt("1");
		kw.runJs("document.getElementsByClassName('el-checkbox__inner')[1].click()");
		// 下载曲目
		kw.halt("1");
		kw.runJs("document.getElementsByClassName('el-button el-button--primary is-plain')[0].click()");
		// 指定下载地址？
//		fp.set_preference("browser.download.dir","c:\\test")

	}

	/**
	 * 根据曲目名称查询曲目信息
	 * @param kw
	 */
	public static void selectMusicForName(KeywordOfWeb kw,String musicName) {
		init(kw);
		kw.halt("1");
		kw.click("//input[@placeholder='曲目名称']");
		kw.input("//input[@placeholder='曲目名称']", musicName);
		kw.halt("1");
		kw.click("//span[text()='搜索']");
	}
	
	/**
	 * 查询表演者信息查询曲目
	 * @param kw
	 */
	public static void selectMusicForSinger(KeywordOfWeb kw,String singerName) {
		init(kw);
		kw.halt("1");
		kw.click("//input[@placeholder='表演者']");
		kw.input("//input[@placeholder='表演者']", singerName);
		kw.halt("1");
		kw.click("//span[text()='搜索']");
	}
	/**
	 * 查询曲目类型查询曲目
	 * @param kw
	 */
	public static void selectMusicForType(KeywordOfWeb kw) {
		init(kw);
		kw.halt("1");
		kw.runJs("document.getElementsByClassName('el-input el-input--suffix')[0].click()");
		kw.click("//li[text()='中国音乐']");
		kw.halt("1");
		kw.click("//span[text()='搜索']");
		
	}
	
}
