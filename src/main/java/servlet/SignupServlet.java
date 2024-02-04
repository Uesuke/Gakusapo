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

@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/signup.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String accountId = request.getParameter("accountId");
		String name = request.getParameter("name");
		String pass = request.getParameter("pass");
		String mail = request.getParameter("mail");
		int userTypeId = Integer.parseInt(request.getParameter("userTypeId"));
		
		//データベース上のアカウントIDの重複を確認
		SignupUser signupUser = new SignupUser(accountId, name, pass, mail, userTypeId);
		SignupLogic sl = new SignupLogic();
		boolean integrityOK = sl.hasAccountIdIntegrity(signupUser);
		
		//セッションスコープに登録ユーザー情報を保存
		HttpSession session = request.getSession();
		session.setAttribute("signupUser", signupUser);
		
		if(integrityOK) {//アカウントIDの重複がなければ
			//確認画面へフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/signup_confirm.jsp");
			dispatcher.forward(request, response);
		}else {//アカウントIDに重複があった場合は
			
			//エラーメッセージをリクエストスコープに保存
			String errorMsg_Id = "このアカウントIDは既に使用されています。";
			request.setAttribute("errorMsg_Id", errorMsg_Id);
			
			//入力画面へフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/signup.jsp");
			dispatcher.forward(request, response);
		}
	}

}