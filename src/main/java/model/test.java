package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.GoalDetailsDAO;
import dao.GoalsDAO;

public class test {

	public static void main(String[] args) {
		CalculateAchievementLogic cal = new CalculateAchievementLogic();
		
		GoalsDAO gDAO = new GoalsDAO();
		Goal goal = gDAO.findById(7);
		
		GoalDetailsDAO gdDAO = new GoalDetailsDAO();
		List<GoalDetail> goalDetailsList = gdDAO.findAllByGoal(goal);
		
		Map<Integer, Integer> resultMap = new HashMap<Integer, Integer>();
		resultMap = cal.calculateDoneValueByGoal(goal);
		for(GoalDetail goalDetail : goalDetailsList) {
			System.out.println("materialID: " + goalDetail.getMaterialId());
			int num = resultMap.get(goalDetail.getMaterialId());
			System.out.println(num);
		}
	}

}
