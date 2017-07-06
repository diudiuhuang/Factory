package com.Factory.factory;

//接收，发送 客户端给服务器的数据结构
public class ResponseData{
	private int code;
	private String msg;
	private RecvData body;
	
	
	public  ResponseData(ResultCode code,RecvData data){
		this.code=code.getcode();
		this.body=data;		
	}
	public  ResponseData(RecvData data){
		this.body=data;
	}
	public void setcode(ResultCode code){
		this.code=code.getcode();
		this.msg=code.getmsg();
	}
	public void setcode(int code){
		this.code=code;
	}
	public int getcode(){
		return this.code;
	}
	public void setmsg(String msg){
		this.msg=msg;
	}
	public String getmsg(){
		return this.msg;
	}
	public void setbody(RecvData body){
		this.body=body;
	}
	public RecvData getbody(){
		return this.body;
	}

}
