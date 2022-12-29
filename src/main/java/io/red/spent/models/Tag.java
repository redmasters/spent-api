package io.red.spent.models;

import javax.persistence.*;

@Entity
@Table(name = "sa_tag")
public class Tag {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @Column(name = "tag_id")
    private Long tagId;

    @Column(name = "tag_description")
    private String description;

    public Tag() {
    }

    public Tag(Long tagId, String description) {
        this.tagId = tagId;
        this.description = description;
    }

    public Tag(Long tagId) {
        this.tagId = tagId;
    }

    public Tag(String description) {
        this.description = description;
    }

    public Long getTagId() {
        return tagId;
    }

    public String getDescription() {
        return description;
    }
}

