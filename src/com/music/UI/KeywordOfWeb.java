package com.music.UI;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * 由于该项目使用的是框架很多元素没有id、name等属性 且class均相同，所以自定义了方法进行针对性操作
 * 
 * @author hp
 *
 */
public class KeywordOfWeb {
	// 成员变量webdrvier对象driver，用这个成员变量操作后续的所有方法。
	public WebDriver driver = null;

	/**
	 * 启动浏览器的方法
	 * 
	 * @param浏览器的类型：可以是IE/FF/chrome
	 */
	public void openBrowser(String browserType) {
		try {
			switch (browserType) {
			case "chrome":
				GoogleDriver gg = new GoogleDriver("tools/chromedriver.exe");
				driver = gg.getdriver();
				AutoLogger.log.info("chrome浏览器启动");
				break;
			case "FF":
				FFDriver ff = new FFDriver("D:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe",
						"tools/geckodriver.exe");
				driver = ff.getdriver();
				AutoLogger.log.info("firefox浏览器启动");
				break;
			case "IE":
				IEDriver IE = new IEDriver("tools/IEDriver.exe");
				driver = IE.getdriver();
				AutoLogger.log.info("IE浏览器启动");
				break;
			default:
				GoogleDriver google = new GoogleDriver("tools/chromedriver.exe");
				driver = google.getdriver();
				AutoLogger.log.info("chrome浏览器启动");
			}
		} catch (Exception e) {
			AutoLogger.log.error(e, e.fillInStackTrace());
		}

	}

	/**
	 * 调整浏览器窗口大小
	 */
	public void setWindow() {
		Point p = new Point(0, 0);
		Dimension d = new Dimension(1920, 1080);
		driver.manage().window().setPosition(p);
		driver.manage().window().setSize(d);
		AutoLogger.log.info("调整浏览器窗口大小完毕");
	}

	/**
	 * 访问URL的方法
	 * 
	 * @param参数为网页URL
	 */
	public void visitWeb(String URL) {
		try {
			driver.get(URL);
			AutoLogger.log.info("访问" + URL);
		} catch (Exception e) {
			AutoLogger.log.error(e, e.fillInStackTrace());
		}
	}

	/**
	 * 定位输入框并输入内容方法
	 * 
	 * @param 定位的表达式以及输入的字符串
	 */
	public void input(String xpath, String content) {
		try {
			explicitlyWait(xpath);
			WebElement element = driver.findElement(By.xpath(xpath));
			element.clear();
			element.sendKeys(content);
		} catch (Exception e) {
			AutoLogger.log.error(e, e.fillInStackTrace());
			saveScrShot("input");
		}
	}

	/**
	 * 点击事件
	 * 
	 * @param xpath
	 */
	public void click(String xpath) {
		try {
			explicitlyWait(xpath);
			WebElement element = driver.findElement(By.xpath(xpath));
			AutoLogger.log.info("执行点击xpath" + xpath);
			element.click();
		} catch (Exception e) {
			AutoLogger.log.error(e, e.fillInStackTrace());
			saveScrShot("click");
		}
	}

	/**
	 * 使用actions类，调用moveElement方法实现悬停。
	 * 
	 * @param xpath
	 */
	public void hover(String xpath) {
		try {
			WebElement element = driver.findElement(By.xpath(xpath));
			Actions act = new Actions(driver);
			act.moveToElement(element).build().perform();
			AutoLogger.log.info("鼠标悬停操作");
		} catch (Exception e) {
			AutoLogger.log.error(e, e.fillInStackTrace());
			AutoLogger.log.info("元素悬停失败！");
			saveScrShot("hover");
		}
	}

	/**
	 * 实现显式等待的方法，在每次定位元素时，先尝试找元素，给10秒钟的最长等待。
	 */
	public void explicitlyWait(String xpath) {
		try {
			WebDriverWait ewait = new WebDriverWait(driver, 10);
			// 设置等待的预期条件为，元素可以被定位到。
			ewait.until(new ExpectedCondition<WebElement>() {
				public WebElement apply(WebDriver d) {
					return d.findElement(By.xpath(xpath));
				}
			});
		} catch (Exception e) {
			AutoLogger.log.error(e, e.fillInStackTrace());
		}
	}

