package com.example.demo.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/posts")
@CrossOrigin(origins = "http://127.0.0.1:5500")
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
    public List<Post> getUserPostsController(@RequestParam(value = "user_id") long user_id) {
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
    public ResponseEntity<String> deleteById(@RequestParam(value="post_id") long post_id) {
        return service.deleteById(post_id);
    }
}
