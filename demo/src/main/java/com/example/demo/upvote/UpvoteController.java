package com.example.demo.upvote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/upvotes")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class UpvoteController {
    @Autowired
    private UpvoteService service;


    @GetMapping()
    public List<Upvote> getAll() {
        return service.getAll();
    }

    @PostMapping()
    public ResponseEntity<String> postUpvote(@RequestParam("user_id") long user_id, @RequestParam("post_id") long post_id) {
        return service.postUpvote(user_id, post_id);
    }
}
