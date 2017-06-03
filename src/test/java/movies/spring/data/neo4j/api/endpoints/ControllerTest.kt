package movies.spring.data.neo4j.api.endpoints

import com.fasterxml.jackson.databind.ObjectMapper
import com.jayway.restassured.RestAssured
import movies.spring.data.neo4j.ApplicationAssembly
import movies.spring.data.neo4j.api.service.authorization.AuthorizationDTO
import movies.spring.data.neo4j.api.service.authorization.CredentialsDTO
import org.junit.Assert
import org.junit.Before
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Transactional

@Transactional
@RunWith(SpringRunner::class)
@SpringBootTest(classes = arrayOf(ApplicationAssembly::class), webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
abstract class ControllerTest {

    @Autowired lateinit var mapper: ObjectMapper

    @LocalServerPort var port: Int? = null

    @Before
    fun setup() {
        RestAssured.baseURI = "http://127.0.0.1"
        RestAssured.port = port!!
    }

    fun login(userName: String, password: String): AuthorizationDTO {
        val testCredentials = mapper.writeValueAsString(CredentialsDTO(userName, password))

        println(testCredentials)

        val auth = RestAssured.given()
                .header("content-type", "application/json")
                .body(testCredentials)
                .post("/authorization").peek().asString()

        if (auth.contains("\"error\" : {")) {
            Assert.fail("Login failed.")
        }

        val authorization = mapper.readValue(auth, AuthorizationDTO::class.java)
        Assert.assertNotNull(authorization.accessToken)
        return authorization
    }


}