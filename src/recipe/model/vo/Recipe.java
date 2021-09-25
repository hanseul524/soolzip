package recipe.model.vo;

import java.sql.Date;

public class Recipe {
	private int recipeNo;
	private int userNo;
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

	public Recipe(int recipeNo, int userNo, String recipeContents, String recipeTitle, String recipeMainDrink,
			int recipeAlcohol, String recipeTag, int recipeSaveState, int recipeLegendState, int recipeViewCount,
			Date recipeEnrollDate, int recipeReplyCount, int recipeLikeCount) {
		super();
		this.recipeNo = recipeNo;
		this.userNo = userNo;
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
	}

	public int getRecipeNo() {
		return recipeNo;
	}

	public void setRecipeNo(int recipeNo) {
		this.recipeNo = recipeNo;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
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

	@Override
	public String toString() {
		return "Recipe [recipeNo=" + recipeNo + ", userNo=" + userNo + ", recipeContents=" + recipeContents
				+ ", recipeTitle=" + recipeTitle + ", recipeMainDrink=" + recipeMainDrink + ", recipeAlcohol="
				+ recipeAlcohol + ", recipeTag=" + recipeTag + ", recipeSaveState=" + recipeSaveState
				+ ", recipeLegendState=" + recipeLegendState + ", recipeViewCount=" + recipeViewCount
				+ ", recipeEnrollDate=" + recipeEnrollDate + ", recipeReplyCount=" + recipeReplyCount
				+ ", recipeLikeCount=" + recipeLikeCount + "]";
	}
	
	
	
}
