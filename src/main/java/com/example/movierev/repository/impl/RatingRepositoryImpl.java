package com.example.movierev.repository.impl;

import com.example.movierev.entity.GenreEntity;
import com.example.movierev.entity.RatingEntity;
import com.example.movierev.repository.AbstractHibernateRepository;
import com.example.movierev.repository.RatingRepository;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

@Stateless
public class RatingRepositoryImpl extends AbstractHibernateRepository<RatingEntity, Long> implements RatingRepository {
    @PersistenceContext
    private EntityManager entityManager;
    public RatingRepositoryImpl() {
        super(RatingEntity.class);
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

    @Override
    public List<RatingEntity> findByUser(Long userId) {
        return entityManager.createQuery(
                        "SELECT r FROM RatingEntity r WHERE r.user.id = :userId", RatingEntity.class)
                .setParameter("userId", userId)
                .getResultList();
    }
    @Override
    public RatingEntity update(RatingEntity rating) {
        entityManager.merge(rating);
        return rating;
    }
}
