package model;

import java.util.ArrayList;
import java.util.List;

import dao.ProgressDAO;

public class ViewRecordsLogic {
	public List<Progress> execute(User user){
		List<Progress> progressList = new ArrayList<>();
		ProgressDAO dao = new ProgressDAO();
		
		//ユーザーIDをもとにDBからProgressを検索
		progressList = dao.findAllByUser(user);
		
		return progressList;
	}
}