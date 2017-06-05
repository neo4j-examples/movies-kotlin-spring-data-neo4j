package movies.spring.data.neo4j.api.endpoints.secured

import com.jayway.restassured.RestAssured
import movies.spring.data.neo4j.api.endpoints.ControllerTest
import org.hamcrest.core.IsNull
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.transaction.annotation.Transactional

class UserIntegrationTests : ControllerTest() {

    @Autowired @Value("@{api.key}") lateinit var apiKey: String

    @Test
    fun getMyUser() {
        val accessToken: String = login("jasper@appsquick.ly", "password").accessToken

        RestAssured.given().header("Access-Token", accessToken).
                header("API-Key", apiKey).
                get("/user/mine").
                peek().then().
                body("email", IsNull.notNullValue())

    }




}

