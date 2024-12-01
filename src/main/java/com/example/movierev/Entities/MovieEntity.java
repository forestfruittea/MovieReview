package com.example.movierev.Entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "movies")
public class MovieEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 255)
    @NotNull
    private String title;

    @Column(name = "description", length = 500)
    private String description;
    private String country;
    @Column(name = "starring_role")
    @NotNull
    private String starringRole;
    @NotNull
    private String director;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(name = "genre")
    private String genre;
    @Column(name = "poster_path")
    @NotNull
    private String posterPath;
    private Long length;
    private Long budget;
    @Column(name = "box_office")
    private Long boxOffice;
}
