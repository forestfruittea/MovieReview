package com.example.movierev.repository.impl;

import com.example.movierev.entity.ActorEntity;
import com.example.movierev.entity.DirectorEntity;
import com.example.movierev.repository.AbstractHibernateRepository;
import com.example.movierev.repository.ActorRepository;
import com.example.movierev.repository.DirectorRepository;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

@Stateless
public class DirectorRepositoryImpl extends AbstractHibernateRepository<DirectorEntity, Long> implements DirectorRepository {
    @PersistenceContext
    private EntityManager entityManager;
    public DirectorRepositoryImpl() {
        super(DirectorEntity.class);
    }
    @Override
    public List<DirectorEntity> findAllSortedByName() {
        return entityManager.createQuery("SELECT d FROM DirectorEntity d ORDER BY d.name", DirectorEntity.class).getResultList();
    }
}