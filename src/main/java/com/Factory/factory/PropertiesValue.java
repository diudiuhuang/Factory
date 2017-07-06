package com.Factory.factory;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

//用户在application.properties中的操作,读取配置文件
@ConfigurationProperties(prefix = "com.web.user")
@Component
public class PropertiesValue {
	private String webRefreshTime;
	
	public void setwebRefreshTime(String name) {
        this.webRefreshTime = name;
	}

	public String getwebRefreshTime() {
        return webRefreshTime;
 	}
	@Override
	public String toString() {
        return "userProperties[webRefreshTime=" + webRefreshTime +"]";
	}

}
