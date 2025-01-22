package com.example.movierev.repository.impl;

import com.example.movierev.entity.UserEntity;
import com.example.movierev.repository.UserRepository;
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
    public void delete(Long userId) {
        UserEntity userEntity = entityManager.find(UserEntity.class, userId);
        if (userEntity !=null) entityManager.remove(userEntity);
    }

    @Override
    public Optional<UserEntity> findById(Long userId) {
        return Optional.ofNullable(entityManager.find(UserEntity.class, userId));
    }

    @Override
    public List<UserEntity> findAll() {
        return entityManager.createQuery("SELECT u FROM UserEntity u", UserEntity.class).getResultList();
    }
    @Override
    public boolean existsByUsername(String username) {
        Long count = entityManager.createQuery("SELECT COUNT(u) FROM UserEntity u WHERE u.username = :username", Long.class)
                .setParameter("username", username)
                .getSingleResult();
        return count > 0;
    }
}
