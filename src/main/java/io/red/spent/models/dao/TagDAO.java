package io.red.spent.models.dao;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TagDAO {
    @Id
    private Long tagId;
    private String tagDescription;

    public TagDAO() {
    }

    public TagDAO(Long tagId, String tagDescription) {
        this.tagId = tagId;
        this.tagDescription = tagDescription;
    }

    public Long getTagId() {
        return tagId;
    }

    public String getTagDescription() {
        return tagDescription.toLowerCase();
    }
}
