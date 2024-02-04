package model;

public class LoginUser {
	private String accountId;
	private String pass;
	public LoginUser(String accountId, String pass) {
		this.accountId = accountId;
		this.pass = pass;
	}
	public String getAccountId() {
		return accountId;
	}
	public String getPass() {
		return pass;
	}
}
