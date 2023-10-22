package com.example.demo.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/posts")
@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost/"})
public class PostController {

    private PostService service;

    @Autowired
    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping()
    public List<Post> getAllPosts() {
        return service.getAllPosts();
    }

    @GetMapping("/find")
    public List<Post> getUserPostsController(@RequestParam(name = "user_id") long user_id) {
        return service.getUserPostsService(user_id);
    }

    @PostMapping()
    public ResponseEntity<String> createPost(@RequestParam(name="user_id") long user_id, @RequestBody Post post) {
        return service.createPost(user_id, post);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteAllPosts() {
        return service.deleteAllPosts();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteById(@RequestParam(name="post_id") long post_id) {
        return service.deleteById(post_id);
    }

    @PutMapping()
    public ResponseEntity<String> updatePost(@RequestParam(name="post_id") long post_id, @RequestBody Post newPost) {
        return service.updatePost(post_id, newPost);
    }

}
