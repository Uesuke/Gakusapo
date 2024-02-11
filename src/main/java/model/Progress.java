package model;

import java.io.Serializable;
import java.util.Date;

public class Progress implements Serializable {
    private int progressId;
    private int userId;
    private int materialId;
    private Date date;
    private int time;	//取り組み時間を秒単位で扱う
    private int pageStart;
    private int pageEnd;
    private boolean isShared;

    public Progress(int progressId, int userId, int materialId, Date date, int time, int pageStart, int pageEnd, boolean isShared) {
        this.progressId = progressId;
        this.userId = userId;
        this.materialId = materialId;
        this.date = date;
        this.time = time;
        this.pageStart = pageStart;
        this.pageEnd = pageEnd;
        this.isShared = isShared;
    }
    
    //時間、分のゲッターメソッド
    public int getHour() {
    	int hour = this.time / 60 / 60;
    	return hour;
    }
    public int getMunite() {
    	int munite = (this.time % (60 * 60)) / 60;
    	return munite;
    }
    
    //プロパティのゲッターセッター
    
    public int getProgressId() {
        return progressId;
    }

    public void setProgressId(int progressId) {
        this.progressId = progressId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getPageStart() {
        return pageStart;
    }

    public void setPageStart(int pageStart) {
        this.pageStart = pageStart;
    }

    public int getPageEnd() {
        return pageEnd;
    }

    public void setPageEnd(int pageEnd) {
        this.pageEnd = pageEnd;
    }
    
    public boolean getIsShared() {
        return isShared;
    }

    public void setIsShared(boolean isShared) {
        this.isShared = isShared;
    }
}
