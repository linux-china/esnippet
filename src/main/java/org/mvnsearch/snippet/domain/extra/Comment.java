package org.mvnsearch.snippet.domain.extra;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;

/**
 * snippet comment
 */
@Entity
@Table(name = "snippet_comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "snippet_id")
    private Integer snippetId;
    @Column(name = "commentator")
    private String commentator;
    @Column(name = "commentator_email")
    private String commentatorEmail;
    @Column(name = "subject")
    private String subject;
    @Column(name = "content")
    private String content;
    @Column(name = "created_at")
    @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    private DateTime createdAt;

    /**
     * get comment id
     *
     * @return comment id
     */
    public Integer getId() {
        return id;
    }

    /**
     * set comment id
     *
     * @param id comment id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * get snippet id
     *
     * @return snippet id
     */
    public Integer getSnippetId() {
        return snippetId;
    }

    /**
     * set snippet id
     *
     * @param snippetId snippet id
     */
    public void setSnippetId(Integer snippetId) {
        this.snippetId = snippetId;
    }

    /**
     * get commentator
     *
     * @return commentator
     */
    public String getCommentator() {
        return commentator;
    }

    /**
     * set commentator
     *
     * @param commentator commentator
     */
    public void setCommentator(String commentator) {
        this.commentator = commentator;
    }

    /**
     * get commentator's email
     *
     * @return commentator's email
     */
    public String getCommentatorEmail() {
        return commentatorEmail;
    }

    /**
     * set commentator's email
     *
     * @param commentatorEmail commentator's email
     */
    public void setCommentatorEmail(String commentatorEmail) {
        this.commentatorEmail = commentatorEmail;
    }

    /**
     * get subject
     *
     * @return subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * set subject
     *
     * @param subject subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * get content
     *
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * set content
     *
     * @param content content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * get created timestamp
     *
     * @return created timestamp
     */
    public DateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * set created timestamp
     *
     * @param createdAt created timestamp
     */
    public void setCreatedAt(DateTime createdAt) {
        this.createdAt = createdAt;
    }
}
