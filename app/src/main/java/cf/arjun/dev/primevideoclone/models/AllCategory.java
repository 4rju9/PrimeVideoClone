package cf.arjun.dev.primevideoclone.models;

public class AllCategory {

    String categoryTitle;
    Integer categoryId;

    public AllCategory(Integer categoryId, String categoryTitle) {
        this.categoryTitle = categoryTitle;
        this.categoryId = categoryId;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

}
