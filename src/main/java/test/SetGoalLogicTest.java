package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.Goal;
import model.SetGoalLogic;

public class SetGoalLogicTest {

	public static void main(String[] args) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date dateStart = sdf.parse("2024-01-24");
			Date dateEnd = sdf.parse("2024-02-11");
			Goal settingGoal = new Goal(0, "ロジックテスト合格", 6, dateStart, dateEnd,1, 1);
			SetGoalLogic sgl = new SetGoalLogic();
			Goal goal = sgl.execute(settingGoal);
			
			System.out.println(goal.getGoalId());
			System.out.println(goal.getGoalName());
			System.out.println(goal.getUserId());
			System.out.println(goal.getDateStart());
			System.out.println(goal.getDateEnd());
			System.out.println(goal.getStandardTypeId());
			System.out.println(goal.getStatusTypeId());
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
