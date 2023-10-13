package com.example.demo.user;


import com.example.demo.post.Post;
import com.example.demo.upvote.Upvote;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="\"Users\"")
public class User {
    @Id
    @SequenceGenerator(
            name="user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator="user_sequence")
    private long user_id;

    private String email;

    private String username;

    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Post> userPosts;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Upvote> userUpvotes;

    public User() {}

    public User(String email, String username, String password) {
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public void setEmail(String email) { this.email = email; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setUserPosts(List<Post> userPosts) { this.userPosts = userPosts; }

    public long getUserId() { return user_id; }
    public String getEmail() { return email; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public List<Post> getUserPosts() { return userPosts; }


    public String toString() {
        return "{\"user_id\": " + user_id + ", \"email\": " + email +  ", \"username\": " + username + ", \"password\": " + password + "}";
    }
}
