package com.example.movierev.Repositories.impl;

import com.example.movierev.Entities.ReviewEntity;
import com.example.movierev.Repositories.ReviewRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;
@ApplicationScoped
public class ReviewRepositoryImpl implements ReviewRepository {
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public ReviewEntity save(ReviewEntity reviewEntity) {
        entityManager.persist(reviewEntity);
        return reviewEntity;
    }
    @Override
    public List<ReviewEntity> findByMovieId(Long movieId) {
        return entityManager.createQuery(
                        "SELECT r FROM ReviewEntity r WHERE r.movie.id = :movieId", ReviewEntity.class)
                .setParameter("movieId", movieId)
                .getResultList();
    }

    @Override
    public List<ReviewEntity> findByUserId(Long userId) {
        return entityManager.createQuery(
                        "SELECT r FROM ReviewEntity r WHERE r.user.id = :userId", ReviewEntity.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public ReviewEntity update(ReviewEntity reviewEntity) {
        return null;
    }

    @Override
    public void delete(Long reviewId) {

    }

    @Override
    public Optional<ReviewEntity> findById(Long reviewId) {
        return Optional.empty();
    }

    @Override
    public List<ReviewEntity> findAll() {
        return null;
    }
}
