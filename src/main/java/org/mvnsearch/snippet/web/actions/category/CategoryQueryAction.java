/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
