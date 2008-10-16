package org.mvnsearch.snippet.web.actions.snippet;

import org.apache.commons.lang.StringUtils;
import org.mvnsearch.ridd.struts2.RichDomainQueryAction;
import org.mvnsearch.snippet.domain.Category;
import org.mvnsearch.snippet.domain.Snippet;
import org.mvnsearch.snippet.domain.manager.CategoryManager;
import org.mvnsearch.snippet.domain.manager.SnippetManager;

import java.util.List;

/**
 * snippet domain action
 */
public class SnippetQueryAction extends RichDomainQueryAction<Snippet> {
    private SnippetManager snippetManager;
    private CategoryManager categoryManager;
    private List<Snippet> snippets; //snippet list
    private String q; //query parameter

    /**
     * query parameter
     *
     * @param q query parameter
     */
    public void setQ(String q) {
        this.q = q;
    }

    /**
     * get snippets list
     *
     * @return snippet list
     */
    public List<Snippet> getSnippets() {
        return snippets;
    }

    /**
     * inject snippetManager
     *
     * @param snippetManager SnippetManager bean
     */
    public void setSnippetManager(SnippetManager snippetManager) {
        this.snippetManager = snippetManager;
    }

    /**
     * inject categoryManager bean
     *
     * @param categoryManager categoryManager bean
     */
    public void setCategoryManager(CategoryManager categoryManager) {
        this.categoryManager = categoryManager;
    }

    /**
     * index result
     *
     * @return index result
     */
    @Override
    public String index() {
        entities = snippetManager.getAll();
        return getAlternativeResult(INDEX);
    }

    /**
     * get category menu
     *
     * @return category menu
     */
    public String categoryMenu() {
        return getAlternativeResult("categoryMenu");
    }

    /**
     * get snippet tag cloud
     *
     * @return tag cloud result
     */
    public String tagCloud() {
        return "tag_cloud";
    }

    /**
     * show RSS
     *
     * @return rss result
     */
    public String showRss() {
        this.snippets = snippetManager.findRecentAddedSnippets(20);
        return "show_rss";
    }

    /**
     * query snippets
     *
     * @return list result
     */
    public String query() {
        if (StringUtils.isNotEmpty(request.getParameter("category"))) { //list snippets under category
            int categoryId = Integer.valueOf(request.getParameter("category"));
            if (categoryId == -1) { //recent added snippets
               snippets = snippetManager.findRecentAddedSnippets(40);
            } else {
                Category category = categoryManager.findById(categoryId);
                if (category != null) {
                    this.snippets = category.findSnippets();
                }
            }
        } else if (StringUtils.isNotEmpty(request.getParameter("tag"))) { //query by tag
            snippets = snippetManager.findSnippetsByTag(request.getParameter("tag"));
        } else { //query by word
            if (StringUtils.isEmpty(q)) { //RSS
                snippets = snippetManager.findRecentAddedSnippets(32);
            } else { //query by word
                snippets = snippetManager.findSnippetsByWord(q);
            }
        }
        return getAlternativeResult(INDEX);
    }
}
