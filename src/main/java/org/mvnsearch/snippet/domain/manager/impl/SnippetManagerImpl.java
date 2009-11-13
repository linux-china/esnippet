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
import org.mvnsearch.snippet.domain.Snippet;
import org.mvnsearch.snippet.domain.extra.Comment;
import org.mvnsearch.snippet.domain.manager.SnippetManager;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springmodules.cache.annotations.Cacheable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * snippet manager implementation
 */
@SuppressWarnings({"unchecked"})
public abstract class SnippetManagerImpl extends RichDomainManagerSupport<Snippet> implements SnippetManager {
    private SimpleJdbcTemplate jdbcTemplate;

    /**
     * inject jdbc template
     *
     * @param jdbcTemplate jdbc template
     */
    public void setJdbcTemplate(SimpleJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * get the entity class
     *
     * @return entity class
     */
    public Class getEntityClass() {
        return Snippet.class;
    }

    /**
     * get recent added snippets
     *
     * @param maxNum max number
     * @return snippet list
     */
    @SuppressWarnings({"unchecked"})
    public List<Snippet> findRecentAddedSnippets(int maxNum) {
        DetachedCriteria criteria = DetachedCriteria.forClass(getEntityClass());
        criteria.addOrder(Order.desc("modifiedAt"));
        return getHibernateTemplate().findByCriteria(criteria, 0, maxNum);
    }

    /**
     * find snippets according to tag
     *
     * @param tag tag
     * @return snippet list
     */
    public List<Snippet> findSnippetsByTag(String tag) {
        DetachedCriteria criteria = DetachedCriteria.forClass(getEntityClass());
        criteria.add(Restrictions.like("tag", "%" + tag + "%"));
        criteria.addOrder(Order.desc("level"));
        return getHibernateTemplate().findByCriteria(criteria);
    }

    /**
     * find snippet by mnemonic
     *
     * @param mnemonic mnemonic
     * @return snippet object
     */
    public Snippet findSnippetByMnemonic(String mnemonic) {
        DetachedCriteria criteria = DetachedCriteria.forClass(getEntityClass());
        criteria.add(Restrictions.eq("mnemonic", mnemonic));
        List snippets = getHibernateTemplate().findByCriteria(criteria);
        if (snippets.size() > 0) {
            return (Snippet) snippets.get(0);
        }
        return null;
    }

    /**
     * get snippets count
     *
     * @return snippets count
     */
    public int getSnippetsCount() {
        return jdbcTemplate.queryForInt("select count(*) from snippets");
    }

    /**
     * find snippets according to word
     *
     * @param word word
     * @return snippet list
     */
    public List<Snippet> findSnippetsByWord(String word) {
        DetachedCriteria criteria = DetachedCriteria.forClass(getEntityClass());
        criteria.add(Restrictions.or(Restrictions.like("name", "%" + word + "%"), Restrictions.like("keywords", "%" + word + "%")));
        criteria.addOrder(Order.desc("level"));
        return getHibernateTemplate().findByCriteria(criteria);
    }

    /**
     * get all language for snippets
     *
     * @return snippet language
     */
    @SuppressWarnings({"ConstantConditions"})
    @Cacheable(modelId = "snippet_languages")
    public Map<Integer, String> getAllSnippetLanguage() {
        return getAllMetaData("language");
    }

    /**
     * get all icon for snippets
     *
     * @return snippet icon
     */
    @Cacheable(modelId = "snippet_icons")
    public Map<Integer, String> getAllSnippetIcon() {
        return getAllMetaData("icon");
    }

    /**
     * find recent added comment
     *
     * @param count count
     * @return comment list
     */
    public List<Comment> findRecentAddedComments(int count) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Comment.class);
        criteria.addOrder(Order.desc("id"));
        return getHibernateTemplate().findByCriteria(criteria, 0, count);
    }

    /**
     * get meta data
     *
     * @param type data type
     * @return meta data list
     */
    @SuppressWarnings({"ConstantConditions"})
    private Map<Integer, String> getAllMetaData(String type) {
        final Map<Integer, String> languages = new HashMap<Integer, String>();
        List<Map<String, Object>> rows = jdbcTemplate.queryForList("select id, value from meta_datas where type=?", type);
        for (Map<String, Object> row : rows) {
            languages.put((Integer) row.get("id"), (String) row.get("value"));
        }
        return languages;
    }

}
