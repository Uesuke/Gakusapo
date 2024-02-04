package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.LoginUser;
import model.SignupUser;
import model.User;

//ユーザー情報を扱うDAO
public class UsersDAO {
	//データベース接続に使用する情報
	private final String JDBC_URL = "jdbc:mysql://localhost:3306/shuPre?useSSL=false";
	private final String DB_USER = "root";
	private final String DB_PASS = "";
	
	//サインアップ処理
	public User registerBySignup(SignupUser signupUser) {
		User user = null;
		String accountId = signupUser.getAccountId();
		String name = signupUser.getName();
		String pass = signupUser.getPass();
		String mail = signupUser.getMail();
		int userTypeId = signupUser.getUserTypeId();
		
		//JDBCドライバを読み込む
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		//データベースに接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER,DB_PASS)) {
			//INSERT文を準備
			String sql = "INSERT INTO Users (accountId, name, pass, mail, userTypeId) VALUES(?, ?, ?, ?, ?);";
			PreparedStatement pStmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			pStmt.setString(1, accountId);
			pStmt.setString(2, name);
			pStmt.setString(3, pass);
			pStmt.setString(4, mail);
			pStmt.setInt(5, userTypeId);
			
			//INSERT文の実行
			int affectedRows = pStmt.executeUpdate();
	        if (affectedRows == 0) {
	            throw new SQLException("ユーザーの登録に失敗しました。");
	        }
			
	        //生成されたuserIdキーを取得し、Userインスタンスを生成
	        try (ResultSet generatedKeys = pStmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                int userId = generatedKeys.getInt(1);
	                user = new User(userId, accountId, name, pass, mail, userTypeId);
	            } else {
	                throw new SQLException("ユーザーIDの取得に失敗しました。");
	            }
	        }			
		}catch(SQLException e) {
			return null;
		}
		return user;
	}
	
	//ログイン処理
	public User findByLogin(LoginUser loginUser) {
		User user = null;
		//JDBCドライバを読み込む
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		//データベースに接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER,DB_PASS)) {
			//SELECT文を準備
			String sql = "SELECT userId, accountId, name, pass, mail, userTypeId FROM Users WHERE accountId = ? AND pass = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, loginUser.getAccountId());
			pStmt.setString(2, loginUser.getPass());
			
			//SELECT文を実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();
			
			if(rs.next()) {
				//ユーザーが存在したらデータを取得
				//そのユーザーを表すUserインスタンスを生成
				int userId = rs.getInt("userId");
				String accountId = rs.getString("accountId");
				String name = rs.getString("name");
				String pass = rs.getString("pass");
				String mail = rs.getString("mail");
				int userTypeId = rs.getInt("userTypeId");
				user = new User(userId, accountId, name, pass, mail, userTypeId);
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return user;
	}
	
	//重複するアカウントIDがDB上に存在するか確認する処理
	public boolean hasAccountIdDuplication(String accountId) {
		boolean hasDupli = false;
		//JDBCドライバを読み込む
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(ClassNotFoundException e) {
			throw new IllegalStateException("JDBCドライバを読み込めませんでした");
		}
		//データベースに接続
		try(Connection conn = DriverManager.getConnection(JDBC_URL, DB_USER,DB_PASS)) {
			String sql = "SELECT COUNT(*) AS num FROM Users WHERE accountId = ?;";
			PreparedStatement pStmt = conn.prepareStatement(sql);
			pStmt.setString(1, accountId);
			
			//SELECT文を実行し、結果表を取得
			ResultSet rs = pStmt.executeQuery();
			
			if (rs.next()) {
			    int numOfDupli = rs.getInt("num");
			    if (numOfDupli != 0) {
			        hasDupli = true;
			    }
			}
		}catch(SQLException e) {
			e.printStackTrace();
			return true;
		}
		return hasDupli;
	}
}
