package com.example.demo.upvote;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UpvoteRepository extends JpaRepository<Upvote, Long> {

    @Query("DELETE FROM Upvote u WHERE u.user.user_id=?1 AND u.post.id=?2")
    @Modifying
    public void removeUserUpvote(long user_id, long post_id);
}