	/**
	 * 实现获取页面标题的方法
	 */
	public String getTitle() {
		String title = "";
		try {
			title = driver.getTitle();
			AutoLogger.log.info("获取页面标题");
			return title;
		} catch (Exception e) {
			AutoLogger.log.error(e, e.fillInStackTrace());
			AutoLogger.log.info("获取页面标题失败！");
			return null;
		}

	}

	/**
	 * 实现关闭浏览器的方法
	 */
	public void closeBrowser() {
		try {
			driver.quit();
			AutoLogger.log.info("关闭浏览器成功");
		} catch (Exception e) {
			AutoLogger.log.error(e, e.fillInStackTrace());
		}
	}

	/**
	 * 实现隐式等待的方法
	 */
	public void implicitlyWait() {
		try {
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		} catch (Exception e) {
			AutoLogger.log.error(e, e.fillInStackTrace());
		}
	}

	/**
	 * 强制等待的方法
	 * 
	 * @param t强制等待的秒数，用字符串类型传递
	 */
	public void halt(String t) {
		try {
			int time = Integer.parseInt(t);
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			AutoLogger.log.error(e, e.fillInStackTrace());
		}
	}

	/**
	 * 进入iframe子页面
	 * 
	 * @param xpath
	 */
	public void intoIframe(String xpath) {
		try {
			explicitlyWait(xpath);
			WebElement frame = driver.findElement(By.xpath(xpath));
			driver.switchTo().frame(frame);
			AutoLogger.log.info("进入iframe成功！");
		} catch (Exception e) {
			AutoLogger.log.error(e, e.fillInStackTrace());
			AutoLogger.log.info("进入iframe失败");
			saveScrShot("切入Iframe");
		}
	}

	/**
	 * 退出子页面
	 */
	public void outIframe() {

		try {
			// 切回主页面
			driver.switchTo().defaultContent();
			AutoLogger.log.info("退出iFrame,切换回主页面");
		} catch (Exception e) {
			AutoLogger.log.error(e, e.fillInStackTrace());
			AutoLogger.log.error("切出Iframe失败");
			saveScrShot("切出Iframe");
		}
	}

	/**
	 * 通过窗口标题切换窗口
	 * 
	 * @param target
	 */
	public void switchWindow(String target) {
		// 创建一个字符串便于之后存放句柄
		String s = null;
		// 获取当前页面中的句柄
		Set<String> handles = driver.getWindowHandles();
		// 循环尝试，找到目标浏览器页面的句柄
		for (String t : handles) {
			// 遍历每一个句柄，判断窗口的标题是否包含预期字符
//				System.out.println(t);
			if (driver.switchTo().window(t).getTitle().equals(target)) {
				s = t;
			}
		}
		// 切换到目标句柄的页面中
		try {
			driver.switchTo().window(s);
			AutoLogger.log.info("切换到窗口" + target + "成功");
		} catch (Exception e) {
			AutoLogger.log.error(e, e.fillInStackTrace());
			AutoLogger.log.info("通过页面标题切换窗口失败！");
		}
	}

	/**
	 * 关闭旧窗口，切换到新窗口操作
	 */
	public void closeOldWin() {
		List<String> handlelist = new ArrayList<String>();
		// 返回一个句柄集合
		Set<String> handles = driver.getWindowHandles();
		// 循环获取集合里面的句柄，保存到List数组handles里面
		Iterator<String> it = handles.iterator();
		while (it.hasNext()) {
			handlelist.add(it.next().toString());
		}
		// 关闭第一个窗口
		driver.close();
		// 切换到新窗口
		try {
			driver.switchTo().window(handlelist.get(1));
			AutoLogger.log.info("关闭旧窗口切换到" + handlelist.get(1) + "--成功--");
		} catch (Exception e) {
			AutoLogger.log.error(e, e.fillInStackTrace());
			AutoLogger.log.info("关闭旧窗口切换到新窗口失败");
		}
	}

