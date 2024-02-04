package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.SignupLogic;
import model.SignupUser;

@WebServlet("/CompleteSignupServlet")
public class CompleteSignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//セッションスコープから登録ユーザー情報を受け取る
		HttpSession session = request.getSession();
		SignupUser signupUser = (SignupUser)session.getAttribute("signupUser");
		
		//ユーザー登録処理を実行
		SignupLogic sl = new SignupLogic();
		sl.execute(signupUser);
		
		//セッションスコープに保存したユーザー情報を破棄
		session.removeAttribute("signupUser");
		
		//完了画面へフォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/signup_completed.jsp");
		dispatcher.forward(request, response);
	}

}
