package com.example.demo.upvote;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UpvoteRepository extends JpaRepository<Upvote, Long> {
}
