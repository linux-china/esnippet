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
