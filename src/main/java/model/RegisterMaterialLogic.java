package model;

import dao.MaterialsDAO;

public class RegisterMaterialLogic {

	public Material execute(Material registeringMaterial) {
		MaterialsDAO dao = new MaterialsDAO();
		Material material = dao.registerMaterial(registeringMaterial);
		return material;
	}

}
