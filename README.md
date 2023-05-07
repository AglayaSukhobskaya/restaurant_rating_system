# Restaurant Raiting System
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/c4a14fe0dba94d7a876f361460f8d58b)](https://app.codacy.com/gh/AglayaSukhobskaya/restaurant_rating_system/dashboard?utm_source=gh&utm_medium=referral&utm_content=&utm_campaign=Badge_grade)  
## Technical requirement

Design and implement a REST API using Hibernate/Spring/SpringMVC (Spring-Boot preferred!) without frontend.

The task is:

Build a voting system for deciding where to have lunch.

- 2 types of users: admin and regular users  
- Admin can input a restaurant and it's lunch menu of the day (2-5 items usually, just a dish name and price)  
- Menu changes each day (admins do the updates)  
- Users can vote for a restaurant they want to have lunch at today  
- Only one vote counted per user  
- If user votes again the same day: 
    * If it is before 11:00 we assume that he changed his mind 
    * If it is after 11:00 then it is too late, vote can't be changed

Each restaurant provides a new menu each day.

## Application functionality
### New User Registration  
<code>POST http: //localhost:8080/auth/registration</code>  

### ADMIN Functionality
#### Manipulations with users
Get all users:  
<code>GET http: //localhost:8080/api/admin/users</code>

Get user by id:  
<code>GET http: //localhost:8080/api/admin/users/1</code>

Delete user by id:  
<code>DELETE http: //localhost:8080/api/admin/users/1</code>

#### Manipulations with restaurants
Get all restaurants with full menu:  
<code>GET http: //localhost:8080/api/admin/restaurants</code>

Get restaurant by id with full menu:  
<code>GET http: //localhost:8080/api/admin/restaurants/1</code>

Create new restaurant:  
<code>POST http: //localhost:8080/api/admin/restaurants</code>

Update restaurant by id:  
<code>PUT http: //localhost:8080/api/admin/restaurants/1</code>

Delete restaurant by id:  
<code>DELETE http: //localhost:8080/api/admin/restaurants/1</code>

#### Manipulations with dishes
Get all dishes:  
<code>GET http: //localhost:8080/api/admin/dishes</code>

Get dish by id:  
<code>GET http: //localhost:8080/api/admin/dishes/1</code>

Create new dish:  
<code>POST http: //localhost:8080/api/admin/dishes</code>

Update dish by id:  
<code>PUT http: //localhost:8080/api/admin/dishes/1</code>

Delete dish by id:  
<code>DELETE http: //localhost:8080/api/admin/dishes/1</code>

### PROFILE Functionality
#### Manipulations with user's data
Get profile:  
<code>GET http: //localhost:8080/api/profile</code>

Update profile:  
<code>PUT http: //localhost:8080/api/profile</code>

Delete profile:  
<code>DELETE http: //localhost:8080/api/profile</code>

#### Manipulations with restaurants
Get all restaurants with day menu:  
<code>GET http: //localhost:8080/api/profile/restaurants</code>

Get restaurant by id with day menu:  
<code>GET http: //localhost:8080/api/profile/restaurants/1</code>

#### Manipulations with votes
Get all user's votes:  
<code>GET http: //localhost:8080/api/profile/votes</code>

Get user's vote by id:  
<code>GET http: //localhost:8080/api/profile/votes/1</code>

Create new vote:  
<code>POST http: //localhost:8080/api/profile/votes</code>

Update user's vote by id:  
<code>PUT http: //localhost:8080/api/profile/votes/1</code>

Delete user's vote by id:  
<code>DELETE http: //localhost:8080/api/profile/votes/1</code>

## Technology Stack
Spring Boot 2.5.1 / Spring Security / Spring Data JPA / Hibernate / Maven / H2 / Postman
