# Kotlin SDN Movies Example Application

This is an example backend app powered by Spring Data Neo4j, Spring Boot and the JVM language Kotlin. The domain is the [SDN movies dataset](https://neo4j.com/developer/example-project/) along with movie buffs as end users. 

The application includes features as would be typical of a social mobile application or web-app: 

* End users can interact (like, dislike) movies, roles or directors, make friends with other movie buffs, and based on these interactions, be recommended other movies that they're likely to enjoy. 
* Token-based authentication provided by Spring Security. 

Spring Data Neo4j was the first Spring Data project, started by the CEOs Rod Johnson and Emil Eifrem.
It enables convenient integration of Neo4j in your Spring-based application.
It provides object-graph mapping functionality and other features common to the Spring Data projects.

----------
### Note

This project uses Spring Data Neo4j 4 which is a complete rewrite from earlier versions. It is optimized for working with Neo4j Server and based on Neo4j's query language, Cypher.

----------

## Quickstart

* [Download](http://neo4j.com/download), install and start Neo4j Server].
* open the web-interface at http://localhost:7474
* configure a username and password if you haven't already.
* run `:play movies` command, and click and run the Cypher statement to insert the dataset
* clone this project from GitHub
. update `src/main/resources/application.properties` with the username and password you set above.
* Open localhost:7474 and create a test user: 

```CREATE (u:User {applicationToken:"ba2ab43b-01e6-4425-8089-f0ade3db28c3", email:"you@you.com", firstName:"YourFirstName", joined:"2018-10-13T01:28:22.771Z", lastActive:"2018-10-13T01:28:22.771Z", lastName:"YourLastName", password:"password", uuid:"b4342c76-f693-4205-8ffd-22d56b92cbbb" })```

* run the project with `gradle bootRun`.



## Code Walkthrough

TODO

## Endpoints:

* [AuthorizationIntegrationTests](https://github.com/appsquickly/movies-kotlin-spring-data-neo4j-4/blob/master/src/test/java/movies/spring/data/neo4j/api/endpoints/pub/AuthorizationIntegrationTests.kt)
* [UserIntegrationTests](https://github.com/appsquickly/movies-kotlin-spring-data-neo4j-4/blob/master/src/test/java/movies/spring/data/neo4j/api/endpoints/secured/UserIntegrationTests.kt)
* [MovieIntegrationTests](https://github.com/appsquickly/movies-kotlin-spring-data-neo4j-4/blob/master/src/test/java/movies/spring/data/neo4j/api/endpoints/secured/MovieIntegrationTests.kt)



