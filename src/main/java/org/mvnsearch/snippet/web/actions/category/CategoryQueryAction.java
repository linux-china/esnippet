package org.mvnsearch.snippet.web.actions.category;

import org.mvnsearch.ridd.struts2.RichDomainQueryAction;
import org.mvnsearch.snippet.domain.Category;
import org.mvnsearch.snippet.domain.manager.CategoryManager;

import java.util.List;
import java.util.ArrayList;

/**
 * category domain query action
 */
public class CategoryQueryAction extends RichDomainQueryAction<Category> {
    private CategoryManager categoryManager;

    /**
     * inject categoryManager
     *
     * @param categoryManager CategoryManager bean
     */
    public void setCategoryManager(CategoryManager categoryManager) {
        this.categoryManager = categoryManager;
    }

    /**
     * list all root directories
     *
     * @return index result
     */
    @Override
    public String index() {
        entities = categoryManager.findRootCategories();
        return getAlternativeResult(INDEX);
    }

    /**
     * list all root directories
     *
     * @return index result
     */
    public String showAll() {
        entities = categoryManager.getAll();
        return getAlternativeResult(INDEX);
    }

    /**
     * category store
     *
     * @return store result
     */
    public String categoryStore() {
        List<Category> flatCategories = new ArrayList<Category>();
        List<Category> rootCategories = categoryManager.findRootCategories();
        for (Category rootCategory : rootCategories) {
            if (rootCategory.getId() > 0) {
                flatCategories.add(rootCategory);
                for (Category subCategory : rootCategory.getChildrenCategories()) {
                    flatCategories.add(subCategory);
                    for (Category leaf : subCategory.getChildrenCategories()) {
                        flatCategories.add(leaf);
                    }
                }
            }
        }
        this.entities = flatCategories;
        return getAlternativeResult("category_store");
    }
}
