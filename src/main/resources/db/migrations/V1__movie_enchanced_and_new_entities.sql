-- Actors Table
CREATE TABLE actors (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(255) NOT NULL UNIQUE,
                        bio VARCHAR(1500),
                        year_of_birth BIGINT,
                        photo_path VARCHAR(255),
                        height BIGINT
);

-- Directors Table
CREATE TABLE directors (
                           id SERIAL PRIMARY KEY,
                           name VARCHAR(255) NOT NULL UNIQUE,
                           bio VARCHAR(1500)
);

-- Genres Table
CREATE TABLE genres (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(100) NOT NULL UNIQUE
);

-- Movies Table
CREATE TABLE movies (
                        id SERIAL PRIMARY KEY,
                        title VARCHAR(255) NOT NULL,
                        description VARCHAR(500),
                        country VARCHAR(255),
                        director_id BIGINT NOT NULL,
                        release_date DATE,
                        poster_path VARCHAR(255) NOT NULL,
                        length BIGINT,
                        budget BIGINT,
                        box_office BIGINT,
                        FOREIGN KEY (director_id) REFERENCES directors(id) ON DELETE CASCADE
);

-- Movie-Genre Many-to-Many Table
CREATE TABLE movie_genre (
                             movie_id BIGINT NOT NULL,
                             genre_id BIGINT NOT NULL,
                             PRIMARY KEY (movie_id, genre_id),
                             FOREIGN KEY (movie_id) REFERENCES movies(id) ON DELETE CASCADE,
                             FOREIGN KEY (genre_id) REFERENCES genres(id) -- Removed ON DELETE CASCADE here
);

-- Movie-Actor Many-to-Many Table
CREATE TABLE movie_actor (
                             movie_id BIGINT NOT NULL,
                             actor_id BIGINT NOT NULL,
                             PRIMARY KEY (movie_id, actor_id),
                             FOREIGN KEY (movie_id) REFERENCES movies(id) ON DELETE CASCADE,
                             FOREIGN KEY (actor_id) REFERENCES actors(id) -- Removed ON DELETE CASCADE here
);
