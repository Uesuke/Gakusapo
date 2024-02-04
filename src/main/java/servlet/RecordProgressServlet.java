package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Material;
import model.User;
import model.ViewMaterialLogic;

@WebServlet("/RecordProgressServlet")
public class RecordProgressServlet extends HttpServlet {
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
			ViewMaterialLogic vml = new ViewMaterialLogic();
			List<Material> materialList = vml.execute(user);
			
			//セッションスコープにリストを保存
			session.setAttribute("materialList", materialList);
			//進捗記録画面にフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/progress_record.jsp");
			dispatcher.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//セッションスコープからユーザーインスタンスを取得
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		int userId = user.getUserId();
	    int goalId = Integer.parseInt(request.getParameter("goalId"));
	    int materialId = Integer.parseInt(request.getParameter("materialId"));
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    try {
			Date date = sdf.parse((String)request.getParameter("date"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    int timeStart = Integer.parseInt(request.getParameter("timeStart"));
	    int timeEnd = Integer.parseInt(request.getParameter("timeEnd"));
	    int pageStartAt = Integer.parseInt(request.getParameter("pageStartAt"));
	    int pageEndAt = Integer.parseInt(request.getParameter("pageEndAt"));;
		
		//進捗記録処理
		
		
	}
}
