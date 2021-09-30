package recipe.model.vo;

public class RecipeScrap {
	private int scrapNo;
	private int recipeNo;
	private String userId;
	
	public RecipeScrap() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public RecipeScrap(int scrapNo, int recipeNo, String userId) {
		super();
		this.scrapNo = scrapNo;
		this.recipeNo = recipeNo;
		this.userId = userId;
	}

	public int getScrapNo() {
		return scrapNo;
	}

	public void setScrapNo(int scrapNo) {
		this.scrapNo = scrapNo;
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


	@Override
	public String toString() {
		return "RecipeScrap [scrapNo=" + scrapNo + ", recipeNo=" + recipeNo + ", userId=" + userId + "]";
	}
	
	
}
