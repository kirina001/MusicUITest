package com.music.Test;

import com.music.Page.Area;
import com.music.Page.ChannelManagement;
import com.music.Page.Customer;
import com.music.Page.Login;
import com.music.Page.TrackManagement;
import com.music.Tools.DateForMusic;
import com.music.UI.KeywordOfWeb;

public class Test {

	public static void main(String[] args) {
		
		KeywordOfWeb kw = new KeywordOfWeb();
		kw.openBrowser("chrome");
		kw.visitWeb("http://10.68.170.184:8080/music/");
		
		
		//正式环境地址
//		kw.visitWeb("http://47.101.197.102:8080/music/");
//		http://47.100.254.37:8888/freshair-server/
		
		Login.login(kw);
		
		//曲目管理
//		TrackManagement.addmusic(kw);
//		TrackManagement.editMusic(kw);//存在问题
//		TrackManagement.deleteMusic(kw);
//		TrackManagement.downloadMusic(kw);
//		TrackManagement.selectMusicForName(kw,"test");
//		TrackManagement.selectMusicForSinger(kw,"周杰伦");
//		TrackManagement.selectMusicForType(kw);
		
		
		//频道信息
//		ChannelManagement.addChannel(kw);
//		ChannelManagement.editChannel(kw);
//		ChannelManagement.selectChannel(kw);
//		ChannelManagement.deleteChannel(kw);
//		ChannelManagement.addMusicForChannel(kw);
//		ChannelManagement.addMusicForChannel(kw);
		
		//客户管理
//		Customer.addCustomer(kw,"客户1");
//		Customer.selectCustomer(kw,"编辑客户22");
//		Customer.editCustomer(kw,"编辑客户22");
//		Customer.deleteCustomer(kw);
//		Customer.customerArea(kw,"编辑客户22");
		
		//区域管理
		Area.addArea(kw,"编辑客户22","新增区域2");
		
		
		
		
		
//		

	}

}
