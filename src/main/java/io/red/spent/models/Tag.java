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
    private String tagDescription;

    public Tag() {
    }

    public Tag(Long tagId, String tagDescription) {
        this.tagId = tagId;
        this.tagDescription = tagDescription;
    }

    public Tag(Long tagId) {
        this.tagId = tagId;
    }

    public Tag(String tagDescription) {
        this.tagDescription = tagDescription;
    }

    public Long getTagId() {
        return tagId;
    }

    public String getTagDescription() {
        return tagDescription;
    }
}

