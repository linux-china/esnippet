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

package org.mvnsearch.snippet.domain.manager;

import java.util.List;
import java.util.Map;

/**
 * snippet service
 *
 * @author linux_china@hotmail.com
 */
public interface SnippetService {
    /**
     * render template into code
     *
     * @param mnemonic    mnemonic
     * @param packageName package name
     * @param fileName    file name
     * @param author      author name
     * @return rendered code
     */
    public String renderTemplate(String mnemonic, String packageName, String fileName, String author);

    /**
     * find snippet by mnemonic
     *
     * @param mnemonic mnemonic
     * @return snippet object
     */
    public Map<String, String> findSnippetByMnemonic(String mnemonic);

    /**
     * find mnemonic list according to prefix
     *
     * @param prefix prefix
     * @return mnemonic list, max size is 100
     */
    public List<String> findMnemonicList(String prefix);

    /**
     * find mnemonic list according to prefix
     *
     * @param prefix prefix
     * @return mnemonic list, max size is 100
     */
    public List<String> findFileMnemonicList(String prefix);

    /**
     * find mnemonic list according to prefix
     *
     * @param prefix prefix
     * @return mnemonic list, max size is 100
     */
    public List<String> findMnemonicListWithName(String prefix);

    /**
     * @param mnemonicPrefix mnemonic prefix
     * @return snippet list
     */
    public List<Map<String, String>> findSnippetsByMnemonic(String mnemonicPrefix);

    /**
     * @param keyword key word
     * @return snippet list
     */
    public List<Map<String, String>> findSnippetsByWord(String keyword);

    /**
     * find snippet by id
     *
     * @param id snippet id
     * @return snippet object
     */
    public Map<String, String> findMapById(Integer id);

    /**
     * find root category
     *
     * @return category list
     */
    public List<Map<String, String>> findAllCategories();

    /**
     * update snippet
     *
     * @param snippet snippet object
     * @return snippet id
     */
    public Integer updateSnippet(Map<String, String> snippet);

    /**
     * find map in category
     *
     * @param categoryId category id
     * @return snippet service
     */
    public List<Map<String, String>> findSnippetsInCategory(Integer categoryId);

    /**
     * find recent added snippets
     *
     * @return snippet list
     */
    public List<Map<String, String>> findRecentAddedSnippets();

    /**
     * add snippet
     *
     * @param snippetId snippet id
     * @param comment   snippet comment
     */
    public void addSnippetComment(Integer snippetId, Map<String, String> comment);

    /**
     * list snippet comment
     *
     * @param snippetId snippet id
     * @return comment list
     */
    public List<Map<String, String>> findSnippetComments(Integer snippetId);
}
