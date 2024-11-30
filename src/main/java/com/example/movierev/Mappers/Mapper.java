package com.example.movierev.Mappers;

public interface Mapper<E, D> {
    D toDto(E entity);
    E toEntity(D dto);
}
