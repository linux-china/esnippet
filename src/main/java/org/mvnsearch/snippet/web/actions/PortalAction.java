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
