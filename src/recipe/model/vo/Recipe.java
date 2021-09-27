package recipe.model.vo;

import java.sql.Date;

public class Recipe {
	private int recipeNo;
	private String userId;
	private String recipeContents;
	private String recipeTitle;
	private String recipeMainDrink;
	private int recipeAlcohol;
	private String recipeTag;
	private int recipeSaveState;
	private int recipeLegendState;
	private int recipeViewCount;
	private Date recipeEnrollDate;
	private int recipeReplyCount;
	private int recipeLikeCount;
	private String fileNo; // 대표사진 id
	private String fileName;// -> 대표사진 파일을 불러오기위한 맴버 
	private RecipeFile recipeFile; // 레시피 대표사진 파일id를 넣기위한 맴버
	
	public Recipe() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	public Recipe(String recipeContents, String recipeTitle, String recipeMainDrink, int recipeAlcohol,
			String recipeTag) {
		super();
		this.recipeContents = recipeContents;
		this.recipeTitle = recipeTitle;
		this.recipeMainDrink = recipeMainDrink;
		this.recipeAlcohol = recipeAlcohol;
		this.recipeTag = recipeTag;
	}

	public Recipe(int recipeNo, String userId, String recipeContents, String recipeTitle, String recipeMainDrink,
			int recipeAlcohol, String recipeTag, int recipeSaveState, int recipeLegendState, int recipeViewCount,
			Date recipeEnrollDate, int recipeReplyCount, int recipeLikeCount, String fileNo, String fileName,
			RecipeFile recipeFile) {
		super();
		this.recipeNo = recipeNo;
		this.userId = userId;
		this.recipeContents = recipeContents;
		this.recipeTitle = recipeTitle;
		this.recipeMainDrink = recipeMainDrink;
		this.recipeAlcohol = recipeAlcohol;
		this.recipeTag = recipeTag;
		this.recipeSaveState = recipeSaveState;
		this.recipeLegendState = recipeLegendState;
		this.recipeViewCount = recipeViewCount;
		this.recipeEnrollDate = recipeEnrollDate;
		this.recipeReplyCount = recipeReplyCount;
		this.recipeLikeCount = recipeLikeCount;
		this.fileNo = fileNo;
		this.fileName = fileName;
		this.recipeFile = recipeFile;
	}


	public String getFileNo() {
		return fileNo;
	}


	public void setFileNo(String fileNo) {
		this.fileNo = fileNo;
	}


	public int getRecipeNo() {
		return recipeNo;
	}

	public void setRecipeNo(int recipeNo) {
		this.recipeNo = recipeNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRecipeContents() {
		return recipeContents;
	}

	public void setRecipeContents(String recipeContents) {
		this.recipeContents = recipeContents;
	}

	public String getRecipeTitle() {
		return recipeTitle;
	}

	public void setRecipeTitle(String recipeTitle) {
		this.recipeTitle = recipeTitle;
	}

	public String getRecipeMainDrink() {
		return recipeMainDrink;
	}

	public void setRecipeMainDrink(String recipeMainDrink) {
		this.recipeMainDrink = recipeMainDrink;
	}

	public int getRecipeAlcohol() {
		return recipeAlcohol;
	}

	public void setRecipeAlcohol(int recipeAlcohol) {
		this.recipeAlcohol = recipeAlcohol;
	}

	public String getRecipeTag() {
		return recipeTag;
	}

	public void setRecipeTag(String recipeTag) {
		this.recipeTag = recipeTag;
	}

	public int getRecipeSaveState() {
		return recipeSaveState;
	}

	public void setRecipeSaveState(int recipeSaveState) {
		this.recipeSaveState = recipeSaveState;
	}

	public int getRecipeLegendState() {
		return recipeLegendState;
	}

	public void setRecipeLegendState(int recipeLegendState) {
		this.recipeLegendState = recipeLegendState;
	}

	public int getRecipeViewCount() {
		return recipeViewCount;
	}

	public void setRecipeViewCount(int recipeViewCount) {
		this.recipeViewCount = recipeViewCount;
	}

	public Date getRecipeEnrollDate() {
		return recipeEnrollDate;
	}

	public void setRecipeEnrollDate(Date recipeEnrollDate) {
		this.recipeEnrollDate = recipeEnrollDate;
	}

	public int getRecipeReplyCount() {
		return recipeReplyCount;
	}

	public void setRecipeReplyCount(int recipeReplyCount) {
		this.recipeReplyCount = recipeReplyCount;
	}

	public int getRecipeLikeCount() {
		return recipeLikeCount;
	}

	public void setRecipeLikeCount(int recipeLikeCount) {
		this.recipeLikeCount = recipeLikeCount;
	}

	public RecipeFile getRecipeFile() {
		return recipeFile;
	}

	public void setRecipeFile(RecipeFile recipeFile) {
		this.recipeFile = recipeFile;
	}


	public String getFileName() {
		return fileName;
	}


	public void setFileName(String fileName) {
		this.fileName = fileName;
	}


	@Override
	public String toString() {
		return "Recipe [recipeNo=" + recipeNo + ", userId=" + userId + ", recipeContents=" + recipeContents
				+ ", recipeTitle=" + recipeTitle + ", recipeMainDrink=" + recipeMainDrink + ", recipeAlcohol="
				+ recipeAlcohol + ", recipeTag=" + recipeTag + ", recipeSaveState=" + recipeSaveState
				+ ", recipeLegendState=" + recipeLegendState + ", recipeViewCount=" + recipeViewCount
				+ ", recipeEnrollDate=" + recipeEnrollDate + ", recipeReplyCount=" + recipeReplyCount
				+ ", recipeLikeCount=" + recipeLikeCount + ", fileNo=" + fileNo + ", recipeFile=" + recipeFile + "]";
	}
	
	
	
	
	
}
