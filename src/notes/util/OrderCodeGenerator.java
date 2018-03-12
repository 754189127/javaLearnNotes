package notes.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderCodeGenerator {
	private  static int i=0;
	
	public static String getCode(){
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH-mm-ss-");
		return sdf.format(date) + ++i;
	}
}
