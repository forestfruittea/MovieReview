package com.example.movierev.repository.impl;

import com.example.movierev.entity.DirectorEntity;
import com.example.movierev.entity.GenreEntity;
import com.example.movierev.repository.AbstractHibernateRepository;
import com.example.movierev.repository.GenreRepository;
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
public class GenreRepositoryImpl extends AbstractHibernateRepository<GenreEntity, Long> implements GenreRepository {
    @PersistenceContext
    private EntityManager entityManager;
    public GenreRepositoryImpl() {
        super(GenreEntity.class);
    }
    @Override
    public List<GenreEntity> findAllSortedByName() {
        return entityManager.createQuery("SELECT g FROM GenreEntity g ORDER BY g.name", GenreEntity.class).getResultList();
    }
    @Override
    public List<GenreEntity> findAllByMovieId(Long movieId) {
        return entityManager.createQuery(
                        "SELECT DISTINCT g FROM GenreEntity g " +
                                "JOIN g.movies m WHERE m.id = :movieId",
                        GenreEntity.class)
                .setParameter("movieId", movieId)
                .getResultList();
    }

}
