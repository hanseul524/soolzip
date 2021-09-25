package recipe.model.vo;

public class RecipeFile {
	private int fileNo;
	private int recipeNo;
	private String filePath;
	private String fileName;
	private long fileSize;
	private String fileContents;
	private int fileOrder;
	
	public RecipeFile(int fileNo, int recipeNo, String filePath, String fileName, long fileSize, String fileContents,
			int fileOrder) {
		super();
		this.fileNo = fileNo;
		this.recipeNo = recipeNo;
		this.filePath = filePath;
		this.fileName = fileName;
		this.fileSize = fileSize;
		this.fileContents = fileContents;
		this.fileOrder = fileOrder;
	}
	
	public RecipeFile() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}
	public int getRecipeNo() {
		return recipeNo;
	}
	public void setRecipeNo(int recipeNo) {
		this.recipeNo = recipeNo;
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
	public String getFileContents() {
		return fileContents;
	}
	public void setFileContents(String fileContents) {
		this.fileContents = fileContents;
	}
	public int getFileOrder() {
		return fileOrder;
	}
	public void setFileOrder(int fileOrder) {
		this.fileOrder = fileOrder;
	}
	
	@Override
	public String toString() {
		return "RecipeFile [fileNo=" + fileNo + ", recipeNo=" + recipeNo + ", filePath=" + filePath + ", fileName="
				+ fileName + ", fileSize=" + fileSize + ", fileContents=" + fileContents + ", fileOrder=" + fileOrder
				+ "]";
	}
	
}
