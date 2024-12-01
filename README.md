
# Movie Evaluation and Review Website - Feature Description and Estimation

This document provides a detailed breakdown of prioritized features for the movie evaluation and review website along with rough time estimates for implementation.

---

## 1. Focus on the Movie List, Detail View, and User Reviews First (29.11-4.12) High priority
### 1.1. Movie List
- Description:  
  Display all movies in a paginated grid or table format.  
  Each movie shows a poster, title, and short description.
- Backend:
  - Movie entity with attributes like id, title, description,
    releaseDate, genre. PosterUrl will relate to Poster and reviews to Review .
  - Create MovieRepository for DB interactions
  - Create MovieService for business logic
  - CRUD endpoints: /movies for managing movie data
  - Poster - to be discovered
- Frontend:
  - HTML with a simple grid or table.
- Estimated Time: ~2 days.

### 1.2. Movie Detail View
- Description:  
  A page displaying detailed movie information(f.e. a description, cast, and director)  
  Includes a section to display user reviews.
- Backend:
  - API endpoint: /movies/{id} for fetching movie details.
  - Review entity to store reviews linked to movies.
- Frontend:
  - Separate pages for movie details.
  - Reviews shown as a list below movie details.
- Estimated Time: ~1 day.

### 1.3. Reviews
- Description:  
  Provides a possibility to make a review.

  Display reviews submitted by users for a specific movie.  
  Each review shows the user’s name, review text, and submission date.
- Backend:
  - Review entity with attributes like id, content, date, userId, movieId.
    Will relate to User and Movie
  - Create ReviewRepository for DB interactions
  - Create ReviewService for business logic
  - CRUD endpoints: /reviews, f.e. getAllReviews by userId or movieId
- Frontend:
  - Add a reviews section below movie details.
- Estimated Time: ~2 days.
### 1.4. Users
- Description:  
  Create a UserEntity and a logic part for it.

- Backend:
  - User entity with attributes like id, username, password, reviews, avatar(mb).
    Will relate to Review
  - Create UserRepository for DB interactions
  - Create UserService for business logic
  - CRUD endpoints: /users for managing User data

- Estimated Time: ~1 day.
---
## 2. Add User Page and Search Features (05.12-7.12) High priority

### 2.1. Personal User Page
- Description:
  - An account page for users where they can(demo, no auth):
    - View their username.
    - Check all their submitted reviews related(kind of movie list but only those that have their reviews).
- Backend:
  - API endpoint: /users/{id}/profile to fetch user details.
  - Is going to be a secure endpoint to ensure only the logged-in user can access their data.
- Frontend:
  - A user dashboard displaying:
    - Username.
    - Kind of specified movie list(to be decided)
- Estimated Time: ~1 day.

### 2.2. Search Movie by title
- Description:
  - Enable searching for a specific movie by title.
  - Display movie details.
- Backend:
  - API endpoint: /movie/search?title={title} to find a user by their username.
  - API endpoint: /movie/{id} to reach specific`s movie page.
- Frontend:
  - A search bar for entering a title at movie list.
- Estimated Time: ~1 day.

### 2.3. Search User Account by Username
- Description:
  - Enable searching for a specific user's public profile by username.
  - Display public details:
    - The user's submitted reviews.
- Backend:
  - API endpoint: /users/search?username={username} to find a user by their username.
  - API endpoint: /users/{id}/public-profile to fetch public profile data.
  - Ensure privacy by exposing only public information.
- Frontend:
  - A search bar for entering a username.
  - Just as user profile.
- Estimated Time: ~1 day.

---
## 3. User/Admin Authentication and Admin Tools (10.12-12.12) High priority
### 3.1. User Authentication
- Description:
  - Implement registration, login, and roles (User/Admin).
- Backend:
  - Secure endpoints using roles.
  - Use hashed passwords (f.e. BCrypt, jwt).
- Frontend:
  - Forms for registration and login.
- Estimated Time: ~2 days.


### 3.2. Admin Tools
- Description:
  - Admin-only pages for managing movies, users and reviews.
  - Reconsider CRUD operations for movies and reviews.
- Backend:
  - Restricted endpoints for admins.
- Frontend:
  - Make pages admin/user-specified .
- Estimated Time: ~1 day.

---
## 4. Introduce Ratings (08.12-09.12) Middle priority
### 4.1. Ratings
- Description:
  - Allow users to rate movies (1 to 5).
  - Display the average rating of each movie on the movie list and detail pages.
- Backend:
  - Add Rating entity or include a rating field in Review(tbd).
  - API endpoints: kind of /movies/{id}/rate and /movies/{id}/ratings.
- Frontend:
  - Star rating widget for input and display(if there is enough time).
- Estimated Time: ~2 days.



--- 

## 5. Preparing the MVP model (13.12)
### 5.1 The MVP model is gotta be finished
- Description:
  - A Backup day before the presentation
  ## Summary of Estimated Time
| Feature                 | Time Estimate |
|-------------------------|---------------|
| Movie List              | 2 days        |
| Movie Detail View       | 1 day         |
| Reviews                 | 2 day         |
| Users                   | 1 day         |
| Personal user page      | 1 day         |
| Search movie by title   | 1 day         |
| Search user by username | 1 day         |
| Authentication          | 2 days        |
| Admin tools             | 1 days        |
| Ratings                 | 2  days       |

## Other features were evaluated as Minor. I'll only get to them if there's time.



---
## 6. Introduce Favorites, Wishlist
### 6.1. Favorites
- Description:
  - Allow users to mark movies as favorites.
  - Add a "My Favorites" section for logged-in users.
- Backend:
  - Create favorites table linking users with movies.
  - API endpoints: /users/{id}/favorites.
- Frontend:
  - Heart icon or button to toggle favorite status.
- Estimated Time: ~1-2 days.
### 6.2. Implement Wishlist
- Description:
  - Enable users to add movies to their wishlist.
  - Provide a "My Wishlist" section for logged-in users.
- Backend:
  - Create wishlist table linking users with movies.
  - API endpoints: /users/{id}/wishlist.
- Frontend:
  - Bookmark or "Add to Wishlist" button.
- Estimated Time: ~1–2 days.

---
## 7. Add Filtering, Sorting, and Movie Categories
### 7.1. Filtering and Sorting
- Description:
  - Allow users to filter movies by genre or release year.
  - Add sorting options for title, release date, or rating.
- Backend:
  - API enhancements: maybe
- Frontend:
  - Dropdowns or buttons for filters and sorting.
- Estimated Time: ~2 days.

### 7.2. Movie Categories
- Description:
  - Categorize movies by genre (e.g., Action, Drama).
  - Show categories as tags or a side navigation menu.
- Backend:
  - Category entity linked to the Movie entity.
  - API endpoint: /categories to fetch all categories.
- Frontend:
  - Tag-based navigation for categories.
- Estimated Time: ~1 days.

---

## 8. Add a Recommendation Feature
- Description:
  - Suggest movies to users based on:
    - Popular Movies: Highest-rated or most-reviewed movies.

- Backend:
  - Add an endpoint: /movies/recommendations.
    - Incorporate user-specific preferences using data ratings.
    - Optionally, use business logic to fetch popular movies or genre-based recommendations.

- Frontend:
  - Display recommendations on the home page or a "Recommended For You" section.
  - UI similar to the movie list, showing posters and titles.

- Estimated Time: ~2 days.


---







## Notes
- Start with the core features (movie list, details, and reviews).
- Gradually add other features based on progress and time remaining.
- User authentication and admin tools can be implemented last, ensuring a functional product early on.
