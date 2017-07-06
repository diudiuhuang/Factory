package com.Factory.factory;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ViewControl {
	@Autowired
	private DataRepository dataDao;
	@Autowired
	PropertiesValue userProperties;

	public ViewControl() {
	}
	/*
	@RequestMapping(value = {"/"})
	public String Index(){
		return "index";
	}*/
	//index页面，展示静态统计和动态数据
	@RequestMapping(value = {"/index"})
    public String index(ModelMap  m) throws Exception {
		//Date now = new Date(System.currentTimeMillis());
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//String dateNowStr = sdf.format(now);
        //List<RecvData> sysDataList = new ArrayList<RecvData>();
        //RecvData u1 = new RecvData("1", "f1","ad1","L1","M1",System.currentTimeMillis(),"INT","12345");
        
        //sysDataList.add(u1);
        
        //m.addAttribute("sysDataList", sysDataList);
        //m.addAttribute("message", sysDataList.hashCode());
        //m.addAttribute("timeNow", dateNowStr);
        //m.addAttribute("factoryName", u1.getfactoryName());
        //m.addAttribute("lineName", u1.getgroupName());
        //m.addAttribute("machineName", u1.getitemName());
        //m.addAttribute("dataCount",0);
        return "index";
    }
	//客户端获取 当日 静态统计数据,timeInterval=Hour,Day,Week,Month
	 @RequestMapping(value = "/GetDataStaticAccount",method=RequestMethod.POST)
	 @ResponseBody
	public List<ResponseStaticAccountData>  getDataStatic(String timeInterval) {
		List<ResponseStaticAccountData> ret=getStaticDataAccount(timeInterval);
		return ret;
	}
	
	//按时间获取所有符合要求的数据
	public List<RecvData> getDatabyTime(String timeInterval){
		long now=ViewDataControl.getTimeNowString();
		long old=ViewDataControl.getOldTimeString(timeInterval);
		return dataDao.findAllBytime(old, now);
	}
	//按时间获取静态统计数据
	public List<ResponseStaticAccountData> getStaticDataAccount(String timeInterval){
		ViewDataControl viewDataControl=new ViewDataControl();
		return viewDataControl.accountResponseStatic(getDatabyTime(timeInterval),"factoryName");
	}
 
}
