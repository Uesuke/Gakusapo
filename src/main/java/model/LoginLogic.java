package model;

import dao.UsersDAO;

public class LoginLogic {
	public boolean execute(LoginUser loginUser) {
		UsersDAO dao = new UsersDAO();
		User user = dao.findByLogin(loginUser);
		return user != null;
	}
}
