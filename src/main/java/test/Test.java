package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

	public static void main(String[] args) {
		Date today = new Date();
		
		String str = "20240224";
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		try {
			Date finish = sdf.parse(str);
			long ms_per_one_day = 1000 * 60 * 60 * 24;
			int todayTime = (int)(today.getTime() / ms_per_one_day);
			int finishTime = (int)(finish.getTime() / ms_per_one_day);
			
			int daysUntilFinish = finishTime - todayTime;
			System.out.println(daysUntilFinish);
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

}
