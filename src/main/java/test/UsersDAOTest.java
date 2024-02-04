package test;

import dao.UsersDAO;
import model.LoginUser;
import model.User;

public class UsersDAOTest {

	public static void main(String[] args) {
		testFindByLoginOK();
		testFindByLoginNG();
	}

	private static void testFindByLoginNG() {
		LoginUser loginUser = new LoginUser("uesuke", "12345");
		UsersDAO dao = new UsersDAO();
		User result = dao.findByLogin(loginUser);
		if(result == null) {
			System.out.println("testFindByLoginNG:成功しました");
		}else {
			System.out.println("testFindByLoginNG:失敗しました");
		}
	}

	private static void testFindByLoginOK() {
		LoginUser login = new LoginUser("uesuke", "1234");
		UsersDAO dao = new UsersDAO();
		User result = dao.findByLogin(login);
		if(result != null) {
			System.out.println("null");
		}
		if(result.getUserId() == 1) {
			System.out.println("userId");
		}
		if(result.getAccountId().equals("uesuke")) {
			System.out.println("accountId");
		}
		if(result.getMail().equals("ueda.kun@halmail.jp")) {
			System.out.println("mail");
		}
		if(result.getName().equals("上田信介")) {
			System.out.println("name");
		}
		if(result.getUserTypeId() == 1) {
			System.out.println("userTypeId");
		}
		if(result != null
			&& result.getUserId() == 1
			&& result.getAccountId().equals("uesuke")
			&& result.getPass().equals("1234")
			&& result.getMail().equals("ueda.kun@halmail.jp")
			&& result.getName().equals("上田信介")
			&& result.getUserTypeId() == 1) {
			System.out.println("testFindByLoginOK:成功しました");
		}else {
			System.out.println("testFindByLoginOK:失敗しました");
		}
	}

}
