package org.mvnsearch.snippet.domain.manager;

import org.mvnsearch.ridd.domain.RichDomainManager;
import org.mvnsearch.snippet.domain.Category;

import java.util.List;

/**
 * Category domain manager interface
 */
public interface CategoryManager extends RichDomainManager<Category> {
    /**
     * find root categories
     *
     * @return category list
     */
    public List<Category> findRootCategories();
}
