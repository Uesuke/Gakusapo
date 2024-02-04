package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.Goal;

public class GoalsDAO {

	//データベース接続に使用する情報
	private final String JDBC_URL = "jdbc:mysql://localhost:3306/shuPre?useSSL=false";
	private final String DB_USER = "root";
	private final String DB_PASS = "";
	//教材登録処理
	public Goal setGoal(Goal sg) {
		Goal goal = null;
	    String goalName = sg.getGoalName();
	    int userId = sg.getUserId();
	    Date dateStart = sg.getDateStart();
	    Date dateEnd = sg.getDateEnd();
	    int standardTypeId = sg.getStandardTypeId();
	    int statusTypeId = sg.getStatusTypeId();
		
		//JDBCドライバを読み込む
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		//データベースに接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER,DB_PASS)) {
			
			//util.Date型→String型→sql.Date型の変換
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String strDateStart = sdf.format(dateStart);
			String strDateEnd = sdf.format(dateEnd);
			java.sql.Date sqlDateStart = java.sql.Date.valueOf(strDateStart);
			java.sql.Date sqlDateEnd = java.sql.Date.valueOf(strDateEnd);
			
			//INSERT文を準備
			String sql = "INSERT INTO Goals (goalName, userId, dateStart, dateEnd, standardTypeId, statusTypeId) VALUES(?, ?, ?, ?, ?, ?);";
			PreparedStatement pStmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pStmt.setString(1, goalName);
			pStmt.setInt(2, userId);
			pStmt.setDate(3, sqlDateStart);
			pStmt.setDate(4, sqlDateEnd);
			pStmt.setInt(5, standardTypeId);
			pStmt.setInt(6, statusTypeId);
			
			//INSERT文の実行
			int affectedRows = pStmt.executeUpdate();
	        if (affectedRows == 0) {
	            throw new SQLException("目標の登録に失敗しました。");
	        }

	        //生成されたgoalIdキーを取得し、Goallインスタンスを生成
	        try (ResultSet generatedKeys = pStmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                int goalId = generatedKeys.getInt(1);
	                goal = new Goal(goalId, goalName, userId, dateStart, dateEnd, standardTypeId, statusTypeId);
	            } else {
	                throw new SQLException("goalIDの取得に失敗しました。");
	            }
	        }
		}catch(SQLException e) {
			return null;
		}
		return goal;
	}
	
}
