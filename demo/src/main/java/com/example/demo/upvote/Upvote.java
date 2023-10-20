package com.example.demo.upvote;


import com.example.demo.post.Post;
import com.example.demo.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name="Upvotes")
public class Upvote {
    @Id
    @SequenceGenerator(name="upvote_sequence",
    sequenceName = "upvote_sequence",
    allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "upvote_sequence")
    private long id;

    @ManyToOne
    @JoinColumn
    @JsonIgnore
    private Post post;

    @ManyToOne
    @JoinColumn
    private User user;

    public Upvote() {}
    public Upvote(User user, Post post) {
        this.user = user;
        this.post = post;
    }

    public long getId() { return id; }
    public void setPost(Post post) { this.post = post; }
    public void setUser(User user) { this.user = user; }
    public Post getPost() { return post; }
    public User getUser() { return user; }
}
