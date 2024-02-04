package model;

public class SignupUser{
	private String accountId;
	private String name;
	private String pass;
	private String mail;
	private int userTypeId;
	public SignupUser( 
		String accountId, 
		String name, 
		String pass, 
		String mail, 
		int userTypeId) {
		
		this.accountId = accountId;
		this.name = name;
		this.pass = pass;
		this.mail = mail;
		this.userTypeId = userTypeId;
	}
	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public void setuserTypeId(int userTypeId) {
		this.userTypeId = userTypeId;
	}
	public String getAccountId() {
		return accountId;
	}
	public String getName() {
		return name;
	}
	public String getPass() {
		return pass;
	}
	public String getMail() {
		return mail;
	}
	public int getUserTypeId() {
		return userTypeId;
	}
}
