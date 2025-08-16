package com.miks.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class BlogPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    @Column(columnDefinition = "TEXT")
    private String content;
    private String author;
    private String imageUrl;

    // Constructors
    public BlogPost() {}

    public BlogPost(String title, String content, String author, String imageUrl) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.imageUrl = imageUrl;
    }
}
