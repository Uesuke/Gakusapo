package model;

import java.io.Serializable;

public class GoalDetail implements Serializable {
    private int goalId;
    private int materialId;
    private int startFrom;
    private int endTo;

    public GoalDetail(int goalId, int materialId, int startFrom, int endTo) {
        this.goalId = goalId;
        this.materialId = materialId;
        this.startFrom = startFrom;
        this.endTo = endTo;
    }

    // ゲッターメソッド
    public int getGoalId() {
        return goalId;
    }

    public int getMaterialId() {
        return materialId;
    }

    public int getStartFrom() {
        return startFrom;
    }

    public int getEndTo() {
        return endTo;
    }

    // セッターメソッド
    public void setGoalId(int goalId) {
        this.goalId = goalId;
    }

    public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public void setStartFrom(int startFrom) {
        this.startFrom = startFrom;
    }

    public void setEndTo(int endTo) {
        this.endTo = endTo;
    }
}
