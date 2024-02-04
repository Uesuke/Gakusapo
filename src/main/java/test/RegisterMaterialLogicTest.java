package test;

import model.Material;
import model.RegisterMaterialLogic;

public class RegisterMaterialLogicTest {


	public static void main(String[] args) {
		String materialName = "";
		int pageStart = 1;
		int pageEnd = 120;
		int sectionStart = 1;
		int sectionEnd = 12;
		int userId = 1;
		
		Material registeringMaterial =
				new Material(
						0,
						materialName,
						pageStart,
						pageEnd,
						sectionStart,
						sectionEnd,
						userId
						);
		
		RegisterMaterialLogic rml = new RegisterMaterialLogic();
		Material material = rml.execute(registeringMaterial);
		System.out.println(material.getMaterialId());
		System.out.println(material.getMaterialName());
		System.out.println(material.getPageStart());
		System.out.println(material.getPageEnd());
		System.out.println(material.getSectionStart());
		System.out.println(material.getSectionEnd());
		System.out.println(material.getUserId());
	}

}
