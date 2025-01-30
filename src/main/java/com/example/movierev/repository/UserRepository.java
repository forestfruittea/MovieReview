package com.example.movierev.repository;

import com.example.movierev.entity.ReviewEntity;
import com.example.movierev.entity.UserEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByUsername(String username);

    // Custom query for existsByUsername
    @Query("SELECT COUNT(u) > 0 FROM UserEntity u WHERE u.username = :username")
    boolean existsByUsername(String username);
    @Override
    @EntityGraph(value = "User.reviews", type = EntityGraph.EntityGraphType.LOAD)
    Optional<UserEntity> findById(Long id);

}
