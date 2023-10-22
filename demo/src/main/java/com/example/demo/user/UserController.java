package com.example.demo.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("api/users")
@CrossOrigin(origins = {"http://127.0.0.1:5500", "http://localhost/"})
public class UserController {
    private UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping()
    public List<User> userControllerGet() {
        return service.userServiceGetAll();
    }

    @GetMapping("/find")
    @ResponseBody
    public String userControllerLogin(@RequestParam("userOrEmail") String userOrEmail,
                                              @RequestParam("password") String password) {
        try {
            return service.userServiceLogin(userOrEmail, password);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    @PostMapping()
    public ResponseEntity<String> userControllerPost(@RequestBody User user) {
        System.out.println("Received user: " + user.toString());
        return service.userServicePost(user);
    }

    @DeleteMapping()
    public ResponseEntity<String> userControllerDelete() {
        return service.userServiceDelete();
    }
}
