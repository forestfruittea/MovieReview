
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