package org.mvnsearch.snippet.domain.manager.impl;

import org.mvnsearch.snippet.AppBaseTest;
import org.mvnsearch.snippet.domain.Snippet;
import org.mvnsearch.snippet.domain.manager.SnippetManager;
import org.unitils.spring.annotation.SpringBean;

import java.util.List;
import java.util.Map;

/**
 * Snippet domain manager logic test case
 */
//@DataSet("../../snippet-dataset.xml")
public class SnippetManagerImplTest extends AppBaseTest {
    private Integer id = 1;
    @SpringBean("snippetManager")
    private SnippetManager snippetManagerImpl;

    /**
     * domain create test
     *
     * @throws Exception exception
     */
    public void testCreate() throws Exception {
        Snippet entity = snippetManagerImpl.findById(id);
        entity.setId(2);
        entity.save();
        assertNotNull("Failed to create snippet:" + id, entity.getId());
    }

    /**
     * test method for update
     *
     * @throws Exception exception
     */
    public void testUpdate() throws Exception {
        Snippet entity = snippetManagerImpl.findById(id);
        entity.setName("jacky");
        entity.save();
    }

    /**
     * findById test
     *
     * @throws Exception exception
     * @see SnippetManagerImpl#findById(java.io.Serializable)
     */
    public void testFindById() throws Exception {
        Snippet domain = snippetManagerImpl.findById(id);
        assertNotNull("Failed to find snippet by id:" + id, domain);
    }

    /**
     * test method for template render
     *
     * @throws Exception exception
     */
    public void testRenderTemplate() throws Exception {

    }

    /**
     * test to get popular tags
     */
    public void testGetPopularTags() {
        Map<String, Integer> tags = snippetManagerImpl.getPopularTags(2);
        for (Map.Entry<String, Integer> entry : tags.entrySet()) {
            System.out.println(entry.getKey());
        }
    }

    /**
     * test to get popular contributors
     */
    public void testGetPopularContributors() {
        List<Map<String, Object>> statics = snippetManagerImpl.getPopularContributors(5);
        for (Map<String, Object> info : statics) {
            System.out.println(info.get("contributor"));
        }
        
    }
}