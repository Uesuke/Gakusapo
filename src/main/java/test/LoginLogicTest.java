package test;

import model.LoginLogic;
import model.LoginUser;

public class LoginLogicTest {
	public static void main(String[] args) {
		testExecuteOK();
		testExecuteNG();
	}

	private static void testExecuteNG() {
		LoginUser loginUser = new LoginUser("uesuke", "12345");
		LoginLogic bo = new LoginLogic();
		boolean result = bo.execute(loginUser);
		if(!result) {
			System.out.println("testExecuteNG:成功しました");
		}else {
			System.out.println("testExecuteNG:失敗しました");
		}
	}

	private static void testExecuteOK() {
		LoginUser loginUser = new LoginUser("uesuke", "1234");
		LoginLogic bo = new LoginLogic();
		boolean result = bo.execute(loginUser);
		if(result) {
			System.out.println("testExecuteOK:成功しました");
		}else {
			System.out.println("testExecuteOK:失敗しました");
		}
	}
}