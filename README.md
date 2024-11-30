- Estimated Time: ~1 day.

---
## 3. Introduce Ratings (08.12-09.12) Middle priority
### 3.1. Ratings
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
## 4. User/Admin Authentication and Admin Tools (10.12-12.12) High priority
### 4.1. User Authentication
## 3. User/Admin Authentication and Admin Tools (10.12-12.12) High priority
### 3.1. User Authentication
- Description:
- Implement registration, login, and roles (User/Admin).
- Backend:
  @@ -128,7 +115,7 @@ Will relate to User and Movie
- Estimated Time: ~2 days.


### 4.2. Admin Tools
### 3.2. Admin Tools
- Description:
- Admin-only pages for managing movies, users and reviews.
- Reconsider CRUD operations for movies and reviews.
  @@ -138,6 +125,21 @@ Will relate to User and Movie
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
@@ -154,9 +156,9 @@ Will relate to User and Movie
| Personal user page      | 1 day         |
| Search movie by title   | 1 day         |
| Search user by username | 1 day         |
| Ratings                 | 2  days       |
| Authentication          | 2 days        |
| Admin tools             | 1 days        |
| Ratings                 | 2  days       |

## Other features were evaluated as Minor. I'll only get to them if there's time.

@@ -195,7 +197,7 @@ Will relate to User and Movie
- API enhancements: maybe
- Frontend:
- Dropdowns or buttons for filters and sorting.
- Estimated Time: ~3 days.
- Estimated Time: ~2 days.

### 7.2. Movie Categories
- Description:
  @@ -224,7 +226,7 @@ Will relate to User and Movie
- Display recommendations on the home page or a "Recommended For You" section.
- UI similar to the movie list, showing posters and titles.

- Estimated Time: ~1 day.
- Estimated Time: ~2 days.


---