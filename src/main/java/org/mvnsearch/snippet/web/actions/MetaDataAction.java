package org.mvnsearch.snippet.web.actions;

import com.opensymphony.xwork2.ActionSupport;

import java.util.Map;
import java.util.Set;

import org.mvnsearch.snippet.domain.manager.SnippetManager;
import org.mvnsearch.ridd.struts2.BaseAction;

/**
 * meta data action
 */
public class MetaDataAction extends BaseAction {
    private Map<Integer, String> datas;
    SnippetManager snippetManager;

    /**
     * inject snippetManager bean
     *
     * @param snippetManager snippet manager bean
     */
    public void setSnippetManager(SnippetManager snippetManager) {
        this.snippetManager = snippetManager;
    }

    public Set<Map.Entry<Integer, String>> getDataSet() {
        return datas.entrySet();
    }

    /**
     * data map
     *
     * @return data map
     */
    public Map<Integer, String> getDatas() {
        return datas;
    }

    @Override
    public String execute() throws Exception {
        String type = request.getParameter("type");
        if (type.equals("language")) {
            datas = snippetManager.getAllSnippetLanguage();
        } else if (type.equals("icon")) {
            datas = snippetManager.getAllSnippetIcon();
        }
        return "data_list_json";
    }
}
