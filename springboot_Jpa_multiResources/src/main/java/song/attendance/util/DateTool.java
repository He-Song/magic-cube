package song.attendance.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTool {

	DateFormat formatter = new SimpleDateFormat("hh:mm");
	
	/**
	 * 比较两个“hh:mm”格式的时间 先后顺序
	 * @param date
	 * @return
	 */
	public static boolean isbehind(String date){
		try {
			Date date1 = new SimpleDateFormat("hh:mm").parse("09:22");
			System.out.println();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return false;
	}
	public static void main(String[] args) {
		DateTool.isbehind("");
	}
}
