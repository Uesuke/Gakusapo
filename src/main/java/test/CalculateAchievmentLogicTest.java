package test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.GoalDetailsDAO;
import dao.GoalsDAO;
import dao.ProgressDAO;
import model.Goal;
import model.GoalDetail;
import model.Progress;

public class CalculateAchievmentLogicTest {

	public static void main(String[] args) {
		
		GoalsDAO gDAO = new GoalsDAO();
		
		Goal goal = gDAO.findById(7);
		
//		Map<GoalDetail, Integer> resultMap = cal.calculateDoneValueByGoal(goal);
		
	
		GoalDetailsDAO gdDAO = new GoalDetailsDAO();
		List<GoalDetail> goalDetailsList = gdDAO.findAllByGoal(goal);
		
		
		ProgressDAO pDAO = new ProgressDAO();
		Map<GoalDetail, List<Progress>> inputMap =
				pDAO.findAllByGoalAndDetails(goal, goalDetailsList);
		Map<GoalDetail, Integer> resultMap = new HashMap<GoalDetail, Integer>();
		
		if(goal.getStandardTypeId() == 1) {	//取り組みページ数で評価する場合
			for(GoalDetail goalDetail : goalDetailsList) {
				List<Integer> pageStartList = new ArrayList<Integer>();
				List<Integer> pageEndList = new ArrayList<Integer>();
				int min = goalDetail.getStartFrom();
				int max = goalDetail.getEndTo();
				int idx = -1;
				List<Progress> progressList = inputMap.get(goalDetail);
				for(int i=0; i<progressList.size(); i++) {
					//一つ目の要素の格納
					Integer pageStart = progressList.get(i).getPageStart();
					Integer pageEnd =progressList.get(i).getPageEnd();
					if(i == 0) {
						if(min > pageStart) {
							pageStartList.add(min);
						}else {
							pageStartList.add(pageStart);
						}
						if(max < pageEnd) {
							pageEndList.add(max);
							idx++;
							break;
						}else {
							pageEndList.add(pageEnd);
							idx++;
							continue;
						}
					}
					//二つ目以降の要素の格納
					if(pageEndList.get(idx) >= pageStart - 1	//開始ページが末尾の要素の終了ページ+1以下で
							&& pageEndList.get(idx) <= pageEnd) {	//終了ページが格納済みの終了ページ以上のとき
						
						pageEndList.set(idx, pageEnd);	//終了ページを置き換えてページ範囲を連結する
						
					}else if(pageEndList.get(idx) < pageStart - 1) {	//開始ページが末尾の要素の終了ページ+1以上のとき
						
						//ページ範囲を新しくリストに追加
						pageStartList.add(pageStart);
						pageEndList.add(pageEnd);
						idx++;
					}
					//終了ページリストの末尾と目標範囲の末尾(max)の比較
					if(pageEndList.get(idx) >= max) {	//格納済み終了ページが目標値の末尾を超えていれば
						
						pageEndList.set(idx, max);	//終了ページの末尾にmaxを代入して操作を終了
						break;
					}
				}
				System.out.println("ID: " + goalDetail.getMaterialId());
				
				Integer sumOfDonePages = 0;
				for(int i=0; i<pageEndList.size(); i++) {
					System.out.println(pageEndList.get(i) + "-" + pageStartList.get(i) + "+1");
					int pages = pageEndList.get(i) - pageStartList.get(i) + 1;
					sumOfDonePages += pages;
				}
				resultMap.put(goalDetail, sumOfDonePages);
			}
		}
		else if(goal.getStandardTypeId() == 2) {	//取り組み時間で評価する場合
			for(GoalDetail goalDetail : goalDetailsList) {
				List<Progress> progressList = inputMap.get(goalDetail);
				Integer sumTime = 0;
				for(Progress progress : progressList) {
					sumTime += progress.getTime();
				}
				resultMap.put(goalDetail, sumTime);
			}
		}
		
		for(GoalDetail goalDetail : goalDetailsList) {
			System.out.println("materialID: " + goalDetail.getMaterialId());
			int num = resultMap.get(goalDetail);
			System.out.println(num);
		}
	
	}

}
