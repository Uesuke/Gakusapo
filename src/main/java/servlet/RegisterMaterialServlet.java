package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Material;
import model.RegisterMaterialLogic;
import model.User;

@WebServlet("/RegisterMaterialServlet")
public class RegisterMaterialServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//フォワード
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/material_register.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//セッションスコープからユーザーインスタンスを取得
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		//リクエストパラメータの取得
		request.setCharacterEncoding("UTF-8");
		String materialName = request.getParameter("materialName");
		int pageStart = Integer.parseInt(request.getParameter("pageStart"));
		int pageEnd = Integer.parseInt(request.getParameter("pageEnd"));
		int sectionStart = 0;
		if(request.getParameter("pageStart").equals("") && request.getParameter("sectionStart") != null) {
			sectionStart = Integer.parseInt(request.getParameter("sectionStart"));
		}
		int sectionEnd = 0;
		if(request.getParameter("pageEnd").equals("") && request.getParameter("sectionEnd") != null) {
			sectionEnd = Integer.parseInt(request.getParameter("sectionEnd"));
		}
		int userId = user.getUserId();
		
		//教材登録処理
		Material registeringMaterial = new Material(0, materialName, pageStart, pageEnd, sectionStart, sectionEnd, userId);
		RegisterMaterialLogic rml = new RegisterMaterialLogic();
		Material material = rml.execute(registeringMaterial);
		
		if(material != null) {
			//登録した教材をセッションスコープに保存
			session.setAttribute("material", material);
			//教材一覧画面へフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/register_material_completed.jsp");
			dispatcher.forward(request, response);
		}
		else {//SQLExceptionにより登録失敗した場合
			//エラーメッセージをリクエストスコープに保存
			String errorMsg_Id = "教材の登録に失敗しました。";
			request.setAttribute("errorMsg_Id", errorMsg_Id);
			
			//入力画面へフォワード
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/material_register.jsp");
			dispatcher.forward(request, response);
		}
			
	}

}
