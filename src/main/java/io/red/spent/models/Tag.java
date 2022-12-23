package io.red.spent.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sa_tag")
public class Tag {
    @Id
    private Long tagId;
    private String tagName;

    public Tag() {
    }

    public Tag(Long tagId, String tagName) {
        this.tagId = tagId;
        this.tagName = tagName;
    }

    public Tag(String tagName) {
        this.tagName = tagName;
    }

    public Long getTagId() {
        return tagId;
    }

    public String getTagName() {
        return tagName;
    }
}

