package com.example.demo.post;


import com.example.demo.upvote.UpvoteRepository;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import com.example.demo.user.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private PostRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UpvoteRepository upvoteRepository;

    @Autowired
    public PostService(PostRepository repository, UserRepository userRepository) {
        this.repository = repository;
    }

    public List<Post> getUserPostsService(long user_id) {
        return repository.getUserPostsRepository(user_id);
    }

    public List<Post> getAllPosts() {
        return repository.findAll();
    }

    public ResponseEntity<String> createPost(long user_id, Post post) {
        Optional<User> user = userRepository.findById(user_id);
        if (user.isPresent()) {
            post.setUser(user.get());
            repository.save(post);
            return ResponseEntity.ok("Successfully created a new post");
        }
        return ResponseEntity.badRequest().body("Error performing POST request to create new Post");
    }

    public ResponseEntity<String> deleteAllPosts() {
        repository.deleteAllPosts();
        return ResponseEntity.ok("DELETE posts operation success");
    }

    @Transactional
    public ResponseEntity<String> deleteById(long post_id) {
        Optional<Post> findPost = repository.findById(post_id);
        if (findPost.isPresent()) {
            upvoteRepository.onPostDeleted(post_id);
            repository.deletePostById(post_id);
            return ResponseEntity.ok("Post deletion success");
        }
        return ResponseEntity.badRequest().body("Could not process post DELETE request");
    }


    @Transactional
    public ResponseEntity<String> updatePost(long post_id, Post post) {
        Optional<Post> p = repository.findById(post_id);
        if (p.isPresent()) {
            Post selectedPost = p.get();
            selectedPost.copy(post);
            repository.save(selectedPost);
            return ResponseEntity.ok("Post update was successful");
        } else {
            return ResponseEntity.badRequest().body("Could not complete update request for selected post");
        }
    }
}
