package com.dauphine.blogger.models;

import java.time.LocalDateTime;
import java.util.UUID;
public class Post {
    private UUID id;
    private String title;
    private String content;
    private Category category;
    private LocalDateTime createdDate;

    public Post(UUID id, String title, String content, UUID uuid) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Category getCategory() {
        return category;
    }

}
