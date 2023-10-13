package com.example.demo.post;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query(value = "SELECT p FROM Post p WHERE p.user.user_id=?1")
    public List<Post> getUserPostsRepository(long user_id);

    @Modifying
    @Transactional
    @Query("DELETE FROM Post p WHERE p.id=?1")
    public void deletePostById(long post_id);

    @Transactional
    @Modifying
    @Query("DELETE FROM Post p")
    public void deleteAllPosts();

    @Query("SELECT p FROM Post p WHERE p.id=?1")
    public Optional<Post> getById(long post_id);

}
