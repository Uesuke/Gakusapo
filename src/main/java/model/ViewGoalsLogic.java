package model;

import java.util.List;

import dao.GoalsDAO;

public class ViewGoalsLogic {
	
	public List<Goal> execute(User user){
		
		GoalsDAO dao = new GoalsDAO();
		
		//ユーザー情報をもとにDBからGoalを検索
		List<Goal> goalsList = dao.findAllByUser(user);
		
		return goalsList;
	}
}
