package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.GoalDetailsDAO;
import dao.GoalsDAO;
import dao.MaterialsDAO;
import model.CalculateAchievementLogic;
import model.Goal;
import model.GoalDetail;
import model.Material;
import model.User;

@WebServlet("/ViewAchievementServlet")
public class ViewAchievementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//セッションスコープからユーザー情報を取得
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		String strGoalId = request.getParameter("goalId");
		
		if(user == null) {	//ログインしていない場合
			//リダイレクト
			response.sendRedirect("WEB-INF/jsp/login.jsp");
		}else if(strGoalId == null || strGoalId.equals("")) {	//GoalIdの送信がない場合
			//リダイレクト
			response.sendRedirect("WEB-INF/jsp/login.jsp");
		}
		//受け取ったIdでGoalをDBから検索
		int goalId = Integer.parseInt(strGoalId);
		GoalsDAO gDAO = new GoalsDAO();
		Goal goal = gDAO.findById(goalId);
		
		//Goalに対応するGoalDetailを検索してリスト化
		GoalDetailsDAO gdDAO = new GoalDetailsDAO();
		List<GoalDetail> goalDetails =  gdDAO.findAllByGoal(goal);
		
		//GoalDetailリストにMaterialを紐づけてマップ化
		Map<GoalDetail, Material> mapOfGoalDetailsAndMaterial = new HashMap<GoalDetail, Material>();
		MaterialsDAO mDAO = new MaterialsDAO();
		
		for(GoalDetail goalDetail : goalDetails) {
			mapOfGoalDetailsAndMaterial.put(goalDetail, mDAO.findById(goalDetail.getMaterialId()));
		}
		
		//GoalDetailリストに達成値を紐づけてマップ化
		CalculateAchievementLogic cal = new CalculateAchievementLogic();
		Map<Integer, Integer> mapOfGoalDetailsAndAchievmentRatio = cal.calculateDoneValueByGoal(goal);
		
		for(GoalDetail goalDetail : goalDetails) {
			int value = mapOfGoalDetailsAndAchievmentRatio.get(goalDetail.getMaterialId());
			int ratio = 0;
			if(goal.getStandardTypeId() == 1) {
				ratio = 100 * value/(goalDetail.getEndTo() - goalDetail.getStartFrom());
			}else if(goal.getStandardTypeId() == 2) {
				//時間で評価機能を実装時に追加
			}
			mapOfGoalDetailsAndAchievmentRatio.put(goalDetail.getMaterialId(), ratio);
		}
		session.setAttribute("checkGoal", goal);
		session.setAttribute("goalDetails", goalDetails);
		session.setAttribute("mapOfGoalDetailsAndMaterial", mapOfGoalDetailsAndMaterial);
		session.setAttribute("mapOfGoalDetailsAndAchievmentRatio", mapOfGoalDetailsAndAchievmentRatio);
		
		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/achievement.jsp");
		dispatcher.forward(request, response);
	}

}
