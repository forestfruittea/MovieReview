package com.example.movierev.repository.impl;

import com.example.movierev.entity.ActorEntity;
import com.example.movierev.repository.AbstractHibernateRepository;
import com.example.movierev.repository.ActorRepository;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

import java.util.List;
import java.util.Optional;
@Stateless
public class ActorRepositoryImpl extends AbstractHibernateRepository<ActorEntity, Long> implements ActorRepository {
    @PersistenceContext
    private EntityManager entityManager;
    public ActorRepositoryImpl() {
        super(ActorEntity.class);
    }
    @Override
    public List<ActorEntity> findAllSortedByName() {
        return entityManager.createQuery("SELECT a FROM ActorEntity a ORDER BY a.name", ActorEntity.class).getResultList();
    }
}
