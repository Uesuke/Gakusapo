package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UsersDAO;
import model.LoginLogic;
import model.LoginUser;
import model.Material;
import model.Progress;
import model.User;
import model.ViewMaterialsLogic;
import model.ViewRecordsLogic;

/**
 * Servlet implementation class Login
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/login.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String accountId = request.getParameter("accountId");
		String pass = request.getParameter("pass");
		
		//ログイン処理の実行
		LoginUser loginUser = new LoginUser(accountId, pass);
		LoginLogic bo = new LoginLogic();
		boolean result = bo.execute(loginUser);
		
		//ログイン処理の成否によって処理を分岐
		if(result) {	//ログイン成功時
			//セッションスコープにユーザーインスタンスを保存
			HttpSession session = request.getSession();
			UsersDAO dao = new UsersDAO();
			User user = dao.findByLogin(loginUser);
			session.setAttribute("user", user);
			
			//記録閲覧機能処理
			//ユーザーに紐づいたProgressをArrayListに取得
			ViewRecordsLogic vrl = new ViewRecordsLogic();
			List<Progress> progressList = vrl.execute(user);
			
			//Progressに紐づいたMaterialをマップ化
			Map<Progress, Material> map = new HashMap<Progress, Material>();
			
			ViewMaterialsLogic vml = new ViewMaterialsLogic();
			List<Material> materialList = vml.execute(user);
			
			for(Progress progress : progressList) {
				for(Material material : materialList) {
					if(material.getMaterialId() == progress.getMaterialId()) {
						map.put(progress, material);
						break;
					}
				}
			}
			
			//セッションスコープにリスト、マップを保存
			session.setAttribute("progressList", progressList);
			session.setAttribute("MapOfProgressAndMaterial", map);
			
			//フォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/mypage.jsp");
			dispatcher.forward(request, response);
		}else {	//ログイン失敗時
			//リダイレクト
			response.sendRedirect("LoginServlet");
		}
	}

}