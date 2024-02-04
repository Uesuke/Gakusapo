package model;

import java.util.Map;

import dao.GoalDetailsDAO;
import dao.MaterialsDAO;

public class SetGoalDetailLogic {

	public GoalDetail execute(GoalDetail settingGoalDetail) {
		GoalDetailsDAO dao = new GoalDetailsDAO();
		GoalDetail goalDetail = dao.setGoalDetail(settingGoalDetail);
		return goalDetail;
	}
	public Map<GoalDetail, Material> mapGoalDetailAndMaterial(
			Map<GoalDetail, Material> map,
			GoalDetail goalDetail){
		//目標詳細に対応するIDを持つ教材をDBから検索し、
		MaterialsDAO dao = new MaterialsDAO();
		int materialId = goalDetail.getMaterialId();
		Material material = dao.findById(materialId);
		//ハッシュマップで紐づける
		map.put(goalDetail, material);
		return map;
	}
}
