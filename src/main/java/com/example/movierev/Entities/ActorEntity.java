package com.example.movierev.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "actors")
public class ActorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;
    @ManyToMany(mappedBy = "actors")
    @ToString.Exclude
    private List<MovieEntity> movies;
    @Column(name = "bio", length = 1500)
    private String bio;
}
