package org.mvnsearch.snippet.web.actions.category;

import org.mvnsearch.ridd.struts2.RichDomainQueryAction;
import org.mvnsearch.snippet.domain.Category;
import org.mvnsearch.snippet.domain.manager.CategoryManager;

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
}
