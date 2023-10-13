package com.example.demo.user;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.username=?1")
    public Optional<User> checkExistingUsername(String username);

    @Query("SELECT u FROM User u WHERE (u.email=?1 OR u.username=?1) AND u.password=?2")
    public Optional<User> validateLoginCredentials(String userOrEmail, String password);

    @Query("SELECT u FROM User u WHERE u.user_id=?1")
    public Optional<User> getById(long user_id);

}