	/**
	 * 关闭新窗口
	 */
	public void closeNewWin() {
		List<String> handles = new ArrayList<String>();
		Set<String> s = driver.getWindowHandles();
		// 循环获取集合里面的句柄，保存到List数组handles里面
		for (Iterator<String> it = s.iterator(); it.hasNext();) {
			handles.add(it.next().toString());
		}
		try {
			driver.switchTo().window(handles.get(1));
			driver.close();
			driver.switchTo().window(handles.get(0));
			AutoLogger.log.info("关闭新窗口切换到" + handles.get(0) + "--成功--");
		} catch (Exception e) {
			AutoLogger.log.error(e, e.fillInStackTrace());
			AutoLogger.log.error("关闭新窗口失败！");
		}
	}

	/**
	 * 获取js运行结果
	 * 
	 * @param text
	 * @return
	 */
	public String getJs(String text) {
		String t = "";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			t = js.executeScript("return " + text).toString();
			AutoLogger.log.info("JS脚本执行成功");
		} catch (Exception e) {
			AutoLogger.log.error(e, e.fillInStackTrace());
			AutoLogger.log.error("JS脚本执行失败！");
		}
		return t;
	}

	/**
	 * 执行无返回的js脚本
	 * 
	 * @param text
	 */
	public void runJs(String text) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			js.executeScript(text);
			AutoLogger.log.info("JS脚本执行成功");
		} catch (Exception e) {
			AutoLogger.log.error(e, e.fillInStackTrace());
			AutoLogger.log.error("JS脚本执行失败！");
		}
	}

	/**
	 * 刷新页面
	 */
	public void refresh() {
		driver.navigate().refresh();
	}

	/**
	 * 页面后退
	 */
	public void back() {
		driver.navigate().back();
	}

	/**
	 * 页面前进
	 */
	public void forward() {
		driver.navigate().forward();
	}

	/**
	 * 执行浏览器滚动
	 * 
	 * @param height
	 */
	public void scrollWindowStraight(String height) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		try {
			String jsCmd = "window.scrollTo(0," + height + ")";
			js.executeScript(jsCmd);
		} catch (Exception e) {
			AutoLogger.log.error(e, e.fillInStackTrace());
			AutoLogger.log.error("操作浏览器滚动条失败");
			saveScrShot("scroll");
		}
	}

