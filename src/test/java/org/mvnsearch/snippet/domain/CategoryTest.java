package org.mvnsearch.snippet.domain;

import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringBean;
import org.unitils.reflectionassert.ReflectionAssert;
import org.mvnsearch.snippet.AppBaseTest;
import org.mvnsearch.snippet.domain.manager.impl.CategoryManagerImpl;

/**
 * Category domain logic test case
 */
@DataSet("category-dataset.xml")
public class CategoryTest extends AppBaseTest {
    private Integer id = null; //todo: id description here
    private Category category; //domain object
    @SpringBean("categoryManager") private CategoryManagerImpl categoryManager;

     /**
     * data filled
     *
     * @throws Exception exception
     */
    protected void setUp() throws Exception {
        super.setUp();
        category = categoryManager.findById(id);
    }

    /**
     * test save
     *
     * @throws Exception exception
     */
    public void testSave() throws Exception {
        //todo update property
        category.save();
        category = categoryManager.findById(id);
        //assertEquals("Failed to save category:"+id,);
    }

    /**
     * test save
     *
     * @throws Exception exception
     */
    public void testDestroy() throws Exception {
         Integer tempId = null; //todo update id
         Category obj= categoryManager.findById(tempId);
         obj.destroy();
         obj= categoryManager.findById(tempId);
         assertNull("Failed to destroy category:"+tempId,obj);
    }
}