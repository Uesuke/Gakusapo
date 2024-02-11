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

import dao.MaterialsDAO;
import model.Material;
import model.Progress;
import model.RecordProgressLogic;
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
		
	    try {
	    	//スコープから進捗に関するプロパティを取得
	    	int userId = user.getUserId();
		    int materialId = Integer.parseInt(request.getParameter("materialId"));
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(request.getParameter("date"));
			int hour = Integer.parseInt(request.getParameter("hour"));
			int munite = Integer.parseInt(request.getParameter("munite"));
			int time = (hour * 60 * 60) + (munite * 60);
		    int pageStart = Integer.parseInt(request.getParameter("pageStart"));
		    int pageEnd = Integer.parseInt(request.getParameter("pageEnd"));
		    int isSharedNumber = Integer.parseInt(request.getParameter("isShared"));
		    boolean isShared = false;
		    if(isSharedNumber == 1) {
		    	isShared = true;
		    }
			
			//進捗記録処理
			Progress recordingProgress = new Progress(0, userId, materialId, date, time, pageStart, pageEnd, isShared);
			RecordProgressLogic rpl = new RecordProgressLogic();
			Progress progress = rpl.execute(recordingProgress);
			
			MaterialsDAO mDAO = new MaterialsDAO();
			Material material = mDAO.findById(materialId);
			
			//進捗情報と教材情報をリクエストスコープに保存
			request.setAttribute("progress", progress);
			request.setAttribute("material", material);
			
			//登録完了画面にフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/progress_record_completed.jsp");
			dispatcher.forward(request, response);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
