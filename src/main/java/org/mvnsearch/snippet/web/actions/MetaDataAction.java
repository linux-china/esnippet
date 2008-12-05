/*
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
