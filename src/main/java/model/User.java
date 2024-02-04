package model;

import java.io.Serializable;

public class User implements Serializable {
	private int userId;
	private String accountId;
	private String name;
	private String pass;
	private String mail;
	private int userTypeId;

	public User(int userId, String accountId, String name, String pass, String mail, int userTypeId) {
		this.userId = userId;
		this.accountId = accountId;
		this.name = name;
		this.pass = pass;
		this.mail = mail;
		this.userTypeId = userTypeId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
	public int getUserId() {
		return userId;
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
