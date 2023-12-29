package cf.arjun.dev.primevideoclone.models;

import java.util.List;

public class AllCategory {

    Integer categoryId;
    String categoryTitle;
    private List<Movies> moviesList;

    public AllCategory(Integer categoryId, String categoryTitle, List<Movies> moviesList) {
        this.categoryId = categoryId;
        this.categoryTitle = categoryTitle;
        this.moviesList = moviesList;
    }

    public List<Movies> getMoviesList() {
        return moviesList;
    }

    public void setMoviesList(List<Movies> moviesList) {
        this.moviesList = moviesList;
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
