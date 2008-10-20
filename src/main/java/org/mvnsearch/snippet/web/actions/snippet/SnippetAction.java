package org.mvnsearch.snippet.web.actions.snippet;

import org.joda.time.DateTime;
import org.mvnsearch.ridd.struts2.RichDomainRestAction;
import org.mvnsearch.snippet.domain.Snippet;
import org.mvnsearch.snippet.domain.extra.Comment;
import org.mvnsearch.snippet.domain.manager.SnippetManager;

import java.util.List;
import java.util.Map;

/**
 * snippet domain action
 */
public class SnippetAction extends RichDomainRestAction<Snippet> {
    private static String COMMENT_LIST = "comment_list";
    private static String COMMENT_ADDED = "comment_added";
    private Integer id;
    private Snippet snippet;
    private SnippetManager snippetManager;
    private List<Comment> comments; //snippet comments
    private Map<Integer, String> languages; //snippet language
    private Map<Integer, String> icons; //snippet icons

    /**
     * inject snippetManager
     *
     * @param snippetManager SnippetManager bean
     */
    public void setSnippetManager(SnippetManager snippetManager) {
        this.snippetManager = snippetManager;
        this.languages = snippetManager.getAllSnippetLanguage();
        this.icons = snippetManager.getAllSnippetIcon();
    }

    /**
     * get snippet icons
     *
     * @return snippet icons
     */
    public Map<Integer, String> getIcons() {
        return icons;
    }

    /**
     * get language map
     *
     * @return language map
     */
    public Map<Integer, String> getLanguages() {
        return languages;
    }

    /**
     * set id for snippet
     *
     * @param id snippet id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * get domain object
     *
     * @return domain object
     */
    public Snippet getSnippet() {
        return this.snippet;
    }

    /**
     * get book's comments
     *
     * @return comment list
     */
    public List<Comment> getComments() {
        return comments;
    }

    /**
     * model driven object
     *
     * @return entity object
     */
    public Snippet getModel() {
        return snippet;
    }

    /**
     * prepare domain object.
     *
     * @throws Exception exception
     */
    public void prepare() throws Exception {
        if (id != null) {
            snippet = snippetManager.findById(id);
        }
    }

    /**
     * destroy operation
     *
     * @return list page with redirect
     */
    @Override
    public String destroy() {
        snippet.destroy();
        return getAlternativeResult(POST_DELETE);
    }

    /**
     * prepare operation for create, such domain construction
     */
    public void prepareCreate() {
        snippet = snippetManager.construct();
    }

    /**
     * create operation
     *
     * @return detail page with redirect
     */
    @Override
    public String create() {
        snippet.save();
        return getAlternativeResult(POST_CREATE);
    }

    /**
     * update operation
     *
     * @return detail page with redirect
     */
    @Override
    public String update() {
        snippet.save();
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
     * show part of snippet
     *
     * @return part result
     */
    public String showPart() {
        String part = request.getParameter("part");
        String result = "plain_text";
        String content = "";
        if ("code".equals(part)) {
            content = snippet.getCode();
            result = "code_part";
        } else if ("example".equals(part)) {
            content = snippet.getExample();
        }
        request.setAttribute("content", content);
        return result;
    }

    /**
     * render code
     *
     * @return part result
     */
    public String render() {
        return "message";
    }

    /**
     * show snippet comments
     *
     * @return comment list result
     */
    public String showComments() {
        if (snippet != null) {
            comments = snippet.findComments();
        } else {
            comments = snippetManager.findRecentAddedComments(20);
        }
        return getAlternativeResult(COMMENT_LIST);
    }

    /**
     * add comment for snippet
     *
     * @return comment
     */
    public String addComment() {
        Comment comment = new Comment();
        comment.setCommentator(request.getParameter("commentator"));
        comment.setCommentatorEmail(request.getParameter("commentatorEmail"));
        comment.setSubject(request.getParameter("subject"));
        comment.setContent(request.getParameter("content"));
        comment.setCreatedAt(new DateTime());
        snippet.addComment(comment);
        request.setAttribute("message", "Comment saved successfully!");
        return getAlternativeResult(COMMENT_ADDED);
    }
}
