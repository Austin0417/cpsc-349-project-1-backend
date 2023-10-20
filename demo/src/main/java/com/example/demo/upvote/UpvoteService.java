package com.example.demo.upvote;


import com.example.demo.post.Post;
import com.example.demo.post.PostRepository;
import com.example.demo.user.User;
import com.example.demo.user.UserRepository;
import jakarta.transaction.Transactional;
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
        User foundUser = user.get();
        Post foundPost = post.get();
        Upvote upvote = new Upvote(foundUser, foundPost);
        repository.save(upvote);

        return ResponseEntity.ok("Successfully upvoted post with id=" + post_id + " as user_id=" + user_id);
    }

    @Transactional
    public ResponseEntity<String> deleteAll() {
        List<User> users = userRepository.findAll();
        List<Post> posts = postRepository.findAll();
        for (User user : users) {
            List<Upvote> userUpvotes = user.getUserUpvotes();
            userUpvotes.clear();
        }
        for (Post post : posts) {
            List<Upvote> postUpvotes = post.getUpvotes();
            postUpvotes.clear();
        }
        repository.deleteAll();
        return ResponseEntity.ok("Delete all upvotes was a success");
    }

    @Transactional
    public ResponseEntity<String> userRemoveUpvoteFromPost(long user_id, long post_id) {
        if (!userRepository.findById(user_id).isPresent() || !postRepository.findById(post_id).isPresent()) {
            return ResponseEntity.badRequest().body("Failed to remove upvote from post");
        }
        repository.removeUserUpvote(user_id, post_id);
        return ResponseEntity.ok("Removed upvote successfully");
    }

}
