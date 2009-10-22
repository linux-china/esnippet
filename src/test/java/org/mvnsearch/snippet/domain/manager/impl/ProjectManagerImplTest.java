package org.mvnsearch.snippet.domain.manager.impl;

import junit.framework.TestCase;
import org.mvnsearch.snippet.AppBaseTest;
import org.mvnsearch.snippet.domain.Project;
import org.mvnsearch.snippet.domain.manager.CategoryManager;
import org.mvnsearch.snippet.domain.manager.ProjectManager;
import org.unitils.spring.annotation.SpringBean;

import java.util.List;

/**
 * project manager implementation test case
 *
 * @author linux_china@hotmail.com
 */
public class ProjectManagerImplTest extends AppBaseTest {
    @SpringBean("projectManager")
    private ProjectManagerImpl projectManager;

    /**
     * test to find project
     *
     * @throws Exception exception
     */
    public void testFindProject() throws Exception {
        Project project = projectManager.findProject(1);
        System.out.println(project.getName());
    }

    /**
     * test to find all projects
     *
     * @throws Exception exception
     */
    public void testFindAllProjects() throws Exception {
        List<Project> projects = projectManager.findAllProjects();
        assertTrue("Load project failed", !projects.isEmpty());
    }
}
