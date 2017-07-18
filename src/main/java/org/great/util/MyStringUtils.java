package org.great.util;
import java.util.Date;
import java.util.Random;

public class MyStringUtils {
	/** 
	 * 创建UUID 
	 * @return 
	 */  
	public static synchronized String makeUUID() {  
	    Date date = new Date();  
	    StringBuffer s = new StringBuffer(DateUtil.format(date,DateUtil.DATE_TIME_PATTERN));  
	    return s.append((new Random().nextInt(900) + 100)).toString();  
	}  
}
