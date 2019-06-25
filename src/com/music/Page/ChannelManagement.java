package com.music.Page;
import com.music.UI.KeywordOfWeb;

public class ChannelManagement {
	/**
	 * 初始化
	 * @param kw
	 */
	public static void init(KeywordOfWeb kw) {
		kw.click("//span[text()='频道管理']");
		kw.halt("3");
	}

	/**
	 * 新增频道
	 * 新增不同类型频道
	 * @param kw
	 */
	public static void addChannel(KeywordOfWeb kw) {
		init(kw);
		kw.click("//span[text()='新建频道']");
		kw.halt("1");
		kw.selectElementClick("//input[@placeholder='频道名称']", 0);
		kw.selectElementInput("//input[@placeholder='频道名称']", 0, "测试频道1");
		kw.halt("1");
		kw.selectElementClick("//input[@placeholder='频道类型']", 0);
		kw.halt("1");
		kw.selectElementClick("//li[@class='el-select-dropdown__item']/span[text()='公共频道']",1);
		kw.click("//span[text()='确认']");
	}
	
	/**
	 * 编辑频道信息
	 * 1.编辑频道信息
	 * 2.编辑曲目信息 * 
	 * @param kw
	 */
	public static void editChannel(KeywordOfWeb kw) {
		init(kw);
		//编辑第一条数据
		kw.selectElementClick("//span[@title='编辑']", 0);
		kw.selectElementClick("//button[@class='el-button el-button--primary is-plain']", 0);
		kw.selectElementInput("//input[@placeholder='频道名称']", 0, "编辑频道test");
		kw.click("//span[text()='保存']");
		kw.selectElementClick("//i[@class='el-dialog__close el-icon el-icon-close']", 4);
		assertEditChannel(kw);
	}
	/**
	 * 校验编辑频道信息
	 * 后期会扩展添加新的校验信息
	 * @param kw
	 */
	public static void assertEditChannel(KeywordOfWeb kw) {
		//其实定位到这个元素已算是测试通过
		kw.assertContentIs("//div[text()='编辑频道test']", "编辑频道test");
	}
	/**
	 * 根据频道名称查询
	 * @param kw
	 */
	public static void selectChannel(KeywordOfWeb kw) {
		init(kw);
		kw.input("//input[@placeholder='频道名称']", "编辑频道test");
		kw.click("//span[text()='搜索']");
		assertEditChannel(kw);
	}
	/**
	 * 删除第一条数据
	 */
	public static void deleteChannel(KeywordOfWeb kw) {
		init(kw);
		kw.selectElementClick("//span[@title='删除']", 0);
		kw.click("//button[@class='el-button el-button--default el-button--small el-button--primary ']");
	}
	/**
	 * 频道新增曲目信息
	 */
	public static void addMusicForChannel(KeywordOfWeb kw) {
		init(kw);
		//新增曲目信息
		kw.selectElementClick("//span[@title='编辑']", 0);
		kw.selectElementClick("//span[text()='编辑']", 1);
		kw.halt("1");
//		kw.selectElementClick("//button[@class='el-button el-button--primary is-plain']", 1);
		kw.click("//input[@placeholder='新增曲目']");
		kw.halt("1");
		kw.click("//span[text()='从曲库选择']");
		kw.halt("1");
		kw.selectElementClick("//span[@class='el-checkbox__inner']", 0);
		kw.click("//span[text()='添加']");
		kw.click("//span[text()='保存']");
		kw.halt("1");
		kw.selectElementClick("//i[@class='el-dialog__close el-icon el-icon-close']", 4);
	}
}
