# Movie Evaluation and Review Website

---

## Getting Started

To get started, follow the steps below:

1. Clone the repository:
    ```bash
    git clone https://github.com/forestfruittea/MovieReview.git
    ```
2. Navigate to the project directory:
    ```bash
    cd <project_directory>
    ```
3. Build the project and start the Docker containers:
    ```bash
    docker-compose build --no-cache
    docker-compose up
    ```
4. Visit the application home page:
   [http://localhost:8080/MovieRev-1.0-SNAPSHOT/](http://localhost:8080/MovieRev-1.0-SNAPSHOT/)

5. To log in as an admin, use the following credentials:
    - **Username:** admin
    - **Password:** 123

---

## Key Features

### 1. **User Authentication and Guest Login**
- Upon entering the site, users can choose to **register**, **log in**, or **log in as a guest**.
- **Registered users** can log in with their credentials, while **guests** can explore content without creating an account.

### 2. **Movie Listings and Ratings**
- After logging in, users will be directed to a page displaying **all available movies**.
- Each movie card shows basic information and the **average rating** (calculated from all user ratings).
- **Clickable movie cards** allow users to navigate to the movie's detailed page for more information.

### 3. **Movie Detail Page**
- The detailed movie page showcases additional movie details such as **cast**, **director**, and **genre**.
- **Actor**, **director**, and **genre names** are clickable, leading to detailed pages of the respective person or genre.
- Logged-in users can **rate the movie** (one rating per user, which can be updated at any time).
- Users can leave a **review** and read reviews from other users.
- **Usernames** are clickable, linking to the user's account page, where their reviews and rated movies are displayed.

### 4. **Actor and Director List Pages**
- The **actor and director pages** show detailed information about personalities in the movie industry.
- Users can click on an actor's or director's name to view their **detailed page**.
- These pages also display a list of **other movies** in which the actor or director has participated.

### 5. **Genre List and Navigation**
- The **genre list** displays clickable cards (with images and genre names), leading to a dedicated page for each genre.
- On the genre page, users can see all the **movies** categorized under that genre.

### 6. **User Accounts and Admin Panel**
- Logged-in users can visit their **account page** to see a list of their movie reviews and rated movies. Each logged in user has an avatar that differs depending on role.
- **Admins and moderators** have access to a special **admin tool page**, enabling them to manage the site's content (movies, actors, directors, genres, reviews, and users).
- Users can also log out from their **account page**.

---