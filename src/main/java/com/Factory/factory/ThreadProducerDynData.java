package com.Factory.factory;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators.Abs;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.SimpleDateFormat;
import java.util.ArrayList; 
import java.util.List;
import java.util.concurrent.BlockingQueue;  
import java.util.concurrent.LinkedBlockingQueue;
import javax.management.monitor.Monitor; 


@Controller
@Component("mTask")
@Scope("prototype")
public class ThreadProducerDynData extends Thread{
	private static Logger logger = Logger.getLogger(ThreadProducerDynData.class); 
	private boolean threadEnable=false;
    private int maxCount = 1000;  
    private BlockingQueue<ResponseMessage> queue = new LinkedBlockingQueue<ResponseMessage>();  
	private long interval;
	private long lastTime=0;
	@Autowired
	private DataRepository dataDao;
    private SimpMessagingTemplate template;
    @Autowired
    public ThreadProducerDynData(SimpMessagingTemplate template) {
        this.template = template;
    }
    @RequestMapping(path="/responseData", method=RequestMethod.POST)
    public void greet(ResponseMessage greeting) {
        this.template.convertAndSend("/topic/responseData", greeting);
    }
	//参数封装
    private Monitor monitor;
    
    public void setMonitor(Monitor monitor) {
        this.monitor = monitor;
    }
    
	public void setInterval(long interval){
		this.interval=interval;
	}
	public void setThreadEnable(boolean threadEnable) {
		this.threadEnable = threadEnable;
	}
	public boolean getThreadEnable() {
		return this.threadEnable;
	}
	@Override
	public void run() {
		if (false == threadEnable){
			return;
		}
		else {
			while(this.threadEnable){
				//read data form mongodb
				sendData();
				System.out.println("thread will sleep "+ String.valueOf(interval)+" ms");
				try {
					Thread.sleep(Math.abs(interval));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
    public void clearQurey() {  
        queue.clear();  
    }
	public ResponseMessage getQury(){
		return queue.poll();
	}
	public void addQury(ResponseMessage data){
		if (queue.size() >= maxCount ){
			queue.remove();
		}
		queue.offer(data);
	}
	public void clearQury(){
		queue.clear();
	}
	public boolean isEmptyQury(){
		if (null != queue.peek()){
			return false;
		}
		return true;
			
	}
	//生产者，将数据加入队列，推送到网页，显示动态曲线
	//@SendTo("/topic/responseData")
	public void sendData(){
		ResponseMessage data=new ResponseMessage();
		List<Float> a1=new ArrayList<Float>();
		List<Float> a2=new ArrayList<Float>();
		List<Float> a3=new ArrayList<Float>();
		List<Float> a4=new ArrayList<Float>();
		long end=System.currentTimeMillis();
		long begin=lastTime;
		if (lastTime <=0){
			begin=end-1000000;
		}
		lastTime=end;
		for (RecvData item:dataDao.findAllByIdandTime("factoryName","factory1", begin, end)){
			if (item != null && !item.getvalue().isEmpty())
				a1.add(Float.valueOf(item.getvalue()));
		}
		for (RecvData item:dataDao.findAllByIdandTime("factoryName","factory2", begin, end)){
			if (item != null && !item.getvalue().isEmpty())
				a2.add(Float.valueOf(item.getvalue()));
		}
		for (RecvData item:dataDao.findAllByIdandTime("factoryName","factory3", begin, end)){
			if (item != null && !item.getvalue().isEmpty())
				a3.add(Float.valueOf(item.getvalue()));
		}
		for (RecvData item:dataDao.findAllByIdandTime("factoryName","factory4", begin, end)){
			if (item != null && !item.getvalue().isEmpty())
				a4.add(Float.valueOf(item.getvalue()));
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateNowStr = sdf.format(end);
		data.setData1(a1);
		data.setData2(a2);
		data.setData3(a3);
		data.setData4(a4);
		data.setResponseMessage(dateNowStr);
		addQury(data);
		greet( getQury());
	}

}
