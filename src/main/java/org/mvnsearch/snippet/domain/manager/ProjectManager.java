package org.mvnsearch.snippet.domain.manager;

import org.mvnsearch.snippet.domain.Project;

import java.util.List;

/**
 * project manager
 *
 * @author linux_china@hotmail.com
 */
public interface ProjectManager {
    /**
     * get project by id
     *
     * @param id project id
     * @return Project object
     */
    public Project findProject(Integer id);

    /**
     * get all project order by name
     *
     * @return project list
     */
    public List<Project> findAllProjects();


}
