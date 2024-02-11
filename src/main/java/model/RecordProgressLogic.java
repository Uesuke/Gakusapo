package model;

import dao.ProgressDAO;

public class RecordProgressLogic {

	public Progress execute(Progress recordingProgress) {
		ProgressDAO dao = new ProgressDAO();
		Progress progress = dao.recordProgress(recordingProgress);
		return progress;
	}
}
