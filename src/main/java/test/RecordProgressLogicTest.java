package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.Progress;
import model.RecordProgressLogic;

public class RecordProgressLogicTest {

	public static void main(String[] args) throws ParseException {
		RecordProgressLogic rpl = new RecordProgressLogic();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse("2024-02-09");
		Progress sp = new Progress(
					0,
					6,
					14,
					date,
					8700,
					46,
					70,
					true
				);
		Progress progress = rpl.execute(sp);
		System.out.println(progress.getProgressId());
		System.out.println(progress.getMaterialId());
		System.out.println(sdf.format(date));
		System.out.println(progress.getTime());
		System.out.println(progress.getHour());
		System.out.println(progress.getMunite());
		System.out.println(progress.getPageStart());
		System.out.println(progress.getPageEnd());
		System.out.println(progress.getIsShared());
	}

}
