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

import model.Material;
import model.Progress;
import model.User;
import model.ViewMaterialsLogic;
import model.ViewRecordsLogic;

/**
 * Servlet implementation class ViewRecordsServlet
 */
@WebServlet("/ViewRecordsServlet")
public class ViewRecordsServlet extends HttpServlet {
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
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/records.jsp");
			dispatcher.forward(request, response);
		}
	}

}
