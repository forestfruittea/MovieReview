CREATE TABLE actors (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(255) NOT NULL UNIQUE,
                        bio VARCHAR(1500),
                        year_of_birth BIGINT,
                        photo_path VARCHAR(255),
                        height BIGINT
);

CREATE TABLE directors (
                           id SERIAL PRIMARY KEY,
                           name VARCHAR(255) NOT NULL UNIQUE,
                           bio VARCHAR(1500),
                           date_of_birth DATE,
                           photo_path VARCHAR(255)
);

CREATE TABLE genres (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(100) NOT NULL UNIQUE,
                        photo_path VARCHAR(255),
                        description VARCHAR(500)

);

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

CREATE TABLE movie_genre (
                             movie_id BIGINT NOT NULL,
                             genre_id BIGINT NOT NULL,
                             PRIMARY KEY (movie_id, genre_id),
                             FOREIGN KEY (movie_id) REFERENCES movies(id) ON DELETE CASCADE,
                             FOREIGN KEY (genre_id) REFERENCES genres(id) -- Removed ON DELETE CASCADE here
);

CREATE TABLE movie_actor (
                             movie_id BIGINT NOT NULL,
                             actor_id BIGINT NOT NULL,
                             PRIMARY KEY (movie_id, actor_id),
                             FOREIGN KEY (movie_id) REFERENCES movies(id) ON DELETE CASCADE,
                             FOREIGN KEY (actor_id) REFERENCES actors(id) -- Removed ON DELETE CASCADE here
);
CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       username VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       avatar_path VARCHAR(255),
                       role VARCHAR(20) NOT NULL DEFAULT 'CUSTOMER'
);
CREATE TABLE reviews (
                         id SERIAL PRIMARY KEY,
                         content TEXT NOT NULL,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         user_id INT NOT NULL,
                         movie_id INT NOT NULL,
                         FOREIGN KEY (user_id) REFERENCES users(id),
                         FOREIGN KEY (movie_id) REFERENCES movies(id)
);

CREATE TABLE ratings (
                                id SERIAL PRIMARY KEY,
                                user_id BIGINT NOT NULL,
                                movie_id BIGINT NOT NULL,
                                rating INT NOT NULL CHECK (rating >= 1 AND rating <= 100),
                                CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
                                CONSTRAINT fk_movie FOREIGN KEY (movie_id) REFERENCES movies (id) ON DELETE CASCADE
);
