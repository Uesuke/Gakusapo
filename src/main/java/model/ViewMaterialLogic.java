package model;

import java.util.ArrayList;
import java.util.List;

import dao.MaterialsDAO;

public class ViewMaterialLogic {

	public List<Material> execute(User user) {
		List<Material> materialList = new ArrayList<>();
		MaterialsDAO dao = new MaterialsDAO();
		
		//ユーザーIDをもとにDBからMaterialを検索
		materialList = dao.findAllByUser(user);
		
		return materialList;
	}

}
