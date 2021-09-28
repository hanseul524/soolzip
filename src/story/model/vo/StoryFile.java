package story.model.vo;

public class StoryFile {
	private int fileNo;
	private String filePath;
	private String fileName;
	private long fileSize;
	
	public StoryFile() {
		super();
	}
	
	public StoryFile(int fileNo, String filePath, String fileName, long fileSize) {
		super();
		this.fileNo = fileNo;
		this.filePath = filePath;
		this.fileName = fileName;
		this.fileSize = fileSize;
	}


	public int getFileNo() {
		return fileNo;
	}

	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	@Override
	public String toString() {
		return "StoryFile [fileNo=" + fileNo + ", filePath=" + filePath + ", fileName=" + fileName + ", fileSize="
				+ fileSize + "]";
	}
	
}
