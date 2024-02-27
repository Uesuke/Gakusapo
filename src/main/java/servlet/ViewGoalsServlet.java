package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Goal;
import model.User;
import model.ViewGoalsLogic;

@WebServlet("/ViewGoalsServlet")
public class ViewGoalsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//セッションスコープからユーザー情報を取得
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		if(user == null) {	//ログインしていない場合
			//リダイレクト
			response.sendRedirect("WEB-INF/jsp/login.jsp");
		}
		else {	//ログイン済みの場合
			//ユーザーに紐づいたMaterialをArrayListに取得
			ViewGoalsLogic vgl = new ViewGoalsLogic();
			List<Goal> goalsList = vgl.execute(user);
			
			//セッションスコープにリストを保存
			session.setAttribute("goalsList", goalsList);
			//フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/goals.jsp");
			dispatcher.forward(request, response);
		}
	}

}
