package com.example.movierev.Mappers;

public interface Mapper<E,D> {

    E toEntity(D d);

    D toDto(E e);

}
