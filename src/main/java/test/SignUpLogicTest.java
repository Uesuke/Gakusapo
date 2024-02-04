package test;

import model.SignupLogic;
import model.SignupUser;

public class SignUpLogicTest {

	public static void main(String[] args) {
		SignupUser su = new SignupUser("YamaHana", "山田花子", "12345", "h.yamada@Halmail.com", 2);
		SignupLogic sl = new SignupLogic();
		boolean integrityOK = sl.hasAccountIdIntegrity(su);	
		
		if(integrityOK) {
			System.out.println("OK,登録処理を実行します");
			sl.execute(su);
		}else {
			System.out.println("NG,既存のアカウントIDです");
		}
		
		
	}
}
