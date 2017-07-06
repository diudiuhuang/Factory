package com.Factory.factory;


import org.springframework.data.annotation.Id;
//接收客户端发送的采集数据格式，关键数据结构，
public class RecvData {
	@Id
	private String id;
	private String factoryName;
	private String opcAddress;
	private String groupName;
	private String itemName;
	private long time;
	private String valueType;
	private String value;
	
	public RecvData(){	}
	public RecvData(String id,String factname,String address,
			String groupName,String itemName,long time,String valuetype,String value ){
		this.id=id;
		this.factoryName=factname;
		this.opcAddress=address;
		this.groupName=groupName;
		this.itemName=itemName;
		this.time=time;
		this.valueType=valuetype;
		this.value=value;
	}
	public String getid(){
		return id;
	}
	public void setid(String id){
		this.id=id;
	}
	public String getfactoryName(){
		return factoryName;
	}
	public void setfactoryName(String factoryname){
		this.factoryName=factoryname;
	}
	public String getopcAddress(){
		return this.opcAddress;
	}
	public void setopcAddress(String opc){
		this.opcAddress=opc;
	}
	public String getgroupName(){
		return groupName;
	}
	public void setgroupName(String linename){
		this.groupName=linename;
	}
	public String getitemName(){
		return itemName;
	}
	public void setitemName(String itemName){
		this.itemName=itemName;
	}
	public long gettime(){
		return time;
	}
	public void settime(long time){
		this.time=time;
	}
	public String getvalue(){
		return value;
	}
	public void setvalue(String value){
		this.value=value;
	}
	public void setvalueType(String type){
		this.valueType=type;
	}
	public String getvalueType() {
		return this.valueType;
	}
    @Override
    public String toString() {
        return String.format(
                "Customer[id=%s, factoryName='%s',opcAddress='%s', groupName='%s',itemName='%s',time='%s',valueType='%s',value='%s']",
                id, factoryName,opcAddress, groupName,itemName,time,valueType,value);
    }

}
