package com.Factory.factory;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

//接收从采集客户端传送的实时数据服务
@RestController
public class MongoDBService {
	@Autowired
	private DataRepository _dataDao;

	private static Logger logger = Logger.getLogger(MongoDBService.class); 
	
	public  MongoDBService() throws Exception {
	}
	//接收从java client通过post方法传输到/dataReceive接口的数据
	 @RequestMapping(value = "/dataReceive",method=RequestMethod.POST)
	 @ResponseBody
	 public   String receivdata(RecvData recvData) {
		 ResponseData requestData = new ResponseData(recvData);
		 requestData=checkData(recvData);
		 if(requestData.getcode() >=0){
			 //数据正确，写入数据库
			 _dataDao.saveData(requestData.getbody());
			 System.out.println("-receive sucess: "+requestData.getbody().toString());
		 }
		 else {
			//数据错误，抛弃
			 System.out.println("-receive failed : "+requestData.getbody().toString());
			 logger.error("-receive failed : "+requestData.getbody().toString());
		}
		 return requestData.getcode()+" "+requestData.getmsg();       
		 } 
	 //验证数据
	 private ResponseData checkData(RecvData recvData){
		 ResponseData requestDatatemp = new ResponseData(recvData);
		ResultCode resultCode = ResultCode.WARN;
		// 验证数据完整
		try {
			if (recvData.getfactoryName().isEmpty() || recvData.getgroupName().isEmpty()
					|| recvData.getitemName().isEmpty() || recvData.getvalue().isEmpty()
					|| recvData.getopcAddress().isEmpty() || recvData.getvalueType().isEmpty()
					|| recvData.getid().isEmpty() || recvData.gettime()<=0) {
				resultCode = ResultCode.WARN;
				resultCode.setcode(-1);
				resultCode.setmsg("data wrong");
				requestDatatemp.setcode(resultCode);
				return requestDatatemp;
			}
		} catch (Exception ex) {
			resultCode = ResultCode.WARN;
			resultCode.setcode(-1);
			resultCode.setmsg("time wrong");
			requestDatatemp.setcode(resultCode);
			return requestDatatemp;
		}
		// 成功
		resultCode = ResultCode.SUCCESS;
		resultCode.setcode(0);
		resultCode.setmsg("sucess");
		requestDatatemp.setcode(resultCode);
		return requestDatatemp;
	}
}
