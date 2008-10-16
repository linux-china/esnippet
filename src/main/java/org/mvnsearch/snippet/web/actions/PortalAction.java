package org.mvnsearch.snippet.web.actions;

import org.mvnsearch.ridd.struts2.BaseAction;
import org.mvnsearch.snippet.domain.manager.SnippetManager;

/**
 * portal action for project
 */
public class PortalAction extends BaseAction {
    private SnippetManager snippetManager;

    /**
     * inject SnippetManager bean
     *
     * @param snippetManager SnippetManager bean
     */
    public void setSnippetManager(SnippetManager snippetManager) {
        this.snippetManager = snippetManager;
    }

    /**
     * default logic for index
     */
    @Override
    public String execute() throws Exception {
        request.setAttribute("snippet_count",snippetManager.getSnippetsCount());
        return "portal";
    }
}
