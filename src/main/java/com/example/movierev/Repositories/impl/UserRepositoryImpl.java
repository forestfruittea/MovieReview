package com.example.movierev.Repositories.impl;

import com.example.movierev.Entities.MovieEntity;
import com.example.movierev.Entities.UserEntity;
import com.example.movierev.Repositories.UserRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public UserEntity save(UserEntity userEntity) {
        entityManager.persist(userEntity);
        return userEntity;    }

    @Override
    public UserEntity findByName(String username) {
        try {
            return entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.username = :username", UserEntity.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public UserEntity update(UserEntity userEntity) {
        return null;
    }

    @Override
    public void delete(Long userId) {

    }

    @Override
    public Optional<UserEntity> findById(Long userId) {
        return Optional.ofNullable(entityManager.find(UserEntity.class, userId));
    }

    @Override
    public List<UserEntity> findAll() {
        return null;
    }
    @Override
    public boolean existsByUsername(String username) {
        Long count = entityManager.createQuery("SELECT COUNT(u) FROM UserEntity u WHERE u.username = :username", Long.class)
                .setParameter("username", username)
                .getSingleResult();
        return count > 0;
    }
}
