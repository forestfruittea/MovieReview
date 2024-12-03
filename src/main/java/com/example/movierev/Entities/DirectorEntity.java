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
@Table(name = "directors")
public class DirectorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 255)
    private String name;
    @ToString.Exclude
    @OneToMany(mappedBy = "director")
    private List<MovieEntity> movies;
    @Column(name = "bio", length = 1500)
    private String bio;
}
