package com.example.movierev.repository;

import com.example.movierev.entity.ActorEntity;
import com.example.movierev.entity.DirectorEntity;
import com.example.movierev.entity.GenreEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface DirectorRepository extends JpaRepository<DirectorEntity, Long> {
    @Query("SELECT d FROM DirectorEntity d ORDER BY d.name")
    List<DirectorEntity> findAllSortedByName();
    Optional<DirectorEntity> findByName(String name);
    @Override
    @EntityGraph(value = "Director.movies", type = EntityGraph.EntityGraphType.LOAD)
    List<DirectorEntity> findAll();
    @EntityGraph(value = "Director.movies", type = EntityGraph.EntityGraphType.LOAD)
    Optional<DirectorEntity> findById(Long id);

}
