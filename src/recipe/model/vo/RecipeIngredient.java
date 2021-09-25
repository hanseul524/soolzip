package recipe.model.vo;

public class RecipeIngredient {
	private int ingredientNo;
	private int recipeNo;
	private String ingredientName;
	private String ingredientGram;
	
	public RecipeIngredient(int ingredientNo, int recipeNo, String ingredientName, String ingredientGram) {
		super();
		this.ingredientNo = ingredientNo;
		this.recipeNo = recipeNo;
		this.ingredientName = ingredientName;
		this.ingredientGram = ingredientGram;
	}

	public RecipeIngredient() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getIngredientNo() {
		return ingredientNo;
	}

	public void setIngredientNo(int ingredientNo) {
		this.ingredientNo = ingredientNo;
	}

	public int getRecipeNo() {
		return recipeNo;
	}

	public void setRecipeNo(int recipeNo) {
		this.recipeNo = recipeNo;
	}

	public String getIngredientName() {
		return ingredientName;
	}

	public void setIngredientName(String ingredientName) {
		this.ingredientName = ingredientName;
	}

	public String getIngredientGram() {
		return ingredientGram;
	}

	public void setIngredientGram(String ingredientGram) {
		this.ingredientGram = ingredientGram;
	}

	@Override
	public String toString() {
		return "RecipeIngredient [ingredientNo=" + ingredientNo + ", recipeNo=" + recipeNo + ", ingredientName="
				+ ingredientName + ", ingredientGram=" + ingredientGram + "]";
	}
	
	
}
