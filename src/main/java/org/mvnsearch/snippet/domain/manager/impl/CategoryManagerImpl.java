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

package org.mvnsearch.snippet.domain.manager.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.mvnsearch.ridd.domain.RichDomainManagerSupport;
import org.mvnsearch.snippet.domain.Category;
import org.mvnsearch.snippet.domain.manager.CategoryManager;
import org.springmodules.cache.annotations.Cacheable;

import java.util.List;

/**
 * category manager implementation
 */
public abstract class CategoryManagerImpl extends RichDomainManagerSupport<Category> implements CategoryManager {

    /**
     * get the entity class
     *
     * @return entity class
     */
    public Class getEntityClass() {
        return Category.class;
    }

    /**
     * find root categories
     *
     * @return category list
     */
    @Cacheable(modelId = "root_categories")
    public List<Category> findRootCategories() {
        DetachedCriteria criteria = DetachedCriteria.forClass(Category.class);
        criteria.add(Restrictions.isNull("parentId"));
        criteria.addOrder(Order.asc("name"));
        return getHibernateTemplate().findByCriteria(criteria);
    }
}
