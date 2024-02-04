package model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Goal implements Serializable {
    private int goalId;
    private String goalName;
    private int userId;
    private Date dateStart;
    private Date dateEnd;
    private int standardTypeId;
    private int statusTypeId;

    public Goal(int goalId, String goalName, int userId, Date dateStart, Date dateEnd, int standardTypeId, int statusTypeId) {
        this.goalId = goalId;
        this.goalName = goalName;
        this.userId = userId;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.standardTypeId = standardTypeId;
        this.statusTypeId = statusTypeId;
    }
    
    //日付をString型で出力するメソッド
    public String getDateStartToString() {
    	String strDateStart;
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    	strDateStart = sdf.format(this.dateStart);
    	return strDateStart;
  
    }

    public String getDateEndToString() {
    	String strDateEnd;
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
    	strDateEnd = sdf.format(this.dateEnd);
    	return strDateEnd;
  
    }
    
    //目標日までの残日数を出力するメソッド
    public int getRemainingDays() {
    	//メソッド実行日を取得
		Date today = new Date();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		try {
			//年月日フォーマットを通すことで実行日の時間情報を削除
			String strToday = sdf.format(today);
			today = sdf.parse(strToday);
			
			//メソッド実行日と目標終了日をlong型に変換
			long todayTime = today.getTime();
			long dateEndTime = this.getDateEnd().getTime();
			long ms_per_one_day = 1000 * 60 * 60 * 24;
			
			//差を1日あたりのミリ秒で割り、残日数を算出
			int remainingDays = (int)((dateEndTime - todayTime)/ms_per_one_day);
			if(remainingDays > 0) {
				return remainingDays;
			}else {
				return 0;
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
			return -1;
		}
    }
    
    
    // ゲッターメソッド
    public int getGoalId() {
        return goalId;
    }

    public String getGoalName() {
        return goalName;
    }

    public int getUserId() {
        return userId;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public Date getDateEnd() {
        return dateEnd;
    }
    
    public int getStandardTypeId() {
        return standardTypeId;
    }

    public int getStatusTypeId() {
        return statusTypeId;
    }

    // セッターメソッド
    public void setGoalId(int goalId) {
        this.goalId = goalId;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public void setDateEnd(Date dateEnd) {
        this.dateEnd = dateEnd;
    }

    public void setStandardTypeId(int standardTypeId) {
        this.standardTypeId = standardTypeId;
    }
    
    public void setStatusTypeId(int statusTypeId) {
        this.statusTypeId = statusTypeId;
    }
    
}
