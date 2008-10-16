package org.mvnsearch.snippet.domain.manager.impl;

import org.unitils.dbunit.annotation.DataSet;
import org.unitils.spring.annotation.SpringBean;
import org.mvnsearch.snippet.AppBaseTest;
import org.mvnsearch.snippet.domain.Category;
import org.mvnsearch.snippet.domain.manager.CategoryManager;

import java.util.List;

/**
 * Category domain manager logic test case
 */
//@DataSet("../../category-dataset.xml")
public class CategoryManagerImplTest extends AppBaseTest {
    private Integer id = 1;
    @SpringBean("categoryManager")
    private CategoryManager categoryManagerImpl;

    /**
     * domain create test
     *
     * @throws Exception exception
     */
    public void testCreate() throws Exception {
        Category entity = categoryManagerImpl.findById(id);
        entity.setId(null);
        entity.setName("new name");
        entity.save();
        assertNotNull("Failed to create user:" + id, entity.getId());
    }

    /**
     * findById test
     *
     * @throws Exception exception
     * @see CategoryManagerImpl#findById(java.io.Serializable)
     */
    public void testFindById() throws Exception {
        Category domain = categoryManagerImpl.findById(id);
        assertNotNull("Failed to find category by id:" + id, domain);
    }

    /**
     * test case for find root categories
     *
     * @throws Exception exception
     */
    public void testFindRootCategories() throws Exception {
        List<Category> rootCategories = categoryManagerImpl.findRootCategories();
        for (Category rootCategory : rootCategories) {
            List<Category> categories = rootCategory.getChildrenCategories();
            System.out.println(categories.size());
        }
        rootCategories = categoryManagerImpl.findRootCategories();
        rootCategories = categoryManagerImpl.findRootCategories();
        rootCategories = categoryManagerImpl.findRootCategories();
        rootCategories = categoryManagerImpl.findRootCategories();
    }
}