package test;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import dao.GoalDetailsDAO;
import dao.GoalsDAO;
import dao.ProgressDAO;
import dao.UsersDAO;
import model.Goal;
import model.GoalDetail;
import model.LoginUser;
import model.Progress;
import model.User;

public class ViewRecordsTest {

	public static void main(String[] args) {
		LoginUser loginUser = new LoginUser("sample", "");
		UsersDAO uDAO = new UsersDAO();
		User user = uDAO.findByLogin(loginUser);
		ProgressDAO pDAO = new ProgressDAO();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		
		GoalsDAO gDAO = new GoalsDAO();
		GoalDetailsDAO gdDAO = new GoalDetailsDAO();
		
		Goal goal = gDAO.findById(2);
		List<GoalDetail> goalDetailsList = gdDAO.findAllByGoal(goal);
		
		System.out.println(goal.getGoalId() + ": " + goal.getGoalName());
		System.out.println(sdf.format(goal.getDateStart()) + "～" + sdf.format(goal.getDateEnd()));
		
		for(GoalDetail gd : goalDetailsList) {
			System.out.println(gd.getGoalId() + ": " + gd.getMaterialId());
		}
		
		Map<GoalDetail, List<Progress>> map = pDAO.findAllByGoalAndDetails(goal, goalDetailsList);
		for(GoalDetail goalDetail : goalDetailsList) {
			List<Progress> progressList = map.get(goalDetail);
			System.out.println("GoalDetailId: " + goalDetail.getGoalId());
			for(Progress p : progressList) {
				System.out.println("ID: " + p.getProgressId());
				System.out.println("userId: " + p.getUserId());
				System.out.println("materialId: " + p.getMaterialId());
				System.out.println("pageStart: " + p.getPageStart());
				System.out.println("pageEnd: " + p.getPageEnd());
				System.out.println("time: " + p.getTime());
				System.out.println("shared: " + p.getIsShared());
				String date = sdf.format(p.getDate());
				System.out.println("date:" + date);
				System.out.println("-------------");
			}
		}
	}

}
