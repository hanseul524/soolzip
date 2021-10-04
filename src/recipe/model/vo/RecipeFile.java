package recipe.model.vo;

import java.sql.Date;

public class RecipeFile {
	private int fileNo;
	private String filePath="";
	private String fileName="";
	private long fileSize;
	private Date regDate;
	private String regName;
	
	
	public RecipeFile() {
		super();
		// TODO Auto-generated constructor stub
	}
	public RecipeFile(int fileNo, String filePath, String fileName, long fileSize, Date regDate, String regName) {
		super();
		this.fileNo = fileNo;
		this.filePath = filePath;
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.regDate = regDate;
		this.regName = regName;
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
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getRegName() {
		return regName;
	}
	public void setRegName(String regName) {
		this.regName = regName;
	}
	@Override
	public String toString() {
		return "RecipeFile [fileNo=" + fileNo + ", filePath=" + filePath + ", fileName=" + fileName + ", fileSize="
				+ fileSize + ", regDate=" + regDate + ", regName=" + regName + "]";
	}
	
	
}
