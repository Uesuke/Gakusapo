package model;

import dao.UsersDAO;

public class SignupLogic {
	public boolean hasAccountIdIntegrity(SignupUser signupUser) {
		boolean result;
		UsersDAO dao = new UsersDAO();
		result = !(dao.hasAccountIdDuplication(signupUser.getAccountId()));
		return result;
	}
	public User execute(SignupUser signupUser) {
		UsersDAO dao = new UsersDAO();
		User user = dao.registerBySignup(signupUser);
		return user;
	}
}