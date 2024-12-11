package com.example.movierev.Repositories.impl;

import com.example.movierev.Entities.RatingEntity;
import com.example.movierev.Entities.ReviewEntity;
import com.example.movierev.Repositories.RatingRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class RatingRepositoryImpl implements RatingRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public RatingEntity save(RatingEntity ratingEntity) {
        entityManager.persist(ratingEntity);
        return ratingEntity;
    }
    @Override
    public List<RatingEntity> findByMovieId(Long movieId) {
        return entityManager.createQuery(
                        "SELECT r FROM RatingEntity r WHERE r.movie.id = :movieId", RatingEntity.class)
                .setParameter("movieId", movieId)
                .getResultList();
    }
    @Override
    public Optional<RatingEntity> findByUserAndMovie(Long userId, Long movieId) {
        try {
            RatingEntity ratingEntity = entityManager.createQuery(
                            "SELECT r FROM RatingEntity r WHERE r.user.id = :userId AND r.movie.id = :movieId", RatingEntity.class)
                    .setParameter("userId", userId)
                    .setParameter("movieId", movieId)
                    .getSingleResult();

            return Optional.ofNullable(ratingEntity);  // Wrap the result in an Optional

        } catch (Exception e) {
            return Optional.empty();  // Return an empty Optional if no result is found or if an error occurs
        }
    }
}
