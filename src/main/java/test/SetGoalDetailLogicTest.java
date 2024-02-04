package test;

import model.GoalDetail;
import model.SetGoalDetailLogic;

public class SetGoalDetailLogicTest {
	public static void main(String[] args) {
		GoalDetail settingGD = new GoalDetail(2, 15, 1, 80);
		SetGoalDetailLogic sgdl = new SetGoalDetailLogic();
		GoalDetail gd = sgdl.execute(settingGD);
		System.out.println(gd.getGoalId());
		System.out.println(gd.getMaterialId());
		System.out.println(gd.getStartFrom());
		System.out.println(gd.getEndTo());
	}

}
