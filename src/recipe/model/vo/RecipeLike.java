package recipe.model.vo;

public class RecipeLike {
	private int likeNo;
	private int recipeNo;
	private String userId;

	
	public RecipeLike() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RecipeLike(int likeNo, int recipeNo, String userId) {
		super();
		this.likeNo = likeNo;
		this.recipeNo = recipeNo;
		this.userId = userId;
	}
	
	public int getLikeNo() {
		return likeNo;
	}
	public void setLikeNo(int likeNo) {
		this.likeNo = likeNo;
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
		return "RecipeLike [likeNo=" + likeNo + ", recipeNo=" + recipeNo + ", userId=" + userId + "]";
	}
	
	
	
}
