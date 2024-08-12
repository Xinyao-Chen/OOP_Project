package reminder_Manager;

import java.util.ArrayList;
import java.util.List;

import reminder_Model.Category;


public class CategoryManager {
    private List<Category> categories;

    public CategoryManager() {
        categories = new ArrayList<>();
    }

    public void addCategory(Category category) {
        categories.add(category);
    }

    public void deleteCategory(Category category) {
        categories.remove(category);
    }

    public List<Category> getAllCategories() {
        return new ArrayList<>(categories);
    }
}
