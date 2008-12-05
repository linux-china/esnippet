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

package org.mvnsearch.snippet.domain;

import org.hibernate.annotations.Type;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.joda.time.DateTime;
import org.mvnsearch.ridd.domain.RichDomainSupport;

import javax.persistence.*;
import java.util.List;

/**
 * domain interface for Category
 */
@Entity
@Table(name = "snippet_category")
public class Category extends RichDomainSupport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "parent_id")
    private Integer parentId;
    @Column(name = "name")
    private String name;
    @Column(name = "icon")
    private String icon;
    @Column(name = "created_at")
    @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    private DateTime createdAt;

    /**
     * get category id
     *
     * @return category id
     */
    public Integer getId() {
        return id;
    }

    /**
     * set category id
     *
     * @param id category id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * get parent category id
     *
     * @return parent category id
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * set parent category id
     *
     * @param parentId parent category id
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * get category name
     *
     * @return category name
     */
    public String getName() {
        return name;
    }

    /**
     * set category name
     *
     * @param name category name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get icon for category
     *
     * @return icon name
     */
    public String getIcon() {
        return icon;
    }

    /**
     * set icon for category
     *
     * @param icon icon name
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * get created timestamp
     *
     * @return created timestamp
     */
    public DateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * set created timestamp
     *
     * @param createdAt created timestamp
     */
    public void setCreatedAt(DateTime createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * get all category in current category, recursive not support
     *
     * @return snippet list
     */
    @SuppressWarnings({"unchecked"})
    public List<Snippet> findSnippets() {
        DetachedCriteria criteria = DetachedCriteria.forClass(Snippet.class);
        criteria.add(Restrictions.eq("categoryId", id));
        criteria.addOrder(Order.desc("createdAt"));
        return getHibernateTemplate().findByCriteria(criteria);
    }

    /**
     * get children categories
     *
     * @return category list
     */
    @SuppressWarnings({"unchecked"})
    public List<Category> getChildrenCategories() {
        DetachedCriteria criteria = DetachedCriteria.forClass(Category.class);
        criteria.add(Restrictions.eq("parentId", id));
        criteria.addOrder(Order.asc("name"));
        return getHibernateTemplate().findByCriteria(criteria);
    }

}