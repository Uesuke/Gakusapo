package model;

import dao.GoalsDAO;

public class SetGoalLogic {

	public Goal execute(Goal settingGoal) {
		GoalsDAO dao = new GoalsDAO();
		Goal goal = dao.setGoal(settingGoal);
		return goal;
	}
	
}
