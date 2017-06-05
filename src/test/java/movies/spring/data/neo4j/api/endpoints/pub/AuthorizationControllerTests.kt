package movies.spring.data.neo4j.api.endpoints.pub

import com.jayway.restassured.RestAssured
import movies.spring.data.neo4j.api.endpoints.ControllerTest
import movies.spring.data.neo4j.api.service.authorization.dto.CredentialsDTO
import org.hamcrest.core.IsNull
import org.junit.Test


class AuthorizationControllerTests : ControllerTest()
{

    @Test
    fun authorize_withUserNameAndPassword()
    {

        val testCredentials = mapper.writeValueAsString(
                CredentialsDTO(email = "jasper@appsquick.ly", password = "password"))

        println(testCredentials)

        RestAssured.given()
                .header("content-type", "application/json")
                .body(testCredentials)
                .post("/authorization").peek().then()
                .body("accessToken", IsNull.notNullValue())
    }


}