//  //定位到百度的输入框元素
//  WebElement menu = driver.findElement(By.xpath("//a[text()='老人手机']/parent::dd"));
//  List<WebElement> list=menu.findElements(By.tagName("a"));
//  for (WebElement ele:list) {
//  	System.out.println(ele.getText());
//  }
	

	/**
	 * 通过xpath定位多个元素，选择其中一个元素进行输入操作
	 */

	public void selectElementInput(String xpath, int index, String content) {
		try {
			List<WebElement> list = driver.findElements(By.xpath(xpath));
			WebElement ele = list.get(index);
			ele.clear();
			ele.sendKeys(content);
			AutoLogger.log.info("定位元素：" + xpath + "第" + index + "个");
		} catch (Exception e) {
			AutoLogger.log.error(e, e.fillInStackTrace());
			AutoLogger.log.error("通过序号定位元素失败！");
		}
	}

	/**
	 * 通过xpath定位多个元素，选择其中一个元素进行点击操作
	 */
	public void selectElementClick(String xpath, int index) {
		try {
			List<WebElement> list = driver.findElements(By.xpath(xpath));
			WebElement ele = list.get(index);
			ele.click();
			AutoLogger.log.info("定位元素：" + xpath + "第" + index + "个");
		} catch (Exception e) {
			AutoLogger.log.error(e, e.fillInStackTrace());
			AutoLogger.log.error("通过序号定位元素失败！");
		}
	}

	/**
	 * 实现select方法
	 * 
	 * @param xpath
	 * @param text
	 */
	public void selectByText(String xpath, String text) {
		try {
			// 将webelement转换为select
			Select userSelect = new Select(driver.findElement(By.xpath(xpath)));
			userSelect.selectByVisibleText(text);
		} catch (Exception e) {
			AutoLogger.log.error(e, e.fillInStackTrace());
			AutoLogger.log.error("通过文本选择Select失败！");
			saveScrShot("Select");
		}
	}

	/**
	 * 实现通过value选择select的选项
	 * 
	 * @param xpath
	 * @param value
	 */
	public void selectByValue(String xpath, String value) {
		try {
			// 将webelement转换为select
			Select userSelect = new Select(driver.findElement(By.xpath(xpath)));
			userSelect.selectByValue(value);
		} catch (Exception e) {
			AutoLogger.log.error(e, e.fillInStackTrace());
			AutoLogger.log.error("通过值选择Select失败！");
			saveScrShot("Select");
		}
	}

	/**
	 * 封装关键字实现登录
	 */
	public void login() {
		visitWeb("http://112.74.191.10:8000/");
		click("//a[@class='red']");
		input("//input[@id='username']", "2798145476@qq.com");
		input("//input[@id='password']", "qinghong");
		input("//input[@placeholder='验证码']", "1");
		click("//a[@name='sbtbutton']");
	}

	/**
	 * 报错时截图
	 * 
	 * @param method
	 */
	public void saveScrShot(String method) {
		// 获取当前的执行时间
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd+HH-mm-ss");
		// 当前时间的字符串
		String createdate = sdf.format(date);
		// 拼接文件名，形式为：工作目录路径+方法名+执行时间.png
		String scrName = "SCRshot/" + method + createdate + ".png";
		// 以当前文件名创建文件
		File scrShot = new File(scrName);
		// 将截图保存到临时文件
		File tmp = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(tmp, scrShot);
		} catch (IOException e) {
			AutoLogger.log.error(e, e.fillInStackTrace());
			AutoLogger.log.error("截图失败！");
		}
	}

	/**
	 * 断言标题中包含指定内容
	 * 
	 * @param target 标题包含的内容
	 */
	public void assertTitleContains(String target) {
		String result = getTitle();
		if (result.contains(target)) {
			AutoLogger.log.info("测试成功！");
		} else {
			AutoLogger.log.info("测试失败！");
		}
	}

	/**
	 * 断言标题的内容符合预期
	 * 
	 * @param target 标题内容
	 */
	public void assertTitleIs(String target) {
		String result = getTitle();
		if (result.equals(target)) {
			AutoLogger.log.info("测试成功！");
		} else {
			AutoLogger.log.info("测试失败！");
		}
	}

	/**
	 * 断言页面中某个元素的文本内容是否符合预期
	 * 
	 * @param xpath  元素定位xpath表达式
	 * @param target 预期的内容
	 */
	public void assertContentIs(String xpath, String target) {
		try {
			WebElement ele = driver.findElement(By.xpath(xpath));
			String text = ele.getText();
			if (text.equals(target)) {
				AutoLogger.log.info("测试成功！");
			} else {
				AutoLogger.log.info("测试失败！");
			}
		} catch (Exception e) {
			AutoLogger.log.info("未找到指定元素！" + xpath);
		}
	}

	/**
	 * 断言元素的某个值是否符合预期
	 * 
	 * @param xpath  元素定位xpath表达式
	 * @param attr   元素中的属性
	 * @param target 属性值
	 */
	public void assertAttrIs(String xpath, String attr, String target) {
		try {
			WebElement ele = driver.findElement(By.xpath(xpath));
			String value = ele.getAttribute(attr);
			if (value.equals(target)) {
				AutoLogger.log.info("测试成功！");
			} else {
				AutoLogger.log.info("测试失败！");
			}
		} catch (Exception e) {
			AutoLogger.log.info("未找到指定元素的指定属性！");
		}
	}

	/**
	 * 校验指定元素Attribute属性值是否为空
	 * 
	 * @param xpath
	 * @param attr
	 * @return返回获取的Attribute值
	 */
	public String assertAttrNotNull(String xpath, String attr, int subS, int sbuE) {
		String rawData = null;
		String value = null;
		try {
			WebElement ele = driver.findElement(By.xpath(xpath));
			rawData = ele.getAttribute(attr);
			value = rawData.substring(subS, sbuE);
			if (value != null) {
				AutoLogger.log.info("测试成功！" + value);
				return value;
			} else {
				AutoLogger.log.info("测试失败！");
				return value;
			}
		} catch (Exception e) {
			AutoLogger.log.info("未找到指定元素的指定属性！");
		}
		return value;
	}

	/**
	 * 断言页面源码中包含某个内容
	 * 
	 * @param target 页面中预期包含的内容
	 */
	public void assertPageContains(String target) {
		String pageContent = driver.getPageSource();
		if (pageContent.contains(target)) {
			AutoLogger.log.info("测试成功！");
		} else {
			AutoLogger.log.info("测试失败！");
		}
	}
}
