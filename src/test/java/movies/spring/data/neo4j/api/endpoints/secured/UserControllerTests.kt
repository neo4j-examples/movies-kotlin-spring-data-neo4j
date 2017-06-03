package movies.spring.data.neo4j.api.endpoints.secured

import com.jayway.restassured.RestAssured
import movies.spring.data.neo4j.api.endpoints.ControllerTest
import org.hamcrest.core.IsNull
import org.junit.Test
import org.springframework.transaction.annotation.Transactional

class UserControllerTests : ControllerTest() {

    @Test
    fun getMyProfile() {
        val accessToken: String = login("jasper@appsquick.ly", "password").accessToken

        RestAssured.given().header("Access-Token", accessToken).
                header("API-Key", "e8f3fdcc-8825-11e5-af63-feff819cdc9f").
                get("/user/mine").
                peek().then().
                body("email", IsNull.notNullValue())

    }




}

