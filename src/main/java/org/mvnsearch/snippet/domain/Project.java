package org.mvnsearch.snippet.domain;

import javax.persistence.*;

/**
 * Project Domain
 *
 * @author linux_china@hotmail.com
 */
@Entity
@Table(name = "snippet_projects")
public class Project {
    /**
     * ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * name
     */
    @Column(name = "name")
    private String name;
    /**
     * short name
     */
    @Column(name = "short_name")
    private String shortName;
    /**
     * description
     */
    @Column(name = "description")
    private String description;

    /**
     * get project id
     *
     * @return project id
     */
    public Integer getId() {
        return id;
    }

    /**
     * set project id
     *
     * @param id project id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * get project name
     *
     * @return project name
     */
    public String getName() {
        return name;
    }

    /**
     * set project name
     *
     * @param name project name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * get project short name
     *
     * @return short name
     */
    public String getShortName() {
        return shortName;
    }

    /**
     * set project short name
     *
     * @param shortName short name
     */
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    /**
     * get project description
     *
     * @return project description
     */
    public String getDescription() {
        return description;
    }

    /**
     * set project description
     *
     * @param description project description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
