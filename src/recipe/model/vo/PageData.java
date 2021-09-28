package recipe.model.vo;

import java.util.List;

public class PageData {
	
	private List<Recipe> recipeList;
	private String pageNavi;
	
	
	public PageData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public List<Recipe> getRecipeList() {
		return recipeList;
	}
	public void setRecipeList(List<Recipe> recipeList) {
		this.recipeList = recipeList;
	}
	public String getPageNavi() {
		return pageNavi;
	}
	public void setPageNavi(String pageNavi) {
		this.pageNavi = pageNavi;
	}
	@Override
	public String toString() {
		return "PageData [recipeList=" + recipeList + ", pageNavi=" + pageNavi + "]";
	}
	
	
}
