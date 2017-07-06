package com.Factory.factory;

import org.apache.log4j.Logger;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;



//webSocket控制类，实现向网页广播推送
@Controller
public class WsController {
	private final Logger logger = Logger.getLogger(WsController.class); 
	private long interval=-1000;
	//private String schedulStr="";
	private boolean enableSchedual=false;
	private ThreadProducerDynData dyncThread;
	
	public  WsController() {
		
	}
	private void newThread(){
		dyncThread=ApplicationContextProvider.getBean("mTask", ThreadProducerDynData.class); 
		dyncThread.setInterval(interval);
		dyncThread.setThreadEnable(enableSchedual);
	}
	@MessageMapping(value="/endpointSync")
	@SendTo("/topic/responseData")
	public  ResponseMessage dyncGraphSend(RequestMessage message) throws Exception {
		interval=message.getInterval();
		if (interval>0){
			//interval >0 启动线程
			if (false==enableSchedual){
				newThread();
				//首次启动线程
				enableSchedual=true;
				dyncThread.setInterval(interval);				
				dyncThread.setThreadEnable(enableSchedual);
				dyncThread.start();
				logger.info("DynaGraph Thread is start "+message.toString());
				System.out.println(message.getInterval());
				return new ResponseMessage("Dynamic data will send in 3 seconds!");
			}
		}
		else {
			//interval <0 停止线程
			if(true == enableSchedual){
				enableSchedual=false;
				dyncThread.setInterval(interval);
				dyncThread.setThreadEnable(enableSchedual);
				if (dyncThread.isAlive()){
					dyncThread.join(1200);
				}
				logger.info("DynaGraph Thread is joined "+message.toString());
			}
			return new ResponseMessage("Dynamic data will stop in 1 seconds!");
		}
		return new ResponseMessage("Dynamic data will stop in 1 seconds!");
	}
	

    //@Scheduled(fixedRate = 1000) // 每1秒执行一次 抛弃此办法，因为停止服务比较麻烦
    public void scheduler() {
        logger.info(">>>>>>>>>>>>> scheduled ... ");
        sendDyncData();
    }
    public ResponseMessage sendDyncData(){
    	ResponseMessage sendData=new ResponseMessage("ok");
    	return sendData;
    }
}
