package com.example.demo.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    private UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> userServiceGetAll() {
        return repository.findAll();
    }

    public String userServiceLogin(String userOrEmail, String password) throws JsonProcessingException {
        Optional<User> user = repository.validateLoginCredentials(userOrEmail, password);
        if (user.isPresent()) {
            Map<String, String> json = new HashMap<String, String>();
            json.put("user_id", Long.toString(user.get().getUserId()));
            json.put("email", user.get().getEmail());
            json.put("username", user.get().getUsername());
            json.put("password", user.get().getPassword());
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(json);
        }
        return null;
    }

    public ResponseEntity<String> userServicePost(User user) {
        Optional<User> potentialUser = repository.checkExistingUsername(user.getUsername());
        if (potentialUser.isPresent()) {
            return ResponseEntity.badRequest().body("Username already exists!");
        }
        repository.save(user);
        return ResponseEntity.ok("Account creation was a success");
    }

    public ResponseEntity<String> userServiceDelete() {
        repository.deleteAll();
        return ResponseEntity.ok("Delete request success");
    }
}
