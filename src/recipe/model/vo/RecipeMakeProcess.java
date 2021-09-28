package recipe.model.vo;

public class RecipeMakeProcess {
	private int makeNo; // 제조 번호
	private int recipeNo; 
	private String fileNo;
	private String fileName;
	private String makeContents;
	private RecipeFile recipeFile;
	
	public RecipeMakeProcess() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RecipeMakeProcess(int makeNo, int recipeNo, String fileNo, String fileName, String makeContents,
			RecipeFile recipeFile) {
		super();
		this.makeNo = makeNo;
		this.recipeNo = recipeNo;
		this.fileNo = fileNo;
		this.fileName = fileName;
		this.makeContents = makeContents;
		this.recipeFile = recipeFile;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}



	public RecipeFile getRecipeFile() {
		return recipeFile;
	}

	public void setRecipeFile(RecipeFile recipeFile) {
		this.recipeFile = recipeFile;
	}

	public int getMakeNo() {
		return makeNo;
	}
	public void setMakeNo(int makeNo) {
		this.makeNo = makeNo;
	}
	public int getRecipeNo() {
		return recipeNo;
	}
	public void setRecipeNo(int recipeNo) {
		this.recipeNo = recipeNo;
	}
	public String getFileNo() {
		return fileNo;
	}
	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}
	public String getMakeContents() {
		return makeContents;
	}
	public void setMakeContents(String makeContents) {
		this.makeContents = makeContents;
	}

	@Override
	public String toString() {
		return "RecipeMakeProcess [makeNo=" + makeNo + ", recipeNo=" + recipeNo + ", fileNo=" + fileNo + ", fileName="
				+ fileName + ", makeContents=" + makeContents + ", recipeFile=" + recipeFile + "]";
	}
	
}
