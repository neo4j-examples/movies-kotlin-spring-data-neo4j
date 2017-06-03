package movies.spring.data.neo4j.api.endpoints.secured

import com.jayway.restassured.RestAssured
import movies.spring.data.neo4j.api.endpoints.ControllerTest
import org.junit.Test


class MovieControllerTest : ControllerTest() {

    @Test
    fun shouldReturnGraph() {
        val accessToken: String = login("jasper@appsquick.ly", "password").accessToken

        RestAssured.given().header("Access-Token", accessToken)
                .header("API-Key", "e8f3fdcc-8825-11e5-af63-feff819cdc9f")
                .get("/graph")
                .peek().then()
                .assertThat().statusCode(200)
    }

}