package com.example.demo.post;


import com.example.demo.upvote.Upvote;
import com.example.demo.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="Posts")
public class Post {
    @Id
    @SequenceGenerator(
            name="post_sequence",
            sequenceName = "post_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_sequence")
    private long id;
    private String title;
    private String imageUrl;
    private String textContent;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post", fetch=FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonIgnore
    @Nullable
    private List<Upvote> upvotes;

    public Post() {}

    public Post(String title, String imageUrl, String textContent) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.textContent = textContent;
    }

    public long getId() { return id; }
    public String getTitle() { return title; }
    public String getImageUrl() { return imageUrl; }
    public String getTextContent() { return textContent; }
    public User getUser() { return user; }
    public List<Upvote> getUpvotes() { return upvotes; }

    public void setUpvotes(List<Upvote> upvotes) { this.upvotes = upvotes; }
    public void setTitle(String title) { this.title = title; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public void setTextContent(String textContent) { this.textContent = textContent; }
    public void setUser(User user) { this.user = user; }

    public String toString() {
        return "{Title: " + title + ", Image URL: " + imageUrl + ", Content: " + textContent + "}";
    }
}
