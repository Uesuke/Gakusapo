package model;

import java.util.List;

import dao.ProgressDAO;

public class CalculateAchievementLogic {
	public int calculateWholeAndPartsByGoal(Goal goal) {
		ProgressDAO pDAO = new ProgressDAO();
		List<Progress> progressList = pDAO.findAllByGoal(goal);
	}
}
