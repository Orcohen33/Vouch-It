

## Vouch-it
<p>
This project is a backend for a android application using Spring-boot, Spring-security, REST API, and Hibernate for the back-end, with MySQL as the database. The project follows a monolithic architecture and the Model-View-Controller (MVC) design pattern.
</p>

### Table of contents


- Getting Started
    - Prerequisites
    - Installation
- Project Structure
- Usage
- Database Design
- Built With
- Author
- License


### Getting Started
<p>
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.
</p>

#### Prerequisites
Before you begin, make sure you have the following installed on your machine:

- <b>Java 8+</b>
- <b>MySQL</b>
- <b>Maven</b>

#### Installation
1. Clone the repository

`git clone https://github.com/<your-username>/<repository-name>.git`

2. Install the dependencies

`mvn clean install`

3. Update the application properties file with your MySQL connection details

`src/main/resources/application.properties`

4. Run the application

`mvn spring-boot:run`

### Project Structure
The project is structured in following way:

```
-src
    |-main
        |-java
            |-com.vouchit.backend
                |-config(contains security config)
                |-controller(contains rest controllers)
                |-dao (contains data access objects)
                |-model (contains entities)
                |-service(contains business logic)
        |-resources (contains application.properties)
```
Usage
This API provides endpoints for CRUD operations on the following tables:

authorities
categories
companies
coupons
customers
customers_coupons
purchases
purchases_coupons
users
users_authorities
The API documentation can be found at http://localhost:8080/swagger-ui.html once the server is running

### Database Design
The project uses MySQL as the database.

\<Picture of ERD>

### Diagrams

- when the user sends a valid HTTP request, the request is handled by the Spring MVC controller, which calls the appropriate service method, and the service method interact with DAO (Data Access Object) class to perform operations on the database (MySQL), retrieve or update the necessary data, after that the service method returns the result to the controller, which then sends an HTTP response back to the user.

\<Diagram picture>

### Built With
- Spring-boot - The web framework used
- Spring-security - The security framework used
- REST API - API design
- Hibernate - ORM tool
- MySQL - Database

