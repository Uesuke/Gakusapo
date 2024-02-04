package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
