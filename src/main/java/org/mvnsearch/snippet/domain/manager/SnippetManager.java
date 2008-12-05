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

import org.mvnsearch.ridd.domain.RichDomainManager;
import org.mvnsearch.snippet.domain.Snippet;
import org.mvnsearch.snippet.domain.extra.Comment;

import java.util.List;
import java.util.Map;

/**
 * Snippet domain manager interface
 */
public interface SnippetManager extends RichDomainManager<Snippet> {
    /**
     * get recent added snippets
     *
     * @param maxNum max number
     * @return snippet list
     */
    public List<Snippet> findRecentAddedSnippets(int maxNum);

    /**
     * find snippets according to tag
     *
     * @param tag tag
     * @return snippet list
     */
    public List<Snippet> findSnippetsByTag(String tag);

    /**
     * find snippet by mnemonic
     *
     * @param mnemonic mnemonic
     * @return snippet object
     */
    public Snippet findSnippetByMnemonic(String mnemonic);

    /**
     * get snippets count
     *
     * @return snippets count
     */
    public int getSnippetsCount();

    /**
     * find snippets according to word
     *
     * @param word word
     * @return snippet list
     */
    public List<Snippet> findSnippetsByWord(String word);

    /**
     * get all language for snippets
     *
     * @return snippet language
     */
    public Map<Integer, String> getAllSnippetLanguage();

    /**
     * get all icon for snippets
     *
     * @return snippet icon
     */
    public Map<Integer, String> getAllSnippetIcon();

    /**
     * find recent added comment
     *
     * @param count count
     * @return comment list
     */
    public List<Comment> findRecentAddedComments(int count);
}
