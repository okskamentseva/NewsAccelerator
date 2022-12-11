package com.news.models;

import javax.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(columnDefinition = "TEXT")
    private String post_text;
    private Long author;
    private Integer likes;

    public Long getId() {
        return id;
    }

    public String getText() {
        return post_text;
    }

    public void setText(String text) {
        this.post_text = text;
    }

    public Long getAuthor() {
        return author;
    }

    public void setAuthor(Long author) {
        this.author = author;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }
}
