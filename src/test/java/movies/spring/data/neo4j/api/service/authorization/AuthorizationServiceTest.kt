package movies.spring.data.neo4j.api.service.authorization

import movies.spring.data.neo4j.ApplicationAssembly
import movies.spring.data.neo4j.exception.UnauthorizedException
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner


@RunWith(SpringRunner::class)
@SpringBootTest(classes = arrayOf(ApplicationAssembly::class),
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthorizationServiceTest
{

    @Autowired private lateinit var authService: AuthorizationService


    @Test
    fun shouldAuthorize_with_valid_credentials()
    {
        val request = CredentialsDTO("jasper@appsquick.ly", "password")
        val authorization = authService.authorize(request)

        Assert.assertNotNull(authorization.accessToken)


    }

    @Test(expected = UnauthorizedException::class)
    fun testAuthorize_with_invalid_credentials_raises_exception()
    {
        val request = CredentialsDTO("jasper@appsquick.ly", "invalid")
        val authorization = authService.authorize(request)
        Assert.fail("$authorization should have thrown exception")
    }
}