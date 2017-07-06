package com.Factory.factory;

import java.util.ArrayList;
import java.util.List;

//WebSocke 响应消息，实现对网页广播推送
public class ResponseMessage {
	  private String responseMessage;
	  private List<Float> data1;
	  private List<Float> data2;
	  private List<Float> data3;
	  private List<Float> data4;
	  
	  
	 
	  public ResponseMessage(String responseMessage) {
	    this.responseMessage = responseMessage;
	    data1=new ArrayList<Float>();
	    data2=new ArrayList<Float>();
	    data3=new ArrayList<Float>();
	    data4=new ArrayList<Float>();
	  }
	  public ResponseMessage() {
		    this.responseMessage = "data";
		    data1=new ArrayList<Float>();
		    data2=new ArrayList<Float>();
		    data3=new ArrayList<Float>();
		    data4=new ArrayList<Float>();
		  }
	 
	  public String getResponseMessage() {
	    return responseMessage;
	  }

	public List<Float> getData1() {
		return data1;
	}

	public void setData1(List<Float> data1) {
		this.data1.addAll(data1);
	}

	public List<Float> getData2() {
		return data2;
	}

	public void setData2(List<Float> data2) {
		this.data2.addAll(data2);
	}

	public List<Float> getData3() {
		return data3;
	}

	public void setData3(List<Float> data3) {
		this.data3.addAll(data3);
	}

	public List<Float> getData4() {
		return data4;
	}

	public void setData4(List<Float> data4) {
		this.data4.addAll(data4);
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	}
