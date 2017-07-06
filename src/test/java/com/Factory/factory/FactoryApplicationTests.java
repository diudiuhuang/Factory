package com.Factory.factory;

import java.text.SimpleDateFormat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FactoryApplicationTests {

	@Test
	public void contextLoads() {
	}
	@Test
	public void testTime(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		System.out.println("getTimeNowString= "+String.valueOf(ViewDataControl.getTimeNowString()));
		System.out.println("Time is Now= "+sdf.format(ViewDataControl.getTimeNowString()));
		
		System.out.println("getOldTimeString Hour= "+String.valueOf(ViewDataControl.getOldTimeString("Hour")));
		System.out.println("Time is Hour= "+sdf.format(ViewDataControl.getOldTimeString("Hour")));
		
		System.out.println("getOldTimeString Day= "+String.valueOf(ViewDataControl.getOldTimeString("Day")));
		System.out.println("Time is Day = "+sdf.format(ViewDataControl.getOldTimeString("Day")));
		
		System.out.println("getOldTimeString Week= "+String.valueOf(ViewDataControl.getOldTimeString("Week")));
		System.out.println("Time is Week = "+sdf.format(ViewDataControl.getOldTimeString("Week")));
		
		System.out.println("getOldTimeString Month= "+String.valueOf(ViewDataControl.getOldTimeString("Month")));
		System.out.println("Time is Month = "+sdf.format(ViewDataControl.getOldTimeString("Month")));
	}
	

}
