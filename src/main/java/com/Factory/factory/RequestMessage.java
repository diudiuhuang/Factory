package com.Factory.factory;


//WebSocke 请求消息，实现对网页广播推送,间隔秒数，4个item的名字
public class RequestMessage {
	  private long interval;
	  private String itemName1;
	  private String itemName2;
	  private String itemName3;
	  private String itemName4;
	  
	public long getInterval() {
		return interval;
	}
	public void setInterval(int interval) {
		this.interval = interval;
	}
	public String getItemName1() {
		return itemName1;
	}
	public void setItemName1(String itemName1) {
		this.itemName1 = itemName1;
	}
	public String getItemName2() {
		return itemName2;
	}
	public void setItemName2(String itemName2) {
		this.itemName2 = itemName2;
	}
	public String getItemName3() {
		return itemName3;
	}
	public void setItemName3(String itemName3) {
		this.itemName3 = itemName3;
	}
	public String getItemName4() {
		return itemName4;
	}
	public void setItemName4(String itemName4) {
		this.itemName4 = itemName4;
	}
	  
}
