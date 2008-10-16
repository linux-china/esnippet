package org.mvnsearch.snippet.domain;

import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringBean;
import org.unitils.reflectionassert.ReflectionAssert;
import org.mvnsearch.snippet.AppBaseTest;
import org.mvnsearch.snippet.domain.manager.impl.SnippetManagerImpl;

/**
 * Snippet domain logic test case
 */
@DataSet("snippet-dataset.xml")
public class SnippetTest extends AppBaseTest {
    private Integer id = 1; 
    private Snippet snippet; //domain object
    @SpringBean("snippetManager") private SnippetManagerImpl snippetManager;

     /**
     * data filled
     *
     * @throws Exception exception
     */
    protected void setUp() throws Exception {
        super.setUp();
        snippet = (Snippet) snippetManager.findById(id);
    }

    /**
     * test save
     *
     * @throws Exception exception
     */
    public void testSave() throws Exception {
        //todo update property
        snippet.save();
        snippet = snippetManager.findById(id);
        //assertEquals("Failed to save snippet:"+id,);
    }

    /**
     * test save
     *
     * @throws Exception exception
     */
    public void testDestroy() throws Exception {
         Integer tempId = null; //todo update id
         Snippet obj= snippetManager.findById(tempId);
         obj.destroy();
         obj= snippetManager.findById(tempId);
         assertNull("Failed to destroy snippet:"+tempId,obj);
    }
}