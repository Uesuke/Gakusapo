package model;

import java.io.Serializable;

public class Material implements Serializable {
	private int materialId;
	private String materialName;
	private int pageStart;
	private int pageEnd;
	private int sectionStart;
	private int sectionEnd;
	private int userId;
	
	public Material(int materialId, String materialName, int pageStart, int pageEnd, int sectionStart, int sectionEnd, int userId) {
		this.materialId = materialId;
		this.materialName = materialName;
		this.pageStart = pageStart;
		this.pageEnd = pageEnd;
		this.sectionStart = sectionStart;
		this.sectionEnd = sectionEnd;
		this.userId = userId;
	}
	
	//固有のメソッド
	
	public int getTotalPages() {
		int totalPages = this.pageEnd - this.pageStart + 1;
		return totalPages;
	}
	
	public int getTotalSections() {
		int totalSections = this.sectionEnd - this.sectionStart + 1;
		return totalSections;
	}
	
	//セッター、ゲッターメソッド
	
	public void setMaterialId(int materialId) {
        this.materialId = materialId;
    }

    public int getMaterialId() {
        return materialId;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setPageStart(int pageStart) {
        this.pageStart = pageStart;
    }

    public int getPageStart() {
        return pageStart;
    }

    public void setPageEnd(int pageEnd) {
        this.pageEnd = pageEnd;
    }

    public int getPageEnd() {
        return pageEnd;
    }

    public void setSectionStart(int sectionStart) {
        this.sectionStart = sectionStart;
    }

    public int getSectionStart() {
        return sectionStart;
    }

    public void setSectionEnd(int sectionEnd) {
        this.sectionEnd = sectionEnd;
    }

    public int getSectionEnd() {
        return sectionEnd;
    }

    
    public void setUserId(int userId) {
    	this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }
}
