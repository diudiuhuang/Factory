package com.Factory.factory;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

//组装静态统计数据
public class ViewDataControl {
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private static Logger logger = Logger.getLogger(ViewDataControl.class);
	//引起警报的阈值
	private final static float _WARRING_VALUE_MIN=0;
	private final static float _WARRING_VALUE_MAX=100000000;
	private final static float _ERROR_VALUE_MIN=-1;
	private final static float _ERROR_VALUE_MAX=999999999;
	
	//计算静态统计数据filed为RECVDATA 字段名,可支持4中方式统计：factoryName,opcAddress,groupName,itemName
	public List<ResponseStaticAccountData> accountResponseStatic(List<RecvData> data,String field){
		if ("factoryName".equals(field)){
			return accountFactoryStatic(data);
		}
		if ("opcAddress".equals(field)){
			return accountOpcAddrStatic(data);
		}
		if ("groupName".equals(field)){
			return accountGroupNameStatic(data);
		}
		if ("itemName".equals(field)){
			return accountItmeNameStatic(data);
		}
		System.out.println(MessageFormat.format("accountResponseStatic() filed:{0} can NOT find !",field));
		logger.error(MessageFormat.format("accountResponseStatic() filed:{0} can NOT find !",field));
		try {
			throw new FactorException(MessageFormat.format("accountResponseStatic() filed:{0} can NOT find !",field));
		} catch (FactorException e) {
			e.printStackTrace();
		}
		return null;
	}
	//按工厂名统计
	private List<ResponseStaticAccountData> accountFactoryStatic(List<RecvData>data){
		List<ResponseStaticAccountData> factoryList=new ArrayList<ResponseStaticAccountData>();
		for (RecvData recvItem:data){
			if (null ==recvItem.getfactoryName() ||recvItem.getfactoryName().isEmpty()){
				continue;
			}
			//找到一个工厂名,插入或合并到factoryList中
			boolean find=false;
			for (ResponseStaticAccountData factoryItem:factoryList){
				if (factoryItem.getFactoryName().equals(recvItem.getfactoryName())){
					find=true;
					//sum
					factoryItem.setSumData(factoryItem.getSumData()+Float.parseFloat(recvItem.getvalue()));
					//数据个数
					factoryItem.setDataAccount(factoryItem.getDataAccount()+1);
					//maxvalue
					if (factoryItem.getMaxData()<Float.parseFloat(recvItem.getvalue())){
						factoryItem.setMaxData(Float.parseFloat(recvItem.getvalue()));
					}
					//minvalue
					if (factoryItem.getMinData()>Float.parseFloat(recvItem.getvalue())){
						factoryItem.setMinData(Float.parseFloat(recvItem.getvalue()));
					}
					factoryItem.setAvgData((Float.parseFloat(recvItem.getvalue())+factoryItem.getAvgData()*
							factoryItem.getDataAccount())/factoryItem.getDataAccount());
					factoryItem.setTime(sdf.format(new Date()));
					factoryItem.setStatuts(getStatus(factoryItem));
					}
				else{
					continue;
				}	
			}
			//如果该factoryName不存在
			if (false == find){
				find=false;
				ResponseStaticAccountData tempData=new ResponseStaticAccountData();
				tempData.setFactoryName(recvItem.getfactoryName());
				tempData.setOpcAddress(recvItem.getopcAddress());
				tempData.setGroupName(recvItem.getgroupName());
				tempData.setItemName(recvItem.getitemName());
				tempData.setDataAccount(1);
				tempData.setAvgData(Float.parseFloat(recvItem.getvalue()));
				tempData.setMaxData(Float.parseFloat(recvItem.getvalue()));
				tempData.setMinData(Float.parseFloat(recvItem.getvalue()));
				tempData.setTime(sdf.format(new Date()));
				tempData.setSumData(Float.parseFloat(recvItem.getvalue()));
				tempData.setStatuts(getStatus(tempData));
				factoryList.add(tempData);
			}
		}
		return factoryList;
	}
	//根据具体情况来修改，现初步模拟
	//根据统计值判断状态
	private String getStatus(ResponseStaticAccountData tempData){
		if (tempData.getDataAccount() >_ERROR_VALUE_MAX){
			return "错误";
		}
		if (tempData.getMaxData() > _ERROR_VALUE_MAX){
			return "错误";
		}
		if (tempData.getMaxData() > _ERROR_VALUE_MAX){
			return "错误";
		}
		if (tempData.getMinData() <= _ERROR_VALUE_MIN){
			return "错误";
		}
		if (tempData.getMinData() <= _WARRING_VALUE_MIN){
			return "警告";
		}
		if (tempData.getMaxData() > _WARRING_VALUE_MAX){
			return "警告";
		}
		return "正常";
	}
	private List<ResponseStaticAccountData> accountOpcAddrStatic(List<RecvData>data){
		List<ResponseStaticAccountData> retData=new ArrayList<ResponseStaticAccountData>();
		return retData;
	}
	private List<ResponseStaticAccountData> accountGroupNameStatic(List<RecvData>data){
		List<ResponseStaticAccountData> retData=new ArrayList<ResponseStaticAccountData>();
		return retData;
	}
	private List<ResponseStaticAccountData> accountItmeNameStatic(List<RecvData>data){
		List<ResponseStaticAccountData> retData=new ArrayList<ResponseStaticAccountData>();
		return retData;
	}
	
	//获取当前时间戳
	public static long getTimeNowString(){
		return System.currentTimeMillis();
	}
	//获取时间戳
	public static long getOldTimeString(String timeInterval){
		Calendar lastDate = Calendar.getInstance();	
		if ("Month".equals(timeInterval)){
			lastDate.set(Calendar.DATE,1);//设为当前月的1 号 
			lastDate.set(Calendar.HOUR_OF_DAY, 0);  
			lastDate.set(Calendar.SECOND, 0);  
			lastDate.set(Calendar.MINUTE, 0);  
			lastDate.set(Calendar.MILLISECOND, 0);  
		}
		if ("Week".equals(timeInterval)){
			int n = lastDate.get(Calendar.DAY_OF_WEEK) - 1;
			if (n == 0) {
			    n = 7;
			}
			lastDate.add(Calendar.DATE, - (n - 1));// 周一的日期
			lastDate.set(Calendar.HOUR_OF_DAY, 0);  
			lastDate.set(Calendar.SECOND, 0);  
			lastDate.set(Calendar.MINUTE, 0);  
			lastDate.set(Calendar.MILLISECOND, 0); 
		}
		if ("Day".equals(timeInterval)){
			lastDate.set(Calendar.HOUR_OF_DAY, 0);  
			lastDate.set(Calendar.SECOND, 0);  
			lastDate.set(Calendar.MINUTE, 0);  
			lastDate.set(Calendar.MILLISECOND, 0);	
		}
		if ("Hour".equals(timeInterval)){  
			lastDate.set(Calendar.SECOND, 0);  
			lastDate.set(Calendar.MINUTE, 0);  
			lastDate.set(Calendar.MILLISECOND, 0);
		}
		return lastDate.getTimeInMillis();
	}
}
