package test;

import java.util.List;

import model.Material;
import model.User;
import model.ViewMaterialLogic;

public class ViewMaterialLogicTest {

	public static void main(String[] args) {
		User user = new User(1, "uesuke", "上田信介", "1234", "mail@mail.com", 1);
		ViewMaterialLogic vml = new ViewMaterialLogic();
		List<Material> materialList = vml.execute(user);
		
		for(Material material: materialList) {
			System.out.println(material.getMaterialId());
			System.out.println(material.getMaterialName());
			System.out.println(material.getPageEnd());
			System.out.println(material.getPageStart());
			System.out.println(material.getSectionEnd());
			System.out.println(material.getSectionStart());
			System.out.println(material.getTotalPages());
			System.out.println(material.getTotalSections());
			System.out.println(material.getUserId());
		}
	}

}
