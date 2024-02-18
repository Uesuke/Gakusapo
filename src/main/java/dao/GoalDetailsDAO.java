package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Goal;
import model.GoalDetail;

public class GoalDetailsDAO {
	
	//データベース接続に使用する情報
	private final String JDBC_URL = "jdbc:mysql://localhost:3306/shuPre?useSSL=false";
	private final String DB_USER = "root";
	private final String DB_PASS = "";

	public GoalDetail setGoalDetail(GoalDetail gd) {
		GoalDetail goalDetail = null;
		int goalId = gd.getGoalId();
	    int materialId = gd.getMaterialId();
	    int startFrom = gd.getStartFrom();
	    int endTo = gd.getEndTo();
		
		//JDBCドライバを読み込む
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		//データベースに接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER,DB_PASS)) {

			//INSERT文を準備
			String sql = "INSERT INTO GoalDetails (goalId, materialId, startFrom, endTo) VALUES(?, ?, ?, ?);";
			PreparedStatement pStmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pStmt.setInt(1, goalId);
			pStmt.setInt(2, materialId);
			pStmt.setInt(3, startFrom);
			pStmt.setInt(4, endTo);
			
			//INSERT文の実行
			int affectedRows = pStmt.executeUpdate();
	        if (affectedRows == 0) {
	            throw new SQLException("目標詳細の登録に失敗しました。");
	        }else {
	        	goalDetail = new GoalDetail(goalId, materialId, startFrom, endTo);
	        	return goalDetail;
	        }
		}catch(SQLException e) {
			return null;
		}
	}
	public List<GoalDetail> findAllByGoal(Goal goal){
		GoalDetail goalDetail = null;
		List<GoalDetail> goalDetailList = new ArrayList<GoalDetail>();
		int goalId = goal.getGoalId();
		//JDBCドライバを読み込む
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		//データベースに接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER,DB_PASS)) {
			//SELECT文を準備
			String sql = "SELECT * FROM GoalDetails WHERE goalId = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setInt(1, goalId);
			
			//SELECT文を実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();
			
			while(rs.next()) {
				int materialId = rs.getInt("materialId");
				int startFrom = rs.getInt("startFrom");
				int endTo = rs.getInt("endTo");
				goalDetail = new GoalDetail(goalId, materialId, startFrom, endTo);
				goalDetailList.add(goalDetail);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return goalDetailList;
	}
}
