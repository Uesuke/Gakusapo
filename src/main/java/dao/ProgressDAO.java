package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import model.Progress;

public class ProgressDAO {
	//データベース接続に使用する情報
		private final String JDBC_URL = "jdbc:mysql://localhost:3306/shuPre?useSSL=false";
		private final String DB_USER = "root";
		private final String DB_PASS = "";
		//教材登録処理
		public Progress recordProgress(Progress recordingProgress) {
			Progress progress = null;
		    int userId = recordingProgress.getUserId();
		    int materialId = recordingProgress.getMaterialId();
		    Date date = recordingProgress.getDate();
		    int time = recordingProgress.getTime();
		    int pageStart = recordingProgress.getPageStart();
		    int pageEnd = recordingProgress.getPageEnd();
		    boolean isShared = recordingProgress.getIsShared();
		    int isSharedNumber;
		    if(isShared) {
		    	isSharedNumber = 1;
		    }else {
		    	isSharedNumber = 0;
		    }
			
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
				String strDate = sdf.format(date);
				java.sql.Date sqlDate = java.sql.Date.valueOf(strDate);
				
				//INSERT文を準備
				String sql = "INSERT INTO"
						+ "	Progress (userId, materialId, date, time, pageStart, pageEnd, isShared)"
						+ "	VALUES (?, ?, ?, ?, ?, ?, ?)"
						+ ";";
				PreparedStatement pStmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
				pStmt.setInt(1, userId);
				pStmt.setInt(2, materialId);
				pStmt.setDate(3, sqlDate);
				pStmt.setInt(4, time);
				pStmt.setInt(5, pageStart);
				pStmt.setInt(6, pageEnd);
				pStmt.setInt(7, isSharedNumber);
				
				//INSERT文の実行
				int affectedRows = pStmt.executeUpdate();
		        if (affectedRows == 0) {
		            throw new SQLException("進捗のDB登録に失敗しました。");
		        }

		        //生成されたprogressIdキーを取得し、Progressインスタンスを生成
		        try (ResultSet generatedKeys = pStmt.getGeneratedKeys()) {
		            if (generatedKeys.next()) {
		                int progressId = generatedKeys.getInt(1);
		                progress = new Progress(progressId, userId, materialId,  date, time, pageStart, pageEnd, isShared);
		            } else {
		                throw new SQLException("ProgressIDの取得に失敗しました。");
		            }
		        }
			}catch(SQLException e) {
				return null;
			}
			return progress;
		}
}
