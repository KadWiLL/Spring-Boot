package com.propfile.profile.repository;

import com.propfile.profile.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByIdAndUserId(Long id, Long userId);
    Optional<Post> findById(Long Id);
}
