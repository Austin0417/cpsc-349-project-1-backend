package com.example.demo.upvote;


import com.example.demo.post.Post;
import com.example.demo.post.PostRepository;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UpvoteService {
    @Autowired
    private UpvoteRepository repository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Upvote> getAll() {
        return repository.findAll();
    }

    public ResponseEntity<String> postUpvote(long user_id, long post_id) {
        Optional<User> user = userRepository.getById(user_id);
        Optional<Post> post = postRepository.getById(post_id);
        if (!user.isPresent() || !post.isPresent()) {
            return ResponseEntity.badRequest().body("Error occurred while processing upvote request");
        }
        Upvote upvote = new Upvote(user.get(), post.get());
        repository.save(upvote);
        return ResponseEntity.ok("Successfully upvoted post with id=" + post_id + " as user_id=" + user_id);
    }


}
