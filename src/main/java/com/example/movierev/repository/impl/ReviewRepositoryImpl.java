package com.example.movierev.repository.impl;

import com.example.movierev.entity.RatingEntity;
import com.example.movierev.entity.ReviewEntity;
import com.example.movierev.repository.AbstractHibernateRepository;
import com.example.movierev.repository.ReviewRepository;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;
@Stateless
public class ReviewRepositoryImpl extends AbstractHibernateRepository<ReviewEntity, Long> implements ReviewRepository {
    @PersistenceContext
    private EntityManager entityManager;
    public ReviewRepositoryImpl() {
        super(ReviewEntity.class);
    }
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
    public Optional<ReviewEntity> findById(Long reviewId) {
        return Optional.empty();
    }
    @Override
    public List<ReviewEntity> findAll() {
        return entityManager.createQuery(
                "SELECT DISTINCT r FROM ReviewEntity r " +
                "LEFT JOIN FETCH r.user " +
                "LEFT JOIN FETCH r.movie", ReviewEntity.class)
                .getResultList();
    }
}
