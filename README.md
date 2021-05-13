#  NewsApp Project 

## A Spring Boot Application which uses MYSQL and MONGODB created in Maven Intellij

Project Completed by : <li>Lakshmi Rajan</li><li>Pooja P</li> <li>Sam P Achankunju</li> 


### Recreate:

1. Clone the repository.
2. Open the folder using your IDE
3. Create collection "articles" in MONGODB.
4. Paste the Article Details  to monogdb (Copy paste the data in the file database to mongo cmd).
5. Run all 5 applications situated in each folder(All 5 Microservices mentioned below)
6. All controller details is documented. Use SWAGGER UI to retrieve them.(http://localhost:{portnumber}/swagger-ui.html)

### Working and Use:

This is an web application that can create users to access the details of news article. User Registration and Authentication is required to use all the Features

<li>JWT Authentication</li>

User can register using the respective url/signup. The user can then login to obtain jwt token(url/login). This jwt token is used on all microservices to use their respective functions.
<li>NEWS API</li>

User with jwt token can search articles based on category,country and read them.
<li>FAVOURITE API</li>

User can like or dislike an articles based on the id of the article and can return all the liked articles of a particular user.
<li>API-Gateway</li>

All the above mentioned microservices can be accessed using the Zuul Gateway. The routes can be determined by its respective application.properties file
<li>Eureka Server</li>

All the above microservices are registered to the Eureka Server.

