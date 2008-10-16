package org.mvnsearch.snippet.web.actions.category;

import org.mvnsearch.snippet.domain.Category;
import org.mvnsearch.snippet.domain.Snippet;
import org.mvnsearch.snippet.domain.manager.CategoryManager;
import org.mvnsearch.ridd.struts2.RichDomainRestAction;

import java.util.List;

/**
 * category domain action
 */
public class CategoryAction extends RichDomainRestAction<Category> {
    private static String SNIPPET_LIST = "snippet_list";
    private Integer id;
    private Category category;
    private List<Snippet> snippets;
    private CategoryManager categoryManager;

    /**
     * inject categoryManager
     *
     * @param categoryManager CategoryManager bean
     */
    public void setCategoryManager(CategoryManager categoryManager) {
        this.categoryManager = categoryManager;
    }

    /**
     * get all snippets in category
     *
     * @return snippets list
     */
    public List<Snippet> getSnippets() {
        return snippets;
    }

    /**
     * get category id
     *
     * @return category id
     */
    public Integer getId() {
        return id;
    }

    /**
     * set id for domain
     *
     * @param id category id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * get domain object
     *
     * @return domain object
     */
    public Category getCategory() {
        return this.category;
    }

    /**
     * model driven object
     *
     * @return entity object
     */
    public Category getModel() {
        return category;
    }

    /**
     * prepare domain object.
     *
     * @throws Exception exception
     */
    public void prepare() throws Exception {
        if (id != null) {
            category = categoryManager.findById(id);
        }
    }

    /**
     * destroy operation
     *
     * @return list page with redirect
     */
    @Override
    public String destroy() {
        category.destroy();
        return getAlternativeResult(POST_DELETE);
    }

    /**
     * prepare operation for create, such domain construction
     */
    public void prepareCreate() {
        category = categoryManager.construct();
    }

    /**
     * create operation
     *
     * @return detail page with redirect
     */
    @Override
    public String create() {
        category.save();
        return getAlternativeResult(POST_CREATE);
    }

    /**
     * update operation
     *
     * @return detail page with redirect
     */
    @Override
    public String update() {
        category.save();
        return getAlternativeResult(POST_UPDATE);
    }

    /**
     * prepare operation for save, such as domain construction
     */
    public void prepareSave() {
        if (id == null) {
            prepareCreate();
        }
    }

    /**
     * save operation, and it will be routed to create or update
     *
     * @return result from create or update
     */
    public String save() {
        return id == null ? create() : update();
    }

    /**
     * find all snippets in category
     *
     * @return snippet list result
     */
    public String findSnippets() {
        snippets = category.findSnippets();
        return getAlternativeResult(SNIPPET_LIST);
    }
}
