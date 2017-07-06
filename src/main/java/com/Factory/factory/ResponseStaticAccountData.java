package com.Factory.factory;

//发送给网页的静态统计数据结构
public class ResponseStaticAccountData {
	private String factoryName;
	private String opcAddress;
	private String groupName;
	private String itemName;
	private String time;
	private long dataAccount;
	private float maxData;
	private float minData;
	private float avgData;
	private float sumData;
	private String statuts;

	public String getGroupName(){
		return this.groupName;
	}
	public void setGroupName(String groupName){
		this.groupName=groupName;
	}
	public float getSumData(){
		return this.sumData;
	}
	public void setSumData(float sumData){
		this.sumData=sumData;
	}
	public String getFactoryName() {
		return factoryName;
	}
	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}
	public String getOpcAddress() {
		return opcAddress;
	}
	public void setOpcAddress(String opcAddress) {
		this.opcAddress = opcAddress;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public long getDataAccount() {
		return dataAccount;
	}
	public void setDataAccount(long dataAccount) {
		this.dataAccount = dataAccount;
	}
	public float getMaxData() {
		return maxData;
	}
	public void setMaxData(float maxData) {
		this.maxData = maxData;
	}
	public float getMinData() {
		return minData;
	}
	public void setMinData(float minData) {
		this.minData = minData;
	}
	public float getAvgData() {
		return avgData;
	}
	public void setAvgData(float avgData) {
		this.avgData = avgData;
	}
	public String getStatuts() {
		return statuts;
	}
	public void setStatuts(String statuts) {
		this.statuts = statuts;
	}
}
