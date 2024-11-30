package com.example.movierev.Repositories;

import com.example.movierev.Entities.UserEntity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
@ApplicationScoped
public class UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void createUser(UserEntity user) {
        entityManager.persist(user);
    }
}