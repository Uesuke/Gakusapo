package model;

import java.io.Serializable;
import java.util.Date;

public class Progress implements Serializable {
    private int progressId;
    private int userId;
    private int goalId;
    private int materialId;
    private Date date;
    private int timeStart;
    private int timeEnd;
    private int pageStartAt;
    private int pageEndAt;

    public Progress(int progressId, int userId, int goalId,  int materialId, Date date, int timeStart, int timeEnd, int pageStartAt, int pageEndAt) {
        this.progressId = progressId;
        this.userId = userId;
        this.goalId = goalId;
        this.materialId = materialId;
        this.date = date;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.pageStartAt = pageStartAt;
        this.pageEndAt = pageEndAt;
    }

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

    public int getGoalId() {
        return goalId;
    }

    public void setGoalId(int goalId) {
        this.goalId = goalId;
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

    public int getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(int timeStart) {
        this.timeStart = timeStart;
    }
    
    public int getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(int timeEnd) {
        this.timeEnd = timeEnd;
    }

    public int getPageStartAt() {
        return pageStartAt;
    }

    public void setPageStartAt(int pageStartAt) {
        this.pageStartAt = pageStartAt;
    }

    public int getPageEndAt() {
        return pageEndAt;
    }

    public void setPageEndAt(int pageEndAt) {
        this.pageEndAt = pageEndAt;
    }

}
