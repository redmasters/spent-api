package io.red.spent.enums;

public enum TagEnum {
    FOOD(1L, "FOOD"),
    TRANSPORT(2L, "TRANSPORT"),
    ENTERTAINMENT(3L, "ENTERTAINMENT"),
    OTHER(4L, "OTHER");

    private Long id;
    private String description;

    TagEnum() {
    }

    TagEnum(Long id, String description) {
        this.id = id;
        this.description = description;
    }

public static Long fromString(String text) {
        for (TagEnum tag : TagEnum.values()) {
            if (tag.description.equalsIgnoreCase(text)) {
                return tag.getId();
            }
        }
        return null;
    }
    public Long getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
