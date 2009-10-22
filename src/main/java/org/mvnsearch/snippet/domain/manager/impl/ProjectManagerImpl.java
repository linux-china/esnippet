package org.mvnsearch.snippet.domain.manager.impl;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.mvnsearch.snippet.domain.Project;
import org.mvnsearch.snippet.domain.manager.ProjectManager;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.util.List;

/**
 * project manager implementation
 *
 * @author linux_china@hotmail.com
 */
@SuppressWarnings({"unchecked"})
public class ProjectManagerImpl extends HibernateDaoSupport implements ProjectManager {

    /**
     * get project by id
     *
     * @param id project id
     * @return Project object
     */
    public Project findProject(Integer id) {
        return (Project) getHibernateTemplate().get(Project.class, id);
    }

    /**
     * get all project order by name
     *
     * @return project list
     */
    public List<Project> findAllProjects() {
        DetachedCriteria criteria = DetachedCriteria.forClass(Project.class);
        criteria.addOrder(Order.asc("name"));
        return getHibernateTemplate().findByCriteria(criteria);
    }
}
