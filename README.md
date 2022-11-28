# Introduction

This is the Register Login Application part of the Techcareer / Airties Cloud SW Bootcamp Graduation projects.

Using Spring Security, Gson, Rest API, Spring Boot, H2 Database, Docker.

Role based user registration. With a JWT token, we can send requests to the Diary and Blog applications.
All the applications have their own databases.
They all must be running at the same time to work properly.

# Installation

Before starting the Register/Login process, when you first run the application, you need to fill the roles table.

You can do this by going to the following link:
http://localhost:3333/h2-console

for the database url you need to enter: jdbc:h2:file:./memory_persist/registerlogin_database
for the username you need to enter: root, and for the password you need to enter: root
after that run this query;
INSERT INTO roles (role_name) VALUES ('ROLE_USER');
INSERT INTO roles (role_name) VALUES ('ROLE_MODERATOR');
INSERT INTO roles (role_name) VALUES ('ROLE_ADMIN');

After that you can start the Register/Login process.

# Docker Setup

Start the terminal in the project directory. Start the containers by "docker-compose up".

## Mappings

## Mappings for the Register Login Application

#### register: Register a new user

- http://localhost:3333/api/auth/register

      {
          "username": "test",
          "email" : "test@test.com",
          "password" : "123456789",
           "role" : ["admin"]
      }

Creates a new user to the database. If you don't provide a role, user registered as a user. You can provide "admin" and "mod".

#### login: Logs in the user
- http://localhost:3333/api/auth/register

      {
          "username": "test",
          "password" : "123456789"
      }

Logs in a user and returns a JWT token. You can use this token to access the Diary and Blog applications.

### GET Mappings

#### getAllUsers: List every user that is on the database.

- http://localhost:3333/api/auth/users

   In order to access this link you must login with a 'Admin' role. Provide the JWT token in the header.

    And you can see the users in the database.

#### Testing roles of the users.
- http://localhost:3333/api/test/all

    Without logging in, you can access this link.

- http://localhost:3333/api/test/admin

    After logging in, you can access this link with a 'Admin' role.

- http://localhost:3333/api/test/mod

    After logging in, you can access this link with a 'Moderator' role.


## Mappings for Diary Application.
### You can send requests after a successfully login.

#### Post Mappings
Create a post at the Diary Application.
- http://localhost:3333/gateway/diary/createDiary
    
        {
             "request_diaryTitle" : "post from gate",
             "request_diaryContent" : "post from gate",
             "request_diaryUserName" : "eneskasikci",
             "request_diaryUserId" : 1
        }

After checking the JWT token, the post is created at the Diary Application if the username and the id matches the Diary application user table.

#### Get Mappings.
- http://localhost:3333/gateway/diary/listDiaryPostFromUser/{username}

    After checking the JWT token, the user can see the posts that he/she created after providing the username.


#### Put Mappings

- http://localhost:3333/gateway/diary/updateDiaryPost
    
            {
                    "request_diaryUpdatedPostId" : 1,
                    "request_diaryUpdatedPostTitle" : "test" ,
                    "request_diaryUpdatedPostContent" : "test",
                    "request_diaryUpdatedPost_userName" : "test"
            }

After checking the JWT token, the user can delete the post that he/she created.

#### Delete Mappings.
- http://localhost:3333/gateway/diary/deleteDiaryPost

        {
            "deletionrequest_userName" : "test",
            "deletionrequest_diaryId" : 1
        }

After checking the JWT token, the user can delete the post that he/she created.

## Mappings for Blog Application.
### You can send requests after a successfully login.

#### Post Mappings
Create a post at the Diary Application.
- http://localhost:3333/gateway/blog/createBlogPost
    
        {
             "request_blogTitle" : "post from gate",
             "request_blogContent" : "post from gate",
             "request_blogUserName" : "eneskasikci",
             "request_blogUserId" : 1
        }

After checking the JWT token, the post is created at the Diary Application if the username and the id matches the Diary application user table.

#### Get Mappings.
- http://localhost:3333/gateway/blog/listBlogPosts
    
        After checking the JWT token, the user can see the posts that is created in the Blog App database.
