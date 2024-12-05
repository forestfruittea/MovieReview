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
    public UserEntity createUser(UserEntity userEntity) {
        entityManager.persist(userEntity);
        return userEntity;    }

    @Override
    public UserEntity findUserByName(String username) {
        try {
            return entityManager.createQuery("SELECT u FROM UserEntity u WHERE u.username = :username", UserEntity.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (NoResultException e) {
            // No user found with the given username
            return null;
        }
    }

    @Override
    public UserEntity updateUser(UserEntity userEntity) {
        return null;
    }

    @Override
    public void deleteUser(Long userId) {

    }

    @Override
    public Optional<UserEntity> findUserById(Long userId) {
        return Optional.ofNullable(entityManager.find(UserEntity.class, userId));
    }

    @Override
    public List<UserEntity> findAllUsers() {
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
