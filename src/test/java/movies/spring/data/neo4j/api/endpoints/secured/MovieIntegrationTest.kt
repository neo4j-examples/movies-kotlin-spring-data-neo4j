package movies.spring.data.neo4j.api.endpoints.secured

import com.jayway.restassured.RestAssured
import movies.spring.data.neo4j.api.endpoints.ControllerTest
import movies.spring.data.neo4j.api.service.movie.dto.GraphDTO
import movies.spring.data.neo4j.api.service.movie.dto.MovieDTO
import movies.spring.data.neo4j.api.service.movie.dto.PersonDTO
import movies.spring.data.neo4j.api.service.movie.dto.RoleDTO
import movies.spring.data.neo4j.repositories.MovieRepository
import org.junit.After
import org.junit.Assert.*
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired


class MovieIntegrationTest : ControllerTest() {

    @Autowired lateinit var repo : MovieRepository

    @After
    fun tearDown() {
        repo.deleteAll()
    }

    @Test
    fun shouldSaveMovies() {
        val accessToken: String = login("jasper@appsquick.ly", "password").accessToken

        val dto = MovieDTO(title = "The Devil's Advocate",
                releasedYear = 1997,
                tagLine = "Evil has its winning ways",
                roles = listOf(
                        RoleDTO(person = PersonDTO("Keanu Reeves", 1964),
                                roles = listOf("Kevin Lomax")),
                        RoleDTO(person = PersonDTO("Charlize Theron", 1975),
                                roles = listOf("Mary Ann Lomax")),
                        RoleDTO(person = PersonDTO("Al Pacino", 1940),
                                roles = listOf("John Milton"))
                ))

        RestAssured.given()
                .header("Access-Token", accessToken)
                .header("API-Key", "e8f3fdcc-8825-11e5-af63-feff819cdc9f")
                .header("Content-Type", "application/json")
                .body(mapper.writeValueAsString(dto))
                .put("/movies/")
                .peek().then()
                .assertThat().statusCode(200)
                .extract().response().asString()
    }

    @Test
    fun shouldReturnGraph() {
        val accessToken: String = login("jasper@appsquick.ly", "password").accessToken

        val json = RestAssured.given().header("Access-Token", accessToken)
                .header("API-Key", "e8f3fdcc-8825-11e5-af63-feff819cdc9f")
                .get("/movies/graph")
                .peek().then()
                .assertThat().statusCode(200)
                .extract().response().asString()

        val dto = mapper.readValue(json, GraphDTO::class.java)
        assertNotNull(dto.nodes)
        assertNotNull(dto.links)
    }

    @Test
    fun shouldFindByTitle() {

        shouldSaveMovies()

        val accessToken: String = login("jasper@appsquick.ly", "password").accessToken

        val json = RestAssured.given().header("Access-Token", accessToken)
                .header("API-Key", "e8f3fdcc-8825-11e5-af63-feff819cdc9f")
                .param("title", "The Devil's Advocate")
                .get("/movies/by/title")
                .peek().then()
                .assertThat().statusCode(200)
                .extract().response().asString()

        val dto = mapper.readValue(json, MovieDTO::class.java)
        assertNotNull(dto)
        assertEquals("The Devil's Advocate", dto.title)

    }

    @Test
    fun shouldListByTitleContaining() {
        shouldSaveMovies()

        val accessToken: String = login("jasper@appsquick.ly", "password").accessToken

        RestAssured.given().header("Access-Token", accessToken)
                .header("API-Key", "e8f3fdcc-8825-11e5-af63-feff819cdc9f")
                .param("title", "The Devil's Advocate")
                .get("movies/by/title/containing")
                .peek().then()
                .assertThat().statusCode(200)
    }

}