package org.mvnsearch.snippet.web.actions.project;

import org.mvnsearch.ridd.struts2.RichDomainQueryAction;
import org.mvnsearch.snippet.domain.Project;
import org.mvnsearch.snippet.domain.manager.ProjectManager;

import java.util.List;

/**
 * project query action
 *
 * @author linux_china@hotmail.com
 */
public class ProjectQueryAction extends RichDomainQueryAction<Project> {
    /**
     * project list
     */
    private List<Project> projects;
    /**
     * project manager
     */
    private ProjectManager projectManager;

    /**
     * inject project manager
     *
     * @param projectManager project manager
     */
    public void setProjectManager(ProjectManager projectManager) {
        this.projectManager = projectManager;
    }

    /**
     * get project list
     *
     * @return project list
     */
    public List<Project> getProjects() {
        return projects;
    }

    /**
     * index result
     *
     * @return result
     */
    public String index() {
        this.projects = projectManager.findAllProjects();
        return getAlternativeResult("index");
    }
}
